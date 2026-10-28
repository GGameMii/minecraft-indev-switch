/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.a.b;

import java.util.Random;
import net.minecraft.a.a.b.x;
import net.minecraft.a.a.d.c;
import net.minecraft.a.a.g;

public final class ai
extends x {
    private int a;

    protected ai(int n2, int n3) {
        super(n2, net.minecraft.a.a.b.x.c[n3].as, net.minecraft.a.a.d.c.f);
        this.a = n3;
        this.a(true);
    }

    public final void d(g g2, int n2, int n3, int n4) {
        super.d(g2, n2, n3, n4);
        if (g2.a(n2 - 1, n3, n4) == 0) {
            g2.b(n2 - 1, n3, n4, this.a);
        }
        if (g2.a(n2 + 1, n3, n4) == 0) {
            g2.b(n2 + 1, n3, n4, this.a);
        }
        if (g2.a(n2, n3, n4 - 1) == 0) {
            g2.b(n2, n3, n4 - 1, this.a);
        }
        if (g2.a(n2, n3, n4 + 1) == 0) {
            g2.b(n2, n3, n4 + 1, this.a);
        }
    }

    public final void a(g g2, int n2, int n3, int n4, Random random) {
        super.a(g2, n2, n3, n4, random);
        if (g2.a(n2 - 1, n3, n4) == 0) {
            g2.b(n2 - 1, n3, n4, this.a);
        }
        if (g2.a(n2 + 1, n3, n4) == 0) {
            g2.b(n2 + 1, n3, n4, this.a);
        }
        if (g2.a(n2, n3, n4 - 1) == 0) {
            g2.b(n2, n3, n4 - 1, this.a);
        }
        if (g2.a(n2, n3, n4 + 1) == 0) {
            g2.b(n2, n3, n4 + 1, this.a);
        }
    }
}

