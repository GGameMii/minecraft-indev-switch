/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.entity;

import com.mojang.nbt.NBTTagCompound;
import java.util.List;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.IMobs;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.tile.StepSound;
import net.minecraft.world.level.tile.Tile;
import net.minecraft.world.phys.Vec3;
import util.Mth;

public class LivingEntity
extends Entity
implements IMobs {
    public int invulnerableDuration = 20;
    public float renderYawOffset = 0.0f;
    public float prevRenderYawOffset = 0.0f;
    private float rotationYawHead;
    private float prevRotationYawHead;
    protected String texture = "/char.png";
    private int scoreValue = 0;
    public int health;
    public int prevHealth;
    private int livingSoundTime;
    public int hurtTime;
    public int hurtDuration;
    public float hurtDir = 0.0f;
    public int deathTime = 0;
    public int attackTime = 0;
    public float oTilt;
    public float tilt;
    public float walkAnimSpeedO;
    public float walkAnimSpeed;
    public float walkAnimPos;
    protected int entityAge;
    protected float moveStrafing;
    protected float moveForward;
    private float randomYawVelocity;
    public boolean isJumping;
    private float defaultPitch;
    protected float moveSpeed;
    public float prevSwingProgress;
    public float swingProgress;
    protected int lastHurt = 0;
    protected int lSteps;
    protected float lx;
    protected float ly;
    protected float lz;
    protected float lxr;
    protected float lyr;
    public boolean onlineEntity;

    public LivingEntity(Level level) {
        super(level);
        Math.random();
        this.entityAge = 0;
        this.isJumping = false;
        this.defaultPitch = 0.0f;
        this.moveSpeed = 0.7f;
        this.health = 10;
        this.collision = true;
        Math.random();
        this.setPos(this.x, this.y, this.z);
        Math.random();
        this.xRot = (float)(Math.random() * 3.1415927410125732 * 2.0);
        this.footSize = 0.5f;
    }

    @Override
    protected void entityInit() {
    }

    @Override
    public final String getTexture() {
        return this.texture;
    }

    @Override
    public final boolean canBeCollidedWith() {
        return !this.removed;
    }

    @Override
    public final boolean canBePushed() {
        return !this.removed;
    }

    @Override
    protected float getEyeHeight() {
        return this.bbHeight * 0.85f;
    }

    @Override
    public void tick() {
        boolean bl;
        float f2;
        float f3;
        this.prevSwingProgress = this.swingProgress;
        super.tick();
        if (this.rand.nextInt(1000) < this.livingSoundTime++) {
            this.livingSoundTime = -80;
            String string = this.getLivingSound();
            if (string != null) {
                this.level.playSoundAtEntity(this, string, 1.0f, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2f + 1.0f);
            }
        }
        if (this.isInsideOfWater()) {
            --this.air;
            if (this.air == -20) {
                this.air = 0;
                if (this.level.survivalWorld) {
                    for (int i2 = 0; i2 < 8; ++i2) {
                        float f4 = this.rand.nextFloat() - this.rand.nextFloat();
                        f3 = this.rand.nextFloat() - this.rand.nextFloat();
                        f2 = this.rand.nextFloat() - this.rand.nextFloat();
                        this.level.spawnParticle("bubble", this.x + f4, this.y + f3, this.z + f2, this.xd, this.yd, this.zd);
                    }
                }
                this.hurt(null, 2);
            }
            this.fire = 0;
        } else {
            this.air = this.maxAir;
        }
        this.oTilt = this.tilt;
        if (this.attackTime > 0) {
            --this.attackTime;
        }
        if (this.hurtTime > 0) {
            --this.hurtTime;
        }
        if (this.invulnerableTime > 0) {
            --this.invulnerableTime;
        }
        if (this.health <= 0) {
            ++this.deathTime;
            if (this.deathTime > 20) {
                this.remove();
            }
        }
        this.prevRenderYawOffset = this.renderYawOffset;
        this.xRotI = this.xRot;
        this.yRotI = this.yRot;
        this.livingTick();
        float f5 = this.x - this.xo;
        float f6 = this.z - this.zo;
        f3 = Mth.sqrt(f5 * f5 + f6 * f6);
        f2 = this.renderYawOffset;
        float f7 = 0.0f;
        float f8 = 0.0f;
        if (f3 > 0.05f) {
            f8 = 1.0f;
            f7 = f3 * 3.0f;
            f2 = (float)Math.atan2(f6, f5) * 180.0f / (float)Math.PI - 90.0f;
        }
        if (!this.onGround) {
            f8 = 0.0f;
        }
        this.rotationYawHead += (f8 - this.rotationYawHead) * 0.3f;
        for (f5 = f2 - this.renderYawOffset; f5 < -180.0f; f5 += 360.0f) {
        }
        while (f5 >= 180.0f) {
            f5 -= 360.0f;
        }
        this.renderYawOffset += f5 * 0.1f;
        for (f5 = this.xRot - this.renderYawOffset; f5 < -180.0f; f5 += 360.0f) {
        }
        while (f5 >= 180.0f) {
            f5 -= 360.0f;
        }
        boolean bl2 = bl = f5 < -90.0f || f5 >= 90.0f;
        if (f5 < -75.0f) {
            f5 = -75.0f;
        }
        if (f5 >= 75.0f) {
            f5 = 75.0f;
        }
        this.renderYawOffset = this.xRot - f5;
        this.renderYawOffset += f5 * 0.1f;
        if (bl) {
            f7 = -f7;
        }
        while (this.xRot - this.xRotI < -180.0f) {
            this.xRotI -= 360.0f;
        }
        while (this.xRot - this.xRotI >= 180.0f) {
            this.xRotI += 360.0f;
        }
        while (this.renderYawOffset - this.prevRenderYawOffset < -180.0f) {
            this.prevRenderYawOffset -= 360.0f;
        }
        while (this.renderYawOffset - this.prevRenderYawOffset >= 180.0f) {
            this.prevRenderYawOffset += 360.0f;
        }
        while (this.yRot - this.yRotI < -180.0f) {
            this.yRotI -= 360.0f;
        }
        while (this.yRot - this.yRotI >= 180.0f) {
            this.yRotI += 360.0f;
        }
        this.prevRotationYawHead += f7;
    }

    @Override
    public final void setSize(float f2, float f3) {
        super.setSize(f2, f3);
    }

    public void heal(int n2) {
        if (this.health > 0) {
            this.health += n2;
            if (this.health > 20) {
                this.health = 20;
            }
            this.invulnerableTime = this.invulnerableDuration / 2;
        }
    }

    @Override
    public boolean hurt(Entity entity, int n2) {
        if (!this.level.survivalWorld || this.level.onlineLevel) {
            return false;
        }
        this.entityAge = 0;
        if (this.health <= 0) {
            return false;
        }
        this.walkAnimSpeed = 1.5f;
        boolean bl = true;
        if ((float)this.invulnerableTime > (float)this.invulnerableDuration / 2.0f) {
            if (n2 <= this.lastHurt) {
                return false;
            }
            this.actuallyHurt(n2 - this.lastHurt);
            this.lastHurt = n2;
            bl = false;
        } else {
            this.prevHealth = this.health;
            this.invulnerableTime = this.invulnerableDuration;
            this.health -= n2;
            this.hurtDuration = 10;
            this.hurtTime = 10;
        }
        this.hurtDir = 0.0f;
        if (bl) {
            this.setBeenAttacked();
            float f2 = -1.0f;
            float f3 = -1.0f;
            if (entity != null) {
                f2 = entity.x;
                f3 = entity.z;
                this.knockback(f2, f3);
            } else {
                this.hurtDir = (int)(Math.random() * 2.0) * 180;
            }
            if (this.health > 0) {
                this.level.updateEntityStatus(this, (byte)2, f2, f3);
            }
        }
        if (this.health <= 0) {
            if (bl) {
                this.level.playSoundAtEntity(this, this.getDeathSound(), 1.0f, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2f + 1.0f);
            }
            this.onDeath(entity);
        } else if (bl) {
            this.level.playSoundAtEntity(this, this.getHurtSound(), 1.0f, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2f + 1.0f);
        }
        return true;
    }

    private void knockback(float f2, float f3) {
        float f4 = f2 - this.x;
        float f5 = f3 - this.z;
        this.hurtDir = (float)(Math.atan2(f5, f4) * 180.0 / 3.1415927410125732) - this.xRot;
        float f6 = Mth.sqrt(f4 * f4 + f5 * f5);
        this.xd /= 2.0f;
        this.yd /= 2.0f;
        this.zd /= 2.0f;
        this.xd -= f4 / f6 * 0.4f;
        this.yd += 0.4f;
        this.zd -= f5 / f6 * 0.4f;
        if (this.yd > 0.4f) {
            this.yd = 0.4f;
        }
    }

    protected String getLivingSound() {
        return null;
    }

    protected String getHurtSound() {
        return "random.hurt";
    }

    protected String getDeathSound() {
        return "random.hurt";
    }

    public void onDeath(Entity entity) {
        int n2 = this.scoreValue();
        if (n2 > 0) {
            int n3 = this.rand.nextInt(3);
            for (int i2 = 0; i2 < n3; ++i2) {
                this.dropItemWithOffset(n2, 1);
            }
        }
        this.level.updateEntityStatus(this, (byte)3, -1.0f, -1.0f);
    }

    protected int scoreValue() {
        return 0;
    }

    @Override
    public void fall(float f2) {
        int n2 = (int)Math.ceil(f2 - 3.0f);
        if (n2 > 0) {
            this.hurt(null, n2);
            int n3 = this.level.getTile((int)this.x, (int)(this.y - 0.2f - this.heightOffset), (int)this.z);
            if (n3 > 0) {
                StepSound stepSound = Tile.tiles[n3].stepSound;
                this.level.playSoundAtEntity(this, stepSound.getSndName(), stepSound.soundVolume * 0.5f, stepSound.soundPitch * 0.75f);
            }
        }
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound nBTTagCompound) {
        nBTTagCompound.setShort("Health", (short)this.health);
        nBTTagCompound.setShort("HurtTime", (short)this.hurtTime);
        nBTTagCompound.setShort("DeathTime", (short)this.deathTime);
        nBTTagCompound.setShort("AttackTime", (short)this.attackTime);
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound nBTTagCompound) {
        this.health = nBTTagCompound.getShort("Health");
        if (!nBTTagCompound.hasKey("Health")) {
            this.health = 10;
        }
        this.hurtTime = nBTTagCompound.getShort("HurtTime");
        this.deathTime = nBTTagCompound.getShort("DeathTime");
        this.attackTime = nBTTagCompound.getShort("AttackTime");
    }

    @Override
    protected String getEntityString() {
        return "Mob";
    }

    @Override
    public final boolean isEntityAlive() {
        return !this.removed && this.health > 0;
    }

    public void livingTick() {
        float f2;
        float f3;
        float f4;
        float f5;
        float f6;
        float f7;
        ++this.entityAge;
        Player player = this.level.getClosestPlayerToEntity(this, 2048.0f);
        if (!this.level.onlineLevel && this.entityAge > 600 && this.rand.nextInt(800) == 0 && player != null) {
            f7 = player.x - this.x;
            f6 = player.y - this.y;
            f5 = player.z - this.z;
            if (f7 * f7 + f6 * f6 + f5 * f5 < 1024.0f) {
                this.entityAge = 0;
            } else {
                this.remove();
            }
        }
        if (this.lSteps > 0) {
            f7 = this.x + (this.lx - this.x) / (float)this.lSteps;
            f6 = this.y + (this.ly - this.y) / (float)this.lSteps;
            f5 = this.z + (this.lz - this.z) / (float)this.lSteps;
            for (f4 = this.lxr - this.xRot; f4 < -180.0f; f4 += 360.0f) {
            }
            while (f4 >= 180.0f) {
                f4 -= 360.0f;
            }
            this.xRot += f4 / (float)this.lSteps;
            this.yRot += (this.lyr - this.yRot) / (float)this.lSteps;
            --this.lSteps;
            this.setPos(f7, f6, f5);
            this.setRot(this.xRot, this.yRot);
        }
        if (this.onlineEntity) {
            float f8;
            this.walkAnimSpeedO = this.walkAnimSpeed;
            f7 = this.x - this.xo;
            f6 = this.z - this.zo;
            this.distanceWalkedModified = (float)((double)this.distanceWalkedModified + (double)Mth.sqrt(f7 * f7 + f6 * f6) * 0.6);
            if (this.canTriggerWalking) {
                int n2 = (int)this.x;
                int n3 = (int)(this.y - 0.2f - this.heightOffset);
                int n4 = (int)this.z;
                int n5 = this.level.getTile(n2, n3, n4);
                if (this.distanceWalkedModified > (float)this.nextStepDistance && n5 > 0) {
                    ++this.nextStepDistance;
                    StepSound stepSound = Tile.tiles[n5].stepSound;
                    if (!Tile.tiles[n5].material.getIsLiquid()) {
                        this.level.playSoundAtEntity(this, stepSound.getSndName(), stepSound.soundVolume * 0.15f, stepSound.soundPitch);
                    }
                    Tile.tiles[n5].onEntityWalking(this.level, n2, n3, n4);
                }
            }
            f6 = Mth.sqrt(f7 * f7 + f6 * f6) * 4.0f;
            if (f6 > 1.0f) {
                f6 = 1.0f;
            }
            this.walkAnimSpeed += (f6 - this.walkAnimSpeed) * 0.4f;
            this.walkAnimPos += this.walkAnimSpeed;
            return;
        }
        if (this.health <= 0) {
            this.isJumping = false;
            this.moveStrafing = 0.0f;
            this.moveForward = 0.0f;
            this.randomYawVelocity = 0.0f;
        } else {
            this.updateState();
        }
        boolean bl = this.inWater();
        boolean bl2 = this.handleLavaMovement();
        if (this.isJumping) {
            if (bl) {
                this.yd += 0.04f;
            } else if (bl2) {
                this.yd += 0.04f;
            } else if (this.onGround) {
                this.yd = 0.42f;
            }
        }
        this.moveStrafing *= 0.98f;
        this.moveForward *= 0.98f;
        this.randomYawVelocity *= 0.9f;
        f5 = this.moveForward;
        f4 = this.moveStrafing;
        if (this.inWater()) {
            f3 = this.y;
            this.moveFlying(f4, f5, 0.02f);
            this.moveEntity(this.xd, this.yd, this.zd);
            this.xd *= 0.8f;
            this.yd *= 0.8f;
            this.zd *= 0.8f;
            this.yd = (float)((double)this.yd - 0.02);
            if (this.horizontalCollision && this.isOffsetPositionInLiquid(this.xd, this.yd + 0.6f - this.y + f3, this.zd)) {
                this.yd = 0.3f;
            }
        } else if (this.handleLavaMovement()) {
            f3 = this.y;
            this.moveFlying(f4, f5, 0.02f);
            this.moveEntity(this.xd, this.yd, this.zd);
            this.xd *= 0.5f;
            this.yd *= 0.5f;
            this.zd *= 0.5f;
            this.yd = (float)((double)this.yd - 0.02);
            if (this.horizontalCollision && this.isOffsetPositionInLiquid(this.xd, this.yd + 0.6f - this.y + f3, this.zd)) {
                this.yd = 0.3f;
            }
        } else {
            this.moveFlying(f4, f5, this.onGround ? 0.1f : 0.02f);
            this.moveEntity(this.xd, this.yd, this.zd);
            this.xd *= 0.91f;
            this.yd *= 0.98f;
            this.zd *= 0.91f;
            this.yd = (float)((double)this.yd - 0.08);
            if (this.onGround) {
                this.xd *= 0.6f;
                this.zd *= 0.6f;
            }
        }
        this.walkAnimSpeedO = this.walkAnimSpeed;
        f3 = this.x - this.xo;
        float f9 = this.z - this.zo;
        f9 = Mth.sqrt(f3 * f3 + f9 * f9) * 4.0f;
        if (f9 > 1.0f) {
            f9 = 1.0f;
        }
        this.walkAnimSpeed += (f9 - this.walkAnimSpeed) * 0.4f;
        this.walkAnimPos += this.walkAnimSpeed;
        List<Entity> list = this.level.getEntitiesWithinAABBExcludingEntity(this, this.bb.expand(0.2f, 0.0f, 0.2f));
        if (list != null && list.size() > 0) {
            for (int i2 = 0; i2 < list.size(); ++i2) {
                Entity entity = list.get(i2);
                if (!entity.canBePushed()) continue;
                entity.applyEntityCollision(this);
            }
        }
    }

    protected void updateState() {
        if (this.onlineEntity) {
            return;
        }
        if (this.rand.nextFloat() < 0.07f) {
            this.moveStrafing = (this.rand.nextFloat() - 0.5f) * this.moveSpeed;
            this.moveForward = this.rand.nextFloat() * this.moveSpeed;
        }
        boolean bl = this.isJumping = this.rand.nextFloat() < 0.01f;
        if (this.rand.nextFloat() < 0.04f) {
            this.randomYawVelocity = (this.rand.nextFloat() - 0.5f) * 60.0f;
        }
        this.xRot += this.randomYawVelocity;
        this.yRot = 0.0f;
        boolean bl2 = this.inWater();
        boolean bl3 = this.handleLavaMovement();
        if (bl2 || bl3) {
            this.isJumping = this.rand.nextFloat() < 0.8f;
        }
    }

    public boolean getCanSpawnHere(float f2, float f3, float f4) {
        this.setPos(f2, f3 + this.bbHeight / 2.0f, f4);
        return this.level.checkIfAABBIsClear1(this.bb) && this.level.getCollidingBoundingBoxes(this, this.bb).size() == 0 && !this.level.getIsAnyLiquid(this.bb);
    }

    public float getSwingProgress(float f2) {
        float f3 = this.swingProgress - this.prevSwingProgress;
        if (f3 < 0.0f) {
            f3 += 1.0f;
        }
        return this.prevSwingProgress + f3 * f2;
    }

    @Override
    public void lerpTo(float f2, float f3, float f4, float f5, float f6, int n2) {
        this.lx = f2;
        this.ly = f3 + this.bbHeight / 2.0f;
        this.lz = f4;
        this.lxr = f5;
        this.lyr = f6;
        this.lSteps = n2;
    }

    protected void actuallyHurt(int n2) {
        this.health -= n2;
    }

    @Override
    public void handleHealthUpdate(byte by, float f2, float f3) {
        if (by == 2) {
            this.walkAnimSpeed = 1.5f;
            this.invulnerableTime = this.invulnerableDuration;
            this.hurtDuration = 10;
            this.hurtTime = 10;
            this.hurtDir = 0.0f;
            this.level.playSoundAtEntity(this, this.getHurtSound(), 1.0f, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2f + 1.0f);
            this.hurt(null, 0);
            if (f2 != -1.0f && f3 != -1.0f) {
                this.knockback(f2, f3);
            }
        } else if (by == 3) {
            this.walkAnimSpeed = 1.5f;
            this.invulnerableTime = this.invulnerableDuration;
            this.hurtDuration = 10;
            this.hurtTime = 10;
            this.hurtDir = 0.0f;
            this.level.playSoundAtEntity(this, this.getDeathSound(), 1.0f, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2f + 1.0f);
            this.health = 0;
        } else {
            super.handleHealthUpdate(by, f2, f3);
        }
    }

    public boolean canEntityBeSeen(Entity entity) {
        return this.level.rayTraceBlocks(new Vec3(this.x, this.y + this.getEyeHeight(), this.z), new Vec3(entity.x, entity.y + entity.getEyeHeight(), entity.z)) == null;
    }

    public ItemStack getHeldItem() {
        return null;
    }
}

