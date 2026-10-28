/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.tile.Tile;

public class ItemTool
extends Item {
    private Tile[] blocksEffectiveAgainst;
    private float efficiencyOnProperMaterial = 4.0f;
    private int damageVsEntity;

    public ItemTool(int n2, int n3, int n4, Tile[] tileArray) {
        super(n2);
        this.blocksEffectiveAgainst = tileArray;
        this.maxStackSize = 1;
        this.maxDamage = 32 << n4;
        this.efficiencyOnProperMaterial = n4 + 1 << 1;
        this.damageVsEntity = n3 + n4;
    }

    @Override
    public final float getStrVsBlock(Tile tile) {
        for (int i2 = 0; i2 < this.blocksEffectiveAgainst.length; ++i2) {
            if (this.blocksEffectiveAgainst[i2] != tile) continue;
            return this.efficiencyOnProperMaterial;
        }
        return 1.0f;
    }

    @Override
    public final void hitEntity(ItemStack itemStack, LivingEntity livingEntity) {
        if (livingEntity.level.survivalWorld) {
            itemStack.damageItem(2, livingEntity);
        }
    }

    @Override
    public final void onTileDestroyed(ItemStack itemStack, LivingEntity livingEntity) {
        if (livingEntity.level.survivalWorld) {
            itemStack.damageItem(1, livingEntity);
        }
    }

    @Override
    public final int getDamageVsEntity() {
        return this.damageVsEntity;
    }
}

