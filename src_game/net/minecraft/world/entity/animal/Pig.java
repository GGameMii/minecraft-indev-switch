/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.entity.animal;

import com.mojang.nbt.NBTTagCompound;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class Pig
extends Animal {
    public Pig(Level world1) {
        super(world1);
        this.texture = "/mob/pig.png";
        this.setSize(0.9f, 0.9f);
    }

    @Override
    protected final void writeEntityToNBT(NBTTagCompound nbt) {
        super.writeEntityToNBT(nbt);
    }

    @Override
    protected final void readEntityFromNBT(NBTTagCompound nbt) {
        super.readEntityFromNBT(nbt);
    }

    @Override
    protected final String getEntityString() {
        return "Pig";
    }

    @Override
    protected final String getLivingSound() {
        return "mob.pig";
    }

    @Override
    protected final String getHurtSound() {
        return "mob.pig";
    }

    @Override
    protected final String getDeathSound() {
        return "mob.pigdeath";
    }

    @Override
    protected final int scoreValue() {
        return Item.porkRaw.shiftedIndex;
    }
}

