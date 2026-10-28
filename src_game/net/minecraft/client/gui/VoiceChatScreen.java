/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.gui;

import java.text.Normalizer;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.TargetDataLine;
import net.minecraft.client.VoiceChat;
import net.minecraft.client.gui.Button;
import net.minecraft.client.gui.Screen;
import net.minecraft.client.gui.Slider;

public final class VoiceChatScreen
extends Screen {
    private Screen parent;
    private String title = "Voice Chat";
    private int microphoneSelected = 0;
    private int max = 0;
    private Mixer.Info[] mixerInfo = AudioSystem.getMixerInfo();
    private boolean noneDetected = false;

    public VoiceChatScreen(Screen parent) {
        this.parent = parent;
    }

    @Override
    public final void init() {
        int cnt = 0;
        while (cnt < this.mixerInfo.length) {
            this.max = cnt++;
        }
        this.buttons.add(new Button(100, this.width / 2 - 100, this.height / 6 + 44, "Input: None??"));
        this.buttons.add(new Button(101, this.width / 2 - 100, this.height / 6, "Microphone: "));
        int i2 = 1;
        this.buttons.add(new Button(102, this.width / 2 - 100, this.height / 6 + 168 - 24, "Apply"));
        this.buttons.add(new Button(103, this.width / 2 - 100, this.height / 6 + 72 + 12, "Loopback Voice"));
        this.buttons.add(new Button(200, this.width / 2 - 100, this.height / 6 + 168, "Done"));
        this.buttons.add(new Slider(104, this.width / 2 - 100, this.height / 6 + 22, 200, 20, i2, this.mc.options.sliderString(i2), this.mc.options.sliders[i2].value));
        this.microphoneSelected = this.mc.options.microphone;
        ((Button)this.buttons.get((int)2)).enabled = false;
        if (!this.mc.isOnlineClient()) {
            ((Button)this.buttons.get((int)0)).enabled = false;
            ((Button)this.buttons.get((int)1)).enabled = false;
        }
        this.updatemicrophone();
    }

    private void updatemicrophone() {
        Mixer.Info[] mixerInfo = AudioSystem.getMixerInfo();
        if (mixerInfo == null || mixerInfo.length < 1) {
            this.noneDetected = true;
        }
        if (!this.noneDetected) {
            String mic = Normalizer.normalize(mixerInfo[this.microphoneSelected].getName(), Normalizer.Form.NFKD).replaceAll("[^\\p{ASCII}]", "");
            ((Button)this.buttons.get((int)0)).displayString = "Input: " + mic;
        } else {
            ((Button)this.buttons.get((int)0)).enabled = false;
            ((Button)this.buttons.get((int)1)).enabled = false;
            ((Button)this.buttons.get((int)2)).enabled = false;
        }
        ((Button)this.buttons.get((int)1)).displayString = "Microphone: " + (this.mc.options.voice ? "ON" : "OFF");
        ((Button)this.buttons.get((int)3)).displayString = "Loopback Voice: " + (this.mc.micThread.loopback ? "ON" : "OFF");
    }

    @Override
    protected final void buttonClicked(Button button) {
        if (button.enabled) {
            if (button.id == 100) {
                if (this.mc.isOnlineClient()) {
                    ((Button)this.buttons.get((int)2)).enabled = true;
                }
                ++this.microphoneSelected;
                if (this.microphoneSelected > this.max) {
                    this.microphoneSelected = 0;
                }
                while (!this.trymicrophone(this.microphoneSelected)) {
                    ++this.microphoneSelected;
                    if (this.microphoneSelected <= this.max) continue;
                    this.microphoneSelected = 0;
                }
                this.updatemicrophone();
            }
            if (button.id == 101) {
                boolean bl = this.mc.options.voice = !this.mc.options.voice;
                if (!this.mc.options.voice) {
                    this.mc.micThread.closeInput();
                } else {
                    if (this.mc.micThread == null) {
                        this.mc.micThread = new VoiceChat(this.mc);
                    }
                    try {
                        this.mc.micThread.openInput(this.mc.options.microphone);
                    }
                    catch (LineUnavailableException e2) {
                        e2.printStackTrace();
                    }
                }
                this.updatemicrophone();
            }
            if (button.id == 103 && this.mc.micThread != null) {
                this.mc.micThread.loopback = !this.mc.micThread.loopback;
                this.updatemicrophone();
            }
            if (button.id == 102) {
                if (this.mc.options.microphone != this.microphoneSelected) {
                    this.mc.micThread.closeInput();
                    this.mc.options.microphone = this.microphoneSelected;
                    if (this.mc.options.voice) {
                        try {
                            this.mc.micThread.openInput(this.mc.options.microphone);
                        }
                        catch (LineUnavailableException e3) {
                            e3.printStackTrace();
                        }
                    }
                    this.updatemicrophone();
                }
                ((Button)this.buttons.get((int)2)).enabled = false;
                this.mc.options.saveOptions();
            }
            if (button.id == 200) {
                this.mc.options.saveOptions();
                this.mc.setScreen(this.parent);
            }
        }
    }

    private boolean trymicrophone(int mic) {
        Mixer mixer = AudioSystem.getMixer(this.mixerInfo[mic]);
        try {
            mixer.open();
        }
        catch (LineUnavailableException e2) {
            e2.printStackTrace();
            return false;
        }
        DataLine.Info dataformat = new DataLine.Info(TargetDataLine.class, this.mc.micThread.format);
        if (!mixer.isLineSupported(dataformat) || !dataformat.isFormatSupported(this.mc.micThread.format)) {
            try {
                mixer.close();
            }
            catch (Exception e3) {
                e3.printStackTrace();
                return false;
            }
            return false;
        }
        try {
            mixer.close();
        }
        catch (Exception e4) {
            e4.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public final void render(int xSize_lo, int ySize_lo, float f3) {
        this.drawBackground();
        VoiceChatScreen.drawCenteredString(this.font, this.title, this.width / 2, 20, 0xFFFFFF);
        if (this.noneDetected) {
            VoiceChatScreen.drawCenteredString(this.font, "No input detected!", this.width / 2, 48, 0xFFFFFF);
        }
        if (this.mc.micThread != null && this.mc.micThread.broken) {
            VoiceChatScreen.drawCenteredString(this.font, "Failed to turn on input! Retry again or try a different input.", this.width / 2, 32, 0xAA2222);
        }
        super.render(xSize_lo, ySize_lo, f3);
    }
}

