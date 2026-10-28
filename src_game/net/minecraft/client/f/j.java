/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.f;

import net.minecraft.a.a.g;
import net.minecraft.client.a.d;
import net.minecraft.client.f.k;

public final class j
extends k {
    private float Z;

    public j(g g2, float f2, float f3, float f4) {
        this(g2, f2, f3, f4, 1.0f);
    }

    public j(g g2, float f2, float f3, float f4, float f5) {
        super(g2, f2, f3, f4, 0.0f, 0.0f, 0.0f);
        this.a *= 0.1f;
        this.b *= 0.1f;
        this.O *= 0.1f;
        this.X = this.Y = (float)(Math.random() * (double)0.3f);
        this.W = this.Y;
        this.U *= 0.75f;
        this.U *= f5;
        this.Z = this.U;
        this.T = (int)(8.0 / (Math.random() * 0.8 + 0.2));
        this.T = (int)((float)this.T * f5);
        this.F = false;
    }

    public final void a(d d2, float f2, float f3, float f4, float f5, float f6, float f7) {
        float f8;
        float f9 = ((float)this.S + f2) / (float)this.T * 32.0f;
        if (f8 < 0.0f) {
            f9 = 0.0f;
        }
        if (f9 > 1.0f) {
            f9 = 1.0f;
        }
        this.U = this.Z * f9;
        super.a(d2, f2, f3, f4, f5, f6, f7);
    }

    public final void b_() {
        this.e = this.h;
        this.f = this.i;
        this.g = this.j;
        if (this.S++ >= this.T) {
            this.k();
        }
        this.P = 7 - (this.S << 3) / this.T;
        this.b = (float)((double)this.b + 0.004);
        this.d(this.a, this.b, this.O);
        if (this.i == this.f) {
            this.a = (float)((double)this.a * 1.1);
            this.O = (float)((double)this.O * 1.1);
        }
        this.a *= 0.96f;
        this.b *= 0.96f;
        this.O *= 0.96f;
        if (this.s) {
            this.a *= 0.7f;
            this.O *= 0.7f;
        }
    }
}

