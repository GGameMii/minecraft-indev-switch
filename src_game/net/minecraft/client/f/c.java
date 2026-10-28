/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.f;

import net.minecraft.a.a.g;
import net.minecraft.client.a.d;
import net.minecraft.client.f.k;

public final class c
extends k {
    private float Z;

    public c(g g2, float f2, float f3, float f4) {
        super(g2, f2, f3, f4, 0.0f, 0.0f, 0.0f);
        this.a *= 0.01f;
        this.b *= 0.01f;
        this.O *= 0.01f;
        this.G.nextFloat();
        this.G.nextFloat();
        this.G.nextFloat();
        this.G.nextFloat();
        this.G.nextFloat();
        this.G.nextFloat();
        this.Z = this.U;
        this.Y = 1.0f;
        this.X = 1.0f;
        this.W = 1.0f;
        this.T = (int)(8.0 / (Math.random() * 0.8 + 0.2)) + 4;
        this.F = true;
        this.P = 48;
    }

    public final void a(d d2, float f2, float f3, float f4, float f5, float f6, float f7) {
        float f8 = ((float)this.S + f2) / (float)this.T;
        this.U = this.Z * (1.0f - f8 * f8 * 0.5f);
        super.a(d2, f2, f3, f4, f5, f6, f7);
    }

    public final float a(float f2) {
        float f3;
        float f4 = ((float)this.S + f2) / (float)this.T;
        if (f3 < 0.0f) {
            f4 = 0.0f;
        }
        if (f4 > 1.0f) {
            f4 = 1.0f;
        }
        f2 = super.a(f2);
        return f2 * f4 + (1.0f - f4);
    }

    public final void b_() {
        this.e = this.h;
        this.f = this.i;
        this.g = this.j;
        if (this.S++ >= this.T) {
            this.k();
        }
        this.d(this.a, this.b, this.O);
        this.a *= 0.96f;
        this.b *= 0.96f;
        this.O *= 0.96f;
        if (this.s) {
            this.a *= 0.7f;
            this.O *= 0.7f;
        }
    }
}

