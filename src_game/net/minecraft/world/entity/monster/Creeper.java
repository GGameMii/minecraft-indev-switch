/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.entity.monster;

import com.mojang.nbt.NBTTagCompound;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class Creeper
extends Monster {
    int timeSinceIgnited;
    int lastActiveTime;

    public Creeper(Level level) {
        super(level);
        this.texture = "/mob/creeper.png";
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, (byte)-1);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbt) {
        super.writeEntityToNBT(nbt);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbt) {
        super.readEntityFromNBT(nbt);
    }

    @Override
    protected String getEntityString() {
        return "Creeper";
    }

    protected void attackBlockedEntity(Entity e2, float dist) {
        if (!this.level.onlineLevel && this.timeSinceIgnited > 0) {
            this.setCreeperState(-1);
            --this.timeSinceIgnited;
            if (this.timeSinceIgnited < 0) {
                this.timeSinceIgnited = 0;
            }
        }
    }

    @Override
    public void tick() {
        this.lastActiveTime = this.timeSinceIgnited;
        if (this.level.onlineLevel) {
            int state = this.getCreeperState();
            if (state > 0 && this.timeSinceIgnited == 0) {
                this.level.playSoundAtEntity(this, "random.fuse", 1.0f, 0.5f);
            }
            this.timeSinceIgnited += state;
            if (this.timeSinceIgnited < 0) {
                this.timeSinceIgnited = 0;
            }
            if (this.timeSinceIgnited >= 30) {
                this.timeSinceIgnited = 30;
            }
        }
        super.tick();
        if (this.playerToAttack == null && this.timeSinceIgnited > 0) {
            this.setCreeperState(-1);
            --this.timeSinceIgnited;
            if (this.timeSinceIgnited < 0) {
                this.timeSinceIgnited = 0;
            }
        }
    }

    @Override
    protected String getHurtSound() {
        return "mob.creeper";
    }

    @Override
    protected String getDeathSound() {
        return "mob.creeperdeath";
    }

    @Override
    public void onDeath(Entity e2) {
        super.onDeath(e2);
    }

    @Override
    protected void attackEntity(Entity e2, float dist) {
        if (!this.level.onlineLevel) {
            int state = this.getCreeperState();
            if (state <= 0 && dist < 3.0f || state > 0 && dist < 7.0f) {
                if (this.timeSinceIgnited == 0) {
                    this.level.playSoundAtEntity(this, "random.fuse", 1.0f, 0.5f);
                }
                this.setCreeperState(1);
                ++this.timeSinceIgnited;
                if (this.timeSinceIgnited >= 30) {
                    this.actionExplode(this, 3.0f);
                    this.remove();
                }
                this.hasAttacked = true;
            } else {
                this.setCreeperState(-1);
                --this.timeSinceIgnited;
                if (this.timeSinceIgnited < 0) {
                    this.timeSinceIgnited = 0;
                }
            }
        }
    }

    protected void actionExplode(Entity entity, float dist) {
        if (!this.level.onlineLevel) {
            this.level.createExplosion(this, this.x, this.y, this.z, 3.0f);
        }
    }

    public float getFlash(float a2) {
        return ((float)this.lastActiveTime + (float)(this.timeSinceIgnited - this.lastActiveTime) * a2) / 28.0f;
    }

    @Override
    protected int scoreValue() {
        return Item.gunpowder.shiftedIndex;
    }

    private int getCreeperState() {
        return this.dataWatcher.getWatchableObjectByte(16);
    }

    private void setCreeperState(int state) {
        this.dataWatcher.updateObject(16, (byte)state);
    }
}

