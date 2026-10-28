/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.a.b;

import java.util.Random;
import net.minecraft.a.a.b.x;
import net.minecraft.a.a.d.c;
import net.minecraft.a.a.g;
import net.minecraft.a.c.c.a;

public final class h
extends x {
    public h(int n2, int n3) {
        super(46, 8, net.minecraft.a.a.d.c.p);
    }

    public final int a(int n2) {
        if (n2 == 0) {
            return this.as + 2;
        }
        if (n2 == 1) {
            return this.as + 1;
        }
        return this.as;
    }

    public final int a(Random random) {
        return 0;
    }

    public final void c(g g2, int n2, int n3, int n4) {
        a a2 = new a(g2, (float)n2 + 0.5f, (float)n3 + 0.5f, (float)n4 + 0.5f);
        new a(g2, (float)n2 + 0.5f, (float)n3 + 0.5f, (float)n4 + 0.5f).a = g2.q.nextInt(a2.a / 4) + a2.a / 8;
        g2.a(a2);
    }

    public final void c(g g2, int n2, int n3, int n4, int n5) {
        a a2 = new a(g2, (float)n2 + 0.5f, (float)n3 + 0.5f, (float)n4 + 0.5f);
        g2.a(a2);
        g2.a(a2, "random.fuse", 1.0f, 1.0f);
    }
}

