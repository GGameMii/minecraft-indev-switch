/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.b.a;

import net.minecraft.a.a.b.x;
import net.minecraft.a.b.a.f;
import net.minecraft.a.b.i;
import net.minecraft.a.b.k;

public final class e {
    private String[][] a = new String[][]{{"XXX", " # ", " # "}, {"X", "#", "#"}, {"XX", "X#", " #"}, {"XX", " #", " #"}};
    private Object[][] b = new Object[][]{{x.m, x.l, i.k, i.j, i.l}, {i.p, i.t, i.d, i.x, i.E}, {i.o, i.s, i.c, i.w, i.D}, {i.q, i.u, i.e, i.y, i.F}, {i.J, i.K, i.L, i.M, i.N}};

    public final void a(f f2) {
        for (int i2 = 0; i2 < this.b[0].length; ++i2) {
            Object object = this.b[0][i2];
            for (int i3 = 0; i3 < this.b.length - 1; ++i3) {
                i i4 = (i)this.b[i3 + 1][i2];
                f2.a(new k(i4), this.a[i3], Character.valueOf('#'), i.z, Character.valueOf('X'), object);
            }
        }
    }
}

