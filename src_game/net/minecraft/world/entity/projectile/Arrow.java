/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.entity.projectile;

import com.mojang.nbt.NBTTagCompound;
import java.util.List;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import util.Mth;

public class Arrow
extends Entity {
    private int xTile = -1;
    private int yTile = -1;
    private int zTile = -1;
    private int inTile = 0;
    private boolean inGround = false;
    public int shake = 0;
    public LivingEntity owner;
    private int ticksInGround;
    private int ticksInAir = 0;
    protected int lSteps;
    protected float lx;
    protected float ly;
    protected float lz;
    protected float lxr;
    protected float lyr;

    public Arrow(Level level, LivingEntity livingEntity) {
        this(level);
        this.owner = livingEntity;
        this.setSize(0.5f, 0.5f);
        this.absMoveTo(livingEntity.x, livingEntity.y, livingEntity.z, livingEntity.xRot, livingEntity.yRot);
        this.x -= Mth.cos(this.xRot / 180.0f * (float)Math.PI) * 0.16f;
        this.y -= 0.1f;
        this.z -= Mth.sin(this.xRot / 180.0f * (float)Math.PI) * 0.16f;
        this.setPos(this.x, this.y, this.z);
        this.heightOffset = 0.0f;
        this.xd = -Mth.sin(this.xRot / 180.0f * (float)Math.PI) * Mth.cos(this.yRot / 180.0f * (float)Math.PI);
        this.zd = Mth.cos(this.xRot / 180.0f * (float)Math.PI) * Mth.cos(this.yRot / 180.0f * (float)Math.PI);
        this.yd = -Mth.sin(this.yRot / 180.0f * (float)Math.PI);
        this.setArrowHeading(this.xd, this.yd, this.zd, 1.5f, 1.0f);
    }

    public Arrow(Level level) {
        super(level);
    }

    public Arrow(Level level, float f2, float f3, float f4) {
        this(level);
        this.setPos(f2, f3, f4);
    }

    @Override
    protected void entityInit() {
    }

    public final void setArrowHeading(float f2, float f3, float f4, float f5, float f6) {
        float f7 = Mth.sqrt(f2 * f2 + f3 * f3 + f4 * f4);
        f2 /= f7;
        f3 /= f7;
        f4 /= f7;
        f2 = (float)((double)f2 + this.rand.nextGaussian() * (double)0.0075f * (double)f6);
        f3 = (float)((double)f3 + this.rand.nextGaussian() * (double)0.0075f * (double)f6);
        f4 = (float)((double)f4 + this.rand.nextGaussian() * (double)0.0075f * (double)f6);
        this.xd = f2 *= f5;
        this.yd = f3 *= f5;
        this.zd = f4 *= f5;
        float f8 = Mth.sqrt(f2 * f2 + f4 * f4);
        this.xRotI = this.xRot = (float)(Math.atan2(f2, f4) * 180.0 / 3.1415927410125732);
        this.yRotI = this.yRot = (float)(Math.atan2(f3, f8) * 180.0 / 3.1415927410125732);
        this.ticksInGround = 0;
    }

    @Override
    public final void tick() {
        super.tick();
        if (!this.level.onlineLevel) {
            if (this.shake > 0) {
                --this.shake;
            }
            if (this.inGround) {
                if (this.level.getTile(this.xTile, this.yTile, this.zTile) == this.inTile) {
                    ++this.ticksInGround;
                    if (this.ticksInGround == 1200 && !this.level.onlineLevel) {
                        this.remove();
                    }
                    return;
                }
                this.inGround = false;
                this.xd *= this.rand.nextFloat() * 0.2f;
                this.yd *= this.rand.nextFloat() * 0.2f;
                this.zd *= this.rand.nextFloat() * 0.2f;
                this.ticksInGround = 0;
                this.ticksInAir = 0;
            } else {
                ++this.ticksInAir;
            }
            Vec3 vec3 = new Vec3(this.x, this.y, this.z);
            Vec3 vec32 = new Vec3(this.x + this.xd, this.y + this.yd, this.z + this.zd);
            HitResult hitResult = this.level.rayTraceBlocks(vec3, vec32);
            vec3 = new Vec3(this.x, this.y, this.z);
            vec32 = new Vec3(this.x + this.xd, this.y + this.yd, this.z + this.zd);
            if (hitResult != null) {
                vec32 = new Vec3(hitResult.hitVec.x, hitResult.hitVec.y, hitResult.hitVec.z);
            }
            Entity entity = null;
            List<Entity> list = this.level.entityMap.getEntitiesWithinAABB(this, this.bb.addCoord(this.xd, this.yd, this.zd).expand(1.0f, 1.0f, 1.0f));
            float f2 = 0.0f;
            for (int i2 = 0; i2 < list.size(); ++i2) {
                float f3;
                HitResult hitResult2;
                Entity entity2 = list.get(i2);
                if (!entity2.canBeCollidedWith() || entity2 == this.owner && this.ticksInAir < 5 || (hitResult2 = entity2.bb.expand(0.3f, 0.3f, 0.3f).calculateIntercept(vec3, vec32)) == null || !((f3 = vec3.distance(hitResult2.hitVec)) < f2) && f2 != 0.0f) continue;
                entity = entity2;
                f2 = f3;
            }
            if (entity != null) {
                hitResult = new HitResult(entity);
            }
            if (hitResult != null) {
                if (hitResult.entityHit != null) {
                    if (hitResult.entityHit.hurt(this, 4)) {
                        this.level.playSoundAtEntity(this, "random.drr", 1.0f, 1.2f / (this.rand.nextFloat() * 0.2f + 0.9f));
                        if (!this.level.onlineLevel) {
                            this.remove();
                        }
                    } else {
                        this.xd *= -0.1f;
                        this.yd *= -0.1f;
                        this.zd *= -0.1f;
                        this.xRot += 180.0f;
                        this.xRotI += 180.0f;
                        this.ticksInAir = 0;
                    }
                } else {
                    this.xTile = hitResult.x;
                    this.yTile = hitResult.y;
                    this.zTile = hitResult.z;
                    this.inTile = this.level.getTile(this.xTile, this.yTile, this.zTile);
                    this.xd = hitResult.hitVec.x - this.x;
                    this.yd = hitResult.hitVec.y - this.y;
                    this.zd = hitResult.hitVec.z - this.z;
                    float f4 = Mth.sqrt(this.xd * this.xd + this.yd * this.yd + this.zd * this.zd);
                    this.x -= this.xd / f4 * 0.05f;
                    this.y -= this.yd / f4 * 0.05f;
                    this.z -= this.zd / f4 * 0.05f;
                    this.level.playSoundAtEntity(this, "random.drr", 1.0f, 1.2f / (this.rand.nextFloat() * 0.2f + 0.9f));
                    this.inGround = true;
                    this.shake = 7;
                }
            }
            this.x += this.xd;
            this.y += this.yd;
            this.z += this.zd;
            float f5 = Mth.sqrt(this.xd * this.xd + this.zd * this.zd);
            this.xRot = (float)(Math.atan2(this.xd, this.zd) * 180.0 / 3.1415927410125732);
            this.yRot = (float)(Math.atan2(this.yd, f5) * 180.0 / 3.1415927410125732);
            while (this.yRot - this.yRotI < -180.0f) {
                this.yRotI -= 360.0f;
            }
            while (this.yRot - this.yRotI >= 180.0f) {
                this.yRotI += 360.0f;
            }
            while (this.xRot - this.xRotI < -180.0f) {
                this.xRotI -= 360.0f;
            }
            while (this.xRot - this.xRotI >= 180.0f) {
                this.xRotI += 360.0f;
            }
            this.yRot = this.yRotI + (this.yRot - this.yRotI) * 0.2f;
            this.xRot = this.xRotI + (this.xRot - this.xRotI) * 0.2f;
            float f6 = 0.99f;
            if (this.inWater()) {
                for (int i3 = 0; i3 < 4; ++i3) {
                    this.level.spawnParticle("bubble", this.x - this.xd * 0.25f, this.y - this.yd * 0.25f, this.z - this.zd * 0.25f, this.xd, this.yd, this.zd);
                }
                f6 = 0.8f;
            }
            this.xd *= f6;
            this.yd *= f6;
            this.zd *= f6;
            this.yd -= 0.03f;
            this.setPos(this.x, this.y, this.z);
        }
        if (this.lSteps > 0) {
            float f7;
            float f8 = this.x + (this.lx - this.x) / (float)this.lSteps;
            float f9 = this.y + (this.ly - this.y) / (float)this.lSteps;
            float f10 = this.z + (this.lz - this.z) / (float)this.lSteps;
            for (f7 = this.lxr - this.xRot; f7 < -180.0f; f7 += 360.0f) {
            }
            while (f7 >= 180.0f) {
                f7 -= 360.0f;
            }
            this.xRot += f7 / (float)this.lSteps;
            this.yRot += (this.lyr - this.yRot) / (float)this.lSteps;
            --this.lSteps;
            this.setPos(f8, f9, f10);
            this.setRot(this.xRot, this.yRot);
        }
    }

    @Override
    protected final void writeEntityToNBT(NBTTagCompound nBTTagCompound) {
        nBTTagCompound.setShort("xTile", (short)this.xTile);
        nBTTagCompound.setShort("yTile", (short)this.yTile);
        nBTTagCompound.setShort("zTile", (short)this.zTile);
        nBTTagCompound.setByte("inTile", (byte)this.inTile);
        nBTTagCompound.setByte("shake", (byte)this.shake);
        nBTTagCompound.setByte("inGround", (byte)(this.inGround ? 1 : 0));
    }

    @Override
    protected final void readEntityFromNBT(NBTTagCompound nBTTagCompound) {
        this.xTile = nBTTagCompound.getShort("xTile");
        this.yTile = nBTTagCompound.getShort("yTile");
        this.zTile = nBTTagCompound.getShort("zTile");
        this.inTile = nBTTagCompound.getByte("inTile") & 0xFF;
        this.shake = nBTTagCompound.getByte("shake") & 0xFF;
        this.inGround = nBTTagCompound.getByte("inGround") == 1;
    }

    @Override
    protected final String getEntityString() {
        return "Arrow";
    }

    @Override
    public final void onCollideWithPlayer(Player player) {
        if (this.level.onlineLevel) {
            return;
        }
        if (this.inGround && this.owner == player && this.shake <= 0 && player.inventory.storePartialItemStack(new ItemStack(Item.arrow.shiftedIndex, 1))) {
            this.level.playSoundAtEntity(this, "random.pop", 0.2f, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7f + 1.0f) * 2.0f);
            player.onItemPickup(this, 1);
            this.remove();
        }
    }

    @Override
    public final float getShadowSize() {
        return 0.0f;
    }

    @Override
    public void lerpTo(float f2, float f3, float f4, float f5, float f6, int n2) {
        this.lx = f2;
        this.ly = f3;
        this.lz = f4;
        this.lxr = f5;
        this.lyr = f6;
        this.lSteps = n2;
    }
}

