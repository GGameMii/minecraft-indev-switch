/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.b;

import net.minecraft.a.a.b.n;
import net.minecraft.a.a.b.x;
import net.minecraft.a.a.g;
import net.minecraft.a.b.i;
import net.minecraft.a.b.k;
import net.minecraft.a.d.b;

public final class d
extends i {
    private int as;

    public d(int n2) {
        super(n2);
        this.as = n2 + 256;
        this.a(net.minecraft.a.a.b.x.c[n2 + 256].a(2));
    }

    public final boolean a(k k2, g object, int n2, int n3, int n4, int n5) {
        b b2;
        if (n5 == 0) {
            --n3;
        }
        if (n5 == 1) {
            ++n3;
        }
        if (n5 == 2) {
            --n4;
        }
        if (n5 == 3) {
            ++n4;
        }
        if (n5 == 4) {
            --n2;
        }
        if (n5 == 5) {
            ++n2;
        }
        if (k2.a == 0) {
            return false;
        }
        if (n2 <= 0 || n3 <= 0 || n4 <= 0 || n2 >= ((g)object).a - 1 || n3 >= ((g)object).c - 1 || n4 >= ((g)object).b - 1) {
            return false;
        }
        int n6 = ((g)object).a(n2, n3, n4);
        x x2 = net.minecraft.a.a.b.x.c[n6];
        if ((this.as > 0 && x2 == null || x2 == net.minecraft.a.a.b.x.p || x2 == net.minecraft.a.a.b.x.q || x2 == net.minecraft.a.a.b.x.r || x2 == net.minecraft.a.a.b.x.s || x2 == net.minecraft.a.a.b.x.ag) && ((g)object).e(b2 = (x2 = net.minecraft.a.a.b.x.c[this.as]).a(n2, n3, n4)) && x2.a((g)object, n2, n3, n4) && ((g)object).b(n2, n3, n4, this.as)) {
            net.minecraft.a.a.b.x.c[this.as].g((g)object, n2, n3, n4, n5);
            g g2 = object;
            n n7 = x2.aA;
            object = n7;
            object = x2.aA;
            g2.a((float)n2 + 0.5f, (float)n3 + 0.5f, (float)n4 + 0.5f, x2.aA.b(), (n7.a + 1.0f) / 2.0f, ((n)object).b * 0.8f);
            --k2.a;
        }
        return true;
    }
}

