/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.b;

import a.a;
import net.minecraft.client.b.c;
import net.minecraft.client.b.l;

public class b
extends l {
    public c a = new c(0, 0);
    public c b;
    public c c;
    public c d;
    public c e;
    public c f;

    public b(int n2, float f2) {
        this.a.a(-4.0f, -4.0f, -8.0f, 8, 8, 8, 0.0f);
        this.a.a(0.0f, 18 - n2, -6.0f);
        this.b = new c(28, 8);
        this.b.a(-5.0f, -10.0f, -7.0f, 10, 16, 8, 0.0f);
        this.b.a(0.0f, 17 - n2, 2.0f);
        this.c = new c(0, 16);
        this.c.a(-2.0f, 0.0f, -2.0f, 4, n2, 4, 0.0f);
        this.c.a(-3.0f, 24 - n2, 7.0f);
        this.d = new c(0, 16);
        this.d.a(-2.0f, 0.0f, -2.0f, 4, n2, 4, 0.0f);
        this.d.a(3.0f, 24 - n2, 7.0f);
        this.e = new c(0, 16);
        this.e.a(-2.0f, 0.0f, -2.0f, 4, n2, 4, 0.0f);
        this.e.a(-3.0f, 24 - n2, -5.0f);
        this.f = new c(0, 16);
        this.f.a(-2.0f, 0.0f, -2.0f, 4, n2, 4, 0.0f);
        this.f.a(3.0f, 24 - n2, -5.0f);
    }

    public final void a(float f2, float f3, float f4, float f5, float f6, float f7) {
        this.b(f2, f3, f4, f5, f6, 1.0f);
        this.a.a(1.0f);
        this.b.a(1.0f);
        this.c.a(1.0f);
        this.d.a(1.0f);
        this.e.a(1.0f);
        this.f.a(1.0f);
    }

    public final void b(float f2, float f3, float f4, float f5, float f6, float f7) {
        this.a.b = f5 / 57.295776f;
        this.a.a = f6 / 57.295776f;
        this.b.a = 1.5707964f;
        this.c.a = a.a.b(f2 * 0.6662f) * 1.4f * f3;
        this.d.a = a.a.b(f2 * 0.6662f + (float)Math.PI) * 1.4f * f3;
        this.e.a = a.a.b(f2 * 0.6662f + (float)Math.PI) * 1.4f * f3;
        this.f.a = a.a.b(f2 * 0.6662f) * 1.4f * f3;
    }
}

