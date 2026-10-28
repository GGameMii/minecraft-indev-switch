/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.d;

import net.minecraft.a.d.a;
import net.minecraft.a.d.c;

public final class b {
    private float g = 0.0f;
    public float a;
    public float b;
    public float c;
    public float d;
    public float e;
    public float f;

    public b(float f2, float f3, float f4, float f5, float f6, float f7) {
        this.a = f2;
        this.b = f3;
        this.c = f4;
        this.d = f5;
        this.e = f6;
        this.f = f7;
    }

    public final b a(float f2, float f3, float f4) {
        float f5 = this.a;
        float f6 = this.b;
        float f7 = this.c;
        float f8 = this.d;
        float f9 = this.e;
        float f10 = this.f;
        if (f2 < 0.0f) {
            f5 += f2;
        }
        if (f2 > 0.0f) {
            f8 += f2;
        }
        if (f3 < 0.0f) {
            f6 += f3;
        }
        if (f3 > 0.0f) {
            f9 += f3;
        }
        if (f4 < 0.0f) {
            f7 += f4;
        }
        if (f4 > 0.0f) {
            f10 += f4;
        }
        return new b(f5, f6, f7, f8, f9, f10);
    }

    public final b b(float f2, float f3, float f4) {
        if (this.b > this.e) {
            throw new IllegalArgumentException("NOOOOOO!");
        }
        float f5 = this.a - f2;
        float f6 = this.b - f3;
        float f7 = this.c - f4;
        f2 = this.d + f2;
        f3 = this.e + f3;
        f4 = this.f + f4;
        return new b(f5, f6, f7, f2, f3, f4);
    }

    public final float a(b b2, float f2) {
        float f3;
        if (b2.e <= this.b || b2.b >= this.e) {
            return f2;
        }
        if (b2.f <= this.c || b2.c >= this.f) {
            return f2;
        }
        if (f2 > 0.0f && b2.d <= this.a) {
            float f4;
            f3 = this.a - b2.d;
            if (f4 < f2) {
                f2 = f3;
            }
        }
        if (f2 < 0.0f && b2.a >= this.d) {
            float f5;
            f3 = this.d - b2.a;
            if (f5 > f2) {
                f2 = f3;
            }
        }
        return f2;
    }

    public final float b(b b2, float f2) {
        float f3;
        if (b2.d <= this.a || b2.a >= this.d) {
            return f2;
        }
        if (b2.f <= this.c || b2.c >= this.f) {
            return f2;
        }
        if (f2 > 0.0f && b2.e <= this.b) {
            float f4;
            f3 = this.b - b2.e;
            if (f4 < f2) {
                f2 = f3;
            }
        }
        if (f2 < 0.0f && b2.b >= this.e) {
            float f5;
            f3 = this.e - b2.b;
            if (f5 > f2) {
                f2 = f3;
            }
        }
        return f2;
    }

    public final float c(b b2, float f2) {
        float f3;
        if (b2.d <= this.a || b2.a >= this.d) {
            return f2;
        }
        if (b2.e <= this.b || b2.b >= this.e) {
            return f2;
        }
        if (f2 > 0.0f && b2.f <= this.c) {
            float f4;
            f3 = this.c - b2.f;
            if (f4 < f2) {
                f2 = f3;
            }
        }
        if (f2 < 0.0f && b2.c >= this.f) {
            float f5;
            f3 = this.f - b2.c;
            if (f5 > f2) {
                f2 = f3;
            }
        }
        return f2;
    }

    public final boolean a(b b2) {
        if (b2.d < this.a || b2.a > this.d) {
            return false;
        }
        if (b2.e < this.b || b2.b > this.e) {
            return false;
        }
        return !(b2.f < this.c) && !(b2.c > this.f);
    }

    public final void c(float f2, float f3, float f4) {
        this.a += f2;
        this.b += f3;
        this.c += f4;
        this.d += f2;
        this.e += f3;
        this.f += f4;
    }

    public final b a() {
        return new b(this.a, this.b, this.c, this.d, this.e, this.f);
    }

    public final c a(a a2, a a3) {
        a a4 = a2.a(a3, this.a);
        a a5 = a2.a(a3, this.d);
        a a6 = a2.b(a3, this.b);
        a a7 = a2.b(a3, this.e);
        a a8 = a2.c(a3, this.c);
        a3 = a2.c(a3, this.f);
        if (!this.a(a4)) {
            a4 = null;
        }
        if (!this.a(a5)) {
            a5 = null;
        }
        if (!this.b(a6)) {
            a6 = null;
        }
        if (!this.b(a7)) {
            a7 = null;
        }
        if (!this.c(a8)) {
            a8 = null;
        }
        if (!this.c(a3)) {
            a3 = null;
        }
        a a9 = null;
        if (a4 != null) {
            a9 = a4;
        }
        if (a5 != null && (a9 == null || a2.c(a5) < a2.c(a9))) {
            a9 = a5;
        }
        if (a6 != null && (a9 == null || a2.c(a6) < a2.c(a9))) {
            a9 = a6;
        }
        if (a7 != null && (a9 == null || a2.c(a7) < a2.c(a9))) {
            a9 = a7;
        }
        if (a8 != null && (a9 == null || a2.c(a8) < a2.c(a9))) {
            a9 = a8;
        }
        if (a3 != null && (a9 == null || a2.c(a3) < a2.c(a9))) {
            a9 = a3;
        }
        if (a9 == null) {
            return null;
        }
        int n2 = -1;
        if (a9 == a4) {
            n2 = 4;
        }
        if (a9 == a5) {
            n2 = 5;
        }
        if (a9 == a6) {
            n2 = 0;
        }
        if (a9 == a7) {
            n2 = 1;
        }
        if (a9 == a8) {
            n2 = 2;
        }
        if (a9 == a3) {
            n2 = 3;
        }
        return new c(0, 0, 0, n2, a9);
    }

    private boolean a(a a2) {
        if (a2 == null) {
            return false;
        }
        return a2.b >= this.b && a2.b <= this.e && a2.c >= this.c && a2.c <= this.f;
    }

    private boolean b(a a2) {
        if (a2 == null) {
            return false;
        }
        return a2.a >= this.a && a2.a <= this.d && a2.c >= this.c && a2.c <= this.f;
    }

    private boolean c(a a2) {
        if (a2 == null) {
            return false;
        }
        return a2.a >= this.a && a2.a <= this.d && a2.b >= this.b && a2.b <= this.e;
    }
}

