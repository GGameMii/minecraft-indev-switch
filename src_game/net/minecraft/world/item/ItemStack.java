/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.item;

import com.mojang.nbt.NBTTagCompound;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.tile.Tile;

public final class ItemStack {
    public int stackSize = 0;
    public int animationsToGo;
    public int itemID;
    public int itemDamage;
    public NBTTagCompound stackTagCompound;

    public ItemStack(Tile tile) {
        this(tile, 1);
    }

    public ItemStack(Tile tile, int n2) {
        this(tile.id, n2);
    }

    public ItemStack(Item item) {
        this(item, 1);
    }

    public ItemStack(Item item, int n2) {
        this(item.shiftedIndex, n2);
    }

    public ItemStack(int n2) {
        this(n2, 1);
    }

    public ItemStack(int n2, int n3) {
        this.itemID = n2;
        this.stackSize = n3;
    }

    public ItemStack(Tile tile, int n2, int n3) {
        this(tile.id, n2, n3);
    }

    public ItemStack(int n2, int n3, int n4) {
        this.itemID = n2;
        this.stackSize = n3;
        this.itemDamage = n4;
    }

    public ItemStack(NBTTagCompound nBTTagCompound) {
        this.itemID = nBTTagCompound.getShort("id");
        this.stackSize = nBTTagCompound.getByte("Count");
        this.itemDamage = nBTTagCompound.getShort("Damage");
        if (nBTTagCompound.hasKey("tag")) {
            this.stackTagCompound = nBTTagCompound.getCompoundTag("tag");
        }
    }

    public final ItemStack splitStack(int n2) {
        this.stackSize -= n2;
        return new ItemStack(this.itemID, n2, this.itemDamage);
    }

    public final Item getItem() {
        return Item.itemsList[this.itemID];
    }

    public final NBTTagCompound writeToNBT(NBTTagCompound nBTTagCompound) {
        nBTTagCompound.setShort("id", (short)this.itemID);
        nBTTagCompound.setByte("Count", (byte)this.stackSize);
        nBTTagCompound.setShort("Damage", (short)this.itemDamage);
        if (this.stackTagCompound != null) {
            nBTTagCompound.setTag("tag", this.stackTagCompound);
        }
        return nBTTagCompound;
    }

    public int getMaxStackSize() {
        return this.getItem().maxStackSize;
    }

    public boolean isItemDamaged() {
        return this.isItemStackDamageable() && this.itemDamage > 0;
    }

    public boolean isItemStackDamageable() {
        return Item.itemsList[this.itemID].getMaxDamage() > 0;
    }

    public boolean getHasSubtypes() {
        return Item.itemsList[this.itemID].getHasSubtypes();
    }

    public boolean isStackable() {
        return this.getMaxStackSize() > 1 && (!this.isItemStackDamageable() || !this.isItemDamaged());
    }

    public final int getMaxDamage() {
        return Item.itemsList[this.itemID].getMaxDamage();
    }

    public final void damageItem(int n2, Entity entity) {
        if (this.isItemStackDamageable()) {
            this.itemDamage += n2;
            if (this.itemDamage > this.getMaxDamage()) {
                --this.stackSize;
                if (this.stackSize < 0) {
                    this.stackSize = 0;
                }
                this.itemDamage = 0;
            }
        }
    }

    public ItemStack copy() {
        return new ItemStack(this.itemID, this.stackSize, this.itemDamage);
    }

    public static ItemStack copyItemStack(ItemStack itemStack) {
        return itemStack == null ? null : itemStack.copy();
    }

    public ItemStack useItemRightClick(Level level, Player player) {
        return this.getItem().onItemRightClick(this, level, player);
    }

    public boolean useItem(Player player, Level level, int n2, int n3, int n4, int n5) {
        return this.getItem().onItemUse(this, level, n2, n3, n4, n5, player);
    }

    public boolean isStackEqual(ItemStack itemStack) {
        return this.itemID == itemStack.itemID && this.stackSize == itemStack.stackSize && this.itemDamage == itemStack.itemDamage;
    }

    public static boolean areItemStacksEqual(ItemStack itemStack, ItemStack itemStack2) {
        return itemStack == null && itemStack2 == null ? true : (itemStack != null && itemStack2 != null ? itemStack.isItemStackEqual(itemStack2) : false);
    }

    private boolean isItemStackEqual(ItemStack itemStack) {
        return this.stackSize != itemStack.stackSize ? false : (this.itemID != itemStack.itemID ? false : this.itemDamage == itemStack.itemDamage);
    }
}

