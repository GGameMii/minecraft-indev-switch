/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client.renderer.entity;

import java.util.Random;
import net.minecraft.client.renderer.Tesselator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EnumArt;
import net.minecraft.world.entity.Painting;
import org.lwjgl.opengl.GL11;

public final class RenderPainting
extends Render {
    private Random rand = new Random();

    @Override
    public final void doRender(Entity entity, float f2, float f3, float f4, float f5, float f6) {
        Painting entityPainting22 = (Painting)entity;
        this.rand.setSeed(187L);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)f2, (float)f3, (float)f4);
        GL11.glRotatef((float)f5, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glEnable((int)2977);
        this.loadTexture("/art/kz.png");
        EnumArt enumArt23 = entityPainting22.art;
        GL11.glScalef((float)0.0625f, (float)0.0625f, (float)0.0625f);
        int i27 = enumArt23.offsetY;
        int i26 = enumArt23.offsetX;
        int i25 = enumArt23.sizeY;
        int i24 = enumArt23.sizeX;
        RenderPainting renderPainting21 = this;
        float f7 = (float)(-i24) / 2.0f;
        float f8 = (float)(-i25) / 2.0f;
        int i9 = 0;
        while (i9 < i24 / 16) {
            int i10 = 0;
            while (i10 < i25 / 16) {
                float f11 = f7 + (float)(i9 + 1 << 4);
                float f12 = f7 + (float)(i9 << 4);
                float f13 = f8 + (float)(i10 + 1 << 4);
                float f14 = f8 + (float)(i10 << 4);
                float f10002 = (f11 + f12) / 2.0f;
                float f18 = (f13 + f14) / 2.0f;
                float f17 = f10002;
                int i19 = (int)entityPainting22.x;
                int i28 = (int)(entityPainting22.y + f18 / 16.0f);
                int i20 = (int)entityPainting22.z;
                if (entityPainting22.direction == 0) {
                    i19 = (int)(entityPainting22.x + f17 / 16.0f);
                }
                if (entityPainting22.direction == 1) {
                    i20 = (int)(entityPainting22.z - f17 / 16.0f);
                }
                if (entityPainting22.direction == 2) {
                    i19 = (int)(entityPainting22.x - f17 / 16.0f);
                }
                if (entityPainting22.direction == 3) {
                    i20 = (int)(entityPainting22.z + f17 / 16.0f);
                }
                float f15 = renderPainting21.renderManager.worldObj.getLightBrightness(i19, i28, i20);
                GL11.glColor3f((float)f15, (float)f15, (float)f15);
                f15 = (float)(i26 + i24 - (i9 << 4)) / 256.0f;
                float f16 = (float)(i26 + i24 - (i9 + 1 << 4)) / 256.0f;
                f17 = (float)(i27 + i25 - (i10 << 4)) / 256.0f;
                f18 = (float)(i27 + i25 - (i10 + 1 << 4)) / 256.0f;
                Tesselator tessellator29 = Tesselator.instance;
                Tesselator.instance.begin();
                Tesselator.setNormal(0.0f, 0.0f, -1.0f);
                tessellator29.vertexUV(f11, f14, -0.5f, f16, f17);
                tessellator29.vertexUV(f12, f14, -0.5f, f15, f17);
                tessellator29.vertexUV(f12, f13, -0.5f, f15, f18);
                tessellator29.vertexUV(f11, f13, -0.5f, f16, f18);
                Tesselator.setNormal(0.0f, 0.0f, 1.0f);
                tessellator29.vertexUV(f11, f13, 0.5f, 0.75f, 0.0f);
                tessellator29.vertexUV(f12, f13, 0.5f, 0.8125f, 0.0f);
                tessellator29.vertexUV(f12, f14, 0.5f, 0.8125f, 0.0625f);
                tessellator29.vertexUV(f11, f14, 0.5f, 0.75f, 0.0625f);
                Tesselator.setNormal(0.0f, -1.0f, 0.0f);
                tessellator29.vertexUV(f11, f13, -0.5f, 0.75f, 0.001953125f);
                tessellator29.vertexUV(f12, f13, -0.5f, 0.8125f, 0.001953125f);
                tessellator29.vertexUV(f12, f13, 0.5f, 0.8125f, 0.001953125f);
                tessellator29.vertexUV(f11, f13, 0.5f, 0.75f, 0.001953125f);
                Tesselator.setNormal(0.0f, 1.0f, 0.0f);
                tessellator29.vertexUV(f11, f14, 0.5f, 0.75f, 0.001953125f);
                tessellator29.vertexUV(f12, f14, 0.5f, 0.8125f, 0.001953125f);
                tessellator29.vertexUV(f12, f14, -0.5f, 0.8125f, 0.001953125f);
                tessellator29.vertexUV(f11, f14, -0.5f, 0.75f, 0.001953125f);
                Tesselator.setNormal(-1.0f, 0.0f, 0.0f);
                tessellator29.vertexUV(f11, f13, 0.5f, 0.7519531f, 0.0f);
                tessellator29.vertexUV(f11, f14, 0.5f, 0.7519531f, 0.0625f);
                tessellator29.vertexUV(f11, f14, -0.5f, 0.7519531f, 0.0625f);
                tessellator29.vertexUV(f11, f13, -0.5f, 0.7519531f, 0.0f);
                Tesselator.setNormal(1.0f, 0.0f, 0.0f);
                tessellator29.vertexUV(f12, f13, -0.5f, 0.7519531f, 0.0f);
                tessellator29.vertexUV(f12, f14, -0.5f, 0.7519531f, 0.0625f);
                tessellator29.vertexUV(f12, f14, 0.5f, 0.7519531f, 0.0625f);
                tessellator29.vertexUV(f12, f13, 0.5f, 0.7519531f, 0.0f);
                tessellator29.end();
                ++i10;
            }
            ++i9;
        }
        GL11.glDisable((int)2977);
        GL11.glPopMatrix();
    }
}

