/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.level.tile;

import java.util.Random;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.tile.Tile;

public final class TntTile
extends Tile {
    public TntTile(int blockID, int textureSlot) {
        super(46, 8, Material.tnt);
    }

    @Override
    public final int getTexture(int blockSide) {
        return blockSide == 0 ? this.tex + 2 : (blockSide == 1 ? this.tex + 1 : this.tex);
    }

    @Override
    public final int quantityDropped(Random random) {
        return 0;
    }

    @Override
    public final void onBlockDestroyedByExplosion(Level level, int x2, int y2, int z2) {
        if (level.onlineLevel) {
            return;
        }
        PrimedTnt tnt = new PrimedTnt(level, (float)x2 + 0.5f, (float)y2 + 0.5f, (float)z2 + 0.5f);
        tnt.fuse = level.random.nextInt(tnt.fuse / 4) + tnt.fuse / 8;
        level.spawnEntityInWorld(tnt);
    }

    @Override
    public final void onBlockDestroyedByPlayer(Level level, int x2, int y2, int z2, int i5) {
        if (!level.survivalWorld) {
            return;
        }
        if (!level.onlineLevel) {
            PrimedTnt tnt = new PrimedTnt(level, (float)x2 + 0.5f, (float)y2 + 0.5f, (float)z2 + 0.5f);
            level.spawnEntityInWorld(tnt);
        }
        level.playSoundAtPlayer(x2, y2, z2, "random.fuse", 1.0f, 1.0f);
    }
}

