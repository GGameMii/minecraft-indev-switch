/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.f;

import net.minecraft.a.a.d.c;
import net.minecraft.a.a.g;
import net.minecraft.client.f.k;

public final class a
extends k {
    public a(g g2, float f2, float f3, float f4, float f5, float f6, float f7) {
        super(g2, f2, f3, f4, f5, f6, f7);
        this.W = 1.0f;
        this.X = 1.0f;
        this.Y = 1.0f;
        this.P = 32;
        this.a(0.02f, 0.02f);
        this.U *= this.G.nextFloat() * 0.6f + 0.2f;
        this.a = f5 * 0.2f + (float)(Math.random() * 2.0 - 1.0) * 0.02f;
        this.b = f6 * 0.2f + (float)(Math.random() * 2.0 - 1.0) * 0.02f;
        this.O = f7 * 0.2f + (float)(Math.random() * 2.0 - 1.0) * 0.02f;
        this.T = (int)(8.0 / (Math.random() * 0.8 + 0.2));
    }

    public final void b_() {
        this.e = this.h;
        this.f = this.i;
        this.g = this.j;
        this.b = (float)((double)this.b + 0.002);
        this.d(this.a, this.b, this.O);
        this.a *= 0.85f;
        this.b *= 0.85f;
        this.O *= 0.85f;
        if (this.d.f((int)this.h, (int)this.i, (int)this.j) != net.minecraft.a.a.d.c.f) {
            this.k();
        }
        if (this.T-- <= 0) {
            this.k();
        }
    }
}

