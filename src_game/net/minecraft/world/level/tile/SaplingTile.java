/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.level.tile;

import java.util.Random;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.tile.FlowerTile;

public final class SaplingTile
extends FlowerTile {
    protected SaplingTile(int i1, int i2) {
        super(6, 15);
        this.setShape(0.099999994f, 0.0f, 0.099999994f, 0.9f, 0.8f, 0.9f);
    }

    @Override
    public final void tick(Level world, int xCoord, int yCoord, int zCoord, Random random) {
        if (world.onlineLevel) {
            return;
        }
        super.tick(world, xCoord, yCoord, zCoord, random);
        if (world.getBlockLightValue(xCoord, yCoord + 1, zCoord) >= 9 && random.nextInt(5) == 0) {
            byte random1 = world.getTileMetadata(xCoord, yCoord, zCoord);
            if (random1 < 15) {
                world.setTileMetadata(xCoord, yCoord, zCoord, random1 + 1);
                return;
            }
            world.setTileNoUpdate(xCoord, yCoord, zCoord, 0);
            if (!world.growTrees(xCoord, yCoord, zCoord)) {
                world.setTileNoUpdate(xCoord, yCoord, zCoord, this.id);
            }
        }
    }
}

