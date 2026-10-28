/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client.renderer.entity;

import java.util.Random;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.client.renderer.Tesselator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.tile.Tile;
import org.lwjgl.opengl.GL11;
import util.Mth;

public final class RenderItem
extends Render {
    private RenderBlocks renderBlocks = new RenderBlocks();
    private Random random = new Random();

    public RenderItem() {
        this.shadowSize = 0.15f;
        this.shadowOpaque = 0.75f;
    }

    public final void renderItemIntoGUI(RenderEngine renderEngineClass, ItemStack itemStackClass, int i3, int i4) {
        if (itemStackClass != null) {
            if (itemStackClass.itemID < 256 && Tile.tiles[itemStackClass.itemID].getRenderType() == 0) {
                int itemStackClass1 = itemStackClass.itemID;
                RenderEngine.bind(renderEngineClass.getTexture("/terrain.png"));
                Tile renderEngineClass3 = Tile.tiles[itemStackClass1];
                GL11.glPushMatrix();
                GL11.glTranslatef((float)(i3 - 2), (float)(i4 + 3), (float)0.0f);
                GL11.glScalef((float)10.0f, (float)10.0f, (float)10.0f);
                GL11.glTranslatef((float)1.0f, (float)0.5f, (float)8.0f);
                GL11.glRotatef((float)210.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                this.renderBlocks.renderBlockOnInventory(renderEngineClass3);
                GL11.glPopMatrix();
            } else if (itemStackClass.getItem().getIconIndex() >= 0) {
                int i10003;
                GL11.glDisable((int)2896);
                if (itemStackClass.itemID < 256) {
                    RenderEngine.bind(renderEngineClass.getTexture("/terrain.png"));
                } else {
                    RenderEngine.bind(renderEngineClass.getTexture("/gui/items.png"));
                }
                int i10000 = i3;
                int i10001 = i4;
                int i10002 = itemStackClass.getItem().getIconIndex() % 16 << 4;
                i4 = i10003 = itemStackClass.getItem().getIconIndex() / 16 << 4;
                i3 = i10002;
                int itemStackClass1 = i10001;
                int renderEngineClass2 = i10000;
                Tesselator tessellator5 = Tesselator.instance;
                Tesselator.instance.begin();
                tessellator5.vertexUV(renderEngineClass2, itemStackClass1 + 16, 0.0f, (float)i3 * 0.00390625f, (float)(i4 + 16) * 0.00390625f);
                tessellator5.vertexUV(renderEngineClass2 + 16, itemStackClass1 + 16, 0.0f, (float)(i3 + 16) * 0.00390625f, (float)(i4 + 16) * 0.00390625f);
                tessellator5.vertexUV(renderEngineClass2 + 16, itemStackClass1, 0.0f, (float)(i3 + 16) * 0.00390625f, (float)i4 * 0.00390625f);
                tessellator5.vertexUV(renderEngineClass2, itemStackClass1, 0.0f, (float)i3 * 0.00390625f, (float)i4 * 0.00390625f);
                tessellator5.end();
                GL11.glEnable((int)2896);
            }
        }
    }

    public final void renderItemOverlayIntoGUI(Font fontRenderer1, RenderEngine renderEngine, ItemStack itemStack2, int i3, int i4) {
        if (itemStack2 != null) {
            if (itemStack2.stackSize > 1) {
                String string5 = "" + itemStack2.stackSize;
                GL11.glDisable((int)2896);
                GL11.glDisable((int)2929);
                fontRenderer1.drawShadow(string5, i3 + 19 - 2 - fontRenderer1.width(string5), i4 + 6 + 3, 0xFFFFFF);
                GL11.glEnable((int)2896);
                GL11.glEnable((int)2929);
            }
            if (itemStack2.itemDamage > 0) {
                int i9 = 13 - itemStack2.itemDamage * 13 / itemStack2.getMaxDamage();
                int i7 = 255 - itemStack2.itemDamage * 255 / itemStack2.getMaxDamage();
                GL11.glDisable((int)2896);
                GL11.glDisable((int)2929);
                GL11.glDisable((int)3553);
                Tesselator tessellator8 = Tesselator.instance;
                int i6 = 255 - i7 << 16 | i7 << 8;
                i7 = (255 - i7) / 4 << 16 | 0x3F00;
                RenderItem.renderQuad(tessellator8, i3 + 2, i4 + 13, 13, 2, 0);
                RenderItem.renderQuad(tessellator8, i3 + 2, i4 + 13, 12, 1, i7);
                RenderItem.renderQuad(tessellator8, i3 + 2, i4 + 13, i9, 1, i6);
                GL11.glEnable((int)3553);
                GL11.glEnable((int)2896);
                GL11.glEnable((int)2929);
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            }
        }
    }

    private static void renderQuad(Tesselator tessellator0, int i1, int i2, int i3, int i4, int i5) {
        tessellator0.begin();
        tessellator0.color(i5);
        tessellator0.addVertex(i1, i2, 0.0f);
        tessellator0.addVertex(i1, i2 + i4, 0.0f);
        tessellator0.addVertex(i1 + i3, i2 + i4, 0.0f);
        tessellator0.addVertex(i1 + i3, i2, 0.0f);
        tessellator0.end();
    }

    @Override
    public final void doRender(Entity entity, float f2, float f3, float f4, float f5, float f6) {
        ItemEntity entityItem13 = (ItemEntity)entity;
        RenderItem renderItem12 = this;
        this.random.setSeed(187L);
        ItemStack itemStack7 = entityItem13.item;
        GL11.glPushMatrix();
        float f8 = Mth.sin(((float)entityItem13.age + f6) / 10.0f + entityItem13.hoverStart) * 0.1f + 0.1f;
        f6 = (((float)entityItem13.age + f6) / 20.0f + entityItem13.hoverStart) * 57.295776f;
        int b9 = 1;
        if (entityItem13.item.stackSize > 1) {
            b9 = 2;
        }
        if (entityItem13.item.stackSize > 5) {
            b9 = 3;
        }
        if (entityItem13.item.stackSize > 20) {
            b9 = 4;
        }
        GL11.glTranslatef((float)f2, (float)(f3 + f8), (float)f4);
        GL11.glEnable((int)2977);
        if (itemStack7.itemID < 256 && Tile.tiles[itemStack7.itemID].getRenderType() == 0) {
            GL11.glRotatef((float)f6, (float)0.0f, (float)1.0f, (float)0.0f);
            this.loadTexture("/terrain.png");
            f2 = 0.25f;
            if (!Tile.tiles[itemStack7.itemID].renderAsNormalBlock() && itemStack7.itemID != Tile.stairSingle.id) {
                f2 = 0.5f;
            }
            GL11.glScalef((float)f2, (float)f2, (float)f2);
            int i16 = 0;
            while (i16 < b9) {
                GL11.glPushMatrix();
                if (i16 > 0) {
                    f4 = (renderItem12.random.nextFloat() * 2.0f - 1.0f) * 0.2f / f2;
                    f5 = (renderItem12.random.nextFloat() * 2.0f - 1.0f) * 0.2f / f2;
                    f6 = (renderItem12.random.nextFloat() * 2.0f - 1.0f) * 0.2f / f2;
                    GL11.glTranslatef((float)f4, (float)f5, (float)f6);
                }
                renderItem12.renderBlocks.renderBlockOnInventory(Tile.tiles[itemStack7.itemID]);
                GL11.glPopMatrix();
                ++i16;
            }
        } else {
            GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
            int i14 = itemStack7.getItem().getIconIndex();
            if (itemStack7.itemID < 256) {
                this.loadTexture("/terrain.png");
            } else {
                this.loadTexture("/gui/items.png");
            }
            Tesselator tessellator15 = Tesselator.instance;
            f4 = (float)(i14 % 16 << 4) / 256.0f;
            f5 = (float)((i14 % 16 << 4) + 16) / 256.0f;
            f6 = (float)(i14 / 16 << 4) / 256.0f;
            f2 = (float)((i14 / 16 << 4) + 16) / 256.0f;
            int i17 = 0;
            while (i17 < b9) {
                GL11.glPushMatrix();
                if (i17 > 0) {
                    f8 = (renderItem12.random.nextFloat() * 2.0f - 1.0f) * 0.3f;
                    float f10 = (renderItem12.random.nextFloat() * 2.0f - 1.0f) * 0.3f;
                    float f11 = (renderItem12.random.nextFloat() * 2.0f - 1.0f) * 0.3f;
                    GL11.glTranslatef((float)f8, (float)f10, (float)f11);
                }
                GL11.glRotatef((float)(180.0f - renderItem12.renderManager.playerViewY), (float)0.0f, (float)1.0f, (float)0.0f);
                tessellator15.begin();
                Tesselator.setNormal(0.0f, 1.0f, 0.0f);
                tessellator15.vertexUV(-0.5f, -0.25f, 0.0f, f4, f2);
                tessellator15.vertexUV(0.5f, -0.25f, 0.0f, f5, f2);
                tessellator15.vertexUV(0.5f, 0.75f, 0.0f, f5, f6);
                tessellator15.vertexUV(-0.5f, 0.75f, 0.0f, f4, f6);
                tessellator15.end();
                GL11.glPopMatrix();
                ++i17;
            }
        }
        GL11.glDisable((int)2977);
        GL11.glPopMatrix();
    }
}

