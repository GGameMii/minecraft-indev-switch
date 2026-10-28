/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client.c.a;

import net.minecraft.a.c.e.b;
import net.minecraft.client.a.k;
import net.minecraft.client.c.a.g;
import net.minecraft.client.c.a.h;
import net.minecraft.client.c.a.j;
import org.lwjgl.opengl.GL11;

public final class c
extends j {
    private net.minecraft.a.a.b.a.b k;

    public c(b b2, net.minecraft.a.a.b.a.b b3) {
        int n2;
        new h();
        this.k = b3;
        this.j.add(new g(this, b3, 0, 56, 17));
        this.j.add(new g(this, b3, 1, 56, 53));
        this.j.add(new g(this, b3, 2, 116, 35));
        for (n2 = 0; n2 < 3; ++n2) {
            for (int i2 = 0; i2 < 9; ++i2) {
                this.j.add(new g(this, b2, i2 + (n2 + 1) * 9, 8 + i2 * 18, 84 + n2 * 18));
            }
        }
        for (n2 = 0; n2 < 9; ++n2) {
            this.j.add(new g(this, b2, n2, 8 + n2 * 18, 142));
        }
    }

    protected final void e_() {
        this.g.b("Furnace", 60, 6, 0x404040);
        this.g.b("Inventory", 8, this.i - 96 + 2, 0x404040);
    }

    protected final void d() {
        int n2;
        int n3 = this.b.m.a("/gui/furnace.png");
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        net.minecraft.client.a.k.a(n3);
        n3 = (this.c - this.a) / 2;
        int n4 = (this.d - this.i) / 2;
        this.b(n3, n4, 0, 0, this.a, this.i);
        if (this.k.e()) {
            n2 = this.k.c(12);
            this.b(n3 + 56, n4 + 36 + 12 - n2, 176, 12 - n2, 14, n2 + 2);
        }
        n2 = this.k.b(24);
        this.b(n3 + 79, n4 + 34, 176, 14, n2 + 1, 16);
    }
}

