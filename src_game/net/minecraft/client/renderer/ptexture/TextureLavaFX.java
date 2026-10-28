/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.renderer.ptexture;

import net.minecraft.client.renderer.ptexture.TextureFX;
import net.minecraft.world.level.tile.Tile;
import util.Mth;

public final class TextureLavaFX
extends TextureFX {
    private float[] red = new float[256];
    private float[] green = new float[256];
    private float[] blue = new float[256];
    private float[] alpha = new float[256];

    public TextureLavaFX() {
        super(Tile.lava.tex);
    }

    @Override
    public final void tick() {
        int i9;
        int i8;
        int i7;
        int i6;
        int i5;
        float f3;
        int i2;
        int i1 = 0;
        while (i1 < 16) {
            i2 = 0;
            while (i2 < 16) {
                f3 = 0.0f;
                int i4 = (int)(Mth.sin((float)i2 * (float)Math.PI * 2.0f / 16.0f) * 1.2f);
                i5 = (int)(Mth.sin((float)i1 * (float)Math.PI * 2.0f / 16.0f) * 1.2f);
                i6 = i1 - 1;
                while (i6 <= i1 + 1) {
                    i7 = i2 - 1;
                    while (i7 <= i2 + 1) {
                        i8 = i6 + i4 & 0xF;
                        i9 = i7 + i5 & 0xF;
                        f3 += this.red[i8 + (i9 << 4)];
                        ++i7;
                    }
                    ++i6;
                }
                this.green[i1 + (i2 << 4)] = f3 / 10.0f + (this.blue[(i1 & 0xF) + ((i2 & 0xF) << 4)] + this.blue[(i1 + 1 & 0xF) + ((i2 & 0xF) << 4)] + this.blue[(i1 + 1 & 0xF) + ((i2 + 1 & 0xF) << 4)] + this.blue[(i1 & 0xF) + ((i2 + 1 & 0xF) << 4)]) / 4.0f * 0.8f;
                int n2 = i1 + (i2 << 4);
                this.blue[n2] = this.blue[n2] + this.alpha[i1 + (i2 << 4)] * 0.01f;
                if (this.blue[i1 + (i2 << 4)] < 0.0f) {
                    this.blue[i1 + (i2 << 4)] = 0.0f;
                }
                int n3 = i1 + (i2 << 4);
                this.alpha[n3] = this.alpha[n3] - 0.06f;
                if (Math.random() < 0.005) {
                    this.alpha[i1 + (i2 << 4)] = 1.5f;
                }
                ++i2;
            }
            ++i1;
        }
        float[] f10 = this.green;
        this.green = this.red;
        this.red = f10;
        i2 = 0;
        while (i2 < 256) {
            f3 = this.red[i2] * 2.0f;
            if (f3 > 1.0f) {
                f3 = 1.0f;
            }
            if (f3 < 0.0f) {
                f3 = 0.0f;
            }
            i5 = (int)(f3 * 100.0f + 155.0f);
            i6 = (int)(f3 * f3 * 255.0f);
            i7 = (int)(f3 * f3 * f3 * f3 * 128.0f);
            if (this.anaglyphEnabled) {
                i8 = (i5 * 30 + i6 * 59 + i7 * 11) / 100;
                i9 = (i5 * 30 + i6 * 70) / 100;
                i1 = (i5 * 30 + i7 * 70) / 100;
                i5 = i8;
                i6 = i9;
                i7 = i1;
            }
            this.imageData[i2 << 2] = (byte)i5;
            this.imageData[(i2 << 2) + 1] = (byte)i6;
            this.imageData[(i2 << 2) + 2] = (byte)i7;
            this.imageData[(i2 << 2) + 3] = -1;
            ++i2;
        }
    }
}

