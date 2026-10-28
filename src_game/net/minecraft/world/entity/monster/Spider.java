/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.entity.monster;

import com.mojang.nbt.NBTTagCompound;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import util.Mth;

public class Spider
extends Monster {
    public Spider(Level world1) {
        super(world1);
        this.texture = "/mob/spider.png";
        this.setSize(1.4f, 0.9f);
        this.moveSpeed = 0.8f;
    }

    @Override
    protected final Entity findPlayerToAttack() {
        Player player = this.level.getClosestPlayerToEntity(this, 256.0f);
        return this.getEntityBrightness(1.0f) < 0.5f && player != null ? player : null;
    }

    @Override
    protected final void attackEntity(Entity entity, float f2) {
        if (this.onlineEntity) {
            super.livingTick();
            return;
        }
        if (this.getEntityBrightness(1.0f) > 0.5f && this.rand.nextInt(100) == 0) {
            this.playerToAttack = null;
        } else if (f2 > 2.0f && f2 < 6.0f && this.rand.nextInt(10) == 0) {
            if (this.onGround) {
                f2 = entity.x - this.x;
                float entity1 = entity.z - this.z;
                float f3 = Mth.sqrt(f2 * f2 + entity1 * entity1);
                this.xd = f2 / f3 * 0.5f * 0.8f + this.xd * 0.2f;
                this.zd = entity1 / f3 * 0.5f * 0.8f + this.zd * 0.2f;
                this.yd = 0.4f;
                return;
            }
        } else {
            super.attackEntity(entity, f2);
        }
    }

    @Override
    protected final void writeEntityToNBT(NBTTagCompound nbtTagCompound) {
        super.writeEntityToNBT(nbtTagCompound);
    }

    @Override
    protected final void readEntityFromNBT(NBTTagCompound nbtTagCompound) {
        super.readEntityFromNBT(nbtTagCompound);
    }

    @Override
    protected final String getEntityString() {
        return "Spider";
    }

    @Override
    protected final int scoreValue() {
        return Item.silk.shiftedIndex;
    }
}

