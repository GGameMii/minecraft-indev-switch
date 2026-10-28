/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.level.tile;

import java.util.Random;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.tile.Tile;

public final class SlabTile
extends Tile {
    private boolean blockType;

    public SlabTile(int blockID, boolean z2) {
        super(blockID, 6, Material.rock);
        this.blockType = z2;
        if (!z2) {
            this.setShape(0.0f, 0.0f, 0.0f, 1.0f, 0.5f, 1.0f);
        }
        this.setLightOpacity(255);
    }

    @Override
    public final int getTexture(int blockSide) {
        return blockSide <= 1 ? 6 : 5;
    }

    @Override
    public final boolean isSolid() {
        return this.blockType;
    }

    @Override
    public final void neighborChanged(Level world, int xCoord, int yCoord, int zCoord, int blockID) {
        if (this == Tile.stairSingle) {
            // empty if block
        }
    }

    @Override
    public final void onBlockAdded(Level world, int xCoord, int yCoord, int zCoord) {
        if (this != Tile.stairSingle) {
            super.onBlockAdded(world, xCoord, yCoord, zCoord);
        }
        if (world.getTile(xCoord, yCoord - 1, zCoord) == SlabTile.stairSingle.id) {
            world.setTile(xCoord, yCoord, zCoord, 0);
            world.setTile(xCoord, yCoord - 1, zCoord, Tile.stairDouble.id);
        }
    }

    @Override
    public final int idDropped(int i1, Random random) {
        return Tile.stairSingle.id;
    }

    @Override
    public final boolean renderAsNormalBlock() {
        return this.blockType;
    }

    @Override
    public final boolean shouldRenderFace(Level world, int xCoord, int yCoord, int zCoord, int i5) {
        return i5 == 1 ? true : (!super.shouldRenderFace(world, xCoord, yCoord, zCoord, i5) ? false : (i5 == 0 ? true : world.getTile(xCoord, yCoord, zCoord) != this.id));
    }
}

