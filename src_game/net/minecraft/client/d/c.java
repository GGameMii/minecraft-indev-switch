/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.d;

import net.minecraft.a.a.b;
import net.minecraft.a.a.b.x;
import net.minecraft.a.a.g;
import net.minecraft.a.b.k;
import net.minecraft.client.d.a;
import net.minecraft.client.l;

public final class c
extends a {
    private b c;

    public final void a(net.minecraft.a.c.e.a a2) {
        for (int i2 = 0; i2 < 9; ++i2) {
            if (a2.b.a[i2] == null) {
                this.a.f.b.a[i2] = new k(((x)l.a.get((int)i2)).at);
                continue;
            }
            this.a.f.b.a[i2].a = 1;
        }
    }

    public final boolean d() {
        return false;
    }

    public final void a(g g2) {
        super.a(g2);
        g2.z = false;
        this.c = new b(g2);
    }

    public final void c() {
        this.c.a();
    }
}

