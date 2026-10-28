/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.a.b;

import java.util.Random;
import net.minecraft.a.a.b.a.c;
import net.minecraft.a.a.b.q;
import net.minecraft.a.a.b.x;
import net.minecraft.a.a.g;
import net.minecraft.a.b;
import net.minecraft.a.b.k;

public final class a
extends q {
    private Random a = new Random();

    protected a(int n2) {
        super(54, net.minecraft.a.a.d.c.c);
        this.as = 26;
    }

    public final int a(g g2, int n2, int n3, int n4, int n5) {
        if (n5 == 1) {
            return this.as - 1;
        }
        if (n5 == 0) {
            return this.as - 1;
        }
        int n6 = g2.a(n2, n3, n4 - 1);
        int n7 = g2.a(n2, n3, n4 + 1);
        int n8 = g2.a(n2 - 1, n3, n4);
        int n9 = g2.a(n2 + 1, n3, n4);
        if (n6 == this.at || n7 == this.at) {
            if (n5 == 2 || n5 == 3) {
                return this.as;
            }
            int n10 = 0;
            if (n6 == this.at) {
                n10 = -1;
            }
            int n11 = g2.a(n2 - 1, n3, n6 == this.at ? n4 - 1 : n4 + 1);
            int n12 = g2.a(n2 + 1, n3, n6 == this.at ? n4 - 1 : n4 + 1);
            if (n5 == 4) {
                n10 = -1 - n10;
            }
            n2 = 5;
            if ((net.minecraft.a.a.b.x.e[n8] || net.minecraft.a.a.b.x.e[n11]) && !net.minecraft.a.a.b.x.e[n9] && !net.minecraft.a.a.b.x.e[n12]) {
                n2 = 5;
            }
            if ((net.minecraft.a.a.b.x.e[n9] || net.minecraft.a.a.b.x.e[n12]) && !net.minecraft.a.a.b.x.e[n8] && !net.minecraft.a.a.b.x.e[n11]) {
                n2 = 4;
            }
            return (n5 == n2 ? this.as + 16 : this.as + 32) + n10;
        }
        if (n8 == this.at || n9 == this.at) {
            if (n5 == 4 || n5 == 5) {
                return this.as;
            }
            int n13 = 0;
            if (n8 == this.at) {
                n13 = -1;
            }
            int n14 = g2.a(n8 == this.at ? n2 - 1 : n2 + 1, n3, n4 - 1);
            int n15 = g2.a(n8 == this.at ? n2 - 1 : n2 + 1, n3, n4 + 1);
            if (n5 == 3) {
                n13 = -1 - n13;
            }
            n2 = 3;
            if ((net.minecraft.a.a.b.x.e[n6] || net.minecraft.a.a.b.x.e[n14]) && !net.minecraft.a.a.b.x.e[n7] && !net.minecraft.a.a.b.x.e[n15]) {
                n2 = 3;
            }
            if ((net.minecraft.a.a.b.x.e[n7] || net.minecraft.a.a.b.x.e[n15]) && !net.minecraft.a.a.b.x.e[n6] && !net.minecraft.a.a.b.x.e[n14]) {
                n2 = 2;
            }
            return (n5 == n2 ? this.as + 16 : this.as + 32) + n13;
        }
        int n16 = 3;
        if (net.minecraft.a.a.b.x.e[n6] && !net.minecraft.a.a.b.x.e[n7]) {
            n16 = 3;
        }
        if (net.minecraft.a.a.b.x.e[n7] && !net.minecraft.a.a.b.x.e[n6]) {
            n16 = 2;
        }
        if (net.minecraft.a.a.b.x.e[n8] && !net.minecraft.a.a.b.x.e[n9]) {
            n16 = 5;
        }
        if (net.minecraft.a.a.b.x.e[n9] && !net.minecraft.a.a.b.x.e[n8]) {
            n16 = 4;
        }
        if (n5 == n16) {
            return this.as + 1;
        }
        return this.as;
    }

    public final int a(int n2) {
        if (n2 == 1) {
            return this.as - 1;
        }
        if (n2 == 0) {
            return this.as - 1;
        }
        if (n2 == 3) {
            return this.as + 1;
        }
        return this.as;
    }

    public final boolean a(g g2, int n2, int n3, int n4) {
        int n5 = 0;
        if (g2.a(n2 - 1, n3, n4) == this.at) {
            ++n5;
        }
        if (g2.a(n2 + 1, n3, n4) == this.at) {
            ++n5;
        }
        if (g2.a(n2, n3, n4 - 1) == this.at) {
            ++n5;
        }
        if (g2.a(n2, n3, n4 + 1) == this.at) {
            ++n5;
        }
        if (n5 > 1) {
            return false;
        }
        if (this.e(g2, n2 - 1, n3, n4)) {
            return false;
        }
        if (this.e(g2, n2 + 1, n3, n4)) {
            return false;
        }
        if (this.e(g2, n2, n3, n4 - 1)) {
            return false;
        }
        return !this.e(g2, n2, n3, n4 + 1);
    }

    private boolean e(g g2, int n2, int n3, int n4) {
        if (g2.a(n2, n3, n4) != this.at) {
            return false;
        }
        if (g2.a(n2 - 1, n3, n4) == this.at) {
            return true;
        }
        if (g2.a(n2 + 1, n3, n4) == this.at) {
            return true;
        }
        if (g2.a(n2, n3, n4 - 1) == this.at) {
            return true;
        }
        return g2.a(n2, n3, n4 + 1) == this.at;
    }

    public final void b(g g2, int n2, int n3, int n4) {
        c c2 = (c)g2.j(n2, n3, n4);
        for (int i2 = 0; i2 < c2.a(); ++i2) {
            k k2 = c2.a(i2);
            if (k2 == null) continue;
            float f2 = this.a.nextFloat() * 0.8f + 0.1f;
            float f3 = this.a.nextFloat() * 0.8f + 0.1f;
            float f4 = this.a.nextFloat() * 0.8f + 0.1f;
            while (k2.a > 0) {
                int n5 = this.a.nextInt(21) + 10;
                if (n5 > k2.a) {
                    n5 = k2.a;
                }
                k2.a -= n5;
                net.minecraft.a.c.c.b b2 = new net.minecraft.a.c.c.b(g2, (float)n2 + f2, (float)n3 + f3, (float)n4 + f4, new k(k2.c, n5, k2.d));
                new net.minecraft.a.c.c.b(g2, (float)n2 + f2, (float)n3 + f3, (float)n4 + f4, new k(k2.c, n5, k2.d)).k = (float)this.a.nextGaussian() * 0.05f;
                b2.l = (float)this.a.nextGaussian() * 0.05f + 0.2f;
                b2.m = (float)this.a.nextGaussian() * 0.05f;
                g2.a(b2);
            }
        }
        super.b(g2, n2, n3, n4);
    }

    public final boolean a(g g2, int n2, int n3, int n4, net.minecraft.a.c.e.a a2) {
        b b2 = (c)g2.j(n2, n3, n4);
        if (g2.b(n2, n3 + 1, n4)) {
            return true;
        }
        if (g2.a(n2 - 1, n3, n4) == this.at && g2.b(n2 - 1, n3 + 1, n4)) {
            return true;
        }
        if (g2.a(n2 + 1, n3, n4) == this.at && g2.b(n2 + 1, n3 + 1, n4)) {
            return true;
        }
        if (g2.a(n2, n3, n4 - 1) == this.at && g2.b(n2, n3 + 1, n4 - 1)) {
            return true;
        }
        if (g2.a(n2, n3, n4 + 1) == this.at && g2.b(n2, n3 + 1, n4 + 1)) {
            return true;
        }
        if (g2.a(n2 - 1, n3, n4) == this.at) {
            b2 = new net.minecraft.a.a("Large chest", (c)g2.j(n2 - 1, n3, n4), b2);
        }
        if (g2.a(n2 + 1, n3, n4) == this.at) {
            b2 = new net.minecraft.a.a("Large chest", b2, (c)g2.j(n2 + 1, n3, n4));
        }
        if (g2.a(n2, n3, n4 - 1) == this.at) {
            b2 = new net.minecraft.a.a("Large chest", (c)g2.j(n2, n3, n4 - 1), b2);
        }
        if (g2.a(n2, n3, n4 + 1) == this.at) {
            b2 = new net.minecraft.a.a("Large chest", b2, (c)g2.j(n2, n3, n4 + 1));
        }
        a2.a(b2);
        return true;
    }

    protected final net.minecraft.a.a.b.a.a a_() {
        return new c();
    }
}

