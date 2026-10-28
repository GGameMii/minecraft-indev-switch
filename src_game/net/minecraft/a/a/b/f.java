/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.a.b;

import java.util.Random;
import net.minecraft.a.a.b.ad;
import net.minecraft.a.a.b.x;
import net.minecraft.a.a.g;
import net.minecraft.a.b.i;
import net.minecraft.a.b.k;
import net.minecraft.a.c.c.b;

public final class f
extends ad {
    protected f(int n2, int n3) {
        super(59, 88);
        this.as = 88;
        this.a(true);
        this.a(0.0f, 0.0f, 0.0f, 1.0f, 0.25f, 1.0f);
    }

    protected final boolean b(int n2) {
        return n2 == net.minecraft.a.a.b.x.ap.at;
    }

    public final void a(g g2, int n2, int n3, int n4, Random random) {
        int n5;
        super.a(g2, n2, n3, n4, random);
        if (g2.d(n2, n3 + 1, n4) >= 9 && (n5 = g2.e(n2, n3, n4)) < 7) {
            float f2;
            int n6 = n4;
            int n7 = n3;
            int n8 = n2;
            g g3 = g2;
            f f3 = this;
            float f4 = 1.0f;
            int n9 = g3.a(n8, n7, n6 - 1);
            int n10 = g3.a(n8, n7, n6 + 1);
            int n11 = g3.a(n8 - 1, n7, n6);
            int n12 = g3.a(n8 + 1, n7, n6);
            int n13 = g3.a(n8 - 1, n7, n6 - 1);
            int n14 = g3.a(n8 + 1, n7, n6 - 1);
            int n15 = g3.a(n8 + 1, n7, n6 + 1);
            int n16 = g3.a(n8 - 1, n7, n6 + 1);
            n11 = n11 == f3.at || n12 == f3.at ? 1 : 0;
            n9 = n9 == f3.at || n10 == f3.at ? 1 : 0;
            boolean bl = n13 == f3.at || n14 == f3.at || n15 == f3.at || n16 == f3.at;
            for (n10 = n8 - 1; n10 <= n8 + 1; ++n10) {
                for (n12 = n6 - 1; n12 <= n6 + 1; ++n12) {
                    n13 = g3.a(n10, n7 - 1, n12);
                    float f5 = 0.0f;
                    if (n13 == net.minecraft.a.a.b.x.ap.at) {
                        f5 = 1.0f;
                        if (g3.e(n10, n7 - 1, n12) > 0) {
                            f5 = 3.0f;
                        }
                    }
                    if (n10 != n8 || n12 != n6) {
                        f5 /= 4.0f;
                    }
                    f4 += f5;
                }
            }
            if (bl || n11 != 0 && n9 != 0) {
                f4 /= 2.0f;
            }
            if (random.nextInt((int)(100.0f / (f2 = f4))) == 0) {
                g2.f(n2, n3, n4, ++n5);
            }
        }
    }

    public final int a(int n2, int n3) {
        if (n3 < 0) {
            n3 = 7;
        }
        return this.as + n3;
    }

    public final int a() {
        return 6;
    }

    public final void c(g g2, int n2, int n3, int n4, int n5) {
        super.c(g2, n2, n3, n4, n5);
        for (int i2 = 0; i2 < 3; ++i2) {
            if (g2.q.nextInt(15) > n5) continue;
            float f2 = g2.q.nextFloat() * 0.7f + 0.15f;
            float f3 = g2.q.nextFloat() * 0.7f + 0.15f;
            float f4 = g2.q.nextFloat() * 0.7f + 0.15f;
            b b2 = new b(g2, (float)n2 + f2, (float)n3 + f3, (float)n4 + f4, new k(net.minecraft.a.b.i.O));
            new b(g2, (float)n2 + f2, (float)n3 + f3, (float)n4 + f4, new k(net.minecraft.a.b.i.O)).O = 10;
            g2.a(b2);
        }
    }

    public final int a(int n2, Random random) {
        System.out.println("Get resource: " + n2);
        if (n2 == 7) {
            return net.minecraft.a.b.i.P.ap;
        }
        return -1;
    }

    public final int a(Random random) {
        return 1;
    }
}

