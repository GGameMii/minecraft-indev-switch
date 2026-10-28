/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.b;

import a.a;
import net.minecraft.client.b.c;
import net.minecraft.client.b.l;

public final class k
extends l {
    private c a = new c(0, 0);
    private c b;
    private c c;
    private c d;
    private c e;
    private c f;
    private c g;

    public k() {
        this.a.a(-4.0f, -8.0f, -4.0f, 8, 8, 8, 0.0f);
        this.a.a(0.0f, 4.0f, 0.0f);
        this.b = new c(32, 0);
        this.b.a(-4.0f, -8.0f, -4.0f, 8, 8, 8, 0.5f);
        this.b.a(0.0f, 4.0f, 0.0f);
        this.c = new c(16, 16);
        this.c.a(-4.0f, 0.0f, -2.0f, 8, 12, 4, 0.0f);
        this.c.a(0.0f, 4.0f, 0.0f);
        this.d = new c(0, 16);
        this.d.a(-2.0f, 0.0f, -2.0f, 4, 6, 4, 0.0f);
        this.d.a(-2.0f, 16.0f, 4.0f);
        this.e = new c(0, 16);
        this.e.a(-2.0f, 0.0f, -2.0f, 4, 6, 4, 0.0f);
        this.e.a(2.0f, 16.0f, 4.0f);
        this.f = new c(0, 16);
        this.f.a(-2.0f, 0.0f, -2.0f, 4, 6, 4, 0.0f);
        this.f.a(-2.0f, 16.0f, -4.0f);
        this.g = new c(0, 16);
        this.g.a(-2.0f, 0.0f, -2.0f, 4, 6, 4, 0.0f);
        this.g.a(2.0f, 16.0f, -4.0f);
    }

    public final void a(float f2, float f3, float f4, float f5, float f6, float f7) {
        this.b(f2, f3, f4, f5, f6, 1.0f);
        this.a.a(1.0f);
        this.c.a(1.0f);
        this.d.a(1.0f);
        this.e.a(1.0f);
        this.f.a(1.0f);
        this.g.a(1.0f);
    }

    public final void b(float f2, float f3, float f4, float f5, float f6, float f7) {
        this.a.b = f5 / 57.295776f;
        this.a.a = f6 / 57.295776f;
        this.d.a = a.a.b(f2 * 0.6662f) * 1.4f * f3;
        this.e.a = a.a.b(f2 * 0.6662f + (float)Math.PI) * 1.4f * f3;
        this.f.a = a.a.b(f2 * 0.6662f + (float)Math.PI) * 1.4f * f3;
        this.g.a = a.a.b(f2 * 0.6662f) * 1.4f * f3;
    }
}

