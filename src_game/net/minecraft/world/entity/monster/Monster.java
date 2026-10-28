/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.entity.monster;

import com.mojang.nbt.NBTTagCompound;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;

public class Monster
extends Mob {
    protected int attackStrength = 2;

    public Monster(Level world1) {
        super(world1);
        this.health = 20;
    }

    @Override
    public void livingTick() {
        if (this.getEntityBrightness(1.0f) > 0.5f) {
            this.entityAge += 2;
        }
        super.livingTick();
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level.difficultySetting == 0 && !this.level.onlineLevel) {
            this.remove();
        }
    }

    @Override
    protected Entity findPlayerToAttack() {
        return this.level.getClosestPlayerToEntity(this, 256.0f);
    }

    @Override
    public final boolean hurt(Entity entity, int i2) {
        if (super.hurt(entity, i2)) {
            if (entity != this) {
                this.playerToAttack = entity;
            }
            return true;
        }
        return false;
    }

    @Override
    protected void attackEntity(Entity entity, float f2) {
        if (this.onlineEntity) {
            return;
        }
        if ((double)f2 < 2.5 && entity.bb.y1 > this.bb.y0 && entity.bb.y0 < this.bb.y1) {
            this.attackTime = 20;
            entity.hurt(this, this.attackStrength);
        }
    }

    @Override
    protected float getBlockPathWeight(int i1, int i2, int i3) {
        return 0.5f - this.level.getLightBrightness(i1, i2, i3);
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound nbtTagCompound) {
        super.writeEntityToNBT(nbtTagCompound);
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound nbtTagCompound) {
        super.readEntityFromNBT(nbtTagCompound);
    }

    @Override
    protected String getEntityString() {
        return "Monster";
    }

    @Override
    public final boolean getCanSpawnHere(float f1, float f2, float f3) {
        return this.level.getBlockLightValue((int)f1, (int)f2, (int)f3) <= this.rand.nextInt(8) && super.getCanSpawnHere(f1, f2, f3);
    }
}

