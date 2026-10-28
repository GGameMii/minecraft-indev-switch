/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client.c.a;

import net.minecraft.a.b;
import net.minecraft.client.a.k;
import net.minecraft.client.c.a.g;
import net.minecraft.client.c.a.j;
import org.lwjgl.opengl.GL11;

public final class i
extends j {
    private b k;
    private b l;
    private int m = 0;

    public i(b b2, b b3) {
        int n2;
        int n3;
        this.k = b2;
        this.l = b3;
        this.f = false;
        this.m = b3.a() / 9;
        this.i = 114 + this.m * 18;
        int n4 = (this.m - 4) * 18;
        for (n3 = 0; n3 < this.m; ++n3) {
            for (n2 = 0; n2 < 9; ++n2) {
                this.j.add(new g(this, b3, n2 + n3 * 9, 8 + n2 * 18, 18 + n3 * 18));
            }
        }
        for (n3 = 0; n3 < 3; ++n3) {
            for (n2 = 0; n2 < 9; ++n2) {
                this.j.add(new g(this, b2, n2 + (n3 + 1) * 9, 8 + n2 * 18, 103 + n3 * 18 + n4));
            }
        }
        for (n3 = 0; n3 < 9; ++n3) {
            this.j.add(new g(this, b2, n3, 8 + n3 * 18, n4 + 161));
        }
    }

    protected final void e_() {
        this.g.b(this.l.b(), 8, 6, 0x404040);
        this.g.b(this.k.b(), 8, this.i - 96 + 2, 0x404040);
    }

    protected final void d() {
        int n2 = this.b.m.a("/gui/container.png");
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        net.minecraft.client.a.k.a(n2);
        n2 = (this.c - this.a) / 2;
        int n3 = (this.d - this.i) / 2;
        this.b(n2, n3, 0, 0, this.a, this.m * 18 + 17);
        this.b(n2, n3 + this.m * 18 + 17, 0, 126, this.a, 96);
    }
}

