/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client.a;

import a.a;
import net.minecraft.a.a.b.x;
import net.minecraft.a.a.g;
import net.minecraft.a.d.b;
import net.minecraft.client.a.b.e;
import net.minecraft.client.a.d;
import net.minecraft.client.a.f;
import org.lwjgl.opengl.GL11;

public final class h {
    private g g;
    private int h = -1;
    private static d i = net.minecraft.client.a.d.a;
    public static int a = 0;
    private int j;
    private int k;
    private int l;
    private int m;
    private int n;
    private int o;
    public boolean b = false;
    private boolean[] p = new boolean[2];
    private int q;
    private int r;
    private int s;
    public boolean c;
    private b t;
    private f u;
    public boolean d = true;
    public boolean e;
    public int f;

    public h(g g2, int n2, int n3, int n4, int n5, int n6) {
        this.u = new f(g2);
        this.g = g2;
        this.j = n2;
        this.k = n3;
        this.l = n4;
        this.o = 16;
        this.n = 16;
        this.m = 16;
        this.q = n2 + this.m / 2;
        this.r = n3 + this.n / 2;
        this.s = n4 + this.o / 2;
        a.a.c(this.m * this.m + this.n * this.n + this.o * this.o);
        this.t = new b(n2, n3, n4, n2 + this.m, n3 + this.n, n4 + this.o).b(2.0f, 2.0f, 2.0f);
        this.h = n6;
        this.d();
        GL11.glDisable((int)3553);
        GL11.glNewList((int)(n6 + 2), (int)4864);
        net.minecraft.client.a.a.e.a(this.t);
        GL11.glEndList();
        GL11.glEnable((int)3553);
    }

    public final void a() {
        int n2;
        if (!this.c) {
            return;
        }
        ++a;
        int n3 = this.j;
        int n4 = this.k;
        int n5 = this.l;
        int n6 = this.j + this.m;
        int n7 = this.k + this.n;
        int n8 = this.l + this.o;
        for (n2 = 0; n2 < 2; ++n2) {
            this.p[n2] = true;
        }
        for (n2 = 0; n2 < 2; ++n2) {
            boolean bl = false;
            boolean bl2 = false;
            i.b();
            GL11.glNewList((int)(this.h + n2), (int)4864);
            for (int i2 = n4; i2 < n7; ++i2) {
                for (int i3 = n5; i3 < n8; ++i3) {
                    int n9 = (i2 * this.g.b + i3) * this.g.a + n3;
                    for (int i4 = n3; i4 < n6; ++i4) {
                        int n10;
                        if ((n10 = this.g.d[n9++] & 0xFF) <= 0) continue;
                        x x2 = x.c[n10];
                        if (x2.f() != n2) {
                            bl = true;
                            continue;
                        }
                        bl2 |= this.u.b(x2, i4, i2, i3);
                    }
                }
            }
            i.a();
            GL11.glEndList();
            if (bl2) {
                this.p[n2] = false;
            }
            if (!bl) break;
        }
    }

    public final float a(net.minecraft.a.c.e e2) {
        float f2 = e2.h - (float)this.q;
        float f3 = e2.i - (float)this.r;
        float f4 = e2.j - (float)this.s;
        return f2 * f2 + f3 * f3 + f4 * f4;
    }

    private void d() {
        for (int i2 = 0; i2 < 2; ++i2) {
            this.p[i2] = true;
        }
    }

    public final void b() {
        this.d();
        this.g = null;
    }

    public final int a(int[] nArray, int n2, int n3) {
        if (!this.b) {
            return n2;
        }
        if (!this.p[n3]) {
            nArray[n2++] = this.h + n3;
        }
        return n2;
    }

    public final void a(e e2) {
        this.b = e2.a(this.t);
    }

    public final void c() {
        GL11.glCallList((int)(this.h + 2));
    }
}

