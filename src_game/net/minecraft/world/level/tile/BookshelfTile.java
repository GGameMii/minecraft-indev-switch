/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.level.tile;

import java.util.Random;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.tile.Tile;

public final class BookshelfTile
extends Tile {
    public BookshelfTile(int blockID, int textureSlot) {
        super(47, 35, Material.wood);
    }

    @Override
    public final int getTexture(int blockSide) {
        return blockSide <= 1 ? 4 : this.tex;
    }

    @Override
    public final int quantityDropped(Random random) {
        return 0;
    }
}

