/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.a.b;

import net.minecraft.a.a.b.x;
import net.minecraft.a.a.d.c;
import net.minecraft.a.a.g;

public final class ag
extends x {
    protected ag(int n2) {
        super(19, net.minecraft.a.a.d.c.j);
        this.as = 48;
    }

    public final void d(g g2, int n2, int n3, int n4) {
        for (int i2 = n2 - 2; i2 <= n2 + 2; ++i2) {
            for (int i3 = n3 - 2; i3 <= n3 + 2; ++i3) {
                for (int i4 = n4 - 2; i4 <= n4 + 2; ++i4) {
                    if (!g2.g(i2, i3, i4)) continue;
                    g2.a(i2, i3, i4, 0);
                }
            }
        }
    }

    public final void b(g g2, int n2, int n3, int n4) {
        for (int i2 = n2 - 2; i2 <= n2 + 2; ++i2) {
            for (int i3 = n3 - 2; i3 <= n3 + 2; ++i3) {
                for (int i4 = n4 - 2; i4 <= n4 + 2; ++i4) {
                    g2.c(i2, i3, i4, g2.a(i2, i3, i4));
                }
            }
        }
    }
}

