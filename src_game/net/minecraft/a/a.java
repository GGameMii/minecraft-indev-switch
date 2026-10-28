/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a;

import net.minecraft.a.b;
import net.minecraft.a.b.k;

public final class a
implements b {
    private String a;
    private b b;
    private b c;

    public a(String string, b b2, b b3) {
        this.a = string;
        this.b = b2;
        this.c = b3;
    }

    public final int a() {
        return this.b.a() + this.c.a();
    }

    public final String b() {
        return this.a;
    }

    public final k a(int n2) {
        if (n2 >= this.b.a()) {
            return this.c.a(n2 - this.b.a());
        }
        return this.b.a(n2);
    }

    public final k a(int n2, int n3) {
        if (n2 >= this.b.a()) {
            return this.c.a(n2 - this.b.a(), n3);
        }
        return this.b.a(n2, n3);
    }

    public final void a(int n2, k k2) {
        if (n2 >= this.b.a()) {
            this.c.a(n2 - this.b.a(), k2);
            return;
        }
        this.b.a(n2, k2);
    }

    public final int c() {
        return this.b.c();
    }
}

