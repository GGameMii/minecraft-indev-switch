/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.a.b;

import java.util.Random;
import net.minecraft.a.a.b.g;
import net.minecraft.a.a.b.x;
import net.minecraft.a.a.d.c;

public final class o
extends g {
    protected o(int n2, int n3) {
        super(18, 52, net.minecraft.a.a.d.c.h, true);
        this.a(true);
    }

    public final void a(net.minecraft.a.a.g g2, int n2, int n3, int n4, Random random) {
        if (g2.f(n2, n3 - 1, n4).a()) {
            return;
        }
        for (int i2 = n2 - 2; i2 <= n2 + 2; ++i2) {
            for (int i3 = n3 - 1; i3 <= n3; ++i3) {
                for (int i4 = n4 - 2; i4 <= n4 + 2; ++i4) {
                    if (g2.a(i2, i3, i4) != net.minecraft.a.a.b.x.y.at) continue;
                    return;
                }
            }
        }
        this.f(g2, n2, n3, n4, g2.e(n2, n3, n4));
        g2.b(n2, n3, n4, 0);
    }

    public final int a(Random random) {
        if (random.nextInt(10) == 0) {
            return 1;
        }
        return 0;
    }

    public final int a(int n2, Random random) {
        return net.minecraft.a.a.b.x.n.at;
    }
}

