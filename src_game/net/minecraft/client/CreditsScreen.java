/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client;

import net.minecraft.client.RenderHelper;
import net.minecraft.client.gui.Button;
import net.minecraft.client.gui.Screen;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.level.tile.Tile;
import org.lwjgl.opengl.GL11;

public class CreditsScreen
extends Screen {
    private Screen parent;
    private RenderBlocks tile = new RenderBlocks();
    private int ticks;

    public CreditsScreen(Screen par) {
        this.parent = par;
    }

    @Override
    public void tick() {
        ++this.ticks;
    }

    @Override
    public void init() {
        this.buttons.add(new Button(0, this.width / 2 - 100, this.height - 48, "Back"));
    }

    @Override
    public void buttonClicked(Button b2) {
        if (b2.id == 0) {
            this.mc.setScreen(this.parent);
        }
    }

    @Override
    public void render(int mx, int my, float a2) {
        this.drawBackground();
        CreditsScreen.drawCenteredString(this.font, "Credits", this.width / 2, 20, 0xFFFFFF);
        CreditsScreen.drawString(this.font, "GenericPnpMonitor - Base for networking", this.width / 2 - 100, 42, 0xFFFFFF);
        CreditsScreen.drawString(this.font, "SithGames members - Betatesting and feedback", this.width / 2 - 100, 52, 0xFFFFFF);
        CreditsScreen.drawString(this.font, "MrLordSith - Everything else (Networking", this.width / 2 - 100, 72, 0xFFFFFF);
        CreditsScreen.drawString(this.font, "implementation into Indev)", this.width / 2 - 100, 82, 0xFFFFFF);
        CreditsScreen.drawString(this.font, "v1.3.6", this.width / 2 - 100, this.height - 64, 0xFFFFFF);
        GL11.glPushMatrix();
        GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        RenderHelper.enableStandardItemLighting();
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glBindTexture((int)3553, (int)this.mc.renderEngine.getTexture("/terrain.png"));
        GL11.glTranslatef((float)((float)this.width / 2.0f), (float)((float)this.height / 2.0f + 24.0f), (float)32.0f);
        GL11.glRotatef((float)16.0f, (float)-1.0f, (float)0.0f, (float)0.0f);
        float s2 = 32.0f;
        GL11.glScalef((float)s2, (float)(-s2), (float)s2);
        GL11.glRotatef((float)(-this.ticks), (float)0.0f, (float)1.0f, (float)0.0f);
        this.tile.renderBlockOnInventory(Tile.chest);
        RenderHelper.disableStandardItemLighting();
        GL11.glPopMatrix();
        super.render(mx, my, a2);
    }
}

