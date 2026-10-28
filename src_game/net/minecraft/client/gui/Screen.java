/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.input.Keyboard
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client.gui;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Button;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.Tesselator;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class Screen
extends Gui {
    protected Minecraft mc;
    public int width;
    public int height;
    protected List<Button> buttons = new ArrayList<Button>();
    public boolean allowUserInput = false;
    protected Font font;
    private Button selectedButton = null;

    public void render(int mx, int my, float a2) {
        int i2 = 0;
        while (i2 < this.buttons.size()) {
            this.buttons.get(i2).drawButton(this.mc, mx, my);
            ++i2;
        }
    }

    public void sendKey(char c1, int i2) {
        this.keyTyped(c1, i2);
    }

    public void sendClick(int mx, int my, int type) {
        this.mouseClicked(mx, my, type);
    }

    protected void keyTyped(char c1, int i2) {
        if (i2 == 1) {
            this.mc.setScreen(null);
            this.mc.grabMouse();
        }
    }

    protected void mouseClicked(int mx, int my, int type) {
        if (type == 0) {
            int i2 = 0;
            while (i2 < this.buttons.size()) {
                Button button = this.buttons.get(i2);
                if (button.mousePressed(this.mc, mx, my)) {
                    this.selectedButton = button;
                    this.mc.sndManager.playSoundFX("random.click", 1.0f, 1.0f);
                    this.buttonClicked(button);
                }
                ++i2;
            }
        }
    }

    protected void buttonClicked(Button button) {
    }

    public final void setWorldAndResolution(Minecraft game, int width, int height) {
        this.mc = game;
        this.font = game.font;
        this.width = width;
        this.height = height;
        this.buttons.clear();
        this.init();
    }

    public void init() {
    }

    public void handleMouseInput() {
        if (Mouse.getEventButtonState()) {
            int mx = Mouse.getEventX() * this.width / this.mc.width;
            int my = this.height - Mouse.getEventY() * this.height / this.mc.height - 1;
            this.mouseClicked(mx, my, Mouse.getEventButton());
        } else {
            int mx = Mouse.getEventX() * this.width / this.mc.width;
            int my = this.height - Mouse.getEventY() * this.height / this.mc.height - 1;
            this.mouseMovedOrUp(mx, my, Mouse.getEventButton());
        }
    }

    public void sendRelease(int mx, int my, int type) {
        this.mouseMovedOrUp(mx, my, type);
    }

    protected void mouseMovedOrUp(int mx, int my, int type) {
        if (this.selectedButton != null && type == 0) {
            this.selectedButton.mouseReleased(mx, my);
            this.selectedButton = null;
        }
    }

    public final void handleKeyboardInput() {
        if (Keyboard.getEventKeyState()) {
            if (Keyboard.getEventKey() == 87) {
                this.mc.toggleFullscreen();
                return;
            }
            this.keyTyped(Keyboard.getEventCharacter(), Keyboard.getEventKey());
        }
    }

    public void tick() {
    }

    public void closeScreen() {
    }

    public final void drawBackground() {
        if (this.mc.level != null) {
            Screen.drawGradientRect(0, 0, this.width, this.height, 0x60050500, -1607454624);
        } else {
            GL11.glDisable((int)2896);
            GL11.glDisable((int)2912);
            Tesselator t2 = Tesselator.instance;
            GL11.glBindTexture((int)3553, (int)this.mc.renderEngine.getTexture("/dirt.png"));
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            t2.begin();
            t2.color(0x404040);
            t2.vertexUV(0.0f, this.height, 0.0f, 0.0f, (float)this.height / 32.0f);
            t2.vertexUV(this.width, this.height, 0.0f, (float)this.width / 32.0f, (float)this.height / 32.0f);
            t2.vertexUV(this.width, 0.0f, 0.0f, (float)this.width / 32.0f, 0.0f);
            t2.vertexUV(0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
            t2.end();
        }
    }

    public boolean doesGuiPauseGame() {
        return true;
    }

    public static String getClipboardString() {
        try {
            Transferable trans = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
            if (trans != null && trans.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                String txt = (String)trans.getTransferData(DataFlavor.stringFlavor);
                return txt;
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return null;
    }
}

