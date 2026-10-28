/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.a.b;

import net.minecraft.a.a.b.a.a;
import net.minecraft.a.a.b.x;
import net.minecraft.a.a.d.c;
import net.minecraft.a.a.g;

public abstract class q
extends x {
    protected q(int n2, c c2) {
        super(n2, c2);
    }

    public void d(g g2, int n2, int n3, int n4) {
        super.d(g2, n2, n3, n4);
        g2.a(n2, n3, n4, this.a_());
    }

    public void b(g g2, int n2, int n3, int n4) {
        super.b(g2, n2, n3, n4);
        g2.i(n2, n3, n4);
    }

    protected abstract a a_();
}

