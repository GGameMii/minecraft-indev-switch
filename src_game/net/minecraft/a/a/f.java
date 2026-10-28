/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.a;

import net.minecraft.a.a.i;
import net.minecraft.a.c.b;

final class f {
    private int a;
    private int b;
    private int c;
    private /* synthetic */ i d;

    private f(i i2, byte by) {
        this.d = i2;
    }

    public final f a(float f2, float f3, float f4) {
        this.a = (int)(f2 / 8.0f);
        this.b = (int)(f3 / 8.0f);
        this.c = (int)(f4 / 8.0f);
        if (this.a < 0) {
            this.a = 0;
        }
        if (this.b < 0) {
            this.b = 0;
        }
        if (this.c < 0) {
            this.c = 0;
        }
        if (this.a >= this.d.a) {
            this.a = this.d.a - 1;
        }
        if (this.b >= this.d.b) {
            this.b = this.d.b - 1;
        }
        if (this.c >= this.d.c) {
            this.c = this.d.c - 1;
        }
        return this;
    }

    public final void a(b b2) {
        if (this.a >= 0 && this.b >= 0 && this.c >= 0) {
            this.d.d[(this.c * this.d.b + this.b) * this.d.a + this.a].add(b2);
        }
    }

    public final void b(b b2) {
        if (this.a >= 0 && this.b >= 0 && this.c >= 0) {
            this.d.d[(this.c * this.d.b + this.b) * this.d.a + this.a].remove(b2);
        }
    }

    /* synthetic */ f(i i2) {
        this(i2, 0);
    }

    static /* synthetic */ int a(f f2) {
        return f2.a;
    }

    static /* synthetic */ int b(f f2) {
        return f2.b;
    }

    static /* synthetic */ int c(f f2) {
        return f2.c;
    }
}

