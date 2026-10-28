/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client.c;

import net.minecraft.client.a.d;
import net.minecraft.client.c.j;
import org.lwjgl.opengl.GL11;

public class k {
    protected float h = 0.0f;

    protected static void a(int n2, int n3, int n4, int n5, int n6) {
        float f2 = (float)(n6 >>> 24) / 255.0f;
        float f3 = (float)(n6 >> 16 & 0xFF) / 255.0f;
        float f4 = (float)(n6 >> 8 & 0xFF) / 255.0f;
        float f5 = (float)(n6 & 0xFF) / 255.0f;
        d d2 = d.a;
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glColor4f((float)f3, (float)f4, (float)f5, (float)f2);
        d2.b();
        d2.b(n2, n5, 0.0f);
        d2.b(n4, n5, 0.0f);
        d2.b(n4, n3, 0.0f);
        d2.b(n2, n3, 0.0f);
        d2.a();
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
    }

    protected static void a(int n2, int n3, int n4, int n5, int n6, int n7) {
        float f2 = (float)(n6 >>> 24) / 255.0f;
        float f3 = (float)(n6 >> 16 & 0xFF) / 255.0f;
        float f4 = (float)(n6 >> 8 & 0xFF) / 255.0f;
        float f5 = (float)(n6 & 0xFF) / 255.0f;
        float f6 = (float)(n7 >>> 24) / 255.0f;
        float f7 = (float)(n7 >> 16 & 0xFF) / 255.0f;
        float f8 = (float)(n7 >> 8 & 0xFF) / 255.0f;
        float f9 = (float)(n7 & 0xFF) / 255.0f;
        GL11.glDisable((int)3553);
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3008);
        GL11.glBlendFunc((int)770, (int)771);
        d d2 = d.a;
        d2.b();
        d2.a(f3, f4, f5, f2);
        d2.b(n4, n3, 0.0f);
        d2.b(n2, n3, 0.0f);
        d2.a(f7, f8, f9, f6);
        d2.b(n2, n5, 0.0f);
        d2.b(n4, n5, 0.0f);
        d2.a();
        GL11.glDisable((int)3042);
        GL11.glEnable((int)3008);
        GL11.glEnable((int)3553);
    }

    public static void a(j j2, String string, int n2, int n3, int n4) {
        j2.a(string, n2 - j2.a(string) / 2, n3, n4);
    }

    public static void b(j j2, String string, int n2, int n3, int n4) {
        j2.a(string, n2, n3, n4);
    }

    public final void b(int n2, int n3, int n4, int n5, int n6, int n7) {
        d d2 = d.a;
        d2.b();
        d2.a(n2, n3 + n7, this.h, (float)n4 * 0.00390625f, (float)(n5 + n7) * 0.00390625f);
        d2.a(n2 + n6, n3 + n7, this.h, (float)(n4 + n6) * 0.00390625f, (float)(n5 + n7) * 0.00390625f);
        d2.a(n2 + n6, n3, this.h, (float)(n4 + n6) * 0.00390625f, (float)n5 * 0.00390625f);
        d2.a(n2, n3, this.h, (float)n4 * 0.00390625f, (float)n5 * 0.00390625f);
        d2.a();
    }
}

