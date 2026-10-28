/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.renderer.ptexture;

import net.minecraft.client.renderer.ptexture.TextureFX;
import net.minecraft.world.level.tile.Tile;

public final class TextureWaterFX
extends TextureFX {
    private float[] red = new float[256];
    private float[] green = new float[256];
    private float[] blue = new float[256];
    private float[] alpha = new float[256];
    private int tickCounter = 0;

    public TextureWaterFX() {
        super(Tile.water.tex);
    }

    @Override
    public final void tick() {
        int i6;
        int i5;
        int i4;
        float f3;
        int i2;
        ++this.tickCounter;
        int i1 = 0;
        while (i1 < 16) {
            i2 = 0;
            while (i2 < 16) {
                f3 = 0.0f;
                i4 = i1 - 1;
                while (i4 <= i1 + 1) {
                    i5 = i4 & 0xF;
                    i6 = i2 & 0xF;
                    f3 += this.red[i5 + (i6 << 4)];
                    ++i4;
                }
                this.green[i1 + (i2 << 4)] = f3 / 3.3f + this.blue[i1 + (i2 << 4)] * 0.8f;
                ++i2;
            }
            ++i1;
        }
        i1 = 0;
        while (i1 < 16) {
            i2 = 0;
            while (i2 < 16) {
                int n2 = i1 + (i2 << 4);
                this.blue[n2] = this.blue[n2] + this.alpha[i1 + (i2 << 4)] * 0.05f;
                if (this.blue[i1 + (i2 << 4)] < 0.0f) {
                    this.blue[i1 + (i2 << 4)] = 0.0f;
                }
                int n3 = i1 + (i2 << 4);
                this.alpha[n3] = this.alpha[n3] - 0.1f;
                if (Math.random() < 0.05) {
                    this.alpha[i1 + (i2 << 4)] = 0.5f;
                }
                ++i2;
            }
            ++i1;
        }
        float[] f8 = this.green;
        this.green = this.red;
        this.red = f8;
        i2 = 0;
        while (i2 < 256) {
            f3 = this.red[i2];
            if (f3 > 1.0f) {
                f3 = 1.0f;
            }
            if (f3 < 0.0f) {
                f3 = 0.0f;
            }
            float f9 = f3 * f3;
            i5 = (int)(32.0f + f9 * 32.0f);
            i6 = (int)(50.0f + f9 * 64.0f);
            i1 = 255;
            int i10 = (int)(146.0f + f9 * 50.0f);
            if (this.anaglyphEnabled) {
                i1 = (i5 * 30 + i6 * 59 + 2805) / 100;
                i4 = (i5 * 30 + i6 * 70) / 100;
                int i7 = (i5 * 30 + 17850) / 100;
                i5 = i1;
                i6 = i4;
                i1 = i7;
            }
            this.imageData[i2 << 2] = (byte)i5;
            this.imageData[(i2 << 2) + 1] = (byte)i6;
            this.imageData[(i2 << 2) + 2] = (byte)i1;
            this.imageData[(i2 << 2) + 3] = (byte)i10;
            ++i2;
        }
    }
}

