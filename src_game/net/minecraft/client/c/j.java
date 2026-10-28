/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.BufferUtils
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client.c;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.IntBuffer;
import javax.imageio.ImageIO;
import net.minecraft.client.a.d;
import net.minecraft.client.a.k;
import net.minecraft.client.q;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public final class j {
    private int[] a = new int[256];
    private int b = 0;
    private int c;
    private IntBuffer d = BufferUtils.createIntBuffer((int)1024);

    public j(q q2, String string, k k2) {
        int n2;
        int n3;
        int n4;
        int n5;
        int n6;
        int n7;
        int n8;
        BufferedImage bufferedImage;
        try {
            bufferedImage = ImageIO.read(k.class.getResourceAsStream(string));
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
        int n9 = bufferedImage.getWidth();
        int n10 = bufferedImage.getHeight();
        int[] nArray = new int[n9 * n10];
        bufferedImage.getRGB(0, 0, n9, n10, nArray, 0, n9);
        for (int i2 = 0; i2 < 128; ++i2) {
            n10 = i2 % 16;
            n8 = i2 / 16;
            n7 = 0;
            for (n6 = 0; n6 < 8 && n7 == 0; ++n6) {
                n5 = (n10 << 3) + n6;
                n7 = 1;
                for (n4 = 0; n4 < 8 && n7 != 0; ++n4) {
                    n3 = ((n8 << 3) + n4) * n9;
                    n2 = nArray[n5 + n3] & 0xFF;
                    if (n2 <= 128) continue;
                    n7 = 0;
                }
            }
            if (i2 == 32) {
                n6 = 4;
            }
            this.a[i2] = n6;
        }
        this.b = k2.a(string);
        this.c = GL11.glGenLists((int)288);
        d d2 = net.minecraft.client.a.d.a;
        for (n10 = 0; n10 < 256; ++n10) {
            GL11.glNewList((int)(this.c + n10), (int)4864);
            d2.b();
            n8 = n10 % 16 << 3;
            n6 = n10 / 16 << 3;
            d2.a(0.0f, 7.99f, 0.0f, (float)n8 / 128.0f, ((float)n6 + 7.99f) / 128.0f);
            d2.a(7.99f, 7.99f, 0.0f, ((float)n8 + 7.99f) / 128.0f, ((float)n6 + 7.99f) / 128.0f);
            d2.a(7.99f, 0.0f, 0.0f, ((float)n8 + 7.99f) / 128.0f, (float)n6 / 128.0f);
            d2.a(0.0f, 0.0f, 0.0f, (float)n8 / 128.0f, (float)n6 / 128.0f);
            d2.a();
            GL11.glTranslatef((float)this.a[n10], (float)0.0f, (float)0.0f);
            GL11.glEndList();
        }
        for (n10 = 0; n10 < 32; ++n10) {
            n8 = (n10 & 8) << 3;
            n6 = (n10 & 1) * 191 + n8;
            n7 = ((n10 & 2) >> 1) * 191 + n8;
            n5 = ((n10 & 4) >> 2) * 191 + n8;
            int n11 = n4 = n10 >= 16 ? 1 : 0;
            if (q2.g) {
                n3 = (n5 * 30 + n7 * 59 + n6 * 11) / 100;
                n2 = (n5 * 30 + n7 * 70) / 100;
                int n12 = (n5 * 30 + n6 * 70) / 100;
                n5 = n3;
                n7 = n2;
                n6 = n12;
            }
            n10 += 2;
            if (n4 != 0) {
                n5 /= 4;
                n7 /= 4;
                n6 /= 4;
            }
            GL11.glColor4f((float)((float)n5 / 255.0f), (float)((float)n7 / 255.0f), (float)((float)n6 / 255.0f), (float)1.0f);
        }
    }

    public final void a(String string, int n2, int n3, int n4) {
        this.a(string, n2 + 1, n3 + 1, n4, true);
        this.b(string, n2, n3, n4);
    }

    public final void b(String string, int n2, int n3, int n4) {
        this.a(string, n2, n3, n4, false);
    }

    private void a(String object, int n2, int n3, int n4, boolean bl) {
        if (object == null) {
            return;
        }
        object = ((String)object).toCharArray();
        if (bl) {
            n4 = (n4 & 0xFCFCFC) >> 2;
        }
        GL11.glBindTexture((int)3553, (int)this.b);
        float f2 = (float)(n4 >> 16 & 0xFF) / 255.0f;
        float f3 = (float)(n4 >> 8 & 0xFF) / 255.0f;
        float f4 = (float)(n4 & 0xFF) / 255.0f;
        GL11.glColor4f((float)f2, (float)f3, (float)f4, (float)1.0f);
        this.d.clear();
        GL11.glPushMatrix();
        GL11.glTranslatef((float)n2, (float)n3, (float)0.0f);
        for (int i2 = 0; i2 < ((Object)object).length; ++i2) {
            while (object[i2] == 38 && ((Object)object).length > i2 + 1) {
                int n5 = "0123456789abcdef".indexOf((int)object[i2 + 1]);
                if (n5 < 0 || n5 > 15) {
                    n5 = 15;
                }
                this.d.put(this.c + 256 + n5 + (bl ? 16 : 0));
                if (this.d.remaining() == 0) {
                    this.d.flip();
                    GL11.glCallLists((IntBuffer)this.d);
                    this.d.clear();
                }
                i2 += 2;
            }
            this.d.put(this.c + object[i2]);
            if (this.d.remaining() != 0) continue;
            this.d.flip();
            GL11.glCallLists((IntBuffer)this.d);
            this.d.clear();
        }
        this.d.flip();
        GL11.glCallLists((IntBuffer)this.d);
        GL11.glPopMatrix();
    }

    public final int a(String object) {
        if (object == null) {
            return 0;
        }
        object = ((String)object).toCharArray();
        int n2 = 0;
        for (int i2 = 0; i2 < ((Object)object).length; ++i2) {
            if (object[i2] == 38) {
                ++i2;
                continue;
            }
            n2 += this.a[object[i2]];
        }
        return n2;
    }
}

