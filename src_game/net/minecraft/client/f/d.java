/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client.f;

import a.a;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.a.a.b.x;
import net.minecraft.a.a.g;
import net.minecraft.client.f.b;
import net.minecraft.client.f.k;
import org.lwjgl.opengl.GL11;

public final class d {
    private g a;
    private List[] b = new List[3];
    private net.minecraft.client.a.k c;
    private Random d = new Random();

    public d(g g2, net.minecraft.client.a.k k2) {
        if (g2 != null) {
            this.a = g2;
        }
        this.c = k2;
        for (int i2 = 0; i2 < 3; ++i2) {
            this.b[i2] = new ArrayList();
        }
    }

    public final void a(k k2) {
        int n2 = k2.c();
        this.b[n2].add(k2);
    }

    public final void a() {
        for (int i2 = 0; i2 < 3; ++i2) {
            for (int i3 = 0; i3 < this.b[i2].size(); ++i3) {
                k k2 = (k)this.b[i2].get(i3);
                k2.b_();
                if (!k2.u) continue;
                this.b[i2].remove(i3--);
            }
        }
    }

    public final void a(net.minecraft.a.c.b b2, float f2) {
        float f3 = a.a.b(b2.n * (float)Math.PI / 180.0f);
        float f4 = a.a.a(b2.n * (float)Math.PI / 180.0f);
        float f5 = -f4 * a.a.a(b2.o * (float)Math.PI / 180.0f);
        float f6 = f3 * a.a.a(b2.o * (float)Math.PI / 180.0f);
        float f7 = a.a.b(b2.o * (float)Math.PI / 180.0f);
        for (int i2 = 0; i2 < 2; ++i2) {
            if (this.b[i2].size() == 0) continue;
            int n2 = 0;
            if (i2 == 0) {
                n2 = this.c.a("/particles.png");
            }
            if (i2 == 1) {
                n2 = this.c.a("/terrain.png");
            }
            GL11.glBindTexture((int)3553, (int)n2);
            net.minecraft.client.a.d d2 = net.minecraft.client.a.d.a;
            d2.b();
            for (int i3 = 0; i3 < this.b[i2].size(); ++i3) {
                k k2 = (k)this.b[i2].get(i3);
                k2.a(d2, f2, f3, f7, f4, f5, f6);
            }
            d2.a();
        }
    }

    public final void a(float f2) {
        if (this.b[2].size() == 0) {
            return;
        }
        net.minecraft.client.a.d d2 = net.minecraft.client.a.d.a;
        for (int i2 = 0; i2 < this.b[2].size(); ++i2) {
            k k2 = (k)this.b[2].get(i2);
            k2.a(d2, f2, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        }
    }

    public final void a(g g2) {
        this.a = g2;
        for (int i2 = 0; i2 < 3; ++i2) {
            this.b[i2].clear();
        }
    }

    public final void a(int n2, int n3, int n4) {
        int n5 = this.a.a(n2, n3, n4);
        if (n5 == 0) {
            return;
        }
        x x2 = x.c[n5];
        for (int i2 = 0; i2 < 4; ++i2) {
            for (int i3 = 0; i3 < 4; ++i3) {
                for (int i4 = 0; i4 < 4; ++i4) {
                    float f2 = (float)n2 + ((float)i2 + 0.5f) / 4.0f;
                    float f3 = (float)n3 + ((float)i3 + 0.5f) / 4.0f;
                    float f4 = (float)n4 + ((float)i4 + 0.5f) / 4.0f;
                    this.a(new b(this.a, f2, f3, f4, f2 - (float)n2 - 0.5f, f3 - (float)n3 - 0.5f, f4 - (float)n4 - 0.5f, x2));
                }
            }
        }
    }

    public final void a(int n2, int n3, int n4, int n5) {
        int n6 = this.a.a(n2, n3, n4);
        if (n6 == 0) {
            return;
        }
        x x2 = x.c[n6];
        float f2 = (float)n2 + this.d.nextFloat() * (x2.ax - x2.au - 0.2f) + 0.1f + x2.au;
        float f3 = (float)n3 + this.d.nextFloat() * (x2.ay - x2.av - 0.2f) + 0.1f + x2.av;
        float f4 = (float)n4 + this.d.nextFloat() * (x2.az - x2.aw - 0.2f) + 0.1f + x2.aw;
        if (n5 == 0) {
            f3 = (float)n3 + x2.av - 0.1f;
        }
        if (n5 == 1) {
            f3 = (float)n3 + x2.ay + 0.1f;
        }
        if (n5 == 2) {
            f4 = (float)n4 + x2.aw - 0.1f;
        }
        if (n5 == 3) {
            f4 = (float)n4 + x2.az + 0.1f;
        }
        if (n5 == 4) {
            f2 = (float)n2 + x2.au - 0.1f;
        }
        if (n5 == 5) {
            f2 = (float)n2 + x2.ax + 0.1f;
        }
        this.a(new b(this.a, f2, f3, f4, 0.0f, 0.0f, 0.0f, x2).c(0.2f).d(0.6f));
    }

    public final String b() {
        return "" + (this.b[0].size() + this.b[1].size() + this.b[2].size());
    }
}

