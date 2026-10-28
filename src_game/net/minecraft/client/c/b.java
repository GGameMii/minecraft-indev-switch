/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.c;

import net.minecraft.client.c.e;
import net.minecraft.client.c.g;
import net.minecraft.client.c.i;
import net.minecraft.client.c.n;
import net.minecraft.client.c.o;
import net.minecraft.client.c.r;

public final class b
extends i {
    public final void b() {
        this.e.clear();
        this.e.add(new r(0, this.c / 2 - 100, this.d / 4, "Options..."));
        this.e.add(new r(1, this.c / 2 - 100, this.d / 4 + 24, "Generate new level..."));
        this.e.add(new r(2, this.c / 2 - 100, this.d / 4 + 48, "Save level.."));
        this.e.add(new r(3, this.c / 2 - 100, this.d / 4 + 72, "Load level.."));
        this.e.add(new r(4, this.c / 2 - 100, this.d / 4 + 120, "Back to game"));
        if (this.b.h == null) {
            ((r)this.e.get((int)2)).c = false;
            ((r)this.e.get((int)3)).c = false;
        }
    }

    protected final void a(r r2) {
        if (r2.b == 0) {
            this.b.a(new g(this, this.b.w));
        }
        if (r2.b == 1) {
            this.b.a(new n(this));
        }
        if (this.b.h != null) {
            if (r2.b == 2) {
                this.b.a(new o(this));
            }
            if (r2.b == 3) {
                this.b.a(new e(this));
            }
        }
        if (r2.b == 4) {
            this.b.a((i)null);
            this.b.b();
        }
    }

    public final void a(int n2, int n3, float f2) {
        this.h();
        net.minecraft.client.c.b.a(this.g, "Game menu", this.c / 2, 40, 0xFFFFFF);
        super.a(n2, n3, f2);
    }
}

