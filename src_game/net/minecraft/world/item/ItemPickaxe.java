/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.item;

import net.minecraft.world.item.ItemTool;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.tile.Tile;

public final class ItemPickaxe
extends ItemTool {
    private static Tile[] blocksEffectiveAgainst = new Tile[]{Tile.cobblestone, Tile.stairDouble, Tile.stairSingle, Tile.stone, Tile.cobblestoneMossy, Tile.oreIron, Tile.blockIron, Tile.oreCoal, Tile.blockGold, Tile.oreGold, Tile.oreDiamond, Tile.blockDiamond};
    private int harvestLevel;

    public ItemPickaxe(int n2, int n3) {
        super(n2, 2, n3, blocksEffectiveAgainst);
        this.harvestLevel = n3;
    }

    @Override
    public final boolean canHarvestBlock(Tile tile) {
        return tile == Tile.obsidian ? this.harvestLevel == 3 : (tile != Tile.blockDiamond && tile != Tile.oreDiamond ? (tile != Tile.blockGold && tile != Tile.oreGold ? (tile != Tile.blockIron && tile != Tile.oreIron ? (tile.material == Material.rock ? true : tile.material == Material.iron) : this.harvestLevel > 0) : this.harvestLevel >= 2) : this.harvestLevel >= 2);
    }
}

