/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.a.b;

import java.util.Random;
import net.minecraft.a.a.b.p;
import net.minecraft.a.a.b.x;
import net.minecraft.a.a.d.c;
import net.minecraft.a.a.g;

public final class b
extends p {
    protected b(int n2, c c2) {
        super(n2, c2);
        this.b = n2 - 1;
        this.a = n2;
        this.a(false);
    }

    public final void a(g g2, int n2, int n3, int n4, Random random) {
    }

    public final void b(g g2, int n2, int n3, int n4, int n5) {
        boolean bl = false;
        if (this.e(g2, n2, n3 - 1, n4)) {
            bl = true;
        }
        if (!bl && this.e(g2, n2 - 1, n3, n4)) {
            bl = true;
        }
        if (!bl && this.e(g2, n2 + 1, n3, n4)) {
            bl = true;
        }
        if (!bl && this.e(g2, n2, n3, n4 - 1)) {
            bl = true;
        }
        if (!bl && this.e(g2, n2, n3, n4 + 1)) {
            bl = true;
        }
        if (n5 != 0) {
            c c2 = net.minecraft.a.a.b.x.c[n5].aC;
            if (this.aC == net.minecraft.a.a.d.c.f && c2 == net.minecraft.a.a.d.c.g || c2 == net.minecraft.a.a.d.c.f && this.aC == net.minecraft.a.a.d.c.g) {
                g2.b(n2, n3, n4, net.minecraft.a.a.b.x.i.at);
                return;
            }
        }
        if (net.minecraft.a.a.b.x.ag.b(n5)) {
            bl = true;
        }
        if (bl) {
            g2.d(n2, n3, n4, this.b);
            g2.e(n2, n3, n4, this.b);
        }
    }
}

