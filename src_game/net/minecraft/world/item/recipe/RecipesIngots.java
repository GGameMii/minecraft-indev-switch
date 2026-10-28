/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.item.recipe;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.recipe.CraftingManager;
import net.minecraft.world.level.tile.Tile;

public final class RecipesIngots {
    private Object[][] recipeItems = new Object[][]{{Tile.blockGold, Item.ingotGold}, {Tile.blockIron, Item.ingotIron}, {Tile.blockDiamond, Item.diamond}};

    public final void addRecipes(CraftingManager craftingManager) {
        int i2 = 0;
        while (i2 < this.recipeItems.length) {
            Tile block3 = (Tile)this.recipeItems[i2][0];
            Item item4 = (Item)this.recipeItems[i2][1];
            craftingManager.addRecipe(new ItemStack(block3), "###", "###", "###", Character.valueOf('#'), item4);
            craftingManager.addRecipe(new ItemStack(item4, 9), "#", Character.valueOf('#'), block3);
            ++i2;
        }
    }
}

