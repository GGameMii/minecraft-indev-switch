/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client.renderer.entity;

import net.minecraft.client.gui.Font;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.lwjgl.opengl.GL11;
import util.Mth;

public class RenderLiving
extends Render {
    protected ModelBase mainModel;
    private ModelBase renderPassModel;

    public RenderLiving(ModelBase modelBase, float f2) {
        this.mainModel = modelBase;
        this.shadowSize = f2;
    }

    public final void setRenderPassModel(ModelBase modelBase) {
        this.renderPassModel = modelBase;
    }

    public void renderEntity(LivingEntity e2, float x2, float y2, float z2, float f5, float a2) {
        GL11.glPushMatrix();
        GL11.glDisable((int)2884);
        try {
            this.mainModel.swingProgress = this.getSwing(e2, a2);
            if (this.renderPassModel != null) {
                this.renderPassModel.swingProgress = this.mainModel.swingProgress;
            }
            f5 = e2.prevRenderYawOffset + (e2.renderYawOffset - e2.prevRenderYawOffset) * a2;
            float f7 = e2.xRotI + (e2.xRot - e2.xRotI) * a2;
            float f8 = e2.yRotI + (e2.yRot - e2.yRotI) * a2;
            GL11.glTranslatef((float)x2, (float)(y2 + e2.onlineOffset), (float)z2);
            float xx = (float)e2.ticks + a2;
            GL11.glRotatef((float)(180.0f - f5), (float)0.0f, (float)1.0f, (float)0.0f);
            if (e2.deathTime > 0) {
                float rot = Mth.sqrt(((float)e2.deathTime + a2 - 1.0f) / 20.0f * 1.6f);
                if (rot > 1.0f) {
                    rot = 1.0f;
                }
                GL11.glRotatef((float)(rot * this.getDeathMaxRotation(e2)), (float)0.0f, (float)0.0f, (float)1.0f);
            }
            GL11.glScalef((float)-0.0625f, (float)-0.0625f, (float)0.0625f);
            this.mainModel.attackTime = (float)e2.attackTime / 5.0f;
            this.preRenderCallback(e2, a2);
            GL11.glTranslatef((float)0.0f, (float)-24.0f, (float)0.0f);
            GL11.glEnable((int)2977);
            float yy = e2.walkAnimSpeedO + (e2.walkAnimSpeed - e2.walkAnimSpeedO) * a2;
            float zz = e2.walkAnimPos - e2.walkAnimSpeed * (1.0f - a2);
            if (yy > 1.0f) {
                yy = 1.0f;
            }
            this.loadDownloadableImageTexture(e2.skinUrl, e2.getTexture());
            GL11.glEnable((int)3008);
            this.mainModel.render(zz, yy, xx, f7 - f5, f8, 1.0f);
            int i2 = 0;
            while (i2 < 4) {
                if (this.shouldRenderPass(e2, i2)) {
                    this.renderPassModel.render(zz, yy, xx, f7 - f5, f8, 1.0f);
                    GL11.glDisable((int)3042);
                    GL11.glEnable((int)3008);
                }
                ++i2;
            }
            this.renderEquippedItems(e2, a2);
            float br = e2.getEntityBrightness(a2);
            int i14 = this.getColorMultiplier(e2, br, a2);
            if (i14 >>> 24 > 0 || e2.hurtTime > 0 || e2.deathTime > 0) {
                GL11.glDisable((int)3553);
                GL11.glDisable((int)3008);
                GL11.glEnable((int)3042);
                GL11.glBlendFunc((int)770, (int)771);
                GL11.glDepthFunc((int)514);
                if (e2.hurtTime > 0 || e2.deathTime > 0) {
                    GL11.glColor4f((float)br, (float)0.0f, (float)0.0f, (float)0.4f);
                    this.mainModel.render(zz, yy, xx, f7 - f5, f8, 1.0f);
                    int i10 = 0;
                    while (i10 < 4) {
                        if (this.shouldRenderPass(e2, i10)) {
                            GL11.glColor4f((float)br, (float)0.0f, (float)0.0f, (float)0.4f);
                            this.renderPassModel.render(zz, yy, xx, f7 - f5, f8, 1.0f);
                        }
                        ++i10;
                    }
                }
                if (i14 >>> 24 > 0) {
                    float f16 = (float)(i14 >> 16 & 0xFF) / 255.0f;
                    br = (float)(i14 >> 8 & 0xFF) / 255.0f;
                    float f11 = (float)(i14 & 0xFF) / 255.0f;
                    a2 = (float)(i14 >>> 24) / 255.0f;
                    GL11.glColor4f((float)f16, (float)br, (float)f11, (float)a2);
                    this.mainModel.render(zz, yy, xx, f7 - f5, f8, 1.0f);
                    int i12 = 0;
                    while (i12 < 4) {
                        if (this.shouldRenderPass(e2, i12)) {
                            GL11.glColor4f((float)f16, (float)br, (float)f11, (float)a2);
                            this.renderPassModel.render(zz, yy, xx, f7 - f5, f8, 1.0f);
                        }
                        ++i12;
                    }
                }
                GL11.glDepthFunc((int)515);
                GL11.glDisable((int)3042);
                GL11.glEnable((int)3008);
                GL11.glEnable((int)3553);
            }
            GL11.glDisable((int)2977);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        GL11.glEnable((int)2884);
        GL11.glPopMatrix();
        this.passSpecialRender(e2, x2, y2, z2);
    }

    protected float getSwing(LivingEntity e2, float a2) {
        return e2.getSwingProgress(a2);
    }

    protected void renderEquippedItems(LivingEntity e2, float a2) {
    }

    protected boolean shouldRenderPass(LivingEntity e2, int i2) {
        return false;
    }

    protected float getDeathMaxRotation(LivingEntity e2) {
        return 90.0f;
    }

    protected int getColorMultiplier(LivingEntity e2, float f2, float f3) {
        return 0;
    }

    protected void preRenderCallback(LivingEntity e2, float f2) {
    }

    @Override
    public void doRender(Entity e2, float f2, float f3, float f4, float f5, float f6) {
        this.renderEntity((LivingEntity)e2, f2, f3, f4, f5, f6);
    }

    protected void passSpecialRender(LivingEntity e2, float d2, float d4, float d6) {
    }

    protected void renderLivingLabel(LivingEntity e2, String name, double d3, double d5, double d7, int maxDist) {
        float dist = e2.getDistanceToEntity(this.renderManager.player);
        if (dist <= (float)maxDist) {
            Font font = this.renderManager.font;
            int c0 = 0x20FFFFFF;
            int c1 = -1;
            if (name.equals("MrLordSith")) {
                c1 = 0xFFFF00;
            }
            c0 = 0x505050;
            GL11.glPushMatrix();
            GL11.glTranslatef((float)((float)d3), (float)((float)(d5 + (double)0.8f + (double)1.62f + (double)e2.onlineOffset)), (float)((float)d7));
            GL11.glRotatef((float)(-this.renderManager.playerViewY + 180.0f), (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)(-this.renderManager.playerViewX), (float)1.0f, (float)0.0f, (float)0.0f);
            float f2 = 0.05f;
            GL11.glScalef((float)0.05f, (float)(-f2), (float)f2);
            GL11.glTranslatef((float)((float)(-font.width(name)) / 2.0f), (float)0.0f, (float)0.0f);
            GL11.glNormal3f((float)1.0f, (float)-1.0f, (float)1.0f);
            GL11.glDisable((int)2896);
            GL11.glDisable((int)16384);
            if (name.equalsIgnoreCase("MrLordSith")) {
                font.draw(name, 0, 0, 0xFFFF00);
            } else {
                font.draw(name, 0, 0, c1);
            }
            GL11.glDepthFunc((int)516);
            GL11.glDepthMask((boolean)false);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)0.8f);
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            font.draw(name, 0, 0, c1);
            GL11.glDisable((int)3042);
            GL11.glDepthMask((boolean)true);
            GL11.glDepthFunc((int)515);
            GL11.glTranslatef((float)1.0f, (float)1.0f, (float)-0.05f);
            font.draw(name, 0, 0, c0);
            GL11.glEnable((int)16384);
            GL11.glEnable((int)2896);
            GL11.glEnable((int)3553);
            GL11.glPopMatrix();
        }
    }
}

