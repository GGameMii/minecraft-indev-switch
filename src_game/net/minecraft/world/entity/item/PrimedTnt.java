/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.entity.item;

import com.mojang.nbt.NBTTagCompound;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import util.Mth;

public class PrimedTnt
extends Entity {
    public int fuse = 0;
    protected int lSteps;
    protected float lx;
    protected float ly;
    protected float lz;
    protected float lxr;
    protected float lyr;

    public PrimedTnt(Level world, float x2, float y2, float z2) {
        this(world);
        this.setPos(x2, y2, z2);
        float xz = (float)(Math.random() * 3.1415927410125732 * 2.0);
        this.xd = -Mth.sin(xz * (float)Math.PI / 180.0f) * 0.02f;
        this.yd = 0.2f;
        this.zd = -Mth.cos(xz * (float)Math.PI / 180.0f) * 0.02f;
        this.fuse = 80;
        this.xo = x2;
        this.yo = y2;
        this.zo = z2;
    }

    public PrimedTnt(Level level) {
        super(level);
        this.collision = true;
        this.canTriggerWalking = false;
        this.fuse = 80;
        this.setSize(0.98f, 0.98f);
        this.heightOffset = this.bbHeight / 2.0f;
    }

    @Override
    protected void entityInit() {
    }

    @Override
    public final boolean canBeCollidedWith() {
        return !this.removed;
    }

    @Override
    public final void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (!this.level.onlineLevel) {
            this.yd -= 0.04f;
            this.moveEntity(this.xd, this.yd, this.zd);
        }
        this.xd *= 0.98f;
        this.yd *= 0.98f;
        this.zd *= 0.98f;
        if (this.onGround) {
            this.xd *= 0.7f;
            this.zd *= 0.7f;
            this.yd *= -0.5f;
        }
        if (this.lSteps > 0) {
            float xx = this.x + (this.lx - this.x) / (float)this.lSteps;
            float yy = this.y + (this.ly - this.y) / (float)this.lSteps;
            float zz = this.z + (this.lz - this.z) / (float)this.lSteps;
            float rot = this.lxr - this.xRot;
            while (rot < -180.0f) {
                rot += 360.0f;
            }
            while (rot >= 180.0f) {
                rot -= 360.0f;
            }
            this.xRot += rot / (float)this.lSteps;
            this.yRot += (this.lyr - this.yRot) / (float)this.lSteps;
            --this.lSteps;
            this.setPos(xx, yy, zz);
            this.setRot(this.xRot, this.yRot);
        }
        if (this.fuse-- <= 0) {
            if (!this.level.onlineLevel) {
                this.remove();
                this.level.createExplosion(null, this.x, this.y, this.z, 4.0f);
            }
        } else {
            this.level.spawnParticle("smoke", this.x, this.y + 0.5f, this.z, 0.0f, 0.0f, 0.0f);
        }
    }

    @Override
    protected final void writeEntityToNBT(NBTTagCompound nbt) {
        nbt.setByte("Fuse", (byte)this.fuse);
    }

    @Override
    protected final void readEntityFromNBT(NBTTagCompound nbt) {
        this.fuse = nbt.getByte("Fuse");
    }

    @Override
    protected final String getEntityString() {
        return "PrimedTnt";
    }

    @Override
    public final float getShadowSize() {
        return 0.0f;
    }

    @Override
    public void lerpTo(float x2, float y2, float z2, float xr, float yr, int steps) {
        this.lx = x2;
        this.ly = y2 - 0.0f;
        this.lz = z2;
        this.lxr = xr;
        this.lyr = yr;
        this.lSteps = steps;
    }
}

