/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.particle;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tesselator;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Material;

public class EntityRainFX
extends EntityFX {
    public EntityRainFX(Level world, float f2, float f3, float f4) {
        super(world, f2, f3, f4, 0.0f, 0.0f, 0.0f);
        this.motionX1 *= 0.3f;
        this.motionY1 = (float)Math.random() * 0.2f + 0.1f;
        this.motionZ1 *= 0.3f;
        this.particleRed = 1.0f;
        this.particleGreen = 1.0f;
        this.particleBlue = 1.0f;
        this.particleTextureIndex = 16;
        this.setSize(0.01f, 0.01f);
        this.particleGravity = 0.06f;
        this.particleMaxAge = (int)(8.0 / (Math.random() * 0.8 + 0.2));
    }

    @Override
    public final void renderParticle(Tesselator tessellator, float f2, float f3, float f4, float f5, float f6, float f7) {
        super.renderParticle(tessellator, f2, f3, f4, f5, f6, f7);
    }

    @Override
    public final void tick() {
        Material material1;
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        this.motionY1 -= this.particleGravity;
        this.moveEntity(this.motionX1, this.motionY1, this.motionZ1);
        this.motionX1 *= 0.98f;
        this.motionY1 *= 0.98f;
        this.motionZ1 *= 0.98f;
        if (this.particleMaxAge-- <= 0) {
            this.remove();
        }
        if (this.onGround) {
            if (Math.random() < 0.5) {
                this.remove();
            }
            this.motionX1 *= 0.7f;
            this.motionZ1 *= 0.7f;
        }
        if ((material1 = this.level.getMaterial((int)this.x, (int)this.y, (int)this.z)).getIsLiquid() || material1.isSolid()) {
            this.remove();
        }
    }
}

