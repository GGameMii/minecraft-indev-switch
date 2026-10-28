/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.b.a;

import net.minecraft.a.a.b.x;
import net.minecraft.a.b.a.f;
import net.minecraft.a.b.i;
import net.minecraft.a.b.k;

public final class h {
    private String[][] a = new String[][]{{"X", "X", "#"}};
    private Object[][] b = new Object[][]{{x.m, x.l, i.k, i.j, i.l}, {i.n, i.r, i.m, i.v, i.C}};

    public final void a(f f2) {
        for (int i2 = 0; i2 < this.b[0].length; ++i2) {
            Object object = this.b[0][i2];
            for (int i3 = 0; i3 < this.b.length - 1; ++i3) {
                i i4 = (i)this.b[i3 + 1][i2];
                f2.a(new k(i4), this.a[i3], Character.valueOf('#'), i.z, Character.valueOf('X'), object);
            }
        }
        f2.a(new k(i.g, 1), " #X", "# X", " #X", Character.valueOf('X'), i.G, Character.valueOf('#'), i.z);
        f2.a(new k(i.h, 4), "X", "#", "Y", Character.valueOf('Y'), i.H, Character.valueOf('X'), i.k, Character.valueOf('#'), i.z);
    }
}

