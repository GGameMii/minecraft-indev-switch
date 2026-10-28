/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client.gui.inventory;

import net.minecraft.client.RenderHelper;
import net.minecraft.client.gui.inventory.ContainerScreen;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.world.entity.player.Player;
import org.lwjgl.opengl.GL11;

public final class InventoryScreen
extends ContainerScreen {
    private float xSize_lo;
    private float ySize_lo;

    public InventoryScreen(Player var1) {
        super(var1.inventorySlots);
        this.allowUserInput = true;
    }

    @Override
    protected final void drawGuiContainerForegroundLayer() {
        this.font.draw("Crafting", 86, 16, 0x404040);
    }

    @Override
    public final void render(int xSize_lo, int ySize_lo, float f3) {
        super.render(xSize_lo, ySize_lo, f3);
        this.xSize_lo = xSize_lo;
        this.ySize_lo = ySize_lo;
    }

    @Override
    protected final void drawGuiContainerBackgroundLayer(float a2) {
        int i1 = this.mc.renderEngine.getTexture("/gui/inventory.png");
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        RenderEngine.bind(i1);
        i1 = (this.width - this.xSize) / 2;
        int i2 = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i1, i2, 0, 0, this.xSize, this.ySize);
        GL11.glEnable((int)2977);
        GL11.glEnable((int)2903);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)(i1 + 51), (float)(i2 + 75), (float)50.0f);
        GL11.glScalef((float)-30.0f, (float)30.0f, (float)30.0f);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        float f3 = this.mc.player.renderYawOffset;
        float f4 = this.mc.player.xRot;
        float f5 = this.mc.player.yRot;
        float f6 = (float)(i1 + 51) - this.xSize_lo;
        float f7 = (float)(i2 + 75 - 50) - this.ySize_lo;
        GL11.glRotatef((float)135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        RenderHelper.enableStandardItemLighting();
        GL11.glRotatef((float)-135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)(-((float)Math.atan(f7 / 40.0f)) * 20.0f), (float)1.0f, (float)0.0f, (float)0.0f);
        this.mc.player.renderYawOffset = (float)Math.atan(f6 / 40.0f) * 20.0f;
        this.mc.player.xRot = (float)Math.atan(f6 / 40.0f) * 40.0f;
        this.mc.player.yRot = -((float)Math.atan(f7 / 40.0f)) * 20.0f;
        GL11.glTranslatef((float)0.0f, (float)this.mc.player.heightOffset, (float)0.0f);
        RenderManager.instance.renderEntityWithPosYaw(this.mc.player, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f);
        this.mc.player.renderYawOffset = f3;
        this.mc.player.xRot = f4;
        this.mc.player.yRot = f5;
        GL11.glPopMatrix();
        RenderHelper.disableStandardItemLighting();
        GL11.glDisable((int)2977);
    }
}

