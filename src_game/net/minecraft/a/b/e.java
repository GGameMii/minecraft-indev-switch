/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.b;

import net.minecraft.a.a.g;
import net.minecraft.a.b.i;
import net.minecraft.a.b.k;
import net.minecraft.a.c.d.a;

public final class e
extends i {
    public e(int n2) {
        super(5);
        this.aq = 1;
    }

    public final k a(k k2, g g2, net.minecraft.a.c.e.a a2) {
        if (a2.b.c(net.minecraft.a.b.i.h.ap)) {
            g2.a(a2, "random.bow", 1.0f, 1.0f / (a.nextFloat() * 0.4f + 0.8f));
            g2.a(new a(g2, a2));
        }
        return k2;
    }
}

