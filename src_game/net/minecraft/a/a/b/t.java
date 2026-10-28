/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.a.b;

import net.minecraft.a.a.b.x;
import net.minecraft.a.a.d.c;
import net.minecraft.a.a.g;
import net.minecraft.a.c.e.a;

public final class t
extends x {
    protected t(int n2) {
        super(58, net.minecraft.a.a.d.c.c);
        this.as = 59;
    }

    public final int a(int n2) {
        if (n2 == 1) {
            return this.as - 16;
        }
        if (n2 == 0) {
            return net.minecraft.a.a.b.x.m.a(0);
        }
        if (n2 == 2 || n2 == 4) {
            return this.as + 1;
        }
        return this.as;
    }

    public final boolean a(g g2, int n2, int n3, int n4, a a2) {
        a2.g_();
        return true;
    }
}

