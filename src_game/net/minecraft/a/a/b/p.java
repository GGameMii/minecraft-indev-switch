/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.a.b;

import java.util.Random;
import net.minecraft.a.a.b.x;
import net.minecraft.a.a.d.c;
import net.minecraft.a.a.g;
import net.minecraft.a.d.b;

public class p
extends x {
    protected int a;
    protected int b;

    protected p(int n2, c c2) {
        super(n2, c2);
        this.as = 14;
        if (c2 == net.minecraft.a.a.d.c.g) {
            this.as = 30;
        }
        net.minecraft.a.a.b.x.g[n2] = true;
        this.b = n2;
        this.a = n2 + 1;
        this.a(0.01f, -0.09f, 0.01f, 1.01f, 0.90999997f, 1.01f);
        this.a(true);
        this.a(2.0f);
    }

    public final int a(int n2) {
        if (this.aC == net.minecraft.a.a.d.c.g) {
            return this.as;
        }
        if (n2 == 1) {
            return this.as;
        }
        if (n2 == 0) {
            return this.as;
        }
        return this.as + 32;
    }

    public final boolean c() {
        return false;
    }

    public void d(g g2, int n2, int n3, int n4) {
        g2.e(n2, n3, n4, this.b);
    }

    public void a(g g2, int n2, int n3, int n4, Random random) {
        this.e(g2, n2, n3, n4, 0);
    }

    public boolean e(g g2, int n2, int n3, int n4, int n5) {
        n5 = 0;
        while (this.e(g2, n2, --n3, n4)) {
            boolean bl = g2.b(n2, n3, n4, this.b);
            if (bl) {
                n5 = 1;
            }
            if (bl && this.aC != net.minecraft.a.a.d.c.g) continue;
        }
        ++n3;
        if (this.aC == net.minecraft.a.a.d.c.f || n5 == 0) {
            n5 |= this.i(g2, n2 - 1, n3, n4);
            n5 |= this.i(g2, n2 + 1, n3, n4);
            n5 |= this.i(g2, n2, n3, n4 - 1);
            n5 |= this.i(g2, n2, n3, n4 + 1);
        }
        if (this.aC == net.minecraft.a.a.d.c.g) {
            n5 |= net.minecraft.a.a.b.p.h(g2, n2 - 1, n3, n4);
            n5 |= net.minecraft.a.a.b.p.h(g2, n2 + 1, n3, n4);
            n5 |= net.minecraft.a.a.b.p.h(g2, n2, n3, n4 - 1);
            n5 |= net.minecraft.a.a.b.p.h(g2, n2, n3, n4 + 1);
        }
        if (n5 == 0) {
            g2.d(n2, n3, n4, this.a);
        } else {
            g2.e(n2, n3, n4, this.b);
        }
        return n5 != 0;
    }

    protected final boolean e(g g2, int n2, int n3, int n4) {
        if (!g2.f(n2, n3, n4).e()) {
            return false;
        }
        if (this.aC == net.minecraft.a.a.d.c.f) {
            for (int i2 = n2 - 2; i2 <= n2 + 2; ++i2) {
                for (int i3 = n3 - 2; i3 <= n3 + 2; ++i3) {
                    for (int i4 = n4 - 2; i4 <= n4 + 2; ++i4) {
                        if (g2.a(i2, i3, i4) != net.minecraft.a.a.b.x.A.at) continue;
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static boolean h(g g2, int n2, int n3, int n4) {
        if (net.minecraft.a.a.b.x.ag.b(g2.a(n2, n3, n4))) {
            net.minecraft.a.a.b.x.ag.h(g2, n2, n3, n4);
            return true;
        }
        return false;
    }

    private boolean i(g g2, int n2, int n3, int n4) {
        if (!this.e(g2, n2, n3, n4)) {
            return false;
        }
        boolean bl = g2.b(n2, n3, n4, this.b);
        if (bl) {
            g2.e(n2, n3, n4, this.b);
        }
        return false;
    }

    public final float f(g g2, int n2, int n3, int n4) {
        if (this.aC == net.minecraft.a.a.d.c.g) {
            return 100.0f;
        }
        return g2.c(n2, n3, n4);
    }

    public boolean d(g g2, int n2, int n3, int n4, int n5) {
        if (n2 < 0 || n3 < 0 || n4 < 0 || n2 >= g2.a || n4 >= g2.b) {
            return false;
        }
        int n6 = g2.a(n2, n3, n4);
        if (n6 == this.b || n6 == this.a) {
            return false;
        }
        if (n5 == 1 && (g2.a(n2 - 1, n3, n4) == 0 || g2.a(n2 + 1, n3, n4) == 0 || g2.a(n2, n3, n4 - 1) == 0 || g2.a(n2, n3, n4 + 1) == 0)) {
            return true;
        }
        return super.d(g2, n2, n3, n4, n5);
    }

    public boolean d() {
        return false;
    }

    public b a(int n2, int n3, int n4) {
        return null;
    }

    public boolean b() {
        return false;
    }

    public void b(g g2, int n2, int n3, int n4, int n5) {
        if (n5 != 0) {
            c c2 = net.minecraft.a.a.b.x.c[n5].aC;
            if (this.aC == net.minecraft.a.a.d.c.f && c2 == net.minecraft.a.a.d.c.g || c2 == net.minecraft.a.a.d.c.f && this.aC == net.minecraft.a.a.d.c.g) {
                g2.b(n2, n3, n4, net.minecraft.a.a.b.x.i.at);
            }
        }
        g2.e(n2, n3, n4, this.at);
    }

    public int e() {
        if (this.aC == net.minecraft.a.a.d.c.g) {
            return 25;
        }
        return 5;
    }

    public int a(Random random) {
        return 0;
    }

    public int f() {
        if (this.aC == net.minecraft.a.a.d.c.f) {
            return 1;
        }
        return 0;
    }

    public final void b(g g2, int n2, int n3, int n4, Random random) {
        if (random.nextInt(128) == -1 && g2.f(n2, n3 + 1, n4).c()) {
            if (this.aC == net.minecraft.a.a.d.c.g) {
                g2.a((float)n2 + 0.5f, (float)n3 + 0.5f, (float)n4 + 0.5f, "liquid.lava", random.nextFloat() * 0.25f + 0.75f, random.nextFloat() * 0.5f + 0.3f);
            }
            if (this.aC == net.minecraft.a.a.d.c.f) {
                g2.a((float)n2 + 0.5f, (float)n3 + 0.5f, (float)n4 + 0.5f, "liquid.water", random.nextFloat() * 0.25f + 0.75f, random.nextFloat() + 0.5f);
            }
        }
        if (this.aC == net.minecraft.a.a.d.c.g && g2.f(n2, n3 + 1, n4) == net.minecraft.a.a.d.c.a && !g2.b(n2, n3 + 1, n4) && random.nextInt(100) == 0) {
            float f2 = (float)n2 + random.nextFloat();
            float f3 = (float)n3 + this.ay;
            float f4 = (float)n4 + random.nextFloat();
            g2.a("lava", f2, f3, f4, 0.0f, 0.0f, 0.0f);
        }
        if (this.aC == net.minecraft.a.a.d.c.f) {
            if (net.minecraft.a.a.b.p.j(g2, n2 + 1, n3, n4)) {
                for (int i2 = 0; i2 < 4; ++i2) {
                    g2.a("splash", (float)(n2 + 1) + 0.125f, n3, (float)n4 + random.nextFloat(), 0.0f, 0.0f, 0.0f);
                }
            }
            if (net.minecraft.a.a.b.p.j(g2, n2 - 1, n3, n4)) {
                for (int i3 = 0; i3 < 4; ++i3) {
                    g2.a("splash", (float)n2 - 0.125f, n3, (float)n4 + random.nextFloat(), 0.0f, 0.0f, 0.0f);
                }
            }
            if (net.minecraft.a.a.b.p.j(g2, n2, n3, n4 + 1)) {
                for (int i4 = 0; i4 < 4; ++i4) {
                    g2.a("splash", (float)n2 + random.nextFloat(), n3, (float)(n4 + 1) + 0.125f, 0.0f, 0.0f, 0.0f);
                }
            }
            if (net.minecraft.a.a.b.p.j(g2, n2, n3, n4 - 1)) {
                for (int i5 = 0; i5 < 4; ++i5) {
                    g2.a("splash", (float)n2 + random.nextFloat(), n3, (float)n4 - 0.125f, 0.0f, 0.0f, 0.0f);
                }
            }
        }
    }

    private static boolean j(g object, int n2, int n3, int n4) {
        c c2 = ((g)object).f(n2, n3, n4);
        object = ((g)object).f(n2, n3 - 1, n4);
        if (c2.c() || c2.d()) {
            return false;
        }
        return ((c)object).c() || ((c)object).d();
    }
}

