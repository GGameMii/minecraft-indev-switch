/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.level.levelgen.synth;

import java.util.Random;
import net.minecraft.world.level.levelgen.synth.Synth;
import util.Mth;

public final class PerlinNoise
extends Synth {
    private int[] permutations = new int[512];

    public PerlinNoise() {
        this(new Random());
    }

    public PerlinNoise(Random random1) {
        int i2 = 0;
        while (i2 < 256) {
            this.permutations[i2] = i2++;
        }
        i2 = 0;
        while (i2 < 256) {
            int i3 = random1.nextInt(256 - i2) + i2;
            int i4 = this.permutations[i2];
            this.permutations[i2] = this.permutations[i3];
            this.permutations[i3] = i4;
            this.permutations[i2 + 256] = this.permutations[i2];
            ++i2;
        }
    }

    private static double generateNoise(double d0) {
        return d0 * d0 * d0 * (d0 * (d0 * 6.0 - 15.0) + 10.0);
    }

    private static double lerp(double d0, double d2, double d4) {
        return d2 + d0 * (d4 - d2);
    }

    private static double grad(int i0, double d1, double d3, double d5) {
        double d8;
        double d2 = d8 = (i0 &= 0xF) < 8 ? d1 : d3;
        double d10 = i0 < 4 ? d3 : (i0 != 12 && i0 != 14 ? d5 : d1);
        return ((i0 & 1) == 0 ? d8 : -d8) + ((i0 & 2) == 0 ? d10 : -d10);
    }

    @Override
    public final double generateNoise(double x2, double z2) {
        double d10 = 0.0;
        double d8 = z2;
        int i2 = Mth.floor(x2) & 0xFF;
        int i21 = Mth.floor(z2) & 0xFF;
        int i4 = Mth.floor(0.0) & 0xFF;
        double d6 = x2 - (double)Mth.floor(x2);
        d8 -= (double)Mth.floor(d8);
        d10 = 0.0 - (double)Mth.floor(0.0);
        double d15 = PerlinNoise.generateNoise(d6);
        double d17 = PerlinNoise.generateNoise(d8);
        double d19 = PerlinNoise.generateNoise(d10);
        int i5 = this.permutations[i2] + i21;
        int i12 = this.permutations[i5] + i4;
        i5 = this.permutations[i5 + 1] + i4;
        i2 = this.permutations[i2 + 1] + i21;
        i21 = this.permutations[i2] + i4;
        i2 = this.permutations[i2 + 1] + i4;
        return PerlinNoise.lerp(d19, PerlinNoise.lerp(d17, PerlinNoise.lerp(d15, PerlinNoise.grad(this.permutations[i12], d6, d8, d10), PerlinNoise.grad(this.permutations[i21], d6 - 1.0, d8, d10)), PerlinNoise.lerp(d15, PerlinNoise.grad(this.permutations[i5], d6, d8 - 1.0, d10), PerlinNoise.grad(this.permutations[i2], d6 - 1.0, d8 - 1.0, d10))), PerlinNoise.lerp(d17, PerlinNoise.lerp(d15, PerlinNoise.grad(this.permutations[i12 + 1], d6, d8, d10 - 1.0), PerlinNoise.grad(this.permutations[i21 + 1], d6 - 1.0, d8, d10 - 1.0)), PerlinNoise.lerp(d15, PerlinNoise.grad(this.permutations[i5 + 1], d6, d8 - 1.0, d10 - 1.0), PerlinNoise.grad(this.permutations[i2 + 1], d6 - 1.0, d8 - 1.0, d10 - 1.0))));
    }
}

