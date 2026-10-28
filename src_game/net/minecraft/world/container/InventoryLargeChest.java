/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.container;

import net.minecraft.world.IInventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public final class InventoryLargeChest
implements IInventory {
    private String name;
    private IInventory upperChest;
    private IInventory lowerChest;

    public InventoryLargeChest(String name, IInventory chest1, IInventory chest2) {
        this.name = name;
        this.upperChest = chest1;
        this.lowerChest = chest2;
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
    public final ItemStack getStackInSlot(int id) {
        if (id >= this.upperChest.getSizeInventory()) {
            return this.lowerChest.getStackInSlot(id - this.upperChest.getSizeInventory());
        }
        return this.upperChest.getStackInSlot(id);
    }

    @Override
    public final ItemStack decrStackSize(int i2, int i22) {
        if (i2 >= this.upperChest.getSizeInventory()) {
            return this.lowerChest.decrStackSize(i2 - this.upperChest.getSizeInventory(), i22);
        }
        return this.upperChest.decrStackSize(i2, i22);
    }

    @Override
    public final void setInventorySlotContents(int i2, ItemStack stack) {
        if (i2 >= this.upperChest.getSizeInventory()) {
            this.lowerChest.setInventorySlotContents(i2 - this.upperChest.getSizeInventory(), stack);
            return;
        }
        this.upperChest.setInventorySlotContents(i2, stack);
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
    public boolean canInteractWith(Player entityplayer) {
        return this.upperChest.canInteractWith(entityplayer) && this.lowerChest.canInteractWith(entityplayer);
    }
}

