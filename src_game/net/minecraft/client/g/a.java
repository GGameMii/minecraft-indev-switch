/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.g;

import com.a.a.f;
import com.a.a.j;
import net.minecraft.a.a.g;
import net.minecraft.a.b.k;
import net.minecraft.client.c.a.c;
import net.minecraft.client.c.a.i;
import net.minecraft.client.d;
import net.minecraft.client.f.e;
import net.minecraft.client.g.b;
import net.minecraft.client.l;

public class a
extends net.minecraft.a.c.e.a {
    public b a;
    private d an;

    public a(d d2, g g2, l l2) {
        super(g2);
        this.an = d2;
        if (l2 != null) {
            this.N = "http://www.minecraft.net/skin/" + l2.b + ".png";
        }
    }

    public final void e() {
        this.aj = this.a.a;
        this.ak = this.a.b;
        this.al = this.a.c;
    }

    public final void f() {
        this.a.a();
        super.f();
    }

    protected final void a(com.a.a.c object) {
        com.a.a.c c2;
        int n2;
        super.a((com.a.a.c)object);
        ((com.a.a.c)object).a("Score", this.P);
        com.a.a.c c3 = object;
        f f2 = new f();
        object = this.b;
        for (n2 = 0; n2 < ((net.minecraft.a.c.e.b)object).a.length; ++n2) {
            if (((net.minecraft.a.c.e.b)object).a[n2] == null) continue;
            c2 = new com.a.a.c();
            c2.a("Slot", (byte)n2);
            ((net.minecraft.a.c.e.b)object).a[n2].a(c2);
            f2.a(c2);
        }
        for (n2 = 0; n2 < ((net.minecraft.a.c.e.b)object).b.length; ++n2) {
            if (((net.minecraft.a.c.e.b)object).b[n2] == null) continue;
            c2 = new com.a.a.c();
            c2.a("Slot", (byte)(n2 + 100));
            ((net.minecraft.a.c.e.b)object).b[n2].a(c2);
            f2.a(c2);
        }
        c3.a("Inventory", f2);
    }

    protected final void b(com.a.a.c object) {
        super.b((com.a.a.c)object);
        this.P = ((com.a.a.c)object).d("Score");
        object = ((com.a.a.c)object).j("Inventory");
        j j2 = object;
        object = this.b;
        this.b.a = new k[36];
        ((net.minecraft.a.c.e.b)object).b = new k[4];
        for (int i2 = 0; i2 < ((f)j2).b(); ++i2) {
            com.a.a.c c2 = (com.a.a.c)((f)j2).a(i2);
            int n2 = c2.b("Slot") & 0xFF;
            if (n2 >= 0 && n2 < ((net.minecraft.a.c.e.b)object).a.length) {
                ((net.minecraft.a.c.e.b)object).a[n2] = new k(c2);
            }
            if (n2 < 100 || n2 >= ((net.minecraft.a.c.e.b)object).b.length + 100) continue;
            ((net.minecraft.a.c.e.b)object).b[n2 - 100] = new k(c2);
        }
    }

    protected final String a() {
        return "LocalPlayer";
    }

    public final void a(net.minecraft.a.b b2) {
        this.an.a(new i(this.b, b2));
    }

    public final void g_() {
        this.an.a(new net.minecraft.client.c.a.b(this.b));
    }

    public final void a(net.minecraft.a.a.b.a.b b2) {
        this.an.a(new c(this.b, b2));
    }

    public final void h_() {
        this.b.a(this.b.c, null);
    }

    public final void a(net.minecraft.a.c.b b2) {
        this.an.g.a(new e(this.an.d, b2, this, -0.5f));
    }
}

