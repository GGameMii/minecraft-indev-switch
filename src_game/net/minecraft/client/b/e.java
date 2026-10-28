/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.b;

import a.a;
import net.minecraft.client.b.c;
import net.minecraft.client.b.l;

public final class e
extends l {
    private c a = new c(32, 4);
    private c b;
    private c c;
    private c d;
    private c e;
    private c f;
    private c g;
    private c h;
    private c i;
    private c j;
    private c k;

    public e() {
        this.a.a(-4.0f, -4.0f, -8.0f, 8, 8, 8, 0.0f);
        this.a.a(0.0f, 15.0f, -3.0f);
        this.b = new c(0, 0);
        this.b.a(-3.0f, -3.0f, -3.0f, 6, 6, 6, 0.0f);
        this.b.a(0.0f, 15.0f, 0.0f);
        this.c = new c(0, 12);
        this.c.a(-5.0f, -4.0f, -6.0f, 10, 8, 12, 0.0f);
        this.c.a(0.0f, 15.0f, 9.0f);
        this.d = new c(18, 0);
        this.d.a(-15.0f, -1.0f, -1.0f, 16, 2, 2, 0.0f);
        this.d.a(-4.0f, 15.0f, 2.0f);
        this.e = new c(18, 0);
        this.e.a(-1.0f, -1.0f, -1.0f, 16, 2, 2, 0.0f);
        this.e.a(4.0f, 15.0f, 2.0f);
        this.f = new c(18, 0);
        this.f.a(-15.0f, -1.0f, -1.0f, 16, 2, 2, 0.0f);
        this.f.a(-4.0f, 15.0f, 1.0f);
        this.g = new c(18, 0);
        this.g.a(-1.0f, -1.0f, -1.0f, 16, 2, 2, 0.0f);
        this.g.a(4.0f, 15.0f, 1.0f);
        this.h = new c(18, 0);
        this.h.a(-15.0f, -1.0f, -1.0f, 16, 2, 2, 0.0f);
        this.h.a(-4.0f, 15.0f, 0.0f);
        this.i = new c(18, 0);
        this.i.a(-1.0f, -1.0f, -1.0f, 16, 2, 2, 0.0f);
        this.i.a(4.0f, 15.0f, 0.0f);
        this.j = new c(18, 0);
        this.j.a(-15.0f, -1.0f, -1.0f, 16, 2, 2, 0.0f);
        this.j.a(-4.0f, 15.0f, -1.0f);
        this.k = new c(18, 0);
        this.k.a(-1.0f, -1.0f, -1.0f, 16, 2, 2, 0.0f);
        this.k.a(4.0f, 15.0f, -1.0f);
    }

    public final void a(float f2, float f3, float f4, float f5, float f6, float f7) {
        this.b(f2, f3, f4, f5, f6, 1.0f);
        this.a.a(1.0f);
        this.b.a(1.0f);
        this.c.a(1.0f);
        this.d.a(1.0f);
        this.e.a(1.0f);
        this.f.a(1.0f);
        this.g.a(1.0f);
        this.h.a(1.0f);
        this.i.a(1.0f);
        this.j.a(1.0f);
        this.k.a(1.0f);
    }

    public final void b(float f2, float f3, float f4, float f5, float f6, float f7) {
        this.a.b = f5 / 57.295776f;
        this.a.a = f6 / 57.295776f;
        this.d.c = -0.7853982f;
        this.e.c = 0.7853982f;
        this.f.c = -0.58119464f;
        this.g.c = 0.58119464f;
        this.h.c = -0.58119464f;
        this.i.c = 0.58119464f;
        this.j.c = -0.7853982f;
        this.k.c = 0.7853982f;
        this.d.b = 0.7853982f;
        this.e.b = -0.7853982f;
        this.f.b = 0.3926991f;
        this.g.b = -0.3926991f;
        this.h.b = -0.3926991f;
        this.i.b = 0.3926991f;
        this.j.b = -0.7853982f;
        this.k.b = 0.7853982f;
        f4 = -(a.a.b(f2 * 0.6662f * 2.0f) * 0.4f) * f3;
        f5 = -(a.a.b(f2 * 0.6662f * 2.0f + (float)Math.PI) * 0.4f) * f3;
        f6 = -(a.a.b(f2 * 0.6662f * 2.0f + 1.5707964f) * 0.4f) * f3;
        f7 = -(a.a.b(f2 * 0.6662f * 2.0f + 4.712389f) * 0.4f) * f3;
        float f8 = Math.abs(a.a.a(f2 * 0.6662f) * 0.4f) * f3;
        float f9 = Math.abs(a.a.a(f2 * 0.6662f + (float)Math.PI) * 0.4f) * f3;
        float f10 = Math.abs(a.a.a(f2 * 0.6662f + 1.5707964f) * 0.4f) * f3;
        f2 = Math.abs(a.a.a(f2 * 0.6662f + 4.712389f) * 0.4f) * f3;
        this.d.b += f4;
        this.e.b -= f4;
        this.f.b += f5;
        this.g.b -= f5;
        this.h.b += f6;
        this.i.b -= f6;
        this.j.b += f7;
        this.k.b -= f7;
        this.d.c += f8;
        this.e.c -= f8;
        this.f.c += f9;
        this.g.c -= f9;
        this.h.c += f10;
        this.i.c -= f10;
        this.j.c += f2;
        this.k.c -= f2;
    }
}

