/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.b;

import net.minecraft.a.a.b.x;
import net.minecraft.a.b.i;
import net.minecraft.a.b.k;

public class g
extends i {
    private x[] as;
    private float at = 4.0f;
    private int au;

    public g(int n2, int n3, int n4, x[] xArray) {
        super(n2);
        this.as = xArray;
        this.aq = 1;
        this.ar = 32 << n4;
        this.at = n4 + 1 << 1;
        this.au = n3 + n4;
    }

    public final float b(x x2) {
        for (int i2 = 0; i2 < this.as.length; ++i2) {
            if (this.as[i2] != x2) continue;
            return this.at;
        }
        return 1.0f;
    }

    public final void a(k k2) {
        k2.b(2);
    }

    public final void b(k k2) {
        k2.b(1);
    }

    public final int a() {
        return this.au;
    }
}

