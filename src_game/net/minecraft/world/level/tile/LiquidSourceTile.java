/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.level.tile;

import java.util.Random;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.tile.Tile;

public final class LiquidSourceTile
extends Tile {
    private int fluid;

    protected LiquidSourceTile(int blockID, int fluidType) {
        super(blockID, Tile.tiles[fluidType].tex, Material.water);
        this.fluid = fluidType;
        this.setTicking(true);
    }

    @Override
    public final void onBlockAdded(Level world, int xCoord, int yCoord, int zCoord) {
        if (world.onlineLevel) {
            return;
        }
        super.onBlockAdded(world, xCoord, yCoord, zCoord);
        if (world.getTile(xCoord - 1, yCoord, zCoord) == 0) {
            world.setTile(xCoord - 1, yCoord, zCoord, this.fluid);
        }
        if (world.getTile(xCoord + 1, yCoord, zCoord) == 0) {
            world.setTile(xCoord + 1, yCoord, zCoord, this.fluid);
        }
        if (world.getTile(xCoord, yCoord, zCoord - 1) == 0) {
            world.setTile(xCoord, yCoord, zCoord - 1, this.fluid);
        }
        if (world.getTile(xCoord, yCoord, zCoord + 1) == 0) {
            world.setTile(xCoord, yCoord, zCoord + 1, this.fluid);
        }
    }

    @Override
    public final void tick(Level world, int xCoord, int yCoord, int zCoord, Random random) {
        if (world.onlineLevel) {
            return;
        }
        super.tick(world, xCoord, yCoord, zCoord, random);
        if (world.getTile(xCoord - 1, yCoord, zCoord) == 0) {
            world.setTile(xCoord - 1, yCoord, zCoord, this.fluid);
        }
        if (world.getTile(xCoord + 1, yCoord, zCoord) == 0) {
            world.setTile(xCoord + 1, yCoord, zCoord, this.fluid);
        }
        if (world.getTile(xCoord, yCoord, zCoord - 1) == 0) {
            world.setTile(xCoord, yCoord, zCoord - 1, this.fluid);
        }
        if (world.getTile(xCoord, yCoord, zCoord + 1) == 0) {
            world.setTile(xCoord, yCoord, zCoord + 1, this.fluid);
        }
    }
}

