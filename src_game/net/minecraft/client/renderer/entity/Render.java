/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.client.renderer.Tesselator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.tile.Tile;
import net.minecraft.world.phys.AABB;
import org.lwjgl.opengl.GL11;

public abstract class Render {
    protected RenderManager renderManager;
    protected float shadowSize;
    protected float shadowOpaque;

    public Render() {
        new ModelBiped();
        new RenderBlocks();
        this.shadowSize = 0.0f;
        this.shadowOpaque = 1.0f;
    }

    public abstract void doRender(Entity var1, float var2, float var3, float var4, float var5, float var6);

    protected final void loadTexture(String string1) {
        RenderEngine.bind(this.renderManager.renderEngine.getTexture(string1));
    }

    protected final void loadDownloadableImageTexture(String string1, String string2) {
        RenderEngine.bind(this.renderManager.renderEngine.loadHttpTexture(string1, string2));
    }

    public static void renderOffsetAABB(AABB aabb) {
        GL11.glDisable((int)3553);
        Tesselator tessellator1 = Tesselator.instance;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        tessellator1.begin();
        Tesselator.setNormal(0.0f, 0.0f, -1.0f);
        tessellator1.addVertex(aabb.x0, aabb.y1, aabb.z0);
        tessellator1.addVertex(aabb.x1, aabb.y1, aabb.z0);
        tessellator1.addVertex(aabb.x1, aabb.y0, aabb.z0);
        tessellator1.addVertex(aabb.x0, aabb.y0, aabb.z0);
        Tesselator.setNormal(0.0f, 0.0f, 1.0f);
        tessellator1.addVertex(aabb.x0, aabb.y0, aabb.z1);
        tessellator1.addVertex(aabb.x1, aabb.y0, aabb.z1);
        tessellator1.addVertex(aabb.x1, aabb.y1, aabb.z1);
        tessellator1.addVertex(aabb.x0, aabb.y1, aabb.z1);
        Tesselator.setNormal(0.0f, -1.0f, 0.0f);
        tessellator1.addVertex(aabb.x0, aabb.y0, aabb.z0);
        tessellator1.addVertex(aabb.x1, aabb.y0, aabb.z0);
        tessellator1.addVertex(aabb.x1, aabb.y0, aabb.z1);
        tessellator1.addVertex(aabb.x0, aabb.y0, aabb.z1);
        Tesselator.setNormal(0.0f, 1.0f, 0.0f);
        tessellator1.addVertex(aabb.x0, aabb.y1, aabb.z1);
        tessellator1.addVertex(aabb.x1, aabb.y1, aabb.z1);
        tessellator1.addVertex(aabb.x1, aabb.y1, aabb.z0);
        tessellator1.addVertex(aabb.x0, aabb.y1, aabb.z0);
        Tesselator.setNormal(-1.0f, 0.0f, 0.0f);
        tessellator1.addVertex(aabb.x0, aabb.y0, aabb.z1);
        tessellator1.addVertex(aabb.x0, aabb.y1, aabb.z1);
        tessellator1.addVertex(aabb.x0, aabb.y1, aabb.z0);
        tessellator1.addVertex(aabb.x0, aabb.y0, aabb.z0);
        Tesselator.setNormal(1.0f, 0.0f, 0.0f);
        tessellator1.addVertex(aabb.x1, aabb.y0, aabb.z0);
        tessellator1.addVertex(aabb.x1, aabb.y1, aabb.z0);
        tessellator1.addVertex(aabb.x1, aabb.y1, aabb.z1);
        tessellator1.addVertex(aabb.x1, aabb.y0, aabb.z1);
        tessellator1.end();
        GL11.glEnable((int)3553);
    }

    public final void setRenderManager(RenderManager renderManager) {
        this.renderManager = renderManager;
    }

    public final void renderShadow(Entity entity, float f2, float f3, float f4, float f5) {
        float f19;
        float f36;
        float f18;
        float f34;
        int i29;
        float f12;
        if (this.shadowSize > 0.0f) {
            f5 = this.renderManager.getDistanceToCamera(f2, f3, f4);
            f5 = (1.0f - f5 / 256.0f) * this.shadowOpaque;
            if (f5 > 0.0f) {
                float f9 = f5;
                float f8 = f4;
                float f7 = f3;
                float f62 = f2;
                Render render27 = this;
                GL11.glEnable((int)3042);
                RenderEngine.bind(this.renderManager.renderEngine.getTexture("%%/shadow.png"));
                Level world11 = this.renderManager.worldObj;
                GL11.glDepthMask((boolean)false);
                f12 = this.shadowSize;
                i29 = (int)(f2 - f12);
                while (i29 <= (int)(f62 + f12)) {
                    int i13 = (int)(f7 - 2.0f);
                    while (i13 <= (int)f7) {
                        int i14 = (int)(f8 - f12);
                        while (i14 <= (int)(f8 + f12)) {
                            int i15 = world11.getTile(i29, i13 - 1, i14);
                            if (i15 > 0 && world11.getBlockLightValue(i29, i13, i14) > 3) {
                                Tile block16 = Tile.tiles[i15];
                                Tesselator tessellator25 = Tesselator.instance;
                                f34 = (f9 - (f7 - (float)i13) / 2.0f) * 0.5f * render27.renderManager.worldObj.getLightBrightness(i29, i13, i14);
                                if (f34 >= 0.0f) {
                                    GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)f34);
                                    tessellator25.begin();
                                    f34 = (float)i29 + block16.xx0;
                                    f18 = (float)i29 + block16.xx1;
                                    float f20 = (float)i13 + block16.yy0;
                                    float f21 = (float)i14 + block16.zz0;
                                    f36 = (float)i14 + block16.zz1;
                                    float f22 = (f62 - f34) / 2.0f / f12 + 0.5f;
                                    float f17 = (f62 - f18) / 2.0f / f12 + 0.5f;
                                    float f23 = (f8 - f21) / 2.0f / f12 + 0.5f;
                                    f19 = (f8 - f36) / 2.0f / f12 + 0.5f;
                                    tessellator25.vertexUV(f34, f20, f21, f22, f23);
                                    tessellator25.vertexUV(f34, f20, f36, f22, f19);
                                    tessellator25.vertexUV(f18, f20, f36, f17, f19);
                                    tessellator25.vertexUV(f18, f20, f21, f17, f23);
                                    tessellator25.end();
                                }
                            }
                            ++i14;
                        }
                        ++i13;
                    }
                    ++i29;
                }
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                GL11.glDisable((int)3042);
                GL11.glDepthMask((boolean)true);
            }
        }
        if (entity.fire > 0) {
            GL11.glDisable((int)2896);
            int i26 = Tile.fire.tex;
            i29 = (Tile.fire.tex & 0xF) << 4;
            int i30 = i26 & 0xF0;
            f12 = (float)i29 / 256.0f;
            float f31 = ((float)i29 + 15.99f) / 256.0f;
            float f32 = (float)i30 / 256.0f;
            float f33 = ((float)i30 + 15.99f) / 256.0f;
            GL11.glPushMatrix();
            GL11.glTranslatef((float)f2, (float)f3, (float)f4);
            f34 = entity.bbWidth * 1.4f;
            GL11.glScalef((float)f34, (float)f34, (float)f34);
            this.loadTexture("/terrain.png");
            Tesselator tessellator35 = Tesselator.instance;
            f36 = 1.0f;
            f18 = 0.0f;
            f19 = entity.bbHeight / entity.bbWidth;
            GL11.glRotatef((float)(-this.renderManager.playerViewY), (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glTranslatef((float)0.0f, (float)0.0f, (float)(0.4f + (float)((int)f19) * 0.02f));
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            tessellator35.begin();
            while (f19 > 0.0f) {
                tessellator35.vertexUV(f36 - 0.5f, 0.0f - f18, 0.0f, f31, f33);
                tessellator35.vertexUV(-0.5f, 0.0f - f18, 0.0f, f12, f33);
                tessellator35.vertexUV(-0.5f, 1.4f - f18, 0.0f, f12, f32);
                tessellator35.vertexUV(f36 - 0.5f, 1.4f - f18, 0.0f, f31, f32);
                f19 -= 1.0f;
                f18 -= 1.0f;
                f36 *= 0.9f;
                GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-0.04f);
            }
            tessellator35.end();
            GL11.glPopMatrix();
            GL11.glEnable((int)2896);
        }
    }
}

