/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.c;

import net.minecraft.client.c.a;
import net.minecraft.client.c.i;
import net.minecraft.client.c.r;
import net.minecraft.client.q;

public final class m
extends i {
    private i a;
    private String i = "Controls";
    private q j;
    private int k = -1;

    public m(i i2, q q2) {
        this.a = i2;
        this.j = q2;
    }

    public final void b() {
        for (int i2 = 0; i2 < this.j.s.length; ++i2) {
            this.e.add(new a(i2, this.c / 2 - 155 + i2 % 2 * 160, this.d / 6 + 24 * (i2 >> 1), this.j.a(i2)));
        }
        this.e.add(new r(200, this.c / 2 - 100, this.d / 6 + 168, "Done"));
    }

    protected final void a(r r2) {
        for (int i2 = 0; i2 < this.j.s.length; ++i2) {
            ((r)this.e.get((int)i2)).a = this.j.a(i2);
        }
        if (r2.b == 200) {
            this.b.a(this.a);
            return;
        }
        this.k = r2.b;
        r2.a = "> " + this.j.a(r2.b) + " <";
    }

    protected final void a(char c2, int n2) {
        if (this.k >= 0) {
            this.j.a(this.k, n2);
            ((r)this.e.get((int)this.k)).a = this.j.a(this.k);
            this.k = -1;
            return;
        }
        super.a(c2, n2);
    }

    public final void a(int n2, int n3, float f2) {
        this.h();
        m.a(this.g, this.i, this.c / 2, 20, 0xFFFFFF);
        super.a(n2, n3, f2);
    }
}

