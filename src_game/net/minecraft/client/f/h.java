/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.f;

import net.minecraft.a.a.g;
import net.minecraft.client.a.d;
import net.minecraft.client.f.k;

public final class h
extends k {
    public h(g g2, float f2, float f3, float f4, float f5, float f6, float f7) {
        super(g2, f2, f3, f4, f5, f6, f7);
        this.a = f5 + (float)(Math.random() * 2.0 - 1.0) * 0.05f;
        this.b = f6 + (float)(Math.random() * 2.0 - 1.0) * 0.05f;
        this.O = f7 + (float)(Math.random() * 2.0 - 1.0) * 0.05f;
        this.X = this.Y = this.G.nextFloat() * 0.3f + 0.7f;
        this.W = this.Y;
        this.U = this.G.nextFloat() * this.G.nextFloat() * 6.0f + 1.0f;
        this.T = (int)(16.0 / ((double)this.G.nextFloat() * 0.8 + 0.2)) + 2;
    }

    public final void a(d d2, float f2, float f3, float f4, float f5, float f6, float f7) {
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
        this.a *= 0.9f;
        this.b *= 0.9f;
        this.O *= 0.9f;
        if (this.s) {
            this.a *= 0.7f;
            this.O *= 0.7f;
        }
    }
}

