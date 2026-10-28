/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.container;

import net.minecraft.world.IInventory;
import net.minecraft.world.container.Slot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class SlotCrafting
extends Slot {
    private final IInventory craftMatrix;
    private Player thePlayer;

    public SlotCrafting(Player var1, IInventory var2, IInventory var3, int var4, int var5, int var6) {
        super(var3, var4, var5, var6);
        this.thePlayer = var1;
        this.craftMatrix = var2;
    }

    @Override
    public boolean isItemValid(ItemStack var1) {
        return false;
    }

    @Override
    public void onPickupFromSlot(ItemStack var1) {
        int var2 = 0;
        while (var2 < this.craftMatrix.getSizeInventory()) {
            ItemStack var3 = this.craftMatrix.getStackInSlot(var2);
            if (var3 != null) {
                this.craftMatrix.decrStackSize(var2, 1);
                if (var3.getItem().hasContainerItem()) {
                    this.craftMatrix.setInventorySlotContents(var2, new ItemStack(var3.getItem().getContainerItem()));
                }
            }
            ++var2;
        }
    }
}

