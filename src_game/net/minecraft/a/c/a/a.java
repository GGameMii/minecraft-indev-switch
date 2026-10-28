/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.c.a;

import net.minecraft.a.a.g;
import net.minecraft.a.c.a.e;

public class a
extends e {
    public a(g g2) {
        super(g2);
        this.V = "/mob/zombie.png";
        this.am = 0.5f;
        this.a = 50;
        this.W *= 10;
        this.v *= 6.0f;
        this.a(this.w * 6.0f, this.x * 6.0f);
    }

    protected final float a(int n2, int n3, int n4) {
        return this.d.c(n2, n3, n4) - 0.5f;
    }

    protected final String a() {
        return "Giant";
    }
}

