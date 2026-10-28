/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.renderer;

import java.util.Comparator;
import net.minecraft.client.renderer.Chunk;
import net.minecraft.world.entity.player.Player;

public final class EntitySorter
implements Comparator<Chunk> {
    private Player player;

    public EntitySorter(Player entityPlayer) {
        this.player = entityPlayer;
    }

    @Override
    public final int compare(Chunk object1, Chunk object2) {
        Chunk worldRenderer10001 = object1;
        Chunk worldRenderer3 = object2;
        Chunk worldRenderer4 = worldRenderer10001;
        return worldRenderer4.distanceToEntitySquared(this.player) < worldRenderer3.distanceToEntitySquared(this.player) ? -1 : 1;
    }
}

