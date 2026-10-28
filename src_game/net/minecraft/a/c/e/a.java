/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.c.e;

import java.util.List;
import net.minecraft.a.a.b.x;
import net.minecraft.a.a.d.c;
import net.minecraft.a.a.g;
import net.minecraft.a.b.i;
import net.minecraft.a.b.j;
import net.minecraft.a.b.k;
import net.minecraft.a.c.e;
import net.minecraft.a.c.e.b;

public class a
extends e {
    public b b = new b(this);
    public byte O = 0;
    public int P = 0;
    public float Q;
    public float R;
    private int a = 0;

    public a(g g2) {
        super(g2);
        if (g2 != null) {
            g2.y = this;
            g2.b(this);
        }
        this.b(g2.i, g2.j, g2.k, 0.0f, 0.0f);
        this.v = 1.62f;
        this.W = 20;
        this.I = 20;
        this.V = "/char.png";
    }

    public final void j() {
        this.v = 1.62f;
        this.a(0.6f, 1.8f);
        super.j();
        if (this.d != null) {
            this.d.y = this;
        }
        this.W = 20;
        this.ab = 0;
    }

    public void f() {
        List list;
        this.d.a(this.h, this.i, this.j, "calm", 0.0f);
        if (this.d.E == 0 && this.W < 20 && this.H % 20 << 2 == 0) {
            this.b(1);
        }
        Object object = this.b;
        for (int i2 = 0; i2 < ((b)object).a.length; ++i2) {
            if (((b)object).a[i2] == null || ((b)object).a[i2].b <= 0) continue;
            --((b)object).a[i2].b;
        }
        this.Q = this.R;
        super.f();
        float f2 = a.a.c(this.k * this.k + this.m * this.m);
        float f3 = (float)Math.atan(-this.l * 0.2f) * 15.0f;
        if (f2 > 0.1f) {
            f2 = 0.1f;
        }
        if (!this.s || this.W <= 0) {
            f2 = 0.0f;
        }
        if (this.s || this.W <= 0) {
            f3 = 0.0f;
        }
        this.R += (f2 - this.R) * 0.4f;
        this.ae += (f3 - this.ae) * 0.8f;
        if (this.W > 0 && (list = this.d.a(this, this.r.b(1.0f, 0.0f, 1.0f))) != null) {
            for (int i3 = 0; i3 < list.size(); ++i3) {
                net.minecraft.a.c.b b2 = (net.minecraft.a.c.b)list.get(i3);
                object = this;
                b2.a((a)object);
            }
        }
    }

    public final void d(net.minecraft.a.c.b b2) {
        this.a(0.2f, 0.2f);
        this.b(this.h, this.i, this.j);
        this.l = 0.1f;
        if (b2 != null) {
            this.k = -a.a.b((this.aa + this.n) * (float)Math.PI / 180.0f) * 0.1f;
            this.m = -a.a.a((this.aa + this.n) * (float)Math.PI / 180.0f) * 0.1f;
        } else {
            this.m = 0.0f;
            this.k = 0.0f;
        }
        this.v = 0.1f;
    }

    public final void k() {
    }

    public final void a(k k2) {
        this.a(k2, false);
    }

    public final void a(k object, boolean bl) {
        if (object == null) {
            return;
        }
        object = new net.minecraft.a.c.c.b(this.d, this.h, this.i - 0.3f, this.j, (k)object);
        v0.O = 40;
        ((net.minecraft.a.c.c.b)object).k = -a.a.a(this.n / 180.0f * (float)Math.PI) * a.a.b(this.o / 180.0f * (float)Math.PI) * 0.3f;
        ((net.minecraft.a.c.c.b)object).m = a.a.b(this.n / 180.0f * (float)Math.PI) * a.a.b(this.o / 180.0f * (float)Math.PI) * 0.3f;
        ((net.minecraft.a.c.c.b)object).l = -a.a.a(this.o / 180.0f * (float)Math.PI) * 0.3f + 0.1f;
        float f2 = this.G.nextFloat() * (float)Math.PI * 2.0f;
        float f3 = 0.02f * this.G.nextFloat();
        ((net.minecraft.a.c.c.b)object).k = (float)((double)((net.minecraft.a.c.c.b)object).k + Math.cos(f2) * (double)f3);
        ((net.minecraft.a.c.c.b)object).l += (this.G.nextFloat() - this.G.nextFloat()) * 0.1f;
        ((net.minecraft.a.c.c.b)object).m = (float)((double)((net.minecraft.a.c.c.b)object).m + Math.sin(f2) * (double)f3);
        this.d.a((net.minecraft.a.c.b)object);
    }

    public final boolean a(x object) {
        x x2 = object;
        object = this.b;
        if (x2.aC != net.minecraft.a.a.d.c.d && x2.aC != net.minecraft.a.a.d.c.e) {
            return true;
        }
        if ((object = ((b)object).a(((b)object).c)) != null) {
            return net.minecraft.a.b.i.b[((k)object).c].a(x2);
        }
        return false;
    }

    protected void b(com.a.a.c c2) {
        super.b(c2);
    }

    protected void a(com.a.a.c c2) {
        super.a(c2);
    }

    protected String a() {
        return null;
    }

    public void a(net.minecraft.a.b b2) {
    }

    public void g_() {
    }

    public void a(net.minecraft.a.c.b b2) {
    }

    protected final float n() {
        return 0.12f;
    }

    public final boolean a(net.minecraft.a.c.b b2, int n2) {
        if (!this.d.z) {
            return false;
        }
        this.ai = 0;
        if (this.W <= 0) {
            return false;
        }
        if ((float)this.L > (float)this.S / 2.0f) {
            return false;
        }
        if (b2 instanceof net.minecraft.a.c.a.e || b2 instanceof net.minecraft.a.c.d.a) {
            if (this.d.E == 0) {
                n2 = 0;
            }
            if (this.d.E == 1) {
                n2 = n2 / 3 + 1;
            }
            if (this.d.E == 3) {
                n2 = n2 * 3 / 2;
            }
        }
        int n3 = 25 - this.b.e();
        n3 = n2 * n3 + this.a;
        int n4 = n2;
        b b3 = this.b;
        for (int i2 = 0; i2 < b3.b.length; ++i2) {
            if (b3.b[i2] == null || !(b3.b[i2].a() instanceof j)) continue;
            b3.b[i2].b(n4);
            if (b3.b[i2].a != 0) continue;
            b3.b[i2] = null;
        }
        int n5 = n3 / 25;
        this.a = n3 % 25;
        if (n5 == 0) {
            return false;
        }
        return super.a(b2, n5);
    }

    public void a(net.minecraft.a.a.b.a.b b2) {
    }
}

