/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.entity.item;

import com.mojang.nbt.NBTTagCompound;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.tile.Tile;

public class ItemEntity
extends Entity {
    private static final int LIFETIME = 6000;
    public ItemStack item;
    public int age = 0;
    public int delayBeforeCanPickup;
    private int health = 5;
    public float hoverStart = (float)(Math.random() * Math.PI * 2.0);
    protected int lSteps;
    protected float lx;
    protected float ly;
    protected float lz;
    protected float lxr;
    protected float lyr;

    public ItemEntity(Level level, float f2, float f3, float f4, ItemStack itemStack) {
        super(level);
        this.setSize(0.25f, 0.25f);
        this.heightOffset = this.bbHeight / 2.0f;
        this.setPos(f2, f3, f4);
        this.item = itemStack;
        this.xRot = (float)(Math.random() * 360.0);
        this.xd = (float)(Math.random() * (double)0.2f - (double)0.1f);
        this.yd = 0.2f;
        this.zd = (float)(Math.random() * (double)0.2f - (double)0.1f);
        this.canTriggerWalking = false;
    }

    public ItemEntity(Level level) {
        super(level);
    }

    @Override
    protected void entityInit() {
    }

    @Override
    public final void tick() {
        super.tick();
        if (this.delayBeforeCanPickup > 0) {
            --this.delayBeforeCanPickup;
        }
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        this.yd -= 0.04f;
        if (this.level.getMaterial((int)this.x, (int)this.y, (int)this.z) == Material.lava) {
            this.yd = 0.2f;
            this.xd = (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2f;
            this.zd = (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2f;
            this.level.playSoundAtEntity(this, "random.fizz", 0.4f, 2.0f + this.rand.nextFloat() * 0.4f);
        }
        float f2 = this.z;
        float f3 = this.y;
        float f4 = this.x;
        int n2 = (int)f4;
        int n3 = (int)f3;
        int n4 = (int)f2;
        f4 -= (float)n2;
        f3 -= (float)n3;
        f2 -= (float)n4;
        if (Tile.opaqueCubeLookup[this.level.getTile(n2, n3, n4)]) {
            boolean bl = !Tile.opaqueCubeLookup[this.level.getTile(n2 - 1, n3, n4)];
            boolean bl2 = !Tile.opaqueCubeLookup[this.level.getTile(n2 + 1, n3, n4)];
            boolean bl3 = !Tile.opaqueCubeLookup[this.level.getTile(n2, n3 - 1, n4)];
            boolean bl4 = !Tile.opaqueCubeLookup[this.level.getTile(n2, n3 + 1, n4)];
            boolean bl5 = !Tile.opaqueCubeLookup[this.level.getTile(n2, n3, n4 - 1)];
            boolean bl6 = !Tile.opaqueCubeLookup[this.level.getTile(n2, n3, n4 + 1)];
            int n5 = -1;
            float f5 = 9999.0f;
            if (bl && f4 < 9999.0f) {
                f5 = f4;
                n5 = 0;
            }
            if (bl2 && 1.0f - f4 < f5) {
                f5 = 1.0f - f4;
                n5 = 1;
            }
            if (bl3 && f3 < f5) {
                f5 = f3;
                n5 = 2;
            }
            if (bl4 && 1.0f - f3 < f5) {
                f5 = 1.0f - f3;
                n5 = 3;
            }
            if (bl5 && f2 < f5) {
                f5 = f2;
                n5 = 4;
            }
            if (bl6 && 1.0f - f2 < f5) {
                n5 = 5;
            }
            f4 = this.rand.nextFloat() * 0.2f + 0.1f;
            if (n5 == 0) {
                this.xd = -f4;
            }
            if (n5 == 1) {
                this.xd = f4;
            }
            if (n5 == 2) {
                this.yd = -f4;
            }
            if (n5 == 3) {
                this.yd = f4;
            }
            if (n5 == 4) {
                this.zd = -f4;
            }
            if (n5 == 5) {
                this.zd = f4;
            }
        }
        if (!this.level.onlineLevel) {
            this.moveEntity(this.xd, this.yd, this.zd);
            this.xd *= 0.98f;
            this.yd *= 0.98f;
            this.zd *= 0.98f;
            if (this.onGround) {
                this.xd *= 0.7f;
                this.zd *= 0.7f;
                this.yd *= -0.5f;
            }
        }
        if (this.lSteps > 0) {
            float f6;
            float f7 = this.x + (this.lx - this.x) / (float)this.lSteps;
            float f8 = this.y + (this.ly - this.y) / (float)this.lSteps;
            float f9 = this.z + (this.lz - this.z) / (float)this.lSteps;
            for (f6 = this.lxr - this.xRot; f6 < -180.0f; f6 += 360.0f) {
            }
            while (f6 >= 180.0f) {
                f6 -= 360.0f;
            }
            this.xRot += f6 / (float)this.lSteps;
            this.yRot += (this.lyr - this.yRot) / (float)this.lSteps;
            --this.lSteps;
            this.setPos(f7, f8, f9);
            this.setRot(this.xRot, this.yRot);
        }
        ++this.age;
        if (this.age >= 6000) {
            this.remove();
        }
    }

    @Override
    protected final void dealFireDamage(int n2) {
        this.hurt(null, 1);
    }

    @Override
    public final boolean hurt(Entity entity, int n2) {
        this.health -= n2;
        if (this.health <= 0) {
            this.remove();
        }
        return false;
    }

    @Override
    protected final void writeEntityToNBT(NBTTagCompound nBTTagCompound) {
        nBTTagCompound.setShort("Health", (byte)this.health);
        nBTTagCompound.setShort("Age", (short)this.age);
        nBTTagCompound.setCompoundTag("Item", this.item.writeToNBT(new NBTTagCompound()));
    }

    @Override
    protected final void readEntityFromNBT(NBTTagCompound nBTTagCompound) {
        this.health = nBTTagCompound.getShort("Health") & 0xFF;
        this.age = nBTTagCompound.getShort("Age");
        nBTTagCompound = nBTTagCompound.getCompoundTag("Item");
        this.item = new ItemStack(nBTTagCompound);
    }

    @Override
    protected final String getEntityString() {
        return "Item";
    }

    @Override
    public final void onCollideWithPlayer(Player player) {
        if (this.level.onlineLevel) {
            return;
        }
        int n2 = this.item.stackSize;
        if (n2 > 0 && this.delayBeforeCanPickup == 0 && player.inventory.storePartialItemStack(this.item)) {
            this.level.playSoundAtEntity(this, "random.pop", 0.2f, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7f + 1.0f) * 2.0f);
            player.onItemPickup(this, n2);
        }
        if (n2 <= 0) {
            this.remove();
        }
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

