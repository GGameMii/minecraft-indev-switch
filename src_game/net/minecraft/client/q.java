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
import net.minecraft.client.d;
import net.minecraft.client.h;
import org.lwjgl.input.Keyboard;

public final class q {
    private static final String[] w = new String[]{"FAR", "NORMAL", "SHORT", "TINY"};
    private static final String[] x = new String[]{"Peaceful", "Easy", "Normal", "Hard"};
    public boolean a = true;
    public boolean b = true;
    public boolean c = false;
    public boolean d = false;
    public int e = 0;
    public boolean f = true;
    public boolean g = false;
    public boolean h = false;
    public h i = new h("Forward", 17);
    public h j = new h("Left", 30);
    public h k = new h("Back", 31);
    public h l = new h("Right", 32);
    public h m = new h("Jump", 57);
    public h n = new h("Inventory", 23);
    public h o = new h("Drop", 16);
    private h y = new h("Chat", 20);
    public h p = new h("Toggle fog", 33);
    public h q = new h("Save location", 28);
    public h r = new h("Load location", 19);
    public h[] s = new h[]{this.i, this.j, this.k, this.l, this.m, this.o, this.n, this.y, this.p, this.q, this.r};
    private d z;
    private File A;
    public int t = 9;
    public int u = 2;
    public boolean v = false;

    public q(d d2, File file) {
        this.z = d2;
        this.A = new File(file, "options.txt");
        this.b();
    }

    public final String a(int n2) {
        return this.s[n2].a + ": " + Keyboard.getKeyName((int)this.s[n2].b);
    }

    public final void a(int n2, int n3) {
        this.s[n2].b = n3;
        this.a();
    }

    public final void b(int n2, int n3) {
        if (n2 == 0) {
            this.a = !this.a;
            this.z.x.a();
        }
        if (n2 == 1) {
            this.b = !this.b;
            this.z.x.a();
        }
        if (n2 == 2) {
            boolean bl = this.c = !this.c;
        }
        if (n2 == 3) {
            boolean bl = this.d = !this.d;
        }
        if (n2 == 4) {
            this.e = this.e + n3 & 3;
        }
        if (n2 == 5) {
            boolean bl = this.f = !this.f;
        }
        if (n2 == 6) {
            this.g = !this.g;
            this.z.m.b();
        }
        if (n2 == 7) {
            boolean bl = this.h = !this.h;
        }
        if (n2 == 8) {
            this.u = this.u + n3 & 3;
        }
        this.a();
    }

    public final String b(int n2) {
        if (n2 == 0) {
            return "Music: " + (this.a ? "ON" : "OFF");
        }
        if (n2 == 1) {
            return "Sound: " + (this.b ? "ON" : "OFF");
        }
        if (n2 == 2) {
            return "Invert mouse: " + (this.c ? "ON" : "OFF");
        }
        if (n2 == 3) {
            return "Show FPS: " + (this.d ? "ON" : "OFF");
        }
        if (n2 == 4) {
            return "Render distance: " + w[this.e];
        }
        if (n2 == 5) {
            return "View bobbing: " + (this.f ? "ON" : "OFF");
        }
        if (n2 == 6) {
            return "3d anaglyph: " + (this.g ? "ON" : "OFF");
        }
        if (n2 == 7) {
            return "Limit framerate: " + (this.h ? "ON" : "OFF");
        }
        if (n2 == 8) {
            return "Difficulty: " + x[this.u];
        }
        return "";
    }

    private void b() {
        try {
            String[] stringArray;
            if (!this.A.exists()) {
                return;
            }
            BufferedReader bufferedReader = new BufferedReader(new FileReader(this.A));
            while ((stringArray = bufferedReader.readLine()) != null) {
                if ((stringArray = stringArray.split(":"))[0].equals("music")) {
                    this.a = stringArray[1].equals("true");
                }
                if (stringArray[0].equals("sound")) {
                    this.b = stringArray[1].equals("true");
                }
                if (stringArray[0].equals("invertYMouse")) {
                    this.c = stringArray[1].equals("true");
                }
                if (stringArray[0].equals("showFrameRate")) {
                    this.d = stringArray[1].equals("true");
                }
                if (stringArray[0].equals("viewDistance")) {
                    this.e = Integer.parseInt(stringArray[1]);
                }
                if (stringArray[0].equals("bobView")) {
                    this.f = stringArray[1].equals("true");
                }
                if (stringArray[0].equals("anaglyph3d")) {
                    this.g = stringArray[1].equals("true");
                }
                if (stringArray[0].equals("limitFramerate")) {
                    this.h = stringArray[1].equals("true");
                }
                if (stringArray[0].equals("difficulty")) {
                    this.u = Integer.parseInt(stringArray[1]);
                }
                for (int i2 = 0; i2 < this.s.length; ++i2) {
                    if (!stringArray[0].equals("key_" + this.s[i2].a)) continue;
                    this.s[i2].b = Integer.parseInt(stringArray[1]);
                }
            }
            bufferedReader.close();
            return;
        }
        catch (Exception exception) {
            System.out.println("Failed to load options");
            exception.printStackTrace();
            return;
        }
    }

    public final void a() {
        try {
            PrintWriter printWriter = new PrintWriter(new FileWriter(this.A));
            printWriter.println("music:" + this.a);
            printWriter.println("sound:" + this.b);
            printWriter.println("invertYMouse:" + this.c);
            printWriter.println("showFrameRate:" + this.d);
            printWriter.println("viewDistance:" + this.e);
            printWriter.println("bobView:" + this.f);
            printWriter.println("anaglyph3d:" + this.g);
            printWriter.println("limitFramerate:" + this.h);
            printWriter.println("difficulty:" + this.u);
            for (int i2 = 0; i2 < this.s.length; ++i2) {
                printWriter.println("key_" + this.s[i2].a + ":" + this.s[i2].b);
            }
            printWriter.close();
            return;
        }
        catch (Exception exception) {
            System.out.println("Failed to save options");
            exception.printStackTrace();
            return;
        }
    }
}

