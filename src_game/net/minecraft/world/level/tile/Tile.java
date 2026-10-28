/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.level.tile;

import java.util.Random;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.InventoryPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemBlock;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.tile.BookshelfTile;
import net.minecraft.world.level.tile.ChestTile;
import net.minecraft.world.level.tile.CropTile;
import net.minecraft.world.level.tile.DirtTile;
import net.minecraft.world.level.tile.FallingTile;
import net.minecraft.world.level.tile.FarmlandTile;
import net.minecraft.world.level.tile.FireTile;
import net.minecraft.world.level.tile.FlowerTile;
import net.minecraft.world.level.tile.FlowingTile;
import net.minecraft.world.level.tile.FurnaceTile;
import net.minecraft.world.level.tile.GearTile;
import net.minecraft.world.level.tile.GlassTile;
import net.minecraft.world.level.tile.GrassTile;
import net.minecraft.world.level.tile.GravelTile;
import net.minecraft.world.level.tile.LeavesTile;
import net.minecraft.world.level.tile.LiquidSourceTile;
import net.minecraft.world.level.tile.MushroomTile;
import net.minecraft.world.level.tile.OreStorageTile;
import net.minecraft.world.level.tile.OreTile;
import net.minecraft.world.level.tile.SaplingTile;
import net.minecraft.world.level.tile.SlabTile;
import net.minecraft.world.level.tile.SpongeTile;
import net.minecraft.world.level.tile.StationaryTile;
import net.minecraft.world.level.tile.StepSound;
import net.minecraft.world.level.tile.StepSoundGlass;
import net.minecraft.world.level.tile.StepSoundSand;
import net.minecraft.world.level.tile.StoneTile;
import net.minecraft.world.level.tile.TntTile;
import net.minecraft.world.level.tile.TorchTile;
import net.minecraft.world.level.tile.WoodTile;
import net.minecraft.world.level.tile.WorkbenchTile;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class Tile {
    private static StepSound soundPowder = new StepSound("stone", 1.0f, 1.0f);
    private static StepSound soundWood = new StepSound("wood", 1.0f, 1.0f);
    private static StepSound soundGravel = new StepSound("gravel", 1.0f, 1.0f);
    private static StepSound soundGrass = new StepSound("grass", 1.0f, 1.0f);
    private static StepSound soundStone = new StepSound("stone", 1.0f, 1.0f);
    private static StepSound soundMetal = new StepSound("stone", 1.0f, 1.5f);
    private static StepSound soundGlass = new StepSoundGlass("stone", 1.0f, 1.0f);
    private static StepSound soundCloth = new StepSound("cloth", 1.0f, 1.0f);
    private static StepSound soundSand = new StepSoundSand("sand", 1.0f, 1.0f);
    public static final Tile[] tiles = new Tile[256];
    public static final boolean[] shouldTick = new boolean[256];
    public static final boolean[] opaqueCubeLookup = new boolean[256];
    public static final int[] lightOpacity = new int[256];
    private static boolean[] canBlockGrass = new boolean[256];
    public static final boolean[] isBlockFluid = new boolean[256];
    public static final int[] lightValue = new int[256];
    public static final Tile stone;
    public static final GrassTile grass;
    public static final Tile dirt;
    public static final Tile cobblestone;
    public static final Tile planks;
    public static final Tile sapling;
    public static final Tile bedrock;
    public static final Tile water;
    public static final Tile waterCalm;
    public static final Tile lava;
    public static final Tile lavaCalm;
    public static final Tile sand;
    public static final Tile gravel;
    public static final Tile oreGold;
    public static final Tile oreIron;
    public static final Tile oreCoal;
    public static final Tile wood;
    public static final Tile leaves;
    public static final Tile sponge;
    public static final Tile glass;
    public static final Tile clothRed;
    public static final Tile clothOrange;
    public static final Tile clothYellow;
    public static final Tile clothChartreuse;
    public static final Tile clothGreen;
    public static final Tile clothSpringGreen;
    public static final Tile clothCyan;
    public static final Tile clothCapri;
    public static final Tile clothUltramarine;
    public static final Tile clothViolet;
    public static final Tile clothPurple;
    public static final Tile clothMagenta;
    public static final Tile clothRose;
    public static final Tile clothDarkGray;
    public static final Tile clothGray;
    public static final Tile clothWhite;
    public static final FlowerTile plantYellow;
    public static final FlowerTile plantRed;
    public static final FlowerTile mushroomBrown;
    public static final FlowerTile mushroomRed;
    public static final Tile blockGold;
    public static final Tile blockIron;
    public static final Tile stairDouble;
    public static final Tile stairSingle;
    public static final Tile brick;
    public static final Tile tnt;
    public static final Tile bookShelf;
    public static final Tile cobblestoneMossy;
    public static final Tile obsidian;
    public static final Tile torch;
    public static final FireTile fire;
    public static final Tile waterSource;
    public static final Tile lavaSource;
    public static final Tile chest;
    public static final Tile cog;
    public static final Tile oreDiamond;
    public static final Tile blockDiamond;
    public static final Tile workbench;
    public static final Tile crops;
    public static final Tile farmland;
    public static final Tile furnace;
    public static final Tile furnaceActive;
    public int tex;
    public final int id;
    private float hardness;
    private float resistance;
    public float xx0;
    public float yy0;
    public float zz0;
    public float xx1;
    public float yy1;
    public float zz1;
    public StepSound stepSound = soundPowder;
    public float blockParticleGravity = 1.0f;
    public final Material material;

    static {
        Tile block10000 = new StoneTile(1, 1).setHardness(1.5f).setResistance(10.0f);
        StepSound stepSound1 = soundStone;
        Tile block0 = block10000;
        block10000.stepSound = stepSound1;
        stone = block0;
        block10000 = new GrassTile(2).setHardness(0.6f);
        stepSound1 = soundGrass;
        block0 = block10000;
        block10000.stepSound = stepSound1;
        grass = (GrassTile)block0;
        block10000 = new DirtTile(3, 2).setHardness(0.5f);
        stepSound1 = soundGravel;
        block0 = block10000;
        block10000.stepSound = stepSound1;
        dirt = block0;
        block10000 = new Tile(4, 16, Material.rock).setHardness(2.0f).setResistance(10.0f);
        stepSound1 = soundStone;
        block0 = block10000;
        block10000.stepSound = stepSound1;
        cobblestone = block0;
        block10000 = new Tile(5, 4, Material.wood).setHardness(2.0f).setResistance(5.0f);
        stepSound1 = soundWood;
        block0 = block10000;
        block10000.stepSound = stepSound1;
        planks = block0;
        block10000 = new SaplingTile(6, 15).setHardness(0.0f);
        stepSound1 = soundGrass;
        block0 = block10000;
        block10000.stepSound = stepSound1;
        sapling = block0;
        block10000 = new Tile(7, 17, Material.rock).setHardness(-1.0f).setResistance(6000000.0f);
        stepSound1 = soundStone;
        block0 = block10000;
        block10000.stepSound = stepSound1;
        bedrock = block0;
        water = new FlowingTile(8, Material.water).setHardness(100.0f).setLightOpacity(3);
        waterCalm = new StationaryTile(9, Material.water).setHardness(100.0f).setLightOpacity(3);
        lava = new FlowingTile(10, Material.lava).setHardness(0.0f).setLightValue(1.0f).setLightOpacity(255);
        lavaCalm = new StationaryTile(11, Material.lava).setHardness(100.0f).setLightValue(1.0f).setLightOpacity(255);
        block10000 = new FallingTile(12, 18).setHardness(0.5f);
        stepSound1 = soundSand;
        block0 = block10000;
        block10000.stepSound = stepSound1;
        sand = block0;
        block10000 = new GravelTile(13, 19).setHardness(0.6f);
        stepSound1 = soundGravel;
        block0 = block10000;
        block10000.stepSound = stepSound1;
        gravel = block0;
        block10000 = new OreTile(14, 32).setHardness(3.0f).setResistance(5.0f);
        stepSound1 = soundStone;
        block0 = block10000;
        block10000.stepSound = stepSound1;
        oreGold = block0;
        block10000 = new OreTile(15, 33).setHardness(3.0f).setResistance(5.0f);
        stepSound1 = soundStone;
        block0 = block10000;
        block10000.stepSound = stepSound1;
        oreIron = block0;
        block10000 = new OreTile(16, 34).setHardness(3.0f).setResistance(5.0f);
        stepSound1 = soundStone;
        block0 = block10000;
        block10000.stepSound = stepSound1;
        oreCoal = block0;
        block10000 = new WoodTile(17).setHardness(2.0f);
        stepSound1 = soundWood;
        block0 = block10000;
        block10000.stepSound = stepSound1;
        wood = block0;
        block10000 = new LeavesTile(18, 52).setHardness(0.2f).setLightOpacity(1);
        stepSound1 = soundGrass;
        block0 = block10000;
        block10000.stepSound = stepSound1;
        leaves = block0;
        block10000 = new SpongeTile(19).setHardness(0.6f);
        stepSound1 = soundGrass;
        block0 = block10000;
        block10000.stepSound = stepSound1;
        sponge = block0;
        block10000 = new GlassTile(20, 49, Material.glass, false).setHardness(0.3f);
        stepSound1 = soundGlass;
        block0 = block10000;
        block10000.stepSound = stepSound1;
        glass = block0;
        block10000 = new Tile(21, 64, Material.cloth).setHardness(0.8f);
        stepSound1 = soundCloth;
        block0 = block10000;
        block10000.stepSound = stepSound1;
        clothRed = block0;
        block10000 = new Tile(22, 65, Material.cloth).setHardness(0.8f);
        stepSound1 = soundCloth;
        block0 = block10000;
        block10000.stepSound = stepSound1;
        clothOrange = block0;
        block10000 = new Tile(23, 66, Material.cloth).setHardness(0.8f);
        stepSound1 = soundCloth;
        block0 = block10000;
        block10000.stepSound = stepSound1;
        clothYellow = block0;
        block10000 = new Tile(24, 67, Material.cloth).setHardness(0.8f);
        stepSound1 = soundCloth;
        block0 = block10000;
        block10000.stepSound = stepSound1;
        clothChartreuse = block0;
        block10000 = new Tile(25, 68, Material.cloth).setHardness(0.8f);
        stepSound1 = soundCloth;
        block0 = block10000;
        block10000.stepSound = stepSound1;
        clothGreen = block0;
        block10000 = new Tile(26, 69, Material.cloth).setHardness(0.8f);
        stepSound1 = soundCloth;
        block0 = block10000;
        block10000.stepSound = stepSound1;
        clothSpringGreen = block0;
        block10000 = new Tile(27, 70, Material.cloth).setHardness(0.8f);
        stepSound1 = soundCloth;
        block0 = block10000;
        block10000.stepSound = stepSound1;
        clothCyan = block0;
        block10000 = new Tile(28, 71, Material.cloth).setHardness(0.8f);
        stepSound1 = soundCloth;
        block0 = block10000;
        block10000.stepSound = stepSound1;
        clothCapri = block0;
        block10000 = new Tile(29, 72, Material.cloth).setHardness(0.8f);
        stepSound1 = soundCloth;
        block0 = block10000;
        block10000.stepSound = stepSound1;
        clothUltramarine = block0;
        block10000 = new Tile(30, 73, Material.cloth).setHardness(0.8f);
        stepSound1 = soundCloth;
        block0 = block10000;
        block10000.stepSound = stepSound1;
        clothViolet = block0;
        block10000 = new Tile(31, 74, Material.cloth).setHardness(0.8f);
        stepSound1 = soundCloth;
        block0 = block10000;
        block10000.stepSound = stepSound1;
        clothPurple = block0;
        block10000 = new Tile(32, 75, Material.cloth).setHardness(0.8f);
        stepSound1 = soundCloth;
        block0 = block10000;
        block10000.stepSound = stepSound1;
        clothMagenta = block0;
        block10000 = new Tile(33, 76, Material.cloth).setHardness(0.8f);
        stepSound1 = soundCloth;
        block0 = block10000;
        block10000.stepSound = stepSound1;
        clothRose = block0;
        block10000 = new Tile(34, 77, Material.cloth).setHardness(0.8f);
        stepSound1 = soundCloth;
        block0 = block10000;
        block10000.stepSound = stepSound1;
        clothDarkGray = block0;
        block10000 = new Tile(35, 78, Material.cloth).setHardness(0.8f);
        stepSound1 = soundCloth;
        block0 = block10000;
        block10000.stepSound = stepSound1;
        clothGray = block0;
        block10000 = new Tile(36, 79, Material.cloth).setHardness(0.8f);
        stepSound1 = soundCloth;
        block0 = block10000;
        block10000.stepSound = stepSound1;
        clothWhite = block0;
        block10000 = new FlowerTile(37, 13).setHardness(0.0f);
        stepSound1 = soundGrass;
        block0 = block10000;
        block10000.stepSound = stepSound1;
        plantYellow = (FlowerTile)block0;
        block10000 = new FlowerTile(38, 12).setHardness(0.0f);
        stepSound1 = soundGrass;
        block0 = block10000;
        block10000.stepSound = stepSound1;
        plantRed = (FlowerTile)block0;
        block10000 = new MushroomTile(39, 29).setHardness(0.0f);
        stepSound1 = soundGrass;
        block0 = block10000;
        block10000.stepSound = stepSound1;
        mushroomBrown = (FlowerTile)block0.setLightValue(0.125f);
        block10000 = new MushroomTile(40, 28).setHardness(0.0f);
        stepSound1 = soundGrass;
        block0 = block10000;
        block10000.stepSound = stepSound1;
        mushroomRed = (FlowerTile)block0;
        block10000 = new OreStorageTile(41, 39).setHardness(3.0f).setResistance(10.0f);
        stepSound1 = soundMetal;
        block0 = block10000;
        block10000.stepSound = stepSound1;
        blockGold = block0;
        block10000 = new OreStorageTile(42, 38).setHardness(5.0f).setResistance(10.0f);
        stepSound1 = soundMetal;
        block0 = block10000;
        block10000.stepSound = stepSound1;
        blockIron = block0;
        block10000 = new SlabTile(43, true).setHardness(2.0f).setResistance(10.0f);
        stepSound1 = soundStone;
        block0 = block10000;
        block10000.stepSound = stepSound1;
        stairDouble = block0;
        block10000 = new SlabTile(44, false).setHardness(2.0f).setResistance(10.0f);
        stepSound1 = soundStone;
        block0 = block10000;
        block10000.stepSound = stepSound1;
        stairSingle = block0;
        block10000 = new Tile(45, 7, Material.rock).setHardness(2.0f).setResistance(10.0f);
        stepSound1 = soundStone;
        block0 = block10000;
        block10000.stepSound = stepSound1;
        brick = block0;
        block10000 = new TntTile(46, 8).setHardness(0.0f);
        stepSound1 = soundGrass;
        block0 = block10000;
        block10000.stepSound = stepSound1;
        tnt = block0;
        block10000 = new BookshelfTile(47, 35).setHardness(1.5f);
        stepSound1 = soundWood;
        block0 = block10000;
        block10000.stepSound = stepSound1;
        bookShelf = block0;
        block10000 = new Tile(48, 36, Material.rock).setHardness(2.0f).setResistance(10.0f);
        stepSound1 = soundStone;
        block0 = block10000;
        block10000.stepSound = stepSound1;
        cobblestoneMossy = block0;
        block10000 = new StoneTile(49, 37).setHardness(10.0f).setResistance(10.0f);
        stepSound1 = soundStone;
        block0 = block10000;
        block10000.stepSound = stepSound1;
        obsidian = block0;
        block10000 = new TorchTile(50, 80).setHardness(0.0f).setLightValue(0.875f);
        stepSound1 = soundWood;
        block0 = block10000;
        block10000.stepSound = stepSound1;
        torch = block0;
        block10000 = new FireTile(51, 31).setHardness(0.0f).setLightValue(1.0f);
        stepSound1 = soundWood;
        block0 = block10000;
        block10000.stepSound = stepSound1;
        fire = (FireTile)block0;
        block10000 = new LiquidSourceTile(52, Tile.water.id).setHardness(0.0f);
        stepSound1 = soundWood;
        block0 = block10000;
        block10000.stepSound = stepSound1;
        waterSource = block0;
        block10000 = new LiquidSourceTile(53, Tile.lava.id).setHardness(0.0f);
        stepSound1 = soundWood;
        block0 = block10000;
        block10000.stepSound = stepSound1;
        lavaSource = block0;
        block10000 = new ChestTile(54).setHardness(2.5f);
        stepSound1 = soundWood;
        block0 = block10000;
        block10000.stepSound = stepSound1;
        chest = block0;
        block10000 = new GearTile(55, 62).setHardness(0.5f);
        stepSound1 = soundMetal;
        block0 = block10000;
        block10000.stepSound = stepSound1;
        cog = block0;
        block10000 = new OreTile(56, 50).setHardness(3.0f).setResistance(5.0f);
        stepSound1 = soundStone;
        block0 = block10000;
        block10000.stepSound = stepSound1;
        oreDiamond = block0;
        block10000 = new OreStorageTile(57, 40).setHardness(5.0f).setResistance(10.0f);
        stepSound1 = soundMetal;
        block0 = block10000;
        block10000.stepSound = stepSound1;
        blockDiamond = block0;
        block10000 = new WorkbenchTile(58).setHardness(2.5f);
        stepSound1 = soundWood;
        block0 = block10000;
        block10000.stepSound = stepSound1;
        workbench = block0;
        block10000 = new CropTile(59, 88).setHardness(0.0f);
        stepSound1 = soundGrass;
        block0 = block10000;
        block10000.stepSound = stepSound1;
        crops = block0;
        block10000 = new FarmlandTile(60).setHardness(0.6f);
        stepSound1 = soundGravel;
        block0 = block10000;
        block10000.stepSound = stepSound1;
        farmland = block0;
        block10000 = new FurnaceTile(61, false).setHardness(3.5f);
        stepSound1 = soundStone;
        block0 = block10000;
        block10000.stepSound = stepSound1;
        furnace = block0;
        block10000 = new FurnaceTile(62, true).setHardness(3.5f);
        stepSound1 = soundStone;
        block0 = block10000;
        block10000.stepSound = stepSound1;
        furnaceActive = block0.setLightValue(0.875f);
        int id = 0;
        while (id < 256) {
            if (tiles[id] != null) {
                Item.itemsList[id] = new ItemBlock(id - 256);
            }
            ++id;
        }
    }

    protected Tile(int id, Material material) {
        if (tiles[id] != null) {
            throw new IllegalArgumentException("Slot " + id + " is already occupied by " + tiles[id] + " when adding " + this);
        }
        this.material = material;
        Tile.tiles[id] = this;
        this.id = id;
        this.setShape(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        Tile.opaqueCubeLookup[id] = this.isSolid();
        Tile.lightOpacity[id] = this.isSolid() ? 255 : 0;
        Tile.canBlockGrass[id] = this.renderAsNormalBlock();
        Tile.isBlockFluid[id] = false;
    }

    protected Tile(int id, int tex, Material material) {
        this(id, material);
        this.tex = tex;
    }

    protected final Tile setLightOpacity(int i1) {
        Tile.lightOpacity[this.id] = i1;
        return this;
    }

    private Tile setLightValue(float f1) {
        Tile.lightValue[this.id] = (int)(15.0f * f1);
        return this;
    }

    protected final Tile setResistance(float f1) {
        this.resistance = f1 * 3.0f;
        return this;
    }

    public boolean renderAsNormalBlock() {
        return true;
    }

    public int getRenderType() {
        return 0;
    }

    protected final Tile setHardness(float f1) {
        this.hardness = f1;
        if (this.resistance < f1 * 5.0f) {
            this.resistance = f1 * 5.0f;
        }
        return this;
    }

    protected final void setTicking(boolean z1) {
        Tile.shouldTick[this.id] = z1;
    }

    protected final void setShape(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        this.xx0 = minX;
        this.yy0 = minY;
        this.zz0 = minZ;
        this.xx1 = maxX;
        this.yy1 = maxY;
        this.zz1 = maxZ;
    }

    public float getBrightness(Level world, int x2, int y2, int z2) {
        return world.getLightBrightness(x2, y2, z2);
    }

    public boolean shouldRenderFace(Level world, int x2, int y2, int z2, int f2) {
        return !world.isSolidTile(x2, y2, z2);
    }

    public int getTexture(Level level, int xCoord, int yCoord, int zCoord, int f2) {
        return this.getTextureFromSideAndMetadata(f2, level.getTileMetadata(xCoord, yCoord, zCoord));
    }

    public int getTextureFromSideAndMetadata(int f2, int data) {
        return this.getTexture(f2);
    }

    public int getTexture(int f2) {
        return this.tex;
    }

    public final AABB getSelectedBoundingBoxFromPool(int x2, int y2, int z2) {
        return new AABB((float)x2 + this.xx0, (float)y2 + this.yy0, (float)z2 + this.zz0, (float)x2 + this.xx1, (float)y2 + this.yy1, (float)z2 + this.zz1);
    }

    public AABB getAABB(int x2, int y2, int z2) {
        return new AABB((float)x2 + this.xx0, (float)y2 + this.yy0, (float)z2 + this.zz0, (float)x2 + this.xx1, (float)y2 + this.yy1, (float)z2 + this.zz1);
    }

    public boolean isSolid() {
        return true;
    }

    public boolean mayPick() {
        return true;
    }

    public void tick(Level world, int x2, int y2, int z2, Random random) {
    }

    public void randomTick(Level world, int x2, int y2, int z2, Random random) {
    }

    public void onBlockDestroyedByPlayer(Level world, int x2, int y2, int z2, int i5) {
    }

    public void neighborChanged(Level world, int x2, int y2, int z2, int id) {
    }

    public int tickRate() {
        return 5;
    }

    public void onBlockAdded(Level world, int x2, int y2, int z2) {
    }

    public void onBlockRemoved(Level world, int x2, int y2, int z2) {
    }

    public int quantityDropped(Random random) {
        return 1;
    }

    public int idDropped(int i1, Random random) {
        return this.id;
    }

    public final float blockStrength(Player player) {
        if (this.hardness < 0.0f) {
            return 0.0f;
        }
        if (!player.canHarvestTile(this)) {
            return 1.0f / this.hardness / 100.0f;
        }
        InventoryPlayer inventoryPlayer2 = player.inventory;
        float f4 = 1.0f;
        if (inventoryPlayer2.mainInventory[inventoryPlayer2.currentItem] != null) {
            f4 = 1.0f * inventoryPlayer2.mainInventory[inventoryPlayer2.currentItem].getItem().getStrVsBlock(this);
        }
        float f5 = f4;
        if (player.isInsideOfWater()) {
            f5 = f4 / 5.0f;
        }
        if (!player.onGround) {
            f5 /= 5.0f;
        }
        return f5 / this.hardness / 30.0f;
    }

    public final void dropBlockAsItem(Level world, int i2, int i3, int i4, int i5) {
        this.dropBlockAsItemWithChance(world, i2, i3, i4, i5, 1.0f);
    }

    public final void dropBlockAsItemWithChance(Level world, int i2, int i3, int i4, int i5, float f6) {
        int q2 = this.quantityDropped(world.random);
        int i6 = 0;
        while (i6 < q2) {
            int id = this.idDropped(i5, world.random);
            if (world.random.nextFloat() <= f6 && id > 0) {
                float f10 = world.random.nextFloat() * 0.7f + 0.15f;
                float f11 = world.random.nextFloat() * 0.7f + 0.15f;
                float f12 = world.random.nextFloat() * 0.7f + 0.15f;
                if (!world.onlineLevel) {
                    ItemEntity item = new ItemEntity(world, (float)i2 + f10, (float)i3 + f11, (float)i4 + f12, new ItemStack(id));
                    item.delayBeforeCanPickup = 10;
                    world.spawnEntityInWorld(item);
                }
            }
            ++i6;
        }
    }

    public final float getExplosionResistance() {
        return this.resistance / 5.0f;
    }

    public HitResult collisionRayTrace(Level world, int i2, int i3, int i4, Vec3 vec3D, Vec3 vec3D2) {
        vec3D = vec3D.addVector(-i2, -i3, -i4);
        vec3D2 = vec3D2.addVector(-i2, -i3, -i4);
        Vec3 world1 = vec3D.getIntermediateWithXValue(vec3D2, this.xx0);
        Vec3 vec3D7 = vec3D.getIntermediateWithXValue(vec3D2, this.xx1);
        Vec3 vec3D8 = vec3D.getIntermediateWithYValue(vec3D2, this.yy0);
        Vec3 vec3D9 = vec3D.getIntermediateWithYValue(vec3D2, this.yy1);
        Vec3 vec3D10 = vec3D.getIntermediateWithZValue(vec3D2, this.zz0);
        vec3D2 = vec3D.getIntermediateWithZValue(vec3D2, this.zz1);
        if (!this.isVecInsideYZBounds(world1)) {
            world1 = null;
        }
        if (!this.isVecInsideYZBounds(vec3D7)) {
            vec3D7 = null;
        }
        if (!this.isVecInsideXZBounds(vec3D8)) {
            vec3D8 = null;
        }
        if (!this.isVecInsideXZBounds(vec3D9)) {
            vec3D9 = null;
        }
        if (!this.isVecInsideXYBounds(vec3D10)) {
            vec3D10 = null;
        }
        if (!this.isVecInsideXYBounds(vec3D2)) {
            vec3D2 = null;
        }
        Vec3 vec3D11 = null;
        if (world1 != null) {
            vec3D11 = world1;
        }
        if (vec3D7 != null && (vec3D11 == null || vec3D.distance(vec3D7) < vec3D.distance(vec3D11))) {
            vec3D11 = vec3D7;
        }
        if (vec3D8 != null && (vec3D11 == null || vec3D.distance(vec3D8) < vec3D.distance(vec3D11))) {
            vec3D11 = vec3D8;
        }
        if (vec3D9 != null && (vec3D11 == null || vec3D.distance(vec3D9) < vec3D.distance(vec3D11))) {
            vec3D11 = vec3D9;
        }
        if (vec3D10 != null && (vec3D11 == null || vec3D.distance(vec3D10) < vec3D.distance(vec3D11))) {
            vec3D11 = vec3D10;
        }
        if (vec3D2 != null && (vec3D11 == null || vec3D.distance(vec3D2) < vec3D.distance(vec3D11))) {
            vec3D11 = vec3D2;
        }
        if (vec3D11 == null) {
            return null;
        }
        int vec3D1 = -1;
        if (vec3D11 == world1) {
            vec3D1 = 4;
        }
        if (vec3D11 == vec3D7) {
            vec3D1 = 5;
        }
        if (vec3D11 == vec3D8) {
            vec3D1 = 0;
        }
        if (vec3D11 == vec3D9) {
            vec3D1 = 1;
        }
        if (vec3D11 == vec3D10) {
            vec3D1 = 2;
        }
        if (vec3D11 == vec3D2) {
            vec3D1 = 3;
        }
        return new HitResult(i2, i3, i4, vec3D1, vec3D11.addVector(i2, i3, i4));
    }

    private boolean isVecInsideYZBounds(Vec3 vec3D) {
        return vec3D == null ? false : vec3D.y >= this.yy0 && vec3D.y <= this.yy1 && vec3D.z >= this.zz0 && vec3D.z <= this.zz1;
    }

    private boolean isVecInsideXZBounds(Vec3 vec3D) {
        return vec3D == null ? false : vec3D.x >= this.xx0 && vec3D.x <= this.xx1 && vec3D.z >= this.zz0 && vec3D.z <= this.zz1;
    }

    private boolean isVecInsideXYBounds(Vec3 vec3D) {
        return vec3D == null ? false : vec3D.x >= this.xx0 && vec3D.x <= this.xx1 && vec3D.y >= this.yy0 && vec3D.y <= this.yy1;
    }

    public void onBlockDestroyedByExplosion(Level world, int xCoord, int yCoord, int zCoord) {
    }

    public int getRenderBlockPass() {
        return 0;
    }

    public boolean canPlaceBlockAt(Level world, int xCoord, int yCoord, int zCoord) {
        return true;
    }

    public boolean blockActivated(Level world, int xCoord, int yCoord, int zCoord, Player player) {
        return false;
    }

    public void onEntityWalking(Level world, int i2, int i3, int i4) {
    }

    public void onBlockPlaced(Level world, int i2, int i3, int i4, int i5) {
    }

    public void onTileClicked(Level level, int x2, int y2, int z2, Player player) {
    }
}

