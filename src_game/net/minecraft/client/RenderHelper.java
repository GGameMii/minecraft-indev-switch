/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.BufferUtils
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client;

import java.nio.FloatBuffer;
import net.minecraft.world.phys.Vec3;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public final class RenderHelper {
    private static FloatBuffer colorBuffer = BufferUtils.createFloatBuffer((int)16);

    public static void disableStandardItemLighting() {
        GL11.glDisable((int)2896);
        GL11.glDisable((int)16384);
        GL11.glDisable((int)16385);
        GL11.glDisable((int)2903);
    }

    public static void enableStandardItemLighting() {
        GL11.glEnable((int)2896);
        GL11.glEnable((int)16384);
        GL11.glEnable((int)16385);
        GL11.glEnable((int)2903);
        GL11.glColorMaterial((int)1032, (int)5634);
        Vec3 vec3D0 = new Vec3(0.3f, 1.0f, -0.7f);
        GL11.glLight((int)16384, (int)4611, (FloatBuffer)RenderHelper.setColorBuffer(vec3D0.x, vec3D0.y, vec3D0.z, 0.0f));
        GL11.glLight((int)16384, (int)4609, (FloatBuffer)RenderHelper.setColorBuffer(0.5f, 0.5f, 0.5f, 1.0f));
        GL11.glLight((int)16384, (int)4608, (FloatBuffer)RenderHelper.setColorBuffer(0.0f, 0.0f, 0.0f, 1.0f));
        GL11.glLight((int)16384, (int)4610, (FloatBuffer)RenderHelper.setColorBuffer(0.0f, 0.0f, 0.0f, 1.0f));
        vec3D0 = new Vec3(-0.7f, 1.0f, 0.2f);
        GL11.glLight((int)16385, (int)4611, (FloatBuffer)RenderHelper.setColorBuffer(vec3D0.x, vec3D0.y, vec3D0.z, 0.0f));
        GL11.glLight((int)16385, (int)4609, (FloatBuffer)RenderHelper.setColorBuffer(0.5f, 0.5f, 0.5f, 1.0f));
        GL11.glLight((int)16385, (int)4608, (FloatBuffer)RenderHelper.setColorBuffer(0.0f, 0.0f, 0.0f, 1.0f));
        GL11.glLight((int)16385, (int)4610, (FloatBuffer)RenderHelper.setColorBuffer(0.0f, 0.0f, 0.0f, 1.0f));
        GL11.glShadeModel((int)7425);
        GL11.glLightModel((int)2899, (FloatBuffer)RenderHelper.setColorBuffer(0.5f, 0.5f, 0.5f, 1.0f));
    }

    private static FloatBuffer setColorBuffer(float f0, float f1, float f2, float f3) {
        colorBuffer.clear();
        colorBuffer.put(f0).put(f1).put(f2).put(f3);
        colorBuffer.flip();
        return colorBuffer;
    }
}

