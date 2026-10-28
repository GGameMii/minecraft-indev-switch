/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Button;
import org.lwjgl.opengl.GL11;

public class Slider
extends Button {
    public float sliderValue = 1.0f;
    public boolean dragging = false;
    private int idFloat = -1;

    public Slider(int id, int x2, int y2, int option, String txt, float val) {
        this(id, x2, y2, 150, 20, option, txt, val);
    }

    public Slider(int id, int x2, int y2, int width, int height, int option, String txt, float val) {
        super(id, x2, y2, width, height, txt);
        this.idFloat = option;
        this.sliderValue = val;
    }

    @Override
    protected byte getType(boolean hovered) {
        return 0;
    }

    @Override
    protected void mouseDragged(Minecraft game, int mx, int my) {
        if (this.visible) {
            if (this.dragging) {
                this.sliderValue = (float)(mx - (this.xPosition + 4)) / (float)(this.width - 8);
                if (this.sliderValue < 0.0f) {
                    this.sliderValue = 0.0f;
                }
                if (this.sliderValue > 1.0f) {
                    this.sliderValue = 1.0f;
                }
                game.options.sliderOption(this.idFloat, this.sliderValue);
                this.displayString = game.options.sliderString(this.idFloat);
            }
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, 66, 4 + (int)(this.sliderValue * (float)(this.width - 8)), 20);
            this.drawTexturedModalRect(this.xPosition + (int)(this.sliderValue * (float)(this.width - 8)) + 4, this.yPosition, 196, 66, 4, 20);
        }
    }

    @Override
    public boolean mousePressed(Minecraft game, int mx, int my) {
        if (super.mousePressed(game, mx, my)) {
            this.sliderValue = (float)(mx - (this.xPosition + 4)) / (float)(this.width - 8);
            if (this.sliderValue < 0.0f) {
                this.sliderValue = 0.0f;
            }
            if (this.sliderValue > 1.0f) {
                this.sliderValue = 1.0f;
            }
            game.options.sliderOption(this.idFloat, this.sliderValue);
            this.displayString = game.options.sliderString(this.idFloat);
            this.dragging = true;
            return true;
        }
        return false;
    }

    @Override
    public void mouseReleased(int mx, int my) {
        this.dragging = false;
    }
}

