/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.gui;

import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import net.minecraft.client.PlayerLoader;
import net.minecraft.client.gui.Button;
import net.minecraft.client.gui.GuiLevelDialog;
import net.minecraft.client.gui.Screen;
import net.minecraft.world.level.Level;

public class LoadLevelScreen
extends Screen
implements Runnable {
    private Screen parent;
    private boolean finished = false;
    private boolean loaded = false;
    private String[] levels = null;
    private String status = "";
    protected String title = "Load level";
    private boolean frozen = false;
    private File selectedFile;

    public LoadLevelScreen(Screen guiScreen1) {
        this.parent = guiScreen1;
    }

    @Override
    public final void tick() {
        if (this.selectedFile != null) {
            if (!this.selectedFile.getName().endsWith(".mclevel")) {
                this.selectedFile = new File(String.valueOf(this.selectedFile.getAbsolutePath()) + ".mclevel");
            }
            this.openLevel(this.selectedFile);
            this.selectedFile = null;
            this.mc.setScreen(null);
        }
    }

    @Override
    public void run() {
        try {
            this.status = "Getting level list..";
            URL uRL1 = new URL("http://" + this.mc.minecraftUri + "/listmaps.jsp?user=" + this.mc.user.name);
            BufferedReader bufferedReader3 = new BufferedReader(new InputStreamReader(uRL1.openConnection().getInputStream()));
            this.levels = bufferedReader3.readLine().split(";");
            if (this.levels.length >= 5) {
                this.setLevels(this.levels);
                this.loaded = true;
                return;
            }
            this.status = this.levels[0];
            this.finished = true;
        }
        catch (Exception exception2) {
            exception2.printStackTrace();
            this.status = "Failed to load levels";
            this.finished = true;
        }
    }

    protected void setLevels(String[] string1) {
        int i2 = 0;
        while (i2 < 5) {
            ((Button)this.buttons.get((int)i2)).enabled = !string1[i2].equals("-");
            ((Button)this.buttons.get((int)i2)).displayString = string1[i2];
            ((Button)this.buttons.get((int)i2)).visible = true;
            ++i2;
        }
        ((Button)this.buttons.get((int)5)).visible = true;
    }

    @Override
    public void init() {
        new Thread(this).start();
        int i1 = 0;
        while (i1 < 5) {
            this.buttons.add(new Button(i1, this.width / 2 - 100, this.height / 6 + i1 * 24, "---"));
            ((Button)this.buttons.get((int)i1)).visible = false;
            ++i1;
        }
        this.buttons.add(new Button(5, this.width / 2 - 100, this.height / 6 + 120 + 12, "Load file..."));
        this.buttons.add(new Button(6, this.width / 2 - 100, this.height / 6 + 168, "Cancel"));
        ((Button)this.buttons.get((int)5)).visible = false;
    }

    @Override
    protected final void buttonClicked(Button guiButton1) {
        if (!this.frozen && guiButton1.enabled) {
            if (this.loaded && guiButton1.id < 5) {
                this.openLevel(guiButton1.id);
            }
            if (this.finished || this.loaded && guiButton1.id == 5) {
                this.frozen = true;
                GuiLevelDialog guiLevelDialog2 = new GuiLevelDialog(this);
                guiLevelDialog2.setDaemon(true);
                guiLevelDialog2.start();
            }
            if (this.finished || this.loaded && guiButton1.id == 6) {
                this.mc.setScreen(this.parent);
            }
        }
    }

    protected FileDialog saveFileDialog() {
        return new FileDialog((java.awt.Frame)null, "Load level", 0);
    }

    protected void openLevel(int i1) {
        this.mc.loadLevel(this.mc.user.name, i1);
        this.mc.setScreen(null);
        this.mc.grabMouse();
    }

    @Override
    public final void render(int xSize_lo, int ySize_lo, float f3) {
        this.drawBackground();
        LoadLevelScreen.drawCenteredString(this.font, this.title, this.width / 2, 20, 0xFFFFFF);
        if (!this.loaded) {
            LoadLevelScreen.drawCenteredString(this.font, this.status, this.width / 2, this.height / 2 - 4, 0xFFFFFF);
        }
        super.render(xSize_lo, ySize_lo, f3);
    }

    protected void openLevel(File file1) {
        try {
            FileInputStream fileInputStream4 = new FileInputStream(file1);
            Level world2 = new PlayerLoader(this.mc, this.mc.loadingScreen).load(fileInputStream4);
            fileInputStream4.close();
            this.mc.setLevel(world2);
        }
        catch (IOException iOException3) {
            iOException3.printStackTrace();
        }
    }

    static File a(LoadLevelScreen guiLoadLevel0, File file1) {
        guiLoadLevel0.selectedFile = file1;
        return guiLoadLevel0.selectedFile;
    }

    static boolean unknown(LoadLevelScreen guiLoadLevel0, boolean z1) {
        guiLoadLevel0.frozen = false;
        return false;
    }
}

