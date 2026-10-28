/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.gamemode;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gamemode.Gamemode;
import net.minecraft.client.network.Client;
import net.minecraft.network.packet.BlockDigPacket;
import net.minecraft.network.packet.BlockItemSwitchPacket;
import net.minecraft.network.packet.PlaceTilePacket;
import net.minecraft.network.packet.SetCreativeSlotPacket;
import net.minecraft.network.packet.UseEntityPacket;
import net.minecraft.network.packet.WindowClickPacket;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.tile.Tile;

public class MultiplayerGamemode
extends Gamemode {
    private int currentBlockX = -1;
    private int currentBlockY = -1;
    private int currentBlockZ = -1;
    private float curBlockDamageMP = 0.0f;
    private float prevBlockDamageMP = 0.0f;
    private float stepSoundTickCounter = 0.0f;
    private int blockHitDelay = 0;
    private boolean isHittingBlock = false;
    private Client client;
    private int currentPlayerItem = 0;

    public MultiplayerGamemode(Minecraft game, Client c2) {
        super(game);
        this.client = c2;
    }

    @Override
    public boolean shouldDrawHUD() {
        return !this.creativeMode;
    }

    @Override
    public void respawn(Player p2) {
        p2.xRot = 0.0f;
    }

    @Override
    public boolean sendBlockRemoved(int x2, int y2, int z2, int f2) {
        int id = this.mc.level.getTile(x2, y2, z2);
        byte data = this.mc.level.getTileMetadata(x2, y2, z2);
        boolean removed = super.sendBlockRemoved(x2, y2, z2, f2);
        ItemStack item = this.mc.player.inventory.getCurrentItem();
        if (item != null) {
            Item.itemsList[item.itemID].onTileDestroyed(item, this.mc.player);
            if (item.stackSize == 0) {
                this.mc.player.destroyCurrentEquippedItem();
            }
        }
        if (removed && this.mc.player.canHarvestTile(Tile.tiles[id])) {
            Tile.tiles[id].dropBlockAsItem(this.mc.level, x2, y2, z2, data);
        }
        return removed;
    }

    @Override
    public void clickBlock(int x2, int y2, int z2, int f2) {
        if (!this.isHittingBlock || x2 != this.currentBlockX || y2 != this.currentBlockY || z2 != this.currentBlockZ) {
            this.client.addToQueue(new BlockDigPacket(0, x2, y2, z2, f2));
            if (this.mc.level.survivalWorld) {
                int id = this.mc.level.getTile(x2, y2, z2);
                if (id > 0 && this.curBlockDamageMP == 0.0f) {
                    Tile.tiles[id].onTileClicked(this.mc.level, x2, y2, z2, this.mc.player);
                }
                if (id > 0 && Tile.tiles[id].blockStrength(this.mc.player) >= 1.0f) {
                    this.sendBlockRemoved(x2, y2, z2, f2);
                } else {
                    this.isHittingBlock = true;
                    this.currentBlockX = x2;
                    this.currentBlockY = y2;
                    this.currentBlockZ = z2;
                    this.curBlockDamageMP = 0.0f;
                    this.prevBlockDamageMP = 0.0f;
                    this.stepSoundTickCounter = 0.0f;
                }
            } else {
                this.sendBlockRemoved(x2, y2, z2, f2);
                this.client.addToQueue(new BlockDigPacket(0, x2, y2, z2, f2));
            }
        }
    }

    @Override
    public void resetBlockRemoving() {
        this.curBlockDamageMP = 0.0f;
        this.isHittingBlock = false;
    }

    @Override
    public void sendBlockRemoving(int x2, int y2, int z2, int f2) {
        if (this.isHittingBlock) {
            this.syncCurrentPlayItem();
            if (this.blockHitDelay > 0) {
                --this.blockHitDelay;
            } else if (x2 == this.currentBlockX && y2 == this.currentBlockY && z2 == this.currentBlockZ) {
                int id = this.mc.level.getTile(x2, y2, z2);
                if (id == 0) {
                    return;
                }
                Tile tile = Tile.tiles[id];
                this.curBlockDamageMP += tile.blockStrength(this.mc.player);
                if (this.stepSoundTickCounter % 4.0f == 0.0f && tile != null) {
                    this.mc.sndManager.playSound(tile.stepSound.getSndName(), (float)x2 + 0.5f, (float)y2 + 0.5f, (float)z2 + 0.5f, (tile.stepSound.soundVolume + 1.0f) / 8.0f, tile.stepSound.soundPitch * 0.5f);
                }
                this.stepSoundTickCounter += 1.0f;
                if (this.curBlockDamageMP >= 1.0f) {
                    this.client.addToQueue(new BlockDigPacket(2, x2, y2, z2, f2));
                    this.sendBlockRemoved(x2, y2, z2, f2);
                    this.curBlockDamageMP = 0.0f;
                    this.prevBlockDamageMP = 0.0f;
                    this.stepSoundTickCounter = 0.0f;
                    this.blockHitDelay = 5;
                }
            } else {
                this.clickBlock(x2, y2, z2, f2);
            }
        }
    }

    @Override
    public void setPartialTime(float a2) {
        float f2;
        this.mc.levelRenderer.damagePartialTime = this.curBlockDamageMP <= 0.0f ? 0.0f : (f2 = this.prevBlockDamageMP + (this.curBlockDamageMP - this.prevBlockDamageMP) * a2);
    }

    @Override
    public float getBlockReach() {
        return this.creativeMode ? 5.0f : 4.0f;
    }

    @Override
    public void setLevel(Level level) {
        super.setLevel(level);
    }

    @Override
    public void tick() {
        this.syncCurrentPlayItem();
        this.prevBlockDamageMP = this.curBlockDamageMP;
    }

    private void syncCurrentPlayItem() {
        int slot = this.mc.player.inventory.currentItem;
        if (slot != this.currentPlayerItem) {
            this.currentPlayerItem = slot;
            this.client.addToQueue(new BlockItemSwitchPacket(this.currentPlayerItem));
        }
    }

    @Override
    public boolean onPlayerRightClick(Player player, Level level, ItemStack item, int x2, int y2, int z2, int f2) {
        this.syncCurrentPlayItem();
        this.client.addToQueue(new PlaceTilePacket(x2, y2, z2, f2, item));
        return super.onPlayerRightClick(player, level, item, x2, y2, z2, f2);
    }

    @Override
    public boolean sendUseItem(Player player, Level level, ItemStack item) {
        this.syncCurrentPlayItem();
        this.client.addToQueue(new PlaceTilePacket(-1, -1, -1, 255, player.inventory.getCurrentItem()));
        boolean useItem = super.sendUseItem(player, level, item);
        return useItem;
    }

    @Override
    public void attackEntity(Player player, Entity e2) {
        this.syncCurrentPlayItem();
        this.client.addToQueue(new UseEntityPacket(player.entityID, e2.entityID, 1));
        player.attackEntity(e2);
    }

    @Override
    public void interactWithEntity(Player player, Entity e2) {
        this.syncCurrentPlayItem();
        this.client.addToQueue(new UseEntityPacket(player.entityID, e2.entityID, 0));
        player.interactWithEntity(e2);
    }

    @Override
    public ItemStack handleInventoryMouseClick(int containerId, int slotNum, int buttonNum, boolean var4, Player player) {
        short backup = player.craftingInventory.backup(player.inventory);
        ItemStack item = super.handleInventoryMouseClick(containerId, slotNum, buttonNum, var4, player);
        this.client.addToQueue(new WindowClickPacket(containerId, slotNum, buttonNum, var4, item, backup));
        return item;
    }

    @Override
    public void onWindowClosed(int id, Player player) {
    }

    @Override
    public void sendSlotPacket(ItemStack par1ItemStack, int par2) {
        if (this.creativeMode) {
            this.client.addToQueue(new SetCreativeSlotPacket(par2, par1ItemStack));
        }
    }

    @Override
    public void dropCreativeSlot(ItemStack item) {
        if (this.creativeMode && item != null) {
            this.client.addToQueue(new SetCreativeSlotPacket(-1, item));
        }
    }
}

