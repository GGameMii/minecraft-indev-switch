/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.particle;

import com.mojang.nbt.NBTTagCompound;
import net.minecraft.client.renderer.Tesselator;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import util.Mth;

public class EntityFX
extends Entity {
    protected float motionX1;
    protected float motionY1;
    protected float motionZ1;
    protected int particleTextureIndex;
    protected float particleTextureJitterX;
    protected float particleTextureJitterY;
    protected int particleAge = 0;
    protected int particleMaxAge = 0;
    protected float particleScale;
    protected float particleGravity;
    protected float particleRed;
    protected float particleGreen;
    protected float particleBlue;

    public EntityFX(Level world, float f2, float f3, float f4, float f5, float f6, float f7) {
        super(world);
        this.setSize(0.2f, 0.2f);
        this.heightOffset = this.bbHeight / 2.0f;
        this.setPos(f2, f3, f4);
        this.particleBlue = 1.0f;
        this.particleGreen = 1.0f;
        this.particleRed = 1.0f;
        this.motionX1 = f5 + (float)(Math.random() * 2.0 - 1.0) * 0.4f;
        this.motionY1 = f6 + (float)(Math.random() * 2.0 - 1.0) * 0.4f;
        this.motionZ1 = f7 + (float)(Math.random() * 2.0 - 1.0) * 0.4f;
        float world1 = (float)(Math.random() + Math.random() + 1.0) * 0.15f;
        f2 = Mth.sqrt(this.motionX1 * this.motionX1 + this.motionY1 * this.motionY1 + this.motionZ1 * this.motionZ1);
        this.motionX1 = this.motionX1 / f2 * world1 * 0.4f;
        this.motionY1 = this.motionY1 / f2 * world1 * 0.4f + 0.1f;
        this.motionZ1 = this.motionZ1 / f2 * world1 * 0.4f;
        this.particleTextureJitterX = this.rand.nextFloat() * 3.0f;
        this.particleTextureJitterY = this.rand.nextFloat() * 3.0f;
        this.particleScale = (this.rand.nextFloat() * 0.5f + 0.5f) * 2.0f;
        this.particleMaxAge = (int)(4.0f / (this.rand.nextFloat() * 0.9f + 0.1f));
        this.particleAge = 0;
        this.canTriggerWalking = false;
    }

    @Override
    protected void entityInit() {
    }

    public final EntityFX multiplyVelocity(float f1) {
        this.motionX1 *= 0.2f;
        this.motionY1 = (this.motionY1 - 0.1f) * 0.2f + 0.1f;
        this.motionZ1 *= 0.2f;
        return this;
    }

    public final EntityFX multipleParticleScaleBy(float f1) {
        this.setSize(0.120000005f, 0.120000005f);
        this.particleScale *= 0.6f;
        return this;
    }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.particleAge++ >= this.particleMaxAge) {
            this.remove();
        }
        this.motionY1 = (float)((double)this.motionY1 - 0.04 * (double)this.particleGravity);
        this.moveEntity(this.motionX1, this.motionY1, this.motionZ1);
        this.motionX1 *= 0.98f;
        this.motionY1 *= 0.98f;
        this.motionZ1 *= 0.98f;
        if (this.onGround) {
            this.motionX1 *= 0.7f;
            this.motionZ1 *= 0.7f;
        }
    }

    public void renderParticle(Tesselator t2, float f2, float f3, float f4, float f5, float f6, float f7) {
        float f8 = (float)(this.particleTextureIndex % 16) / 16.0f;
        float f9 = f8 + 0.0624375f;
        float f10 = (float)(this.particleTextureIndex / 16) / 16.0f;
        float f11 = f10 + 0.0624375f;
        float f12 = 0.1f * this.particleScale;
        float f13 = this.xo + (this.x - this.xo) * f2;
        float f14 = this.yo + (this.y - this.yo) * f2;
        float f15 = this.zo + (this.z - this.zo) * f2;
        f2 = this.getEntityBrightness(f2);
        t2.setColorOpaque_F(this.particleRed * f2, this.particleGreen * f2, this.particleBlue * f2);
        t2.vertexUV(f13 - f3 * f12 - f6 * f12, f14 - f4 * f12, f15 - f5 * f12 - f7 * f12, f8, f11);
        t2.vertexUV(f13 - f3 * f12 + f6 * f12, f14 + f4 * f12, f15 - f5 * f12 + f7 * f12, f8, f10);
        t2.vertexUV(f13 + f3 * f12 + f6 * f12, f14 + f4 * f12, f15 + f5 * f12 + f7 * f12, f9, f10);
        t2.vertexUV(f13 + f3 * f12 - f6 * f12, f14 - f4 * f12, f15 + f5 * f12 - f7 * f12, f9, f11);
    }

    public int getFXLayer() {
        return 0;
    }

    @Override
    protected final void writeEntityToNBT(NBTTagCompound nbt) {
    }

    @Override
    protected final String getEntityString() {
        return null;
    }

    @Override
    protected final void readEntityFromNBT(NBTTagCompound nbt) {
    }
}

