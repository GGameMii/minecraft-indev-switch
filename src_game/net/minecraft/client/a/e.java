/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.BufferUtils
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.Display
 *  org.lwjgl.opengl.GL11
 *  org.lwjgl.util.glu.GLU
 */
package net.minecraft.client.a;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;
import net.minecraft.a.a.b.x;
import net.minecraft.client.a.b;
import net.minecraft.client.a.b.a;
import net.minecraft.client.a.b.c;
import net.minecraft.client.a.b.d;
import net.minecraft.client.a.f;
import net.minecraft.client.a.g;
import net.minecraft.client.c.h;
import org.lwjgl.BufferUtils;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

public final class e {
    private net.minecraft.client.d b;
    private boolean c = false;
    private float d = 0.0f;
    public g a;
    private int e;
    private net.minecraft.a.c.b f = null;
    private int g;
    private int h;
    private DecimalFormat i = new DecimalFormat("0000");
    private ByteBuffer j;
    private FloatBuffer k = BufferUtils.createFloatBuffer((int)16);
    private Random l = new Random();
    private volatile int m = 0;
    private volatile int n = 0;
    private FloatBuffer o = BufferUtils.createFloatBuffer((int)16);
    private float p;
    private float q;
    private float r;
    private float s;
    private float t;

    public e(net.minecraft.client.d d2) {
        this.b = d2;
        this.a = new g(d2);
    }

    public final void a() {
        this.s = this.t;
        float f2 = this.b.d.c((int)this.b.f.h, (int)this.b.f.i, (int)this.b.f.j);
        float f3 = (float)(3 - this.b.w.e) / 3.0f;
        f2 = f2 * (1.0f - f3) + f3;
        this.t += (f2 - this.t) * 0.1f;
        ++this.e;
        this.a.a();
    }

    private net.minecraft.a.d.a b(float f2) {
        net.minecraft.client.g.a a2 = this.b.f;
        float f3 = a2.e + (a2.h - a2.e) * f2;
        float f4 = a2.f + (a2.i - a2.f) * f2;
        f2 = a2.g + (a2.j - a2.g) * f2;
        return new net.minecraft.a.d.a(f3, f4, f2);
    }

    private void c(float f2) {
        net.minecraft.client.g.a a2 = this.b.f;
        float f3 = (float)a2.Y - f2;
        if (a2.W <= 0) {
            f2 = (float)a2.ab + f2;
            GL11.glRotatef((float)(40.0f - 8000.0f / (f2 + 200.0f)), (float)0.0f, (float)0.0f, (float)1.0f);
        }
        if (f3 < 0.0f) {
            return;
        }
        f3 /= (float)a2.Z;
        f3 = a.a.a(f3 * f3 * f3 * f3 * (float)Math.PI);
        f2 = a2.aa;
        GL11.glRotatef((float)(-f2), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)(-f3 * 14.0f), (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)f2, (float)0.0f, (float)1.0f, (float)0.0f);
    }

    private void d(float f2) {
        if (this.b.w.v) {
            return;
        }
        net.minecraft.client.g.a a2 = this.b.f;
        float f3 = a2.z - a2.y;
        f3 = a2.z + f3 * f2;
        float f4 = a2.Q + (a2.R - a2.Q) * f2;
        f2 = a2.ad + (a2.ae - a2.ad) * f2;
        GL11.glTranslatef((float)(a.a.a(f3 * (float)Math.PI) * f4 * 0.5f), (float)(-Math.abs(a.a.b(f3 * (float)Math.PI) * f4)), (float)0.0f);
        GL11.glRotatef((float)(a.a.a(f3 * (float)Math.PI) * f4 * 3.0f), (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)(Math.abs(a.a.b(f3 * (float)Math.PI + 0.2f) * f4) * 5.0f), (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glRotatef((float)f2, (float)1.0f, (float)0.0f, (float)0.0f);
    }

    public final void a(float f2) {
        Object object;
        if (this.c && !Display.isActive()) {
            this.b.c();
        }
        this.c = Display.isActive();
        if (this.b.C) {
            Mouse.getDX();
            int n2 = 0;
            Mouse.getDY();
            int n3 = 0;
            this.b.y.b();
            int n4 = 1;
            if (this.b.w.c) {
                n4 = -1;
            }
            int n5 = n2 + this.b.y.a;
            int n6 = n3 - this.b.y.b;
            if (n2 != 0 || this.g != 0) {
                System.out.println("xxo: " + n2 + ", " + this.g + ": " + this.g + ", xo: " + n5);
            }
            if (this.g != 0) {
                this.g = 0;
            }
            if (this.h != 0) {
                this.h = 0;
            }
            if (n2 != 0) {
                this.g = n2;
            }
            if (n3 != 0) {
                this.h = n3;
            }
            float f3 = n6 * n4;
            float f4 = n5;
            object = this.b.f;
            float f5 = ((net.minecraft.a.c.b)object).o;
            float f6 = ((net.minecraft.a.c.b)object).n;
            ((net.minecraft.a.c.b)object).n = (float)((double)((net.minecraft.a.c.b)object).n + (double)f4 * 0.15);
            ((net.minecraft.a.c.b)object).o = (float)((double)((net.minecraft.a.c.b)object).o - (double)f3 * 0.15);
            if (((net.minecraft.a.c.b)object).o < -90.0f) {
                ((net.minecraft.a.c.b)object).o = -90.0f;
            }
            if (((net.minecraft.a.c.b)object).o > 90.0f) {
                ((net.minecraft.a.c.b)object).o = 90.0f;
            }
            ((net.minecraft.a.c.b)object).q += ((net.minecraft.a.c.b)object).o - f5;
            ((net.minecraft.a.c.b)object).p += ((net.minecraft.a.c.b)object).n - f6;
        }
        object = new h(this.b.b, this.b.c);
        int n7 = ((h)object).a();
        int n8 = ((h)object).b();
        int n9 = Mouse.getX() * n7 / this.b.b;
        int n10 = n8 - Mouse.getY() * n8 / this.b.c - 1;
        if (this.b.d != null) {
            this.e(f2);
            this.b.t.a(f2);
        } else {
            GL11.glViewport((int)0, (int)0, (int)this.b.b, (int)this.b.c);
            GL11.glClearColor((float)0.0f, (float)0.0f, (float)0.0f, (float)0.0f);
            GL11.glClear((int)16640);
            GL11.glMatrixMode((int)5889);
            GL11.glLoadIdentity();
            GL11.glMatrixMode((int)5888);
            GL11.glLoadIdentity();
            this.c();
        }
        if (this.b.o != null) {
            GL11.glClear((int)256);
            this.b.o.a(n9, n10, f2);
        }
        Thread.yield();
        Display.update();
    }

    public final void b() {
        File file;
        this.b.p.a("Grabbing large screenshot");
        Serializable serializable = new File(System.getProperty("user.home", "."));
        int n2 = 0;
        while ((file = new File((File)serializable, "mc_map_" + this.i.format(n2) + ".png")).exists()) {
            ++n2;
        }
        file = file.getAbsoluteFile();
        this.b.p.b("Rendering");
        this.b.p.a(0);
        try {
            int n3 = (this.b.d.a << 4) + (this.b.d.b << 4);
            n2 = (this.b.d.c << 4) + n3 / 2;
            BufferedImage bufferedImage = new BufferedImage(n3, n2, 1);
            Graphics graphics = bufferedImage.getGraphics();
            int n4 = this.b.b;
            int n5 = this.b.c;
            int n6 = (n3 / n4 + 1) * (n2 / n5 + 1);
            int n7 = 0;
            for (int i2 = 0; i2 < n3; i2 += n4) {
                for (int i3 = 0; i3 < n2; i3 += n5) {
                    this.b.p.a(++n7 * 100 / n6);
                    float f2 = 0.0f;
                    int n8 = i3 - n2 / 2;
                    int n9 = i2 - n3 / 2;
                    Object object = this;
                    if (((e)object).j == null) {
                        ((e)object).j = BufferUtils.createByteBuffer((int)(((e)object).b.b * ((e)object).b.c << 2));
                    }
                    net.minecraft.client.g.a a2 = ((e)object).b.f;
                    net.minecraft.a.a.g g2 = ((e)object).b.d;
                    b b2 = ((e)object).b.e;
                    GL11.glViewport((int)0, (int)0, (int)((e)object).b.b, (int)((e)object).b.c);
                    ((e)object).f(0.0f);
                    GL11.glClear((int)16640);
                    GL11.glEnable((int)2884);
                    ((e)object).d = 512 >> (((e)object).b.w.e << 1);
                    GL11.glMatrixMode((int)5889);
                    GL11.glLoadIdentity();
                    GL11.glOrtho((double)0.0, (double)((e)object).b.b, (double)0.0, (double)((e)object).b.c, (double)10.0, (double)10000.0);
                    GL11.glMatrixMode((int)5888);
                    GL11.glLoadIdentity();
                    GL11.glTranslatef((float)(-n9), (float)(-n8), (float)-5000.0f);
                    GL11.glScalef((float)16.0f, (float)-16.0f, (float)-16.0f);
                    float f3 = 1.0f;
                    f3 = 0.0f;
                    f3 = 0.0f;
                    f3 = 0.0f;
                    f3 = 0.0f;
                    f3 = 0.0f;
                    f3 = 0.5f;
                    f3 = 1.0f;
                    f3 = 0.0f;
                    f3 = -1.0f;
                    f3 = 1.0f;
                    f3 = 0.0f;
                    f3 = 0.0f;
                    f3 = 0.0f;
                    f3 = -0.5f;
                    f3 = 1.0f;
                    Object object2 = object;
                    ((e)object2).k.clear();
                    ((e)object2).k.put(1.0f).put(-0.5f).put(0.0f).put(0.0f);
                    ((e)object2).k.put(0.0f).put(1.0f).put(-1.0f).put(0.0f);
                    ((e)object2).k.put(1.0f).put(0.5f).put(0.0f).put(0.0f);
                    ((e)object2).k.put(0.0f).put(0.0f).put(0.0f).put(1.0f);
                    ((e)object2).k.flip();
                    GL11.glMultMatrix((FloatBuffer)((e)object2).k);
                    GL11.glRotatef((float)0.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    GL11.glTranslatef((float)((float)(-g2.a) / 2.0f), (float)((float)(-g2.c) / 2.0f), (float)((float)(-g2.b) / 2.0f));
                    object2 = new d();
                    ((e)object).b.e.a((net.minecraft.client.a.b.e)object2);
                    ((e)object).b.e.a(a2);
                    ((e)object).d();
                    GL11.glEnable((int)2912);
                    GL11.glFogi((int)2917, (int)9729);
                    float f4 = (float)g2.c * 8.0f;
                    GL11.glFogf((int)2915, (float)(5000.0f - f4));
                    GL11.glFogf((int)2916, (float)(5000.0f + f4 * 8.0f));
                    net.minecraft.client.c.b();
                    b2.a(((e)object).b(0.0f), (net.minecraft.client.a.b.e)object2, 0.0f);
                    net.minecraft.client.c.a();
                    GL11.glBindTexture((int)3553, (int)((e)object).b.m.a("/terrain.png"));
                    b2.a(a2, 0);
                    b2.f();
                    if (g2.u < g2.c) {
                        b2.a(0.0f);
                    }
                    GL11.glEnable((int)3042);
                    GL11.glBlendFunc((int)770, (int)771);
                    GL11.glColorMask((boolean)false, (boolean)false, (boolean)false, (boolean)false);
                    n9 = b2.a(a2, 1);
                    GL11.glColorMask((boolean)true, (boolean)true, (boolean)true, (boolean)true);
                    if (n9 > 0) {
                        b2.d();
                    }
                    if (g2.g() >= 0) {
                        b2.g();
                    }
                    GL11.glDepthMask((boolean)true);
                    GL11.glDisable((int)3042);
                    GL11.glDisable((int)2912);
                    ((e)object).j.clear();
                    GL11.glPixelStorei((int)3333, (int)1);
                    GL11.glReadPixels((int)0, (int)0, (int)((e)object).b.b, (int)((e)object).b.c, (int)6407, (int)5121, (ByteBuffer)((e)object).j);
                    object = net.minecraft.client.a.e.a(((e)object).j, n4, n5);
                    graphics.drawImage((Image)object, i2, i3, null);
                }
            }
            graphics.dispose();
            this.b.p.b("Saving as " + file.toString());
            this.b.p.a(100);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ImageIO.write((RenderedImage)bufferedImage, "png", fileOutputStream);
            fileOutputStream.close();
            return;
        }
        catch (Throwable throwable) {
            serializable = throwable;
            throwable.printStackTrace();
            return;
        }
    }

    private static BufferedImage a(ByteBuffer byteBuffer, int n2, int n3) {
        byteBuffer.position(0).limit(n2 * n3 << 2);
        BufferedImage bufferedImage = new BufferedImage(n2, n3, 1);
        int[] nArray = ((DataBufferInt)bufferedImage.getRaster().getDataBuffer()).getData();
        for (int i2 = 0; i2 < n2 * n3; ++i2) {
            int n4 = byteBuffer.get(i2 * 3) & 0xFF;
            int n5 = byteBuffer.get(i2 * 3 + 1) & 0xFF;
            int n6 = byteBuffer.get(i2 * 3 + 2) & 0xFF;
            nArray[i2] = n4 << 16 | n5 << 8 | n6;
        }
        return bufferedImage;
    }

    private void e(float f2) {
        float f3 = f2;
        Object object = this;
        net.minecraft.client.g.a a2 = ((e)object).b.f;
        float f4 = a2.q + (a2.o - a2.q) * f3;
        float f5 = a2.p + (a2.n - a2.p) * f3;
        net.minecraft.a.d.a a3 = ((e)object).b(f3);
        float f6 = a.a.b(-f5 * ((float)Math.PI / 180) - (float)Math.PI);
        float f7 = a.a.a(-f5 * ((float)Math.PI / 180) - (float)Math.PI);
        float f8 = -a.a.b(-f4 * ((float)Math.PI / 180));
        float f9 = a.a.a(-f4 * ((float)Math.PI / 180));
        float f10 = f7 * f8;
        float f11 = f6 * f8;
        float f12 = ((e)object).b.a.b();
        net.minecraft.a.d.a a4 = a3.a(f10 * f12, f9 * f12, f11 * f12);
        ((e)object).b.v = ((e)object).b.d.a(a3, a4);
        float f13 = f12;
        a3 = ((e)object).b(f3);
        if (((e)object).b.v != null) {
            f13 = ((e)object).b.v.f.b(a3);
        }
        if (((e)object).b.a instanceof net.minecraft.client.d.c) {
            f12 = 32.0f;
        } else {
            if (f13 > 3.0f) {
                f13 = 3.0f;
            }
            f12 = f13;
        }
        a4 = a3.a(f10 * f12, f9 * f12, f11 * f12);
        ((e)object).f = null;
        List list = ((e)object).b.d.r.a(a2, a2.r.a(f10 * f12, f9 * f12, f11 * f12));
        float f14 = 0.0f;
        for (int i2 = 0; i2 < list.size(); ++i2) {
            float f15;
            net.minecraft.a.c.b b2 = (net.minecraft.a.c.b)list.get(i2);
            if (!b2.d()) continue;
            Object object2 = b2.r.b(0.1f, 0.1f, 0.1f);
            if ((object2 = ((net.minecraft.a.d.b)object2).a(a3, a4)) == null || !((f15 = a3.b(((net.minecraft.a.d.c)object2).f)) < f14) && f14 != 0.0f) continue;
            ((e)object).f = b2;
            f14 = f15;
        }
        if (((e)object).f != null && !(((e)object).b.a instanceof net.minecraft.client.d.c)) {
            ((e)object).b.v = new net.minecraft.a.d.c(((e)object).f);
        }
        for (int i3 = 0; i3 < 2; ++i3) {
            if (this.b.w.g) {
                if (i3 == 0) {
                    GL11.glColorMask((boolean)false, (boolean)true, (boolean)true, (boolean)false);
                } else {
                    GL11.glColorMask((boolean)true, (boolean)false, (boolean)false, (boolean)false);
                }
            }
            net.minecraft.client.g.a a5 = this.b.f;
            net.minecraft.a.a.g g2 = this.b.d;
            b b3 = this.b.e;
            net.minecraft.client.f.d d2 = this.b.g;
            GL11.glViewport((int)0, (int)0, (int)this.b.b, (int)this.b.c);
            this.f(f2);
            GL11.glClear((int)16640);
            GL11.glEnable((int)2884);
            int n2 = i3;
            float f16 = f2;
            object = this;
            this.d = 512 >> (((e)object).b.w.e << 1);
            GL11.glMatrixMode((int)5889);
            GL11.glLoadIdentity();
            if (((e)object).b.w.g) {
                GL11.glTranslatef((float)((float)(-((n2 << 1) - 1)) * 0.07f), (float)0.0f, (float)0.0f);
            }
            float f17 = f16;
            e e2 = object;
            net.minecraft.client.g.a a6 = e2.b.f;
            f7 = 70.0f;
            if (a6.m()) {
                f7 = 60.0f;
            }
            if (a6.W <= 0) {
                f8 = (float)a6.ab + f17;
                f7 /= (1.0f - 500.0f / (f8 + 500.0f)) * 2.0f + 1.0f;
            }
            GLU.gluPerspective((float)f7, (float)((float)((e)object).b.b / (float)((e)object).b.c), (float)0.05f, (float)((e)object).d);
            GL11.glMatrixMode((int)5888);
            GL11.glLoadIdentity();
            if (((e)object).b.w.g) {
                GL11.glTranslatef((float)((float)((n2 << 1) - 1) * 0.1f), (float)0.0f, (float)0.0f);
            }
            ((e)object).c(f16);
            if (((e)object).b.w.f) {
                ((e)object).d(f16);
            }
            f17 = f16;
            e2 = object;
            a6 = e2.b.f;
            f7 = a6.e + (a6.h - a6.e) * f17;
            f8 = a6.f + (a6.i - a6.f) * f17;
            f9 = a6.g + (a6.j - a6.g) * f17;
            if (e2.b.w.v) {
                f10 = 4.0f;
                float f18 = -a.a.a(a6.n / 180.0f * (float)Math.PI) * a.a.b(a6.o / 180.0f * (float)Math.PI) * 4.0f;
                f11 = a.a.b(a6.n / 180.0f * (float)Math.PI) * a.a.b(a6.o / 180.0f * (float)Math.PI) * 4.0f;
                f12 = -a.a.a(a6.o / 180.0f * (float)Math.PI) * 4.0f;
                for (int i4 = 0; i4 < 8; ++i4) {
                    float f19;
                    net.minecraft.a.d.c c2;
                    float f20 = ((i4 & 1) << 1) - 1;
                    f16 = ((i4 >> 1 & 1) << 1) - 1;
                    float f21 = ((i4 >> 2 & 1) << 1) - 1;
                    if ((c2 = e2.b.d.a(new net.minecraft.a.d.a(f7 + (f20 *= 0.1f), f8 + (f16 *= 0.1f), f9 + (f21 *= 0.1f)), new net.minecraft.a.d.a(f7 - f18 + f20 + f21, f8 - f12 + f16, f9 - f11 + f21))) == null) continue;
                    float f22 = c2.f.b(new net.minecraft.a.d.a(f7, f8, f9));
                    if (!(f19 < f10)) continue;
                    f10 = f22;
                }
                GL11.glTranslatef((float)0.0f, (float)0.0f, (float)(-f10));
            } else {
                GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-0.1f);
            }
            GL11.glRotatef((float)(a6.q + (a6.o - a6.q) * f17), (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)(a6.p + (a6.n - a6.p) * f17 + 180.0f), (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glTranslatef((float)(-f7), (float)(-f8), (float)(-f9));
            net.minecraft.client.a.b.a.a();
            this.d();
            GL11.glEnable((int)2912);
            b3.a(f2);
            this.d();
            object = new c();
            this.b.e.a((net.minecraft.client.a.b.e)object);
            this.b.e.a(a5);
            this.d();
            GL11.glEnable((int)2912);
            GL11.glBindTexture((int)3553, (int)this.b.m.a("/terrain.png"));
            net.minecraft.client.c.a();
            b3.a(a5, 0);
            if (g2.a(a5.h, a5.i, a5.j, 0.1f)) {
                int n3 = (int)a5.h;
                int n4 = (int)a5.i;
                int n5 = (int)a5.j;
                f f23 = new f(g2);
                for (int i5 = n3 - 1; i5 <= n3 + 1; ++i5) {
                    for (int i6 = n4 - 1; i6 <= n4 + 1; ++i6) {
                        for (int i7 = n5 - 1; i7 <= n5 + 1; ++i7) {
                            int n6 = g2.a(i5, i6, i7);
                            if (n6 <= 0) continue;
                            f23.a(x.c[n6], i5, i6, i7);
                        }
                    }
                }
            }
            net.minecraft.client.c.b();
            b3.a(this.b(f2), (net.minecraft.client.a.b.e)object, f2);
            d2.a(f2);
            net.minecraft.client.c.a();
            this.d();
            d2.a(a5, f2);
            b3.f();
            if (this.b.v != null && a5.m()) {
                GL11.glDisable((int)3008);
                b3.a(this.b.v, 0, a5.b.d());
                b3.a(this.b.v, 0);
                GL11.glEnable((int)3008);
            }
            GL11.glBlendFunc((int)770, (int)771);
            this.d();
            b3.g();
            GL11.glEnable((int)3042);
            GL11.glDisable((int)2884);
            GL11.glColorMask((boolean)false, (boolean)false, (boolean)false, (boolean)false);
            int n7 = b3.a(a5, 1);
            GL11.glColorMask((boolean)true, (boolean)true, (boolean)true, (boolean)true);
            if (this.b.w.g) {
                if (i3 == 0) {
                    GL11.glColorMask((boolean)false, (boolean)true, (boolean)true, (boolean)false);
                } else {
                    GL11.glColorMask((boolean)true, (boolean)false, (boolean)false, (boolean)false);
                }
            }
            if (n7 > 0) {
                b3.d();
            }
            GL11.glDepthMask((boolean)true);
            GL11.glEnable((int)2884);
            GL11.glDisable((int)3042);
            if (this.b.v != null && !a5.m()) {
                GL11.glDisable((int)3008);
                b3.a(this.b.v, 0, a5.b.d());
                b3.a(this.b.v, 0);
                GL11.glEnable((int)3008);
            }
            GL11.glDisable((int)2912);
            GL11.glClear((int)256);
            int n8 = i3;
            float f24 = f2;
            object = this;
            GL11.glLoadIdentity();
            if (((e)object).b.w.g) {
                GL11.glTranslatef((float)((float)((n8 << 1) - 1) * 0.1f), (float)0.0f, (float)0.0f);
            }
            GL11.glPushMatrix();
            super.c(f24);
            if (((e)object).b.w.f) {
                super.d(f24);
            }
            if (!((e)object).b.w.v) {
                ((e)object).a.a(f24);
            }
            GL11.glPopMatrix();
            if (!((e)object).b.w.v) {
                ((e)object).a.b(f24);
                super.c(f24);
            }
            if (((e)object).b.w.f) {
                super.d(f24);
            }
            if (this.b.w.g) continue;
            return;
        }
        GL11.glColorMask((boolean)true, (boolean)true, (boolean)true, (boolean)false);
    }

    public final void c() {
        h h2 = new h(this.b.b, this.b.c);
        int n2 = h2.a();
        int n3 = h2.b();
        GL11.glClear((int)256);
        GL11.glMatrixMode((int)5889);
        GL11.glLoadIdentity();
        GL11.glOrtho((double)0.0, (double)n2, (double)n3, (double)0.0, (double)1000.0, (double)3000.0);
        GL11.glMatrixMode((int)5888);
        GL11.glLoadIdentity();
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-2000.0f);
    }

    private void f(float f2) {
        Object object = this.b.d;
        net.minecraft.client.g.a a2 = this.b.f;
        float f3 = 1.0f / (float)(4 - this.b.w.e);
        f3 = 1.0f - (float)Math.pow(f3, 0.25);
        net.minecraft.a.d.a a3 = ((net.minecraft.a.a.g)object).b(f2);
        float f4 = a3.a;
        float f5 = a3.b;
        float f6 = a3.c;
        net.minecraft.a.d.a a4 = ((net.minecraft.a.a.g)object).d(f2);
        this.p = a4.a;
        this.q = a4.b;
        this.r = a4.c;
        this.p += (f4 - this.p) * f3;
        this.q += (f5 - this.q) * f3;
        this.r += (f6 - this.r) * f3;
        if ((object = x.c[((net.minecraft.a.a.g)object).a((int)a2.h, (int)(a2.i + 0.12f), (int)a2.j)]) != null && ((x)object).aC != net.minecraft.a.a.d.c.a) {
            object = ((x)object).aC;
            if (object == net.minecraft.a.a.d.c.f) {
                this.p = 0.02f;
                this.q = 0.02f;
                this.r = 0.2f;
            } else if (object == net.minecraft.a.a.d.c.g) {
                this.p = 0.6f;
                this.q = 0.1f;
                this.r = 0.0f;
            }
        }
        float f7 = this.s + (this.t - this.s) * f2;
        this.p *= f7;
        this.q *= f7;
        this.r *= f7;
        if (this.b.w.g) {
            f2 = (this.p * 30.0f + this.q * 59.0f + this.r * 11.0f) / 100.0f;
            f7 = (this.p * 30.0f + this.q * 70.0f) / 100.0f;
            float f8 = (this.p * 30.0f + this.r * 70.0f) / 100.0f;
            this.p = f2;
            this.q = f7;
            this.r = f8;
        }
        GL11.glClearColor((float)this.p, (float)this.q, (float)this.r, (float)0.0f);
    }

    private void d() {
        Object object = this.b.d;
        net.minecraft.client.g.a a2 = this.b.f;
        float f2 = 1.0f;
        float f3 = this.r;
        float f4 = this.q;
        float f5 = this.p;
        e e2 = this;
        e2.o.clear();
        e2.o.put(f5).put(f4).put(f3).put(1.0f);
        e2.o.flip();
        GL11.glFog((int)2918, (FloatBuffer)e2.o);
        GL11.glNormal3f((float)0.0f, (float)-1.0f, (float)0.0f);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        object = x.c[((net.minecraft.a.a.g)object).a((int)a2.h, (int)(a2.i + 0.12f), (int)a2.j)];
        if (object != null && ((x)object).aC.d()) {
            object = ((x)object).aC;
            GL11.glFogi((int)2917, (int)2048);
            if (object == net.minecraft.a.a.d.c.f) {
                GL11.glFogf((int)2914, (float)0.1f);
            } else if (object == net.minecraft.a.a.d.c.g) {
                GL11.glFogf((int)2914, (float)2.0f);
            }
        } else {
            GL11.glFogi((int)2917, (int)9729);
            GL11.glFogf((int)2915, (float)(this.d / 4.0f));
            GL11.glFogf((int)2916, (float)this.d);
        }
        GL11.glEnable((int)2903);
        GL11.glColorMaterial((int)1028, (int)4608);
    }
}

