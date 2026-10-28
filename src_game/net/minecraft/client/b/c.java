/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client.b;

import net.minecraft.a.d.a;
import net.minecraft.client.a.d;
import net.minecraft.client.b.i;
import net.minecraft.client.b.j;
import org.lwjgl.opengl.GL11;

public final class c {
    private j[] f;
    private i[] g;
    private int h;
    private int i;
    private float j;
    private float k;
    private float l;
    public float a;
    public float b;
    public float c;
    private boolean m = false;
    private int n = 0;
    public boolean d = false;
    public boolean e = true;
    private boolean o = false;

    public c(int n2, int n3) {
        this.h = n2;
        this.i = n3;
    }

    public final void a(float f2, float f3, float f4, int n2, int n3, int n4, float f5) {
        this.f = new j[8];
        this.g = new i[6];
        float f6 = f2 + (float)n2;
        float f7 = f3 + (float)n3;
        float f8 = f4 + (float)n4;
        f2 -= f5;
        f3 -= f5;
        f4 -= f5;
        f6 += f5;
        f7 += f5;
        f8 += f5;
        if (this.d) {
            f5 = f6;
            f6 = f2;
            f2 = f5;
        }
        j j2 = new j(f2, f3, f4, 0.0f, 0.0f);
        j j3 = new j(f6, f3, f4, 0.0f, 8.0f);
        j j4 = new j(f6, f7, f4, 8.0f, 8.0f);
        j[] jArray = new j(f2, f7, f4, 8.0f, 0.0f);
        j j5 = new j(f2, f3, f8, 0.0f, 0.0f);
        Object object = new j(f6, f3, f8, 0.0f, 8.0f);
        j j6 = new j(f6, f7, f8, 8.0f, 8.0f);
        j j7 = new j(f2, f7, f8, 8.0f, 0.0f);
        this.f[0] = j2;
        this.f[1] = j3;
        this.f[2] = j4;
        this.f[3] = jArray;
        this.f[4] = j5;
        this.f[5] = object;
        this.f[6] = j6;
        this.f[7] = j7;
        this.g[0] = new i(new j[]{object, j3, j4, j6}, this.h + n4 + n2, this.i + n4, this.h + n4 + n2 + n4, this.i + n4 + n3);
        this.g[1] = new i(new j[]{j2, j5, j7, jArray}, this.h, this.i + n4, this.h + n4, this.i + n4 + n3);
        this.g[2] = new i(new j[]{object, j5, j2, j3}, this.h + n4, this.i, this.h + n4 + n2, this.i + n4);
        this.g[3] = new i(new j[]{j4, jArray, j7, j6}, this.h + n4 + n2, this.i, this.h + n4 + n2 + n2, this.i + n4);
        this.g[4] = new i(new j[]{j3, j2, jArray, j4}, this.h + n4, this.i + n4, this.h + n4 + n2, this.i + n4 + n3);
        this.g[5] = new i(new j[]{j5, object, j6, j7}, this.h + n4 + n2 + n4, this.i + n4, this.h + n4 + n2 + n4 + n2, this.i + n4 + n3);
        if (this.d) {
            for (int i2 = 0; i2 < this.g.length; ++i2) {
                object = this.g[i2];
                jArray = new j[((i)object).a.length];
                for (n2 = 0; n2 < ((i)object).a.length; ++n2) {
                    jArray[n2] = ((i)object).a[((i)object).a.length - n2 - 1];
                }
                ((i)object).a = jArray;
            }
        }
    }

    public final void a(float f2, float f3, float f4) {
        this.j = f2;
        this.k = f3;
        this.l = f4;
    }

    public final void a(float f2) {
        if (!this.e) {
            return;
        }
        if (!this.m) {
            float f3 = f2;
            c c2 = this;
            this.n = GL11.glGenLists((int)1);
            GL11.glNewList((int)c2.n, (int)4864);
            d d2 = net.minecraft.client.a.d.a;
            for (int i2 = 0; i2 < c2.g.length; ++i2) {
                d2.b();
                float f4 = f3;
                d d3 = d2;
                i i3 = c2.g[i2];
                a a2 = i3.a[1].a.a(i3.a[0].a).a();
                Object object = i3.a[1].a.a(i3.a[2].a).a();
                a2 = new a(a2.b * ((a)object).c - a2.c * ((a)object).b, a2.c * ((a)object).a - a2.a * ((a)object).c, a2.a * ((a)object).b - a2.b * ((a)object).a).a();
                net.minecraft.client.a.d.c(-a2.a, -a2.b, -a2.c);
                for (int i4 = 0; i4 < 4; ++i4) {
                    object = i3.a[i4];
                    d3.a(((j)object).a.a * f4, ((j)object).a.b * f4, ((j)object).a.c * f4, ((j)object).b, ((j)object).c);
                }
                d2.a();
            }
            GL11.glEndList();
            c2.m = true;
        }
        if (this.a != 0.0f || this.b != 0.0f || this.c != 0.0f) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float)(this.j * f2), (float)(this.k * f2), (float)(this.l * f2));
            if (this.c != 0.0f) {
                GL11.glRotatef((float)(this.c * 57.295776f), (float)0.0f, (float)0.0f, (float)1.0f);
            }
            if (this.b != 0.0f) {
                GL11.glRotatef((float)(this.b * 57.295776f), (float)0.0f, (float)1.0f, (float)0.0f);
            }
            if (this.a != 0.0f) {
                GL11.glRotatef((float)(this.a * 57.295776f), (float)1.0f, (float)0.0f, (float)0.0f);
            }
            GL11.glCallList((int)this.n);
            GL11.glPopMatrix();
            return;
        }
        if (this.j != 0.0f || this.k != 0.0f || this.l != 0.0f) {
            GL11.glTranslatef((float)(this.j * f2), (float)(this.k * f2), (float)(this.l * f2));
            GL11.glCallList((int)this.n);
            GL11.glTranslatef((float)(-this.j * f2), (float)(-this.k * f2), (float)(-this.l * f2));
            return;
        }
        GL11.glCallList((int)this.n);
    }
}

