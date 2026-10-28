/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.level.tile;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.tile.Tile;

public class TransparentTile
extends Tile {
    private boolean graphicsLevel = true;

    protected TransparentTile(int blockID, int textureSlot, Material material, boolean z4) {
        super(blockID, textureSlot, material);
    }

    @Override
    public final boolean isSolid() {
        return false;
    }

    @Override
    public final boolean shouldRenderFace(Level world, int xCoord, int yCoord, int zCoord, int i5) {
        int i6 = world.getTile(xCoord, yCoord, zCoord);
        return !this.graphicsLevel && i6 == this.id ? false : super.shouldRenderFace(world, xCoord, yCoord, zCoord, i5);
    }
}

