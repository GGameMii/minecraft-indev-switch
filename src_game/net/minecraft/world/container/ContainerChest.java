/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.container;

import net.minecraft.world.IInventory;
import net.minecraft.world.container.Container;
import net.minecraft.world.container.Slot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class ContainerChest
extends Container {
    private IInventory inventory;
    private int field_27282_b;

    public ContainerChest(IInventory iinventory, IInventory iinventory1) {
        this.inventory = iinventory1;
        this.field_27282_b = iinventory1.getSizeInventory() / 9;
        int i2 = (this.field_27282_b - 4) * 18;
        int j2 = 0;
        while (j2 < this.field_27282_b) {
            int i1 = 0;
            while (i1 < 9) {
                this.addSlot(new Slot(iinventory1, i1 + j2 * 9, 8 + i1 * 18, 18 + j2 * 18));
                ++i1;
            }
            ++j2;
        }
        int k2 = 0;
        while (k2 < 3) {
            int j1 = 0;
            while (j1 < 9) {
                this.addSlot(new Slot(iinventory, j1 + k2 * 9 + 9, 8 + j1 * 18, 103 + k2 * 18 + i2));
                ++j1;
            }
            ++k2;
        }
        int l2 = 0;
        while (l2 < 9) {
            this.addSlot(new Slot(iinventory, l2, 8 + l2 * 18, 161 + i2));
            ++l2;
        }
    }

    @Override
    public boolean isUsableByPlayer(Player player) {
        return this.inventory.canInteractWith(player);
    }

    @Override
    public ItemStack getStackInSlot(int i2) {
        ItemStack copy = null;
        Slot slot = (Slot)this.slots.get(i2);
        if (slot != null && slot.getHasStack()) {
            ItemStack item = slot.getStack();
            copy = item.copy();
            if (i2 < this.field_27282_b * 9) {
                this.func_28125_a(item, this.field_27282_b * 9, this.slots.size(), true);
            } else {
                this.func_28125_a(item, 0, this.field_27282_b * 9, false);
            }
            if (item.stackSize == 0) {
                slot.putStack(null);
            } else {
                slot.onSlotChanged();
            }
        }
        return copy;
    }
}

