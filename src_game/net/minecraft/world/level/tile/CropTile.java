/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.level.tile;

import java.util.Random;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.tile.FlowerTile;
import net.minecraft.world.level.tile.Tile;

public final class CropTile
extends FlowerTile {
    protected CropTile(int i1, int i2) {
        super(59, 88);
        this.tex = 88;
        this.setTicking(true);
        this.setShape(0.0f, 0.0f, 0.0f, 1.0f, 0.25f, 1.0f);
    }

    @Override
    protected final boolean canThisPlantGrowOnThisBlockID(int blockID) {
        return blockID == Tile.farmland.id;
    }

    @Override
    public final void tick(Level world, int xCoord, int yCoord, int zCoord, Random random) {
        byte b6;
        super.tick(world, xCoord, yCoord, zCoord, random);
        if (world.getBlockLightValue(xCoord, yCoord + 1, zCoord) >= 9 && (b6 = world.getTileMetadata(xCoord, yCoord, zCoord)) < 7) {
            int i11 = zCoord;
            int i10 = yCoord;
            int i9 = xCoord;
            Level world8 = world;
            float f12 = 1.0f;
            int i13 = world.getTile(xCoord, yCoord, zCoord - 1);
            int i14 = world.getTile(xCoord, yCoord, zCoord + 1);
            int i15 = world.getTile(xCoord - 1, yCoord, zCoord);
            int i16 = world.getTile(xCoord + 1, yCoord, zCoord);
            int i17 = world.getTile(xCoord - 1, yCoord, zCoord - 1);
            int i18 = world.getTile(xCoord + 1, yCoord, zCoord - 1);
            int i19 = world.getTile(xCoord + 1, yCoord, zCoord + 1);
            int i20 = world.getTile(xCoord - 1, yCoord, zCoord + 1);
            boolean z23 = i15 == this.id || i16 == this.id;
            boolean z22 = i13 == this.id || i14 == this.id;
            boolean z7 = i17 == this.id || i18 == this.id || i19 == this.id || i20 == this.id;
            i14 = xCoord - 1;
            while (i14 <= i9 + 1) {
                i16 = i11 - 1;
                while (i16 <= i11 + 1) {
                    i17 = world8.getTile(i14, i10 - 1, i16);
                    float f24 = 0.0f;
                    if (i17 == Tile.farmland.id) {
                        f24 = 1.0f;
                        if (world8.getTileMetadata(i14, i10 - 1, i16) > 0) {
                            f24 = 3.0f;
                        }
                    }
                    if (i14 != i9 || i16 != i11) {
                        f24 /= 4.0f;
                    }
                    f12 += f24;
                    ++i16;
                }
                ++i14;
            }
            if (z7 || z23 && z22) {
                f12 /= 2.0f;
            }
            if (random.nextInt((int)(100.0f / f12)) == 0) {
                int i21 = b6 + 1;
                world.setTileMetadata(xCoord, yCoord, zCoord, i21);
            }
        }
    }

    @Override
    public final int getTextureFromSideAndMetadata(int i1, int i2) {
        if (i2 < 0) {
            i2 = 7;
        }
        return this.tex + i2;
    }

    @Override
    public final int getRenderType() {
        return 6;
    }

    @Override
    public final void onBlockDestroyedByPlayer(Level world, int x2, int y2, int z2, int i5) {
        super.onBlockDestroyedByPlayer(world, x2, y2, z2, i5);
        int i2 = 0;
        while (i2 < 3) {
            if (world.random.nextInt(15) <= i5 && !world.onlineLevel) {
                float ox = world.random.nextFloat() * 0.7f + 0.15f;
                float oy = world.random.nextFloat() * 0.7f + 0.15f;
                float oz = world.random.nextFloat() * 0.7f + 0.15f;
                ItemEntity item = new ItemEntity(world, (float)x2 + ox, (float)y2 + oy, (float)z2 + oz, new ItemStack(Item.seeds));
                item.delayBeforeCanPickup = 10;
                world.spawnEntityInWorld(item);
            }
            ++i2;
        }
    }

    @Override
    public final int idDropped(int i1, Random random) {
        System.out.println("Get resource: " + i1);
        return i1 == 7 ? Item.wheat.shiftedIndex : -1;
    }

    @Override
    public final int quantityDropped(Random random) {
        return 1;
    }
}

