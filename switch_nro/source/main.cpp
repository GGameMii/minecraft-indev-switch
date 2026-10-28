#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/stat.h>
#include <switch.h>
#include <cmath>
#include <vector>
#include <string>
#include <chrono>

#include <EGL/egl.h>
#include <EGL/eglext.h>
#include <glad/glad.h>
#include <AL/al.h>
#include <AL/alc.h>

#include "jruntime.hpp"
#include "glshim.hpp"
#include "texload.hpp"
#include "mcfont.hpp"
#include "title_screen.hpp"

// ============================================================
// Global State
// ============================================================
static PadState s_pad;
static java::SwitchInputBridge s_bridge;

static EGLDisplay s_display = EGL_NO_DISPLAY;
static EGLContext s_context = EGL_NO_CONTEXT;
static EGLSurface s_surface = EGL_NO_SURFACE;

static ALCdevice*  s_alDevice  = nullptr;
static ALCcontext* s_alContext = nullptr;

static const int SCR_W = 1280;
static const int SCR_H = 720;

// ============================================================
// EGL
// ============================================================
static bool initEgl(NWindow* win) {
    s_display = eglGetDisplay(EGL_DEFAULT_DISPLAY);
    if (!s_display) return false;
    eglInitialize(s_display, nullptr, nullptr);
    eglBindAPI(EGL_OPENGL_API);

    static const EGLint attribs[] = {
        EGL_RENDERABLE_TYPE, EGL_OPENGL_BIT,
        EGL_SURFACE_TYPE, EGL_WINDOW_BIT,
        EGL_RED_SIZE,   8, EGL_GREEN_SIZE, 8,
        EGL_BLUE_SIZE,  8, EGL_ALPHA_SIZE, 8,
        EGL_DEPTH_SIZE, 24, EGL_STENCIL_SIZE, 8,
        EGL_NONE
    };
    EGLConfig cfg; EGLint n;
    eglChooseConfig(s_display, attribs, &cfg, 1, &n);
    s_surface = eglCreateWindowSurface(s_display, cfg, win, nullptr);
    if (!s_surface) return false;

    static const EGLint ctxAttribs[] = {
        EGL_CONTEXT_OPENGL_PROFILE_MASK_KHR, EGL_CONTEXT_OPENGL_CORE_PROFILE_BIT_KHR,
        EGL_CONTEXT_MAJOR_VERSION_KHR, 4,
        EGL_CONTEXT_MINOR_VERSION_KHR, 3,
        EGL_NONE
    };
    s_context = eglCreateContext(s_display, cfg, EGL_NO_CONTEXT, ctxAttribs);
    if (!s_context) return false;

    eglMakeCurrent(s_display, s_surface, s_surface, s_context);
    gladLoadGL();
    eglSwapInterval(s_display, 1);
    printf("[GL] %s | %s\n", glGetString(GL_VERSION), glGetString(GL_RENDERER));
    return true;
}

static void deinitEgl() {
    if (s_display) {
        eglMakeCurrent(s_display, EGL_NO_SURFACE, EGL_NO_SURFACE, EGL_NO_CONTEXT);
        if (s_context) { eglDestroyContext(s_display, s_context); s_context = nullptr; }
        if (s_surface) { eglDestroySurface(s_display, s_surface); s_surface = nullptr; }
        eglTerminate(s_display); s_display = nullptr;
    }
}

// ============================================================
// OpenAL
// ============================================================
static void initOpenAL() {
    s_alDevice  = alcOpenDevice(nullptr);
    if (!s_alDevice) return;
    s_alContext = alcCreateContext(s_alDevice, nullptr);
    if (s_alContext) alcMakeContextCurrent(s_alContext);
}

static void deinitOpenAL() {
    if (s_alContext) { alcMakeContextCurrent(nullptr); alcDestroyContext(s_alContext); s_alContext = nullptr; }
    if (s_alDevice)  { alcCloseDevice(s_alDevice); s_alDevice = nullptr; }
}

// ============================================================
// Input polling
// ============================================================
// Previous button state for edge detection
static u64 s_prevButtons = 0;
static bool s_prevTouch = false;

static void pollInputs() {
    s_prevButtons = padGetButtons(&s_pad);
    s_prevTouch   = s_bridge.touching;

    padUpdate(&s_pad);
    u64 kDown = padGetButtons(&s_pad);

    s_bridge.btnA      = (kDown & HidNpadButton_A)     != 0;
    s_bridge.btnB      = (kDown & HidNpadButton_B)     != 0;
    s_bridge.btnX      = (kDown & HidNpadButton_X)     != 0;
    s_bridge.btnY      = (kDown & HidNpadButton_Y)     != 0;
    s_bridge.btnL      = (kDown & HidNpadButton_L)     != 0;
    s_bridge.btnR      = (kDown & HidNpadButton_R)     != 0;
    s_bridge.btnZL     = (kDown & HidNpadButton_ZL)    != 0;
    s_bridge.btnZR     = (kDown & HidNpadButton_ZR)    != 0;
    s_bridge.btnPlus   = (kDown & HidNpadButton_Plus)  != 0;
    s_bridge.btnMinus  = (kDown & HidNpadButton_Minus) != 0;
    s_bridge.dpadUp    = (kDown & HidNpadButton_Up)    != 0;
    s_bridge.dpadDown  = (kDown & HidNpadButton_Down)  != 0;
    s_bridge.dpadLeft  = (kDown & HidNpadButton_Left)  != 0;
    s_bridge.dpadRight = (kDown & HidNpadButton_Right) != 0;

    HidAnalogStickState ls = padGetStickPos(&s_pad, 0);
    HidAnalogStickState rs = padGetStickPos(&s_pad, 1);
    s_bridge.leftStickX  = ls.x / 32767.0f;
    s_bridge.leftStickY  = ls.y / 32767.0f;
    s_bridge.rightStickX = rs.x / 32767.0f;
    s_bridge.rightStickY = rs.y / 32767.0f;

    HidTouchScreenState touch;
    if (hidGetTouchScreenStates(&touch, 1) > 0 && touch.count > 0) {
        s_bridge.touching = true;
        s_bridge.touchX = touch.touches[0].x;
        s_bridge.touchY = touch.touches[0].y;
    } else {
        s_bridge.touching = false;
    }
}

[[maybe_unused]] static bool justTapped() {
    return !s_prevTouch && s_bridge.touching;
}

static bool justReleased() {
    return s_prevTouch && !s_bridge.touching;
}

// ============================================================
// Options persistence (read/write player name from options.txt)
// ============================================================
static std::string s_playerName = "Steve";

static void loadOptions() {
    FILE* fp = fopen("sdmc:/switch/minecraft_indev/options.txt", "r");
    if (!fp) return;
    char line[256];
    while (fgets(line, sizeof(line), fp)) {
        std::string s(line);
        if (s.rfind("username:", 0) == 0) {
            s_playerName = s.substr(9);
            while (!s_playerName.empty() && (s_playerName.back() == '\n' || s_playerName.back() == '\r'))
                s_playerName.pop_back();
        }
    }
    fclose(fp);
}

static void saveOptions() {
    FILE* fp = fopen("sdmc:/switch/minecraft_indev/options.txt", "w");
    if (!fp) return;
    fprintf(fp, "username:%s\n", s_playerName.c_str());
    fclose(fp);
}

// ============================================================
// Main
// ============================================================
int main(int argc, char* argv[]) {
    // 1. Init Switch services
    romfsInit();
    socketInitializeDefault();
    mkdir("sdmc:/switch", 0777);
    mkdir("sdmc:/switch/minecraft_indev", 0777);
    mkdir("sdmc:/switch/minecraft_indev/saves", 0777);

    padConfigureInput(1, HidNpadStyleSet_NpadStandard);
    padInitializeDefault(&s_pad);
    hidInitializeTouchScreen();

    loadOptions();

    // 2. Init display
    NWindow* win = nwindowGetDefault();
    nwindowSetDimensions(win, SCR_W, SCR_H);
    if (!initEgl(win)) {
        printf("[FATAL] EGL init failed\n");
        return -1;
    }

    // 3. Init audio
    initOpenAL();

    // 4. Init GL shim
    glshim::GL11Shim& gl = glshim::GL11Shim::get();
    gl.init();

    glViewport(0, 0, SCR_W, SCR_H);
    gl.matrixMode(1); // Projection
    gl.loadIdentity();
    gl.ortho(0.0f, (float)SCR_W, (float)SCR_H, 0.0f, -1.0f, 1.0f);
    gl.matrixMode(0); // ModelView
    gl.loadIdentity();

    glEnable(GL_BLEND);
    glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

    // 5. Load assets from romfs
    // romfsInit maps romfs:/ to the embedded RomFS
    mcfont::MCFont font;
    font.load("romfs:/default.png");

    GLuint logoTex = texload::TextureManager::get().getOrLoad("romfs:/gui/logo.png");
    GLuint dirtTex = texload::TextureManager::get().getOrLoad("romfs:/dirt.png");
    // gui.png used for button backgrounds (not strictly needed with our drawn buttons)

    // 6. Init Title Screen
    TitleScreen titleScreen;
    titleScreen.init((float)SCR_W, (float)SCR_H, &font, logoTex, dirtTex, s_playerName);

    // Virtual cursor position (starts in center)
    float cursorX = SCR_W / 2.0f;
    float cursorY = SCR_H / 2.0f;

    // Timing
    auto lastTime = std::chrono::high_resolution_clock::now();

    printf("[OK] Minecraft Indev Switch - Title Screen running\n");

    // ============================================================
    // Main Game Loop
    // ============================================================
    while (appletMainLoop()) {
        pollInputs();

        // Delta time
        auto now = std::chrono::high_resolution_clock::now();
        float dt = std::chrono::duration<float>(now - lastTime).count();
        lastTime = now;
        if (dt > 0.1f) dt = 0.1f; // cap to avoid spiral of death

        // + to quit
        if (s_bridge.btnPlus) break;

        // --- Cursor movement ---
        // Touch takes priority and sets cursor directly
        if (s_bridge.touching) {
            cursorX = (float)s_bridge.touchX;
            cursorY = (float)s_bridge.touchY;
        } else {
            // Left stick: speed proportional to deflection (pixels per second)
            float speed = 600.0f * dt;
            // Apply deadzone of 0.15
            float lx = s_bridge.leftStickX;
            float ly = s_bridge.leftStickY;
            if (lx > -0.15f && lx < 0.15f) lx = 0.0f;
            if (ly > -0.15f && ly < 0.15f) ly = 0.0f;
            cursorX += lx * speed;
            cursorY -= ly * speed; // Y axis inverted (stick up = cursor up = less Y)

            // D-Pad: fixed step per frame
            if (s_bridge.dpadLeft)  cursorX -= speed * 0.6f;
            if (s_bridge.dpadRight) cursorX += speed * 0.6f;
            if (s_bridge.dpadUp)    cursorY -= speed * 0.6f;
            if (s_bridge.dpadDown)  cursorY += speed * 0.6f;
        }

        // Clamp cursor to screen
        cursorX = std::max(0.0f, std::min((float)(SCR_W - 1), cursorX));
        cursorY = std::max(0.0f, std::min((float)(SCR_H - 1), cursorY));

        titleScreen.updateCursor(cursorX, cursorY);

        // --- Click detection ---
        // Touch: fire on finger-lift (release)
        bool touchClick = justReleased();
        // Controller: A button pressed this frame (using padGetButtonsDown for edge)
        bool padClick = (padGetButtonsDown(&s_pad) & HidNpadButton_A) != 0;
        bool doClick = touchClick || padClick;

        if (doClick) {
            TitleScreen::Action act = titleScreen.onClick(cursorX, cursorY);
            if (act == TitleScreen::QUIT) break;
            if (act == TitleScreen::OPTIONS) {
                std::string newName = java::SwitchInputBridge::openKeyboard(
                    "Enter your player name", s_playerName, 16);
                if (!newName.empty()) {
                    s_playerName = newName;
                    titleScreen.playerName = newName;
                    saveOptions();
                }
            }
            // TODO: NEW_WORLD, LOAD_WORLD, MULTIPLAYER → next screens
        }

        // ---- Render ----
        glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        gl.matrixMode(0);
        gl.loadIdentity();

        titleScreen.render(gl, 0, dt);

        // Always draw cursor (cross-hair) — white when pad/dpad, cyan when touch
        float cr = s_bridge.touching ? 0.0f : 1.0f;
        float cg = 1.0f;
        float cb = s_bridge.touching ? 1.0f : 1.0f;
        drawRect(gl, cursorX - 1.0f, cursorY - 8.0f, 3.0f, 16.0f, cr, cg, cb, 0.9f);
        drawRect(gl, cursorX - 8.0f, cursorY - 1.0f, 16.0f, 3.0f, cr, cg, cb, 0.9f);

        eglSwapBuffers(s_display, s_surface);
    }

    saveOptions();
    deinitOpenAL();
    deinitEgl();
    socketExit();
    romfsExit();
    return 0;
}
