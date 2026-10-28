/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.item.recipe;

import java.util.Comparator;
import net.minecraft.world.item.recipe.CraftingManager;
import net.minecraft.world.item.recipe.IRecipe;
import net.minecraft.world.item.recipe.ShapedRecipes;
import net.minecraft.world.item.recipe.ShapelessRecipes;

final class RecipeSorter
implements Comparator<IRecipe> {
    RecipeSorter(CraftingManager craftingManager) {
    }

    @Override
    public final int compare(IRecipe var1, IRecipe var2) {
        return var1 instanceof ShapelessRecipes && var2 instanceof ShapedRecipes ? 1 : (var2 instanceof ShapelessRecipes && var1 instanceof ShapedRecipes ? -1 : (var2.getRecipeSize() < var1.getRecipeSize() ? -1 : (var2.getRecipeSize() > var1.getRecipeSize() ? 1 : 0)));
    }
}

