/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.container;

import net.minecraft.world.IInventory;
import net.minecraft.world.container.Container;
import net.minecraft.world.container.InventoryCraftResult;
import net.minecraft.world.container.InventoryCrafting;
import net.minecraft.world.container.Slot;
import net.minecraft.world.container.SlotCrafting;
import net.minecraft.world.entity.player.InventoryPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.recipe.CraftingManager;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.tile.Tile;

public class ContainerWorkbench
extends Container {
    public InventoryCrafting craftMatrix = new InventoryCrafting(this, 3, 3);
    public IInventory craftResult = new InventoryCraftResult();
    private Level level;
    private int x;
    private int y;
    private int z;

    public ContainerWorkbench(InventoryPlayer var1, Level var2, int var3, int var4, int var5) {
        int var7;
        this.level = var2;
        this.x = var3;
        this.y = var4;
        this.z = var5;
        this.addSlot(new SlotCrafting(var1.player, this.craftMatrix, this.craftResult, 0, 124, 35));
        int var6 = 0;
        while (var6 < 3) {
            var7 = 0;
            while (var7 < 3) {
                this.addSlot(new Slot(this.craftMatrix, var7 + var6 * 3, 30 + var7 * 18, 17 + var6 * 18));
                ++var7;
            }
            ++var6;
        }
        var6 = 0;
        while (var6 < 3) {
            var7 = 0;
            while (var7 < 9) {
                this.addSlot(new Slot(var1, var7 + var6 * 9 + 9, 8 + var7 * 18, 84 + var6 * 18));
                ++var7;
            }
            ++var6;
        }
        var6 = 0;
        while (var6 < 9) {
            this.addSlot(new Slot(var1, var6, 8 + var6 * 18, 142));
            ++var6;
        }
        this.onCraftMatrixChanged(this.craftMatrix);
    }

    @Override
    public void onCraftMatrixChanged(IInventory var1) {
        this.craftResult.setInventorySlotContents(0, CraftingManager.getInstance().findMatchingRecipe(this.craftMatrix));
    }

    @Override
    public void onCraftGuiClosed(Player player) {
        super.onCraftGuiClosed(player);
        if (!this.level.onlineLevel) {
            int i2 = 0;
            while (i2 < 9) {
                ItemStack item = this.craftMatrix.getStackInSlot(i2);
                if (item != null && item.stackSize > 0) {
                    player.dropPlayerItem(item);
                }
                ++i2;
            }
        }
    }

    @Override
    public boolean isUsableByPlayer(Player var1) {
        return this.level.getTile(this.x, this.y, this.z) != Tile.workbench.id ? false : (double)var1.getDistanceSq((float)this.x + 0.5f, (float)this.y + 0.5f, (float)this.z + 0.5f) <= 64.0;
    }

    @Override
    public ItemStack getStackInSlot(int var1) {
        ItemStack var2 = null;
        Slot var3 = (Slot)this.slots.get(var1);
        if (var3 != null && var3.getHasStack()) {
            ItemStack var4 = var3.getStack();
            var2 = var4.copy();
            if (var1 == 0) {
                this.func_28125_a(var4, 10, 46, true);
            } else if (var1 >= 10 && var1 < 37) {
                this.func_28125_a(var4, 37, 46, false);
            } else if (var1 >= 37 && var1 < 46) {
                this.func_28125_a(var4, 10, 37, false);
            } else {
                this.func_28125_a(var4, 10, 46, false);
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

