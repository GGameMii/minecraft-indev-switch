/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.b;

import net.minecraft.a.a.b.x;
import net.minecraft.a.a.d.c;
import net.minecraft.a.b.g;

public final class a
extends g {
    private static x[] as = new x[]{net.minecraft.a.a.b.x.l, net.minecraft.a.a.b.x.Y, net.minecraft.a.a.b.x.Z, net.minecraft.a.a.b.x.i, net.minecraft.a.a.b.x.ad, net.minecraft.a.a.b.x.w, net.minecraft.a.a.b.x.X, net.minecraft.a.a.b.x.x, net.minecraft.a.a.b.x.W, net.minecraft.a.a.b.x.v, net.minecraft.a.a.b.x.al, net.minecraft.a.a.b.x.am};
    private int at;

    public a(int n2, int n3) {
        super(n2, 2, n3, as);
        this.at = n3;
    }

    public final boolean a(x x2) {
        if (x2 == net.minecraft.a.a.b.x.ae) {
            return this.at == 3;
        }
        if (x2 == net.minecraft.a.a.b.x.am || x2 == net.minecraft.a.a.b.x.al) {
            return this.at >= 2;
        }
        if (x2 == net.minecraft.a.a.b.x.W || x2 == net.minecraft.a.a.b.x.v) {
            return this.at >= 2;
        }
        if (x2 == net.minecraft.a.a.b.x.X || x2 == net.minecraft.a.a.b.x.w) {
            return this.at > 0;
        }
        if (x2.aC == net.minecraft.a.a.d.c.d) {
            return true;
        }
        return x2.aC == net.minecraft.a.a.d.c.e;
    }
}

