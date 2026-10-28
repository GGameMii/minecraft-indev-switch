/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.f;

import net.minecraft.a.a.b.x;
import net.minecraft.a.a.g;
import net.minecraft.client.a.d;
import net.minecraft.client.f.k;

public final class b
extends k {
    public b(g g2, float f2, float f3, float f4, float f5, float f6, float f7, x x2) {
        super(g2, f2, f3, f4, f5, f6, f7);
        this.P = x2.as;
        this.V = x2.aB;
        this.Y = 0.6f;
        this.X = 0.6f;
        this.W = 0.6f;
        this.U /= 2.0f;
    }

    public final int c() {
        return 1;
    }

    public final void a(d d2, float f2, float f3, float f4, float f5, float f6, float f7) {
        float f8 = ((float)(this.P % 16) + this.Q / 4.0f) / 16.0f;
        float f9 = f8 + 0.015609375f;
        float f10 = ((float)(this.P / 16) + this.R / 4.0f) / 16.0f;
        float f11 = f10 + 0.015609375f;
        float f12 = 0.1f * this.U;
        float f13 = this.e + (this.h - this.e) * f2;
        float f14 = this.f + (this.i - this.f) * f2;
        float f15 = this.g + (this.j - this.g) * f2;
        f2 = this.a(f2);
        d2.a(f2 * this.W, f2 * this.X, f2 * this.Y);
        d2.a(f13 - f3 * f12 - f6 * f12, f14 - f4 * f12, f15 - f5 * f12 - f7 * f12, f8, f11);
        d2.a(f13 - f3 * f12 + f6 * f12, f14 + f4 * f12, f15 - f5 * f12 + f7 * f12, f8, f10);
        d2.a(f13 + f3 * f12 + f6 * f12, f14 + f4 * f12, f15 + f5 * f12 + f7 * f12, f9, f10);
        d2.a(f13 + f3 * f12 - f6 * f12, f14 - f4 * f12, f15 + f5 * f12 - f7 * f12, f9, f11);
    }
}

