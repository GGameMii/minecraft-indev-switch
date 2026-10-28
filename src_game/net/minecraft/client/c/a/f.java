/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client.c.a;

import net.minecraft.a.b;
import net.minecraft.client.a.a.i;
import net.minecraft.client.a.k;
import net.minecraft.client.c;
import net.minecraft.client.c.a.a;
import net.minecraft.client.c.a.d;
import net.minecraft.client.c.a.e;
import net.minecraft.client.c.a.g;
import net.minecraft.client.c.a.h;
import net.minecraft.client.c.a.j;
import org.lwjgl.opengl.GL11;

public final class f
extends j {
    private a k = new a(this, 2, 2);
    private b l = new h();
    private float m;
    private float n;

    public f(b b2) {
        int n2;
        int n3;
        this.f = true;
        this.j.add(new d(this, this.k, this.l, 0, 144, 36));
        for (n3 = 0; n3 < 2; ++n3) {
            for (n2 = 0; n2 < 2; ++n2) {
                this.j.add(new g(this, this.k, n2 + (n3 << 1), 88 + n2 * 18, 26 + n3 * 18));
            }
        }
        for (n3 = 0; n3 < 4; ++n3) {
            n2 = n3;
            this.j.add(new e(this, this, b2, b2.a() - 1 - n3, 8, 8 + n3 * 18, n2));
        }
        for (n3 = 0; n3 < 3; ++n3) {
            for (n2 = 0; n2 < 9; ++n2) {
                this.j.add(new g(this, b2, n2 + (n3 + 1) * 9, 8 + n2 * 18, 84 + n3 * 18));
            }
        }
        for (n3 = 0; n3 < 9; ++n3) {
            this.j.add(new g(this, b2, n3, 8 + n3 * 18, 142));
        }
    }

    public final void a() {
        super.a();
        for (int i2 = 0; i2 < this.k.a(); ++i2) {
            net.minecraft.a.b.k k2 = this.k.a(i2);
            if (k2 == null) continue;
            this.b.f.a(k2);
        }
    }

    public final void d_() {
        int[] nArray = new int[9];
        for (int i2 = 0; i2 < 3; ++i2) {
            for (int i3 = 0; i3 < 3; ++i3) {
                net.minecraft.a.b.k k2;
                int n2 = -1;
                if (i2 < 2 && i3 < 2 && (k2 = this.k.a(i2 + (i3 << 1))) != null) {
                    n2 = k2.c;
                }
                nArray[i2 + i3 * 3] = n2;
            }
        }
        this.l.a(0, net.minecraft.a.b.a.f.a().a(nArray));
    }

    protected final void e_() {
        this.g.b("Crafting", 86, 16, 0x404040);
    }

    public final void a(int n2, int n3, float f2) {
        super.a(n2, n3, f2);
        this.m = n2;
        this.n = n3;
    }

    protected final void d() {
        int n2 = this.b.m.a("/gui/inventory.png");
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        net.minecraft.client.a.k.a(n2);
        n2 = (this.c - this.a) / 2;
        int n3 = (this.d - this.i) / 2;
        this.b(n2, n3, 0, 0, this.a, this.i);
        GL11.glEnable((int)2977);
        GL11.glEnable((int)2903);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)(n2 + 51), (float)(n3 + 75), (float)50.0f);
        GL11.glScalef((float)-30.0f, (float)30.0f, (float)30.0f);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        float f2 = this.b.f.T;
        float f3 = this.b.f.n;
        float f4 = this.b.f.o;
        float f5 = (float)(n2 + 51) - this.m;
        float f6 = (float)(n3 + 75 - 50) - this.n;
        GL11.glRotatef((float)135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        net.minecraft.client.c.b();
        GL11.glRotatef((float)-135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)(-((float)Math.atan(f6 / 40.0f)) * 20.0f), (float)1.0f, (float)0.0f, (float)0.0f);
        this.b.f.T = (float)Math.atan(f5 / 40.0f) * 20.0f;
        this.b.f.n = (float)Math.atan(f5 / 40.0f) * 40.0f;
        this.b.f.o = -((float)Math.atan(f6 / 40.0f)) * 20.0f;
        GL11.glTranslatef((float)0.0f, (float)this.b.f.v, (float)0.0f);
        net.minecraft.client.a.a.i.a.a(this.b.f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f);
        this.b.f.T = f2;
        this.b.f.n = f3;
        this.b.f.o = f4;
        GL11.glPopMatrix();
        net.minecraft.client.c.a();
        GL11.glDisable((int)2977);
    }
}

