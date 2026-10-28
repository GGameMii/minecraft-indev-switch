/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.d;

import net.minecraft.a.a.b.n;
import net.minecraft.a.a.b.x;
import net.minecraft.a.a.g;
import net.minecraft.a.b.i;
import net.minecraft.a.b.k;
import net.minecraft.client.d;
import net.minecraft.client.d.a;

public final class b
extends a {
    private int c = -1;
    private int d = -1;
    private int e = -1;
    private float f = 0.0f;
    private float g = 0.0f;
    private float h = 0.0f;
    private int i = 0;
    private net.minecraft.a.a.b j;

    public b(d d2) {
        super(d2);
    }

    public final boolean b(int n2, int n3, int n4) {
        int n5 = this.a.d.a(n2, n3, n4);
        byte by = this.a.d.e(n2, n3, n4);
        boolean bl = super.b(n2, n3, n4);
        Object object = this.a.f;
        object = ((net.minecraft.client.g.a)object).b.d();
        if (object != null) {
            int n6 = n4;
            n6 = n3;
            n6 = n2;
            n6 = n5;
            Object object2 = object;
            net.minecraft.a.b.i.b[((k)object2).c].b((k)object2);
            if (((k)object).a == 0) {
                this.a.f.h_();
            }
        }
        if (bl && this.a.f.a(x.c[n5])) {
            x.c[n5].f(this.a.d, n2, n3, n4, by);
        }
        return bl;
    }

    public final void a(int n2, int n3, int n4) {
        int n5 = this.a.d.a(n2, n3, n4);
        if (n5 > 0 && x.c[n5].a(this.a.f) >= 1.0f) {
            this.b(n2, n3, n4);
        }
    }

    public final void a() {
        this.f = 0.0f;
        this.i = 0;
    }

    public final void a(int n2, int n3, int n4, int n5) {
        if (this.i > 0) {
            --this.i;
            return;
        }
        super.a(n2, n3, n4, n5);
        if (n2 == this.c && n3 == this.d && n4 == this.e) {
            n5 = this.a.d.a(n2, n3, n4);
            if (n5 == 0) {
                return;
            }
            x x2 = x.c[n5];
            this.f += x2.a(this.a.f);
            if (this.h % 4.0f == 0.0f && x2 != null) {
                n n6 = x2.aA;
                n n7 = n6;
                n7 = x2.aA;
                this.a.x.a(x2.aA.b(), (float)n2 + 0.5f, (float)n3 + 0.5f, (float)n4 + 0.5f, (n6.a + 1.0f) / 8.0f, n7.b * 0.5f);
            }
            this.h += 1.0f;
            if (this.f >= 1.0f) {
                this.b(n2, n3, n4);
                this.f = 0.0f;
                this.g = 0.0f;
                this.h = 0.0f;
                this.i = 5;
            }
            return;
        }
        this.f = 0.0f;
        this.g = 0.0f;
        this.h = 0.0f;
        this.c = n2;
        this.d = n3;
        this.e = n4;
    }

    public final void a(float f2) {
        if (this.f <= 0.0f) {
            this.a.e.a = 0.0f;
            return;
        }
        this.a.e.a = f2 = this.g + (this.f - this.g) * f2;
    }

    public final float b() {
        return 4.0f;
    }

    public final void a(g g2) {
        super.a(g2);
        this.j = new net.minecraft.a.a.b(g2);
    }

    public final void c() {
        this.g = this.f;
        this.j.a();
    }
}

