/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.gui;

import java.awt.FileDialog;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import net.minecraft.client.PlayerLoader;
import net.minecraft.client.gui.Button;
import net.minecraft.client.gui.LoadLevelScreen;
import net.minecraft.client.gui.NameLevelScreen;
import net.minecraft.client.gui.Screen;

public final class SaveLevelScreen
extends LoadLevelScreen {
    public SaveLevelScreen(Screen screen) {
        super(screen);
        this.title = "Save level";
    }

    @Override
    protected final FileDialog saveFileDialog() {
        return new FileDialog((java.awt.Frame)null, "Save level", 1);
    }

    @Override
    public final void init() {
        super.init();
        ((Button)this.buttons.get((int)5)).displayString = "Save file...";
    }

    @Override
    protected final void setLevels(String[] text) {
        int slot = 0;
        while (slot < 5) {
            ((Button)this.buttons.get((int)slot)).displayString = text[slot];
            ((Button)this.buttons.get((int)slot)).visible = true;
            ++slot;
        }
        ((Button)this.buttons.get((int)5)).visible = true;
    }

    @Override
    protected final void openLevel(File file) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            new PlayerLoader(this.mc, this.mc.loadingScreen).save(this.mc.level, fos, true);
            fos.close();
        }
        catch (IOException io) {
            io.printStackTrace();
        }
    }

    @Override
    protected final void openLevel(int slot) {
        this.mc.setScreen(new NameLevelScreen(this, ((Button)this.buttons.get((int)slot)).displayString, slot));
    }
}

