/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.BufferUtils
 *  org.lwjgl.LWJGLException
 *  org.lwjgl.input.Cursor
 *  org.lwjgl.input.Mouse
 */
package net.minecraft.client;

import java.awt.AWTException;
import java.awt.Component;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.nio.Buffer;
import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Cursor;
import org.lwjgl.input.Mouse;

public final class m {
    private Component c;
    private Robot d;
    private int e;
    private int f;
    private Cursor g;
    public int a;
    public int b;
    private int h = 10;

    public m(Component object) {
        this.c = object;
        try {
            this.d = new Robot();
        }
        catch (AWTException aWTException) {
            object = aWTException;
            aWTException.printStackTrace();
        }
        object = BufferUtils.createIntBuffer((int)1);
        ((IntBuffer)object).put(0);
        ((Buffer)object).flip();
        IntBuffer intBuffer = BufferUtils.createIntBuffer((int)1024);
        try {
            this.g = new Cursor(32, 32, 16, 16, 1, intBuffer, (IntBuffer)object);
            return;
        }
        catch (LWJGLException lWJGLException) {
            object = lWJGLException;
            lWJGLException.printStackTrace();
            return;
        }
    }

    public final void a() {
        try {
            Mouse.setNativeCursor((Cursor)this.g);
        }
        catch (LWJGLException lWJGLException) {
            LWJGLException lWJGLException2 = lWJGLException;
            lWJGLException.printStackTrace();
        }
        this.b();
        this.a = 0;
        this.b = 0;
    }

    public final void b() {
        Point point = MouseInfo.getPointerInfo().getLocation();
        Point point2 = this.c.getLocationOnScreen();
        this.d.mouseMove(this.e, this.f);
        this.e = point2.x + this.c.getWidth() / 2;
        this.f = point2.y + this.c.getHeight() / 2;
        if (this.h == 0) {
            this.a = point.x - this.e;
            this.b = point.y - this.f;
            return;
        }
        this.b = 0;
        this.a = 0;
        --this.h;
    }
}

