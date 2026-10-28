/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.container;

import java.util.List;
import net.minecraft.world.IInventory;
import net.minecraft.world.container.IInvBasic;
import net.minecraft.world.container.Slot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class InventoryBasic
implements IInventory {
    private String inventoryTitle;
    private int slotsCount;
    private ItemStack[] inventoryContents;
    private List<Slot> slots;

    public InventoryBasic(String var1, int var2) {
        this.inventoryTitle = var1;
        this.slotsCount = var2;
        this.inventoryContents = new ItemStack[var2];
    }

    @Override
    public ItemStack getStackInSlot(int var1) {
        return this.inventoryContents[var1];
    }

    @Override
    public ItemStack decrStackSize(int id, int size) {
        if (this.inventoryContents[id] != null) {
            if (this.inventoryContents[id].stackSize <= size) {
                ItemStack var3 = this.inventoryContents[id];
                this.inventoryContents[id] = null;
                this.onInventoryChanged();
                return var3;
            }
            ItemStack var3 = this.inventoryContents[id].splitStack(size);
            if (this.inventoryContents[id].stackSize == 0) {
                this.inventoryContents[id] = null;
            }
            this.onInventoryChanged();
            return var3;
        }
        return null;
    }

    @Override
    public void setInventorySlotContents(int id, ItemStack item) {
        this.inventoryContents[id] = item;
        if (item != null && item.stackSize > this.getInventoryStackLimit()) {
            item.stackSize = this.getInventoryStackLimit();
        }
        this.onInventoryChanged();
    }

    @Override
    public int getSizeInventory() {
        return this.slotsCount;
    }

    @Override
    public String getInvName() {
        return this.inventoryTitle;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public void onInventoryChanged() {
        if (this.slots != null) {
            int id = 0;
            while (id < this.slots.size()) {
                ((IInvBasic)((Object)this.slots.get(id))).onInventoryChanged(this);
                ++id;
            }
        }
    }

    @Override
    public boolean canInteractWith(Player player) {
        return true;
    }
}

