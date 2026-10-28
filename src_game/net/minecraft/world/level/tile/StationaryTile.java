/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.level.tile;

import java.util.Random;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.tile.LiquidTile;
import net.minecraft.world.level.tile.Tile;

public final class StationaryTile
extends LiquidTile {
    protected StationaryTile(int i1, Material material2) {
        super(i1, material2);
        this.movingId = i1 - 1;
        this.stillId = i1;
        this.setTicking(false);
    }

    @Override
    public final void tick(Level world, int xCoord, int yCoord, int zCoord, Random random) {
    }

    @Override
    public final void neighborChanged(Level world, int xCoord, int yCoord, int zCoord, int blockID) {
        boolean z6 = false;
        if (this.canFlow(world, xCoord, yCoord - 1, zCoord)) {
            z6 = true;
        }
        if (!z6 && this.canFlow(world, xCoord - 1, yCoord, zCoord)) {
            z6 = true;
        }
        if (!z6 && this.canFlow(world, xCoord + 1, yCoord, zCoord)) {
            z6 = true;
        }
        if (!z6 && this.canFlow(world, xCoord, yCoord, zCoord - 1)) {
            z6 = true;
        }
        if (!z6 && this.canFlow(world, xCoord, yCoord, zCoord + 1)) {
            z6 = true;
        }
        if (blockID != 0) {
            Material material7 = Tile.tiles[blockID].material;
            if (this.material == Material.water && material7 == Material.lava || material7 == Material.water && this.material == Material.lava) {
                world.setTile(xCoord, yCoord, zCoord, Tile.stone.id);
                return;
            }
        }
        if (Tile.fire.getChanceOfNeighborsEncouragingFire(blockID)) {
            z6 = true;
        }
        if (z6) {
            world.setTileNoUpdate(xCoord, yCoord, zCoord, this.movingId);
            world.scheduleBlockUpdate(xCoord, yCoord, zCoord, this.movingId);
        }
    }
}

