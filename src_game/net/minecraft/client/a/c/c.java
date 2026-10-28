/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.a.c;

import a.a;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import net.minecraft.a.a.b.x;
import net.minecraft.client.a.c.b;

public class c
extends b {
    private int e = 0;
    private int[] f = new int[1024];
    private int[] g = new int[1024];
    private int h;

    public c(int n2) {
        super(x.ak.as + n2);
        this.h = (n2 << 1) - 1;
        this.e = 2;
        try {
            BufferedImage bufferedImage = ImageIO.read(c.class.getResource("/misc/gear.png"));
            bufferedImage.getRGB(0, 0, 32, 32, this.f, 0, 32);
            bufferedImage = ImageIO.read(c.class.getResource("/misc/gearmiddle.png"));
            bufferedImage.getRGB(0, 0, 16, 16, this.g, 0, 16);
            return;
        }
        catch (IOException iOException) {
            IOException iOException2 = iOException;
            iOException.printStackTrace();
            return;
        }
    }

    public final void a() {
        this.e = this.e + this.h & 0x3F;
        float f2 = a.a.a((float)this.e / 64.0f * (float)Math.PI * 2.0f);
        float f3 = a.a.b((float)this.e / 64.0f * (float)Math.PI * 2.0f);
        for (int i2 = 0; i2 < 16; ++i2) {
            for (int i3 = 0; i3 < 16; ++i3) {
                float f4 = ((float)i2 / 15.0f - 0.5f) * 31.0f;
                float f5 = ((float)i3 / 15.0f - 0.5f) * 31.0f;
                float f6 = f3 * f4 - f2 * f5;
                f4 = f3 * f5 + f2 * f4;
                int n2 = (int)(f6 + 16.0f);
                int n3 = (int)(f4 + 16.0f);
                int n4 = 0;
                if (n2 >= 0 && n3 >= 0 && n2 < 32 && n3 < 32) {
                    n4 = this.f[n2 + (n3 << 5)];
                    n3 = this.g[i2 + (i3 << 4)];
                    if (n3 >>> 24 > 128) {
                        n4 = n3;
                    }
                }
                n3 = n4 >> 16 & 0xFF;
                n2 = n4 >> 8 & 0xFF;
                int n5 = n4 & 0xFF;
                n4 = n4 >>> 24 > 128 ? 255 : 0;
                int n6 = i2 + (i3 << 4);
                this.a[n6 << 2] = (byte)n3;
                this.a[(n6 << 2) + 1] = (byte)n2;
                this.a[(n6 << 2) + 2] = (byte)n5;
                this.a[(n6 << 2) + 3] = (byte)n4;
            }
        }
    }
}

