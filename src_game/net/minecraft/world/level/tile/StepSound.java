/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.level.tile;

public class StepSound {
    private String sound;
    public final float soundVolume;
    public final float soundPitch;

    public StepSound(String string, float f2, float f3) {
        this.sound = string;
        this.soundVolume = f2;
        this.soundPitch = f3;
    }

    public String stepSoundDir() {
        return "step." + this.sound;
    }

    public final String getSndName() {
        return "step." + this.sound;
    }
}

