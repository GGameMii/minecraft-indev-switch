/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.c.a;

import net.minecraft.a.b;
import net.minecraft.a.b.k;
import net.minecraft.client.c.a.f;
import net.minecraft.client.c.a.g;
import net.minecraft.client.c.a.j;

final class e
extends g {
    private /* synthetic */ int e;

    e(f f2, j j2, b b2, int n2, int n3, int n4, int n5) {
        this.e = n5;
        super(j2, b2, n2, 8, n4);
    }

    public final boolean a(k k2) {
        if (k2.a() instanceof net.minecraft.a.b.j) {
            return ((net.minecraft.a.b.j)k2.a()).as == this.e;
        }
        return false;
    }

    public final int b() {
        return 15 + (this.e << 4);
    }
}

