/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.level.tile;

import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.tile.Tile;

public final class OreStorageTile
extends Tile {
    public OreStorageTile(int blockID, int oreSideTexture) {
        super(blockID, Material.iron);
        this.tex = oreSideTexture;
    }

    @Override
    public final int getTexture(int blockSide) {
        return blockSide == 1 ? this.tex - 16 : (blockSide == 0 ? this.tex + 16 : this.tex);
    }
}

