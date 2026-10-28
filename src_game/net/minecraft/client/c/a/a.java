/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.c.a;

import net.minecraft.a.b;
import net.minecraft.a.b.k;
import net.minecraft.client.c.a.j;

public final class a
implements b {
    private k[] a;
    private int b;
    private j c;

    public a(j j2, int n2, int n3) {
        this.b = n2 * n3;
        this.a = new k[this.b];
        this.c = j2;
    }

    public final int a() {
        return this.b;
    }

    public final k a(int n2) {
        return this.a[n2];
    }

    public final String b() {
        return "Crafting";
    }

    public final k a(int n2, int n3) {
        if (this.a[n2] != null) {
            if (this.a[n2].a <= n3) {
                k k2 = this.a[n2];
                this.a[n2] = null;
                this.c.d_();
                return k2;
            }
            k k3 = this.a[n2].a(n3);
            if (this.a[n2].a == 0) {
                this.a[n2] = null;
            }
            this.c.d_();
            return k3;
        }
        return null;
    }

    public final void a(int n2, k k2) {
        this.a[n2] = k2;
        this.c.d_();
    }

    public final int c() {
        return 64;
    }
}

