/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.level.tile;

import net.minecraft.world.level.tile.StepSound;

final class StepSoundSand
extends StepSound {
    StepSoundSand(String string1, float f2, float f3) {
        super(string1, 1.0f, 1.0f);
    }

    @Override
    public final String stepSoundDir() {
        return "step.gravel";
    }
}

