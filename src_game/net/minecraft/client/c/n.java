/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.c;

import net.minecraft.client.c.i;
import net.minecraft.client.c.r;

public final class n
extends i {
    private i a;
    private String[] i = new String[]{"Inland", "Island", "Floating", "Flat"};
    private String[] j = new String[]{"Square", "Long", "Deep"};
    private String[] k = new String[]{"Small", "Normal", "Huge"};
    private String[] l = new String[]{"Normal", "Hell", "Paradise", "Woods"};
    private int m = 1;
    private int n = 0;
    private int o = 1;
    private int p = 0;

    public n(i i2) {
        this.a = i2;
    }

    public final void b() {
        this.e.clear();
        this.e.add(new r(0, this.c / 2 - 100, this.d / 4, "Type: "));
        this.e.add(new r(1, this.c / 2 - 100, this.d / 4 + 24, "Shape:"));
        this.e.add(new r(2, this.c / 2 - 100, this.d / 4 + 48, "Size: "));
        this.e.add(new r(3, this.c / 2 - 100, this.d / 4 + 72, "Theme: "));
        this.e.add(new r(4, this.c / 2 - 100, this.d / 4 + 96 + 12, "Create"));
        this.e.add(new r(5, this.c / 2 - 100, this.d / 4 + 120 + 12, "Cancel"));
        this.e();
    }

    private void e() {
        ((r)this.e.get((int)0)).a = "Type: " + this.i[this.m];
        ((r)this.e.get((int)1)).a = "Shape: " + this.j[this.n];
        ((r)this.e.get((int)2)).a = "Size: " + this.k[this.o];
        ((r)this.e.get((int)3)).a = "Theme: " + this.l[this.p];
    }

    protected final void a(r r2) {
        if (r2.b == 5) {
            this.b.a(this.a);
        } else if (r2.b == 4) {
            this.b.a(this.o, this.n, this.m, this.p);
            this.b.a((i)null);
        } else if (r2.b == 0) {
            this.m = (this.m + 1) % this.i.length;
        } else if (r2.b == 1) {
            this.n = (this.n + 1) % this.j.length;
        } else if (r2.b == 2) {
            this.o = (this.o + 1) % this.k.length;
        } else if (r2.b == 3) {
            this.p = (this.p + 1) % this.l.length;
        }
        this.e();
    }

    public final void a(int n2, int n3, float f2) {
        this.h();
        net.minecraft.client.c.n.a(this.g, "Generate new level", this.c / 2, 40, 0xFFFFFF);
        super.a(n2, n3, f2);
    }
}

