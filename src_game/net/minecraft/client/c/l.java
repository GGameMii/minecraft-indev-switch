/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client.c;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.a.c.e.b;
import net.minecraft.client.a.a.e;
import net.minecraft.client.c;
import net.minecraft.client.c.h;
import net.minecraft.client.c.j;
import net.minecraft.client.c.k;
import net.minecraft.client.d;
import net.minecraft.client.g.a;
import org.lwjgl.opengl.GL11;

public final class l
extends k {
    private static e a = new e();
    private List b = new ArrayList();
    private Random c = new Random();
    private d d;
    private int e = 0;

    public l(d d2) {
        this.d = d2;
    }

    public final void a(float f2) {
        int n2;
        int n3;
        int n4;
        Object object;
        boolean bl;
        h h2 = new h(this.d.b, this.d.c);
        int n5 = h2.a();
        int n6 = h2.b();
        j j2 = this.d.n;
        this.d.q.c();
        GL11.glBindTexture((int)3553, (int)this.d.m.a("/gui/gui.png"));
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glEnable((int)3042);
        b b2 = this.d.f.b;
        this.h = -90.0f;
        this.b(n5 / 2 - 91, n6 - 22, 0, 0, 182, 22);
        this.b(n5 / 2 - 91 - 1 + b2.c * 20, n6 - 22 - 1, 0, 22, 24, 22);
        GL11.glBindTexture((int)3553, (int)this.d.m.a("/gui/icons.png"));
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)775, (int)769);
        this.b(n5 / 2 - 7, n6 / 2 - 7, 0, 0, 16, 16);
        GL11.glDisable((int)3042);
        boolean bl2 = bl = this.d.f.L / 3 % 2 == 1;
        if (this.d.f.L < 10) {
            bl = false;
        }
        int n7 = this.d.f.W;
        int n8 = this.d.f.X;
        this.c.setSeed(this.e * 312871);
        if (this.d.a.d()) {
            int n9;
            object = this.d.f;
            n4 = ((a)object).b.e();
            for (n3 = 0; n3 < 10; ++n3) {
                n2 = n6 - 32;
                if (n4 > 0) {
                    n9 = n5 / 2 + 91 - (n3 << 3) - 9;
                    if ((n3 << 1) + 1 < n4) {
                        this.b(n9, n2, 34, 9, 9, 9);
                    }
                    if ((n3 << 1) + 1 == n4) {
                        this.b(n9, n2, 25, 9, 9, 9);
                    }
                    if ((n3 << 1) + 1 > n4) {
                        this.b(n9, n2, 16, 9, 9, 9);
                    }
                }
                n9 = 0;
                if (bl) {
                    n9 = 1;
                }
                int n10 = n5 / 2 - 91 + (n3 << 3);
                if (n7 <= 4) {
                    n2 += this.c.nextInt(2);
                }
                this.b(n10, n2, 16 + n9 * 9, 0, 9, 9);
                if (bl) {
                    if ((n3 << 1) + 1 < n8) {
                        this.b(n10, n2, 70, 0, 9, 9);
                    }
                    if ((n3 << 1) + 1 == n8) {
                        this.b(n10, n2, 79, 0, 9, 9);
                    }
                }
                if ((n3 << 1) + 1 < n7) {
                    this.b(n10, n2, 52, 0, 9, 9);
                }
                if ((n3 << 1) + 1 != n7) continue;
                this.b(n10, n2, 61, 0, 9, 9);
            }
            if (this.d.f.m()) {
                n3 = (int)Math.ceil((double)(this.d.f.M - 2) * 10.0 / 300.0);
                n2 = (int)Math.ceil((double)this.d.f.M * 10.0 / 300.0) - n3;
                for (n9 = 0; n9 < n3 + n2; ++n9) {
                    if (n9 < n3) {
                        this.b(n5 / 2 - 91 + (n9 << 3), n6 - 32 - 9, 16, 18, 9, 9);
                        continue;
                    }
                    this.b(n5 / 2 - 91 + (n9 << 3), n6 - 32 - 9, 25, 18, 9, 9);
                }
            }
        }
        GL11.glDisable((int)3042);
        GL11.glEnable((int)2977);
        GL11.glPushMatrix();
        GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        net.minecraft.client.c.b();
        GL11.glPopMatrix();
        for (n4 = 0; n4 < 9; ++n4) {
            float f3;
            n3 = n5 / 2 - 90 + n4 * 20 + 2;
            n2 = n6 - 16 - 3;
            float f4 = f2;
            n8 = n2;
            n7 = n3;
            int n11 = n4;
            object = this;
            net.minecraft.a.b.k k2 = ((l)object).d.f.b.a[n11];
            if (k2 == null) continue;
            f4 = (float)k2.b - f4;
            if (f3 > 0.0f) {
                GL11.glPushMatrix();
                float f5 = 1.0f + f4 / 5.0f;
                GL11.glTranslatef((float)(n7 + 8), (float)(n8 + 12), (float)0.0f);
                GL11.glScalef((float)(1.0f / f5), (float)((f5 + 1.0f) / 2.0f), (float)1.0f);
                GL11.glTranslatef((float)(-(n7 + 8)), (float)(-(n8 + 12)), (float)0.0f);
            }
            a.a(((l)object).d.m, k2, n7, n8);
            if (f4 > 0.0f) {
                GL11.glPopMatrix();
            }
            a.a(((l)object).d.n, k2, n7, n8);
        }
        net.minecraft.client.c.a();
        GL11.glDisable((int)2977);
        if (this.d.w.d) {
            j2.a("Minecraft Indev (" + this.d.B + ")", 2, 2, 0xFFFFFF);
            object = this.d;
            j2.a(((d)object).e.b(), 2, 12, 0xFFFFFF);
            object = this.d;
            j2.a(((d)object).e.c(), 2, 22, 0xFFFFFF);
            object = this.d;
            j2.a("P: " + ((d)object).g.b() + ". T: " + ((d)object).d.j(), 2, 32, 0xFFFFFF);
            long l2 = Runtime.getRuntime().maxMemory();
            long l3 = Runtime.getRuntime().totalMemory();
            long l4 = Runtime.getRuntime().freeMemory();
            long l5 = l2 - l4;
            String string = "Free memory: " + l5 * 100L / l2 + "% of " + l2 / 1024L / 1024L + "MB";
            l.b(j2, string, n5 - j2.a(string) - 2, 2, 0xE0E0E0);
            string = "Allocated memory: " + l3 * 100L / l2 + "% (" + l3 / 1024L / 1024L + "MB)";
            l.b(j2, string, n5 - j2.a(string) - 2, 12, 0xE0E0E0);
        } else {
            j2.a("Minecraft Indev", 2, 2, 0xFFFFFF);
        }
        for (int i2 = 0; i2 < this.b.size() && i2 < 10; ++i2) {
            if (((net.minecraft.client.k)this.b.get((int)i2)).a >= 200) continue;
            this.b.get(i2);
            j2.a(null, 2, n6 - 8 - i2 * 9 - 20, 0xFFFFFF);
        }
    }

    public final void a() {
        ++this.e;
        for (int i2 = 0; i2 < this.b.size(); ++i2) {
            ++((net.minecraft.client.k)this.b.get((int)i2)).a;
        }
    }
}

