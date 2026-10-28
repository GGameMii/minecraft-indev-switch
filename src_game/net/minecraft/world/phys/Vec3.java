/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.phys;

import util.Mth;

public final class Vec3 {
    public float x;
    public float y;
    public float z;

    public Vec3(float f2, float f3, float f4) {
        this.x = f2;
        this.y = f3;
        this.z = f4;
    }

    public final Vec3 subtract(Vec3 vec3) {
        return new Vec3(this.x - vec3.x, this.y - vec3.y, this.z - vec3.z);
    }

    public final Vec3 normalize() {
        float f2 = Mth.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
        return new Vec3(this.x / f2, this.y / f2, this.z / f2);
    }

    public final Vec3 addVector(float f2, float f3, float f4) {
        return new Vec3(this.x + f2, this.y + f3, this.z + f4);
    }

    public final float distance(Vec3 vec3) {
        float f2 = vec3.x - this.x;
        float f3 = vec3.y - this.y;
        float f4 = vec3.z - this.z;
        return Mth.sqrt(f2 * f2 + f3 * f3 + f4 * f4);
    }

    public final float squareDistanceTo(Vec3 vec3) {
        float f2 = vec3.x - this.x;
        float f3 = vec3.y - this.y;
        float f4 = vec3.z - this.z;
        return f2 * f2 + f3 * f3 + f4 * f4;
    }

    public final Vec3 getIntermediateWithXValue(Vec3 vec3, float f2) {
        Vec3 vec32;
        float f3 = vec3.x - this.x;
        float f4 = vec3.y - this.y;
        float f5 = vec3.z - this.z;
        if (f3 * f3 < 1.0E-7f) {
            vec32 = null;
        } else {
            f2 = (f2 - this.x) / f3;
            vec32 = f2 >= 0.0f && f2 <= 1.0f ? new Vec3(this.x + f3 * f2, this.y + f4 * f2, this.z + f5 * f2) : null;
        }
        return vec32;
    }

    public final Vec3 getIntermediateWithYValue(Vec3 vec3, float f2) {
        Vec3 vec32;
        float f3 = vec3.x - this.x;
        float f4 = vec3.y - this.y;
        float f5 = vec3.z - this.z;
        if (f4 * f4 < 1.0E-7f) {
            vec32 = null;
        } else {
            f2 = (f2 - this.y) / f4;
            vec32 = f2 >= 0.0f && f2 <= 1.0f ? new Vec3(this.x + f3 * f2, this.y + f4 * f2, this.z + f5 * f2) : null;
        }
        return vec32;
    }

    public final Vec3 getIntermediateWithZValue(Vec3 vec3, float f2) {
        Vec3 vec32;
        float f4 = vec3.x - this.x;
        float f5 = vec3.y - this.y;
        float f6 = vec3.z - this.z;
        if (f6 * f6 < 1.0E-7f) {
            vec32 = null;
        } else {
            f2 = (f2 - this.z) / f6;
            vec32 = f2 >= 0.0f && f2 <= 1.0f ? new Vec3(this.x + f4 * f2, this.y + f5 * f2, this.z + f6 * f2) : null;
        }
        return vec32;
    }

    public final String toString() {
        return "(" + this.x + ", " + this.y + ", " + this.z + ")";
    }
}

