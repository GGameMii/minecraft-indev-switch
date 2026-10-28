#pragma once
#include <glad/glad.h>
#include <string>
#include <vector>
#include "glshim.hpp"
#include "texload.hpp"
#include "mcfont.hpp"

// Button state
struct Button {
    int id;
    float x, y, w, h;
    std::string label;
    bool enabled = true;
    bool hovered = false;

    bool contains(float px, float py) const {
        return px >= x && px < x + w && py >= y && py < y + h;
    }
};

// Simple 2D textured quad draw
static void drawTexturedQuad(glshim::GL11Shim& gl, GLuint tex,
    float sx, float sy, float sw, float sh,
    float u0, float v0, float u1, float v1,
    float r = 1.0f, float g = 1.0f, float b = 1.0f, float a = 1.0f)
{
    gl.enableTexture2D(true);
    glBindTexture(GL_TEXTURE_2D, tex);
    gl.color4f(r, g, b, a);
    gl.begin(7);
        gl.texCoord2f(u0, v0); gl.vertex3f(sx,      sy,      0);
        gl.texCoord2f(u1, v0); gl.vertex3f(sx + sw, sy,      0);
        gl.texCoord2f(u1, v1); gl.vertex3f(sx + sw, sy + sh, 0);
        gl.texCoord2f(u0, v1); gl.vertex3f(sx,      sy + sh, 0);
    gl.end();
    gl.enableTexture2D(false);
}

static void drawRect(glshim::GL11Shim& gl, float x, float y, float w, float h,
    float r, float g, float b, float a = 1.0f)
{
    gl.enableTexture2D(false);
    gl.color4f(r, g, b, a);
    gl.begin(7);
        gl.vertex3f(x,     y,     0);
        gl.vertex3f(x + w, y,     0);
        gl.vertex3f(x + w, y + h, 0);
        gl.vertex3f(x,     y + h, 0);
    gl.end();
}

static void drawButton(glshim::GL11Shim& gl, GLuint guiTex,
    mcfont::MCFont& font, const Button& btn, float screenW, float screenH)
{
    // Button is drawn with solid rects and font text
    float bx = btn.x, by = btn.y, bw = btn.w, bh = btn.h;

    // Darken if disabled
    float tint = btn.enabled ? 1.0f : 0.5f;
    float tintH = (btn.hovered && btn.enabled) ? 1.2f : tint;

    // Background gradient: dark stone-like
    drawRect(gl, bx, by, bw, bh, 0.0f, 0.0f, 0.0f, 0.6f);

    // Border highlight
    drawRect(gl, bx, by, bw, 1.0f, tintH, tintH, tintH, 0.4f);
    drawRect(gl, bx, by + bh - 1, bw, 1.0f, tintH * 0.5f, tintH * 0.5f, tintH * 0.5f, 0.4f);
    drawRect(gl, bx, by, 1.0f, bh, tintH, tintH, tintH, 0.4f);
    drawRect(gl, bx + bw - 1, by, 1.0f, bh, tintH * 0.5f, tintH * 0.5f, tintH * 0.5f, 0.4f);

    // Label text
    int col = btn.enabled ? (btn.hovered ? 0xFFFFA0 : 0xE0E0E0) : 0x707070;
    font.drawCentered(btn.label, bx + bw * 0.5f, by + bh * 0.5f - 4.0f, col, true, 1.0f);
}

// ============================================================
// Title Screen
// ============================================================
class TitleScreen {
public:
    mcfont::MCFont* font;
    GLuint logoTex = 0;
    GLuint dirtTex = 0;
    float screenW, screenH;
    float t = 0.0f;

    // Splash texts
    const char* splash = "Multiplayer!";

    std::vector<Button> buttons;
    int hoveredBtn = -1;

    // Outputs
    enum Action { NONE, NEW_WORLD, LOAD_WORLD, MULTIPLAYER, OPTIONS, QUIT };
    Action pendingAction = NONE;

    std::string playerName = "Steve";

    void init(float w, float h, mcfont::MCFont* f, GLuint logo, GLuint dirt, const std::string& name) {
        screenW = w; screenH = h; font = f; logoTex = logo; dirtTex = dirt;
        playerName = name;
        buttons.clear();

        float cx = w / 2.0f;
        float bw = 200.0f, bh = 20.0f;
        float startY = h / 4.0f + 48.0f;

        buttons.push_back({0, cx - bw/2.0f, startY,       bw, bh, "Singleplayer"});
        buttons.push_back({1, cx - bw/2.0f, startY + 24,  bw, bh, "Multiplayer"});
        buttons.push_back({2, cx - bw/2.0f, startY + 48,  bw, bh, "Options..."});
        buttons.push_back({3, cx - bw/2.0f, startY + 72,  bw, bh, "Quit Game"});
    }

    void updateCursor(float cx, float cy) {
        hoveredBtn = -1;
        for (int i = 0; i < (int)buttons.size(); i++) {
            buttons[i].hovered = buttons[i].contains(cx, cy);
            if (buttons[i].hovered) hoveredBtn = i;
        }
    }

    Action onClick(float cx, float cy) {
        for (auto& btn : buttons) {
            if (!btn.enabled) continue;
            if (btn.contains(cx, cy)) {
                switch (btn.id) {
                    case 0: return NEW_WORLD;
                    case 1: return MULTIPLAYER;
                    case 2: return OPTIONS;
                    case 3: return QUIT;
                }
            }
        }
        return NONE;
    }

    void render(glshim::GL11Shim& gl, GLuint guiTex, float dt) {
        t += dt;

        // Dirt background (tiled)
        if (dirtTex) {
            float ts = 32.0f; // tile size in screen pixels
            int tilesX = (int)(screenW / ts) + 1;
            int tilesY = (int)(screenH / ts) + 1;
            for (int ty = 0; ty < tilesY; ty++) {
                for (int tx = 0; tx < tilesX; tx++) {
                    drawTexturedQuad(gl, dirtTex,
                        tx * ts, ty * ts, ts, ts,
                        0.0f, 0.0f, 1.0f, 1.0f,
                        0.25f, 0.25f, 0.25f); // darkened
                }
            }
        } else {
            // Fallback dark gradient
            drawRect(gl, 0, 0, screenW, screenH * 0.5f, 0.05f, 0.05f, 0.20f);
            drawRect(gl, 0, screenH * 0.5f, screenW, screenH * 0.5f, 0.08f, 0.08f, 0.30f);
        }

        // Logo
        if (logoTex) {
            float logoW = 256.0f, logoH = 44.0f;
            float lx = (screenW - logoW) * 0.5f;
            float ly = 30.0f;
            // Top half of logo.png (rows 0-44 of a 256-pixel wide texture)
            drawTexturedQuad(gl, logoTex, lx, ly, logoW, logoH,
                0.0f, 0.0f, 1.0f, 44.0f / 256.0f);
        }

        // Splash text (animated)
        if (font) {
            float splashScale = 1.8f - std::fabs(std::sin(t * 3.14159f * 2.0f) * 0.1f);
            float sx = screenW * 0.5f + 90.0f;
            float sy = 70.0f;
            font->drawCentered(splash, sx, sy, 0xFFFF00, true, splashScale);
        }

        // Buttons
        for (auto& btn : buttons) {
            drawButton(gl, guiTex, *font, btn, screenW, screenH);
        }

        // Player name (bottom left, green)
        if (font) {
            std::string pname = "Player: " + playerName;
            font->draw(pname, 2.0f, screenH - 10.0f, 0x00FF88, true);
        }

        // Copyright (bottom right)
        if (font) {
            std::string copy = "Copyright Mojang Specifications.";
            font->draw(copy, screenW - font->textWidth(copy) - 2.0f, screenH - 10.0f, 0xFFFFFF);
        }

        // Version
        if (font) {
            font->draw("Indev 0.31 MP (Switch)", 2.0f, 2.0f, 0xFFFFFF);
        }
    }
};
