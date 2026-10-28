/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.PathEntity;
import net.minecraft.world.phys.Vec3;
import util.Mth;

public class Mob
extends LivingEntity {
    private PathEntity pathToEntity;
    protected Entity playerToAttack;
    protected boolean hasAttacked = false;

    public Mob(Level world) {
        super(world);
    }

    @Override
    protected void updateState() {
        float f6;
        float f8;
        float f7;
        if (this.onlineEntity) {
            return;
        }
        this.hasAttacked = false;
        if (this.playerToAttack == null) {
            this.playerToAttack = this.findPlayerToAttack();
            if (this.playerToAttack != null) {
                this.pathToEntity = this.level.pathFinder.createEntityPathTo(this, this.playerToAttack, 16.0f);
            }
        } else if (!this.playerToAttack.isEntityAlive()) {
            this.playerToAttack = null;
        } else {
            Entity entity5 = this.playerToAttack;
            f7 = this.playerToAttack.x - this.x;
            f8 = entity5.y - this.y;
            f6 = entity5.z - this.z;
            float f1 = Mth.sqrt(f7 * f7 + f8 * f8 + f6 * f6);
            if (this.level.rayTraceBlocks(new Vec3(this.x, this.y + this.getEyeHeight(), this.z), new Vec3(this.playerToAttack.x, this.playerToAttack.y + this.playerToAttack.getEyeHeight(), this.playerToAttack.z)) == null) {
                this.attackEntity(this.playerToAttack, f1);
            }
        }
        if (this.hasAttacked) {
            this.moveStrafing = 0.0f;
            this.moveForward = 0.0f;
            this.isJumping = false;
        } else {
            if (this.playerToAttack != null && (this.pathToEntity == null || this.rand.nextInt(20) == 0)) {
                this.pathToEntity = this.level.pathFinder.createEntityPathTo(this, this.playerToAttack, 16.0f);
            } else if (this.pathToEntity == null || this.rand.nextInt(100) == 0) {
                int i10 = -1;
                int i2 = -1;
                int i3 = -1;
                float f4 = -99999.0f;
                int i14 = 0;
                while (i14 < 200) {
                    int i16 = (int)(this.x + (float)this.rand.nextInt(21) - 10.0f);
                    int i17 = (int)(this.y + (float)this.rand.nextInt(9) - 4.0f);
                    int i18 = (int)(this.z + (float)this.rand.nextInt(21) - 10.0f);
                    float f9 = this.getBlockPathWeight(i16, i17, i18);
                    if (f9 > f4) {
                        f4 = f9;
                        i10 = i16;
                        i2 = i17;
                        i3 = i18;
                    }
                    ++i14;
                }
                if (i10 > 0) {
                    this.pathToEntity = this.level.pathFinder.createEntityPathTo(this, i10, i2, i3, 16.0f);
                }
            }
            boolean z11 = this.inWater();
            boolean z12 = this.handleLavaMovement();
            if (this.pathToEntity != null && this.rand.nextInt(100) != 0) {
                Vec3 vec3D13 = this.pathToEntity.getPosition(this);
                float f4 = this.bbWidth * 2.0f;
                while (vec3D13 != null) {
                    f8 = this.z;
                    f7 = this.y;
                    f6 = this.x;
                    float f15 = f8 - vec3D13.z;
                    if ((f6 -= vec3D13.x) * f6 + (f7 -= vec3D13.y) * f7 + f15 * f15 >= f4 * f4 || vec3D13.y > this.y) break;
                    this.pathToEntity.incrementPathIndex();
                    if (this.pathToEntity.isFinished()) {
                        vec3D13 = null;
                        this.pathToEntity = null;
                        continue;
                    }
                    vec3D13 = this.pathToEntity.getPosition(this);
                }
                this.isJumping = false;
                if (vec3D13 != null) {
                    float f15 = vec3D13.x - this.x;
                    f6 = vec3D13.z - this.z;
                    f7 = vec3D13.y - this.y;
                    this.xRot = (float)(Math.atan2(f6, f15) * 180.0 / 3.1415927410125732) - 90.0f;
                    this.moveForward = this.moveSpeed;
                    if (f7 > 0.0f) {
                        this.isJumping = true;
                    }
                }
                if (this.rand.nextFloat() < 0.8f && (z11 || z12)) {
                    this.isJumping = true;
                }
            } else {
                super.updateState();
                this.pathToEntity = null;
            }
        }
    }

    protected void attackEntity(Entity entity, float f2) {
    }

    protected float getBlockPathWeight(int i1, int i2, int i3) {
        return 0.0f;
    }

    protected Entity findPlayerToAttack() {
        return null;
    }

    @Override
    public boolean getCanSpawnHere(float f1, float f2, float f3) {
        return super.getCanSpawnHere(f1, f2, f3) && this.getBlockPathWeight((int)f1, (int)f2, (int)f3) >= 0.0f;
    }
}

