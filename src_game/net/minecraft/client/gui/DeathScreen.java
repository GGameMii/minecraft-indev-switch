/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client.gui;

import net.minecraft.client.TitleScreen;
import net.minecraft.client.gui.Button;
import net.minecraft.client.gui.LoadLevelScreen;
import net.minecraft.client.gui.NewLevelScreen;
import net.minecraft.client.gui.OptionsScreen;
import net.minecraft.client.gui.Screen;
import net.minecraft.network.packet.RevivePacket;
import org.lwjgl.opengl.GL11;

public final class DeathScreen
extends Screen {
    public boolean hardcore = false;

    @Override
    public final void init() {
        this.buttons.clear();
        this.buttons.add(new Button(1, this.width / 2 - 100, this.height / 4 + 72, "Generate new level..."));
        this.buttons.add(new Button(2, this.width / 2 - 100, this.height / 4 + 96, "Load level.."));
        if (this.mc.isOnlineClient()) {
            ((Button)this.buttons.get((int)1)).displayString = "Disconnect";
            ((Button)this.buttons.get((int)0)).displayString = "Revive";
        } else if (this.mc.user == null) {
            ((Button)this.buttons.get((int)1)).enabled = false;
        }
    }

    @Override
    protected final void keyTyped(char c1, int i2) {
    }

    @Override
    protected final void buttonClicked(Button button) {
        if (button.id == 0) {
            this.mc.setScreen(new OptionsScreen(this, this.mc.options));
        }
        if (button.id == 1) {
            if (this.mc.isOnlineClient()) {
                this.mc.networkClient.addToQueue(new RevivePacket(false, -1));
            } else {
                this.mc.setScreen(new NewLevelScreen(this));
            }
        }
        if (this.mc.user != null && button.id == 2) {
            if (this.mc.isOnlineClient()) {
                this.mc.networkClient.closeConnection();
                this.mc.setLevel(null);
                this.mc.setScreen(new TitleScreen());
                this.mc.networkClient = null;
            } else {
                this.mc.setScreen(new LoadLevelScreen(this));
            }
        }
    }

    @Override
    public final void render(int mx, int my, float a2) {
        DeathScreen.drawGradientRect(0, 0, this.width, this.height, 0x60500000, -1602211792);
        GL11.glPushMatrix();
        GL11.glScalef((float)2.0f, (float)2.0f, (float)2.0f);
        DeathScreen.drawCenteredString(this.font, "Game over!", this.width / 2 / 2, 30, 0xFFFFFF);
        GL11.glPopMatrix();
        StringBuilder msg = new StringBuilder().append("Score: &e");
        DeathScreen.drawCenteredString(this.font, msg.append(this.mc.player.score).toString(), this.width / 2, 100, 0xFFFFFF);
        if (this.hardcore) {
            ((Button)this.buttons.get((int)1)).enabled = false;
            DeathScreen.drawCenteredString(this.font, "You can't revive on this server!", this.width / 2, 112, 0xFFFFFF);
        }
        super.render(mx, my, a2);
    }

    @Override
    public final boolean doesGuiPauseGame() {
        return false;
    }
}

