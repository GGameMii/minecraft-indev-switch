/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.c;

import net.minecraft.client.c.a;
import net.minecraft.client.c.i;
import net.minecraft.client.c.m;
import net.minecraft.client.c.r;
import net.minecraft.client.q;

public final class g
extends i {
    private i a;
    private String i = "Options";
    private q j;

    public g(i i2, q q2) {
        this.a = i2;
        this.j = q2;
    }

    public final void b() {
        for (int i2 = 0; i2 < this.j.t; ++i2) {
            this.e.add(new a(i2, this.c / 2 - 155 + i2 % 2 * 160, this.d / 6 + 24 * (i2 >> 1), this.j.b(i2)));
        }
        this.e.add(new r(100, this.c / 2 - 100, this.d / 6 + 120 + 12, "Controls..."));
        this.e.add(new r(200, this.c / 2 - 100, this.d / 6 + 168, "Done"));
    }

    protected final void a(r r2) {
        if (!r2.c) {
            return;
        }
        if (r2.b < 100) {
            this.j.b(r2.b, 1);
            r2.a = this.j.b(r2.b);
        }
        if (r2.b == 100) {
            this.b.a(new m(this, this.j));
        }
        if (r2.b == 200) {
            this.b.a(this.a);
        }
    }

    public final void a(int n2, int n3, float f2) {
        this.h();
        net.minecraft.client.c.g.a(this.g, this.i, this.c / 2, 20, 0xFFFFFF);
        super.a(n2, n3, f2);
    }
}

