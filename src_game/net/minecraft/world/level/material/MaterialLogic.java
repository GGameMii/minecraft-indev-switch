/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.level.material;

import net.minecraft.world.level.material.Material;

public final class MaterialLogic
extends Material {
    @Override
    public final boolean isSolid() {
        return false;
    }

    @Override
    public final boolean getCanBlockGrass() {
        return false;
    }

    @Override
    public final boolean getIsSolid() {
        return false;
    }
}

