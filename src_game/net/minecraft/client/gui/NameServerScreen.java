/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.input.Keyboard
 */
package net.minecraft.client.gui;

import net.minecraft.client.gui.Button;
import net.minecraft.client.gui.GuiTextBox;
import net.minecraft.client.gui.Screen;
import net.minecraft.client.gui.ServerListScreen;
import org.lwjgl.input.Keyboard;

public final class NameServerScreen
extends Screen {
    private Screen parent;
    private String title = "Enter server info:";
    private int id;
    private String name;
    private String ip;
    private GuiTextBox boxName;
    private GuiTextBox boxIp;

    public NameServerScreen(Screen parent, String name, String ip, int id) {
        this.parent = parent;
        this.id = id;
        this.name = name;
        this.ip = ip;
        if (this.name.equals("-")) {
            this.name = "";
        }
    }

    @Override
    public final void init() {
        String last;
        this.buttons.clear();
        Keyboard.enableRepeatEvents((boolean)true);
        this.buttons.add(new Button(0, this.width / 2 - 100, this.height / 4 + 120, "Save"));
        this.buttons.add(new Button(1, this.width / 2 - 100, this.height / 4 + 144, "Cancel"));
        this.boxName = new GuiTextBox(this.mc.font, "", this.width / 2 - 100, this.height / 3 + 12, 200, 20);
        this.boxName.text = this.name;
        this.boxIp = new GuiTextBox(this.mc.font, "", this.width / 2 - 100, this.height / 3 + 56, 200, 20);
        this.boxIp.text = last = this.ip.replaceAll("_", ":");
        ((Button)this.buttons.get((int)0)).enabled = this.name.trim().length() > 1 && this.ip.trim().length() > 1;
        this.boxName.focused = true;
        this.boxIp.focused = false;
    }

    @Override
    public final void closeScreen() {
        Keyboard.enableRepeatEvents((boolean)false);
    }

    @Override
    public void mouseClicked(int x2, int y2, int type) {
        this.boxName.clicked(x2, y2);
        this.boxIp.clicked(x2, y2);
        super.mouseClicked(x2, y2, type);
    }

    @Override
    public void tick() {
        this.boxName.tick();
        this.boxIp.tick();
    }

    @Override
    protected final void keyTyped(char ch, int key) {
        this.boxName.keyPressed(ch, key);
        this.boxIp.keyPressed(ch, key);
        ((Button)this.buttons.get((int)0)).enabled = this.boxIp.text.trim().length() > 1 && this.boxName.text.trim().length() > 1 && !this.boxName.text.equals("-");
    }

    @Override
    protected final void buttonClicked(Button button) {
        if (button.enabled) {
            if (button.id == 0 && this.boxName.text.trim().length() > 1 && this.boxIp.text.trim().length() > 1) {
                String ip = this.boxIp.text.replaceAll(":", "_");
                ((ServerListScreen)this.parent).setServer(this.id, this.boxName.text, ip);
                this.mc.setScreen(this.parent);
            }
            if (button.id == 1) {
                this.mc.setScreen(this.parent);
            }
        }
    }

    @Override
    public final void render(int mx, int my, float a2) {
        this.drawBackground();
        NameServerScreen.drawCenteredString(this.font, this.title, this.width / 2, 40, 0xFFFFFF);
        int xx = this.width / 2 - 100;
        int yy = this.height / 3;
        NameServerScreen.drawString(this.font, "Name", xx, yy, 0xE0E0E0);
        NameServerScreen.drawString(this.font, "Address", xx, yy + 44, 0xE0E0E0);
        this.boxName.draw(mx, my, a2);
        this.boxIp.draw(mx, my, a2);
        super.render(mx, my, a2);
    }
}

