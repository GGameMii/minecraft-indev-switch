/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.level.tile;

import java.util.Random;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.tile.Tile;

public final class OreTile
extends Tile {
    public OreTile(int blockID, int textureSlot) {
        super(blockID, textureSlot, Material.rock);
    }

    @Override
    public final int idDropped(int i1, Random random) {
        return this.id == Tile.oreCoal.id ? Item.coal.shiftedIndex : (this.id == Tile.oreDiamond.id ? Item.diamond.shiftedIndex : this.id);
    }

    @Override
    public final int quantityDropped(Random random) {
        return 1;
    }
}

