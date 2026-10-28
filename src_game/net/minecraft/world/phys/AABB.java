/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.phys;

import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public final class AABB {
    private float epsilon = 0.0f;
    public float x0;
    public float y0;
    public float z0;
    public float x1;
    public float y1;
    public float z1;

    public AABB(float f2, float f3, float f4, float f5, float f6, float f7) {
        this.x0 = f2;
        this.y0 = f3;
        this.z0 = f4;
        this.x1 = f5;
        this.y1 = f6;
        this.z1 = f7;
    }

    public final AABB addCoord(float f2, float f3, float f4) {
        float f5 = this.x0;
        float f6 = this.y0;
        float f7 = this.z0;
        float f8 = this.x1;
        float f9 = this.y1;
        float f10 = this.z1;
        if (f2 < this.epsilon) {
            f5 += f2;
        }
        if (f2 > this.epsilon) {
            f8 += f2;
        }
        if (f3 < this.epsilon) {
            f6 += f3;
        }
        if (f3 > this.epsilon) {
            f9 += f3;
        }
        if (f4 < this.epsilon) {
            f7 += f4;
        }
        if (f4 > this.epsilon) {
            f10 += f4;
        }
        return new AABB(f5, f6, f7, f8, f9, f10);
    }

    public final AABB expand(float f2, float f3, float f4) {
        if (this.y0 > this.y1) {
            throw new IllegalArgumentException("NOOOOOO!");
        }
        float f5 = this.x0 - f2;
        float f6 = this.y0 - f3;
        float f7 = this.z0 - f4;
        return new AABB(f5, f6, f7, f2 += this.x1, f3 += this.y1, f4 += this.z1);
    }

    public final float calculateXOffset(AABB aABB, float f2) {
        if (aABB.y1 > this.y0 && aABB.y0 < this.y1) {
            if (aABB.z1 > this.z0 && aABB.z0 < this.z1) {
                float f3;
                if (f2 > 0.0f && aABB.x1 <= this.x0) {
                    f3 = this.x0 - aABB.x1;
                    if (f3 < f2) {
                        f2 = f3;
                    }
                }
                if (f2 < 0.0f && aABB.x0 >= this.x1) {
                    f3 = this.x1 - aABB.x0;
                    if (f3 > f2) {
                        f2 = f3;
                    }
                }
                return f2;
            }
            return f2;
        }
        return f2;
    }

    public final float calculateYOffset(AABB aABB, float f2) {
        if (aABB.x1 > this.x0 && aABB.x0 < this.x1) {
            if (aABB.z1 > this.z0 && aABB.z0 < this.z1) {
                float f3;
                if (f2 > this.epsilon && aABB.y1 <= this.y0) {
                    f3 = this.y0 - aABB.y1;
                    if (f3 < f2) {
                        f2 = f3;
                    }
                }
                if (f2 < this.epsilon && aABB.y0 >= this.y1) {
                    f3 = this.y1 - aABB.y0;
                    if (f3 > f2) {
                        f2 = f3;
                    }
                }
                return f2;
            }
            return f2;
        }
        return f2;
    }

    public final float calculateZOffset(AABB aABB, float f2) {
        if (aABB.x1 > this.x0 && aABB.x0 < this.x1) {
            if (aABB.y1 > this.y0 && aABB.y0 < this.y1) {
                float f3;
                if (f2 > this.epsilon && aABB.z1 <= this.z0) {
                    f3 = this.z0 - aABB.z1;
                    if (f3 < f2) {
                        f2 = f3;
                    }
                }
                if (f2 < this.epsilon && aABB.z0 >= this.z1) {
                    f3 = this.z1 - aABB.z0;
                    if (f3 > f2) {
                        f2 = f3;
                    }
                }
                return f2;
            }
            return f2;
        }
        return f2;
    }

    public final boolean intersectsWith(AABB aABB) {
        return aABB.x1 >= this.x0 && aABB.x0 <= this.x1 ? (aABB.y1 >= this.y0 && aABB.y0 <= this.y1 ? aABB.z1 >= this.z0 && aABB.z0 <= this.z1 : false) : false;
    }

    public final void offset(float f2, float f3, float f4) {
        this.x0 += f2;
        this.y0 += f3;
        this.z0 += f4;
        this.x1 += f2;
        this.y1 += f3;
        this.z1 += f4;
    }

    public AABB removeCoord(float f2, float f3, float f4) {
        float f5 = this.x0;
        float f6 = this.y0;
        float f7 = this.z0;
        float f8 = this.x1;
        float f9 = this.y1;
        float f10 = this.z1;
        if (f2 < 0.0f) {
            f5 -= f2;
        }
        if (f2 > 0.0f) {
            f8 -= f2;
        }
        if (f3 < 0.0f) {
            f6 -= f3;
        }
        if (f3 > 0.0f) {
            f9 -= f3;
        }
        if (f4 < 0.0f) {
            f7 -= f4;
        }
        if (f4 > 0.0f) {
            f10 -= f4;
        }
        return new AABB(f5, f6, f7, f8, f9, f10);
    }

    public final AABB copy() {
        return new AABB(this.x0, this.y0, this.z0, this.x1, this.y1, this.z1);
    }

    public final HitResult calculateIntercept(Vec3 vec3, Vec3 vec32) {
        Vec3 vec33 = vec3.getIntermediateWithXValue(vec32, this.x0);
        Vec3 vec34 = vec3.getIntermediateWithXValue(vec32, this.x1);
        Vec3 vec35 = vec3.getIntermediateWithYValue(vec32, this.y0);
        Vec3 vec36 = vec3.getIntermediateWithYValue(vec32, this.y1);
        Vec3 vec37 = vec3.getIntermediateWithZValue(vec32, this.z0);
        vec32 = vec3.getIntermediateWithZValue(vec32, this.z1);
        if (!this.isVecInYZ(vec33)) {
            vec33 = null;
        }
        if (!this.isVecInYZ(vec34)) {
            vec34 = null;
        }
        if (!this.isVecInXZ(vec35)) {
            vec35 = null;
        }
        if (!this.isVecInXZ(vec36)) {
            vec36 = null;
        }
        if (!this.isVecInXY(vec37)) {
            vec37 = null;
        }
        if (!this.isVecInXY(vec32)) {
            vec32 = null;
        }
        Vec3 vec38 = null;
        if (vec33 != null) {
            vec38 = vec33;
        }
        if (vec34 != null && (vec38 == null || vec3.squareDistanceTo(vec34) < vec3.squareDistanceTo(vec38))) {
            vec38 = vec34;
        }
        if (vec35 != null && (vec38 == null || vec3.squareDistanceTo(vec35) < vec3.squareDistanceTo(vec38))) {
            vec38 = vec35;
        }
        if (vec36 != null && (vec38 == null || vec3.squareDistanceTo(vec36) < vec3.squareDistanceTo(vec38))) {
            vec38 = vec36;
        }
        if (vec37 != null && (vec38 == null || vec3.squareDistanceTo(vec37) < vec3.squareDistanceTo(vec38))) {
            vec38 = vec37;
        }
        if (vec32 != null && (vec38 == null || vec3.squareDistanceTo(vec32) < vec3.squareDistanceTo(vec38))) {
            vec38 = vec32;
        }
        if (vec38 == null) {
            return null;
        }
        int n2 = -1;
        if (vec38 == vec33) {
            n2 = 4;
        }
        if (vec38 == vec34) {
            n2 = 5;
        }
        if (vec38 == vec35) {
            n2 = 0;
        }
        if (vec38 == vec36) {
            n2 = 1;
        }
        if (vec38 == vec37) {
            n2 = 2;
        }
        if (vec38 == vec32) {
            n2 = 3;
        }
        return new HitResult(0, 0, 0, n2, vec38);
    }

    private boolean isVecInYZ(Vec3 vec3) {
        return vec3 == null ? false : vec3.y >= this.y0 && vec3.y <= this.y1 && vec3.z >= this.z0 && vec3.z <= this.z1;
    }

    private boolean isVecInXZ(Vec3 vec3) {
        return vec3 == null ? false : vec3.x >= this.x0 && vec3.x <= this.x1 && vec3.z >= this.z0 && vec3.z <= this.z1;
    }

    private boolean isVecInXY(Vec3 vec3) {
        return vec3 == null ? false : vec3.x >= this.x0 && vec3.x <= this.x1 && vec3.y >= this.y0 && vec3.y <= this.y1;
    }
}

