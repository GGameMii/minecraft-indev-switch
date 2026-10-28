/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client.c;

import net.minecraft.client.c.e;
import net.minecraft.client.c.g;
import net.minecraft.client.c.i;
import net.minecraft.client.c.n;
import net.minecraft.client.c.r;
import net.minecraft.client.g.a;
import org.lwjgl.opengl.GL11;

public final class d
extends i {
    public final void b() {
        this.e.clear();
        this.e.add(new r(1, this.c / 2 - 100, this.d / 4 + 72, "Generate new level..."));
        this.e.add(new r(2, this.c / 2 - 100, this.d / 4 + 96, "Load level.."));
        if (this.b.h == null) {
            ((r)this.e.get((int)1)).c = false;
        }
    }

    protected final void a(char c2, int n2) {
    }

    protected final void a(r r2) {
        if (r2.b == 0) {
            this.b.a(new g(this, this.b.w));
        }
        if (r2.b == 1) {
            this.b.a(new n(this));
        }
        if (this.b.h != null && r2.b == 2) {
            this.b.a(new e(this));
        }
    }

    public final void a(int n2, int n3, float f2) {
        net.minecraft.client.c.d.a(0, 0, this.c, this.d, 0x60500000, -1602211792);
        GL11.glPushMatrix();
        GL11.glScalef((float)2.0f, (float)2.0f, (float)2.0f);
        net.minecraft.client.c.d.a(this.g, "Game over!", this.c / 2 / 2, 30, 0xFFFFFF);
        GL11.glPopMatrix();
        a a2 = this.b.f;
        net.minecraft.client.c.d.a(this.g, "Score: &e" + a2.P, this.c / 2, 100, 0xFFFFFF);
        super.a(n2, n3, f2);
    }

    public final boolean c() {
        return false;
    }
}

