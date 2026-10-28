/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.entity.monster;

import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class GiantZombie
extends Monster {
    public GiantZombie(Level level) {
        super(level);
        this.texture = "/mob/zombie.png";
        this.moveSpeed = 0.5f;
        this.attackStrength = 50;
        this.health *= 10;
        this.heightOffset *= 6.0f;
        this.setSize(this.bbWidth * 6.0f, this.bbHeight * 6.0f);
    }

    @Override
    protected final float getBlockPathWeight(int x2, int y2, int z2) {
        return this.level.getLightBrightness(x2, y2, z2) - 0.5f;
    }

    @Override
    protected final String getEntityString() {
        return "Giant";
    }
}

