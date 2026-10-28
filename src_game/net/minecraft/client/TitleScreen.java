/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client;

import net.minecraft.client.CreditsScreen;
import net.minecraft.client.gui.Button;
import net.minecraft.client.gui.LoadLevelScreen;
import net.minecraft.client.gui.NewLevelScreen;
import net.minecraft.client.gui.OptionsScreen;
import net.minecraft.client.gui.Screen;
import net.minecraft.client.gui.ServerListScreen;
import net.minecraft.client.renderer.Tesselator;
import org.lwjgl.opengl.GL11;
import util.Mth;

public final class TitleScreen
extends Screen {
    private float updateCounter = 0.0f;
    private String[] splashes = new String[]{"Pre-beta!", "As seen on TV!", "Awesome!", "100% pure!", "May contain nuts!", "Better than Prey!", "More polygons!", "Sexy!", "Limited edition!", "Flashing letters!", "Made by Notch!", "Coming soon!", "Best in class!", "When it's finished!", "Absolutely dragon free!", "Excitement!", "More than 5000 sold!", "One of a kind!", "700+ hits on YouTube!", "Indev!", "Spiders everywhere!", "Check it out!", "Holy cow, man!", "It's a game!", "Made in Sweden!", "Uses LWJGL!", "Reticulating splines!", "Minecraft!", "Yaaay!", "Alpha version!", "Singleplayer!", "Keyboard compatible!", "Undocumented!", "Ingots!", "Exploding creepers!", "That's not a moon!", "l33t!", "Create!", "Survive!", "Dungeon!", "Exclusive!", "The bee's knees!", "Down with O.P.P.!", "Closed source!", "Classy!", "Wow!", "Not on steam!", "9.95 euro!", "Half price!", "Oh man!", "Check it out!", "Awesome community!", "Pixels!", "Teetsuuuuoooo!", "Kaaneeeedaaaa!", "Now with difficulty!", "Enhanced!", "90% bug free!", "Pretty!", "12 herbs and spices!", "Fat free!", "Absolutely no memes!", "Free dental!", "Ask your doctor!", "Minors welcome!", "Cloud computing!", "Legal in Finland!", "Hard to label!", "Technically good!", "Bringing home the bacon!", "Indie!", "GOTY!", "Ceci n'est pas une title screen!", "Euclidian!", "Now in 3D!", "Inspirational!", "Herregud!", "Complex cellular automata!", "Yes, sir!", "Played by cowboys!", "OpenGL 1.1!", "Thousands of colors!", "Try it!", "Age of Wonders is better!", "Try the mushroom stew!", "Sensational!", "Hot tamale, hot hot tamale!", "Play him off, keyboard cat!", "Guaranteed!", "Macroscopic!", "Bring it on!", "Random splash!", "Call your mother!", "Monster infighting!", "Loved by millions!", "Ultimate edition!", "Freaky!", "You've got a brand new key!", "Water proof!", "Uninflammable!", "Whoa, dude!", "All inclusive!", "Tell your friends!", "NP is not in P!", "Notch <3 Ez!", "Music by C418!", "Multiplayer!"};
    private String currentSplash = this.splashes[(int)(Math.random() * (double)this.splashes.length)];

    @Override
    public final void tick() {
        this.updateCounter += 0.01f;
    }

    @Override
    protected final void keyTyped(char c2, int n2) {
    }

    @Override
    public final void init() {
        this.buttons.clear();
        this.buttons.add(new Button(1, this.width / 2 - 100, this.height / 4 + 48, "Generate new level..."));
        this.buttons.add(new Button(4, this.width / 2 - 100, this.height / 4 + 72, "Multiplayer"));
        this.buttons.add(new Button(2, this.width / 2 - 100, this.height / 4 + 96, "Load level.."));
        this.buttons.add(new Button(0, this.width / 2 - 100, this.height / 4 + 120 + 12, "Options..."));
        this.buttons.add(new Button(5, this.width / 2 - 100 + 201, this.height / 4 + 120 + 12, 20, 20, "?"));
        if (this.mc.user == null) {
            String name = (this.mc.options != null && this.mc.options.username != null) ? this.mc.options.username : "Steve";
            this.mc.user = new net.minecraft.network.User(name, "");
        }
    }

    @Override
    protected final void buttonClicked(Button button) {
        if (button.id == 0) {
            this.mc.setScreen(new OptionsScreen(this, this.mc.options));
        }
        if (button.id == 1) {
            this.mc.setScreen(new NewLevelScreen(this));
        }
        if (this.mc.user != null && button.id == 2) {
            this.mc.setScreen(new LoadLevelScreen(this));
        }
        if (button.id == 4) {
            this.mc.setScreen(new ServerListScreen(this));
        }
        if (button.id == 5) {
            this.mc.setScreen(new CreditsScreen(this));
        }
    }

    @Override
    public final void render(int n2, int n3, float f2) {
        this.drawBackground();
        Tesselator tesselator = Tesselator.instance;
        GL11.glBindTexture((int)3553, (int)this.mc.renderEngine.getTexture("/gui/logo.png"));
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        tesselator.color(0xFFFFFF);
        this.drawTexturedModalRect((this.width - 256) / 2, 30, 0, 0, 256, 49);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)(this.width / 2 + 90), (float)70.0f, (float)0.0f);
        GL11.glRotatef((float)-20.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        float f3 = (1.8f - Mth.abs(Mth.sin((float)(System.currentTimeMillis() % 1000L) / 1000.0f * (float)Math.PI * 2.0f) * 0.1f)) * 100.0f / (float)(this.font.width(this.currentSplash) + 32);
        GL11.glScalef((float)f3, (float)f3, (float)f3);
        TitleScreen.drawCenteredString(this.font, this.currentSplash, 0, -8, 0xFFFF00);
        GL11.glPopMatrix();
        String string = "Copyright Mojang Specifications. Do not distribute.";
        TitleScreen.drawString(this.font, string, this.width - this.font.width(string) - 2, this.height - 10, 0xFFFFFF);
        long l2 = Runtime.getRuntime().maxMemory();
        long l3 = Runtime.getRuntime().totalMemory();
        long l4 = Runtime.getRuntime().freeMemory();
        long l5 = l2 - l4;
        string = "Free memory: " + l5 * 100L / l2 + "% of " + l2 / 1024L / 1024L + "MB";
        TitleScreen.drawString(this.font, string, this.width - this.font.width(string) - 2, 2, 0x808080);
        string = "Allocated memory: " + l3 * 100L / l2 + "% (" + l3 / 1024L / 1024L + "MB)";
        TitleScreen.drawString(this.font, string, this.width - this.font.width(string) - 2, 12, 0x808080);
        super.render(n2, n3, f2);
        String userStr = "Player: " + (this.mc.user != null ? this.mc.user.name : "Steve");
        TitleScreen.drawString(this.font, userStr, 2, this.height - 10, 0x00FF88);
    }
}

