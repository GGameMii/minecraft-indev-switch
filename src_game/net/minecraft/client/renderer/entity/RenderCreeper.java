/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelCreeper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Creeper;
import org.lwjgl.opengl.GL11;
import util.Mth;

public final class RenderCreeper
extends RenderLiving {
    public RenderCreeper() {
        super(new ModelCreeper(), 0.5f);
    }

    @Override
    protected final void preRenderCallback(LivingEntity e2, float a2) {
        float flash = ((Creeper)e2).getFlash(a2);
        a2 = 1.0f + Mth.sin(flash * 100.0f) * flash * 0.01f;
        if (flash < 0.0f) {
            flash = 0.0f;
        }
        if (flash > 1.0f) {
            flash = 1.0f;
        }
        flash *= flash;
        flash *= flash;
        float size = (1.0f + flash * 0.4f) * a2;
        flash = (1.0f + flash * 0.1f) / a2;
        GL11.glScalef((float)size, (float)flash, (float)size);
    }

    @Override
    protected final int getColorMultiplier(LivingEntity e2, float f2, float a2) {
        float flash = ((Creeper)e2).getFlash(a2);
        if ((int)(flash * 10.0f) % 2 == 0) {
            return 0;
        }
        int c2 = (int)(flash * 0.2f * 255.0f);
        if (c2 < 0) {
            c2 = 0;
        }
        if (c2 > 255) {
            c2 = 255;
        }
        return c2 << 24 | 0xFF0000 | 0xFF00 | 0xFF;
    }
}

