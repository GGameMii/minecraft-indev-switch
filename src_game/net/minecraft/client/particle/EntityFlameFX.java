/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.particle;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tesselator;
import net.minecraft.world.level.Level;

public final class EntityFlameFX
extends EntityFX {
    private float flameScale;

    public EntityFlameFX(Level world, float f2, float f3, float f4) {
        super(world, f2, f3, f4, 0.0f, 0.0f, 0.0f);
        this.motionX1 *= 0.01f;
        this.motionY1 *= 0.01f;
        this.motionZ1 *= 0.01f;
        this.rand.nextFloat();
        this.rand.nextFloat();
        this.rand.nextFloat();
        this.rand.nextFloat();
        this.rand.nextFloat();
        this.rand.nextFloat();
        this.flameScale = this.particleScale;
        this.particleBlue = 1.0f;
        this.particleGreen = 1.0f;
        this.particleRed = 1.0f;
        this.particleMaxAge = (int)(8.0 / (Math.random() * 0.8 + 0.2)) + 4;
        this.noPhysics = true;
        this.particleTextureIndex = 48;
    }

    @Override
    public final void renderParticle(Tesselator tessellator, float f2, float f3, float f4, float f5, float f6, float f7) {
        float f8 = ((float)this.particleAge + f2) / (float)this.particleMaxAge;
        this.particleScale = this.flameScale * (1.0f - f8 * f8 * 0.5f);
        super.renderParticle(tessellator, f2, f3, f4, f5, f6, f7);
    }

    @Override
    public final float getEntityBrightness(float f1) {
        float f22 = ((float)this.particleAge + f1) / (float)this.particleMaxAge;
        if (f22 < 0.0f) {
            f22 = 0.0f;
        }
        if (f22 > 1.0f) {
            f22 = 1.0f;
        }
        return super.getEntityBrightness(f1) * f22 + (1.0f - f22);
    }

    @Override
    public final void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.particleAge++ >= this.particleMaxAge) {
            this.remove();
        }
        this.moveEntity(this.motionX1, this.motionY1, this.motionZ1);
        this.motionX1 *= 0.96f;
        this.motionY1 *= 0.96f;
        this.motionZ1 *= 0.96f;
        if (this.onGround) {
            this.motionX1 *= 0.7f;
            this.motionZ1 *= 0.7f;
        }
    }
}

