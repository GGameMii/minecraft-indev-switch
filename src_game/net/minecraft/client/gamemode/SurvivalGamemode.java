/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.gamemode;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gamemode.Gamemode;
import net.minecraft.client.player.PlayerSP;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.tile.Tile;

public final class SurvivalGamemode
extends Gamemode {
    private int curBlockX = -1;
    private int curBlockY = -1;
    private int curBlockZ = -1;
    private float curBlockDamage = 0.0f;
    private float prevBlockDamage = 0.0f;
    private float blockDestroySoundCounter = 0.0f;
    private int blockHitWait = 0;

    public SurvivalGamemode(Minecraft game) {
        super(game);
    }

    @Override
    public final boolean sendBlockRemoved(int x2, int y2, int z2, int f2) {
        int id = this.mc.level.getTile(x2, y2, z2);
        byte data = this.mc.level.getTileMetadata(x2, y2, z2);
        boolean removed = super.sendBlockRemoved(x2, y2, z2, f2);
        PlayerSP entityPlayerSP7 = this.mc.player;
        ItemStack item = this.mc.player.inventory.getCurrentItem();
        if (item != null) {
            Item.itemsList[item.itemID].onTileDestroyed(item, entityPlayerSP7);
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
    public final void clickBlock(int x2, int y2, int z2, int f2) {
        int id = this.mc.level.getTile(x2, y2, z2);
        if (id > 0 && Tile.tiles[id].blockStrength(this.mc.player) >= 1.0f) {
            this.sendBlockRemoved(x2, y2, z2, f2);
        }
    }

    @Override
    public final void resetBlockRemoving() {
        this.curBlockDamage = 0.0f;
        this.blockHitWait = 0;
    }

    @Override
    public final void sendBlockRemoving(int x2, int y2, int z2, int f2) {
        if (this.blockHitWait > 0) {
            --this.blockHitWait;
        } else {
            super.sendBlockRemoving(x2, y2, z2, f2);
            if (x2 == this.curBlockX && y2 == this.curBlockY && z2 == this.curBlockZ) {
                int id = this.mc.level.getTile(x2, y2, z2);
                if (id != 0) {
                    Tile tile = Tile.tiles[id];
                    this.curBlockDamage += tile.blockStrength(this.mc.player);
                    if (this.blockDestroySoundCounter % 4.0f == 0.0f && tile != null) {
                        SoundManager snd = this.mc.sndManager;
                        String name = tile.stepSound.getSndName();
                        float x0 = (float)x2 + 0.5f;
                        float y0 = (float)y2 + 0.5f;
                        float z0 = (float)z2 + 0.5f;
                        float vol = (tile.stepSound.soundVolume + 1.0f) / 8.0f;
                        snd.playSound(name, x0, y0, z0, vol, tile.stepSound.soundPitch * 0.5f);
                    }
                    this.blockDestroySoundCounter += 1.0f;
                    if (this.curBlockDamage >= 1.0f) {
                        this.sendBlockRemoved(x2, y2, z2, f2);
                        this.curBlockDamage = 0.0f;
                        this.prevBlockDamage = 0.0f;
                        this.blockDestroySoundCounter = 0.0f;
                        this.blockHitWait = 5;
                    }
                }
            } else {
                this.curBlockDamage = 0.0f;
                this.prevBlockDamage = 0.0f;
                this.blockDestroySoundCounter = 0.0f;
                this.curBlockX = x2;
                this.curBlockY = y2;
                this.curBlockZ = z2;
            }
        }
    }

    @Override
    public final void setPartialTime(float a2) {
        this.mc.levelRenderer.damagePartialTime = this.curBlockDamage <= 0.0f ? 0.0f : (a2 = this.prevBlockDamage + (this.curBlockDamage - this.prevBlockDamage) * a2);
    }

    @Override
    public final float getBlockReach() {
        return 4.0f;
    }

    @Override
    public final void setLevel(Level level) {
        super.setLevel(level);
    }

    @Override
    public final void tick() {
        this.prevBlockDamage = this.curBlockDamage;
        this.mc.level.spawner.performSpawning();
    }
}

