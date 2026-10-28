/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.entity;

import com.mojang.nbt.NBTTagCompound;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EnumArt;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.AABB;

public class Painting
extends Entity {
    private int tickCounter1 = 0;
    public int direction = 0;
    public int xPosition;
    public int yPosition;
    public int zPosition;
    public EnumArt art;

    public Painting(Level level) {
        super(level);
        this.heightOffset = 0.0f;
        this.setSize(0.5f, 0.5f);
    }

    @Override
    protected void entityInit() {
    }

    public Painting(Level level, int n2, int n3, int n4, int n5) {
        this(level);
        this.xPosition = n2;
        this.yPosition = n3;
        this.zPosition = n4;
        ArrayList<EnumArt> arrayList = new ArrayList<EnumArt>();
        EnumArt[] enumArtArray = EnumArt.values();
        n3 = enumArtArray.length;
        for (n4 = 0; n4 < n3; ++n4) {
            EnumArt enumArt;
            this.art = enumArt = enumArtArray[n4];
            this.setDirection(n5);
            if (!this.onValidSurface()) continue;
            arrayList.add(enumArt);
        }
        if (arrayList.size() > 0) {
            this.art = (EnumArt)((Object)arrayList.get(this.rand.nextInt(arrayList.size())));
        }
        this.setDirection(n5);
    }

    public Painting(Level level, int n2, int n3, int n4, int n5, String string) {
        this(level, n2, n3, n4, n5);
        for (EnumArt enumArt : EnumArt.values()) {
            if (!enumArt.title.equals(string)) continue;
            this.art = enumArt;
            break;
        }
        this.setDirection(n5);
    }

    private void setDirection(int n2) {
        this.direction = n2;
        this.xRotI = this.xRot = (float)(n2 * 90);
        float f2 = this.art.sizeX;
        float f3 = this.art.sizeY;
        float f4 = this.art.sizeX;
        if (n2 != 0 && n2 != 2) {
            f2 = 0.5f;
        } else {
            f4 = 0.5f;
        }
        f2 /= 32.0f;
        f3 /= 32.0f;
        f4 /= 32.0f;
        float f5 = (float)this.xPosition + 0.5f;
        float f6 = (float)this.yPosition + 0.5f;
        float f7 = (float)this.zPosition + 0.5f;
        if (n2 == 0) {
            f7 -= 0.5625f;
        }
        if (n2 == 1) {
            f5 -= 0.5625f;
        }
        if (n2 == 2) {
            f7 += 0.5625f;
        }
        if (n2 == 3) {
            f5 += 0.5625f;
        }
        if (n2 == 0) {
            f5 -= Painting.getArtSize(this.art.sizeX);
        }
        if (n2 == 1) {
            f7 += Painting.getArtSize(this.art.sizeX);
        }
        if (n2 == 2) {
            f5 += Painting.getArtSize(this.art.sizeX);
        }
        if (n2 == 3) {
            f7 -= Painting.getArtSize(this.art.sizeX);
        }
        this.setPos(f5, f6 += Painting.getArtSize(this.art.sizeY), f7);
        this.bb = new AABB(f5 - f2, f6 - f3, f7 - f4, f5 + f2, f6 + f3, f7 + f4);
        float f8 = 0.00625f;
        f8 = 0.00625f;
        f8 = 0.00625f;
        AABB aABB = this.bb;
        f2 = this.bb.x0;
        f3 = aABB.y0;
        f4 = aABB.z0;
        f5 = aABB.x1;
        f6 = aABB.y1;
        f8 = aABB.z1;
        this.bb = new AABB(f2, f3, f4, f5 -= 0.00625f, f6 -= 0.00625f, f8 -= 0.00625f);
    }

    private static float getArtSize(int n2) {
        return n2 == 32 ? 0.5f : (n2 == 64 ? 0.5f : 0.0f);
    }

    @Override
    public final void tick() {
        if (this.tickCounter1++ == 100 && !this.onValidSurface()) {
            this.tickCounter1 = 0;
            this.remove();
            if (!this.level.onlineLevel) {
                this.level.spawnEntityInWorld(new ItemEntity(this.level, this.x, this.y, this.z, new ItemStack(Item.painting)));
            }
        }
    }

    public final boolean onValidSurface() {
        int n2;
        if (this.level.getCollidingBoundingBoxes(this, this.bb).size() > 0) {
            return false;
        }
        int n3 = this.art.sizeX / 16;
        int n4 = this.art.sizeY / 16;
        int n5 = this.xPosition;
        int n6 = this.zPosition;
        if (this.direction == 0) {
            n5 = (int)(this.x - (float)this.art.sizeX / 32.0f);
        }
        if (this.direction == 1) {
            n6 = (int)(this.z - (float)this.art.sizeX / 32.0f);
        }
        if (this.direction == 2) {
            n5 = (int)(this.x - (float)this.art.sizeX / 32.0f);
        }
        if (this.direction == 3) {
            n6 = (int)(this.z - (float)this.art.sizeX / 32.0f);
        }
        int n7 = (int)(this.y - (float)this.art.sizeY / 32.0f);
        for (int i2 = 0; i2 < n3; ++i2) {
            for (n2 = 0; n2 < n4; ++n2) {
                Material material = this.direction != 0 && this.direction != 2 ? this.level.getMaterial(this.xPosition, n7 + n2, n6 + i2) : this.level.getMaterial(n5 + i2, n7 + n2, this.zPosition);
                if (material.isSolid()) continue;
                return false;
            }
        }
        List<Entity> list = this.level.entityMap.getEntitiesWithinAABB(this, this.bb);
        for (n2 = 0; n2 < list.size(); ++n2) {
            if (!(list.get(n2) instanceof Painting)) continue;
            return false;
        }
        return true;
    }

    @Override
    public final boolean canBeCollidedWith() {
        return true;
    }

    @Override
    public final boolean hurt(Entity entity, int n2) {
        this.remove();
        if (!this.level.onlineLevel && this.level.survivalWorld) {
            this.level.spawnEntityInWorld(new ItemEntity(this.level, this.x, this.y, this.z, new ItemStack(Item.painting)));
        }
        return true;
    }

    @Override
    protected final void writeEntityToNBT(NBTTagCompound nBTTagCompound) {
        nBTTagCompound.setByte("Dir", (byte)this.direction);
        nBTTagCompound.setString("Motive", this.art.title);
        nBTTagCompound.setInteger("TileX", this.xPosition);
        nBTTagCompound.setInteger("TileY", this.yPosition);
        nBTTagCompound.setInteger("TileZ", this.zPosition);
    }

    @Override
    protected final String getEntityString() {
        return "Painting";
    }

    @Override
    protected final void readEntityFromNBT(NBTTagCompound nBTTagCompound) {
        this.direction = nBTTagCompound.getByte("Dir");
        this.xPosition = nBTTagCompound.getInteger("TileX");
        this.yPosition = nBTTagCompound.getInteger("TileY");
        this.zPosition = nBTTagCompound.getInteger("TileZ");
        String string = nBTTagCompound.getString("Motive");
        for (EnumArt enumArt : EnumArt.values()) {
            if (!enumArt.title.equals(string)) continue;
            this.art = enumArt;
        }
        if (this.art == null) {
            this.art = EnumArt.Kebab;
        }
        this.setDirection(this.direction);
    }
}

