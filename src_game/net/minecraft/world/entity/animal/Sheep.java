/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.entity.animal;

import com.mojang.nbt.NBTTagCompound;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.tile.Tile;

public class Sheep
extends Animal {
    public Sheep(Level world1) {
        super(world1);
        this.texture = "/mob/sheep.png";
        this.setSize(0.9f, 1.3f);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, new Byte((byte)0));
    }

    public boolean getSheared() {
        return (this.dataWatcher.getWatchableObjectByte(16) & 0x10) != 0;
    }

    public void setSheared(boolean sheared) {
        byte b2 = this.dataWatcher.getWatchableObjectByte(16);
        if (sheared) {
            this.dataWatcher.updateObject(16, (byte)(b2 | 0x10));
        } else {
            this.dataWatcher.updateObject(16, (byte)(b2 & 0xFFFFFFEF));
        }
    }

    @Override
    public final boolean hurt(Entity entity, int i2) {
        if (!this.getSheared() && entity instanceof LivingEntity && !this.level.onlineLevel) {
            this.setSheared(true);
            int q2 = 1 + this.rand.nextInt(3);
            int i3 = 0;
            while (i3 < q2) {
                ItemEntity item = this.entityDropItem(Tile.clothGray.id, 1, 1.0f);
                item.yd += this.rand.nextFloat() * 0.05f;
                item.xd += (this.rand.nextFloat() - this.rand.nextFloat()) * 0.1f;
                item.zd += (this.rand.nextFloat() - this.rand.nextFloat()) * 0.1f;
                ++i3;
            }
        }
        return super.hurt(entity, i2);
    }

    @Override
    protected final void writeEntityToNBT(NBTTagCompound nbt) {
        super.writeEntityToNBT(nbt);
        nbt.setBoolean("Sheared", this.getSheared());
    }

    @Override
    protected final void readEntityFromNBT(NBTTagCompound nbt) {
        super.readEntityFromNBT(nbt);
        this.setSheared(nbt.getBoolean("Sheared"));
    }

    @Override
    protected final String getEntityString() {
        return "Sheep";
    }

    @Override
    protected final String getLivingSound() {
        return "mob.sheep";
    }

    @Override
    protected final String getHurtSound() {
        return "mob.sheep";
    }

    @Override
    protected final String getDeathSound() {
        return "mob.sheep";
    }
}

