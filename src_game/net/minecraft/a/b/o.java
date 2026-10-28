/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.b;

import net.minecraft.a.a.g;
import net.minecraft.a.b.i;
import net.minecraft.a.b.k;
import net.minecraft.a.c.a;

public final class o
extends i {
    public o(int n2) {
        super(65);
        this.ar = 64;
    }

    public final boolean a(k k2, g g2, int n2, int n3, int n4, int n5) {
        a a2;
        if (n5 == 0) {
            return false;
        }
        if (n5 == 1) {
            return false;
        }
        if (n2 <= 0 || n3 <= 0 || n4 <= 0 || n2 >= g2.a - 1 || n3 >= g2.c - 1 || n4 >= g2.b - 1) {
            return false;
        }
        int n6 = 0;
        if (n5 == 4) {
            n6 = 1;
        }
        if (n5 == 3) {
            n6 = 2;
        }
        if (n5 == 5) {
            n6 = 3;
        }
        if ((a2 = new a(g2, n2, n3, n4, n6)).c()) {
            g2.a(a2);
            --k2.a;
        }
        return true;
    }
}

