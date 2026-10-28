/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.c.a;

import a.a;
import com.a.a.c;
import net.minecraft.a.a.g;
import net.minecraft.a.b.i;
import net.minecraft.a.c.a.e;

public class b
extends e {
    public b(g g2) {
        super(g2);
        this.V = "/mob/spider.png";
        this.a(1.4f, 0.9f);
        this.am = 0.8f;
    }

    protected final net.minecraft.a.c.b b() {
        float f2;
        float f3 = this.a(1.0f);
        if (f2 < 0.5f) {
            float f4;
            f3 = this.d.y.b(this);
            if (f4 < 256.0f) {
                return this.d.y;
            }
        }
        return null;
    }

    protected final void a(net.minecraft.a.c.b b2, float f2) {
        float f3;
        float f4 = this.a(1.0f);
        if (f3 > 0.5f && this.G.nextInt(100) == 0) {
            this.b = null;
            return;
        }
        if (f2 > 2.0f && f2 < 6.0f && this.G.nextInt(10) == 0) {
            if (this.s) {
                f2 = b2.h - this.h;
                float f5 = b2.j - this.j;
                f4 = a.a.c(f2 * f2 + f5 * f5);
                this.k = f2 / f4 * 0.5f * 0.8f + this.k * 0.2f;
                this.m = f5 / f4 * 0.5f * 0.8f + this.m * 0.2f;
                this.l = 0.4f;
                return;
            }
        } else {
            super.a(b2, f2);
        }
    }

    protected final void a(c c2) {
        super.a(c2);
    }

    protected final void b(c c2) {
        super.b(c2);
    }

    protected final String a() {
        return "Spider";
    }

    protected final int c() {
        return net.minecraft.a.b.i.G.ap;
    }
}

