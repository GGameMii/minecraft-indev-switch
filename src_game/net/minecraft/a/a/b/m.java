/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.a.b;

import java.util.Random;
import net.minecraft.a.a.b.p;
import net.minecraft.a.a.b.x;
import net.minecraft.a.a.d.c;
import net.minecraft.a.a.g;
import net.minecraft.a.d.b;

public final class m
extends p {
    private int aD;
    private int aE;
    private Random aF = new Random();
    private int[] aG = new int[]{0, 1, 2, 3};

    protected m(int n2, c c2) {
        super(n2, c2);
        this.as = 14;
        if (c2 == net.minecraft.a.a.d.c.g) {
            this.as = 30;
        }
        net.minecraft.a.a.b.x.g[n2] = true;
        this.aE = n2;
        this.aD = n2 + 1;
        this.a(0.01f, -0.09f, 0.01f, 1.01f, 0.90999997f, 1.01f);
        this.a(true);
    }

    public final void d(g g2, int n2, int n3, int n4) {
        g2.e(n2, n3, n4, this.aE);
    }

    public final void a(g g2, int n2, int n3, int n4, Random random) {
        this.e(g2, n2, n3, n4, 0);
    }

    public final boolean e(g g2, int n2, int n3, int n4, int n5) {
        int n6;
        int n7;
        int n8;
        n5 = 0;
        boolean bl = this.e(g2, n2 - 1, n3, n4) || this.e(g2, n2 + 1, n3, n4) || this.e(g2, n2, n3, n4 - 1) || this.e(g2, n2, n3, n4 + 1);
        if (bl && g2.f(n2, n3 - 1, n4) == this.aC && (n5 = g2.b(n2, n3 - 1, n4, this.aE, this.aD)) == 1) {
            int n9 = g2.a(n2, n3, n4, this.aE, this.aD);
            if (n9 != -9999) {
                if (n9 < 0) {
                    return false;
                }
                int n10 = n9 % 1024;
                int n11 = (n9 >>= 10) % 1024;
                n2 = (n9 >>= 10) % 1024;
                g2.b(n10, n2, n11, 0);
                return false;
            }
            return false;
        }
        n5 = this.b(g2, n2, n3, n4, n2, n3 - 1, n4) ? 1 : 0;
        for (n8 = 0; n8 < 4; ++n8) {
            n7 = this.aF.nextInt(4 - n8) + n8;
            n6 = this.aG[n8];
            this.aG[n8] = this.aG[n7];
            this.aG[n7] = n6;
            if (this.aG[n8] == 0 && n5 == 0) {
                n5 = this.b(g2, n2, n3, n4, n2 - 1, n3, n4) ? 1 : 0;
            }
            if (this.aG[n8] == 1 && n5 == 0) {
                n5 = this.b(g2, n2, n3, n4, n2 + 1, n3, n4) ? 1 : 0;
            }
            if (this.aG[n8] == 2 && n5 == 0) {
                n5 = this.b(g2, n2, n3, n4, n2, n3, n4 - 1) ? 1 : 0;
            }
            if (this.aG[n8] != 3 || n5 != 0) continue;
            n5 = this.b(g2, n2, n3, n4, n2, n3, n4 + 1) ? 1 : 0;
        }
        if (n5 == 0 && bl) {
            if (this.aF.nextInt(3) == 0) {
                if (this.aF.nextInt(3) == 0) {
                    n5 = 0;
                    for (n8 = 0; n8 < 4; ++n8) {
                        n7 = this.aF.nextInt(4 - n8) + n8;
                        n6 = this.aG[n8];
                        this.aG[n8] = this.aG[n7];
                        this.aG[n7] = n6;
                        if (this.aG[n8] == 0 && n5 == 0) {
                            n5 = this.a(g2, n2, n3, n4, n2 - 1, n3, n4) ? 1 : 0;
                        }
                        if (this.aG[n8] == 1 && n5 == 0) {
                            n5 = this.a(g2, n2, n3, n4, n2 + 1, n3, n4) ? 1 : 0;
                        }
                        if (this.aG[n8] == 2 && n5 == 0) {
                            n5 = this.a(g2, n2, n3, n4, n2, n3, n4 - 1) ? 1 : 0;
                        }
                        if (this.aG[n8] != 3 || n5 != 0) continue;
                        n5 = this.a(g2, n2, n3, n4, n2, n3, n4 + 1) ? 1 : 0;
                    }
                } else if (this.aC == net.minecraft.a.a.d.c.g) {
                    g2.b(n2, n3, n4, net.minecraft.a.a.b.x.i.at);
                } else {
                    g2.b(n2, n3, n4, 0);
                }
            }
            return false;
        }
        if (this.aC == net.minecraft.a.a.d.c.f) {
            n5 |= net.minecraft.a.a.b.m.h(g2, n2 - 1, n3, n4);
            n5 |= net.minecraft.a.a.b.m.h(g2, n2 + 1, n3, n4);
            n5 |= net.minecraft.a.a.b.m.h(g2, n2, n3, n4 - 1);
            n5 |= net.minecraft.a.a.b.m.h(g2, n2, n3, n4 + 1);
        }
        if (this.aC == net.minecraft.a.a.d.c.g) {
            n5 |= net.minecraft.a.a.b.m.i(g2, n2 - 1, n3, n4);
            n5 |= net.minecraft.a.a.b.m.i(g2, n2 + 1, n3, n4);
            n5 |= net.minecraft.a.a.b.m.i(g2, n2, n3, n4 - 1);
            n5 |= net.minecraft.a.a.b.m.i(g2, n2, n3, n4 + 1);
        }
        if (n5 == 0) {
            g2.d(n2, n3, n4, this.aD);
        } else {
            g2.e(n2, n3, n4, this.aE);
        }
        return n5 != 0;
    }

    private boolean a(g g2, int n2, int n3, int n4, int n5, int n6, int n7) {
        if (this.e(g2, n5, n6, n7)) {
            g2.b(n5, n6, n7, this.at);
            g2.e(n5, n6, n7, this.at);
            return true;
        }
        return false;
    }

    private boolean b(g g2, int n2, int n3, int n4, int n5, int n6, int n7) {
        if (this.e(g2, n5, n6, n7)) {
            if ((n2 = g2.a(n2, n3, n4, this.aE, this.aD)) != -9999) {
                if (n2 < 0) {
                    return false;
                }
                n3 = n2 % 1024;
                n4 = (n2 >>= 10) % 1024;
                n2 >>= 10;
                if (!((n2 %= 1024) <= n6 && this.e(g2, n5, n6 - 1, n7) || n2 > n6 || n3 == 0 || n3 == g2.a - 1 || n4 == 0 || n4 == g2.b - 1)) {
                    return false;
                }
                g2.b(n3, n2, n4, 0);
            }
            g2.b(n5, n6, n7, this.at);
            g2.e(n5, n6, n7, this.at);
            return true;
        }
        return false;
    }

    public final boolean d(g g2, int n2, int n3, int n4, int n5) {
        if (n2 < 0 || n3 < 0 || n4 < 0 || n2 >= g2.a || n4 >= g2.b) {
            return false;
        }
        int n6 = g2.a(n2, n3, n4);
        if (n6 == this.aE || n6 == this.aD) {
            return false;
        }
        if (n5 == 1 && (g2.a(n2 - 1, n3, n4) == 0 || g2.a(n2 + 1, n3, n4) == 0 || g2.a(n2, n3, n4 - 1) == 0 || g2.a(n2, n3, n4 + 1) == 0)) {
            return true;
        }
        return super.d(g2, n2, n3, n4, n5);
    }

    public final boolean d() {
        return false;
    }

    public final b a(int n2, int n3, int n4) {
        return null;
    }

    public final boolean b() {
        return false;
    }

    public final void b(g g2, int n2, int n3, int n4, int n5) {
    }

    public final int e() {
        if (this.aC == net.minecraft.a.a.d.c.g) {
            return 25;
        }
        return 5;
    }

    public final int a(Random random) {
        return 0;
    }

    public final int f() {
        if (this.aC == net.minecraft.a.a.d.c.f) {
            return 1;
        }
        return 0;
    }

    private static boolean h(g g2, int n2, int n3, int n4) {
        if (g2.a(n2, n3, n4) == net.minecraft.a.a.b.x.ag.at) {
            g2.b(n2, n3, n4, 0);
            return true;
        }
        if (g2.a(n2, n3, n4) == net.minecraft.a.a.b.x.r.at || g2.a(n2, n3, n4) == net.minecraft.a.a.b.x.s.at) {
            g2.b(n2, n3, n4, net.minecraft.a.a.b.x.i.at);
            return true;
        }
        return false;
    }

    private static boolean i(g g2, int n2, int n3, int n4) {
        if (net.minecraft.a.a.b.x.ag.b(g2.a(n2, n3, n4))) {
            net.minecraft.a.a.b.x.ag.h(g2, n2, n3, n4);
            return true;
        }
        return false;
    }
}

