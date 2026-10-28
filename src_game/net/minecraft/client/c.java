/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.BufferUtils
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client;

import java.nio.FloatBuffer;
import net.minecraft.a.d.a;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public final class c {
    private static FloatBuffer a = BufferUtils.createFloatBuffer((int)16);

    public static void a() {
        GL11.glDisable((int)2896);
        GL11.glDisable((int)16384);
        GL11.glDisable((int)16385);
        GL11.glDisable((int)2903);
    }

    public static void b() {
        GL11.glEnable((int)2896);
        GL11.glEnable((int)16384);
        GL11.glEnable((int)16385);
        GL11.glEnable((int)2903);
        GL11.glColorMaterial((int)1032, (int)5634);
        a a2 = new a(0.3f, 1.0f, -0.7f);
        GL11.glLight((int)16384, (int)4611, (FloatBuffer)c.a(a2.a, a2.b, a2.c, 0.0f));
        GL11.glLight((int)16384, (int)4609, (FloatBuffer)c.a(0.5f, 0.5f, 0.5f, 1.0f));
        GL11.glLight((int)16384, (int)4608, (FloatBuffer)c.a(0.0f, 0.0f, 0.0f, 1.0f));
        GL11.glLight((int)16384, (int)4610, (FloatBuffer)c.a(0.0f, 0.0f, 0.0f, 1.0f));
        a2 = new a(-0.7f, 1.0f, 0.2f);
        GL11.glLight((int)16385, (int)4611, (FloatBuffer)c.a(a2.a, a2.b, a2.c, 0.0f));
        GL11.glLight((int)16385, (int)4609, (FloatBuffer)c.a(0.5f, 0.5f, 0.5f, 1.0f));
        GL11.glLight((int)16385, (int)4608, (FloatBuffer)c.a(0.0f, 0.0f, 0.0f, 1.0f));
        GL11.glLight((int)16385, (int)4610, (FloatBuffer)c.a(0.0f, 0.0f, 0.0f, 1.0f));
        GL11.glShadeModel((int)7425);
        GL11.glLightModel((int)2899, (FloatBuffer)c.a(0.5f, 0.5f, 0.5f, 1.0f));
    }

    private static FloatBuffer a(float f2, float f3, float f4, float f5) {
        a.clear();
        a.put(f2).put(f3).put(f4).put(f5);
        a.flip();
        return a;
    }
}

