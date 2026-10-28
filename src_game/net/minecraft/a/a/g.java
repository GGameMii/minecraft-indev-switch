/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.a;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeSet;
import net.minecraft.a.a.a.c;
import net.minecraft.a.a.b.a.a;
import net.minecraft.a.a.b.q;
import net.minecraft.a.a.b.x;
import net.minecraft.a.a.d;
import net.minecraft.a.a.e;
import net.minecraft.a.a.h;
import net.minecraft.a.a.i;
import net.minecraft.a.c.b;

public final class g {
    private static float[] F = new float[16];
    public int a;
    public int b;
    public int c;
    public byte[] d;
    public byte[] e;
    public String f;
    public String g;
    public long h;
    public int i;
    public int j;
    public int k;
    public float l;
    public int m;
    List n;
    private List G;
    public Map o;
    private List H;
    int[] p;
    public Random q;
    private Random I;
    private int J;
    public i r;
    public int s;
    public int t;
    public int u;
    public int v;
    public int w;
    public int x;
    private int K;
    private int L;
    public net.minecraft.a.c.e y;
    public boolean z;
    public int A;
    public int B;
    public c C;
    private h M;
    public int D;
    private static short N;
    private short[] O;
    private int[] P;
    private int[] Q;
    public int E;

    public g() {
        this.m = net.minecraft.a.a.b.x.p.at;
        this.n = new ArrayList();
        this.G = new LinkedList();
        this.o = new HashMap();
        this.H = new ArrayList();
        this.q = new Random();
        this.I = new Random();
        this.J = this.q.nextInt();
        this.K = 0;
        this.L = 0;
        this.z = true;
        this.A = 15;
        this.B = 15;
        this.C = new c(this);
        this.D = 0;
        this.O = new short[0x100000];
        this.P = new int[0x100000];
        this.Q = new int[0x100000];
        this.E = 2;
        this.v = 0x99CCFF;
        this.w = 0xFFFFFF;
        this.x = 0xFFFFFF;
    }

    public final void a() {
        if (this.d == null) {
            throw new RuntimeException("The level is corrupt!");
        }
        this.n = new ArrayList();
        this.p = new int[this.a * this.b];
        Arrays.fill(this.p, this.c);
        this.M.a(0, 0, this.a, this.b);
        this.q = new Random();
        this.J = this.q.nextInt();
        this.G = new ArrayList();
        if (this.r == null) {
            this.r = new i(this.a, this.c, this.b);
        }
    }

    /*
     * Unable to fully structure code
     */
    public final void a(int var1_1, int var2_3, int var3_4, byte[] var4_5, byte[] var5_7) {
        if (var5_7 != null && var5_7.length == 0) {
            var5_7 = null;
        }
        this.a = var1_1;
        this.b = var3_4;
        this.c = var2_3;
        this.d = var4_5;
        for (var2_3 = 0; var2_3 < this.a; ++var2_3) {
            for (var6_9 = 0; var6_9 < this.b; ++var6_9) {
                for (var7_10 = 0; var7_10 < this.c; ++var7_10) {
                    var8_11 = 0;
                    if (var7_10 > 1)
                    var9_12 = this;
                    if (var7_10 < var9_12.t - 1 && var4_5[((var7_10 + 1) * this.b + var6_9) * this.a + var2_3] == 0) {
                        var8_11 = net.minecraft.a.a.b.x.s.at;
                    // 2 sources

                    {
                        var9_12 = this;
                        if (var7_10 < var9_12.t - 1) {
                            var8_11 = net.minecraft.a.a.b.x.o.at;
                        } else {
                            var9_12 = this;
                            if (var7_10 < var9_12.t) {
                                v0 = this;
                                var9_12 = v0;
                                var9_12 = this;
                                var8_11 = v0.t > var9_12.s && this.m == net.minecraft.a.a.b.x.p.at ? net.minecraft.a.a.b.x.j.at : net.minecraft.a.a.b.x.k.at;
                            } else {
                                var9_12 = this;
                                if (var7_10 < var9_12.s) {
                                    var8_11 = this.m;
                                }
                            }
                        }
                    }
                    var4_5[(var7_10 * this.b + var6_9) * this.a + var2_3] = (byte)var8_11;
                    if (var7_10 != 1 || var2_3 == 0 || var6_9 == 0 || var2_3 == this.a - 1 || var6_9 == this.b - 1) continue;
                    var7_10 = this.c - 2;
                }
            }
        }
        this.p = new int[var1_1 * var3_4];
        Arrays.fill(this.p, this.c);
        if (var5_7 == null) {
            this.e = new byte[var4_5.length];
            this.M = new h(this);
            var1_1 = 1;
            var1_2 = this;
            var2_3 = var1_2.B;
            for (var3_4 = 0; var3_4 < var1_2.a; ++var3_4) {
                for (var4_6 = 0; var4_6 < var1_2.b; ++var4_6) {
                    for (var5_8 = var1_2.c - 1; var5_8 > 0 && net.minecraft.a.a.b.x.f[var1_2.a(var3_4, var5_8, var4_6)] == 0; --var5_8) {
                    }
                    var1_2.p[var3_4 + var4_6 * var1_2.a] = var5_8 + 1;
                    for (var5_8 = 0; var5_8 < var1_2.c; ++var5_8) {
                        var7_10 = var1_2.p[var3_4 + var4_6 * var1_2.a];
                        var7_10 = var5_8 >= var7_10 ? var2_3 : 0;
                        if (var7_10 < net.minecraft.a.a.b.x.h[var8_11 = var1_2.d[var6_9 = (var5_8 * var1_2.b + var4_6) * var1_2.a + var3_4]]) {
                            var7_10 = net.minecraft.a.a.b.x.h[var8_11];
                        }
                        var1_2.e[var6_9] = (byte)((var1_2.e[var6_9] & 240) + var7_10);
                    }
                }
            }
            var1_2.M.a(0, 0, 0, var1_2.a, var1_2.c, var1_2.b);
        } else {
            this.e = var5_7;
            this.M = new h(this);
        }
        for (var2_3 = 0; var2_3 < this.n.size(); ++var2_3) {
            ((d)this.n.get(var2_3)).a();
        }
        this.G.clear();
        this.b();
        this.a();
        System.gc();
    }

    public final void b() {
        int n2;
        int n3;
        int n4;
        Random random = new Random();
        int n5 = 0;
        block0: while (true) {
            int n6;
            int n7;
            int n8;
            n4 = random.nextInt(this.a / 2) + this.a / 4;
            n3 = random.nextInt(this.b / 2) + this.b / 4;
            n2 = this.a(n4, n3) + 1;
            if (++n5 == 1000000) {
                this.i = n4;
                this.j = this.c + 100;
                this.k = n3;
                this.l = 180.0f;
                return;
            }
            if (n2 < 4) continue;
            g g2 = this;
            if (n2 <= g2.s) continue;
            for (n8 = n4 - 3; n8 <= n4 + 3; ++n8) {
                for (n7 = n2 - 1; n7 <= n2 + 2; ++n7) {
                    for (n6 = n3 - 3 - 2; n6 <= n3 + 3; ++n6) {
                        if (this.f(n8, n7, n6).a()) continue block0;
                    }
                }
            }
            n8 = n2 - 2;
            for (n7 = n4 - 3; n7 <= n4 + 3; ++n7) {
                for (n6 = n3 - 3 - 2; n6 <= n3 + 3; ++n6) {
                    if (net.minecraft.a.a.b.x.e[this.a(n7, n8, n6)]) continue;
                    continue block0;
                }
            }
            break;
        }
        this.i = n4;
        this.j = n2;
        this.k = n3;
        this.l = 180.0f;
    }

    public final void a(d d2) {
        for (int i2 = 0; i2 < this.r.e.size(); ++i2) {
            d2.a((b)this.r.e.get(i2));
        }
        this.n.add(d2);
    }

    public final void finalize() {
    }

    public final void b(d d2) {
        this.n.remove(d2);
    }

    public final ArrayList a(net.minecraft.a.d.b b2) {
        ArrayList<Object> arrayList = new ArrayList<Object>();
        int n2 = (int)b2.a;
        int n3 = (int)b2.d + 1;
        int n4 = (int)b2.b;
        int n5 = (int)b2.e + 1;
        int n6 = (int)b2.c;
        int n7 = (int)b2.f + 1;
        if (b2.a < 0.0f) {
            --n2;
        }
        if (b2.b < 0.0f) {
            --n4;
        }
        if (b2.c < 0.0f) {
            --n6;
        }
        while (n2 < n3) {
            for (int i2 = n4; i2 < n5; ++i2) {
                for (int i3 = n6; i3 < n7; ++i3) {
                    Object object = net.minecraft.a.a.b.x.c[this.a(n2, i2, i3)];
                    if (object != null) {
                        if ((object = ((x)object).a(n2, i2, i3)) == null || !b2.a((net.minecraft.a.d.b)object)) continue;
                        arrayList.add(object);
                        continue;
                    }
                    if (this.t >= 0 || i2 >= this.t && i2 >= this.s || (object = net.minecraft.a.a.b.x.o.a(n2, i2, i3)) == null || !b2.a((net.minecraft.a.d.b)object)) continue;
                    arrayList.add(object);
                }
            }
            ++n2;
        }
        return arrayList;
    }

    public final void a(int n2, int n3, int n4, int n5, int n6, int n7) {
        int n8 = this.a(n2, n3, n4);
        int n9 = this.a(n5, n6, n7);
        this.a(n2, n3, n4, n9);
        this.a(n5, n6, n7, n8);
        this.c(n2, n3, n4, n9);
        this.c(n5, n6, n7, n8);
    }

    public final boolean a(int n2, int n3, int n4, int n5) {
        if (n2 <= 0 || n3 <= 0 || n4 <= 0 || n2 >= this.a - 1 || n3 >= this.c - 1 || n4 >= this.b - 1) {
            return false;
        }
        if (n5 == this.d[(n3 * this.b + n4) * this.a + n2]) {
            return false;
        }
        if (n5 == 0 && (n2 == 0 || n4 == 0 || n2 == this.a - 1 || n4 == this.b - 1)) {
            g g2 = this;
            if (n3 >= g2.t) {
                g2 = this;
                if (n3 < g2.s) {
                    n5 = net.minecraft.a.a.b.x.p.at;
                }
            }
        }
        byte by = this.d[(n3 * this.b + n4) * this.a + n2];
        this.d[(n3 * this.b + n4) * this.a + n2] = (byte)n5;
        this.f(n2, n3, n4, 0);
        if (by != 0) {
            net.minecraft.a.a.b.x.c[by].b(this, n2, n3, n4);
        }
        if (n5 != 0) {
            net.minecraft.a.a.b.x.c[n5].d(this, n2, n3, n4);
        }
        if (net.minecraft.a.a.b.x.f[by] != net.minecraft.a.a.b.x.f[n5] || net.minecraft.a.a.b.x.h[by] != 0 || net.minecraft.a.a.b.x.h[n5] != 0) {
            this.M.a(n2, n4, 1, 1);
            this.M.a(n2, n3, n4, n2 + 1, n3 + 1, n4 + 1);
        }
        for (n5 = 0; n5 < this.n.size(); ++n5) {
            ((d)this.n.get(n5)).a(n2, n3, n4);
        }
        return true;
    }

    public final boolean b(int n2, int n3, int n4, int n5) {
        if (this.a(n2, n3, n4, n5)) {
            this.c(n2, n3, n4, n5);
            return true;
        }
        return false;
    }

    public final void c(int n2, int n3, int n4, int n5) {
        this.h(n2 - 1, n3, n4, n5);
        this.h(n2 + 1, n3, n4, n5);
        this.h(n2, n3 - 1, n4, n5);
        this.h(n2, n3 + 1, n4, n5);
        this.h(n2, n3, n4 - 1, n5);
        this.h(n2, n3, n4 + 1, n5);
    }

    public final boolean d(int n2, int n3, int n4, int n5) {
        if (n2 < 0 || n3 < 0 || n4 < 0 || n2 >= this.a || n3 >= this.c || n4 >= this.b) {
            return false;
        }
        if (n5 == this.d[(n3 * this.b + n4) * this.a + n2]) {
            return false;
        }
        this.d[(n3 * this.b + n4) * this.a + n2] = (byte)n5;
        this.M.a(n2, n3, n4, n2 + 1, n3 + 1, n4 + 1);
        return true;
    }

    private void h(int n2, int n3, int n4, int n5) {
        if (n2 < 0 || n3 < 0 || n4 < 0 || n2 >= this.a || n3 >= this.c || n4 >= this.b) {
            return;
        }
        x x2 = net.minecraft.a.a.b.x.c[this.d[(n3 * this.b + n4) * this.a + n2]];
        if (x2 != null) {
            x2.b(this, n2, n3, n4, n5);
        }
    }

    public final int a(int n2, int n3, int n4) {
        if (n2 < 0) {
            n2 = 0;
        } else if (n2 >= this.a) {
            n2 = this.a - 1;
        }
        if (n3 < 0) {
            n3 = 0;
        } else if (n3 >= this.c) {
            n3 = this.c - 1;
        }
        if (n4 < 0) {
            n4 = 0;
        } else if (n4 >= this.b) {
            n4 = this.b - 1;
        }
        return this.d[(n3 * this.b + n4) * this.a + n2] & 0xFF;
    }

    public final boolean b(int n2, int n3, int n4) {
        x x2 = net.minecraft.a.a.b.x.c[this.a(n2, n3, n4)];
        if (x2 == null) {
            return false;
        }
        return x2.b();
    }

    public final void c() {
        this.r.a();
        for (int i2 = 0; i2 < this.H.size(); ++i2) {
            a a2 = (a)this.H.get(i2);
            a2.d();
        }
    }

    public final void d() {
        this.M.a();
    }

    public final float a(float f2) {
        float f3;
        f2 = this.c(f2);
        f2 = 1.0f - (a.a.b(f2 * (float)Math.PI * 2.0f) * 2.0f + 0.75f);
        if (f3 < 0.0f) {
            f2 = 0.0f;
        }
        if (f2 > 1.0f) {
            f2 = 1.0f;
        }
        return f2 * f2 * 0.5f;
    }

    public final net.minecraft.a.d.a b(float f2) {
        float f3;
        f2 = this.c(f2);
        f2 = a.a.b(f2 * (float)Math.PI * 2.0f) * 2.0f + 0.5f;
        if (f3 < 0.0f) {
            f2 = 0.0f;
        }
        if (f2 > 1.0f) {
            f2 = 1.0f;
        }
        float f4 = (float)(this.v >> 16 & 0xFF) / 255.0f;
        float f5 = (float)(this.v >> 8 & 0xFF) / 255.0f;
        float f6 = (float)(this.v & 0xFF) / 255.0f;
        return new net.minecraft.a.d.a(f4 *= f2, f5 *= f2, f6 *= f2);
    }

    public final float c(float f2) {
        if (this.A > 15) {
            return 0.0f;
        }
        f2 = ((float)this.D + f2) / 24000.0f - 0.15f;
        return f2;
    }

    public final net.minecraft.a.d.a d(float f2) {
        float f3;
        f2 = this.c(f2);
        f2 = a.a.b(f2 * (float)Math.PI * 2.0f) * 2.0f + 0.5f;
        if (f3 < 0.0f) {
            f2 = 0.0f;
        }
        if (f2 > 1.0f) {
            f2 = 1.0f;
        }
        float f4 = (float)(this.w >> 16 & 0xFF) / 255.0f;
        float f5 = (float)(this.w >> 8 & 0xFF) / 255.0f;
        float f6 = (float)(this.w & 0xFF) / 255.0f;
        return new net.minecraft.a.d.a(f4 *= f2 * 0.94f + 0.06f, f5 *= f2 * 0.94f + 0.06f, f6 *= f2 * 0.91f + 0.09f);
    }

    public final net.minecraft.a.d.a e(float f2) {
        float f3;
        f2 = this.c(f2);
        f2 = a.a.b(f2 * (float)Math.PI * 2.0f) * 2.0f + 0.5f;
        if (f3 < 0.0f) {
            f2 = 0.0f;
        }
        if (f2 > 1.0f) {
            f2 = 1.0f;
        }
        float f4 = (float)(this.x >> 16 & 0xFF) / 255.0f;
        float f5 = (float)(this.x >> 8 & 0xFF) / 255.0f;
        float f6 = (float)(this.x & 0xFF) / 255.0f;
        return new net.minecraft.a.d.a(f4 *= f2 * 0.9f + 0.1f, f5 *= f2 * 0.9f + 0.1f, f6 *= f2 * 0.85f + 0.15f);
    }

    public final int e() {
        int n2;
        float f2;
        float f3 = this.c(1.0f);
        f3 = a.a.b(f3 * (float)Math.PI * 2.0f) * 1.5f + 0.5f;
        if (f2 < 0.0f) {
            f3 = 0.0f;
        }
        if (f3 > 1.0f) {
            f3 = 1.0f;
        }
        if ((n2 = (int)(f3 * ((float)(15 * this.A) / 15.0f - 4.0f) + 4.0f)) > 15) {
            n2 = 15;
        }
        if (n2 < 4) {
            n2 = 4;
        }
        return n2;
    }

    public final void f() {
        int n2;
        int n3;
        int n4;
        int n5;
        ++this.D;
        if (this.D == 24000) {
            this.D = 0;
        }
        if (this.B > (n5 = this.e())) {
            this.a(this.B - 1);
        }
        if (this.B < n5) {
            this.a(this.B + 1);
        }
        ++this.L;
        n5 = 1;
        int n6 = 1;
        while (1 << n5 < this.a) {
            ++n5;
        }
        while (1 << n6 < this.b) {
            ++n6;
        }
        int n7 = this.b - 1;
        int n8 = this.a - 1;
        int n9 = this.c - 1;
        int n10 = this.G.size();
        if (n10 > 200) {
            n10 = 200;
        }
        for (n4 = 0; n4 < n10; ++n4) {
            byte by;
            e e2 = (e)this.G.remove(0);
            if (e2.e > 0) {
                --e2.e;
                this.G.add(e2);
                continue;
            }
            int n11 = e2.c;
            n3 = e2.b;
            n2 = e2.a;
            g g2 = this;
            if (!(n2 >= 0 && n3 >= 0 && n11 >= 0 && n2 < g2.a && n3 < g2.c && n11 < g2.b) || (by = this.d[(e2.b * this.b + e2.c) * this.a + e2.a]) != e2.d || by <= 0) continue;
            net.minecraft.a.a.b.x.c[by].a(this, e2.a, e2.b, e2.c, this.q);
        }
        this.K += this.a * this.b * this.c;
        n10 = this.K / 200;
        this.K -= n10 * 200;
        for (n4 = 0; n4 < n10; ++n4) {
            this.J = this.J * 3 + 1013904223;
            int n12 = this.J >> 2;
            n2 = n12 >> n5 & n7;
            int n13 = n12 & n8;
            n3 = this.d[((n12 = n12 >> n5 + n6 & n9) * this.b + n2) * this.a + n13];
            if (!net.minecraft.a.a.b.x.d[n3]) continue;
            net.minecraft.a.a.b.x.c[n3].a(this, n13, n12, n2, this.q);
        }
    }

    public final int a(Class clazz) {
        int n2 = 0;
        for (int i2 = 0; i2 < this.r.e.size(); ++i2) {
            b b2 = (b)this.r.e.get(i2);
            if (!clazz.isAssignableFrom(b2.getClass())) continue;
            ++n2;
        }
        return n2;
    }

    public final int g() {
        return this.t;
    }

    public final int h() {
        return this.s;
    }

    public final boolean b(net.minecraft.a.d.b b2) {
        int n2 = (int)b2.a;
        int n3 = (int)b2.d + 1;
        int n4 = (int)b2.b;
        int n5 = (int)b2.e + 1;
        int n6 = (int)b2.c;
        int n7 = (int)b2.f + 1;
        if (b2.a < 0.0f) {
            --n2;
        }
        if (b2.b < 0.0f) {
            --n4;
        }
        if (b2.c < 0.0f) {
            --n6;
        }
        if (n2 < 0) {
            n2 = 0;
        }
        if (n4 < 0) {
            n4 = 0;
        }
        if (n6 < 0) {
            n6 = 0;
        }
        if (n3 > this.a) {
            n3 = this.a;
        }
        if (n5 > this.c) {
            n5 = this.c;
        }
        if (n7 > this.b) {
            n7 = this.b;
        }
        for (int i2 = n2; i2 < n3; ++i2) {
            for (n2 = n4; n2 < n5; ++n2) {
                for (int i3 = n6; i3 < n7; ++i3) {
                    x x2 = net.minecraft.a.a.b.x.c[this.a(i2, n2, i3)];
                    if (x2 == null || !x2.aC.d()) continue;
                    return true;
                }
            }
        }
        return false;
    }

    public final boolean c(net.minecraft.a.d.b b2) {
        int n2 = (int)b2.a;
        int n3 = (int)b2.d + 1;
        int n4 = (int)b2.b;
        int n5 = (int)b2.e + 1;
        int n6 = (int)b2.c;
        int n7 = (int)b2.f + 1;
        while (n2 < n3) {
            for (int i2 = n4; i2 < n5; ++i2) {
                for (int i3 = n6; i3 < n7; ++i3) {
                    int n8 = this.a(n2, i2, i3);
                    if (n8 != net.minecraft.a.a.b.x.ag.at && n8 != net.minecraft.a.a.b.x.r.at && n8 != net.minecraft.a.a.b.x.s.at) continue;
                    return true;
                }
            }
            ++n2;
        }
        return false;
    }

    public final boolean a(net.minecraft.a.d.b b2, net.minecraft.a.a.d.c c2) {
        int n2 = (int)b2.a;
        int n3 = (int)b2.d + 1;
        int n4 = (int)b2.b;
        int n5 = (int)b2.e + 1;
        int n6 = (int)b2.c;
        int n7 = (int)b2.f + 1;
        while (n2 < n3) {
            for (int i2 = n4; i2 < n5; ++i2) {
                for (int i3 = n6; i3 < n7; ++i3) {
                    x x2 = net.minecraft.a.a.b.x.c[this.a(n2, i2, i3)];
                    if (x2 == null || x2.aC != c2) continue;
                    return true;
                }
            }
            ++n2;
        }
        return false;
    }

    public final void e(int n2, int n3, int n4, int n5) {
        e e2 = new e(n2, n3, n4, n5);
        if (n5 > 0) {
            n4 = net.minecraft.a.a.b.x.c[n5].e();
            e e3 = e2;
            e2.e = n4;
        }
        this.G.add(e2);
    }

    public final boolean d(net.minecraft.a.d.b b2) {
        return this.r.a(null, b2).size() == 0;
    }

    public final boolean e(net.minecraft.a.d.b object) {
        object = this.r.a(null, (net.minecraft.a.d.b)object);
        for (int i2 = 0; i2 < object.size(); ++i2) {
            b b2 = (b)object.get(i2);
            if (!b2.c) continue;
            return false;
        }
        return true;
    }

    public final List a(b b2, net.minecraft.a.d.b b3) {
        return this.r.a(b2, b3);
    }

    public final boolean a(float f2, float f3, float f4, float f5) {
        if (this.a(f2 - 0.1f, f3 - 0.1f, f4 - 0.1f)) {
            return true;
        }
        if (this.a(f2 - 0.1f, f3 - 0.1f, f4 + 0.1f)) {
            return true;
        }
        if (this.a(f2 - 0.1f, f3 + 0.1f, f4 - 0.1f)) {
            return true;
        }
        if (this.a(f2 - 0.1f, f3 + 0.1f, f4 + 0.1f)) {
            return true;
        }
        if (this.a(f2 + 0.1f, f3 - 0.1f, f4 - 0.1f)) {
            return true;
        }
        if (this.a(f2 + 0.1f, f3 - 0.1f, f4 + 0.1f)) {
            return true;
        }
        if (this.a(f2 + 0.1f, f3 + 0.1f, f4 - 0.1f)) {
            return true;
        }
        return this.a(f2 + 0.1f, f3 + 0.1f, f4 + 0.1f);
    }

    private boolean a(float f2, float f3, float f4) {
        int n2 = this.a((int)f2, (int)f3, (int)f4);
        return n2 > 0 && net.minecraft.a.a.b.x.c[n2].b();
    }

    private int a(int n2, int n3) {
        int n4;
        for (n4 = this.c; (this.a(n2, n4 - 1, n3) == 0 || net.minecraft.a.a.b.x.c[this.a((int)n2, (int)(n4 - 1), (int)n3)].aC == net.minecraft.a.a.d.c.a) && n4 > 0; --n4) {
        }
        return n4;
    }

    public final void a(int n2, int n3, int n4, float f2) {
        this.i = n2;
        this.j = n3;
        this.k = n4;
        this.l = f2;
    }

    public final float c(int n2, int n3, int n4) {
        return F[this.d(n2, n3, n4)];
    }

    public final byte d(int n2, int n3, int n4) {
        if (n2 < 0) {
            n2 = 0;
        } else if (n2 >= this.a) {
            n2 = this.a - 1;
        }
        if (n3 < 0) {
            n3 = 0;
        } else if (n3 >= this.c) {
            n3 = this.c - 1;
        }
        if (n4 < 0) {
            n4 = 0;
        } else if (n4 >= this.b) {
            n4 = this.b - 1;
        }
        if (this.d[(n3 * this.b + n4) * this.a + n2] == net.minecraft.a.a.b.x.Z.at) {
            if (n3 < this.c - 1) {
                return (byte)(this.e[((n3 + 1) * this.b + n4) * this.a + n2] & 0xF);
            }
            return 15;
        }
        return (byte)(this.e[(n3 * this.b + n4) * this.a + n2] & 0xF);
    }

    public final byte e(int n2, int n3, int n4) {
        if (n2 < 0) {
            n2 = 0;
        } else if (n2 >= this.a) {
            n2 = this.a - 1;
        }
        if (n3 < 0) {
            n3 = 0;
        } else if (n3 >= this.c) {
            n3 = this.c - 1;
        }
        if (n4 < 0) {
            n4 = 0;
        } else if (n4 >= this.b) {
            n4 = this.b - 1;
        }
        return (byte)(this.e[(n3 * this.b + n4) * this.a + n2] >>> 4 & 0xF);
    }

    public final void f(int n2, int n3, int n4, int n5) {
        if (n2 < 0) {
            n2 = 0;
        } else if (n2 >= this.a) {
            n2 = this.a - 1;
        }
        if (n3 < 0) {
            n3 = 0;
        } else if (n3 >= this.c) {
            n3 = this.c - 1;
        }
        if (n4 < 0) {
            n4 = 0;
        } else if (n4 >= this.b) {
            n4 = this.b - 1;
        }
        this.e[(n3 * this.b + n4) * this.a + n2] = (byte)((this.e[(n3 * this.b + n4) * this.a + n2] & 0xF) + (n5 << 4));
        for (n5 = 0; n5 < this.n.size(); ++n5) {
            ((d)this.n.get(n5)).a(n2, n3, n4);
        }
    }

    public final net.minecraft.a.a.d.c f(int n2, int n3, int n4) {
        if ((n2 = this.a(n2, n3, n4)) == 0) {
            return net.minecraft.a.a.d.c.a;
        }
        return net.minecraft.a.a.b.x.c[n2].aC;
    }

    public final boolean g(int n2, int n3, int n4) {
        return (n2 = this.a(n2, n3, n4)) > 0 && net.minecraft.a.a.b.x.c[n2].aC == net.minecraft.a.a.d.c.f;
    }

    public final net.minecraft.a.d.c a(net.minecraft.a.d.a a2, net.minecraft.a.d.a a3) {
        if (Float.isNaN(a2.a) || Float.isNaN(a2.b) || Float.isNaN(a2.c)) {
            return null;
        }
        if (Float.isNaN(a3.a) || Float.isNaN(a3.b) || Float.isNaN(a3.c)) {
            return null;
        }
        int n2 = a.a.d(a3.a);
        int n3 = a.a.d(a3.b);
        int n4 = a.a.d(a3.c);
        int n5 = a.a.d(a2.a);
        int n6 = a.a.d(a2.b);
        int n7 = a.a.d(a2.c);
        int n8 = 20;
        while (n8-- >= 0) {
            net.minecraft.a.d.c c2;
            int n9;
            if (Float.isNaN(a2.a) || Float.isNaN(a2.b) || Float.isNaN(a2.c)) {
                return null;
            }
            if (n5 == n2 && n6 == n3 && n7 == n4) {
                return null;
            }
            float f2 = 999.0f;
            float f3 = 999.0f;
            float f4 = 999.0f;
            if (n2 > n5) {
                f2 = (float)n5 + 1.0f;
            }
            if (n2 < n5) {
                f2 = n5;
            }
            if (n3 > n6) {
                f3 = (float)n6 + 1.0f;
            }
            if (n3 < n6) {
                f3 = n6;
            }
            if (n4 > n7) {
                f4 = (float)n7 + 1.0f;
            }
            if (n4 < n7) {
                f4 = n7;
            }
            float f5 = 999.0f;
            float f6 = 999.0f;
            float f7 = 999.0f;
            float f8 = a3.a - a2.a;
            float f9 = a3.b - a2.b;
            float f10 = a3.c - a2.c;
            if (f2 != 999.0f) {
                f5 = (f2 - a2.a) / f8;
            }
            if (f3 != 999.0f) {
                f6 = (f3 - a2.b) / f9;
            }
            if (f4 != 999.0f) {
                f7 = (f4 - a2.c) / f10;
            }
            if (f5 < f6 && f5 < f7) {
                n9 = n2 > n5 ? 4 : 5;
                a2.a = f2;
                a2.b += f9 * f5;
                a2.c += f10 * f5;
            } else if (f6 < f7) {
                n9 = n3 > n6 ? 0 : 1;
                a2.a += f8 * f6;
                a2.b = f3;
                a2.c += f10 * f6;
            } else {
                n9 = n4 > n7 ? 2 : 3;
                a2.a += f8 * f7;
                a2.b += f9 * f7;
                a2.c = f4;
            }
            net.minecraft.a.d.a a4 = new net.minecraft.a.d.a(a2.a, a2.b, a2.c);
            new net.minecraft.a.d.a(a2.a, a2.b, a2.c).a = a.a.d(a2.a);
            n5 = (int)new net.minecraft.a.d.a(a2.a, a2.b, a2.c).a;
            if (n9 == 5) {
                --n5;
                a4.a += 1.0f;
            }
            a4.b = a.a.d(a2.b);
            n6 = (int)a4.b;
            if (n9 == 1) {
                --n6;
                a4.b += 1.0f;
            }
            a4.c = a.a.d(a2.c);
            n7 = (int)a4.c;
            if (n9 == 3) {
                --n7;
                a4.c += 1.0f;
            }
            int n10 = this.a(n5, n6, n7);
            x x2 = net.minecraft.a.a.b.x.c[n10];
            if (n10 <= 0 || !x2.d() || (c2 = x2.a(this, n5, n6, n7, a2, a3)) == null) continue;
            return c2;
        }
        return null;
    }

    public final boolean h(int n2, int n3, int n4) {
        int n5;
        int n6;
        int n7;
        int n8;
        int n9;
        int n10 = this.q.nextInt(3) + 4;
        int n11 = 1;
        if (n3 <= 0 || n3 + n10 + 1 > this.c) {
            return false;
        }
        for (n9 = n3; n9 <= n3 + 1 + n10; ++n9) {
            n8 = 1;
            if (n9 == n3) {
                n8 = 0;
            }
            if (n9 >= n3 + 1 + n10 - 2) {
                n8 = 2;
            }
            for (n7 = n2 - n8; n7 <= n2 + n8 && n11 != 0; ++n7) {
                for (n6 = n4 - n8; n6 <= n4 + n8 && n11 != 0; ++n6) {
                    if (n7 >= 0 && n9 >= 0 && n6 >= 0 && n7 < this.a && n9 < this.c && n6 < this.b) {
                        n5 = this.d[(n9 * this.b + n6) * this.a + n7] & 0xFF;
                        if (n5 == 0) continue;
                        n11 = 0;
                        continue;
                    }
                    n11 = 0;
                }
            }
        }
        if (n11 == 0) {
            return false;
        }
        n9 = this.d[((n3 - 1) * this.b + n4) * this.a + n2] & 0xFF;
        if (n9 != net.minecraft.a.a.b.x.j.at && n9 != net.minecraft.a.a.b.x.k.at || n3 >= this.c - n10 - 1) {
            return false;
        }
        this.b(n2, n3 - 1, n4, net.minecraft.a.a.b.x.k.at);
        for (n8 = n3 - 3 + n10; n8 <= n3 + n10; ++n8) {
            n7 = n8 - (n3 + n10);
            n6 = 1 - n7 / 2;
            for (n5 = n2 - n6; n5 <= n2 + n6; ++n5) {
                n11 = n5 - n2;
                for (n9 = n4 - n6; n9 <= n4 + n6; ++n9) {
                    int n12 = n9 - n4;
                    if (Math.abs(n11) == n6 && Math.abs(n12) == n6 && (this.q.nextInt(2) == 0 || n7 == 0) || net.minecraft.a.a.b.x.e[this.a(n5, n8, n9)]) continue;
                    this.b(n5, n8, n9, net.minecraft.a.a.b.x.z.at);
                }
            }
        }
        for (n8 = 0; n8 < n10; ++n8) {
            if (net.minecraft.a.a.b.x.e[this.a(n2, n3 + n8, n4)]) continue;
            this.b(n2, n3 + n8, n4, net.minecraft.a.a.b.x.y.at);
        }
        return true;
    }

    public final b i() {
        return this.y;
    }

    public final void a(b b2) {
        this.r.a(b2);
        b2.a(this);
        for (int i2 = 0; i2 < this.n.size(); ++i2) {
            ((d)this.n.get(i2)).a(b2);
        }
    }

    public final void b(b b2) {
        this.r.b(b2);
        for (int i2 = 0; i2 < this.n.size(); ++i2) {
            ((d)this.n.get(i2)).b(b2);
        }
    }

    public final void a(b b2, float f2, float f3, float f4, float f5) {
        float f6;
        int n2;
        int n3;
        int n4;
        this.a(f2, f3, f4, "random.explode", 4.0f, (1.0f + (this.q.nextFloat() - this.q.nextFloat()) * 0.2f) * 0.7f);
        TreeSet<Integer> treeSet = new TreeSet<Integer>();
        float f7 = f5;
        for (n4 = 0; n4 < 16; ++n4) {
            for (n3 = 0; n3 < 16; ++n3) {
                for (n2 = 0; n2 < 16; ++n2) {
                    if (n4 != 0 && n4 != 15 && n3 != 0 && n3 != 15 && n2 != 0 && n2 != 15) continue;
                    float f8 = (float)n4 / 15.0f * 2.0f - 1.0f;
                    float f9 = (float)n3 / 15.0f * 2.0f - 1.0f;
                    float f10 = (float)n2 / 15.0f * 2.0f - 1.0f;
                    float f11 = (float)Math.sqrt(f8 * f8 + f9 * f9 + f10 * f10);
                    f8 /= f11;
                    f9 /= f11;
                    f10 /= f11;
                    float f12 = f2;
                    float f13 = f3;
                    f6 = f4;
                    for (float f14 = f5 * (0.7f + this.q.nextFloat() * 0.6f); f14 > 0.0f; f14 -= 0.22500001f) {
                        int n5 = (int)f12;
                        int n6 = (int)f13;
                        int n7 = (int)f6;
                        int n8 = this.a(n5, n6, n7);
                        if (n8 > 0) {
                            f14 -= (net.minecraft.a.a.b.x.c[n8].g() + 0.3f) * 0.3f;
                        }
                        if (f14 > 0.0f) {
                            int n9 = n5 + (n6 << 10) + (n7 << 10 << 10);
                            treeSet.add(n9);
                        }
                        f12 += f8 * 0.3f;
                        f13 += f9 * 0.3f;
                        f6 += f10 * 0.3f;
                    }
                }
            }
        }
        n4 = (int)(f2 - (f5 *= 2.0f) - 1.0f);
        n3 = (int)(f2 + f5 + 1.0f);
        n2 = (int)(f3 - f5 - 1.0f);
        int n10 = (int)(f3 + f5 + 1.0f);
        int n11 = (int)(f4 - f5 - 1.0f);
        int n12 = (int)(f4 + f5 + 1.0f);
        List list = this.r.a(b2, n4, n2, n11, n3, n10, n12);
        net.minecraft.a.d.a a2 = new net.minecraft.a.d.a(f2, f3, f4);
        for (int i2 = 0; i2 < list.size(); ++i2) {
            float f15;
            b b3 = (b)list.get(i2);
            float f16 = f4;
            float f17 = f3;
            float f18 = f2;
            b b4 = b3;
            f18 = b3.h - f18;
            f17 = b4.i - f17;
            float f19 = b4.j - f16;
            f6 = a.a.c(f18 * f18 + f17 * f17 + f19 * f19) / f5;
            if (!(f15 <= 1.0f)) continue;
            f19 = b3.h - f2;
            float f20 = b3.i - f3;
            float f21 = b3.j - f4;
            float f22 = a.a.c(f19 * f19 + f20 * f20 + f21 * f21);
            f19 /= f22;
            f20 /= f22;
            f21 /= f22;
            float f23 = this.a(a2, b3.r);
            float f24 = (1.0f - f6) * f23;
            b3.a(b2, (int)((f24 * f24 + f24) / 2.0f * 8.0f * f5 + 1.0f));
            f18 = f24;
            b3.k += f19 * f18;
            b3.l += f20 * f18;
            b3.m += f21 * f18;
        }
        f5 = f7;
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        arrayList.addAll(treeSet);
        for (int i3 = arrayList.size() - 1; i3 >= 0; --i3) {
            int n13 = (Integer)arrayList.get(i3);
            n4 = n13 & 0x3FF;
            int n14 = n13 >> 10 & 0x3FF;
            int n15 = n13 >> 20 & 0x3FF;
            if (n4 < 0 || n14 < 0 || n15 < 0 || n4 >= this.a || n14 >= this.c || n15 >= this.b) continue;
            int n16 = this.a(n4, n14, n15);
            for (int i4 = 0; i4 <= 0; ++i4) {
                float f25 = (float)n4 + this.q.nextFloat();
                float f26 = (float)n14 + this.q.nextFloat();
                float f27 = (float)n15 + this.q.nextFloat();
                float f28 = f25 - f2;
                f7 = f26 - f3;
                float f29 = f27 - f4;
                float f30 = a.a.c(f28 * f28 + f7 * f7 + f29 * f29);
                f28 /= f30;
                f7 /= f30;
                f29 /= f30;
                f30 = 0.5f / (f30 / f5 + 0.1f);
                this.a("explode", (f25 + f2) / 2.0f, (f26 + f3) / 2.0f, (f27 + f4) / 2.0f, f28 *= (f30 *= this.q.nextFloat() * this.q.nextFloat() + 0.3f), f7 *= f30, f29 *= f30);
                this.a("smoke", f25, f26, f27, f28, f7, f29);
            }
            if (n16 <= 0) continue;
            net.minecraft.a.a.b.x.c[n16].a(this, n4, n14, n15, this.e(n4, n14, n15), 0.3f);
            this.b(n4, n14, n15, 0);
            net.minecraft.a.a.b.x.c[n16].c(this, n4, n14, n15);
        }
    }

    private float a(net.minecraft.a.d.a a2, net.minecraft.a.d.b b2) {
        float f2 = 1.0f / ((b2.d - b2.a) * 2.0f + 1.0f);
        float f3 = 1.0f / ((b2.e - b2.b) * 2.0f + 1.0f);
        float f4 = 1.0f / ((b2.f - b2.c) * 2.0f + 1.0f);
        int n2 = 0;
        int n3 = 0;
        for (float f5 = 0.0f; f5 <= 1.0f; f5 += f2) {
            for (float f6 = 0.0f; f6 <= 1.0f; f6 += f3) {
                for (float f7 = 0.0f; f7 <= 1.0f; f7 += f4) {
                    float f8 = b2.a + (b2.d - b2.a) * f5;
                    float f9 = b2.b + (b2.e - b2.b) * f6;
                    float f10 = b2.c + (b2.f - b2.c) * f7;
                    if (this.a(new net.minecraft.a.d.a(f8, f9, f10), a2) == null) {
                        ++n2;
                    }
                    ++n3;
                }
            }
        }
        return (float)n2 / (float)n3;
    }

    public final b b(Class clazz) {
        for (int i2 = 0; i2 < this.r.e.size(); ++i2) {
            b b2 = (b)this.r.e.get(i2);
            if (!clazz.isAssignableFrom(b2.getClass())) continue;
            return b2;
        }
        return null;
    }

    public final int a(int n2, int n3, int n4, int n5, int n6) {
        boolean bl;
        int n7;
        int n8;
        if (n2 < 0 || n3 < 0 || n4 < 0 || n2 >= this.a || n3 >= this.c || n4 >= this.b) {
            return -1;
        }
        int n82 = n2;
        int n9 = n4;
        int n10 = ((n3 << 10) + n4 << 10) + n2;
        int n11 = 0;
        ++n11;
        this.P[0] = n2 + (n4 << 10);
        int by = -9999;
        if (n5 == net.minecraft.a.a.b.x.q.at || n5 == net.minecraft.a.a.b.x.p.at) {
            int n12 = net.minecraft.a.a.b.x.ah.at;
        }
        if (n5 == net.minecraft.a.a.b.x.s.at || n5 == net.minecraft.a.a.b.x.r.at) {
            n8 = net.minecraft.a.a.b.x.ai.at;
        }
        do {
            bl = false;
            int n13 = -1;
            n7 = 0;
            if ((N = (short)(N + 1)) == 30000) {
                Arrays.fill(this.O, (short)0);
                N = 1;
            }
            while (n11 > 0) {
                int n14;
                if (this.O[n14 = this.P[--n11]] == N) continue;
                n2 = n14 % 1024;
                n4 = n14 / 1024;
                int n15 = n4 - n9;
                n15 *= n15;
                while (n2 > 0 && this.O[n14 - 1] != N && (this.d[(n3 * this.b + n4) * this.a + n2 - 1] == n5 || this.d[(n3 * this.b + n4) * this.a + n2 - 1] == n6)) {
                    --n2;
                    --n14;
                }
                if (n2 > 0 && this.d[(n3 * this.b + n4) * this.a + n2 - 1] == n8) {
                    bl = true;
                }
                int n16 = 0;
                int n17 = 0;
                int n18 = 0;
                while (n2 < this.a && this.O[n14] != N && (this.d[(n3 * this.b + n4) * this.a + n2] == n5 || this.d[(n3 * this.b + n4) * this.a + n2] == n6)) {
                    int n19;
                    if (n4 > 0) {
                        n19 = this.d[(n3 * this.b + n4 - 1) * this.a + n2];
                        if (n19 == n8) {
                            bl = true;
                        }
                        if ((n19 = this.O[n14 - 1024] != N && (n19 == n5 || n19 == n6) ? 1 : 0) != 0 && n16 == 0) {
                            this.P[n11++] = n14 - 1024;
                        }
                        n16 = n19;
                    }
                    if (n4 < this.b - 1) {
                        n19 = this.d[(n3 * this.b + n4 + 1) * this.a + n2];
                        if (n19 == n8) {
                            bl = true;
                        }
                        if ((n19 = this.O[n14 + 1024] != N && (n19 == n5 || n19 == n6) ? 1 : 0) != 0 && n17 == 0) {
                            this.P[n11++] = n14 + 1024;
                        }
                        n17 = n19;
                    }
                    if (n3 < this.c - 1) {
                        n19 = this.d[((n3 + 1) * this.b + n4) * this.a + n2];
                        n19 = n19 == n5 || n19 == n6 ? 1 : 0;
                        if (n19 != 0 && n18 == 0) {
                            this.Q[n7++] = n14;
                        }
                        n18 = n19;
                    }
                    n19 = n2 - n82;
                    n19 *= n19;
                    if ((n19 += n15) > n13) {
                        n13 = n19;
                        n10 = ((n3 << 10) + n4 << 10) + n2;
                    }
                    this.O[n14++] = N;
                    ++n2;
                }
                if (n2 >= this.a || this.d[(n3 * this.b + n4) * this.a + n2] != n8) continue;
                bl = true;
            }
            ++n3;
            int[] nArray = this.Q;
            this.Q = this.P;
            this.P = nArray;
        } while ((n11 = n7) > 0);
        if (bl) {
            return -9999;
        }
        return n10;
    }

    public final int b(int n2, int n3, int n4, int n5, int n6) {
        if (n2 < 0 || n3 < 0 || n4 < 0 || n2 >= this.a || n3 >= this.c || n4 >= this.b) {
            return 0;
        }
        if ((N = (short)(N + 1)) == 30000) {
            Arrays.fill(this.O, (short)0);
            N = 1;
        }
        int n7 = 0;
        ++n7;
        this.P[0] = n2 + (n4 << 10);
        while (n7 > 0) {
            int n8;
            if (this.O[n8 = this.P[--n7]] == N) continue;
            n2 = n8 % 1024;
            n4 = n8 / 1024;
            if (n2 == 0 || n2 == this.a - 1 || n3 == 0 || n3 == this.c - 1 || n4 == 0 || n4 == this.b - 1) {
                return 2;
            }
            while (n2 > 0 && this.O[n8 - 1] != N && (this.d[(n3 * this.b + n4) * this.a + n2 - 1] == n5 || this.d[(n3 * this.b + n4) * this.a + n2 - 1] == n6)) {
                --n2;
                --n8;
            }
            if (n2 > 0 && this.d[(n3 * this.b + n4) * this.a + n2 - 1] == 0) {
                return 0;
            }
            byte by = 0;
            byte by2 = 0;
            while (n2 < this.a && this.O[n8] != N && (this.d[(n3 * this.b + n4) * this.a + n2] == n5 || this.d[(n3 * this.b + n4) * this.a + n2] == n6)) {
                byte by3;
                if (n2 == 0 || n2 == this.a - 1) {
                    return 2;
                }
                if (n4 > 0) {
                    by3 = this.d[(n3 * this.b + n4 - 1) * this.a + n2];
                    if (by3 == 0) {
                        return 0;
                    }
                    by3 = this.O[n8 - 1024] != N && (by3 == n5 || by3 == n6) ? (byte)1 : 0;
                    if (by3 != 0 && by == 0) {
                        this.P[n7++] = n8 - 1024;
                    }
                    by = by3;
                }
                if (n4 < this.b - 1) {
                    by3 = this.d[(n3 * this.b + n4 + 1) * this.a + n2];
                    if (by3 == 0) {
                        return 0;
                    }
                    by3 = this.O[n8 + 1024] != N && (by3 == n5 || by3 == n6) ? (byte)1 : 0;
                    if (by3 != 0 && by2 == 0) {
                        this.P[n7++] = n8 + 1024;
                    }
                    by2 = by3;
                }
                this.O[n8] = N;
                ++n8;
                ++n2;
            }
            if (n2 >= this.a || this.d[(n3 * this.b + n4) * this.a + n2] != 0) continue;
            return 0;
        }
        return 1;
    }

    public final void a(b b2, String string, float f2, float f3) {
        for (int i2 = 0; i2 < this.n.size(); ++i2) {
            float f4 = 16.0f;
            if (f2 > 1.0f) {
                f4 = 16.0f * f2;
            }
            if (!(this.y.b(b2) < f4 * f4)) continue;
            ((d)this.n.get(i2)).a(string, b2.h, b2.i - b2.v, b2.j, f2, f3);
        }
    }

    public final void a(float f2, float f3, float f4, String string, float f5) {
        try {
            for (int i2 = 0; i2 < this.n.size(); ++i2) {
                ((d)this.n.get(i2)).a(string, f2, f3, f4, 0.0f);
            }
            return;
        }
        catch (Exception exception) {
            Exception exception2 = exception;
            exception.printStackTrace();
            return;
        }
    }

    public final void a(float f2, float f3, float f4, String string, float f5, float f6) {
        try {
            for (int i2 = 0; i2 < this.n.size(); ++i2) {
                float f7;
                float f8;
                float f9;
                float f10 = 16.0f;
                if (f5 > 1.0f) {
                    f10 = 16.0f * f5;
                }
                if (!((f9 = f2 - this.y.h) * f9 + (f8 = f3 - this.y.i) * f8 + (f7 = f4 - this.y.j) * f7 < f10 * f10)) continue;
                ((d)this.n.get(i2)).a(string, f2, f3, f4, f5, f6);
            }
            return;
        }
        catch (Exception exception) {
            Exception exception2 = exception;
            exception.printStackTrace();
            return;
        }
    }

    public final void g(int n2, int n3, int n4, int n5) {
        if (n5 == 0) {
            --n3;
        }
        if (n5 == 1) {
            ++n3;
        }
        if (n5 == 2) {
            --n4;
        }
        if (n5 == 3) {
            ++n4;
        }
        if (n5 == 4) {
            --n2;
        }
        if (n5 == 5) {
            ++n2;
        }
        if (this.a(n2, n3, n4) == net.minecraft.a.a.b.x.ag.at) {
            this.a((float)n2 + 0.5f, (float)n3 + 0.5f, (float)n4 + 0.5f, "random.fizz", 0.5f, 2.6f + (this.q.nextFloat() - this.q.nextFloat()) * 0.8f);
            this.b(n2, n3, n4, 0);
        }
    }

    public final void a(int n2, int n3, int n4, a a2) {
        a2.a = this;
        a2.b = n2;
        a2.c = n3;
        a2.d = n4;
        this.o.put(n2 + (n3 << 10) + (n4 << 10 << 10), a2);
        this.H.add(a2);
    }

    public final void i(int n2, int n3, int n4) {
        this.H.remove(this.o.remove(n2 + (n3 << 10) + (n4 << 10 << 10)));
    }

    public final a j(int n2, int n3, int n4) {
        int n5 = n2 + (n3 << 10) + (n4 << 10 << 10);
        Object object = (a)this.o.get(n5);
        if (object == null) {
            int n6 = this.a(n2, n3, n4);
            object = (q)net.minecraft.a.a.b.x.c[n6];
            ((q)object).d(this, n2, n3, n4);
            object = (a)this.o.get(n5);
        }
        return object;
    }

    public final void a(String string, float f2, float f3, float f4, float f5, float f6, float f7) {
        for (int i2 = 0; i2 < this.n.size(); ++i2) {
            ((d)this.n.get(i2)).a(string, f2, f3, f4, f5, f6, f7);
        }
    }

    public final void k(int n2, int n3, int n4) {
        for (int i2 = 0; i2 < 1000; ++i2) {
            int n5;
            int n6;
            int n7 = n2 + this.q.nextInt(16) - this.q.nextInt(16);
            int n8 = this.a(n7, n6 = n3 + this.q.nextInt(16) - this.q.nextInt(16), n5 = n4 + this.q.nextInt(16) - this.q.nextInt(16));
            if (n8 <= 0) continue;
            net.minecraft.a.a.b.x.c[n8].b(this, n7, n6, n5, this.I);
        }
    }

    public final String j() {
        return "" + this.G.size() + ". L: " + this.M.b();
    }

    public final void k() {
        for (int i2 = 0; i2 < this.n.size(); ++i2) {
            d d2 = (d)this.n.get(i2);
            for (int i3 = 0; i3 < this.r.e.size(); ++i3) {
                d2.b((b)this.r.e.get(i2));
            }
        }
    }

    private void a(int n2) {
        this.M.a(n2);
    }

    public final boolean l(int n2, int n3, int n4) {
        if (this.p[n2 + n4 * this.a] <= n3) {
            return true;
        }
        while (n3 < this.c) {
            if (net.minecraft.a.a.b.x.e[this.a(n2, n3, n4)]) {
                return false;
            }
            ++n3;
        }
        return true;
    }

    static {
        for (int i2 = 0; i2 <= 15; ++i2) {
            float f2 = 1.0f - (float)i2 / 15.0f;
            net.minecraft.a.a.g.F[i2] = (1.0f - f2) / (f2 * 3.0f + 1.0f) * 0.95f + 0.05f;
        }
        N = 0;
    }
}

