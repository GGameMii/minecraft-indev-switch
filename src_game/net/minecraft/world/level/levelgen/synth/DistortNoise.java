/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.level.levelgen.synth;

import net.minecraft.world.level.levelgen.synth.Synth;

public final class DistortNoise
extends Synth {
    private Synth source;
    private Synth distort;

    public DistortNoise(Synth s0, Synth s1) {
        this.source = s0;
        this.distort = s1;
    }

    @Override
    public final double generateNoise(double x2, double z2) {
        return this.source.generateNoise(x2 + this.distort.generateNoise(x2, z2), z2);
    }
}

