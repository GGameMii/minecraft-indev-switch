/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.b.a;

import net.minecraft.a.a.b.x;
import net.minecraft.a.b.a.f;
import net.minecraft.a.b.i;
import net.minecraft.a.b.k;

public final class d {
    private Object[][] a = new Object[][]{{x.W, i.l}, {x.X, i.k}, {x.am, i.j}};

    public final void a(f f2) {
        for (int i2 = 0; i2 < this.a.length; ++i2) {
            x x2 = (x)this.a[i2][0];
            i i3 = (i)this.a[i2][1];
            f2.a(new k(x2), "###", "###", "###", Character.valueOf('#'), i3);
            f2.a(new k(i3, 9), "#", Character.valueOf('#'), x2);
        }
    }
}

