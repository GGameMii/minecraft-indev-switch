/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.input.Keyboard
 */
package net.minecraft.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import net.minecraft.client.KeyBinding;
import net.minecraft.client.Minecraft;
import net.minecraft.client.SliderOption;
import org.lwjgl.input.Keyboard;

public final class GameOptions {
    private static final String[] RENDER_DISTANCES = new String[]{"FAR", "NORMAL", "SHORT", "TINY"};
    private static final String[] DIFFICULTIES = new String[]{"Peaceful", "Easy", "Normal", "Hard"};
    public boolean music = true;
    public boolean sound = true;
    public boolean invertMouse = false;
    public boolean showFPS = false;
    public int renderDistance = 0;
    public boolean fancyGraphics = true;
    public boolean anaglyph3d = false;
    public boolean limitFramerate = false;
    public KeyBinding keyBindForward = new KeyBinding("Forward", 17);
    public KeyBinding keyBindLeft = new KeyBinding("Left", 30);
    public KeyBinding keyBindBack = new KeyBinding("Back", 31);
    public KeyBinding keyBindRight = new KeyBinding("Right", 32);
    public KeyBinding keyBindJump = new KeyBinding("Jump", 57);
    public KeyBinding keyBindInventory = new KeyBinding("Inventory", 23);
    public KeyBinding keyBindBuild = new KeyBinding("Build", 48);
    public KeyBinding keyBindDrop = new KeyBinding("Drop", 16);
    public KeyBinding keyBindChat = new KeyBinding("Chat", 20);
    public KeyBinding keyBindToggleFog = new KeyBinding("Toggle fog", 33);
    public KeyBinding keyBindSave = new KeyBinding("Save location", 28);
    public KeyBinding keyBindLoad = new KeyBinding("Load location", 19);
    public KeyBinding[] keyBindings = new KeyBinding[]{this.keyBindForward, this.keyBindLeft, this.keyBindBack, this.keyBindRight, this.keyBindJump, this.keyBindDrop, this.keyBindInventory, this.keyBindChat, this.keyBindToggleFog, this.keyBindSave, this.keyBindLoad, this.keyBindBuild};
    public SliderOption sliderMouse = new SliderOption("Mouse", 0.3f);
    public SliderOption sliderVoice = new SliderOption("Voice", 0.25f);
    public SliderOption[] sliders = new SliderOption[]{this.sliderMouse, this.sliderVoice};
    public static final int MOUSE_SENSITIVITY = 0;
    public static final int MICROPHONE_VOLUME = 1;
    private Minecraft mc;
    private File optionsFile;
    public int numberOfOptions = 9;
    public int difficulty = 2;
    public boolean thirdPersonView = false;
    public String lastServer = "";
    public int microphone = 0;
    public boolean voice = false;
    public String username = "Steve";

    public GameOptions(Minecraft minecraft, File file) {
        this.mc = minecraft;
        this.optionsFile = new File(file, "options.txt");
        this.loadOptions();
    }

    public final String setKeyBindingString(int keyBindingNumber) {
        return String.valueOf(this.keyBindings[keyBindingNumber].keyDescription) + ": " + Keyboard.getKeyName((int)this.keyBindings[keyBindingNumber].keyCode);
    }

    public final void setKeyBinding(int keyBindingNumber, int keyCode) {
        this.keyBindings[keyBindingNumber].keyCode = keyCode;
        this.saveOptions();
    }

    public final void setOptionValue(int optionMenuValue, int optionSubValue) {
        if (optionMenuValue == 0) {
            this.music = !this.music;
            this.mc.sndManager.onSoundOptionsChanged();
        }
        if (optionMenuValue == 1) {
            this.sound = !this.sound;
            this.mc.sndManager.onSoundOptionsChanged();
        }
        if (optionMenuValue == 2) {
            boolean bl = this.invertMouse = !this.invertMouse;
        }
        if (optionMenuValue == 3) {
            boolean bl = this.showFPS = !this.showFPS;
        }
        if (optionMenuValue == 4) {
            this.renderDistance = this.renderDistance + optionSubValue & 3;
        }
        if (optionMenuValue == 5) {
            boolean bl = this.fancyGraphics = !this.fancyGraphics;
        }
        if (optionMenuValue == 6) {
            this.anaglyph3d = !this.anaglyph3d;
            this.mc.renderEngine.refreshTextures();
        }
        if (optionMenuValue == 7) {
            boolean bl = this.limitFramerate = !this.limitFramerate;
        }
        if (optionMenuValue == 8) {
            this.difficulty = this.difficulty + optionSubValue & 3;
        }
        this.saveOptions();
    }

    public final String setOptionString(int value) {
        return value == 0 ? "Music: " + (this.music ? "ON" : "OFF") : (value == 1 ? "Sound: " + (this.sound ? "ON" : "OFF") : (value == 2 ? "Invert mouse: " + (this.invertMouse ? "ON" : "OFF") : (value == 3 ? "Show FPS: " + (this.showFPS ? "ON" : "OFF") : (value == 4 ? "Render distance: " + RENDER_DISTANCES[this.renderDistance] : (value == 5 ? "View bobbing: " + (this.fancyGraphics ? "ON" : "OFF") : (value == 6 ? "3d anaglyph: " + (this.anaglyph3d ? "ON" : "OFF") : (value == 7 ? "Limit framerate: " + (this.limitFramerate ? "ON" : "OFF") : (value == 8 ? "Difficulty: " + DIFFICULTIES[this.difficulty] : ""))))))));
    }

    public final String sliderString(int id) {
        if (id == 1) {
            return "Mic. Amplifier: " + (int)(this.sliders[id].value * 1000.0f) + "%";
        }
        return "Mouse Sensitivity: " + (int)(this.sliders[id].value * 200.0f) + "%";
    }

    private void loadOptions() {
        try {
            if (this.optionsFile.exists()) {
                String log;
                BufferedReader reader = new BufferedReader(new FileReader(this.optionsFile));
                while ((log = reader.readLine()) != null) {
                    String[] l2 = log.split(":");
                    if (l2[0].equals("music")) {
                        this.music = l2[1].equals("true");
                    }
                    if (l2[0].equals("sound")) {
                        this.sound = l2[1].equals("true");
                    }
                    if (l2[0].equals("invertYMouse")) {
                        this.invertMouse = l2[1].equals("true");
                    }
                    if (l2[0].equals("showFrameRate")) {
                        this.showFPS = l2[1].equals("true");
                    }
                    if (l2[0].equals("viewDistance")) {
                        this.renderDistance = Integer.parseInt(l2[1]);
                    }
                    if (l2[0].equals("bobView")) {
                        this.fancyGraphics = l2[1].equals("true");
                    }
                    if (l2[0].equals("anaglyph3d")) {
                        this.anaglyph3d = l2[1].equals("true");
                    }
                    if (l2[0].equals("limitFramerate")) {
                        this.limitFramerate = l2[1].equals("true");
                    }
                    if (l2[0].equals("difficulty")) {
                        this.difficulty = Integer.parseInt(l2[1]);
                    }
                    if (l2[0].equals("lastServer") && l2.length >= 2) {
                        this.lastServer = l2[1];
                    }
                    if (l2[0].equals("username") && l2.length >= 2) {
                        this.username = l2[1].trim();
                        if (this.username.length() == 0) {
                            this.username = "Steve";
                        }
                    }
                    if (l2[0].equals("microphone")) {
                        this.microphone = Integer.parseInt(l2[1]);
                    }
                    int i2 = 0;
                    while (i2 < this.keyBindings.length) {
                        if (l2[0].equals("key_" + this.keyBindings[i2].keyDescription)) {
                            this.keyBindings[i2].keyCode = Integer.parseInt(l2[1]);
                        }
                        ++i2;
                    }
                    i2 = 0;
                    while (i2 < this.sliders.length) {
                        if (l2[0].equals("slider_" + this.sliders[i2].name)) {
                            this.sliders[i2].value = Float.parseFloat(l2[1]);
                        }
                        ++i2;
                    }
                }
                reader.close();
            }
        }
        catch (Exception e2) {
            System.out.println("Failed to load options");
            e2.printStackTrace();
        }
    }

    public final void saveOptions() {
        try {
            PrintWriter w2 = new PrintWriter(new FileWriter(this.optionsFile));
            w2.println("music:" + this.music);
            w2.println("sound:" + this.sound);
            w2.println("invertYMouse:" + this.invertMouse);
            w2.println("showFrameRate:" + this.showFPS);
            w2.println("viewDistance:" + this.renderDistance);
            w2.println("bobView:" + this.fancyGraphics);
            w2.println("anaglyph3d:" + this.anaglyph3d);
            w2.println("limitFramerate:" + this.limitFramerate);
            w2.println("difficulty:" + this.difficulty);
            w2.println("lastServer:" + this.lastServer);
            w2.println("username:" + this.username);
            w2.println("microphone:" + this.microphone);
            int i2 = 0;
            while (i2 < this.keyBindings.length) {
                w2.println("key_" + this.keyBindings[i2].keyDescription + ":" + this.keyBindings[i2].keyCode);
                ++i2;
            }
            i2 = 0;
            while (i2 < this.sliders.length) {
                w2.println("slider_" + this.sliders[i2].name + ":" + this.sliders[i2].value);
                ++i2;
            }
            w2.close();
        }
        catch (Exception e2) {
            System.out.println("Failed to save options");
            e2.printStackTrace();
        }
    }

    public void sliderOption(int id, float value) {
        this.sliders[id].value = value;
    }
}

