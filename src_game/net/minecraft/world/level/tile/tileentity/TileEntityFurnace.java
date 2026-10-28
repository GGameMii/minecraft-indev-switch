/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.level.tile.tileentity;

import com.mojang.nbt.NBTTagCompound;
import com.mojang.nbt.NBTTagList;
import net.minecraft.world.IInventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.tile.Tile;
import net.minecraft.world.level.tile.tileentity.TileEntity;

public final class TileEntityFurnace
extends TileEntity
implements IInventory {
    public ItemStack[] furnaceItemStacks = new ItemStack[3];
    public int furnaceBurnTime = 0;
    public int currentItemBurnTime = 0;
    public int furnaceCookTime = 0;

    @Override
    public final int getSizeInventory() {
        return this.furnaceItemStacks.length;
    }

    @Override
    public final ItemStack getStackInSlot(int i1) {
        return this.furnaceItemStacks[i1];
    }

    @Override
    public final ItemStack decrStackSize(int i1, int i2) {
        if (this.furnaceItemStacks[i1] != null) {
            if (this.furnaceItemStacks[i1].stackSize <= i2) {
                ItemStack itemStack3 = this.furnaceItemStacks[i1];
                this.furnaceItemStacks[i1] = null;
                return itemStack3;
            }
            ItemStack itemStack3 = this.furnaceItemStacks[i1].splitStack(i2);
            if (this.furnaceItemStacks[i1].stackSize == 0) {
                this.furnaceItemStacks[i1] = null;
            }
            return itemStack3;
        }
        return null;
    }

    @Override
    public final void setInventorySlotContents(int i1, ItemStack itemStack) {
        this.furnaceItemStacks[i1] = itemStack;
        if (itemStack != null && itemStack.stackSize > 64) {
            itemStack.stackSize = 64;
        }
    }

    @Override
    public final String getInvName() {
        return "Chest";
    }

    @Override
    public final void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);
        NBTTagList nBTTagList2 = nbtTagCompound.getTagList("Items");
        this.furnaceItemStacks = new ItemStack[this.furnaceItemStacks.length];
        int i3 = 0;
        while (i3 < nBTTagList2.tagCount()) {
            NBTTagCompound nBTTagCompound4 = (NBTTagCompound)nBTTagList2.tagAt(i3);
            byte b5 = nBTTagCompound4.getByte("Slot");
            if (b5 >= 0 && b5 < this.furnaceItemStacks.length) {
                this.furnaceItemStacks[b5] = new ItemStack(nBTTagCompound4);
            }
            ++i3;
        }
        this.furnaceBurnTime = nbtTagCompound.getShort("BurnTime");
        this.furnaceCookTime = nbtTagCompound.getShort("CookTime");
        this.currentItemBurnTime = TileEntityFurnace.getItemBurnTime(this.furnaceItemStacks[1]);
        System.out.println("Lit: " + this.furnaceBurnTime + "/" + this.currentItemBurnTime);
    }

    @Override
    public final void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);
        nbtTagCompound.setShort("BurnTime", (short)this.furnaceBurnTime);
        nbtTagCompound.setShort("CookTime", (short)this.furnaceCookTime);
        nbtTagCompound.setString("id", "Furnace");
        NBTTagList nBTTagList2 = new NBTTagList();
        int i3 = 0;
        while (i3 < this.furnaceItemStacks.length) {
            if (this.furnaceItemStacks[i3] != null) {
                NBTTagCompound nBTTagCompound4 = new NBTTagCompound();
                nBTTagCompound4.setByte("Slot", (byte)i3);
                this.furnaceItemStacks[i3].writeToNBT(nBTTagCompound4);
                nBTTagList2.setTag(nBTTagCompound4);
            }
            ++i3;
        }
        nbtTagCompound.setTag("Items", nBTTagList2);
    }

    @Override
    public final int getInventoryStackLimit() {
        return 64;
    }

    public final int getCookProgressScaled(int i1) {
        return this.furnaceCookTime * 24 / 200;
    }

    public final int getBurnTimeRemainingScaled(int i1) {
        return this.furnaceBurnTime * 12 / this.currentItemBurnTime;
    }

    public final boolean isBurning() {
        return this.furnaceBurnTime > 0;
    }

    @Override
    public final void updateEntity() {
        boolean z1;
        boolean bl = z1 = this.furnaceBurnTime > 0;
        if (this.furnaceBurnTime > 0) {
            --this.furnaceBurnTime;
        }
        if (!this.level.onlineLevel) {
            if (this.furnaceBurnTime == 0 && this.canSmelt()) {
                this.currentItemBurnTime = this.furnaceBurnTime = TileEntityFurnace.getItemBurnTime(this.furnaceItemStacks[1]);
                if (this.furnaceBurnTime > 0 && this.furnaceItemStacks[1] != null) {
                    --this.furnaceItemStacks[1].stackSize;
                    if (this.furnaceItemStacks[1].stackSize == 0) {
                        this.furnaceItemStacks[1] = null;
                    }
                }
            }
            if (this.isBurning() && this.canSmelt()) {
                ++this.furnaceCookTime;
                if (this.furnaceCookTime == 200) {
                    this.furnaceCookTime = 0;
                    if (this.canSmelt()) {
                        int i3 = TileEntityFurnace.smeltItem(this.furnaceItemStacks[0].getItem().shiftedIndex);
                        if (this.furnaceItemStacks[2] == null) {
                            this.furnaceItemStacks[2] = new ItemStack(i3, 1);
                        } else if (this.furnaceItemStacks[2].itemID == i3) {
                            ++this.furnaceItemStacks[2].stackSize;
                        }
                        --this.furnaceItemStacks[0].stackSize;
                        if (this.furnaceItemStacks[0].stackSize <= 0) {
                            this.furnaceItemStacks[0] = null;
                        }
                    }
                }
            } else {
                this.furnaceCookTime = 0;
            }
            if (z1 != this.furnaceBurnTime > 0) {
                boolean z10000 = this.furnaceBurnTime > 0;
                int i5 = this.z;
                int i4 = this.y;
                int i8 = this.x;
                Level world9 = this.level;
                boolean z2 = z10000;
                byte b6 = world9.getTileMetadata(i8, i4, i5);
                TileEntity tileEntity7 = world9.getTileEntity(i8, i4, i5);
                if (z2) {
                    world9.setTile(i8, i4, i5, Tile.furnaceActive.id);
                } else {
                    world9.setTile(i8, i4, i5, Tile.furnace.id);
                }
                world9.setTileMetadata(i8, i4, i5, b6);
                world9.setTileEntity(i8, i4, i5, tileEntity7);
            }
        }
    }

    private boolean canSmelt() {
        int i1;
        return this.furnaceItemStacks[0] == null ? false : ((i1 = TileEntityFurnace.smeltItem(this.furnaceItemStacks[0].getItem().shiftedIndex)) < 0 ? false : (this.furnaceItemStacks[2] == null ? true : (this.furnaceItemStacks[2].itemID != i1 ? false : (this.furnaceItemStacks[2].stackSize < 64 ? true : this.furnaceItemStacks[2].stackSize < Item.itemsList[i1].getItemStackLimit()))));
    }

    private static int smeltItem(int i0) {
        return i0 == Tile.oreIron.id ? Item.ingotIron.shiftedIndex : (i0 == Tile.oreGold.id ? Item.ingotGold.shiftedIndex : (i0 == Tile.oreDiamond.id ? Item.diamond.shiftedIndex : (i0 == Tile.sand.id ? Tile.glass.id : (i0 == Item.porkRaw.shiftedIndex ? Item.porkCooked.shiftedIndex : (i0 == Tile.cobblestone.id ? Tile.stone.id : -1)))));
    }

    private static int getItemBurnTime(ItemStack itemStack) {
        int itemStack1;
        return itemStack == null ? 0 : ((itemStack1 = itemStack.getItem().shiftedIndex) < 256 && Tile.tiles[itemStack1].material == Material.wood ? 300 : (itemStack1 == Item.stick.shiftedIndex ? 100 : (itemStack1 == Item.coal.shiftedIndex ? 1600 : 0)));
    }

    @Override
    public boolean canInteractWith(Player entityplayer) {
        if (this.level.getTileEntity(this.x, this.y, this.z) != null && this.level.getTileEntity(this.x, this.y, this.z) != this) {
            return false;
        }
        return (double)entityplayer.getDistanceSq((float)this.x + 0.5f, (float)this.y + 0.5f, (float)this.z + 0.5f) <= 64.0;
    }
}

