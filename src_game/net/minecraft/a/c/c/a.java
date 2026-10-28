/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.c.c;

import com.a.a.c;
import net.minecraft.a.a.g;
import net.minecraft.a.c.b;

public class a
extends b {
    public int a = 0;

    public a(g g2, float f2, float f3, float f4) {
        super(g2);
        this.c = true;
        this.a(0.98f, 0.98f);
        this.v = this.x / 2.0f;
        this.b(f2, f3, f4);
        float f5 = (float)(Math.random() * 3.1415927410125732 * 2.0);
        this.k = -a.a.a(f5 * (float)Math.PI / 180.0f) * 0.02f;
        this.l = 0.2f;
        this.m = -a.a.b(f5 * (float)Math.PI / 180.0f) * 0.02f;
        this.A = false;
        this.a = 80;
        this.e = f2;
        this.f = f3;
        this.g = f4;
    }

    public final boolean d() {
        return !this.u;
    }

    public final void b_() {
        this.e = this.h;
        this.f = this.i;
        this.g = this.j;
        this.l -= 0.04f;
        this.d(this.k, this.l, this.m);
        this.k *= 0.98f;
        this.l *= 0.98f;
        this.m *= 0.98f;
        if (this.s) {
            this.k *= 0.7f;
            this.m *= 0.7f;
            this.l *= -0.5f;
        }
        if (this.a-- <= 0) {
            this.k();
            a a2 = this;
            a2.d.a(null, a2.h, a2.i, a2.j, 4.0f);
            return;
        }
        this.d.a("smoke", this.h, this.i + 0.5f, this.j, 0.0f, 0.0f, 0.0f);
    }

    protected final void a(c c2) {
        c2.a("Fuse", (byte)this.a);
    }

    protected final void b(c c2) {
        this.a = c2.b("Fuse");
    }

    protected final String a() {
        return "PrimedTnt";
    }

    public final float c_() {
        return 0.0f;
    }
}

