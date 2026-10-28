/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.lwjgl.opengl.GL11;

public class RenderBiped
extends RenderLiving {
    protected ModelBiped modelBipedMain;

    public RenderBiped(ModelBiped var1, float var2) {
        super(var1, var2);
        this.modelBipedMain = var1;
    }

    @Override
    protected void renderEquippedItems(LivingEntity e2, float a2) {
        float scale = 1.0f;
        ItemStack var3 = e2.getHeldItem();
        if (var3 != null) {
            GL11.glPushMatrix();
            this.modelBipedMain.bipedRightArm.postRender(1.0f / scale);
            GL11.glTranslatef((float)(-(1.0f / scale)), (float)(7.0f / scale), (float)(1.0f / scale));
            if (var3.itemID < 256) {
                float var4 = 0.5f;
                GL11.glTranslatef((float)0.0f, (float)(3.0f / scale), (float)(-(5.0f / scale)));
                GL11.glRotatef((float)20.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glScalef((float)(var4 *= 12.0f / scale), (float)(-var4), (float)var4);
            } else {
                float var4 = 6.0f / scale;
                GL11.glTranslatef((float)0.25f, (float)(3.0f / scale), (float)(-(3.0f / scale)));
                GL11.glScalef((float)var4, (float)var4, (float)var4);
                GL11.glRotatef((float)60.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glRotatef((float)-90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)20.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            }
            this.renderManager.itemRenderer.renderItem(e2, var3);
            GL11.glPopMatrix();
        }
    }
}

