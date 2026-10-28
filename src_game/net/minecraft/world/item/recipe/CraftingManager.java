/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.item.recipe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import net.minecraft.world.container.InventoryCrafting;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.recipe.IRecipe;
import net.minecraft.world.item.recipe.RecipeSorter;
import net.minecraft.world.item.recipe.RecipesArmor;
import net.minecraft.world.item.recipe.RecipesCrafting;
import net.minecraft.world.item.recipe.RecipesFood;
import net.minecraft.world.item.recipe.RecipesIngots;
import net.minecraft.world.item.recipe.RecipesTools;
import net.minecraft.world.item.recipe.RecipesWeapons;
import net.minecraft.world.item.recipe.ShapedRecipes;
import net.minecraft.world.item.recipe.ShapelessRecipes;
import net.minecraft.world.level.tile.Tile;

public final class CraftingManager {
    private static final CraftingManager instance = new CraftingManager();
    private List<IRecipe> recipes = new ArrayList<IRecipe>();

    public static final CraftingManager getInstance() {
        return instance;
    }

    private CraftingManager() {
        new RecipesTools().addRecipes(this);
        new RecipesWeapons().addRecipes(this);
        new RecipesIngots().addRecipes(this);
        new RecipesFood();
        this.addRecipe(new ItemStack(Item.bowlSoup), "Y", "X", "#", Character.valueOf('X'), Tile.mushroomBrown, Character.valueOf('Y'), Tile.mushroomRed, Character.valueOf('#'), Item.bowlEmpty);
        this.addRecipe(new ItemStack(Item.bowlSoup), "Y", "X", "#", Character.valueOf('X'), Tile.mushroomRed, Character.valueOf('Y'), Tile.mushroomBrown, Character.valueOf('#'), Item.bowlEmpty);
        new RecipesCrafting();
        this.addRecipe(new ItemStack(Tile.chest), "###", "# #", "###", Character.valueOf('#'), Tile.planks);
        this.addRecipe(new ItemStack(Tile.furnace), "###", "# #", "###", Character.valueOf('#'), Tile.cobblestone);
        this.addRecipe(new ItemStack(Tile.workbench), "##", "##", Character.valueOf('#'), Tile.planks);
        new RecipesArmor().addRecipes(this);
        this.addRecipe(new ItemStack(Tile.clothGray, 1), "###", "###", "###", Character.valueOf('#'), Item.silk);
        this.addRecipe(new ItemStack(Tile.tnt, 1), "X#X", "#X#", "X#X", Character.valueOf('X'), Item.gunpowder, Character.valueOf('#'), Tile.sand);
        this.addRecipe(new ItemStack(Tile.stairSingle, 3), "###", Character.valueOf('#'), Tile.cobblestone);
        this.addRecipe(new ItemStack(Tile.planks, 4), "#", Character.valueOf('#'), Tile.wood);
        this.addRecipe(new ItemStack(Item.stick, 4), "#", "#", Character.valueOf('#'), Tile.planks);
        this.addRecipe(new ItemStack(Tile.torch, 4), "X", "#", Character.valueOf('X'), Item.coal, Character.valueOf('#'), Item.stick);
        this.addRecipe(new ItemStack(Item.bowlEmpty, 4), "# #", " # ", Character.valueOf('#'), Tile.planks);
        this.addRecipe(new ItemStack(Item.striker, 1), "A ", " B", Character.valueOf('A'), Item.ingotIron, Character.valueOf('B'), Item.flint);
        this.addRecipe(new ItemStack(Item.bread, 1), "###", Character.valueOf('#'), Item.wheat);
        this.addRecipe(new ItemStack(Item.painting, 1), "###", "#X#", "###", Character.valueOf('#'), Tile.planks, Character.valueOf('X'), Tile.clothGray);
        Collections.sort(this.recipes, new RecipeSorter(this));
        System.out.println(String.valueOf(this.recipes.size()) + " recipes");
    }

    /*
     * Unable to fully structure code
     */
    void addRecipe(ItemStack var1, Object ... var2) {
        String var3 = "";
        int var4 = 0;
        int var5 = 0;
        int var6 = 0;
        if (var2[var4] instanceof String[]) {
            String[] var11 = (String[])var2[var4++];
            for (int var8 = 0; var8 < var11.length; ++var8) {
                String var9 = var11[var8];
                ++var6;
                var5 = var9.length();
                var3 = var3 + var9;
            }
        } else {
            while (var4 < var2.length && var2[var4] instanceof String) {
                String var7 = (String)var2[var4++];
                ++var6;
                var5 = var7.length();
                var3 = var3 + var7;
            }
        }
        HashMap<Character, ItemStack> var12 = new HashMap<Character, ItemStack>();
        while (var4 < var2.length) {
            Character var13 = (Character)var2[var4];
            ItemStack var15 = null;
            if (var2[var4 + 1] instanceof Item) {
                var15 = new ItemStack((Item)var2[var4 + 1]);
            } else if (var2[var4 + 1] instanceof Tile) {
                var15 = new ItemStack((Tile)var2[var4 + 1], 1, -1);
            } else if (var2[var4 + 1] instanceof ItemStack) {
                var15 = (ItemStack)var2[var4 + 1];
            }
            var12.put(var13, var15);
            var4 += 2;
        }
        ItemStack[] var14 = new ItemStack[var5 * var6];
        for (int var16 = 0; var16 < var5 * var6; ++var16) {
            char var10 = var3.charAt(var16);
            var14[var16] = var12.containsKey(Character.valueOf(var10)) ? ((ItemStack)var12.get(Character.valueOf(var10))).copy() : null;
        }
        this.recipes.add(new ShapedRecipes(var5, var6, var14, var1));
    }

    void addShapelessRecipe(ItemStack var1, Object ... var2) {
        ArrayList<ItemStack> var3 = new ArrayList<ItemStack>();
        Object[] var4 = var2;
        int var5 = var2.length;
        int var6 = 0;
        while (var6 < var5) {
            Object var7 = var4[var6];
            if (var7 instanceof ItemStack) {
                var3.add(((ItemStack)var7).copy());
            } else if (var7 instanceof Item) {
                var3.add(new ItemStack((Item)var7));
            } else {
                if (!(var7 instanceof Tile)) {
                    throw new RuntimeException("Invalid shapeless recipy!");
                }
                var3.add(new ItemStack((Tile)var7));
            }
            ++var6;
        }
        this.recipes.add(new ShapelessRecipes(var1, var3));
    }

    public ItemStack findMatchingRecipe(InventoryCrafting var1) {
        int var2 = 0;
        while (var2 < this.recipes.size()) {
            IRecipe var3 = this.recipes.get(var2);
            if (var3.matches(var1)) {
                return var3.getCraftingResult(var1);
            }
            ++var2;
        }
        return null;
    }
}

