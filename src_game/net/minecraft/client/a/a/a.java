/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client.a.a;

import net.minecraft.a.c.b;
import net.minecraft.client.a.a.f;
import org.lwjgl.opengl.GL11;

public final class a
extends f {
    public final void a(b b2, float f2, float f3, float f4, float f5, float f6) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)(f2 - b2.B), (float)(f3 - b2.C), (float)(f4 - b2.D));
        net.minecraft.client.a.a.a.a(b2.r);
        GL11.glPopMatrix();
    }
}

