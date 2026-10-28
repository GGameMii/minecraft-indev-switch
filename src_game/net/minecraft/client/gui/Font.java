/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client.gui;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import net.minecraft.client.GameOptions;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.client.renderer.Tesselator;
import org.lwjgl.opengl.GL11;

public class Font {
    private int[] charWidth = new int[256];
    private int fontTextureName = 0;
    private int fontDisplayLists;

    public Font(GameOptions settings, String tex, RenderEngine engine) {
        BufferedImage read;
        try {
            read = ImageIO.read(RenderEngine.class.getResourceAsStream(tex));
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        int width = read.getWidth();
        int i2 = read.getHeight();
        int[] array = new int[width * i2];
        read.getRGB(0, 0, width, i2, array, 0, width);
        int j2 = 0;
        while (j2 < 128) {
            i2 = j2 % 16;
            int n2 = j2 / 16;
            int n22 = 0;
            boolean n3 = false;
            while (n22 < 8 && !n3) {
                int n4 = (i2 << 3) + n22;
                n3 = true;
                int n5 = 0;
                while (n5 < 8 && n3) {
                    int n6 = ((n2 << 3) + n5) * width;
                    int n7 = array[n4 + n6] & 0xFF;
                    if (n7 > 128) {
                        n3 = false;
                    }
                    ++n5;
                }
                ++n22;
            }
            if (j2 == 32) {
                n22 = 4;
            }
            this.charWidth[j2] = n22;
            ++j2;
        }
        this.initFont(settings, tex, engine);
    }

    public void initFont(GameOptions options, String tex, RenderEngine engine) {
        int n2;
        int n3;
        this.fontTextureName = engine.getTexture(tex);
        this.fontDisplayLists = GL11.glGenLists((int)288);
        Tesselator t2 = Tesselator.instance;
        int i2 = 0;
        while (i2 < 256) {
            GL11.glNewList((int)(this.fontDisplayLists + i2), (int)4864);
            t2.begin();
            n3 = i2 % 16 << 3;
            n2 = i2 / 16 << 3;
            t2.vertexUV(0.0f, 7.99f, 0.0f, (float)n3 / 128.0f, ((float)n2 + 7.99f) / 128.0f);
            t2.vertexUV(7.99f, 7.99f, 0.0f, ((float)n3 + 7.99f) / 128.0f, ((float)n2 + 7.99f) / 128.0f);
            t2.vertexUV(7.99f, 0.0f, 0.0f, ((float)n3 + 7.99f) / 128.0f, (float)n2 / 128.0f);
            t2.vertexUV(0.0f, 0.0f, 0.0f, (float)n3 / 128.0f, (float)n2 / 128.0f);
            t2.end();
            GL11.glTranslatef((float)this.charWidth[i2], (float)0.0f, (float)0.0f);
            GL11.glEndList();
            ++i2;
        }
        i2 = 0;
        while (i2 < 32) {
            boolean b2;
            n3 = (i2 & 8) << 3;
            n2 = (i2 & 1) * 191 + n3;
            int n8 = ((i2 & 2) >> 1) * 191 + n3;
            int n4 = ((i2 & 4) >> 2) * 191 + n3;
            boolean bl = b2 = i2 >= 16;
            if (options.anaglyph3d) {
                int n6 = (n4 * 30 + n8 * 59 + n2 * 11) / 100;
                int n7 = (n4 * 30 + n8 * 70) / 100;
                int n9 = (n4 * 30 + n2 * 70) / 100;
                n4 = n6;
                n8 = n7;
                n2 = n9;
            }
            i2 += 2;
            if (b2) {
                n4 /= 4;
                n8 /= 4;
                n2 /= 4;
            }
            GL11.glColor4f((float)((float)n4 / 255.0f), (float)((float)n8 / 255.0f), (float)((float)n2 / 255.0f), (float)1.0f);
            ++i2;
        }
    }

    public void drawShadow(String text, int x2, int y2, int color) {
        this.draw(text, x2 + 1, y2 + 1, color, true);
        this.draw(text, x2, y2, color);
    }

    public void draw(String text, int x2, int y2, int color) {
        this.draw(text, x2, y2, color, false);
    }

    private void draw(String text, int x2, int y2, int color, boolean shadow) {
        if (text != null) {
            char[] var12 = text.toCharArray();
            if (shadow) {
                color = (color & 0xFCFCFC) >> 2;
            }
            GL11.glBindTexture((int)3553, (int)this.fontTextureName);
            Tesselator t2 = Tesselator.instance;
            Tesselator.instance.begin();
            t2.color(color);
            int var7 = 0;
            int var8 = 0;
            while (var8 < var12.length) {
                int var9;
                if (var12[var8] == '&' && var12.length > var8 + 1) {
                    color = "0123456789abcdef".indexOf(var12[var8 + 1]);
                    if (color < 0) {
                        color = 15;
                    }
                    var9 = (color & 8) << 3;
                    int var10 = (color & 1) * 191 + var9;
                    int var11 = ((color & 2) >> 1) * 191 + var9;
                    color = ((color & 4) >> 2) * 191 + var9;
                    color = color << 16 | var11 << 8 | var10;
                    var8 += 2;
                    if (shadow) {
                        color = (color & 0xFCFCFC) >> 2;
                    }
                    t2.color(color);
                }
                color = var12[var8] % 16 << 3;
                var9 = var12[var8] / 16 << 3;
                float var13 = 7.99f;
                t2.vertexUV(x2 + var7, (float)y2 + var13, 0.0f, (float)color / 128.0f, ((float)var9 + var13) / 128.0f);
                t2.vertexUV((float)(x2 + var7) + var13, (float)y2 + var13, 0.0f, ((float)color + var13) / 128.0f, ((float)var9 + var13) / 128.0f);
                t2.vertexUV((float)(x2 + var7) + var13, y2, 0.0f, ((float)color + var13) / 128.0f, (float)var9 / 128.0f);
                t2.vertexUV(x2 + var7, y2, 0.0f, (float)color / 128.0f, (float)var9 / 128.0f);
                var7 += this.charWidth[var12[var8]];
                ++var8;
            }
            t2.end();
        }
    }

    public int width(String text) {
        if (text == null) {
            return 0;
        }
        char[] charArray = text.toCharArray();
        int n2 = 0;
        int i2 = 0;
        while (i2 < charArray.length) {
            if (charArray[i2] == '&') {
                ++i2;
            } else {
                n2 += this.charWidth[charArray[i2]];
            }
            ++i2;
        }
        return n2;
    }

    public static String sanitize(String var0) {
        char[] var3 = var0.toCharArray();
        String var1 = "";
        int var2 = 0;
        while (var2 < var3.length) {
            if (var3[var2] == '&') {
                ++var2;
            } else {
                var1 = String.valueOf(var1) + var3[var2];
            }
            ++var2;
        }
        return var1;
    }
}

