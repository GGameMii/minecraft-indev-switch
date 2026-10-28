/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.item.recipe;

import net.minecraft.world.item.ItemStack;

public final class CraftingRecipe {
    private int width;
    private int height;
    private int[] ingredientMap;
    private ItemStack recipeOutput;

    public CraftingRecipe(int width, int height, int[] ingredientMap, ItemStack recipeOutput) {
        this.width = width;
        this.height = height;
        this.ingredientMap = ingredientMap;
        this.recipeOutput = recipeOutput;
    }

    public final boolean matchRecipe(int[] i1) {
        int i2 = 0;
        while (i2 <= 3 - this.width) {
            int i3 = 0;
            while (i3 <= 3 - this.height) {
                if (this.matches(i1, i2, i3, true)) {
                    return true;
                }
                if (this.matches(i1, i2, i3, false)) {
                    return true;
                }
                ++i3;
            }
            ++i2;
        }
        return false;
    }

    private boolean matches(int[] i1, int i2, int i3, boolean z4) {
        int i5 = 0;
        while (i5 < 3) {
            int i6 = 0;
            while (i6 < 3) {
                int i7 = i5 - i2;
                int i8 = i6 - i3;
                int i9 = -1;
                if (i7 >= 0 && i8 >= 0 && i7 < this.width && i8 < this.height) {
                    i9 = z4 ? this.ingredientMap[this.width - i7 - 1 + i8 * this.width] : this.ingredientMap[i7 + i8 * this.width];
                }
                if (i1[i5 + i6 * 3] != i9) {
                    return false;
                }
                ++i6;
            }
            ++i5;
        }
        return true;
    }

    public final ItemStack createResult() {
        return new ItemStack(this.recipeOutput.itemID, this.recipeOutput.stackSize);
    }

    public final int b() {
        return this.width * this.height;
    }
}

