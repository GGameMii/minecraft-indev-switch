/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.tile.Tile;

public final class ItemBlock
extends Item {
    private int blockID;

    public ItemBlock(int i1) {
        super(i1);
        this.blockID = i1 + 256;
        this.setIconIndex(Tile.tiles[i1 + 256].getTexture(2));
    }

    @Override
    public final boolean onItemUse(ItemStack itemStack, Level world, int x2, int y2, int z2, int f2, LivingEntity e2) {
        if (f2 == 0) {
            --y2;
        }
        if (f2 == 1) {
            ++y2;
        }
        if (f2 == 2) {
            --z2;
        }
        if (f2 == 3) {
            ++z2;
        }
        if (f2 == 4) {
            --x2;
        }
        if (f2 == 5) {
            ++x2;
        }
        if (itemStack.stackSize == 0) {
            return false;
        }
        if (x2 > 0 && y2 > 0 && z2 > 0 && x2 < world.width - 1 && y2 < world.height - 1 && z2 < world.depth - 1) {
            int id = world.getTile(x2, y2, z2);
            Tile tile = Tile.tiles[id];
            if (this.blockID > 0 && tile == null || tile == Tile.water || tile == Tile.waterCalm || tile == Tile.lava || tile == Tile.lavaCalm || tile == Tile.fire) {
                tile = Tile.tiles[this.blockID];
                if (world.canBlockBePlacedAt(this.blockID, x2, y2, z2, false, f2) && world.setTile(x2, y2, z2, this.blockID)) {
                    Tile.tiles[this.blockID].onBlockPlaced(world, x2, y2, z2, f2);
                    float xx = (float)x2 + 0.5f;
                    float yy = (float)y2 + 0.5f;
                    float zz = (float)z2 + 0.5f;
                    String name = tile.stepSound.getSndName();
                    float vol = (tile.stepSound.soundVolume + 1.0f) / 2.0f;
                    world.playSoundAtPlayer(xx, yy, zz, name, vol, tile.stepSound.soundPitch * 0.8f);
                    if (world.survivalWorld) {
                        --itemStack.stackSize;
                    }
                }
            }
            return true;
        }
        return false;
    }
}

