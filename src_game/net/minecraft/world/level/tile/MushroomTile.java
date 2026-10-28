/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.level.tile;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.tile.FlowerTile;
import net.minecraft.world.level.tile.Tile;

public final class MushroomTile
extends FlowerTile {
    protected MushroomTile(int i1, int i2) {
        super(i1, i2);
        this.setShape(0.3f, 0.0f, 0.3f, 0.7f, 0.4f, 0.7f);
    }

    @Override
    protected final boolean canThisPlantGrowOnThisBlockID(int blockID) {
        return Tile.opaqueCubeLookup[blockID];
    }

    @Override
    public final boolean canBlockStay(Level world, int xCoord, int yCoord, int zCoord) {
        return world.getBlockLightValue(xCoord, yCoord, zCoord) <= 13 && Tile.opaqueCubeLookup[xCoord = world.getTile(xCoord, yCoord - 1, zCoord)];
    }
}

