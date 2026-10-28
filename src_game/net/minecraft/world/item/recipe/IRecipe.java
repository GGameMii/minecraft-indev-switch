/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.item.recipe;

import net.minecraft.world.container.InventoryCrafting;
import net.minecraft.world.item.ItemStack;

public interface IRecipe {
    public boolean matches(InventoryCrafting var1);

    public ItemStack getCraftingResult(InventoryCrafting var1);

    public int getRecipeSize();

    public ItemStack getRecipeOutput();
}

