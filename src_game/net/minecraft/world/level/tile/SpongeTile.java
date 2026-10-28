/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.level.tile;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.tile.Tile;

public final class SpongeTile
extends Tile {
    protected SpongeTile(int blockID) {
        super(19, Material.sponge);
        this.tex = 48;
    }

    @Override
    public final void onBlockAdded(Level world, int xCoord, int yCoord, int zCoord) {
        int i5 = xCoord - 2;
        while (i5 <= xCoord + 2) {
            int i6 = yCoord - 2;
            while (i6 <= yCoord + 2) {
                int i7 = zCoord - 2;
                while (i7 <= zCoord + 2) {
                    if (world.isWater(i5, i6, i7)) {
                        world.setTileNoNeighborChange(i5, i6, i7, 0);
                    }
                    ++i7;
                }
                ++i6;
            }
            ++i5;
        }
    }

    @Override
    public final void onBlockRemoved(Level world, int xCoord, int yCoord, int zCoord) {
        int i5 = xCoord - 2;
        while (i5 <= xCoord + 2) {
            int i6 = yCoord - 2;
            while (i6 <= yCoord + 2) {
                int i7 = zCoord - 2;
                while (i7 <= zCoord + 2) {
                    world.updateNeighborAt(i5, i6, i7, world.getTile(i5, i6, i7));
                    ++i7;
                }
                ++i6;
            }
            ++i5;
        }
    }
}

