/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.level;

import net.minecraft.world.level.Light;

final class LightUpdate {
    public int x;
    public int y;
    public int z;
    public int maxX;
    public int maxY;
    public int maxZ;

    public LightUpdate(Light skyBlock, int x2, int y2, int z2, int x22, int y22, int z22) {
        this.x = x2;
        this.y = y2;
        this.z = z2;
        this.maxX = x22;
        this.maxY = y22;
        this.maxZ = z22;
    }
}

