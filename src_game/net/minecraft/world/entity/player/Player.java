/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.entity.player;

import com.mojang.nbt.NBTTagCompound;
import com.mojang.nbt.NBTTagList;
import java.util.List;
import net.minecraft.world.IInventory;
import net.minecraft.world.container.Container;
import net.minecraft.world.container.ContainerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.InventoryPlayer;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemArmor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.tile.Tile;
import net.minecraft.world.level.tile.tileentity.TileEntityFurnace;
import util.Mth;

public class Player
extends LivingEntity {
    public static final int MP_PLAYER_ID = -999;
    public InventoryPlayer inventory = new InventoryPlayer(this);
    public Container inventorySlots;
    public Container craftingInventory;
    public byte userType = 0;
    public int score = 0;
    public float oBob;
    public float bob;
    private int dmgSpill = 0;
    public String username;
    public boolean swinging = false;
    public int swingTime = 0;
    public float prevCapeX;
    public float prevCapeY;
    public float prevCapeZ;
    public float capeX;
    public float capeY;
    public float capeZ;
    public String playerCloakUrl;

    public Player(Level level) {
        super(level);
        if (level != null) {
            this.setInventoryContainer(level);
            level.releaseEntitySkin(this);
            this.absMoveTo(level.xSpawn, level.ySpawn, level.zSpawn, 0.0f, 0.0f);
        }
        this.heightOffset = 1.62f;
        this.health = 20;
        this.fireResistance = 20;
        this.texture = "/char.png";
    }

    @Override
    public void updateCloak() {
        this.cloakUrl = this.playerCloakUrl = "https://www.sithgames.com/projects/minecraft/cloaks/" + this.username + ".png";
    }

    public void setInventoryContainer(Level world1) {
        this.craftingInventory = this.inventorySlots = new ContainerPlayer(this.inventory, !world1.onlineLevel);
    }

    @Override
    public final void preparePlayerToSpawn() {
        this.heightOffset = 1.62f;
        this.setSize(0.6f, 1.8f);
        super.preparePlayerToSpawn();
        this.health = 20;
        this.deathTime = 0;
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level.onlineLevel && this.craftingInventory != null && !this.craftingInventory.isUsableByPlayer(this)) {
            this.closeScreen();
            this.craftingInventory = this.inventorySlots;
        }
        this.prevCapeX = this.capeX;
        this.prevCapeY = this.capeY;
        this.prevCapeZ = this.capeZ;
        float var1 = this.x - this.capeX;
        float var3 = this.y - this.capeY;
        float var5 = this.z - this.capeZ;
        float var7 = 10.0f;
        if (var1 > var7) {
            this.prevCapeX = this.capeX = this.x;
        }
        if (var5 > var7) {
            this.prevCapeZ = this.capeZ = this.z;
        }
        if (var3 > var7) {
            this.prevCapeY = this.capeY = this.y;
        }
        if (var1 < -var7) {
            this.prevCapeX = this.capeX = this.x;
        }
        if (var5 < -var7) {
            this.prevCapeZ = this.capeZ = this.z;
        }
        if (var3 < -var7) {
            this.prevCapeY = this.capeY = this.y;
        }
        this.capeX += var1 * 0.25f;
        this.capeZ += var5 * 0.25f;
        this.capeY += var3 * 0.25f;
    }

    @Override
    public void livingTick() {
        this.level.playSoundEffect(this.x, this.y, this.z, "calm", 0.0f);
        if (this.level.difficultySetting == 0 && this.health < 20 && this.ticks % 20 << 2 == 0) {
            this.heal(1);
        }
        InventoryPlayer inventoryPlayer3 = this.inventory;
        int i4 = 0;
        while (i4 < inventoryPlayer3.mainInventory.length) {
            if (inventoryPlayer3.mainInventory[i4] != null && inventoryPlayer3.mainInventory[i4].animationsToGo > 0) {
                --inventoryPlayer3.mainInventory[i4].animationsToGo;
            }
            ++i4;
        }
        this.oBob = this.bob;
        super.livingTick();
        float f1 = Mth.sqrt(this.xd * this.xd + this.zd * this.zd);
        float f2 = (float)Math.atan(-this.yd * 0.2f) * 15.0f;
        if (f1 > 0.1f) {
            f1 = 0.1f;
        }
        if (!this.onGround || this.health <= 0) {
            f1 = 0.0f;
        }
        if (this.onGround || this.health <= 0) {
            f2 = 0.0f;
        }
        this.bob += (f1 - this.bob) * 0.4f;
        this.tilt += (f2 - this.tilt) * 0.8f;
        List<Entity> entities = this.level.getEntitiesWithinAABBExcludingEntity(this, this.bb.expand(1.0f, 0.0f, 1.0f));
        if (this.health > 0 && entities != null) {
            int i2 = 0;
            while (i2 < entities.size()) {
                Entity e2 = entities.get(i2);
                e2.onCollideWithPlayer(this);
                ++i2;
            }
        }
    }

    @Override
    public void onDeath(Entity entity) {
        this.setSize(0.2f, 0.2f);
        this.setPos(this.x, this.y, this.z);
        this.yd = 0.1f;
        float xx = -1.0f;
        float zz = -1.0f;
        if (entity != null) {
            this.xd = -Mth.cos((this.hurtDir + this.xRot) * (float)Math.PI / 180.0f) * 0.1f;
            this.zd = -Mth.sin((this.hurtDir + this.xRot) * (float)Math.PI / 180.0f) * 0.1f;
            xx = entity.x;
            zz = entity.z;
        } else {
            this.zd = 0.0f;
            this.xd = 0.0f;
        }
        this.heightOffset = 0.1f;
        this.level.updateEntityStatus(this, (byte)3, xx, zz);
    }

    @Override
    public final void remove() {
    }

    public final void dropPlayerItem(ItemStack item) {
        if (this.level.onlineLevel) {
            return;
        }
        this.dropPlayerItemWithRandomChoice(item, false);
    }

    public final void dropPlayerItemWithRandomChoice(ItemStack item, boolean z2) {
        if (item != null) {
            ItemEntity ie = new ItemEntity(this.level, this.x, this.y - 0.3f, this.z, item);
            ie.delayBeforeCanPickup = 40;
            ie.xd = -Mth.sin(this.xRot / 180.0f * (float)Math.PI) * Mth.cos(this.yRot / 180.0f * (float)Math.PI) * 0.3f;
            ie.zd = Mth.cos(this.xRot / 180.0f * (float)Math.PI) * Mth.cos(this.yRot / 180.0f * (float)Math.PI) * 0.3f;
            ie.yd = -Mth.sin(this.yRot / 180.0f * (float)Math.PI) * 0.3f + 0.1f;
            float f3 = this.rand.nextFloat() * (float)Math.PI * 2.0f;
            float f5 = 0.02f * this.rand.nextFloat();
            ie.xd = (float)((double)ie.xd + Math.cos(f3) * (double)f5);
            ie.yd += (this.rand.nextFloat() - this.rand.nextFloat()) * 0.1f;
            ie.zd = (float)((double)ie.zd + Math.sin(f3) * (double)f5);
            this.level.spawnEntityInWorld(ie);
        }
    }

    public final boolean canHarvestTile(Tile block1) {
        ItemStack block12;
        Tile block2 = block1;
        InventoryPlayer block11 = this.inventory;
        return block2.material != Material.rock && block2.material != Material.iron ? true : ((block12 = block11.getStackInSlot(block11.currentItem)) != null ? Item.itemsList[block12.itemID].canHarvestBlock(block2) : false);
    }

    @Override
    protected final void writeEntityToNBT(NBTTagCompound nbt) {
        NBTTagCompound slot;
        super.writeEntityToNBT(nbt);
        nbt.setInteger("Score", this.score);
        NBTTagList nbtInventory = new NBTTagList();
        int i2 = 0;
        while (i2 < this.inventory.mainInventory.length) {
            if (this.inventory.mainInventory[i2] != null) {
                slot = new NBTTagCompound();
                slot.setByte("Slot", (byte)i2);
                this.inventory.mainInventory[i2].writeToNBT(slot);
                nbtInventory.setTag(slot);
            }
            ++i2;
        }
        i2 = 0;
        while (i2 < this.inventory.armorInventory.length) {
            if (this.inventory.armorInventory[i2] != null) {
                slot = new NBTTagCompound();
                slot.setByte("Slot", (byte)(i2 + 100));
                this.inventory.armorInventory[i2].writeToNBT(slot);
                nbtInventory.setTag(slot);
            }
            ++i2;
        }
        nbt.setTag("Inventory", nbtInventory);
    }

    @Override
    protected final void readEntityFromNBT(NBTTagCompound nbt) {
        super.readEntityFromNBT(nbt);
        this.score = nbt.getInteger("Score");
        NBTTagList nbtInventory = nbt.getTagList("Inventory");
        this.inventory.mainInventory = new ItemStack[36];
        this.inventory.armorInventory = new ItemStack[4];
        int i2 = 0;
        while (i2 < nbtInventory.tagCount()) {
            NBTTagCompound nbtItem = (NBTTagCompound)nbtInventory.tagAt(i2);
            int slot = nbtItem.getByte("Slot") & 0xFF;
            if (slot >= 0 && slot < this.inventory.mainInventory.length) {
                this.inventory.mainInventory[slot] = new ItemStack(nbtItem);
            }
            if (slot >= 100 && slot < this.inventory.armorInventory.length + 100) {
                this.inventory.armorInventory[slot - 100] = new ItemStack(nbtItem);
            }
            ++i2;
        }
    }

    @Override
    protected String getEntityString() {
        return null;
    }

    public void displayGUIChest(IInventory iInventory) {
    }

    public void displayWorkbenchGUI(int x2, int y2, int z2) {
    }

    public void onItemPickup(Entity entity, int var2) {
    }

    @Override
    protected final float getEyeHeight() {
        return 0.12f;
    }

    @Override
    public boolean hurt(Entity source, int dmg) {
        if (!this.level.survivalWorld) {
            return false;
        }
        this.entityAge = 0;
        if (this.health <= 0) {
            return false;
        }
        if ((float)this.invulnerableTime > (float)this.invulnerableDuration / 2.0f) {
            return false;
        }
        if (source instanceof Monster || source instanceof Arrow) {
            if (this.level.difficultySetting == 0) {
                dmg = 0;
            }
            if (this.level.difficultySetting == 1) {
                dmg = dmg / 3 + 1;
            }
            if (this.level.difficultySetting == 3) {
                dmg = dmg * 3 / 2;
            }
        }
        int i3 = 25 - this.inventory.getPlayerArmorValue();
        i3 = dmg * i3 + this.dmgSpill;
        int i4 = dmg;
        InventoryPlayer inventoryPlayer6 = this.inventory;
        int i5 = 0;
        while (i5 < inventoryPlayer6.armorInventory.length) {
            if (inventoryPlayer6.armorInventory[i5] != null && inventoryPlayer6.armorInventory[i5].getItem() instanceof ItemArmor) {
                inventoryPlayer6.armorInventory[i5].damageItem(i4, this);
                if (inventoryPlayer6.armorInventory[i5].stackSize == 0) {
                    inventoryPlayer6.armorInventory[i5] = null;
                }
            }
            ++i5;
        }
        dmg = i3 / 25;
        this.dmgSpill = i3 % 25;
        if (dmg == 0) {
            return false;
        }
        return super.hurt(source, dmg);
    }

    public void displayGUIFurnace(TileEntityFurnace furnace) {
    }

    public final void destroyCurrentEquippedItem() {
        this.inventory.setInventorySlotContents(this.inventory.currentItem, null);
    }

    @Override
    protected void updateState() {
        if (this.swinging) {
            ++this.swingTime;
            if (this.swingTime == 8) {
                this.swingTime = 0;
                this.swinging = false;
            }
        } else {
            this.swingTime = 0;
        }
        this.swingProgress = (float)this.swingTime / 8.0f;
    }

    public void swingItem() {
        this.swingTime = -1;
        this.swinging = true;
    }

    public void attackEntity(Entity e2) {
        int dmg;
        ItemStack curStack = this.inventory.getStackInSlot(this.inventory.currentItem);
        int n2 = dmg = curStack != null ? Item.itemsList[curStack.itemID].getDamageVsEntity() : 1;
        if (dmg > 0) {
            e2.hurt(this, dmg);
            ItemStack item = this.inventory.getCurrentItem();
            if (item != null && e2 instanceof LivingEntity) {
                Item.itemsList[item.itemID].hitEntity(item, this);
                if (item.stackSize <= 0) {
                    this.destroyCurrentEquippedItem();
                }
            }
        }
    }

    public void interactWithEntity(Entity e2) {
    }

    public void closeScreen() {
        this.craftingInventory = this.inventorySlots;
    }

    public void dropCurrentItem() {
        this.dropPlayerItemWithRandomChoice(this.inventory.decrStackSize(this.inventory.currentItem, 1), false);
    }
}

