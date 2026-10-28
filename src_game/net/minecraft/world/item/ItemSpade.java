/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.item;

import net.minecraft.world.item.ItemTool;
import net.minecraft.world.level.tile.Tile;

public final class ItemSpade
extends ItemTool {
    private static Tile[] blocksEffectiveAgainst = new Tile[]{Tile.grass, Tile.dirt, Tile.sand, Tile.gravel};

    public ItemSpade(int n2, int n3) {
        super(n2, 1, n3, blocksEffectiveAgainst);
    }
}

