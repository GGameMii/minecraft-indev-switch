/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client.c.a;

import net.minecraft.a.b.a.f;
import net.minecraft.client.a.k;
import net.minecraft.client.c.a.a;
import net.minecraft.client.c.a.d;
import net.minecraft.client.c.a.g;
import net.minecraft.client.c.a.h;
import net.minecraft.client.c.a.j;
import org.lwjgl.opengl.GL11;

public final class b
extends j {
    private a k = new a(this, 3, 3);
    private net.minecraft.a.b l = new h();

    public b(net.minecraft.a.c.e.b b2) {
        int n2;
        int n3;
        this.j.add(new d(this, this.k, this.l, 0, 124, 35));
        for (n3 = 0; n3 < 3; ++n3) {
            for (n2 = 0; n2 < 3; ++n2) {
                this.j.add(new g(this, this.k, n2 + n3 * 3, 30 + n2 * 18, 17 + n3 * 18));
            }
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
        for (int i2 = 0; i2 < 9; ++i2) {
            net.minecraft.a.b.k k2 = this.k.a(i2);
            if (k2 == null) continue;
            this.b.f.a(k2);
        }
    }

    public final void d_() {
        int[] nArray = new int[9];
        for (int i2 = 0; i2 < 3; ++i2) {
            for (int i3 = 0; i3 < 3; ++i3) {
                int n2 = i2 + i3 * 3;
                net.minecraft.a.b.k k2 = this.k.a(n2);
                nArray[n2] = k2 == null ? -1 : k2.c;
            }
        }
        this.l.a(0, net.minecraft.a.b.a.f.a().a(nArray));
    }

    protected final void e_() {
        this.g.b("Crafting", 28, 6, 0x404040);
        this.g.b("Inventory", 8, this.i - 96 + 2, 0x404040);
    }

    protected final void d() {
        int n2 = this.b.m.a("/gui/crafting.png");
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        net.minecraft.client.a.k.a(n2);
        n2 = (this.c - this.a) / 2;
        int n3 = (this.d - this.i) / 2;
        this.b(n2, n3, 0, 0, this.a, this.i);
    }
}

