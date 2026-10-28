/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.level.tile;

import java.util.Random;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.tile.ContainerTile;
import net.minecraft.world.level.tile.Tile;
import net.minecraft.world.level.tile.tileentity.TileEntity;
import net.minecraft.world.level.tile.tileentity.TileEntityFurnace;

public final class FurnaceTile
extends ContainerTile {
    private final boolean isActive;

    protected FurnaceTile(int blockID, boolean status) {
        super(blockID, Material.rock);
        this.isActive = status;
        this.tex = 45;
    }

    @Override
    public final void onBlockAdded(Level world, int xCoord, int yCoord, int zCoord) {
        super.onBlockAdded(world, xCoord, yCoord, zCoord);
        FurnaceTile.setDefaultDirection(world, xCoord, yCoord, zCoord);
    }

    private static void setDefaultDirection(Level world, int xCoord, int yCoord, int zCoord) {
        int i4 = world.getTile(xCoord, yCoord, zCoord - 1);
        int i5 = world.getTile(xCoord, yCoord, zCoord + 1);
        int i6 = world.getTile(xCoord - 1, yCoord, zCoord);
        int i7 = world.getTile(xCoord + 1, yCoord, zCoord);
        int b8 = 3;
        if (Tile.opaqueCubeLookup[i4] && !Tile.opaqueCubeLookup[i5]) {
            b8 = 3;
        }
        if (Tile.opaqueCubeLookup[i5] && !Tile.opaqueCubeLookup[i4]) {
            b8 = 2;
        }
        if (Tile.opaqueCubeLookup[i6] && !Tile.opaqueCubeLookup[i7]) {
            b8 = 5;
        }
        if (Tile.opaqueCubeLookup[i7] && !Tile.opaqueCubeLookup[i6]) {
            b8 = 4;
        }
        world.setTileMetadata(xCoord, yCoord, zCoord, b8);
    }

    @Override
    public final int getTexture(Level world, int xCoord, int yCoord, int zCoord, int blockSide) {
        if (blockSide == 1) {
            return Tile.stone.tex;
        }
        if (blockSide == 0) {
            return Tile.stone.tex;
        }
        byte b6 = world.getTileMetadata(xCoord, yCoord, zCoord);
        if (b6 == 0) {
            FurnaceTile.setDefaultDirection(world, xCoord, yCoord, zCoord);
            b6 = world.getTileMetadata(xCoord, yCoord, zCoord);
        }
        return blockSide != b6 ? this.tex : (this.isActive ? this.tex + 16 : this.tex - 1);
    }

    @Override
    public void randomTick(Level world, int xCoord, int yCoord, int zCoord, Random random) {
        if (this.isActive) {
            byte b6 = world.getTileMetadata(xCoord, yCoord, zCoord);
            float xCoord1 = (float)xCoord + 0.5f;
            float yCoord1 = (float)yCoord + random.nextFloat() * 6.0f / 16.0f;
            float zCoord1 = (float)zCoord + 0.5f;
            float random1 = random.nextFloat() * 0.6f - 0.3f;
            if (b6 == 4) {
                world.spawnParticle("smoke", xCoord1 - 0.52f, yCoord1, zCoord1 + random1, 0.0f, 0.0f, 0.0f);
                world.spawnParticle("flame", xCoord1 - 0.52f, yCoord1, zCoord1 + random1, 0.0f, 0.0f, 0.0f);
            } else if (b6 == 5) {
                world.spawnParticle("smoke", xCoord1 + 0.52f, yCoord1, zCoord1 + random1, 0.0f, 0.0f, 0.0f);
                world.spawnParticle("flame", xCoord1 + 0.52f, yCoord1, zCoord1 + random1, 0.0f, 0.0f, 0.0f);
            } else if (b6 == 2) {
                world.spawnParticle("smoke", xCoord1 + random1, yCoord1, zCoord1 - 0.52f, 0.0f, 0.0f, 0.0f);
                world.spawnParticle("flame", xCoord1 + random1, yCoord1, zCoord1 - 0.52f, 0.0f, 0.0f, 0.0f);
            } else if (b6 == 3) {
                world.spawnParticle("smoke", xCoord1 + random1, yCoord1, zCoord1 + 0.52f, 0.0f, 0.0f, 0.0f);
                world.spawnParticle("flame", xCoord1 + random1, yCoord1, zCoord1 + 0.52f, 0.0f, 0.0f, 0.0f);
            }
        }
    }

    @Override
    public int getTexture(int blockSide) {
        return blockSide == 1 ? Tile.stone.id : (blockSide == 0 ? Tile.stone.id : (blockSide == 3 ? this.tex - 1 : this.tex));
    }

    @Override
    public boolean blockActivated(Level world, int xCoord, int yCoord, int zCoord, Player player) {
        if (world.onlineLevel) {
            return true;
        }
        TileEntityFurnace world1 = (TileEntityFurnace)world.getTileEntity(xCoord, yCoord, zCoord);
        player.displayGUIFurnace(world1);
        return true;
    }

    @Override
    protected final TileEntity getBlockEntity() {
        return new TileEntityFurnace();
    }
}

