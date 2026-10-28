/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.particle;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Material;

public final class EntityBubbleFX
extends EntityFX {
    public EntityBubbleFX(Level world1, float f2, float f3, float f4, float f5, float f6, float f7) {
        super(world1, f2, f3, f4, f5, f6, f7);
        this.particleRed = 1.0f;
        this.particleGreen = 1.0f;
        this.particleBlue = 1.0f;
        this.particleTextureIndex = 32;
        this.setSize(0.02f, 0.02f);
        this.particleScale *= this.rand.nextFloat() * 0.6f + 0.2f;
        this.motionX1 = f5 * 0.2f + (float)(Math.random() * 2.0 - 1.0) * 0.02f;
        this.motionY1 = f6 * 0.2f + (float)(Math.random() * 2.0 - 1.0) * 0.02f;
        this.motionZ1 = f7 * 0.2f + (float)(Math.random() * 2.0 - 1.0) * 0.02f;
        this.particleMaxAge = (int)(8.0 / (Math.random() * 0.8 + 0.2));
    }

    @Override
    public final void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        this.motionY1 = (float)((double)this.motionY1 + 0.002);
        this.moveEntity(this.motionX1, this.motionY1, this.motionZ1);
        this.motionX1 *= 0.85f;
        this.motionY1 *= 0.85f;
        this.motionZ1 *= 0.85f;
        if (this.level.getMaterial((int)this.x, (int)this.y, (int)this.z) != Material.water) {
            this.remove();
        }
        if (this.particleMaxAge-- <= 0) {
            this.remove();
        }
    }
}

