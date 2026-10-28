/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.level.tile;

import java.util.Random;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.tile.Tile;
import net.minecraft.world.phys.AABB;

public class FlowerTile
extends Tile {
    protected FlowerTile(int n2, int n3) {
        super(n2, Material.plants);
        this.tex = n3;
        this.setTicking(true);
        this.setShape(0.3f, 0.0f, 0.3f, 0.7f, 0.6f, 0.7f);
    }

    @Override
    public final boolean canPlaceBlockAt(Level level, int n2, int n3, int n4) {
        return this.canThisPlantGrowOnThisBlockID(level.getTile(n2, n3 - 1, n4));
    }

    protected boolean canThisPlantGrowOnThisBlockID(int n2) {
        return n2 == Tile.grass.id || n2 == Tile.dirt.id || n2 == Tile.farmland.id;
    }

    @Override
    public final void neighborChanged(Level level, int n2, int n3, int n4, int n5) {
        super.neighborChanged(level, n2, n3, n4, n5);
        this.checkFlowerChange(level, n2, n3, n4);
    }

    @Override
    public void tick(Level level, int n2, int n3, int n4, Random random) {
        this.checkFlowerChange(level, n2, n3, n4);
    }

    private void checkFlowerChange(Level level, int n2, int n3, int n4) {
        if (level.tilePhysics && !this.canBlockStay(level, n2, n3, n4) && !level.onlineLevel) {
            this.dropBlockAsItem(level, n2, n3, n4, level.getTileMetadata(n2, n3, n4));
            level.setTile(n2, n3, n4, 0);
        }
    }

    public boolean canBlockStay(Level level, int n2, int n3, int n4) {
        return (level.getBlockLightValue(n2, n3, n4) >= 8 || level.getBlockLightValue(n2, n3, n4) >= 4 && level.canBlockSeeTheSky(n2, n3, n4)) && this.canThisPlantGrowOnThisBlockID(level.getTile(n2, n3 - 1, n4));
    }

    @Override
    public final AABB getAABB(int n2, int n3, int n4) {
        return null;
    }

    @Override
    public final boolean isSolid() {
        return false;
    }

    @Override
    public final boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public int getRenderType() {
        return 1;
    }
}

