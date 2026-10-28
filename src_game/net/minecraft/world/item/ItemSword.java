/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.tile.Tile;

public final class ItemSword
extends Item {
    private int weaponDamage;

    public ItemSword(int n2, int n3) {
        super(n2);
        this.maxStackSize = 1;
        this.maxDamage = 32 << n3;
        this.weaponDamage = 4 + (n3 << 1);
    }

    @Override
    public final float getStrVsBlock(Tile tile) {
        return 1.5f;
    }

    @Override
    public final void hitEntity(ItemStack itemStack, LivingEntity livingEntity) {
        if (livingEntity.level.survivalWorld) {
            itemStack.damageItem(1, livingEntity);
        }
    }

    @Override
    public final void onTileDestroyed(ItemStack itemStack, LivingEntity livingEntity) {
        if (livingEntity.level.survivalWorld) {
            itemStack.damageItem(2, livingEntity);
        }
    }

    @Override
    public final int getDamageVsEntity() {
        return this.weaponDamage;
    }
}

