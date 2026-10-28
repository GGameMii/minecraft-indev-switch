/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.a.c;

import net.minecraft.a.a.b.x;
import net.minecraft.client.a.c.b;

public final class f
extends b {
    private float[] e = new float[256];
    private float[] f = new float[256];
    private float[] g = new float[256];
    private float[] h = new float[256];
    private int i = 0;

    public f() {
        super(x.p.as);
    }

    public final void a() {
        int n2;
        int n3;
        int n4;
        int n5;
        ++this.i;
        for (n5 = 0; n5 < 16; ++n5) {
            for (n4 = 0; n4 < 16; ++n4) {
                float f2 = 0.0f;
                for (int i2 = n5 - 1; i2 <= n5 + 1; ++i2) {
                    n3 = i2 & 0xF;
                    n2 = n4 & 0xF;
                    f2 += this.e[n3 + (n2 << 4)];
                }
                this.f[n5 + (n4 << 4)] = f2 / 3.3f + this.g[n5 + (n4 << 4)] * 0.8f;
            }
        }
        for (n5 = 0; n5 < 16; ++n5) {
            for (n4 = 0; n4 < 16; ++n4) {
                int n6 = n5 + (n4 << 4);
                this.g[n6] = this.g[n6] + this.h[n5 + (n4 << 4)] * 0.05f;
                if (this.g[n5 + (n4 << 4)] < 0.0f) {
                    this.g[n5 + (n4 << 4)] = 0.0f;
                }
                int n7 = n5 + (n4 << 4);
                this.h[n7] = this.h[n7] - 0.1f;
                if (!(Math.random() < 0.05)) continue;
                this.h[n5 + (n4 << 4)] = 0.5f;
            }
        }
        float[] fArray = this.f;
        this.f = this.e;
        this.e = fArray;
        for (n4 = 0; n4 < 256; ++n4) {
            float f3;
            float f4 = this.e[n4];
            if (f3 > 1.0f) {
                f4 = 1.0f;
            }
            if (f4 < 0.0f) {
                f4 = 0.0f;
            }
            float f5 = f4 * f4;
            n3 = (int)(32.0f + f5 * 32.0f);
            n2 = (int)(50.0f + f5 * 64.0f);
            int n8 = 255;
            int n9 = (int)(146.0f + f5 * 50.0f);
            if (this.c) {
                n8 = (n3 * 30 + n2 * 59 + 2805) / 100;
                int n10 = (n3 * 30 + n2 * 70) / 100;
                int n11 = (n3 * 30 + 17850) / 100;
                n3 = n8;
                n2 = n10;
                n8 = n11;
            }
            this.a[n4 << 2] = (byte)n3;
            this.a[(n4 << 2) + 1] = (byte)n2;
            this.a[(n4 << 2) + 2] = (byte)n8;
            this.a[(n4 << 2) + 3] = (byte)n9;
        }
    }
}

