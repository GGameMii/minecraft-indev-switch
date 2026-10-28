/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.tile.Tile;

public final class ItemFlintAndSteel
extends Item {
    public ItemFlintAndSteel(int n2) {
        super(3);
        this.maxStackSize = 1;
        this.maxDamage = 64;
    }

    @Override
    public final boolean onItemUse(ItemStack itemStack, Level level, int n2, int n3, int n4, int n5, LivingEntity livingEntity) {
        if (n5 == 0) {
            --n3;
        }
        if (n5 == 1) {
            ++n3;
        }
        if (n5 == 2) {
            --n4;
        }
        if (n5 == 3) {
            ++n4;
        }
        if (n5 == 4) {
            --n2;
        }
        if (n5 == 5) {
            ++n2;
        }
        if (n2 > 0 && n3 > 0 && n4 > 0 && n2 < level.width - 1 && n3 < level.height - 1 && n4 < level.depth - 1) {
            if (level.getTile(n2, n3, n4) == 0) {
                level.playSoundAtPlayer((float)n2 + 0.5f, (float)n3 + 0.5f, (float)n4 + 0.5f, "fire.ignite", 1.0f, rand.nextFloat() * 0.4f + 0.8f);
                level.setTile(n2, n3, n4, Tile.fire.id);
            }
            itemStack.damageItem(1, livingEntity);
            return true;
        }
        return false;
    }
}

