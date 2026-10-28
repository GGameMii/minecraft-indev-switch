/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.a.b;

import java.util.Random;
import net.minecraft.a.a.b.x;
import net.minecraft.a.a.d.c;
import net.minecraft.a.a.g;
import net.minecraft.a.d.b;

public class ad
extends x {
    protected ad(int n2, int n3) {
        super(n2, net.minecraft.a.a.d.c.i);
        this.as = n3;
        this.a(true);
        this.a(0.3f, 0.0f, 0.3f, 0.7f, 0.6f, 0.7f);
    }

    public final boolean a(g g2, int n2, int n3, int n4) {
        return this.b(g2.a(n2, n3 - 1, n4));
    }

    protected boolean b(int n2) {
        return n2 == net.minecraft.a.a.b.x.j.at || n2 == net.minecraft.a.a.b.x.k.at || n2 == net.minecraft.a.a.b.x.ap.at;
    }

    public final void b(g g2, int n2, int n3, int n4, int n5) {
        super.b(g2, n2, n3, n4, n5);
        this.h(g2, n2, n3, n4);
    }

    public void a(g g2, int n2, int n3, int n4, Random random) {
        this.h(g2, n2, n3, n4);
    }

    private void h(g g2, int n2, int n3, int n4) {
        if (!this.e(g2, n2, n3, n4)) {
            this.f(g2, n2, n3, n4, g2.e(n2, n3, n4));
            g2.b(n2, n3, n4, 0);
        }
    }

    public boolean e(g g2, int n2, int n3, int n4) {
        return (g2.d(n2, n3, n4) >= 8 || g2.d(n2, n3, n4) >= 4 && g2.l(n2, n3, n4)) && this.b(g2.a(n2, n3 - 1, n4));
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

    public int a() {
        return 1;
    }
}

