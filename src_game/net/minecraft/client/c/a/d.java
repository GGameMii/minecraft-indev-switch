/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.c.a;

import net.minecraft.a.b;
import net.minecraft.a.b.k;
import net.minecraft.client.c.a.g;
import net.minecraft.client.c.a.j;

final class d
extends g {
    private final b e;

    public d(j j2, b b2, b b3, int n2, int n3, int n4) {
        super(j2, b3, 0, n3, n4);
        this.e = b2;
    }

    public final boolean a(k k2) {
        return false;
    }

    public final void a() {
        for (int i2 = 0; i2 < this.e.a(); ++i2) {
            if (this.e.a(i2) == null) continue;
            this.e.a(i2, 1);
        }
    }
}

