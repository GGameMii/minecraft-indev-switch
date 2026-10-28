/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.a.a;

import net.minecraft.a.c.e.a;
import net.minecraft.client.a.a.j;
import net.minecraft.client.b.h;

public final class g
extends j {
    private h e;
    private h f;
    private h g;
    private static final String[] h = new String[]{"cloth", "chain", "iron", "diamond", "gold"};

    public g() {
        super(new h(0.0f), 0.5f);
        this.e = (h)this.d;
        this.f = new h(1.0f);
        this.g = new h(0.5f);
    }

    private void a(a a2, float f2, float f3, float f4, float f5, float f6) {
        super.a(a2, f2, f3 - a2.v, f4, f5, f6);
    }

    public final void a() {
        this.e.d.a(1.0f);
    }
}

