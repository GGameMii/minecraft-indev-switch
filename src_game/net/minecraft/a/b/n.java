/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.b;

import net.minecraft.a.a.b.x;
import net.minecraft.a.a.d.c;
import net.minecraft.a.a.g;
import net.minecraft.a.b.i;
import net.minecraft.a.b.k;
import net.minecraft.a.c.c.b;

public final class n
extends i {
    public n(int n2, int n3) {
        super(n2);
        this.aq = 1;
        this.ar = 32 << n3;
    }

    public final boolean a(k k2, g g2, int n2, int n3, int n4, int n5) {
        if (n2 <= 0 || n3 <= 0 || n4 <= 0 || n2 >= g2.a - 1 || n3 >= g2.c - 1 || n4 >= g2.b - 1) {
            return false;
        }
        n5 = g2.a(n2, n3, n4);
        Object object = g2.f(n2, n3 + 1, n4);
        if (!((c)object).a() && n5 == net.minecraft.a.a.b.x.j.at || n5 == net.minecraft.a.a.b.x.k.at) {
            object = net.minecraft.a.a.b.x.ap;
            net.minecraft.a.a.b.n n6 = ((x)object).aA;
            net.minecraft.a.a.b.n n7 = n6;
            n7 = ((x)object).aA;
            g2.a((float)n2 + 0.5f, (float)n3 + 0.5f, (float)n4 + 0.5f, ((x)object).aA.b(), (n6.a + 1.0f) / 2.0f, n7.b * 0.8f);
            g2.b(n2, n3, n4, ((x)object).at);
            k2.b(1);
            if (g2.q.nextInt(8) == 0 && n5 == net.minecraft.a.a.b.x.j.at) {
                for (int i2 = 0; i2 <= 0; ++i2) {
                    float f2 = g2.q.nextFloat() * 0.7f + 0.15f;
                    float f3 = g2.q.nextFloat() * 0.7f + 0.15f;
                    b b2 = new b(g2, (float)n2 + f2, (float)n3 + 1.2f, (float)n4 + f3, new k(net.minecraft.a.b.i.O));
                    new b(g2, (float)n2 + f2, (float)n3 + 1.2f, (float)n4 + f3, new k(net.minecraft.a.b.i.O)).O = 10;
                    g2.a(b2);
                }
            }
            return true;
        }
        return false;
    }
}

