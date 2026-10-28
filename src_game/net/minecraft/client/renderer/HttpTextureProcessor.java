/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.renderer;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class HttpTextureProcessor {
    private int[] imageData;
    private int imageWidth;
    private int imageHeight;

    public BufferedImage parseUserSkin(BufferedImage bufferedImage1) {
        this.imageWidth = 64;
        this.imageHeight = 32;
        BufferedImage bufferedImage2 = new BufferedImage(this.imageWidth, this.imageHeight, 2);
        Graphics graphics3 = bufferedImage2.getGraphics();
        graphics3.drawImage(bufferedImage1, 0, 0, null);
        graphics3.dispose();
        this.imageData = ((DataBufferInt)bufferedImage2.getRaster().getDataBuffer()).getData();
        this.setAreaOpaque(0, 0, 32, 16);
        this.setAreaTransparent(32, 0, 64, 32);
        this.setAreaOpaque(0, 16, 64, 32);
        return bufferedImage2;
    }

    private void setAreaTransparent(int i1, int i2, int i3, int i4) {
        boolean z10000;
        int b5 = 32;
        int b11 = 64;
        int b10 = 0;
        int b9 = 32;
        HttpTextureProcessor imageBufferDownload8 = this;
        i2 = b9;
        block0: while (true) {
            if (i2 >= b11) {
                z10000 = false;
                break;
            }
            int i6 = b10;
            while (i6 < b5) {
                if (imageBufferDownload8.imageData[i2 + i6 * imageBufferDownload8.imageWidth] >>> 24 < 128) {
                    z10000 = true;
                    break block0;
                }
                ++i6;
            }
            ++i2;
        }
        if (!z10000) {
            i1 = 32;
            while (i1 < 64) {
                i2 = 0;
                while (i2 < 32) {
                    int n2 = i1 + i2 * this.imageWidth;
                    this.imageData[n2] = this.imageData[n2] & 0xFFFFFF;
                    ++i2;
                }
                ++i1;
            }
        }
    }

    private void setAreaOpaque(int i1, int i2, int i3, int i4) {
        i1 = 0;
        while (i1 < i3) {
            int i5 = i2;
            while (i5 < i4) {
                int n2 = i1 + i5 * this.imageWidth;
                this.imageData[n2] = this.imageData[n2] | 0xFF000000;
                ++i5;
            }
            ++i1;
        }
    }
}

