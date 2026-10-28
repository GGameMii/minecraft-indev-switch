/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.container;

import net.minecraft.world.container.Container;
import net.minecraft.world.container.ICrafting;
import net.minecraft.world.container.Slot;
import net.minecraft.world.container.SlotFurnace;
import net.minecraft.world.entity.player.InventoryPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.tile.tileentity.TileEntityFurnace;

public class ContainerFurnace
extends Container {
    private TileEntityFurnace furnace;
    private int cookTime = 0;
    private int burnTime = 0;
    private int itemBurnTime = 0;

    public ContainerFurnace(InventoryPlayer inventoryplayer, TileEntityFurnace tileentityfurnace) {
        this.furnace = tileentityfurnace;
        this.addSlot(new Slot(tileentityfurnace, 0, 56, 17));
        this.addSlot(new Slot(tileentityfurnace, 1, 56, 53));
        this.addSlot(new SlotFurnace(inventoryplayer.player, tileentityfurnace, 2, 116, 35));
        int i2 = 0;
        while (i2 < 3) {
            int k2 = 0;
            while (k2 < 9) {
                this.addSlot(new Slot(inventoryplayer, k2 + i2 * 9 + 9, 8 + k2 * 18, 84 + i2 * 18));
                ++k2;
            }
            ++i2;
        }
        int j2 = 0;
        while (j2 < 9) {
            this.addSlot(new Slot(inventoryplayer, j2, 8 + j2 * 18, 142));
            ++j2;
        }
    }

    @Override
    public void onCraftGuiOpened(ICrafting iCrafting1) {
        super.onCraftGuiOpened(iCrafting1);
        iCrafting1.updateCraftingInventoryInfo(this, 0, this.furnace.furnaceCookTime);
        iCrafting1.updateCraftingInventoryInfo(this, 1, this.furnace.furnaceBurnTime);
        iCrafting1.updateCraftingInventoryInfo(this, 2, this.furnace.currentItemBurnTime);
    }

    @Override
    public void updateCraftingMatrix() {
        super.updateCraftingMatrix();
        int i2 = 0;
        while (i2 < this.containerListener.size()) {
            ICrafting icrafting = (ICrafting)this.containerListener.get(i2);
            if (this.cookTime != this.furnace.furnaceCookTime) {
                icrafting.updateCraftingInventoryInfo(this, 0, this.furnace.furnaceCookTime);
            }
            if (this.burnTime != this.furnace.furnaceBurnTime) {
                icrafting.updateCraftingInventoryInfo(this, 1, this.furnace.furnaceBurnTime);
            }
            if (this.itemBurnTime != this.furnace.currentItemBurnTime) {
                icrafting.updateCraftingInventoryInfo(this, 2, this.furnace.currentItemBurnTime);
            }
            ++i2;
        }
        this.cookTime = this.furnace.furnaceCookTime;
        this.burnTime = this.furnace.furnaceBurnTime;
        this.itemBurnTime = this.furnace.currentItemBurnTime;
    }

    @Override
    public void setContainerStatus(int type, int i2) {
        if (type == 0) {
            this.furnace.furnaceCookTime = i2;
        }
        if (type == 1) {
            this.furnace.furnaceBurnTime = i2;
        }
        if (type == 2) {
            this.furnace.currentItemBurnTime = i2;
        }
    }

    @Override
    public boolean isUsableByPlayer(Player entityplayer) {
        return this.furnace.canInteractWith(entityplayer);
    }

    @Override
    public ItemStack getStackInSlot(int i2) {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.slots.get(i2);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (i2 == 2) {
                this.func_28125_a(itemstack1, 3, 39, true);
            } else if (i2 >= 3 && i2 < 30) {
                this.func_28125_a(itemstack1, 30, 39, false);
            } else if (i2 >= 30 && i2 < 39) {
                this.func_28125_a(itemstack1, 3, 30, false);
            } else {
                this.func_28125_a(itemstack1, 3, 39, false);
            }
            if (itemstack1.stackSize == 0) {
                slot.putStack(null);
            } else {
                slot.onSlotChanged();
            }
            if (itemstack1.stackSize != itemstack.stackSize) {
                slot.onPickupFromSlot(itemstack1);
            } else {
                return null;
            }
        }
        return itemstack;
    }
}

