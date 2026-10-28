/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.level.tile;

import java.util.Random;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.tile.Tile;
import net.minecraft.world.level.tile.TransparentTile;

public final class LeavesTile
extends TransparentTile {
    protected LeavesTile(int blockID, int textureSlot) {
        super(18, 52, Material.leaves, true);
        this.setTicking(true);
    }

    @Override
    public final void tick(Level world, int xCoord, int yCoord, int zCoord, Random random) {
        if (world.onlineLevel) {
            return;
        }
        if (!world.getMaterial(xCoord, yCoord - 1, zCoord).isSolid()) {
            int i8 = xCoord - 2;
            while (i8 <= xCoord + 2) {
                int i6 = yCoord - 1;
                while (i6 <= yCoord) {
                    int i7 = zCoord - 2;
                    while (i7 <= zCoord + 2) {
                        if (world.getTile(i8, i6, i7) == Tile.wood.id) {
                            return;
                        }
                        ++i7;
                    }
                    ++i6;
                }
                ++i8;
            }
            this.dropBlockAsItem(world, xCoord, yCoord, zCoord, world.getTileMetadata(xCoord, yCoord, zCoord));
            world.setTile(xCoord, yCoord, zCoord, 0);
        }
    }

    @Override
    public final int quantityDropped(Random random) {
        return random.nextInt(10) == 0 ? 1 : 0;
    }

    @Override
    public final int idDropped(int i1, Random random) {
        return Tile.sapling.id;
    }
}

