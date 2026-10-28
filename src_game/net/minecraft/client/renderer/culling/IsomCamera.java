/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.renderer.culling;

import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.world.phys.AABB;

public final class IsomCamera
implements ICamera {
    @Override
    public final boolean isBoundingBoxInFrustrum(AABB aabb) {
        return true;
    }
}

