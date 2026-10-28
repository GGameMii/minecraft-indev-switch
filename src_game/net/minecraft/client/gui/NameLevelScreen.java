/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.input.Keyboard
 */
package net.minecraft.client.gui;

import net.minecraft.client.gui.Button;
import net.minecraft.client.gui.Screen;
import org.lwjgl.input.Keyboard;

public final class NameLevelScreen
extends Screen {
    private Screen parent;
    private String title = "Enter level name:";
    private int id;
    private String name;
    private int counter = 0;

    public NameLevelScreen(Screen parent, String name, int id) {
        this.parent = parent;
        this.id = id;
        this.name = name;
        if (this.name.equals("-")) {
            this.name = "";
        }
    }

    @Override
    public final void init() {
        this.buttons.clear();
        Keyboard.enableRepeatEvents((boolean)true);
        this.buttons.add(new Button(0, this.width / 2 - 100, this.height / 4 + 120, "Save"));
        this.buttons.add(new Button(1, this.width / 2 - 100, this.height / 4 + 144, "Cancel"));
        ((Button)this.buttons.get((int)0)).enabled = this.name.trim().length() > 1;
    }

    @Override
    public final void closeScreen() {
        Keyboard.enableRepeatEvents((boolean)false);
    }

    @Override
    public final void tick() {
        ++this.counter;
    }

    @Override
    protected final void buttonClicked(Button button) {
        if (button.enabled) {
            if (button.id == 0 && this.name.trim().length() > 1) {
                this.mc.saveLevel(this.id, this.name.trim());
                this.mc.setScreen(null);
                this.mc.grabMouse();
            }
            if (button.id == 1) {
                this.mc.setScreen(this.parent);
            }
        }
    }

    @Override
    protected final void keyTyped(char c1, int i2) {
        if (i2 == 14 && this.name.length() > 0) {
            this.name = this.name.substring(0, this.name.length() - 1);
        }
        if ("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 ,.:-_'*!\"#%/()=+?[]{}<>".indexOf(c1) >= 0 && this.name.length() < 64) {
            this.name = String.valueOf(this.name) + c1;
        }
        ((Button)this.buttons.get((int)0)).enabled = this.name.trim().length() > 1;
    }

    @Override
    protected final void mouseClicked(int mx, int my, int button) {
        super.mouseClicked(mx, my, button);
        int i4 = this.width / 2 - 100;
        int i5 = this.height / 2 - 10;
        if (mx >= i4 && mx < i4 + 200 && my >= i5 && my < i5 + 20) {
            net.minecraft.client.controller.SwitchKeyboard.open("Enter level name", this.name, 64, new net.minecraft.client.controller.SwitchKeyboard.Callback() {
                @Override
                public void onTextEntered(String str) {
                    if (str != null) {
                        name = str;
                        if (buttons.size() > 0) {
                            ((Button)buttons.get(0)).enabled = name.trim().length() > 1;
                        }
                    }
                }
            });
        }
    }

    @Override
    public final void render(int xSize_lo, int ySize_lo, float f3) {
        this.drawBackground();
        NameLevelScreen.drawCenteredString(this.font, this.title, this.width / 2, 40, 0xFFFFFF);
        int i4 = this.width / 2 - 100;
        int i5 = this.height / 2 - 10;
        NameLevelScreen.drawRect(i4 - 1, i5 - 1, i4 + 200 + 1, i5 + 20 + 1, -6250336);
        NameLevelScreen.drawRect(i4, i5, i4 + 200, i5 + 20, -16777216);
        NameLevelScreen.drawString(this.font, String.valueOf(this.name) + (this.counter / 6 % 2 == 0 ? "_" : ""), i4 + 4, i5 + 6, 0xE0E0E0);
        super.render(xSize_lo, ySize_lo, f3);
    }
}

