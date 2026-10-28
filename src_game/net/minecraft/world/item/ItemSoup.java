/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.item;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemFood;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public final class ItemSoup
extends ItemFood {
    public ItemSoup(int n2, int n3) {
        super(26, 10);
    }

    @Override
    public final ItemStack onItemRightClick(ItemStack itemStack, Level level, Player player) {
        super.onItemRightClick(itemStack, level, player);
        return new ItemStack(Item.bowlEmpty);
    }
}

