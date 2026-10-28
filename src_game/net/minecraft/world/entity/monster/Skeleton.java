/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.entity.monster;

import com.mojang.nbt.NBTTagCompound;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import util.Mth;

public class Skeleton
extends Monster {
    public Skeleton(Level level) {
        super(level);
        this.texture = "/mob/skeleton.png";
    }

    @Override
    public final void livingTick() {
        if (this.onlineEntity) {
            super.livingTick();
            return;
        }
        float br = this.getEntityBrightness(1.0f);
        if (this.level.skylightSubtracted > 7 && br > 0.5f && this.level.canBlockSeeTheSky((int)this.x, (int)this.y, (int)this.z) && this.rand.nextFloat() * 30.0f < (br - 0.4f) * 2.0f) {
            this.fire = 300;
        }
        super.livingTick();
    }

    @Override
    protected final void attackEntity(Entity e2, float dist) {
        if (this.onlineEntity) {
            return;
        }
        if (dist < 10.0f) {
            dist = e2.x - this.x;
            float f3 = e2.z - this.z;
            if (this.attackTime == 0) {
                Arrow arrow = new Arrow(this.level, this);
                arrow.y += 1.0f;
                float entity1 = e2.y - 0.2f - arrow.y;
                float f5 = Mth.sqrt(dist * dist + f3 * f3) * 0.2f;
                this.level.playSoundAtEntity(this, "random.bow", 1.0f, 1.0f / (this.rand.nextFloat() * 0.4f + 0.8f));
                this.level.spawnEntityInWorld(arrow);
                arrow.setArrowHeading(dist, entity1 + f5, f3, 0.6f, 12.0f);
                this.attackTime = 30;
            }
            this.xRot = (float)(Math.atan2(f3, dist) * 180.0 / 3.1415927410125732) - 90.0f;
            this.hasAttacked = true;
        }
    }

    @Override
    protected final void writeEntityToNBT(NBTTagCompound nbt) {
        super.writeEntityToNBT(nbt);
    }

    @Override
    protected final void readEntityFromNBT(NBTTagCompound nbt) {
        super.readEntityFromNBT(nbt);
    }

    @Override
    protected final String getEntityString() {
        return "Skeleton";
    }

    @Override
    protected final int scoreValue() {
        return Item.arrow.shiftedIndex;
    }

    @Override
    public ItemStack getHeldItem() {
        return null;
    }
}

