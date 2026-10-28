/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.BufferUtils
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.Display
 *  org.lwjgl.opengl.GL11
 *  org.lwjgl.util.glu.GLU
 */
package net.minecraft.client.renderer;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.RenderHelper;
import net.minecraft.client.gamemode.CreativeGamemode;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.player.PlayerSP;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.culling.ClippingHelperImplementation;
import net.minecraft.client.renderer.culling.Frustrum;
import net.minecraft.client.renderer.culling.IsomCamera;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.tile.Tile;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.lwjgl.BufferUtils;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import util.Mth;

public final class GameRenderer {
    private Minecraft mc;
    private boolean anaglyphEnable = false;
    private float farPlaneDistance = 0.0f;
    public ItemRenderer itemRenderer;
    private int rendererUpdateCount;
    private Entity pointedEntity = null;
    private DecimalFormat entityDecimalFormat = new DecimalFormat("0000");
    private ByteBuffer entityByteBuffer;
    private FloatBuffer entityFloatBuffer = BufferUtils.createFloatBuffer((int)16);
    private Random random = new Random();
    private FloatBuffer fogColorBuffer = BufferUtils.createFloatBuffer((int)16);
    private float fogColorRed;
    private float fogColorGreen;
    private float fogColorBlue;
    private float prevFogColor;
    private float fogColor;

    public GameRenderer(Minecraft game) {
        this.mc = game;
        this.itemRenderer = new ItemRenderer(game);
    }

    public final void updateRenderer() {
        this.prevFogColor = this.fogColor;
        float br = this.mc.level.getLightBrightness((int)this.mc.player.x, (int)this.mc.player.y, (int)this.mc.player.z);
        float dist = (float)(3 - this.mc.options.renderDistance) / 3.0f;
        br = br * (1.0f - dist) + dist;
        this.fogColor += (br - this.fogColor) * 0.1f;
        ++this.rendererUpdateCount;
        this.itemRenderer.updateEquippedItem();
    }

    private Vec3 orientCamera(float a2) {
        PlayerSP player = this.mc.player;
        float f3 = this.mc.player.xo + (player.x - player.xo) * a2;
        float f4 = player.yo + (player.y - player.yo) * a2;
        a2 = player.zo + (player.z - player.zo) * a2;
        return new Vec3(f3, f4, a2);
    }

    private void hurtCameraEffect(float a2) {
        PlayerSP player = this.mc.player;
        float f3 = (float)this.mc.player.hurtTime - a2;
        if (player.health <= 0) {
            GL11.glRotatef((float)(40.0f - 8000.0f / ((a2 += (float)player.deathTime) + 200.0f)), (float)0.0f, (float)0.0f, (float)1.0f);
        }
        if (f3 >= 0.0f) {
            f3 /= (float)player.hurtDuration;
            f3 = Mth.sin(f3 * f3 * f3 * f3 * (float)Math.PI);
            a2 = player.hurtDir;
            GL11.glRotatef((float)(-player.hurtDir), (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)(-f3 * 14.0f), (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glRotatef((float)a2, (float)0.0f, (float)1.0f, (float)0.0f);
        }
    }

    private void setupViewBobbing(float a2) {
        if (!this.mc.options.thirdPersonView) {
            PlayerSP player = this.mc.player;
            float f3 = this.mc.player.distanceWalkedModified - player.prevDistanceWalkedModified;
            f3 = player.distanceWalkedModified + f3 * a2;
            float f4 = player.oBob + (player.bob - player.oBob) * a2;
            a2 = player.oTilt + (player.tilt - player.oTilt) * a2;
            GL11.glTranslatef((float)(Mth.sin(f3 * (float)Math.PI) * f4 * 0.5f), (float)(-Math.abs(Mth.cos(f3 * (float)Math.PI) * f4)), (float)0.0f);
            GL11.glRotatef((float)(Mth.sin(f3 * (float)Math.PI) * f4 * 3.0f), (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glRotatef((float)(Math.abs(Mth.cos(f3 * (float)Math.PI + 0.2f) * f4) * 5.0f), (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)a2, (float)1.0f, (float)0.0f, (float)0.0f);
        }
    }

    public final void render(float a2) {
        if (this.anaglyphEnable && !Display.isActive() && !this.mc.hideScreen) {
            this.mc.displayInGameMenu();
        }
        this.anaglyphEnable = Display.isActive();
        if (this.mc.mouseGrabbed) {
            this.mc.mouseHelper.mouseXYChange();
            float mouseSensitivity = this.mc.options.sliders[0].value * 0.6f + 0.2f;
            float f2 = mouseSensitivity * 0.6f + 0.2f;
            float f3 = f2 * f2 * f2 * 8.0f;
            float f4 = (float)this.mc.mouseHelper.deltaX * f3;
            float f5 = (float)this.mc.mouseHelper.deltaY * f3;
            int dir = 1;
            if (this.mc.options.invertMouse) {
                dir = -1;
            }
            this.mc.player.interpolateTurn(f4, f5 * (float)dir);
        }
        ScaledResolution res = new ScaledResolution(this.mc.width, this.mc.height);
        int w2 = res.getScaledWidth();
        int h2 = res.getScaledHeight();
        int dx = Mouse.getX() * w2 / this.mc.width;
        int dy = h2 - Mouse.getY() * h2 / this.mc.height - 1;
        if (this.mc.hideScreen) {
            return;
        }
        if (this.mc.level != null) {
            this.renderLevel(a2);
            this.mc.hud.renderGameOverlay(a2, dx, dy);
        } else {
            GL11.glViewport((int)0, (int)0, (int)this.mc.width, (int)this.mc.height);
            GL11.glClearColor((float)0.0f, (float)0.0f, (float)0.0f, (float)0.0f);
            GL11.glClear((int)16640);
            GL11.glMatrixMode((int)5889);
            GL11.glLoadIdentity();
            GL11.glMatrixMode((int)5888);
            GL11.glLoadIdentity();
            this.setupOverlayRendering();
        }
        if (this.mc.screen != null && !this.mc.hideScreen) {
            GL11.glClear((int)256);
            this.mc.screen.render(dx, dy, a2);
        }
        Thread.yield();
        Display.update();
    }

    public final void renderIsometricScreenshot() {
        File file3;
        this.mc.loadingScreen.beginLevelLoading("Grabbing large screenshot");
        File file1 = new File(System.getProperty("user.home", "."));
        int i2 = 0;
        while ((file3 = new File(file1, "mc_map_" + this.entityDecimalFormat.format(i2) + ".png")).exists()) {
            ++i2;
        }
        file3 = file3.getAbsoluteFile();
        this.mc.loadingScreen.levelLoadUpdate("Rendering");
        this.mc.loadingScreen.setLoadingProgress(0);
        try {
            int i19 = (this.mc.level.width << 4) + (this.mc.level.depth << 4);
            i2 = (this.mc.level.height << 4) + i19 / 2;
            BufferedImage bufferedImage4 = new BufferedImage(i19, i2, 1);
            Graphics graphics5 = bufferedImage4.getGraphics();
            int i6 = this.mc.width;
            int i7 = this.mc.height;
            int i8 = (i19 / i6 + 1) * (i2 / i7 + 1);
            int i9 = 0;
            int i10 = 0;
            while (i10 < i19) {
                int i11 = 0;
                while (i11 < i2) {
                    int i10002;
                    this.mc.loadingScreen.setLoadingProgress(++i9 * 100 / i8);
                    int i10001 = i10 - i19 / 2;
                    int i14 = i10002 = i11 - i2 / 2;
                    int i13 = i10001;
                    if (this.entityByteBuffer == null) {
                        this.entityByteBuffer = BufferUtils.createByteBuffer((int)(this.mc.width * this.mc.height << 2));
                    }
                    PlayerSP entityPlayerSP15 = this.mc.player;
                    Level world16 = this.mc.level;
                    LevelRenderer renderGlobal17 = this.mc.levelRenderer;
                    GL11.glViewport((int)0, (int)0, (int)this.mc.width, (int)this.mc.height);
                    this.updateFogColor(0.0f);
                    GL11.glClear((int)16640);
                    GL11.glEnable((int)2884);
                    this.farPlaneDistance = 512 >> (this.mc.options.renderDistance << 1);
                    GL11.glMatrixMode((int)5889);
                    GL11.glLoadIdentity();
                    GL11.glOrtho((double)0.0, (double)this.mc.width, (double)0.0, (double)this.mc.height, (double)10.0, (double)10000.0);
                    GL11.glMatrixMode((int)5888);
                    GL11.glLoadIdentity();
                    GL11.glTranslatef((float)(-i13), (float)(-i14), (float)-5000.0f);
                    GL11.glScalef((float)16.0f, (float)-16.0f, (float)-16.0f);
                    float f22 = 1.0f;
                    f22 = 0.0f;
                    f22 = 0.0f;
                    f22 = 0.0f;
                    f22 = 0.0f;
                    f22 = 0.0f;
                    f22 = 0.5f;
                    f22 = 1.0f;
                    f22 = 0.0f;
                    f22 = -1.0f;
                    f22 = 1.0f;
                    f22 = 0.0f;
                    f22 = 0.0f;
                    f22 = 0.0f;
                    f22 = -0.5f;
                    f22 = 1.0f;
                    this.entityFloatBuffer.clear();
                    this.entityFloatBuffer.put(1.0f).put(-0.5f).put(0.0f).put(0.0f);
                    this.entityFloatBuffer.put(0.0f).put(1.0f).put(-1.0f).put(0.0f);
                    this.entityFloatBuffer.put(1.0f).put(0.5f).put(0.0f).put(0.0f);
                    this.entityFloatBuffer.put(0.0f).put(0.0f).put(0.0f).put(1.0f);
                    this.entityFloatBuffer.flip();
                    GL11.glMultMatrix((FloatBuffer)this.entityFloatBuffer);
                    GL11.glRotatef((float)0.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    GL11.glTranslatef((float)((float)(-world16.width) / 2.0f), (float)((float)(-world16.height) / 2.0f), (float)((float)(-world16.depth) / 2.0f));
                    IsomCamera isomCamera24 = new IsomCamera();
                    this.mc.levelRenderer.clipRenderersByFrustrum(isomCamera24);
                    this.mc.levelRenderer.updateRenderers(entityPlayerSP15);
                    this.setupFog();
                    GL11.glEnable((int)2912);
                    GL11.glFogi((int)2917, (int)9729);
                    float f23 = (float)world16.height * 8.0f;
                    GL11.glFogf((int)2915, (float)(5000.0f - f23));
                    GL11.glFogf((int)2916, (float)(5000.0f + f23 * 8.0f));
                    RenderHelper.enableStandardItemLighting();
                    renderGlobal17.renderEntities(this.orientCamera(0.0f), isomCamera24, 0.0f);
                    RenderHelper.disableStandardItemLighting();
                    GL11.glBindTexture((int)3553, (int)this.mc.renderEngine.getTexture("/terrain.png"));
                    renderGlobal17.sortAndRender(entityPlayerSP15, 0);
                    renderGlobal17.oobGroundRenderer();
                    if (world16.cloudHeight < world16.height) {
                        renderGlobal17.renderSky(0.0f);
                    }
                    GL11.glEnable((int)3042);
                    GL11.glBlendFunc((int)770, (int)771);
                    GL11.glColorMask((boolean)false, (boolean)false, (boolean)false, (boolean)false);
                    i13 = renderGlobal17.sortAndRender(entityPlayerSP15, 1);
                    GL11.glColorMask((boolean)true, (boolean)true, (boolean)true, (boolean)true);
                    if (i13 > 0) {
                        renderGlobal17.renderAllRenderLists();
                    }
                    if (world16.getGroundLevel() >= 0) {
                        renderGlobal17.oobWaterRenderer();
                    }
                    GL11.glDepthMask((boolean)true);
                    GL11.glDisable((int)3042);
                    GL11.glDisable((int)2912);
                    this.entityByteBuffer.clear();
                    GL11.glPixelStorei((int)3333, (int)1);
                    GL11.glReadPixels((int)0, (int)0, (int)this.mc.width, (int)this.mc.height, (int)6407, (int)5121, (ByteBuffer)this.entityByteBuffer);
                    BufferedImage bufferedImage21 = GameRenderer.screenshotBuffer(this.entityByteBuffer, i6, i7);
                    graphics5.drawImage(bufferedImage21, i10, i11, null);
                    i11 += i7;
                }
                i10 += i6;
            }
            graphics5.dispose();
            this.mc.loadingScreen.levelLoadUpdate("Saving as " + file3.toString());
            this.mc.loadingScreen.setLoadingProgress(100);
            FileOutputStream fileOutputStream20 = new FileOutputStream(file3);
            ImageIO.write((RenderedImage)bufferedImage4, "png", fileOutputStream20);
            fileOutputStream20.close();
        }
        catch (Throwable throwable18) {
            throwable18.printStackTrace();
        }
    }

    private static BufferedImage screenshotBuffer(ByteBuffer byteBuffer0, int i1, int i2) {
        byteBuffer0.position(0).limit(i1 * i2 << 2);
        BufferedImage bufferedImage3 = new BufferedImage(i1, i2, 1);
        int[] i4 = ((DataBufferInt)bufferedImage3.getRaster().getDataBuffer()).getData();
        int i5 = 0;
        while (i5 < i1 * i2) {
            int i6 = byteBuffer0.get(i5 * 3) & 0xFF;
            int i7 = byteBuffer0.get(i5 * 3 + 1) & 0xFF;
            int i8 = byteBuffer0.get(i5 * 3 + 2) & 0xFF;
            i4[i5] = i6 << 16 | i7 << 8 | i8;
            ++i5;
        }
        return bufferedImage3;
    }

    private void renderLevel(float f1) {
        GameRenderer entityRenderer7 = this;
        PlayerSP entityPlayerSP9 = this.mc.player;
        float f2 = entityPlayerSP9.yRotI + (entityPlayerSP9.yRot - entityPlayerSP9.yRotI) * f1;
        float f10 = entityPlayerSP9.xRotI + (entityPlayerSP9.xRot - entityPlayerSP9.xRotI) * f1;
        Vec3 vec3D11 = this.orientCamera(f1);
        float f12 = Mth.cos(-f10 * ((float)Math.PI / 180) - (float)Math.PI);
        float f13 = Mth.sin(-f10 * ((float)Math.PI / 180) - (float)Math.PI);
        float f14 = -Mth.cos(-f2 * ((float)Math.PI / 180));
        float f15 = Mth.sin(-f2 * ((float)Math.PI / 180));
        float f16 = f13 * f14;
        float f17 = f12 * f14;
        float f18 = this.mc.gamemode.getBlockReach();
        Vec3 vec3D19 = vec3D11.addVector(f16 * f18, f15 * f18, f17 * f18);
        this.mc.hitResult = this.mc.level.rayTraceBlocks(vec3D11, vec3D19);
        float f20 = f18;
        vec3D11 = this.orientCamera(f1);
        if (this.mc.hitResult != null) {
            f20 = this.mc.hitResult.hitVec.distance(vec3D11);
        }
        if (this.mc.gamemode instanceof CreativeGamemode) {
            f18 = 32.0f;
        } else {
            if (f20 > 3.0f) {
                f20 = 3.0f;
            }
            f18 = f20;
        }
        vec3D19 = vec3D11.addVector(f16 * f18, f15 * f18, f17 * f18);
        this.pointedEntity = null;
        List<Entity> list8 = this.mc.level.entityMap.getEntitiesWithinAABB(entityPlayerSP9, entityPlayerSP9.bb.addCoord(f16 * f18, f15 * f18, f17 * f18));
        float f27 = 0.0f;
        int i40 = 0;
        while (i40 < list8.size()) {
            HitResult movingObjectPosition22;
            Entity entity21 = list8.get(i40);
            if (entity21.canBeCollidedWith() && (movingObjectPosition22 = entity21.bb.expand(0.1f, 0.1f, 0.1f).calculateIntercept(vec3D11, vec3D19)) != null && ((f2 = vec3D11.distance(movingObjectPosition22.hitVec)) < f27 || f27 == 0.0f)) {
                entityRenderer7.pointedEntity = entity21;
                f27 = f2;
            }
            ++i40;
        }
        if (entityRenderer7.pointedEntity != null) {
            entityRenderer7.mc.hitResult = new HitResult(entityRenderer7.pointedEntity);
        }
        int i23 = 0;
        while (i23 < 2) {
            int i28;
            if (this.mc.options.anaglyph3d) {
                if (i23 == 0) {
                    GL11.glColorMask((boolean)false, (boolean)true, (boolean)true, (boolean)false);
                } else {
                    GL11.glColorMask((boolean)true, (boolean)false, (boolean)false, (boolean)false);
                }
            }
            PlayerSP entityPlayerSP3 = this.mc.player;
            Level world4 = this.mc.level;
            LevelRenderer renderGlobal5 = this.mc.levelRenderer;
            EffectRenderer effectRenderer6 = this.mc.particles;
            GL11.glViewport((int)0, (int)0, (int)this.mc.width, (int)this.mc.height);
            this.updateFogColor(f1);
            GL11.glClear((int)16640);
            GL11.glEnable((int)2884);
            float f26 = f1;
            this.farPlaneDistance = 512 >> (this.mc.options.renderDistance << 1);
            GL11.glMatrixMode((int)5889);
            GL11.glLoadIdentity();
            if (this.mc.options.anaglyph3d) {
                GL11.glTranslatef((float)((float)(-((i23 << 1) - 1)) * 0.07f), (float)0.0f, (float)0.0f);
            }
            PlayerSP entityPlayerSP33 = this.mc.player;
            f13 = 70.0f;
            if (entityPlayerSP33.isInsideOfWater()) {
                f13 = 60.0f;
            }
            if (entityPlayerSP33.health <= 0) {
                f14 = (float)entityPlayerSP33.deathTime + f1;
                f13 /= (1.0f - 500.0f / (f14 + 500.0f)) * 2.0f + 1.0f;
            }
            GLU.gluPerspective((float)f13, (float)((float)this.mc.width / (float)this.mc.height), (float)0.05f, (float)this.farPlaneDistance);
            GL11.glMatrixMode((int)5888);
            GL11.glLoadIdentity();
            if (this.mc.options.anaglyph3d) {
                GL11.glTranslatef((float)((float)((i23 << 1) - 1) * 0.1f), (float)0.0f, (float)0.0f);
            }
            this.hurtCameraEffect(f1);
            if (this.mc.options.fancyGraphics) {
                this.setupViewBobbing(f1);
            }
            GameRenderer entityRenderer29 = this;
            float f10001 = entityPlayerSP33.x - entityPlayerSP33.xo;
            entityPlayerSP33 = this.mc.player;
            f13 = entityPlayerSP33.xo + f10001 * f1;
            f14 = entityPlayerSP33.yo + (entityPlayerSP33.y - entityPlayerSP33.yo) * f1;
            f15 = entityPlayerSP33.zo + (entityPlayerSP33.z - entityPlayerSP33.zo) * f1;
            if (!this.mc.options.thirdPersonView) {
                GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-0.1f);
            } else {
                f16 = 4.0f;
                float f24 = -Mth.sin(entityPlayerSP33.xRot / 180.0f * (float)Math.PI) * Mth.cos(entityPlayerSP33.yRot / 180.0f * (float)Math.PI) * 4.0f;
                f17 = Mth.cos(entityPlayerSP33.xRot / 180.0f * (float)Math.PI) * Mth.cos(entityPlayerSP33.yRot / 180.0f * (float)Math.PI) * 4.0f;
                f18 = -Mth.sin(entityPlayerSP33.yRot / 180.0f * (float)Math.PI) * 4.0f;
                int i38 = 0;
                while (i38 < 8) {
                    HitResult movingObjectPosition41;
                    f20 = ((i38 & 1) << 1) - 1;
                    f26 = ((i38 >> 1 & 1) << 1) - 1;
                    f27 = ((i38 >> 2 & 1) << 1) - 1;
                    if ((movingObjectPosition41 = entityRenderer29.mc.level.rayTraceBlocks(new Vec3(f13 + (f20 *= 0.1f), f14 + (f26 *= 0.1f), f15 + (f27 *= 0.1f)), new Vec3(f13 - f24 + f20 + f27, f14 - f18 + f26, f15 - f17 + f27))) != null) {
                        float f39 = movingObjectPosition41.hitVec.distance(new Vec3(f13, f14, f15));
                        if (f39 < f16) {
                            f16 = f39;
                        }
                    }
                    ++i38;
                }
                GL11.glTranslatef((float)0.0f, (float)0.0f, (float)(-f16));
            }
            GL11.glRotatef((float)(entityPlayerSP33.yRotI + (entityPlayerSP33.yRot - entityPlayerSP33.yRotI) * f26), (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)(entityPlayerSP33.xRotI + (entityPlayerSP33.xRot - entityPlayerSP33.xRotI) * f26 + 180.0f), (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glTranslatef((float)(-f13), (float)(-f14), (float)(-f15));
            ClippingHelperImplementation.init();
            this.setupFog();
            GL11.glEnable((int)2912);
            renderGlobal5.renderSky(f1);
            this.setupFog();
            Frustrum frustrum25 = new Frustrum();
            this.mc.levelRenderer.clipRenderersByFrustrum(frustrum25);
            this.mc.levelRenderer.updateRenderers(entityPlayerSP3);
            this.setupFog();
            GL11.glEnable((int)2912);
            GL11.glBindTexture((int)3553, (int)this.mc.renderEngine.getTexture("/terrain.png"));
            RenderHelper.disableStandardItemLighting();
            renderGlobal5.sortAndRender(entityPlayerSP3, 0);
            if (world4.isSolid(entityPlayerSP3.x, entityPlayerSP3.y, entityPlayerSP3.z, 0.1f)) {
                i28 = (int)entityPlayerSP3.x;
                int i30 = (int)entityPlayerSP3.y;
                int i31 = (int)entityPlayerSP3.z;
                RenderBlocks renderBlocks32 = new RenderBlocks(world4);
                int i34 = i28 - 1;
                while (i34 <= i28 + 1) {
                    int i36 = i30 - 1;
                    while (i36 <= i30 + 1) {
                        int i35 = i31 - 1;
                        while (i35 <= i31 + 1) {
                            int i37 = world4.getTile(i34, i36, i35);
                            if (i37 > 0) {
                                renderBlocks32.renderBlockAllFaces(Tile.tiles[i37], i34, i36, i35);
                            }
                            ++i35;
                        }
                        ++i36;
                    }
                    ++i34;
                }
            }
            RenderHelper.enableStandardItemLighting();
            renderGlobal5.renderEntities(this.orientCamera(f1), frustrum25, f1);
            effectRenderer6.renderLitParticles(f1);
            RenderHelper.disableStandardItemLighting();
            this.setupFog();
            effectRenderer6.renderParticles(entityPlayerSP3, f1);
            renderGlobal5.oobGroundRenderer();
            if (this.mc.hitResult != null && entityPlayerSP3.isInsideOfWater()) {
                GL11.glDisable((int)3008);
                renderGlobal5.drawBlockBreaking(this.mc.hitResult, 0, entityPlayerSP3.inventory.getCurrentItem());
                renderGlobal5.drawSelectionBox(this.mc.hitResult, 0);
                GL11.glEnable((int)3008);
            }
            GL11.glBlendFunc((int)770, (int)771);
            this.setupFog();
            renderGlobal5.oobWaterRenderer();
            GL11.glEnable((int)3042);
            GL11.glDisable((int)2884);
            GL11.glColorMask((boolean)false, (boolean)false, (boolean)false, (boolean)false);
            i28 = renderGlobal5.sortAndRender(entityPlayerSP3, 1);
            GL11.glColorMask((boolean)true, (boolean)true, (boolean)true, (boolean)true);
            if (this.mc.options.anaglyph3d) {
                if (i23 == 0) {
                    GL11.glColorMask((boolean)false, (boolean)true, (boolean)true, (boolean)false);
                } else {
                    GL11.glColorMask((boolean)true, (boolean)false, (boolean)false, (boolean)false);
                }
            }
            if (i28 > 0) {
                renderGlobal5.renderAllRenderLists();
            }
            GL11.glDepthMask((boolean)true);
            GL11.glEnable((int)2884);
            GL11.glDisable((int)3042);
            if (this.mc.hitResult != null && !entityPlayerSP3.isInsideOfWater()) {
                GL11.glDisable((int)3008);
                renderGlobal5.drawBlockBreaking(this.mc.hitResult, 0, entityPlayerSP3.inventory.getCurrentItem());
                renderGlobal5.drawSelectionBox(this.mc.hitResult, 0);
                GL11.glEnable((int)3008);
            }
            GL11.glDisable((int)2912);
            GL11.glClear((int)256);
            GL11.glLoadIdentity();
            if (this.mc.options.anaglyph3d) {
                GL11.glTranslatef((float)((float)((i23 << 1) - 1) * 0.1f), (float)0.0f, (float)0.0f);
            }
            GL11.glPushMatrix();
            this.hurtCameraEffect(f1);
            if (this.mc.options.fancyGraphics) {
                this.setupViewBobbing(f1);
            }
            if (!this.mc.options.thirdPersonView) {
                this.itemRenderer.renderItemInFirstPerson(f1);
            }
            GL11.glPopMatrix();
            if (!this.mc.options.thirdPersonView) {
                this.itemRenderer.renderOverlays(f1);
                this.hurtCameraEffect(f1);
            }
            if (this.mc.options.fancyGraphics) {
                this.setupViewBobbing(f1);
            }
            if (!this.mc.options.anaglyph3d) {
                return;
            }
            ++i23;
        }
        GL11.glColorMask((boolean)true, (boolean)true, (boolean)true, (boolean)false);
    }

    public final void setupOverlayRendering() {
        ScaledResolution res = new ScaledResolution(this.mc.width, this.mc.height);
        int w2 = res.getScaledWidth();
        int h2 = res.getScaledHeight();
        GL11.glClear((int)256);
        GL11.glMatrixMode((int)5889);
        GL11.glLoadIdentity();
        GL11.glOrtho((double)0.0, (double)w2, (double)h2, (double)0.0, (double)1000.0, (double)3000.0);
        GL11.glMatrixMode((int)5888);
        GL11.glLoadIdentity();
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-2000.0f);
    }

    private void updateFogColor(float f1) {
        Level world2 = this.mc.level;
        PlayerSP entityPlayerSP3 = this.mc.player;
        float f4 = 1.0f / (float)(4 - this.mc.options.renderDistance);
        f4 = 1.0f - (float)Math.pow(f4, 0.25);
        Vec3 vec3D5 = world2.getSkyColor(f1);
        float f6 = vec3D5.x;
        float f7 = vec3D5.y;
        float f13 = vec3D5.z;
        Vec3 vec3D8 = world2.getFogColor(f1);
        this.fogColorRed = vec3D8.x;
        this.fogColorGreen = vec3D8.y;
        this.fogColorBlue = vec3D8.z;
        this.fogColorRed += (f6 - this.fogColorRed) * f4;
        this.fogColorGreen += (f7 - this.fogColorGreen) * f4;
        this.fogColorBlue += (f13 - this.fogColorBlue) * f4;
        Tile block9 = Tile.tiles[world2.getTile((int)entityPlayerSP3.x, (int)(entityPlayerSP3.y + 0.12f), (int)entityPlayerSP3.z)];
        if (block9 != null && block9.material != Material.air) {
            Material material10 = block9.material;
            if (material10 == Material.water) {
                this.fogColorRed = 0.02f;
                this.fogColorGreen = 0.02f;
                this.fogColorBlue = 0.2f;
            } else if (material10 == Material.lava) {
                this.fogColorRed = 0.6f;
                this.fogColorGreen = 0.1f;
                this.fogColorBlue = 0.0f;
            }
        }
        float f11 = this.prevFogColor + (this.fogColor - this.prevFogColor) * f1;
        this.fogColorRed *= f11;
        this.fogColorGreen *= f11;
        this.fogColorBlue *= f11;
        if (this.mc.options.anaglyph3d) {
            f1 = (this.fogColorRed * 30.0f + this.fogColorGreen * 59.0f + this.fogColorBlue * 11.0f) / 100.0f;
            f11 = (this.fogColorRed * 30.0f + this.fogColorGreen * 70.0f) / 100.0f;
            float f12 = (this.fogColorRed * 30.0f + this.fogColorBlue * 70.0f) / 100.0f;
            this.fogColorRed = f1;
            this.fogColorGreen = f11;
            this.fogColorBlue = f12;
        }
        GL11.glClearColor((float)this.fogColorRed, (float)this.fogColorGreen, (float)this.fogColorBlue, (float)0.0f);
    }

    private void setupFog() {
        Level world1 = this.mc.level;
        PlayerSP entityPlayerSP2 = this.mc.player;
        float f6 = this.fogColorBlue;
        float f5 = this.fogColorGreen;
        float f4 = this.fogColorRed;
        this.fogColorBuffer.clear();
        this.fogColorBuffer.put(f4).put(f5).put(f6).put(1.0f);
        this.fogColorBuffer.flip();
        GL11.glFog((int)2918, (FloatBuffer)this.fogColorBuffer);
        GL11.glNormal3f((float)0.0f, (float)-1.0f, (float)0.0f);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        Tile block7 = Tile.tiles[world1.getTile((int)entityPlayerSP2.x, (int)(entityPlayerSP2.y + 0.12f), (int)entityPlayerSP2.z)];
        if (block7 != null && block7.material.getIsLiquid()) {
            Material material8 = block7.material;
            GL11.glFogi((int)2917, (int)2048);
            if (material8 == Material.water) {
                GL11.glFogf((int)2914, (float)0.1f);
            } else if (material8 == Material.lava) {
                GL11.glFogf((int)2914, (float)2.0f);
            }
        } else {
            GL11.glFogi((int)2917, (int)9729);
            GL11.glFogf((int)2915, (float)(this.farPlaneDistance / 4.0f));
            GL11.glFogf((int)2916, (float)this.farPlaneDistance);
        }
        GL11.glEnable((int)2903);
        GL11.glColorMaterial((int)1028, (int)4608);
    }
}

