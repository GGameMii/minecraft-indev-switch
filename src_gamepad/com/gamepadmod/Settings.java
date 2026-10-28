/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.loader.ModLoader
 */
package com.gamepadmod;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.Properties;
import net.minecraft.loader.ModLoader;

final class Settings {
    static final String FILE_NAME = "gamepad.properties";
    private static final String LEGACY_AXES_FILE = "gamepad-axes.properties";
    private static final String DEFAULT_FILE = "#Gamepad mod settings. controller = name substring or index; axis.* = optional axis index overrides (leftX, leftY, rightX, rightY, leftTrigger, rightTrigger)\n#{DATE}\ninvertPovY=false\ncontroller=\nrescan=true\ncursorSpeed=1.0\ncustomCursor=true\ndeadzone=0.25\nlookSpeed=1.0\nswapSticks=false     # troca stick esquerdo <-> direito\nswapLeftXY=false     # troca horizontal/vertical no stick esquerdo\nswapRightXY=false    # troca horizontal/vertical no stick direito\ninvertLeftX=false    # inverte a dire\u00e7\u00e3o de cada eixo\ninvertLeftY=false\ninvertRightX=false\ninvertRightY=false\ndebug=true           # regista no log que eixo mexes e para que serve\naxis.leftX=3\naxis.leftY=2\naxis.rightX=1\naxis.rightY=0\naxis.leftTrigger=5\naxis.rightTrigger=4\n";
    float deadZone = 0.25f;
    float lookSpeed = 1.0f;
    float cursorSpeed = 1.0f;
    boolean customCursor = true;
    boolean rescan = true;
    boolean invertPovY = false;
    String controller = "";
    boolean swapSticks;
    boolean swapLeftXY;
    boolean swapRightXY;
    boolean invertLeftX;
    boolean invertLeftY;
    boolean invertRightX;
    boolean invertRightY;
    boolean debug;
    int axisLeftX = -1;
    int axisLeftY = -1;
    int axisRightX = -1;
    int axisRightY = -1;
    int axisLeftTrigger = -1;
    int axisRightTrigger = -1;

    Settings() {
    }

    static Settings load(File file) {
        File file2 = new File(file, FILE_NAME);
        if (!file2.isFile()) {
            Settings.writeDefault(file2);
        }
        Properties properties = new Properties();
        File file3 = new File(file, LEGACY_AXES_FILE);
        if (file3.isFile()) {
            Settings.readInto(properties, file3);
        }
        Settings.readInto(properties, file2);
        Settings settings = new Settings();
        settings.deadZone = Settings.clamp(Settings.f(properties, "deadzone", settings.deadZone), 0.05f, 0.9f);
        settings.lookSpeed = Settings.clamp(Settings.f(properties, "lookSpeed", settings.lookSpeed), 0.1f, 10.0f);
        settings.cursorSpeed = Settings.clamp(Settings.f(properties, "cursorSpeed", settings.cursorSpeed), 0.1f, 10.0f);
        settings.customCursor = Settings.b(properties, "customCursor", settings.customCursor);
        settings.rescan = Settings.b(properties, "rescan", settings.rescan);
        settings.invertPovY = Settings.b(properties, "invertPovY", settings.invertPovY);
        settings.controller = properties.getProperty("controller", "").trim();
        settings.swapSticks = Settings.b(properties, "swapSticks", false);
        settings.swapLeftXY = Settings.b(properties, "swapLeftXY", false);
        settings.swapRightXY = Settings.b(properties, "swapRightXY", false);
        settings.invertLeftX = Settings.b(properties, "invertLeftX", false);
        settings.invertLeftY = Settings.b(properties, "invertLeftY", false);
        settings.invertRightX = Settings.b(properties, "invertRightX", false);
        settings.invertRightY = Settings.b(properties, "invertRightY", false);
        settings.debug = Settings.b(properties, "debug", false);
        settings.axisLeftX = Settings.i(properties, "axis.leftX", -1);
        settings.axisLeftY = Settings.i(properties, "axis.leftY", -1);
        settings.axisRightX = Settings.i(properties, "axis.rightX", -1);
        settings.axisRightY = Settings.i(properties, "axis.rightY", -1);
        settings.axisLeftTrigger = Settings.i(properties, "axis.leftTrigger", -1);
        settings.axisRightTrigger = Settings.i(properties, "axis.rightTrigger", -1);
        return settings;
    }

    private static void writeDefault(File file) {
        try {
            File file2 = file.getParentFile();
            if (file2 != null && !file2.isDirectory()) {
                file2.mkdirs();
            }
            try (OutputStreamWriter outputStreamWriter = new OutputStreamWriter((OutputStream)new FileOutputStream(file), "UTF-8");){
                outputStreamWriter.write(DEFAULT_FILE.replace("{DATE}", new Date().toString()));
            }
        }
        catch (Throwable throwable) {
            ModLoader.warn((String)("Gamepad: could not write gamepad.properties: " + throwable));
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static void readInto(Properties properties, File file) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((InputStream)new FileInputStream(file), "UTF-8"));){
            String string;
            while ((string = bufferedReader.readLine()) != null) {
                String string2 = string.trim();
                if (string2.isEmpty() || string2.charAt(0) == '#' || string2.charAt(0) == '!') continue;
                int n = -1;
                for (int i = 0; i < string2.length(); ++i) {
                    char c = string2.charAt(i);
                    if (c != '=' && c != ':') continue;
                    n = i;
                    break;
                }
                if (n <= 0) continue;
                String string3 = string2.substring(0, n).trim();
                String string4 = string2.substring(n + 1);
                for (int i = 0; i < string4.length(); ++i) {
                    if (string4.charAt(i) != '#' || i != 0 && !Character.isWhitespace(string4.charAt(i - 1))) continue;
                    string4 = string4.substring(0, i);
                    break;
                }
                properties.setProperty(string3, string4.trim());
            }
        }
        catch (Throwable throwable) {
            ModLoader.warn((String)("Gamepad: could not read " + file.getName() + ": " + throwable));
        }
    }

    private static float f(Properties properties, String string, float f) {
        try {
            return Float.parseFloat(properties.getProperty(string, "").trim());
        }
        catch (Throwable throwable) {
            return f;
        }
    }

    private static int i(Properties properties, String string, int n) {
        try {
            return Integer.parseInt(properties.getProperty(string, "").trim());
        }
        catch (Throwable throwable) {
            return n;
        }
    }

    private static boolean b(Properties properties, String string, boolean bl) {
        String string2 = properties.getProperty(string);
        return string2 == null || string2.trim().isEmpty() ? bl : Boolean.parseBoolean(string2.trim());
    }

    private static float clamp(float f, float f2, float f3) {
        return f < f2 ? f2 : (f > f3 ? f3 : f);
    }
}

