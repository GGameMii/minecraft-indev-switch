/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.c.a;

import net.minecraft.a.b;
import net.minecraft.a.b.k;

public final class h
implements b {
    private k[] a = new k[1];

    public final int a() {
        return 1;
    }

    public final k a(int n2) {
        return this.a[n2];
    }

    public final String b() {
        return "Result";
    }

    public final k a(int n2, int n3) {
        if (this.a[n2] != null) {
            k k2 = this.a[n2];
            this.a[n2] = null;
            return k2;
        }
        return null;
    }

    public final void a(int n2, k k2) {
        this.a[n2] = k2;
    }

    public final int c() {
        return 64;
    }
}

