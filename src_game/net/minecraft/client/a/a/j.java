/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client.a.a;

import a.a;
import net.minecraft.a.c.e;
import net.minecraft.client.a.a.f;
import net.minecraft.client.b.l;
import org.lwjgl.opengl.GL11;

public class j
extends f {
    protected l d;
    private l e;

    public j(l l2, float f2) {
        this.d = l2;
        this.b = f2;
    }

    public final void a(l l2) {
        this.e = l2;
    }

    public void a(e e2, float f2, float f3, float f4, float f5, float f6) {
        GL11.glPushMatrix();
        GL11.glDisable((int)2884);
        try {
            f5 = e2.U + (e2.T - e2.U) * f6;
            float f7 = e2.p + (e2.n - e2.p) * f6;
            float f8 = e2.q + (e2.o - e2.q) * f6;
            GL11.glTranslatef((float)f2, (float)f3, (float)f4);
            f2 = (float)e2.H + f6;
            GL11.glRotatef((float)(180.0f - f5), (float)0.0f, (float)1.0f, (float)0.0f);
            if (e2.ab > 0) {
                float f9;
                f3 = ((float)e2.ab + f6 - 1.0f) / 20.0f * 1.6f;
                f3 = a.a.c(f3);
                if (f9 > 1.0f) {
                    f3 = 1.0f;
                }
                GL11.glRotatef((float)(f3 * this.a(e2)), (float)0.0f, (float)0.0f, (float)1.0f);
            }
            GL11.glScalef((float)-0.0625f, (float)-0.0625f, (float)0.0625f);
            this.a(e2, f6);
            GL11.glTranslatef((float)0.0f, (float)-24.0f, (float)0.0f);
            GL11.glEnable((int)2977);
            f3 = e2.af + (e2.ag - e2.af) * f6;
            f4 = e2.ah - e2.ag * (1.0f - f6);
            if (f3 > 1.0f) {
                f3 = 1.0f;
            }
            this.a(e2.N, e2.q());
            GL11.glEnable((int)3008);
            this.d.a(f4, f3, f2, f7 - f5, f8, 1.0f);
            for (int i2 = 0; i2 < 4; ++i2) {
                if (!this.a(e2, i2)) continue;
                this.e.a(f4, f3, f2, f7 - f5, f8, 1.0f);
                GL11.glDisable((int)3042);
                GL11.glEnable((int)3008);
            }
            float f10 = e2.a(f6);
            int n2 = this.a(e2, f10, f6);
            if (n2 >>> 24 > 0 || e2.Y > 0 || e2.ab > 0) {
                GL11.glDisable((int)3553);
                GL11.glDisable((int)3008);
                GL11.glEnable((int)3042);
                GL11.glBlendFunc((int)770, (int)771);
                GL11.glDepthFunc((int)514);
                if (e2.Y > 0 || e2.ab > 0) {
                    GL11.glColor4f((float)f10, (float)0.0f, (float)0.0f, (float)0.4f);
                    this.d.a(f4, f3, f2, f7 - f5, f8, 1.0f);
                    for (int i3 = 0; i3 < 4; ++i3) {
                        if (!this.a(e2, i3)) continue;
                        GL11.glColor4f((float)f10, (float)0.0f, (float)0.0f, (float)0.4f);
                        this.e.a(f4, f3, f2, f7 - f5, f8, 1.0f);
                    }
                }
                if (n2 >>> 24 > 0) {
                    float f11 = (float)(n2 >> 16 & 0xFF) / 255.0f;
                    f10 = (float)(n2 >> 8 & 0xFF) / 255.0f;
                    float f12 = (float)(n2 & 0xFF) / 255.0f;
                    float f13 = (float)(n2 >>> 24) / 255.0f;
                    GL11.glColor4f((float)f11, (float)f10, (float)f12, (float)f13);
                    this.d.a(f4, f3, f2, f7 - f5, f8, 1.0f);
                    for (int i4 = 0; i4 < 4; ++i4) {
                        if (!this.a(e2, i4)) continue;
                        GL11.glColor4f((float)f11, (float)f10, (float)f12, (float)f13);
                        this.e.a(f4, f3, f2, f7 - f5, f8, 1.0f);
                    }
                }
                GL11.glDepthFunc((int)515);
                GL11.glDisable((int)3042);
                GL11.glEnable((int)3008);
                GL11.glEnable((int)3553);
            }
            GL11.glDisable((int)2977);
        }
        catch (Exception exception) {
            Exception exception2 = exception;
            exception.printStackTrace();
        }
        GL11.glEnable((int)2884);
        GL11.glPopMatrix();
    }

    protected boolean a(e e2, int n2) {
        return false;
    }

    protected float a(e e2) {
        return 90.0f;
    }

    protected int a(e e2, float f2, float f3) {
        return 0;
    }

    protected void a(e e2, float f2) {
    }
}

