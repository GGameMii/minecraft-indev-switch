/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.c.a;

import net.minecraft.a.a.g;
import net.minecraft.a.b.i;
import net.minecraft.a.c.a.e;
import net.minecraft.a.c.b;
import net.minecraft.a.c.d.a;

public class c
extends e {
    public c(g g2) {
        super(g2);
        this.V = "/mob/skeleton.png";
    }

    public final void f() {
        if (this.d.B > 7) {
            float f2;
            float f3 = this.a(1.0f);
            if (f2 > 0.5f && this.d.l((int)this.h, (int)this.i, (int)this.j) && this.G.nextFloat() * 30.0f < (f3 - 0.4f) * 2.0f) {
                this.J = 300;
            }
        }
        super.f();
    }

    protected final void a(b b2, float f2) {
        if (f2 < 10.0f) {
            f2 = b2.h - this.h;
            float f3 = b2.j - this.j;
            if (this.ac == 0) {
                a a2 = new a(this.d, this);
                a2.i += 1.4f;
                float f4 = b2.i - 0.2f - a2.i;
                float f5 = a.a.c(f2 * f2 + f3 * f3) * 0.2f;
                this.d.a(this, "random.bow", 1.0f, 1.0f / (this.G.nextFloat() * 0.4f + 0.8f));
                this.d.a(a2);
                a2.a(f2, f4 + f5, f3, 0.6f, 12.0f);
                this.ac = 30;
            }
            this.n = (float)(Math.atan2(f3, f2) * 180.0 / 3.1415927410125732) - 90.0f;
            this.O = true;
        }
    }

    protected final void a(com.a.a.c c2) {
        super.a(c2);
    }

    protected final void b(com.a.a.c c2) {
        super.b(c2);
    }

    protected final String a() {
        return "Skeleton";
    }

    protected final int c() {
        return net.minecraft.a.b.i.h.ap;
    }
}

