/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.g;

import net.minecraft.client.g.b;
import net.minecraft.client.q;

public final class c
extends b {
    private boolean[] d = new boolean[10];
    private q e;

    public c(q q2) {
        this.e = q2;
    }

    public final void a(int n2, boolean bl) {
        int n3 = -1;
        if (n2 == this.e.i.b) {
            n3 = 0;
        }
        if (n2 == this.e.k.b) {
            n3 = 1;
        }
        if (n2 == this.e.j.b) {
            n3 = 2;
        }
        if (n2 == this.e.l.b) {
            n3 = 3;
        }
        if (n2 == this.e.m.b) {
            n3 = 4;
        }
        if (n3 >= 0) {
            this.d[n3] = bl;
        }
    }

    public final void b() {
        for (int i2 = 0; i2 < 10; ++i2) {
            this.d[i2] = false;
        }
    }

    public final void a() {
        this.a = 0.0f;
        this.b = 0.0f;
        if (this.d[0]) {
            this.b += 1.0f;
        }
        if (this.d[1]) {
            this.b -= 1.0f;
        }
        if (this.d[2]) {
            this.a += 1.0f;
        }
        if (this.d[3]) {
            this.a -= 1.0f;
        }
        this.c = this.d[4];
    }
}

