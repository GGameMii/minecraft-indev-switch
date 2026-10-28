/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.Gui;
import org.lwjgl.opengl.GL11;

public class Button
extends Gui {
    public int width = 200;
    public int height = 20;
    public int xPosition;
    public int yPosition;
    public String displayString;
    public int id;
    public boolean enabled = true;
    public boolean visible = true;

    public Button(int n2, int n3, int n4, String string) {
        this(n2, n3, n4, 200, 20, string);
    }

    public Button(int n2, int n3, int n4, int n5, int n6, String string) {
        this.id = n2;
        this.xPosition = n3;
        this.yPosition = n4;
        this.width = n5;
        this.height = n6;
        this.displayString = string;
    }

    public final void drawButton(Minecraft minecraft, int n2, int n3) {
        if (this.visible) {
            Font font = minecraft.font;
            GL11.glBindTexture((int)3553, (int)minecraft.renderEngine.getTexture("/gui/gui.png"));
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            boolean bl = n2 >= this.xPosition && n3 >= this.yPosition && n2 < this.xPosition + this.width && n3 < this.yPosition + this.height;
            byte by = this.getType(bl);
            this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, 46 + by * 20, this.width / 2, this.height);
            this.drawTexturedModalRect(this.xPosition + this.width / 2, this.yPosition, 200 - this.width / 2, 46 + by * 20, this.width / 2, this.height);
            this.mouseDragged(minecraft, n2, n3);
            if (!this.enabled) {
                Button.drawCenteredString(font, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, -6250336);
            } else if (bl) {
                Button.drawCenteredString(font, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, 0xFFFFA0);
            } else {
                Button.drawCenteredString(font, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, 0xE0E0E0);
            }
        }
    }

    protected byte getType(boolean bl) {
        byte by = 1;
        if (!this.enabled) {
            by = 0;
        } else if (bl) {
            by = 2;
        }
        return by;
    }

    protected void mouseDragged(Minecraft minecraft, int n2, int n3) {
    }

    public boolean mousePressed(Minecraft minecraft, int n2, int n3) {
        return this.enabled && n2 >= this.xPosition && n3 >= this.yPosition && n2 < this.xPosition + this.width && n3 < this.yPosition + this.height;
    }

    public void mouseReleased(int n2, int n3) {
    }
}

