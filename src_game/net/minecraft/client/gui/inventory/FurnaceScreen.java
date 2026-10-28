/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client.gui.inventory;

import net.minecraft.client.gui.inventory.ContainerScreen;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.world.container.ContainerFurnace;
import net.minecraft.world.entity.player.InventoryPlayer;
import net.minecraft.world.level.tile.tileentity.TileEntityFurnace;
import org.lwjgl.opengl.GL11;

public final class FurnaceScreen
extends ContainerScreen {
    private TileEntityFurnace furnaceInventory;

    public FurnaceScreen(InventoryPlayer playerInventory, TileEntityFurnace furnace) {
        super(new ContainerFurnace(playerInventory, furnace));
        this.furnaceInventory = furnace;
    }

    @Override
    protected final void drawGuiContainerForegroundLayer() {
        this.font.draw("Furnace", 60, 6, 0x404040);
        this.font.draw("Inventory", 8, this.ySize - 96 + 2, 0x404040);
    }

    @Override
    protected final void drawGuiContainerBackgroundLayer(float a2) {
        int texture = this.mc.renderEngine.getTexture("/gui/furnace.png");
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        RenderEngine.bind(texture);
        int x2 = (this.width - this.xSize) / 2;
        int y2 = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(x2, y2, 0, 0, this.xSize, this.ySize);
        if (this.furnaceInventory.isBurning()) {
            int time = this.furnaceInventory.getBurnTimeRemainingScaled(12);
            this.drawTexturedModalRect(x2 + 56, y2 + 36 + 12 - time, 176, 12 - time, 14, time + 2);
        }
        int progress = this.furnaceInventory.getCookProgressScaled(24);
        this.drawTexturedModalRect(x2 + 79, y2 + 34, 176, 14, progress + 1, 16);
    }
}

