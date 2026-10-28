/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client.particle;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tesselator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.lwjgl.opengl.GL11;

public final class PickupEffect
extends EntityFX {
    private Entity entityToPickUp;
    private LivingEntity entityPickingUp;
    private int age = 0;
    private int maxAge = 0;
    private float yOffs;

    public PickupEffect(Level world, Entity entity2, LivingEntity entityLiving3, float f4) {
        super(world, entity2.x, entity2.y, entity2.z, entity2.xd, entity2.yd, entity2.zd);
        this.entityToPickUp = entity2;
        this.entityPickingUp = entityLiving3;
        this.maxAge = 3;
        this.yOffs = -0.5f;
    }

    @Override
    public final void renderParticle(Tesselator tessellator, float f2, float f3, float f4, float f5, float f6, float f7) {
        float tessellator1 = ((float)this.age + f2) / (float)this.maxAge;
        tessellator1 *= tessellator1;
        f3 = this.entityToPickUp.x;
        f4 = this.entityToPickUp.y;
        f5 = this.entityToPickUp.z;
        f6 = this.entityPickingUp.lastTickX + (this.entityPickingUp.x - this.entityPickingUp.lastTickX) * f2;
        f7 = this.entityPickingUp.lastTickY + (this.entityPickingUp.y - this.entityPickingUp.lastTickY) * f2 + this.yOffs;
        float f8 = this.entityPickingUp.lastTickZ + (this.entityPickingUp.z - this.entityPickingUp.lastTickZ) * f2;
        f3 += (f6 - f3) * tessellator1;
        f4 += (f7 - f4) * tessellator1;
        tessellator1 = f5 + (f8 - f5) * tessellator1;
        f5 = this.level.getLightBrightness((int)f3, (int)f4, (int)tessellator1);
        GL11.glColor4f((float)f5, (float)f5, (float)f5, (float)1.0f);
        RenderManager.instance.renderEntityWithPosYaw(this.entityToPickUp, f3, f4, tessellator1, this.entityToPickUp.xRot, f2);
    }

    @Override
    public final void tick() {
        ++this.age;
        if (this.age == this.maxAge) {
            this.remove();
        }
    }

    @Override
    public final int getFXLayer() {
        return 2;
    }
}

