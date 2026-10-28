/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.level.levelgen;

import java.util.ArrayList;
import java.util.Random;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.MobSpawner;
import net.minecraft.world.level.levelgen.synth.DistortNoise;
import net.minecraft.world.level.levelgen.synth.ImprovedNoise;
import net.minecraft.world.level.tile.FlowerTile;
import net.minecraft.world.level.tile.Tile;
import util.IProgressUpdate;
import util.Mth;

public class LevelGen {
    protected IProgressUpdate loadingScreen;
    private int width;
    private int depth;
    private int height;
    private Random rand = new Random();
    private byte[] blocksByteArray;
    private int waterLevel;
    private int groundLevel;
    public boolean islandGen = false;
    public boolean floatingGen = false;
    public boolean flatGen = false;
    public int levelType;
    private int phaseBar;
    private int phases;
    private int[] floodFillBlocks = new int[0x100000];
    public boolean survival = true;

    public LevelGen(IProgressUpdate guiLoading) {
        this.loadingScreen = guiLoading;
    }

    protected Level getLevel(long seed) {
        return new Level();
    }

    public final Level generateLevel(String authorName, int width, int depth, int height) {
        int i56;
        int i31;
        int i21;
        int i51;
        int i25;
        int i53;
        int i52;
        LevelGen levelGenerator9;
        int i45;
        long seed = -1L;
        int loops = 1;
        if (this.floatingGen) {
            loops = (height - 64) / 48 + 1;
            System.out.println("loops: " + loops);
        }
        this.phases = 13 + loops * 4;
        this.loadingScreen.beginLevelLoading("Generating level");
        Level level = this.getLevel(seed);
        level.waterLevel = this.waterLevel;
        level.groundLevel = this.groundLevel;
        this.width = width;
        this.depth = depth;
        this.height = height;
        this.blocksByteArray = new byte[width * depth * height];
        int i7 = 0;
        while (i7 < loops) {
            int[] i46;
            int i22;
            ImprovedNoise noiseGeneratorOctaves13;
            int[] i8;
            this.waterLevel = height - 32 - i7 * 48;
            this.groundLevel = this.waterLevel - 2;
            if (this.flatGen) {
                i8 = new int[width * depth];
                i45 = 0;
                while (i45 < i8.length) {
                    i8[i45] = 0;
                    ++i45;
                }
                this.loadingBar();
                this.loadingBar();
            } else {
                this.loadingScreen.levelLoadUpdate("Raising..");
                this.loadingBar();
                levelGenerator9 = this;
                DistortNoise noiseGeneratorDistort10 = new DistortNoise(new ImprovedNoise(this.rand, 8), new ImprovedNoise(this.rand, 8));
                DistortNoise noiseGeneratorDistort11 = new DistortNoise(new ImprovedNoise(this.rand, 8), new ImprovedNoise(this.rand, 8));
                ImprovedNoise noiseGeneratorOctaves12 = new ImprovedNoise(this.rand, 6);
                noiseGeneratorOctaves13 = new ImprovedNoise(this.rand, 2);
                int[] i14 = new int[this.width * this.depth];
                i22 = 0;
                while (true) {
                    if (i22 >= levelGenerator9.width) {
                        i8 = i14;
                        this.loadingScreen.levelLoadUpdate("Eroding..");
                        this.loadingBar();
                        i46 = i14;
                        levelGenerator9 = this;
                        noiseGeneratorDistort11 = new DistortNoise(new ImprovedNoise(this.rand, 8), new ImprovedNoise(this.rand, 8));
                        DistortNoise noiseGeneratorDistort50 = new DistortNoise(new ImprovedNoise(this.rand, 8), new ImprovedNoise(this.rand, 8));
                        for (i52 = 0; i52 < levelGenerator9.width; ++i52) {
                            levelGenerator9.setNextPhase((float)i52 * 100.0f / (float)(levelGenerator9.width - 1));
                            i53 = 0;
                            while (i53 < levelGenerator9.depth) {
                                double d20 = noiseGeneratorDistort11.generateNoise(i52 << 1, i53 << 1) / 8.0;
                                int n2 = i22 = noiseGeneratorDistort50.generateNoise(i52 << 1, i53 << 1) > 0.0 ? 1 : 0;
                                if (d20 > 2.0) {
                                    int i58;
                                    i46[i52 + i53 * levelGenerator9.width] = i58 = ((i46[i52 + i53 * levelGenerator9.width] - i22) / 2 << 1) + i22;
                                }
                                ++i53;
                            }
                        }
                        break;
                    }
                    double d23 = Math.abs(((double)i22 / ((double)levelGenerator9.width - 1.0) - 0.5) * 2.0);
                    levelGenerator9.setNextPhase((float)i22 * 100.0f / (float)(levelGenerator9.width - 1));
                    i25 = 0;
                    while (i25 < levelGenerator9.depth) {
                        double d26 = Math.abs(((double)i25 / ((double)levelGenerator9.depth - 1.0) - 0.5) * 2.0);
                        double d28 = noiseGeneratorDistort10.generateNoise((float)i22 * 1.3f, (float)i25 * 1.3f) / 6.0 + -4.0;
                        double d30 = noiseGeneratorDistort11.generateNoise((float)i22 * 1.3f, (float)i25 * 1.3f) / 5.0 + 10.0 + -4.0;
                        if (noiseGeneratorOctaves12.generateNoise(i22, i25) / 8.0 > 0.0) {
                            d30 = d28;
                        }
                        double d34 = Math.max(d28, d30) / 2.0;
                        if (levelGenerator9.islandGen) {
                            double d36 = Math.sqrt(d23 * d23 + d26 * d26) * (double)1.2f;
                            double d39 = noiseGeneratorOctaves13.generateNoise((float)i22 * 0.05f, (float)i25 * 0.05f) / 4.0 + 1.0;
                            d36 = Math.max(Math.min(d36, d39), Math.max(d23, d26));
                            if (d36 > 1.0) {
                                d36 = 1.0;
                            }
                            if (d36 < 0.0) {
                                d36 = 0.0;
                            }
                            d36 *= d36;
                            d34 = d34 * (1.0 - d36) - d36 * 10.0 + 5.0;
                            if (d34 < 0.0) {
                                d34 -= d34 * d34 * (double)0.2f;
                            }
                        } else if (d34 < 0.0) {
                            d34 *= 0.8;
                        }
                        i14[i22 + i25 * levelGenerator9.width] = (int)d34;
                        ++i25;
                    }
                    ++i22;
                }
            }
            this.loadingScreen.levelLoadUpdate("Soiling..");
            this.loadingBar();
            i46 = i8;
            levelGenerator9 = this;
            int i49 = this.width;
            i51 = this.depth;
            i52 = this.height;
            ImprovedNoise noiseGeneratorOctaves54 = new ImprovedNoise(this.rand, 8);
            ImprovedNoise noiseGeneratorOctaves55 = new ImprovedNoise(this.rand, 8);
            i21 = 0;
            while (i21 < i49) {
                double d57 = Math.abs(((double)i21 / ((double)i49 - 1.0) - 0.5) * 2.0);
                levelGenerator9.setNextPhase((float)i21 * 100.0f / (float)(i49 - 1));
                int i24 = 0;
                while (i24 < i51) {
                    double d32;
                    int i76;
                    double d64 = Math.abs(((double)i24 / ((double)i51 - 1.0) - 0.5) * 2.0);
                    double d27 = Math.max(d57, d64);
                    d27 = d27 * d27 * d27;
                    int i29 = (int)(noiseGeneratorOctaves54.generateNoise(i21, i24) / 24.0) - 4;
                    int i72 = i46[i21 + i24 * i49] + levelGenerator9.waterLevel;
                    i31 = i72 + i29;
                    i46[i21 + i24 * i49] = Math.max(i72, i31);
                    if (i46[i21 + i24 * i49] > i52 - 2) {
                        i46[i21 + i24 * i49] = i52 - 2;
                    }
                    if (i46[i21 + i24 * i49] <= 0) {
                        i46[i21 + i24 * i49] = 1;
                    }
                    if ((i76 = (int)((double)((int)(Math.sqrt(Math.abs(d32 = noiseGeneratorOctaves55.generateNoise((double)i21 * 2.3, (double)i24 * 2.3) / 24.0)) * Math.signum(d32) * 20.0) + levelGenerator9.waterLevel) * (1.0 - d27) + d27 * (double)levelGenerator9.height)) > levelGenerator9.waterLevel) {
                        i76 = levelGenerator9.height;
                    }
                    int i35 = 0;
                    while (i35 < i52) {
                        int i78 = (i35 * levelGenerator9.depth + i24) * levelGenerator9.width + i21;
                        int i37 = 0;
                        if (i35 <= i72) {
                            i37 = Tile.dirt.id;
                        }
                        if (i35 <= i31) {
                            i37 = Tile.stone.id;
                        }
                        if (levelGenerator9.floatingGen && i35 < i76) {
                            i37 = 0;
                        }
                        if (levelGenerator9.blocksByteArray[i78] == 0) {
                            levelGenerator9.blocksByteArray[i78] = (byte)i37;
                        }
                        ++i35;
                    }
                    ++i24;
                }
                ++i21;
            }
            this.loadingScreen.levelLoadUpdate("Growing..");
            this.loadingBar();
            i46 = i8;
            levelGenerator9 = this;
            i49 = this.width;
            i51 = this.depth;
            noiseGeneratorOctaves13 = new ImprovedNoise(this.rand, 8);
            noiseGeneratorOctaves54 = new ImprovedNoise(this.rand, 8);
            i56 = this.waterLevel - 1;
            if (this.levelType == 2) {
                i56 += 2;
            }
            i21 = 0;
            while (i21 < i49) {
                levelGenerator9.setNextPhase((float)i21 * 100.0f / (float)(i49 - 1));
                i22 = 0;
                while (i22 < i51) {
                    boolean z61;
                    boolean z59;
                    boolean bl = z59 = noiseGeneratorOctaves13.generateNoise(i21, i22) > 8.0;
                    if (levelGenerator9.islandGen) {
                        boolean bl2 = z59 = noiseGeneratorOctaves13.generateNoise(i21, i22) > -8.0;
                    }
                    if (levelGenerator9.levelType == 2) {
                        z59 = noiseGeneratorOctaves13.generateNoise(i21, i22) > -32.0;
                    }
                    boolean bl3 = z61 = noiseGeneratorOctaves54.generateNoise(i21, i22) > 12.0;
                    if (levelGenerator9.levelType == 1 || levelGenerator9.levelType == 3) {
                        z59 = noiseGeneratorOctaves13.generateNoise(i21, i22) > -8.0;
                    }
                    i25 = i46[i21 + i22 * i49];
                    int i65 = (i25 * levelGenerator9.depth + i22) * levelGenerator9.width + i21;
                    int i67 = levelGenerator9.blocksByteArray[((i25 + 1) * levelGenerator9.depth + i22) * levelGenerator9.width + i21] & 0xFF;
                    if ((i67 == Tile.water.id || i67 == Tile.waterCalm.id || i67 == 0) && i25 <= levelGenerator9.waterLevel - 1 && z61) {
                        levelGenerator9.blocksByteArray[i65] = (byte)Tile.gravel.id;
                    }
                    if (i67 == 0) {
                        int i69 = -1;
                        if (i25 <= i56 && z59) {
                            i69 = Tile.sand.id;
                            if (levelGenerator9.levelType == 1) {
                                i69 = Tile.grass.id;
                            }
                        }
                        if (levelGenerator9.blocksByteArray[i65] != 0 && i69 > 0) {
                            levelGenerator9.blocksByteArray[i65] = (byte)i69;
                        }
                    }
                    ++i22;
                }
                ++i21;
            }
            ++i7;
        }
        this.loadingScreen.levelLoadUpdate("Carving..");
        this.loadingBar();
        levelGenerator9 = this;
        i51 = this.width;
        i52 = this.depth;
        i53 = this.height;
        i56 = i51 * i52 * i53 / 256 / 64 << 1;
        i21 = 0;
        while (i21 < i56) {
            levelGenerator9.setNextPhase((float)i21 * 100.0f / (float)(i56 - 1));
            float f60 = levelGenerator9.rand.nextFloat() * (float)i51;
            float f63 = levelGenerator9.rand.nextFloat() * (float)i53;
            float f62 = levelGenerator9.rand.nextFloat() * (float)i52;
            i25 = (int)((levelGenerator9.rand.nextFloat() + levelGenerator9.rand.nextFloat()) * 200.0f);
            float f66 = levelGenerator9.rand.nextFloat() * (float)Math.PI * 2.0f;
            float f68 = 0.0f;
            float f71 = levelGenerator9.rand.nextFloat() * (float)Math.PI * 2.0f;
            float f70 = 0.0f;
            float f73 = levelGenerator9.rand.nextFloat() * levelGenerator9.rand.nextFloat();
            i31 = 0;
            while (i31 < i25) {
                f60 += Mth.sin(f66) * Mth.cos(f71);
                f62 += Mth.cos(f66) * Mth.cos(f71);
                f63 += Mth.sin(f71);
                f66 += f68 * 0.2f;
                f68 *= 0.9f;
                f68 += levelGenerator9.rand.nextFloat() - levelGenerator9.rand.nextFloat();
                f71 = (f71 + f70 * 0.5f) * 0.5f;
                f70 *= 0.75f;
                f70 += levelGenerator9.rand.nextFloat() - levelGenerator9.rand.nextFloat();
                if (levelGenerator9.rand.nextFloat() >= 0.25f) {
                    float f74 = f60 + (levelGenerator9.rand.nextFloat() * 4.0f - 2.0f) * 0.2f;
                    float f33 = f63 + (levelGenerator9.rand.nextFloat() * 4.0f - 2.0f) * 0.2f;
                    float f77 = f62 + (levelGenerator9.rand.nextFloat() * 4.0f - 2.0f) * 0.2f;
                    float f75 = ((float)levelGenerator9.height - f33) / (float)levelGenerator9.height;
                    float f79 = 1.2f + (f75 * 3.5f + 1.0f) * f73;
                    float f80 = Mth.sin((float)i31 * (float)Math.PI / (float)i25) * f79;
                    loops = (int)(f74 - f80);
                    while (loops <= (int)(f74 + f80)) {
                        int i81 = (int)(f33 - f80);
                        while (i81 <= (int)(f33 + f80)) {
                            int i40 = (int)(f77 - f80);
                            while (i40 <= (int)(f77 + f80)) {
                                float f41 = (float)loops - f74;
                                float f42 = (float)i81 - f33;
                                float f48 = (float)i40 - f77;
                                if (f41 * f41 + f42 * f42 * 2.0f + f48 * f48 < f80 * f80 && loops > 0 && i81 > 0 && i40 > 0 && loops < levelGenerator9.width - 1 && i81 < levelGenerator9.height - 1 && i40 < levelGenerator9.depth - 1 && levelGenerator9.blocksByteArray[i7 = (i81 * levelGenerator9.depth + i40) * levelGenerator9.width + loops] == Tile.stone.id) {
                                    levelGenerator9.blocksByteArray[i7] = 0;
                                }
                                ++i40;
                            }
                            ++i81;
                        }
                        ++loops;
                    }
                }
                ++i31;
            }
            ++i21;
        }
        i7 = this.populateOre(Tile.oreCoal.id, 1000, 10, (height << 2) / 5);
        int i44 = this.populateOre(Tile.oreIron.id, 800, 8, height * 3 / 5);
        i45 = this.populateOre(Tile.oreGold.id, 500, 6, (height << 1) / 5);
        loops = this.populateOre(Tile.oreDiamond.id, 800, 2, height / 5);
        System.out.println("Coal: " + i7 + ", Iron: " + i44 + ", Gold: " + i45 + ", Diamond: " + loops);
        this.loadingScreen.levelLoadUpdate("Melting..");
        this.loadingBar();
        this.lavaGen();
        level.cloudHeight = height + 2;
        if (this.floatingGen) {
            this.groundLevel = -128;
            this.waterLevel = this.groundLevel + 1;
            level.cloudHeight = -16;
        } else if (!this.islandGen) {
            this.groundLevel = this.waterLevel + 1;
            this.waterLevel = this.groundLevel - 16;
        } else {
            this.groundLevel = this.waterLevel - 9;
        }
        this.loadingScreen.levelLoadUpdate("Watering..");
        this.loadingBar();
        this.liquidThemeSpawner();
        if (!this.floatingGen) {
            loops = Tile.waterCalm.id;
            if (this.levelType == 1) {
                loops = Tile.lavaCalm.id;
            }
            i7 = 0;
            while (i7 < width) {
                this.floodFill(i7, this.waterLevel - 1, 0, 0, loops);
                this.floodFill(i7, this.waterLevel - 1, depth - 1, 0, loops);
                ++i7;
            }
            i7 = 0;
            while (i7 < depth) {
                this.floodFill(width - 1, this.waterLevel - 1, i7, 0, loops);
                this.floodFill(0, this.waterLevel - 1, i7, 0, loops);
                ++i7;
            }
        }
        if (this.levelType == 0) {
            level.skyColor = 0x99CCFF;
            level.fogColor = 0xFFFFFF;
            level.cloudColor = 0xFFFFFF;
        }
        if (this.levelType == 1) {
            level.cloudColor = 2164736;
            level.fogColor = 0x100400;
            level.skyColor = 0x100400;
            level.skyBrightness = 7;
            level.skylightSubtracted = 7;
            level.defaultFluid = Tile.lava.id;
            if (this.floatingGen) {
                level.cloudHeight = height + 2;
                this.waterLevel = -16;
            }
        }
        if (this.levelType == 2) {
            level.skyColor = 13033215;
            level.fogColor = 13033215;
            level.cloudColor = 0xEEEEFF;
            level.skyBrightness = 15;
            level.skylightSubtracted = 15;
            level.skyBrightness = 16;
            level.cloudHeight = height + 64;
        }
        if (this.levelType == 3) {
            level.skyColor = 7699847;
            level.fogColor = 5069403;
            level.cloudColor = 5069403;
            level.skyBrightness = 12;
            level.skylightSubtracted = 12;
        }
        level.waterLevel = this.waterLevel;
        level.groundLevel = this.groundLevel;
        this.loadingScreen.levelLoadUpdate("Assembling..");
        this.loadingBar();
        this.setNextPhase(0.0f);
        level.setData(width, height, depth, this.blocksByteArray, null, true);
        this.loadingScreen.levelLoadUpdate("Building..");
        this.loadingBar();
        this.setNextPhase(0.0f);
        level.findSpawn();
        LevelGen.generateHouse(level);
        this.loadingScreen.levelLoadUpdate("Planting..");
        this.loadingBar();
        if (this.levelType != 1) {
            this.growGrassOnDirt(level);
        }
        this.loadingBar();
        this.growTrees(level);
        if (this.levelType == 3) {
            loops = 0;
            while (loops < 50) {
                this.growTrees(level);
                ++loops;
            }
        }
        int s43 = 100;
        if (this.levelType == 2) {
            s43 = 1000;
        }
        this.loadingBar();
        this.populateFlowersAndMushrooms(level, Tile.plantYellow, s43);
        this.loadingBar();
        this.populateFlowersAndMushrooms(level, Tile.plantRed, s43);
        this.loadingBar();
        this.populateFlowersAndMushrooms(level, Tile.mushroomBrown, 50);
        this.loadingBar();
        this.populateFlowersAndMushrooms(level, Tile.mushroomRed, 50);
        this.loadingScreen.levelLoadUpdate("Lighting..");
        this.loadingBar();
        i7 = 0;
        while (i7 < 10000) {
            this.setNextPhase(i7 * 100 / 10000);
            level.updateLighting();
            ++i7;
        }
        this.loadingScreen.levelLoadUpdate("Spawning..");
        this.loadingBar();
        if (this.survival) {
            MobSpawner mobs = new MobSpawner(level);
            width = 0;
            while (width < 1000) {
                this.setNextPhase((float)width * 100.0f / 999.0f);
                mobs.performSpawning();
                ++width;
            }
        }
        level.createTime = System.currentTimeMillis();
        level.authorName = authorName;
        level.name = "A Nice World";
        if (this.phaseBar != this.phases) {
            throw new IllegalStateException("Wrong number of phases! Wanted " + this.phases + ", got " + this.phaseBar);
        }
        return level;
    }

    private static void generateHouse(Level world) {
        int i1 = world.xSpawn;
        int i2 = world.ySpawn;
        int i3 = world.zSpawn;
        int i4 = i1 - 3;
        while (i4 <= i1 + 3) {
            int i5 = i2 - 2;
            while (i5 <= i2 + 2) {
                int i6 = i3 - 3;
                while (i6 <= i3 + 3) {
                    int i7;
                    int n2 = i7 = i5 < i2 - 1 ? Tile.obsidian.id : 0;
                    if (i4 == i1 - 3 || i6 == i3 - 3 || i4 == i1 + 3 || i6 == i3 + 3 || i5 == i2 - 2 || i5 == i2 + 2) {
                        i7 = Tile.stone.id;
                        if (i5 >= i2 - 1) {
                            i7 = Tile.planks.id;
                        }
                    }
                    if (i6 == i3 - 3 && i4 == i1 && i5 >= i2 - 1 && i5 <= i2) {
                        i7 = 0;
                    }
                    world.setTile(i4, i5, i6, i7);
                    ++i6;
                }
                ++i5;
            }
            ++i4;
        }
        world.setTile(i1 - 3 + 1, i2, i3, Tile.torch.id);
        world.setTile(i1 + 3 - 1, i2, i3, Tile.torch.id);
    }

    private void growGrassOnDirt(Level world) {
        int i2 = 0;
        while (i2 < this.width) {
            this.setNextPhase((float)i2 * 100.0f / (float)(this.width - 1));
            int i3 = 0;
            while (i3 < this.height) {
                int i4 = 0;
                while (i4 < this.depth) {
                    if (world.getTile(i2, i3, i4) == Tile.dirt.id && world.getBlockLightValue(i2, i3 + 1, i4) >= 4 && !world.getMaterial(i2, i3 + 1, i4).getCanBlockGrass()) {
                        world.setTileNoNeighborChange(i2, i3, i4, Tile.grass.id);
                    }
                    ++i4;
                }
                ++i3;
            }
            ++i2;
        }
    }

    private void growTrees(Level world) {
        int i2 = this.width * this.depth * this.height / 80000;
        int i3 = 0;
        while (i3 < i2) {
            if (i3 % 100 == 0) {
                this.setNextPhase((float)i3 * 100.0f / (float)(i2 - 1));
            }
            int i4 = this.rand.nextInt(this.width);
            int i5 = this.rand.nextInt(this.height);
            int i6 = this.rand.nextInt(this.depth);
            int i7 = 0;
            while (i7 < 25) {
                int i8 = i4;
                int i9 = i5;
                int i10 = i6;
                int i11 = 0;
                while (i11 < 20) {
                    if ((i8 += this.rand.nextInt(12) - this.rand.nextInt(12)) >= 0 && (i9 += this.rand.nextInt(3) - this.rand.nextInt(6)) >= 0 && (i10 += this.rand.nextInt(12) - this.rand.nextInt(12)) >= 0 && i8 < this.width && i9 < this.height && i10 < this.depth) {
                        world.growTrees(i8, i9, i10);
                    }
                    ++i11;
                }
                ++i7;
            }
            ++i3;
        }
    }

    private void populateFlowersAndMushrooms(Level world, FlowerTile flowers, int i3) {
        i3 = (int)((long)this.width * (long)this.depth * (long)this.height * (long)i3 / 1600000L);
        int i4 = 0;
        while (i4 < i3) {
            if (i4 % 100 == 0) {
                this.setNextPhase((float)i4 * 100.0f / (float)(i3 - 1));
            }
            int i5 = this.rand.nextInt(this.width);
            int i6 = this.rand.nextInt(this.height);
            int i7 = this.rand.nextInt(this.depth);
            int i8 = 0;
            while (i8 < 10) {
                int i9 = i5;
                int i10 = i6;
                int i11 = i7;
                int i12 = 0;
                while (i12 < 10) {
                    if ((i9 += this.rand.nextInt(4) - this.rand.nextInt(4)) >= 0 && (i11 += this.rand.nextInt(4) - this.rand.nextInt(4)) >= 0 && (i10 += this.rand.nextInt(2) - this.rand.nextInt(2)) > 0 && i9 < this.width && i11 < this.depth && i10 < this.height && world.getTile(i9, i10, i11) == 0 && flowers.canBlockStay(world, i9, i10, i11)) {
                        world.setTile(i9, i10, i11, flowers.id);
                    }
                    ++i12;
                }
                ++i8;
            }
            ++i4;
        }
    }

    private int populateOre(int i1, int i2, int i3, int i4) {
        int i5 = 0;
        byte b26 = (byte)i1;
        int i6 = this.width;
        int i7 = this.depth;
        int i8 = this.height;
        i2 = i6 * i7 * i8 / 256 / 64 * i2 / 100;
        int i9 = 0;
        while (i9 < i2) {
            this.setNextPhase((float)i9 * 100.0f / (float)(i2 - 1));
            float f10 = this.rand.nextFloat() * (float)i6;
            float f11 = this.rand.nextFloat() * (float)i8;
            float f12 = this.rand.nextFloat() * (float)i7;
            if (f11 <= (float)i4) {
                int i13 = (int)((this.rand.nextFloat() + this.rand.nextFloat()) * 75.0f * (float)i3 / 100.0f);
                float f14 = this.rand.nextFloat() * (float)Math.PI * 2.0f;
                float f15 = 0.0f;
                float f16 = this.rand.nextFloat() * (float)Math.PI * 2.0f;
                float f17 = 0.0f;
                int i18 = 0;
                while (i18 < i13) {
                    f10 += Mth.sin(f14) * Mth.cos(f16);
                    f12 += Mth.cos(f14) * Mth.cos(f16);
                    f11 += Mth.sin(f16);
                    f14 += f15 * 0.2f;
                    f15 *= 0.9f;
                    f15 += this.rand.nextFloat() - this.rand.nextFloat();
                    f16 = (f16 + f17 * 0.5f) * 0.5f;
                    f17 *= 0.9f;
                    f17 += this.rand.nextFloat() - this.rand.nextFloat();
                    float f19 = Mth.sin((float)i18 * (float)Math.PI / (float)i13) * (float)i3 / 100.0f + 1.0f;
                    int i20 = (int)(f10 - f19);
                    while (i20 <= (int)(f10 + f19)) {
                        int i21 = (int)(f11 - f19);
                        while (i21 <= (int)(f11 + f19)) {
                            int i22 = (int)(f12 - f19);
                            while (i22 <= (int)(f12 + f19)) {
                                int i27;
                                float f23 = (float)i20 - f10;
                                float f24 = (float)i21 - f11;
                                float f25 = (float)i22 - f12;
                                if (f23 * f23 + f24 * f24 * 2.0f + f25 * f25 < f19 * f19 && i20 > 0 && i21 > 0 && i22 > 0 && i20 < this.width - 1 && i21 < this.height - 1 && i22 < this.depth - 1 && this.blocksByteArray[i27 = (i21 * this.depth + i22) * this.width + i20] == Tile.stone.id) {
                                    this.blocksByteArray[i27] = b26;
                                    ++i5;
                                }
                                ++i22;
                            }
                            ++i21;
                        }
                        ++i20;
                    }
                    ++i18;
                }
            }
            ++i9;
        }
        return i5;
    }

    private void liquidThemeSpawner() {
        int i1 = Tile.waterCalm.id;
        if (this.levelType == 1) {
            i1 = Tile.lavaCalm.id;
        }
        int i2 = this.width * this.depth * this.height / 1000;
        int i3 = 0;
        while (i3 < i2) {
            int i6;
            if (i3 % 100 == 0) {
                this.setNextPhase((float)i3 * 100.0f / (float)(i2 - 1));
            }
            int i4 = this.rand.nextInt(this.width);
            int i5 = this.rand.nextInt(this.height);
            if (this.blocksByteArray[(i5 * this.depth + (i6 = this.rand.nextInt(this.depth))) * this.width + i4] == 0) {
                long j7 = this.floodFill(i4, i5, i6, 0, 255);
                if (j7 > 0L && j7 < 640L) {
                    this.floodFill(i4, i5, i6, 255, i1);
                } else {
                    this.floodFill(i4, i5, i6, 255, 0);
                }
            }
            ++i3;
        }
        this.setNextPhase(100.0f);
    }

    private void loadingBar() {
        ++this.phaseBar;
        this.setNextPhase(0.0f);
    }

    private void setNextPhase(float f1) {
        if (f1 < 0.0f) {
            throw new IllegalStateException("Failed to set next phase!");
        }
        int i2 = (int)(((float)(this.phaseBar - 1) + f1 / 100.0f) * 100.0f / (float)this.phases);
        this.loadingScreen.setLoadingProgress(i2);
    }

    private void lavaGen() {
        int i1 = this.width * this.depth * this.height / 2000;
        int i2 = this.groundLevel;
        int i3 = 0;
        while (i3 < i1) {
            int i6;
            if (i3 % 100 == 0) {
                this.setNextPhase((float)i3 * 100.0f / (float)(i1 - 1));
            }
            int i4 = this.rand.nextInt(this.width);
            int i5 = Math.min(Math.min(this.rand.nextInt(i2), this.rand.nextInt(i2)), Math.min(this.rand.nextInt(i2), this.rand.nextInt(i2)));
            if (this.blocksByteArray[(i5 * this.depth + (i6 = this.rand.nextInt(this.depth))) * this.width + i4] == 0) {
                long j7 = this.floodFill(i4, i5, i6, 0, 255);
                if (j7 > 0L && j7 < 640L) {
                    this.floodFill(i4, i5, i6, 255, Tile.lavaCalm.id);
                } else {
                    this.floodFill(i4, i5, i6, 255, 0);
                }
            }
            ++i3;
        }
        this.setNextPhase(100.0f);
    }

    private long floodFill(int i1, int i2, int i3, int i4, int i5) {
        byte b6 = (byte)i5;
        byte b22 = (byte)i4;
        ArrayList<int[]> arrayList7 = new ArrayList<int[]>();
        int b8 = 0;
        int i9 = 1;
        int i10 = 1;
        while (1 << i9 < this.width) {
            ++i9;
        }
        while (1 << i10 < this.depth) {
            ++i10;
        }
        int i11 = this.depth - 1;
        int i12 = this.width - 1;
        int i23 = b8 + 1;
        this.floodFillBlocks[0] = ((i2 << i10) + i3 << i9) + i1;
        long j14 = 0L;
        i1 = this.width * this.depth;
        while (i23 > 0) {
            int i162;
            i2 = this.floodFillBlocks[--i23];
            if (i23 == 0 && arrayList7.size() > 0) {
                this.floodFillBlocks = (int[])arrayList7.remove(arrayList7.size() - 1);
                i23 = this.floodFillBlocks.length;
            }
            i3 = i2 >> i9 & i11;
            int i13 = i2 >> i9 + i10;
            int i17 = i162 = i2 & i12;
            while (i162 > 0 && this.blocksByteArray[i2 - 1] == b22) {
                --i162;
                --i2;
            }
            while (i17 < this.width && this.blocksByteArray[i2 + i17 - i162] == b22) {
                ++i17;
            }
            int i18 = i2 >> i9 & i11;
            int i19 = i2 >> i9 + i10;
            if (i5 == 255 && (i162 == 0 || i17 == this.width - 1 || i13 == 0 || i13 == this.height - 1 || i3 == 0 || i3 == this.depth - 1)) {
                return -1L;
            }
            if (i18 != i3 || i19 != i13) {
                System.out.println("Diagonal flood!?");
            }
            boolean z24 = false;
            boolean z25 = false;
            boolean z20 = false;
            j14 += (long)(i17 - i162);
            int i16 = i162;
            while (i16 < i17) {
                boolean z21;
                this.blocksByteArray[i2] = b6;
                if (i3 > 0) {
                    z21 = this.blocksByteArray[i2 - this.width] == b22;
                    if (z21 && !z24) {
                        if (i23 == this.floodFillBlocks.length) {
                            arrayList7.add(this.floodFillBlocks);
                            this.floodFillBlocks = new int[0x100000];
                            i23 = 0;
                        }
                        this.floodFillBlocks[i23++] = i2 - this.width;
                    }
                    z24 = z21;
                }
                if (i3 < this.depth - 1) {
                    z21 = this.blocksByteArray[i2 + this.width] == b22;
                    if (z21 && !z25) {
                        if (i23 == this.floodFillBlocks.length) {
                            arrayList7.add(this.floodFillBlocks);
                            this.floodFillBlocks = new int[0x100000];
                            i23 = 0;
                        }
                        this.floodFillBlocks[i23++] = i2 + this.width;
                    }
                    z25 = z21;
                }
                if (i13 > 0) {
                    byte b26 = this.blocksByteArray[i2 - i1];
                    if (!(b6 != Tile.lava.id && b6 != Tile.lavaCalm.id || b26 != Tile.water.id && b26 != Tile.waterCalm.id)) {
                        this.blocksByteArray[i2 - i1] = (byte)Tile.stone.id;
                    }
                    if ((z21 = b26 == b22) && !z20) {
                        if (i23 == this.floodFillBlocks.length) {
                            arrayList7.add(this.floodFillBlocks);
                            this.floodFillBlocks = new int[0x100000];
                            i23 = 0;
                        }
                        this.floodFillBlocks[i23++] = i2 - i1;
                    }
                    z20 = z21;
                }
                ++i2;
                ++i16;
            }
        }
        return j14;
    }
}

