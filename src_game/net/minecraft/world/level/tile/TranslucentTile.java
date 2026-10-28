/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.level.tile;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.tile.Tile;

public class TranslucentTile
extends Tile {
    private boolean localFlag;

    protected TranslucentTile(int blockID, int textureSlot, Material material, boolean localFlag) {
        super(blockID, textureSlot, material);
        this.localFlag = localFlag;
    }

    @Override
    public final boolean isSolid() {
        return false;
    }

    @Override
    public final boolean shouldRenderFace(Level world, int xCoord, int yCoord, int zCoord, int i5) {
        int i6 = world.getTile(xCoord, yCoord, zCoord);
        return !this.localFlag && i6 == this.id ? false : super.shouldRenderFace(world, xCoord, yCoord, zCoord, i5);
    }
}

