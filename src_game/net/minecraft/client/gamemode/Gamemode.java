/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.gamemode;

import net.minecraft.client.Minecraft;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.tile.Tile;

public class Gamemode {
    protected final Minecraft mc;
    public boolean creativeMode = false;

    public Gamemode(Minecraft game) {
        this.mc = game;
    }

    public void setLevel(Level level) {
    }

    public void clickBlock(int x2, int y2, int z2, int f2) {
        this.sendBlockRemoved(x2, y2, z2, f2);
    }

    public boolean sendBlockRemoved(int x2, int y2, int z2, int f2) {
        this.mc.particles.addBlockDestroyEffects(x2, y2, z2);
        Level world = this.mc.level;
        Tile tile = Tile.tiles[world.getTile(x2, y2, z2)];
        byte data = world.getTileMetadata(x2, y2, z2);
        boolean removed = world.setTile(x2, y2, z2, 0);
        if (tile != null && removed) {
            SoundManager snd = this.mc.sndManager;
            String name = tile.stepSound.stepSoundDir();
            float x0 = (float)x2 + 0.5f;
            float y0 = (float)y2 + 0.5f;
            float z0 = (float)z2 + 0.5f;
            float vol = (tile.stepSound.soundVolume + 1.0f) / 2.0f;
            snd.playSound(name, x0, y0, z0, vol, tile.stepSound.soundPitch * 0.8f);
            tile.onBlockDestroyedByPlayer(world, x2, y2, z2, data);
        }
        return removed;
    }

    public void sendBlockRemoving(int x2, int y2, int z2, int f2) {
    }

    public void resetBlockRemoving() {
    }

    public void setPartialTime(float a2) {
    }

    public float getBlockReach() {
        return 5.0f;
    }

    public void tick() {
    }

    public boolean shouldDrawHUD() {
        return true;
    }

    public void respawn(Player player) {
    }

    public boolean onPlayerRightClick(Player player, Level level, ItemStack item, int x2, int y2, int z2, int f2) {
        int id = level.getTile(x2, y2, z2);
        return id > 0 && Tile.tiles[id].blockActivated(level, x2, y2, z2, player) ? true : (item == null ? false : item.useItem(player, level, x2, y2, z2, f2));
    }

    public void interactWithEntity(Player player, Entity e2) {
        player.interactWithEntity(e2);
    }

    public void attackEntity(Player player, Entity e2) {
        player.attackEntity(e2);
    }

    public boolean sendUseItem(Player player, Level level, ItemStack item) {
        int i2 = item.stackSize;
        ItemStack result = item.useItemRightClick(level, player);
        if (result != item || result != null && result.stackSize != i2) {
            player.inventory.mainInventory[player.inventory.currentItem] = result;
            if (result.stackSize == 0) {
                player.inventory.mainInventory[player.inventory.currentItem] = null;
            }
            return true;
        }
        return false;
    }

    public ItemStack handleInventoryMouseClick(int containerId, int slotNum, int buttonNum, boolean var4, Player player) {
        return player.craftingInventory.slotClick(slotNum, buttonNum, var4, player);
    }

    public void onWindowClosed(int id, Player player) {
        player.craftingInventory.onCraftGuiClosed(player);
        player.craftingInventory = player.inventorySlots;
    }

    public void sendSlotPacket(ItemStack itemstack, int i2) {
    }

    public void dropCreativeSlot(ItemStack item) {
    }
}

