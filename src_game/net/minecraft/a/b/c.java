/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.b;

import net.minecraft.a.a.b.x;
import net.minecraft.a.b.i;
import net.minecraft.a.b.k;

public final class c
extends i {
    private int as;

    public c(int n2, int n3) {
        super(n2);
        this.aq = 1;
        this.ar = 32 << n3;
        this.as = 4 + (n3 << 1);
    }

    public final float b(x x2) {
        return 1.5f;
    }

    public final void a(k k2) {
        k2.b(1);
    }

    public final void b(k k2) {
        k2.b(2);
    }

    public final int a() {
        return this.as;
    }
}

