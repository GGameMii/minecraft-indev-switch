/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.level.tile;

import java.util.Random;
import net.minecraft.world.IInventory;
import net.minecraft.world.InventoryLargeChest;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.tile.ContainerTile;
import net.minecraft.world.level.tile.Tile;
import net.minecraft.world.level.tile.tileentity.TileEntity;
import net.minecraft.world.level.tile.tileentity.TileEntityChest;

public final class ChestTile
extends ContainerTile {
    private Random random = new Random();

    protected ChestTile(int blockID) {
        super(54, Material.wood);
        this.tex = 26;
    }

    @Override
    public final int getTexture(Level world, int xCoord, int yCoord, int zCoord, int blockSide) {
        if (blockSide == 1) {
            return this.tex - 1;
        }
        if (blockSide == 0) {
            return this.tex - 1;
        }
        int i6 = world.getTile(xCoord, yCoord, zCoord - 1);
        int i7 = world.getTile(xCoord, yCoord, zCoord + 1);
        int i8 = world.getTile(xCoord - 1, yCoord, zCoord);
        int i9 = world.getTile(xCoord + 1, yCoord, zCoord);
        if (i6 != this.id && i7 != this.id) {
            if (i8 != this.id && i9 != this.id) {
                int b14 = 3;
                if (Tile.opaqueCubeLookup[i6] && !Tile.opaqueCubeLookup[i7]) {
                    b14 = 3;
                }
                if (Tile.opaqueCubeLookup[i7] && !Tile.opaqueCubeLookup[i6]) {
                    b14 = 2;
                }
                if (Tile.opaqueCubeLookup[i8] && !Tile.opaqueCubeLookup[i9]) {
                    b14 = 5;
                }
                if (Tile.opaqueCubeLookup[i9] && !Tile.opaqueCubeLookup[i8]) {
                    b14 = 4;
                }
                return blockSide == b14 ? this.tex + 1 : this.tex;
            }
            if (blockSide != 4 && blockSide != 5) {
                int i10 = 0;
                if (i8 == this.id) {
                    i10 = -1;
                }
                int i11 = world.getTile(i8 == this.id ? xCoord - 1 : xCoord + 1, yCoord, zCoord - 1);
                int world1 = world.getTile(i8 == this.id ? xCoord - 1 : xCoord + 1, yCoord, zCoord + 1);
                if (blockSide == 3) {
                    i10 = -1 - i10;
                }
                int xCoord1 = 3;
                if ((Tile.opaqueCubeLookup[i6] || Tile.opaqueCubeLookup[i11]) && !Tile.opaqueCubeLookup[i7] && !Tile.opaqueCubeLookup[world1]) {
                    xCoord1 = 3;
                }
                if ((Tile.opaqueCubeLookup[i7] || Tile.opaqueCubeLookup[world1]) && !Tile.opaqueCubeLookup[i6] && !Tile.opaqueCubeLookup[i11]) {
                    xCoord1 = 2;
                }
                return (blockSide == xCoord1 ? this.tex + 16 : this.tex + 32) + i10;
            }
            return this.tex;
        }
        if (blockSide != 2 && blockSide != 3) {
            int i10 = 0;
            if (i6 == this.id) {
                i10 = -1;
            }
            int i11 = world.getTile(xCoord - 1, yCoord, i6 == this.id ? zCoord - 1 : zCoord + 1);
            int world1 = world.getTile(xCoord + 1, yCoord, i6 == this.id ? zCoord - 1 : zCoord + 1);
            if (blockSide == 4) {
                i10 = -1 - i10;
            }
            int xCoord1 = 5;
            if ((Tile.opaqueCubeLookup[i8] || Tile.opaqueCubeLookup[i11]) && !Tile.opaqueCubeLookup[i9] && !Tile.opaqueCubeLookup[world1]) {
                xCoord1 = 5;
            }
            if ((Tile.opaqueCubeLookup[i9] || Tile.opaqueCubeLookup[world1]) && !Tile.opaqueCubeLookup[i8] && !Tile.opaqueCubeLookup[i11]) {
                xCoord1 = 4;
            }
            return (blockSide == xCoord1 ? this.tex + 16 : this.tex + 32) + i10;
        }
        return this.tex;
    }

    @Override
    public final int getTexture(int blockSide) {
        return blockSide == 1 ? this.tex - 1 : (blockSide == 0 ? this.tex - 1 : (blockSide == 3 ? this.tex + 1 : this.tex));
    }

    @Override
    public final boolean canPlaceBlockAt(Level world, int xCoord, int yCoord, int zCoord) {
        int i5 = 0;
        if (world.getTile(xCoord - 1, yCoord, zCoord) == this.id) {
            ++i5;
        }
        if (world.getTile(xCoord + 1, yCoord, zCoord) == this.id) {
            ++i5;
        }
        if (world.getTile(xCoord, yCoord, zCoord - 1) == this.id) {
            ++i5;
        }
        if (world.getTile(xCoord, yCoord, zCoord + 1) == this.id) {
            ++i5;
        }
        return i5 > 1 ? false : (this.isThereANeighborChest(world, xCoord - 1, yCoord, zCoord) ? false : (this.isThereANeighborChest(world, xCoord + 1, yCoord, zCoord) ? false : (this.isThereANeighborChest(world, xCoord, yCoord, zCoord - 1) ? false : !this.isThereANeighborChest(world, xCoord, yCoord, zCoord + 1))));
    }

    private boolean isThereANeighborChest(Level world, int xCoord, int yCoord, int zCoord) {
        return world.getTile(xCoord, yCoord, zCoord) != this.id ? false : (world.getTile(xCoord - 1, yCoord, zCoord) == this.id ? true : (world.getTile(xCoord + 1, yCoord, zCoord) == this.id ? true : (world.getTile(xCoord, yCoord, zCoord - 1) == this.id ? true : world.getTile(xCoord, yCoord, zCoord + 1) == this.id)));
    }

    @Override
    public final void onBlockRemoved(Level world, int x2, int y2, int z2) {
        if (world.onlineLevel) {
            return;
        }
        TileEntityChest chest = (TileEntityChest)world.getTileEntity(x2, y2, z2);
        int i2 = 0;
        while (i2 < chest.getSizeInventory()) {
            ItemStack item = chest.getStackInSlot(i2);
            if (item != null) {
                float f8 = this.random.nextFloat() * 0.8f + 0.1f;
                float f9 = this.random.nextFloat() * 0.8f + 0.1f;
                float f10 = this.random.nextFloat() * 0.8f + 0.1f;
                while (item.stackSize > 0) {
                    int q2 = this.random.nextInt(21) + 10;
                    if (q2 > item.stackSize) {
                        q2 = item.stackSize;
                    }
                    item.stackSize -= q2;
                    if (item.stackSize <= 0 || world.onlineLevel) continue;
                    ItemEntity ei = new ItemEntity(world, (float)x2 + f8, (float)y2 + f9, (float)z2 + f10, new ItemStack(item.itemID, q2, item.itemDamage));
                    ei.xd = (float)this.random.nextGaussian() * 0.05f;
                    ei.yd = (float)this.random.nextGaussian() * 0.05f + 0.2f;
                    ei.zd = (float)this.random.nextGaussian() * 0.05f;
                    world.spawnEntityInWorld(ei);
                }
            }
            ++i2;
        }
        super.onBlockRemoved(world, x2, y2, z2);
    }

    @Override
    public final boolean blockActivated(Level world, int xCoord, int yCoord, int zCoord, Player player) {
        IInventory object6 = (TileEntityChest)world.getTileEntity(xCoord, yCoord, zCoord);
        if (world.isSolidTile(xCoord, yCoord + 1, zCoord)) {
            return true;
        }
        if (world.getTile(xCoord - 1, yCoord, zCoord) == this.id && world.isSolidTile(xCoord - 1, yCoord + 1, zCoord)) {
            return true;
        }
        if (world.getTile(xCoord + 1, yCoord, zCoord) == this.id && world.isSolidTile(xCoord + 1, yCoord + 1, zCoord)) {
            return true;
        }
        if (world.getTile(xCoord, yCoord, zCoord - 1) == this.id && world.isSolidTile(xCoord, yCoord + 1, zCoord - 1)) {
            return true;
        }
        if (world.getTile(xCoord, yCoord, zCoord + 1) == this.id && world.isSolidTile(xCoord, yCoord + 1, zCoord + 1)) {
            return true;
        }
        if (world.getTile(xCoord - 1, yCoord, zCoord) == this.id) {
            object6 = new InventoryLargeChest("Large chest", (TileEntityChest)world.getTileEntity(xCoord - 1, yCoord, zCoord), object6);
        }
        if (world.getTile(xCoord + 1, yCoord, zCoord) == this.id) {
            object6 = new InventoryLargeChest("Large chest", object6, (TileEntityChest)world.getTileEntity(xCoord + 1, yCoord, zCoord));
        }
        if (world.getTile(xCoord, yCoord, zCoord - 1) == this.id) {
            object6 = new InventoryLargeChest("Large chest", (TileEntityChest)world.getTileEntity(xCoord, yCoord, zCoord - 1), object6);
        }
        if (world.getTile(xCoord, yCoord, zCoord + 1) == this.id) {
            object6 = new InventoryLargeChest("Large chest", object6, (TileEntityChest)world.getTileEntity(xCoord, yCoord, zCoord + 1));
        }
        if (world.onlineLevel) {
            return true;
        }
        player.displayGUIChest(object6);
        return true;
    }

    @Override
    protected final TileEntity getBlockEntity() {
        return new TileEntityChest();
    }
}

