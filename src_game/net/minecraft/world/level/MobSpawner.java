/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.level;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class MobSpawner {
    private Level level;

    public MobSpawner(Level world) {
        this.level = world;
    }

    public final void performSpawning() {
        this.spawnMonsters();
        this.spawnAnimals();
    }

    public void spawnMonsters() {
        int rate = this.level.width * this.level.depth * this.level.height * 20 / 64 / 64 / 64 / 2;
        if (this.level.difficultySetting == 0) {
            rate = 0;
        }
        if (this.level.difficultySetting == 1) {
            rate = rate * 3 / 4;
        }
        if (this.level.difficultySetting == 2) {
            rate = (rate << 2) / 4;
        }
        if (this.level.difficultySetting == 3) {
            rate = rate * 6 / 4;
        }
        int i3 = this.level.entitiesInLevelList(Monster.class);
        int i4 = 0;
        while (i4 < 4) {
            if (i3 < rate) {
                MobSpawner mobSpawner22 = this;
                int i6 = 0;
                int i7 = this.level.random.nextInt(5);
                int i8 = this.level.random.nextInt(this.level.width);
                int i9 = (int)(Math.min(this.level.random.nextFloat(), this.level.random.nextFloat()) * (float)this.level.height);
                int i10 = this.level.random.nextInt(this.level.depth);
                Player entityLiving5 = this.level.getClosestPlayer(i8, i9, i10, 1024.0f);
                int i11 = 0;
                while (i11 < 2) {
                    int i12 = i8;
                    int i13 = i9;
                    int i14 = i10;
                    int i15 = 0;
                    while (i15 < 3) {
                        if ((i12 += mobSpawner22.level.random.nextInt(6) - mobSpawner22.level.random.nextInt(6)) >= 0 && (i14 += mobSpawner22.level.random.nextInt(6) - mobSpawner22.level.random.nextInt(6)) > 0 && (i13 += mobSpawner22.level.random.nextInt(1) - mobSpawner22.level.random.nextInt(1)) >= 0 && i13 < mobSpawner22.level.height - 2 && i12 < mobSpawner22.level.width && i14 < mobSpawner22.level.depth) {
                            float f21;
                            float f20;
                            float f19;
                            float f16 = (float)i12 + 0.5f;
                            float f17 = (float)i13 + 0.5f;
                            float f18 = (float)i14 + 0.5f;
                            if (!(entityLiving5 != null ? (f19 = f16 - entityLiving5.x) * f19 + (f20 = f17 - entityLiving5.y) * f20 + (f21 = f18 - entityLiving5.z) * f21 < 1024.0f : (f19 = f16 - (float)mobSpawner22.level.xSpawn) * f19 + (f20 = f17 - (float)mobSpawner22.level.ySpawn) * f20 + (f21 = f18 - (float)mobSpawner22.level.zSpawn) * f21 < 1024.0f)) {
                                Monster object23 = null;
                                if (i7 == 0) {
                                    object23 = new Skeleton(mobSpawner22.level);
                                }
                                if (i7 == 1) {
                                    object23 = new Creeper(mobSpawner22.level);
                                }
                                if (i7 == 2) {
                                    object23 = new Spider(mobSpawner22.level);
                                }
                                if (i7 == 3) {
                                    object23 = new Zombie(mobSpawner22.level);
                                }
                                if (object23 instanceof Monster && mobSpawner22.level.difficultySetting == 0) {
                                    object23 = null;
                                }
                                if (object23 != null && !mobSpawner22.level.isSolidTile(i12, i13, i14) && mobSpawner22.level.isSolidTile(i12, i13 - 1, i14) && ((LivingEntity)object23).getCanSpawnHere(f16, f17, f18)) {
                                    f21 = mobSpawner22.level.random.nextFloat() * 360.0f;
                                    ((LivingEntity)object23).absMoveTo(f16, f17, f18, f21, 0.0f);
                                    ++i6;
                                    mobSpawner22.level.spawnEntityInWorld(object23);
                                }
                            }
                        }
                        ++i15;
                    }
                    ++i11;
                }
                i3 += i6;
            }
            ++i4;
        }
    }

    public void spawnAnimals() {
        int rate = this.level.width * this.level.depth * this.level.height * 20 / 64 / 64 / 64 / 2;
        if (this.level.difficultySetting == 0) {
            rate = 0;
        }
        if (this.level.difficultySetting == 1) {
            rate = rate * 3 / 4;
        }
        if (this.level.difficultySetting == 2) {
            rate = (rate << 2) / 4;
        }
        if (this.level.difficultySetting == 3) {
            rate = rate * 6 / 4;
        }
        int i2 = this.level.width * this.level.depth / 4000;
        int i4 = this.level.entitiesInLevelList(Animal.class);
        rate = 0;
        while (rate < 4) {
            if (i4 < i2) {
                MobSpawner mobSpawner22 = this;
                int i6 = 0;
                int i7 = this.level.random.nextInt(2);
                int i8 = this.level.random.nextInt(this.level.width);
                int i9 = this.level.random.nextInt(this.level.height);
                int i10 = this.level.random.nextInt(this.level.depth);
                Player entityLiving5 = this.level.getClosestPlayer(i8, i9, i10, 1024.0f);
                int i11 = 0;
                while (i11 < 2) {
                    int i12 = i8;
                    int i13 = i9;
                    int i14 = i10;
                    int i15 = 0;
                    while (i15 < 3) {
                        if ((i12 += mobSpawner22.level.random.nextInt(6) - mobSpawner22.level.random.nextInt(6)) >= 0 && (i14 += mobSpawner22.level.random.nextInt(6) - mobSpawner22.level.random.nextInt(6)) > 0 && (i13 += mobSpawner22.level.random.nextInt(1) - mobSpawner22.level.random.nextInt(1)) >= 0 && i13 < mobSpawner22.level.height - 2 && i12 < mobSpawner22.level.width && i14 < mobSpawner22.level.depth) {
                            float f21;
                            float f20;
                            float f19;
                            float f16 = (float)i12 + 0.5f;
                            float f17 = (float)i13 + 0.5f;
                            float f18 = (float)i14 + 0.5f;
                            if (!(entityLiving5 != null ? (f19 = f16 - entityLiving5.x) * f19 + (f20 = f17 - entityLiving5.y) * f20 + (f21 = f18 - entityLiving5.z) * f21 < 1024.0f : (f19 = f16 - (float)mobSpawner22.level.xSpawn) * f19 + (f20 = f17 - (float)mobSpawner22.level.ySpawn) * f20 + (f21 = f18 - (float)mobSpawner22.level.zSpawn) * f21 < 1024.0f)) {
                                Animal object23 = null;
                                if (i7 == 0) {
                                    object23 = new Pig(mobSpawner22.level);
                                }
                                if (i7 == 1) {
                                    object23 = new Sheep(mobSpawner22.level);
                                }
                                if (object23 != null && !mobSpawner22.level.isSolidTile(i12, i13, i14) && mobSpawner22.level.isSolidTile(i12, i13 - 1, i14) && ((LivingEntity)object23).getCanSpawnHere(f16, f17, f18)) {
                                    f21 = mobSpawner22.level.random.nextFloat() * 360.0f;
                                    ((LivingEntity)object23).absMoveTo(f16, f17, f18, f21, 0.0f);
                                    ++i6;
                                    mobSpawner22.level.spawnEntityInWorld(object23);
                                }
                            }
                        }
                        ++i15;
                    }
                    ++i11;
                }
                i4 += i6;
            }
            ++rate;
        }
    }
}

