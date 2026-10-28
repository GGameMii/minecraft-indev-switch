/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.container;

import net.minecraft.world.IInventory;
import net.minecraft.world.container.Slot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class SlotFurnace
extends Slot {
    public SlotFurnace(Player entityplayer, IInventory iinventory, int i2, int j2, int k2) {
        super(iinventory, i2, j2, k2);
    }

    @Override
    public boolean isItemValid(ItemStack itemstack) {
        return false;
    }

    @Override
    public void onPickupFromSlot(ItemStack stack) {
        super.onPickupFromSlot(stack);
    }
}

