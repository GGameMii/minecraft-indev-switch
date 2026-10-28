/*
 * Decompiled with CFR 0.152.
 */
package a;

public final class a {
    private static float[] a = new float[65536];

    public static final float a(float f2) {
        return a[(int)(f2 * 10430.378f) & 0xFFFF];
    }

    public static final float b(float f2) {
        return a[(int)(f2 * 10430.378f + 16384.0f) & 0xFFFF];
    }

    public static final float c(float f2) {
        return (float)Math.sqrt(f2);
    }

    public static int d(float f2) {
        int n2 = (int)f2;
        if (f2 < (float)n2) {
            return n2 - 1;
        }
        return n2;
    }

    public static int a(double d2) {
        int n2 = (int)d2;
        if (d2 < (double)n2) {
            return n2 - 1;
        }
        return n2;
    }

    public static float e(float f2) {
        if (f2 >= 0.0f) {
            return f2;
        }
        return -f2;
    }

    static {
        for (int i2 = 0; i2 < 65536; ++i2) {
            a.a.a[i2] = (float)Math.sin((double)i2 * Math.PI * 2.0 / 65536.0);
        }
    }
}

