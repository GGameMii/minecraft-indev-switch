/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.a.c.a;

import java.util.Random;
import net.minecraft.a.a.c.a.b;

public final class a
extends b {
    private int[] a = new int[512];

    public a() {
        this(new Random());
    }

    public a(Random random) {
        int n2;
        for (n2 = 0; n2 < 256; ++n2) {
            this.a[n2] = n2;
        }
        for (n2 = 0; n2 < 256; ++n2) {
            int n3 = random.nextInt(256 - n2) + n2;
            int n4 = this.a[n2];
            this.a[n2] = this.a[n3];
            this.a[n3] = n4;
            this.a[n2 + 256] = this.a[n2];
        }
    }

    private static double a(double d2) {
        return d2 * d2 * d2 * (d2 * (d2 * 6.0 - 15.0) + 10.0);
    }

    private static double a(double d2, double d3, double d4) {
        return d3 + d2 * (d4 - d3);
    }

    private static double a(int n2, double d2, double d3, double d4) {
        double d5;
        double d6 = d5 = (n2 &= 0xF) < 8 ? d2 : d3;
        double d7 = n2 < 4 ? d3 : (n2 == 12 || n2 == 14 ? d2 : d4);
        return ((n2 & 1) == 0 ? d5 : -d5) + ((n2 & 2) == 0 ? d7 : -d7);
    }

    public final double a(double d2, double d3) {
        double d4 = 0.0;
        double d5 = d3;
        double d6 = d2;
        a a2 = this;
        int n2 = a.a.a(d6) & 0xFF;
        int n3 = a.a.a(d5) & 0xFF;
        int n4 = a.a.a(0.0) & 0xFF;
        d6 -= (double)a.a.a(d6);
        d5 -= (double)a.a.a(d5);
        d4 = 0.0 - (double)a.a.a(0.0);
        double d7 = net.minecraft.a.a.c.a.a.a(d6);
        double d8 = net.minecraft.a.a.c.a.a.a(d5);
        double d9 = net.minecraft.a.a.c.a.a.a(d4);
        int n5 = a2.a[n2] + n3;
        int n6 = a2.a[n5] + n4;
        n5 = a2.a[n5 + 1] + n4;
        n2 = a2.a[n2 + 1] + n3;
        n3 = a2.a[n2] + n4;
        n2 = a2.a[n2 + 1] + n4;
        return net.minecraft.a.a.c.a.a.a(d9, net.minecraft.a.a.c.a.a.a(d8, net.minecraft.a.a.c.a.a.a(d7, net.minecraft.a.a.c.a.a.a(a2.a[n6], d6, d5, d4), net.minecraft.a.a.c.a.a.a(a2.a[n3], d6 - 1.0, d5, d4)), net.minecraft.a.a.c.a.a.a(d7, net.minecraft.a.a.c.a.a.a(a2.a[n5], d6, d5 - 1.0, d4), net.minecraft.a.a.c.a.a.a(a2.a[n2], d6 - 1.0, d5 - 1.0, d4))), net.minecraft.a.a.c.a.a.a(d8, net.minecraft.a.a.c.a.a.a(d7, net.minecraft.a.a.c.a.a.a(a2.a[n6 + 1], d6, d5, d4 - 1.0), net.minecraft.a.a.c.a.a.a(a2.a[n3 + 1], d6 - 1.0, d5, d4 - 1.0)), net.minecraft.a.a.c.a.a.a(d7, net.minecraft.a.a.c.a.a.a(a2.a[n5 + 1], d6, d5 - 1.0, d4 - 1.0), net.minecraft.a.a.c.a.a.a(a2.a[n2 + 1], d6 - 1.0, d5 - 1.0, d4 - 1.0))));
    }
}

