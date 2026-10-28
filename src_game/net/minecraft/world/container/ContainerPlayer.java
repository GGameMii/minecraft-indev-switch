/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.container;

import net.minecraft.world.IInventory;
import net.minecraft.world.container.Container;
import net.minecraft.world.container.InventoryCraftResult;
import net.minecraft.world.container.InventoryCrafting;
import net.minecraft.world.container.Slot;
import net.minecraft.world.container.SlotArmor;
import net.minecraft.world.container.SlotCrafting;
import net.minecraft.world.entity.player.InventoryPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.recipe.CraftingManager;

public class ContainerPlayer
extends Container {
    public InventoryCrafting craftMatrix = new InventoryCrafting(this, 2, 2);
    public IInventory craftResult = new InventoryCraftResult();
    public boolean isSinglePlayer = false;

    public ContainerPlayer(InventoryPlayer var1) {
        this(var1, true);
    }

    public ContainerPlayer(InventoryPlayer var1, boolean var2) {
        int var4;
        this.isSinglePlayer = var2;
        this.addSlot(new SlotCrafting(var1.player, this.craftMatrix, this.craftResult, 0, 144, 36));
        int var3 = 0;
        while (var3 < 2) {
            var4 = 0;
            while (var4 < 2) {
                this.addSlot(new Slot(this.craftMatrix, var4 + var3 * 2, 88 + var4 * 18, 26 + var3 * 18));
                ++var4;
            }
            ++var3;
        }
        var3 = 0;
        while (var3 < 4) {
            this.addSlot(new SlotArmor(this, var1, var1.getSizeInventory() - 1 - var3, 8, 8 + var3 * 18, var3));
            ++var3;
        }
        var3 = 0;
        while (var3 < 3) {
            var4 = 0;
            while (var4 < 9) {
                this.addSlot(new Slot(var1, var4 + (var3 + 1) * 9, 8 + var4 * 18, 84 + var3 * 18));
                ++var4;
            }
            ++var3;
        }
        var3 = 0;
        while (var3 < 9) {
            this.addSlot(new Slot(var1, var3, 8 + var3 * 18, 142));
            ++var3;
        }
        this.onCraftMatrixChanged(this.craftMatrix);
    }

    @Override
    public void onCraftMatrixChanged(IInventory var1) {
        this.craftResult.setInventorySlotContents(0, CraftingManager.getInstance().findMatchingRecipe(this.craftMatrix));
    }

    @Override
    public void onCraftGuiClosed(Player var1) {
        super.onCraftGuiClosed(var1);
        int var2 = 0;
        while (var2 < 4) {
            ItemStack var3 = this.craftMatrix.getStackInSlot(var2);
            if (var3 != null) {
                var1.dropPlayerItem(var3);
                this.craftMatrix.setInventorySlotContents(var2, null);
            }
            ++var2;
        }
    }

    @Override
    public boolean isUsableByPlayer(Player var1) {
        return true;
    }

    @Override
    public ItemStack getStackInSlot(int var1) {
        ItemStack var2 = null;
        Slot var3 = (Slot)this.slots.get(var1);
        if (var3 != null && var3.getHasStack()) {
            ItemStack var4 = var3.getStack();
            var2 = var4.copy();
            if (var1 == 0) {
                this.func_28125_a(var4, 9, 45, true);
            } else if (var1 >= 9 && var1 < 36) {
                this.func_28125_a(var4, 36, 45, false);
            } else if (var1 >= 36 && var1 < 45) {
                this.func_28125_a(var4, 9, 36, false);
            } else {
                this.func_28125_a(var4, 9, 45, false);
            }
            if (var4.stackSize == 0) {
                var3.putStack(null);
            } else {
                var3.onSlotChanged();
            }
            if (var4.stackSize == var2.stackSize) {
                return null;
            }
            var3.onPickupFromSlot(var4);
        }
        return var2;
    }
}

