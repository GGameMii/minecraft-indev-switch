/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.BufferUtils
 *  org.lwjgl.input.Mouse
 */
package net.minecraft.client;

import java.awt.Component;
import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.input.Mouse;

public final class MouseHelper {
    private Component windowComponent;
    public int deltaX;
    public int deltaY;

    public MouseHelper(Component component) {
        this.windowComponent = component;
        IntBuffer component1 = BufferUtils.createIntBuffer((int)1);
        component1.put(0);
        component1.flip();
    }

    public void grabMouseCursor() {
        Mouse.setGrabbed((boolean)true);
        this.deltaX = 0;
        this.deltaY = 0;
    }

    public void ungrabMouseCursor() {
        Mouse.setCursorPosition((int)(this.windowComponent.getWidth() / 2), (int)(this.windowComponent.getHeight() / 2));
        Mouse.setGrabbed((boolean)false);
    }

    public void mouseXYChange() {
        this.deltaX = Mouse.getDX();
        this.deltaY = Mouse.getDY();
    }
}

