/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.BufferUtils
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client.a;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import net.minecraft.client.a.c;
import net.minecraft.client.a.c.b;
import net.minecraft.client.a.i;
import net.minecraft.client.q;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public class k {
    private HashMap a = new HashMap();
    private HashMap b = new HashMap();
    private IntBuffer c = BufferUtils.createIntBuffer((int)1);
    private ByteBuffer d = BufferUtils.createByteBuffer((int)262144);
    private List e = new ArrayList();
    private Map f = new HashMap();
    private q g;
    private boolean h = false;

    public k(q q2) {
        this.g = q2;
    }

    public final int a(String string) {
        Integer n2 = (Integer)this.a.get(string);
        if (n2 != null) {
            return n2;
        }
        try {
            this.c.clear();
            GL11.glGenTextures((IntBuffer)this.c);
            int n3 = this.c.get(0);
            if (string.startsWith("##")) {
                this.a(k.a(ImageIO.read(k.class.getResourceAsStream(string.substring(2)))), n3);
            } else if (string.startsWith("%%")) {
                this.h = true;
                this.a(ImageIO.read(k.class.getResourceAsStream(string.substring(2))), n3);
                this.h = false;
            } else {
                this.a(ImageIO.read(k.class.getResourceAsStream(string)), n3);
            }
            this.a.put(string, n3);
            return n3;
        }
        catch (IOException iOException) {
            throw new RuntimeException("!!");
        }
    }

    private static BufferedImage a(BufferedImage bufferedImage) {
        int n2 = bufferedImage.getWidth() / 16;
        BufferedImage bufferedImage2 = new BufferedImage(16, bufferedImage.getHeight() * n2, 2);
        Graphics graphics = bufferedImage2.getGraphics();
        for (int i2 = 0; i2 < n2; ++i2) {
            graphics.drawImage(bufferedImage, -i2 << 4, i2 * bufferedImage.getHeight(), null);
        }
        graphics.dispose();
        return bufferedImage2;
    }

    private void a(BufferedImage bufferedImage, int n2) {
        GL11.glBindTexture((int)3553, (int)n2);
        GL11.glTexParameteri((int)3553, (int)10241, (int)9728);
        GL11.glTexParameteri((int)3553, (int)10240, (int)9728);
        if (this.h) {
            GL11.glTexParameteri((int)3553, (int)10242, (int)10496);
            GL11.glTexParameteri((int)3553, (int)10243, (int)10496);
        } else {
            GL11.glTexParameteri((int)3553, (int)10242, (int)10497);
            GL11.glTexParameteri((int)3553, (int)10243, (int)10497);
        }
        n2 = bufferedImage.getWidth();
        int n3 = bufferedImage.getHeight();
        int[] nArray = new int[n2 * n3];
        byte[] byArray = new byte[n2 * n3 << 2];
        bufferedImage.getRGB(0, 0, n2, n3, nArray, 0, n2);
        for (int i2 = 0; i2 < nArray.length; ++i2) {
            int n4 = nArray[i2] >>> 24;
            int n5 = nArray[i2] >> 16 & 0xFF;
            int n6 = nArray[i2] >> 8 & 0xFF;
            int n7 = nArray[i2] & 0xFF;
            if (this.g != null && this.g.g) {
                int n8 = (n5 * 30 + n6 * 59 + n7 * 11) / 100;
                n6 = (n5 * 30 + n6 * 70) / 100;
                n7 = (n5 * 30 + n7 * 70) / 100;
                n5 = n8;
            }
            byArray[i2 << 2] = (byte)n5;
            byArray[(i2 << 2) + 1] = (byte)n6;
            byArray[(i2 << 2) + 2] = (byte)n7;
            byArray[(i2 << 2) + 3] = (byte)n4;
        }
        this.d.clear();
        this.d.put(byArray);
        this.d.position(0).limit(byArray.length);
        GL11.glTexImage2D((int)3553, (int)0, (int)6408, (int)n2, (int)n3, (int)0, (int)6408, (int)5121, (ByteBuffer)this.d);
    }

    public final int a(String object, String string) {
        if ((object = (i)this.f.get(object)) != null && ((i)object).a != null && !((i)object).d) {
            if (((i)object).c < 0) {
                BufferedImage bufferedImage = ((i)object).a;
                k k2 = this;
                k2.c.clear();
                GL11.glGenTextures((IntBuffer)k2.c);
                int n2 = k2.c.get(0);
                k2.a(bufferedImage, n2);
                k2.b.put(n2, bufferedImage);
                ((i)object).c = n2;
            } else {
                this.a(((i)object).a, ((i)object).c);
            }
            ((i)object).d = true;
        }
        if (object == null || ((i)object).c < 0) {
            return this.a(string);
        }
        return ((i)object).c;
    }

    public final i a(String string, c c2) {
        i i2 = (i)this.f.get(string);
        if (i2 == null) {
            this.f.put(string, new i(string, c2));
        } else {
            ++i2.b;
        }
        return i2;
    }

    public final void b(String string) {
        Object object = (i)this.f.get(string);
        if (object != null) {
            --((i)object).b;
            if (((i)object).b == 0) {
                if (((i)object).c >= 0) {
                    int n2 = ((i)object).c;
                    object = this;
                    ((k)object).b.remove(n2);
                    ((k)object).c.clear();
                    ((k)object).c.put(n2);
                    ((k)object).c.flip();
                    GL11.glDeleteTextures((IntBuffer)((k)object).c);
                }
                this.f.remove(string);
            }
        }
    }

    public final void a(b b2) {
        this.e.add(b2);
        b2.a();
    }

    public final void a() {
        b b2;
        int n2;
        for (n2 = 0; n2 < this.e.size(); ++n2) {
            b2 = (b)this.e.get(n2);
            ((b)this.e.get(n2)).c = this.g.g;
            b2.a();
            this.d.clear();
            this.d.put(b2.a);
            this.d.position(0).limit(b2.a.length);
            GL11.glTexSubImage2D((int)3553, (int)0, (int)(b2.b % 16 << 4), (int)(b2.b / 16 << 4), (int)16, (int)16, (int)6408, (int)5121, (ByteBuffer)this.d);
        }
        for (n2 = 0; n2 < this.e.size(); ++n2) {
            b2 = (b)this.e.get(n2);
            if (b2.d <= 0) continue;
            this.d.clear();
            this.d.put(b2.a);
            this.d.position(0).limit(b2.a.length);
            GL11.glBindTexture((int)3553, (int)b2.d);
            GL11.glTexSubImage2D((int)3553, (int)0, (int)0, (int)0, (int)16, (int)16, (int)6408, (int)5121, (ByteBuffer)this.d);
        }
    }

    public final void b() {
        Object object;
        Iterator<Object> iterator = this.b.keySet().iterator();
        while (iterator.hasNext()) {
            int n2 = (Integer)iterator.next();
            object = (BufferedImage)this.b.get(n2);
            this.a((BufferedImage)object, n2);
        }
        for (i i2 : this.f.values()) {
            ((i)iterator.next()).d = false;
        }
        for (String string : this.a.keySet()) {
            try {
                if (string.startsWith("##")) {
                    object = k.a(ImageIO.read(k.class.getResourceAsStream(string.substring(2))));
                } else if (string.startsWith("%%")) {
                    this.h = true;
                    object = ImageIO.read(k.class.getResourceAsStream(string.substring(2)));
                    this.h = false;
                } else {
                    object = ImageIO.read(k.class.getResourceAsStream(string));
                }
                int n3 = (Integer)this.a.get(string);
                this.a((BufferedImage)object, n3);
            }
            catch (IOException iOException) {
                object = iOException;
                iOException.printStackTrace();
            }
        }
    }

    public static void a(int n2) {
        if (n2 < 0) {
            return;
        }
        GL11.glBindTexture((int)3553, (int)n2);
    }
}

