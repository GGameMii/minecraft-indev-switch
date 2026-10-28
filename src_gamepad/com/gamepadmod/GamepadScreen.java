/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.Button
 *  net.minecraft.client.gui.Font
 *  net.minecraft.client.gui.Screen
 *  net.minecraft.client.gui.SmallButton
 */
package com.gamepadmod;

import com.gamepadmod.Bindings;
import com.gamepadmod.PadAction;
import com.gamepadmod.PadInput;
import java.util.List;
import net.minecraft.client.gui.Button;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.Screen;
import net.minecraft.client.gui.SmallButton;

final class GamepadScreen
extends Screen {
    private static final int PER_PAGE = 10;
    private final Screen parent;
    private final PadInput pad;
    private final Bindings bindings;
    private int page;
    private int capturing = -1;
    private boolean skipTick;

    GamepadScreen(Screen screen, PadInput padInput, Bindings bindings) {
        this.parent = screen;
        this.pad = padInput;
        this.bindings = bindings;
    }

    boolean isCapturing() {
        return this.capturing >= 0;
    }

    private int pages() {
        return (PadAction.values().length + 10 - 1) / 10;
    }

    private String text(int n) {
        PadAction padAction = PadAction.values()[n];
        return this.capturing == n ? padAction.label + ": > ? <" : padAction.label + ": " + PadInput.codeName(this.bindings.get(padAction));
    }

    public void init() {
        int n;
        int n2;
        List list = this.buttons;
        list.clear();
        PadAction[] padActionArray = PadAction.values();
        for (n2 = 0; n2 < 10 && (n = this.page * 10 + n2) < padActionArray.length; ++n2) {
            list.add(new SmallButton(n2, this.width / 2 - 155 + n2 % 2 * 160, this.height / 6 + 24 * (n2 >> 1), this.text(n)));
        }
        n2 = this.height / 6 + 144;
        Button button = new Button(100, this.width / 2 - 100, n2, 64, 20, "< Prev");
        Button button2 = new Button(101, this.width / 2 - 32, n2, 64, 20, "Reset");
        Button button3 = new Button(102, this.width / 2 + 36, n2, 64, 20, "Next >");
        button.enabled = this.page > 0;
        button3.enabled = this.page < this.pages() - 1;
        list.add(button);
        list.add(button2);
        list.add(button3);
        list.add(new Button(200, this.width / 2 - 100, this.height / 6 + 168, "Done"));
    }

    private void refresh() {
        for (Object e : this.buttons) {
            int n;
            Button button = (Button)e;
            if (button.id < 0 || button.id >= 10 || (n = this.page * 10 + button.id) >= PadAction.values().length) continue;
            button.displayString = this.text(n);
        }
    }

    protected void buttonClicked(Button button) {
        if (button.id == 200) {
            this.capturing = -1;
            this.mc.setScreen(this.parent);
        } else if (button.id == 100) {
            if (this.page > 0) {
                --this.page;
                this.capturing = -1;
                this.init();
            }
        } else if (button.id == 102) {
            if (this.page < this.pages() - 1) {
                ++this.page;
                this.capturing = -1;
                this.init();
            }
        } else if (button.id == 101) {
            this.bindings.resetAll();
            this.capturing = -1;
            this.refresh();
        } else if (button.id >= 0 && button.id < 10) {
            this.capturing = this.page * 10 + button.id;
            this.skipTick = true;
            this.refresh();
        }
    }

    public void tick() {
        super.tick();
        if (this.capturing >= 0) {
            if (this.skipTick) {
                this.skipTick = false;
            } else {
                int n = this.pad.anyJustPressed();
                if (n >= 0) {
                    this.bindings.set(PadAction.values()[this.capturing], n);
                    this.capturing = -1;
                    this.refresh();
                }
            }
        }
    }

    protected void keyTyped(char c, int n) {
        if (this.capturing < 0) {
            if (n == 1) {
                this.mc.setScreen(this.parent);
            } else {
                super.keyTyped(c, n);
            }
        } else if (n == 1) {
            this.capturing = -1;
            this.refresh();
        } else if (n == 14 || n == 211) {
            this.bindings.set(PadAction.values()[this.capturing], -1);
            this.capturing = -1;
            this.refresh();
        }
    }

    public void render(int n, int n2, float f) {
        this.drawBackground();
        GamepadScreen.drawCenteredString((Font)this.font, (String)"Gamepad Controls", (int)(this.width / 2), (int)20, (int)0xFFFFFF);
        String string = this.pad.controllerName();
        String string2 = (string == null ? "No controller detected" : string) + "  -  page " + (this.page + 1) + "/" + this.pages();
        if (this.capturing >= 0) {
            string2 = "Press a controller button (Esc = cancel, Del = unbind)";
        }
        GamepadScreen.drawCenteredString((Font)this.font, (String)string2, (int)(this.width / 2), (int)(this.height / 6 + 128), (int)0xA0A0A0);
        super.render(n, n2, f);
    }
}

