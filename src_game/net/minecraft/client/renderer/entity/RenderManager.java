/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client.renderer.entity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.client.gui.Font;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelPig;
import net.minecraft.client.model.ModelSheep;
import net.minecraft.client.model.ModelSheepFur;
import net.minecraft.client.model.ModelSkeleton;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.network.ClientNetworkPlayer;
import net.minecraft.client.network.NetworkPlayer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderCreeper;
import net.minecraft.client.renderer.entity.RenderEntity;
import net.minecraft.client.renderer.entity.RenderGiantZombie;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderPainting;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.RenderPrimedTnt;
import net.minecraft.client.renderer.entity.RenderSheep;
import net.minecraft.client.renderer.entity.RenderSpider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Painting;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.GiantZombie;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.level.Level;
import org.lwjgl.opengl.GL11;

public final class RenderManager {
    private Map<Class<? extends Entity>, Render> entityRenderMap = new HashMap<Class<? extends Entity>, Render>();
    public static RenderManager instance = new RenderManager();
    public RenderEngine renderEngine;
    public ItemRenderer itemRenderer;
    public Level worldObj;
    public Player player;
    public Font font;
    public float playerViewX;
    public float playerViewY;
    private float viewerPosX;
    private float viewerPosY;
    private float viewerPosZ;

    private RenderManager() {
        this.entityRenderMap.put(Spider.class, new RenderSpider());
        this.entityRenderMap.put(Pig.class, new RenderLiving(new ModelPig(), 0.7f));
        this.entityRenderMap.put(Sheep.class, new RenderSheep(new ModelSheep(), new ModelSheepFur(), 0.7f));
        this.entityRenderMap.put(Creeper.class, new RenderCreeper());
        this.entityRenderMap.put(Skeleton.class, new RenderBiped(new ModelSkeleton(), 0.5f));
        this.entityRenderMap.put(Zombie.class, new RenderLiving(new ModelZombie(), 0.5f));
        this.entityRenderMap.put(Player.class, new RenderPlayer());
        this.entityRenderMap.put(ClientNetworkPlayer.class, new RenderPlayer());
        this.entityRenderMap.put(NetworkPlayer.class, new RenderPlayer());
        this.entityRenderMap.put(GiantZombie.class, new RenderGiantZombie(new ModelZombie(), 0.5f, 6.0f));
        this.entityRenderMap.put(Monster.class, new RenderLiving(new ModelBiped(), 0.5f));
        this.entityRenderMap.put(LivingEntity.class, new RenderLiving(new ModelBiped(), 0.5f));
        this.entityRenderMap.put(Entity.class, new RenderEntity());
        this.entityRenderMap.put(Painting.class, new RenderPainting());
        this.entityRenderMap.put(Arrow.class, new RenderArrow());
        this.entityRenderMap.put(ItemEntity.class, new RenderItem());
        this.entityRenderMap.put(PrimedTnt.class, new RenderPrimedTnt());
        Iterator<Render> renders = this.entityRenderMap.values().iterator();
        while (renders.hasNext()) {
            renders.next().setRenderManager(this);
        }
    }

    public final Render getEntityRenderObject(Entity entity) {
        Class<? extends Entity> c2 = entity.getClass();
        Render render3 = this.entityRenderMap.get(c2);
        if (render3 == null && c2 != Entity.class) {
            render3 = this.entityRenderMap.get(c2.getSuperclass());
            ((Map)this.entityRenderMap).put(c2, render3);
        }
        return render3;
    }

    public final void cacheActiveRenderInfo(Level world, RenderEngine renderEngine, Font font, Player player, float a2) {
        this.worldObj = world;
        this.renderEngine = renderEngine;
        this.player = player;
        this.font = font;
        this.playerViewX = player.yRotI + (player.yRot - player.yRotI) * a2;
        this.playerViewY = player.xRotI + (player.xRot - player.xRotI) * a2;
        this.viewerPosX = player.lastTickX + (player.x - player.lastTickX) * a2;
        this.viewerPosY = player.lastTickY + (player.y - player.lastTickY) * a2;
        this.viewerPosZ = player.lastTickZ + (player.z - player.lastTickZ) * a2;
    }

    public final void renderEntity(Entity entity, float a2) {
        float x2 = entity.lastTickX + (entity.x - entity.lastTickX) * a2;
        float y2 = entity.lastTickY + (entity.y - entity.lastTickY) * a2;
        float z2 = entity.lastTickZ + (entity.z - entity.lastTickZ) * a2;
        float rot = entity.xRotI + (entity.xRot - entity.xRotI) * a2;
        float br = this.worldObj.getLightBrightness((int)x2, (int)(y2 + entity.getShadowSize()), (int)z2);
        GL11.glColor3f((float)br, (float)br, (float)br);
        this.renderEntityWithPosYaw(entity, x2, y2, z2, rot, a2);
    }

    public final void renderEntityWithPosYaw(Entity entity, float f2, float f3, float f4, float f5, float f6) {
        Render render = this.getEntityRenderObject(entity);
        if (render != null) {
            render.doRender(entity, f2, f3, f4, f5, f6);
            render.renderShadow(entity, f2, f3, f4, f6);
        }
    }

    public final void setLevel(Level world) {
        this.worldObj = world;
    }

    public final float getDistanceToCamera(float f1, float f2, float f3) {
        return (f1 -= this.viewerPosX) * f1 + (f2 -= this.viewerPosY) * f2 + (f3 -= this.viewerPosZ) * f3;
    }
}

