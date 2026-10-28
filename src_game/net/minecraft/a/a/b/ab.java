/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.a.b;

import java.util.Random;
import net.minecraft.a.a.b.x;
import net.minecraft.a.a.d.c;
import net.minecraft.a.a.g;
import net.minecraft.a.d.b;

public final class ab
extends x {
    protected ab(int n2) {
        super(60, net.minecraft.a.a.d.c.b);
        this.as = 87;
        this.a(true);
        this.a(0.0f, 0.0f, 0.0f, 1.0f, 0.9375f, 1.0f);
        this.c(255);
    }

    public final b a(int n2, int n3, int n4) {
        return new b(n2, n3, n4, n2 + 1, n3 + 1, n4 + 1);
    }

    public final boolean b() {
        return false;
    }

    public final boolean c() {
        return false;
    }

    public final int a(int n2, int n3) {
        if (n2 == 1 && n3 > 0) {
            return this.as - 1;
        }
        if (n2 == 1) {
            return this.as;
        }
        return 2;
    }

    public final void a(g g2, int n2, int n3, int n4, Random object) {
        if (((Random)object).nextInt(5) == 0) {
            boolean bl;
            block10: {
                boolean bl2;
                int n5;
                int n6;
                int n7;
                int n8;
                int n9;
                block9: {
                    n9 = n4;
                    n8 = n3;
                    n7 = n2;
                    object = g2;
                    for (int i2 = n7 - 4; i2 <= n7 + 4; ++i2) {
                        for (n6 = n8; n6 <= n8 + 1; ++n6) {
                            for (n5 = n9 - 4; n5 <= n9 + 4; ++n5) {
                                if (((g)object).f(i2, n6, n5) != net.minecraft.a.a.d.c.f) continue;
                                bl2 = true;
                                break block9;
                            }
                        }
                    }
                    bl2 = false;
                }
                if (bl2) {
                    g2.f(n2, n3, n4, 7);
                    return;
                }
                byte by = g2.e(n2, n3, n4);
                if (by > 0) {
                    g2.f(n2, n3, n4, by - 1);
                    return;
                }
                n9 = n4;
                n8 = n3;
                n7 = n2;
                g g3 = g2;
                for (n6 = n7; n6 <= n7; ++n6) {
                    for (n5 = n9; n5 <= n9; ++n5) {
                        if (g3.a(n6, n8 + 1, n5) != net.minecraft.a.a.b.x.ao.at) continue;
                        bl = true;
                        break block10;
                    }
                }
                bl = false;
            }
            if (!bl) {
                g2.b(n2, n3, n4, net.minecraft.a.a.b.x.k.at);
            }
        }
    }

    public final void g(g g2, int n2, int n3, int n4) {
        if (g2.q.nextInt(4) == 0) {
            g2.b(n2, n3, n4, net.minecraft.a.a.b.x.k.at);
        }
    }

    public final void b(g g2, int n2, int n3, int n4, int n5) {
        super.b(g2, n2, n3, n4, n5);
        c c2 = g2.f(n2, n3 + 1, n4);
        if (c2.a()) {
            g2.b(n2, n3, n4, net.minecraft.a.a.b.x.k.at);
        }
    }

    public final int a(int n2, Random random) {
        return net.minecraft.a.a.b.x.k.a(0, random);
    }
}

