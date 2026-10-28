/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.renderer.culling;

import net.minecraft.world.phys.AABB;

public interface ICamera {
    public boolean isBoundingBoxInFrustrum(AABB var1);
}

