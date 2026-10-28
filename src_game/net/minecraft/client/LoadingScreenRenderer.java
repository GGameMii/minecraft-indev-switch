/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.Display
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.MinecraftError;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tesselator;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import util.IProgressUpdate;

public class LoadingScreenRenderer
implements IProgressUpdate {
    private String text;
    private Minecraft mc;
    private String title;

    public LoadingScreenRenderer(Minecraft game) {
        this.text = "";
        this.title = "";
        this.mc = game;
    }

    @Override
    public final void beginLevelLoading(String title) {
        if (!this.mc.running) {
            throw new MinecraftError();
        }
        this.title = title;
        ScaledResolution res = new ScaledResolution(this.mc.width, this.mc.height);
        int w2 = res.getScaledWidth();
        int h2 = res.getScaledHeight();
        GL11.glClear((int)256);
        GL11.glMatrixMode((int)5889);
        GL11.glLoadIdentity();
        GL11.glOrtho((double)0.0, (double)w2, (double)h2, (double)0.0, (double)100.0, (double)300.0);
        GL11.glMatrixMode((int)5888);
        GL11.glLoadIdentity();
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-200.0f);
    }

    @Override
    public final void levelLoadUpdate(String text) {
        if (!this.mc.running) {
            throw new MinecraftError();
        }
        this.text = text;
        this.setLoadingProgress(-1);
    }

    @Override
    public final void setLoadingProgress(int progress) {
        if (!this.mc.running) {
            throw new MinecraftError();
        }
        ScaledResolution res = new ScaledResolution(this.mc.width, this.mc.height);
        int w2 = res.getScaledWidth();
        int h2 = res.getScaledHeight();
        GL11.glClear((int)256);
        GL11.glMatrixMode((int)5889);
        GL11.glLoadIdentity();
        GL11.glOrtho((double)0.0, (double)w2, (double)h2, (double)0.0, (double)100.0, (double)300.0);
        GL11.glMatrixMode((int)5888);
        GL11.glLoadIdentity();
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-200.0f);
        GL11.glClear((int)16640);
        Tesselator t2 = Tesselator.instance;
        int i5 = this.mc.renderEngine.getTexture("/dirt.png");
        GL11.glBindTexture((int)3553, (int)i5);
        t2.begin();
        t2.color(0x404040);
        t2.vertexUV(0.0f, h2, 0.0f, 0.0f, (float)h2 / 32.0f);
        t2.vertexUV(w2, h2, 0.0f, (float)w2 / 32.0f, (float)h2 / 32.0f);
        t2.vertexUV(w2, 0.0f, 0.0f, (float)w2 / 32.0f, 0.0f);
        t2.vertexUV(0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        t2.end();
        if (progress >= 0) {
            i5 = w2 / 2 - 50;
            int i6 = h2 / 2 + 16;
            GL11.glDisable((int)3553);
            t2.begin();
            t2.color(0x808080);
            t2.addVertex(i5, i6, 0.0f);
            t2.addVertex(i5, i6 + 2, 0.0f);
            t2.addVertex(i5 + 100, i6 + 2, 0.0f);
            t2.addVertex(i5 + 100, i6, 0.0f);
            t2.color(0x80FF80);
            t2.addVertex(i5, i6, 0.0f);
            t2.addVertex(i5, i6 + 2, 0.0f);
            t2.addVertex(i5 + progress, i6 + 2, 0.0f);
            t2.addVertex(i5 + progress, i6, 0.0f);
            t2.end();
            GL11.glEnable((int)3553);
        }
        this.mc.font.drawShadow(this.title, (w2 - this.mc.font.width(this.title)) / 2, h2 / 2 - 4 - 16, 0xFFFFFF);
        this.mc.font.drawShadow(this.text, (w2 - this.mc.font.width(this.text)) / 2, h2 / 2 - 4 + 8, 0xFFFFFF);
        Display.update();
        try {
            Thread.yield();
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public LoadingScreenRenderer() {
    }
}

