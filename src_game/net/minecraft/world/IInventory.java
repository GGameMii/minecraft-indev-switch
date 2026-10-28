/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public interface IInventory {
    public int getSizeInventory();

    public ItemStack getStackInSlot(int var1);

    public ItemStack decrStackSize(int var1, int var2);

    public void setInventorySlotContents(int var1, ItemStack var2);

    public String getInvName();

    public int getInventoryStackLimit();

    public void onInventoryChanged();

    public boolean canInteractWith(Player var1);
}

