/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.a.b;

import java.util.Random;
import net.minecraft.a.a.b.x;
import net.minecraft.a.a.d.c;
import net.minecraft.a.a.g;
import net.minecraft.a.d.b;

public final class ah
extends x {
    private int[] a = new int[256];
    private int[] b = new int[256];

    protected ah(int n2, int n3) {
        super(51, 31, net.minecraft.a.a.d.c.l);
        this.c(net.minecraft.a.a.b.x.m.at, 5, 20);
        this.c(net.minecraft.a.a.b.x.y.at, 5, 5);
        this.c(net.minecraft.a.a.b.x.z.at, 30, 60);
        this.c(net.minecraft.a.a.b.x.ac.at, 30, 20);
        this.c(net.minecraft.a.a.b.x.ab.at, 15, 100);
        for (n2 = 0; n2 < 16; ++n2) {
            this.c(net.minecraft.a.a.b.x.C.at + n2, 30, 60);
        }
        this.a(true);
    }

    private void c(int n2, int n3, int n4) {
        this.a[n2] = n3;
        this.b[n2] = n4;
    }

    public final b a(int n2, int n3, int n4) {
        return null;
    }

    public final boolean b() {
        return false;
    }

    public final boolean c() {
        return false;
    }

    public final int a() {
        return 3;
    }

    public final int a(Random random) {
        return 0;
    }

    public final int e() {
        return 20;
    }

    public final void a(g g2, int n2, int n3, int n4, Random random) {
        int n5 = g2.e(n2, n3, n4);
        if (n5 < 15) {
            g2.f(n2, n3, n4, n5 + 1);
            g2.e(n2, n3, n4, this.at);
        }
        if (!this.i(g2, n2, n3, n4)) {
            if (!g2.b(n2, n3 - 1, n4) || n5 > 3) {
                g2.b(n2, n3, n4, 0);
            }
            return;
        }
        if (!this.e(g2, n2, n3 - 1, n4) && n5 == 15 && random.nextInt(4) == 0) {
            g2.b(n2, n3, n4, 0);
            return;
        }
        if (n5 % 5 == 0 && n5 > 5) {
            this.a(g2, n2 + 1, n3, n4, 300, random);
            this.a(g2, n2 - 1, n3, n4, 300, random);
            this.a(g2, n2, n3 - 1, n4, 100, random);
            this.a(g2, n2, n3 + 1, n4, 200, random);
            this.a(g2, n2, n3, n4 - 1, 300, random);
            this.a(g2, n2, n3, n4 + 1, 300, random);
            for (n5 = n2 - 1; n5 <= n2 + 1; ++n5) {
                for (int i2 = n4 - 1; i2 <= n4 + 1; ++i2) {
                    for (int i3 = n3 - 1; i3 <= n3 + 4; ++i3) {
                        int n6;
                        int n7;
                        if (n5 == n2 && i3 == n3 && i2 == n4) continue;
                        int n8 = 100;
                        if (i3 > n3 + 1) {
                            n8 = 100 + (i3 - (n3 + 1)) * 100;
                        }
                        int n9 = i2;
                        int n10 = i3;
                        int n11 = n5;
                        g g3 = g2;
                        ah ah2 = this;
                        if (g3.a(n11, n10, n9) != 0) {
                            n7 = 0;
                        } else {
                            int n12 = ah2.e(g3, n11 + 1, n10, n9, 0);
                            n12 = ah2.e(g3, n11 - 1, n10, n9, n12);
                            n12 = ah2.e(g3, n11, n10 - 1, n9, n12);
                            n12 = ah2.e(g3, n11, n10 + 1, n9, n12);
                            n12 = ah2.e(g3, n11, n10, n9 - 1, n12);
                            n7 = n6 = (n12 = ah2.e(g3, n11, n10, n9 + 1, n12));
                        }
                        if (n7 <= 0 || random.nextInt(n8) > n6) continue;
                        g2.b(n5, i3, i2, this.at);
                    }
                }
            }
        }
    }

    private void a(g g2, int n2, int n3, int n4, int n5, Random random) {
        int n6 = this.b[g2.a(n2, n3, n4)];
        if (random.nextInt(n5) < n6) {
            int n7 = n5 = g2.a(n2, n3, n4) == net.minecraft.a.a.b.x.ab.at ? 1 : 0;
            if (random.nextInt(2) == 0) {
                g2.b(n2, n3, n4, this.at);
            } else {
                g2.b(n2, n3, n4, 0);
            }
            if (n5 != 0) {
                net.minecraft.a.a.b.x.ab.c(g2, n2, n3, n4, 0);
            }
        }
    }

    private boolean i(g g2, int n2, int n3, int n4) {
        if (this.e(g2, n2 + 1, n3, n4)) {
            return true;
        }
        if (this.e(g2, n2 - 1, n3, n4)) {
            return true;
        }
        if (this.e(g2, n2, n3 - 1, n4)) {
            return true;
        }
        if (this.e(g2, n2, n3 + 1, n4)) {
            return true;
        }
        if (this.e(g2, n2, n3, n4 - 1)) {
            return true;
        }
        return this.e(g2, n2, n3, n4 + 1);
    }

    public final boolean d() {
        return false;
    }

    public final boolean e(g g2, int n2, int n3, int n4) {
        return this.a[g2.a(n2, n3, n4)] > 0;
    }

    private int e(g g2, int n2, int n3, int n4, int n5) {
        int n6 = this.a[g2.a(n2, n3, n4)];
        if (n6 > n5) {
            return n6;
        }
        return n5;
    }

    public final boolean a(g g2, int n2, int n3, int n4) {
        return g2.b(n2, n3 - 1, n4) || this.i(g2, n2, n3, n4);
    }

    public final void b(g g2, int n2, int n3, int n4, int n5) {
        if (!g2.b(n2, n3 - 1, n4) && !this.i(g2, n2, n3, n4)) {
            g2.b(n2, n3, n4, 0);
            return;
        }
    }

    public final void d(g g2, int n2, int n3, int n4) {
        if (!g2.b(n2, n3 - 1, n4) && !this.i(g2, n2, n3, n4)) {
            g2.b(n2, n3, n4, 0);
            return;
        }
        g2.e(n2, n3, n4, this.at);
    }

    public final boolean b(int n2) {
        return this.a[n2] > 0;
    }

    public final void h(g g2, int n2, int n3, int n4) {
        boolean bl = false;
        bl = net.minecraft.a.a.b.ah.j(g2, n2, n3 + 1, n4);
        if (!bl) {
            bl = net.minecraft.a.a.b.ah.j(g2, n2 - 1, n3, n4);
        }
        if (!bl) {
            bl = net.minecraft.a.a.b.ah.j(g2, n2 + 1, n3, n4);
        }
        if (!bl) {
            bl = net.minecraft.a.a.b.ah.j(g2, n2, n3, n4 - 1);
        }
        if (!bl) {
            bl = net.minecraft.a.a.b.ah.j(g2, n2, n3, n4 + 1);
        }
        if (!bl) {
            bl = net.minecraft.a.a.b.ah.j(g2, n2, n3 - 1, n4);
        }
        if (!bl) {
            g2.b(n2, n3, n4, net.minecraft.a.a.b.x.ag.at);
        }
    }

    public final void b(g g2, int n2, int n3, int n4, Random random) {
        float f2;
        float f3;
        float f4;
        int n5;
        if (random.nextInt(24) == 0) {
            g2.a((float)n2 + 0.5f, (float)n3 + 0.5f, (float)n4 + 0.5f, "fire.fire", 1.0f + random.nextFloat(), random.nextFloat() * 0.7f + 0.3f);
        }
        if (g2.b(n2, n3 - 1, n4) || net.minecraft.a.a.b.x.ag.e(g2, n2, n3 - 1, n4)) {
            for (int i2 = 0; i2 < 3; ++i2) {
                float f5 = (float)n2 + random.nextFloat();
                float f6 = (float)n3 + random.nextFloat() * 0.5f + 0.5f;
                float f7 = (float)n4 + random.nextFloat();
                g2.a("largesmoke", f5, f6, f7, 0.0f, 0.0f, 0.0f);
            }
            return;
        }
        if (net.minecraft.a.a.b.x.ag.e(g2, n2 - 1, n3, n4)) {
            for (n5 = 0; n5 < 2; ++n5) {
                f4 = (float)n2 + random.nextFloat() * 0.1f;
                f3 = (float)n3 + random.nextFloat();
                f2 = (float)n4 + random.nextFloat();
                g2.a("largesmoke", f4, f3, f2, 0.0f, 0.0f, 0.0f);
            }
        }
        if (net.minecraft.a.a.b.x.ag.e(g2, n2 + 1, n3, n4)) {
            for (n5 = 0; n5 < 2; ++n5) {
                f4 = (float)(n2 + 1) - random.nextFloat() * 0.1f;
                f3 = (float)n3 + random.nextFloat();
                f2 = (float)n4 + random.nextFloat();
                g2.a("largesmoke", f4, f3, f2, 0.0f, 0.0f, 0.0f);
            }
        }
        if (net.minecraft.a.a.b.x.ag.e(g2, n2, n3, n4 - 1)) {
            for (n5 = 0; n5 < 2; ++n5) {
                f4 = (float)n2 + random.nextFloat();
                f3 = (float)n3 + random.nextFloat();
                f2 = (float)n4 + random.nextFloat() * 0.1f;
                g2.a("largesmoke", f4, f3, f2, 0.0f, 0.0f, 0.0f);
            }
        }
        if (net.minecraft.a.a.b.x.ag.e(g2, n2, n3, n4 + 1)) {
            for (n5 = 0; n5 < 2; ++n5) {
                f4 = (float)n2 + random.nextFloat();
                f3 = (float)n3 + random.nextFloat();
                f2 = (float)(n4 + 1) - random.nextFloat() * 0.1f;
                g2.a("largesmoke", f4, f3, f2, 0.0f, 0.0f, 0.0f);
            }
        }
        if (net.minecraft.a.a.b.x.ag.e(g2, n2, n3 + 1, n4)) {
            for (n5 = 0; n5 < 2; ++n5) {
                f4 = (float)n2 + random.nextFloat();
                f3 = (float)(n3 + 1) - random.nextFloat() * 0.1f;
                f2 = (float)n4 + random.nextFloat();
                g2.a("largesmoke", f4, f3, f2, 0.0f, 0.0f, 0.0f);
            }
        }
    }

    private static boolean j(g g2, int n2, int n3, int n4) {
        int n5 = g2.a(n2, n3, n4);
        if (n5 == net.minecraft.a.a.b.x.ag.at) {
            return true;
        }
        if (n5 == 0) {
            g2.b(n2, n3, n4, net.minecraft.a.a.b.x.ag.at);
            return true;
        }
        return false;
    }
}

