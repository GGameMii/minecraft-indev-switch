/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.container;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.minecraft.world.IInventory;
import net.minecraft.world.container.ICrafting;
import net.minecraft.world.container.Slot;
import net.minecraft.world.entity.player.InventoryPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public abstract class Container {
    public List<ItemStack> items = new ArrayList<ItemStack>();
    public List<Slot> slots = new ArrayList<Slot>();
    public int windowId = 0;
    private short changeUid = 0;
    protected List<ICrafting> containerListener = new ArrayList<ICrafting>();
    private Set<Player> playerEntities = new HashSet<Player>();

    protected void addSlot(Slot var1) {
        var1.slotNumber = this.slots.size();
        this.slots.add(var1);
        this.items.add(null);
    }

    public void updateCraftingResults() {
        int var1 = 0;
        while (var1 < this.slots.size()) {
            ItemStack var2 = this.slots.get(var1).getStack();
            ItemStack var3 = this.items.get(var1);
            if (!ItemStack.areItemStacksEqual(var3, var2)) {
                var3 = var2 == null ? null : var2.copy();
                this.items.set(var1, var3);
                int var4 = 0;
                while (var4 < this.containerListener.size()) {
                    this.containerListener.get(var4).func_20159_a(this, var1, var3);
                    ++var4;
                }
            }
            ++var1;
        }
    }

    public Slot getSlot(int var1) {
        return this.slots.get(var1);
    }

    public ItemStack getStackInSlot(int var1) {
        Slot var2 = this.slots.get(var1);
        return var2 != null ? var2.getStack() : null;
    }

    public ItemStack slotClick(int var1, int var2, boolean var3, Player player) {
        ItemStack var5 = null;
        if (var2 == 0 || var2 == 1) {
            InventoryPlayer inventory = player.inventory;
            if (var1 == -999) {
                if (inventory.getItemStack() != null && var1 == -999) {
                    if (var2 == 0 && inventory.getItemStack().stackSize > 0) {
                        player.dropPlayerItem(inventory.getItemStack());
                        inventory.setItemStack(null);
                    }
                    if (var2 == 1) {
                        player.dropPlayerItem(inventory.getItemStack().splitStack(1));
                        if (inventory.getItemStack().stackSize == 0) {
                            inventory.setItemStack(null);
                        }
                    }
                }
            } else if (var3) {
                ItemStack var7 = this.getStackInSlot(var1);
                if (var7 != null) {
                    int var10;
                    int var8 = var7.stackSize;
                    var5 = var7.copy();
                    Slot var9 = this.slots.get(var1);
                    if (var9 != null && var9.getStack() != null && (var10 = var9.getStack().stackSize) < var8) {
                        this.slotClick(var1, var2, var3, player);
                    }
                }
            } else {
                Slot var12 = this.slots.get(var1);
                if (var12 != null) {
                    int var10;
                    var12.onSlotChanged();
                    ItemStack var13 = var12.getStack();
                    ItemStack var14 = inventory.getItemStack();
                    if (var13 != null) {
                        var5 = var13.copy();
                    }
                    if (var13 == null) {
                        if (var14 != null && var12.isItemValid(var14)) {
                            int var102;
                            int n2 = var102 = var2 == 0 ? var14.stackSize : 1;
                            if (var102 > var12.getSlotStackLimit()) {
                                var102 = var12.getSlotStackLimit();
                            }
                            var12.putStack(var14.splitStack(var102));
                            if (var14.stackSize == 0) {
                                inventory.setItemStack(null);
                            }
                        }
                    } else if (var14 == null) {
                        int var103 = var2 == 0 ? var13.stackSize : (var13.stackSize + 1) / 2;
                        ItemStack var11 = var12.decrStackSize(var103);
                        inventory.setItemStack(var11);
                        if (var13.stackSize == 0) {
                            var12.putStack(null);
                        }
                        var12.onPickupFromSlot(inventory.getItemStack());
                    } else if (var12.isItemValid(var14)) {
                        if (var13.itemID != var14.itemID || var13.getHasSubtypes() && var13.itemDamage != var14.itemDamage) {
                            if (var14.stackSize <= var12.getSlotStackLimit()) {
                                var12.putStack(var14);
                                inventory.setItemStack(var13);
                            }
                        } else {
                            int var104;
                            int n3 = var104 = var2 == 0 ? var14.stackSize : 1;
                            if (var104 > var12.getSlotStackLimit() - var13.stackSize) {
                                var104 = var12.getSlotStackLimit() - var13.stackSize;
                            }
                            if (var104 > var14.getMaxStackSize() - var13.stackSize) {
                                var104 = var14.getMaxStackSize() - var13.stackSize;
                            }
                            var14.splitStack(var104);
                            if (var14.stackSize == 0) {
                                inventory.setItemStack(null);
                            }
                            var13.stackSize += var104;
                        }
                    } else if (!(var13.itemID != var14.itemID || var14.getMaxStackSize() <= 1 || var13.getHasSubtypes() && var13.itemDamage != var14.itemDamage || (var10 = var13.stackSize) <= 0 || var10 + var14.stackSize > var14.getMaxStackSize())) {
                        var14.stackSize += var10;
                        var13.splitStack(var10);
                        if (var13.stackSize == 0) {
                            var12.putStack(null);
                        }
                        var12.onPickupFromSlot(inventory.getItemStack());
                    }
                }
            }
        }
        return var5;
    }

    public void onCraftGuiClosed(Player player) {
        InventoryPlayer inventory = player.inventory;
        if (inventory.getItemStack() != null && inventory.getItemStack().stackSize > 0) {
            player.dropPlayerItem(inventory.getItemStack());
            inventory.setItemStack(null);
        }
    }

    public void onCraftMatrixChanged(IInventory var1) {
        this.updateCraftingResults();
    }

    public void putStackInSlot(int var1, ItemStack var2) {
        this.getSlot(var1).putStack(var2);
    }

    public void putStacksInSlots(ItemStack[] var1) {
        int var2 = 0;
        while (var2 < var1.length) {
            this.getSlot(var2).putStack(var1[var2]);
            ++var2;
        }
    }

    public void setContainerStatus(int type, int i2) {
    }

    public short backup(InventoryPlayer var1) {
        this.changeUid = (short)(this.changeUid + 1);
        return this.changeUid;
    }

    public void deleteBackup(short var1) {
    }

    public void rollbackToBackup(short var1) {
    }

    public abstract boolean isUsableByPlayer(Player var1);

    protected void func_28125_a(ItemStack var1, int var2, int var3, boolean var4) {
        ItemStack var7;
        Slot var6;
        int var5 = var2;
        if (var4) {
            var5 = var3 - 1;
        }
        if (var1.isStackable()) {
            while (var1.stackSize > 0 && (!var4 && var5 < var3 || var4 && var5 >= var2)) {
                var6 = this.slots.get(var5);
                var7 = var6.getStack();
                if (!(var7 == null || var7.itemID != var1.itemID || var1.getHasSubtypes() && var1.itemDamage != var7.itemDamage)) {
                    int var8 = var7.stackSize + var1.stackSize;
                    if (var8 <= var1.getMaxStackSize()) {
                        var1.stackSize = 0;
                        var7.stackSize = var8;
                        var6.onSlotChanged();
                    } else if (var7.stackSize < var1.getMaxStackSize()) {
                        var1.stackSize -= var1.getMaxStackSize() - var7.stackSize;
                        var7.stackSize = var1.getMaxStackSize();
                        var6.onSlotChanged();
                    }
                }
                if (var4) {
                    --var5;
                    continue;
                }
                ++var5;
            }
        }
        if (var1.stackSize > 0) {
            var5 = var4 ? var3 - 1 : var2;
            while (!var4 && var5 < var3 || var4 && var5 >= var2) {
                var6 = this.slots.get(var5);
                var7 = var6.getStack();
                if (var7 == null) {
                    var6.putStack(var1.copy());
                    var6.onSlotChanged();
                    var1.stackSize = 0;
                    break;
                }
                if (var4) {
                    --var5;
                    continue;
                }
                ++var5;
            }
        }
    }

    public void onCraftGuiOpened(ICrafting var1) {
        if (this.containerListener.contains(var1)) {
            throw new IllegalArgumentException("Listener already listening");
        }
        this.containerListener.add(var1);
        var1.updateCraftingInventory(this, this.getInventoryItems());
        this.updateCraftingMatrix();
    }

    public void removeContainerListener(ICrafting var1) {
        if (!this.containerListener.contains(var1)) {
            throw new IllegalArgumentException("Listener wasn't even listening!");
        }
        this.containerListener.remove(var1);
    }

    public List<ItemStack> getInventoryItems() {
        ArrayList<ItemStack> stacks = new ArrayList<ItemStack>();
        for (Slot slot : this.slots) {
            stacks.add(slot.getStack());
        }
        return stacks;
    }

    public void updateCraftingMatrix() {
        int var1 = 0;
        while (var1 < this.slots.size()) {
            ItemStack var2 = this.slots.get(var1).getStack();
            ItemStack var3 = this.items.get(var1);
            if (!ItemStack.areItemStacksEqual(var3, var2)) {
                var3 = var2 == null ? null : var2.copy();
                this.items.set(var1, var3);
                for (ICrafting o2 : this.containerListener) {
                    o2.func_20159_a(this, var1, var3);
                }
            }
            ++var1;
        }
    }

    public Slot func_20127_a(IInventory var1, int var2) {
        for (Slot o2 : this.slots) {
            Slot slot = o2;
            if (!slot.isHere(var1, var2)) continue;
            return slot;
        }
        return null;
    }

    public boolean getCanCraft(Player player) {
        return !this.playerEntities.contains(player);
    }

    public void setCanCraft(Player player, boolean canCraft) {
        if (canCraft) {
            this.playerEntities.remove(player);
        } else {
            this.playerEntities.add(player);
        }
    }
}

