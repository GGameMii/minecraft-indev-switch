/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.loader.ModLoader
 */
package com.gamepadmod;

import com.gamepadmod.PadAction;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import net.minecraft.loader.ModLoader;

final class Bindings {
    private final int[] codes = new int[PadAction.values().length];
    private final File file;

    Bindings(File file) {
        this.file = new File(file, "bindings.properties");
        this.reset();
        this.load();
    }

    int get(PadAction padAction) {
        return this.codes[padAction.ordinal()];
    }

    void set(PadAction padAction, int n) {
        this.codes[padAction.ordinal()] = n;
        this.save();
    }

    void resetAll() {
        this.reset();
        this.save();
    }

    private void reset() {
        for (PadAction padAction : PadAction.values()) {
            this.codes[padAction.ordinal()] = padAction.defaultCode;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void load() {
        if (this.file.isFile()) {
            FileInputStream fileInputStream = null;
            try {
                Properties properties = new Properties();
                fileInputStream = new FileInputStream(this.file);
                properties.load(fileInputStream);
                for (PadAction padAction : PadAction.values()) {
                    String string = properties.getProperty(padAction.id);
                    if (string == null) continue;
                    this.codes[padAction.ordinal()] = Integer.parseInt(string.trim());
                }
            }
            catch (Throwable throwable) {
                ModLoader.warn((String)("Gamepad: could not read bindings: " + throwable));
            }
            finally {
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    }
                    catch (Throwable throwable) {}
                }
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void save() {
        FileOutputStream fileOutputStream = null;
        try {
            Properties properties = new Properties();
            for (PadAction padAction : PadAction.values()) {
                properties.setProperty(padAction.id, Integer.toString(this.codes[padAction.ordinal()]));
            }
            File file = this.file.getParentFile();
            if (file != null && !file.isDirectory()) {
                file.mkdirs();
            }
            fileOutputStream = new FileOutputStream(this.file);
            properties.store(fileOutputStream, "Gamepad bindings (-1 = unbound; 0-99 = button index; 100-103 = D-pad up/down/left/right; 110/111 = left/right trigger)");
        }
        catch (Throwable throwable) {
            ModLoader.warn((String)("Gamepad: could not save bindings: " + throwable));
        }
        finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                }
                catch (Throwable throwable) {}
            }
        }
    }
}

