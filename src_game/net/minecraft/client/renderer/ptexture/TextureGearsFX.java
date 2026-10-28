/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.renderer.ptexture;

import java.io.IOException;
import javax.imageio.ImageIO;
import net.minecraft.client.renderer.ptexture.TextureFX;
import net.minecraft.world.level.tile.Tile;
import util.Mth;

public class TextureGearsFX
extends TextureFX {
    private int gearRotation = 0;
    private int[] gearColor = new int[1024];
    private int[] gearMiddleColor = new int[1024];
    private int gearRotationDir;

    public TextureGearsFX(int i1) {
        super(Tile.cog.tex + i1);
        this.gearRotationDir = (i1 << 1) - 1;
        this.gearRotation = 2;
        try {
            ImageIO.read(TextureGearsFX.class.getResource("/misc/gear.png")).getRGB(0, 0, 32, 32, this.gearColor, 0, 32);
            ImageIO.read(TextureGearsFX.class.getResource("/misc/gearmiddle.png")).getRGB(0, 0, 16, 16, this.gearMiddleColor, 0, 16);
        }
        catch (IOException iOException2) {
            iOException2.printStackTrace();
        }
    }

    @Override
    public final void tick() {
        this.gearRotation = this.gearRotation + this.gearRotationDir & 0x3F;
        float f1 = Mth.sin((float)this.gearRotation / 64.0f * (float)Math.PI * 2.0f);
        float f2 = Mth.cos((float)this.gearRotation / 64.0f * (float)Math.PI * 2.0f);
        int i3 = 0;
        while (i3 < 16) {
            int i4 = 0;
            while (i4 < 16) {
                float f5 = ((float)i3 / 15.0f - 0.5f) * 31.0f;
                float f6 = ((float)i4 / 15.0f - 0.5f) * 31.0f;
                float f7 = f2 * f5 - f1 * f6;
                f5 = f2 * f6 + f1 * f5;
                int i11 = (int)(f7 + 16.0f);
                int i10 = (int)(f5 + 16.0f);
                int i12 = 0;
                if (i11 >= 0 && i10 >= 0 && i11 < 32 && i10 < 32) {
                    i12 = this.gearColor[i11 + (i10 << 5)];
                    i10 = this.gearMiddleColor[i3 + (i4 << 4)];
                    if (i10 >>> 24 > 128) {
                        i12 = i10;
                    }
                }
                i10 = i12 >> 16 & 0xFF;
                i11 = i12 >> 8 & 0xFF;
                int i8 = i12 & 0xFF;
                i12 = i12 >>> 24 > 128 ? 255 : 0;
                int i9 = i3 + (i4 << 4);
                this.imageData[i9 << 2] = (byte)i10;
                this.imageData[(i9 << 2) + 1] = (byte)i11;
                this.imageData[(i9 << 2) + 2] = (byte)i8;
                this.imageData[(i9 << 2) + 3] = (byte)i12;
                ++i4;
            }
            ++i3;
        }
    }
}

