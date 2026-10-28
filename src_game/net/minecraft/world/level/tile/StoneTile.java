/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.level.tile;

import java.util.Random;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.tile.Tile;

public final class StoneTile
extends Tile {
    public StoneTile(int blockID, int textureSlot) {
        super(blockID, textureSlot, Material.rock);
    }

    @Override
    public final int idDropped(int i1, Random random) {
        return Tile.cobblestone.id;
    }
}

