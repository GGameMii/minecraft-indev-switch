/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.item;

import net.minecraft.world.item.ItemTool;
import net.minecraft.world.level.tile.Tile;

public final class ItemAxe
extends ItemTool {
    private static Tile[] blocksEffectiveAgainst = new Tile[]{Tile.planks, Tile.bookShelf, Tile.wood, Tile.chest};

    public ItemAxe(int n2, int n3) {
        super(n2, 3, n3, blocksEffectiveAgainst);
    }
}

