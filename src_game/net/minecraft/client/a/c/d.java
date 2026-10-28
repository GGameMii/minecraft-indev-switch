/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.a.c;

import a.a;
import net.minecraft.a.a.b.x;
import net.minecraft.client.a.c.b;

public final class d
extends b {
    private float[] e = new float[256];
    private float[] f = new float[256];
    private float[] g = new float[256];
    private float[] h = new float[256];

    public d() {
        super(x.r.as);
    }

    public final void a() {
        int n2;
        int n3;
        int n4;
        int n5;
        int n6;
        float f2;
        int n7;
        for (int i2 = 0; i2 < 16; ++i2) {
            for (n7 = 0; n7 < 16; ++n7) {
                f2 = 0.0f;
                int n8 = (int)(a.a.a((float)n7 * (float)Math.PI * 2.0f / 16.0f) * 1.2f);
                n6 = (int)(a.a.a((float)i2 * (float)Math.PI * 2.0f / 16.0f) * 1.2f);
                for (n5 = i2 - 1; n5 <= i2 + 1; ++n5) {
                    for (n4 = n7 - 1; n4 <= n7 + 1; ++n4) {
                        n3 = n5 + n8 & 0xF;
                        n2 = n4 + n6 & 0xF;
                        f2 += this.e[n3 + (n2 << 4)];
                    }
                }
                this.f[i2 + (n7 << 4)] = f2 / 10.0f + (this.g[(i2 & 0xF) + ((n7 & 0xF) << 4)] + this.g[(i2 + 1 & 0xF) + ((n7 & 0xF) << 4)] + this.g[(i2 + 1 & 0xF) + ((n7 + 1 & 0xF) << 4)] + this.g[(i2 & 0xF) + ((n7 + 1 & 0xF) << 4)]) / 4.0f * 0.8f;
                int n9 = i2 + (n7 << 4);
                this.g[n9] = this.g[n9] + this.h[i2 + (n7 << 4)] * 0.01f;
                if (this.g[i2 + (n7 << 4)] < 0.0f) {
                    this.g[i2 + (n7 << 4)] = 0.0f;
                }
                int n10 = i2 + (n7 << 4);
                this.h[n10] = this.h[n10] - 0.06f;
                if (!(Math.random() < 0.005)) continue;
                this.h[i2 + (n7 << 4)] = 1.5f;
            }
        }
        float[] fArray = this.f;
        this.f = this.e;
        this.e = fArray;
        for (n7 = 0; n7 < 256; ++n7) {
            float f3;
            f2 = this.e[n7] * 2.0f;
            if (f3 > 1.0f) {
                f2 = 1.0f;
            }
            if (f2 < 0.0f) {
                f2 = 0.0f;
            }
            float f4 = f2;
            n6 = (int)(f4 * 100.0f + 155.0f);
            n5 = (int)(f4 * f4 * 255.0f);
            n4 = (int)(f4 * f4 * f4 * f4 * 128.0f);
            if (this.c) {
                n3 = (n6 * 30 + n5 * 59 + n4 * 11) / 100;
                n2 = (n6 * 30 + n5 * 70) / 100;
                int n11 = (n6 * 30 + n4 * 70) / 100;
                n6 = n3;
                n5 = n2;
                n4 = n11;
            }
            this.a[n7 << 2] = (byte)n6;
            this.a[(n7 << 2) + 1] = (byte)n5;
            this.a[(n7 << 2) + 2] = (byte)n4;
            this.a[(n7 << 2) + 3] = -1;
        }
    }
}

