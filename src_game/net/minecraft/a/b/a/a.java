/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.b.a;

import net.minecraft.a.a.b.x;
import net.minecraft.a.b.a.f;
import net.minecraft.a.b.i;
import net.minecraft.a.b.k;

public final class a {
    private String[][] a = new String[][]{{"XXX", "X X"}, {"X X", "XXX", "XXX"}, {"XXX", "X X", "X X"}, {"X X", "X X"}};
    private Object[][] b = new Object[][]{{x.Q, x.ag, i.k, i.j, i.l}, {i.R, i.V, i.Z, i.ad, i.ah}, {i.S, i.W, i.aa, i.ae, i.ai}, {i.T, i.X, i.ab, i.af, i.aj}, {i.U, i.Y, i.ac, i.ag, i.ak}};

    public final void a(f f2) {
        for (int i2 = 0; i2 < this.b[0].length; ++i2) {
            Object object = this.b[0][i2];
            for (int i3 = 0; i3 < this.b.length - 1; ++i3) {
                i i4 = (i)this.b[i3 + 1][i2];
                f2.a(new k(i4), this.a[i3], Character.valueOf('X'), object);
            }
        }
    }
}

