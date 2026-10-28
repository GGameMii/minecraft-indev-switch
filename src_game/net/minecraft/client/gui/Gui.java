/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client.gui;

import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.Tesselator;
import org.lwjgl.opengl.GL11;

public class Gui {
    protected float zLevel = 0.0f;

    protected static void drawRect(int n2, int n3, int n4, int n5, int n6) {
        float f2 = (float)(n6 >>> 24) / 255.0f;
        float f3 = (float)(n6 >> 16 & 0xFF) / 255.0f;
        float f4 = (float)(n6 >> 8 & 0xFF) / 255.0f;
        float f5 = (float)(n6 & 0xFF) / 255.0f;
        Tesselator tesselator = Tesselator.instance;
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glColor4f((float)f3, (float)f4, (float)f5, (float)f2);
        tesselator.begin();
        tesselator.addVertex(n2, n5, 0.0f);
        tesselator.addVertex(n4, n5, 0.0f);
        tesselator.addVertex(n4, n3, 0.0f);
        tesselator.addVertex(n2, n3, 0.0f);
        tesselator.end();
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
    }

    protected static void drawGradientRect(int n2, int n3, int n4, int n5, int n6, int n7) {
        float f2 = (float)(n6 >>> 24) / 255.0f;
        float f3 = (float)(n6 >> 16 & 0xFF) / 255.0f;
        float f4 = (float)(n6 >> 8 & 0xFF) / 255.0f;
        float f5 = (float)(n6 & 0xFF) / 255.0f;
        float f6 = (float)(n7 >>> 24) / 255.0f;
        float f7 = (float)(n7 >> 16 & 0xFF) / 255.0f;
        float f8 = (float)(n7 >> 8 & 0xFF) / 255.0f;
        float f9 = (float)(n7 & 0xFF) / 255.0f;
        GL11.glDisable((int)3553);
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3008);
        GL11.glBlendFunc((int)770, (int)771);
        Tesselator tesselator = Tesselator.instance;
        Tesselator.instance.begin();
        tesselator.setColorRGBA_F(f3, f4, f5, f2);
        tesselator.addVertex(n4, n3, 0.0f);
        tesselator.addVertex(n2, n3, 0.0f);
        tesselator.setColorRGBA_F(f7, f8, f9, f6);
        tesselator.addVertex(n2, n5, 0.0f);
        tesselator.addVertex(n4, n5, 0.0f);
        tesselator.end();
        GL11.glDisable((int)3042);
        GL11.glEnable((int)3008);
        GL11.glEnable((int)3553);
    }

    public static void drawCenteredString(Font font, String string, int n2, int n3, int n4) {
        font.drawShadow(string, n2 - font.width(string) / 2, n3, n4);
    }

    public static void drawString(Font font, String string, int n2, int n3, int n4) {
        font.drawShadow(string, n2, n3, n4);
    }

    public final void drawTexturedModalRect(int n2, int n3, int n4, int n5, int n6, int n7) {
        Tesselator tesselator = Tesselator.instance;
        Tesselator.instance.begin();
        tesselator.vertexUV(n2, n3 + n7, this.zLevel, (float)n4 * 0.00390625f, (float)(n5 + n7) * 0.00390625f);
        tesselator.vertexUV(n2 + n6, n3 + n7, this.zLevel, (float)(n4 + n6) * 0.00390625f, (float)(n5 + n7) * 0.00390625f);
        tesselator.vertexUV(n2 + n6, n3, this.zLevel, (float)(n4 + n6) * 0.00390625f, (float)n5 * 0.00390625f);
        tesselator.vertexUV(n2, n3, this.zLevel, (float)n4 * 0.00390625f, (float)n5 * 0.00390625f);
        tesselator.end();
    }
}

