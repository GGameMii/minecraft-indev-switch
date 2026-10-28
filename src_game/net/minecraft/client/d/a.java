/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.d;

import net.minecraft.a.a.b.n;
import net.minecraft.a.a.b.x;
import net.minecraft.a.a.g;
import net.minecraft.client.d;

public class a {
    protected final d a;
    public boolean b = false;

    public a(d d2) {
        this.a = d2;
    }

    public void a(g g2) {
    }

    public void a(int n2, int n3, int n4) {
        this.b(n2, n3, n4);
    }

    public boolean b(int n2, int n3, int n4) {
        this.a.g.a(n2, n3, n4);
        g g2 = this.a.d;
        x x2 = x.c[g2.a(n2, n3, n4)];
        byte by = g2.e(n2, n3, n4);
        boolean bl = g2.b(n2, n3, n4, 0);
        if (x2 != null && bl) {
            n n5 = x2.aA;
            n n6 = n5;
            n6 = x2.aA;
            this.a.x.a(x2.aA.a(), (float)n2 + 0.5f, (float)n3 + 0.5f, (float)n4 + 0.5f, (n5.a + 1.0f) / 2.0f, n6.b * 0.8f);
            x2.c(g2, n2, n3, n4, by);
        }
        return bl;
    }

    public void a(int n2, int n3, int n4, int n5) {
    }

    public void a() {
    }

    public void a(float f2) {
    }

    public float b() {
        return 5.0f;
    }

    public void c() {
    }

    public boolean d() {
        return true;
    }

    public void a(net.minecraft.a.c.e.a a2) {
    }
}

