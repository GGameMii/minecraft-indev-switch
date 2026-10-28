/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.gui;

import net.minecraft.client.TitleScreen;
import net.minecraft.client.gui.Button;
import net.minecraft.client.gui.LoadLevelScreen;
import net.minecraft.client.gui.OptionsScreen;
import net.minecraft.client.gui.SaveLevelScreen;
import net.minecraft.client.gui.Screen;

public final class PauseScreen
extends Screen {
    @Override
    public final void init() {
        this.buttons.clear();
        this.buttons.add(new Button(0, this.width / 2 - 100, this.height / 4, "Options..."));
        this.buttons.add(new Button(1, this.width / 2 - 100, this.height / 4 + 24, "Quit game"));
        this.buttons.add(new Button(2, this.width / 2 - 100, this.height / 4 + 48, "Save level.."));
        this.buttons.add(new Button(3, this.width / 2 - 100, this.height / 4 + 72, "Load level.."));
        this.buttons.add(new Button(4, this.width / 2 - 100, this.height / 4 + 120, "Back to game"));
        if (this.mc.user == null || this.mc.networkClient != null) {
            ((Button)this.buttons.get((int)2)).enabled = false;
            ((Button)this.buttons.get((int)3)).enabled = false;
        }
        if (this.mc.networkClient != null) {
            ((Button)this.buttons.get((int)1)).displayString = "Disconnect";
        }
    }

    @Override
    protected final void buttonClicked(Button guiButton1) {
        if (guiButton1.id == 0) {
            this.mc.setScreen(new OptionsScreen(this, this.mc.options));
        }
        if (guiButton1.id == 1) {
            if (this.mc.networkClient != null) {
                this.mc.networkClient.closeConnection();
                this.mc.setLevel(null);
                this.mc.setScreen(new TitleScreen());
                this.mc.networkClient = null;
            } else {
                this.mc.setLevel(null);
                this.mc.setScreen(null);
            }
        }
        if (this.mc.user != null) {
            if (guiButton1.id == 2) {
                this.mc.setScreen(new SaveLevelScreen(this));
            }
            if (guiButton1.id == 3) {
                this.mc.setScreen(new LoadLevelScreen(this));
            }
        }
        if (guiButton1.id == 4) {
            this.mc.setScreen(null);
            this.mc.grabMouse();
        }
    }

    @Override
    public final void render(int xSize_lo, int ySize_lo, float f3) {
        this.drawBackground();
        PauseScreen.drawCenteredString(this.font, "Game menu", this.width / 2, 40, 0xFFFFFF);
        super.render(xSize_lo, ySize_lo, f3);
    }
}

