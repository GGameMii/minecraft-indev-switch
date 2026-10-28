package net.minecraft.client.controller;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Screen;
import net.minecraft.client.gui.inventory.ContainerScreen;
import net.minecraft.client.gui.inventory.InventoryScreen;
import net.minecraft.client.player.MovementInputFromOptions;
import net.minecraft.client.player.PlayerSP;
import net.minecraft.world.phys.HitResult;
import org.lwjgl.input.Controller;
import org.lwjgl.input.Controllers;
import org.lwjgl.input.Mouse;

public class GamepadController {

    public interface NativePadHandler {
        boolean isConnected();
        boolean isButtonDown(int button);
        boolean isButtonJustPressed(int button);
        float getLeftStickX();
        float getLeftStickY();
        float getRightStickX();
        float getRightStickY();
    }

    public static final int BTN_A = 0;
    public static final int BTN_B = 1;
    public static final int BTN_X = 2;
    public static final int BTN_Y = 3;
    public static final int BTN_L = 4;
    public static final int BTN_R = 5;
    public static final int BTN_ZL = 6;
    public static final int BTN_ZR = 7;
    public static final int BTN_PLUS = 8;
    public static final int BTN_MINUS = 9;
    public static final int BTN_LSTICK = 10;
    public static final int BTN_RSTICK = 11;
    public static final int BTN_DUP = 12;
    public static final int BTN_DDOWN = 13;
    public static final int BTN_DLEFT = 14;
    public static final int BTN_DRIGHT = 15;

    private static NativePadHandler nativeHandler = null;

    private final Minecraft mc;
    private Controller lwjglController = null;
    private boolean lwjglInitAttempted = false;

    // Analog stick states (-1.0 to 1.0)
    private float lx, ly, rx, ry;

    // Virtual cursor for menus
    private float curX, curY;
    private boolean cursorInit = false;
    private long lastCursorNanos = 0L;
    private float cursorSpeed = 1.6f;

    // Mining / Breaking
    private boolean breakHeld = false;
    private int breakTicks = 0;
    private boolean placeHeld = false;
    private int placeTicks = 0;

    // Button states
    private final boolean[] curButtons = new boolean[16];
    private final boolean[] prevButtons = new boolean[16];

    public GamepadController(Minecraft mc) {
        this.mc = mc;
    }

    public static void setNativeHandler(NativePadHandler handler) {
        nativeHandler = handler;
    }

    public void init() {
        if (nativeHandler == null && !lwjglInitAttempted) {
            lwjglInitAttempted = true;
            try {
                if (!Controllers.isCreated()) {
                    Controllers.create();
                }
                int count = Controllers.getControllerCount();
                for (int i = 0; i < count; i++) {
                    Controller c = Controllers.getController(i);
                    if (c != null && c.getButtonCount() >= 4) {
                        lwjglController = c;
                        System.out.println("[Gamepad] Selected LWJGL controller: " + c.getName());
                        break;
                    }
                }
            } catch (Throwable t) {
                System.out.println("[Gamepad] Note: LWJGL controller init skipped/unavailable: " + t.getMessage());
            }
        }
    }

    public void update() {
        init();
        poll();

        Screen screen = mc.screen;
        if (screen != null) {
            updateMenu(screen);
        } else {
            updateWorld();
        }
    }

    private void poll() {
        for (int i = 0; i < 16; i++) {
            prevButtons[i] = curButtons[i];
        }

        if (nativeHandler != null && nativeHandler.isConnected()) {
            lx = applyDeadzone(nativeHandler.getLeftStickX());
            ly = applyDeadzone(nativeHandler.getLeftStickY());
            rx = applyDeadzone(nativeHandler.getRightStickX());
            ry = applyDeadzone(nativeHandler.getRightStickY());

            for (int i = 0; i < 16; i++) {
                curButtons[i] = nativeHandler.isButtonDown(i);
            }
            return;
        }

        if (lwjglController != null) {
            try {
                lwjglController.poll();
                lx = applyDeadzone(lwjglController.getXAxisValue());
                ly = applyDeadzone(lwjglController.getYAxisValue());

                // Axis 2 / 3 for right stick in LWJGL
                if (lwjglController.getAxisCount() >= 4) {
                    rx = applyDeadzone(lwjglController.getAxisValue(2));
                    ry = applyDeadzone(lwjglController.getAxisValue(3));
                }

                // Map basic buttons
                int btnCount = Math.min(16, lwjglController.getButtonCount());
                for (int i = 0; i < btnCount; i++) {
                    curButtons[i] = lwjglController.isButtonPressed(i);
                }
            } catch (Throwable ignored) {
            }
        }
    }

    private float applyDeadzone(float val) {
        float deadzone = 0.20f;
        float abs = Math.abs(val);
        if (abs < deadzone) return 0.0f;
        float norm = (abs - deadzone) / (1.0f - deadzone);
        return Math.signum(val) * norm;
    }

    public boolean isDown(int btn) {
        return btn >= 0 && btn < 16 && curButtons[btn];
    }

    public boolean justPressed(int btn) {
        return btn >= 0 && btn < 16 && curButtons[btn] && !prevButtons[btn];
    }

    private void updateMenu(Screen screen) {
        MovementInputFromOptions.padForward = 0.0f;
        MovementInputFromOptions.padStrafe = 0.0f;
        MovementInputFromOptions.padJump = false;

        long now = System.nanoTime();
        float dt = lastCursorNanos == 0L ? 0.0f : (float) (now - lastCursorNanos) / 1.0E9f;
        lastCursorNanos = now;
        if (dt > 0.1f) dt = 0.1f;

        if (!cursorInit) {
            curX = Mouse.getX();
            curY = Mouse.getY();
            cursorInit = true;
        }

        float sx = lx + rx * 0.45f;
        float sy = ly + ry * 0.45f;
        if (sx != 0.0f || sy != 0.0f) {
            float speed = cursorSpeed * Math.max(1, mc.height) * 1.1f * dt;
            curX += sx * speed;
            curY -= sy * speed; // OpenGL coords are bottom-left

            curX = Math.max(0, Math.min(mc.width - 1, curX));
            curY = Math.max(0, Math.min(mc.height - 1, curY));

            try {
                Mouse.setCursorPosition((int) curX, (int) curY);
            } catch (Throwable ignored) {
            }
        }

        int guiX = (int) (curX * screen.width / Math.max(1, mc.width));
        int guiY = (int) (screen.height - curY * screen.height / Math.max(1, mc.height) - 1);

        // A button = Click
        if (justPressed(BTN_A)) {
            screen.sendClick(guiX, guiY, 0);
        }
        if (justPressed(BTN_X)) {
            // Right click / Split stack
            screen.sendClick(guiX, guiY, 1);
        }

        // B button = Back / Close Menu
        if (justPressed(BTN_B)) {
            screen.sendKey('\0', 1); // 1 = ESC
        }
    }

    private void updateWorld() {
        cursorInit = false;
        lastCursorNanos = 0L;

        PlayerSP player = mc.player;
        if (player == null || mc.level == null) return;

        // Camera Look (Right stick)
        if (rx != 0.0f || ry != 0.0f) {
            float sens = (mc.options.sliderMouse != null ? mc.options.sliderMouse.value : 0.5f) * 0.6f + 0.2f;
            float lookSpeed = sens * sens * sens * 14.0f;
            player.interpolateTurn(rx * lookSpeed, -ry * lookSpeed);
        }

        // Movement (Left stick)
        float forward = -ly;
        float strafe = lx;
        MovementInputFromOptions.padForward = Math.abs(forward) > 0.20f ? forward : 0.0f;
        MovementInputFromOptions.padStrafe = Math.abs(strafe) > 0.20f ? strafe : 0.0f;
        MovementInputFromOptions.padJump = isDown(BTN_A);

        // Inventory
        if (justPressed(BTN_X) || justPressed(BTN_Y)) {
            mc.setScreen(new InventoryScreen(player));
            return;
        }

        // Pause
        if (justPressed(BTN_PLUS)) {
            mc.displayInGameMenu();
            return;
        }

        // Hotbar cycle (L and R)
        if (justPressed(BTN_L)) {
            player.inventory.currentItem = (player.inventory.currentItem + 8) % 9;
        }
        if (justPressed(BTN_R)) {
            player.inventory.currentItem = (player.inventory.currentItem + 1) % 9;
        }

        // Drop item
        if (justPressed(BTN_DDOWN)) {
            player.dropCurrentItem();
        }

        // Perspective toggle
        if (justPressed(BTN_RSTICK)) {
            mc.options.thirdPersonView = !mc.options.thirdPersonView;
        }

        // Mining / Break (ZR)
        boolean breakDown = isDown(BTN_ZR);
        if (justPressed(BTN_ZR)) {
            if (!mc.mouseGrabbed) mc.grabMouse();
            breakHeld = true;
            breakTicks = 0;
            mc.clickMouse(0);
        }
        if (breakHeld && !breakDown) {
            breakHeld = false;
            mc.gamemode.resetBlockRemoving();
        } else if (breakHeld) {
            if (mc.gamemode.creativeMode) {
                if (++breakTicks >= 5) {
                    breakTicks = 0;
                    mc.clickMouse(0);
                }
            } else {
                if (++breakTicks >= 5) {
                    breakTicks = 0;
                    player.swingItem();
                }
                HitResult hr = mc.hitResult;
                if (hr != null && hr.typeOfHit == 0) {
                    mc.gamemode.sendBlockRemoving(hr.x, hr.y, hr.z, hr.f);
                    mc.particles.addBlockHitEffects(hr.x, hr.y, hr.z, hr.f);
                } else {
                    mc.gamemode.resetBlockRemoving();
                }
            }
        }

        // Place / Use item (ZL)
        boolean placeDown = isDown(BTN_ZL);
        if (justPressed(BTN_ZL)) {
            if (!mc.mouseGrabbed) mc.grabMouse();
            placeHeld = true;
            placeTicks = 0;
            mc.clickMouse(1);
        }
        if (placeHeld && !placeDown) {
            placeHeld = false;
        } else if (placeHeld) {
            if (++placeTicks >= 4) {
                placeTicks = 0;
                mc.clickMouse(1);
            }
        }
    }
}
