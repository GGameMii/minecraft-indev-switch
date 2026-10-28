/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.a.b;

import java.util.Random;
import net.minecraft.a.a.b.ad;
import net.minecraft.a.a.g;

public final class z
extends ad {
    protected z(int n2, int n3) {
        super(6, 15);
        this.a(0.099999994f, 0.0f, 0.099999994f, 0.9f, 0.8f, 0.9f);
    }

    public final void a(g g2, int n2, int n3, int n4, Random random) {
        super.a(g2, n2, n3, n4, random);
        if (g2.d(n2, n3 + 1, n4) >= 9 && random.nextInt(5) == 0) {
            byte by = g2.e(n2, n3, n4);
            if (by < 15) {
                g2.f(n2, n3, n4, by + 1);
                return;
            }
            g2.d(n2, n3, n4, 0);
            if (!g2.h(n2, n3, n4)) {
                g2.d(n2, n3, n4, this.at);
            }
        }
    }
}

