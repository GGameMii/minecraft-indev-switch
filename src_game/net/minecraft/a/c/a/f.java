/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.c.a;

import net.minecraft.a.a.g;
import net.minecraft.a.b.i;
import net.minecraft.a.c.a.e;

public class f
extends e {
    public f(g g2) {
        super(g2);
        this.V = "/mob/zombie.png";
        this.am = 0.5f;
        this.a = 5;
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

    protected final String a() {
        return "Zombie";
    }

    protected final int c() {
        return net.minecraft.a.b.i.H.ap;
    }
}

