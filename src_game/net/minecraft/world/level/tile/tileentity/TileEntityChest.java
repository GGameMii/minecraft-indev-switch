/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.level.tile.tileentity;

import com.mojang.nbt.NBTTagCompound;
import com.mojang.nbt.NBTTagList;
import net.minecraft.world.IInventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.tile.tileentity.TileEntity;

public final class TileEntityChest
extends TileEntity
implements IInventory {
    private ItemStack[] chestContents = new ItemStack[36];

    @Override
    public final int getSizeInventory() {
        return 27;
    }

    @Override
    public final ItemStack getStackInSlot(int var1) {
        return this.chestContents[var1];
    }

    @Override
    public final ItemStack decrStackSize(int var1, int var2) {
        if (this.chestContents[var1] != null) {
            if (this.chestContents[var1].stackSize <= var2) {
                ItemStack var3 = this.chestContents[var1];
                this.chestContents[var1] = null;
                return var3;
            }
            ItemStack var3 = this.chestContents[var1].splitStack(var2);
            if (this.chestContents[var1].stackSize == 0) {
                this.chestContents[var1] = null;
            }
            return var3;
        }
        return null;
    }

    @Override
    public final void setInventorySlotContents(int var1, ItemStack var2) {
        this.chestContents[var1] = var2;
        if (var2 != null && var2.stackSize > 64) {
            var2.stackSize = 64;
        }
    }

    @Override
    public final String getInvName() {
        return "Chest";
    }

    @Override
    public final void readFromNBT(NBTTagCompound var1) {
        NBTTagList var5 = var1.getTagList("Items");
        this.chestContents = new ItemStack[27];
        int var2 = 0;
        while (var2 < var5.tagCount()) {
            NBTTagCompound var3 = (NBTTagCompound)var5.tagAt(var2);
            int var4 = var3.getByte("Slot") & 0xFF;
            if (var4 >= 0 && var4 < this.chestContents.length) {
                this.chestContents[var4] = new ItemStack(var3);
            }
            ++var2;
        }
    }

    @Override
    public final void writeToNBT(NBTTagCompound var1) {
        var1.setString("id", "Chest");
        NBTTagList var2 = new NBTTagList();
        int var3 = 0;
        while (var3 < this.chestContents.length) {
            if (this.chestContents[var3] != null) {
                NBTTagCompound var4 = new NBTTagCompound();
                var4.setByte("Slot", (byte)var3);
                this.chestContents[var3].writeToNBT(var4);
                var2.setTag(var4);
            }
            ++var3;
        }
        var1.setTag("Items", var2);
    }

    @Override
    public final int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean canInteractWith(Player entityplayer) {
        if (this.level.getTileEntity(this.x, this.y, this.z) != null && this.level.getTileEntity(this.x, this.y, this.z) != this) {
            return false;
        }
        return (double)entityplayer.getDistanceSq((float)this.x + 0.5f, (float)this.y + 0.5f, (float)this.z + 0.5f) <= 64.0;
    }
}

