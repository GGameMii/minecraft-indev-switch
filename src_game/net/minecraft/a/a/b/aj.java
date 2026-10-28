/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.a.b;

import java.util.Random;
import net.minecraft.a.a.b.x;
import net.minecraft.a.a.d.c;
import net.minecraft.a.a.g;

public final class aj
extends x {
    private boolean a;

    public aj(int n2, boolean bl) {
        super(n2, 6, net.minecraft.a.a.d.c.d);
        this.a = bl;
        if (!bl) {
            this.a(0.0f, 0.0f, 0.0f, 1.0f, 0.5f, 1.0f);
        }
        this.c(255);
    }

    public final int a(int n2) {
        if (n2 <= 1) {
            return 6;
        }
        return 5;
    }

    public final boolean b() {
        return this.a;
    }

    public final void b(g g2, int n2, int n3, int n4, int n5) {
        if (this != net.minecraft.a.a.b.x.Z) {
            return;
        }
    }

    public final void d(g g2, int n2, int n3, int n4) {
        int n5;
        if (this != net.minecraft.a.a.b.x.Z) {
            super.d(g2, n2, n3, n4);
        }
        if ((n5 = g2.a(n2, n3 - 1, n4)) == net.minecraft.a.a.b.aj.Z.at) {
            g2.b(n2, n3, n4, 0);
            g2.b(n2, n3 - 1, n4, net.minecraft.a.a.b.x.Y.at);
        }
    }

    public final int a(int n2, Random random) {
        return net.minecraft.a.a.b.x.Z.at;
    }

    public final boolean c() {
        return this.a;
    }

    public final boolean d(g g2, int n2, int n3, int n4, int n5) {
        if (n5 == 1) {
            return true;
        }
        if (!super.d(g2, n2, n3, n4, n5)) {
            return false;
        }
        if (n5 == 0) {
            return true;
        }
        return g2.a(n2, n3, n4) != this.at;
    }
}

