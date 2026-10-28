/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.entity.animal;

import com.mojang.nbt.NBTTagCompound;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.tile.Tile;

public abstract class Animal
extends Mob {
    public Animal(Level level) {
        super(level);
    }

    @Override
    protected final float getBlockPathWeight(int x2, int y2, int z2) {
        return this.level.getTile(x2, y2 - 1, z2) == Tile.grass.id ? 10.0f : this.level.getLightBrightness(x2, y2, z2) - 0.5f;
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound nbt) {
        super.writeEntityToNBT(nbt);
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound nbt) {
        super.readEntityFromNBT(nbt);
    }

    @Override
    public final boolean getCanSpawnHere(float x2, float y2, float z2) {
        return this.level.getBlockLightValue((int)x2, (int)y2, (int)z2) > 8 && super.getCanSpawnHere(x2, y2, z2);
    }
}

