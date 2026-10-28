/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.BufferUtils
 *  org.lwjgl.opengl.ARBVertexBufferObject
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client.a;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.ARBVertexBufferObject;
import org.lwjgl.opengl.GL11;

public final class d {
    private static boolean b = false;
    private static boolean c = false;
    private ByteBuffer d = BufferUtils.createByteBuffer((int)0x800000);
    private int[] e = new int[0x200000];
    private int f = 0;
    private float g;
    private float h;
    private int i;
    private boolean j = false;
    private boolean k = false;
    private int l = 0;
    private int m = 0;
    private boolean n = false;
    private int o;
    public static d a = new d();
    private boolean p = false;
    private boolean q = false;
    private IntBuffer r;
    private int s = 0;
    private int t = 10;

    private d() {
        if (this.q) {
            this.r = BufferUtils.createIntBuffer((int)this.t);
            ARBVertexBufferObject.glGenBuffersARB((IntBuffer)this.r);
        }
    }

    public final void a() {
        if (!this.p) {
            throw new IllegalStateException("Not tesselating!");
        }
        this.p = false;
        if (this.f > 0) {
            IntBuffer intBuffer = this.d.asIntBuffer();
            FloatBuffer floatBuffer = this.d.asFloatBuffer();
            intBuffer.clear();
            intBuffer.put(this.e, 0, this.l);
            this.d.position(0);
            this.d.limit(this.l << 2);
            if (this.q) {
                this.s = (this.s + 1) % this.t;
                ARBVertexBufferObject.glBindBufferARB((int)34962, (int)this.r.get(this.s));
                ARBVertexBufferObject.glBufferDataARB((int)34962, (ByteBuffer)this.d, (int)35040);
            }
            if (this.k) {
                if (this.q) {
                    GL11.glTexCoordPointer((int)2, (int)5126, (int)32, (long)12L);
                } else {
                    floatBuffer.position(3);
                    GL11.glTexCoordPointer((int)2, (int)32, (FloatBuffer)floatBuffer);
                }
                GL11.glEnableClientState((int)32888);
            }
            if (this.j) {
                if (this.q) {
                    GL11.glColorPointer((int)4, (int)5121, (int)32, (long)20L);
                } else {
                    this.d.position(20);
                    GL11.glColorPointer((int)4, (boolean)true, (int)32, (ByteBuffer)this.d);
                }
                GL11.glEnableClientState((int)32886);
            }
            if (this.q) {
                GL11.glVertexPointer((int)3, (int)5126, (int)32, (long)0L);
            } else {
                floatBuffer.position(0);
                GL11.glVertexPointer((int)3, (int)32, (FloatBuffer)floatBuffer);
            }
            GL11.glEnableClientState((int)32884);
            GL11.glDrawArrays((int)this.o, (int)0, (int)this.f);
            GL11.glDisableClientState((int)32884);
            if (this.k) {
                GL11.glDisableClientState((int)32888);
            }
            if (this.j) {
                GL11.glDisableClientState((int)32886);
            }
        }
        this.d();
    }

    private void d() {
        this.f = 0;
        this.d.clear();
        this.l = 0;
        this.m = 0;
    }

    public final void b() {
        this.a(7);
    }

    public final void a(int n2) {
        if (this.p) {
            throw new IllegalStateException("Already tesselating!");
        }
        this.p = true;
        this.d();
        this.o = n2;
        this.j = false;
        this.k = false;
        this.n = false;
    }

    public final void a(float f2, float f3, float f4) {
        this.a((int)(f2 * 255.0f), (int)(f3 * 255.0f), (int)(f4 * 255.0f));
    }

    public final void a(float f2, float f3, float f4, float f5) {
        this.a((int)(f2 * 255.0f), (int)(f3 * 255.0f), (int)(f4 * 255.0f), (int)(f5 * 255.0f));
    }

    private void a(int n2, int n3, int n4) {
        this.a(n2, n3, n4, 255);
    }

    private void a(int n2, int n3, int n4, int n5) {
        if (this.n) {
            return;
        }
        if (n2 > 255) {
            n2 = 255;
        }
        if (n3 > 255) {
            n3 = 255;
        }
        if (n4 > 255) {
            n4 = 255;
        }
        if (n5 > 255) {
            n5 = 255;
        }
        if (n2 < 0) {
            n2 = 0;
        }
        if (n3 < 0) {
            n3 = 0;
        }
        if (n4 < 0) {
            n4 = 0;
        }
        if (n5 < 0) {
            n5 = 0;
        }
        this.j = true;
        this.i = n5 << 24 | n4 << 16 | n3 << 8 | n2;
    }

    public final void a(float f2, float f3, float f4, float f5, float f6) {
        float f7 = f6;
        f6 = f5;
        d d2 = this;
        this.k = true;
        d2.g = f6;
        d2.h = f7;
        this.b(f2, f3, f4);
    }

    public final void b(float f2, float f3, float f4) {
        ++this.m;
        if (this.k) {
            this.e[this.l + 3] = Float.floatToRawIntBits(this.g);
            this.e[this.l + 4] = Float.floatToRawIntBits(this.h);
        }
        if (this.j) {
            this.e[this.l + 5] = this.i;
        }
        this.e[this.l] = Float.floatToRawIntBits(f2);
        this.e[this.l + 1] = Float.floatToRawIntBits(f3);
        this.e[this.l + 2] = Float.floatToRawIntBits(f4);
        this.l += 8;
        ++this.f;
        if (this.f % 4 == 0 && this.l >= 2097120) {
            this.a();
        }
    }

    public final void b(int n2) {
        int n3 = n2 >> 16 & 0xFF;
        int n4 = n2 >> 8 & 0xFF;
        this.a(n3, n4, n2 &= 0xFF);
    }

    public final void c() {
        this.n = true;
    }

    public static void c(float f2, float f3, float f4) {
        GL11.glNormal3f((float)f2, (float)f3, (float)f4);
    }
}

