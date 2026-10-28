/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.level.tile;

import java.util.Random;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.tile.FallingTile;

public final class GravelTile
extends FallingTile {
    public GravelTile(int i1, int i2) {
        super(13, 19);
    }

    @Override
    public final int idDropped(int i1, Random random) {
        return random.nextInt(10) == 0 ? Item.flint.shiftedIndex : this.id;
    }
}

