/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.BufferUtils
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client.a.b;

import java.nio.FloatBuffer;
import net.minecraft.client.a.b.b;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public final class a
extends b {
    private static a e = new a();
    private FloatBuffer f = BufferUtils.createFloatBuffer((int)16);
    private FloatBuffer g = BufferUtils.createFloatBuffer((int)16);
    private FloatBuffer h = BufferUtils.createFloatBuffer((int)16);

    public static b a() {
        a a2 = e;
        a2.f.clear();
        a2.g.clear();
        a2.h.clear();
        GL11.glGetFloat((int)2983, (FloatBuffer)a2.f);
        GL11.glGetFloat((int)2982, (FloatBuffer)a2.g);
        a2.f.flip().limit(16);
        a2.f.get(a2.b);
        a2.g.flip().limit(16);
        a2.g.get(a2.c);
        a2.d[0] = a2.c[0] * a2.b[0] + a2.c[1] * a2.b[4] + a2.c[2] * a2.b[8] + a2.c[3] * a2.b[12];
        a2.d[1] = a2.c[0] * a2.b[1] + a2.c[1] * a2.b[5] + a2.c[2] * a2.b[9] + a2.c[3] * a2.b[13];
        a2.d[2] = a2.c[0] * a2.b[2] + a2.c[1] * a2.b[6] + a2.c[2] * a2.b[10] + a2.c[3] * a2.b[14];
        a2.d[3] = a2.c[0] * a2.b[3] + a2.c[1] * a2.b[7] + a2.c[2] * a2.b[11] + a2.c[3] * a2.b[15];
        a2.d[4] = a2.c[4] * a2.b[0] + a2.c[5] * a2.b[4] + a2.c[6] * a2.b[8] + a2.c[7] * a2.b[12];
        a2.d[5] = a2.c[4] * a2.b[1] + a2.c[5] * a2.b[5] + a2.c[6] * a2.b[9] + a2.c[7] * a2.b[13];
        a2.d[6] = a2.c[4] * a2.b[2] + a2.c[5] * a2.b[6] + a2.c[6] * a2.b[10] + a2.c[7] * a2.b[14];
        a2.d[7] = a2.c[4] * a2.b[3] + a2.c[5] * a2.b[7] + a2.c[6] * a2.b[11] + a2.c[7] * a2.b[15];
        a2.d[8] = a2.c[8] * a2.b[0] + a2.c[9] * a2.b[4] + a2.c[10] * a2.b[8] + a2.c[11] * a2.b[12];
        a2.d[9] = a2.c[8] * a2.b[1] + a2.c[9] * a2.b[5] + a2.c[10] * a2.b[9] + a2.c[11] * a2.b[13];
        a2.d[10] = a2.c[8] * a2.b[2] + a2.c[9] * a2.b[6] + a2.c[10] * a2.b[10] + a2.c[11] * a2.b[14];
        a2.d[11] = a2.c[8] * a2.b[3] + a2.c[9] * a2.b[7] + a2.c[10] * a2.b[11] + a2.c[11] * a2.b[15];
        a2.d[12] = a2.c[12] * a2.b[0] + a2.c[13] * a2.b[4] + a2.c[14] * a2.b[8] + a2.c[15] * a2.b[12];
        a2.d[13] = a2.c[12] * a2.b[1] + a2.c[13] * a2.b[5] + a2.c[14] * a2.b[9] + a2.c[15] * a2.b[13];
        a2.d[14] = a2.c[12] * a2.b[2] + a2.c[13] * a2.b[6] + a2.c[14] * a2.b[10] + a2.c[15] * a2.b[14];
        a2.d[15] = a2.c[12] * a2.b[3] + a2.c[13] * a2.b[7] + a2.c[14] * a2.b[11] + a2.c[15] * a2.b[15];
        a2.a[0][0] = a2.d[3] - a2.d[0];
        a2.a[0][1] = a2.d[7] - a2.d[4];
        a2.a[0][2] = a2.d[11] - a2.d[8];
        a2.a[0][3] = a2.d[15] - a2.d[12];
        net.minecraft.client.a.b.a.a(a2.a, 0);
        a2.a[1][0] = a2.d[3] + a2.d[0];
        a2.a[1][1] = a2.d[7] + a2.d[4];
        a2.a[1][2] = a2.d[11] + a2.d[8];
        a2.a[1][3] = a2.d[15] + a2.d[12];
        net.minecraft.client.a.b.a.a(a2.a, 1);
        a2.a[2][0] = a2.d[3] + a2.d[1];
        a2.a[2][1] = a2.d[7] + a2.d[5];
        a2.a[2][2] = a2.d[11] + a2.d[9];
        a2.a[2][3] = a2.d[15] + a2.d[13];
        net.minecraft.client.a.b.a.a(a2.a, 2);
        a2.a[3][0] = a2.d[3] - a2.d[1];
        a2.a[3][1] = a2.d[7] - a2.d[5];
        a2.a[3][2] = a2.d[11] - a2.d[9];
        a2.a[3][3] = a2.d[15] - a2.d[13];
        net.minecraft.client.a.b.a.a(a2.a, 3);
        a2.a[4][0] = a2.d[3] - a2.d[2];
        a2.a[4][1] = a2.d[7] - a2.d[6];
        a2.a[4][2] = a2.d[11] - a2.d[10];
        a2.a[4][3] = a2.d[15] - a2.d[14];
        net.minecraft.client.a.b.a.a(a2.a, 4);
        a2.a[5][0] = a2.d[3] + a2.d[2];
        a2.a[5][1] = a2.d[7] + a2.d[6];
        a2.a[5][2] = a2.d[11] + a2.d[10];
        a2.a[5][3] = a2.d[15] + a2.d[14];
        net.minecraft.client.a.b.a.a(a2.a, 5);
        return e;
    }

    private static void a(float[][] fArray, int n2) {
        float f2 = a.a.c(fArray[n2][0] * fArray[n2][0] + fArray[n2][1] * fArray[n2][1] + fArray[n2][2] * fArray[n2][2]);
        float[] fArray2 = fArray[n2];
        fArray2[0] = fArray2[0] / f2;
        float[] fArray3 = fArray[n2];
        fArray3[1] = fArray3[1] / f2;
        float[] fArray4 = fArray[n2];
        fArray4[2] = fArray4[2] / f2;
        float[] fArray5 = fArray[n2];
        fArray5[3] = fArray5[3] / f2;
    }
}

