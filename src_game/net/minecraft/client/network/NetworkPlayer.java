/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.network;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.tile.StepSound;
import net.minecraft.world.level.tile.Tile;
import util.Mth;

public class NetworkPlayer
extends Player {
    private int lSteps;
    private float lx;
    private float ly;
    private float lz;
    private float lxr;
    private float lyr;

    public NetworkPlayer(Level level, String username) {
        super(level);
        this.username = username;
        if (!username.isEmpty()) {
            this.skinUrl = "http://www.minecraft.net/skin/" + username + ".png";
        }
        this.noPhysics = true;
        this.renderDistance = 256.0f;
    }

    @Override
    public boolean hurt(Entity source, int dmg) {
        return true;
    }

    @Override
    public void lerpTo(float x2, float y2, float z2, float xRot, float yRot, int steps) {
        this.lx = x2;
        this.ly = y2 - 0.625f;
        this.lz = z2;
        this.lxr = xRot;
        this.lyr = yRot;
        this.lSteps = steps;
    }

    @Override
    public void tick() {
        float rr;
        super.tick();
        if (this.lSteps > 0) {
            float xt = this.x + (this.lx - this.x) / (float)this.lSteps;
            float yt = this.y + (this.ly - this.y) / (float)this.lSteps;
            float zt = this.z + (this.lz - this.z) / (float)this.lSteps;
            float xrd = this.lxr - this.xRot;
            while (xrd < -180.0f) {
                xrd += 360.0f;
            }
            while (xrd >= 180.0f) {
                xrd -= 360.0f;
            }
            this.xRot += xrd / (float)this.lSteps;
            this.yRot += (this.lyr - this.yRot) / (float)this.lSteps;
            --this.lSteps;
            this.setPos(xt, yt, zt);
            this.setRot(this.xRot, this.yRot);
        }
        this.walkAnimSpeedO = this.walkAnimSpeed;
        float xd = this.x - this.xo;
        float zd = this.z - this.zo;
        this.distanceWalkedModified = (float)((double)this.distanceWalkedModified + (double)Mth.sqrt(xd * xd + zd * zd) * 0.6);
        if (this.canTriggerWalking) {
            int xx = (int)this.x;
            int yy = (int)(this.y - 0.2f - this.heightOffset);
            int zz = (int)this.z;
            int id = this.level.getTile(xx, yy, zz);
            if (this.distanceWalkedModified > (float)this.nextStepDistance && id > 0) {
                ++this.nextStepDistance;
                StepSound snd = Tile.tiles[id].stepSound;
                if (!Tile.tiles[id].material.getIsLiquid()) {
                    this.level.playSoundAtEntity(this, snd.getSndName(), snd.soundVolume * 0.15f, snd.soundPitch);
                }
                Tile.tiles[id].onEntityWalking(this.level, xx, yy, zz);
            }
        }
        if ((rr = Mth.sqrt(xd * xd + zd * zd) * 4.0f) > 1.0f) {
            rr = 1.0f;
        }
        this.walkAnimSpeed += (rr - this.walkAnimSpeed) * 0.4f;
        this.walkAnimPos += this.walkAnimSpeed;
    }

    @Override
    public void livingTick() {
        super.updateState();
        this.oBob = this.bob;
        float tBob1 = Mth.sqrt(this.xd * this.xd + this.zd * this.zd);
        float tTilt = (float)Math.atan((double)(-this.yd) * (double)0.2f) * 15.0f;
        if (tBob1 > 0.1f) {
            tBob1 = 0.1f;
        }
        if (!this.onGround || this.health <= 0) {
            tBob1 = 0.0f;
        }
        if (this.onGround || this.health <= 0) {
            tTilt = 0.0f;
        }
        this.bob += (tBob1 - this.bob) * 0.4f;
        this.tilt += (tTilt - this.tilt) * 0.8f;
    }

    @Override
    public void outfitWithItem(int slot, int id, int dmg) {
        ItemStack item = null;
        if (id >= 0) {
            item = new ItemStack(id, 1, dmg);
        }
        if (slot == 0) {
            this.inventory.mainInventory[this.inventory.currentItem] = item;
        } else {
            this.inventory.armorInventory[slot - 1] = item;
        }
    }
}

