/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.level.tile;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.tile.Tile;
import net.minecraft.world.level.tile.tileentity.TileEntity;

public abstract class ContainerTile
extends Tile {
    protected ContainerTile(int i1, Material material2) {
        super(i1, material2);
    }

    @Override
    public void onBlockAdded(Level world, int xCoord, int yCoord, int zCoord) {
        super.onBlockAdded(world, xCoord, yCoord, zCoord);
        world.setTileEntity(xCoord, yCoord, zCoord, this.getBlockEntity());
    }

    @Override
    public void onBlockRemoved(Level world, int xCoord, int yCoord, int zCoord) {
        super.onBlockRemoved(world, xCoord, yCoord, zCoord);
        world.removeTileEntity(xCoord, yCoord, zCoord);
    }

    protected abstract TileEntity getBlockEntity();
}

