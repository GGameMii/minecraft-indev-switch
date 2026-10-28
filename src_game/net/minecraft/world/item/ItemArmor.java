/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.item;

import net.minecraft.world.item.Item;

public final class ItemArmor
extends Item {
    private static final int[] damageReduceAmountArray = new int[]{3, 8, 6, 3};
    private static final int[] maxDamageArray = new int[]{11, 16, 15, 13};
    public final int armorType;
    public final int damageReduceAmount;
    public final int renderIndex;

    public ItemArmor(int n2, int n3, int n4, int n5) {
        super(n2);
        this.armorType = n5;
        this.renderIndex = n4;
        this.damageReduceAmount = damageReduceAmountArray[n5];
        this.maxDamage = maxDamageArray[n5] * 3 << n3;
        this.maxStackSize = 1;
    }
}

