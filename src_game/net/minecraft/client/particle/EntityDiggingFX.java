/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.particle;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tesselator;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.tile.Tile;

public final class EntityDiggingFX
extends EntityFX {
    public EntityDiggingFX(Level world, float f2, float f3, float f4, float f5, float f6, float f7, Tile block8) {
        super(world, f2, f3, f4, f5, f6, f7);
        this.particleTextureIndex = block8.tex;
        this.particleGravity = block8.blockParticleGravity;
        this.particleBlue = 0.6f;
        this.particleGreen = 0.6f;
        this.particleRed = 0.6f;
        this.particleScale /= 2.0f;
    }

    @Override
    public final int getFXLayer() {
        return 1;
    }

    @Override
    public final void renderParticle(Tesselator tessellator, float f2, float f3, float f4, float f5, float f6, float f7) {
        float f8 = ((float)(this.particleTextureIndex % 16) + this.particleTextureJitterX / 4.0f) / 16.0f;
        float f9 = f8 + 0.015609375f;
        float f10 = ((float)(this.particleTextureIndex / 16) + this.particleTextureJitterY / 4.0f) / 16.0f;
        float f11 = f10 + 0.015609375f;
        float f12 = 0.1f * this.particleScale;
        float f13 = this.xo + (this.x - this.xo) * f2;
        float f14 = this.yo + (this.y - this.yo) * f2;
        float f15 = this.zo + (this.z - this.zo) * f2;
        f2 = this.getEntityBrightness(f2);
        tessellator.setColorOpaque_F(f2 * this.particleRed, f2 * this.particleGreen, f2 * this.particleBlue);
        tessellator.vertexUV(f13 - f3 * f12 - f6 * f12, f14 - f4 * f12, f15 - f5 * f12 - f7 * f12, f8, f11);
        tessellator.vertexUV(f13 - f3 * f12 + f6 * f12, f14 + f4 * f12, f15 - f5 * f12 + f7 * f12, f8, f10);
        tessellator.vertexUV(f13 + f3 * f12 + f6 * f12, f14 + f4 * f12, f15 + f5 * f12 + f7 * f12, f9, f10);
        tessellator.vertexUV(f13 + f3 * f12 - f6 * f12, f14 - f4 * f12, f15 + f5 * f12 - f7 * f12, f9, f11);
    }
}

