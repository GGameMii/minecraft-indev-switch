/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.c;

import a.a;
import com.a.a.c;
import java.util.List;
import net.minecraft.a.a.b.n;
import net.minecraft.a.a.b.x;
import net.minecraft.a.a.g;
import net.minecraft.a.c.b;

public class e
extends b {
    public int S = 20;
    public float T = 0.0f;
    public float U = 0.0f;
    private float a;
    private float b;
    protected String V = "/char.png";
    private int O = 0;
    public int W;
    public int X;
    private int P;
    public int Y;
    public int Z;
    public float aa = 0.0f;
    public int ab = 0;
    public int ac = 0;
    public float ad;
    public float ae;
    public float af;
    public float ag;
    public float ah;
    protected int ai;
    protected float aj;
    protected float ak;
    private float Q;
    protected boolean al;
    private float R;
    protected float am;

    public e(g g2) {
        super(g2);
        Math.random();
        this.ai = 0;
        this.al = false;
        this.R = 0.0f;
        this.am = 0.7f;
        this.W = 10;
        this.c = true;
        Math.random();
        this.b(this.h, this.i, this.j);
        Math.random();
        this.n = (float)(Math.random() * 3.1415927410125732 * 2.0);
        this.E = 0.5f;
    }

    public final String q() {
        return this.V;
    }

    public final boolean d() {
        return !this.u;
    }

    public final boolean p() {
        return !this.u;
    }

    protected float n() {
        return this.x * 0.85f;
    }

    public void b_() {
        boolean bl;
        float f2;
        float f3;
        float f4;
        super.b_();
        if (this.G.nextInt(1000) < this.P++) {
            this.P = -80;
            String string = this.g();
            if (string != null) {
                this.d.a(this, string, 1.0f, (this.G.nextFloat() - this.G.nextFloat()) * 0.2f + 1.0f);
            }
        }
        if (this.m()) {
            --this.M;
            if (this.M == -20) {
                this.M = 0;
                for (int i2 = 0; i2 < 8; ++i2) {
                    f4 = this.G.nextFloat() - this.G.nextFloat();
                    f3 = this.G.nextFloat() - this.G.nextFloat();
                    f2 = this.G.nextFloat() - this.G.nextFloat();
                    this.d.a("bubble", this.h + f4, this.i + f3, this.j + f2, this.k, this.l, this.m);
                }
                this.a(null, 2);
            }
            this.J = 0;
        } else {
            this.M = this.K;
        }
        this.ad = this.ae;
        if (this.ac > 0) {
            --this.ac;
        }
        if (this.Y > 0) {
            --this.Y;
        }
        if (this.L > 0) {
            --this.L;
        }
        if (this.W <= 0) {
            ++this.ab;
            if (this.ab > 20) {
                this.k();
            }
        }
        this.U = this.T;
        this.p = this.n;
        this.q = this.o;
        this.f();
        float f5 = this.h - this.e;
        f4 = this.j - this.g;
        f3 = a.a.c(f5 * f5 + f4 * f4);
        f2 = this.T;
        float f6 = 0.0f;
        float f7 = 0.0f;
        if (!(f3 <= 0.05f)) {
            f7 = 1.0f;
            f6 = f3 * 3.0f;
            f2 = (float)Math.atan2(f4, f5) * 180.0f / (float)Math.PI - 90.0f;
        }
        if (!this.s) {
            f7 = 0.0f;
        }
        this.a += (f7 - this.a) * 0.3f;
        for (f5 = f2 - this.T; f5 < -180.0f; f5 += 360.0f) {
        }
        while (f5 >= 180.0f) {
            f5 -= 360.0f;
        }
        this.T += f5 * 0.1f;
        for (f5 = this.n - this.T; f5 < -180.0f; f5 += 360.0f) {
        }
        while (f5 >= 180.0f) {
            f5 -= 360.0f;
        }
        boolean bl2 = bl = f5 < -90.0f || f5 >= 90.0f;
        if (f5 < -75.0f) {
            f5 = -75.0f;
        }
        if (f5 >= 75.0f) {
            f5 = 75.0f;
        }
        this.T = this.n - f5;
        this.T += f5 * 0.1f;
        if (bl) {
            f6 = -f6;
        }
        while (this.n - this.p < -180.0f) {
            this.p -= 360.0f;
        }
        while (this.n - this.p >= 180.0f) {
            this.p += 360.0f;
        }
        while (this.T - this.U < -180.0f) {
            this.U -= 360.0f;
        }
        while (this.T - this.U >= 180.0f) {
            this.U += 360.0f;
        }
        while (this.o - this.q < -180.0f) {
            this.q -= 360.0f;
        }
        while (this.o - this.q >= 180.0f) {
            this.q += 360.0f;
        }
        this.b += f6;
    }

    protected final void a(float f2, float f3) {
        super.a(f2, f3);
    }

    public final void b(int n2) {
        if (this.W <= 0) {
            return;
        }
        this.W += n2;
        if (this.W > 20) {
            this.W = 20;
        }
        this.L = this.S / 2;
    }

    public boolean a(b b2, int n2) {
        if (!this.d.z) {
            return false;
        }
        this.ai = 0;
        if (this.W <= 0) {
            return false;
        }
        this.ag = 1.5f;
        if ((float)this.L > (float)this.S / 2.0f) {
            if (this.X - n2 >= this.W) {
                return false;
            }
            this.W = this.X - n2;
        } else {
            this.X = this.W;
            this.L = this.S;
            this.W -= n2;
            this.Z = 10;
            this.Y = 10;
        }
        this.aa = 0.0f;
        if (b2 != null) {
            float f2 = b2.h - this.h;
            float f3 = b2.j - this.j;
            this.aa = (float)(Math.atan2(f3, f2) * 180.0 / 3.1415927410125732) - this.n;
            float f4 = f3;
            f3 = f2;
            e e2 = this;
            float f5 = a.a.c(f3 * f3 + f4 * f4);
            e2.k /= 2.0f;
            e2.l /= 2.0f;
            e2.m /= 2.0f;
            e2.k -= f3 / f5 * 0.4f;
            e2.l += 0.4f;
            e2.m -= f4 / f5 * 0.4f;
            if (e2.l > 0.4f) {
                e2.l = 0.4f;
            }
        } else {
            this.aa = (int)(Math.random() * 2.0) * 180;
        }
        if (this.W <= 0) {
            this.d.a(this, this.i(), 1.0f, (this.G.nextFloat() - this.G.nextFloat()) * 0.2f + 1.0f);
            this.d(b2);
        } else {
            this.d.a(this, this.h(), 1.0f, (this.G.nextFloat() - this.G.nextFloat()) * 0.2f + 1.0f);
        }
        return true;
    }

    protected String g() {
        return null;
    }

    protected String h() {
        return "random.hurt";
    }

    protected String i() {
        return "random.hurt";
    }

    public void d(b b2) {
        int n2 = this.c();
        if (n2 > 0) {
            int n3 = this.G.nextInt(3);
            for (int i2 = 0; i2 < n3; ++i2) {
                this.a(n2, 1);
            }
        }
    }

    protected int c() {
        return 0;
    }

    protected final void b(float f2) {
        int n2 = (int)Math.ceil(f2 - 3.0f);
        if (n2 > 0) {
            this.a(null, n2);
            n2 = this.d.a((int)this.h, (int)(this.i - 0.2f - this.v), (int)this.j);
            if (n2 > 0) {
                n n3;
                n n4 = n3 = net.minecraft.a.a.b.x.c[n2].aA;
                n n5 = n4;
                n5 = n3;
                this.d.a(this, n3.b(), n4.a * 0.5f, n5.b * 0.75f);
            }
        }
    }

    protected void a(c c2) {
        c2.a("Health", (short)this.W);
        c2.a("HurtTime", (short)this.Y);
        c2.a("DeathTime", (short)this.ab);
        c2.a("AttackTime", (short)this.ac);
    }

    protected void b(c c2) {
        this.W = c2.c("Health");
        if (!c2.a("Health")) {
            this.W = 10;
        }
        this.Y = c2.c("HurtTime");
        this.ab = c2.c("DeathTime");
        this.ac = c2.c("AttackTime");
    }

    protected String a() {
        return "Mob";
    }

    public final boolean r() {
        return !this.u && this.W > 0;
    }

    public void f() {
        float f2;
        float f3;
        float f4;
        b b2;
        ++this.ai;
        if (this.ai > 600 && this.G.nextInt(800) == 0 && (b2 = this.d.i()) != null) {
            float f5;
            float f6 = b2.h - this.h;
            f4 = b2.i - this.i;
            float f7 = b2.j - this.j;
            f6 = f6 * f6 + f4 * f4 + f7 * f7;
            if (f5 < 1024.0f) {
                this.ai = 0;
            } else {
                this.k();
            }
        }
        if (this.W <= 0) {
            this.al = false;
            this.aj = 0.0f;
            this.ak = 0.0f;
            this.Q = 0.0f;
        } else {
            this.e();
        }
        boolean bl = this.l();
        boolean bl2 = this.o();
        if (this.al) {
            if (bl) {
                this.l += 0.04f;
            } else if (bl2) {
                this.l += 0.04f;
            } else if (this.s) {
                e e2 = this;
                this.l = 0.42f;
            }
        }
        this.aj *= 0.98f;
        this.ak *= 0.98f;
        this.Q *= 0.9f;
        f4 = this.ak;
        float f8 = this.aj;
        e e3 = this;
        if (e3.l()) {
            f3 = e3.i;
            e3.e(f8, f4, 0.02f);
            e3.d(e3.k, e3.l, e3.m);
            e3.k *= 0.8f;
            e3.l *= 0.8f;
            e3.m *= 0.8f;
            e3.l = (float)((double)e3.l - 0.02);
            if (e3.t && e3.c(e3.k, e3.l + 0.6f - e3.i + f3, e3.m)) {
                e3.l = 0.3f;
            }
        } else if (e3.o()) {
            f3 = e3.i;
            e3.e(f8, f4, 0.02f);
            e3.d(e3.k, e3.l, e3.m);
            e3.k *= 0.5f;
            e3.l *= 0.5f;
            e3.m *= 0.5f;
            e3.l = (float)((double)e3.l - 0.02);
            if (e3.t && e3.c(e3.k, e3.l + 0.6f - e3.i + f3, e3.m)) {
                e3.l = 0.3f;
            }
        } else {
            e3.e(f8, f4, e3.s ? 0.1f : 0.02f);
            e3.d(e3.k, e3.l, e3.m);
            e3.k *= 0.91f;
            e3.l *= 0.98f;
            e3.m *= 0.91f;
            e3.l = (float)((double)e3.l - 0.08);
            if (e3.s) {
                e3.k *= 0.6f;
                e3.m *= 0.6f;
            }
        }
        e3.af = e3.ag;
        f3 = e3.h - e3.e;
        f8 = e3.j - e3.g;
        f8 = a.a.c(f3 * f3 + f8 * f8) * 4.0f;
        if (f2 > 1.0f) {
            f8 = 1.0f;
        }
        e3.ag += (f8 - e3.ag) * 0.4f;
        e3.ah += e3.ag;
        List list = this.d.a(this, this.r.b(0.2f, 0.0f, 0.2f));
        if (list != null && list.size() > 0) {
            for (int i2 = 0; i2 < list.size(); ++i2) {
                b b3 = (b)list.get(i2);
                if (!b3.p()) continue;
                b3.c(this);
            }
        }
    }

    protected void e() {
        if (this.G.nextFloat() < 0.07f) {
            this.aj = (this.G.nextFloat() - 0.5f) * this.am;
            this.ak = this.G.nextFloat() * this.am;
        }
        boolean bl = this.al = this.G.nextFloat() < 0.01f;
        if (this.G.nextFloat() < 0.04f) {
            this.Q = (this.G.nextFloat() - 0.5f) * 60.0f;
        }
        this.n += this.Q;
        this.o = 0.0f;
        boolean bl2 = this.l();
        boolean bl3 = this.o();
        if (bl2 || bl3) {
            this.al = this.G.nextFloat() < 0.8f;
        }
    }

    public boolean a(float f2, float f3, float f4) {
        this.b(f2, f3 + this.x / 2.0f, f4);
        return this.d.d(this.r) && this.d.a(this.r).size() == 0 && !this.d.b(this.r);
    }
}

