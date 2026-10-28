/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client.gui.inventory;

import net.minecraft.client.gui.inventory.ContainerCreative;
import net.minecraft.client.gui.inventory.ContainerScreen;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.world.container.InventoryBasic;
import net.minecraft.world.container.Slot;
import net.minecraft.world.entity.player.InventoryPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class BuildScreen
extends ContainerScreen {
    private static InventoryBasic inventory = new InventoryBasic("tmp", 72);
    private float currentScroll = 0.0f;
    private boolean isScrolling;
    private boolean wasClicking;

    public BuildScreen(Player player) {
        super(new ContainerCreative(player));
        this.ySize = 208;
    }

    @Override
    public void init() {
        this.buttons.clear();
    }

    @Override
    protected void drawGuiContainerForegroundLayer() {
        BuildScreen.drawCenteredString(this.mc.font, "Build", 86, 6, 0xFFFFFF);
    }

    @Override
    public void render(int mx, int my, float a2) {
        boolean flag = Mouse.isButtonDown((int)0);
        int x0 = (this.width - this.xSize) / 2;
        int y0 = (this.height - this.ySize) / 2;
        int x1 = x0 + 155;
        int y1 = y0 + 17;
        int i1 = x1 + 14;
        int j1 = y1 + 160 + 2;
        if (!this.wasClicking && flag && mx >= x1 && my >= y1 && mx < i1 && my < j1) {
            this.isScrolling = true;
        }
        if (!flag) {
            this.isScrolling = false;
        }
        this.wasClicking = flag;
        if (this.isScrolling) {
            this.currentScroll = (float)(my - (y1 + 8)) / ((float)(j1 - y1) - 16.0f);
            if (this.currentScroll < 0.0f) {
                this.currentScroll = 0.0f;
            }
            if (this.currentScroll > 1.0f) {
                this.currentScroll = 1.0f;
            }
            ((ContainerCreative)this.inventorySlots).scrollTo(this.currentScroll);
        }
        super.render(mx, my, a2);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glDisable((int)2896);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float a2) {
        int tex = this.mc.renderEngine.getTexture("/gui/allitems.png");
        RenderEngine.bind(tex);
        int x2 = (this.width - this.xSize) / 2;
        int y2 = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(x2, y2, 0, 0, this.xSize, this.ySize);
        int i8 = y2 + 17;
        int i9 = i8 + 160 + 2;
        this.drawTexturedModalRect(x2 + 154, y2 + 17 + (int)((float)(i9 - i8 - 17) * this.currentScroll), 0, 208, 16, 16);
    }

    @Override
    protected void handleMouseClick(Slot slot, int mx, int my, boolean max) {
        if (slot != null) {
            if (slot.inventory == inventory) {
                InventoryPlayer inventoryplayer = this.mc.player.inventory;
                ItemStack itemstack1 = inventoryplayer.getItemStack();
                ItemStack itemstack4 = slot.getStack();
                if (itemstack1 != null && itemstack4 != null && itemstack1.itemID == itemstack4.itemID) {
                    if (my == 0) {
                        if (max) {
                            itemstack1.stackSize = itemstack1.getMaxStackSize();
                        } else if (itemstack1.stackSize < itemstack1.getMaxStackSize()) {
                            ++itemstack1.stackSize;
                        }
                    } else if (itemstack1.stackSize <= 1) {
                        inventoryplayer.setItemStack(null);
                    } else {
                        --itemstack1.stackSize;
                    }
                } else if (itemstack1 != null) {
                    inventoryplayer.setItemStack(null);
                } else if (itemstack4 == null) {
                    inventoryplayer.setItemStack(null);
                } else if (itemstack1 == null || itemstack1.itemID != itemstack4.itemID) {
                    inventoryplayer.setItemStack(ItemStack.copyItemStack(itemstack4));
                    ItemStack itemstack2 = inventoryplayer.getItemStack();
                    if (max) {
                        itemstack2.stackSize = itemstack2.getMaxStackSize();
                    }
                }
            } else {
                this.inventorySlots.slotClick(slot.slotNumber, my, max, this.mc.player);
                ItemStack itemstack = this.inventorySlots.getSlot(slot.slotNumber).getStack();
                this.mc.gamemode.sendSlotPacket(itemstack, slot.slotNumber - this.inventorySlots.slots.size() + 9 + 36);
            }
        } else {
            InventoryPlayer inventory = this.mc.player.inventory;
            if (inventory.getItemStack() != null) {
                if (my == 0) {
                    this.mc.player.dropPlayerItem(inventory.getItemStack());
                    this.mc.gamemode.dropCreativeSlot(inventory.getItemStack());
                    inventory.setItemStack(null);
                }
                if (my == 1) {
                    ItemStack splitItem = inventory.getItemStack().splitStack(1);
                    this.mc.player.dropPlayerItem(splitItem);
                    this.mc.gamemode.dropCreativeSlot(splitItem);
                    if (inventory.getItemStack().stackSize == 0) {
                        inventory.setItemStack(null);
                    }
                }
            }
        }
    }

    @Override
    public void handleMouseInput() {
        super.handleMouseInput();
        int i2 = Mouse.getEventDWheel();
        if (i2 != 0) {
            int j2 = ((ContainerCreative)this.inventorySlots).creativeItems.size() / 8 - 8 + 1;
            if (i2 > 0) {
                i2 = 1;
            }
            if (i2 < 0) {
                i2 = -1;
            }
            this.currentScroll = (float)((double)this.currentScroll - (double)i2 / (double)j2);
            if (this.currentScroll < 0.0f) {
                this.currentScroll = 0.0f;
            }
            if (this.currentScroll > 1.0f) {
                this.currentScroll = 1.0f;
            }
            ((ContainerCreative)this.inventorySlots).scrollTo(this.currentScroll);
        }
    }

    public static InventoryBasic getInventory() {
        return inventory;
    }
}

