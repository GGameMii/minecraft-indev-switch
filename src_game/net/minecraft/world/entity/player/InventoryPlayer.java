/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.entity.player;

import net.minecraft.world.IInventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemArmor;
import net.minecraft.world.item.ItemStack;

public final class InventoryPlayer
implements IInventory {
    public ItemStack[] mainInventory = new ItemStack[36];
    public ItemStack[] armorInventory = new ItemStack[4];
    public int currentItem = 0;
    public Player player;
    private ItemStack itemStack;

    public InventoryPlayer(Player player) {
        this.player = player;
    }

    public final ItemStack getCurrentItem() {
        return this.mainInventory[this.currentItem];
    }

    private int getInventorySlotContainItem(int n2) {
        for (int i2 = 0; i2 < this.mainInventory.length; ++i2) {
            if (this.mainInventory[i2] == null || this.mainInventory[i2].itemID != n2) continue;
            return i2;
        }
        return -1;
    }

    private int storeItemStack() {
        for (int i2 = 0; i2 < this.mainInventory.length; ++i2) {
            if (this.mainInventory[i2] != null) continue;
            return i2;
        }
        return -1;
    }

    public final void getFirstEmptyStack(int n2) {
        if ((n2 = this.getInventorySlotContainItem(n2)) >= 0 && n2 < 9) {
            this.currentItem = n2;
        }
    }

    public final boolean consumeInventoryItem(int n2) {
        if ((n2 = this.getInventorySlotContainItem(n2)) < 0) {
            return false;
        }
        if (--this.mainInventory[n2].stackSize <= 0) {
            this.mainInventory[n2] = null;
        }
        return true;
    }

    public final boolean storePartialItemStack(ItemStack itemStack) {
        int n2;
        if (itemStack.itemDamage == 0) {
            int n3;
            int n4;
            n2 = itemStack.stackSize;
            int n5 = n4 = itemStack.itemID;
            InventoryPlayer inventoryPlayer = this;
            int n6 = 0;
            while (true) {
                if (n6 >= inventoryPlayer.mainInventory.length) {
                    n3 = -1;
                    break;
                }
                if (inventoryPlayer.mainInventory[n6] != null && inventoryPlayer.mainInventory[n6].itemID == n5 && inventoryPlayer.mainInventory[n6].stackSize < inventoryPlayer.mainInventory[n6].getItem().getItemStackLimit() && inventoryPlayer.mainInventory[n6].stackSize < 64) {
                    n3 = n6;
                    break;
                }
                ++n6;
            }
            int n7 = n3;
            if (n3 < 0) {
                n7 = this.storeItemStack();
            }
            if (n7 < 0) {
                n3 = n2;
            } else {
                if (this.mainInventory[n7] == null) {
                    this.mainInventory[n7] = new ItemStack(n4, 0);
                }
                n4 = n2;
                if (n2 > this.mainInventory[n7].getItem().getItemStackLimit() - this.mainInventory[n7].stackSize) {
                    n4 = this.mainInventory[n7].getItem().getItemStackLimit() - this.mainInventory[n7].stackSize;
                }
                if (n4 > 64 - this.mainInventory[n7].stackSize) {
                    n4 = 64 - this.mainInventory[n7].stackSize;
                }
                if (n4 == 0) {
                    n3 = n2;
                } else {
                    this.mainInventory[n7].stackSize += n4;
                    this.mainInventory[n7].animationsToGo = 5;
                    n3 = n2 -= n4;
                }
            }
            itemStack.stackSize = n3;
            if (itemStack.stackSize == 0) {
                return true;
            }
        }
        if ((n2 = this.storeItemStack()) >= 0) {
            this.mainInventory[n2] = itemStack;
            this.mainInventory[n2].animationsToGo = 5;
            return true;
        }
        return false;
    }

    @Override
    public final ItemStack decrStackSize(int n2, int n3) {
        ItemStack[] itemStackArray = this.mainInventory;
        if (n2 >= this.mainInventory.length) {
            itemStackArray = this.armorInventory;
            n2 -= this.mainInventory.length;
        }
        if (itemStackArray[n2] != null) {
            if (itemStackArray[n2].stackSize <= n3) {
                ItemStack itemStack = itemStackArray[n2];
                itemStackArray[n2] = null;
                return itemStack;
            }
            ItemStack itemStack = itemStackArray[n2].splitStack(n3);
            if (itemStackArray[n2].stackSize == 0) {
                itemStackArray[n2] = null;
            }
            return itemStack;
        }
        return null;
    }

    @Override
    public final void setInventorySlotContents(int n2, ItemStack itemStack) {
        ItemStack[] itemStackArray = this.mainInventory;
        if (n2 >= this.mainInventory.length) {
            itemStackArray = this.armorInventory;
            n2 -= this.mainInventory.length;
        }
        itemStackArray[n2] = itemStack;
    }

    @Override
    public final int getSizeInventory() {
        return this.mainInventory.length + 4;
    }

    @Override
    public final ItemStack getStackInSlot(int n2) {
        ItemStack[] itemStackArray = this.mainInventory;
        if (n2 >= this.mainInventory.length) {
            itemStackArray = this.armorInventory;
            n2 -= this.mainInventory.length;
        }
        return itemStackArray[n2];
    }

    @Override
    public final String getInvName() {
        return "Inventory";
    }

    @Override
    public final int getInventoryStackLimit() {
        return 64;
    }

    public final int getPlayerArmorValue() {
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        for (int i2 = 0; i2 < this.armorInventory.length; ++i2) {
            if (this.armorInventory[i2] == null || !(this.armorInventory[i2].getItem() instanceof ItemArmor)) continue;
            int n5 = this.armorInventory[i2].getMaxDamage();
            int n6 = this.armorInventory[i2].itemDamage;
            n6 = n5 - n6;
            n3 += n6;
            n4 += n5;
            n5 = ((ItemArmor)this.armorInventory[i2].getItem()).damageReduceAmount;
            n2 += n5;
        }
        if (n4 == 0) {
            return 0;
        }
        return (n2 - 1) * n3 / n4 + 1;
    }

    @Override
    public boolean canInteractWith(Player player) {
        return this.player.removed ? false : (double)player.getDistanceSqToEntity(this.player) <= 64.0;
    }

    @Override
    public void onInventoryChanged() {
    }

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public ItemStack getItemStack() {
        return this.itemStack;
    }

    public void setCurrentItem(int n2, int n3, boolean bl) {
        if (bl) {
            int n4 = this.getInventorySlotContainItem(n2);
            if (n4 >= 0) {
                this.currentItem = n4;
            }
            this.mainInventory[this.currentItem] = new ItemStack(n2, 1, n3);
        }
    }

    public void dropAllItems() {
        int n2;
        for (n2 = 0; n2 < this.mainInventory.length; ++n2) {
            if (this.mainInventory[n2] == null) continue;
            this.player.dropPlayerItemWithRandomChoice(this.mainInventory[n2], true);
            this.mainInventory[n2] = null;
        }
        for (n2 = 0; n2 < this.armorInventory.length; ++n2) {
            if (this.armorInventory[n2] == null) continue;
            this.player.dropPlayerItemWithRandomChoice(this.armorInventory[n2], true);
            this.armorInventory[n2] = null;
        }
    }
}

