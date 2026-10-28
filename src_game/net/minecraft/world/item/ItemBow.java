/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.item;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public final class ItemBow
extends Item {
    public ItemBow(int n2) {
        super(n2);
        this.maxStackSize = 1;
    }

    @Override
    public final ItemStack onItemRightClick(ItemStack itemStack, Level level, Player player) {
        if (!level.survivalWorld || player.inventory.consumeInventoryItem(Item.arrow.shiftedIndex)) {
            if (!level.onlineLevel) {
                level.playSoundAtEntity(player, "random.bow", 1.0f, 1.0f / (rand.nextFloat() * 0.4f + 0.8f));
            }
            if (!level.onlineLevel) {
                level.spawnEntityInWorld(new Arrow(level, player));
            }
        }
        return itemStack;
    }
}

