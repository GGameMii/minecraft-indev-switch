/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.level.tile;

import java.util.Random;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.tile.TranslucentTile;

public final class GlassTile
extends TranslucentTile {
    public GlassTile(int i1, int i2, Material material3, boolean z4) {
        super(20, 49, material3, false);
    }

    @Override
    public final int quantityDropped(Random random) {
        return 0;
    }
}

