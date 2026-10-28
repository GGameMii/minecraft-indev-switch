/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.c;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.a.a.d.c;
import net.minecraft.a.a.g;
import net.minecraft.a.b.i;
import net.minecraft.a.b.k;
import net.minecraft.a.c.c.b;
import net.minecraft.a.c.d;

public class a
extends net.minecraft.a.c.b {
    private int O = 0;
    public int a = 0;
    private int P;
    private int Q;
    private int R;
    public d b;

    public a(g g2) {
        super(g2);
        this.v = 0.0f;
        this.a(0.5f, 0.5f);
    }

    public a(g object, int n2, int n3, int n4, int n5) {
        this((g)object);
        this.P = n2;
        this.Q = n3;
        this.R = n4;
        object = new ArrayList();
        d[] dArray = net.minecraft.a.c.d.values();
        n3 = dArray.length;
        for (n4 = 0; n4 < n3; ++n4) {
            d d2;
            this.b = d2 = dArray[n4];
            this.b(n5);
            if (!this.c()) continue;
            object.add(d2);
        }
        if (object.size() > 0) {
            this.b = (d)((Object)object.get(this.G.nextInt(object.size())));
        }
        this.b(n5);
    }

    private void b(int n2) {
        this.a = n2;
        this.p = this.n = (float)(n2 * 90);
        float f2 = this.b.c;
        float f3 = this.b.d;
        float f4 = this.b.c;
        if (n2 == 0 || n2 == 2) {
            f4 = 0.5f;
        } else {
            f2 = 0.5f;
        }
        f2 /= 32.0f;
        f3 /= 32.0f;
        f4 /= 32.0f;
        float f5 = (float)this.P + 0.5f;
        float f6 = (float)this.Q + 0.5f;
        float f7 = (float)this.R + 0.5f;
        if (n2 == 0) {
            f7 -= 0.5625f;
        }
        if (n2 == 1) {
            f5 -= 0.5625f;
        }
        if (n2 == 2) {
            f7 += 0.5625f;
        }
        if (n2 == 3) {
            f5 += 0.5625f;
        }
        if (n2 == 0) {
            f5 -= net.minecraft.a.c.a.c(this.b.c);
        }
        if (n2 == 1) {
            f7 += net.minecraft.a.c.a.c(this.b.c);
        }
        if (n2 == 2) {
            f5 += net.minecraft.a.c.a.c(this.b.c);
        }
        if (n2 == 3) {
            f7 -= net.minecraft.a.c.a.c(this.b.c);
        }
        this.b(f5, f6 += net.minecraft.a.c.a.c(this.b.d), f7);
        this.r = new net.minecraft.a.d.b(f5 - f2, f6 - f3, f7 - f4, f5 + f2, f6 + f3, f7 + f4);
        float f8 = 0.00625f;
        f8 = 0.00625f;
        f8 = 0.00625f;
        net.minecraft.a.d.b b2 = this.r;
        f2 = b2.a;
        f3 = b2.b;
        f4 = b2.c;
        f5 = b2.d;
        f6 = b2.e;
        float f9 = b2.f;
        this.r = new net.minecraft.a.d.b(f2, f3, f4, f5 -= 0.00625f, f6 -= 0.00625f, f9 -= 0.00625f);
    }

    private static float c(int n2) {
        if (n2 == 32) {
            return 0.5f;
        }
        if (n2 == 64) {
            return 0.5f;
        }
        return 0.0f;
    }

    public final void b_() {
        if (this.O++ == 100 && !this.c()) {
            this.O = 0;
            this.k();
            this.d.a(new b(this.d, this.h, this.i, this.j, new k(net.minecraft.a.b.i.ao)));
        }
    }

    public final boolean c() {
        int n2;
        if (this.d.a(this.r).size() > 0) {
            return false;
        }
        int n3 = this.b.c / 16;
        int n4 = this.b.d / 16;
        int n5 = this.P;
        int n6 = this.R;
        if (this.a == 0) {
            n5 = (int)(this.h - (float)this.b.c / 32.0f);
        }
        if (this.a == 1) {
            n6 = (int)(this.j - (float)this.b.c / 32.0f);
        }
        if (this.a == 2) {
            n5 = (int)(this.h - (float)this.b.c / 32.0f);
        }
        if (this.a == 3) {
            n6 = (int)(this.j - (float)this.b.c / 32.0f);
        }
        int n7 = (int)(this.i - (float)this.b.d / 32.0f);
        for (int i2 = 0; i2 < n3; ++i2) {
            for (n2 = 0; n2 < n4; ++n2) {
                c c2 = this.a == 0 || this.a == 2 ? this.d.f(n5 + i2, n7 + n2, this.R) : this.d.f(this.P, n7 + n2, n6 + i2);
                if (c2.a()) continue;
                return false;
            }
        }
        List list = this.d.r.a(this, this.r);
        for (n2 = 0; n2 < list.size(); ++n2) {
            if (!(list.get(n2) instanceof a)) continue;
            return false;
        }
        return true;
    }

    public final boolean d() {
        return true;
    }

    public final boolean a(net.minecraft.a.c.b b2, int n2) {
        this.k();
        this.d.a(new b(this.d, this.h, this.i, this.j, new k(net.minecraft.a.b.i.ao)));
        return true;
    }

    protected final void a(com.a.a.c c2) {
        c2.a("Dir", (byte)this.a);
        c2.a("Motive", this.b.b);
        c2.a("TileX", this.P);
        c2.a("TileY", this.Q);
        c2.a("TileZ", this.R);
    }

    protected final String a() {
        return "Painting";
    }

    protected final void b(com.a.a.c object) {
        this.a = ((com.a.a.c)object).b("Dir");
        this.P = ((com.a.a.c)object).d("TileX");
        this.Q = ((com.a.a.c)object).d("TileY");
        this.R = ((com.a.a.c)object).d("TileZ");
        object = ((com.a.a.c)object).g("Motive");
        for (d d2 : net.minecraft.a.c.d.values()) {
            if (!d2.b.equals(object)) continue;
            this.b = d2;
        }
        if (this.b == null) {
            this.b = net.minecraft.a.c.d.a;
        }
        this.b(this.a);
    }
}

