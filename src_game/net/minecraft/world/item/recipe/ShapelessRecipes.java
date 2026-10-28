/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.item.recipe;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.world.container.InventoryCrafting;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.recipe.IRecipe;

public class ShapelessRecipes
implements IRecipe {
    private final ItemStack recipeOutput;
    private final List<ItemStack> recipeItems;

    public ShapelessRecipes(ItemStack var1, List<ItemStack> var2) {
        this.recipeOutput = var1;
        this.recipeItems = var2;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return this.recipeOutput;
    }

    @Override
    public boolean matches(InventoryCrafting var1) {
        ArrayList<ItemStack> var2 = new ArrayList<ItemStack>(this.recipeItems);
        int var3 = 0;
        while (var3 < 3) {
            int var4 = 0;
            while (var4 < 3) {
                ItemStack var5 = var1.func_21103_b(var4, var3);
                if (var5 != null) {
                    boolean var6 = false;
                    for (ItemStack var8 : var2) {
                        if (var5.itemID != var8.itemID || var8.itemDamage != -1 && var5.itemDamage != var8.itemDamage) continue;
                        var6 = true;
                        var2.remove(var8);
                        break;
                    }
                    if (!var6) {
                        return false;
                    }
                }
                ++var4;
            }
            ++var3;
        }
        return var2.isEmpty();
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting var1) {
        return this.recipeOutput.copy();
    }

    @Override
    public int getRecipeSize() {
        return this.recipeItems.size();
    }
}

