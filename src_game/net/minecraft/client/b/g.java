/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.b;

import a.a;
import net.minecraft.client.b.h;

public class g
extends h {
    public final void b(float f2, float f3, float f4, float f5, float f6, float f7) {
        super.b(f2, f3, f4, f5, f6, f7);
        f2 = a.a.a(0.0f);
        f3 = a.a.a(0.0f);
        this.d.c = 0.0f;
        this.e.c = 0.0f;
        this.d.b = -(0.1f - f2 * 0.6f);
        this.e.b = 0.1f - f2 * 0.6f;
        this.d.a = -1.5707964f;
        this.e.a = -1.5707964f;
        this.d.a -= f2 * 1.2f - f3 * 0.4f;
        this.e.a -= f2 * 1.2f - f3 * 0.4f;
        this.d.c += a.a.b(f4 * 0.09f) * 0.05f + 0.05f;
        this.e.c -= a.a.b(f4 * 0.09f) * 0.05f + 0.05f;
        this.d.a += a.a.a(f4 * 0.067f) * 0.05f;
        this.e.a -= a.a.a(f4 * 0.067f) * 0.05f;
    }
}

