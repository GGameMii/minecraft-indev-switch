/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.a.b;

import java.util.Random;
import net.minecraft.a.a.b.a;
import net.minecraft.a.a.b.aa;
import net.minecraft.a.a.b.ab;
import net.minecraft.a.a.b.ac;
import net.minecraft.a.a.b.ad;
import net.minecraft.a.a.b.af;
import net.minecraft.a.a.b.ag;
import net.minecraft.a.a.b.ah;
import net.minecraft.a.a.b.ai;
import net.minecraft.a.a.b.aj;
import net.minecraft.a.a.b.b;
import net.minecraft.a.a.b.c;
import net.minecraft.a.a.b.d;
import net.minecraft.a.a.b.e;
import net.minecraft.a.a.b.f;
import net.minecraft.a.a.b.h;
import net.minecraft.a.a.b.i;
import net.minecraft.a.a.b.j;
import net.minecraft.a.a.b.k;
import net.minecraft.a.a.b.l;
import net.minecraft.a.a.b.m;
import net.minecraft.a.a.b.n;
import net.minecraft.a.a.b.o;
import net.minecraft.a.a.b.r;
import net.minecraft.a.a.b.s;
import net.minecraft.a.a.b.t;
import net.minecraft.a.a.b.u;
import net.minecraft.a.a.b.v;
import net.minecraft.a.a.b.w;
import net.minecraft.a.a.b.y;
import net.minecraft.a.a.b.z;
import net.minecraft.a.a.g;

public class x {
    private static n a = new n("stone", 1.0f, 1.0f);
    private static n b = new n("wood", 1.0f, 1.0f);
    private static n aD = new n("gravel", 1.0f, 1.0f);
    private static n aE = new n("grass", 1.0f, 1.0f);
    private static n aF = new n("stone", 1.0f, 1.0f);
    private static n aG = new n("stone", 1.0f, 1.5f);
    private static n aH = new j("stone", 1.0f, 1.0f);
    private static n aI = new n("cloth", 1.0f, 1.0f);
    private static n aJ = new i("sand", 1.0f, 1.0f);
    public static final x[] c = new x[256];
    public static final boolean[] d = new boolean[256];
    public static final boolean[] e = new boolean[256];
    public static final int[] f = new int[256];
    private static boolean[] aK = new boolean[256];
    public static final boolean[] g = new boolean[256];
    public static final int[] h = new int[256];
    public static final x i;
    public static final af j;
    public static final x k;
    public static final x l;
    public static final x m;
    public static final x n;
    public static final x o;
    public static final x p;
    public static final x q;
    public static final x r;
    public static final x s;
    public static final x t;
    public static final x u;
    public static final x v;
    public static final x w;
    public static final x x;
    public static final x y;
    public static final x z;
    public static final x A;
    public static final x B;
    public static final x C;
    public static final x D;
    public static final x E;
    public static final x F;
    public static final x G;
    public static final x H;
    public static final x I;
    public static final x J;
    public static final x K;
    public static final x L;
    public static final x M;
    public static final x N;
    public static final x O;
    public static final x P;
    public static final x Q;
    public static final x R;
    public static final ad S;
    public static final ad T;
    public static final ad U;
    public static final ad V;
    public static final x W;
    public static final x X;
    public static final x Y;
    public static final x Z;
    public static final x aa;
    public static final x ab;
    public static final x ac;
    public static final x ad;
    public static final x ae;
    public static final x af;
    public static final ah ag;
    public static final x ah;
    public static final x ai;
    public static final x aj;
    public static final x ak;
    public static final x al;
    public static final x am;
    public static final x an;
    public static final x ao;
    public static final x ap;
    public static final x aq;
    public static final x ar;
    public int as;
    public final int at;
    private float aL;
    private float aM;
    public float au;
    public float av;
    public float aw;
    public float ax;
    public float ay;
    public float az;
    public n aA = a;
    public float aB = 1.0f;
    public final net.minecraft.a.a.d.c aC;

    protected x(int n2, net.minecraft.a.a.d.c c2) {
        if (c[n2] != null) {
            throw new IllegalArgumentException("Slot " + n2 + " is already occupied by " + c[n2] + " when adding " + this);
        }
        this.aC = c2;
        net.minecraft.a.a.b.x.c[n2] = this;
        this.at = n2;
        this.a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        net.minecraft.a.a.b.x.e[n2] = this.b();
        net.minecraft.a.a.b.x.f[n2] = this.b() ? 255 : 0;
        net.minecraft.a.a.b.x.aK[n2] = this.c();
        net.minecraft.a.a.b.x.g[n2] = false;
    }

    protected x(int n2, int n3, net.minecraft.a.a.d.c c2) {
        this(n2, c2);
        this.as = n3;
    }

    protected final x c(int n2) {
        net.minecraft.a.a.b.x.f[this.at] = n2;
        return this;
    }

    private x c(float f2) {
        net.minecraft.a.a.b.x.h[this.at] = (int)(15.0f * f2);
        return this;
    }

    protected final x a(float f2) {
        this.aM = f2 * 3.0f;
        return this;
    }

    public boolean c() {
        return true;
    }

    public int a() {
        return 0;
    }

    protected final x b(float f2) {
        this.aL = f2;
        if (this.aM < f2 * 5.0f) {
            this.aM = f2 * 5.0f;
        }
        return this;
    }

    protected final void a(boolean bl) {
        net.minecraft.a.a.b.x.d[this.at] = bl;
    }

    protected final void a(float f2, float f3, float f4, float f5, float f6, float f7) {
        this.au = f2;
        this.av = f3;
        this.aw = f4;
        this.ax = f5;
        this.ay = f6;
        this.az = f7;
    }

    public float f(g g2, int n2, int n3, int n4) {
        return g2.c(n2, n3, n4);
    }

    public boolean d(g g2, int n2, int n3, int n4, int n5) {
        return !g2.b(n2, n3, n4);
    }

    public int a(g g2, int n2, int n3, int n4, int n5) {
        return this.a(n5, g2.e(n2, n3, n4));
    }

    public int a(int n2, int n3) {
        return this.a(n2);
    }

    public int a(int n2) {
        return this.as;
    }

    public final net.minecraft.a.d.b b(int n2, int n3, int n4) {
        return new net.minecraft.a.d.b((float)n2 + this.au, (float)n3 + this.av, (float)n4 + this.aw, (float)n2 + this.ax, (float)n3 + this.ay, (float)n4 + this.az);
    }

    public net.minecraft.a.d.b a(int n2, int n3, int n4) {
        return new net.minecraft.a.d.b((float)n2 + this.au, (float)n3 + this.av, (float)n4 + this.aw, (float)n2 + this.ax, (float)n3 + this.ay, (float)n4 + this.az);
    }

    public boolean b() {
        return true;
    }

    public boolean d() {
        return true;
    }

    public void a(g g2, int n2, int n3, int n4, Random random) {
    }

    public void b(g g2, int n2, int n3, int n4, Random random) {
    }

    public void c(g g2, int n2, int n3, int n4, int n5) {
    }

    public void b(g g2, int n2, int n3, int n4, int n5) {
    }

    public int e() {
        return 5;
    }

    public void d(g g2, int n2, int n3, int n4) {
    }

    public void b(g g2, int n2, int n3, int n4) {
    }

    public int a(Random random) {
        return 1;
    }

    public int a(int n2, Random random) {
        return this.at;
    }

    public final float a(net.minecraft.a.c.e.a a2) {
        if (this.aL < 0.0f) {
            return 0.0f;
        }
        if (!a2.a(this)) {
            return 1.0f / this.aL / 100.0f;
        }
        Object object = this;
        x x2 = object;
        object = a2.b;
        float f2 = 1.0f;
        if (((net.minecraft.a.c.e.b)object).a[((net.minecraft.a.c.e.b)object).c] != null) {
            object = ((net.minecraft.a.c.e.b)object).a[((net.minecraft.a.c.e.b)object).c];
            f2 = 1.0f * ((net.minecraft.a.b.k)object).a().b(x2);
        }
        float f3 = f2;
        if (a2.m()) {
            f3 /= 5.0f;
        }
        if (!a2.s) {
            f3 /= 5.0f;
        }
        return f3 / this.aL / 30.0f;
    }

    public final void f(g g2, int n2, int n3, int n4, int n5) {
        this.a(g2, n2, n3, n4, n5, 1.0f);
    }

    public final void a(g g2, int n2, int n3, int n4, int n5, float f2) {
        int n6 = this.a(g2.q);
        for (int i2 = 0; i2 < n6; ++i2) {
            int n7;
            if (g2.q.nextFloat() > f2 || (n7 = this.a(n5, g2.q)) <= 0) continue;
            float f3 = g2.q.nextFloat() * 0.7f + 0.15f;
            float f4 = g2.q.nextFloat() * 0.7f + 0.15f;
            float f5 = g2.q.nextFloat() * 0.7f + 0.15f;
            net.minecraft.a.c.c.b b2 = new net.minecraft.a.c.c.b(g2, (float)n2 + f3, (float)n3 + f4, (float)n4 + f5, new net.minecraft.a.b.k(n7));
            new net.minecraft.a.c.c.b(g2, (float)n2 + f3, (float)n3 + f4, (float)n4 + f5, new net.minecraft.a.b.k(n7)).O = 10;
            g2.a(b2);
        }
    }

    public final float g() {
        return this.aM / 5.0f;
    }

    public net.minecraft.a.d.c a(g object, int n2, int n3, int n4, net.minecraft.a.d.a a2, net.minecraft.a.d.a a3) {
        a2 = a2.a(-n2, -n3, -n4);
        a3 = a3.a(-n2, -n3, -n4);
        object = a2.a(a3, this.au);
        net.minecraft.a.d.a a4 = a2.a(a3, this.ax);
        net.minecraft.a.d.a a5 = a2.b(a3, this.av);
        net.minecraft.a.d.a a6 = a2.b(a3, this.ay);
        net.minecraft.a.d.a a7 = a2.c(a3, this.aw);
        a3 = a2.c(a3, this.az);
        if (!this.a((net.minecraft.a.d.a)object)) {
            object = null;
        }
        if (!this.a(a4)) {
            a4 = null;
        }
        if (!this.b(a5)) {
            a5 = null;
        }
        if (!this.b(a6)) {
            a6 = null;
        }
        if (!this.c(a7)) {
            a7 = null;
        }
        if (!this.c(a3)) {
            a3 = null;
        }
        Object object2 = null;
        if (object != null) {
            object2 = object;
        }
        if (a4 != null && (object2 == null || a2.b(a4) < a2.b((net.minecraft.a.d.a)object2))) {
            object2 = a4;
        }
        if (a5 != null && (object2 == null || a2.b(a5) < a2.b((net.minecraft.a.d.a)object2))) {
            object2 = a5;
        }
        if (a6 != null && (object2 == null || a2.b(a6) < a2.b((net.minecraft.a.d.a)object2))) {
            object2 = a6;
        }
        if (a7 != null && (object2 == null || a2.b(a7) < a2.b((net.minecraft.a.d.a)object2))) {
            object2 = a7;
        }
        if (a3 != null && (object2 == null || a2.b(a3) < a2.b((net.minecraft.a.d.a)object2))) {
            object2 = a3;
        }
        if (object2 == null) {
            return null;
        }
        int n5 = -1;
        if (object2 == object) {
            n5 = 4;
        }
        if (object2 == a4) {
            n5 = 5;
        }
        if (object2 == a5) {
            n5 = 0;
        }
        if (object2 == a6) {
            n5 = 1;
        }
        if (object2 == a7) {
            n5 = 2;
        }
        if (object2 == a3) {
            n5 = 3;
        }
        return new net.minecraft.a.d.c(n2, n3, n4, n5, ((net.minecraft.a.d.a)object2).a(n2, n3, n4));
    }

    private boolean a(net.minecraft.a.d.a a2) {
        if (a2 == null) {
            return false;
        }
        return a2.b >= this.av && a2.b <= this.ay && a2.c >= this.aw && a2.c <= this.az;
    }

    private boolean b(net.minecraft.a.d.a a2) {
        if (a2 == null) {
            return false;
        }
        return a2.a >= this.au && a2.a <= this.ax && a2.c >= this.aw && a2.c <= this.az;
    }

    private boolean c(net.minecraft.a.d.a a2) {
        if (a2 == null) {
            return false;
        }
        return a2.a >= this.au && a2.a <= this.ax && a2.b >= this.av && a2.b <= this.ay;
    }

    public void c(g g2, int n2, int n3, int n4) {
    }

    public int f() {
        return 0;
    }

    public boolean a(g g2, int n2, int n3, int n4) {
        return true;
    }

    public boolean a(g g2, int n2, int n3, int n4, net.minecraft.a.c.e.a a2) {
        return false;
    }

    public void g(g g2, int n2, int n3, int n4) {
    }

    public void g(g g2, int n2, int n3, int n4, int n5) {
    }

    static {
        n n2 = aF;
        x x2 = new r(1, 1).b(1.5f).a(10.0f);
        new r(1, 1).b(1.5f).a(10.0f).aA = n2;
        i = x2;
        n2 = aE;
        x2 = new af(2).b(0.6f);
        new af(2).b(0.6f).aA = n2;
        j = (af)x2;
        n2 = aD;
        x2 = new l(3, 2).b(0.5f);
        new l(3, 2).b(0.5f).aA = n2;
        k = x2;
        n2 = aF;
        x2 = new x(4, 16, net.minecraft.a.a.d.c.d).b(2.0f).a(10.0f);
        new x(4, 16, net.minecraft.a.a.d.c.d).b(2.0f).a(10.0f).aA = n2;
        l = x2;
        n2 = b;
        x2 = new x(5, 4, net.minecraft.a.a.d.c.c).b(2.0f).a(5.0f);
        new x(5, 4, net.minecraft.a.a.d.c.c).b(2.0f).a(5.0f).aA = n2;
        m = x2;
        n2 = aE;
        x2 = new z(6, 15).b(0.0f);
        new z(6, 15).b(0.0f).aA = n2;
        n = x2;
        n2 = aF;
        x2 = new x(7, 17, net.minecraft.a.a.d.c.d).b(-1.0f).a(6000000.0f);
        new x(7, 17, net.minecraft.a.a.d.c.d).b(-1.0f).a(6000000.0f).aA = n2;
        o = x2;
        p = new m(8, net.minecraft.a.a.d.c.f).b(100.0f).c(3);
        q = new b(9, net.minecraft.a.a.d.c.f).b(100.0f).c(3);
        r = new m(10, net.minecraft.a.a.d.c.g).b(0.0f).c(1.0f).c(255);
        s = new b(11, net.minecraft.a.a.d.c.g).b(100.0f).c(1.0f).c(255);
        n2 = aJ;
        x2 = new w(12, 18).b(0.5f);
        new w(12, 18).b(0.5f).aA = n2;
        t = x2;
        n2 = aD;
        x2 = new e(13, 19).b(0.6f);
        new e(13, 19).b(0.6f).aA = n2;
        u = x2;
        n2 = aF;
        x2 = new c(14, 32).b(3.0f).a(5.0f);
        new c(14, 32).b(3.0f).a(5.0f).aA = n2;
        v = x2;
        n2 = aF;
        x2 = new c(15, 33).b(3.0f).a(5.0f);
        new c(15, 33).b(3.0f).a(5.0f).aA = n2;
        w = x2;
        n2 = aF;
        x2 = new c(16, 34).b(3.0f).a(5.0f);
        new c(16, 34).b(3.0f).a(5.0f).aA = n2;
        x = x2;
        n2 = b;
        x2 = new aa(17).b(2.0f);
        new aa(17).b(2.0f).aA = n2;
        y = x2;
        n2 = aE;
        x2 = new o(18, 52).b(0.2f).c(1);
        new o(18, 52).b(0.2f).c(1).aA = n2;
        z = x2;
        n2 = aE;
        x2 = new ag(19).b(0.6f);
        new ag(19).b(0.6f).aA = n2;
        A = x2;
        n2 = aH;
        x2 = new u(20, 49, net.minecraft.a.a.d.c.o, false).b(0.3f);
        new u(20, 49, net.minecraft.a.a.d.c.o, false).b(0.3f).aA = n2;
        B = x2;
        n2 = aI;
        x2 = new x(21, 64, net.minecraft.a.a.d.c.k).b(0.8f);
        new x(21, 64, net.minecraft.a.a.d.c.k).b(0.8f).aA = n2;
        C = x2;
        n2 = aI;
        x2 = new x(22, 65, net.minecraft.a.a.d.c.k).b(0.8f);
        new x(22, 65, net.minecraft.a.a.d.c.k).b(0.8f).aA = n2;
        D = x2;
        n2 = aI;
        x2 = new x(23, 66, net.minecraft.a.a.d.c.k).b(0.8f);
        new x(23, 66, net.minecraft.a.a.d.c.k).b(0.8f).aA = n2;
        E = x2;
        n2 = aI;
        x2 = new x(24, 67, net.minecraft.a.a.d.c.k).b(0.8f);
        new x(24, 67, net.minecraft.a.a.d.c.k).b(0.8f).aA = n2;
        F = x2;
        n2 = aI;
        x2 = new x(25, 68, net.minecraft.a.a.d.c.k).b(0.8f);
        new x(25, 68, net.minecraft.a.a.d.c.k).b(0.8f).aA = n2;
        G = x2;
        n2 = aI;
        x2 = new x(26, 69, net.minecraft.a.a.d.c.k).b(0.8f);
        new x(26, 69, net.minecraft.a.a.d.c.k).b(0.8f).aA = n2;
        H = x2;
        n2 = aI;
        x2 = new x(27, 70, net.minecraft.a.a.d.c.k).b(0.8f);
        new x(27, 70, net.minecraft.a.a.d.c.k).b(0.8f).aA = n2;
        I = x2;
        n2 = aI;
        x2 = new x(28, 71, net.minecraft.a.a.d.c.k).b(0.8f);
        new x(28, 71, net.minecraft.a.a.d.c.k).b(0.8f).aA = n2;
        J = x2;
        n2 = aI;
        x2 = new x(29, 72, net.minecraft.a.a.d.c.k).b(0.8f);
        new x(29, 72, net.minecraft.a.a.d.c.k).b(0.8f).aA = n2;
        K = x2;
        n2 = aI;
        x2 = new x(30, 73, net.minecraft.a.a.d.c.k).b(0.8f);
        new x(30, 73, net.minecraft.a.a.d.c.k).b(0.8f).aA = n2;
        L = x2;
        n2 = aI;
        x2 = new x(31, 74, net.minecraft.a.a.d.c.k).b(0.8f);
        new x(31, 74, net.minecraft.a.a.d.c.k).b(0.8f).aA = n2;
        M = x2;
        n2 = aI;
        x2 = new x(32, 75, net.minecraft.a.a.d.c.k).b(0.8f);
        new x(32, 75, net.minecraft.a.a.d.c.k).b(0.8f).aA = n2;
        N = x2;
        n2 = aI;
        x2 = new x(33, 76, net.minecraft.a.a.d.c.k).b(0.8f);
        new x(33, 76, net.minecraft.a.a.d.c.k).b(0.8f).aA = n2;
        O = x2;
        n2 = aI;
        x2 = new x(34, 77, net.minecraft.a.a.d.c.k).b(0.8f);
        new x(34, 77, net.minecraft.a.a.d.c.k).b(0.8f).aA = n2;
        P = x2;
        n2 = aI;
        x2 = new x(35, 78, net.minecraft.a.a.d.c.k).b(0.8f);
        new x(35, 78, net.minecraft.a.a.d.c.k).b(0.8f).aA = n2;
        Q = x2;
        n2 = aI;
        x2 = new x(36, 79, net.minecraft.a.a.d.c.k).b(0.8f);
        new x(36, 79, net.minecraft.a.a.d.c.k).b(0.8f).aA = n2;
        R = x2;
        n2 = aE;
        x2 = new ad(37, 13).b(0.0f);
        new ad(37, 13).b(0.0f).aA = n2;
        S = (ad)x2;
        n2 = aE;
        x2 = new ad(38, 12).b(0.0f);
        new ad(38, 12).b(0.0f).aA = n2;
        T = (ad)x2;
        n2 = aE;
        x2 = new v(39, 29).b(0.0f);
        new v(39, 29).b(0.0f).aA = n2;
        U = (ad)x2.c(0.125f);
        n2 = aE;
        x2 = new v(40, 28).b(0.0f);
        new v(40, 28).b(0.0f).aA = n2;
        V = (ad)x2;
        n2 = aG;
        x2 = new d(41, 39).b(3.0f).a(10.0f);
        new d(41, 39).b(3.0f).a(10.0f).aA = n2;
        W = x2;
        n2 = aG;
        x2 = new d(42, 38).b(5.0f).a(10.0f);
        new d(42, 38).b(5.0f).a(10.0f).aA = n2;
        X = x2;
        n2 = aF;
        x2 = new aj(43, true).b(2.0f).a(10.0f);
        new aj(43, true).b(2.0f).a(10.0f).aA = n2;
        Y = x2;
        n2 = aF;
        x2 = new aj(44, false).b(2.0f).a(10.0f);
        new aj(44, false).b(2.0f).a(10.0f).aA = n2;
        Z = x2;
        n2 = aF;
        x2 = new x(45, 7, net.minecraft.a.a.d.c.d).b(2.0f).a(10.0f);
        new x(45, 7, net.minecraft.a.a.d.c.d).b(2.0f).a(10.0f).aA = n2;
        aa = x2;
        n2 = aE;
        x2 = new h(46, 8).b(0.0f);
        new h(46, 8).b(0.0f).aA = n2;
        ab = x2;
        n2 = b;
        x2 = new y(47, 35).b(1.5f);
        new y(47, 35).b(1.5f).aA = n2;
        ac = x2;
        n2 = aF;
        x2 = new x(48, 36, net.minecraft.a.a.d.c.d).b(2.0f).a(10.0f);
        new x(48, 36, net.minecraft.a.a.d.c.d).b(2.0f).a(10.0f).aA = n2;
        ad = x2;
        n2 = aF;
        x2 = new r(49, 37).b(10.0f).a(10.0f);
        new r(49, 37).b(10.0f).a(10.0f).aA = n2;
        ae = x2;
        n2 = b;
        x2 = new ac(50, 80).b(0.0f).c(0.875f);
        new ac(50, 80).b(0.0f).c(0.875f).aA = n2;
        af = x2;
        n2 = b;
        x2 = new ah(51, 31).b(0.0f).c(1.0f);
        new ah(51, 31).b(0.0f).c(1.0f).aA = n2;
        ag = (ah)x2;
        n2 = b;
        x2 = new ai(52, net.minecraft.a.a.b.x.p.at).b(0.0f);
        new ai(52, net.minecraft.a.a.b.x.p.at).b(0.0f).aA = n2;
        ah = x2;
        n2 = b;
        x2 = new ai(53, net.minecraft.a.a.b.x.r.at).b(0.0f);
        new ai(53, net.minecraft.a.a.b.x.r.at).b(0.0f).aA = n2;
        ai = x2;
        n2 = b;
        x2 = new a(54).b(2.5f);
        new a(54).b(2.5f).aA = n2;
        aj = x2;
        n2 = aG;
        x2 = new k(55, 62).b(0.5f);
        new k(55, 62).b(0.5f).aA = n2;
        ak = x2;
        n2 = aF;
        x2 = new c(56, 50).b(3.0f).a(5.0f);
        new c(56, 50).b(3.0f).a(5.0f).aA = n2;
        al = x2;
        n2 = aG;
        x2 = new d(57, 40).b(5.0f).a(10.0f);
        new d(57, 40).b(5.0f).a(10.0f).aA = n2;
        am = x2;
        n2 = b;
        x2 = new t(58).b(2.5f);
        new t(58).b(2.5f).aA = n2;
        an = x2;
        n2 = aE;
        x2 = new f(59, 88).b(0.0f);
        new f(59, 88).b(0.0f).aA = n2;
        ao = x2;
        n2 = aD;
        x2 = new ab(60).b(0.6f);
        new ab(60).b(0.6f).aA = n2;
        ap = x2;
        n2 = aF;
        x2 = new s(61, false).b(3.5f);
        new s(61, false).b(3.5f).aA = n2;
        aq = x2;
        n2 = aF;
        x2 = new s(62, true).b(3.5f);
        new s(62, true).b(3.5f).aA = n2;
        ar = x2.c(0.875f);
        for (int i2 = 0; i2 < 256; ++i2) {
            if (c[i2] == null) continue;
            net.minecraft.a.b.i.b[i2] = new net.minecraft.a.b.d(i2 - 256);
        }
    }
}

