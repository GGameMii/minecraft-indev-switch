/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client.renderer;

import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tesselator;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.tile.Tile;
import net.minecraft.world.phys.AABB;
import org.lwjgl.opengl.GL11;
import util.Mth;

public final class Chunk {
    private Level worldObj;
    private int glRenderList = -1;
    private static Tesselator tessellator = Tesselator.instance;
    public static int chunksUpdated = 0;
    private int posX;
    private int posY;
    private int posZ;
    private int sizeWidth;
    private int sizeHeight;
    private int sizeDepth;
    public boolean isInFrustrum = false;
    private boolean[] skipRenderPass = new boolean[2];
    private int posXPlus;
    private int posYPlus;
    private int posZPlus;
    public boolean needsUpdate;
    private AABB rendererBoundingBox;
    private RenderBlocks renderBlocks;
    public boolean isVisible = true;
    public boolean isWaitingOnOcclusionQuery;
    public int glOcclusionQuery;

    public Chunk(Level world, int xCoord, int yCoord, int zCoord, int i5, int i6) {
        this.renderBlocks = new RenderBlocks(world);
        this.worldObj = world;
        this.posX = xCoord;
        this.posY = yCoord;
        this.posZ = zCoord;
        this.sizeDepth = 16;
        this.sizeHeight = 16;
        this.sizeWidth = 16;
        this.posXPlus = xCoord + this.sizeWidth / 2;
        this.posYPlus = yCoord + this.sizeHeight / 2;
        this.posZPlus = zCoord + this.sizeDepth / 2;
        Mth.sqrt(this.sizeWidth * this.sizeWidth + this.sizeHeight * this.sizeHeight + this.sizeDepth * this.sizeDepth);
        this.rendererBoundingBox = new AABB(xCoord, yCoord, zCoord, xCoord + this.sizeWidth, yCoord + this.sizeHeight, zCoord + this.sizeDepth).expand(2.0f, 2.0f, 2.0f);
        this.glRenderList = i6;
        this.setDontDraw();
        GL11.glDisable((int)3553);
        GL11.glNewList((int)(i6 + 2), (int)4864);
        RenderItem.renderOffsetAABB(this.rendererBoundingBox);
        GL11.glEndList();
        GL11.glEnable((int)3553);
    }

    public final void updateRenderer() {
        if (this.needsUpdate) {
            ++chunksUpdated;
            int i1 = this.posX;
            int i2 = this.posY;
            int i3 = this.posZ;
            int i4 = this.posX + this.sizeWidth;
            int i5 = this.posY + this.sizeHeight;
            int i6 = this.posZ + this.sizeDepth;
            int i7 = 0;
            while (i7 < 2) {
                this.skipRenderPass[i7] = true;
                ++i7;
            }
            i7 = 0;
            while (i7 < 2) {
                boolean z8 = false;
                boolean z9 = false;
                tessellator.begin();
                GL11.glNewList((int)(this.glRenderList + i7), (int)4864);
                int i10 = i2;
                while (i10 < i5) {
                    int i11 = i3;
                    while (i11 < i6) {
                        int i12 = (i10 * this.worldObj.depth + i11) * this.worldObj.width + i1;
                        int i13 = i1;
                        while (i13 < i4) {
                            int i14;
                            if ((i14 = this.worldObj.blocks[i12++] & 0xFF) > 0) {
                                Tile block15 = Tile.tiles[i14];
                                if (block15.getRenderBlockPass() != i7) {
                                    z8 = true;
                                } else {
                                    z9 |= this.renderBlocks.renderBlockByRenderType(block15, i13, i10, i11);
                                }
                            }
                            ++i13;
                        }
                        ++i11;
                    }
                    ++i10;
                }
                tessellator.end();
                GL11.glEndList();
                if (z9) {
                    this.skipRenderPass[i7] = false;
                }
                if (!z8) break;
                ++i7;
            }
        }
    }

    public final float distanceToEntitySquared(LivingEntity entityLiving1) {
        float f2 = entityLiving1.x - (float)this.posXPlus;
        float f3 = entityLiving1.y - (float)this.posYPlus;
        float f4 = entityLiving1.z - (float)this.posZPlus;
        return f2 * f2 + f3 * f3 + f4 * f4;
    }

    private void setDontDraw() {
        int i1 = 0;
        while (i1 < 2) {
            this.skipRenderPass[i1] = true;
            ++i1;
        }
    }

    public final void stopRendering() {
        this.setDontDraw();
        this.worldObj = null;
    }

    public final int getGLCallListForPass(int[] i1, int i2, int i3) {
        if (!this.isInFrustrum) {
            return i2;
        }
        if (!this.skipRenderPass[i3]) {
            i1[i2++] = this.glRenderList + i3;
        }
        return i2;
    }

    public final void updateInFrustrum(ICamera iCamera1) {
        this.isInFrustrum = iCamera1.isBoundingBoxInFrustrum(this.rendererBoundingBox);
    }

    public final void callOcclusionQueryList() {
        GL11.glCallList((int)(this.glRenderList + 2));
    }
}

