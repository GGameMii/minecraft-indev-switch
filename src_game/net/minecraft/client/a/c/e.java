/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.a.c;

import net.minecraft.a.a.b.x;
import net.minecraft.client.a.c.b;

public final class e
extends b {
    private float[] e = new float[320];
    private float[] f = new float[320];

    public e(int n2) {
        super(x.ag.as + (n2 << 4));
    }

    public final void a() {
        int n2;
        int n3;
        int n4;
        int n5;
        int n6;
        for (int i2 = 0; i2 < 16; ++i2) {
            for (n6 = 0; n6 < 20; ++n6) {
                int n7 = 18;
                float f2 = this.e[i2 + ((n6 + 1) % 20 << 4)] * 18.0f;
                for (n5 = i2 - 1; n5 <= i2 + 1; ++n5) {
                    for (n4 = n6; n4 <= n6 + 1; ++n4) {
                        n3 = n5;
                        n2 = n4;
                        if (n3 >= 0 && n2 >= 0 && n3 < 16 && n2 < 20) {
                            f2 += this.e[n3 + (n2 << 4)];
                        }
                        ++n7;
                    }
                }
                this.f[i2 + (n6 << 4)] = f2 / ((float)n7 * 1.06f);
                if (n6 < 19) continue;
                this.f[i2 + (n6 << 4)] = (float)(Math.random() * Math.random() * Math.random() * 4.0 + Math.random() * (double)0.1f + (double)0.2f);
            }
        }
        float[] fArray = this.f;
        this.f = this.e;
        this.e = fArray;
        for (n6 = 0; n6 < 256; ++n6) {
            float f3;
            float f4 = this.e[n6] * 1.8f;
            if (f3 > 1.0f) {
                f4 = 1.0f;
            }
            if (f4 < 0.0f) {
                f4 = 0.0f;
            }
            float f5 = f4;
            n5 = (int)(f5 * 155.0f + 100.0f);
            n4 = (int)(f5 * f5 * 255.0f);
            n3 = (int)(f5 * f5 * f5 * f5 * f5 * f5 * f5 * f5 * f5 * f5 * 255.0f);
            n2 = 255;
            if (f5 < 0.5f) {
                n2 = 0;
            }
            if (this.c) {
                int n8 = (n5 * 30 + n4 * 59 + n3 * 11) / 100;
                int n9 = (n5 * 30 + n4 * 70) / 100;
                int n10 = (n5 * 30 + n3 * 70) / 100;
                n5 = n8;
                n4 = n9;
                n3 = n10;
            }
            this.a[n6 << 2] = (byte)n5;
            this.a[(n6 << 2) + 1] = (byte)n4;
            this.a[(n6 << 2) + 2] = (byte)n3;
            this.a[(n6 << 2) + 3] = (byte)n2;
        }
    }
}

