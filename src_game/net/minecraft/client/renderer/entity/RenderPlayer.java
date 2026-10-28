/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client.renderer.entity;

import net.minecraft.client.gui.Font;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.Tesselator;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemArmor;
import net.minecraft.world.item.ItemStack;
import org.lwjgl.opengl.GL11;

public final class RenderPlayer
extends RenderLiving {
    private ModelBiped modelBipedMain;
    private ModelBiped modelArmorChestplate;
    private ModelBiped modelArmor;
    private static final String[] armorFilenamePrefix = new String[]{"cloth", "chain", "iron", "diamond", "gold"};

    public RenderPlayer() {
        super(new ModelBiped(0.0f), 0.5f);
        this.modelBipedMain = (ModelBiped)this.mainModel;
        this.modelArmorChestplate = new ModelBiped(1.0f);
        this.modelArmor = new ModelBiped(0.5f);
    }

    private void renderPlayer(Player player, float f2, float f3, float f4, float f5, float f6) {
        super.renderEntity(player, f2, f3 - player.heightOffset, f4, f5, f6);
    }

    public final void drawFirstPersonHand() {
        this.modelBipedMain.bipedRightArm.render(1.0f);
    }

    @Override
    protected final boolean shouldRenderPass(LivingEntity entityLiving, int i2) {
        Item item7;
        Player entityPlayer10001 = (Player)entityLiving;
        int i3 = i2;
        Player entityPlayer5 = entityPlayer10001;
        int i4 = 3 - i3;
        ItemStack itemStack6 = entityPlayer5.inventory.armorInventory[i4];
        if (itemStack6 != null && (item7 = itemStack6.getItem()) instanceof ItemArmor) {
            ItemArmor itemArmor8 = (ItemArmor)item7;
            this.loadTexture("/armor/" + armorFilenamePrefix[itemArmor8.renderIndex] + "_" + (i3 == 2 ? 2 : 1) + ".png");
            ModelBiped modelBiped9 = i3 == 2 ? this.modelArmor : this.modelArmorChestplate;
            modelBiped9.bipedHead.showModel = i3 == 0;
            modelBiped9.bipedHeadwear.showModel = i3 == 0;
            modelBiped9.bipedBody.showModel = i3 == 1 || i3 == 2;
            modelBiped9.bipedRightArm.showModel = i3 == 1;
            modelBiped9.bipedLeftArm.showModel = i3 == 1;
            modelBiped9.bipedRightLeg.showModel = i3 == 2 || i3 == 3;
            modelBiped9.bipedLeftLeg.showModel = i3 == 2 || i3 == 3;
            this.setRenderPassModel(modelBiped9);
            return true;
        }
        return false;
    }

    @Override
    public final void renderEntity(LivingEntity entityLiving1, float f2, float f3, float f4, float f5, float f6) {
        this.renderPlayer((Player)entityLiving1, f2, f3, f4, f5, f6);
    }

    @Override
    public final void doRender(Entity entity, float f2, float f3, float f4, float f5, float f6) {
        this.renderPlayer((Player)entity, f2, f3, f4, f5, f6);
    }

    @Override
    protected void renderEquippedItems(LivingEntity e2, float a2) {
        this.renderSpecials((Player)e2, a2);
    }

    @Override
    protected void passSpecialRender(LivingEntity e2, float x2, float y2, float z2) {
        if (e2 != this.renderManager.player) {
            String name = ((Player)e2).username;
            this.renderLivingLabel(e2, name, x2, y2, z2, 128);
            if (!e2.speaking) {
                return;
            }
            Font font = this.renderManager.font;
            GL11.glPushMatrix();
            GL11.glTranslatef((float)x2, (float)(y2 + 0.8f + 1.62f + e2.onlineOffset), (float)z2);
            GL11.glRotatef((float)(-this.renderManager.playerViewY + 180.0f), (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)(-this.renderManager.playerViewX), (float)1.0f, (float)0.0f, (float)0.0f);
            float scale = 0.05f;
            GL11.glScalef((float)scale, (float)(-scale), (float)scale);
            GL11.glTranslatef((float)((float)font.width(name) / 2.0f), (float)-4.0f, (float)0.0f);
            GL11.glNormal3f((float)1.0f, (float)-1.0f, (float)1.0f);
            GL11.glDisable((int)2896);
            GL11.glDisable((int)16384);
            GL11.glDepthFunc((int)516);
            GL11.glDepthMask((boolean)false);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)0.8f);
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            GL11.glBindTexture((int)3553, (int)this.renderManager.renderEngine.getTexture("/gui/icons.png"));
            int px = 0;
            int py = 48;
            this.drawTexturedModalRect(0, 0, px, py, 16, 16, -1.0f);
            GL11.glDisable((int)3042);
            GL11.glDepthMask((boolean)true);
            GL11.glDepthFunc((int)515);
            GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-0.05f);
            this.drawTexturedModalRect(0, 0, px, py, 16, 16, -1.0f);
            GL11.glEnable((int)16384);
            GL11.glEnable((int)2896);
            GL11.glEnable((int)3553);
            GL11.glPopMatrix();
        }
    }

    public void drawTexturedModalRect(int x2, int y2, int px, int py, int pw, int ph, float zLevel) {
        Tesselator t2 = Tesselator.instance;
        Tesselator.instance.begin();
        t2.vertexUV(x2, y2 + ph, zLevel, (float)px * 0.00390625f, (float)(py + ph) * 0.00390625f);
        t2.vertexUV(x2 + pw, y2 + ph, zLevel, (float)(px + pw) * 0.00390625f, (float)(py + ph) * 0.00390625f);
        t2.vertexUV(x2 + pw, y2, zLevel, (float)(px + pw) * 0.00390625f, (float)py * 0.00390625f);
        t2.vertexUV(x2, y2, zLevel, (float)px * 0.00390625f, (float)py * 0.00390625f);
        t2.end();
    }

    protected void renderSpecials(Player player, float a2) {
        float scale = 1.0f;
        ItemStack var21 = player.inventory.getCurrentItem();
        if (var21 != null) {
            GL11.glPushMatrix();
            this.modelBipedMain.bipedRightArm.postRender(1.0f / scale);
            GL11.glTranslatef((float)(-(1.0f / scale)), (float)(7.0f / scale), (float)(1.0f / scale));
            if (var21.itemID < 256) {
                float var5 = 0.5f;
                GL11.glTranslatef((float)0.0f, (float)(3.0f / scale), (float)(-(5.0f / scale)));
                GL11.glRotatef((float)20.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glScalef((float)(var5 *= 12.0f / scale), (float)(-var5), (float)var5);
            } else {
                float var5 = 6.0f / scale;
                GL11.glTranslatef((float)0.25f, (float)(3.0f / scale), (float)(-(3.0f / scale)));
                GL11.glScalef((float)var5, (float)var5, (float)var5);
                GL11.glRotatef((float)60.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glRotatef((float)-90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)20.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glRotatef((float)-80.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            }
            this.renderManager.itemRenderer.renderItem(player, var21);
            GL11.glPopMatrix();
        }
    }
}

