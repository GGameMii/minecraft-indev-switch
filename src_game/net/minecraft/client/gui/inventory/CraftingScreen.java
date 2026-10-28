/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client.gui.inventory;

import net.minecraft.client.gui.inventory.ContainerScreen;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.world.container.ContainerWorkbench;
import net.minecraft.world.entity.player.InventoryPlayer;
import net.minecraft.world.level.Level;
import org.lwjgl.opengl.GL11;

public final class CraftingScreen
extends ContainerScreen {
    public CraftingScreen(InventoryPlayer var1, Level var2, int var3, int var4, int var5) {
        super(new ContainerWorkbench(var1, var2, var3, var4, var5));
    }

    @Override
    public void closeScreen() {
        super.closeScreen();
        this.inventorySlots.onCraftGuiClosed(this.mc.player);
    }

    @Override
    protected final void drawGuiContainerForegroundLayer() {
        this.font.draw("Crafting", 28, 6, 0x404040);
        this.font.draw("Inventory", 8, this.ySize - 96 + 2, 0x404040);
    }

    @Override
    protected final void drawGuiContainerBackgroundLayer(float a2) {
        int tex = this.mc.renderEngine.getTexture("/gui/crafting.png");
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        RenderEngine.bind(tex);
        int x2 = (this.width - this.xSize) / 2;
        int y2 = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(x2, y2, 0, 0, this.xSize, this.ySize);
    }
}

