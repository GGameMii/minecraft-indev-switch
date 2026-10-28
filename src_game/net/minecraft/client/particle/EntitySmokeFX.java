/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.particle;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tesselator;
import net.minecraft.world.level.Level;

public final class EntitySmokeFX
extends EntityFX {
    private float smokeParticleScale;

    public EntitySmokeFX(Level world, float f2, float f3, float f4) {
        this(world, f2, f3, f4, 1.0f);
    }

    public EntitySmokeFX(Level world, float f2, float f3, float f4, float f5) {
        super(world, f2, f3, f4, 0.0f, 0.0f, 0.0f);
        this.motionX1 *= 0.1f;
        this.motionY1 *= 0.1f;
        this.motionZ1 *= 0.1f;
        this.particleGreen = this.particleBlue = (float)(Math.random() * (double)0.3f);
        this.particleRed = this.particleBlue;
        this.particleScale *= 0.75f;
        this.particleScale *= f5;
        this.smokeParticleScale = this.particleScale;
        this.particleMaxAge = (int)(8.0 / (Math.random() * 0.8 + 0.2));
        this.particleMaxAge = (int)((float)this.particleMaxAge * f5);
        this.noPhysics = false;
    }

    @Override
    public final void renderParticle(Tesselator tessellator, float f2, float f3, float f4, float f5, float f6, float f7) {
        float f82 = ((float)this.particleAge + f2) / (float)this.particleMaxAge * 32.0f;
        if (f82 < 0.0f) {
            f82 = 0.0f;
        }
        if (f82 > 1.0f) {
            f82 = 1.0f;
        }
        this.particleScale = this.smokeParticleScale * f82;
        super.renderParticle(tessellator, f2, f3, f4, f5, f6, f7);
    }

    @Override
    public final void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.particleAge++ >= this.particleMaxAge) {
            this.remove();
        }
        this.particleTextureIndex = 7 - (this.particleAge << 3) / this.particleMaxAge;
        this.motionY1 = (float)((double)this.motionY1 + 0.004);
        this.moveEntity(this.motionX1, this.motionY1, this.motionZ1);
        if (this.y == this.yo) {
            this.motionX1 = (float)((double)this.motionX1 * 1.1);
            this.motionZ1 = (float)((double)this.motionZ1 * 1.1);
        }
        this.motionX1 *= 0.96f;
        this.motionY1 *= 0.96f;
        this.motionZ1 *= 0.96f;
        if (this.onGround) {
            this.motionX1 *= 0.7f;
            this.motionZ1 *= 0.7f;
        }
    }
}

