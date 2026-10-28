/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.particle;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tesselator;
import net.minecraft.world.level.Level;

public final class EntityLavaFX
extends EntityFX {
    private float lavaParticleScale;

    public EntityLavaFX(Level world, float f2, float f3, float f4) {
        super(world, f2, f3, f4, 0.0f, 0.0f, 0.0f);
        this.motionX1 *= 0.8f;
        this.motionY1 *= 0.8f;
        this.motionZ1 *= 0.8f;
        this.motionY1 = this.rand.nextFloat() * 0.4f + 0.05f;
        this.particleBlue = 1.0f;
        this.particleGreen = 1.0f;
        this.particleRed = 1.0f;
        this.particleScale *= this.rand.nextFloat() * 2.0f + 0.2f;
        this.lavaParticleScale = this.particleScale;
        this.particleMaxAge = (int)(16.0 / (Math.random() * 0.8 + 0.2));
        this.noPhysics = false;
        this.particleTextureIndex = 49;
    }

    @Override
    public final float getEntityBrightness(float f1) {
        return 1.0f;
    }

    @Override
    public final void renderParticle(Tesselator tessellator, float f2, float f3, float f4, float f5, float f6, float f7) {
        float f8 = ((float)this.particleAge + f2) / (float)this.particleMaxAge;
        this.particleScale = this.lavaParticleScale * (1.0f - f8 * f8);
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
        float f1 = (float)this.particleAge / (float)this.particleMaxAge;
        if (this.rand.nextFloat() > f1) {
            this.level.spawnParticle("smoke", this.x, this.y, this.z, this.motionX1, this.motionY1, this.motionZ1);
        }
        this.motionY1 = (float)((double)this.motionY1 - 0.03);
        this.moveEntity(this.motionX1, this.motionY1, this.motionZ1);
        this.motionX1 *= 0.999f;
        this.motionY1 *= 0.999f;
        this.motionZ1 *= 0.999f;
        if (this.onGround) {
            this.motionX1 *= 0.7f;
            this.motionZ1 *= 0.7f;
        }
    }
}

