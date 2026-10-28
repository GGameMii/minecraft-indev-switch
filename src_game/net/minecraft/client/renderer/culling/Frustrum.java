/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.renderer.culling;

import net.minecraft.client.renderer.culling.ClippingHelper;
import net.minecraft.client.renderer.culling.ClippingHelperImplementation;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.world.phys.AABB;

public final class Frustrum
implements ICamera {
    private ClippingHelper clippingHelper = ClippingHelperImplementation.init();

    @Override
    public final boolean isBoundingBoxInFrustrum(AABB aabb) {
        float f7 = aabb.z1;
        float f6 = aabb.y1;
        float f5 = aabb.x1;
        float f4 = aabb.z0;
        float f3 = aabb.y0;
        float f2 = aabb.x0;
        ClippingHelper clippingHelper9 = this.clippingHelper;
        int i8 = 0;
        while (i8 < 6) {
            if (clippingHelper9.frustrum[i8][0] * f2 + clippingHelper9.frustrum[i8][1] * f3 + clippingHelper9.frustrum[i8][2] * f4 + clippingHelper9.frustrum[i8][3] <= 0.0f && clippingHelper9.frustrum[i8][0] * f5 + clippingHelper9.frustrum[i8][1] * f3 + clippingHelper9.frustrum[i8][2] * f4 + clippingHelper9.frustrum[i8][3] <= 0.0f && clippingHelper9.frustrum[i8][0] * f2 + clippingHelper9.frustrum[i8][1] * f6 + clippingHelper9.frustrum[i8][2] * f4 + clippingHelper9.frustrum[i8][3] <= 0.0f && clippingHelper9.frustrum[i8][0] * f5 + clippingHelper9.frustrum[i8][1] * f6 + clippingHelper9.frustrum[i8][2] * f4 + clippingHelper9.frustrum[i8][3] <= 0.0f && clippingHelper9.frustrum[i8][0] * f2 + clippingHelper9.frustrum[i8][1] * f3 + clippingHelper9.frustrum[i8][2] * f7 + clippingHelper9.frustrum[i8][3] <= 0.0f && clippingHelper9.frustrum[i8][0] * f5 + clippingHelper9.frustrum[i8][1] * f3 + clippingHelper9.frustrum[i8][2] * f7 + clippingHelper9.frustrum[i8][3] <= 0.0f && clippingHelper9.frustrum[i8][0] * f2 + clippingHelper9.frustrum[i8][1] * f6 + clippingHelper9.frustrum[i8][2] * f7 + clippingHelper9.frustrum[i8][3] <= 0.0f && clippingHelper9.frustrum[i8][0] * f5 + clippingHelper9.frustrum[i8][1] * f6 + clippingHelper9.frustrum[i8][2] * f7 + clippingHelper9.frustrum[i8][3] <= 0.0f) {
                return false;
            }
            ++i8;
        }
        return true;
    }
}

