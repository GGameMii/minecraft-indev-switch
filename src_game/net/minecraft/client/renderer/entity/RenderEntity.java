/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client.renderer.entity;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.world.entity.Entity;
import org.lwjgl.opengl.GL11;

public final class RenderEntity
extends Render {
    @Override
    public final void doRender(Entity entity, float f2, float f3, float f4, float f5, float f6) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)(f2 - entity.lastTickX), (float)(f3 - entity.lastTickY), (float)(f4 - entity.lastTickZ));
        RenderEntity.renderOffsetAABB(entity.bb);
        GL11.glPopMatrix();
    }
}

