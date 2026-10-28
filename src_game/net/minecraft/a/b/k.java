/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.b;

import com.a.a.c;
import net.minecraft.a.a.b.x;
import net.minecraft.a.b.i;

public final class k {
    public int a = 0;
    public int b;
    public int c;
    public int d;

    public k(x x2) {
        this(x2, 1);
    }

    public k(x x2, int n2) {
        this(x2.at, n2);
    }

    public k(i i2) {
        this(i2, 1);
    }

    public k(i i2, int n2) {
        this(i2.ap, n2);
    }

    public k(int n2) {
        this(n2, 1);
    }

    public k(int n2, int n3) {
        this.c = n2;
        this.a = n3;
    }

    public k(int n2, int n3, int n4) {
        this.c = n2;
        this.a = n3;
        this.d = n4;
    }

    public k(c object) {
        c c2 = object;
        object = this;
        this.c = c2.c("id");
        ((k)object).a = c2.b("Count");
        ((k)object).d = c2.c("Damage");
    }

    public final k a(int n2) {
        this.a -= n2;
        return new k(this.c, n2, this.d);
    }

    public final i a() {
        return i.b[this.c];
    }

    public final c a(c c2) {
        c2.a("id", (short)this.c);
        c2.a("Count", (byte)this.a);
        c2.a("Damage", (short)this.d);
        return c2;
    }

    public final int b() {
        return i.b[this.c].d();
    }

    public final void b(int n2) {
        this.d += n2;
        if (this.d > this.b()) {
            --this.a;
            if (this.a < 0) {
                this.a = 0;
            }
            this.d = 0;
        }
    }
}

