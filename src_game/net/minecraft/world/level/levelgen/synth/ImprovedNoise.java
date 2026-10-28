/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.level.levelgen.synth;

import java.util.Random;
import net.minecraft.world.level.levelgen.synth.PerlinNoise;
import net.minecraft.world.level.levelgen.synth.Synth;

public final class ImprovedNoise
extends Synth {
    private PerlinNoise[] generatorCollection;
    private int octaves;

    public ImprovedNoise(Random rand, int octaves) {
        this.octaves = octaves;
        this.generatorCollection = new PerlinNoise[octaves];
        int i3 = 0;
        while (i3 < octaves) {
            this.generatorCollection[i3] = new PerlinNoise(rand);
            ++i3;
        }
    }

    @Override
    public final double generateNoise(double x2, double z2) {
        double d5 = 0.0;
        double d7 = 1.0;
        int i9 = 0;
        while (i9 < this.octaves) {
            d5 += this.generatorCollection[i9].generateNoise(x2 / d7, z2 / d7) * d7;
            d7 *= 2.0;
            ++i9;
        }
        return d5;
    }
}

