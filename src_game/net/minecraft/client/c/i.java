/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.input.Keyboard
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client.c;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.c.j;
import net.minecraft.client.c.k;
import net.minecraft.client.c.r;
import net.minecraft.client.d;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class i
extends k {
    protected d b;
    public int c;
    public int d;
    protected List e = new ArrayList();
    public boolean f = false;
    protected j g;

    public void a(int n2, int n3, float f2) {
        for (int i2 = 0; i2 < this.e.size(); ++i2) {
            r r2 = (r)this.e.get(i2);
            r2.a(this.b, n2, n3);
        }
    }

    protected void a(char c2, int n2) {
        if (n2 == 1) {
            this.b.a((i)null);
            this.b.b();
        }
    }

    protected void a(int n2, int n3, int n4) {
        if (n4 == 0) {
            for (n4 = 0; n4 < this.e.size(); ++n4) {
                r r2 = (r)this.e.get(n4);
                if (!r2.a(n2, n3)) continue;
                this.b.x.a("random.click", 1.0f, 1.0f);
                this.a(r2);
            }
        }
    }

    protected void a(r r2) {
    }

    public final void a(d d2, int n2, int n3) {
        this.b = d2;
        this.g = d2.n;
        this.c = n2;
        this.d = n3;
        this.b();
    }

    public void b() {
    }

    public final void f() {
        if (Mouse.getEventButtonState()) {
            int n2 = Mouse.getEventX() * this.c / this.b.b;
            int n3 = this.d - Mouse.getEventY() * this.d / this.b.c - 1;
            this.a(n2, n3, Mouse.getEventButton());
            return;
        }
        Mouse.getEventX();
        Mouse.getEventY();
        Mouse.getEventButton();
    }

    public final void g() {
        if (Keyboard.getEventKeyState()) {
            if (Keyboard.getEventKey() == 87) {
                this.b.d();
                return;
            }
            this.a(Keyboard.getEventCharacter(), Keyboard.getEventKey());
        }
    }

    public void f_() {
    }

    public void a() {
    }

    public final void h() {
        boolean bl = false;
        i i2 = this;
        if (i2.b.d != null) {
            i.a(0, 0, i2.c, i2.d, 0x60050500, -1607454624);
            return;
        }
        GL11.glDisable((int)2896);
        GL11.glDisable((int)2912);
        net.minecraft.client.a.d d2 = net.minecraft.client.a.d.a;
        GL11.glBindTexture((int)3553, (int)i2.b.m.a("/dirt.png"));
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        d2.b();
        d2.b(0x404040);
        d2.a(0.0f, i2.d, 0.0f, 0.0f, (float)i2.d / 32.0f);
        d2.a(i2.c, i2.d, 0.0f, (float)i2.c / 32.0f, (float)i2.d / 32.0f);
        d2.a(i2.c, 0.0f, 0.0f, (float)i2.c / 32.0f, 0.0f);
        d2.a(0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        d2.a();
    }

    public boolean c() {
        return true;
    }
}

