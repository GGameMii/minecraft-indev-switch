/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.e;

import java.io.File;
import net.minecraft.a.c.e;
import net.minecraft.client.e.a;
import net.minecraft.client.e.b;
import net.minecraft.client.q;
import paulscode.sound.SoundSystem;
import paulscode.sound.SoundSystemConfig;
import paulscode.sound.codecs.CodecJOrbis;
import paulscode.sound.codecs.CodecWav;
import paulscode.sound.libraries.LibraryLWJGLOpenAL;

public final class c {
    private SoundSystem a;
    private b b = new b();
    private b c = new b();
    private int d = 0;
    private q e;
    private boolean f = false;

    public final void a(q q2) {
        this.e = q2;
        if (!this.f && (q2.b || q2.a)) {
            this.c();
        }
    }

    private void c() {
        try {
            boolean bl = this.e.b;
            boolean bl2 = this.e.a;
            this.e.b = false;
            this.e.a = false;
            this.e.a();
            SoundSystemConfig.addLibrary(LibraryLWJGLOpenAL.class);
            SoundSystemConfig.setCodec("ogg", CodecJOrbis.class);
            SoundSystemConfig.setCodec("wav", CodecWav.class);
            this.a = new SoundSystem();
            this.e.b = bl;
            this.e.a = bl2;
            this.e.a();
        }
        catch (Throwable throwable) {
            System.err.println("error linking with the LibraryJavaSound plug-in");
        }
        this.f = true;
    }

    public final void a() {
        if (!this.f && (this.e.b || this.e.a)) {
            this.c();
        }
        if (!this.e.a) {
            this.a.stop("BgMusic");
        }
    }

    public final void b() {
        if (this.f) {
            this.a.cleanup();
        }
    }

    public final void a(String string, File file) {
        this.b.a(string, file);
    }

    public final void b(String string, File file) {
        this.c.a(string, file);
    }

    public final void a(float f2, float f3, float f4) {
        if (!this.f || !this.e.a) {
            return;
        }
        if (!this.a.playing("BgMusic")) {
            a a2 = this.c.a("calm");
            this.a.newStreamingSource(true, "BgMusic", a2.b, a2.a, false, f2, f3, f4, 2, 32.0f);
            this.a.play("BgMusic");
        }
    }

    public final void a(e e2, float f2) {
        if (!this.f || !this.e.b) {
            return;
        }
        if (e2 == null) {
            return;
        }
        float f3 = e2.q + (e2.o - e2.q) * f2;
        float f4 = e2.p + (e2.n - e2.p) * f2;
        float f5 = e2.e + (e2.h - e2.e) * f2;
        float f6 = e2.f + (e2.i - e2.f) * f2;
        float f7 = e2.g + (e2.j - e2.g) * f2;
        f2 = a.a.b(-f4 * ((float)Math.PI / 180) - (float)Math.PI);
        f4 = a.a.a(-f4 * ((float)Math.PI / 180) - (float)Math.PI);
        float f8 = a.a.b(-f3 * ((float)Math.PI / 180));
        f3 = a.a.a(-f3 * ((float)Math.PI / 180));
        float f9 = -f4 * f8;
        float f10 = -f2 * f8;
        f4 = -f4 * f3;
        f2 = -f2 * f3;
        this.a.setListenerPosition(f5, f6, f7);
        this.a.setListenerOrientation(f9, f3, f10, f4, f8, f2);
    }

    public final void a(String object, float f2, float f3, float f4, float f5, float f6) {
        if (!this.f || !this.e.b) {
            return;
        }
        if ((object = this.b.a((String)object)) != null && f5 > 0.0f) {
            this.d = (this.d + 1) % 256;
            String string = "sound_" + this.d;
            float f7 = 16.0f;
            if (f5 > 1.0f) {
                f7 = 16.0f * f5;
            }
            this.a.newSource(f5 > 1.0f, string, ((a)object).b, ((a)object).a, false, f2, f3, f4, 2, f7);
            this.a.setPitch(string, f6);
            if (f5 > 1.0f) {
                f5 = 1.0f;
            }
            this.a.setVolume(string, f5);
            this.a.play(string);
        }
    }

    public final void a(String object, float f2, float f3) {
        if (!this.f || !this.e.b) {
            return;
        }
        if ((object = this.b.a((String)object)) != null) {
            this.d = (this.d + 1) % 256;
            String string = "sound_" + this.d;
            this.a.newSource(false, string, ((a)object).b, ((a)object).a, false, 0.0f, 0.0f, 0.0f, 0, 0.0f);
            this.a.setPitch(string, 1.0f);
            this.a.setVolume(string, 0.25f);
            this.a.play(string);
        }
    }
}

