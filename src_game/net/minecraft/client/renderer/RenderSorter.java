/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.renderer;

import java.util.Comparator;
import net.minecraft.client.renderer.Chunk;
import net.minecraft.world.entity.player.Player;

public final class RenderSorter
implements Comparator<Chunk> {
    private Player baseEntity;

    public RenderSorter(Player entityPlayer) {
        this.baseEntity = entityPlayer;
    }

    @Override
    public final int compare(Chunk object1, Chunk object2) {
        Chunk worldRenderer10001 = object1;
        Chunk worldRenderer3 = object2;
        Chunk worldRenderer6 = worldRenderer10001;
        boolean z4 = worldRenderer6.isInFrustrum;
        boolean z5 = worldRenderer3.isInFrustrum;
        return z4 && !z5 ? 1 : ((!z5 || z4) && worldRenderer6.distanceToEntitySquared(this.baseEntity) < worldRenderer3.distanceToEntitySquared(this.baseEntity) ? 1 : -1);
    }
}

