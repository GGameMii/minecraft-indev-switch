/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client;

import java.io.ByteArrayOutputStream;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.TargetDataLine;
import net.minecraft.client.Minecraft;
import net.minecraft.network.packet.MicrophonePacket;

public class VoiceChat
extends Thread {
    private Minecraft game;
    public AudioFormat format = this.getAudioFormat();
    public boolean running;
    public boolean ready = false;
    public boolean loopback = false;
    public boolean muted = false;
    public boolean broken = false;

    public AudioFormat getAudioFormat() {
        float sampleRate = 16000.0f;
        int sampleSizeBits = 16;
        int channels = 1;
        boolean signed = true;
        boolean bigEndian = false;
        return new AudioFormat(sampleRate, sampleSizeBits, channels, signed, bigEndian);
    }

    public VoiceChat(Minecraft game) {
        this.game = game;
    }

    @Override
    public void run() {
        System.out.println("Starting mic thread!");
        this.running = true;
        while (this.running) {
            this.initMicInput();
        }
        System.out.println("Ending mic thread.");
    }

    public void initMicInput() {
        try {
            if (this.ready) {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                while (this.game.options.voice && this.running) {
                    int count;
                    byte[] tmpBuff = new byte[1024];
                    byte[] tmpBuffB = new byte[1024];
                    if (this.game.mic.available() == 0 || (count = this.game.mic.read(tmpBuff, 0, tmpBuff.length)) <= 0) continue;
                    out.write(tmpBuff, 0, count);
                    tmpBuffB = out.toByteArray();
                    if (tmpBuffB.length >= 1024) {
                        int i2 = 0;
                        while (i2 < tmpBuff.length) {
                            tmpBuff[i2] = tmpBuffB[i2] > 1 ? (byte)((float)tmpBuffB[i2] * this.game.options.sliders[1].value * 10.0f) : tmpBuffB[i2];
                            ++i2;
                        }
                    }
                    this.game.networkClient.addToQueue(new MicrophonePacket(tmpBuff, -1));
                    if (this.loopback) {
                        this.game.send(this.format, tmpBuff, this.game.player);
                    }
                    out.reset();
                }
                System.out.println("Mic input closed!");
                out.close();
                this.closeInput();
            }
        }
        catch (Exception e2) {
            e2.printStackTrace();
            this.closeInput();
        }
    }

    public void openInput(int port) throws LineUnavailableException {
        this.broken = false;
        Mixer.Info[] mixerInfo = AudioSystem.getMixerInfo();
        DataLine.Info dataformat = new DataLine.Info(TargetDataLine.class, this.format);
        System.out.println("Opening input on " + mixerInfo[port].getName());
        this.game.targetMixer = AudioSystem.getMixer(mixerInfo[port]);
        try {
            if (this.game.targetMixer.isOpen()) {
                this.game.targetMixer.close();
            }
            this.game.targetMixer.open();
            if (this.game.targetMixer.getLine(dataformat).isOpen()) {
                this.game.targetMixer.getLine(dataformat).close();
            }
            this.game.mic = (TargetDataLine)this.game.targetMixer.getLine(dataformat);
        }
        catch (Exception e2) {
            this.broken = true;
            try {
                this.game.targetMixer.close();
            }
            catch (Exception e22) {
                e22.printStackTrace();
            }
            e2.printStackTrace();
        }
        if (!this.broken) {
            if (this.game.mic.isOpen()) {
                this.game.mic.close();
            }
            try {
                this.game.mic.open(this.format);
            }
            catch (Exception e3) {
                e3.printStackTrace();
                this.broken = true;
            }
            if (!this.broken) {
                this.game.mic.start();
                this.ready = true;
                if (!this.running) {
                    this.start();
                }
            }
        }
    }

    public void closeInput() {
        this.broken = false;
        if (this.game.mic != null) {
            this.game.mic.flush();
            this.game.mic.close();
        }
        if (this.game.targetMixer != null) {
            this.game.targetMixer.close();
        }
        this.game.options.voice = false;
        this.ready = false;
        this.interrupt();
    }
}

