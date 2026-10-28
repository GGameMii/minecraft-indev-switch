/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.level.tile;

import java.util.Random;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.tile.Tile;

public final class GrassTile
extends Tile {
    protected GrassTile(int n2) {
        super(2, Material.ground);
        this.tex = 3;
        this.setTicking(true);
    }

    @Override
    public final int getTexture(int n2) {
        return n2 == 1 ? 0 : (n2 == 0 ? 2 : 3);
    }

    @Override
    public final void tick(Level level, int n2, int n3, int n4, Random random) {
        if (level.onlineLevel) {
            return;
        }
        if (level.getBlockLightValue(n2, n3 + 1, n4) < 4 && level.getMaterial(n2, n3 + 1, n4).getCanBlockGrass()) {
            if (random.nextInt(4) == 0) {
                level.setTile(n2, n3, n4, Tile.dirt.id);
            }
        } else if (level.getBlockLightValue(n2, n3 + 1, n4) >= 9 && level.getTile(n2 = n2 + random.nextInt(3) - 1, n3 = n3 + random.nextInt(5) - 3, n4 = n4 + random.nextInt(3) - 1) == Tile.dirt.id && level.getBlockLightValue(n2, n3 + 1, n4) >= 4 && !level.getMaterial(n2, n3 + 1, n4).getCanBlockGrass()) {
            level.setTile(n2, n3, n4, Tile.grass.id);
        }
    }

    @Override
    public final int idDropped(int n2, Random random) {
        return Tile.dirt.idDropped(0, random);
    }
}

