/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.gui;

import net.minecraft.client.gui.Button;
import net.minecraft.client.gui.Screen;

public final class NewLevelScreen
extends Screen {
    private Screen parent;
    private String[] worldType = new String[]{"Inland", "Island", "Floating", "Flat"};
    private String[] worldShape = new String[]{"Square", "Long", "Deep"};
    private String[] worldSize = new String[]{"Small", "Normal", "Huge"};
    private String[] worldTheme = new String[]{"Normal", "Hell", "Paradise", "Woods"};
    private int type = 1;
    private int shape = 0;
    private int size = 1;
    private int theme = 0;
    private int gamemode = 0;

    public NewLevelScreen(Screen par) {
        this.parent = par;
    }

    @Override
    public final void init() {
        this.buttons.clear();
        this.buttons.add(new Button(0, this.width / 2 - 100, this.height / 4 - 20, "Type: "));
        this.buttons.add(new Button(1, this.width / 2 - 100, this.height / 4 - 20 + 24, "Shape:"));
        this.buttons.add(new Button(2, this.width / 2 - 100, this.height / 4 - 20 + 48, "Size: "));
        this.buttons.add(new Button(3, this.width / 2 - 100, this.height / 4 - 20 + 72, "Theme: "));
        this.buttons.add(new Button(4, this.width / 2 - 100, this.height / 4 + 96 + 12, "Create"));
        this.buttons.add(new Button(5, this.width / 2 - 100, this.height / 4 + 120 + 12, "Cancel"));
        this.buttons.add(new Button(6, this.width / 2 - 100, this.height / 4 - 20 + 96, "Gamemode: "));
        this.worldOptions();
    }

    private void worldOptions() {
        ((Button)this.buttons.get((int)0)).displayString = "Type: " + this.worldType[this.type];
        ((Button)this.buttons.get((int)1)).displayString = "Shape: " + this.worldShape[this.shape];
        ((Button)this.buttons.get((int)2)).displayString = "Size: " + this.worldSize[this.size];
        ((Button)this.buttons.get((int)3)).displayString = "Theme: " + this.worldTheme[this.theme];
        ((Button)this.buttons.get((int)6)).displayString = "Gamemode: " + (this.gamemode == 0 ? "Survival" : "Creative");
    }

    @Override
    protected final void buttonClicked(Button button) {
        if (button.id == 5) {
            this.mc.setScreen(this.parent);
        } else if (button.id == 6) {
            ++this.gamemode;
            if (this.gamemode > 1) {
                this.gamemode = 0;
            }
        } else if (button.id == 4) {
            this.mc.generateLevel(this.size, this.shape, this.type, this.theme, this.gamemode == 0);
            this.mc.setScreen(null);
        } else if (button.id == 0) {
            this.type = (this.type + 1) % this.worldType.length;
        } else if (button.id == 1) {
            this.shape = (this.shape + 1) % this.worldShape.length;
        } else if (button.id == 2) {
            this.size = (this.size + 1) % this.worldSize.length;
        } else if (button.id == 3) {
            this.theme = (this.theme + 1) % this.worldTheme.length;
        }
        this.worldOptions();
    }

    @Override
    public final void render(int mx, int my, float a2) {
        this.drawBackground();
        NewLevelScreen.drawCenteredString(this.font, "Generate new level", this.width / 2, 20, 0xFFFFFF);
        super.render(mx, my, a2);
    }
}

