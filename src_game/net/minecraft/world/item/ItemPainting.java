/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Painting;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public final class ItemPainting
extends Item {
    public ItemPainting(int n2) {
        super(n2);
        this.maxDamage = 64;
    }

    @Override
    public final boolean onItemUse(ItemStack itemStack, Level level, int n2, int n3, int n4, int n5, LivingEntity livingEntity) {
        if (n5 == 0) {
            return false;
        }
        if (n5 == 1) {
            return false;
        }
        if (n2 > 0 && n3 > 0 && n4 > 0 && n2 < level.width - 1 && n3 < level.height - 1 && n4 < level.depth - 1) {
            Painting painting;
            int n6 = 0;
            if (n5 == 4) {
                n6 = 1;
            }
            if (n5 == 3) {
                n6 = 2;
            }
            if (n5 == 5) {
                n6 = 3;
            }
            if (!level.onlineLevel && (painting = new Painting(level, n2, n3, n4, n6)).onValidSurface()) {
                level.spawnEntityInWorld(painting);
                if (level.survivalWorld && !level.onlineLevel) {
                    --itemStack.stackSize;
                }
            }
            return true;
        }
        return false;
    }
}

