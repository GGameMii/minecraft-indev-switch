/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.a.b;

import java.util.Random;
import net.minecraft.a.a.b.a.a;
import net.minecraft.a.a.b.a.b;
import net.minecraft.a.a.b.q;
import net.minecraft.a.a.b.x;
import net.minecraft.a.a.d.c;
import net.minecraft.a.a.g;

public final class s
extends q {
    private final boolean a;

    protected s(int n2, boolean bl) {
        super(n2, net.minecraft.a.a.d.c.d);
        this.a = bl;
        this.as = 45;
    }

    public final void d(g g2, int n2, int n3, int n4) {
        super.d(g2, n2, n3, n4);
        net.minecraft.a.a.b.s.e(g2, n2, n3, n4);
    }

    private static void e(g g2, int n2, int n3, int n4) {
        int n5 = g2.a(n2, n3, n4 - 1);
        int n6 = g2.a(n2, n3, n4 + 1);
        int n7 = g2.a(n2 - 1, n3, n4);
        int n8 = g2.a(n2 + 1, n3, n4);
        int n9 = 3;
        if (net.minecraft.a.a.b.x.e[n5] && !net.minecraft.a.a.b.x.e[n6]) {
            n9 = 3;
        }
        if (net.minecraft.a.a.b.x.e[n6] && !net.minecraft.a.a.b.x.e[n5]) {
            n9 = 2;
        }
        if (net.minecraft.a.a.b.x.e[n7] && !net.minecraft.a.a.b.x.e[n8]) {
            n9 = 5;
        }
        if (net.minecraft.a.a.b.x.e[n8] && !net.minecraft.a.a.b.x.e[n7]) {
            n9 = 4;
        }
        g2.f(n2, n3, n4, n9);
    }

    public final int a(g g2, int n2, int n3, int n4, int n5) {
        if (n5 == 1) {
            return net.minecraft.a.a.b.x.i.as;
        }
        if (n5 == 0) {
            return net.minecraft.a.a.b.x.i.as;
        }
        byte by = g2.e(n2, n3, n4);
        if (by == 0) {
            net.minecraft.a.a.b.s.e(g2, n2, n3, n4);
            by = g2.e(n2, n3, n4);
        }
        if (n5 != by) {
            return this.as;
        }
        if (this.a) {
            return this.as + 16;
        }
        return this.as - 1;
    }

    public final void b(g g2, int n2, int n3, int n4, Random random) {
        if (!this.a) {
            return;
        }
        byte by = g2.e(n2, n3, n4);
        float f2 = (float)n2 + 0.5f;
        float f3 = (float)n3 + random.nextFloat() * 6.0f / 16.0f;
        float f4 = (float)n4 + 0.5f;
        float f5 = random.nextFloat() * 0.6f - 0.3f;
        if (by == 4) {
            g2.a("smoke", f2 - 0.52f, f3, f4 + f5, 0.0f, 0.0f, 0.0f);
            g2.a("flame", f2 - 0.52f, f3, f4 + f5, 0.0f, 0.0f, 0.0f);
            return;
        }
        if (by == 5) {
            g2.a("smoke", f2 + 0.52f, f3, f4 + f5, 0.0f, 0.0f, 0.0f);
            g2.a("flame", f2 + 0.52f, f3, f4 + f5, 0.0f, 0.0f, 0.0f);
            return;
        }
        if (by == 2) {
            g2.a("smoke", f2 + f5, f3, f4 - 0.52f, 0.0f, 0.0f, 0.0f);
            g2.a("flame", f2 + f5, f3, f4 - 0.52f, 0.0f, 0.0f, 0.0f);
            return;
        }
        if (by == 3) {
            g2.a("smoke", f2 + f5, f3, f4 + 0.52f, 0.0f, 0.0f, 0.0f);
            g2.a("flame", f2 + f5, f3, f4 + 0.52f, 0.0f, 0.0f, 0.0f);
        }
    }

    public final int a(int n2) {
        if (n2 == 1) {
            return net.minecraft.a.a.b.x.i.at;
        }
        if (n2 == 0) {
            return net.minecraft.a.a.b.x.i.at;
        }
        if (n2 == 3) {
            return this.as - 1;
        }
        return this.as;
    }

    public final boolean a(g object, int n2, int n3, int n4, net.minecraft.a.c.e.a a2) {
        object = (b)((g)object).j(n2, n3, n4);
        a2.a((b)object);
        return true;
    }

    protected final a a_() {
        return new b();
    }
}

