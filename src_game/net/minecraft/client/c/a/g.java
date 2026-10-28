/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.c.a;

import net.minecraft.a.b;
import net.minecraft.a.b.k;
import net.minecraft.client.c.a.j;

public class g {
    public final int a;
    public final int b;
    public final int c;
    public final b d;
    private final j e;

    public g(j j2, b b2, int n2, int n3, int n4) {
        this.e = j2;
        this.d = b2;
        this.a = n2;
        this.b = n3;
        this.c = n4;
    }

    public final boolean a(int n2, int n3) {
        int n4 = (this.e.c - this.e.a) / 2;
        int n5 = (this.e.d - this.e.i) / 2;
        return (n2 -= n4) >= this.b - 1 && n2 < this.b + 16 + 1 && (n3 -= n5) >= this.c - 1 && n3 < this.c + 16 + 1;
    }

    public void a() {
    }

    public boolean a(k k2) {
        return true;
    }

    public final void b(k k2) {
        this.d.a(this.a, k2);
    }

    public int b() {
        return -1;
    }
}

