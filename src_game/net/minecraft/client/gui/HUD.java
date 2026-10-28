/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.input.Keyboard
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.client.ChatLine;
import net.minecraft.client.ChatScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.RenderHelper;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.network.Client;
import net.minecraft.client.network.ClientNetworkPlayer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.world.entity.player.InventoryPlayer;
import net.minecraft.world.item.ItemStack;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public final class HUD
extends Gui {
    private static RenderItem itemRenderer = new RenderItem();
    private List<ChatLine> chatMessageList = new ArrayList<ChatLine>();
    private Random rand = new Random();
    private Minecraft mc;
    private int updateCounter = 0;
    public String hoveredUsername;

    public HUD(Minecraft minecraft) {
        this.mc = minecraft;
    }

    public final void renderGameOverlay(float f2, int n2, int n3) {
        int n4;
        int n5;
        int n6;
        boolean bl;
        ScaledResolution scaledResolution = new ScaledResolution(this.mc.width, this.mc.height);
        int n7 = scaledResolution.getScaledWidth();
        int n8 = scaledResolution.getScaledHeight();
        Font font = this.mc.font;
        this.mc.gameRenderer.setupOverlayRendering();
        GL11.glBindTexture((int)3553, (int)this.mc.renderEngine.getTexture("/gui/gui.png"));
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glEnable((int)3042);
        InventoryPlayer inventoryPlayer = this.mc.player.inventory;
        this.zLevel = -90.0f;
        this.drawTexturedModalRect(n7 / 2 - 91, n8 - 22, 0, 0, 182, 22);
        this.drawTexturedModalRect(n7 / 2 - 91 - 1 + inventoryPlayer.currentItem * 20, n8 - 22 - 1, 0, 22, 24, 22);
        GL11.glBindTexture((int)3553, (int)this.mc.renderEngine.getTexture("/gui/icons.png"));
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)775, (int)769);
        this.drawTexturedModalRect(n7 / 2 - 7, n8 / 2 - 7, 0, 0, 16, 16);
        GL11.glDisable((int)3042);
        boolean bl2 = bl = this.mc.player.invulnerableTime / 3 % 2 == 1;
        if (this.mc.player.invulnerableTime < 10) {
            bl = false;
        }
        int n9 = this.mc.player.health;
        int n10 = this.mc.player.prevHealth;
        this.rand.setSeed(this.updateCounter * 312871);
        if (this.mc.gamemode.shouldDrawHUD()) {
            int n11;
            n6 = this.mc.player.inventory.getPlayerArmorValue();
            for (n5 = 0; n5 < 10; ++n5) {
                n11 = n8 - 32;
                if (n6 > 0) {
                    n4 = n7 / 2 + 91 - (n5 << 3) - 9;
                    if ((n5 << 1) + 1 < n6) {
                        this.drawTexturedModalRect(n4, n11, 34, 9, 9, 9);
                    }
                    if ((n5 << 1) + 1 == n6) {
                        this.drawTexturedModalRect(n4, n11, 25, 9, 9, 9);
                    }
                    if ((n5 << 1) + 1 > n6) {
                        this.drawTexturedModalRect(n4, n11, 16, 9, 9, 9);
                    }
                }
                int n12 = 0;
                if (bl) {
                    n12 = 1;
                }
                int n13 = n7 / 2 - 91 + (n5 << 3);
                if (n9 <= 4) {
                    n11 += this.rand.nextInt(2);
                }
                this.drawTexturedModalRect(n13, n11, 16 + n12 * 9, 0, 9, 9);
                if (bl) {
                    if ((n5 << 1) + 1 < n10) {
                        this.drawTexturedModalRect(n13, n11, 70, 0, 9, 9);
                    }
                    if ((n5 << 1) + 1 == n10) {
                        this.drawTexturedModalRect(n13, n11, 79, 0, 9, 9);
                    }
                }
                if ((n5 << 1) + 1 < n9) {
                    this.drawTexturedModalRect(n13, n11, 52, 0, 9, 9);
                }
                if ((n5 << 1) + 1 != n9) continue;
                this.drawTexturedModalRect(n13, n11, 61, 0, 9, 9);
            }
            if (this.mc.player.isInsideOfWater()) {
                n5 = (int)Math.ceil((double)(this.mc.player.air - 2) * 10.0 / 300.0);
                n11 = (int)Math.ceil((double)this.mc.player.air * 10.0 / 300.0) - n5;
                for (n4 = 0; n4 < n5 + n11; ++n4) {
                    if (n4 < n5) {
                        this.drawTexturedModalRect(n7 / 2 - 91 + (n4 << 3), n8 - 32 - 9, 16, 18, 9, 9);
                        continue;
                    }
                    this.drawTexturedModalRect(n7 / 2 - 91 + (n4 << 3), n8 - 32 - 9, 25, 18, 9, 9);
                }
            }
        }
        GL11.glDisable((int)3042);
        GL11.glEnable((int)2977);
        GL11.glPushMatrix();
        GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        RenderHelper.enableStandardItemLighting();
        GL11.glPopMatrix();
        for (n6 = 0; n6 < 9; ++n6) {
            n5 = n7 / 2 - 90 + n6 * 20 + 2;
            n4 = n8 - 16 - 3;
            ItemStack itemStack = this.mc.player.inventory.mainInventory[n6];
            if (itemStack == null) continue;
            float f4 = (float)itemStack.animationsToGo - f2;
            if (f4 > 0.0f) {
                GL11.glPushMatrix();
                float f5 = 1.0f + f4 / 5.0f;
                GL11.glTranslatef((float)(n5 + 8), (float)(n4 + 12), (float)0.0f);
                GL11.glScalef((float)(1.0f / f5), (float)((f5 + 1.0f) / 2.0f), (float)1.0f);
                GL11.glTranslatef((float)(-(n5 + 8)), (float)(-(n4 + 12)), (float)0.0f);
            }
            itemRenderer.renderItemIntoGUI(this.mc.renderEngine, itemStack, n5, n4);
            if (f4 > 0.0f) {
                GL11.glPopMatrix();
            }
            itemRenderer.renderItemOverlayIntoGUI(this.mc.font, this.mc.renderEngine, itemStack, n5, n4);
        }
        RenderHelper.disableStandardItemLighting();
        GL11.glDisable((int)2977);
        if (this.mc.options.showFPS) {
            font.drawShadow("Minecraft Indev (" + this.mc.debug + ")", 2, 2, 0xFFFFFF);
            Minecraft minecraft = this.mc;
            font.drawShadow(this.mc.levelRenderer.getDebugInfoRenders(), 2, 12, 0xFFFFFF);
            minecraft = this.mc;
            font.drawShadow(this.mc.levelRenderer.getDebugInfoEntities(), 2, 22, 0xFFFFFF);
            minecraft = this.mc;
            font.drawShadow("P: " + minecraft.particles.getStatistics() + ". T: " + minecraft.level.debugSkylightUpdates(), 2, 32, 0xFFFFFF);
            long l2 = Runtime.getRuntime().maxMemory();
            long l3 = Runtime.getRuntime().totalMemory();
            long l4 = Runtime.getRuntime().freeMemory();
            long l5 = l2 - l4;
            String string = "Free memory: " + l5 * 100L / l2 + "% of " + l2 / 1024L / 1024L + "MB";
            HUD.drawString(font, string, n7 - font.width(string) - 2, 2, 0xE0E0E0);
            string = "Allocated memory: " + l3 * 100L / l2 + "% (" + l3 / 1024L / 1024L + "MB)";
            HUD.drawString(font, string, n7 - font.width(string) - 2, 12, 0xE0E0E0);
            boolean bl3 = false;
            if (bl3) {
                font.drawShadow("x: " + this.mc.player.x, 2, 42, 0xFFFFFF);
                font.drawShadow("y: " + this.mc.player.y, 2, 52, 0xFFFFFF);
                font.drawShadow("z: " + this.mc.player.z, 2, 62, 0xFFFFFF);
                font.drawShadow("UT: " + this.mc.player.userType, 2, 72, 0xFFFFFF);
            }
        } else {
            font.drawShadow("Minecraft Indev", 2, 2, 0xFFFFFF);
        }
        boolean bl4 = false;
        int n14 = 10;
        if (this.mc.screen instanceof ChatScreen) {
            n14 = 20;
            bl4 = true;
        }
        for (int i2 = 0; i2 < this.chatMessageList.size() && i2 < n14; ++i2) {
            if (this.chatMessageList.get((int)i2).updateCounter >= 200 && !bl4) continue;
            ChatLine chatLine = this.chatMessageList.get(i2);
            font.drawShadow(chatLine.message, 2, n8 - 8 - i2 * 9 - 20, 0xFFFFFF);
        }
        this.hoveredUsername = null;
        if (this.mc.isOnlineClient() && Keyboard.isKeyDown((int)15)) {
            Client client = ((ClientNetworkPlayer)this.mc.player).getClientConnection();
            List<String> list = client.getUsernames();
            Gui.drawGradientRect((n7 /= 2) - 128, (n8 /= 2) - 68 - 12, n7 + 128, n8 + 68, -872415232, -582728636);
            String string = "Connected players:";
            this.mc.font.drawShadow(string, n7 - this.mc.font.width(string) / 2, n8 - 64 - 12, 0xFFFFFF);
            for (int i3 = 0; i3 < list.size(); ++i3) {
                int n15 = n7 + i3 % 2 * 120 - 120;
                int n16 = n8 - 64 + (i3 / 2 << 3);
                if (this.mc.screen != null && n2 >= n15 && n3 >= n16 && n2 < n15 + 120 && n3 < n16 + 8) {
                    this.hoveredUsername = list.get(i3);
                    this.mc.font.draw(list.get(i3), n15 + 2, n16, 0xFFFFFF);
                    continue;
                }
                this.mc.font.draw(list.get(i3), n15, n16, 0xEEEEEE);
            }
            string = this.mc.level.name;
            this.mc.font.drawShadow(string, n7 - this.mc.font.width(string) / 2, n8 - 64 - 32 - 8, 0xFFFFFF);
            string = "Map by " + this.mc.level.authorName;
            this.mc.font.drawShadow(string, n7 - this.mc.font.width(string) / 2, n8 - 64 - 24 - 8, 0xFFFFFF);
        }
    }

    public final void updateChat() {
        ++this.updateCounter;
        for (int i2 = 0; i2 < this.chatMessageList.size(); ++i2) {
            ++this.chatMessageList.get((int)i2).updateCounter;
        }
    }

    public void addChatMessage(String string) {
        while (this.mc.font.width(string) > 320) {
            int n2;
            for (n2 = 1; n2 < string.length() && this.mc.font.width(string.substring(0, n2 + 1)) <= 320; ++n2) {
            }
            this.addChatMessage(string.substring(0, n2));
            string = string.substring(n2);
        }
        System.out.println(string);
        this.chatMessageList.add(0, new ChatLine(string));
        while (this.chatMessageList.size() > 50) {
            this.chatMessageList.remove(this.chatMessageList.size() - 1);
        }
    }
}

