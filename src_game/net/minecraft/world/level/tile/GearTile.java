/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.level.tile;

import java.util.Random;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.tile.Tile;
import net.minecraft.world.phys.AABB;

public final class GearTile
extends Tile {
    protected GearTile(int blockID, int textureSlot) {
        super(55, 62, Material.circuits);
    }

    @Override
    public final AABB getAABB(int xCoord, int yCoord, int zCoord) {
        return null;
    }

    @Override
    public final boolean isSolid() {
        return false;
    }

    @Override
    public final boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public final int getRenderType() {
        return 5;
    }

    @Override
    public final int quantityDropped(Random random) {
        return 1;
    }

    @Override
    public final boolean mayPick() {
        return false;
    }
}

