/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.input.Keyboard
 */
package net.minecraft.client;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.Screen;
import org.lwjgl.input.Keyboard;

public class ChatScreen
extends Screen {
    protected String message = "";
    private int updateCounter = 0;
    private static final String field_20082_i = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 ,.:-_'*!\"#%/()=+?[]{}<>";

    @Override
    public void init() {
        Keyboard.enableRepeatEvents(true);
        net.minecraft.client.controller.SwitchKeyboard.open("Chat Message", this.message, 100, new net.minecraft.client.controller.SwitchKeyboard.Callback() {
            @Override
            public void onTextEntered(String str) {
                if (str != null && str.trim().length() > 0) {
                    message = str.trim();
                    if (mc != null && mc.player != null) {
                        mc.player.addChatMessage(message);
                        mc.setScreen(null);
                    }
                }
            }
        });
    }

    @Override
    public void closeScreen() {
        Keyboard.enableRepeatEvents((boolean)false);
    }

    @Override
    public void tick() {
        ++this.updateCounter;
    }

    @Override
    protected void keyTyped(char c1, int i2) {
        if (i2 == 1) {
            this.mc.setScreen(null);
        } else if (i2 == 28) {
            String fullMsg = this.message.trim();
            if (fullMsg.length() > 0) {
                String msg = this.message.trim();
                this.mc.player.addChatMessage(msg);
            }
            this.mc.setScreen(null);
        } else {
            if (i2 == 14 && this.message.length() > 0) {
                this.message = this.message.substring(0, this.message.length() - 1);
            }
            if (field_20082_i.indexOf(c1) >= 0 && this.message.length() < 100) {
                this.message = String.valueOf(this.message) + c1;
            }
        }
    }

    @Override
    public void render(int mx, int my, float a2) {
        Gui.drawRect(2, this.height - 14, this.width - 2, this.height - 2, Integer.MIN_VALUE);
        ChatScreen.drawString(this.mc.font, "> " + this.message + (this.updateCounter / 6 % 2 == 0 ? "_" : ""), 4, this.height - 12, 0xE0E0E0);
        super.render(mx, my, a2);
    }

    @Override
    public void mouseClicked(int i1, int i2, int i3) {
        if (i3 == 0) {
            if (this.mc.hud.hoveredUsername != null) {
                if (this.message.length() > 0 && !this.message.endsWith(" ")) {
                    this.message = String.valueOf(this.message) + " ";
                }
                this.message = String.valueOf(this.message) + this.mc.hud.hoveredUsername;
                int b4 = 100;
                if (this.message.length() > b4) {
                    this.message = this.message.substring(0, b4);
                }
            } else {
                super.mouseClicked(i1, i2, i3);
            }
        }
    }
}

