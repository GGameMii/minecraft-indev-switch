/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.SourceDataLine;
import net.minecraft.client.Minecraft;

public class MicrophoneListener {
    private Minecraft game;
    private IncomingSoundListener isl = new IncomingSoundListener();
    AudioFormat format = this.getAudioFormat();
    InputStream is;
    boolean inVoice = true;

    public MicrophoneListener(Minecraft game) throws IOException {
        this.game = game;
        this.isl.runListener();
    }

    private AudioFormat getAudioFormat() {
        float sampleRate = 16000.0f;
        int sampleSizeBits = 16;
        int channels = 1;
        boolean signed = true;
        boolean bigEndian = false;
        return new AudioFormat(sampleRate, sampleSizeBits, channels, signed, bigEndian);
    }

    class IncomingSoundListener {
        IncomingSoundListener() {
        }

        public void runListener() {
            try {
                System.out.println("Listening for incoming audio.");
                Mixer.Info[] mixerInfo = AudioSystem.getMixerInfo();
                System.out.println("Available mixers:");
                int cnt = 0;
                while (cnt < mixerInfo.length) {
                    System.out.println(mixerInfo[cnt].getName());
                    ++cnt;
                }
                DataLine.Info targetDataLineInfo = new DataLine.Info(SourceDataLine.class, MicrophoneListener.this.format);
                System.out.println("CHOSE PLAY WITH: " + mixerInfo[1].getName());
                Mixer targetMixer = AudioSystem.getMixer(mixerInfo[1]);
                SourceDataLine speaker = (SourceDataLine)targetMixer.getLine(targetDataLineInfo);
                speaker.open(MicrophoneListener.this.format);
                speaker.start();
                while (!((MicrophoneListener)MicrophoneListener.this).game.sndManager.loaded) {
                    Thread.sleep(2L);
                }
                System.out.println("Starting ingame streaming");
                ((MicrophoneListener)MicrophoneListener.this).game.sndManager.sndSystem.rawDataStream(MicrophoneListener.this.format, true, "mic", ((MicrophoneListener)MicrophoneListener.this).game.player.x, ((MicrophoneListener)MicrophoneListener.this).game.player.y, ((MicrophoneListener)MicrophoneListener.this).game.player.z, 2, 32.0f);
                while (MicrophoneListener.this.inVoice) {
                    byte[] data = new byte[1024];
                    ByteArrayInputStream bais = new ByteArrayInputStream(data);
                    AudioInputStream ais = new AudioInputStream(bais, MicrophoneListener.this.format, data.length);
                    int bytesRead = 0;
                    bytesRead = ais.read(data);
                    if (bytesRead != -1) {
                        speaker.write(data, 0, bytesRead);
                        ((MicrophoneListener)MicrophoneListener.this).game.sndManager.sndSystem.feedRawAudioData("mic", data);
                    }
                    ais.close();
                    bais.close();
                }
                speaker.drain();
                speaker.close();
                System.out.println("Stopped listening to incoming audio.");
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}

