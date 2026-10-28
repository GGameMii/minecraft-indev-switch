/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.gui;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.Screen;
import util.SharedConstants;

public class GuiTextBox {
    public String text;
    private int counter = 0;
    private int width = 200;
    private int height = 20;
    private int x;
    private int y;
    private Font font;
    public boolean focused = true;
    private int maxLength = 32;

    public GuiTextBox(Font f2, String text, int x2, int y2, int w2, int h2) {
        this.text = text;
        this.x = x2;
        this.y = y2;
        this.width = w2;
        this.height = h2;
        this.font = f2;
    }

    public void tick() {
        ++this.counter;
    }

    public void setMaxLength(int i2) {
        this.maxLength = i2;
    }

    public void updatePosition(int x2, int y2) {
        this.x = x2;
        this.y = y2;
    }

    public void draw(int i1, int i2, float a2) {
        int x0 = this.x;
        int y0 = this.y;
        Gui.drawRect(x0 - 1, y0 - 1, x0 + this.width + 1, y0 + this.height + 1, -6250336);
        Gui.drawRect(x0, y0, x0 + this.width, y0 + this.height, -16777216);
        this.font.draw(String.valueOf(this.text) + (this.counter / 6 % 2 == 0 && this.focused ? "_" : ""), x0 + 4, y0 + 6, 0xE0E0E0);
    }

    public void clicked(int x0, int y0) {
        this.focused = x0 >= this.x && y0 >= this.y && x0 < this.x + this.width && y0 < this.y + this.height;
        if (this.focused) {
            net.minecraft.client.controller.SwitchKeyboard.open("Enter text", this.text, this.maxLength, new net.minecraft.client.controller.SwitchKeyboard.Callback() {
                @Override
                public void onTextEntered(String str) {
                    if (str != null) {
                        text = str;
                    }
                }
            });
        }
    }

    public void keyPressed(char c1, int i2) {
        if (!this.focused) {
            return;
        }
        if (i2 == 1) {
            this.focused = false;
        }
        if (c1 == '\u0016') {
            int i4;
            String string3 = Screen.getClipboardString();
            if (string3 == null) {
                string3 = "";
            }
            if ((i4 = 32 - this.text.length()) > string3.length()) {
                i4 = string3.length();
            }
            if (i4 > 0) {
                this.text = String.valueOf(this.text) + string3.substring(0, i4);
            }
        }
        if (i2 == 14 && this.text.length() > 0) {
            this.text = this.text.substring(0, this.text.length() - 1);
        }
        if (SharedConstants.charAllowed(c1) && this.text.length() < this.maxLength) {
            this.text = String.valueOf(this.text) + c1;
        }
    }
}

