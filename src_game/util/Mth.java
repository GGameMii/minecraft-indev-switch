/*
 * Decompiled with CFR 0.152.
 */
package util;

public class Mth {
    public static final float PI = (float)Math.PI;
    public static final float RAD_TO_GRAD = (float)Math.PI / 180;
    public static final float DEGRAD = (float)Math.PI / 180;
    public static final float RADDEG = 57.295776f;
    private static float[] sin = new float[65536];

    public static final float sin(float f2) {
        return sin[(int)(f2 * 10430.378f) & 0xFFFF];
    }

    public static final float cos(float f2) {
        return sin[(int)(f2 * 10430.378f + 16384.0f) & 0xFFFF];
    }

    public static final float sqrt(float f2) {
        return (float)Math.sqrt(f2);
    }

    public static final float sqrt(double d2) {
        return (float)Math.sqrt(d2);
    }

    public static int floor(float f2) {
        int n2 = (int)f2;
        return f2 < (float)n2 ? n2 - 1 : n2;
    }

    public static int fastFloor(double d2) {
        return (int)(d2 + 1024.0) - 1024;
    }

    public static int floor(double d2) {
        int n2 = (int)d2;
        return d2 < (double)n2 ? n2 - 1 : n2;
    }

    public static int absFloor(double d2) {
        return (int)(d2 >= 0.0 ? d2 : -d2 + 1.0);
    }

    public static float abs(float f2) {
        return f2 >= 0.0f ? f2 : -f2;
    }

    public static int ceil(float f2) {
        int n2 = (int)f2;
        return f2 > (float)n2 ? n2 + 1 : n2;
    }

    public static void main(String[] stringArray) {
        for (int i2 = -64; i2 <= 64; ++i2) {
            System.out.println(i2 + " -> " + Mth.intFloorDiv(i2, 32));
        }
    }

    public static double asbMax(double d2, double d3) {
        if (d2 < 0.0) {
            d2 = -d2;
        }
        if (d3 < 0.0) {
            d3 = -d3;
        }
        return d2 > d3 ? d2 : d3;
    }

    public static int intFloorDiv(int n2, int n3) {
        return n2 < 0 ? -((-n2 - 1) / n3) - 1 : n2 / n3;
    }

    static {
        for (int i2 = 0; i2 < 65536; ++i2) {
            Mth.sin[i2] = (float)Math.sin((double)i2 * Math.PI * 2.0 / 65536.0);
        }
    }
}

