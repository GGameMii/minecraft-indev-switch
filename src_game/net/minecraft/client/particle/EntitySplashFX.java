/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.particle;

import net.minecraft.client.particle.EntityRainFX;
import net.minecraft.world.level.Level;

public final class EntitySplashFX
extends EntityRainFX {
    public EntitySplashFX(Level world1, float f2, float f3, float f4) {
        super(world1, f2, f3, f4);
        this.particleGravity = 0.04f;
        ++this.particleTextureIndex;
    }
}

