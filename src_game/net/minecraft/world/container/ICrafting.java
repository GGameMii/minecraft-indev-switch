/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.container;

import java.util.List;
import net.minecraft.world.container.Container;
import net.minecraft.world.item.ItemStack;

public interface ICrafting {
    public void updateCraftingInventory(Container var1, List<ItemStack> var2);

    public void func_20159_a(Container var1, int var2, ItemStack var3);

    public void updateCraftingInventoryInfo(Container var1, int var2, int var3);
}

