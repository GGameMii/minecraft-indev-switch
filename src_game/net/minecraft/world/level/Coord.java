/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.level;

public final class Coord {
    public int x;
    public int y;
    public int z;
    public int id;
    public int scheduledTime;

    public Coord(int x2, int y2, int z2, int block) {
        this.x = x2;
        this.y = y2;
        this.z = z2;
        this.id = block;
    }
}

