package net.minecraft.client.controller;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Screen;
import org.lwjgl.input.Mouse;

public class TouchController {
    public interface NativeTouchHandler {
        boolean isTouched();
        int getTouchX(); // 0 to screen width
        int getTouchY(); // 0 to screen height
    }

    private static NativeTouchHandler nativeHandler = null;

    private final Minecraft mc;
    private boolean wasDown = false;
    private int lastX = -1;
    private int lastY = -1;

    public TouchController(Minecraft mc) {
        this.mc = mc;
    }

    public static void setNativeHandler(NativeTouchHandler handler) {
        nativeHandler = handler;
    }

    public void update() {
        if (nativeHandler == null) {
            return;
        }

        boolean isDown = nativeHandler.isTouched();
        int rawX = nativeHandler.getTouchX();
        int rawY = nativeHandler.getTouchY();

        Screen screen = mc.screen;
        if (screen != null) {
            int displayW = Math.max(1, mc.width);
            int displayH = Math.max(1, mc.height);
            int guiX = rawX * screen.width / displayW;
            int guiY = rawY * screen.height / displayH;

            if (isDown && !wasDown) {
                // Touch pressed -> Mouse click
                try {
                    Mouse.setCursorPosition(rawX, displayH - rawY - 1);
                } catch (Throwable ignored) {
                }
                screen.sendClick(guiX, guiY, 0);
            } else if (isDown && wasDown) {
                // Touch dragged -> Mouse moved
                if (guiX != lastX || guiY != lastY) {
                    try {
                        Mouse.setCursorPosition(rawX, displayH - rawY - 1);
                    } catch (Throwable ignored) {
                    }
                    screen.sendRelease(guiX, guiY, 0);
                }
            } else if (!isDown && wasDown) {
                // Touch released -> Mouse up
                screen.sendRelease(guiX, guiY, 0);
            }

            this.lastX = guiX;
            this.lastY = guiY;
        }

        this.wasDown = isDown;
    }
}
