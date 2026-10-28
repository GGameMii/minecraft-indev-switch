/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.particle;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tesselator;
import net.minecraft.world.level.Level;

public final class EntityExplodeFX
extends EntityFX {
    public EntityExplodeFX(Level world1, float f2, float f3, float f4, float f5, float f6, float f7) {
        super(world1, f2, f3, f4, f5, f6, f7);
        this.motionX1 = f5 + (float)(Math.random() * 2.0 - 1.0) * 0.05f;
        this.motionY1 = f6 + (float)(Math.random() * 2.0 - 1.0) * 0.05f;
        this.motionZ1 = f7 + (float)(Math.random() * 2.0 - 1.0) * 0.05f;
        this.particleGreen = this.particleBlue = this.rand.nextFloat() * 0.3f + 0.7f;
        this.particleRed = this.particleBlue;
        this.particleScale = this.rand.nextFloat() * this.rand.nextFloat() * 6.0f + 1.0f;
        this.particleMaxAge = (int)(16.0 / ((double)this.rand.nextFloat() * 0.8 + 0.2)) + 2;
    }

    @Override
    public final void renderParticle(Tesselator tessellator, float f2, float f3, float f4, float f5, float f6, float f7) {
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
        this.motionX1 *= 0.9f;
        this.motionY1 *= 0.9f;
        this.motionZ1 *= 0.9f;
        if (this.onGround) {
            this.motionX1 *= 0.7f;
            this.motionZ1 *= 0.7f;
        }
    }
}

