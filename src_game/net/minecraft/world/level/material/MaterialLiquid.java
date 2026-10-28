/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.level.material;

import net.minecraft.world.level.material.Material;

public final class MaterialLiquid
extends Material {
    @Override
    public final boolean getIsLiquid() {
        return true;
    }

    @Override
    public final boolean isSolid() {
        return false;
    }
}

