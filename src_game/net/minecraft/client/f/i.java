/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.f;

import net.minecraft.a.a.d.c;
import net.minecraft.a.a.g;
import net.minecraft.client.a.d;
import net.minecraft.client.f.k;

public class i
extends k {
    public i(g g2, float f2, float f3, float f4) {
        super(g2, f2, f3, f4, 0.0f, 0.0f, 0.0f);
        this.a *= 0.3f;
        this.b = (float)Math.random() * 0.2f + 0.1f;
        this.O *= 0.3f;
        this.W = 1.0f;
        this.X = 1.0f;
        this.Y = 1.0f;
        this.P = 16;
        this.a(0.01f, 0.01f);
        this.V = 0.06f;
        this.T = (int)(8.0 / (Math.random() * 0.8 + 0.2));
    }

    public final void a(d d2, float f2, float f3, float f4, float f5, float f6, float f7) {
        super.a(d2, f2, f3, f4, f5, f6, f7);
    }

    public final void b_() {
        c c2;
        this.e = this.h;
        this.f = this.i;
        this.g = this.j;
        this.b -= this.V;
        this.d(this.a, this.b, this.O);
        this.a *= 0.98f;
        this.b *= 0.98f;
        this.O *= 0.98f;
        if (this.T-- <= 0) {
            this.k();
        }
        if (this.s) {
            if (Math.random() < 0.5) {
                this.k();
            }
            this.a *= 0.7f;
            this.O *= 0.7f;
        }
        if ((c2 = this.d.f((int)this.h, (int)this.i, (int)this.j)).d() || c2.a()) {
            this.k();
        }
    }
}

