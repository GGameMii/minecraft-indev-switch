/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.sound;

import java.io.File;
import net.minecraft.client.GameOptions;
import net.minecraft.client.sound.SoundPool;
import net.minecraft.client.sound.SoundPoolEntry;
import net.minecraft.world.entity.LivingEntity;
import paulscode.sound.SoundSystem;
import paulscode.sound.SoundSystemConfig;
import paulscode.sound.codecs.CodecJOrbis;
import paulscode.sound.codecs.CodecWav;
import paulscode.sound.libraries.LibraryLWJGLOpenAL;
import util.Mth;

public final class SoundManager {
    public SoundSystem sndSystem;
    private SoundPool soundPoolSounds = new SoundPool();
    private SoundPool soundPoolMusic = new SoundPool();
    private int latestSoundID = 0;
    private GameOptions options;
    public boolean loaded = false;

    public final void loadSoundSettings(GameOptions gameSettings) {
        this.options = gameSettings;
        if (!this.loaded && (gameSettings.sound || gameSettings.music)) {
            this.tryToSetLibraryAndCodecs();
        }
    }

    private void tryToSetLibraryAndCodecs() {
        try {
            boolean snd = this.options.sound;
            boolean mus = this.options.music;
            this.options.sound = false;
            this.options.music = false;
            this.options.saveOptions();
            SoundSystemConfig.addLibrary(LibraryLWJGLOpenAL.class);
            SoundSystemConfig.setCodec("ogg", CodecJOrbis.class);
            SoundSystemConfig.setCodec("wav", CodecWav.class);
            this.sndSystem = new SoundSystem();
            this.options.sound = snd;
            this.options.music = mus;
            this.options.saveOptions();
        }
        catch (Throwable throwable3) {
            System.err.println("error linking with the LibraryJavaSound plug-in");
        }
        this.loaded = true;
    }

    public final void onSoundOptionsChanged() {
        if (!this.loaded && (this.options.sound || this.options.music)) {
            this.tryToSetLibraryAndCodecs();
        }
        if (!this.options.music) {
            this.sndSystem.stop("BgMusic");
        }
    }

    public final void closeMinecraft() {
        if (this.loaded) {
            this.sndSystem.cleanup();
        }
    }

    public final void addSound(String string1, File file2) {
        this.soundPoolSounds.addSound(string1, file2);
    }

    public final void addMusic(String string1, File file2) {
        this.soundPoolMusic.addSound(string1, file2);
    }

    public final void playRandomMusicIfReady(float x2, float y2, float z2) {
        if (this.loaded && this.options.music && !this.sndSystem.playing("BgMusic")) {
            SoundPoolEntry entry = this.soundPoolMusic.getRandomSoundFromSoundPool("calm");
            this.sndSystem.newStreamingSource(true, "BgMusic", entry.soundUrl, entry.soundName, false, x2, y2, z2, 2, 32.0f);
            this.sndSystem.play("BgMusic");
        }
    }

    public final void setListener(LivingEntity e2, float a2) {
        if (this.loaded && this.options.sound && e2 != null) {
            float yr = e2.yRotI + (e2.yRot - e2.yRotI) * a2;
            float xr = e2.xRotI + (e2.xRot - e2.xRotI) * a2;
            float x2 = e2.xo + (e2.x - e2.xo) * a2;
            float y2 = e2.yo + (e2.y - e2.yo) * a2;
            float z2 = e2.zo + (e2.z - e2.zo) * a2;
            a2 = Mth.cos(-xr * ((float)Math.PI / 180) - (float)Math.PI);
            xr = Mth.sin(-xr * ((float)Math.PI / 180) - (float)Math.PI);
            float f7 = Mth.cos(-yr * ((float)Math.PI / 180));
            yr = Mth.sin(-yr * ((float)Math.PI / 180));
            float f8 = -xr * f7;
            float f9 = -a2 * f7;
            xr = -xr * yr;
            a2 = -a2 * yr;
            this.sndSystem.setListenerPosition(x2, y2, z2);
            this.sndSystem.setListenerOrientation(f8, yr, f9, xr, f7, a2);
        }
    }

    public final void playSound(String string1, float f2, float f3, float f4, float f5, float f6) {
        SoundPoolEntry soundPoolEntry9;
        if (this.loaded && this.options.sound && (soundPoolEntry9 = this.soundPoolSounds.getRandomSoundFromSoundPool(string1)) != null && f5 > 0.0f) {
            this.latestSoundID = (this.latestSoundID + 1) % 256;
            String string7 = "sound_" + this.latestSoundID;
            float f8 = 16.0f;
            if (f5 > 1.0f) {
                f8 = 16.0f * f5;
            }
            this.sndSystem.newSource(f5 > 1.0f, string7, soundPoolEntry9.soundUrl, soundPoolEntry9.soundName, false, f2, f3, f4, 2, f8);
            this.sndSystem.setPitch(string7, f6);
            if (f5 > 1.0f) {
                f5 = 1.0f;
            }
            this.sndSystem.setVolume(string7, f5);
            this.sndSystem.play(string7);
        }
    }

    public final void playSoundFX(String string1, float f2, float f3) {
        SoundPoolEntry entry;
        if (this.loaded && this.options.sound && (entry = this.soundPoolSounds.getRandomSoundFromSoundPool(string1)) != null) {
            this.latestSoundID = (this.latestSoundID + 1) % 256;
            String id = "sound_" + this.latestSoundID;
            this.sndSystem.newSource(false, id, entry.soundUrl, entry.soundName, false, 0.0f, 0.0f, 0.0f, 0, 0.0f);
            this.sndSystem.setPitch(id, 1.0f);
            this.sndSystem.setVolume(id, 0.25f);
            this.sndSystem.play(id);
        }
    }
}

