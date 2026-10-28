/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.BufferUtils
 *  org.lwjgl.opengl.ARBOcclusionQuery
 *  org.lwjgl.opengl.GL11
 *  org.lwjgl.opengl.GLContext
 */
package net.minecraft.client.a;

import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import net.minecraft.a.a.b.x;
import net.minecraft.client.a.a;
import net.minecraft.client.a.a.i;
import net.minecraft.client.a.b.e;
import net.minecraft.client.a.c;
import net.minecraft.client.a.d;
import net.minecraft.client.a.f;
import net.minecraft.client.a.h;
import net.minecraft.client.a.k;
import net.minecraft.client.a.l;
import net.minecraft.client.f.g;
import net.minecraft.client.f.j;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.ARBOcclusionQuery;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;

public final class b
implements net.minecraft.a.a.d {
    private net.minecraft.a.a.g b;
    private k c;
    private int d;
    private IntBuffer e = BufferUtils.createIntBuffer((int)65536);
    private List f = new ArrayList();
    private h[] g;
    private h[] h;
    private int i;
    private int j;
    private int k;
    private int l;
    private net.minecraft.client.d m;
    private f n;
    private IntBuffer o;
    private boolean p = false;
    private int q = 0;
    private int r;
    private int s;
    private int t;
    private int u;
    private int[] v = new int[50000];
    private IntBuffer w = BufferUtils.createIntBuffer((int)64);
    private int x;
    private int y;
    private int z;
    private int A;
    private float B = -9999.0f;
    private float C = -9999.0f;
    private float D = -9999.0f;
    public float a;

    public b(net.minecraft.client.d object, k k2) {
        this.m = object;
        this.c = k2;
        this.d = GL11.glGenLists((int)2);
        this.l = GL11.glGenLists((int)786432);
        this.p = GLContext.getCapabilities().GL_ARB_occlusion_query;
        if (this.p) {
            this.w.clear();
            GL11.glGetInteger((int)34916, (IntBuffer)this.w);
            if (this.w.get(0) == 0) {
                this.p = false;
            } else {
                this.o = BufferUtils.createIntBuffer((int)262144);
                this.o.clear();
                this.o.position(0);
                this.o.limit(262144);
                ARBOcclusionQuery.glGenQueriesARB((IntBuffer)this.o);
            }
        }
        this.r = GL11.glGenLists((int)1);
        GL11.glNewList((int)this.r, (int)4864);
        object = new Random(10842L);
        for (int i2 = 0; i2 < 500; ++i2) {
            GL11.glRotatef((float)(((Random)object).nextFloat() * 360.0f), (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)(((Random)object).nextFloat() * 360.0f), (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)(((Random)object).nextFloat() * 360.0f), (float)0.0f, (float)0.0f, (float)1.0f);
            d d2 = net.minecraft.client.a.d.a;
            float f2 = 0.25f + ((Random)object).nextFloat() * 0.25f;
            d2.b();
            d2.a(-f2, -100.0f, f2, 1.0f, 1.0f);
            d2.a(f2, -100.0f, f2, 0.0f, 1.0f);
            d2.a(f2, -100.0f, -f2, 0.0f, 0.0f);
            d2.a(-f2, -100.0f, -f2, 1.0f, 0.0f);
            d2.a();
        }
        GL11.glEndList();
    }

    public final void a(net.minecraft.a.a.g g2) {
        if (this.b != null) {
            this.b.b(this);
        }
        this.B = -9999.0f;
        this.C = -9999.0f;
        this.D = -9999.0f;
        net.minecraft.client.a.a.i.a.a(g2);
        this.b = g2;
        this.n = new f(g2);
        if (g2 != null) {
            g2.a(this);
            this.a();
        }
    }

    public final void a() {
        int n2;
        int n3;
        if (this.h != null) {
            for (n3 = 0; n3 < this.h.length; ++n3) {
                this.h[n3].b();
            }
        }
        this.i = this.b.a / 16;
        this.j = this.b.c / 16;
        this.k = this.b.b / 16;
        this.h = new h[this.i * this.j * this.k];
        this.g = new h[this.i * this.j * this.k];
        n3 = 0;
        int n4 = 0;
        for (n2 = 0; n2 < this.i; ++n2) {
            for (int i2 = 0; i2 < this.j; ++i2) {
                for (int i3 = 0; i3 < this.k; ++i3) {
                    this.h[(i3 * this.j + i2) * this.i + n2] = new h(this.b, n2 << 4, i2 << 4, i3 << 4, 16, this.l + n3);
                    if (this.p) {
                        this.h[(i3 * this.j + i2) * this.i + n2].f = this.o.get(n4);
                    }
                    ++n4;
                    this.g[(i3 * this.j + i2) * this.i + n2] = this.h[(i3 * this.j + i2) * this.i + n2];
                    n3 += 3;
                }
            }
        }
        for (n2 = 0; n2 < this.f.size(); ++n2) {
            ((h)this.f.get((int)n2)).c = false;
        }
        this.f.clear();
        GL11.glNewList((int)this.d, (int)4864);
        this.i();
        GL11.glEndList();
        GL11.glNewList((int)(this.d + 1), (int)4864);
        this.j();
        GL11.glEndList();
        this.b(0, 0, 0, this.b.a, this.b.c, this.b.b);
    }

    public final void a(net.minecraft.a.d.a a2, e e2, float f2) {
        net.minecraft.a.a.i i2 = this.b.r;
        net.minecraft.client.a.a.i.a.a(this.b, this.c, this.m.f, f2);
        this.s = 0;
        this.t = 0;
        this.u = 0;
        for (int i3 = 0; i3 < i2.a; ++i3) {
            for (int i4 = 0; i4 < i2.b; ++i4) {
                for (int i5 = 0; i5 < i2.c; ++i5) {
                    boolean bl;
                    List list = i2.d[(i5 * i2.b + i4) * i2.a + i3];
                    if (list.size() == 0) continue;
                    int n2 = (i3 << 3) + 4;
                    int n3 = (i4 << 3) + 4;
                    int n4 = (i5 << 3) + 4;
                    this.s += list.size();
                    float f3 = n4;
                    float f4 = n3;
                    float f5 = n2;
                    b b2 = this;
                    if (f5 < 0.0f || f4 < 0.0f || f3 < 0.0f || f5 >= (float)b2.b.a || f4 >= (float)b2.b.c || f3 >= (float)b2.b.b) {
                        bl = true;
                    } else {
                        int n5 = (int)(f5 / 16.0f);
                        int n6 = (int)(f4 / 16.0f);
                        int n7 = (int)(f3 / 16.0f);
                        bl = b2.h[(n7 * b2.j + n6) * b2.i + n5].b && b2.h[(n7 * b2.j + n6) * b2.i + n5].d;
                    }
                    if (bl) {
                        for (n2 = 0; n2 < list.size(); ++n2) {
                            net.minecraft.a.c.b b3 = (net.minecraft.a.c.b)list.get(n2);
                            net.minecraft.a.d.a a3 = a2;
                            net.minecraft.a.c.b b4 = b3;
                            f4 = b3.h - a3.a;
                            f3 = b4.i - a3.b;
                            float f6 = b4.j - a3.c;
                            float f7 = f4 = f4 * f4 + f3 * f3 + f6 * f6;
                            net.minecraft.a.c.b b5 = b4;
                            net.minecraft.a.d.b b6 = b5.r;
                            f4 = b6.d - b6.a;
                            f3 = b6.e - b6.b;
                            f5 = b6.f - b6.c;
                            f5 = (f4 + f3 + f5) / 3.0f;
                            if (!(f7 < (f5 *= 64.0f) * f5) || !e2.a(b3.r) || b3 == this.b.y && !this.m.w.v) continue;
                            ++this.t;
                            net.minecraft.client.a.a.i.a.a(b3, f2);
                        }
                        continue;
                    }
                    this.u += list.size();
                }
            }
        }
    }

    public final String b() {
        return "C: " + this.A + "/" + this.x + ". F: " + this.y + ", O: " + this.z;
    }

    public final String c() {
        return "E: " + this.t + "/" + this.s + ". B: " + this.u + ", I: " + (this.s - this.u - this.t);
    }

    public final int a(net.minecraft.a.c.e.a a2, int n2) {
        int n3;
        float f2;
        float f3;
        float f4;
        if (n2 == 0) {
            this.x = 0;
            this.y = 0;
            this.z = 0;
            this.A = 0;
        }
        if ((f4 = a2.h - this.B) * f4 + (f3 = a2.i - this.C) * f3 + (f2 = a2.j - this.D) * f2 > 16.0f) {
            this.B = a2.h;
            this.C = a2.i;
            this.D = a2.j;
            Arrays.sort(this.g, new l(a2));
        }
        if (this.p && n2 == 0) {
            int n4;
            int n5 = 8;
            this.a(0, 8);
            for (n4 = 0; n4 < 8; ++n4) {
                this.g[n4].d = true;
            }
            n3 = 0 + this.b(0, 8, n2);
            do {
                int n6 = n5;
                if ((n5 <<= 1) > this.g.length) {
                    n5 = this.g.length;
                }
                GL11.glDisable((int)3553);
                GL11.glDisable((int)2896);
                GL11.glDisable((int)3008);
                GL11.glColorMask((boolean)false, (boolean)false, (boolean)false, (boolean)false);
                GL11.glDepthMask((boolean)false);
                this.a(n6, n5);
                for (n4 = n6; n4 < n5; ++n4) {
                    float f5;
                    int n7;
                    if (!this.g[n4].b) {
                        this.g[n4].d = true;
                    }
                    if (!this.g[n4].b || this.g[n4].e || this.q % (n7 = (int)(1.0f + (f5 = a.a.c(this.g[n4].a(a2))) / 64.0f)) != n4 % n7) continue;
                    ARBOcclusionQuery.glBeginQueryARB((int)35092, (int)this.g[n4].f);
                    this.g[n4].c();
                    ARBOcclusionQuery.glEndQueryARB((int)35092);
                    this.g[n4].e = true;
                }
                GL11.glColorMask((boolean)true, (boolean)true, (boolean)true, (boolean)true);
                GL11.glDepthMask((boolean)true);
                GL11.glEnable((int)3553);
                GL11.glEnable((int)3008);
                n3 += this.b(n6, n5, n2);
            } while (n5 < this.g.length);
        } else {
            n3 = 0 + this.b(0, this.g.length, n2);
        }
        return n3;
    }

    private void a(int n2, int n3) {
        while (n2 < n3) {
            if (this.g[n2].e) {
                this.w.clear();
                ARBOcclusionQuery.glGetQueryObjectuARB((int)this.g[n2].f, (int)34919, (IntBuffer)this.w);
                if (this.w.get(0) != 0) {
                    this.g[n2].e = false;
                    this.w.clear();
                    ARBOcclusionQuery.glGetQueryObjectuARB((int)this.g[n2].f, (int)34918, (IntBuffer)this.w);
                    this.g[n2].d = this.w.get(0) != 0;
                }
            }
            ++n2;
        }
    }

    private int b(int n2, int n3, int n4) {
        int n5 = 0;
        while (n2 < n3) {
            if (n4 == 0) {
                ++this.x;
                if (!this.g[n2].b) {
                    ++this.y;
                }
                if (this.g[n2].b && !this.g[n2].d) {
                    ++this.z;
                }
                if (this.g[n2].b && this.g[n2].d) {
                    ++this.A;
                }
            }
            if (this.g[n2].b && this.g[n2].d) {
                n5 = this.g[n2].a(this.v, n5, n4);
            }
            ++n2;
        }
        this.e.clear();
        this.e.put(this.v, 0, n5);
        this.e.flip();
        if (this.e.remaining() > 0) {
            GL11.glCallLists((IntBuffer)this.e);
        }
        return this.e.remaining();
    }

    public final void d() {
        GL11.glBindTexture((int)3553, (int)this.c.a("/terrain.png"));
        GL11.glCallLists((IntBuffer)this.e);
    }

    public final void e() {
        ++this.q;
    }

    public final void a(float f2) {
        float f3;
        GL11.glDisable((int)3553);
        net.minecraft.a.d.a a2 = this.b.b(f2);
        float f4 = a2.a;
        float f5 = a2.b;
        float f6 = a2.c;
        if (this.m.w.g) {
            float f7 = (f4 * 30.0f + f5 * 59.0f + f6 * 11.0f) / 100.0f;
            f5 = (f4 * 30.0f + f5 * 70.0f) / 100.0f;
            f6 = (f4 * 30.0f + f6 * 70.0f) / 100.0f;
            f4 = f7;
        }
        GL11.glDepthMask((boolean)false);
        d d2 = net.minecraft.client.a.d.a;
        d2.b();
        d2.a(f4, f5, f6);
        f6 = this.b.c + 10;
        for (int i2 = -2048; i2 < this.b.a + 2048; i2 += 512) {
            for (int i3 = -2048; i3 < this.b.b + 2048; i3 += 512) {
                d2.b(i2, f6, i3);
                d2.b(i2 + 512, f6, i3);
                d2.b(i2 + 512, f6, i3 + 512);
                d2.b(i2, f6, i3 + 512);
            }
        }
        d2.a();
        GL11.glEnable((int)3553);
        GL11.glDisable((int)2912);
        GL11.glDisable((int)3008);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)1, (int)1);
        GL11.glPushMatrix();
        float f8 = this.b.y.B + (this.b.y.h - this.b.y.B) * f2;
        f5 = this.b.y.C + (this.b.y.i - this.b.y.C) * f2;
        float f9 = this.b.y.D + (this.b.y.j - this.b.y.D) * f2;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glTranslatef((float)f8, (float)f5, (float)f9);
        GL11.glRotatef((float)0.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)(this.b.c(f2) * 360.0f), (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glBindTexture((int)3553, (int)this.c.a("/terrain/sun.png"));
        d2.b();
        d2.a(-30.0f, 100.0f, -30.0f, 0.0f, 0.0f);
        d2.a(30.0f, 100.0f, -30.0f, 1.0f, 0.0f);
        d2.a(30.0f, 100.0f, 30.0f, 1.0f, 1.0f);
        d2.a(-30.0f, 100.0f, 30.0f, 0.0f, 1.0f);
        d2.a();
        GL11.glBindTexture((int)3553, (int)this.c.a("/terrain/moon.png"));
        d2.b();
        d2.a(-20.0f, -100.0f, 20.0f, 1.0f, 1.0f);
        d2.a(20.0f, -100.0f, 20.0f, 0.0f, 1.0f);
        d2.a(20.0f, -100.0f, -20.0f, 0.0f, 0.0f);
        d2.a(-20.0f, -100.0f, -20.0f, 1.0f, 0.0f);
        d2.a();
        GL11.glDisable((int)3553);
        f6 = this.b.a(f2);
        GL11.glColor4f((float)f6, (float)f6, (float)f6, (float)f6);
        GL11.glCallList((int)this.r);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glEnable((int)3008);
        GL11.glEnable((int)2912);
        GL11.glPopMatrix();
        GL11.glDepthMask((boolean)true);
        GL11.glBindTexture((int)3553, (int)this.c.a("/clouds.png"));
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        net.minecraft.a.d.a a3 = this.b.e(f2);
        f5 = a3.a;
        f9 = a3.b;
        float f10 = a3.c;
        if (this.m.w.g) {
            f6 = (f5 * 30.0f + f9 * 59.0f + f10 * 11.0f) / 100.0f;
            f3 = (f5 * 30.0f + f9 * 70.0f) / 100.0f;
            f10 = (f5 * 30.0f + f10 * 70.0f) / 100.0f;
            f5 = f6;
            f9 = f3;
        }
        f6 = this.b.u;
        f3 = ((float)this.q + f2) * 4.8828125E-4f * 0.03f;
        d2.b();
        d2.a(f5, f9, f10);
        for (int i4 = -2048; i4 < this.b.a + 2048; i4 += 512) {
            for (int i5 = -2048; i5 < this.b.b + 2048; i5 += 512) {
                d2.a(i4, f6, i5 + 512, (float)i4 * 4.8828125E-4f + f3, (float)(i5 + 512) * 4.8828125E-4f);
                d2.a(i4 + 512, f6, i5 + 512, (float)(i4 + 512) * 4.8828125E-4f + f3, (float)(i5 + 512) * 4.8828125E-4f);
                d2.a(i4 + 512, f6, i5, (float)(i4 + 512) * 4.8828125E-4f + f3, (float)i5 * 4.8828125E-4f);
                d2.a(i4, f6, i5, (float)i4 * 4.8828125E-4f + f3, (float)i5 * 4.8828125E-4f);
                d2.a(i4, f6, i5, (float)i4 * 4.8828125E-4f + f3, (float)i5 * 4.8828125E-4f);
                d2.a(i4 + 512, f6, i5, (float)(i4 + 512) * 4.8828125E-4f + f3, (float)i5 * 4.8828125E-4f);
                d2.a(i4 + 512, f6, i5 + 512, (float)(i4 + 512) * 4.8828125E-4f + f3, (float)(i5 + 512) * 4.8828125E-4f);
                d2.a(i4, f6, i5 + 512, (float)i4 * 4.8828125E-4f + f3, (float)(i5 + 512) * 4.8828125E-4f);
            }
        }
        d2.a();
    }

    public final void f() {
        float f2 = this.b.c(0, this.b.g(), 0);
        GL11.glBindTexture((int)3553, (int)this.c.a("/dirt.png"));
        if (this.b.g() > this.b.h() && this.b.m == net.minecraft.a.a.b.x.p.at) {
            GL11.glBindTexture((int)3553, (int)this.c.a("/grass.png"));
        }
        GL11.glColor4f((float)f2, (float)f2, (float)f2, (float)1.0f);
        GL11.glEnable((int)3553);
        GL11.glCallList((int)this.d);
    }

    private void i() {
        d d2 = net.minecraft.client.a.d.a;
        float f2 = this.b.g();
        int n2 = 128;
        if (128 > this.b.a) {
            n2 = this.b.a;
        }
        if (n2 > this.b.b) {
            n2 = this.b.b;
        }
        int n3 = 2048 / n2;
        d2.b();
        for (int i2 = -n2 * n3; i2 < this.b.a + n2 * n3; i2 += n2) {
            for (int i3 = -n2 * n3; i3 < this.b.b + n2 * n3; i3 += n2) {
                if (f2 >= 0.0f && i2 >= 0 && i3 >= 0 && i2 < this.b.a && i3 < this.b.b) continue;
                d2.a(i2, f2, i3 + n2, 0.0f, n2);
                d2.a(i2 + n2, f2, i3 + n2, n2, n2);
                d2.a(i2 + n2, f2, i3, n2, 0.0f);
                d2.a(i2, f2, i3, 0.0f, 0.0f);
            }
        }
        d2.a();
    }

    public final void g() {
        GL11.glEnable((int)3553);
        GL11.glEnable((int)3042);
        GL11.glBindTexture((int)3553, (int)this.c.a("/water.png"));
        float f2 = this.b.c(0, this.b.h(), 0);
        GL11.glColor4f((float)f2, (float)f2, (float)f2, (float)1.0f);
        GL11.glCallList((int)(this.d + 1));
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glDisable((int)3042);
    }

    private void j() {
        float f2 = this.b.h();
        GL11.glBlendFunc((int)770, (int)771);
        d d2 = net.minecraft.client.a.d.a;
        int n2 = 128;
        if (128 > this.b.a) {
            n2 = this.b.a;
        }
        if (n2 > this.b.b) {
            n2 = this.b.b;
        }
        int n3 = 2048 / n2;
        d2.b();
        float f3 = net.minecraft.a.a.b.x.p.au;
        float f4 = net.minecraft.a.a.b.x.p.aw;
        for (int i2 = -n2 * n3; i2 < this.b.a + n2 * n3; i2 += n2) {
            for (int i3 = -n2 * n3; i3 < this.b.b + n2 * n3; i3 += n2) {
                float f5 = f2 + net.minecraft.a.a.b.x.p.av;
                if (f2 >= 0.0f && i2 >= 0 && i3 >= 0 && i2 < this.b.a && i3 < this.b.b) continue;
                d2.a((float)i2 + f3, f5, (float)(i3 + n2) + f4, 0.0f, n2);
                d2.a((float)(i2 + n2) + f3, f5, (float)(i3 + n2) + f4, n2, n2);
                d2.a((float)(i2 + n2) + f3, f5, (float)i3 + f4, n2, 0.0f);
                d2.a((float)i2 + f3, f5, (float)i3 + f4, 0.0f, 0.0f);
                d2.a((float)i2 + f3, f5, (float)i3 + f4, 0.0f, 0.0f);
                d2.a((float)(i2 + n2) + f3, f5, (float)i3 + f4, n2, 0.0f);
                d2.a((float)(i2 + n2) + f3, f5, (float)(i3 + n2) + f4, n2, n2);
                d2.a((float)i2 + f3, f5, (float)(i3 + n2) + f4, 0.0f, n2);
            }
        }
        d2.a();
        GL11.glDisable((int)3042);
    }

    public final void a(net.minecraft.a.c.e.a a2) {
        Collections.sort(this.f, new a(a2));
        int n2 = this.f.size() - 1;
        int n3 = this.f.size();
        for (int i2 = 0; i2 < n3; ++i2) {
            h h2;
            h h3 = (h)this.f.get(n2 - i2);
            if (h2.a(a2) > 2500.0f && i2 > 4) {
                return;
            }
            this.f.remove(h3);
            h3.a();
            h3.c = false;
        }
    }

    public final void a(net.minecraft.a.d.c c2, int n2, net.minecraft.a.b.k k2) {
        d d2 = net.minecraft.client.a.d.a;
        GL11.glEnable((int)3042);
        GL11.glEnable((int)3008);
        GL11.glBlendFunc((int)770, (int)1);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)((a.a.a((float)System.currentTimeMillis() / 100.0f) * 0.2f + 0.4f) * 0.5f));
        if (this.a > 0.0f) {
            GL11.glBlendFunc((int)774, (int)768);
            int n3 = this.c.a("/terrain.png");
            GL11.glBindTexture((int)3553, (int)n3);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)0.5f);
            GL11.glPushMatrix();
            n3 = this.b.a(c2.b, c2.c, c2.d);
            x x2 = n3 > 0 ? net.minecraft.a.a.b.x.c[n3] : null;
            GL11.glDisable((int)3008);
            d2.b();
            d2.c();
            if (x2 == null) {
                x2 = net.minecraft.a.a.b.x.i;
            }
            this.n.a(x2, c2.b, c2.c, c2.d, 240 + (int)(this.a * 10.0f));
            d2.a();
            GL11.glEnable((int)3008);
            GL11.glDepthMask((boolean)true);
            GL11.glPopMatrix();
        }
        GL11.glDisable((int)3042);
        GL11.glDisable((int)3008);
    }

    public final void a(net.minecraft.a.d.c object, int n2) {
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glColor4f((float)0.0f, (float)0.0f, (float)0.0f, (float)0.4f);
        GL11.glLineWidth((float)2.0f);
        GL11.glDisable((int)3553);
        GL11.glDepthMask((boolean)false);
        n2 = this.b.a(((net.minecraft.a.d.c)object).b, ((net.minecraft.a.d.c)object).c, ((net.minecraft.a.d.c)object).d);
        if (n2 > 0) {
            object = net.minecraft.a.a.b.x.c[n2].b(((net.minecraft.a.d.c)object).b, ((net.minecraft.a.d.c)object).c, ((net.minecraft.a.d.c)object).d).b(0.002f, 0.002f, 0.002f);
            d d2 = net.minecraft.client.a.d.a;
            d2.a(3);
            d2.b(((net.minecraft.a.d.b)object).a, ((net.minecraft.a.d.b)object).b, ((net.minecraft.a.d.b)object).c);
            d2.b(((net.minecraft.a.d.b)object).d, ((net.minecraft.a.d.b)object).b, ((net.minecraft.a.d.b)object).c);
            d2.b(((net.minecraft.a.d.b)object).d, ((net.minecraft.a.d.b)object).b, ((net.minecraft.a.d.b)object).f);
            d2.b(((net.minecraft.a.d.b)object).a, ((net.minecraft.a.d.b)object).b, ((net.minecraft.a.d.b)object).f);
            d2.b(((net.minecraft.a.d.b)object).a, ((net.minecraft.a.d.b)object).b, ((net.minecraft.a.d.b)object).c);
            d2.a();
            d2.a(3);
            d2.b(((net.minecraft.a.d.b)object).a, ((net.minecraft.a.d.b)object).e, ((net.minecraft.a.d.b)object).c);
            d2.b(((net.minecraft.a.d.b)object).d, ((net.minecraft.a.d.b)object).e, ((net.minecraft.a.d.b)object).c);
            d2.b(((net.minecraft.a.d.b)object).d, ((net.minecraft.a.d.b)object).e, ((net.minecraft.a.d.b)object).f);
            d2.b(((net.minecraft.a.d.b)object).a, ((net.minecraft.a.d.b)object).e, ((net.minecraft.a.d.b)object).f);
            d2.b(((net.minecraft.a.d.b)object).a, ((net.minecraft.a.d.b)object).e, ((net.minecraft.a.d.b)object).c);
            d2.a();
            d2.a(1);
            d2.b(((net.minecraft.a.d.b)object).a, ((net.minecraft.a.d.b)object).b, ((net.minecraft.a.d.b)object).c);
            d2.b(((net.minecraft.a.d.b)object).a, ((net.minecraft.a.d.b)object).e, ((net.minecraft.a.d.b)object).c);
            d2.b(((net.minecraft.a.d.b)object).d, ((net.minecraft.a.d.b)object).b, ((net.minecraft.a.d.b)object).c);
            d2.b(((net.minecraft.a.d.b)object).d, ((net.minecraft.a.d.b)object).e, ((net.minecraft.a.d.b)object).c);
            d2.b(((net.minecraft.a.d.b)object).d, ((net.minecraft.a.d.b)object).b, ((net.minecraft.a.d.b)object).f);
            d2.b(((net.minecraft.a.d.b)object).d, ((net.minecraft.a.d.b)object).e, ((net.minecraft.a.d.b)object).f);
            d2.b(((net.minecraft.a.d.b)object).a, ((net.minecraft.a.d.b)object).b, ((net.minecraft.a.d.b)object).f);
            d2.b(((net.minecraft.a.d.b)object).a, ((net.minecraft.a.d.b)object).e, ((net.minecraft.a.d.b)object).f);
            d2.a();
        }
        GL11.glDepthMask((boolean)true);
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
    }

    private void b(int n2, int n3, int n4, int n5, int n6, int n7) {
        n2 /= 16;
        n3 /= 16;
        n4 /= 16;
        n5 /= 16;
        n6 /= 16;
        n7 /= 16;
        if (n2 < 0) {
            n2 = 0;
        }
        if (n3 < 0) {
            n3 = 0;
        }
        if (n4 < 0) {
            n4 = 0;
        }
        if (n5 > this.i - 1) {
            n5 = this.i - 1;
        }
        if (n6 > this.j - 1) {
            n6 = this.j - 1;
        }
        if (n7 > this.k - 1) {
            n7 = this.k - 1;
        }
        while (n2 <= n5) {
            for (int i2 = n3; i2 <= n6; ++i2) {
                for (int i3 = n4; i3 <= n7; ++i3) {
                    h h2 = this.h[(i3 * this.j + i2) * this.i + n2];
                    if (h2.c) continue;
                    h2.c = true;
                    this.f.add(this.h[(i3 * this.j + i2) * this.i + n2]);
                }
            }
            ++n2;
        }
    }

    public final void a(int n2, int n3, int n4) {
        this.b(n2 - 1, n3 - 1, n4 - 1, n2 + 1, n3 + 1, n4 + 1);
    }

    public final void a(int n2, int n3, int n4, int n5, int n6, int n7) {
        this.b(n2 - 1, n3 - 1, n4 - 1, n5 + 1, n6 + 1, n7 + 1);
    }

    public final void a(e e2) {
        for (int i2 = 0; i2 < this.h.length; ++i2) {
            this.h[i2].a(e2);
        }
    }

    public final void a(String string, float f2, float f3, float f4, float f5, float f6) {
        this.m.x.a(string, f2, f3, f4, f5, f6);
    }

    public final void a(String string, float f2, float f3, float f4, float f5, float f6, float f7) {
        float f8 = this.b.y.h - f2;
        float f9 = this.b.y.i - f3;
        float f10 = this.b.y.j - f4;
        if (f8 * f8 + f9 * f9 + f10 * f10 > 256.0f) {
            return;
        }
        if (string == "bubble") {
            this.m.g.a(new net.minecraft.client.f.a(this.b, f2, f3, f4, f5, f6, f7));
            return;
        }
        if (string == "smoke") {
            this.m.g.a(new j(this.b, f2, f3, f4));
            return;
        }
        if (string == "explode") {
            this.m.g.a(new net.minecraft.client.f.h(this.b, f2, f3, f4, f5, f6, f7));
            return;
        }
        if (string == "flame") {
            this.m.g.a(new net.minecraft.client.f.c(this.b, f2, f3, f4));
            return;
        }
        if (string == "lava") {
            this.m.g.a(new g(this.b, f2, f3, f4));
            return;
        }
        if (string == "splash") {
            this.m.g.a(new net.minecraft.client.f.f(this.b, f2, f3, f4));
            return;
        }
        if (string == "largesmoke") {
            this.m.g.a(new j(this.b, f2, f3, f4, 2.5f));
        }
    }

    public final void a(String string, float f2, float f3, float f4, float f5) {
        this.m.x.a(f2, f3, f4);
    }

    public final void a(net.minecraft.a.c.b b2) {
        if (b2.N != null) {
            this.c.a(b2.N, new c());
        }
    }

    public final void b(net.minecraft.a.c.b b2) {
        if (b2.N != null) {
            this.c.b(b2.N);
        }
    }

    public final void h() {
        GL11.glNewList((int)this.d, (int)4864);
        this.i();
        GL11.glEndList();
        GL11.glNewList((int)(this.d + 1), (int)4864);
        this.j();
        GL11.glEndList();
    }
}

