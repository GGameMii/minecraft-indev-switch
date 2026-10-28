/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.a;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class c {
    private int[] a;
    private int b;
    private int c;

    public BufferedImage a(BufferedImage bufferedImage) {
        this.b = 64;
        this.c = 32;
        BufferedImage bufferedImage2 = new BufferedImage(this.b, this.c, 2);
        Graphics graphics = bufferedImage2.getGraphics();
        graphics.drawImage(bufferedImage, 0, 0, null);
        graphics.dispose();
        this.a = ((DataBufferInt)bufferedImage2.getRaster().getDataBuffer()).getData();
        this.b(0, 0, 32, 16);
        this.a(32, 0, 64, 32);
        this.b(0, 16, 64, 32);
        return bufferedImage2;
    }

    private void a(int n2, int n3, int n4, int n5) {
        boolean bl;
        block5: {
            int n6 = 32;
            n5 = 64;
            n4 = 0;
            n3 = 32;
            c c2 = this;
            while (n3 < n5) {
                for (int i2 = n4; i2 < n6; ++i2) {
                    int n7 = c2.a[n3 + i2 * c2.b];
                    if (n7 >>> 24 >= 128) continue;
                    bl = true;
                    break block5;
                }
                ++n3;
            }
            bl = false;
        }
        if (bl) {
            return;
        }
        for (int i3 = 32; i3 < 64; ++i3) {
            for (n3 = 0; n3 < 32; ++n3) {
                int n8 = i3 + n3 * this.b;
                this.a[n8] = this.a[n8] & 0xFFFFFF;
            }
        }
    }

    private void b(int n2, int n3, int n4, int n5) {
        for (n2 = 0; n2 < n4; ++n2) {
            for (int i2 = n3; i2 < n5; ++i2) {
                int n6 = n2 + i2 * this.b;
                this.a[n6] = this.a[n6] | 0xFF000000;
            }
        }
    }
}

