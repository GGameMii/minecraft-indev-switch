/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.item;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ItemFood
extends Item {
    private int healAmount;

    public ItemFood(int n2, int n3) {
        super(n2);
        this.healAmount = n3;
        this.maxStackSize = 1;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, Level level, Player player) {
        if (level.survivalWorld && !level.onlineLevel) {
            --itemStack.stackSize;
        }
        player.heal(this.healAmount);
        return itemStack;
    }
}

