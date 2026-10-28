/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.tile.Tile;

public final class ItemSeeds
extends Item {
    private int blockType;

    public ItemSeeds(int n2, int n3) {
        super(39);
        this.blockType = n3;
    }

    @Override
    public final boolean onItemUse(ItemStack itemStack, Level level, int n2, int n3, int n4, int n5, LivingEntity livingEntity) {
        if (n5 != 1) {
            return false;
        }
        if (n2 > 0 && n3 > 0 && n4 > 0 && n2 < level.width - 1 && n3 < level.height - 1 && n4 < level.depth - 1) {
            if (level.getTile(n2, n3, n4) == Tile.farmland.id) {
                level.setTile(n2, n3 + 1, n4, this.blockType);
                if (level.survivalWorld) {
                    --itemStack.stackSize;
                }
                return true;
            }
            return false;
        }
        return false;
    }
}

