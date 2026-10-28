/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.a.b;

import net.minecraft.a.a.b.x;
import net.minecraft.a.a.d.c;

public class g
extends x {
    private boolean a = true;

    protected g(int n2, int n3, c c2, boolean bl) {
        super(n2, n3, c2);
    }

    public final boolean b() {
        return false;
    }

    public final boolean d(net.minecraft.a.a.g g2, int n2, int n3, int n4, int n5) {
        int n6 = g2.a(n2, n3, n4);
        if (!this.a && n6 == this.at) {
            return false;
        }
        return super.d(g2, n2, n3, n4, n5);
    }
}

