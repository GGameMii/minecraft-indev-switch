/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client.renderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.RenderHelper;
import net.minecraft.client.player.PlayerSP;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tesselator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.tile.Tile;
import org.lwjgl.opengl.GL11;
import util.Mth;

public final class ItemRenderer {
    private Minecraft mc;
    private ItemStack itemToRender = null;
    private float equippedProgress = 0.0f;
    private float prevEquippedProgress = 0.0f;
    private RenderBlocks renderBlocksInstance = new RenderBlocks();

    public ItemRenderer(Minecraft minecraft) {
        this.mc = minecraft;
    }

    public void renderItem(LivingEntity var1, ItemStack var2) {
        GL11.glPushMatrix();
        if (var2.itemID < 256 && Tile.tiles[var2.itemID].getRenderType() == 0) {
            GL11.glBindTexture((int)3553, (int)this.mc.renderEngine.getTexture("/terrain.png"));
            this.renderBlocksInstance.renderBlockOnInventory(Tile.tiles[var2.itemID]);
        } else {
            float var17;
            float var16;
            float var15;
            if (var2.itemID < 256) {
                GL11.glBindTexture((int)3553, (int)this.mc.renderEngine.getTexture("/terrain.png"));
            } else {
                GL11.glBindTexture((int)3553, (int)this.mc.renderEngine.getTexture("/gui/items.png"));
            }
            Tesselator var3 = Tesselator.instance;
            float var5 = (float)(var2.getItem().getIconIndex() % 16 << 4) / 256.0f;
            float var6 = (float)((var2.getItem().getIconIndex() % 16 << 4) + 16) / 256.0f;
            float var7 = (float)(var2.getItem().getIconIndex() / 16 << 4) / 256.0f;
            float var8 = (float)((var2.getItem().getIconIndex() / 16 << 4) + 16) / 256.0f;
            float var9 = 1.0f;
            float var10 = 0.0f;
            float var11 = 0.3f;
            GL11.glEnable((int)32826);
            GL11.glTranslatef((float)(-var10), (float)(-var11), (float)0.0f);
            float var12 = 2.125f;
            GL11.glScalef((float)var12, (float)var12, (float)var12);
            GL11.glRotatef((float)50.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)310.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glTranslatef((float)-0.9375f, (float)-0.0625f, (float)0.0f);
            float var13 = 0.0625f;
            var3.begin();
            Tesselator.setNormal(0.0f, 0.0f, 1.0f);
            var3.vertexUV(0.0f, 0.0f, 0.0f, var6, var8);
            var3.vertexUV(var9, 0.0f, 0.0f, var5, var8);
            var3.vertexUV(var9, 1.0f, 0.0f, var5, var7);
            var3.vertexUV(0.0f, 1.0f, 0.0f, var6, var7);
            var3.end();
            var3.begin();
            Tesselator.setNormal(0.0f, 0.0f, -1.0f);
            var3.vertexUV(0.0f, 1.0f, 0.0f - var13, var6, var7);
            var3.vertexUV(var9, 1.0f, 0.0f - var13, var5, var7);
            var3.vertexUV(var9, 0.0f, 0.0f - var13, var5, var8);
            var3.vertexUV(0.0f, 0.0f, 0.0f - var13, var6, var8);
            var3.end();
            var3.begin();
            Tesselator.setNormal(-1.0f, 0.0f, 0.0f);
            int var14 = 0;
            while (var14 < 16) {
                var15 = (float)var14 / 16.0f;
                var16 = var6 + (var5 - var6) * var15 - 0.001953125f;
                var17 = var9 * var15;
                var3.vertexUV(var17, 0.0f, 0.0f - var13, var16, var8);
                var3.vertexUV(var17, 0.0f, 0.0f, var16, var8);
                var3.vertexUV(var17, 1.0f, 0.0f, var16, var7);
                var3.vertexUV(var17, 1.0f, 0.0f - var13, var16, var7);
                ++var14;
            }
            var3.end();
            var3.begin();
            Tesselator.setNormal(1.0f, 0.0f, 0.0f);
            var14 = 0;
            while (var14 < 16) {
                var15 = (float)var14 / 16.0f;
                var16 = var6 + (var5 - var6) * var15 - 0.001953125f;
                var17 = var9 * var15 + 0.0625f;
                var3.vertexUV(var17, 1.0f, 0.0f - var13, var16, var7);
                var3.vertexUV(var17, 1.0f, 0.0f, var16, var7);
                var3.vertexUV(var17, 0.0f, 0.0f, var16, var8);
                var3.vertexUV(var17, 0.0f, 0.0f - var13, var16, var8);
                ++var14;
            }
            var3.end();
            var3.begin();
            Tesselator.setNormal(0.0f, 1.0f, 0.0f);
            var14 = 0;
            while (var14 < 16) {
                var15 = (float)var14 / 16.0f;
                var16 = var8 + (var7 - var8) * var15 - 0.001953125f;
                var17 = var9 * var15 + 0.0625f;
                var3.vertexUV(0.0f, var17, 0.0f, var6, var16);
                var3.vertexUV(var9, var17, 0.0f, var5, var16);
                var3.vertexUV(var9, var17, 0.0f - var13, var5, var16);
                var3.vertexUV(0.0f, var17, 0.0f - var13, var6, var16);
                ++var14;
            }
            var3.end();
            var3.begin();
            Tesselator.setNormal(0.0f, -1.0f, 0.0f);
            var14 = 0;
            while (var14 < 16) {
                var15 = (float)var14 / 16.0f;
                var16 = var8 + (var7 - var8) * var15 - 0.001953125f;
                var17 = var9 * var15;
                var3.vertexUV(var9, var17, 0.0f, var5, var16);
                var3.vertexUV(0.0f, var17, 0.0f, var6, var16);
                var3.vertexUV(0.0f, var17, 0.0f - var13, var6, var16);
                var3.vertexUV(var9, var17, 0.0f - var13, var5, var16);
                ++var14;
            }
            var3.end();
            GL11.glDisable((int)32826);
        }
        GL11.glPopMatrix();
    }

    public final void renderItemInFirstPerson(float a2) {
        float f2 = this.prevEquippedProgress + (this.equippedProgress - this.prevEquippedProgress) * a2;
        PlayerSP player = this.mc.player;
        GL11.glPushMatrix();
        GL11.glRotatef((float)(player.yRotI + (player.yRot - player.yRotI) * a2), (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glRotatef((float)(player.xRotI + (player.xRot - player.xRotI) * a2), (float)0.0f, (float)1.0f, (float)0.0f);
        RenderHelper.enableStandardItemLighting();
        GL11.glPopMatrix();
        float f9 = this.mc.level.getLightBrightness((int)player.x, (int)player.y, (int)player.z);
        GL11.glColor4f((float)f9, (float)f9, (float)f9, (float)1.0f);
        if (this.itemToRender != null) {
            float f5;
            float f4;
            GL11.glPushMatrix();
            if (player.swinging) {
                f9 = player.getSwingProgress(a2);
                f4 = Mth.sin(f9 * (float)Math.PI);
                GL11.glTranslatef((float)(-Mth.sin(Mth.sqrt(f9) * (float)Math.PI) * 0.4f), (float)(Mth.sin(Mth.sqrt(f9) * (float)Math.PI * 2.0f) * 0.2f), (float)(-f4 * 0.2f));
            }
            GL11.glTranslatef((float)0.56f, (float)(-0.52f - (1.0f - f2) * 0.6f), (float)-0.71999997f);
            GL11.glRotatef((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glEnable((int)2977);
            if (player.swinging) {
                f9 = player.getSwingProgress(a2);
                f4 = Mth.sin(f9 * f9 * (float)Math.PI);
                f5 = Mth.sin(Mth.sqrt(f9) * (float)Math.PI);
                GL11.glRotatef((float)(-f4 * 20.0f), (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glRotatef((float)(-f5 * 20.0f), (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glRotatef((float)(-f5 * 80.0f), (float)1.0f, (float)0.0f, (float)0.0f);
            }
            GL11.glScalef((float)0.4f, (float)0.4f, (float)0.4f);
            if (this.itemToRender.itemID < 256 && Tile.tiles[this.itemToRender.itemID].getRenderType() == 0) {
                GL11.glBindTexture((int)3553, (int)this.mc.renderEngine.getTexture("/terrain.png"));
                this.renderBlocksInstance.renderBlockOnInventory(Tile.tiles[this.itemToRender.itemID]);
            } else {
                float f8;
                float f7;
                if (this.itemToRender.itemID < 256) {
                    GL11.glBindTexture((int)3553, (int)this.mc.renderEngine.getTexture("/terrain.png"));
                } else {
                    GL11.glBindTexture((int)3553, (int)this.mc.renderEngine.getTexture("/gui/items.png"));
                }
                Tesselator tessellator11 = Tesselator.instance;
                f5 = (float)(this.itemToRender.getItem().getIconIndex() % 16 << 4) / 256.0f;
                a2 = (float)((this.itemToRender.getItem().getIconIndex() % 16 << 4) + 16) / 256.0f;
                f2 = (float)(this.itemToRender.getItem().getIconIndex() / 16 << 4) / 256.0f;
                f9 = (float)((this.itemToRender.getItem().getIconIndex() / 16 << 4) + 16) / 256.0f;
                GL11.glEnable((int)2977);
                GL11.glTranslatef((float)0.0f, (float)-0.3f, (float)0.0f);
                GL11.glScalef((float)1.5f, (float)1.5f, (float)1.5f);
                GL11.glRotatef((float)50.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glRotatef((float)335.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glTranslatef((float)-0.9375f, (float)-0.0625f, (float)0.0f);
                Tesselator.setNormal(0.0f, 0.0f, 1.0f);
                tessellator11.begin();
                tessellator11.vertexUV(0.0f, 0.0f, 0.0f, a2, f9);
                tessellator11.vertexUV(1.0f, 0.0f, 0.0f, f5, f9);
                tessellator11.vertexUV(1.0f, 1.0f, 0.0f, f5, f2);
                tessellator11.vertexUV(0.0f, 1.0f, 0.0f, a2, f2);
                tessellator11.end();
                Tesselator.setNormal(0.0f, 0.0f, -1.0f);
                tessellator11.begin();
                tessellator11.vertexUV(0.0f, 1.0f, -0.0625f, a2, f2);
                tessellator11.vertexUV(1.0f, 1.0f, -0.0625f, f5, f2);
                tessellator11.vertexUV(1.0f, 0.0f, -0.0625f, f5, f9);
                tessellator11.vertexUV(0.0f, 0.0f, -0.0625f, a2, f9);
                tessellator11.end();
                Tesselator.setNormal(-1.0f, 0.0f, 0.0f);
                tessellator11.begin();
                int i6 = 0;
                while (i6 < 16) {
                    f7 = (float)i6 / 16.0f;
                    f8 = a2 + (f5 - a2) * f7 - 0.001953125f;
                    tessellator11.vertexUV(f7 *= 1.0f, 0.0f, -0.0625f, f8, f9);
                    tessellator11.vertexUV(f7, 0.0f, 0.0f, f8, f9);
                    tessellator11.vertexUV(f7, 1.0f, 0.0f, f8, f2);
                    tessellator11.vertexUV(f7, 1.0f, -0.0625f, f8, f2);
                    ++i6;
                }
                tessellator11.end();
                Tesselator.setNormal(1.0f, 0.0f, 0.0f);
                tessellator11.begin();
                i6 = 0;
                while (i6 < 16) {
                    f7 = (float)i6 / 16.0f;
                    f8 = a2 + (f5 - a2) * f7 - 0.001953125f;
                    f7 = f7 * 1.0f + 0.0625f;
                    tessellator11.vertexUV(f7, 1.0f, -0.0625f, f8, f2);
                    tessellator11.vertexUV(f7, 1.0f, 0.0f, f8, f2);
                    tessellator11.vertexUV(f7, 0.0f, 0.0f, f8, f9);
                    tessellator11.vertexUV(f7, 0.0f, -0.0625f, f8, f9);
                    ++i6;
                }
                tessellator11.end();
                Tesselator.setNormal(0.0f, 1.0f, 0.0f);
                tessellator11.begin();
                i6 = 0;
                while (i6 < 16) {
                    f7 = (float)i6 / 16.0f;
                    f8 = f9 + (f2 - f9) * f7 - 0.001953125f;
                    f7 = f7 * 1.0f + 0.0625f;
                    tessellator11.vertexUV(0.0f, f7, 0.0f, a2, f8);
                    tessellator11.vertexUV(1.0f, f7, 0.0f, f5, f8);
                    tessellator11.vertexUV(1.0f, f7, -0.0625f, f5, f8);
                    tessellator11.vertexUV(0.0f, f7, -0.0625f, a2, f8);
                    ++i6;
                }
                tessellator11.end();
                Tesselator.setNormal(0.0f, -1.0f, 0.0f);
                tessellator11.begin();
                i6 = 0;
                while (i6 < 16) {
                    f7 = (float)i6 / 16.0f;
                    f8 = f9 + (f2 - f9) * f7 - 0.001953125f;
                    tessellator11.vertexUV(1.0f, f7 *= 1.0f, 0.0f, f5, f8);
                    tessellator11.vertexUV(0.0f, f7, 0.0f, a2, f8);
                    tessellator11.vertexUV(0.0f, f7, -0.0625f, a2, f8);
                    tessellator11.vertexUV(1.0f, f7, -0.0625f, f5, f8);
                    ++i6;
                }
                tessellator11.end();
                GL11.glDisable((int)2977);
            }
            GL11.glPopMatrix();
        } else {
            float f4;
            GL11.glPushMatrix();
            if (player.swinging) {
                f9 = player.getSwingProgress(a2);
                f4 = Mth.sin(f9 * (float)Math.PI);
                GL11.glTranslatef((float)(-Mth.sin(Mth.sqrt(f9) * (float)Math.PI) * 0.3f), (float)(Mth.sin(Mth.sqrt(f9) * (float)Math.PI * 2.0f) * 0.4f), (float)(-f4 * 0.4f));
            }
            GL11.glTranslatef((float)0.64000005f, (float)(-0.6f - (1.0f - f2) * 0.6f), (float)-0.71999997f);
            GL11.glRotatef((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glEnable((int)2977);
            if (player.swinging) {
                f9 = player.getSwingProgress(a2);
                f4 = Mth.sin(f9 * f9 * (float)Math.PI);
                GL11.glRotatef((float)(Mth.sin(Mth.sqrt(f9) * (float)Math.PI) * 70.0f), (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glRotatef((float)(-f4 * 20.0f), (float)0.0f, (float)0.0f, (float)1.0f);
            }
            GL11.glBindTexture((int)3553, (int)this.mc.renderEngine.loadHttpTexture(this.mc.player.skinUrl, this.mc.player.getTexture()));
            GL11.glTranslatef((float)-0.2f, (float)-0.3f, (float)0.1f);
            GL11.glRotatef((float)120.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glRotatef((float)200.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)-135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glScalef((float)0.0625f, (float)0.0625f, (float)0.0625f);
            GL11.glTranslatef((float)6.0f, (float)0.0f, (float)0.0f);
            ((RenderPlayer)RenderManager.instance.getEntityRenderObject(this.mc.player)).drawFirstPersonHand();
            GL11.glPopMatrix();
        }
        GL11.glDisable((int)2977);
        RenderHelper.disableStandardItemLighting();
    }

    public final void renderOverlays(float f1) {
        float f9;
        float f7;
        Tesselator tessellator3;
        int i2;
        GL11.glDisable((int)3008);
        if (this.mc.player.fire > 0) {
            i2 = this.mc.renderEngine.getTexture("/terrain.png");
            GL11.glBindTexture((int)3553, (int)i2);
            tessellator3 = Tesselator.instance;
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)0.9f);
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            i2 = 0;
            while (i2 < 2) {
                GL11.glPushMatrix();
                int i4 = Tile.fire.tex + (i2 << 4);
                int i5 = (i4 & 0xF) << 4;
                float f6 = (float)i5 / 256.0f;
                float f10 = ((float)i5 + 15.99f) / 256.0f;
                f7 = (float)(i4 &= 0xF0) / 256.0f;
                f9 = ((float)i4 + 15.99f) / 256.0f;
                GL11.glTranslatef((float)((float)(-((i2 << 1) - 1)) * 0.24f), (float)-0.3f, (float)0.0f);
                GL11.glRotatef((float)((float)((i2 << 1) - 1) * 10.0f), (float)0.0f, (float)1.0f, (float)0.0f);
                tessellator3.begin();
                tessellator3.vertexUV(-0.5f, -0.5f, -0.5f, f10, f9);
                tessellator3.vertexUV(0.5f, -0.5f, -0.5f, f6, f9);
                tessellator3.vertexUV(0.5f, 0.5f, -0.5f, f6, f7);
                tessellator3.vertexUV(-0.5f, 0.5f, -0.5f, f10, f7);
                tessellator3.end();
                GL11.glPopMatrix();
                ++i2;
            }
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glDisable((int)3042);
        }
        if (this.mc.player.isInsideOfWater()) {
            i2 = this.mc.renderEngine.getTexture("/water.png");
            GL11.glBindTexture((int)3553, (int)i2);
            tessellator3 = Tesselator.instance;
            float f8 = this.mc.player.getEntityBrightness(f1);
            GL11.glColor4f((float)f8, (float)f8, (float)f8, (float)0.5f);
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            GL11.glPushMatrix();
            f7 = -this.mc.player.xRot / 64.0f;
            f9 = this.mc.player.yRot / 64.0f;
            tessellator3.begin();
            tessellator3.vertexUV(-1.0f, -1.0f, -0.5f, f7 + 4.0f, f9 + 4.0f);
            tessellator3.vertexUV(1.0f, -1.0f, -0.5f, f7 + 0.0f, f9 + 4.0f);
            tessellator3.vertexUV(1.0f, 1.0f, -0.5f, f7 + 0.0f, f9 + 0.0f);
            tessellator3.vertexUV(-1.0f, 1.0f, -0.5f, f7 + 4.0f, f9 + 0.0f);
            tessellator3.end();
            GL11.glPopMatrix();
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glDisable((int)3042);
        }
        GL11.glEnable((int)3008);
    }

    public final void updateEquippedItem() {
        this.prevEquippedProgress = this.equippedProgress;
        ItemStack itemStack3 = this.mc.player.inventory.getCurrentItem();
        float f22 = (itemStack3 == this.itemToRender ? 1.0f : 0.0f) - this.equippedProgress;
        if (f22 < -0.4f) {
            f22 = -0.4f;
        }
        if (f22 > 0.4f) {
            f22 = 0.4f;
        }
        this.equippedProgress += f22;
        if (this.equippedProgress < 0.1f) {
            this.itemToRender = itemStack3;
        }
    }

    public final void equipAnimationSpeed() {
        this.equippedProgress = 0.0f;
    }

    public final void resetEquippedProgress() {
        this.equippedProgress = 0.0f;
    }
}

