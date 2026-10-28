/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world;

import net.minecraft.world.IInventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public final class InventoryLargeChest
implements IInventory {
    private String name;
    private IInventory upperChest;
    private IInventory lowerChest;

    public InventoryLargeChest(String name, IInventory upperChest, IInventory lowerChest) {
        this.name = name;
        this.upperChest = upperChest;
        this.lowerChest = lowerChest;
    }

    @Override
    public final int getSizeInventory() {
        return this.upperChest.getSizeInventory() + this.lowerChest.getSizeInventory();
    }

    @Override
    public final String getInvName() {
        return this.name;
    }

    @Override
    public final ItemStack getStackInSlot(int i1) {
        return i1 >= this.upperChest.getSizeInventory() ? this.lowerChest.getStackInSlot(i1 - this.upperChest.getSizeInventory()) : this.upperChest.getStackInSlot(i1);
    }

    @Override
    public final ItemStack decrStackSize(int i1, int i2) {
        return i1 >= this.upperChest.getSizeInventory() ? this.lowerChest.decrStackSize(i1 - this.upperChest.getSizeInventory(), i2) : this.upperChest.decrStackSize(i1, i2);
    }

    @Override
    public final void setInventorySlotContents(int i1, ItemStack itemStack) {
        if (i1 >= this.upperChest.getSizeInventory()) {
            this.lowerChest.setInventorySlotContents(i1 - this.upperChest.getSizeInventory(), itemStack);
        } else {
            this.upperChest.setInventorySlotContents(i1, itemStack);
        }
    }

    @Override
    public final int getInventoryStackLimit() {
        return this.upperChest.getInventoryStackLimit();
    }

    @Override
    public final void onInventoryChanged() {
        this.upperChest.onInventoryChanged();
        this.lowerChest.onInventoryChanged();
    }

    @Override
    public boolean canInteractWith(Player player) {
        return this.upperChest.canInteractWith(player) && this.lowerChest.canInteractWith(player);
    }
}

