/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client.a.a;

import net.minecraft.a.a.b.x;
import net.minecraft.a.a.g;
import net.minecraft.a.c.b;
import net.minecraft.client.a.a.i;
import net.minecraft.client.a.d;
import net.minecraft.client.a.k;
import net.minecraft.client.b.h;
import org.lwjgl.opengl.GL11;

public abstract class f {
    protected i a;
    protected float b;
    protected float c;

    public f() {
        new h();
        new net.minecraft.client.a.f();
        this.b = 0.0f;
        this.c = 1.0f;
    }

    public abstract void a(b var1, float var2, float var3, float var4, float var5, float var6);

    protected final void a(String string) {
        k k2 = this.a.b;
        k.a(k2.a(string));
    }

    protected final void a(String string, String string2) {
        k k2 = this.a.b;
        k.a(k2.a(string, string2));
    }

    public static void a(net.minecraft.a.d.b b2) {
        GL11.glDisable((int)3553);
        d d2 = d.a;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        d2.b();
        d.c(0.0f, 0.0f, -1.0f);
        d2.b(b2.a, b2.e, b2.c);
        d2.b(b2.d, b2.e, b2.c);
        d2.b(b2.d, b2.b, b2.c);
        d2.b(b2.a, b2.b, b2.c);
        d.c(0.0f, 0.0f, 1.0f);
        d2.b(b2.a, b2.b, b2.f);
        d2.b(b2.d, b2.b, b2.f);
        d2.b(b2.d, b2.e, b2.f);
        d2.b(b2.a, b2.e, b2.f);
        d.c(0.0f, -1.0f, 0.0f);
        d2.b(b2.a, b2.b, b2.c);
        d2.b(b2.d, b2.b, b2.c);
        d2.b(b2.d, b2.b, b2.f);
        d2.b(b2.a, b2.b, b2.f);
        d.c(0.0f, 1.0f, 0.0f);
        d2.b(b2.a, b2.e, b2.f);
        d2.b(b2.d, b2.e, b2.f);
        d2.b(b2.d, b2.e, b2.c);
        d2.b(b2.a, b2.e, b2.c);
        d.c(-1.0f, 0.0f, 0.0f);
        d2.b(b2.a, b2.b, b2.f);
        d2.b(b2.a, b2.e, b2.f);
        d2.b(b2.a, b2.e, b2.c);
        d2.b(b2.a, b2.b, b2.c);
        d.c(1.0f, 0.0f, 0.0f);
        d2.b(b2.d, b2.b, b2.c);
        d2.b(b2.d, b2.e, b2.c);
        d2.b(b2.d, b2.e, b2.f);
        d2.b(b2.d, b2.b, b2.f);
        d2.a();
        GL11.glEnable((int)3553);
    }

    public final void a(i i2) {
        this.a = i2;
    }

    public final void a(b b2, float f2, float f3, float f4, float f5) {
        float f6;
        float f7;
        int n2;
        float f8;
        float f9;
        float f10;
        float f11;
        if (this.b > 0.0f) {
            float f12;
            f5 = this.a.a(f2, f3, f4);
            f5 = (1.0f - f5 / 256.0f) * this.c;
            if (f12 > 0.0f) {
                f11 = f5;
                f10 = f4;
                f9 = f3;
                float f13 = f2;
                f f14 = this;
                GL11.glEnable((int)3042);
                k k2 = f14.a.b;
                k.a(k2.a("%%/shadow.png"));
                f f15 = f14;
                g g2 = f15.a.c;
                GL11.glDepthMask((boolean)false);
                f8 = f14.b;
                for (n2 = (int)(f13 - f8); n2 <= (int)(f13 + f8); ++n2) {
                    for (int i2 = (int)(f9 - 2.0f); i2 <= (int)f9; ++i2) {
                        for (int i3 = (int)(f10 - f8); i3 <= (int)(f10 + f8); ++i3) {
                            int n3 = g2.a(n2, i2 - 1, i3);
                            if (n3 <= 0 || g2.d(n2, i2, i3) <= 3) continue;
                            float f16 = f8;
                            float f17 = f11;
                            int n4 = i3;
                            int n5 = i2;
                            int n6 = n2;
                            f7 = f10;
                            f6 = f9;
                            float f18 = f13;
                            x x2 = x.c[n3];
                            f f19 = f14;
                            d d2 = d.a;
                            float f20 = (f17 - (f6 - (float)n5) / 2.0f) * 0.5f * f19.a.c.c(n6, n5, n4);
                            if (f20 < 0.0f) continue;
                            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)f20);
                            d2.b();
                            f20 = (float)n6 + x2.au;
                            f6 = (float)n6 + x2.ax;
                            float f21 = (float)n5 + x2.av;
                            float f22 = (float)n4 + x2.aw;
                            float f23 = (float)n4 + x2.az;
                            float f24 = (f18 - f20) / 2.0f / f16 + 0.5f;
                            f18 = (f18 - f6) / 2.0f / f16 + 0.5f;
                            f17 = (f7 - f22) / 2.0f / f16 + 0.5f;
                            f7 = (f7 - f23) / 2.0f / f16 + 0.5f;
                            d2.a(f20, f21, f22, f24, f17);
                            d2.a(f20, f21, f23, f24, f7);
                            d2.a(f6, f21, f23, f18, f7);
                            d2.a(f6, f21, f22, f18, f17);
                            d2.a();
                        }
                    }
                }
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                GL11.glDisable((int)3042);
                GL11.glDepthMask((boolean)true);
            }
        }
        if (b2.J > 0) {
            f11 = f4;
            f10 = f3;
            f9 = f2;
            b b3 = b2;
            f f25 = this;
            GL11.glDisable((int)2896);
            int n7 = x.ag.as;
            n2 = (n7 & 0xF) << 4;
            int n8 = n7 & 0xF0;
            f8 = (float)n2 / 256.0f;
            float f26 = ((float)n2 + 15.99f) / 256.0f;
            float f27 = (float)n8 / 256.0f;
            float f28 = ((float)n8 + 15.99f) / 256.0f;
            GL11.glPushMatrix();
            GL11.glTranslatef((float)f9, (float)f10, (float)f11);
            float f29 = b3.w * 1.4f;
            GL11.glScalef((float)f29, (float)f29, (float)f29);
            f25.a("/terrain.png");
            d d3 = d.a;
            float f30 = 1.0f;
            f6 = 0.0f;
            f7 = b3.x / b3.w;
            GL11.glRotatef((float)(-f25.a.d), (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glTranslatef((float)0.0f, (float)0.0f, (float)(0.4f + (float)((int)f7) * 0.02f));
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            d3.b();
            while (f7 > 0.0f) {
                d3.a(f30 - 0.5f, 0.0f - f6, 0.0f, f26, f28);
                d3.a(-0.5f, 0.0f - f6, 0.0f, f8, f28);
                d3.a(-0.5f, 1.4f - f6, 0.0f, f8, f27);
                d3.a(f30 - 0.5f, 1.4f - f6, 0.0f, f26, f27);
                f7 -= 1.0f;
                f6 -= 1.0f;
                f30 *= 0.9f;
                GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-0.04f);
            }
            d3.a();
            GL11.glPopMatrix();
            GL11.glEnable((int)2896);
        }
    }
}

