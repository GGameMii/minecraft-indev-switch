/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.gui;

import java.io.File;
import java.io.FilenameFilter;
import net.minecraft.client.gui.GuiLevelDialog;

final class FilenameFilterLevel
implements FilenameFilter {
    FilenameFilterLevel(GuiLevelDialog guiLevelDialog1) {
    }

    @Override
    public final boolean accept(File file1, String worldName) {
        return worldName.toLowerCase().endsWith(".mclevel");
    }
}

