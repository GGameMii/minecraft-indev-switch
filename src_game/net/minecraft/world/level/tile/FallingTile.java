/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.level.tile;

import java.util.Random;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.tile.Tile;

public class FallingTile
extends Tile {
    public FallingTile(int blockID, int textureSlot) {
        super(blockID, textureSlot, Material.sand);
        new Random();
    }

    @Override
    public final void onBlockAdded(Level world, int xCoord, int yCoord, int zCoord) {
        this.tryToFall(world, xCoord, yCoord, zCoord);
    }

    @Override
    public final void neighborChanged(Level world, int xCoord, int yCoord, int zCoord, int blockID) {
        this.tryToFall(world, xCoord, yCoord, zCoord);
    }

    private void tryToFall(Level world, int i2, int i3, int i4) {
        if (world.onlineLevel || !world.tilePhysics) {
            return;
        }
        int i5 = i3;
        while (true) {
            Material material10;
            int i8;
            int i6;
            if ((i6 = world.getTile(i2, i8 = i5 - 1, i4)) != 0 && i6 != Tile.fire.id && (material10 = Tile.tiles[i6].material) != Material.water && material10 != Material.lava || i5 < 0) {
                if (i5 < 0) {
                    world.setTileNoUpdate(i2, i3, i4, 0);
                }
                if (i5 != i3) {
                    i6 = world.getTile(i2, i5, i4);
                    if (i6 > 0 && Tile.tiles[i6].material != Material.air) {
                        world.setTileNoUpdate(i2, i5, i4, 0);
                    }
                    world.swap(i2, i3, i4, i2, i5, i4);
                }
                return;
            }
            if (world.getTile(i2, --i5, i4) != Tile.fire.id) continue;
            world.setTileNoNeighborChange(i2, i5, i4, 0);
        }
    }
}

