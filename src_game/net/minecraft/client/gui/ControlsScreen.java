/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.gui;

import net.minecraft.client.GameOptions;
import net.minecraft.client.gui.Button;
import net.minecraft.client.gui.Screen;
import net.minecraft.client.gui.SmallButton;

public final class ControlsScreen
extends Screen {
    private Screen parentScreen;
    private String screenTitle = "Controls";
    private GameOptions options;
    private int buttonId = -1;

    public ControlsScreen(Screen guiScreen1, GameOptions gameSettings2) {
        this.parentScreen = guiScreen1;
        this.options = gameSettings2;
    }

    @Override
    public final void init() {
        int i1 = 0;
        while (i1 < this.options.keyBindings.length) {
            this.buttons.add(new SmallButton(i1, this.width / 2 - 155 + i1 % 2 * 160, this.height / 6 + 24 * (i1 >> 1), this.options.setKeyBindingString(i1)));
            ++i1;
        }
        this.buttons.add(new Button(200, this.width / 2 - 100, this.height / 6 + 168, "Done"));
    }

    @Override
    protected final void buttonClicked(Button guiButton1) {
        int i2 = 0;
        while (i2 < this.options.keyBindings.length) {
            ((Button)this.buttons.get((int)i2)).displayString = this.options.setKeyBindingString(i2);
            ++i2;
        }
        if (guiButton1.id == 200) {
            this.mc.setScreen(this.parentScreen);
        } else {
            this.buttonId = guiButton1.id;
            guiButton1.displayString = "> " + this.options.setKeyBindingString(guiButton1.id) + " <";
        }
    }

    @Override
    protected final void keyTyped(char c1, int i2) {
        if (this.buttonId >= 0) {
            this.options.setKeyBinding(this.buttonId, i2);
            ((Button)this.buttons.get((int)this.buttonId)).displayString = this.options.setKeyBindingString(this.buttonId);
            this.buttonId = -1;
        } else {
            super.keyTyped(c1, i2);
        }
    }

    @Override
    public final void render(int xSize_lo, int ySize_lo, float f3) {
        this.drawBackground();
        ControlsScreen.drawCenteredString(this.font, this.screenTitle, this.width / 2, 20, 0xFFFFFF);
        super.render(xSize_lo, ySize_lo, f3);
    }
}

