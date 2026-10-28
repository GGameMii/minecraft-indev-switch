/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.tile.Tile;

public final class ItemHoe
extends Item {
    public ItemHoe(int n2, int n3) {
        super(n2);
        this.maxStackSize = 1;
        this.maxDamage = 32 << n3;
    }

    @Override
    public final boolean onItemUse(ItemStack itemStack, Level level, int n2, int n3, int n4, int n5, LivingEntity livingEntity) {
        if (n2 > 0 && n3 > 0 && n4 > 0 && n2 < level.width - 1 && n3 < level.height - 1 && n4 < level.depth - 1) {
            n5 = level.getTile(n2, n3, n4);
            if ((level.getMaterial(n2, n3 + 1, n4).isSolid() || n5 != Tile.grass.id) && n5 != Tile.dirt.id) {
                return false;
            }
            Tile tile = Tile.farmland;
            float f2 = (float)n2 + 0.5f;
            float f3 = (float)n3 + 0.5f;
            float f4 = (float)n4 + 0.5f;
            String string = tile.stepSound.getSndName();
            float f5 = (tile.stepSound.soundVolume + 1.0f) / 2.0f;
            level.playSoundAtPlayer(f2, f3, f4, string, f5, tile.stepSound.soundPitch * 0.8f);
            if (!level.onlineLevel) {
                level.setTile(n2, n3, n4, tile.id);
                if (level.survivalWorld) {
                    itemStack.damageItem(1, livingEntity);
                }
                if (level.random.nextInt(8) == 0 && n5 == Tile.grass.id && !level.onlineLevel) {
                    for (int i2 = 0; i2 <= 0; ++i2) {
                        float f6 = level.random.nextFloat() * 0.7f + 0.15f;
                        float f7 = level.random.nextFloat() * 0.7f + 0.15f;
                        ItemEntity itemEntity = new ItemEntity(level, (float)n2 + f6, (float)n3 + 1.2f, (float)n4 + f7, new ItemStack(Item.seeds));
                        itemEntity.delayBeforeCanPickup = 10;
                        level.spawnEntityInWorld(itemEntity);
                    }
                }
            }
            return true;
        }
        return false;
    }
}

