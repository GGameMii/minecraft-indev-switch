/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.entity.monster;

import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class Zombie
extends Monster {
    public Zombie(Level world1) {
        super(world1);
        this.texture = "/mob/zombie.png";
        this.moveSpeed = 0.5f;
        this.attackStrength = 5;
    }

    @Override
    public final void livingTick() {
        if (this.onlineEntity) {
            super.livingTick();
            return;
        }
        if (this.level.skylightSubtracted > 7) {
            float f1 = this.getEntityBrightness(1.0f);
            if (f1 > 0.5f && this.level.canBlockSeeTheSky((int)this.x, (int)this.y, (int)this.z) && this.rand.nextFloat() * 30.0f < (f1 - 0.4f) * 2.0f) {
                this.fire = 300;
            }
        }
        super.livingTick();
    }

    @Override
    protected final String getEntityString() {
        return "Zombie";
    }

    @Override
    protected final int scoreValue() {
        return Item.feather.shiftedIndex;
    }
}

