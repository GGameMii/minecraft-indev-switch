/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.a.b.a;

import com.a.a.f;
import net.minecraft.a.a.b.a.a;
import net.minecraft.a.b;
import net.minecraft.a.b.k;

public final class c
extends a
implements b {
    private k[] e = new k[36];

    public final int a() {
        return 27;
    }

    public final k a(int n2) {
        return this.e[n2];
    }

    public final k a(int n2, int n3) {
        if (this.e[n2] != null) {
            if (this.e[n2].a <= n3) {
                k k2 = this.e[n2];
                this.e[n2] = null;
                return k2;
            }
            k k3 = this.e[n2].a(n3);
            if (this.e[n2].a == 0) {
                this.e[n2] = null;
            }
            return k3;
        }
        return null;
    }

    public final void a(int n2, k k2) {
        this.e[n2] = k2;
        if (k2 != null) {
            c c2 = this;
            if (k2.a > 64) {
                c2 = this;
                k2.a = 64;
            }
        }
    }

    public final String b() {
        return "Chest";
    }

    public final void a(com.a.a.c j2) {
        j2 = ((com.a.a.c)j2).j("Items");
        c c2 = this;
        this.e = new k[27];
        for (int i2 = 0; i2 < ((f)j2).b(); ++i2) {
            com.a.a.c c3 = (com.a.a.c)((f)j2).a(i2);
            int n2 = c3.b("Slot") & 0xFF;
            if (n2 < 0 || n2 >= this.e.length) continue;
            this.e[n2] = new k(c3);
        }
    }

    public final void b(com.a.a.c c2) {
        c2.a("id", "Chest");
        f f2 = new f();
        for (int i2 = 0; i2 < this.e.length; ++i2) {
            if (this.e[i2] == null) continue;
            com.a.a.c c3 = new com.a.a.c();
            c3.a("Slot", (byte)i2);
            this.e[i2].a(c3);
            f2.a(c3);
        }
        c2.a("Items", f2);
    }

    public final int c() {
        return 64;
    }
}

