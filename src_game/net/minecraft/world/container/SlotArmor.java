/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.container;

import net.minecraft.world.IInventory;
import net.minecraft.world.container.ContainerPlayer;
import net.minecraft.world.container.Slot;
import net.minecraft.world.item.ItemArmor;
import net.minecraft.world.item.ItemStack;

class SlotArmor
extends Slot {
    final int armorType;
    final ContainerPlayer inventory;

    SlotArmor(ContainerPlayer var1, IInventory var2, int var3, int var4, int var5, int var6) {
        super(var2, var3, var4, var5);
        this.inventory = var1;
        this.armorType = var6;
    }

    @Override
    public int getSlotStackLimit() {
        return 1;
    }

    @Override
    public boolean isItemValid(ItemStack var1) {
        return var1.getItem() instanceof ItemArmor ? ((ItemArmor)var1.getItem()).armorType == this.armorType : false;
    }
}

