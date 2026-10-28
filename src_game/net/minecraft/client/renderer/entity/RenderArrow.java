/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client.renderer.entity;

import net.minecraft.client.renderer.Tesselator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.Arrow;
import org.lwjgl.opengl.GL11;
import util.Mth;

public final class RenderArrow
extends Render {
    @Override
    public final void doRender(Entity entity, float f2, float f3, float f4, float f5, float f6) {
        float f7;
        Arrow entityArrow8 = (Arrow)entity;
        this.loadTexture("/item/arrows.png");
        GL11.glPushMatrix();
        GL11.glTranslatef((float)f2, (float)f3, (float)f4);
        GL11.glRotatef((float)(entityArrow8.xRotI + (entityArrow8.xRot - entityArrow8.xRotI) * f6 - 90.0f), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)(entityArrow8.yRotI + (entityArrow8.yRot - entityArrow8.yRotI) * f6), (float)0.0f, (float)0.0f, (float)1.0f);
        Tesselator tessellator7 = Tesselator.instance;
        GL11.glEnable((int)2977);
        f2 = (float)entityArrow8.shake - f6;
        if (f2 > 0.0f) {
            GL11.glRotatef((float)(-Mth.sin(f2 * 3.0f) * f2), (float)0.0f, (float)0.0f, (float)1.0f);
        }
        GL11.glRotatef((float)45.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glScalef((float)0.05625f, (float)0.05625f, (float)0.05625f);
        GL11.glTranslatef((float)-4.0f, (float)0.0f, (float)0.0f);
        GL11.glNormal3f((float)0.05625f, (float)0.0f, (float)0.0f);
        tessellator7.begin();
        tessellator7.vertexUV(-7.0f, -2.0f, -2.0f, 0.0f, 0.15625f);
        tessellator7.vertexUV(-7.0f, -2.0f, 2.0f, 0.15625f, 0.15625f);
        tessellator7.vertexUV(-7.0f, 2.0f, 2.0f, 0.15625f, 0.3125f);
        tessellator7.vertexUV(-7.0f, 2.0f, -2.0f, 0.0f, 0.3125f);
        tessellator7.end();
        GL11.glNormal3f((float)-0.05625f, (float)0.0f, (float)0.0f);
        tessellator7.begin();
        tessellator7.vertexUV(-7.0f, 2.0f, -2.0f, 0.0f, 0.15625f);
        tessellator7.vertexUV(-7.0f, 2.0f, 2.0f, 0.15625f, 0.15625f);
        tessellator7.vertexUV(-7.0f, -2.0f, 2.0f, 0.15625f, 0.3125f);
        tessellator7.vertexUV(-7.0f, -2.0f, -2.0f, 0.0f, 0.3125f);
        tessellator7.end();
        int i9 = 0;
        while (i9 < 4) {
            GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glNormal3f((float)0.0f, (float)0.0f, (float)0.05625f);
            tessellator7.begin();
            tessellator7.vertexUV(-8.0f, -2.0f, 0.0f, 0.0f, 0.0f);
            tessellator7.vertexUV(8.0f, -2.0f, 0.0f, 0.5f, 0.0f);
            tessellator7.vertexUV(8.0f, 2.0f, 0.0f, 0.5f, 0.15625f);
            tessellator7.vertexUV(-8.0f, 2.0f, 0.0f, 0.0f, 0.15625f);
            tessellator7.end();
            ++i9;
        }
        GL11.glDisable((int)2977);
        GL11.glPopMatrix();
    }
}

