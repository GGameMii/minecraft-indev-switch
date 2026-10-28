/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.a.b;

import net.minecraft.a.a.b.x;
import net.minecraft.a.a.d.c;

public final class d
extends x {
    public d(int n2, int n3) {
        super(n2, net.minecraft.a.a.d.c.e);
        this.as = n3;
    }

    public final int a(int n2) {
        if (n2 == 1) {
            return this.as - 16;
        }
        if (n2 == 0) {
            return this.as + 16;
        }
        return this.as;
    }
}

