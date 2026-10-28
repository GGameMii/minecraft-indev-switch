/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.d;

public final class a {
    public float a;
    public float b;
    public float c;

    public a(float f2, float f3, float f4) {
        this.a = f2;
        this.b = f3;
        this.c = f4;
    }

    public final a a(a a2) {
        return new a(this.a - a2.a, this.b - a2.b, this.c - a2.c);
    }

    public final a a() {
        float f2 = a.a.c(this.a * this.a + this.b * this.b + this.c * this.c);
        return new a(this.a / f2, this.b / f2, this.c / f2);
    }

    public final a a(float f2, float f3, float f4) {
        return new a(this.a + f2, this.b + f3, this.c + f4);
    }

    public final float b(a a2) {
        float f2 = a2.a - this.a;
        float f3 = a2.b - this.b;
        float f4 = a2.c - this.c;
        return a.a.c(f2 * f2 + f3 * f3 + f4 * f4);
    }

    public final float c(a a2) {
        float f2 = a2.a - this.a;
        float f3 = a2.b - this.b;
        float f4 = a2.c - this.c;
        return f2 * f2 + f3 * f3 + f4 * f4;
    }

    public final a a(a a2, float f2) {
        float f3 = a2.a - this.a;
        float f4 = a2.b - this.b;
        float f5 = a2.c - this.c;
        if (f3 * f3 < 1.0E-7f) {
            return null;
        }
        if ((f2 = (f2 - this.a) / f3) < 0.0f || f2 > 1.0f) {
            return null;
        }
        return new a(this.a + f3 * f2, this.b + f4 * f2, this.c + f5 * f2);
    }

    public final a b(a a2, float f2) {
        float f3 = a2.a - this.a;
        float f4 = a2.b - this.b;
        float f5 = a2.c - this.c;
        if (f4 * f4 < 1.0E-7f) {
            return null;
        }
        if ((f2 = (f2 - this.b) / f4) < 0.0f || f2 > 1.0f) {
            return null;
        }
        return new a(this.a + f3 * f2, this.b + f4 * f2, this.c + f5 * f2);
    }

    public final a c(a a2, float f2) {
        float f3;
        float f4 = a2.a - this.a;
        float f5 = a2.b - this.b;
        float f6 = a2.c - this.c;
        if (f3 * f6 < 1.0E-7f) {
            return null;
        }
        if ((f2 = (f2 - this.c) / f6) < 0.0f || f2 > 1.0f) {
            return null;
        }
        return new a(this.a + f4 * f2, this.b + f5 * f2, this.c + f6 * f2);
    }

    public final String toString() {
        return "(" + this.a + ", " + this.b + ", " + this.c + ")";
    }
}

