/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.container;

import net.minecraft.world.IInventory;
import net.minecraft.world.container.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class InventoryCrafting
implements IInventory {
    private ItemStack[] stackList;
    private int field_21104_b;
    private Container eventHandler;

    public InventoryCrafting(Container var1, int var2, int var3) {
        int var4 = var2 * var3;
        this.stackList = new ItemStack[var4];
        this.eventHandler = var1;
        this.field_21104_b = var2;
    }

    @Override
    public int getSizeInventory() {
        return this.stackList.length;
    }

    @Override
    public ItemStack getStackInSlot(int var1) {
        return var1 >= this.getSizeInventory() ? null : this.stackList[var1];
    }

    public ItemStack func_21103_b(int var1, int var2) {
        if (var1 >= 0 && var1 < this.field_21104_b) {
            int var3 = var1 + var2 * this.field_21104_b;
            return this.getStackInSlot(var3);
        }
        return null;
    }

    @Override
    public String getInvName() {
        return "Crafting";
    }

    @Override
    public ItemStack decrStackSize(int var1, int var2) {
        if (this.stackList[var1] != null) {
            if (this.stackList[var1].stackSize <= var2) {
                ItemStack var3 = this.stackList[var1];
                this.stackList[var1] = null;
                this.eventHandler.onCraftMatrixChanged(this);
                return var3;
            }
            ItemStack var3 = this.stackList[var1].splitStack(var2);
            if (this.stackList[var1].stackSize == 0) {
                this.stackList[var1] = null;
            }
            this.eventHandler.onCraftMatrixChanged(this);
            return var3;
        }
        return null;
    }

    @Override
    public void setInventorySlotContents(int var1, ItemStack var2) {
        this.stackList[var1] = var2;
        this.eventHandler.onCraftMatrixChanged(this);
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public void onInventoryChanged() {
    }

    @Override
    public boolean canInteractWith(Player var1) {
        return true;
    }
}

