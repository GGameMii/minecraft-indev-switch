/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.entity;

import com.mojang.nbt.NBTTagCompound;
import com.mojang.nbt.NBTTagFloat;
import com.mojang.nbt.NBTTagList;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.data.DataWatcher;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.tile.StepSound;
import net.minecraft.world.level.tile.Tile;
import net.minecraft.world.phys.AABB;
import util.Mth;

public abstract class Entity {
    public static final byte STATUS_HURT = 2;
    public static final byte STATUS_DEAD = 3;
    public float renderDistance = 64.0f;
    private static int nextEntityID = 0;
    public int entityID = -1;
    public boolean collision = false;
    public Level level;
    public float xo;
    public float yo;
    public float zo;
    public float x;
    public float y;
    public float z;
    public float xd;
    public float yd;
    public float zd;
    public float xRot;
    public float yRot;
    public float xRotI;
    public float yRotI;
    public AABB bb;
    public boolean onGround = false;
    public boolean horizontalCollision = false;
    private boolean surfaceCollision = true;
    public boolean removed = false;
    public float heightOffset = 0.0f;
    public float bbWidth = 0.6f;
    public float bbHeight = 1.8f;
    public float prevDistanceWalkedModified = 0.0f;
    public float distanceWalkedModified = 0.0f;
    protected boolean canTriggerWalking = true;
    protected float fallDistance = 0.0f;
    protected int nextStepDistance = 1;
    public float lastTickX;
    public float lastTickY;
    public float lastTickZ;
    public float ySlideOffset = 0.0f;
    public float footSize = 0.0f;
    public boolean noPhysics = false;
    private float pushthrough = 0.0f;
    protected Random rand = new Random();
    public int ticks = 0;
    public int fireResistance = 1;
    public int fire = 0;
    protected int maxAir = 300;
    private boolean inWater = false;
    public int invulnerableTime = 0;
    public int air = 300;
    private boolean isFirstUpdate = true;
    public String skinUrl;
    public String cloakUrl;
    public int serverPosX;
    public int serverPosY;
    public int serverPosZ;
    public int encodedX;
    public int encodedY;
    public int encodedZ;
    public int encodedxRot;
    public int encodedyRot;
    public boolean beenAttacked = false;
    protected DataWatcher dataWatcher = new DataWatcher();
    public float onlineOffset = 0.0f;
    public boolean speaking;

    public Entity(Level level) {
        if (level != null && !level.onlineLevel) {
            this.entityID = nextEntityID++;
        }
        this.level = level;
        this.setPos(0.0f, 0.0f, 0.0f);
        this.dataWatcher.addObject(0, (byte)0);
        this.entityInit();
    }

    protected abstract void entityInit();

    public DataWatcher getDataWatcher() {
        return this.dataWatcher;
    }

    public void updateCloak() {
    }

    protected void preparePlayerToSpawn() {
        if (this.level != null) {
            float f2 = (float)this.level.xSpawn + 0.5f;
            float f3 = (float)this.level.zSpawn + 0.5f;
            for (float f4 = (float)this.level.ySpawn; f4 > 0.0f; f4 += 1.0f) {
                this.setPos(f2, f4, f3);
                if (this.level.getCollidingBoundingBoxes(this, this.bb).size() == 0) break;
            }
            this.zd = 0.0f;
            this.yd = 0.0f;
            this.xd = 0.0f;
            this.xRot = this.level.rotSpawn;
            this.yRot = 0.0f;
        }
    }

    public void remove() {
        this.removed = true;
    }

    protected void setSize(float f2, float f3) {
        this.bbWidth = f2;
        this.bbHeight = f3;
    }

    public final void setPos(float f2, float f3, float f4) {
        this.x = f2;
        this.y = f3;
        this.z = f4;
        float f5 = this.bbWidth / 2.0f;
        float f6 = this.bbHeight / 2.0f;
        this.bb = new AABB(f2 - f5, f3 - f6, f4 - f5, f2 + f5, f3 + f6, f4 + f5);
    }

    public void setRot(float f2, float f3) {
        this.xRot = f2;
        this.yRot = f3;
    }

    public void interpolateTurn(float f2, float f3) {
        float f4 = this.yRot;
        float f5 = this.xRot;
        this.xRot = (float)((double)this.xRot + (double)f2 * 0.15);
        this.yRot = (float)((double)this.yRot - (double)f3 * 0.15);
        if (this.yRot < -90.0f) {
            this.yRot = -90.0f;
        }
        if (this.yRot > 90.0f) {
            this.yRot = 90.0f;
        }
        this.yRotI += this.yRot - f4;
        this.xRotI += this.xRot - f5;
    }

    public void lerpTo(float f2, float f3, float f4, float f5, float f6, int n2) {
        this.setPos(f2, f3, f4);
        this.setRot(f5, f6);
    }

    public void tick() {
        float f2;
        ++this.ticks;
        if (this.ticks % 20 == 0) {
            this.speaking = false;
        }
        this.prevDistanceWalkedModified = this.distanceWalkedModified;
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        this.yRotI = this.yRot;
        this.xRotI = this.xRot;
        if (this.inWater()) {
            if (!this.inWater && !this.isFirstUpdate) {
                float f3;
                float f4;
                f2 = Mth.sqrt(this.xd * this.xd * 0.2f + this.yd * this.yd + this.zd * this.zd * 0.2f) * 0.2f;
                if (f2 > 1.0f) {
                    f2 = 1.0f;
                }
                this.level.playSoundAtEntity(this, "random.splash", f2, 1.0f + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4f);
                f2 = (int)this.bb.y0;
                int n2 = 0;
                while ((float)n2 < 1.0f + this.bbWidth * 20.0f) {
                    f4 = (this.rand.nextFloat() * 2.0f - 1.0f) * this.bbWidth;
                    f3 = (this.rand.nextFloat() * 2.0f - 1.0f) * this.bbWidth;
                    this.level.spawnParticle("bubble", this.x + f4, f2 + 1.0f, this.z + f3, this.xd, this.yd - this.rand.nextFloat() * 0.2f, this.zd);
                    ++n2;
                }
                n2 = 0;
                while ((float)n2 < 1.0f + this.bbWidth * 20.0f) {
                    f4 = (this.rand.nextFloat() * 2.0f - 1.0f) * this.bbWidth;
                    f3 = (this.rand.nextFloat() * 2.0f - 1.0f) * this.bbWidth;
                    this.level.spawnParticle("splash", this.x + f4, f2 + 1.0f, this.z + f3, this.xd, this.yd, this.zd);
                    ++n2;
                }
            }
            this.fallDistance = 0.0f;
            this.inWater = true;
            this.fire = 0;
        } else {
            this.inWater = false;
        }
        if (this.fire > 0) {
            if (this.fire % 20 == 0) {
                this.hurt(null, 1);
            }
            --this.fire;
        }
        if (this.handleLavaMovement()) {
            this.hurt(null, 10);
            this.fire = 600;
        }
        if (this.x < -8.0f) {
            f2 = -(this.x + 8.0f);
            this.xd += f2 * 0.001f;
        }
        if (this.z < -8.0f) {
            f2 = -(this.z + 8.0f);
            this.zd += f2 * 0.001f;
        }
        if (this.x > (float)this.level.width + 8.0f) {
            f2 = this.x - (float)this.level.width + 8.0f;
            this.xd -= f2 * 0.001f;
        }
        if (this.z > (float)this.level.depth + 8.0f) {
            f2 = this.z - (float)this.level.depth + 8.0f;
            this.zd -= f2 * 0.001f;
        }
        this.isFirstUpdate = false;
    }

    public final boolean isOffsetPositionInLiquid(float f2, float f3, float f4) {
        float f5 = f4;
        f4 = f3;
        f3 = f2;
        AABB aABB = this.bb;
        aABB = new AABB(aABB.x0 + f5, aABB.y0 + f4, aABB.z0 + f5, aABB.x1 + f3, aABB.y1 + f4, aABB.z1 + f5);
        return this.level.getCollidingBoundingBoxes(this, aABB).size() > 0 ? false : !this.level.getIsAnyLiquid(aABB);
    }

    public final void moveEntity(float f2, float f3, float f4) {
        if (this.noPhysics) {
            this.bb.offset(f2, f3, f4);
            this.x = (this.bb.x0 + this.bb.x1) / 2.0f;
            this.y = this.bb.y0 + this.heightOffset - this.ySlideOffset;
            this.z = (this.bb.z0 + this.bb.z1) / 2.0f;
        } else {
            int n2;
            float f5;
            int n3;
            int n4;
            float f6 = this.x;
            float f7 = this.z;
            float f8 = f2;
            float f9 = f3;
            float f10 = f4;
            AABB aABB = this.bb.copy();
            ArrayList<AABB> arrayList = this.level.getCollidingBoundingBoxes(this, this.bb.addCoord(f2, f3, f4));
            for (n4 = 0; n4 < arrayList.size(); ++n4) {
                f3 = arrayList.get(n4).calculateYOffset(this.bb, f3);
            }
            this.bb.offset(0.0f, f3, 0.0f);
            if (!this.surfaceCollision && f9 != f3) {
                f4 = 0.0f;
                f3 = 0.0f;
                f2 = 0.0f;
            }
            n4 = this.onGround || f9 != f3 && f9 < 0.0f ? 1 : 0;
            for (n3 = 0; n3 < arrayList.size(); ++n3) {
                f2 = arrayList.get(n3).calculateXOffset(this.bb, f2);
            }
            this.bb.offset(f2, 0.0f, 0.0f);
            if (!this.surfaceCollision && f8 != f2) {
                f4 = 0.0f;
                f3 = 0.0f;
                f2 = 0.0f;
            }
            for (n3 = 0; n3 < arrayList.size(); ++n3) {
                f4 = arrayList.get(n3).calculateZOffset(this.bb, f4);
            }
            this.bb.offset(0.0f, 0.0f, f4);
            if (!this.surfaceCollision && f10 != f4) {
                f4 = 0.0f;
                f3 = 0.0f;
                f2 = 0.0f;
            }
            if (this.footSize > 0.0f && n4 != 0 && this.ySlideOffset < 0.05f && (f8 != f2 || f10 != f4)) {
                int n5;
                float f11 = f2;
                float f12 = f3;
                f5 = f4;
                f2 = f8;
                f3 = this.footSize;
                f4 = f10;
                AABB aABB2 = this.bb.copy();
                this.bb = aABB.copy();
                arrayList = this.level.getCollidingBoundingBoxes(this, this.bb.addCoord(f8, f3, f10));
                for (n5 = 0; n5 < arrayList.size(); ++n5) {
                    f3 = arrayList.get(n5).calculateYOffset(this.bb, f3);
                }
                this.bb.offset(0.0f, f3, 0.0f);
                if (!this.surfaceCollision && f9 != f3) {
                    f4 = 0.0f;
                    f3 = 0.0f;
                    f2 = 0.0f;
                }
                for (n5 = 0; n5 < arrayList.size(); ++n5) {
                    f2 = arrayList.get(n5).calculateXOffset(this.bb, f2);
                }
                this.bb.offset(f2, 0.0f, 0.0f);
                if (!this.surfaceCollision && f8 != f2) {
                    f4 = 0.0f;
                    f3 = 0.0f;
                    f2 = 0.0f;
                }
                for (n5 = 0; n5 < arrayList.size(); ++n5) {
                    f4 = arrayList.get(n5).calculateZOffset(this.bb, f4);
                }
                this.bb.offset(0.0f, 0.0f, f4);
                if (!this.surfaceCollision && f10 != f4) {
                    f4 = 0.0f;
                    f3 = 0.0f;
                    f2 = 0.0f;
                }
                if (f11 * f11 + f5 * f5 >= f2 * f2 + f4 * f4) {
                    f2 = f11;
                    f3 = f12;
                    f4 = f5;
                    this.bb = aABB2.copy();
                } else {
                    this.ySlideOffset = (float)((double)this.ySlideOffset + 0.5);
                }
            }
            this.x = (this.bb.x0 + this.bb.x1) / 2.0f;
            this.y = this.bb.y0 + this.heightOffset - this.ySlideOffset;
            this.z = (this.bb.z0 + this.bb.z1) / 2.0f;
            this.horizontalCollision = f8 != f2 || f10 != f4;
            this.onGround = f9 != f3 && f9 < 0.0f;
            this.updateFallState(f3, this.onGround);
            if (f8 != f2) {
                this.xd = 0.0f;
            }
            if (f9 != f3) {
                this.yd = 0.0f;
            }
            if (f10 != f4) {
                this.zd = 0.0f;
            }
            f5 = this.x - f6;
            float f13 = this.z - f7;
            this.distanceWalkedModified = (float)((double)this.distanceWalkedModified + (double)Mth.sqrt(f5 * f5 + f13 * f13) * 0.6);
            if (this.canTriggerWalking) {
                n2 = (int)this.x;
                int n6 = (int)(this.y - 0.2f - this.heightOffset);
                int n7 = (int)this.z;
                int n8 = this.level.getTile(n2, n6, n7);
                if (this.distanceWalkedModified > (float)this.nextStepDistance && n8 > 0) {
                    ++this.nextStepDistance;
                    StepSound stepSound = Tile.tiles[n8].stepSound;
                    if (!Tile.tiles[n8].material.getIsLiquid()) {
                        this.level.playSoundAtEntity(this, stepSound.getSndName(), stepSound.soundVolume * 0.15f, stepSound.soundPitch);
                    }
                    Tile.tiles[n8].onEntityWalking(this.level, n2, n6, n7);
                }
            }
            this.ySlideOffset *= 0.4f;
            n2 = this.inWater() ? 1 : 0;
            if (this.level.isBoundingBoxBurning(this.bb)) {
                this.dealFireDamage(1);
                if (n2 == 0) {
                    ++this.fire;
                    if (this.fire == 0) {
                        this.fire = 300;
                    }
                }
            } else if (this.fire <= 0) {
                this.fire = -this.fireResistance;
            }
            if (n2 != 0 && this.fire > 0) {
                this.level.playSoundAtEntity(this, "random.fizz", 0.7f, 1.6f + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4f);
                this.fire = -this.fireResistance;
            }
        }
    }

    protected void dealFireDamage(int n2) {
        this.hurt(null, n2);
    }

    protected void fall(float f2) {
    }

    protected void updateFallState(float f2, boolean bl) {
        if (bl) {
            if (this.fallDistance > 0.0f) {
                this.fall(this.fallDistance);
                this.fallDistance = 0.0f;
            }
        } else if (f2 < 0.0f) {
            this.fallDistance -= f2;
        }
    }

    public final boolean inWater() {
        return this.level.handleMaterialAcceleration(this.bb.expand(0.0f, -0.4f, 0.0f), Material.water);
    }

    public final boolean isInsideOfWater() {
        int n2 = this.level.getTile((int)this.x, (int)(this.y + this.getEyeHeight()), (int)this.z);
        return n2 != 0 ? Tile.tiles[n2].material == Material.water : false;
    }

    protected float getEyeHeight() {
        return 0.0f;
    }

    public final boolean handleLavaMovement() {
        return this.level.handleMaterialAcceleration(this.bb.expand(0.0f, -0.4f, 0.0f), Material.lava);
    }

    public final void moveFlying(float f2, float f3, float f4) {
        float f6 = Mth.sqrt(f2 * f2 + f3 * f3);
        if (f6 >= 0.01f) {
            if (f6 < 1.0f) {
                f6 = 1.0f;
            }
            f6 = f4 / f6;
            f2 *= f6;
            f3 *= f6;
            f4 = Mth.sin(this.xRot * (float)Math.PI / 180.0f);
            f6 = Mth.cos(this.xRot * (float)Math.PI / 180.0f);
            this.xd += f2 * f6 - f3 * f4;
            this.zd += f3 * f6 + f2 * f4;
        }
    }

    public float getEntityBrightness(float f2) {
        int n2 = (int)this.x;
        int n3 = (int)(this.y + this.heightOffset / 2.0f);
        int n4 = (int)this.z;
        return this.level.getLightBrightness(n2, n3, n4);
    }

    public final void setWorld(Level level) {
        this.level = level;
    }

    public void absMoveTo(float f2, float f3, float f4, float f5, float f6) {
        this.xo = this.x = f2;
        this.yo = this.y = f3;
        this.zo = this.z = f4;
        this.xRotI = this.xRot = f5;
        this.yRotI = this.yRot = f6;
        this.ySlideOffset = 0.0f;
        float f7 = this.xRotI - f5;
        if (f7 < -180.0f) {
            this.xRotI += 360.0f;
        }
        if (f7 >= 180.0f) {
            this.xRotI -= 360.0f;
        }
        this.setPos(this.x, this.y, this.z);
        this.setRot(f5, f6);
    }

    public void moveTo(float f2, float f3, float f4, float f5, float f6) {
        this.xo = this.x = f2;
        this.lastTickX = this.x;
        this.yo = this.y = f3 + this.heightOffset;
        this.lastTickY = this.y;
        this.zo = this.z = f4;
        this.lastTickZ = this.z;
        this.yRot = f5;
        this.xRot = f6;
        this.setPos(this.x, this.y, this.z);
    }

    public float getDistance(float f2, float f3, float f4) {
        float f5 = this.x - f2;
        float f6 = this.y - f3;
        float f7 = this.z - f4;
        return Mth.sqrt(f5 * f5 + f6 * f6 + f7 * f7);
    }

    public float getDistanceSq(float f2, float f3, float f4) {
        float f5 = this.x - f2;
        float f6 = this.y - f3;
        float f7 = this.z - f4;
        return f5 * f5 + f6 * f6 + f7 * f7;
    }

    public float getDistanceSqToEntity(Entity entity) {
        float f2 = this.x - entity.x;
        float f3 = this.y - entity.y;
        float f4 = this.z - entity.z;
        return f2 * f2 + f3 * f3 + f4 * f4;
    }

    public float getDistanceToEntity(Entity entity) {
        float f2 = this.x - entity.x;
        float f3 = this.y - entity.y;
        float f4 = this.z - entity.z;
        return Mth.sqrt(f2 * f2 + f3 * f3 + f4 * f4);
    }

    public void onCollideWithPlayer(Player player) {
    }

    public final void applyEntityCollision(Entity entity) {
        float f2 = entity.x - this.x;
        float f3 = entity.z - this.z;
        float f4 = f2 * f2 + f3 * f3;
        if (f4 >= 0.01f) {
            f4 = Mth.sqrt(f4);
            f2 /= f4;
            f3 /= f4;
            f2 /= f4;
            f3 /= f4;
            this.addVelocity(-(f2 *= 0.05f - this.pushthrough), 0.0f, -(f3 *= 0.05f - this.pushthrough));
            entity.addVelocity(f2, 0.0f, f3);
        }
    }

    private void addVelocity(float f2, float f3, float f4) {
        this.xd += f2;
        this.yd += f3;
        this.zd += f4;
    }

    public void setVelocity(float f2, float f3, float f4) {
        this.xd = f2;
        this.yd = f3;
        this.zd = f4;
    }

    public boolean hurt(Entity entity, int n2) {
        this.setBeenAttacked();
        return false;
    }

    protected void setBeenAttacked() {
        this.beenAttacked = true;
    }

    public boolean canBeCollidedWith() {
        return false;
    }

    public boolean canBePushed() {
        return false;
    }

    public String getTexture() {
        return null;
    }

    public final void writeToNBT(NBTTagCompound nBTTagCompound) {
        String string = this.getEntityString();
        if (!this.removed && string != null) {
            nBTTagCompound.setString("id", string);
            nBTTagCompound.setTag("Pos", Entity.newDoubleNBTList(this.x, this.y, this.z));
            nBTTagCompound.setTag("Motion", Entity.newDoubleNBTList(this.xd, this.yd, this.zd));
            nBTTagCompound.setTag("Rotation", Entity.newDoubleNBTList(this.xRot, this.yRot));
            nBTTagCompound.setFloat("FallDistance", this.fallDistance);
            nBTTagCompound.setShort("Fire", (short)this.fire);
            nBTTagCompound.setShort("Air", (short)this.air);
            this.writeEntityToNBT(nBTTagCompound);
        }
    }

    public final void readFromNBT(NBTTagCompound nBTTagCompound) {
        NBTTagList nBTTagList = nBTTagCompound.getTagList("Pos");
        NBTTagList nBTTagList2 = nBTTagCompound.getTagList("Motion");
        NBTTagList nBTTagList3 = nBTTagCompound.getTagList("Rotation");
        this.x = ((NBTTagFloat)nBTTagList.tagAt((int)0)).floatValue;
        this.y = ((NBTTagFloat)nBTTagList.tagAt((int)1)).floatValue;
        this.z = ((NBTTagFloat)nBTTagList.tagAt((int)2)).floatValue;
        this.xd = ((NBTTagFloat)nBTTagList2.tagAt((int)0)).floatValue;
        this.yd = ((NBTTagFloat)nBTTagList2.tagAt((int)1)).floatValue;
        this.zd = ((NBTTagFloat)nBTTagList2.tagAt((int)2)).floatValue;
        this.xRot = ((NBTTagFloat)nBTTagList3.tagAt((int)0)).floatValue;
        this.yRot = ((NBTTagFloat)nBTTagList3.tagAt((int)1)).floatValue;
        this.fallDistance = nBTTagCompound.getFloat("FallDistance");
        this.fire = nBTTagCompound.getShort("Fire");
        this.air = nBTTagCompound.getShort("Air");
        this.absMoveTo(this.x, this.y, this.z, this.xRot, this.yRot);
        this.readEntityFromNBT(nBTTagCompound);
    }

    protected abstract String getEntityString();

    protected abstract void readEntityFromNBT(NBTTagCompound var1);

    protected abstract void writeEntityToNBT(NBTTagCompound var1);

    private static NBTTagList newDoubleNBTList(float ... fArray) {
        NBTTagList nBTTagList = new NBTTagList();
        for (float f2 : fArray) {
            nBTTagList.setTag(new NBTTagFloat(f2));
        }
        return nBTTagList;
    }

    public float getShadowSize() {
        return this.bbHeight / 2.0f;
    }

    public final ItemEntity dropItemWithOffset(int n2, int n3) {
        return this.entityDropItem(n2, 1, 0.0f);
    }

    public final ItemEntity entityDropItem(int n2, int n3, float f2) {
        ItemEntity itemEntity = new ItemEntity(this.level, this.x, this.y + f2, this.z, new ItemStack(n2, n3));
        new ItemEntity(this.level, this.x, this.y + f2, this.z, new ItemStack(n2, n3)).delayBeforeCanPickup = 10;
        this.level.spawnEntityInWorld(itemEntity);
        return itemEntity;
    }

    public boolean isEntityAlive() {
        return !this.removed;
    }

    public AABB getCollisionBox(Entity entity) {
        return null;
    }

    public void handleHealthUpdate(byte by, float f2, float f3) {
    }

    public void outfitWithItem(int n2, int n3, int n4) {
    }
}

