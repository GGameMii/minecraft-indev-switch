/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.phys;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

public final class HitResult {
    public int typeOfHit;
    public int x;
    public int y;
    public int z;
    public int f;
    public Vec3 hitVec;
    public Entity entityHit;

    public HitResult(int n2, int n3, int n4, int n5, Vec3 vec3) {
        this.typeOfHit = 0;
        this.x = n2;
        this.y = n3;
        this.z = n4;
        this.f = n5;
        this.hitVec = new Vec3(vec3.x, vec3.y, vec3.z);
    }

    public HitResult(Entity entity) {
        this.typeOfHit = 1;
        this.entityHit = entity;
        this.hitVec = new Vec3(entity.x, entity.y, entity.z);
    }
}

