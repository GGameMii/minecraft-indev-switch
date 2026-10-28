/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client.renderer.entity;

import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.tile.Tile;
import org.lwjgl.opengl.GL11;

public final class RenderPrimedTnt
extends Render {
    private RenderBlocks blockRenderer = new RenderBlocks();

    public RenderPrimedTnt() {
        this.shadowSize = 0.5f;
    }

    @Override
    public final void doRender(Entity entity, float f2, float f3, float f4, float f5, float f6) {
        PrimedTnt tnt = (PrimedTnt)entity;
        f5 = f4;
        f4 = f3;
        f3 = f2;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)f3, (float)(f4 + tnt.onlineOffset), (float)f5);
        if ((float)tnt.fuse - f6 + 1.0f < 10.0f) {
            f3 = 1.0f - ((float)tnt.fuse - f6 + 1.0f) / 10.0f;
            if (f3 < 0.0f) {
                f3 = 0.0f;
            }
            if (f3 > 1.0f) {
                f3 = 1.0f;
            }
            f3 *= f3;
            f3 *= f3;
            f3 = 1.0f + f3 * 0.3f;
            GL11.glScalef((float)f3, (float)f3, (float)f3);
        }
        f3 = (1.0f - ((float)tnt.fuse - f6 + 1.0f) / 100.0f) * 0.8f;
        this.loadTexture("/terrain.png");
        this.blockRenderer.renderBlockOnInventory(Tile.tnt);
        if (tnt.fuse / 5 % 2 == 0) {
            GL11.glDisable((int)3553);
            GL11.glDisable((int)2896);
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)772);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)f3);
            this.blockRenderer.renderBlockOnInventory(Tile.tnt);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glDisable((int)3042);
            GL11.glEnable((int)2896);
            GL11.glEnable((int)3553);
        }
        GL11.glPopMatrix();
    }
}

