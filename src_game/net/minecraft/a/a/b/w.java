/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.a.b;

import java.util.Random;
import net.minecraft.a.a.b.x;
import net.minecraft.a.a.d.c;
import net.minecraft.a.a.g;

public class w
extends x {
    public w(int n2, int n3) {
        super(n2, n3, net.minecraft.a.a.d.c.m);
        new Random();
    }

    public final void d(g g2, int n2, int n3, int n4) {
        this.e(g2, n2, n3, n4);
    }

    public final void b(g g2, int n2, int n3, int n4, int n5) {
        this.e(g2, n2, n3, n4);
    }

    private void e(g g2, int n2, int n3, int n4) {
        c c2;
        int n5;
        int n6;
        int n7;
        g g3;
        int n8;
        int n9 = n3;
        while (((n8 = (g3 = g2).a(n7 = n2, n6 = n9 - 1, n5 = n4)) == 0 ? true : (n8 == net.minecraft.a.a.b.x.ag.at ? true : ((c2 = net.minecraft.a.a.b.x.c[n8].aC) == net.minecraft.a.a.d.c.f ? true : c2 == net.minecraft.a.a.d.c.g))) && n9 >= 0) {
            if (g2.a(n2, --n9, n4) != net.minecraft.a.a.b.x.ag.at) continue;
            g2.a(n2, n9, n4, 0);
        }
        if (n9 < 0) {
            g2.d(n2, n3, n4, 0);
        }
        if (n9 != n3) {
            int n10 = g2.a(n2, n9, n4);
            if (n10 > 0 && net.minecraft.a.a.b.x.c[n10].aC != net.minecraft.a.a.d.c.a) {
                g2.d(n2, n9, n4, 0);
            }
            g2.a(n2, n3, n4, n2, n9, n4);
        }
    }
}

