/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.level;

import net.minecraft.world.entity.Entity;

public interface IWorldAccess {
    public void markBlockAndNeighborsNeedsUpdate(int var1, int var2, int var3);

    public void markBlockRangeNeedsUpdate(int var1, int var2, int var3, int var4, int var5, int var6);

    public void loadRenderers();

    public void playSoundAtEntity(Entity var1, String var2, float var3, float var4);

    public void playSoundAtPlayer(float var1, float var2, float var3, String var4, float var5, float var6);

    public void playSound(String var1, float var2, float var3, float var4, float var5, float var6);

    public void spawnParticle(String var1, float var2, float var3, float var4, float var5, float var6, float var7);

    public void playMusic(String var1, float var2, float var3, float var4, float var5);

    public void obtainEntitySkin(Entity var1);

    public void releaseEntitySkin(Entity var1);

    public void updateAllRenderers();
}

