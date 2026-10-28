/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.network;

public class EntityPos {
    public float x;
    public float y;
    public float z;
    public float xRot;
    public float yRot;
    public boolean rotating = false;
    public boolean moving = false;

    public EntityPos(float x2, float y2, float z2, float yr, float xr) {
        this.x = x2;
        this.y = y2;
        this.z = z2;
        this.xRot = yr;
        this.yRot = xr;
        this.rotating = true;
        this.moving = true;
    }

    public EntityPos(float x2, float y2, float z2) {
        this.x = x2;
        this.y = y2;
        this.z = z2;
        this.moving = true;
        this.rotating = false;
    }

    public EntityPos(float yr, float xr) {
        this.xRot = yr;
        this.yRot = xr;
        this.rotating = true;
        this.moving = false;
    }
}

