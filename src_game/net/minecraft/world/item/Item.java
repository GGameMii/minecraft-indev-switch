/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.item;

import java.util.Random;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemArmor;
import net.minecraft.world.item.ItemAxe;
import net.minecraft.world.item.ItemBow;
import net.minecraft.world.item.ItemFlintAndSteel;
import net.minecraft.world.item.ItemFood;
import net.minecraft.world.item.ItemHoe;
import net.minecraft.world.item.ItemPainting;
import net.minecraft.world.item.ItemPickaxe;
import net.minecraft.world.item.ItemSeeds;
import net.minecraft.world.item.ItemSoup;
import net.minecraft.world.item.ItemSpade;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemSword;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.tile.Tile;

public class Item {
    protected static Random rand = new Random();
    public static Item[] itemsList = new Item[1024];
    public static Item shovel;
    public static Item pickaxeSteel;
    public static Item axeSteel;
    public static Item striker;
    public static Item apple;
    public static Item bow;
    public static Item arrow;
    public static Item coal;
    public static Item diamond;
    public static Item ingotIron;
    public static Item ingotGold;
    public static Item swordSteel;
    public static Item swordWood;
    public static Item shovelWood;
    public static Item pickaxeWood;
    public static Item axeWood;
    public static Item swordStone;
    public static Item shovelStone;
    public static Item pickaxeStone;
    public static Item axeStone;
    public static Item swordDiamond;
    public static Item shovelDiamond;
    public static Item pickaxeDiamond;
    public static Item axeDiamond;
    public static Item stick;
    public static Item bowlEmpty;
    public static Item bowlSoup;
    public static Item swordGold;
    public static Item shovelGold;
    public static Item pickaxeGold;
    public static Item axeGold;
    public static Item silk;
    public static Item feather;
    public static Item gunpowder;
    public static Item hoeWood;
    public static Item hoeStone;
    public static Item hoeSteel;
    public static Item hoeDiamond;
    public static Item hoeGold;
    public static Item seeds;
    public static Item wheat;
    public static Item bread;
    public static Item helmetLeather;
    public static Item plateLeather;
    public static Item legsLeather;
    public static Item bootsLeather;
    public static Item helmetChain;
    public static Item plateChain;
    public static Item legsChain;
    public static Item bootsChain;
    public static Item helmetSteel;
    public static Item plateSteel;
    public static Item legsSteel;
    public static Item bootsSteel;
    public static Item helmetDiamond;
    public static Item plateDiamond;
    public static Item legsDiamond;
    public static Item bootsDiamond;
    public static Item helmetGold;
    public static Item plateGold;
    public static Item legsGold;
    public static Item bootsGold;
    public static Item flint;
    public static Item porkRaw;
    public static Item porkCooked;
    public static Item painting;
    public final int shiftedIndex;
    protected int maxStackSize = 64;
    protected int maxDamage = 32;
    private int iconIndex;
    private Item containerItem = null;
    protected boolean hasSubtypes;

    protected Item(int n2) {
        this.shiftedIndex = n2 + 256;
        if (itemsList[n2 + 256] != null) {
            System.out.println("CONFLICT @ " + n2);
        }
        Item.itemsList[n2 + 256] = this;
    }

    public final Item setIconIndex(int n2) {
        this.iconIndex = n2;
        return this;
    }

    public final int getIconIndex() {
        return this.iconIndex;
    }

    public boolean onItemUse(ItemStack itemStack, Level level, int n2, int n3, int n4, int n5, LivingEntity livingEntity) {
        return false;
    }

    public float getStrVsBlock(Tile tile) {
        return 1.0f;
    }

    public ItemStack onItemRightClick(ItemStack itemStack, Level level, Player player) {
        return itemStack;
    }

    public final int getItemStackLimit() {
        return this.maxStackSize;
    }

    public final int getMaxDamage() {
        return this.maxDamage;
    }

    protected Item setMaxDamage(int n2) {
        this.maxDamage = n2;
        return this;
    }

    public boolean getHasSubtypes() {
        return this.hasSubtypes;
    }

    protected Item setHasSubtypes(boolean bl) {
        this.hasSubtypes = bl;
        return this;
    }

    public boolean isDamageable() {
        return this.maxDamage > 0 && !this.hasSubtypes;
    }

    public void hitEntity(ItemStack itemStack, LivingEntity livingEntity) {
    }

    public void onTileDestroyed(ItemStack itemStack, LivingEntity livingEntity) {
    }

    public int getDamageVsEntity() {
        return 1;
    }

    public boolean canHarvestBlock(Tile tile) {
        return false;
    }

    public Item setContainerItem(Item item) {
        if (this.maxStackSize > 1) {
            throw new IllegalArgumentException("Max stack size must be 1 for items with crafting results");
        }
        this.containerItem = item;
        return this;
    }

    public Item getContainerItem() {
        return this.containerItem;
    }

    public boolean hasContainerItem() {
        return this.containerItem != null;
    }

    static {
        ItemSpade itemSpade = new ItemSpade(0, 2);
        int n2 = 82;
        ItemSpade itemSpade2 = itemSpade;
        itemSpade.setIconIndex(n2);
        shovel = itemSpade2;
        ItemPickaxe itemPickaxe = new ItemPickaxe(1, 2);
        n2 = 98;
        ItemPickaxe itemPickaxe2 = itemPickaxe;
        itemPickaxe.setIconIndex(n2);
        pickaxeSteel = itemPickaxe2;
        ItemAxe itemAxe = new ItemAxe(2, 2);
        n2 = 114;
        ItemAxe itemAxe2 = itemAxe;
        itemAxe.setIconIndex(n2);
        axeSteel = itemAxe2;
        ItemFlintAndSteel itemFlintAndSteel = new ItemFlintAndSteel(3);
        n2 = 5;
        ItemFlintAndSteel itemFlintAndSteel2 = itemFlintAndSteel;
        itemFlintAndSteel.setIconIndex(n2);
        striker = itemFlintAndSteel2;
        ItemFood itemFood = new ItemFood(4, 4);
        n2 = 10;
        itemFood.setIconIndex(n2);
        apple = itemFood;
        ItemBow itemBow = new ItemBow(5);
        n2 = 21;
        ItemBow itemBow2 = itemBow;
        itemBow.setIconIndex(n2);
        bow = itemBow2;
        Item item = new Item(6);
        n2 = 37;
        Item item2 = item;
        item.setIconIndex(n2);
        arrow = item2;
        item = new Item(7);
        n2 = 7;
        item2 = item;
        item.setIconIndex(n2);
        coal = item2;
        item = new Item(8);
        n2 = 55;
        item2 = item;
        item.setIconIndex(n2);
        diamond = item2;
        item = new Item(9);
        n2 = 23;
        item2 = item;
        item.setIconIndex(n2);
        ingotIron = item2;
        item = new Item(10);
        n2 = 39;
        item2 = item;
        item.setIconIndex(n2);
        ingotGold = item2;
        ItemSword itemSword = new ItemSword(11, 2);
        n2 = 66;
        ItemSword itemSword2 = itemSword;
        itemSword.setIconIndex(n2);
        swordSteel = itemSword2;
        itemSword = new ItemSword(12, 0);
        n2 = 64;
        itemSword2 = itemSword;
        itemSword.setIconIndex(n2);
        swordWood = itemSword2;
        itemSpade = new ItemSpade(13, 0);
        n2 = 80;
        itemSpade2 = itemSpade;
        itemSpade.setIconIndex(n2);
        shovelWood = itemSpade2;
        itemPickaxe = new ItemPickaxe(14, 0);
        n2 = 96;
        itemPickaxe2 = itemPickaxe;
        itemPickaxe.setIconIndex(n2);
        pickaxeWood = itemPickaxe2;
        itemAxe = new ItemAxe(15, 0);
        n2 = 112;
        itemAxe2 = itemAxe;
        itemAxe.setIconIndex(n2);
        axeWood = itemAxe2;
        itemSword = new ItemSword(16, 1);
        n2 = 65;
        itemSword2 = itemSword;
        itemSword.setIconIndex(n2);
        swordStone = itemSword2;
        itemSpade = new ItemSpade(17, 1);
        n2 = 81;
        itemSpade2 = itemSpade;
        itemSpade.setIconIndex(n2);
        shovelStone = itemSpade2;
        itemPickaxe = new ItemPickaxe(18, 1);
        n2 = 97;
        itemPickaxe2 = itemPickaxe;
        itemPickaxe.setIconIndex(n2);
        pickaxeStone = itemPickaxe2;
        itemAxe = new ItemAxe(19, 1);
        n2 = 113;
        itemAxe2 = itemAxe;
        itemAxe.setIconIndex(n2);
        axeStone = itemAxe2;
        itemSword = new ItemSword(20, 3);
        n2 = 67;
        itemSword2 = itemSword;
        itemSword.setIconIndex(n2);
        swordDiamond = itemSword2;
        itemSpade = new ItemSpade(21, 3);
        n2 = 83;
        itemSpade2 = itemSpade;
        itemSpade.setIconIndex(n2);
        shovelDiamond = itemSpade2;
        itemPickaxe = new ItemPickaxe(22, 3);
        n2 = 99;
        itemPickaxe2 = itemPickaxe;
        itemPickaxe.setIconIndex(n2);
        pickaxeDiamond = itemPickaxe2;
        itemAxe = new ItemAxe(23, 3);
        n2 = 115;
        itemAxe2 = itemAxe;
        itemAxe.setIconIndex(n2);
        axeDiamond = itemAxe2;
        item = new Item(24);
        n2 = 53;
        item2 = item;
        item.setIconIndex(n2);
        stick = item2;
        item = new Item(25);
        n2 = 71;
        item2 = item;
        item.setIconIndex(n2);
        bowlEmpty = item2;
        ItemSoup itemSoup = new ItemSoup(26, 10);
        n2 = 72;
        ItemSoup itemSoup2 = itemSoup;
        itemSoup.setIconIndex(n2);
        bowlSoup = itemSoup2;
        itemSword = new ItemSword(27, 0);
        n2 = 68;
        itemSword2 = itemSword;
        itemSword.setIconIndex(n2);
        swordGold = itemSword2;
        itemSpade = new ItemSpade(28, 0);
        n2 = 84;
        itemSpade2 = itemSpade;
        itemSpade.setIconIndex(n2);
        shovelGold = itemSpade2;
        itemPickaxe = new ItemPickaxe(29, 0);
        n2 = 100;
        itemPickaxe2 = itemPickaxe;
        itemPickaxe.setIconIndex(n2);
        pickaxeGold = itemPickaxe2;
        itemAxe = new ItemAxe(30, 0);
        n2 = 116;
        itemAxe2 = itemAxe;
        itemAxe.setIconIndex(n2);
        axeGold = itemAxe2;
        item = new Item(31);
        n2 = 8;
        item2 = item;
        item.setIconIndex(n2);
        silk = item2;
        item = new Item(32);
        n2 = 24;
        item2 = item;
        item.setIconIndex(n2);
        feather = item2;
        item = new Item(33);
        n2 = 40;
        item2 = item;
        item.setIconIndex(n2);
        gunpowder = item2;
        ItemHoe itemHoe = new ItemHoe(34, 0);
        int n3 = 128;
        ItemHoe itemHoe2 = itemHoe;
        itemHoe.setIconIndex(n3);
        hoeWood = itemHoe2;
        itemHoe = new ItemHoe(35, 1);
        n3 = 129;
        itemHoe2 = itemHoe;
        itemHoe.setIconIndex(n3);
        hoeStone = itemHoe2;
        itemHoe = new ItemHoe(36, 2);
        n3 = 130;
        itemHoe2 = itemHoe;
        itemHoe.setIconIndex(n3);
        hoeSteel = itemHoe2;
        itemHoe = new ItemHoe(37, 3);
        n3 = 131;
        itemHoe2 = itemHoe;
        itemHoe.setIconIndex(n3);
        hoeDiamond = itemHoe2;
        itemHoe = new ItemHoe(38, 4);
        n3 = 132;
        itemHoe2 = itemHoe;
        itemHoe.setIconIndex(n3);
        hoeGold = itemHoe2;
        ItemSeeds itemSeeds = new ItemSeeds(39, Tile.crops.id);
        n2 = 9;
        ItemSeeds itemSeeds2 = itemSeeds;
        itemSeeds.setIconIndex(n2);
        seeds = itemSeeds2;
        item = new Item(40);
        n2 = 25;
        item2 = item;
        item.setIconIndex(n2);
        wheat = item2;
        itemFood = new ItemFood(41, 5);
        n2 = 41;
        ItemFood itemFood2 = itemFood;
        itemFood.setIconIndex(n2);
        bread = itemFood2;
        ItemArmor itemArmor = new ItemArmor(42, 0, 0, 0);
        n2 = 0;
        ItemArmor itemArmor2 = itemArmor;
        itemArmor.setIconIndex(n2);
        helmetLeather = itemArmor2;
        itemArmor = new ItemArmor(43, 0, 0, 1);
        n2 = 16;
        itemArmor2 = itemArmor;
        itemArmor.setIconIndex(n2);
        plateLeather = itemArmor2;
        itemArmor = new ItemArmor(44, 0, 0, 2);
        n2 = 32;
        itemArmor2 = itemArmor;
        itemArmor.setIconIndex(n2);
        legsLeather = itemArmor2;
        itemArmor = new ItemArmor(45, 0, 0, 3);
        n2 = 48;
        itemArmor2 = itemArmor;
        itemArmor.setIconIndex(n2);
        bootsLeather = itemArmor2;
        itemArmor = new ItemArmor(46, 1, 1, 0);
        n2 = 1;
        itemArmor2 = itemArmor;
        itemArmor.setIconIndex(n2);
        helmetChain = itemArmor2;
        itemArmor = new ItemArmor(47, 1, 1, 1);
        n2 = 17;
        itemArmor2 = itemArmor;
        itemArmor.setIconIndex(n2);
        plateChain = itemArmor2;
        itemArmor = new ItemArmor(48, 1, 1, 2);
        n2 = 33;
        itemArmor2 = itemArmor;
        itemArmor.setIconIndex(n2);
        legsChain = itemArmor2;
        itemArmor = new ItemArmor(49, 1, 1, 3);
        n2 = 49;
        itemArmor2 = itemArmor;
        itemArmor.setIconIndex(n2);
        bootsChain = itemArmor2;
        itemArmor = new ItemArmor(50, 2, 2, 0);
        n2 = 2;
        itemArmor2 = itemArmor;
        itemArmor.setIconIndex(n2);
        helmetSteel = itemArmor2;
        itemArmor = new ItemArmor(51, 2, 2, 1);
        n2 = 18;
        itemArmor2 = itemArmor;
        itemArmor.setIconIndex(n2);
        plateSteel = itemArmor2;
        itemArmor = new ItemArmor(52, 2, 2, 2);
        n2 = 34;
        itemArmor2 = itemArmor;
        itemArmor.setIconIndex(n2);
        legsSteel = itemArmor2;
        itemArmor = new ItemArmor(53, 2, 2, 3);
        n2 = 50;
        itemArmor2 = itemArmor;
        itemArmor.setIconIndex(n2);
        bootsSteel = itemArmor2;
        itemArmor = new ItemArmor(54, 3, 3, 0);
        n2 = 3;
        itemArmor2 = itemArmor;
        itemArmor.setIconIndex(n2);
        helmetDiamond = itemArmor2;
        itemArmor = new ItemArmor(55, 3, 3, 1);
        n2 = 19;
        itemArmor2 = itemArmor;
        itemArmor.setIconIndex(n2);
        plateDiamond = itemArmor2;
        itemArmor = new ItemArmor(56, 3, 3, 2);
        n2 = 35;
        itemArmor2 = itemArmor;
        itemArmor.setIconIndex(n2);
        legsDiamond = itemArmor2;
        itemArmor = new ItemArmor(57, 3, 3, 3);
        n2 = 51;
        itemArmor2 = itemArmor;
        itemArmor.setIconIndex(n2);
        bootsDiamond = itemArmor2;
        itemArmor = new ItemArmor(58, 1, 4, 0);
        n2 = 4;
        itemArmor2 = itemArmor;
        itemArmor.setIconIndex(n2);
        helmetGold = itemArmor2;
        itemArmor = new ItemArmor(59, 1, 4, 1);
        n2 = 20;
        itemArmor2 = itemArmor;
        itemArmor.setIconIndex(n2);
        plateGold = itemArmor2;
        itemArmor = new ItemArmor(60, 1, 4, 2);
        n2 = 36;
        itemArmor2 = itemArmor;
        itemArmor.setIconIndex(n2);
        legsGold = itemArmor2;
        itemArmor = new ItemArmor(61, 1, 4, 3);
        n2 = 52;
        itemArmor2 = itemArmor;
        itemArmor.setIconIndex(n2);
        bootsGold = itemArmor2;
        item = new Item(62);
        n2 = 6;
        item2 = item;
        item.setIconIndex(n2);
        flint = item2;
        itemFood = new ItemFood(63, 3);
        n2 = 87;
        itemFood2 = itemFood;
        itemFood.setIconIndex(n2);
        porkRaw = itemFood2;
        itemFood = new ItemFood(64, 8);
        n2 = 88;
        itemFood2 = itemFood;
        itemFood.setIconIndex(n2);
        porkCooked = itemFood2;
        ItemPainting itemPainting = new ItemPainting(65);
        n2 = 26;
        ItemPainting itemPainting2 = itemPainting;
        itemPainting.setIconIndex(n2);
        painting = itemPainting2;
    }
}

