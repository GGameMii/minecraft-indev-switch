/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.c.c;

import com.a.a.c;
import net.minecraft.a.a.b.x;
import net.minecraft.a.a.g;
import net.minecraft.a.b.k;
import net.minecraft.a.c.e.a;

public class b
extends net.minecraft.a.c.b {
    public k a;
    private int Q;
    public int b = 0;
    public int O;
    private int R = 5;
    public float P = (float)(Math.random() * Math.PI * 2.0);

    public b(g g2, float f2, float f3, float f4, k k2) {
        super(g2);
        this.a(0.25f, 0.25f);
        this.v = this.x / 2.0f;
        this.b(f2, f3, f4);
        this.a = k2;
        this.n = (float)(Math.random() * 360.0);
        this.k = (float)(Math.random() * (double)0.2f - (double)0.1f);
        this.l = 0.2f;
        this.m = (float)(Math.random() * (double)0.2f - (double)0.1f);
        this.A = false;
    }

    public b(g g2) {
        super(g2);
    }

    public final void b_() {
        super.b_();
        if (this.O > 0) {
            --this.O;
        }
        this.e = this.h;
        this.f = this.i;
        this.g = this.j;
        this.l -= 0.04f;
        if (this.d.f((int)this.h, (int)this.i, (int)this.j) == net.minecraft.a.a.d.c.g) {
            this.l = 0.2f;
            this.k = (this.G.nextFloat() - this.G.nextFloat()) * 0.2f;
            this.m = (this.G.nextFloat() - this.G.nextFloat()) * 0.2f;
            this.d.a(this, "random.fizz", 0.4f, 2.0f + this.G.nextFloat() * 0.4f);
        }
        float f2 = this.j;
        float f3 = this.i;
        float f4 = this.h;
        b b2 = this;
        int n2 = (int)f4;
        int n3 = (int)f3;
        int n4 = (int)f2;
        f4 -= (float)n2;
        f3 -= (float)n3;
        f2 -= (float)n4;
        if (net.minecraft.a.a.b.x.e[b2.d.a(n2, n3, n4)]) {
            boolean bl = !net.minecraft.a.a.b.x.e[b2.d.a(n2 - 1, n3, n4)];
            boolean bl2 = !net.minecraft.a.a.b.x.e[b2.d.a(n2 + 1, n3, n4)];
            boolean bl3 = !net.minecraft.a.a.b.x.e[b2.d.a(n2, n3 - 1, n4)];
            boolean bl4 = !net.minecraft.a.a.b.x.e[b2.d.a(n2, n3 + 1, n4)];
            boolean bl5 = !net.minecraft.a.a.b.x.e[b2.d.a(n2, n3, n4 - 1)];
            n2 = !net.minecraft.a.a.b.x.e[b2.d.a(n2, n3, n4 + 1)] ? 1 : 0;
            n3 = -1;
            float f5 = 9999.0f;
            if (bl && f4 < 9999.0f) {
                f5 = f4;
                n3 = 0;
            }
            if (bl2 && 1.0f - f4 < f5) {
                f5 = 1.0f - f4;
                n3 = 1;
            }
            if (bl3 && f3 < f5) {
                f5 = f3;
                n3 = 2;
            }
            if (bl4 && 1.0f - f3 < f5) {
                f5 = 1.0f - f3;
                n3 = 3;
            }
            if (bl5 && f2 < f5) {
                f5 = f2;
                n3 = 4;
            }
            if (n2 != 0 && 1.0f - f2 < f5) {
                n3 = 5;
            }
            f4 = b2.G.nextFloat() * 0.2f + 0.1f;
            if (n3 == 0) {
                b2.k = -f4;
            }
            if (n3 == 1) {
                b2.k = f4;
            }
            if (n3 == 2) {
                b2.l = -f4;
            }
            if (n3 == 3) {
                b2.l = f4;
            }
            if (n3 == 4) {
                b2.m = -f4;
            }
            if (n3 == 5) {
                b2.m = f4;
            }
        }
        this.d(this.k, this.l, this.m);
        this.k *= 0.98f;
        this.l *= 0.98f;
        this.m *= 0.98f;
        if (this.s) {
            this.k *= 0.7f;
            this.m *= 0.7f;
            this.l *= -0.5f;
        }
        ++this.Q;
        ++this.b;
        if (this.b >= 6000) {
            this.k();
        }
    }

    protected final void a(int n2) {
        this.a(null, 1);
    }

    public final boolean a(net.minecraft.a.c.b b2, int n2) {
        this.R -= n2;
        if (this.R <= 0) {
            this.k();
        }
        return false;
    }

    protected final void a(c c2) {
        c2.a("Health", (short)((byte)this.R));
        c2.a("Age", (short)this.b);
        c2.a("Item", this.a.a(new c()));
    }

    protected final void b(c c2) {
        this.R = c2.c("Health") & 0xFF;
        this.b = c2.c("Age");
        c2 = c2.i("Item");
        this.a = new k(c2);
    }

    protected final String a() {
        return "Item";
    }

    public final void a(a a2) {
        if (this.O == 0 && a2.b.a(this.a)) {
            this.d.a(this, "random.pop", 0.2f, ((this.G.nextFloat() - this.G.nextFloat()) * 0.7f + 1.0f) * 2.0f);
            a2.a(this);
            this.k();
        }
    }
}

