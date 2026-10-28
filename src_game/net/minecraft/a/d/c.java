/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.d;

import net.minecraft.a.c.b;
import net.minecraft.a.d.a;

public final class c {
    public int a;
    public int b;
    public int c;
    public int d;
    public int e;
    public a f;
    public b g;

    public c(int n2, int n3, int n4, int n5, a a2) {
        this.a = 0;
        this.b = n2;
        this.c = n3;
        this.d = n4;
        this.e = n5;
        this.f = new a(a2.a, a2.b, a2.c);
    }

    public c(b b2) {
        this.a = 1;
        this.g = b2;
        this.f = new a(b2.h, b2.i, b2.j);
    }
}

