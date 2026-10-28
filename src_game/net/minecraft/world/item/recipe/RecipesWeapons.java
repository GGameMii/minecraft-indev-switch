/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.item.recipe;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.recipe.CraftingManager;
import net.minecraft.world.level.tile.Tile;

public final class RecipesWeapons {
    private String[][] recipePatterns = new String[][]{{"X", "X", "#"}};
    private Object[][] recipeItems = new Object[][]{{Tile.planks, Tile.cobblestone, Item.ingotIron, Item.diamond, Item.ingotGold}, {Item.swordWood, Item.swordStone, Item.swordSteel, Item.swordDiamond, Item.swordGold}};

    public final void addRecipes(CraftingManager craftingManager) {
        int i2 = 0;
        while (i2 < this.recipeItems[0].length) {
            Object object3 = this.recipeItems[0][i2];
            int i4 = 0;
            while (i4 < this.recipeItems.length - 1) {
                Item item5 = (Item)this.recipeItems[i4 + 1][i2];
                craftingManager.addRecipe(new ItemStack(item5), this.recipePatterns[i4], Character.valueOf('#'), Item.stick, Character.valueOf('X'), object3);
                ++i4;
            }
            ++i2;
        }
        craftingManager.addRecipe(new ItemStack(Item.bow, 1), " #X", "# X", " #X", Character.valueOf('X'), Item.silk, Character.valueOf('#'), Item.stick);
        craftingManager.addRecipe(new ItemStack(Item.arrow, 4), "X", "#", "Y", Character.valueOf('Y'), Item.feather, Character.valueOf('X'), Item.ingotIron, Character.valueOf('#'), Item.stick);
    }
}

