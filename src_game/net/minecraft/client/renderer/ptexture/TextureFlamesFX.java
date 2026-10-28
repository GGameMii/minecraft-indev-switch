/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.renderer.ptexture;

import net.minecraft.client.renderer.ptexture.TextureFX;
import net.minecraft.world.level.tile.Tile;

public final class TextureFlamesFX
extends TextureFX {
    private float[] currentFireFrame = new float[320];
    private float[] lastFireFrame = new float[320];

    public TextureFlamesFX(int i1) {
        super(Tile.fire.tex + (i1 << 4));
    }

    @Override
    public final void tick() {
        int i6;
        int i5;
        int i3;
        int i2;
        int i1 = 0;
        while (i1 < 16) {
            i2 = 0;
            while (i2 < 20) {
                i3 = 18;
                float f4 = this.currentFireFrame[i1 + ((i2 + 1) % 20 << 4)] * 18.0f;
                i5 = i1 - 1;
                while (i5 <= i1 + 1) {
                    i6 = i2;
                    while (i6 <= i2 + 1) {
                        if (i5 >= 0 && i6 >= 0 && i5 < 16 && i6 < 20) {
                            f4 += this.currentFireFrame[i5 + (i6 << 4)];
                        }
                        ++i3;
                        ++i6;
                    }
                    ++i5;
                }
                this.lastFireFrame[i1 + (i2 << 4)] = f4 / ((float)i3 * 1.06f);
                if (i2 >= 19) {
                    this.lastFireFrame[i1 + (i2 << 4)] = (float)(Math.random() * Math.random() * Math.random() * 4.0 + Math.random() * (double)0.1f + (double)0.2f);
                }
                ++i2;
            }
            ++i1;
        }
        float[] f9 = this.lastFireFrame;
        this.lastFireFrame = this.currentFireFrame;
        this.currentFireFrame = f9;
        i2 = 0;
        while (i2 < 256) {
            float f10 = this.currentFireFrame[i2] * 1.8f;
            if (f10 > 1.0f) {
                f10 = 1.0f;
            }
            if (f10 < 0.0f) {
                f10 = 0.0f;
            }
            i5 = (int)(f10 * 155.0f + 100.0f);
            i6 = (int)(f10 * f10 * 255.0f);
            int i7 = (int)(f10 * f10 * f10 * f10 * f10 * f10 * f10 * f10 * f10 * f10 * 255.0f);
            int s8 = 255;
            if (f10 < 0.5f) {
                s8 = 0;
            }
            if (this.anaglyphEnabled) {
                i1 = (i5 * 30 + i6 * 59 + i7 * 11) / 100;
                i3 = (i5 * 30 + i6 * 70) / 100;
                int i11 = (i5 * 30 + i7 * 70) / 100;
                i5 = i1;
                i6 = i3;
                i7 = i11;
            }
            this.imageData[i2 << 2] = (byte)i5;
            this.imageData[(i2 << 2) + 1] = (byte)i6;
            this.imageData[(i2 << 2) + 2] = (byte)i7;
            this.imageData[(i2 << 2) + 3] = (byte)s8;
            ++i2;
        }
    }
}

