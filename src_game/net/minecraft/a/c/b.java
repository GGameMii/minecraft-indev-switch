/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.c;

import com.a.a.f;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.a.a.b.n;
import net.minecraft.a.a.b.x;
import net.minecraft.a.a.d.c;
import net.minecraft.a.a.g;
import net.minecraft.a.b.k;
import net.minecraft.a.c.e.a;

public abstract class b {
    public boolean c = false;
    protected g d;
    public float e;
    public float f;
    public float g;
    public float h;
    public float i;
    public float j;
    public float k;
    public float l;
    public float m;
    public float n;
    public float o;
    public float p;
    public float q;
    public net.minecraft.a.d.b r;
    public boolean s = false;
    public boolean t = false;
    private boolean a = true;
    public boolean u = false;
    public float v = 0.0f;
    public float w = 0.6f;
    public float x = 1.8f;
    public float y = 0.0f;
    public float z = 0.0f;
    protected boolean A = true;
    private float b = 0.0f;
    private int O = 1;
    public float B;
    public float C;
    public float D;
    private float P = 0.0f;
    public float E = 0.0f;
    public boolean F = false;
    private float Q = 0.0f;
    protected Random G = new Random();
    public int H = 0;
    public int I = 1;
    public int J = 0;
    protected int K = 300;
    private boolean R = false;
    public int L = 0;
    public int M = 300;
    private boolean S = true;
    public String N;

    public b(g g2) {
        this.d = g2;
        this.b(0.0f, 0.0f, 0.0f);
    }

    protected void j() {
        if (this.d == null) {
            return;
        }
        float f2 = (float)this.d.i + 0.5f;
        float f3 = (float)this.d.k + 0.5f;
        for (float f4 = (float)this.d.j; f4 > 0.0f; f4 += 1.0f) {
            this.b(f2, f4, f3);
            if (this.d.a(this.r).size() == 0) break;
        }
        this.m = 0.0f;
        this.l = 0.0f;
        this.k = 0.0f;
        this.n = this.d.l;
        this.o = 0.0f;
    }

    public void k() {
        this.u = true;
    }

    protected void a(float f2, float f3) {
        this.w = f2;
        this.x = f3;
    }

    protected final void b(float f2, float f3, float f4) {
        this.h = f2;
        this.i = f3;
        this.j = f4;
        float f5 = this.w / 2.0f;
        float f6 = this.x / 2.0f;
        this.r = new net.minecraft.a.d.b(f2 - f5, f3 - f6, f4 - f5, f2 + f5, f3 + f6, f4 + f5);
    }

    public void b_() {
        ++this.H;
        this.y = this.z;
        this.e = this.h;
        this.f = this.i;
        this.g = this.j;
        this.q = this.o;
        this.p = this.n;
        if (this.l()) {
            if (!this.R && !this.S) {
                float f2;
                float f3;
                float f4;
                float f5 = a.a.c(this.k * this.k * 0.2f + this.l * this.l + this.m * this.m * 0.2f) * 0.2f;
                if (f4 > 1.0f) {
                    f5 = 1.0f;
                }
                this.d.a(this, "random.splash", f5, 1.0f + (this.G.nextFloat() - this.G.nextFloat()) * 0.4f);
                f5 = (int)this.r.b;
                int n2 = 0;
                while ((float)n2 < 1.0f + this.w * 20.0f) {
                    f3 = (this.G.nextFloat() * 2.0f - 1.0f) * this.w;
                    f2 = (this.G.nextFloat() * 2.0f - 1.0f) * this.w;
                    this.d.a("bubble", this.h + f3, f5 + 1.0f, this.j + f2, this.k, this.l - this.G.nextFloat() * 0.2f, this.m);
                    ++n2;
                }
                n2 = 0;
                while ((float)n2 < 1.0f + this.w * 20.0f) {
                    f3 = (this.G.nextFloat() * 2.0f - 1.0f) * this.w;
                    f2 = (this.G.nextFloat() * 2.0f - 1.0f) * this.w;
                    this.d.a("splash", this.h + f3, f5 + 1.0f, this.j + f2, this.k, this.l, this.m);
                    ++n2;
                }
            }
            this.b = 0.0f;
            this.R = true;
            this.J = 0;
        } else {
            this.R = false;
        }
        if (this.J > 0) {
            if (this.J % 20 == 0) {
                this.a(null, 1);
            }
            --this.J;
        }
        if (this.o()) {
            this.a(null, 10);
            this.J = 600;
        }
        if (this.h < -8.0f) {
            float f6 = -(this.h + 8.0f);
            this.k += f6 * 0.001f;
        }
        if (this.j < -8.0f) {
            float f7 = -(this.j + 8.0f);
            this.m += f7 * 0.001f;
        }
        if (this.h > (float)this.d.a + 8.0f) {
            float f8 = this.h - (float)this.d.a + 8.0f;
            this.k -= f8 * 0.001f;
        }
        if (this.j > (float)this.d.b + 8.0f) {
            float f9 = this.j - (float)this.d.b + 8.0f;
            this.m -= f9 * 0.001f;
        }
        this.S = false;
    }

    public final boolean c(float f2, float f3, float f4) {
        float f5 = f4;
        f4 = f3;
        f3 = f2;
        net.minecraft.a.d.b b2 = this.r;
        b2 = new net.minecraft.a.d.b(b2.a + f5, b2.b + f4, b2.c + f5, b2.d + f3, b2.e + f4, b2.f + f5);
        ArrayList arrayList = this.d.a(b2);
        if (arrayList.size() > 0) {
            return false;
        }
        return !this.d.b(b2);
    }

    public final void d(float f2, float f3, float f4) {
        int n2;
        int n3;
        if (this.F) {
            this.r.c(f2, f3, f4);
            this.h = (this.r.a + this.r.d) / 2.0f;
            this.i = this.r.b + this.v - this.P;
            this.j = (this.r.c + this.r.f) / 2.0f;
            return;
        }
        float f5 = this.h;
        float f6 = this.j;
        float f7 = f2;
        float f8 = f3;
        float f9 = f4;
        net.minecraft.a.d.b b2 = this.r.a();
        ArrayList arrayList = this.d.a(this.r.a(f2, f3, f4));
        for (n3 = 0; n3 < arrayList.size(); ++n3) {
            f3 = ((net.minecraft.a.d.b)arrayList.get(n3)).b(this.r, f3);
        }
        this.r.c(0.0f, f3, 0.0f);
        if (!this.a && f8 != f3) {
            f4 = 0.0f;
            f3 = 0.0f;
            f2 = 0.0f;
        }
        n3 = this.s || f8 != f3 && f8 < 0.0f ? 1 : 0;
        for (n2 = 0; n2 < arrayList.size(); ++n2) {
            f2 = ((net.minecraft.a.d.b)arrayList.get(n2)).a(this.r, f2);
        }
        this.r.c(f2, 0.0f, 0.0f);
        if (!this.a && f7 != f2) {
            f4 = 0.0f;
            f3 = 0.0f;
            f2 = 0.0f;
        }
        for (n2 = 0; n2 < arrayList.size(); ++n2) {
            f4 = ((net.minecraft.a.d.b)arrayList.get(n2)).c(this.r, f4);
        }
        this.r.c(0.0f, 0.0f, f4);
        if (!this.a && f9 != f4) {
            f4 = 0.0f;
            f3 = 0.0f;
            f2 = 0.0f;
        }
        if (this.E > 0.0f && n3 != 0 && this.P < 0.05f && (f7 != f2 || f9 != f4)) {
            int n4;
            float f10 = f2;
            float f11 = f3;
            float f12 = f4;
            f2 = f7;
            f3 = this.E;
            f4 = f9;
            net.minecraft.a.d.b b3 = this.r.a();
            this.r = b2.a();
            arrayList = this.d.a(this.r.a(f2, f3, f4));
            for (n4 = 0; n4 < arrayList.size(); ++n4) {
                f3 = ((net.minecraft.a.d.b)arrayList.get(n4)).b(this.r, f3);
            }
            this.r.c(0.0f, f3, 0.0f);
            if (!this.a && f8 != f3) {
                f4 = 0.0f;
                f3 = 0.0f;
                f2 = 0.0f;
            }
            for (n4 = 0; n4 < arrayList.size(); ++n4) {
                f2 = ((net.minecraft.a.d.b)arrayList.get(n4)).a(this.r, f2);
            }
            this.r.c(f2, 0.0f, 0.0f);
            if (!this.a && f7 != f2) {
                f4 = 0.0f;
                f3 = 0.0f;
                f2 = 0.0f;
            }
            for (n4 = 0; n4 < arrayList.size(); ++n4) {
                f4 = ((net.minecraft.a.d.b)arrayList.get(n4)).c(this.r, f4);
            }
            this.r.c(0.0f, 0.0f, f4);
            if (!this.a && f9 != f4) {
                f4 = 0.0f;
                f3 = 0.0f;
                f2 = 0.0f;
            }
            if (f10 * f10 + f12 * f12 >= f2 * f2 + f4 * f4) {
                f2 = f10;
                f3 = f11;
                f4 = f12;
                this.r = b3.a();
            } else {
                this.P = (float)((double)this.P + 0.5);
            }
        }
        this.h = (this.r.a + this.r.d) / 2.0f;
        this.i = this.r.b + this.v - this.P;
        this.j = (this.r.c + this.r.f) / 2.0f;
        this.t = f7 != f2 || f9 != f4;
        boolean bl = this.s = f8 != f3 && f8 < 0.0f;
        if (this.s) {
            if (this.b > 0.0f) {
                this.b(this.b);
                this.b = 0.0f;
            }
        } else if (f3 < 0.0f) {
            this.b -= f3;
        }
        if (f7 != f2) {
            this.k = 0.0f;
        }
        if (f8 != f3) {
            this.l = 0.0f;
        }
        if (f9 != f4) {
            this.m = 0.0f;
        }
        float f13 = this.h - f5;
        float f14 = this.j - f6;
        this.z = (float)((double)this.z + (double)a.a.c(f13 * f13 + f14 * f14) * 0.6);
        if (this.A) {
            int n5 = (int)this.h;
            int n6 = (int)(this.i - 0.2f - this.v);
            int n7 = (int)this.j;
            int n8 = this.d.a(n5, n6, n7);
            if (this.z > (float)this.O && n8 > 0) {
                ++this.O;
                n n9 = net.minecraft.a.a.b.x.c[n8].aA;
                if (!net.minecraft.a.a.b.x.c[n8].aC.d()) {
                    n n10 = n9;
                    n n11 = n10;
                    n11 = n9;
                    this.d.a(this, n9.b(), n10.a * 0.15f, n11.b);
                }
                net.minecraft.a.a.b.x.c[n8].g(this.d, n5, n6, n7);
            }
        }
        this.P *= 0.4f;
        boolean bl2 = this.l();
        if (this.d.c(this.r)) {
            this.a(1);
            if (!bl2) {
                ++this.J;
                if (this.J == 0) {
                    this.J = 300;
                }
            }
        } else if (this.J <= 0) {
            this.J = -this.I;
        }
        if (bl2 && this.J > 0) {
            this.d.a(this, "random.fizz", 0.7f, 1.6f + (this.G.nextFloat() - this.G.nextFloat()) * 0.4f);
            this.J = -this.I;
        }
    }

    protected void a(int n2) {
        this.a(null, 1);
    }

    protected void b(float f2) {
    }

    public final boolean l() {
        return this.d.a(this.r.b(0.0f, -0.4f, 0.0f), net.minecraft.a.a.d.c.f);
    }

    public final boolean m() {
        int n2 = this.d.a((int)this.h, (int)(this.i + this.n()), (int)this.j);
        if (n2 != 0) {
            return net.minecraft.a.a.b.x.c[n2].aC == net.minecraft.a.a.d.c.f;
        }
        return false;
    }

    protected float n() {
        return 0.0f;
    }

    public final boolean o() {
        return this.d.a(this.r.b(0.0f, -0.4f, 0.0f), net.minecraft.a.a.d.c.g);
    }

    public final void e(float f2, float f3, float f4) {
        float f5;
        float f6 = a.a.c(f2 * f2 + f3 * f3);
        if (f5 < 0.01f) {
            return;
        }
        if (f6 < 1.0f) {
            f6 = 1.0f;
        }
        f6 = f4 / f6;
        f2 *= f6;
        f3 *= f6;
        f4 = a.a.a(this.n * (float)Math.PI / 180.0f);
        f6 = a.a.b(this.n * (float)Math.PI / 180.0f);
        this.k += f2 * f6 - f3 * f4;
        this.m += f3 * f6 + f2 * f4;
    }

    public float a(float f2) {
        int n2 = (int)this.h;
        int n3 = (int)(this.i + this.v / 2.0f);
        int n4 = (int)this.j;
        return this.d.c(n2, n3, n4);
    }

    public final void a(g g2) {
        this.d = g2;
    }

    public final void b(float f2, float f3, float f4, float f5, float f6) {
        this.e = this.h = f2;
        this.f = this.i = f3 + this.v;
        this.g = this.j = f4;
        this.n = f5;
        this.o = f6;
        this.b(this.h, this.i, this.j);
    }

    public final float b(b b2) {
        float f2 = this.h - b2.h;
        float f3 = this.i - b2.i;
        float f4 = this.j - b2.j;
        return f2 * f2 + f3 * f3 + f4 * f4;
    }

    public void a(a a2) {
    }

    public final void c(b b2) {
        float f2;
        float f3 = b2.h - this.h;
        float f4 = b2.j - this.j;
        float f5 = f3 * f3 + f4 * f4;
        if (f2 >= 0.01f) {
            f5 = a.a.c(f5);
            f3 /= f5;
            f4 /= f5;
            f3 /= f5;
            f4 /= f5;
            f3 *= 0.05f;
            f4 *= 0.05f;
            this.a(-f3, 0.0f, -f4);
            b2.a(f3, 0.0f, f4);
        }
    }

    private void a(float f2, float f3, float f4) {
        this.k += f2;
        this.l = this.l;
        this.m += f4;
    }

    public boolean a(b b2, int n2) {
        return false;
    }

    public boolean d() {
        return false;
    }

    public boolean p() {
        return false;
    }

    public String q() {
        return null;
    }

    public final void c(com.a.a.c c2) {
        String string = this.a();
        if (this.u || string == null) {
            return;
        }
        c2.a("id", string);
        c2.a("Pos", net.minecraft.a.c.b.a(this.h, this.i, this.j));
        c2.a("Motion", net.minecraft.a.c.b.a(this.k, this.l, this.m));
        c2.a("Rotation", net.minecraft.a.c.b.a(this.n, this.o));
        c2.a("FallDistance", this.b);
        c2.a("Fire", (short)this.J);
        c2.a("Air", (short)this.M);
        this.a(c2);
    }

    public final void d(com.a.a.c c2) {
        f f2 = c2.j("Pos");
        f f3 = c2.j("Motion");
        f f4 = c2.j("Rotation");
        this.h = ((com.a.a.b)f2.a((int)0)).a;
        this.i = ((com.a.a.b)f2.a((int)1)).a;
        this.j = ((com.a.a.b)f2.a((int)2)).a;
        this.k = ((com.a.a.b)f3.a((int)0)).a;
        this.l = ((com.a.a.b)f3.a((int)1)).a;
        this.m = ((com.a.a.b)f3.a((int)2)).a;
        this.n = ((com.a.a.b)f4.a((int)0)).a;
        this.o = ((com.a.a.b)f4.a((int)1)).a;
        this.b = c2.f("FallDistance");
        this.J = c2.c("Fire");
        this.M = c2.c("Air");
        this.b(this.h, this.i, this.j, this.n, this.o);
        this.b(c2);
    }

    protected abstract String a();

    protected abstract void b(com.a.a.c var1);

    protected abstract void a(com.a.a.c var1);

    private static f a(float ... fArray) {
        f f2 = new f();
        for (float f3 : fArray) {
            f2.a(new com.a.a.b(f3));
        }
        return f2;
    }

    public float c_() {
        return this.x / 2.0f;
    }

    public final net.minecraft.a.c.c.b a(int n2, int n3) {
        return this.a(n2, 1, 0.0f);
    }

    public final net.minecraft.a.c.c.b a(int n2, int n3, float f2) {
        net.minecraft.a.c.c.b b2 = new net.minecraft.a.c.c.b(this.d, this.h, this.i + f2, this.j, new k(n2, n3));
        new net.minecraft.a.c.c.b(this.d, this.h, this.i + f2, this.j, new k(n2, n3)).O = 10;
        this.d.a(b2);
        return b2;
    }

    public boolean r() {
        return !this.u;
    }
}

