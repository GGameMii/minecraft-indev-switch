/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client.particle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.client.particle.EntityDiggingFX;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.client.renderer.Tesselator;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.tile.Tile;
import org.lwjgl.opengl.GL11;
import util.Mth;

public final class EffectRenderer {
    private Level worldObj;
    private List<EntityFX>[] fxLayers = new List[3];
    private RenderEngine renderEngine;
    private Random rand = new Random();

    public EffectRenderer(Level world, RenderEngine renderEngine) {
        if (world != null) {
            this.worldObj = world;
        }
        this.renderEngine = renderEngine;
        int i3 = 0;
        while (i3 < 3) {
            this.fxLayers[i3] = new ArrayList<EntityFX>();
            ++i3;
        }
    }

    public final void addEffect(EntityFX entityFX) {
        int i2 = entityFX.getFXLayer();
        this.fxLayers[i2].add(entityFX);
    }

    public final void updateEffects() {
        int i1 = 0;
        while (i1 < 3) {
            int i2 = 0;
            while (i2 < this.fxLayers[i1].size()) {
                EntityFX entityFX3 = this.fxLayers[i1].get(i2);
                entityFX3.tick();
                if (entityFX3.removed) {
                    this.fxLayers[i1].remove(i2--);
                }
                ++i2;
            }
            ++i1;
        }
    }

    public final void renderParticles(Entity particle, float f2) {
        float f3 = Mth.cos(particle.xRot * (float)Math.PI / 180.0f);
        float f4 = Mth.sin(particle.xRot * (float)Math.PI / 180.0f);
        float f5 = -f4 * Mth.sin(particle.yRot * (float)Math.PI / 180.0f);
        float f6 = f3 * Mth.sin(particle.yRot * (float)Math.PI / 180.0f);
        float f11 = Mth.cos(particle.yRot * (float)Math.PI / 180.0f);
        int l2 = 0;
        while (l2 < 2) {
            if (this.fxLayers[l2].size() != 0) {
                int id = 0;
                if (l2 == 0) {
                    id = this.renderEngine.getTexture("/particles.png");
                }
                if (l2 == 1) {
                    id = this.renderEngine.getTexture("/terrain.png");
                }
                GL11.glBindTexture((int)3553, (int)id);
                Tesselator t2 = Tesselator.instance;
                Tesselator.instance.begin();
                int i2 = 0;
                while (i2 < this.fxLayers[l2].size()) {
                    this.fxLayers[l2].get(i2).renderParticle(t2, f2, f3, f11, f4, f5, f6);
                    ++i2;
                }
                t2.end();
            }
            ++l2;
        }
    }

    public final void renderLitParticles(float f1) {
        if (this.fxLayers[2].size() != 0) {
            Tesselator tessellator2 = Tesselator.instance;
            int i3 = 0;
            while (i3 < this.fxLayers[2].size()) {
                this.fxLayers[2].get(i3).renderParticle(tessellator2, f1, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
                ++i3;
            }
        }
    }

    public final void clearEffects(Level world) {
        this.worldObj = world;
        int i2 = 0;
        while (i2 < 3) {
            this.fxLayers[i2].clear();
            ++i2;
        }
    }

    public final void addBlockDestroyEffects(int i1, int i2, int i3) {
        int i4 = this.worldObj.getTile(i1, i2, i3);
        if (i4 != 0) {
            Tile block11 = Tile.tiles[i4];
            int i5 = 0;
            while (i5 < 4) {
                int i6 = 0;
                while (i6 < 4) {
                    int i7 = 0;
                    while (i7 < 4) {
                        float f8 = (float)i1 + ((float)i5 + 0.5f) / 4.0f;
                        float f9 = (float)i2 + ((float)i6 + 0.5f) / 4.0f;
                        float f10 = (float)i3 + ((float)i7 + 0.5f) / 4.0f;
                        this.addEffect(new EntityDiggingFX(this.worldObj, f8, f9, f10, f8 - (float)i1 - 0.5f, f9 - (float)i2 - 0.5f, f10 - (float)i3 - 0.5f, block11));
                        ++i7;
                    }
                    ++i6;
                }
                ++i5;
            }
        }
    }

    public final void addBlockHitEffects(int i1, int i2, int i3, int i4) {
        int i5 = this.worldObj.getTile(i1, i2, i3);
        if (i5 != 0) {
            Tile block9 = Tile.tiles[i5];
            float f6 = (float)i1 + this.rand.nextFloat() * (block9.xx1 - block9.xx0 - 0.2f) + 0.1f + block9.xx0;
            float f7 = (float)i2 + this.rand.nextFloat() * (block9.yy1 - block9.yy0 - 0.2f) + 0.1f + block9.yy0;
            float f8 = (float)i3 + this.rand.nextFloat() * (block9.zz1 - block9.zz0 - 0.2f) + 0.1f + block9.zz0;
            if (i4 == 0) {
                f7 = (float)i2 + block9.yy0 - 0.1f;
            }
            if (i4 == 1) {
                f7 = (float)i2 + block9.yy1 + 0.1f;
            }
            if (i4 == 2) {
                f8 = (float)i3 + block9.zz0 - 0.1f;
            }
            if (i4 == 3) {
                f8 = (float)i3 + block9.zz1 + 0.1f;
            }
            if (i4 == 4) {
                f6 = (float)i1 + block9.xx0 - 0.1f;
            }
            if (i4 == 5) {
                f6 = (float)i1 + block9.xx1 + 0.1f;
            }
            this.addEffect(new EntityDiggingFX(this.worldObj, f6, f7, f8, 0.0f, 0.0f, 0.0f, block9).multiplyVelocity(0.2f).multipleParticleScaleBy(0.6f));
        }
    }

    public final String getStatistics() {
        return "" + (this.fxLayers[0].size() + this.fxLayers[1].size() + this.fxLayers[2].size());
    }
}

