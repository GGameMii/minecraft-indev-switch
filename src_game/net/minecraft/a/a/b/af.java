/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.a.b;

import java.util.Random;
import net.minecraft.a.a.b.x;
import net.minecraft.a.a.d.c;
import net.minecraft.a.a.g;

public final class af
extends x {
    protected af(int n2) {
        super(2, net.minecraft.a.a.d.c.b);
        this.as = 3;
        this.a(true);
    }

    public final int a(int n2) {
        if (n2 == 1) {
            return 0;
        }
        if (n2 == 0) {
            return 2;
        }
        return 3;
    }

    public final void a(g g2, int n2, int n3, int n4, Random random) {
        if (g2.d(n2, n3 + 1, n4) < 4 && g2.f(n2, n3 + 1, n4).b()) {
            if (random.nextInt(4) != 0) {
                return;
            }
            g2.b(n2, n3, n4, net.minecraft.a.a.b.x.k.at);
            return;
        }
        if (g2.d(n2, n3 + 1, n4) >= 9 && g2.a(n2 = n2 + random.nextInt(3) - 1, n3 = n3 + random.nextInt(5) - 3, n4 = n4 + random.nextInt(3) - 1) == net.minecraft.a.a.b.x.k.at && g2.d(n2, n3 + 1, n4) >= 4 && !g2.f(n2, n3 + 1, n4).b()) {
            g2.b(n2, n3, n4, net.minecraft.a.a.b.x.j.at);
        }
    }

    public final int a(int n2, Random random) {
        return net.minecraft.a.a.b.x.k.a(0, random);
    }
}

