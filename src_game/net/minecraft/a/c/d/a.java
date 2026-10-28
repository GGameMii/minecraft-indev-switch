/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.c.d;

import com.a.a.c;
import java.util.List;
import net.minecraft.a.a.g;
import net.minecraft.a.b.i;
import net.minecraft.a.b.k;
import net.minecraft.a.c.b;
import net.minecraft.a.c.e;

public class a
extends b {
    private int b = -1;
    private int O = -1;
    private int P = -1;
    private int Q = 0;
    private boolean R = false;
    public int a = 0;
    private e S;
    private int T;
    private int U = 0;

    public a(g g2, e e2) {
        super(g2);
        this.S = e2;
        this.a(0.5f, 0.5f);
        this.b(e2.h, e2.i, e2.j, e2.n, e2.o);
        this.h -= a.a.b(this.n / 180.0f * (float)Math.PI) * 0.16f;
        this.i -= 0.1f;
        this.j -= a.a.a(this.n / 180.0f * (float)Math.PI) * 0.16f;
        this.b(this.h, this.i, this.j);
        this.v = 0.0f;
        this.k = -a.a.a(this.n / 180.0f * (float)Math.PI) * a.a.b(this.o / 180.0f * (float)Math.PI);
        this.m = a.a.b(this.n / 180.0f * (float)Math.PI) * a.a.b(this.o / 180.0f * (float)Math.PI);
        this.l = -a.a.a(this.o / 180.0f * (float)Math.PI);
        this.a(this.k, this.l, this.m, 1.5f, 1.0f);
    }

    public final void a(float f2, float f3, float f4, float f5, float f6) {
        float f7 = a.a.c(f2 * f2 + f3 * f3 + f4 * f4);
        f2 /= f7;
        f3 /= f7;
        f4 /= f7;
        f2 = (float)((double)f2 + this.G.nextGaussian() * (double)0.0075f * (double)f6);
        f3 = (float)((double)f3 + this.G.nextGaussian() * (double)0.0075f * (double)f6);
        f4 = (float)((double)f4 + this.G.nextGaussian() * (double)0.0075f * (double)f6);
        this.k = f2 *= f5;
        this.l = f3 *= f5;
        this.m = f4 *= f5;
        f5 = a.a.c(f2 * f2 + f4 * f4);
        this.p = this.n = (float)(Math.atan2(f2, f4) * 180.0 / 3.1415927410125732);
        this.q = this.o = (float)(Math.atan2(f3, f5) * 180.0 / 3.1415927410125732);
        this.T = 0;
    }

    /*
     * Enabled aggressive block sorting
     */
    public final void b_() {
        block19: {
            super.b_();
            if (this.a > 0) {
                --this.a;
            }
            if (this.R) {
                int n2 = this.d.a(this.b, this.O, this.P);
                if (n2 != this.Q) {
                    this.R = false;
                    this.k *= this.G.nextFloat() * 0.2f;
                    this.l *= this.G.nextFloat() * 0.2f;
                    this.m *= this.G.nextFloat() * 0.2f;
                    this.T = 0;
                    this.U = 0;
                    break block19;
                } else {
                    ++this.T;
                    if (this.T == 1200) {
                        this.k();
                    }
                    return;
                }
            }
            ++this.U;
        }
        net.minecraft.a.d.a a2 = new net.minecraft.a.d.a(this.h, this.i, this.j);
        net.minecraft.a.d.a a3 = new net.minecraft.a.d.a(this.h + this.k, this.i + this.l, this.j + this.m);
        net.minecraft.a.d.c c2 = this.d.a(a2, a3);
        a2 = new net.minecraft.a.d.a(this.h, this.i, this.j);
        a3 = new net.minecraft.a.d.a(this.h + this.k, this.i + this.l, this.j + this.m);
        if (c2 != null) {
            a3 = new net.minecraft.a.d.a(c2.f.a, c2.f.b, c2.f.c);
        }
        b b2 = null;
        List list = this.d.r.a(this, this.r.a(this.k, this.l, this.m).b(1.0f, 1.0f, 1.0f));
        float f2 = 0.0f;
        for (int i2 = 0; i2 < list.size(); ++i2) {
            float f3;
            b b3 = (b)list.get(i2);
            if (!b3.d() || b3 == this.S && this.U < 5) continue;
            Object object = b3.r.b(0.3f, 0.3f, 0.3f);
            if ((object = ((net.minecraft.a.d.b)object).a(a2, a3)) == null || !((f3 = a2.b(((net.minecraft.a.d.c)object).f)) < f2) && f2 != 0.0f) continue;
            b2 = b3;
            f2 = f3;
        }
        if (b2 != null) {
            c2 = new net.minecraft.a.d.c(b2);
        }
        if (c2 != null) {
            if (c2.g != null) {
                if (c2.g.a(this, 4)) {
                    this.d.a(this, "random.drr", 1.0f, 1.2f / (this.G.nextFloat() * 0.2f + 0.9f));
                    this.k();
                } else {
                    this.k *= -0.1f;
                    this.l *= -0.1f;
                    this.m *= -0.1f;
                    this.n += 180.0f;
                    this.p += 180.0f;
                    this.U = 0;
                }
            } else {
                this.b = c2.b;
                this.O = c2.c;
                this.P = c2.d;
                this.Q = this.d.a(this.b, this.O, this.P);
                this.k = c2.f.a - this.h;
                this.l = c2.f.b - this.i;
                this.m = c2.f.c - this.j;
                float f4 = a.a.c(this.k * this.k + this.l * this.l + this.m * this.m);
                this.h -= this.k / f4 * 0.05f;
                this.i -= this.l / f4 * 0.05f;
                this.j -= this.m / f4 * 0.05f;
                this.d.a(this, "random.drr", 1.0f, 1.2f / (this.G.nextFloat() * 0.2f + 0.9f));
                this.R = true;
                this.a = 7;
            }
        }
        this.h += this.k;
        this.i += this.l;
        this.j += this.m;
        float f5 = a.a.c(this.k * this.k + this.m * this.m);
        this.n = (float)(Math.atan2(this.k, this.m) * 180.0 / 3.1415927410125732);
        this.o = (float)(Math.atan2(this.l, f5) * 180.0 / 3.1415927410125732);
        while (this.o - this.q < -180.0f) {
            this.q -= 360.0f;
        }
        while (this.o - this.q >= 180.0f) {
            this.q += 360.0f;
        }
        while (this.n - this.p < -180.0f) {
            this.p -= 360.0f;
        }
        while (this.n - this.p >= 180.0f) {
            this.p += 360.0f;
        }
        this.o = this.q + (this.o - this.q) * 0.2f;
        this.n = this.p + (this.n - this.p) * 0.2f;
        float f6 = 0.99f;
        if (this.l()) {
            for (int i3 = 0; i3 < 4; ++i3) {
                this.d.a("bubble", this.h - this.k * 0.25f, this.i - this.l * 0.25f, this.j - this.m * 0.25f, this.k, this.l, this.m);
            }
            f6 = 0.8f;
        }
        this.k *= f6;
        this.l *= f6;
        this.m *= f6;
        this.l -= 0.03f;
        this.b(this.h, this.i, this.j);
    }

    protected final void a(c c2) {
        c2.a("xTile", (short)this.b);
        c2.a("yTile", (short)this.O);
        c2.a("zTile", (short)this.P);
        c2.a("inTile", (byte)this.Q);
        c2.a("shake", (byte)this.a);
        c2.a("inGround", (byte)(this.R ? 1 : 0));
    }

    protected final void b(c c2) {
        this.b = c2.c("xTile");
        this.O = c2.c("yTile");
        this.P = c2.c("zTile");
        this.Q = c2.b("inTile") & 0xFF;
        this.a = c2.b("shake") & 0xFF;
        this.R = c2.b("inGround") == 1;
    }

    protected final String a() {
        return "Arrow";
    }

    public final void a(net.minecraft.a.c.e.a a2) {
        if (this.R && this.S == a2 && this.a <= 0 && a2.b.a(new k(net.minecraft.a.b.i.h.ap, 1))) {
            this.d.a(this, "random.pop", 0.2f, ((this.G.nextFloat() - this.G.nextFloat()) * 0.7f + 1.0f) * 2.0f);
            a2.a(this);
            this.k();
        }
    }

    public final float c_() {
        return 0.0f;
    }
}

