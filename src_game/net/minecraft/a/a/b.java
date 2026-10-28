/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.a;

import net.minecraft.a.a.g;
import net.minecraft.a.c.a.d;
import net.minecraft.a.c.a.e;
import net.minecraft.a.c.a.f;
import net.minecraft.a.c.b.a;
import net.minecraft.a.c.b.c;

public class b {
    private g a;

    public b(g g2) {
        this.a = g2;
    }

    public final void a() {
        float f2;
        float f3;
        float f4;
        float f5;
        float f6;
        int n2;
        int n3;
        int n4;
        int n5;
        int n6;
        int n7;
        int n8;
        int n9;
        int n10;
        int n11;
        net.minecraft.a.c.e e2;
        int n12;
        int n13 = this.a.a * this.a.b * this.a.c * 20 / 64 / 64 / 64;
        n13 /= 2;
        if (this.a.E == 0) {
            n13 = 0 / 4;
        }
        if (this.a.E == 1) {
            n13 = n13 * 3 / 4;
        }
        if (this.a.E == 2) {
            n13 = (n13 << 2) / 4;
        }
        if (this.a.E == 3) {
            n13 = n13 * 6 / 4;
        }
        int n14 = this.a.a * this.a.b / 4000;
        int n15 = this.a.a(e.class);
        for (n12 = 0; n12 < 4; ++n12) {
            if (n15 >= n13) continue;
            e2 = this.a.y;
            b b2 = this;
            n11 = 0;
            n10 = b2.a.q.nextInt(5);
            n9 = b2.a.q.nextInt(b2.a.a);
            n8 = (int)(Math.min(b2.a.q.nextFloat(), b2.a.q.nextFloat()) * (float)b2.a.c);
            n7 = b2.a.q.nextInt(b2.a.b);
            for (n6 = 0; n6 < 2; ++n6) {
                n5 = n9;
                n4 = n8;
                n3 = n7;
                for (n2 = 0; n2 < 3; ++n2) {
                    float f7;
                    if ((n5 += b2.a.q.nextInt(6) - b2.a.q.nextInt(6)) < 0 || (n3 += b2.a.q.nextInt(6) - b2.a.q.nextInt(6)) <= 0 || (n4 += b2.a.q.nextInt(1) - b2.a.q.nextInt(1)) < 0 || n4 >= b2.a.c - 2 || n5 >= b2.a.a || n3 >= b2.a.b) continue;
                    f6 = (float)n5 + 0.5f;
                    f5 = (float)n4 + 0.5f;
                    f4 = (float)n3 + 0.5f;
                    if (e2 != null) {
                        f7 = f6 - e2.h;
                        f3 = f5 - e2.i;
                        f2 = f4 - e2.j;
                        if ((f7 = f7 * f7 + f3 * f3 + f2 * f2) < 1024.0f) continue;
                    } else {
                        f7 = f6 - (float)b2.a.i;
                        f3 = f5 - (float)b2.a.j;
                        f2 = f4 - (float)b2.a.k;
                        if ((f7 = f7 * f7 + f3 * f3 + f2 * f2) < 1024.0f) continue;
                    }
                    e e3 = null;
                    if (n10 == 0) {
                        e3 = new net.minecraft.a.c.a.c(b2.a);
                    }
                    if (n10 == 1) {
                        e3 = new d(b2.a);
                    }
                    if (n10 == 2) {
                        e3 = new net.minecraft.a.c.a.b(b2.a);
                    }
                    if (n10 == 3) {
                        e3 = new f(b2.a);
                    }
                    if (e3 instanceof e && b2.a.E == 0) {
                        e3 = null;
                    }
                    if (e3 == null || b2.a.b(n5, n4, n3) || !b2.a.b(n5, n4 - 1, n3) || !((net.minecraft.a.c.e)e3).a(f6, f5, f4)) continue;
                    f2 = b2.a.q.nextFloat() * 360.0f;
                    e3.b(f6, f5, f4, f2, 0.0f);
                    ++n11;
                    b2.a.a(e3);
                }
            }
            n15 += n11;
        }
        n12 = this.a.a(a.class);
        for (n13 = 0; n13 < 4; ++n13) {
            if (n12 >= n14) continue;
            e2 = this.a.y;
            b b3 = this;
            n11 = 0;
            n10 = b3.a.q.nextInt(2);
            n9 = b3.a.q.nextInt(b3.a.a);
            n8 = b3.a.q.nextInt(b3.a.c);
            n7 = b3.a.q.nextInt(b3.a.b);
            for (n6 = 0; n6 < 2; ++n6) {
                n5 = n9;
                n4 = n8;
                n3 = n7;
                for (n2 = 0; n2 < 3; ++n2) {
                    float f8;
                    if ((n5 += b3.a.q.nextInt(6) - b3.a.q.nextInt(6)) < 0 || (n3 += b3.a.q.nextInt(6) - b3.a.q.nextInt(6)) <= 0 || (n4 += b3.a.q.nextInt(1) - b3.a.q.nextInt(1)) < 0 || n4 >= b3.a.c - 2 || n5 >= b3.a.a || n3 >= b3.a.b) continue;
                    f6 = (float)n5 + 0.5f;
                    f5 = (float)n4 + 0.5f;
                    f4 = (float)n3 + 0.5f;
                    if (e2 != null) {
                        f8 = f6 - e2.h;
                        f3 = f5 - e2.i;
                        f2 = f4 - e2.j;
                        if ((f8 = f8 * f8 + f3 * f3 + f2 * f2) < 1024.0f) continue;
                    } else {
                        f8 = f6 - (float)b3.a.i;
                        f3 = f5 - (float)b3.a.j;
                        f2 = f4 - (float)b3.a.k;
                        if ((f8 = f8 * f8 + f3 * f3 + f2 * f2) < 1024.0f) continue;
                    }
                    a a2 = null;
                    if (n10 == 0) {
                        a2 = new c(b3.a);
                    }
                    if (n10 == 1) {
                        a2 = new net.minecraft.a.c.b.b(b3.a);
                    }
                    if (a2 == null || b3.a.b(n5, n4, n3) || !b3.a.b(n5, n4 - 1, n3) || !((net.minecraft.a.c.e)a2).a(f6, f5, f4)) continue;
                    f2 = b3.a.q.nextFloat() * 360.0f;
                    a2.b(f6, f5, f4, f2, 0.0f);
                    ++n11;
                    b3.a.a(a2);
                }
            }
            n12 += n11;
        }
    }
}

