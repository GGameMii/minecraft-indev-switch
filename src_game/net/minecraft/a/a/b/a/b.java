/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.a.b.a;

import com.a.a.f;
import net.minecraft.a.a.b.a.a;
import net.minecraft.a.a.b.x;
import net.minecraft.a.a.d.c;
import net.minecraft.a.a.g;
import net.minecraft.a.b.i;
import net.minecraft.a.b.k;

public final class b
extends a
implements net.minecraft.a.b {
    private k[] e = new k[3];
    private int f = 0;
    private int g = 0;
    private int h = 0;

    public final int a() {
        return this.e.length;
    }

    public final k a(int n2) {
        return this.e[n2];
    }

    public final k a(int n2, int n3) {
        if (this.e[n2] != null) {
            if (this.e[n2].a <= n3) {
                k k2 = this.e[n2];
                this.e[n2] = null;
                return k2;
            }
            k k3 = this.e[n2].a(n3);
            if (this.e[n2].a == 0) {
                this.e[n2] = null;
            }
            return k3;
        }
        return null;
    }

    public final void a(int n2, k k2) {
        this.e[n2] = k2;
        if (k2 != null) {
            b b2 = this;
            if (k2.a > 64) {
                b2 = this;
                k2.a = 64;
            }
        }
    }

    public final String b() {
        return "Chest";
    }

    public final void a(com.a.a.c c2) {
        super.a(c2);
        f f2 = c2.j("Items");
        b b2 = this;
        this.e = new k[b2.e.length];
        for (int i2 = 0; i2 < f2.b(); ++i2) {
            com.a.a.c c3 = (com.a.a.c)f2.a(i2);
            byte by = c3.b("Slot");
            if (by < 0 || by >= this.e.length) continue;
            this.e[by] = new k(c3);
        }
        this.f = c2.c("BurnTime");
        this.h = c2.c("CookTime");
        this.g = net.minecraft.a.a.b.a.b.a(this.e[1]);
        System.out.println("Lit: " + this.f + "/" + this.g);
    }

    public final void b(com.a.a.c c2) {
        super.b(c2);
        c2.a("BurnTime", (short)this.f);
        c2.a("CookTime", (short)this.h);
        c2.a("id", "Furnace");
        f f2 = new f();
        for (int i2 = 0; i2 < this.e.length; ++i2) {
            if (this.e[i2] == null) continue;
            com.a.a.c c3 = new com.a.a.c();
            c3.a("Slot", (byte)i2);
            this.e[i2].a(c3);
            f2.a(c3);
        }
        c2.a("Items", f2);
    }

    public final int c() {
        return 64;
    }

    public final int b(int n2) {
        return this.h * 24 / 200;
    }

    public final int c(int n2) {
        return this.f * 12 / this.g;
    }

    public final boolean e() {
        return this.f > 0;
    }

    public final void d() {
        int n2;
        int n3 = n2 = this.f > 0 ? 1 : 0;
        if (this.f > 0) {
            --this.f;
        }
        if (this.f == 0 && this.f()) {
            this.g = this.f = net.minecraft.a.a.b.a.b.a(this.e[1]);
            if (this.f > 0 && this.e[1] != null) {
                --this.e[1].a;
                if (this.e[1].a == 0) {
                    this.e[1] = null;
                }
            }
        }
        if (this.e() && this.f()) {
            ++this.h;
            if (this.h == 200) {
                this.h = 0;
                b b2 = this;
                if (b2.f()) {
                    int n4 = net.minecraft.a.a.b.a.b.d(b2.e[0].a().ap);
                    if (b2.e[2] == null) {
                        b2.e[2] = new k(n4, 1);
                    } else if (b2.e[2].c == n4) {
                        ++b2.e[2].a;
                    }
                    --b2.e[0].a;
                    if (b2.e[0].a <= 0) {
                        b2.e[0] = null;
                    }
                }
            }
        } else {
            this.h = 0;
        }
        if (n2 != this.f > 0) {
            int n5 = this.d;
            int n6 = this.c;
            n2 = this.b;
            g g2 = this.a;
            boolean bl = this.f > 0;
            byte by = g2.e(n2, n6, n5);
            a a2 = g2.j(n2, n6, n5);
            if (bl) {
                g2.b(n2, n6, n5, x.ar.at);
            } else {
                g2.b(n2, n6, n5, x.aq.at);
            }
            g2.f(n2, n6, n5, by);
            g2.a(n2, n6, n5, a2);
        }
    }

    private boolean f() {
        if (this.e[0] == null) {
            return false;
        }
        int n2 = net.minecraft.a.a.b.a.b.d(this.e[0].a().ap);
        if (n2 < 0) {
            return false;
        }
        if (this.e[2] == null) {
            return true;
        }
        if (this.e[2].c != n2) {
            return false;
        }
        b b2 = this;
        if (this.e[2].a < 64) {
            return true;
        }
        return this.e[2].a < i.b[n2].c();
    }

    private static int d(int n2) {
        if (n2 == x.w.at) {
            return i.k.ap;
        }
        if (n2 == x.v.at) {
            return i.l.ap;
        }
        if (n2 == x.al.at) {
            return i.j.ap;
        }
        if (n2 == x.t.at) {
            return x.B.at;
        }
        if (n2 == i.am.ap) {
            return i.an.ap;
        }
        if (n2 == x.l.at) {
            return x.i.at;
        }
        return -1;
    }

    private static int a(k k2) {
        if (k2 == null) {
            return 0;
        }
        int n2 = k2.a().ap;
        if (n2 < 256 && x.c[n2].aC == net.minecraft.a.a.d.c.c) {
            return 300;
        }
        if (n2 == i.z.ap) {
            return 100;
        }
        if (n2 == i.i.ap) {
            return 1600;
        }
        return 0;
    }
}

