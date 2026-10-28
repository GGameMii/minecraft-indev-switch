/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.b;

import a.a;
import net.minecraft.client.b.c;
import net.minecraft.client.b.l;

public class h
extends l {
    public c a = new c(0, 0);
    public c b;
    public c c;
    public c d;
    public c e;
    public c f;
    public c g;

    public h() {
        this(0.0f);
    }

    public h(float f2) {
        this(f2, 0.0f);
    }

    private h(float f2, float f3) {
        this.a.a(-4.0f, -8.0f, -4.0f, 8, 8, 8, f2);
        this.a.a(0.0f, 0.0f, 0.0f);
        this.b = new c(32, 0);
        this.b.a(-4.0f, -8.0f, -4.0f, 8, 8, 8, f2 + 0.5f);
        this.b.a(0.0f, 0.0f, 0.0f);
        this.c = new c(16, 16);
        this.c.a(-4.0f, 0.0f, -2.0f, 8, 12, 4, f2);
        this.c.a(0.0f, 0.0f, 0.0f);
        this.d = new c(40, 16);
        this.d.a(-3.0f, -2.0f, -2.0f, 4, 12, 4, f2);
        this.d.a(-5.0f, 2.0f, 0.0f);
        this.e = new c(40, 16);
        this.e.d = true;
        this.e.a(-1.0f, -2.0f, -2.0f, 4, 12, 4, f2);
        this.e.a(5.0f, 2.0f, 0.0f);
        this.f = new c(0, 16);
        this.f.a(-2.0f, 0.0f, -2.0f, 4, 12, 4, f2);
        this.f.a(-2.0f, 12.0f, 0.0f);
        this.g = new c(0, 16);
        this.g.d = true;
        this.g.a(-2.0f, 0.0f, -2.0f, 4, 12, 4, f2);
        this.g.a(2.0f, 12.0f, 0.0f);
    }

    public final void a(float f2, float f3, float f4, float f5, float f6, float f7) {
        this.b(f2, f3, f4, f5, f6, 1.0f);
        this.a.a(1.0f);
        this.c.a(1.0f);
        this.d.a(1.0f);
        this.e.a(1.0f);
        this.f.a(1.0f);
        this.g.a(1.0f);
        this.b.a(1.0f);
    }

    public void b(float f2, float f3, float f4, float f5, float f6, float f7) {
        this.a.b = f5 / 57.295776f;
        this.a.a = f6 / 57.295776f;
        this.b.b = this.a.b;
        this.b.a = this.a.a;
        this.d.a = a.a.b(f2 * 0.6662f + (float)Math.PI) * 2.0f * f3;
        this.d.c = (a.a.b(f2 * 0.2312f) + 1.0f) * f3;
        this.e.a = a.a.b(f2 * 0.6662f) * 2.0f * f3;
        this.e.c = (a.a.b(f2 * 0.2812f) - 1.0f) * f3;
        this.f.a = a.a.b(f2 * 0.6662f) * 1.4f * f3;
        this.g.a = a.a.b(f2 * 0.6662f + (float)Math.PI) * 1.4f * f3;
        this.d.c += a.a.b(f4 * 0.09f) * 0.05f + 0.05f;
        this.e.c -= a.a.b(f4 * 0.09f) * 0.05f + 0.05f;
        this.d.a += a.a.a(f4 * 0.067f) * 0.05f;
        this.e.a -= a.a.a(f4 * 0.067f) * 0.05f;
    }
}

