/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client.a;

import net.minecraft.a.a.b.x;
import net.minecraft.a.b.k;
import net.minecraft.client.a.a.i;
import net.minecraft.client.a.d;
import net.minecraft.client.a.f;
import net.minecraft.client.c;
import net.minecraft.client.g.a;
import org.lwjgl.opengl.GL11;

public final class g {
    private net.minecraft.client.d a;
    private k b = null;
    private float c = 0.0f;
    private float d = 0.0f;
    private int e = 0;
    private boolean f = false;
    private f g = new f();

    public g(net.minecraft.client.d d2) {
        this.a = d2;
    }

    public final void a(float f2) {
        float f3 = this.d + (this.c - this.d) * f2;
        a a2 = this.a.f;
        GL11.glPushMatrix();
        GL11.glRotatef((float)(a2.q + (a2.o - a2.q) * f2), (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glRotatef((float)(a2.p + (a2.n - a2.p) * f2), (float)0.0f, (float)1.0f, (float)0.0f);
        net.minecraft.client.c.b();
        GL11.glPopMatrix();
        float f4 = this.a.d.c((int)a2.h, (int)a2.i, (int)a2.j);
        GL11.glColor4f((float)f4, (float)f4, (float)f4, (float)1.0f);
        if (this.b != null) {
            float f5;
            float f6;
            GL11.glPushMatrix();
            if (this.f) {
                f4 = ((float)this.e + f2) / 8.0f;
                f6 = a.a.a(f4 * (float)Math.PI);
                f5 = a.a.a(a.a.c(f4) * (float)Math.PI);
                GL11.glTranslatef((float)(-f5 * 0.4f), (float)(a.a.a(a.a.c(f4) * (float)Math.PI * 2.0f) * 0.2f), (float)(-f6 * 0.2f));
            }
            GL11.glTranslatef((float)0.56f, (float)(-0.52f - (1.0f - f3) * 0.6f), (float)-0.71999997f);
            GL11.glRotatef((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glEnable((int)2977);
            if (this.f) {
                f4 = ((float)this.e + f2) / 8.0f;
                f6 = a.a.a(f4 * f4 * (float)Math.PI);
                f5 = a.a.a(a.a.c(f4) * (float)Math.PI);
                GL11.glRotatef((float)(-f6 * 20.0f), (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glRotatef((float)(-f5 * 20.0f), (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glRotatef((float)(-f5 * 80.0f), (float)1.0f, (float)0.0f, (float)0.0f);
            }
            GL11.glScalef((float)0.4f, (float)0.4f, (float)0.4f);
            if (this.b.c < 256 && x.c[this.b.c].a() == 0) {
                GL11.glBindTexture((int)3553, (int)this.a.m.a("/terrain.png"));
                this.g.a(x.c[this.b.c]);
            } else {
                float f7;
                float f8;
                int n2;
                if (this.b.c < 256) {
                    GL11.glBindTexture((int)3553, (int)this.a.m.a("/terrain.png"));
                } else {
                    GL11.glBindTexture((int)3553, (int)this.a.m.a("/gui/items.png"));
                }
                d d2 = net.minecraft.client.a.d.a;
                k k2 = this.b;
                f5 = (float)(k2.a().b() % 16 << 4) / 256.0f;
                k2 = this.b;
                f2 = (float)((k2.a().b() % 16 << 4) + 16) / 256.0f;
                k2 = this.b;
                f3 = (float)(k2.a().b() / 16 << 4) / 256.0f;
                k2 = this.b;
                float f9 = (float)((k2.a().b() / 16 << 4) + 16) / 256.0f;
                GL11.glEnable((int)2977);
                GL11.glTranslatef((float)0.0f, (float)-0.3f, (float)0.0f);
                GL11.glScalef((float)1.5f, (float)1.5f, (float)1.5f);
                GL11.glRotatef((float)50.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glRotatef((float)335.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glTranslatef((float)-0.9375f, (float)-0.0625f, (float)0.0f);
                net.minecraft.client.a.d.c(0.0f, 0.0f, 1.0f);
                d2.b();
                d2.a(0.0f, 0.0f, 0.0f, f2, f9);
                d2.a(1.0f, 0.0f, 0.0f, f5, f9);
                d2.a(1.0f, 1.0f, 0.0f, f5, f3);
                d2.a(0.0f, 1.0f, 0.0f, f2, f3);
                d2.a();
                net.minecraft.client.a.d.c(0.0f, 0.0f, -1.0f);
                d2.b();
                d2.a(0.0f, 1.0f, -0.0625f, f2, f3);
                d2.a(1.0f, 1.0f, -0.0625f, f5, f3);
                d2.a(1.0f, 0.0f, -0.0625f, f5, f9);
                d2.a(0.0f, 0.0f, -0.0625f, f2, f9);
                d2.a();
                net.minecraft.client.a.d.c(-1.0f, 0.0f, 0.0f);
                d2.b();
                for (n2 = 0; n2 < 16; ++n2) {
                    f8 = (float)n2 / 16.0f;
                    f7 = f2 + (f5 - f2) * f8 - 0.001953125f;
                    d2.a(f8 *= 1.0f, 0.0f, -0.0625f, f7, f9);
                    d2.a(f8, 0.0f, 0.0f, f7, f9);
                    d2.a(f8, 1.0f, 0.0f, f7, f3);
                    d2.a(f8, 1.0f, -0.0625f, f7, f3);
                }
                d2.a();
                net.minecraft.client.a.d.c(1.0f, 0.0f, 0.0f);
                d2.b();
                for (n2 = 0; n2 < 16; ++n2) {
                    f8 = (float)n2 / 16.0f;
                    f7 = f2 + (f5 - f2) * f8 - 0.001953125f;
                    f8 = f8 * 1.0f + 0.0625f;
                    d2.a(f8, 1.0f, -0.0625f, f7, f3);
                    d2.a(f8, 1.0f, 0.0f, f7, f3);
                    d2.a(f8, 0.0f, 0.0f, f7, f9);
                    d2.a(f8, 0.0f, -0.0625f, f7, f9);
                }
                d2.a();
                net.minecraft.client.a.d.c(0.0f, 1.0f, 0.0f);
                d2.b();
                for (n2 = 0; n2 < 16; ++n2) {
                    f8 = (float)n2 / 16.0f;
                    f7 = f9 + (f3 - f9) * f8 - 0.001953125f;
                    f8 = f8 * 1.0f + 0.0625f;
                    d2.a(0.0f, f8, 0.0f, f2, f7);
                    d2.a(1.0f, f8, 0.0f, f5, f7);
                    d2.a(1.0f, f8, -0.0625f, f5, f7);
                    d2.a(0.0f, f8, -0.0625f, f2, f7);
                }
                d2.a();
                net.minecraft.client.a.d.c(0.0f, -1.0f, 0.0f);
                d2.b();
                for (n2 = 0; n2 < 16; ++n2) {
                    f8 = (float)n2 / 16.0f;
                    f7 = f9 + (f3 - f9) * f8 - 0.001953125f;
                    d2.a(1.0f, f8 *= 1.0f, 0.0f, f5, f7);
                    d2.a(0.0f, f8, 0.0f, f2, f7);
                    d2.a(0.0f, f8, -0.0625f, f2, f7);
                    d2.a(1.0f, f8, -0.0625f, f5, f7);
                }
                d2.a();
                GL11.glDisable((int)2977);
            }
            GL11.glPopMatrix();
        } else {
            float f10;
            float f11;
            GL11.glPushMatrix();
            if (this.f) {
                f4 = ((float)this.e + f2) / 8.0f;
                f11 = a.a.a(f4 * (float)Math.PI);
                f10 = a.a.a(a.a.c(f4) * (float)Math.PI);
                GL11.glTranslatef((float)(-f10 * 0.3f), (float)(a.a.a(a.a.c(f4) * (float)Math.PI * 2.0f) * 0.4f), (float)(-f11 * 0.4f));
            }
            GL11.glTranslatef((float)0.64000005f, (float)(-0.6f - (1.0f - f3) * 0.6f), (float)-0.71999997f);
            GL11.glRotatef((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glEnable((int)2977);
            if (this.f) {
                f4 = ((float)this.e + f2) / 8.0f;
                f11 = a.a.a(f4 * f4 * (float)Math.PI);
                f10 = a.a.a(a.a.c(f4) * (float)Math.PI);
                GL11.glRotatef((float)(f10 * 70.0f), (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glRotatef((float)(-f11 * 20.0f), (float)0.0f, (float)0.0f, (float)1.0f);
            }
            GL11.glBindTexture((int)3553, (int)this.a.m.a(this.a.f.N, this.a.f.q()));
            GL11.glTranslatef((float)-0.2f, (float)-0.3f, (float)0.1f);
            GL11.glRotatef((float)120.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glRotatef((float)200.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)-135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glScalef((float)0.0625f, (float)0.0625f, (float)0.0625f);
            GL11.glTranslatef((float)6.0f, (float)0.0f, (float)0.0f);
            net.minecraft.client.a.a.f f12 = i.a.a(this.a.f);
            net.minecraft.client.a.a.g g2 = (net.minecraft.client.a.a.g)f12;
            g2.a();
            GL11.glPopMatrix();
        }
        GL11.glDisable((int)2977);
        net.minecraft.client.c.a();
    }

    public final void b(float f2) {
        float f3;
        float f4;
        d d2;
        int n2;
        GL11.glDisable((int)3008);
        if (this.a.f.J > 0) {
            n2 = this.a.m.a("/terrain.png");
            GL11.glBindTexture((int)3553, (int)n2);
            d2 = net.minecraft.client.a.d.a;
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)0.9f);
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            for (n2 = 0; n2 < 2; ++n2) {
                GL11.glPushMatrix();
                int n3 = x.ag.as + (n2 << 4);
                int n4 = (n3 & 0xF) << 4;
                float f5 = (float)n4 / 256.0f;
                float f6 = ((float)n4 + 15.99f) / 256.0f;
                f4 = (float)(n3 &= 0xF0) / 256.0f;
                f3 = ((float)n3 + 15.99f) / 256.0f;
                GL11.glTranslatef((float)((float)(-((n2 << 1) - 1)) * 0.24f), (float)-0.3f, (float)0.0f);
                GL11.glRotatef((float)((float)((n2 << 1) - 1) * 10.0f), (float)0.0f, (float)1.0f, (float)0.0f);
                d2.b();
                d2.a(-0.5f, -0.5f, -0.5f, f6, f3);
                d2.a(0.5f, -0.5f, -0.5f, f5, f3);
                d2.a(0.5f, 0.5f, -0.5f, f5, f4);
                d2.a(-0.5f, 0.5f, -0.5f, f6, f4);
                d2.a();
                GL11.glPopMatrix();
            }
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glDisable((int)3042);
        }
        if (this.a.f.m()) {
            n2 = this.a.m.a("/water.png");
            GL11.glBindTexture((int)3553, (int)n2);
            float f7 = f2;
            g g2 = this;
            d2 = net.minecraft.client.a.d.a;
            f7 = g2.a.f.a(f7);
            GL11.glColor4f((float)f7, (float)f7, (float)f7, (float)0.5f);
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            GL11.glPushMatrix();
            f4 = -g2.a.f.n / 64.0f;
            f3 = g2.a.f.o / 64.0f;
            d2.b();
            d2.a(-1.0f, -1.0f, -0.5f, f4 + 4.0f, f3 + 4.0f);
            d2.a(1.0f, -1.0f, -0.5f, f4 + 0.0f, f3 + 4.0f);
            d2.a(1.0f, 1.0f, -0.5f, f4 + 0.0f, f3 + 0.0f);
            d2.a(-1.0f, 1.0f, -0.5f, f4 + 4.0f, f3 + 0.0f);
            d2.a();
            GL11.glPopMatrix();
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glDisable((int)3042);
        }
        GL11.glEnable((int)3008);
    }

    public final void a() {
        float f2;
        this.d = this.c;
        if (this.f) {
            ++this.e;
            if (this.e == 8) {
                this.e = 0;
                this.f = false;
            }
        }
        Object object = this.a.f;
        k k2 = ((net.minecraft.a.c.e.a)object).b.d();
        object = k2;
        object = k2;
        float f3 = k2 == this.b ? 1.0f : 0.0f;
        f3 = f3 - this.c;
        if (f2 < -0.4f) {
            f3 = -0.4f;
        }
        if (f3 > 0.4f) {
            f3 = 0.4f;
        }
        this.c += f3;
        if (this.c < 0.1f) {
            this.b = object;
        }
    }

    public final void b() {
        this.c = 0.0f;
    }

    public final void c() {
        this.e = -1;
        this.f = true;
    }

    public final void d() {
        this.c = 0.0f;
    }
}

