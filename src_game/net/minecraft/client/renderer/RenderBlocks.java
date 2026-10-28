/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client.renderer;

import net.minecraft.client.renderer.Tesselator;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.tile.Tile;
import org.lwjgl.opengl.GL11;

public final class RenderBlocks {
    private Level blockAccess;
    private int overrideBlockTexture = -1;
    private boolean renderAllFaces = false;

    public RenderBlocks(Level world) {
        this.blockAccess = world;
    }

    public RenderBlocks() {
    }

    public final void renderBlockUsingTexture(Tile block, int i2, int i3, int i4, int i5) {
        this.overrideBlockTexture = i5;
        this.renderBlockByRenderType(block, i2, i3, i4);
        this.overrideBlockTexture = -1;
    }

    public final void renderBlockAllFaces(Tile block, int i2, int i3, int i4) {
        this.renderAllFaces = true;
        this.renderBlockByRenderType(block, i2, i3, i4);
        this.renderAllFaces = false;
    }

    public final boolean renderBlockByRenderType(Tile block, int i2, int i3, int i4) {
        int i5 = block.getRenderType();
        if (i5 == 0) {
            float f10;
            Tesselator tessellator6 = Tesselator.instance;
            boolean z26 = false;
            if (this.renderAllFaces || block.shouldRenderFace(this.blockAccess, i2, i3 - 1, i4, 0)) {
                f10 = block.getBrightness(this.blockAccess, i2, i3 - 1, i4);
                if (Tile.lightValue[block.id] > 0) {
                    f10 = 1.0f;
                }
                tessellator6.setColorOpaque_F(0.5f * f10, 0.5f * f10, 0.5f * f10);
                this.renderBlockBottom(block, i2, i3, i4, block.getTexture(this.blockAccess, i2, i3, i4, 0));
                z26 = true;
            }
            if (this.renderAllFaces || block.shouldRenderFace(this.blockAccess, i2, i3 + 1, i4, 1)) {
                f10 = block.getBrightness(this.blockAccess, i2, i3 + 1, i4);
                if (Tile.lightValue[block.id] > 0) {
                    f10 = 1.0f;
                }
                tessellator6.setColorOpaque_F(f10 * 1.0f, f10 * 1.0f, f10 * 1.0f);
                this.renderBlockTop(block, i2, i3, i4, block.getTexture(this.blockAccess, i2, i3, i4, 1));
                z26 = true;
            }
            if (this.renderAllFaces || block.shouldRenderFace(this.blockAccess, i2, i3, i4 - 1, 2)) {
                f10 = block.getBrightness(this.blockAccess, i2, i3, i4 - 1);
                if (Tile.lightValue[block.id] > 0) {
                    f10 = 1.0f;
                }
                tessellator6.setColorOpaque_F(0.8f * f10, 0.8f * f10, 0.8f * f10);
                this.renderBlockNorth(block, i2, i3, i4, block.getTexture(this.blockAccess, i2, i3, i4, 2));
                z26 = true;
            }
            if (this.renderAllFaces || block.shouldRenderFace(this.blockAccess, i2, i3, i4 + 1, 3)) {
                f10 = block.getBrightness(this.blockAccess, i2, i3, i4 + 1);
                if (Tile.lightValue[block.id] > 0) {
                    f10 = 1.0f;
                }
                tessellator6.setColorOpaque_F(0.8f * f10, 0.8f * f10, 0.8f * f10);
                this.renderBlockSouth(block, i2, i3, i4, block.getTexture(this.blockAccess, i2, i3, i4, 3));
                z26 = true;
            }
            if (this.renderAllFaces || block.shouldRenderFace(this.blockAccess, i2 - 1, i3, i4, 4)) {
                f10 = block.getBrightness(this.blockAccess, i2 - 1, i3, i4);
                if (Tile.lightValue[block.id] > 0) {
                    f10 = 1.0f;
                }
                tessellator6.setColorOpaque_F(0.6f * f10, 0.6f * f10, 0.6f * f10);
                this.renderBlockWest(block, i2, i3, i4, block.getTexture(this.blockAccess, i2, i3, i4, 4));
                z26 = true;
            }
            if (this.renderAllFaces || block.shouldRenderFace(this.blockAccess, i2 + 1, i3, i4, 5)) {
                f10 = block.getBrightness(this.blockAccess, i2 + 1, i3, i4);
                if (Tile.lightValue[block.id] > 0) {
                    f10 = 1.0f;
                }
                tessellator6.setColorOpaque_F(0.6f * f10, 0.6f * f10, 0.6f * f10);
                this.renderBlockEast(block, i2, i3, i4, block.getTexture(this.blockAccess, i2, i3, i4, 5));
                z26 = true;
            }
            return z26;
        }
        if (i5 == 4) {
            float f22;
            Tesselator tessellator6 = Tesselator.instance;
            boolean z26 = false;
            float f10 = block.yy0;
            float f11 = block.yy1;
            block.yy1 = f11 - this.materialNotWater(i2, i3, i4);
            if (this.renderAllFaces || block.shouldRenderFace(this.blockAccess, i2, i3 - 1, i4, 0)) {
                f22 = block.getBrightness(this.blockAccess, i2, i3 - 1, i4);
                tessellator6.setColorOpaque_F(0.5f * f22, 0.5f * f22, 0.5f * f22);
                this.renderBlockBottom(block, i2, i3, i4, block.getTexture(0));
                z26 = true;
            }
            if (this.renderAllFaces || block.shouldRenderFace(this.blockAccess, i2, i3 + 1, i4, 1)) {
                f22 = block.getBrightness(this.blockAccess, i2, i3 + 1, i4);
                tessellator6.setColorOpaque_F(f22 * 1.0f, f22 * 1.0f, f22 * 1.0f);
                this.renderBlockTop(block, i2, i3, i4, block.getTexture(1));
                z26 = true;
            }
            block.yy0 = f11 - this.materialNotWater(i2, i3, i4 - 1);
            if (this.renderAllFaces || block.yy1 > block.yy0 || block.shouldRenderFace(this.blockAccess, i2, i3, i4 - 1, 2)) {
                f22 = block.getBrightness(this.blockAccess, i2, i3, i4 - 1);
                tessellator6.setColorOpaque_F(0.8f * f22, 0.8f * f22, 0.8f * f22);
                this.renderBlockNorth(block, i2, i3, i4, block.getTexture(2));
                z26 = true;
            }
            block.yy0 = f11 - this.materialNotWater(i2, i3, i4 + 1);
            if (this.renderAllFaces || block.yy1 > block.yy0 || block.shouldRenderFace(this.blockAccess, i2, i3, i4 + 1, 3)) {
                f22 = block.getBrightness(this.blockAccess, i2, i3, i4 + 1);
                tessellator6.setColorOpaque_F(0.8f * f22, 0.8f * f22, 0.8f * f22);
                this.renderBlockSouth(block, i2, i3, i4, block.getTexture(3));
                z26 = true;
            }
            block.yy0 = f11 - this.materialNotWater(i2 - 1, i3, i4);
            if (this.renderAllFaces || block.yy1 > block.yy0 || block.shouldRenderFace(this.blockAccess, i2 - 1, i3, i4, 4)) {
                f22 = block.getBrightness(this.blockAccess, i2 - 1, i3, i4);
                tessellator6.setColorOpaque_F(0.6f * f22, 0.6f * f22, 0.6f * f22);
                this.renderBlockWest(block, i2, i3, i4, block.getTexture(4));
                z26 = true;
            }
            block.yy0 = f11 - this.materialNotWater(i2 + 1, i3, i4);
            if (this.renderAllFaces || block.yy1 > block.yy0 || block.shouldRenderFace(this.blockAccess, i2 + 1, i3, i4, 5)) {
                f22 = block.getBrightness(this.blockAccess, i2 + 1, i3, i4);
                tessellator6.setColorOpaque_F(0.6f * f22, 0.6f * f22, 0.6f * f22);
                this.renderBlockEast(block, i2, i3, i4, block.getTexture(5));
                z26 = true;
            }
            block.yy0 = f10;
            block.yy1 = f11;
            return z26;
        }
        if (i5 == 1) {
            Tesselator tessellator6 = Tesselator.instance;
            float f22 = block.getBrightness(this.blockAccess, i2, i3, i4);
            tessellator6.setColorOpaque_F(f22, f22, f22);
            this.renderBlockPlant(block, this.blockAccess.getTileMetadata(i2, i3, i4), i2, i3, i4);
            return true;
        }
        if (i5 == 6) {
            Tesselator tessellator6 = Tesselator.instance;
            float f22 = block.getBrightness(this.blockAccess, i2, i3, i4);
            tessellator6.setColorOpaque_F(f22, f22, f22);
            this.renderBlockCrops(block, this.blockAccess.getTileMetadata(i2, i3, i4), i2, (float)i3 - 0.0625f, i4);
            return true;
        }
        if (i5 == 2) {
            byte b21 = this.blockAccess.getTileMetadata(i2, i3, i4);
            Tesselator tessellator25 = Tesselator.instance;
            float f8 = block.getBrightness(this.blockAccess, i2, i3, i4);
            if (Tile.lightValue[block.id] > 0) {
                f8 = 1.0f;
            }
            tessellator25.setColorOpaque_F(f8, f8, f8);
            if (b21 == 1) {
                this.renderBlockTorch(block, (float)i2 - 0.099999994f, (float)i3 + 0.2f, i4, -0.4f, 0.0f);
            } else if (b21 == 2) {
                this.renderBlockTorch(block, (float)i2 + 0.099999994f, (float)i3 + 0.2f, i4, 0.4f, 0.0f);
            } else if (b21 == 3) {
                this.renderBlockTorch(block, i2, (float)i3 + 0.2f, (float)i4 - 0.099999994f, 0.0f, -0.4f);
            } else if (b21 == 4) {
                this.renderBlockTorch(block, i2, (float)i3 + 0.2f, (float)i4 + 0.099999994f, 0.0f, 0.4f);
            } else {
                this.renderBlockTorch(block, i2, i3, i4, 0.0f, 0.0f);
            }
            return true;
        }
        if (i5 == 3) {
            i5 = i4;
            i4 = i3;
            i3 = i2;
            Tesselator tessellator6 = Tesselator.instance;
            int i7 = block.getTexture(0);
            if (this.overrideBlockTexture >= 0) {
                i7 = this.overrideBlockTexture;
            }
            float f8 = block.getBrightness(this.blockAccess, i2, i4, i5);
            tessellator6.setColorOpaque_F(f8, f8, f8);
            i2 = (i7 & 0xF) << 4;
            int i23 = i7 & 0xF0;
            float f24 = (float)i2 / 256.0f;
            float f10 = ((float)i2 + 15.99f) / 256.0f;
            float f11 = (float)i23 / 256.0f;
            float f22 = ((float)i23 + 15.99f) / 256.0f;
            if (!this.blockAccess.isSolidTile(i3, i4 - 1, i5) && !Tile.fire.canBlockCatchFire(this.blockAccess, i3, i4 - 1, i5)) {
                float f14;
                if ((i3 + i4 + i5 & 1) == 1) {
                    f24 = (float)i2 / 256.0f;
                    f10 = ((float)i2 + 15.99f) / 256.0f;
                    f11 = (float)(i23 + 16) / 256.0f;
                    f22 = ((float)i23 + 15.99f + 16.0f) / 256.0f;
                }
                if ((i3 / 2 + i4 / 2 + i5 / 2 & 1) == 1) {
                    f14 = f10;
                    f10 = f24;
                    f24 = f14;
                }
                if (Tile.fire.canBlockCatchFire(this.blockAccess, i3 - 1, i4, i5)) {
                    tessellator6.vertexUV((float)i3 + 0.2f, (float)i4 + 1.4f + 0.0625f, i5 + 1, f10, f11);
                    tessellator6.vertexUV(i3, (float)i4 + 0.0625f, i5 + 1, f10, f22);
                    tessellator6.vertexUV(i3, (float)i4 + 0.0625f, i5, f24, f22);
                    tessellator6.vertexUV((float)i3 + 0.2f, (float)i4 + 1.4f + 0.0625f, i5, f24, f11);
                    tessellator6.vertexUV((float)i3 + 0.2f, (float)i4 + 1.4f + 0.0625f, i5, f24, f11);
                    tessellator6.vertexUV(i3, (float)i4 + 0.0625f, i5, f24, f22);
                    tessellator6.vertexUV(i3, (float)i4 + 0.0625f, i5 + 1, f10, f22);
                    tessellator6.vertexUV((float)i3 + 0.2f, (float)i4 + 1.4f + 0.0625f, i5 + 1, f10, f11);
                }
                if (Tile.fire.canBlockCatchFire(this.blockAccess, i3 + 1, i4, i5)) {
                    tessellator6.vertexUV((float)(i3 + 1) - 0.2f, (float)i4 + 1.4f + 0.0625f, i5, f24, f11);
                    tessellator6.vertexUV(i3 + 1, (float)i4 + 0.0625f, i5, f24, f22);
                    tessellator6.vertexUV(i3 + 1, (float)i4 + 0.0625f, i5 + 1, f10, f22);
                    tessellator6.vertexUV((float)(i3 + 1) - 0.2f, (float)i4 + 1.4f + 0.0625f, i5 + 1, f10, f11);
                    tessellator6.vertexUV((float)(i3 + 1) - 0.2f, (float)i4 + 1.4f + 0.0625f, i5 + 1, f10, f11);
                    tessellator6.vertexUV(i3 + 1, (float)i4 + 0.0625f, i5 + 1, f10, f22);
                    tessellator6.vertexUV(i3 + 1, (float)i4 + 0.0625f, i5, f24, f22);
                    tessellator6.vertexUV((float)(i3 + 1) - 0.2f, (float)i4 + 1.4f + 0.0625f, i5, f24, f11);
                }
                if (Tile.fire.canBlockCatchFire(this.blockAccess, i3, i4, i5 - 1)) {
                    tessellator6.vertexUV(i3, (float)i4 + 1.4f + 0.0625f, (float)i5 + 0.2f, f10, f11);
                    tessellator6.vertexUV(i3, (float)i4 + 0.0625f, i5, f10, f22);
                    tessellator6.vertexUV(i3 + 1, (float)i4 + 0.0625f, i5, f24, f22);
                    tessellator6.vertexUV(i3 + 1, (float)i4 + 1.4f + 0.0625f, (float)i5 + 0.2f, f24, f11);
                    tessellator6.vertexUV(i3 + 1, (float)i4 + 1.4f + 0.0625f, (float)i5 + 0.2f, f24, f11);
                    tessellator6.vertexUV(i3 + 1, (float)i4 + 0.0625f, i5, f24, f22);
                    tessellator6.vertexUV(i3, (float)i4 + 0.0625f, i5, f10, f22);
                    tessellator6.vertexUV(i3, (float)i4 + 1.4f + 0.0625f, (float)i5 + 0.2f, f10, f11);
                }
                if (Tile.fire.canBlockCatchFire(this.blockAccess, i3, i4, i5 + 1)) {
                    tessellator6.vertexUV(i3 + 1, (float)i4 + 1.4f + 0.0625f, (float)(i5 + 1) - 0.2f, f24, f11);
                    tessellator6.vertexUV(i3 + 1, (float)i4 + 0.0625f, i5 + 1, f24, f22);
                    tessellator6.vertexUV(i3, (float)i4 + 0.0625f, i5 + 1, f10, f22);
                    tessellator6.vertexUV(i3, (float)i4 + 1.4f + 0.0625f, (float)(i5 + 1) - 0.2f, f10, f11);
                    tessellator6.vertexUV(i3, (float)i4 + 1.4f + 0.0625f, (float)(i5 + 1) - 0.2f, f10, f11);
                    tessellator6.vertexUV(i3, (float)i4 + 0.0625f, i5 + 1, f10, f22);
                    tessellator6.vertexUV(i3 + 1, (float)i4 + 0.0625f, i5 + 1, f24, f22);
                    tessellator6.vertexUV(i3 + 1, (float)i4 + 1.4f + 0.0625f, (float)(i5 + 1) - 0.2f, f24, f11);
                }
                if (Tile.fire.canBlockCatchFire(this.blockAccess, i3, i4 + 1, i5)) {
                    f14 = (float)i3 + 0.5f + 0.5f;
                    float f15 = (float)i3 + 0.5f - 0.5f;
                    float f19 = (float)i5 + 0.5f + 0.5f;
                    float f16 = (float)i5 + 0.5f - 0.5f;
                    f24 = (float)i2 / 256.0f;
                    f10 = ((float)i2 + 15.99f) / 256.0f;
                    f11 = (float)i23 / 256.0f;
                    f22 = ((float)i23 + 15.99f) / 256.0f;
                    if ((i3 + ++i4 + i5 & 1) == 0) {
                        tessellator6.vertexUV(f15, (float)i4 + -0.2f, i5, f10, f11);
                        tessellator6.vertexUV(f14, i4, i5, f10, f22);
                        tessellator6.vertexUV(f14, i4, i5 + 1, f24, f22);
                        tessellator6.vertexUV(f15, (float)i4 + -0.2f, i5 + 1, f24, f11);
                        f24 = (float)i2 / 256.0f;
                        f10 = ((float)i2 + 15.99f) / 256.0f;
                        f11 = (float)(i23 + 16) / 256.0f;
                        f22 = ((float)i23 + 15.99f + 16.0f) / 256.0f;
                        tessellator6.vertexUV(f14, (float)i4 + -0.2f, i5 + 1, f10, f11);
                        tessellator6.vertexUV(f15, i4, i5 + 1, f10, f22);
                        tessellator6.vertexUV(f15, i4, i5, f24, f22);
                        tessellator6.vertexUV(f14, (float)i4 + -0.2f, i5, f24, f11);
                    } else {
                        tessellator6.vertexUV(i3, (float)i4 + -0.2f, f19, f10, f11);
                        tessellator6.vertexUV(i3, i4, f16, f10, f22);
                        tessellator6.vertexUV(i3 + 1, i4, f16, f24, f22);
                        tessellator6.vertexUV(i3 + 1, (float)i4 + -0.2f, f19, f24, f11);
                        f24 = (float)i2 / 256.0f;
                        f10 = ((float)i2 + 15.99f) / 256.0f;
                        f11 = (float)(i23 + 16) / 256.0f;
                        f22 = ((float)i23 + 15.99f + 16.0f) / 256.0f;
                        tessellator6.vertexUV(i3 + 1, (float)i4 + -0.2f, f16, f10, f11);
                        tessellator6.vertexUV(i3 + 1, i4, f19, f10, f22);
                        tessellator6.vertexUV(i3, i4, f19, f24, f22);
                        tessellator6.vertexUV(i3, (float)i4 + -0.2f, f16, f24, f11);
                    }
                }
            } else {
                float f12 = (float)i3 + 0.5f + 0.2f;
                float f13 = (float)i3 + 0.5f - 0.2f;
                float f14 = (float)i5 + 0.5f + 0.2f;
                float f15 = (float)i5 + 0.5f - 0.2f;
                float f19 = (float)i3 + 0.5f - 0.3f;
                float f16 = (float)i3 + 0.5f + 0.3f;
                float f17 = (float)i5 + 0.5f - 0.3f;
                float f18 = (float)i5 + 0.5f + 0.3f;
                tessellator6.vertexUV(f19, (float)i4 + 1.4f, i5 + 1, f10, f11);
                tessellator6.vertexUV(f12, i4, i5 + 1, f10, f22);
                tessellator6.vertexUV(f12, i4, i5, f24, f22);
                tessellator6.vertexUV(f19, (float)i4 + 1.4f, i5, f24, f11);
                tessellator6.vertexUV(f16, (float)i4 + 1.4f, i5, f10, f11);
                tessellator6.vertexUV(f13, i4, i5, f10, f22);
                tessellator6.vertexUV(f13, i4, i5 + 1, f24, f22);
                tessellator6.vertexUV(f16, (float)i4 + 1.4f, i5 + 1, f24, f11);
                f24 = (float)i2 / 256.0f;
                f10 = ((float)i2 + 15.99f) / 256.0f;
                f11 = (float)(i23 + 16) / 256.0f;
                f22 = ((float)i23 + 15.99f + 16.0f) / 256.0f;
                tessellator6.vertexUV(i3 + 1, (float)i4 + 1.4f, f18, f10, f11);
                tessellator6.vertexUV(i3 + 1, i4, f15, f10, f22);
                tessellator6.vertexUV(i3, i4, f15, f24, f22);
                tessellator6.vertexUV(i3, (float)i4 + 1.4f, f18, f24, f11);
                tessellator6.vertexUV(i3, (float)i4 + 1.4f, f17, f10, f11);
                tessellator6.vertexUV(i3, i4, f14, f10, f22);
                tessellator6.vertexUV(i3 + 1, i4, f14, f24, f22);
                tessellator6.vertexUV(i3 + 1, (float)i4 + 1.4f, f17, f24, f11);
                f12 = (float)i3 + 0.5f - 0.5f;
                f13 = (float)i3 + 0.5f + 0.5f;
                f14 = (float)i5 + 0.5f - 0.5f;
                f15 = (float)i5 + 0.5f + 0.5f;
                f19 = (float)i3 + 0.5f - 0.4f;
                f16 = (float)i3 + 0.5f + 0.4f;
                f17 = (float)i5 + 0.5f - 0.4f;
                f18 = (float)i5 + 0.5f + 0.4f;
                tessellator6.vertexUV(f19, (float)i4 + 1.4f, i5, f24, f11);
                tessellator6.vertexUV(f12, i4, i5, f24, f22);
                tessellator6.vertexUV(f12, i4, i5 + 1, f10, f22);
                tessellator6.vertexUV(f19, (float)i4 + 1.4f, i5 + 1, f10, f11);
                tessellator6.vertexUV(f16, (float)i4 + 1.4f, i5 + 1, f24, f11);
                tessellator6.vertexUV(f13, i4, i5 + 1, f24, f22);
                tessellator6.vertexUV(f13, i4, i5, f10, f22);
                tessellator6.vertexUV(f16, (float)i4 + 1.4f, i5, f10, f11);
                f24 = (float)i2 / 256.0f;
                f10 = ((float)i2 + 15.99f) / 256.0f;
                f11 = (float)i23 / 256.0f;
                f22 = ((float)i23 + 15.99f) / 256.0f;
                tessellator6.vertexUV(i3, (float)i4 + 1.4f, f18, f24, f11);
                tessellator6.vertexUV(i3, i4, f15, f24, f22);
                tessellator6.vertexUV(i3 + 1, i4, f15, f10, f22);
                tessellator6.vertexUV(i3 + 1, (float)i4 + 1.4f, f18, f10, f11);
                tessellator6.vertexUV(i3 + 1, (float)i4 + 1.4f, f17, f24, f11);
                tessellator6.vertexUV(i3 + 1, i4, f14, f24, f22);
                tessellator6.vertexUV(i3, i4, f14, f10, f22);
                tessellator6.vertexUV(i3, (float)i4 + 1.4f, f17, f10, f11);
            }
            return true;
        }
        if (i5 == 5) {
            i5 = i4;
            i4 = i3;
            i3 = i2;
            Tesselator tessellator6 = Tesselator.instance;
            int i7 = block.getTexture(0);
            if (this.overrideBlockTexture >= 0) {
                i7 = this.overrideBlockTexture;
            }
            float f8 = block.getBrightness(this.blockAccess, i2, i4, i5);
            tessellator6.setColorOpaque_F(f8, f8, f8);
            i2 = ((i7 & 0xF) << 4) + 16;
            int i23 = (i7 & 0xF) << 4;
            int i9 = i7 & 0xF0;
            if ((i3 + i4 + i5 & 1) == 1) {
                i2 = (i7 & 0xF) << 4;
                i23 = ((i7 & 0xF) << 4) + 16;
            }
            float f10 = (float)i2 / 256.0f;
            float f11 = ((float)i2 + 15.99f) / 256.0f;
            float f22 = (float)i9 / 256.0f;
            float f20 = ((float)i9 + 15.99f) / 256.0f;
            float f12 = (float)i23 / 256.0f;
            float f13 = ((float)i23 + 15.99f) / 256.0f;
            float f14 = (float)i9 / 256.0f;
            float f15 = ((float)i9 + 15.99f) / 256.0f;
            if (this.blockAccess.isSolidTile(i3 - 1, i4, i5)) {
                tessellator6.vertexUV((float)i3 + 0.05f, (float)(i4 + 1) + 0.125f, (float)(i5 + 1) + 0.125f, f10, f22);
                tessellator6.vertexUV((float)i3 + 0.05f, (float)i4 - 0.125f, (float)(i5 + 1) + 0.125f, f10, f20);
                tessellator6.vertexUV((float)i3 + 0.05f, (float)i4 - 0.125f, (float)i5 - 0.125f, f11, f20);
                tessellator6.vertexUV((float)i3 + 0.05f, (float)(i4 + 1) + 0.125f, (float)i5 - 0.125f, f11, f22);
            }
            if (this.blockAccess.isSolidTile(i3 + 1, i4, i5)) {
                tessellator6.vertexUV((float)(i3 + 1) - 0.05f, (float)i4 - 0.125f, (float)(i5 + 1) + 0.125f, f11, f20);
                tessellator6.vertexUV((float)(i3 + 1) - 0.05f, (float)(i4 + 1) + 0.125f, (float)(i5 + 1) + 0.125f, f11, f22);
                tessellator6.vertexUV((float)(i3 + 1) - 0.05f, (float)(i4 + 1) + 0.125f, (float)i5 - 0.125f, f10, f22);
                tessellator6.vertexUV((float)(i3 + 1) - 0.05f, (float)i4 - 0.125f, (float)i5 - 0.125f, f10, f20);
            }
            if (this.blockAccess.isSolidTile(i3, i4, i5 - 1)) {
                tessellator6.vertexUV((float)(i3 + 1) + 0.125f, (float)i4 - 0.125f, (float)i5 + 0.05f, f13, f15);
                tessellator6.vertexUV((float)(i3 + 1) + 0.125f, (float)(i4 + 1) + 0.125f, (float)i5 + 0.05f, f13, f14);
                tessellator6.vertexUV((float)i3 - 0.125f, (float)(i4 + 1) + 0.125f, (float)i5 + 0.05f, f12, f14);
                tessellator6.vertexUV((float)i3 - 0.125f, (float)i4 - 0.125f, (float)i5 + 0.05f, f12, f15);
            }
            if (this.blockAccess.isSolidTile(i3, i4, i5 + 1)) {
                tessellator6.vertexUV((float)(i3 + 1) + 0.125f, (float)(i4 + 1) + 0.125f, (float)(i5 + 1) - 0.05f, f12, f14);
                tessellator6.vertexUV((float)(i3 + 1) + 0.125f, (float)i4 - 0.125f, (float)(i5 + 1) - 0.05f, f12, f15);
                tessellator6.vertexUV((float)i3 - 0.125f, (float)i4 - 0.125f, (float)(i5 + 1) - 0.05f, f13, f15);
                tessellator6.vertexUV((float)i3 - 0.125f, (float)(i4 + 1) + 0.125f, (float)(i5 + 1) - 0.05f, f13, f14);
            }
            return true;
        }
        return false;
    }

    private void renderBlockTorch(Tile block, float f2, float f3, float f4, float f5, float f6) {
        Tesselator tessellator7 = Tesselator.instance;
        int block1 = block.getTexture(0);
        if (this.overrideBlockTexture >= 0) {
            block1 = this.overrideBlockTexture;
        }
        int i8 = (block1 & 0xF) << 4;
        float f9 = (float)i8 / 256.0f;
        float f21 = ((float)i8 + 15.99f) / 256.0f;
        float f10 = (float)(block1 &= 0xF0) / 256.0f;
        float block2 = ((float)block1 + 15.99f) / 256.0f;
        float f11 = f9 + 0.02734375f;
        float f12 = f10 + 0.0234375f;
        float f13 = f9 + 0.03515625f;
        float f14 = f10 + 0.03125f;
        float f15 = (f2 += 0.5f) - 0.5f;
        float f16 = f2 + 0.5f;
        float f17 = (f4 += 0.5f) - 0.5f;
        float f18 = f4 + 0.5f;
        tessellator7.vertexUV(f2 + f5 * 0.375f - 0.0625f, f3 + 0.625f, f4 + f6 * 0.375f - 0.0625f, f11, f12);
        tessellator7.vertexUV(f2 + f5 * 0.375f - 0.0625f, f3 + 0.625f, f4 + f6 * 0.375f + 0.0625f, f11, f14);
        tessellator7.vertexUV(f2 + f5 * 0.375f + 0.0625f, f3 + 0.625f, f4 + f6 * 0.375f + 0.0625f, f13, f14);
        tessellator7.vertexUV(f2 + f5 * 0.375f + 0.0625f, f3 + 0.625f, f4 + f6 * 0.375f - 0.0625f, f13, f12);
        tessellator7.vertexUV(f2 - 0.0625f, f3 + 1.0f, f17, f9, f10);
        tessellator7.vertexUV(f2 - 0.0625f + f5, f3, f17 + f6, f9, block2);
        tessellator7.vertexUV(f2 - 0.0625f + f5, f3, f18 + f6, f21, block2);
        tessellator7.vertexUV(f2 - 0.0625f, f3 + 1.0f, f18, f21, f10);
        tessellator7.vertexUV(f2 + 0.0625f, f3 + 1.0f, f18, f9, f10);
        tessellator7.vertexUV(f2 + f5 + 0.0625f, f3, f18 + f6, f9, block2);
        tessellator7.vertexUV(f2 + f5 + 0.0625f, f3, f17 + f6, f21, block2);
        tessellator7.vertexUV(f2 + 0.0625f, f3 + 1.0f, f17, f21, f10);
        tessellator7.vertexUV(f15, f3 + 1.0f, f4 + 0.0625f, f9, f10);
        tessellator7.vertexUV(f15 + f5, f3, f4 + 0.0625f + f6, f9, block2);
        tessellator7.vertexUV(f16 + f5, f3, f4 + 0.0625f + f6, f21, block2);
        tessellator7.vertexUV(f16, f3 + 1.0f, f4 + 0.0625f, f21, f10);
        tessellator7.vertexUV(f16, f3 + 1.0f, f4 - 0.0625f, f9, f10);
        tessellator7.vertexUV(f16 + f5, f3, f4 - 0.0625f + f6, f9, block2);
        tessellator7.vertexUV(f15 + f5, f3, f4 - 0.0625f + f6, f21, block2);
        tessellator7.vertexUV(f15, f3 + 1.0f, f4 - 0.0625f, f21, f10);
    }

    private void renderBlockPlant(Tile block, int i2, float f3, float f4, float f5) {
        Tesselator tessellator6 = Tesselator.instance;
        int block1 = block.getTextureFromSideAndMetadata(0, i2);
        if (this.overrideBlockTexture >= 0) {
            block1 = this.overrideBlockTexture;
        }
        i2 = (block1 & 0xF) << 4;
        float f7 = (float)i2 / 256.0f;
        float f12 = ((float)i2 + 15.99f) / 256.0f;
        float f8 = (float)(block1 &= 0xF0) / 256.0f;
        float block2 = ((float)block1 + 15.99f) / 256.0f;
        float f9 = f3 + 0.5f - 0.45f;
        f3 = f3 + 0.5f + 0.45f;
        float f10 = f5 + 0.5f - 0.45f;
        f5 = f5 + 0.5f + 0.45f;
        tessellator6.vertexUV(f9, f4 + 1.0f, f10, f7, f8);
        tessellator6.vertexUV(f9, f4, f10, f7, block2);
        tessellator6.vertexUV(f3, f4, f5, f12, block2);
        tessellator6.vertexUV(f3, f4 + 1.0f, f5, f12, f8);
        tessellator6.vertexUV(f3, f4 + 1.0f, f5, f7, f8);
        tessellator6.vertexUV(f3, f4, f5, f7, block2);
        tessellator6.vertexUV(f9, f4, f10, f12, block2);
        tessellator6.vertexUV(f9, f4 + 1.0f, f10, f12, f8);
        tessellator6.vertexUV(f9, f4 + 1.0f, f5, f7, f8);
        tessellator6.vertexUV(f9, f4, f5, f7, block2);
        tessellator6.vertexUV(f3, f4, f10, f12, block2);
        tessellator6.vertexUV(f3, f4 + 1.0f, f10, f12, f8);
        tessellator6.vertexUV(f3, f4 + 1.0f, f10, f7, f8);
        tessellator6.vertexUV(f3, f4, f10, f7, block2);
        tessellator6.vertexUV(f9, f4, f5, f12, block2);
        tessellator6.vertexUV(f9, f4 + 1.0f, f5, f12, f8);
    }

    private void renderBlockCrops(Tile block, int i2, float f3, float f4, float f5) {
        Tesselator tessellator6 = Tesselator.instance;
        int block1 = block.getTextureFromSideAndMetadata(0, i2);
        if (this.overrideBlockTexture >= 0) {
            block1 = this.overrideBlockTexture;
        }
        i2 = (block1 & 0xF) << 4;
        float f7 = (float)i2 / 256.0f;
        float f14 = ((float)i2 + 15.99f) / 256.0f;
        float f8 = (float)(block1 &= 0xF0) / 256.0f;
        float block2 = ((float)block1 + 15.99f) / 256.0f;
        float f9 = f3 + 0.5f - 0.25f;
        float f10 = f3 + 0.5f + 0.25f;
        float f11 = f5 + 0.5f - 0.5f;
        float f12 = f5 + 0.5f + 0.5f;
        tessellator6.vertexUV(f9, f4 + 1.0f, f11, f7, f8);
        tessellator6.vertexUV(f9, f4, f11, f7, block2);
        tessellator6.vertexUV(f9, f4, f12, f14, block2);
        tessellator6.vertexUV(f9, f4 + 1.0f, f12, f14, f8);
        tessellator6.vertexUV(f9, f4 + 1.0f, f12, f7, f8);
        tessellator6.vertexUV(f9, f4, f12, f7, block2);
        tessellator6.vertexUV(f9, f4, f11, f14, block2);
        tessellator6.vertexUV(f9, f4 + 1.0f, f11, f14, f8);
        tessellator6.vertexUV(f10, f4 + 1.0f, f12, f7, f8);
        tessellator6.vertexUV(f10, f4, f12, f7, block2);
        tessellator6.vertexUV(f10, f4, f11, f14, block2);
        tessellator6.vertexUV(f10, f4 + 1.0f, f11, f14, f8);
        tessellator6.vertexUV(f10, f4 + 1.0f, f11, f7, f8);
        tessellator6.vertexUV(f10, f4, f11, f7, block2);
        tessellator6.vertexUV(f10, f4, f12, f14, block2);
        tessellator6.vertexUV(f10, f4 + 1.0f, f12, f14, f8);
        f9 = f3 + 0.5f - 0.5f;
        f10 = f3 + 0.5f + 0.5f;
        f11 = f5 + 0.5f - 0.25f;
        f12 = f5 + 0.5f + 0.25f;
        tessellator6.vertexUV(f9, f4 + 1.0f, f11, f7, f8);
        tessellator6.vertexUV(f9, f4, f11, f7, block2);
        tessellator6.vertexUV(f10, f4, f11, f14, block2);
        tessellator6.vertexUV(f10, f4 + 1.0f, f11, f14, f8);
        tessellator6.vertexUV(f10, f4 + 1.0f, f11, f7, f8);
        tessellator6.vertexUV(f10, f4, f11, f7, block2);
        tessellator6.vertexUV(f9, f4, f11, f14, block2);
        tessellator6.vertexUV(f9, f4 + 1.0f, f11, f14, f8);
        tessellator6.vertexUV(f10, f4 + 1.0f, f12, f7, f8);
        tessellator6.vertexUV(f10, f4, f12, f7, block2);
        tessellator6.vertexUV(f9, f4, f12, f14, block2);
        tessellator6.vertexUV(f9, f4 + 1.0f, f12, f14, f8);
        tessellator6.vertexUV(f9, f4 + 1.0f, f12, f7, f8);
        tessellator6.vertexUV(f9, f4, f12, f7, block2);
        tessellator6.vertexUV(f10, f4, f12, f14, block2);
        tessellator6.vertexUV(f10, f4 + 1.0f, f12, f14, f8);
    }

    private float materialNotWater(int i1, int i2, int i3) {
        return this.blockAccess.getMaterial(i1, i2, i3) != Material.water ? 1.0f : (float)this.blockAccess.getTileMetadata(i1, i2, i3) / 9.0f;
    }

    private void renderBlockBottom(Tile block, float f2, float f3, float f4, int i5) {
        Tesselator tessellator6 = Tesselator.instance;
        if (this.overrideBlockTexture >= 0) {
            i5 = this.overrideBlockTexture;
        }
        int i7 = (i5 & 0xF) << 4;
        float f8 = (float)i7 / 256.0f;
        float f14 = ((float)i7 + 15.99f) / 256.0f;
        float f9 = (float)(i5 &= 0xF0) / 256.0f;
        float f13 = ((float)i5 + 15.99f) / 256.0f;
        float f10 = f2 + block.xx0;
        float f11 = f4 + block.zz0;
        float block1 = f4 + block.zz1;
        tessellator6.vertexUV(f10, f3 += block.yy0, block1, f8, f13);
        tessellator6.vertexUV(f10, f3, f11, f8, f9);
        tessellator6.vertexUV(f2 += block.xx1, f3, f11, f14, f9);
        tessellator6.vertexUV(f2, f3, block1, f14, f13);
    }

    private void renderBlockTop(Tile block, float f2, float f3, float f4, int i5) {
        Tesselator tessellator6 = Tesselator.instance;
        if (this.overrideBlockTexture >= 0) {
            i5 = this.overrideBlockTexture;
        }
        int i7 = (i5 & 0xF) << 4;
        float f8 = (float)i7 / 256.0f;
        float f14 = ((float)i7 + 15.99f) / 256.0f;
        float f9 = (float)(i5 &= 0xF0) / 256.0f;
        float f13 = ((float)i5 + 15.99f) / 256.0f;
        float f10 = f2 + block.xx0;
        float f11 = f4 + block.zz0;
        float block1 = f4 + block.zz1;
        tessellator6.vertexUV(f2 += block.xx1, f3 += block.yy1, block1, f14, f13);
        tessellator6.vertexUV(f2, f3, f11, f14, f9);
        tessellator6.vertexUV(f10, f3, f11, f8, f9);
        tessellator6.vertexUV(f10, f3, block1, f8, f13);
    }

    private void renderBlockNorth(Tile block, int i2, int i3, int i4, int i5) {
        float f15;
        float f9;
        Tesselator tessellator6 = Tesselator.instance;
        if (this.overrideBlockTexture >= 0) {
            i5 = this.overrideBlockTexture;
        }
        int i7 = (i5 & 0xF) << 4;
        i5 &= 0xF0;
        float f8 = (float)i7 / 256.0f;
        float f16 = ((float)i7 + 15.99f) / 256.0f;
        if (block.yy0 >= 0.0f && block.yy1 <= 1.0f) {
            f9 = ((float)i5 + block.yy0 * 15.99f) / 256.0f;
            f15 = ((float)i5 + block.yy1 * 15.99f) / 256.0f;
        } else {
            f9 = (float)i5 / 256.0f;
            f15 = ((float)i5 + 15.99f) / 256.0f;
        }
        float f10 = (float)i2 + block.xx0;
        float f13 = (float)i2 + block.xx1;
        float f11 = (float)i3 + block.yy0;
        float f14 = (float)i3 + block.yy1;
        float block1 = (float)i4 + block.zz0;
        tessellator6.vertexUV(f10, f14, block1, f16, f9);
        tessellator6.vertexUV(f13, f14, block1, f8, f9);
        tessellator6.vertexUV(f13, f11, block1, f8, f15);
        tessellator6.vertexUV(f10, f11, block1, f16, f15);
    }

    private void renderBlockSouth(Tile block, int i2, int i3, int i4, int i5) {
        float f15;
        float f9;
        Tesselator tessellator6 = Tesselator.instance;
        if (this.overrideBlockTexture >= 0) {
            i5 = this.overrideBlockTexture;
        }
        int i7 = (i5 & 0xF) << 4;
        i5 &= 0xF0;
        float f8 = (float)i7 / 256.0f;
        float f16 = ((float)i7 + 15.99f) / 256.0f;
        if (block.yy0 >= 0.0f && block.yy1 <= 1.0f) {
            f9 = ((float)i5 + block.yy0 * 15.99f) / 256.0f;
            f15 = ((float)i5 + block.yy1 * 15.99f) / 256.0f;
        } else {
            f9 = (float)i5 / 256.0f;
            f15 = ((float)i5 + 15.99f) / 256.0f;
        }
        float f10 = (float)i2 + block.xx0;
        float f13 = (float)i2 + block.xx1;
        float f11 = (float)i3 + block.yy0;
        float f14 = (float)i3 + block.yy1;
        float block1 = (float)i4 + block.zz1;
        tessellator6.vertexUV(f10, f14, block1, f8, f9);
        tessellator6.vertexUV(f10, f11, block1, f8, f15);
        tessellator6.vertexUV(f13, f11, block1, f16, f15);
        tessellator6.vertexUV(f13, f14, block1, f16, f9);
    }

    private void renderBlockWest(Tile block, int i2, int i3, int i4, int i5) {
        float f15;
        float f9;
        Tesselator tessellator6 = Tesselator.instance;
        if (this.overrideBlockTexture >= 0) {
            i5 = this.overrideBlockTexture;
        }
        int i7 = (i5 & 0xF) << 4;
        i5 &= 0xF0;
        float f8 = (float)i7 / 256.0f;
        float f16 = ((float)i7 + 15.99f) / 256.0f;
        if (block.yy0 >= 0.0f && block.yy1 <= 1.0f) {
            f9 = ((float)i5 + block.yy0 * 15.99f) / 256.0f;
            f15 = ((float)i5 + block.yy1 * 15.99f) / 256.0f;
        } else {
            f9 = (float)i5 / 256.0f;
            f15 = ((float)i5 + 15.99f) / 256.0f;
        }
        float f13 = (float)i2 + block.xx0;
        float f10 = (float)i3 + block.yy0;
        float f14 = (float)i3 + block.yy1;
        float f11 = (float)i4 + block.zz0;
        float block1 = (float)i4 + block.zz1;
        tessellator6.vertexUV(f13, f14, block1, f16, f9);
        tessellator6.vertexUV(f13, f14, f11, f8, f9);
        tessellator6.vertexUV(f13, f10, f11, f8, f15);
        tessellator6.vertexUV(f13, f10, block1, f16, f15);
    }

    private void renderBlockEast(Tile block, int i2, int i3, int i4, int i5) {
        float f15;
        float f9;
        Tesselator tessellator6 = Tesselator.instance;
        if (this.overrideBlockTexture >= 0) {
            i5 = this.overrideBlockTexture;
        }
        int i7 = (i5 & 0xF) << 4;
        i5 &= 0xF0;
        float f8 = (float)i7 / 256.0f;
        float f16 = ((float)i7 + 15.99f) / 256.0f;
        if (block.yy0 >= 0.0f && block.yy1 <= 1.0f) {
            f9 = ((float)i5 + block.yy0 * 15.99f) / 256.0f;
            f15 = ((float)i5 + block.yy1 * 15.99f) / 256.0f;
        } else {
            f9 = (float)i5 / 256.0f;
            f15 = ((float)i5 + 15.99f) / 256.0f;
        }
        float f13 = (float)i2 + block.xx1;
        float f10 = (float)i3 + block.yy0;
        float f14 = (float)i3 + block.yy1;
        float f11 = (float)i4 + block.zz0;
        float block1 = (float)i4 + block.zz1;
        tessellator6.vertexUV(f13, f10, block1, f8, f15);
        tessellator6.vertexUV(f13, f10, f11, f16, f15);
        tessellator6.vertexUV(f13, f14, f11, f16, f9);
        tessellator6.vertexUV(f13, f14, block1, f8, f9);
    }

    public final void renderBlockOnInventory(Tile block) {
        Tesselator tessellator2 = Tesselator.instance;
        int i3 = block.getRenderType();
        if (i3 == 0) {
            GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)-0.5f);
            tessellator2.begin();
            Tesselator.setNormal(0.0f, -1.0f, 0.0f);
            this.renderBlockBottom(block, 0.0f, 0.0f, 0.0f, block.getTexture(0));
            tessellator2.end();
            tessellator2.begin();
            Tesselator.setNormal(0.0f, 1.0f, 0.0f);
            this.renderBlockTop(block, 0.0f, 0.0f, 0.0f, block.getTexture(1));
            tessellator2.end();
            tessellator2.begin();
            Tesselator.setNormal(0.0f, 0.0f, -1.0f);
            this.renderBlockNorth(block, 0, 0, 0, block.getTexture(2));
            tessellator2.end();
            tessellator2.begin();
            Tesselator.setNormal(0.0f, 0.0f, 1.0f);
            this.renderBlockSouth(block, 0, 0, 0, block.getTexture(3));
            tessellator2.end();
            tessellator2.begin();
            Tesselator.setNormal(-1.0f, 0.0f, 0.0f);
            this.renderBlockWest(block, 0, 0, 0, block.getTexture(4));
            tessellator2.end();
            tessellator2.begin();
            Tesselator.setNormal(1.0f, 0.0f, 0.0f);
            this.renderBlockEast(block, 0, 0, 0, block.getTexture(5));
            tessellator2.end();
            GL11.glTranslatef((float)0.5f, (float)0.5f, (float)0.5f);
        } else if (i3 == 1) {
            tessellator2.begin();
            Tesselator.setNormal(0.0f, -1.0f, 0.0f);
            this.renderBlockPlant(block, -1, -0.5f, -0.5f, -0.5f);
            tessellator2.end();
        } else if (i3 == 6) {
            tessellator2.begin();
            Tesselator.setNormal(0.0f, -1.0f, 0.0f);
            this.renderBlockCrops(block, -1, -0.5f, -0.5f, -0.5f);
            tessellator2.end();
        } else if (i3 == 2) {
            tessellator2.begin();
            Tesselator.setNormal(0.0f, -1.0f, 0.0f);
            this.renderBlockTorch(block, -0.5f, -0.5f, -0.5f, 0.0f, 0.0f);
            tessellator2.end();
        }
    }
}

