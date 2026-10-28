/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.a;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.a.a.b.x;
import net.minecraft.a.a.c;
import net.minecraft.a.a.d;
import net.minecraft.a.a.g;

public final class h {
    private int a = 0;
    private List b = new ArrayList();
    private g c;
    private int d;
    private int e;
    private int f;
    private byte[] g;
    private byte[] h;
    private int[] i;
    private List j = new ArrayList();
    private List k = new ArrayList();
    private List l = new ArrayList();
    private List m = new ArrayList();
    private int[] n = this.c();
    private byte[] o;
    private c p = null;
    private int q = 0;
    private int r;

    public h(g g2) {
        this.c = g2;
        this.d = g2.a;
        this.e = g2.b;
        this.f = g2.c;
        this.o = new byte[g2.d.length / 8];
        this.g = g2.d;
        this.h = g2.e;
        this.i = g2.p;
    }

    private int[] c() {
        if (this.m.size() > 0) {
            return (int[])this.m.remove(this.m.size() - 1);
        }
        return new int[32768];
    }

    public final void a(int n2, int n3, int n4, int n5) {
        this.l.add(new c(this, n2, n3, 0, n4, n5, 1));
    }

    public final void a(int n2) {
        if (n2 > 15) {
            n2 = 15;
        }
        if (n2 < 0) {
            n2 = 0;
        }
        this.r = n2 - this.c.B;
        if (this.r == 0) {
            return;
        }
        this.q = this.c.B;
        this.c.B = n2;
        while (this.p != null) {
            this.b(64);
        }
        this.p = new c(this, 0, 0, 0, this.c.a, this.c.c, this.c.b);
    }

    private void b(int n2) {
        int n3;
        int n4;
        int n5 = this.p.a;
        int n6 = this.p.d;
        int n7 = this.p.c;
        int n8 = this.p.f;
        while (n5 < n6) {
            if (n2-- <= 0 && n5 != n6 - 1) {
                this.p.a = n5;
                return;
            }
            for (n4 = n7; n4 < n8; ++n4) {
                for (n3 = this.i[n5 + n4 * this.d] - 1; n3 > 0 && x.f[this.g[(n3 * this.e + n4) * this.d + n5]] < 100; --n3) {
                }
                ++n3;
                while (n3 < this.f) {
                    int n9;
                    int n10 = (n3 * this.e + n4) * this.d + n5;
                    if (x.h[this.g[n10]] == 0 && (n9 = this.h[n10] & 0xF) <= this.q) {
                        if (this.r < 0 && n9 > 0) {
                            int n11 = n10;
                            this.h[n11] = (byte)(this.h[n11] - 1);
                        } else if (this.r > 0 && n9 < 15) {
                            int n12 = n10;
                            this.h[n12] = (byte)(this.h[n12] + 1);
                        }
                    }
                    ++n3;
                }
            }
            ++n5;
        }
        for (n4 = 0; n4 < this.d; n4 += 32) {
            for (n3 = 0; n3 < this.e; n3 += 32) {
                this.k.add(new c(this, n4, 0, n3, n4 + 32, this.f, n3 + 32));
                this.j.add(new c(this, n4, 0, n3, n4 + 32, this.f, n3 + 32));
            }
        }
        for (n4 = 0; n4 < this.c.n.size(); ++n4) {
            d d2 = (d)this.c.n.get(n4);
            d2.h();
        }
        this.p = null;
    }

    public final void a(int n2, int n3, int n4, int n5, int n6, int n7) {
        this.k.add(new c(this, n2, n3, n4, n5, n6, n7));
    }

    private void b(int n2, int n3, int n4, int n5, int n6, int n7) {
        while (n3 < n6) {
            for (int i2 = n4; i2 < n7; ++i2) {
                for (int i3 = n2; i3 < n5; ++i3) {
                    int n8 = i3 + n3 * this.d + i2 * this.d * this.f;
                    if ((this.o[n8 >> 3] & 1 << (n8 & 7)) != 0) continue;
                    int n9 = n8 >> 3;
                    this.o[n9] = (byte)(this.o[n9] | 1 << (n8 & 7));
                    this.n[this.a++] = n8;
                    if ((this.o[n8 >> 3] & 1 << (n8 & 7)) == 0) {
                        System.out.println("OMG ERROR!");
                    }
                    if (this.a <= this.n.length - 32) continue;
                    n8 = this.n[--this.a];
                    this.n[this.n.length - 1] = this.a;
                    this.b.add(this.n);
                    this.n = this.c();
                    this.a = 1;
                    this.n[0] = n8;
                }
            }
            ++n3;
        }
    }

    public final void a() {
        int n2;
        if (this.m.size() > 0) {
            this.m.remove(this.m.size() - 1);
        }
        int n3 = 5;
        while (this.j.size() > 0 && n3-- > 0) {
            c c2 = (c)this.j.remove(0);
            for (n2 = 0; n2 < this.c.n.size(); ++n2) {
                ((d)this.c.n.get(n2)).a(c2.a, c2.b, c2.c, c2.d, c2.e, c2.f);
            }
        }
        if (this.p != null) {
            this.b(8);
            return;
        }
        for (int i2 = 0; i2 < 16; ++i2) {
            int n4;
            int n5;
            int n6;
            int n7;
            int n8;
            int n9;
            int n10;
            int n11;
            int n12;
            int n13;
            h h2 = this;
            n2 = 0;
            if (h2.k.size() > 0) {
                n2 = 1;
                c c3 = (c)h2.k.remove(0);
                h2.b(c3.a, c3.b, c3.c, c3.d, c3.e, c3.f);
            }
            if (h2.l.size() > 0) {
                boolean bl = true;
                Object object = (c)h2.l.remove(0);
                n13 = ((c)object).e;
                n12 = ((c)object).d;
                n11 = ((c)object).b;
                n10 = ((c)object).a;
                object = h2;
                for (n9 = n10; n9 < n10 + n12; ++n9) {
                    for (n8 = n11; n8 < n11 + n13; ++n8) {
                        n7 = ((h)object).i[n9 + n8 * ((h)object).d];
                        for (n6 = ((h)object).f - 1; n6 > 0 && x.f[((h)object).g[(n6 * ((h)object).e + n8) * ((h)object).d + n9]] == 0; --n6) {
                        }
                        ((h)object).i[n9 + n8 * ((h)object).d] = n6 + 1;
                        if (n7 == n6) continue;
                        n5 = n7 < n6 ? n7 : n6;
                        n4 = n7 > n6 ? n7 : n6;
                        super.b(n9, n5, n8, n9 + 1, n4, n8 + 1);
                    }
                }
            }
            h h3 = h2;
            n10 = h3.c.B;
            n11 = -999;
            n12 = -999;
            n13 = -999;
            n9 = -999;
            n8 = -999;
            n7 = -999;
            n6 = 1024;
            n5 = 0;
            while (n6-- > 0 && (h3.a > 0 || h3.b.size() > 0)) {
                ++n5;
                if (h3.a == 0) {
                    if (h3.n != null) {
                        int[] nArray = h3.n;
                        h h4 = h3;
                        h4.m.add(nArray);
                    }
                    h3.n = (int[])h3.b.remove(h3.b.size() - 1);
                    h3.a = h3.n[h3.n.length - 1];
                }
                if (h3.a > h3.n.length - 32) {
                    n4 = h3.n[--h3.a];
                    h3.n[h3.n.length - 1] = h3.a;
                    h3.b.add(h3.n);
                    h3.n = h3.c();
                    h3.a = 1;
                    h3.n[0] = n4;
                    continue;
                }
                n4 = h3.n[--h3.a];
                int n14 = n4 % h3.d;
                int n15 = n4 / h3.d % h3.f;
                int n16 = n4 / h3.d / h3.f % h3.e;
                int n17 = n4 >> 3;
                h3.o[n17] = (byte)(h3.o[n17] ^ 1 << (n4 & 7));
                n4 = h3.i[n14 + n16 * h3.d];
                int n18 = n15 >= n4 ? n10 : 0;
                n4 = h3.g[(n15 * h3.e + n16) * h3.d + n14];
                int n19 = x.f[n4];
                if (n19 > 100) {
                    n18 = 0;
                } else if (n18 < 15) {
                    int n20;
                    if (n19 == 0) {
                        n19 = 1;
                    }
                    if (n14 > 0 && (n20 = (h3.h[(n15 * h3.e + n16) * h3.d + (n14 - 1)] & 0xF) - n19) > n18) {
                        n18 = n20;
                    }
                    if (n14 < h3.d - 1 && (n20 = (h3.h[(n15 * h3.e + n16) * h3.d + (n14 + 1)] & 0xF) - n19) > n18) {
                        n18 = n20;
                    }
                    if (n15 > 0 && (n20 = (h3.h[((n15 - 1) * h3.e + n16) * h3.d + n14] & 0xF) - n19) > n18) {
                        n18 = n20;
                    }
                    if (n15 < h3.f - 1 && (n20 = (h3.h[((n15 + 1) * h3.e + n16) * h3.d + n14] & 0xF) - n19) > n18) {
                        n18 = n20;
                    }
                    if (n16 > 0 && (n20 = (h3.h[(n15 * h3.e + (n16 - 1)) * h3.d + n14] & 0xF) - n19) > n18) {
                        n18 = n20;
                    }
                    if (n16 < h3.e - 1 && (n20 = (h3.h[(n15 * h3.e + (n16 + 1)) * h3.d + n14] & 0xF) - n19) > n18) {
                        n18 = n20;
                    }
                }
                if (n18 < x.h[n4]) {
                    n18 = x.h[n4];
                }
                if ((n19 = h3.h[(n15 * h3.e + n16) * h3.d + n14] & 0xF) == n18) continue;
                h3.h[(n15 * h3.e + n16) * h3.d + n14] = (byte)((h3.h[(n15 * h3.e + n16) * h3.d + n14] & 0xF0) + n18);
                if (n14 > 0 && (h3.h[(n15 * h3.e + n16) * h3.d + (n14 - 1)] & 0xF) != n18 - 1 && (h3.o[(n4 = n14 - 1 + n15 * h3.d + n16 * h3.d * h3.f) >> 3] & 1 << (n4 & 7)) == 0) {
                    int n21 = n4 >> 3;
                    h3.o[n21] = (byte)(h3.o[n21] | 1 << (n4 & 7));
                    h3.n[h3.a++] = n4;
                }
                if (n14 < h3.d - 1 && (h3.h[(n15 * h3.e + n16) * h3.d + (n14 + 1)] & 0xF) != n18 - 1 && (h3.o[(n4 = n14 + 1 + n15 * h3.d + n16 * h3.d * h3.f) >> 3] & 1 << (n4 & 7)) == 0) {
                    int n22 = n4 >> 3;
                    h3.o[n22] = (byte)(h3.o[n22] | 1 << (n4 & 7));
                    h3.n[h3.a++] = n4;
                }
                if (n15 > 0 && (h3.h[((n15 - 1) * h3.e + n16) * h3.d + n14] & 0xF) != n18 - 1 && (h3.o[(n4 = n14 + (n15 - 1) * h3.d + n16 * h3.d * h3.f) >> 3] & 1 << (n4 & 7)) == 0) {
                    int n23 = n4 >> 3;
                    h3.o[n23] = (byte)(h3.o[n23] | 1 << (n4 & 7));
                    h3.n[h3.a++] = n4;
                }
                if (n15 < h3.f - 1 && (h3.h[((n15 + 1) * h3.e + n16) * h3.d + n14] & 0xF) != n18 - 1 && (h3.o[(n4 = n14 + (n15 + 1) * h3.d + n16 * h3.d * h3.f) >> 3] & 1 << (n4 & 7)) == 0) {
                    int n24 = n4 >> 3;
                    h3.o[n24] = (byte)(h3.o[n24] | 1 << (n4 & 7));
                    h3.n[h3.a++] = n4;
                }
                if (n16 > 0 && (h3.h[(n15 * h3.e + (n16 - 1)) * h3.d + n14] & 0xF) != n18 - 1 && (h3.o[(n4 = n14 + n15 * h3.d + (n16 - 1) * h3.d * h3.f) >> 3] & 1 << (n4 & 7)) == 0) {
                    int n25 = n4 >> 3;
                    h3.o[n25] = (byte)(h3.o[n25] | 1 << (n4 & 7));
                    h3.n[h3.a++] = n4;
                }
                if (n16 < h3.e - 1 && (h3.h[(n15 * h3.e + (n16 + 1)) * h3.d + n14] & 0xF) != n18 - 1 && (h3.o[(n4 = n14 + n15 * h3.d + (n16 + 1) * h3.d * h3.f) >> 3] & 1 << (n4 & 7)) == 0) {
                    int n26 = n4 >> 3;
                    h3.o[n26] = (byte)(h3.o[n26] | 1 << (n4 & 7));
                    h3.n[h3.a++] = n4;
                }
                if (n11 == -999) {
                    n11 = n14;
                    n12 = n14;
                    n13 = n15;
                    n9 = n15;
                    n8 = n16;
                    n7 = n16;
                }
                if (n14 < n11) {
                    n11 = n14;
                } else if (n14 > n12) {
                    n12 = n14;
                }
                if (n15 > n9) {
                    n9 = n15;
                } else if (n15 < n13) {
                    n13 = n15;
                }
                if (n16 < n8) {
                    n8 = n16;
                    continue;
                }
                if (n16 <= n7) continue;
                n7 = n16;
            }
            if (n11 > -999) {
                h3.j.add(new c(h3, n11, n13, n8, n12, n9, n7));
            }
            if ((n2 = n5) <= 0) continue;
            n2 = 1;
        }
    }

    public final String b() {
        return "" + (this.k.size() + this.j.size());
    }
}

