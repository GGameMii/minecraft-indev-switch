/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.c;

import a.a;
import net.minecraft.a.a.g;
import net.minecraft.a.c.b;
import net.minecraft.a.c.e;

public class c
extends e {
    private net.minecraft.a.a.a.b a;
    protected b b;
    protected boolean O = false;

    public c(g g2) {
        super(g2);
    }

    protected void e() {
        float f2;
        int n2;
        float f3;
        float f4;
        Object object;
        this.O = false;
        if (this.b == null) {
            this.b = this.b();
            if (this.b != null) {
                this.a = this.d.C.a(this, this.b, 16.0f);
            }
        } else if (!this.b.r()) {
            this.b = null;
        } else {
            c c2 = this;
            object = this.b;
            f4 = ((b)object).h - c2.h;
            f3 = ((b)object).i - c2.i;
            float f5 = ((b)object).j - c2.j;
            float f6 = a.a.c(f4 * f4 + f3 * f3 + f5 * f5);
            if (this.d.a(new net.minecraft.a.d.a(this.h, this.i + this.n(), this.j), new net.minecraft.a.d.a(this.b.h, this.b.i + this.b.n(), this.b.j)) == null) {
                this.a(this.b, f6);
            }
        }
        if (this.O) {
            this.aj = 0.0f;
            this.ak = 0.0f;
            this.al = false;
            return;
        }
        if (this.b != null && (this.a == null || this.G.nextInt(20) == 0)) {
            this.a = this.d.C.a(this, this.b, 16.0f);
        } else if (this.a == null || this.G.nextInt(100) == 0) {
            int n3 = -1;
            n2 = -1;
            int n4 = -1;
            f2 = -99999.0f;
            for (int i2 = 0; i2 < 200; ++i2) {
                float f7;
                int n5 = (int)(this.h + (float)this.G.nextInt(21) - 10.0f);
                int n6 = (int)(this.i + (float)this.G.nextInt(9) - 4.0f);
                int n7 = (int)(this.j + (float)this.G.nextInt(21) - 10.0f);
                float f8 = this.a(n5, n6, n7);
                if (!(f7 > f2)) continue;
                f2 = f8;
                n3 = n5;
                n2 = n6;
                n4 = n7;
            }
            if (n3 > 0) {
                this.a = this.d.C.a((b)this, n3, n2, n4, 16.0f);
            }
        }
        boolean bl = this.l();
        n2 = this.o() ? 1 : 0;
        if (this.a == null || this.G.nextInt(100) == 0) {
            super.e();
            this.a = null;
            return;
        }
        net.minecraft.a.d.a a2 = this.a.a(this);
        f2 = this.w * 2.0f;
        while (a2 != null) {
            f3 = this.j;
            f4 = this.i;
            float f9 = this.h;
            object = a2;
            float f10 = f3 - ((net.minecraft.a.d.a)object).c;
            if (!((f9 -= ((net.minecraft.a.d.a)object).a) * f9 + (f4 -= ((net.minecraft.a.d.a)object).b) * f4 + f10 * f10 < f2 * f2) || !(a2.b <= this.i)) break;
            this.a.a();
            if (this.a.b()) {
                a2 = null;
                this.a = null;
                continue;
            }
            a2 = this.a.a(this);
        }
        this.al = false;
        if (a2 != null) {
            float f11 = a2.a - this.h;
            float f12 = a2.c - this.j;
            f4 = a2.b - this.i;
            this.n = (float)(Math.atan2(f12, f11) * 180.0 / 3.1415927410125732) - 90.0f;
            this.ak = this.am;
            if (f4 > 0.0f) {
                this.al = true;
            }
        }
        if (this.G.nextFloat() < 0.8f && (bl || n2 != 0)) {
            this.al = true;
        }
    }

    protected void a(b b2, float f2) {
    }

    protected float a(int n2, int n3, int n4) {
        return 0.0f;
    }

    protected b b() {
        return null;
    }

    public boolean a(float f2, float f3, float f4) {
        return super.a(f2, f3, f4) && this.a((int)f2, (int)f3, (int)f4) >= 0.0f;
    }
}

