/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.a.c.a;

import java.util.Random;
import net.minecraft.a.a.c.a.a;
import net.minecraft.a.a.c.a.b;

public final class c
extends b {
    private a[] a;
    private int b;

    public c(Random random, int n2) {
        this.b = n2;
        this.a = new a[n2];
        for (int i2 = 0; i2 < n2; ++i2) {
            this.a[i2] = new a(random);
        }
    }

    public final double a(double d2, double d3) {
        double d4 = 0.0;
        double d5 = 1.0;
        for (int i2 = 0; i2 < this.b; ++i2) {
            d4 += this.a[i2].a(d2 / d5, d3 / d5) * d5;
            d5 *= 2.0;
        }
        return d4;
    }
}

