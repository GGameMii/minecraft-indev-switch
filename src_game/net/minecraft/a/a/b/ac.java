/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.a.b;

import java.util.Random;
import net.minecraft.a.a.b.x;
import net.minecraft.a.a.d.c;
import net.minecraft.a.a.g;
import net.minecraft.a.d.a;
import net.minecraft.a.d.b;

public final class ac
extends x {
    protected ac(int n2, int n3) {
        super(50, 80, net.minecraft.a.a.d.c.n);
        this.a(true);
    }

    public final b a(int n2, int n3, int n4) {
        return null;
    }

    public final boolean b() {
        return false;
    }

    public final boolean c() {
        return false;
    }

    public final int a() {
        return 2;
    }

    public final boolean a(g g2, int n2, int n3, int n4) {
        if (g2.b(n2 - 1, n3, n4)) {
            return true;
        }
        if (g2.b(n2 + 1, n3, n4)) {
            return true;
        }
        if (g2.b(n2, n3, n4 - 1)) {
            return true;
        }
        if (g2.b(n2, n3, n4 + 1)) {
            return true;
        }
        return g2.b(n2, n3 - 1, n4);
    }

    public final void g(g g2, int n2, int n3, int n4, int n5) {
        int n6 = g2.e(n2, n3, n4);
        if (n5 == 1 && g2.b(n2, n3 - 1, n4)) {
            n6 = 5;
        }
        if (n5 == 2 && g2.b(n2, n3, n4 + 1)) {
            n6 = 4;
        }
        if (n5 == 3 && g2.b(n2, n3, n4 - 1)) {
            n6 = 3;
        }
        if (n5 == 4 && g2.b(n2 + 1, n3, n4)) {
            n6 = 2;
        }
        if (n5 == 5 && g2.b(n2 - 1, n3, n4)) {
            n6 = 1;
        }
        g2.f(n2, n3, n4, n6);
    }

    public final void a(g g2, int n2, int n3, int n4, Random random) {
        super.a(g2, n2, n3, n4, random);
        if (g2.e(n2, n3, n4) == 0) {
            this.d(g2, n2, n3, n4);
        }
    }

    public final void d(g g2, int n2, int n3, int n4) {
        if (g2.b(n2 - 1, n3, n4)) {
            g2.f(n2, n3, n4, 1);
        } else if (g2.b(n2 + 1, n3, n4)) {
            g2.f(n2, n3, n4, 2);
        } else if (g2.b(n2, n3, n4 - 1)) {
            g2.f(n2, n3, n4, 3);
        } else if (g2.b(n2, n3, n4 + 1)) {
            g2.f(n2, n3, n4, 4);
        } else if (g2.b(n2, n3 - 1, n4)) {
            g2.f(n2, n3, n4, 5);
        }
        this.e(g2, n2, n3, n4);
    }

    public final void b(g g2, int n2, int n3, int n4, int n5) {
        if (this.e(g2, n2, n3, n4)) {
            n5 = g2.e(n2, n3, n4);
            boolean bl = false;
            if (!g2.b(n2 - 1, n3, n4) && n5 == 1) {
                bl = true;
            }
            if (!g2.b(n2 + 1, n3, n4) && n5 == 2) {
                bl = true;
            }
            if (!g2.b(n2, n3, n4 - 1) && n5 == 3) {
                bl = true;
            }
            if (!g2.b(n2, n3, n4 + 1) && n5 == 4) {
                bl = true;
            }
            if (!g2.b(n2, n3 - 1, n4) && n5 == 5) {
                bl = true;
            }
            if (bl) {
                this.f(g2, n2, n3, n4, g2.e(n2, n3, n4));
                g2.b(n2, n3, n4, 0);
            }
        }
    }

    private boolean e(g g2, int n2, int n3, int n4) {
        if (!this.a(g2, n2, n3, n4)) {
            this.f(g2, n2, n3, n4, g2.e(n2, n3, n4));
            g2.b(n2, n3, n4, 0);
            return false;
        }
        return true;
    }

    public final net.minecraft.a.d.c a(g g2, int n2, int n3, int n4, a a2, a a3) {
        byte by = g2.e(n2, n3, n4);
        if (by == 1) {
            this.a(0.0f, 0.2f, 0.35f, 0.3f, 0.8f, 0.65f);
        } else if (by == 2) {
            this.a(0.7f, 0.2f, 0.35f, 1.0f, 0.8f, 0.65f);
        } else if (by == 3) {
            this.a(0.35f, 0.2f, 0.0f, 0.65f, 0.8f, 0.3f);
        } else if (by == 4) {
            this.a(0.35f, 0.2f, 0.7f, 0.65f, 0.8f, 1.0f);
        } else {
            this.a(0.4f, 0.0f, 0.4f, 0.6f, 0.6f, 0.6f);
        }
        return super.a(g2, n2, n3, n4, a2, a3);
    }

    public final void b(g g2, int n2, int n3, int n4, Random random) {
        byte by = g2.e(n2, n3, n4);
        float f2 = (float)n2 + 0.5f;
        float f3 = (float)n3 + 0.7f;
        float f4 = (float)n4 + 0.5f;
        if (by == 1) {
            g2.a("smoke", f2 - 0.27f, f3 + 0.22f, f4, 0.0f, 0.0f, 0.0f);
            g2.a("flame", f2 - 0.27f, f3 + 0.22f, f4, 0.0f, 0.0f, 0.0f);
            return;
        }
        if (by == 2) {
            g2.a("smoke", f2 + 0.27f, f3 + 0.22f, f4, 0.0f, 0.0f, 0.0f);
            g2.a("flame", f2 + 0.27f, f3 + 0.22f, f4, 0.0f, 0.0f, 0.0f);
            return;
        }
        if (by == 3) {
            g2.a("smoke", f2, f3 + 0.22f, f4 - 0.27f, 0.0f, 0.0f, 0.0f);
            g2.a("flame", f2, f3 + 0.22f, f4 - 0.27f, 0.0f, 0.0f, 0.0f);
            return;
        }
        if (by == 4) {
            g2.a("smoke", f2, f3 + 0.22f, f4 + 0.27f, 0.0f, 0.0f, 0.0f);
            g2.a("flame", f2, f3 + 0.22f, f4 + 0.27f, 0.0f, 0.0f, 0.0f);
            return;
        }
        g2.a("smoke", f2, f3, f4, 0.0f, 0.0f, 0.0f);
        g2.a("flame", f2, f3, f4, 0.0f, 0.0f, 0.0f);
    }
}

