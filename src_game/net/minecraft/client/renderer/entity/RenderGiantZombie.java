/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.world.entity.LivingEntity;
import org.lwjgl.opengl.GL11;

public final class RenderGiantZombie
extends RenderLiving {
    private float scale = 6.0f;

    public RenderGiantZombie(ModelBase modelBase, float f2, float f3) {
        super(modelBase, 3.0f);
    }

    @Override
    protected final void preRenderCallback(LivingEntity entityLiving, float f2) {
        GL11.glScalef((float)this.scale, (float)this.scale, (float)this.scale);
    }
}

