/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.BufferUtils
 *  org.lwjgl.opengl.ARBOcclusionQuery
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client.renderer;

import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityBubbleFX;
import net.minecraft.client.particle.EntityExplodeFX;
import net.minecraft.client.particle.EntityFlameFX;
import net.minecraft.client.particle.EntityLavaFX;
import net.minecraft.client.particle.EntitySmokeFX;
import net.minecraft.client.particle.EntitySplashFX;
import net.minecraft.client.renderer.Chunk;
import net.minecraft.client.renderer.EntitySorter;
import net.minecraft.client.renderer.HttpTextureProcessor;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.client.renderer.RenderSorter;
import net.minecraft.client.renderer.Tesselator;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.EntityMap;
import net.minecraft.world.level.IWorldAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.tile.Tile;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.ARBOcclusionQuery;
import org.lwjgl.opengl.GL11;
import util.Mth;

public final class LevelRenderer
implements IWorldAccess {
    private Level worldObj;
    private RenderEngine textures;
    private int glGenList;
    private IntBuffer renderIntBuffer = BufferUtils.createIntBuffer((int)65536);
    private List<Chunk> worldRenderersToUpdate = new ArrayList<Chunk>();
    private Chunk[] sortedWorldRenderers;
    private Chunk[] worldRenderers;
    private int renderChunksWide;
    private int renderChunksTall;
    private int renderChunksDeep;
    private int glRenderListBase;
    private Minecraft mc;
    private RenderBlocks globalRenderBlocks;
    private IntBuffer glOcclusionQueryBase;
    private boolean occlusionEnabled = false;
    private int cloudOffsetX = 0;
    private int glSkyList;
    private int countEntitiesTotal;
    private int countEntitiesRendered;
    private int countEntitiesHidden;
    private int[] dummyBuf50k = new int[50000];
    private IntBuffer occlusionResult = BufferUtils.createIntBuffer((int)64);
    private int renderersLoaded;
    private int renderersBeingClipped;
    private int renderersBeingOccluded;
    private int renderersBeingRendered;
    private float prevSortX = -9999.0f;
    private float prevSortY = -9999.0f;
    private float prevSortZ = -9999.0f;
    public float damagePartialTime;

    public LevelRenderer(Minecraft minecraft, RenderEngine renderEngine) {
        this.mc = minecraft;
        this.textures = renderEngine;
        this.glGenList = GL11.glGenLists((int)2);
        this.glRenderListBase = GL11.glGenLists((int)786432);
        this.occlusionEnabled = false;
        if (this.occlusionEnabled) {
            this.occlusionResult.clear();
            GL11.glGetInteger((int)34916, (IntBuffer)this.occlusionResult);
            if (this.occlusionResult.get(0) == 0) {
                this.occlusionEnabled = false;
            } else {
                this.glOcclusionQueryBase = BufferUtils.createIntBuffer((int)262144);
                this.glOcclusionQueryBase.clear();
                this.glOcclusionQueryBase.position(0);
                this.glOcclusionQueryBase.limit(262144);
                ARBOcclusionQuery.glGenQueriesARB((IntBuffer)this.glOcclusionQueryBase);
            }
        }
        this.glSkyList = GL11.glGenLists((int)1);
        GL11.glNewList((int)this.glSkyList, (int)4864);
        Random random5 = new Random(10842L);
        int i6 = 0;
        while (i6 < 500) {
            GL11.glRotatef((float)(random5.nextFloat() * 360.0f), (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)(random5.nextFloat() * 360.0f), (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)(random5.nextFloat() * 360.0f), (float)0.0f, (float)0.0f, (float)1.0f);
            Tesselator tessellator3 = Tesselator.instance;
            float f4 = 0.25f + random5.nextFloat() * 0.25f;
            tessellator3.begin();
            tessellator3.vertexUV(-f4, -100.0f, f4, 1.0f, 1.0f);
            tessellator3.vertexUV(f4, -100.0f, f4, 0.0f, 1.0f);
            tessellator3.vertexUV(f4, -100.0f, -f4, 0.0f, 0.0f);
            tessellator3.vertexUV(-f4, -100.0f, -f4, 1.0f, 0.0f);
            tessellator3.end();
            ++i6;
        }
        GL11.glEndList();
    }

    public final void changeWorld(Level world) {
        if (this.worldObj != null) {
            this.worldObj.removeListener(this);
        }
        this.prevSortX = -9999.0f;
        this.prevSortY = -9999.0f;
        this.prevSortZ = -9999.0f;
        RenderManager.instance.setLevel(world);
        this.worldObj = world;
        this.globalRenderBlocks = new RenderBlocks(world);
        if (world != null) {
            world.addListener(this);
            this.loadRenderers();
        }
    }

    @Override
    public final void loadRenderers() {
        if (this.worldRenderers != null) {
            int i2 = 0;
            while (i2 < this.worldRenderers.length) {
                this.worldRenderers[i2].stopRendering();
                ++i2;
            }
        }
        this.renderChunksWide = this.worldObj.width / 16;
        this.renderChunksTall = this.worldObj.height / 16;
        this.renderChunksDeep = this.worldObj.depth / 16;
        this.worldRenderers = new Chunk[this.renderChunksWide * this.renderChunksTall * this.renderChunksDeep];
        this.sortedWorldRenderers = new Chunk[this.renderChunksWide * this.renderChunksTall * this.renderChunksDeep];
        int sorted = 0;
        int chunks = 0;
        int i3 = 0;
        while (i3 < this.renderChunksWide) {
            int i4 = 0;
            while (i4 < this.renderChunksTall) {
                int i5 = 0;
                while (i5 < this.renderChunksDeep) {
                    this.worldRenderers[(i5 * this.renderChunksTall + i4) * this.renderChunksWide + i3] = new Chunk(this.worldObj, i3 << 4, i4 << 4, i5 << 4, 16, this.glRenderListBase + sorted);
                    if (this.occlusionEnabled) {
                        this.worldRenderers[(i5 * this.renderChunksTall + i4) * this.renderChunksWide + i3].glOcclusionQuery = this.glOcclusionQueryBase.get(chunks);
                    }
                    ++chunks;
                    this.sortedWorldRenderers[(i5 * this.renderChunksTall + i4) * this.renderChunksWide + i3] = this.worldRenderers[(i5 * this.renderChunksTall + i4) * this.renderChunksWide + i3];
                    sorted += 3;
                    ++i5;
                }
                ++i4;
            }
            ++i3;
        }
        i3 = 0;
        while (i3 < this.worldRenderersToUpdate.size()) {
            this.worldRenderersToUpdate.get((int)i3).needsUpdate = false;
            ++i3;
        }
        this.worldRenderersToUpdate.clear();
        GL11.glNewList((int)this.glGenList, (int)4864);
        this.oobGroundRenderHeight();
        GL11.glEndList();
        GL11.glNewList((int)(this.glGenList + 1), (int)4864);
        this.oobWaterRenderHeight();
        GL11.glEndList();
        this.markBlocksForUpdate(0, 0, 0, this.worldObj.width, this.worldObj.height, this.worldObj.depth);
    }

    public final void renderEntities(Vec3 pos, ICamera camera, float a2) {
        EntityMap map = this.worldObj.entityMap;
        RenderManager.instance.cacheActiveRenderInfo(this.worldObj, this.textures, this.mc.font, this.mc.player, a2);
        this.countEntitiesTotal = 0;
        this.countEntitiesRendered = 0;
        this.countEntitiesHidden = 0;
        List<Entity> entities = map.entities;
        if (entities.size() != 0) {
            this.countEntitiesTotal += entities.size();
            int i2 = 0;
            while (i2 < entities.size()) {
                float sq;
                Entity e2 = entities.get(i2);
                float xx0 = e2.x - pos.x;
                float yy0 = e2.y - pos.y;
                float zz0 = e2.z - pos.z;
                float dist = sq = xx0 * xx0 + yy0 * yy0 + zz0 * zz0;
                AABB bb = e2.bb;
                float xxd = bb.x1 - bb.x0;
                float yyd = bb.y1 - bb.y0;
                float zzd = bb.z1 - bb.z0;
                float rr = e2.renderDistance;
                float range = (xxd + yyd + zzd) / 3.0f * rr;
                if (dist < range * range && camera.isBoundingBoxInFrustrum(e2.bb) && (e2 != this.mc.player || this.mc.options.thirdPersonView)) {
                    ++this.countEntitiesRendered;
                    RenderManager.instance.renderEntity(e2, a2);
                } else {
                    ++this.countEntitiesHidden;
                }
                ++i2;
            }
        }
    }

    public final String getDebugInfoRenders() {
        return "C: " + this.renderersBeingRendered + "/" + this.renderersLoaded + ". F: " + this.renderersBeingClipped + ", O: " + this.renderersBeingOccluded;
    }

    public final String getDebugInfoEntities() {
        return "E: " + this.countEntitiesRendered + "/" + this.countEntitiesTotal + ". B: " + this.countEntitiesHidden + ", I: " + (this.countEntitiesTotal - this.countEntitiesHidden - this.countEntitiesRendered);
    }

    public final int sortAndRender(Player player, int i2) {
        int i8;
        float f5;
        float f4;
        float f3;
        if (i2 == 0) {
            this.renderersLoaded = 0;
            this.renderersBeingClipped = 0;
            this.renderersBeingOccluded = 0;
            this.renderersBeingRendered = 0;
        }
        if ((f3 = player.x - this.prevSortX) * f3 + (f4 = player.y - this.prevSortY) * f4 + (f5 = player.z - this.prevSortZ) * f5 > 16.0f) {
            this.prevSortX = player.x;
            this.prevSortY = player.y;
            this.prevSortZ = player.z;
            Arrays.sort(this.sortedWorldRenderers, new EntitySorter(player));
        }
        if (this.occlusionEnabled && i2 == 0) {
            int i10 = 8;
            this.checkOcclusionQueryResult(0, 8);
            int i6 = 0;
            while (i6 < 8) {
                this.sortedWorldRenderers[i6].isVisible = true;
                ++i6;
            }
            i8 = 0 + this.renderSortedRenderers(0, 8, i2);
            do {
                int i9 = i10;
                if ((i10 <<= 1) > this.sortedWorldRenderers.length) {
                    i10 = this.sortedWorldRenderers.length;
                }
                GL11.glDisable((int)3553);
                GL11.glDisable((int)2896);
                GL11.glDisable((int)3008);
                GL11.glColorMask((boolean)false, (boolean)false, (boolean)false, (boolean)false);
                GL11.glDepthMask((boolean)false);
                this.checkOcclusionQueryResult(i9, i10);
                i6 = i9;
                while (i6 < i10) {
                    float f7;
                    int i11;
                    if (!this.sortedWorldRenderers[i6].isInFrustrum) {
                        this.sortedWorldRenderers[i6].isVisible = true;
                    }
                    if (this.sortedWorldRenderers[i6].isInFrustrum && !this.sortedWorldRenderers[i6].isWaitingOnOcclusionQuery && this.cloudOffsetX % (i11 = (int)(1.0f + (f7 = Mth.sqrt(this.sortedWorldRenderers[i6].distanceToEntitySquared(player))) / 64.0f)) == i6 % i11) {
                        ARBOcclusionQuery.glBeginQueryARB((int)35092, (int)this.sortedWorldRenderers[i6].glOcclusionQuery);
                        this.sortedWorldRenderers[i6].callOcclusionQueryList();
                        ARBOcclusionQuery.glEndQueryARB((int)35092);
                        this.sortedWorldRenderers[i6].isWaitingOnOcclusionQuery = true;
                    }
                    ++i6;
                }
                GL11.glColorMask((boolean)true, (boolean)true, (boolean)true, (boolean)true);
                GL11.glDepthMask((boolean)true);
                GL11.glEnable((int)3553);
                GL11.glEnable((int)3008);
                i8 += this.renderSortedRenderers(i9, i10, i2);
            } while (i10 < this.sortedWorldRenderers.length);
        } else {
            i8 = 0 + this.renderSortedRenderers(0, this.sortedWorldRenderers.length, i2);
        }
        return i8;
    }

    private void checkOcclusionQueryResult(int i1, int i2) {
        int i3 = i1;
        while (i3 < i2) {
            if (this.sortedWorldRenderers[i3].isWaitingOnOcclusionQuery) {
                this.occlusionResult.clear();
                ARBOcclusionQuery.glGetQueryObjectuARB((int)this.sortedWorldRenderers[i3].glOcclusionQuery, (int)34919, (IntBuffer)this.occlusionResult);
                if (this.occlusionResult.get(0) != 0) {
                    this.sortedWorldRenderers[i3].isWaitingOnOcclusionQuery = false;
                    this.occlusionResult.clear();
                    ARBOcclusionQuery.glGetQueryObjectuARB((int)this.sortedWorldRenderers[i3].glOcclusionQuery, (int)34918, (IntBuffer)this.occlusionResult);
                    this.sortedWorldRenderers[i3].isVisible = this.occlusionResult.get(0) != 0;
                }
            }
            ++i3;
        }
    }

    private int renderSortedRenderers(int i1, int i2, int i3) {
        int list = 0;
        int i4 = i1;
        while (i4 < i2) {
            if (i3 == 0) {
                ++this.renderersLoaded;
                if (!this.sortedWorldRenderers[i4].isInFrustrum) {
                    ++this.renderersBeingClipped;
                }
                if (this.sortedWorldRenderers[i4].isInFrustrum && !this.sortedWorldRenderers[i4].isVisible) {
                    ++this.renderersBeingOccluded;
                }
                if (this.sortedWorldRenderers[i4].isInFrustrum && this.sortedWorldRenderers[i4].isVisible) {
                    ++this.renderersBeingRendered;
                }
            }
            if (this.sortedWorldRenderers[i4].isInFrustrum && this.sortedWorldRenderers[i4].isVisible) {
                list = this.sortedWorldRenderers[i4].getGLCallListForPass(this.dummyBuf50k, list, i3);
            }
            ++i4;
        }
        this.renderIntBuffer.clear();
        this.renderIntBuffer.put(this.dummyBuf50k, 0, list);
        this.renderIntBuffer.flip();
        if (this.renderIntBuffer.remaining() > 0) {
            GL11.glCallLists((IntBuffer)this.renderIntBuffer);
        }
        return this.renderIntBuffer.remaining();
    }

    public final void renderAllRenderLists() {
        GL11.glBindTexture((int)3553, (int)this.textures.getTexture("/terrain.png"));
        GL11.glCallLists((IntBuffer)this.renderIntBuffer);
    }

    public final void updateClouds() {
        ++this.cloudOffsetX;
    }

    public final void renderSky(float f1) {
        float f7;
        GL11.glDisable((int)3553);
        Vec3 vec3D2 = this.worldObj.getSkyColor(f1);
        float f3 = vec3D2.x;
        float f4 = vec3D2.y;
        float f9 = vec3D2.z;
        if (this.mc.options.anaglyph3d) {
            float f5 = (f3 * 30.0f + f4 * 59.0f + f9 * 11.0f) / 100.0f;
            f4 = (f3 * 30.0f + f4 * 70.0f) / 100.0f;
            f9 = (f3 * 30.0f + f9 * 70.0f) / 100.0f;
            f3 = f5;
        }
        GL11.glDepthMask((boolean)false);
        Tesselator t2 = Tesselator.instance;
        Tesselator.instance.begin();
        t2.setColorOpaque_F(f3, f4, f9);
        f9 = this.worldObj.height + 10;
        int i10 = -2048;
        while (i10 < this.worldObj.width + 2048) {
            int i13 = -2048;
            while (i13 < this.worldObj.depth + 2048) {
                t2.addVertex(i10, f9, i13);
                t2.addVertex(i10 + 512, f9, i13);
                t2.addVertex(i10 + 512, f9, i13 + 512);
                t2.addVertex(i10, f9, i13 + 512);
                i13 += 512;
            }
            i10 += 512;
        }
        t2.end();
        GL11.glEnable((int)3553);
        GL11.glDisable((int)2912);
        GL11.glDisable((int)3008);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)1, (int)1);
        GL11.glPushMatrix();
        f3 = this.mc.player.lastTickX + (this.mc.player.x - this.mc.player.lastTickX) * f1;
        f4 = this.mc.player.lastTickY + (this.mc.player.y - this.mc.player.lastTickY) * f1;
        float f6 = this.mc.player.lastTickZ + (this.mc.player.z - this.mc.player.lastTickZ) * f1;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glTranslatef((float)f3, (float)f4, (float)f6);
        GL11.glRotatef((float)0.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)(this.worldObj.getCelestialAngle(f1) * 360.0f), (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glBindTexture((int)3553, (int)this.textures.getTexture("/terrain/sun.png"));
        t2.begin();
        t2.vertexUV(-30.0f, 100.0f, -30.0f, 0.0f, 0.0f);
        t2.vertexUV(30.0f, 100.0f, -30.0f, 1.0f, 0.0f);
        t2.vertexUV(30.0f, 100.0f, 30.0f, 1.0f, 1.0f);
        t2.vertexUV(-30.0f, 100.0f, 30.0f, 0.0f, 1.0f);
        t2.end();
        GL11.glBindTexture((int)3553, (int)this.textures.getTexture("/terrain/moon.png"));
        t2.begin();
        t2.vertexUV(-20.0f, -100.0f, 20.0f, 1.0f, 1.0f);
        t2.vertexUV(20.0f, -100.0f, 20.0f, 0.0f, 1.0f);
        t2.vertexUV(20.0f, -100.0f, -20.0f, 0.0f, 0.0f);
        t2.vertexUV(-20.0f, -100.0f, -20.0f, 1.0f, 0.0f);
        t2.end();
        GL11.glDisable((int)3553);
        f9 = this.worldObj.getStarBrightness(f1);
        GL11.glColor4f((float)f9, (float)f9, (float)f9, (float)f9);
        GL11.glCallList((int)this.glSkyList);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glEnable((int)3008);
        GL11.glEnable((int)2912);
        GL11.glPopMatrix();
        GL11.glDepthMask((boolean)true);
        GL11.glBindTexture((int)3553, (int)this.textures.getTexture("/clouds.png"));
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        Vec3 vec3D11 = this.worldObj.getCloudColor(f1);
        f4 = vec3D11.x;
        f6 = vec3D11.y;
        f3 = vec3D11.z;
        if (this.mc.options.anaglyph3d) {
            f9 = (f4 * 30.0f + f6 * 59.0f + f3 * 11.0f) / 100.0f;
            f7 = (f4 * 30.0f + f6 * 70.0f) / 100.0f;
            f3 = (f4 * 30.0f + f3 * 70.0f) / 100.0f;
            f4 = f9;
            f6 = f7;
        }
        f9 = this.worldObj.cloudHeight;
        f7 = ((float)this.cloudOffsetX + f1) * 4.8828125E-4f * 0.03f;
        t2.begin();
        t2.setColorOpaque_F(f4, f6, f3);
        int i8 = -2048;
        while (i8 < this.worldObj.width + 2048) {
            i10 = -2048;
            while (i10 < this.worldObj.depth + 2048) {
                t2.vertexUV(i8, f9, i10 + 512, (float)i8 * 4.8828125E-4f + f7, (float)(i10 + 512) * 4.8828125E-4f);
                t2.vertexUV(i8 + 512, f9, i10 + 512, (float)(i8 + 512) * 4.8828125E-4f + f7, (float)(i10 + 512) * 4.8828125E-4f);
                t2.vertexUV(i8 + 512, f9, i10, (float)(i8 + 512) * 4.8828125E-4f + f7, (float)i10 * 4.8828125E-4f);
                t2.vertexUV(i8, f9, i10, (float)i8 * 4.8828125E-4f + f7, (float)i10 * 4.8828125E-4f);
                t2.vertexUV(i8, f9, i10, (float)i8 * 4.8828125E-4f + f7, (float)i10 * 4.8828125E-4f);
                t2.vertexUV(i8 + 512, f9, i10, (float)(i8 + 512) * 4.8828125E-4f + f7, (float)i10 * 4.8828125E-4f);
                t2.vertexUV(i8 + 512, f9, i10 + 512, (float)(i8 + 512) * 4.8828125E-4f + f7, (float)(i10 + 512) * 4.8828125E-4f);
                t2.vertexUV(i8, f9, i10 + 512, (float)i8 * 4.8828125E-4f + f7, (float)(i10 + 512) * 4.8828125E-4f);
                i10 += 512;
            }
            i8 += 512;
        }
        t2.end();
    }

    public final void oobGroundRenderer() {
        float f1 = this.worldObj.getLightBrightness(0, this.worldObj.getGroundLevel(), 0);
        GL11.glBindTexture((int)3553, (int)this.textures.getTexture("/dirt.png"));
        if (this.worldObj.getGroundLevel() > this.worldObj.getWaterLevel() && this.worldObj.defaultFluid == Tile.water.id) {
            GL11.glBindTexture((int)3553, (int)this.textures.getTexture("/grass.png"));
        }
        GL11.glColor4f((float)f1, (float)f1, (float)f1, (float)1.0f);
        GL11.glEnable((int)3553);
        GL11.glCallList((int)this.glGenList);
    }

    private void oobGroundRenderHeight() {
        Tesselator tessellator1 = Tesselator.instance;
        float f2 = this.worldObj.getGroundLevel();
        int i3 = 128;
        if (128 > this.worldObj.width) {
            i3 = this.worldObj.width;
        }
        if (i3 > this.worldObj.depth) {
            i3 = this.worldObj.depth;
        }
        int i4 = 2048 / i3;
        tessellator1.begin();
        int i5 = -i3 * i4;
        while (i5 < this.worldObj.width + i3 * i4) {
            int i6 = -i3 * i4;
            while (i6 < this.worldObj.depth + i3 * i4) {
                if (f2 < 0.0f || i5 < 0 || i6 < 0 || i5 >= this.worldObj.width || i6 >= this.worldObj.depth) {
                    tessellator1.vertexUV(i5, f2, i6 + i3, 0.0f, i3);
                    tessellator1.vertexUV(i5 + i3, f2, i6 + i3, i3, i3);
                    tessellator1.vertexUV(i5 + i3, f2, i6, i3, 0.0f);
                    tessellator1.vertexUV(i5, f2, i6, 0.0f, 0.0f);
                }
                i6 += i3;
            }
            i5 += i3;
        }
        tessellator1.end();
    }

    public final void oobWaterRenderer() {
        GL11.glEnable((int)3553);
        GL11.glEnable((int)3042);
        GL11.glBindTexture((int)3553, (int)this.textures.getTexture("/water.png"));
        float f1 = this.worldObj.getLightBrightness(0, this.worldObj.getWaterLevel(), 0);
        GL11.glColor4f((float)f1, (float)f1, (float)f1, (float)1.0f);
        GL11.glCallList((int)(this.glGenList + 1));
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glDisable((int)3042);
    }

    private void oobWaterRenderHeight() {
        float f1 = this.worldObj.getWaterLevel();
        GL11.glBlendFunc((int)770, (int)771);
        Tesselator tessellator2 = Tesselator.instance;
        int i3 = 128;
        if (128 > this.worldObj.width) {
            i3 = this.worldObj.width;
        }
        if (i3 > this.worldObj.depth) {
            i3 = this.worldObj.depth;
        }
        int i4 = 2048 / i3;
        tessellator2.begin();
        float f5 = Tile.water.xx0;
        float f6 = Tile.water.zz0;
        int i7 = -i3 * i4;
        while (i7 < this.worldObj.width + i3 * i4) {
            int i8 = -i3 * i4;
            while (i8 < this.worldObj.depth + i3 * i4) {
                float f9 = f1 + Tile.water.yy0;
                if (f1 < 0.0f || i7 < 0 || i8 < 0 || i7 >= this.worldObj.width || i8 >= this.worldObj.depth) {
                    tessellator2.vertexUV((float)i7 + f5, f9, (float)(i8 + i3) + f6, 0.0f, i3);
                    tessellator2.vertexUV((float)(i7 + i3) + f5, f9, (float)(i8 + i3) + f6, i3, i3);
                    tessellator2.vertexUV((float)(i7 + i3) + f5, f9, (float)i8 + f6, i3, 0.0f);
                    tessellator2.vertexUV((float)i7 + f5, f9, (float)i8 + f6, 0.0f, 0.0f);
                    tessellator2.vertexUV((float)i7 + f5, f9, (float)i8 + f6, 0.0f, 0.0f);
                    tessellator2.vertexUV((float)(i7 + i3) + f5, f9, (float)i8 + f6, i3, 0.0f);
                    tessellator2.vertexUV((float)(i7 + i3) + f5, f9, (float)(i8 + i3) + f6, i3, i3);
                    tessellator2.vertexUV((float)i7 + f5, f9, (float)(i8 + i3) + f6, 0.0f, i3);
                }
                i8 += i3;
            }
            i7 += i3;
        }
        tessellator2.end();
        GL11.glDisable((int)3042);
    }

    public final void updateRenderers(Player entityPlayer) {
        Collections.sort(this.worldRenderersToUpdate, new RenderSorter(entityPlayer));
        int i2 = this.worldRenderersToUpdate.size() - 1;
        int i3 = this.worldRenderersToUpdate.size();
        int i4 = 0;
        while (i4 < i3) {
            Chunk worldRenderer5 = this.worldRenderersToUpdate.get(i2 - i4);
            if (worldRenderer5.distanceToEntitySquared(entityPlayer) > 2500.0f && i4 > 4) {
                return;
            }
            this.worldRenderersToUpdate.remove(worldRenderer5);
            worldRenderer5.updateRenderer();
            worldRenderer5.needsUpdate = false;
            ++i4;
        }
    }

    public final void drawBlockBreaking(HitResult movingObjectPosition, int i2, ItemStack itemStack) {
        Tesselator tessellator4 = Tesselator.instance;
        GL11.glEnable((int)3042);
        GL11.glEnable((int)3008);
        GL11.glBlendFunc((int)770, (int)1);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)((Mth.sin((float)System.currentTimeMillis() / 100.0f) * 0.2f + 0.4f) * 0.5f));
        if (this.damagePartialTime > 0.0f) {
            GL11.glBlendFunc((int)774, (int)768);
            int itemStack1 = this.textures.getTexture("/terrain.png");
            GL11.glBindTexture((int)3553, (int)itemStack1);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)0.5f);
            GL11.glPushMatrix();
            itemStack1 = this.worldObj.getTile(movingObjectPosition.x, movingObjectPosition.y, movingObjectPosition.z);
            Tile itemStack2 = itemStack1 > 0 ? Tile.tiles[itemStack1] : null;
            GL11.glDisable((int)3008);
            tessellator4.begin();
            tessellator4.disableColor();
            if (itemStack2 == null) {
                itemStack2 = Tile.stone;
            }
            this.globalRenderBlocks.renderBlockUsingTexture(itemStack2, movingObjectPosition.x, movingObjectPosition.y, movingObjectPosition.z, 240 + (int)(this.damagePartialTime * 10.0f));
            tessellator4.end();
            GL11.glEnable((int)3008);
            GL11.glDepthMask((boolean)true);
            GL11.glPopMatrix();
        }
        GL11.glDisable((int)3042);
        GL11.glDisable((int)3008);
    }

    public final void drawSelectionBox(HitResult movingObjectPosition, int i2) {
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glColor4f((float)0.0f, (float)0.0f, (float)0.0f, (float)0.4f);
        GL11.glLineWidth((float)2.0f);
        GL11.glDisable((int)3553);
        GL11.glDepthMask((boolean)false);
        i2 = this.worldObj.getTile(movingObjectPosition.x, movingObjectPosition.y, movingObjectPosition.z);
        if (i2 > 0) {
            AABB movingObjectPosition1 = Tile.tiles[i2].getSelectedBoundingBoxFromPool(movingObjectPosition.x, movingObjectPosition.y, movingObjectPosition.z).expand(0.002f, 0.002f, 0.002f);
            Tesselator tessellator4 = Tesselator.instance;
            Tesselator.instance.startDrawing(3);
            tessellator4.addVertex(movingObjectPosition1.x0, movingObjectPosition1.y0, movingObjectPosition1.z0);
            tessellator4.addVertex(movingObjectPosition1.x1, movingObjectPosition1.y0, movingObjectPosition1.z0);
            tessellator4.addVertex(movingObjectPosition1.x1, movingObjectPosition1.y0, movingObjectPosition1.z1);
            tessellator4.addVertex(movingObjectPosition1.x0, movingObjectPosition1.y0, movingObjectPosition1.z1);
            tessellator4.addVertex(movingObjectPosition1.x0, movingObjectPosition1.y0, movingObjectPosition1.z0);
            tessellator4.end();
            tessellator4.startDrawing(3);
            tessellator4.addVertex(movingObjectPosition1.x0, movingObjectPosition1.y1, movingObjectPosition1.z0);
            tessellator4.addVertex(movingObjectPosition1.x1, movingObjectPosition1.y1, movingObjectPosition1.z0);
            tessellator4.addVertex(movingObjectPosition1.x1, movingObjectPosition1.y1, movingObjectPosition1.z1);
            tessellator4.addVertex(movingObjectPosition1.x0, movingObjectPosition1.y1, movingObjectPosition1.z1);
            tessellator4.addVertex(movingObjectPosition1.x0, movingObjectPosition1.y1, movingObjectPosition1.z0);
            tessellator4.end();
            tessellator4.startDrawing(1);
            tessellator4.addVertex(movingObjectPosition1.x0, movingObjectPosition1.y0, movingObjectPosition1.z0);
            tessellator4.addVertex(movingObjectPosition1.x0, movingObjectPosition1.y1, movingObjectPosition1.z0);
            tessellator4.addVertex(movingObjectPosition1.x1, movingObjectPosition1.y0, movingObjectPosition1.z0);
            tessellator4.addVertex(movingObjectPosition1.x1, movingObjectPosition1.y1, movingObjectPosition1.z0);
            tessellator4.addVertex(movingObjectPosition1.x1, movingObjectPosition1.y0, movingObjectPosition1.z1);
            tessellator4.addVertex(movingObjectPosition1.x1, movingObjectPosition1.y1, movingObjectPosition1.z1);
            tessellator4.addVertex(movingObjectPosition1.x0, movingObjectPosition1.y0, movingObjectPosition1.z1);
            tessellator4.addVertex(movingObjectPosition1.x0, movingObjectPosition1.y1, movingObjectPosition1.z1);
            tessellator4.end();
        }
        GL11.glDepthMask((boolean)true);
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
    }

    private void markBlocksForUpdate(int i1, int i2, int i3, int i4, int i5, int i6) {
        i1 /= 16;
        i2 /= 16;
        i3 /= 16;
        i4 /= 16;
        i5 /= 16;
        i6 /= 16;
        if (i1 < 0) {
            i1 = 0;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        if (i3 < 0) {
            i3 = 0;
        }
        if (i4 > this.renderChunksWide - 1) {
            i4 = this.renderChunksWide - 1;
        }
        if (i5 > this.renderChunksTall - 1) {
            i5 = this.renderChunksTall - 1;
        }
        if (i6 > this.renderChunksDeep - 1) {
            i6 = this.renderChunksDeep - 1;
        }
        int i7 = i1;
        while (i7 <= i4) {
            int i72 = i2;
            while (i72 <= i5) {
                int i8 = i3;
                while (i8 <= i6) {
                    Chunk worldRenderer9 = this.worldRenderers[(i8 * this.renderChunksTall + i72) * this.renderChunksWide + i7];
                    if (!worldRenderer9.needsUpdate) {
                        worldRenderer9.needsUpdate = true;
                        this.worldRenderersToUpdate.add(this.worldRenderers[(i8 * this.renderChunksTall + i72) * this.renderChunksWide + i7]);
                    }
                    ++i8;
                }
                ++i72;
            }
            ++i7;
        }
    }

    @Override
    public final void markBlockAndNeighborsNeedsUpdate(int i1, int i2, int i3) {
        this.markBlocksForUpdate(i1 - 1, i2 - 1, i3 - 1, i1 + 1, i2 + 1, i3 + 1);
    }

    @Override
    public final void markBlockRangeNeedsUpdate(int i1, int i2, int i3, int i4, int i5, int i6) {
        this.markBlocksForUpdate(i1 - 1, i2 - 1, i3 - 1, i4 + 1, i5 + 1, i6 + 1);
    }

    public final void clipRenderersByFrustrum(ICamera iCamera) {
        int i2 = 0;
        while (i2 < this.worldRenderers.length) {
            this.worldRenderers[i2].updateInFrustrum(iCamera);
            ++i2;
        }
    }

    @Override
    public void playSoundAtEntity(Entity e2, String string, float f3, float f4) {
        float f6 = 16.0f;
        if (f3 > 1.0f) {
            f6 = 16.0f * f3;
        }
        if (this.mc.player.getDistanceSqToEntity(e2) < f6 * f6) {
            this.playSound(string, e2.x, e2.y - e2.heightOffset, e2.z, f3, f4);
        }
    }

    @Override
    public final void playSoundAtPlayer(float f1, float f2, float f3, String string, float f5, float f6) {
        float f11;
        float f10;
        float f9;
        float dist = 16.0f;
        if (f5 > 1.0f) {
            dist = 16.0f * f5;
        }
        if ((f9 = f1 - this.mc.player.x) * f9 + (f10 = f2 - this.mc.player.y) * f10 + (f11 = f3 - this.mc.player.z) * f11 < dist * dist) {
            this.playSound(string, f1, f2, f3, f5, f6);
        }
    }

    @Override
    public final void playSound(String string1, float f2, float f3, float f4, float f5, float f6) {
        this.mc.sndManager.playSound(string1, f2, f3, f4, f5, f6);
    }

    @Override
    public final void spawnParticle(String string1, float f2, float f3, float f4, float f5, float f6, float f7) {
        float f8 = this.mc.player.x - f2;
        float f9 = this.mc.player.y - f3;
        float f10 = this.mc.player.z - f4;
        if (f8 * f8 + f9 * f9 + f10 * f10 <= 256.0f) {
            if (string1 == "bubble") {
                this.mc.particles.addEffect(new EntityBubbleFX(this.worldObj, f2, f3, f4, f5, f6, f7));
            } else if (string1 == "smoke") {
                this.mc.particles.addEffect(new EntitySmokeFX(this.worldObj, f2, f3, f4));
            } else if (string1 == "explode") {
                this.mc.particles.addEffect(new EntityExplodeFX(this.worldObj, f2, f3, f4, f5, f6, f7));
            } else if (string1 == "flame") {
                this.mc.particles.addEffect(new EntityFlameFX(this.worldObj, f2, f3, f4));
            } else if (string1 == "lava") {
                this.mc.particles.addEffect(new EntityLavaFX(this.worldObj, f2, f3, f4));
            } else if (string1 == "splash") {
                this.mc.particles.addEffect(new EntitySplashFX(this.worldObj, f2, f3, f4));
            } else if (string1 == "largesmoke") {
                this.mc.particles.addEffect(new EntitySmokeFX(this.worldObj, f2, f3, f4, 2.5f));
            }
        }
    }

    @Override
    public final void playMusic(String string1, float f2, float f3, float f4, float f5) {
        this.mc.sndManager.playRandomMusicIfReady(f2, f3, f4);
    }

    @Override
    public final void obtainEntitySkin(Entity player) {
        player.updateCloak();
        if (player.skinUrl != null) {
            this.textures.addHttpTexture(player.skinUrl, new HttpTextureProcessor());
        }
        if (player.cloakUrl != null) {
            this.textures.addHttpTexture(player.cloakUrl, new HttpTextureProcessor());
        }
    }

    @Override
    public final void releaseEntitySkin(Entity player) {
        if (player.skinUrl != null) {
            this.textures.removeHttpTexture(player.skinUrl);
        }
    }

    @Override
    public final void updateAllRenderers() {
        GL11.glNewList((int)this.glGenList, (int)4864);
        this.oobGroundRenderHeight();
        GL11.glEndList();
        GL11.glNewList((int)(this.glGenList + 1), (int)4864);
        this.oobWaterRenderHeight();
        GL11.glEndList();
    }
}

