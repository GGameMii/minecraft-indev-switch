/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client;

import a.a;
import net.minecraft.client.a.d;
import net.minecraft.client.c.e;
import net.minecraft.client.c.g;
import net.minecraft.client.c.i;
import net.minecraft.client.c.n;
import org.lwjgl.opengl.GL11;

public final class r
extends i {
    private float a = 0.0f;
    private String[] i = new String[]{"Pre-beta!", "As seen on TV!", "Awesome!", "100% pure!", "May contain nuts!", "Better than Prey!", "More polygons!", "Sexy!", "Limited edition!", "Flashing letters!", "Made by Notch!", "Coming soon!", "Best in class!", "When it's finished!", "Absolutely dragon free!", "Excitement!", "More than 5000 sold!", "One of a kind!", "700+ hits on YouTube!", "Indev!", "Spiders everywhere!", "Check it out!", "Holy cow, man!", "It's a game!", "Made in Sweden!", "Uses LWJGL!", "Reticulating splines!", "Minecraft!", "Yaaay!", "Alpha version!", "Singleplayer!", "Keyboard compatible!", "Undocumented!", "Ingots!", "Exploding creepers!", "That's not a moon!", "l33t!", "Create!", "Survive!", "Dungeon!", "Exclusive!", "The bee's knees!", "Down with O.P.P.!", "Closed source!", "Classy!", "Wow!", "Not on steam!", "9.95 euro!", "Half price!", "Oh man!", "Check it out!", "Awesome community!", "Pixels!", "Teetsuuuuoooo!", "Kaaneeeedaaaa!", "Now with difficulty!", "Enhanced!", "90% bug free!", "Pretty!", "12 herbs and spices!", "Fat free!", "Absolutely no memes!", "Free dental!", "Ask your doctor!", "Minors welcome!", "Cloud computing!", "Legal in Finland!", "Hard to label!", "Technically good!", "Bringing home the bacon!", "Indie!", "GOTY!", "Ceci n'est pas une title screen!", "Euclidian!", "Now in 3D!", "Inspirational!", "Herregud!", "Complex cellular automata!", "Yes, sir!", "Played by cowboys!", "OpenGL 1.1!", "Thousands of colors!", "Try it!", "Age of Wonders is better!", "Try the mushroom stew!", "Sensational!", "Hot tamale, hot hot tamale!", "Play him off, keyboard cat!", "Guaranteed!", "Macroscopic!", "Bring it on!", "Random splash!", "Call your mother!", "Monster infighting!", "Loved by millions!", "Ultimate edition!", "Freaky!", "You've got a brand new key!", "Water proof!", "Uninflammable!", "Whoa, dude!", "All inclusive!", "Tell your friends!", "NP is not in P!", "Notch <3 Ez!", "Music by C418!"};
    private String j = this.i[(int)(Math.random() * (double)this.i.length)];

    public final void f_() {
        this.a += 0.01f;
    }

    protected final void a(char c2, int n2) {
    }

    public final void b() {
        this.e.clear();
        this.e.add(new net.minecraft.client.c.r(1, this.c / 2 - 100, this.d / 4 + 48, "Generate new level..."));
        this.e.add(new net.minecraft.client.c.r(2, this.c / 2 - 100, this.d / 4 + 72, "Load level.."));
        this.e.add(new net.minecraft.client.c.r(3, this.c / 2 - 100, this.d / 4 + 96, "Play tutorial level"));
        this.e.add(new net.minecraft.client.c.r(0, this.c / 2 - 100, this.d / 4 + 120 + 12, "Options..."));
        ((net.minecraft.client.c.r)this.e.get((int)2)).c = false;
        if (this.b.h == null) {
            ((net.minecraft.client.c.r)this.e.get((int)1)).c = false;
        }
    }

    protected final void a(net.minecraft.client.c.r r2) {
        if (r2.b == 0) {
            this.b.a(new g(this, this.b.w));
        }
        if (r2.b == 1) {
            this.b.a(new n(this));
        }
        if (this.b.h != null && r2.b == 2) {
            this.b.a(new e(this));
        }
    }

    public final void a(int n2, int n3, float f2) {
        this.h();
        d d2 = net.minecraft.client.a.d.a;
        GL11.glBindTexture((int)3553, (int)this.b.m.a("/gui/logo.png"));
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        d2.b(0xFFFFFF);
        this.b((this.c - 256) / 2, 30, 0, 0, 256, 49);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)(this.c / 2 + 90), (float)70.0f, (float)0.0f);
        GL11.glRotatef((float)-20.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        float f3 = 1.8f - a.a.e(a.a.a((float)(System.currentTimeMillis() % 1000L) / 1000.0f * (float)Math.PI * 2.0f) * 0.1f);
        f3 = f3 * 100.0f / (float)(this.g.a(this.j) + 32);
        GL11.glScalef((float)f3, (float)f3, (float)f3);
        r.a(this.g, this.j, 0, -8, 0xFFFF00);
        GL11.glPopMatrix();
        String string = "Copyright Mojang Specifications. Do not distribute.";
        r.b(this.g, string, this.c - this.g.a(string) - 2, this.d - 10, 0xFFFFFF);
        long l2 = Runtime.getRuntime().maxMemory();
        long l3 = Runtime.getRuntime().totalMemory();
        long l4 = Runtime.getRuntime().freeMemory();
        long l5 = l2 - l4;
        string = "Free memory: " + l5 * 100L / l2 + "% of " + l2 / 1024L / 1024L + "MB";
        r.b(this.g, string, this.c - this.g.a(string) - 2, 2, 0x808080);
        string = "Allocated memory: " + l3 * 100L / l2 + "% (" + l3 / 1024L / 1024L + "MB)";
        r.b(this.g, string, this.c - this.g.a(string) - 2, 12, 0x808080);
        super.a(n2, n3, f2);
    }
}

