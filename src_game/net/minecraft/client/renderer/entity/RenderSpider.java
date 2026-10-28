/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelSpider;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Spider;
import org.lwjgl.opengl.GL11;

public final class RenderSpider
extends RenderLiving {
    public RenderSpider() {
        super(new ModelSpider(), 1.0f);
        this.setRenderPassModel(new ModelSpider());
    }

    @Override
    protected final float getDeathMaxRotation(LivingEntity entityLiving) {
        return 180.0f;
    }

    @Override
    protected final boolean shouldRenderPass(LivingEntity entityLiving, int i2) {
        Spider entitySpider10001 = (Spider)entityLiving;
        int i3 = i2;
        Spider entitySpider5 = entitySpider10001;
        if (i3 != 0) {
            return false;
        }
        if (i3 != 0) {
            return false;
        }
        this.loadTexture("/mob/spider_eyes.png");
        float entityLiving1 = (1.0f - entitySpider5.getEntityBrightness(1.0f)) * 0.5f;
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3008);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)entityLiving1);
        return true;
    }
}

