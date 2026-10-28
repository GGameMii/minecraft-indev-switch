/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.b;

import net.minecraft.a.a.b.x;
import net.minecraft.a.a.g;
import net.minecraft.a.b.i;
import net.minecraft.a.b.k;

public final class f
extends i {
    private int as;

    public f(int n2, int n3) {
        super(39);
        this.as = n3;
    }

    public final boolean a(k k2, g g2, int n2, int n3, int n4, int n5) {
        if (n5 != 1) {
            return false;
        }
        if (n2 <= 0 || n3 <= 0 || n4 <= 0 || n2 >= g2.a - 1 || n3 >= g2.c - 1 || n4 >= g2.b - 1) {
            return false;
        }
        n5 = g2.a(n2, n3, n4);
        if (n5 == net.minecraft.a.a.b.x.ap.at) {
            g2.b(n2, n3 + 1, n4, this.as);
            --k2.a;
            return true;
        }
        return false;
    }
}

