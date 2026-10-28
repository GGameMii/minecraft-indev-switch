/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.level.tile;

import java.util.Random;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.tile.Tile;

public final class WoodTile
extends Tile {
    protected WoodTile(int blockID) {
        super(17, Material.wood);
        this.tex = 20;
    }

    @Override
    public final int quantityDropped(Random random) {
        return 1;
    }

    @Override
    public final int idDropped(int i1, Random random) {
        return Tile.wood.id;
    }

    @Override
    public final int getTexture(int blockSide) {
        return blockSide == 1 ? 21 : (blockSide == 0 ? 21 : 20);
    }
}

