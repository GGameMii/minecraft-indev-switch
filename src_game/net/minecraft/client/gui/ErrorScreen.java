/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client.gui;

import net.minecraft.client.gui.Button;
import net.minecraft.client.gui.Screen;
import org.lwjgl.opengl.GL11;

public final class ErrorScreen
extends Screen {
    private String title;
    private String text;
    private boolean button = false;

    public ErrorScreen(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public ErrorScreen(String title, String text, boolean button) {
        this(title, text);
        this.button = button;
    }

    @Override
    public void closeScreen() {
    }

    @Override
    public final void init() {
        this.mc.hideScreen = false;
        if (this.button) {
            this.buttons.add(new Button(0, this.width / 2 - 100, this.height - 48, "Return to title"));
        }
    }

    @Override
    protected final void buttonClicked(Button button) {
        if (button.id == 0) {
            this.mc.setScreen(null);
        }
    }

    @Override
    public final void render(int xSize_lo, int ySize_lo, float f3) {
        GL11.glDisable((int)2896);
        ErrorScreen.drawGradientRect(0, 0, this.width, this.height, -12574688, -11530224);
        ErrorScreen.drawCenteredString(this.font, this.title, this.width / 2, 90, 0xFFFFFF);
        ErrorScreen.drawCenteredString(this.font, this.text, this.width / 2, 110, 0xFFFFFF);
        super.render(xSize_lo, ySize_lo, f3);
    }

    @Override
    protected final void keyTyped(char c1, int i2) {
    }
}

