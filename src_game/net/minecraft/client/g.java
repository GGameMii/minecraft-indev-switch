/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client;

public final class g {
    float a = 20.0f;
    private double d;
    public int b;
    public float c;
    private float e = 1.0f;
    private float f = 0.0f;
    private long g = System.currentTimeMillis();
    private long h = System.nanoTime() / 1000000L;
    private double i = 1.0;

    public g(float f2) {
    }

    public final void a() {
        double d2;
        long l2 = System.currentTimeMillis();
        long l3 = l2 - this.g;
        long l4 = System.nanoTime() / 1000000L;
        if (l3 > 1000L) {
            long l5 = l4 - this.h;
            d2 = (double)l3 / (double)l5;
            this.i += (d2 - this.i) * (double)0.2f;
            this.g = l2;
            this.h = l4;
        }
        if (l3 < 0L) {
            this.g = l2;
            this.h = l4;
        }
        double d3 = (double)l4 / 1000.0;
        d2 = (d3 - this.d) * this.i;
        this.d = d3;
        if (d2 < 0.0) {
            d2 = 0.0;
        }
        if (d2 > 1.0) {
            d2 = 1.0;
        }
        this.f = (float)((double)this.f + d2 * (double)this.e * (double)this.a);
        this.b = (int)this.f;
        this.f -= (float)this.b;
        if (this.b > 10) {
            this.b = 10;
        }
        this.c = this.f;
    }
}

