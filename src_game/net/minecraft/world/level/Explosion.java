/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.level;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.tile.Tile;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import util.Mth;

public class Explosion {
    public boolean isFlaming = false;
    private Random ExplosionRNG = new Random();
    private Level worldObj;
    public float explosionX;
    public float explosionY;
    public float explosionZ;
    public Entity exploder;
    public float explosionSize;
    public Set<Integer> destroyedBlockPositions = new HashSet<Integer>();

    public Explosion(Level level, Entity e2, float x2, float y2, float z2, float s2) {
        this.worldObj = level;
        this.exploder = e2;
        this.explosionSize = s2;
        this.explosionX = x2;
        this.explosionY = y2;
        this.explosionZ = z2;
    }

    public void doExplosion() {
        float d19;
        float d17;
        float d15;
        int i5;
        int i4;
        if (!this.worldObj.mobGriefing) {
            return;
        }
        float f1 = this.explosionSize;
        int b2 = 16;
        int i3 = 0;
        while (i3 < b2) {
            i4 = 0;
            while (i4 < b2) {
                i5 = 0;
                while (i5 < b2) {
                    if (i3 == 0 || i3 == b2 - 1 || i4 == 0 || i4 == b2 - 1 || i5 == 0 || i5 == b2 - 1) {
                        float d6 = (float)i3 / ((float)b2 - 1.0f) * 2.0f - 1.0f;
                        float d8 = (float)i4 / ((float)b2 - 1.0f) * 2.0f - 1.0f;
                        float d10 = (float)i5 / ((float)b2 - 1.0f) * 2.0f - 1.0f;
                        float d12 = (float)Math.sqrt(d6 * d6 + d8 * d8 + d10 * d10);
                        d6 /= d12;
                        d8 /= d12;
                        d10 /= d12;
                        float range = this.explosionSize * (0.7f + this.worldObj.random.nextFloat() * 0.6f);
                        d15 = this.explosionX;
                        d17 = this.explosionY;
                        d19 = this.explosionZ;
                        float f21 = 0.3f;
                        while (range > 0.0f) {
                            int z0;
                            int y0;
                            int x0 = Mth.floor(d15);
                            int id = this.worldObj.getTile(x0, y0 = Mth.floor(d17), z0 = Mth.floor(d19));
                            if (id > 0) {
                                range -= (Tile.tiles[id].getExplosionResistance() + 0.3f) * f21;
                            }
                            if (range > 0.0f) {
                                int pos = x0 + (y0 << 10) + (z0 << 10 << 10);
                                this.destroyedBlockPositions.add(pos);
                            }
                            d15 += d6 * f21;
                            d17 += d8 * f21;
                            d19 += d10 * f21;
                            range -= f21 * 0.75f;
                        }
                    }
                    ++i5;
                }
                ++i4;
            }
            ++i3;
        }
        this.explosionSize *= 2.0f;
        i3 = Mth.floor(this.explosionX - this.explosionSize - 1.0f);
        i4 = Mth.floor(this.explosionX + this.explosionSize + 1.0f);
        i5 = Mth.floor(this.explosionY - this.explosionSize - 1.0f);
        int i29 = Mth.floor(this.explosionY + this.explosionSize + 1.0f);
        int i7 = Mth.floor(this.explosionZ - this.explosionSize - 1.0f);
        int i30 = Mth.floor(this.explosionZ + this.explosionSize + 1.0f);
        List<Entity> entitiesInRange = this.worldObj.getEntitiesWithinAABBExcludingEntity(this.exploder, new AABB(i3, i5, i7, i4, i29, i30));
        Vec3 vec3D31 = new Vec3(this.explosionX, this.explosionY, this.explosionZ);
        int i11 = 0;
        while (i11 < entitiesInRange.size()) {
            Entity entity33 = entitiesInRange.get(i11);
            double d13 = (double)entity33.getDistance(this.explosionX, this.explosionY, this.explosionZ) / (double)this.explosionSize;
            if (d13 <= 1.0) {
                d15 = entity33.x - this.explosionX;
                d17 = entity33.y - this.explosionY;
                d19 = entity33.z - this.explosionZ;
                double d39 = Mth.sqrt(d15 * d15 + d17 * d17 + d19 * d19);
                d15 = (float)((double)d15 / d39);
                d17 = (float)((double)d17 / d39);
                d19 = (float)((double)d19 / d39);
                double d40 = this.worldObj.getBlockDensity(vec3D31, entity33.bb);
                double d41 = (1.0 - d13) * d40;
                if (!this.worldObj.onlineLevel) {
                    entity33.hurt(this.exploder, (int)((d41 * d41 + d41) / 2.0 * 8.0 * (double)this.explosionSize + 1.0));
                }
                entity33.xd = (float)((double)entity33.xd + (double)d15 * d41);
                entity33.yd = (float)((double)entity33.yd + (double)d17 * d41);
                entity33.zd = (float)((double)entity33.zd + (double)d19 * d41);
            }
            ++i11;
        }
        this.explosionSize = f1;
        ArrayList<Integer> arrayList32 = new ArrayList<Integer>();
        arrayList32.addAll(this.destroyedBlockPositions);
        if (this.isFlaming) {
            int i34 = arrayList32.size() - 1;
            while (i34 >= 0) {
                int i39 = (Integer)arrayList32.get(i34);
                int i8 = i39 & 0x3FF;
                int i19 = i39 >> 10 & 0x3FF;
                int i20 = i39 >> 20 & 0x3FF;
                if (i8 >= 0 && i19 >= 0 && i20 >= 0 && i8 < this.worldObj.width && i19 < this.worldObj.height && i20 < this.worldObj.depth) {
                    int i38 = this.worldObj.getTile(i8, i19, i20);
                    int i18 = this.worldObj.getTile(i8, i19 - 1, i20);
                    if (i38 == 0 && Tile.opaqueCubeLookup[i18] && this.ExplosionRNG.nextInt(3) == 0) {
                        this.worldObj.setTile(i8, i19, i20, Tile.fire.id);
                    }
                }
                --i34;
            }
        }
    }

    public void applyTerrain(boolean smoke) {
        this.worldObj.playSoundAtPlayer(this.explosionX, this.explosionY, this.explosionZ, "random.explode", 4.0f, (1.0f + (this.worldObj.random.nextFloat() - this.worldObj.random.nextFloat()) * 0.2f) * 0.7f);
        ArrayList<Integer> tiles = new ArrayList<Integer>();
        tiles.addAll(this.destroyedBlockPositions);
        int i2 = tiles.size() - 1;
        while (i2 >= 0) {
            int pos = (Integer)tiles.get(i2);
            int x0 = pos & 0x3FF;
            int y0 = pos >> 10 & 0x3FF;
            int z0 = pos >> 20 & 0x3FF;
            if (x0 >= 0 && y0 >= 0 && z0 >= 0 && x0 < this.worldObj.width && y0 < this.worldObj.height && z0 < this.worldObj.depth) {
                int id = this.worldObj.getTile(x0, y0, z0);
                if (smoke) {
                    float xx = (float)x0 + this.worldObj.random.nextFloat();
                    float yy = (float)y0 + this.worldObj.random.nextFloat();
                    float zz = (float)z0 + this.worldObj.random.nextFloat();
                    float xxd = xx - this.explosionX;
                    float yyd = yy - this.explosionY;
                    float zzd = zz - this.explosionZ;
                    float rr = Mth.sqrt(xxd * xxd + yyd * yyd + zzd * zzd);
                    xxd /= rr;
                    yyd /= rr;
                    zzd /= rr;
                    float radius = 0.5f / (rr / this.explosionSize + 0.1f);
                    this.worldObj.spawnParticle("explode", (xx + this.explosionX * 1.0f) / 2.0f, (yy + this.explosionY * 1.0f) / 2.0f, (zz + this.explosionZ * 1.0f) / 2.0f, xxd *= (radius *= this.worldObj.random.nextFloat() * this.worldObj.random.nextFloat() + 0.3f), yyd *= radius, zzd *= radius);
                    this.worldObj.spawnParticle("smoke", xx, yy, zz, xxd, yyd, zzd);
                }
                if (id > 0) {
                    if (!this.worldObj.onlineLevel) {
                        Tile.tiles[id].dropBlockAsItemWithChance(this.worldObj, x0, y0, z0, this.worldObj.getTileMetadata(x0, y0, z0), 0.3f);
                    }
                    this.worldObj.setTile(x0, y0, z0, 0);
                    if (!this.worldObj.onlineLevel) {
                        Tile.tiles[id].onBlockDestroyedByExplosion(this.worldObj, x0, y0, z0);
                    }
                }
            }
            --i2;
        }
    }
}

