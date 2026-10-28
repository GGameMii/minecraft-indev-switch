/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.level;

import net.minecraft.world.level.EntityMap;

final class EntityMapSlot {
    private int x;
    private int y;
    private int z;
    private EntityMap map;

    private EntityMapSlot(EntityMap blockMap, byte b2) {
        this.map = blockMap;
    }

    public final EntityMapSlot init(float f1, float f2, float f3) {
        this.x = (int)(f1 / 8.0f);
        this.y = (int)(f2 / 8.0f);
        this.z = (int)(f3 / 8.0f);
        if (this.x < 0) {
            this.x = 0;
        }
        if (this.y < 0) {
            this.y = 0;
        }
        if (this.z < 0) {
            this.z = 0;
        }
        if (this.x >= this.map.width) {
            this.x = this.map.width - 1;
        }
        if (this.y >= this.map.depth) {
            this.y = this.map.depth - 1;
        }
        if (this.z >= this.map.height) {
            this.z = this.map.height - 1;
        }
        return this;
    }

    EntityMapSlot(EntityMap entityMap1) {
        this(entityMap1, (byte)0);
    }

    static int getX(EntityMapSlot entityMapSlot0) {
        return entityMapSlot0.x;
    }

    static int getY(EntityMapSlot entityMapSlot0) {
        return entityMapSlot0.y;
    }

    static int getZ(EntityMapSlot entityMapSlot0) {
        return entityMapSlot0.z;
    }
}

