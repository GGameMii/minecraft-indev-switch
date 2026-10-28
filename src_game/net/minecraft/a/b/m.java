/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.b;

import net.minecraft.a.a.b.x;
import net.minecraft.a.a.g;
import net.minecraft.a.b.i;
import net.minecraft.a.b.k;

public final class m
extends i {
    public m(int n2) {
        super(3);
        this.aq = 1;
        this.ar = 64;
    }

    public final boolean a(k k2, g g2, int n2, int n3, int n4, int n5) {
        if (n5 == 0) {
            --n3;
        }
        if (n5 == 1) {
            ++n3;
        }
        if (n5 == 2) {
            --n4;
        }
        if (n5 == 3) {
            ++n4;
        }
        if (n5 == 4) {
            --n2;
        }
        if (n5 == 5) {
            ++n2;
        }
        if (n2 <= 0 || n3 <= 0 || n4 <= 0 || n2 >= g2.a - 1 || n3 >= g2.c - 1 || n4 >= g2.b - 1) {
            return false;
        }
        n5 = g2.a(n2, n3, n4);
        if (n5 == 0) {
            g2.a((float)n2 + 0.5f, (float)n3 + 0.5f, (float)n4 + 0.5f, "fire.ignite", 1.0f, a.nextFloat() * 0.4f + 0.8f);
            g2.b(n2, n3, n4, net.minecraft.a.a.b.x.ag.at);
        }
        k2.b(1);
        return true;
    }
}

