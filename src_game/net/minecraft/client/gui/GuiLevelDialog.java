/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.gui;

import java.awt.FileDialog;
import java.io.File;
import net.minecraft.client.gui.FilenameFilterLevel;
import net.minecraft.client.gui.LoadLevelScreen;

final class GuiLevelDialog
extends Thread {
    private LoadLevelScreen screen;

    GuiLevelDialog(LoadLevelScreen guiLoadLevel1) {
        this.screen = guiLoadLevel1;
    }

    @Override
    public final void run() {
        try {
            FileDialog fileDialog1 = this.screen.saveFileDialog();
            File file2 = new File(this.screen.mc.mcDataDir, "saves");
            file2.mkdir();
            String string5 = file2.toString();
            if (!string5.endsWith(File.separator)) {
                string5 = String.valueOf(string5) + File.separator;
            }
            fileDialog1.setDirectory(string5);
            FilenameFilterLevel filenameFilterLevel6 = new FilenameFilterLevel(this);
            fileDialog1.setFilenameFilter(filenameFilterLevel6);
            fileDialog1.setLocationRelativeTo(this.screen.mc.mcCanvas);
            fileDialog1.setVisible(true);
            if (fileDialog1.getFile() != null) {
                string5 = fileDialog1.getDirectory();
                if (!string5.endsWith(File.separator)) {
                    string5 = String.valueOf(string5) + File.separator;
                }
                LoadLevelScreen.a(this.screen, new File(String.valueOf(string5) + fileDialog1.getFile()));
            }
        }
        finally {
            LoadLevelScreen.unknown(this.screen, false);
        }
    }
}

