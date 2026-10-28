/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.input.Keyboard
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client.gui.inventory;

import net.minecraft.client.RenderHelper;
import net.minecraft.client.gui.Screen;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.world.container.Container;
import net.minecraft.world.container.Slot;
import net.minecraft.world.entity.player.InventoryPlayer;
import net.minecraft.world.item.ItemStack;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public abstract class ContainerScreen
extends Screen {
    private static RenderItem itemRenderer = new RenderItem();
    protected int xSize = 176;
    protected int ySize = 166;
    public Container inventorySlots;

    public ContainerScreen(Container var1) {
        this.inventorySlots = var1;
    }

    @Override
    public void init() {
        super.init();
        this.mc.player.craftingInventory = this.inventorySlots;
    }

    @Override
    public void render(int var1, int var2, float var3) {
        this.drawBackground();
        int var4 = (this.width - this.xSize) / 2;
        int var5 = (this.height - this.ySize) / 2;
        this.drawGuiContainerBackgroundLayer(var3);
        GL11.glPushMatrix();
        GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        RenderHelper.enableStandardItemLighting();
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef((float)var4, (float)var5, (float)0.0f);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glEnable((int)32826);
        int var7 = 0;
        while (var7 < this.inventorySlots.slots.size()) {
            Slot var8 = this.inventorySlots.slots.get(var7);
            this.drawSlotInventory(var8);
            if (this.getIsMouseOverSlot(var8, var1, var2)) {
                GL11.glDisable((int)2896);
                GL11.glDisable((int)2929);
                int var9 = var8.xDisplayPosition;
                int var10 = var8.yDisplayPosition;
                ContainerScreen.drawGradientRect(var9, var10, var9 + 16, var10 + 16, -2130706433, -2130706433);
                GL11.glEnable((int)2896);
                GL11.glEnable((int)2929);
            }
            ++var7;
        }
        InventoryPlayer inv = this.mc.player.inventory;
        if (inv.getItemStack() != null) {
            GL11.glTranslatef((float)0.0f, (float)0.0f, (float)56.0f);
            itemRenderer.renderItemIntoGUI(this.mc.renderEngine, inv.getItemStack(), var1 - var4 - 8, var2 - var5 - 8);
            itemRenderer.renderItemOverlayIntoGUI(this.mc.font, this.mc.renderEngine, inv.getItemStack(), var1 - var4 - 8, var2 - var5 - 8);
        }
        GL11.glDisable((int)32826);
        RenderHelper.disableStandardItemLighting();
        GL11.glDisable((int)2896);
        GL11.glDisable((int)2929);
        this.drawGuiContainerForegroundLayer();
        GL11.glPopMatrix();
        super.render(var1, var2, var3);
        GL11.glEnable((int)2896);
        GL11.glEnable((int)2929);
    }

    protected void drawGuiContainerForegroundLayer() {
    }

    protected abstract void drawGuiContainerBackgroundLayer(float var1);

    private void drawSlotInventory(Slot var1) {
        int var5;
        int var2 = var1.xDisplayPosition;
        int var3 = var1.yDisplayPosition;
        ItemStack var4 = var1.getStack();
        if (var4 == null && (var5 = var1.getBackgroundIconIndex()) >= 0) {
            GL11.glDisable((int)2896);
            RenderEngine.bind(this.mc.renderEngine.getTexture("/gui/items.png"));
            this.drawTexturedModalRect(var2, var3, var5 % 16 * 16, var5 / 16 * 16, 16, 16);
            GL11.glEnable((int)2896);
            return;
        }
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)48.0f);
        itemRenderer.renderItemIntoGUI(this.mc.renderEngine, var4, var2, var3);
        itemRenderer.renderItemOverlayIntoGUI(this.mc.font, this.mc.renderEngine, var4, var2, var3);
        GL11.glPopMatrix();
    }

    private Slot getSlotAtPosition(int var1, int var2) {
        int var3 = 0;
        while (var3 < this.inventorySlots.slots.size()) {
            Slot var4 = this.inventorySlots.slots.get(var3);
            if (this.getIsMouseOverSlot(var4, var1, var2)) {
                return var4;
            }
            ++var3;
        }
        return null;
    }

    private boolean getIsMouseOverSlot(Slot var1, int var2, int var3) {
        int var4 = (this.width - this.xSize) / 2;
        int var5 = (this.height - this.ySize) / 2;
        return (var2 -= var4) >= var1.xDisplayPosition - 1 && var2 < var1.xDisplayPosition + 16 + 1 && (var3 -= var5) >= var1.yDisplayPosition - 1 && var3 < var1.yDisplayPosition + 16 + 1;
    }

    @Override
    public void mouseClicked(int var1, int var2, int var3) {
        super.mouseClicked(var1, var2, var3);
        if (var3 == 0 || var3 == 1) {
            Slot var4 = this.getSlotAtPosition(var1, var2);
            int var5 = (this.width - this.xSize) / 2;
            int var6 = (this.height - this.ySize) / 2;
            boolean var7 = var1 < var5 || var2 < var6 || var1 >= var5 + this.xSize || var2 >= var6 + this.ySize;
            int var8 = -1;
            if (var4 != null) {
                var8 = var4.slotNumber;
            }
            if (var7) {
                var8 = -999;
            }
            if (var8 != -1) {
                boolean shiftclick = var8 != -999 && (Keyboard.isKeyDown((int)42) || Keyboard.isKeyDown((int)54));
                shiftclick = false;
                this.handleMouseClick(var4, var8, var3, shiftclick);
            }
        }
    }

    protected void handleMouseClick(Slot slot, int par2, int par3, boolean par4) {
        if (slot != null) {
            par2 = slot.slotNumber;
        }
        this.mc.gamemode.handleInventoryMouseClick(this.inventorySlots.windowId, par2, par3, par4, this.mc.player);
    }

    @Override
    protected void mouseMovedOrUp(int var1, int var2, int var3) {
    }

    @Override
    protected void keyTyped(char var1, int var2) {
        if (var2 == 1 || var2 == this.mc.options.keyBindInventory.keyCode) {
            this.mc.player.closeScreen();
        }
    }

    @Override
    public void closeScreen() {
        if (this.mc.player != null) {
            this.mc.gamemode.onWindowClosed(this.inventorySlots.windowId, this.mc.player);
        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.mc.player.isEntityAlive() || this.mc.player.removed) {
            this.mc.player.closeScreen();
        }
    }
}

