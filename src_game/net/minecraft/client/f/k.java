/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.f;

import a.a;
import com.a.a.c;
import net.minecraft.a.a.g;
import net.minecraft.a.c.b;
import net.minecraft.client.a.d;

public class k
extends b {
    protected float a;
    protected float b;
    protected float O;
    protected int P;
    protected float Q;
    protected float R;
    protected int S = 0;
    protected int T = 0;
    protected float U;
    protected float V;
    protected float W;
    protected float X;
    protected float Y;

    public k(g g2, float f2, float f3, float f4, float f5, float f6, float f7) {
        super(g2);
        this.a(0.2f, 0.2f);
        this.v = this.x / 2.0f;
        this.b(f2, f3, f4);
        this.Y = 1.0f;
        this.X = 1.0f;
        this.W = 1.0f;
        this.a = f5 + (float)(Math.random() * 2.0 - 1.0) * 0.4f;
        this.b = f6 + (float)(Math.random() * 2.0 - 1.0) * 0.4f;
        this.O = f7 + (float)(Math.random() * 2.0 - 1.0) * 0.4f;
        float f8 = (float)(Math.random() + Math.random() + 1.0) * 0.15f;
        f2 = a.a.c(this.a * this.a + this.b * this.b + this.O * this.O);
        this.a = this.a / f2 * f8 * 0.4f;
        this.b = this.b / f2 * f8 * 0.4f + 0.1f;
        this.O = this.O / f2 * f8 * 0.4f;
        this.Q = this.G.nextFloat() * 3.0f;
        this.R = this.G.nextFloat() * 3.0f;
        this.U = (this.G.nextFloat() * 0.5f + 0.5f) * 2.0f;
        this.T = (int)(4.0f / (this.G.nextFloat() * 0.9f + 0.1f));
        this.S = 0;
        this.A = false;
    }

    public final k c(float f2) {
        this.a *= 0.2f;
        this.b = (this.b - 0.1f) * 0.2f + 0.1f;
        this.O *= 0.2f;
        return this;
    }

    public final k d(float f2) {
        this.a(0.120000005f, 0.120000005f);
        this.U *= 0.6f;
        return this;
    }

    public void b_() {
        this.e = this.h;
        this.f = this.i;
        this.g = this.j;
        if (this.S++ >= this.T) {
            this.k();
        }
        this.b = (float)((double)this.b - 0.04 * (double)this.V);
        this.d(this.a, this.b, this.O);
        this.a *= 0.98f;
        this.b *= 0.98f;
        this.O *= 0.98f;
        if (this.s) {
            this.a *= 0.7f;
            this.O *= 0.7f;
        }
    }

    public void a(d d2, float f2, float f3, float f4, float f5, float f6, float f7) {
        float f8 = (float)(this.P % 16) / 16.0f;
        float f9 = f8 + 0.0624375f;
        float f10 = (float)(this.P / 16) / 16.0f;
        float f11 = f10 + 0.0624375f;
        float f12 = 0.1f * this.U;
        float f13 = this.e + (this.h - this.e) * f2;
        float f14 = this.f + (this.i - this.f) * f2;
        float f15 = this.g + (this.j - this.g) * f2;
        f2 = this.a(f2);
        d2.a(this.W * f2, this.X * f2, this.Y * f2);
        d2.a(f13 - f3 * f12 - f6 * f12, f14 - f4 * f12, f15 - f5 * f12 - f7 * f12, f8, f11);
        d2.a(f13 - f3 * f12 + f6 * f12, f14 + f4 * f12, f15 - f5 * f12 + f7 * f12, f8, f10);
        d2.a(f13 + f3 * f12 + f6 * f12, f14 + f4 * f12, f15 + f5 * f12 + f7 * f12, f9, f10);
        d2.a(f13 + f3 * f12 - f6 * f12, f14 - f4 * f12, f15 + f5 * f12 - f7 * f12, f9, f11);
    }

    public int c() {
        return 0;
    }

    protected final void a(c c2) {
    }

    protected final String a() {
        return null;
    }

    protected final void b(c c2) {
    }
}

