/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client;

import net.minecraft.a.a.g;
import net.minecraft.a.c.b;
import net.minecraft.client.d;
import net.minecraft.client.g.a;

public final class f
extends net.minecraft.a.a.a {
    private d a;

    public f(d d2, a.b b2) {
        super(b2);
        this.a = d2;
    }

    protected final b a(g g2, String string) {
        if (string.equals("LocalPlayer")) {
            return new a(this.a, g2, this.a.h);
        }
        return super.a(g2, string);
    }
}

