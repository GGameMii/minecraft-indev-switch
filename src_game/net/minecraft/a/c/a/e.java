/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.c.a;

import net.minecraft.a.a.g;
import net.minecraft.a.c.b;
import net.minecraft.a.c.c;

public class e
extends c {
    protected int a = 2;

    public e(g g2) {
        super(g2);
        this.W = 20;
    }

    public void f() {
        float f2;
        float f3 = this.a(1.0f);
        if (f2 > 0.5f) {
            this.ai += 2;
        }
        super.f();
    }

    public final void b_() {
        super.b_();
        if (this.d.E == 0) {
            this.k();
        }
    }

    protected b b() {
        float f2;
        float f3 = this.d.y.b(this);
        if (f2 < 256.0f) {
            return this.d.y;
        }
        return null;
    }

    public final boolean a(b b2, int n2) {
        if (super.a(b2, n2)) {
            if (b2 != this) {
                this.b = b2;
            }
            return true;
        }
        return false;
    }

    protected void a(b b2, float f2) {
        if ((double)f2 < 2.5 && b2.r.e > this.r.b && b2.r.b < this.r.e) {
            this.ac = 20;
            b2.a(this, this.a);
        }
    }

    protected float a(int n2, int n3, int n4) {
        return 0.5f - this.d.c(n2, n3, n4);
    }

    protected void a(com.a.a.c c2) {
        super.a(c2);
    }

    protected void b(com.a.a.c c2) {
        super.b(c2);
    }

    protected String a() {
        return "Monster";
    }

    public final boolean a(float f2, float f3, float f4) {
        byte by = this.d.d((int)f2, (int)f3, (int)f4);
        return by <= this.G.nextInt(8) && super.a(f2, f3, f4);
    }
}

