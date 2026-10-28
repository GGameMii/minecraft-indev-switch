/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client.gui.inventory;

import net.minecraft.client.gui.inventory.ContainerScreen;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.world.IInventory;
import net.minecraft.world.container.ContainerChest;
import org.lwjgl.opengl.GL11;

public final class ChestScreen
extends ContainerScreen {
    private IInventory upperChestInventory;
    private IInventory lowerChestInventory;
    private int inventoryRows = 0;

    public ChestScreen(IInventory upperChestInventory, IInventory lowerChestInventory) {
        super(new ContainerChest(upperChestInventory, lowerChestInventory));
        this.upperChestInventory = upperChestInventory;
        this.lowerChestInventory = lowerChestInventory;
        this.allowUserInput = false;
        this.inventoryRows = lowerChestInventory.getSizeInventory() / 9;
        this.ySize = 114 + this.inventoryRows * 18;
    }

    @Override
    protected final void drawGuiContainerForegroundLayer() {
        this.font.draw(this.lowerChestInventory.getInvName(), 8, 6, 0x404040);
        this.font.draw(this.upperChestInventory.getInvName(), 8, this.ySize - 96 + 2, 0x404040);
    }

    @Override
    protected final void drawGuiContainerBackgroundLayer(float a2) {
        int tex = this.mc.renderEngine.getTexture("/gui/container.png");
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        RenderEngine.bind(tex);
        int x2 = (this.width - this.xSize) / 2;
        int y2 = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(x2, y2, 0, 0, this.xSize, this.inventoryRows * 18 + 17);
        this.drawTexturedModalRect(x2, y2 + this.inventoryRows * 18 + 17, 0, 126, this.xSize, 96);
    }
}

