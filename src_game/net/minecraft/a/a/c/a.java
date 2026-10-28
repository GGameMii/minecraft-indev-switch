/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.a.c;

import java.util.ArrayList;
import java.util.Random;
import net.minecraft.a.a.b;
import net.minecraft.a.a.b.ad;
import net.minecraft.a.a.b.x;
import net.minecraft.a.a.c.a.c;
import net.minecraft.a.a.c.a.d;
import net.minecraft.a.a.g;

public final class a {
    private a.b e;
    private int f;
    private int g;
    private int h;
    private Random i = new Random();
    private byte[] j;
    private int k;
    private int l;
    public boolean a = false;
    public boolean b = false;
    public boolean c = false;
    public int d;
    private int m;
    private int n;
    private float o = 0.0f;
    private int[] p = new int[0x100000];

    public a(a.b b2) {
        this.e = b2;
    }

    /*
     * Could not resolve type clashes
     */
    public final g a(String string, int n2, int n3, int n4) {
        int n5;
        Object object;
        int n6;
        int n7;
        a a2;
        int n8;
        int n9 = 1;
        if (this.b) {
            n9 = (n4 - 64) / 48 + 1;
        }
        this.n = 13 + n9 * 4;
        this.e.a("Generating level");
        g g2 = new g();
        new g().s = this.k;
        g2.t = this.l;
        this.f = n2;
        this.g = n3;
        this.h = n4;
        this.j = new byte[n2 * n3 * n4];
        for (n8 = 0; n8 < n9; ++n8) {
            int n10;
            double d2;
            int n11;
            Object object2;
            c c2;
            Object object3;
            Object object4;
            this.k = n4 - 32 - n8 * 48;
            this.l = this.k - 2;
            if (this.c) {
                object4 = new int[n2 * n3];
                for (int i2 = 0; i2 < ((int[])object4).length; ++i2) {
                    object4[i2] = 0;
                }
                this.b();
                this.b();
            } else {
                this.e.b("Raising..");
                this.b();
                a2 = this;
                object3 = new d(new c(a2.i, 8), new c(a2.i, 8));
                d d3 = new d(new c(a2.i, 8), new c(a2.i, 8));
                net.minecraft.a.a.c.a.b b2 = new c(a2.i, 6);
                c2 = new c(a2.i, 2);
                object2 = new int[a2.f * a2.g];
                for (n11 = 0; n11 < a2.f; ++n11) {
                    double d4 = Math.abs(((double)n11 / ((double)a2.f - 1.0) - 0.5) * 2.0);
                    a2.a((float)n11 * 100.0f / (float)(a2.f - 1));
                    for (int i3 = 0; i3 < a2.g; ++i3) {
                        double d5;
                        double d6 = Math.abs(((double)i3 / ((double)a2.g - 1.0) - 0.5) * 2.0);
                        double d7 = ((net.minecraft.a.a.c.a.b)object3).a((float)n11 * 1.3f, (float)i3 * 1.3f) / 6.0 + -4.0;
                        double d8 = ((net.minecraft.a.a.c.a.b)d3).a((float)n11 * 1.3f, (float)i3 * 1.3f) / 5.0 + 10.0 + -4.0;
                        d2 = b2.a(n11, i3) / 8.0;
                        if (d5 > 0.0) {
                            d8 = d7;
                        }
                        double d9 = Math.max(d7, d8) / 2.0;
                        if (a2.a) {
                            double d10;
                            double d11;
                            double d12 = Math.sqrt(d4 * d4 + d6 * d6) * (double)1.2f;
                            double d13 = ((net.minecraft.a.a.c.a.b)c2).a((float)n11 * 0.05f, (float)i3 * 0.05f) / 4.0 + 1.0;
                            d12 = Math.min(d12, d13);
                            d12 = Math.max(d12, Math.max(d4, d6));
                            if (d11 > 1.0) {
                                d12 = 1.0;
                            }
                            if (d12 < 0.0) {
                                d12 = 0.0;
                            }
                            d12 *= d12;
                            d9 = d9 * (1.0 - d12) - d12 * 10.0 + 5.0;
                            if (d10 < 0.0) {
                                d9 -= d9 * d9 * (double)0.2f;
                            }
                        } else if (d9 < 0.0) {
                            d9 *= 0.8;
                        }
                        object2[n11 + i3 * a2.f] = (int)d9;
                    }
                }
                object4 = object2;
                this.e.b("Eroding..");
                this.b();
                object3 = object4;
                a2 = this;
                d3 = new d(new c(a2.i, 8), new c(a2.i, 8));
                b2 = new d(new c(a2.i, 8), new c(a2.i, 8));
                for (int i4 = 0; i4 < a2.f; ++i4) {
                    a2.a((float)i4 * 100.0f / (float)(a2.f - 1));
                    for (int i5 = 0; i5 < a2.g; ++i5) {
                        double d14 = ((net.minecraft.a.a.c.a.b)d3).a(i4 << 1, i5 << 1) / 8.0;
                        int n12 = n11 = b2.a(i4 << 1, i5 << 1) > 0.0 ? 1 : 0;
                        if (!(d14 > 2.0)) continue;
                        reference var23_41 = object3[i4 + i5 * a2.f];
                        object3[i4 + i5 * a2.f] = var23_41 = ((var23_41 - n11) / 2 << 1) + n11;
                    }
                }
            }
            this.e.b("Soiling..");
            this.b();
            object3 = object4;
            a2 = this;
            int n13 = a2.f;
            n7 = a2.g;
            int n14 = a2.h;
            object2 = new c(a2.i, 8);
            c c3 = new c(a2.i, 8);
            for (n6 = 0; n6 < n13; ++n6) {
                double d15 = Math.abs(((double)n6 / ((double)n13 - 1.0) - 0.5) * 2.0);
                a2.a((float)n6 * 100.0f / (float)(n13 - 1));
                for (n10 = 0; n10 < n7; ++n10) {
                    double d16 = Math.abs(((double)n10 / ((double)n7 - 1.0) - 0.5) * 2.0);
                    double d17 = Math.max(d15, d16);
                    d17 = d17 * d17 * d17;
                    int n15 = (int)(((net.minecraft.a.a.c.a.b)object2).a(n6, n10) / 24.0) - 4;
                    reference var30_63 = object3[n6 + n10 * n13] + a2.k;
                    object = var30_63 + n15;
                    object3[n6 + n10 * n13] = Math.max((int)var30_63, object);
                    if (object3[n6 + n10 * n13] > n14 - 2) {
                        object3[n6 + n10 * n13] = n14 - 2;
                    }
                    if (object3[n6 + n10 * n13] <= 0) {
                        object3[n6 + n10 * n13] = true;
                    }
                    d2 = ((net.minecraft.a.a.c.a.b)c3).a((double)n6 * 2.3, (double)n10 * 2.3) / 24.0;
                    int n16 = (int)(Math.sqrt(Math.abs(d2)) * Math.signum(d2) * 20.0) + a2.k;
                    if ((n16 = (int)((double)n16 * (1.0 - d17) + d17 * (double)a2.h)) > a2.k) {
                        n16 = a2.h;
                    }
                    for (int i6 = 0; i6 < n14; ++i6) {
                        int n17 = (i6 * a2.g + n10) * a2.f + n6;
                        int n18 = 0;
                        if (i6 <= var30_63) {
                            n18 = x.k.at;
                        }
                        if (i6 <= object) {
                            n18 = x.i.at;
                        }
                        if (a2.b && i6 < n16) {
                            n18 = 0;
                        }
                        if (a2.j[n17] != 0) continue;
                        a2.j[n17] = (byte)n18;
                    }
                }
            }
            this.e.b("Growing..");
            this.b();
            object3 = object4;
            a2 = this;
            n13 = a2.f;
            n7 = a2.g;
            c2 = new c(a2.i, 8);
            object2 = new c(a2.i, 8);
            n5 = a2.k - 1;
            if (a2.d == 2) {
                n5 += 2;
            }
            for (n6 = 0; n6 < n13; ++n6) {
                a2.a((float)n6 * 100.0f / (float)(n13 - 1));
                for (n11 = 0; n11 < n7; ++n11) {
                    boolean bl;
                    boolean bl2 = bl = ((net.minecraft.a.a.c.a.b)c2).a(n6, n11) > 8.0;
                    if (a2.a) {
                        boolean bl3 = bl = ((net.minecraft.a.a.c.a.b)c2).a(n6, n11) > -8.0;
                    }
                    if (a2.d == 2) {
                        bl = ((net.minecraft.a.a.c.a.b)c2).a(n6, n11) > -32.0;
                    }
                    int n19 = n10 = ((net.minecraft.a.a.c.a.b)object2).a(n6, n11) > 12.0 ? 1 : 0;
                    if (a2.d == 1 || a2.d == 3) {
                        bl = ((net.minecraft.a.a.c.a.b)c2).a(n6, n11) > -8.0;
                    }
                    Object object5 = object3[n6 + n11 * n13];
                    reference var26_52 = (object5 * a2.g + n11) * a2.f + n6;
                    int n20 = a2.j[((object5 + true) * a2.g + n11) * a2.f + n6] & 0xFF;
                    if ((n20 == x.p.at || n20 == x.q.at || n20 == 0) && object5 <= a2.k - 1 && n10 != 0) {
                        a2.j[var26_52] = (byte)x.u.at;
                    }
                    if (n20 != 0) continue;
                    int n21 = -1;
                    if (object5 <= n5 && bl) {
                        n21 = x.t.at;
                        if (a2.d == 1) {
                            n21 = x.j.at;
                        }
                    }
                    if (a2.j[var26_52] == 0 || n21 <= 0) continue;
                    a2.j[var26_52] = (byte)n21;
                }
            }
        }
        this.e.b("Carving..");
        this.b();
        a2 = this;
        n7 = a2.f;
        int n22 = a2.g;
        int n23 = a2.h;
        n5 = n7 * n22 * n23 / 256 / 64 << 1;
        for (n6 = 0; n6 < n5; ++n6) {
            a2.a((float)n6 * 100.0f / (float)(n5 - 1));
            float f2 = a2.i.nextFloat() * (float)n7;
            float f3 = a2.i.nextFloat() * (float)n23;
            float f4 = a2.i.nextFloat() * (float)n22;
            int n24 = (int)((a2.i.nextFloat() + a2.i.nextFloat()) * 200.0f);
            float f5 = a2.i.nextFloat() * (float)Math.PI * 2.0f;
            float f6 = 0.0f;
            float f7 = a2.i.nextFloat() * (float)Math.PI * 2.0f;
            float f8 = 0.0f;
            float f9 = a2.i.nextFloat() * a2.i.nextFloat();
            for (object = 0; object < n24; ++object) {
                f2 += a.a.a(f5) * a.a.b(f7);
                f4 += a.a.b(f5) * a.a.b(f7);
                f3 += a.a.a(f7);
                f5 += f6 * 0.2f;
                f6 *= 0.9f;
                f6 += a2.i.nextFloat() - a2.i.nextFloat();
                f7 += f8 * 0.5f;
                f7 *= 0.5f;
                f8 *= 0.75f;
                f8 += a2.i.nextFloat() - a2.i.nextFloat();
                if (a2.i.nextFloat() < 0.25f) continue;
                float f10 = f2 + (a2.i.nextFloat() * 4.0f - 2.0f) * 0.2f;
                float f11 = f3 + (a2.i.nextFloat() * 4.0f - 2.0f) * 0.2f;
                float f12 = f4 + (a2.i.nextFloat() * 4.0f - 2.0f) * 0.2f;
                float f13 = ((float)a2.h - f11) / (float)a2.h;
                float f14 = 1.2f + (f13 * 3.5f + 1.0f) * f9;
                float f15 = a.a.a((float)object * (float)Math.PI / (float)n24) * f14;
                for (n9 = (int)(f10 - f15); n9 <= (int)(f10 + f15); ++n9) {
                    for (int i7 = (int)(f11 - f15); i7 <= (int)(f11 + f15); ++i7) {
                        for (int i8 = (int)(f12 - f15); i8 <= (int)(f12 + f15); ++i8) {
                            int n25;
                            float f16;
                            float f17 = (float)n9 - f10;
                            float f18 = (float)i7 - f11;
                            float f19 = (float)i8 - f12;
                            f17 = f17 * f17 + f18 * f18 * 2.0f + f19 * f19;
                            if (!(f16 < f15 * f15) || n9 <= 0 || i7 <= 0 || i8 <= 0 || n9 >= a2.f - 1 || i7 >= a2.h - 1 || i8 >= a2.g - 1 || a2.j[n25 = (i7 * a2.g + i8) * a2.f + n9] != x.i.at) continue;
                            a2.j[n25] = 0;
                        }
                    }
                }
            }
        }
        n8 = this.a(x.x.at, 1000, 10, (n4 << 2) / 5);
        int n26 = this.a(x.w.at, 800, 8, n4 * 3 / 5);
        int n27 = this.a(x.v.at, 500, 6, (n4 << 1) / 5);
        n9 = this.a(x.al.at, 800, 2, n4 / 5);
        System.out.println("Coal: " + n8 + ", Iron: " + n26 + ", Gold: " + n27 + ", Diamond: " + n9);
        this.e.b("Melting..");
        this.b();
        this.c();
        g2.u = n4 + 2;
        if (this.b) {
            this.l = -128;
            this.k = this.l + 1;
            g2.u = -16;
        } else if (!this.a) {
            this.l = this.k + 1;
            this.k = this.l - 16;
        } else {
            this.l = this.k - 9;
        }
        this.e.b("Watering..");
        this.b();
        this.a();
        if (!this.b) {
            n9 = x.q.at;
            if (this.d == 1) {
                n9 = x.s.at;
            }
            for (n8 = 0; n8 < n2; ++n8) {
                this.a(n8, this.k - 1, 0, 0, n9);
                this.a(n8, this.k - 1, n3 - 1, 0, n9);
            }
            for (n8 = 0; n8 < n3; ++n8) {
                this.a(n2 - 1, this.k - 1, n8, 0, n9);
                this.a(0, this.k - 1, n8, 0, n9);
            }
        }
        if (this.d == 0) {
            g2.v = 0x99CCFF;
            g2.w = 0xFFFFFF;
            g2.x = 0xFFFFFF;
        }
        if (this.d == 1) {
            g2.x = 2164736;
            g2.w = 0x100400;
            g2.v = 0x100400;
            g2.A = 7;
            g2.B = 7;
            g2.m = x.r.at;
            if (this.b) {
                g2.u = n4 + 2;
                this.k = -16;
            }
        }
        if (this.d == 2) {
            g2.v = 13033215;
            g2.w = 13033215;
            g2.x = 0xEEEEFF;
            g2.A = 15;
            g2.B = 15;
            g2.A = 16;
            g2.u = n4 + 64;
        }
        if (this.d == 3) {
            g2.v = 7699847;
            g2.w = 5069403;
            g2.x = 5069403;
            g2.A = 12;
            g2.B = 12;
        }
        g2.s = this.k;
        g2.t = this.l;
        this.e.b("Assembling..");
        this.b();
        this.a(0.0f);
        g2.a(n2, n4, n3, this.j, null);
        this.e.b("Building..");
        this.b();
        this.a(0.0f);
        g2.b();
        net.minecraft.a.a.c.a.a(g2);
        this.e.b("Planting..");
        this.b();
        if (this.d != 1) {
            this.b(g2);
        }
        this.b();
        this.c(g2);
        if (this.d == 3) {
            for (n9 = 0; n9 < 50; ++n9) {
                this.c(g2);
            }
        }
        n9 = 100;
        if (this.d == 2) {
            n9 = 1000;
        }
        this.b();
        this.a(g2, x.S, n9);
        this.b();
        this.a(g2, x.T, n9);
        this.b();
        this.a(g2, x.U, 50);
        this.b();
        this.a(g2, x.V, 50);
        this.e.b("Lighting..");
        this.b();
        for (n8 = 0; n8 < 10000; ++n8) {
            this.a(n8 * 100 / 10000);
            g2.d();
        }
        this.e.b("Spawning..");
        this.b();
        b b3 = new b(g2);
        for (n2 = 0; n2 < 1000; ++n2) {
            this.a((float)n2 * 100.0f / 999.0f);
            b3.a();
        }
        g2.h = System.currentTimeMillis();
        g2.g = string;
        g2.f = "A Nice World";
        if (this.m != this.n) {
            throw new IllegalStateException("Wrong number of phases! Wanted " + this.n + ", got " + this.m);
        }
        return g2;
    }

    private static void a(g g2) {
        int n2 = g2.i;
        int n3 = g2.j;
        int n4 = g2.k;
        for (int i2 = n2 - 3; i2 <= n2 + 3; ++i2) {
            for (int i3 = n3 - 2; i3 <= n3 + 2; ++i3) {
                for (int i4 = n4 - 3; i4 <= n4 + 3; ++i4) {
                    int n5;
                    int n6 = n5 = i3 < n3 - 1 ? x.ae.at : 0;
                    if (i2 == n2 - 3 || i4 == n4 - 3 || i2 == n2 + 3 || i4 == n4 + 3 || i3 == n3 - 2 || i3 == n3 + 2) {
                        n5 = x.i.at;
                        if (i3 >= n3 - 1) {
                            n5 = x.m.at;
                        }
                    }
                    if (i4 == n4 - 3 && i2 == n2 && i3 >= n3 - 1 && i3 <= n3) {
                        n5 = 0;
                    }
                    g2.b(i2, i3, i4, n5);
                }
            }
        }
        g2.b(n2 - 3 + 1, n3, n4, x.af.at);
        g2.b(n2 + 3 - 1, n3, n4, x.af.at);
    }

    private void b(g g2) {
        for (int i2 = 0; i2 < this.f; ++i2) {
            this.a((float)i2 * 100.0f / (float)(this.f - 1));
            for (int i3 = 0; i3 < this.h; ++i3) {
                for (int i4 = 0; i4 < this.g; ++i4) {
                    if (g2.a(i2, i3, i4) != x.k.at || g2.d(i2, i3 + 1, i4) < 4 || g2.f(i2, i3 + 1, i4).b()) continue;
                    g2.a(i2, i3, i4, x.j.at);
                }
            }
        }
    }

    private void c(g g2) {
        int n2 = this.f * this.g * this.h / 80000;
        for (int i2 = 0; i2 < n2; ++i2) {
            if (i2 % 100 == 0) {
                this.a((float)i2 * 100.0f / (float)(n2 - 1));
            }
            int n3 = this.i.nextInt(this.f);
            int n4 = this.i.nextInt(this.h);
            int n5 = this.i.nextInt(this.g);
            for (int i3 = 0; i3 < 25; ++i3) {
                int n6 = n3;
                int n7 = n4;
                int n8 = n5;
                for (int i4 = 0; i4 < 20; ++i4) {
                    if ((n6 += this.i.nextInt(12) - this.i.nextInt(12)) < 0 || (n7 += this.i.nextInt(3) - this.i.nextInt(6)) < 0 || (n8 += this.i.nextInt(12) - this.i.nextInt(12)) < 0 || n6 >= this.f || n7 >= this.h || n8 >= this.g) continue;
                    g2.h(n6, n7, n8);
                }
            }
        }
    }

    private void a(g g2, ad ad2, int n2) {
        n2 = (int)((long)this.f * (long)this.g * (long)this.h * (long)n2 / 1600000L);
        for (int i2 = 0; i2 < n2; ++i2) {
            if (i2 % 100 == 0) {
                this.a((float)i2 * 100.0f / (float)(n2 - 1));
            }
            int n3 = this.i.nextInt(this.f);
            int n4 = this.i.nextInt(this.h);
            int n5 = this.i.nextInt(this.g);
            for (int i3 = 0; i3 < 10; ++i3) {
                int n6 = n3;
                int n7 = n4;
                int n8 = n5;
                for (int i4 = 0; i4 < 10; ++i4) {
                    if ((n6 += this.i.nextInt(4) - this.i.nextInt(4)) < 0 || (n8 += this.i.nextInt(4) - this.i.nextInt(4)) < 0 || (n7 += this.i.nextInt(2) - this.i.nextInt(2)) <= 0 || n6 >= this.f || n8 >= this.g || n7 >= this.h || g2.a(n6, n7, n8) != 0 || !ad2.e(g2, n6, n7, n8)) continue;
                    g2.b(n6, n7, n8, ad2.at);
                }
            }
        }
    }

    private int a(int n2, int n3, int n4, int n5) {
        int n6 = 0;
        n2 = (byte)n2;
        int n7 = this.f;
        int n8 = this.g;
        int n9 = this.h;
        n3 = n7 * n8 * n9 / 256 / 64 * n3 / 100;
        for (int i2 = 0; i2 < n3; ++i2) {
            this.a((float)i2 * 100.0f / (float)(n3 - 1));
            float f2 = this.i.nextFloat() * (float)n7;
            float f3 = this.i.nextFloat() * (float)n9;
            float f4 = this.i.nextFloat() * (float)n8;
            if (f3 > (float)n5) continue;
            int n10 = (int)((this.i.nextFloat() + this.i.nextFloat()) * 75.0f * (float)n4 / 100.0f);
            float f5 = this.i.nextFloat() * (float)Math.PI * 2.0f;
            float f6 = 0.0f;
            float f7 = this.i.nextFloat() * (float)Math.PI * 2.0f;
            float f8 = 0.0f;
            for (int i3 = 0; i3 < n10; ++i3) {
                f2 += a.a.a(f5) * a.a.b(f7);
                f4 += a.a.b(f5) * a.a.b(f7);
                f3 += a.a.a(f7);
                f5 += f6 * 0.2f;
                f6 *= 0.9f;
                f6 += this.i.nextFloat() - this.i.nextFloat();
                f7 += f8 * 0.5f;
                f7 *= 0.5f;
                f8 *= 0.9f;
                f8 += this.i.nextFloat() - this.i.nextFloat();
                float f9 = a.a.a((float)i3 * (float)Math.PI / (float)n10) * (float)n4 / 100.0f + 1.0f;
                for (int i4 = (int)(f2 - f9); i4 <= (int)(f2 + f9); ++i4) {
                    for (int i5 = (int)(f3 - f9); i5 <= (int)(f3 + f9); ++i5) {
                        for (int i6 = (int)(f4 - f9); i6 <= (int)(f4 + f9); ++i6) {
                            int n11;
                            float f10;
                            float f11 = (float)i4 - f2;
                            float f12 = (float)i5 - f3;
                            float f13 = (float)i6 - f4;
                            f11 = f11 * f11 + f12 * f12 * 2.0f + f13 * f13;
                            if (!(f10 < f9 * f9) || i4 <= 0 || i5 <= 0 || i6 <= 0 || i4 >= this.f - 1 || i5 >= this.h - 1 || i6 >= this.g - 1 || this.j[n11 = (i5 * this.g + i6) * this.f + i4] != x.i.at) continue;
                            this.j[n11] = n2;
                            ++n6;
                        }
                    }
                }
            }
        }
        return n6;
    }

    private void a() {
        int n2 = x.q.at;
        if (this.d == 1) {
            n2 = x.s.at;
        }
        int n3 = this.f * this.g * this.h / 1000;
        for (int i2 = 0; i2 < n3; ++i2) {
            int n4;
            if (i2 % 100 == 0) {
                this.a((float)i2 * 100.0f / (float)(n3 - 1));
            }
            int n5 = this.i.nextInt(this.f);
            int n6 = this.i.nextInt(this.h);
            if (this.j[(n6 * this.g + (n4 = this.i.nextInt(this.g))) * this.f + n5] != 0) continue;
            long l2 = this.a(n5, n6, n4, 0, 255);
            if (l2 > 0L && l2 < 640L) {
                this.a(n5, n6, n4, 255, n2);
                continue;
            }
            this.a(n5, n6, n4, 255, 0);
        }
        this.a(100.0f);
    }

    private void b() {
        ++this.m;
        this.o = 0.0f;
        this.a(0.0f);
    }

    private void a(float f2) {
        if (f2 < 0.0f) {
            throw new IllegalStateException("Failed to set next phase!");
        }
        int n2 = (int)(((float)(this.m - 1) + f2 / 100.0f) * 100.0f / (float)this.n);
        this.e.a(n2);
    }

    private void c() {
        int n2 = this.f * this.g * this.h / 2000;
        int n3 = this.l;
        for (int i2 = 0; i2 < n2; ++i2) {
            int n4;
            if (i2 % 100 == 0) {
                this.a((float)i2 * 100.0f / (float)(n2 - 1));
            }
            int n5 = this.i.nextInt(this.f);
            int n6 = Math.min(Math.min(this.i.nextInt(n3), this.i.nextInt(n3)), Math.min(this.i.nextInt(n3), this.i.nextInt(n3)));
            if (this.j[(n6 * this.g + (n4 = this.i.nextInt(this.g))) * this.f + n5] != 0) continue;
            long l2 = this.a(n5, n6, n4, 0, 255);
            if (l2 > 0L && l2 < 640L) {
                this.a(n5, n6, n4, 255, x.s.at);
                continue;
            }
            this.a(n5, n6, n4, 255, 0);
        }
        this.a(100.0f);
    }

    private long a(int n2, int n3, int n4, int n5, int n6) {
        byte by = (byte)n6;
        n5 = (byte)n5;
        ArrayList<int[]> arrayList = new ArrayList<int[]>();
        int n7 = 0;
        int n8 = 1;
        int n9 = 1;
        while (1 << n8 < this.f) {
            ++n8;
        }
        while (1 << n9 < this.g) {
            ++n9;
        }
        int n10 = this.g - 1;
        int n11 = this.f - 1;
        ++n7;
        this.p[0] = ((n3 << n9) + n4 << n8) + n2;
        long l2 = 0L;
        n2 = this.f * this.g;
        while (n7 > 0) {
            int n12;
            n3 = this.p[--n7];
            if (n7 == 0 && arrayList.size() > 0) {
                this.p = (int[])arrayList.remove(arrayList.size() - 1);
                n7 = this.p.length;
            }
            n4 = n3 >> n8 & n10;
            int n13 = n3 >> n8 + n9;
            int n14 = n12 = n3 & n11;
            while (n12 > 0 && this.j[n3 - 1] == n5) {
                --n12;
                --n3;
            }
            while (n14 < this.f && this.j[n3 + n14 - n12] == n5) {
                ++n14;
            }
            int n15 = n3 >> n8 & n10;
            int n16 = n3 >> n8 + n9;
            if (n6 == 255 && (n12 == 0 || n14 == this.f - 1 || n13 == 0 || n13 == this.h - 1 || n4 == 0 || n4 == this.g - 1)) {
                return -1L;
            }
            if (n15 != n4 || n16 != n13) {
                System.out.println("Diagonal flood!?");
            }
            n15 = 0;
            n16 = 0;
            int n17 = 0;
            l2 += (long)(n14 - n12);
            while (n12 < n14) {
                int n18;
                this.j[n3] = by;
                if (n4 > 0) {
                    n18 = this.j[n3 - this.f] == n5 ? 1 : 0;
                    if (n18 != 0 && n15 == 0) {
                        if (n7 == this.p.length) {
                            arrayList.add(this.p);
                            this.p = new int[0x100000];
                            n7 = 0;
                        }
                        this.p[n7++] = n3 - this.f;
                    }
                    n15 = n18;
                }
                if (n4 < this.g - 1) {
                    n18 = this.j[n3 + this.f] == n5 ? 1 : 0;
                    if (n18 != 0 && n16 == 0) {
                        if (n7 == this.p.length) {
                            arrayList.add(this.p);
                            this.p = new int[0x100000];
                            n7 = 0;
                        }
                        this.p[n7++] = n3 + this.f;
                    }
                    n16 = n18;
                }
                if (n13 > 0) {
                    n18 = this.j[n3 - n2];
                    if (!(by != x.r.at && by != x.s.at || n18 != x.p.at && n18 != x.q.at)) {
                        this.j[n3 - n2] = (byte)x.i.at;
                    }
                    if ((n18 = n18 == n5 ? 1 : 0) != 0 && n17 == 0) {
                        if (n7 == this.p.length) {
                            arrayList.add(this.p);
                            this.p = new int[0x100000];
                            n7 = 0;
                        }
                        this.p[n7++] = n3 - n2;
                    }
                    n17 = n18;
                }
                ++n3;
                ++n12;
            }
        }
        return l2;
    }
}

