/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client.f;

import net.minecraft.a.a.g;
import net.minecraft.a.c.b;
import net.minecraft.client.a.a.i;
import net.minecraft.client.a.d;
import net.minecraft.client.f.k;
import org.lwjgl.opengl.GL11;

public final class e
extends k {
    private b Z;
    private net.minecraft.a.c.e aa;
    private int ab = 0;
    private int ac = 0;
    private float ad;

    public e(g g2, b b2, net.minecraft.a.c.e e2, float f2) {
        super(g2, b2.h, b2.i, b2.j, b2.k, b2.l, b2.m);
        this.Z = b2;
        this.aa = e2;
        this.ac = 3;
        this.ad = -0.5f;
    }

    public final void a(d d2, float f2, float f3, float f4, float f5, float f6, float f7) {
        float f8 = ((float)this.ab + f2) / (float)this.ac;
        f8 *= f8;
        f3 = this.Z.h;
        f4 = this.Z.i;
        f5 = this.Z.j;
        f6 = this.aa.B + (this.aa.h - this.aa.B) * f2;
        f7 = this.aa.C + (this.aa.i - this.aa.C) * f2 + this.ad;
        float f9 = this.aa.D + (this.aa.j - this.aa.D) * f2;
        f3 += (f6 - f3) * f8;
        f4 += (f7 - f4) * f8;
        f8 = f5 + (f9 - f5) * f8;
        f5 = this.d.c((int)f3, (int)f4, (int)f8);
        GL11.glColor4f((float)f5, (float)f5, (float)f5, (float)1.0f);
        net.minecraft.client.a.a.i.a.a(this.Z, f3, f4, f8, this.Z.n, f2);
    }

    public final void b_() {
        ++this.ab;
        if (this.ab == this.ac) {
            this.k();
        }
    }

    public final int c() {
        return 2;
    }
}

