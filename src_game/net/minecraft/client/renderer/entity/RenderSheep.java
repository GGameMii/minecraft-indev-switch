/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Sheep;

public final class RenderSheep
extends RenderLiving {
    public RenderSheep(ModelBase modelBase, ModelBase modelBase2, float f3) {
        super(modelBase, 0.7f);
        this.setRenderPassModel(modelBase2);
    }

    @Override
    protected final boolean shouldRenderPass(LivingEntity e2, int layer) {
        Sheep sheep = (Sheep)e2;
        this.loadTexture("/mob/sheep_fur.png");
        return layer == 0 && !sheep.getSheared();
    }
}

