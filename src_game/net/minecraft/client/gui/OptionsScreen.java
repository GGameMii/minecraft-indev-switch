/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.gui;

import net.minecraft.client.GameOptions;
import net.minecraft.client.gui.Button;
import net.minecraft.client.gui.ControlsScreen;
import net.minecraft.client.gui.Screen;
import net.minecraft.client.gui.Slider;
import net.minecraft.client.gui.SmallButton;
import net.minecraft.client.gui.VoiceChatScreen;

public final class OptionsScreen
extends Screen {
    private Screen parentScreen;
    private String screenTitle = "Options";
    private GameOptions options;
    private Button vcButton;
    private GuiTextBox nameBox;

    public OptionsScreen(Screen screen, GameOptions options) {
        this.parentScreen = screen;
        this.options = options;
    }

    @Override
    public void init() {
        int offs = -12;
        int i2 = 0;
        while (i2 < this.options.numberOfOptions) {
            this.buttons.add(new SmallButton(i2, this.width / 2 - 155 + i2 % 2 * 160, this.height / 6 + 24 * (i2 >> 1) + offs, this.options.setOptionString(i2)));
            ++i2;
        }
        i2 = 0;
        int ii = i2 + this.options.numberOfOptions;
        this.buttons.add(new Slider(i2, this.width / 2 - 155 + ii % 2 * 160, this.height / 6 + 24 * (ii >> 1) + offs, i2, this.options.sliderString(i2), this.options.sliders[i2].value));
        this.buttons.add(new Button(100, this.width / 2 - 155, this.height / 6 + 106, 150, 20, "Controls..."));
        this.vcButton = new Button(101, this.width / 2 + 5, this.height / 6 + 106, 150, 20, "Voice chat...");
        this.buttons.add(this.vcButton);

        this.nameBox = new GuiTextBox(this.font, this.options.username != null ? this.options.username : "Steve", this.width / 2 - 95, this.height / 6 + 132, 190, 20);
        this.nameBox.setMaxLength(16);

        this.buttons.add(new Button(200, this.width / 2 - 100, this.height / 6 + 168, "Done"));
        if (!this.mc.isOnlineClient() || this.mc.level == null) {
            this.vcButton.enabled = false;
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (this.nameBox != null) {
            this.nameBox.tick();
        }
    }

    @Override
    public void mouseClicked(int mx, int my, int btn) {
        if (this.nameBox != null) {
            this.nameBox.clicked(mx, my);
        }
        super.mouseClicked(mx, my, btn);
    }

    @Override
    protected void keyTyped(char ch, int key) {
        if (this.nameBox != null && this.nameBox.focused) {
            this.nameBox.keyPressed(ch, key);
            String newName = this.nameBox.text.trim();
            if (newName.length() > 0) {
                this.options.username = newName;
                if (this.mc != null && this.mc.user != null) {
                    this.mc.user.name = newName;
                }
            }
        }
        if (key == 1) {
            syncAndSave();
            this.mc.setScreen(this.parentScreen);
        }
    }

    private void syncAndSave() {
        if (this.nameBox != null) {
            String newName = this.nameBox.text.trim();
            if (newName.length() > 0) {
                this.options.username = newName;
                if (this.mc != null && this.mc.user != null) {
                    this.mc.user.name = newName;
                }
            }
        }
        this.mc.options.saveOptions();
    }

    @Override
    public void closeScreen() {
        syncAndSave();
        super.closeScreen();
    }

    @Override
    protected void buttonClicked(Button button) {
        if (button.enabled) {
            if (button.id < 100 && !(button instanceof Slider)) {
                this.options.setOptionValue(button.id, 1);
                button.displayString = this.options.setOptionString(button.id);
            }
            if (button.id == 100) {
                syncAndSave();
                this.mc.setScreen(new ControlsScreen(this, this.options));
            }
            if (button.id == 101) {
                syncAndSave();
                this.mc.setScreen(new VoiceChatScreen(this));
            }
            if (button.id == 200) {
                syncAndSave();
                this.mc.setScreen(this.parentScreen);
            }
        }
    }

    @Override
    public void render(int mx, int my, float a2) {
        this.drawBackground();
        OptionsScreen.drawCenteredString(this.font, this.screenTitle, this.width / 2, 12, 0xFFFFFF);
        this.font.draw("Player:", this.width / 2 - 145, this.height / 6 + 138, 0xFFFFFF);
        if (this.nameBox != null) {
            this.nameBox.draw(mx, my, a2);
        }
        super.render(mx, my, a2);
    }
}

