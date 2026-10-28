/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.item.recipe;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.recipe.CraftingManager;
import net.minecraft.world.level.tile.Tile;

public final class RecipesTools {
    private String[][] recipePatterns = new String[][]{{"XXX", " # ", " # "}, {"X", "#", "#"}, {"XX", "X#", " #"}, {"XX", " #", " #"}};
    private Object[][] recipeItems = new Object[][]{{Tile.planks, Tile.cobblestone, Item.ingotIron, Item.diamond, Item.ingotGold}, {Item.pickaxeWood, Item.pickaxeStone, Item.pickaxeSteel, Item.pickaxeDiamond, Item.pickaxeGold}, {Item.shovelWood, Item.shovelStone, Item.shovel, Item.shovelDiamond, Item.shovelGold}, {Item.axeWood, Item.axeStone, Item.axeSteel, Item.axeDiamond, Item.axeGold}, {Item.hoeWood, Item.hoeStone, Item.hoeSteel, Item.hoeDiamond, Item.hoeGold}};

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
    }
}

