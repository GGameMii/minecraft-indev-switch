/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.input.Keyboard
 */
package net.minecraft.client;

import net.minecraft.client.gui.Button;
import net.minecraft.client.gui.GuiTextBox;
import net.minecraft.client.gui.Screen;
import org.lwjgl.input.Keyboard;

public class MultiplayerScreen
extends Screen {
    private GuiTextBox boxIp;
    private Screen prevScr;

    public MultiplayerScreen(Screen scr) {
        this.prevScr = scr;
    }

    @Override
    public void init() {
        String last;
        this.buttons.add(new Button(0, this.width / 2 - 100, this.height - 48, "Cancel"));
        this.buttons.add(new Button(1, this.width / 2 - 100, this.height - 72, "Connect"));
        this.boxIp = new GuiTextBox(this.mc.font, "", this.width / 2 - 100, this.height / 3 + 32, 200, 20);
        this.boxIp.text = last = this.mc.options.lastServer.replaceAll("_", ":");
    }

    @Override
    public void buttonClicked(Button b2) {
        if (b2.id == 0) {
            this.mc.setScreen(this.prevScr);
        }
        if (b2.id == 1) {
            String server = this.boxIp.text;
            String port = "25565";
            String[] split = server.split(":");
            if (split.length > 1) {
                port = split[1];
            }
            this.mc.hideScreen = true;
            this.mc.loadingScreen.beginLevelLoading("Connecting...");
            this.mc.loadingScreen.setLoadingProgress(0);
            this.mc.connectTo(split[0], Integer.parseInt(port));
            this.mc.hideScreen = false;
            this.mc.options.lastServer = server = server.replaceAll(":", "_");
            this.mc.options.saveOptions();
        }
    }

    @Override
    public final void closeScreen() {
        Keyboard.enableRepeatEvents((boolean)false);
    }

    @Override
    public void mouseClicked(int x2, int y2, int type) {
        this.boxIp.clicked(x2, y2);
        super.mouseClicked(x2, y2, type);
    }

    @Override
    public void tick() {
        this.boxIp.tick();
    }

    @Override
    protected final void keyTyped(char c1, int i2) {
        this.boxIp.keyPressed(c1, i2);
        boolean bl = ((Button)this.buttons.get((int)1)).enabled = this.boxIp.text.trim().length() > 1;
        if (((Button)this.buttons.get((int)1)).enabled && i2 == 28) {
            this.buttonClicked((Button)this.buttons.get(1));
        }
    }

    @Override
    public void render(int mx, int my, float a2) {
        this.drawBackground();
        MultiplayerScreen.drawCenteredString(this.mc.font, "Minecraft Indev Multiplayer", this.width / 2, 24, 0xFFFFFF);
        this.boxIp.draw(mx, my, a2);
        super.render(mx, my, a2);
    }
}

