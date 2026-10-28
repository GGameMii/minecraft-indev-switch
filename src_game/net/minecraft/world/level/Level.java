/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.level;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeSet;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Coord;
import net.minecraft.world.level.EntityMap;
import net.minecraft.world.level.IWorldAccess;
import net.minecraft.world.level.Light;
import net.minecraft.world.level.MobSpawner;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.pathfinder.Pathfinder;
import net.minecraft.world.level.tile.ContainerTile;
import net.minecraft.world.level.tile.Tile;
import net.minecraft.world.level.tile.tileentity.TileEntity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import util.Mth;

public class Level {
    private static float[] lightBrightnessTable = new float[16];
    public int width;
    public int depth;
    public int height;
    public byte[] blocks;
    public byte[] data;
    public byte[] lightData;
    public String name;
    public String authorName;
    public long createTime;
    public int xSpawn;
    public int ySpawn;
    public int zSpawn;
    public float rotSpawn;
    public int defaultFluid;
    List<IWorldAccess> levelListeners;
    private List<Coord> tickList;
    public Map<Integer, TileEntity> tileEntities;
    private List<TileEntity> tileEntities2;
    protected int[] heightMap;
    public Random random;
    private Random rand;
    private int randId;
    public EntityMap entityMap;
    public int waterLevel;
    public int groundLevel;
    public int cloudHeight;
    public int skyColor;
    public int fogColor;
    public int cloudColor;
    private int updateLCG;
    public boolean survivalWorld;
    public int skyBrightness;
    public int skylightSubtracted;
    public Pathfinder pathFinder;
    protected Light lightUpdates;
    public int worldTime;
    private static short floodFillCounter;
    private short[] floodFillCounters;
    private int[] coords;
    private int[] floodedBlocks;
    public int difficultySetting;
    public List<Player> playerEntities;
    public MobSpawner spawner;
    public boolean onlineLevel;
    public boolean tilePhysics;
    public boolean mobGriefing;
    public boolean doDayCycle;

    static {
        int i0 = 0;
        while (i0 <= 15) {
            float f1 = 1.0f - (float)i0 / 15.0f;
            Level.lightBrightnessTable[i0] = (1.0f - f1) / (f1 * 3.0f + 1.0f) * 0.95f + 0.05f;
            ++i0;
        }
        floodFillCounter = 0;
    }

    public Level() {
        this.defaultFluid = Tile.water.id;
        this.levelListeners = new ArrayList<IWorldAccess>();
        this.tickList = new LinkedList<Coord>();
        this.tileEntities = new HashMap<Integer, TileEntity>();
        this.tileEntities2 = new ArrayList<TileEntity>();
        this.random = new Random();
        this.rand = new Random();
        this.randId = this.random.nextInt();
        this.skyColor = 0x99CCFF;
        this.fogColor = 0xFFFFFF;
        this.cloudColor = 0xFFFFFF;
        this.updateLCG = 0;
        this.survivalWorld = true;
        this.skyBrightness = 15;
        this.skylightSubtracted = 15;
        this.pathFinder = new Pathfinder(this);
        this.worldTime = 0;
        this.floodFillCounters = new short[0x100000];
        this.coords = new int[0x100000];
        this.floodedBlocks = new int[0x100000];
        this.difficultySetting = 2;
        this.playerEntities = new ArrayList<Player>();
        this.spawner = new MobSpawner(this);
        this.tilePhysics = true;
        this.mobGriefing = true;
        this.doDayCycle = true;
    }

    public final void initTransient() {
        if (this.blocks == null) {
            throw new RuntimeException("The level is corrupt!");
        }
        this.levelListeners = new ArrayList<IWorldAccess>();
        this.heightMap = new int[this.width * this.depth];
        Arrays.fill(this.heightMap, this.height);
        this.lightUpdates.updateSkylight(0, 0, this.width, this.depth);
        this.random = new Random();
        this.randId = this.random.nextInt();
        this.tickList = new ArrayList<Coord>();
        if (this.entityMap == null) {
            this.entityMap = new EntityMap(this.width, this.height, this.depth);
        }
    }

    public final void setData(int w2, int h2, int d2, byte[] blocks, byte[] meta, boolean spawn) {
        if (meta != null && meta.length == 0) {
            meta = null;
        }
        this.width = w2;
        this.depth = d2;
        this.height = h2;
        this.blocks = blocks;
        h2 = 0;
        while (h2 < this.width) {
            int i6 = 0;
            while (i6 < this.depth) {
                int i7 = 0;
                while (i7 < this.height) {
                    int i8 = 0;
                    if (i7 <= 1 && i7 < this.groundLevel - 1 && blocks[((i7 + 1) * this.depth + i6) * this.width + h2] == 0) {
                        i8 = Tile.lavaCalm.id;
                    } else if (i7 < this.groundLevel - 1) {
                        i8 = Tile.bedrock.id;
                    } else if (i7 < this.groundLevel) {
                        i8 = this.groundLevel > this.waterLevel && this.defaultFluid == Tile.water.id ? Tile.grass.id : Tile.dirt.id;
                    } else if (i7 < this.waterLevel) {
                        i8 = this.defaultFluid;
                    }
                    blocks[(i7 * this.depth + i6) * this.width + h2] = (byte)i8;
                    if (i7 == 1 && h2 != 0 && i6 != 0 && h2 != this.width - 1 && i6 != this.depth - 1) {
                        i7 = this.height - 2;
                    }
                    ++i7;
                }
                ++i6;
            }
            ++h2;
        }
        this.data = new byte[blocks.length];
        this.heightMap = new int[w2 * d2];
        Arrays.fill(this.heightMap, this.height);
        if (meta == null) {
            this.createLighting(w2, h2, d2, blocks, meta);
        } else {
            this.lightData = meta;
            this.lightUpdates = new Light(this);
        }
        h2 = 0;
        while (h2 < this.levelListeners.size()) {
            this.levelListeners.get(h2).loadRenderers();
            ++h2;
        }
        this.tickList.clear();
        if (spawn) {
            this.findSpawn();
        }
        this.initTransient();
        System.gc();
    }

    public void createLighting(int w2, int h2, int d2, byte[] blocks, byte[] meta) {
        this.lightData = new byte[blocks.length];
        this.lightUpdates = new Light(this);
        Level world = this;
        h2 = this.skylightSubtracted;
        d2 = 0;
        while (d2 < world.width) {
            int i12 = 0;
            while (i12 < world.depth) {
                int i13 = world.height - 1;
                while (i13 > 0 && Tile.lightOpacity[world.getTile(d2, i13, i12)] == 0) {
                    --i13;
                }
                world.heightMap[d2 + i12 * world.width] = i13 + 1;
                i13 = 0;
                while (i13 < world.height) {
                    int at;
                    byte id;
                    int i7 = world.heightMap[d2 + i12 * world.width];
                    i7 = i13 >= i7 ? h2 : 0;
                    if (i7 < Tile.lightValue[id = world.blocks[at = (i13 * world.depth + i12) * world.width + d2]]) {
                        i7 = Tile.lightValue[id];
                    }
                    world.lightData[at] = (byte)((world.lightData[at] & 0xF0) + i7);
                    ++i13;
                }
                ++i12;
            }
            ++d2;
        }
        world.lightUpdates.updateBlockLight(0, 0, 0, world.width, world.height, world.depth);
    }

    public final void updateBorders(int w2, int h2, int d2) {
        h2 = 0;
        while (h2 < this.width) {
            int i6 = 0;
            while (i6 < this.depth) {
                int i7 = 0;
                while (i7 < this.height) {
                    int i8 = 0;
                    if (i7 <= 1 && i7 < this.groundLevel - 1 && this.blocks[((i7 + 1) * this.depth + i6) * this.width + h2] == 0) {
                        i8 = Tile.lavaCalm.id;
                    } else if (i7 < this.groundLevel - 1) {
                        i8 = Tile.bedrock.id;
                    } else if (i7 < this.groundLevel) {
                        i8 = this.groundLevel > this.waterLevel && this.defaultFluid == Tile.water.id ? Tile.grass.id : Tile.dirt.id;
                    } else if (i7 < this.waterLevel) {
                        i8 = this.defaultFluid;
                    }
                    this.blocks[(i7 * this.depth + i6) * this.width + h2] = (byte)i8;
                    if (i7 == 1 && h2 != 0 && i6 != 0 && h2 != this.width - 1 && i6 != this.depth - 1) {
                        i7 = this.height - 2;
                    }
                    ++i7;
                }
                ++i6;
            }
            ++h2;
        }
        h2 = 0;
        while (h2 < this.levelListeners.size()) {
            this.levelListeners.get(h2).loadRenderers();
            ++h2;
        }
    }

    /*
     * Unable to fully structure code
     */
    public final void findSpawn() {
        Random random1 = new Random();
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        block0: while (true) {
            i3 = random1.nextInt(this.width / 2) + this.width / 4;
            i4 = random1.nextInt(this.depth / 2) + this.depth / 4;
            i5 = this.getFirstUncoveredBlock(i3, i4) + 1;
            if (++i2 == 1000000) {
                this.xSpawn = i3;
                this.ySpawn = this.height + 100;
                this.zSpawn = i4;
                this.rotSpawn = 180.0f;
                return;
            }
            if (i5 < 4 || i5 <= this.waterLevel) continue;
            i6 = i3 - 3;
            while (i6 <= i3 + 3) {
                i7 = i5 - 1;
                while (i7 <= i5 + 2) {
                    i8 = i4 - 3 - 2;
                    while (i8 <= i4 + 3) {
                        if (this.getMaterial(i6, i7, i8).isSolid()) continue block0;
                        ++i8;
                    }
                    ++i7;
                }
                ++i6;
            }
            i6 = i5 - 2;
            i7 = i3 - 3;
            while (i7 <= i3 + 3) {
                i8 = i4 - 3 - 2;
                while (i8 <= i4 + 3) {
                    if (!Tile.opaqueCubeLookup[this.getTile(i7, i6, i8)]) continue block0;
                    ++i8;
                }
                ++i7;
            }
            break;
        }
        this.xSpawn = i3;
        this.ySpawn = i5;
        this.zSpawn = i4;
        this.rotSpawn = 180.0f;
    }

    public final void addListener(IWorldAccess listener) {
        int i2 = 0;
        while (i2 < this.entityMap.entities.size()) {
            listener.obtainEntitySkin(this.entityMap.entities.get(i2));
            ++i2;
        }
        this.levelListeners.add(listener);
    }

    public final void finalize() {
    }

    public final void removeListener(IWorldAccess listener) {
        this.levelListeners.remove(listener);
    }

    public final ArrayList<AABB> getCollidingBoundingBoxes(Entity e2, AABB aabb) {
        ArrayList<AABB> arrayList2 = new ArrayList<AABB>();
        int i3 = (int)aabb.x0;
        int i4 = (int)aabb.x1 + 1;
        int i5 = (int)aabb.y0;
        int i6 = (int)aabb.y1 + 1;
        int i7 = (int)aabb.z0;
        int i8 = (int)aabb.z1 + 1;
        if (aabb.x0 < 0.0f) {
            --i3;
        }
        if (aabb.y0 < 0.0f) {
            --i5;
        }
        if (aabb.z0 < 0.0f) {
            --i7;
        }
        int i2 = i3;
        while (i2 < i4) {
            int i9 = i5;
            while (i9 < i6) {
                int i10 = i7;
                while (i10 < i8) {
                    AABB axisAlignedBB12;
                    Tile block11 = Tile.tiles[this.getTile(i2, i9, i10)];
                    if (block11 != null) {
                        axisAlignedBB12 = block11.getAABB(i2, i9, i10);
                        if (axisAlignedBB12 != null && aabb.intersectsWith(axisAlignedBB12)) {
                            arrayList2.add(axisAlignedBB12);
                        }
                    } else if (this.groundLevel < 0 && (i9 < this.groundLevel || i9 < this.waterLevel) && (axisAlignedBB12 = Tile.bedrock.getAABB(i2, i9, i10)) != null && aabb.intersectsWith(axisAlignedBB12)) {
                        arrayList2.add(axisAlignedBB12);
                    }
                    ++i10;
                }
                ++i9;
            }
            ++i2;
        }
        float d14 = 0.25f;
        List<Entity> list15 = this.getEntitiesWithinAABBExcludingEntity(e2, aabb.expand(d14, d14, d14));
        int i16 = 0;
        while (i16 < list15.size()) {
            AABB axisAlignedBB13 = list15.get((int)i16).bb;
            axisAlignedBB13 = e2.getCollisionBox(list15.get(i16));
            if (axisAlignedBB13 != null && axisAlignedBB13.intersectsWith(aabb)) {
                arrayList2.add(axisAlignedBB13);
            }
            ++i16;
        }
        return arrayList2;
    }

    public void swap(int x0, int y0, int z0, int x1, int y1, int z1) {
        int id0 = this.getTile(x0, y0, z0);
        int id1 = this.getTile(x1, y1, z1);
        this.setTileNoNeighborChange(x0, y0, z0, id1);
        this.setTileNoNeighborChange(x1, y1, z1, id0);
        this.updateNeighborAt(x0, y0, z0, id1);
        this.updateNeighborAt(x1, y1, z1, id0);
    }

    public final boolean setTileNoNeighborChange(int x2, int y2, int z2, int id) {
        if (x2 > 0 && y2 > 0 && z2 > 0 && x2 < this.width - 1 && y2 < this.height - 1 && z2 < this.depth - 1) {
            if (id == this.blocks[(y2 * this.depth + z2) * this.width + x2]) {
                return false;
            }
            if (id == 0 && (x2 == 0 || z2 == 0 || x2 == this.width - 1 || z2 == this.depth - 1) && y2 >= this.groundLevel && y2 < this.waterLevel) {
                id = Tile.water.id;
            }
            byte b5 = this.blocks[(y2 * this.depth + z2) * this.width + x2];
            this.blocks[(y2 * this.depth + z2) * this.width + x2] = (byte)id;
            this.setTileMetadata(x2, y2, z2, 0);
            if (b5 != 0) {
                Tile.tiles[b5].onBlockRemoved(this, x2, y2, z2);
            }
            if (id != 0) {
                Tile.tiles[id].onBlockAdded(this, x2, y2, z2);
            }
            if (Tile.lightOpacity[b5] != Tile.lightOpacity[id] || Tile.lightValue[b5] != 0 || Tile.lightValue[id] != 0) {
                this.lightUpdates.updateSkylight(x2, z2, 1, 1);
                this.lightUpdates.updateBlockLight(x2, y2, z2, x2 + 1, y2 + 1, z2 + 1);
            }
            id = 0;
            while (id < this.levelListeners.size()) {
                this.levelListeners.get(id).markBlockAndNeighborsNeedsUpdate(x2, y2, z2);
                ++id;
            }
            return true;
        }
        return false;
    }

    public boolean setTile(int x2, int y2, int z2, int id) {
        if (this.setTileNoNeighborChange(x2, y2, z2, id)) {
            this.updateNeighborAt(x2, y2, z2, id);
            return true;
        }
        return false;
    }

    public void updateNeighborAt(int i1, int i2, int i3, int i4) {
        this.neighborChanged(i1 - 1, i2, i3, i4);
        this.neighborChanged(i1 + 1, i2, i3, i4);
        this.neighborChanged(i1, i2 - 1, i3, i4);
        this.neighborChanged(i1, i2 + 1, i3, i4);
        this.neighborChanged(i1, i2, i3 - 1, i4);
        this.neighborChanged(i1, i2, i3 + 1, i4);
    }

    public boolean setTileNoUpdate(int x2, int y2, int z2, int id) {
        if (x2 >= 0 && y2 >= 0 && z2 >= 0 && x2 < this.width && y2 < this.height && z2 < this.depth) {
            if (id == this.blocks[(y2 * this.depth + z2) * this.width + x2]) {
                return false;
            }
            this.blocks[(y2 * this.depth + z2) * this.width + x2] = (byte)id;
            this.lightUpdates.updateBlockLight(x2, y2, z2, x2 + 1, y2 + 1, z2 + 1);
            return true;
        }
        return false;
    }

    protected void neighborChanged(int x2, int y2, int z2, int id) {
        Tile tile;
        if (x2 >= 0 && y2 >= 0 && z2 >= 0 && x2 < this.width && y2 < this.height && z2 < this.depth && (tile = Tile.tiles[this.blocks[(y2 * this.depth + z2) * this.width + x2]]) != null) {
            tile.neighborChanged(this, x2, y2, z2, id);
        }
    }

    public int getTile(int x2, int y2, int z2) {
        if (x2 < 0) {
            x2 = 0;
        } else if (x2 >= this.width) {
            x2 = this.width - 1;
        }
        if (y2 < 0) {
            y2 = 0;
        } else if (y2 >= this.height) {
            y2 = this.height - 1;
        }
        if (z2 < 0) {
            z2 = 0;
        } else if (z2 >= this.depth) {
            z2 = this.depth - 1;
        }
        return this.blocks[(y2 * this.depth + z2) * this.width + x2] & 0xFF;
    }

    public boolean isSolidTile(int x2, int y2, int z2) {
        Tile tile = Tile.tiles[this.getTile(x2, y2, z2)];
        return tile == null ? false : tile.isSolid();
    }

    public void updateEntities() {
        this.entityMap.updateEntities();
        int i2 = 0;
        while (i2 < this.tileEntities2.size()) {
            this.tileEntities2.get(i2).updateEntity();
            ++i2;
        }
    }

    public void updateLighting() {
        this.lightUpdates.updateLight();
    }

    public float getStarBrightness(float a2) {
        a2 = this.getCelestialAngle(a2);
        a2 = 1.0f - (Mth.cos(a2 * (float)Math.PI * 2.0f) * 2.0f + 0.75f);
        if (a2 < 0.0f) {
            a2 = 0.0f;
        }
        if (a2 > 1.0f) {
            a2 = 1.0f;
        }
        return a2 * a2 * 0.5f;
    }

    public Vec3 getSkyColor(float f1) {
        f1 = Mth.cos(this.getCelestialAngle(f1) * (float)Math.PI * 2.0f) * 2.0f + 0.5f;
        if (f1 < 0.0f) {
            f1 = 0.0f;
        }
        if (f1 > 1.0f) {
            f1 = 1.0f;
        }
        float f22 = (float)(this.skyColor >> 16 & 0xFF) / 255.0f;
        float f3 = (float)(this.skyColor >> 8 & 0xFF) / 255.0f;
        float f4 = (float)(this.skyColor & 0xFF) / 255.0f;
        return new Vec3(f22 *= f1, f3 *= f1, f4 *= f1);
    }

    public final float getCelestialAngle(float f1) {
        return this.skyBrightness > 15 ? 0.0f : ((float)this.worldTime + f1) / 24000.0f - 0.15f;
    }

    public final Vec3 getFogColor(float f1) {
        f1 = Mth.cos(this.getCelestialAngle(f1) * (float)Math.PI * 2.0f) * 2.0f + 0.5f;
        if (f1 < 0.0f) {
            f1 = 0.0f;
        }
        if (f1 > 1.0f) {
            f1 = 1.0f;
        }
        float f22 = (float)(this.fogColor >> 16 & 0xFF) / 255.0f;
        float f3 = (float)(this.fogColor >> 8 & 0xFF) / 255.0f;
        float f4 = (float)(this.fogColor & 0xFF) / 255.0f;
        return new Vec3(f22 *= f1 * 0.94f + 0.06f, f3 *= f1 * 0.94f + 0.06f, f4 *= f1 * 0.91f + 0.09f);
    }

    public final Vec3 getCloudColor(float f1) {
        f1 = Mth.cos(this.getCelestialAngle(f1) * (float)Math.PI * 2.0f) * 2.0f + 0.5f;
        if (f1 < 0.0f) {
            f1 = 0.0f;
        }
        if (f1 > 1.0f) {
            f1 = 1.0f;
        }
        float f22 = (float)(this.cloudColor >> 16 & 0xFF) / 255.0f;
        float f3 = (float)(this.cloudColor >> 8 & 0xFF) / 255.0f;
        float f4 = (float)(this.cloudColor & 0xFF) / 255.0f;
        return new Vec3(f22 *= f1 * 0.9f + 0.1f, f3 *= f1 * 0.9f + 0.1f, f4 *= f1 * 0.85f + 0.15f);
    }

    public final int getSkyBrightness() {
        int i2;
        float f1 = Mth.cos(this.getCelestialAngle(1.0f) * (float)Math.PI * 2.0f) * 1.5f + 0.5f;
        if (f1 < 0.0f) {
            f1 = 0.0f;
        }
        if (f1 > 1.0f) {
            f1 = 1.0f;
        }
        if ((i2 = (int)(f1 * ((float)(15 * this.skyBrightness) / 15.0f - 4.0f) + 4.0f)) > 15) {
            i2 = 15;
        }
        if (i2 < 4) {
            i2 = 4;
        }
        return i2;
    }

    public void tick() {
        int br;
        if (this.doDayCycle) {
            ++this.worldTime;
        }
        if (this.worldTime == 24000) {
            this.worldTime = 0;
        }
        if (this.skylightSubtracted > (br = this.getSkyBrightness())) {
            this.updateChunkLight(this.skylightSubtracted - 1);
        }
        if (this.skylightSubtracted < br) {
            this.updateChunkLight(this.skylightSubtracted + 1);
        }
        int o0 = 1;
        int o1 = 1;
        while (1 << o0 < this.width) {
            ++o0;
        }
        while (1 << o1 < this.depth) {
            ++o1;
        }
        int d2 = this.depth - 1;
        int w2 = this.width - 1;
        int h2 = this.height - 1;
        int tickSize = this.tickList.size();
        if (tickSize > 200) {
            tickSize = 200;
        }
        int i2 = 0;
        while (i2 < tickSize) {
            Coord coord = this.tickList.remove(0);
            if (coord.scheduledTime > 0) {
                --coord.scheduledTime;
                this.tickList.add(coord);
            } else {
                int zz = coord.z;
                int yy = coord.y;
                int xx = coord.x;
                byte id = this.blocks[(coord.y * this.depth + coord.z) * this.width + coord.x];
                if (xx >= 0 && yy >= 0 && zz >= 0 && xx < this.width && yy < this.height && zz < this.depth && id == coord.id && id > 0) {
                    Tile.tiles[id].tick(this, coord.x, coord.y, coord.z, this.random);
                }
            }
            ++i2;
        }
        this.updateLCG += this.width * this.depth * this.height;
        tickSize = this.updateLCG / 200;
        this.updateLCG -= tickSize * 200;
        i2 = 0;
        while (i2 < tickSize) {
            this.randId = this.randId * 3 + 1013904223;
            int r2 = this.randId >> 2;
            int yy = r2 >> o0 + o1 & h2;
            int zz = r2 >> o0 & d2;
            int xx = r2 & w2;
            byte b15 = this.blocks[(yy * this.depth + zz) * this.width + xx];
            if (Tile.shouldTick[b15]) {
                Tile.tiles[b15].tick(this, xx, yy, zz, this.random);
            }
            ++i2;
        }
    }

    public int entitiesInLevelList(Class<?> c2) {
        int entities = 0;
        int i2 = 0;
        while (i2 < this.entityMap.entities.size()) {
            Entity e2 = this.entityMap.entities.get(i2);
            if (c2.isAssignableFrom(e2.getClass())) {
                ++entities;
            }
            ++i2;
        }
        return entities;
    }

    public int getGroundLevel() {
        return this.groundLevel;
    }

    public int getWaterLevel() {
        return this.waterLevel;
    }

    public boolean getIsAnyLiquid(AABB bb) {
        int x0 = (int)bb.x0;
        int x1 = (int)bb.x1 + 1;
        int y0 = (int)bb.y0;
        int y1 = (int)bb.y1 + 1;
        int z0 = (int)bb.z0;
        int z1 = (int)bb.z1 + 1;
        if (bb.x0 < 0.0f) {
            --x0;
        }
        if (bb.y0 < 0.0f) {
            --y0;
        }
        if (bb.z0 < 0.0f) {
            --z0;
        }
        if (x0 < 0) {
            x0 = 0;
        }
        if (y0 < 0) {
            y0 = 0;
        }
        if (z0 < 0) {
            z0 = 0;
        }
        if (x1 > this.width) {
            x1 = this.width;
        }
        if (y1 > this.height) {
            y1 = this.height;
        }
        if (z1 > this.depth) {
            z1 = this.depth;
        }
        int xx = x0;
        while (xx < x1) {
            int yy = y0;
            while (yy < y1) {
                int zz = z0;
                while (zz < z1) {
                    Tile tile = Tile.tiles[this.getTile(xx, yy, zz)];
                    if (tile != null && tile.material.getIsLiquid()) {
                        return true;
                    }
                    ++zz;
                }
                ++yy;
            }
            ++xx;
        }
        return false;
    }

    public boolean isBoundingBoxBurning(AABB bb) {
        int x0 = (int)bb.x0;
        int x1 = (int)bb.x1 + 1;
        int y0 = (int)bb.y0;
        int y1 = (int)bb.y1 + 1;
        int z0 = (int)bb.z0;
        int z1 = (int)bb.z1 + 1;
        int xx = x0;
        while (xx < x1) {
            int yy = y0;
            while (yy < y1) {
                int zz = z0;
                while (zz < z1) {
                    int id = this.getTile(xx, yy, zz);
                    if (id == Tile.fire.id || id == Tile.lava.id || id == Tile.lavaCalm.id) {
                        return true;
                    }
                    ++zz;
                }
                ++yy;
            }
            ++xx;
        }
        return false;
    }

    public boolean handleMaterialAcceleration(AABB bb, Material mat) {
        int x0 = (int)bb.x0;
        int x1 = (int)bb.x1 + 1;
        int y0 = (int)bb.y0;
        int y1 = (int)bb.y1 + 1;
        int z0 = (int)bb.z0;
        int z1 = (int)bb.z1 + 1;
        int xx = x0;
        while (xx < x1) {
            int yy = y0;
            while (yy < y1) {
                int zz = z0;
                while (zz < z1) {
                    Tile tile = Tile.tiles[this.getTile(xx, yy, zz)];
                    if (tile != null && tile.material == mat) {
                        return true;
                    }
                    ++zz;
                }
                ++yy;
            }
            ++xx;
        }
        return false;
    }

    public void scheduleBlockUpdate(int x2, int y2, int z2, int id) {
        Coord coord = new Coord(x2, y2, z2, id);
        if (id > 0) {
            int time;
            coord.scheduledTime = time = Tile.tiles[id].tickRate();
        }
        this.tickList.add(coord);
    }

    public boolean checkIfAABBIsClear1(AABB bb) {
        return this.entityMap.getEntitiesWithinAABB(null, bb).size() == 0;
    }

    public boolean checkIfAABBIsClear(AABB bb) {
        List<Entity> entities = this.entityMap.getEntitiesWithinAABB(null, bb);
        int i2 = 0;
        while (i2 < entities.size()) {
            if (entities.get((int)i2).collision) {
                return false;
            }
            ++i2;
        }
        return true;
    }

    public List<Entity> getEntitiesWithinAABBExcludingEntity(Entity entity, AABB bb) {
        return this.entityMap.getEntitiesWithinAABB(entity, bb);
    }

    public boolean isSolid(float f1, float f2, float f3, float f4) {
        return this.isSolid(f1 - 0.1f, f2 - 0.1f, f3 - 0.1f) ? true : (this.isSolid(f1 - 0.1f, f2 - 0.1f, f3 + 0.1f) ? true : (this.isSolid(f1 - 0.1f, f2 + 0.1f, f3 - 0.1f) ? true : (this.isSolid(f1 - 0.1f, f2 + 0.1f, f3 + 0.1f) ? true : (this.isSolid(f1 + 0.1f, f2 - 0.1f, f3 - 0.1f) ? true : (this.isSolid(f1 + 0.1f, f2 - 0.1f, f3 + 0.1f) ? true : (this.isSolid(f1 + 0.1f, f2 + 0.1f, f3 - 0.1f) ? true : this.isSolid(f1 + 0.1f, f2 + 0.1f, f3 + 0.1f)))))));
    }

    private boolean isSolid(float f1, float f2, float f3) {
        int i4 = this.getTile((int)f1, (int)f2, (int)f3);
        return i4 > 0 && Tile.tiles[i4].isSolid();
    }

    private int getFirstUncoveredBlock(int x2, int z2) {
        int y2 = this.height;
        while ((this.getTile(x2, y2 - 1, z2) == 0 || Tile.tiles[this.getTile((int)x2, (int)(y2 - 1), (int)z2)].material == Material.air) && y2 > 0) {
            --y2;
        }
        return y2;
    }

    public void setSpawnLocation(int x2, int y2, int z2, float rot) {
        this.xSpawn = x2;
        this.ySpawn = y2;
        this.zSpawn = z2;
        this.rotSpawn = rot;
    }

    public float getLightBrightness(int i1, int i2, int i3) {
        return lightBrightnessTable[this.getBlockLightValue(i1, i2, i3)];
    }

    public byte getBlockLightValue(int x2, int y2, int z2) {
        if (x2 < 0) {
            x2 = 0;
        } else if (x2 >= this.width) {
            x2 = this.width - 1;
        }
        if (y2 < 0) {
            y2 = 0;
        } else if (y2 >= this.height) {
            y2 = this.height - 1;
        }
        if (z2 < 0) {
            z2 = 0;
        } else if (z2 >= this.depth) {
            z2 = this.depth - 1;
        }
        return this.blocks[(y2 * this.depth + z2) * this.width + x2] == Tile.stairSingle.id ? (y2 < this.height - 1 ? (byte)(this.lightData[((y2 + 1) * this.depth + z2) * this.width + x2] & 0xF) : (byte)15) : (byte)(this.lightData[(y2 * this.depth + z2) * this.width + x2] & 0xF);
    }

    public byte getTileMetadata(int x2, int y2, int z2) {
        if (x2 < 0) {
            x2 = 0;
        } else if (x2 >= this.width) {
            x2 = this.width - 1;
        }
        if (y2 < 0) {
            y2 = 0;
        } else if (y2 >= this.height) {
            y2 = this.height - 1;
        }
        if (z2 < 0) {
            z2 = 0;
        } else if (z2 >= this.depth) {
            z2 = this.depth - 1;
        }
        return (byte)(this.data[(y2 * this.depth + z2) * this.width + x2] & 0xF);
    }

    public void setTileMetadata(int x2, int y2, int z2, int data) {
        if (x2 < 0) {
            x2 = 0;
        } else if (x2 >= this.width) {
            x2 = this.width - 1;
        }
        if (y2 < 0) {
            y2 = 0;
        } else if (y2 >= this.height) {
            y2 = this.height - 1;
        }
        if (z2 < 0) {
            z2 = 0;
        } else if (z2 >= this.depth) {
            z2 = this.depth - 1;
        }
        this.data[(y2 * this.depth + z2) * this.width + x2] = (byte)data;
        data = 0;
        while (data < this.levelListeners.size()) {
            this.levelListeners.get(data).markBlockAndNeighborsNeedsUpdate(x2, y2, z2);
            ++data;
        }
    }

    public Material getMaterial(int x2, int y2, int z2) {
        int id = this.getTile(x2, y2, z2);
        return id == 0 ? Material.air : Tile.tiles[id].material;
    }

    public boolean isWater(int x2, int y2, int z2) {
        int id = this.getTile(x2, y2, z2);
        return id > 0 && Tile.tiles[id].material == Material.water;
    }

    public HitResult rayTraceBlocks(Vec3 vec0, Vec3 vec1) {
        if (!(Float.isNaN(vec0.x) || Float.isNaN(vec0.y) || Float.isNaN(vec0.z))) {
            if (!(Float.isNaN(vec1.x) || Float.isNaN(vec1.y) || Float.isNaN(vec1.z))) {
                HitResult movingObjectPosition22;
                Tile block23;
                int i21;
                int i3 = Mth.floor(vec1.x);
                int i4 = Mth.floor(vec1.y);
                int i5 = Mth.floor(vec1.z);
                int i6 = Mth.floor(vec0.x);
                int i7 = Mth.floor(vec0.y);
                int i8 = Mth.floor(vec0.z);
                int i9 = 20;
                do {
                    int b19;
                    if (i9-- < 0) {
                        return null;
                    }
                    if (Float.isNaN(vec0.x) || Float.isNaN(vec0.y) || Float.isNaN(vec0.z)) {
                        return null;
                    }
                    if (i6 == i3 && i7 == i4 && i8 == i5) {
                        return null;
                    }
                    float f10 = 999.0f;
                    float f11 = 999.0f;
                    float f12 = 999.0f;
                    if (i3 > i6) {
                        f10 = (float)i6 + 1.0f;
                    }
                    if (i3 < i6) {
                        f10 = i6;
                    }
                    if (i4 > i7) {
                        f11 = (float)i7 + 1.0f;
                    }
                    if (i4 < i7) {
                        f11 = i7;
                    }
                    if (i5 > i8) {
                        f12 = (float)i8 + 1.0f;
                    }
                    if (i5 < i8) {
                        f12 = i8;
                    }
                    float f13 = 999.0f;
                    float f14 = 999.0f;
                    float f15 = 999.0f;
                    float f16 = vec1.x - vec0.x;
                    float f17 = vec1.y - vec0.y;
                    float f18 = vec1.z - vec0.z;
                    if (f10 != 999.0f) {
                        f13 = (f10 - vec0.x) / f16;
                    }
                    if (f11 != 999.0f) {
                        f14 = (f11 - vec0.y) / f17;
                    }
                    if (f12 != 999.0f) {
                        f15 = (f12 - vec0.z) / f18;
                    }
                    if (f13 < f14 && f13 < f15) {
                        b19 = i3 > i6 ? 4 : 5;
                        vec0.x = f10;
                        vec0.y += f17 * f13;
                        vec0.z += f18 * f13;
                    } else if (f14 < f15) {
                        b19 = i4 > i7 ? 0 : 1;
                        vec0.x += f16 * f14;
                        vec0.y = f11;
                        vec0.z += f18 * f14;
                    } else {
                        b19 = i5 > i8 ? 2 : 3;
                        vec0.x += f16 * f15;
                        vec0.y += f17 * f15;
                        vec0.z = f12;
                    }
                    Vec3 vec3D20 = new Vec3(vec0.x, vec0.y, vec0.z);
                    new Vec3(vec0.x, vec0.y, vec0.z).x = Mth.floor(vec0.x);
                    i6 = (int)new Vec3(vec0.x, vec0.y, vec0.z).x;
                    if (b19 == 5) {
                        --i6;
                        vec3D20.x += 1.0f;
                    }
                    vec3D20.y = Mth.floor(vec0.y);
                    i7 = (int)vec3D20.y;
                    if (b19 == 1) {
                        --i7;
                        vec3D20.y += 1.0f;
                    }
                    vec3D20.z = Mth.floor(vec0.z);
                    i8 = (int)vec3D20.z;
                    if (b19 == 3) {
                        --i8;
                        vec3D20.z += 1.0f;
                    }
                    i21 = this.getTile(i6, i7, i8);
                    block23 = Tile.tiles[i21];
                } while (i21 <= 0 || !block23.mayPick() || (movingObjectPosition22 = block23.collisionRayTrace(this, i6, i7, i8, vec0, vec1)) == null);
                return movingObjectPosition22;
            }
            return null;
        }
        return null;
    }

    public boolean growTrees(int x2, int y2, int z2) {
        int i4 = this.random.nextInt(3) + 4;
        boolean z5 = true;
        if (y2 > 0 && y2 + i4 + 1 <= this.height) {
            int i9;
            int i8;
            int i6 = y2;
            while (i6 <= y2 + 1 + i4) {
                int b7 = 1;
                if (i6 == y2) {
                    b7 = 0;
                }
                if (i6 >= y2 + 1 + i4 - 2) {
                    b7 = 2;
                }
                i8 = x2 - b7;
                while (i8 <= x2 + b7 && z5) {
                    i9 = z2 - b7;
                    while (i9 <= z2 + b7 && z5) {
                        if (i8 >= 0 && i6 >= 0 && i9 >= 0 && i8 < this.width && i6 < this.height && i9 < this.depth) {
                            if ((this.blocks[(i6 * this.depth + i9) * this.width + i8] & 0xFF) != 0) {
                                z5 = false;
                            }
                        } else {
                            z5 = false;
                        }
                        ++i9;
                    }
                    ++i8;
                }
                ++i6;
            }
            if (!z5) {
                return false;
            }
            i6 = this.blocks[((y2 - 1) * this.depth + z2) * this.width + x2] & 0xFF;
            if ((i6 == Tile.grass.id || i6 == Tile.dirt.id) && y2 < this.height - i4 - 1) {
                this.setTile(x2, y2 - 1, z2, Tile.dirt.id);
                int i13 = y2 - 3 + i4;
                while (i13 <= y2 + i4) {
                    i8 = i13 - (y2 + i4);
                    i9 = 1 - i8 / 2;
                    int i10 = x2 - i9;
                    while (i10 <= x2 + i9) {
                        int i12 = i10 - x2;
                        i6 = z2 - i9;
                        while (i6 <= z2 + i9) {
                            int i11 = i6 - z2;
                            if ((Math.abs(i12) != i9 || Math.abs(i11) != i9 || this.random.nextInt(2) != 0 && i8 != 0) && !Tile.opaqueCubeLookup[this.getTile(i10, i13, i6)]) {
                                this.setTile(i10, i13, i6, Tile.leaves.id);
                            }
                            ++i6;
                        }
                        ++i10;
                    }
                    ++i13;
                }
                i13 = 0;
                while (i13 < i4) {
                    if (!Tile.opaqueCubeLookup[this.getTile(x2, y2 + i13, z2)]) {
                        this.setTile(x2, y2 + i13, z2, Tile.wood.id);
                    }
                    ++i13;
                }
                return true;
            }
            return false;
        }
        return false;
    }

    public void spawnEntityInWorld(Entity entity) {
        this.entityMap.insert(entity);
        entity.setWorld(this);
        if (entity instanceof Player) {
            this.playerEntities.add((Player)entity);
            System.out.println("Player count: " + this.playerEntities.size());
        }
        int i2 = 0;
        while (i2 < this.levelListeners.size()) {
            this.levelListeners.get(i2).obtainEntitySkin(entity);
            ++i2;
        }
    }

    public void entityRemoved(Entity e2) {
    }

    public void releaseEntitySkin(Entity entity) {
        this.entityMap.remove(entity);
        if (entity instanceof Player) {
            this.playerEntities.remove((Player)entity);
            System.out.println("Player count: " + this.playerEntities.size());
        }
        int i2 = 0;
        while (i2 < this.levelListeners.size()) {
            this.levelListeners.get(i2).releaseEntitySkin(entity);
            ++i2;
        }
    }

    public void createExplosion(Entity entity, float f2, float f3, float f4, float f5) {
        float f44;
        float f28;
        int i22;
        int i21;
        int i20;
        int i19;
        float f18;
        float f11;
        int i10;
        int i9;
        this.playSoundAtPlayer(f2, f3, f4, "random.explode", 4.0f, (1.0f + (this.random.nextFloat() - this.random.nextFloat()) * 0.2f) * 0.7f);
        TreeSet<Integer> treeSet6 = new TreeSet<Integer>();
        float f7 = f5;
        int i8 = 0;
        while (i8 < 16) {
            i9 = 0;
            while (i9 < 16) {
                i10 = 0;
                while (i10 < 16) {
                    if (i8 == 0 || i8 == 15 || i9 == 0 || i9 == 15 || i10 == 0 || i10 == 15) {
                        f11 = (float)i8 / 15.0f * 2.0f - 1.0f;
                        float f12 = (float)i9 / 15.0f * 2.0f - 1.0f;
                        float f13 = (float)i10 / 15.0f * 2.0f - 1.0f;
                        float f14 = (float)Math.sqrt(f11 * f11 + f12 * f12 + f13 * f13);
                        f11 /= f14;
                        f12 /= f14;
                        f13 /= f14;
                        float f15 = f5 * (0.7f + this.random.nextFloat() * 0.6f);
                        float f16 = f2;
                        float f17 = f3;
                        f18 = f4;
                        while (f15 > 0.0f) {
                            i19 = (int)f16;
                            i20 = (int)f17;
                            i21 = (int)f18;
                            i22 = this.getTile(i19, i20, i21);
                            if (i22 > 0) {
                                f15 -= (Tile.tiles[i22].getExplosionResistance() + 0.3f) * 0.3f;
                            }
                            if (f15 > 0.0f) {
                                int i23 = i19 + (i20 << 10) + (i21 << 10 << 10);
                                treeSet6.add(i23);
                            }
                            f16 += f11 * 0.3f;
                            f17 += f12 * 0.3f;
                            f18 += f13 * 0.3f;
                            f15 -= 0.22500001f;
                        }
                    }
                    ++i10;
                }
                ++i9;
            }
            ++i8;
        }
        i8 = (int)(f2 - (f5 *= 2.0f) - 1.0f);
        i9 = (int)(f2 + f5 + 1.0f);
        i10 = (int)(f3 - f5 - 1.0f);
        int i30 = (int)(f3 + f5 + 1.0f);
        int i31 = (int)(f4 - f5 - 1.0f);
        int i32 = (int)(f4 + f5 + 1.0f);
        List<Entity> list33 = this.entityMap.getEntities(entity, i8, i10, i31, i9, i30, i32);
        Vec3 vec3D34 = new Vec3(f2, f3, f4);
        int i35 = 0;
        while (i35 < list33.size()) {
            float f6;
            Entity entity37;
            Entity entity26 = entity37 = list33.get(i35);
            f28 = entity37.x - f2;
            float f29 = entity26.y - f3;
            float f27 = entity26.z - f4;
            f18 = Mth.sqrt(f28 * f28 + f29 * f29 + f27 * f27) / f5;
            if (f18 <= 1.0f) {
                f27 = entity37.x - f2;
                float f40 = entity37.y - f3;
                float f41 = entity37.z - f4;
                float f42 = Mth.sqrt(f27 * f27 + f40 * f40 + f41 * f41);
                f27 /= f42;
                f40 /= f42;
                f41 /= f42;
                float f43 = this.getBlockDensity(vec3D34, entity37.bb);
                f44 = (1.0f - f18) * f43;
                entity37.hurt(entity, (int)((f44 * f44 + f44) / 2.0f * 8.0f * f5 + 1.0f));
                entity37.xd += f27 * f44;
                entity37.yd += f40 * f44;
                entity37.zd += f41 * f44;
            }
            ++i35;
        }
        f5 = f7;
        ArrayList<Integer> arrayList36 = new ArrayList<Integer>();
        arrayList36.addAll(treeSet6);
        int i38 = arrayList36.size() - 1;
        while (i38 >= 0) {
            int i39 = (Integer)arrayList36.get(i38);
            i8 = i39 & 0x3FF;
            i19 = i39 >> 10 & 0x3FF;
            i20 = i39 >> 20 & 0x3FF;
            if (i8 >= 0 && i19 >= 0 && i20 >= 0 && i8 < this.width && i19 < this.height && i20 < this.depth) {
                i21 = this.getTile(i8, i19, i20);
                i22 = 0;
                while (i22 <= 0) {
                    f44 = (float)i8 + this.random.nextFloat();
                    f28 = (float)i19 + this.random.nextFloat();
                    float f24 = (float)i20 + this.random.nextFloat();
                    float f25 = f44 - f2;
                    f7 = f28 - f3;
                    float f29 = f24 - f4;
                    f11 = Mth.sqrt(f25 * f25 + f7 * f7 + f29 * f29);
                    f25 /= f11;
                    f7 /= f11;
                    f29 /= f11;
                    f11 = 0.5f / (f11 / f5 + 0.1f);
                    this.spawnParticle("explode", (f44 + f2) / 2.0f, (f28 + f3) / 2.0f, (f24 + f4) / 2.0f, f25 *= (f11 *= this.random.nextFloat() * this.random.nextFloat() + 0.3f), f7 *= f11, f29 *= f11);
                    this.spawnParticle("smoke", f44, f28, f24, f25, f7, f29);
                    ++i22;
                }
                if (i21 > 0) {
                    Tile.tiles[i21].dropBlockAsItemWithChance(this, i8, i19, i20, this.getTileMetadata(i8, i19, i20), 0.3f);
                    this.setTile(i8, i19, i20, 0);
                    Tile.tiles[i21].onBlockDestroyedByExplosion(this, i8, i19, i20);
                }
            }
            --i38;
        }
    }

    protected float getBlockDensity(Vec3 vec3D, AABB bb) {
        float f3 = 1.0f / ((bb.x1 - bb.x0) * 2.0f + 1.0f);
        float f4 = 1.0f / ((bb.y1 - bb.y0) * 2.0f + 1.0f);
        float f5 = 1.0f / ((bb.z1 - bb.z0) * 2.0f + 1.0f);
        int i6 = 0;
        int i7 = 0;
        float f8 = 0.0f;
        while (f8 <= 1.0f) {
            float f9 = 0.0f;
            while (f9 <= 1.0f) {
                float f10 = 0.0f;
                while (f10 <= 1.0f) {
                    float f11 = bb.x0 + (bb.x1 - bb.x0) * f8;
                    float f12 = bb.y0 + (bb.y1 - bb.y0) * f9;
                    float f13 = bb.z0 + (bb.z1 - bb.z0) * f10;
                    if (this.rayTraceBlocks(new Vec3(f11, f12, f13), vec3D) == null) {
                        ++i6;
                    }
                    ++i7;
                    f10 += f5;
                }
                f9 += f4;
            }
            f8 += f3;
        }
        return (float)i6 / (float)i7;
    }

    public Player getClosestPlayerToEntity(Entity entity, float distance) {
        return this.getClosestPlayer(entity.x, entity.y, entity.z, distance);
    }

    public Player getClosestPlayer(float x2, float y2, float z2, float max) {
        float min = -1.0f;
        Player closest = null;
        int i2 = 0;
        while (i2 < this.playerEntities.size()) {
            Player player = this.playerEntities.get(i2);
            float dist = player.getDistanceSq(x2, y2, z2);
            if (dist < max * max && ((double)min == -1.0 || dist < min)) {
                min = dist;
                closest = player;
            }
            ++i2;
        }
        return closest;
    }

    public final Entity findSubclassOf(Class<? extends Entity> c2) {
        int i2 = 0;
        while (i2 < this.entityMap.entities.size()) {
            Entity e2 = this.entityMap.entities.get(i2);
            if (c2.isAssignableFrom(e2.getClass())) {
                return e2;
            }
            ++i2;
        }
        return null;
    }

    public final int fluidFlowCheck(int i1, int i2, int i3, int i4, int i5) {
        if (i1 >= 0 && i2 >= 0 && i3 >= 0 && i1 < this.width && i2 < this.height && i3 < this.depth) {
            boolean z12;
            int i10;
            int n2 = 0;
            int i6 = i1;
            int i7 = i3;
            int i8 = ((i2 << 10) + i3 << 10) + i1;
            int b9 = 0;
            int i20 = b9 + 1;
            this.coords[0] = i1 + (i3 << 10);
            int n3 = -9999;
            if (i4 == Tile.waterCalm.id || i4 == Tile.water.id) {
                n2 = Tile.waterSource.id;
            }
            if (i4 == Tile.lavaCalm.id || i4 == Tile.lava.id) {
                n2 = Tile.lavaSource.id;
            }
            do {
                z12 = false;
                int i13 = -1;
                i10 = 0;
                if ((floodFillCounter = (short)(floodFillCounter + 1)) == 30000) {
                    Arrays.fill(this.floodFillCounters, (short)0);
                    floodFillCounter = 1;
                }
                while (true) {
                    int i14;
                    if (i20 <= 0) {
                        ++i2;
                        break;
                    }
                    if (this.floodFillCounters[i14 = this.coords[--i20]] == floodFillCounter) continue;
                    i1 = i14 % 1024;
                    i3 = i14 / 1024;
                    int i15 = i3 - i7;
                    i15 *= i15;
                    while (i1 > 0 && this.floodFillCounters[i14 - 1] != floodFillCounter && (this.blocks[(i2 * this.depth + i3) * this.width + i1 - 1] == i4 || this.blocks[(i2 * this.depth + i3) * this.width + i1 - 1] == i5)) {
                        --i1;
                        --i14;
                    }
                    if (i1 > 0 && this.blocks[(i2 * this.depth + i3) * this.width + i1 - 1] == n2) {
                        z12 = true;
                    }
                    boolean z16 = false;
                    boolean z17 = false;
                    boolean z18 = false;
                    while (i1 < this.width && this.floodFillCounters[i14] != floodFillCounter && (this.blocks[(i2 * this.depth + i3) * this.width + i1] == i4 || this.blocks[(i2 * this.depth + i3) * this.width + i1] == i5)) {
                        boolean z22;
                        byte b19;
                        if (i3 > 0) {
                            b19 = this.blocks[(i2 * this.depth + i3 - 1) * this.width + i1];
                            if (b19 == n2) {
                                z12 = true;
                            }
                            if ((z22 = this.floodFillCounters[i14 - 1024] != floodFillCounter && (b19 == i4 || b19 == i5)) && !z16) {
                                this.coords[i20++] = i14 - 1024;
                            }
                            z16 = z22;
                        }
                        if (i3 < this.depth - 1) {
                            b19 = this.blocks[(i2 * this.depth + i3 + 1) * this.width + i1];
                            if (b19 == n2) {
                                z12 = true;
                            }
                            if ((z22 = this.floodFillCounters[i14 + 1024] != floodFillCounter && (b19 == i4 || b19 == i5)) && !z17) {
                                this.coords[i20++] = i14 + 1024;
                            }
                            z17 = z22;
                        }
                        if (i2 < this.height - 1) {
                            b19 = this.blocks[((i2 + 1) * this.depth + i3) * this.width + i1];
                            z22 = b19 == i4 || b19 == i5;
                            if (z22 && !z18) {
                                this.floodedBlocks[i10++] = i14;
                            }
                            z18 = z22;
                        }
                        int i23 = i1 - i6;
                        if ((i23 = i23 * i23 + i15) > i13) {
                            i13 = i23;
                            i8 = ((i2 << 10) + i3 << 10) + i1;
                        }
                        this.floodFillCounters[i14++] = floodFillCounter;
                        ++i1;
                    }
                    if (i1 >= this.width || this.blocks[(i2 * this.depth + i3) * this.width + i1] != n2) continue;
                    z12 = true;
                }
                int[] i21 = this.floodedBlocks;
                this.floodedBlocks = this.coords;
                this.coords = i21;
                i20 = i10;
            } while (i10 > 0);
            if (z12) {
                return -9999;
            }
            return i8;
        }
        return -1;
    }

    /*
     * Unable to fully structure code
     */
    public final int floodFill(int i1, int i2, int i3, int i4, int i5) {
        block13: {
            if (i1 < 0 || i2 < 0 || i3 < 0 || i1 >= this.width || i2 >= this.height || i3 >= this.depth) break block13;
            if ((Level.floodFillCounter = (short)(Level.floodFillCounter + 1)) == 30000) {
                Arrays.fill(this.floodFillCounters, (short)0);
                Level.floodFillCounter = 1;
            }
            int i7 = 0;
            boolean z8 = false;
            boolean z9 = false;
            byte b10 = 0;
            boolean z12 = false;
            byte b6 = 0;
            int i11 = b6 + 1;
            this.coords[0] = i1 + (i3 << 10);
            while (true) {
                if (i11 <= 0) {
                    return 1;
                }
                if (this.floodFillCounters[i7 = this.coords[--i11]] == Level.floodFillCounter) continue;
                i1 = i7 % 1024;
                i3 = i7 / 1024;
                if (i1 == 0 || i1 == this.width - 1 || i2 == 0 || i2 == this.height - 1 || i3 == 0 || i3 == this.depth - 1) {
                    return 2;
                }
                while (i1 > 0 && this.floodFillCounters[i7 - 1] != Level.floodFillCounter && (this.blocks[(i2 * this.depth + i3) * this.width + i1 - 1] == i4 || this.blocks[(i2 * this.depth + i3) * this.width + i1 - 1] == i5)) {
                    --i1;
                    --i7;
                }
                // 1 sources

                if (i1 > 0 && this.blocks[(i2 * this.depth + i3) * this.width + i1 - 1] == 0) {
                    return 0;
                }
                z8 = false;
                z9 = false;
                while (i1 < this.width && this.floodFillCounters[i7] != Level.floodFillCounter && (this.blocks[(i2 * this.depth + i3) * this.width + i1] == i4 || this.blocks[(i2 * this.depth + i3) * this.width + i1] == i5)) {
                    if (i1 == 0 || i1 == this.width - 1) {
                        return 2;
                    }
                    if (i3 > 0) {
                        b10 = this.blocks[(i2 * this.depth + i3 - 1) * this.width + i1];
                        if (b10 == 0) {
                            return 0;
                        }
                        z12 = this.floodFillCounters[i7 - 1024] != Level.floodFillCounter && (b10 == i4 || b10 == i5);
                        if (z12 && !z8) {
                            this.coords[i11++] = i7 - 1024;
                        }
                        z8 = z12;
                    }
                    if (i3 < this.depth - 1) {
                        b10 = this.blocks[(i2 * this.depth + i3 + 1) * this.width + i1];
                        if (b10 == 0) {
                            return 0;
                        }
                        z12 = this.floodFillCounters[i7 + 1024] != Level.floodFillCounter && (b10 == i4 || b10 == i5);
                        if (z12 && !z9) {
                            this.coords[i11++] = i7 + 1024;
                        }
                        z9 = z12;
                    }
                    this.floodFillCounters[i7] = Level.floodFillCounter;
                    ++i7;
                    ++i1;
                }
                if (i1 < this.width && this.blocks[(i2 * this.depth + i3) * this.width + i1] == 0) break;
            }
            return 0;
        }
        return 0;
    }

    public final void playSoundAtEntity(Entity entity, String string2, float f3, float f4) {
        int i2 = 0;
        while (i2 < this.levelListeners.size()) {
            this.levelListeners.get(i2).playSoundAtEntity(entity, string2, f3, f4);
            ++i2;
        }
    }

    public final void playSoundEffect(float f1, float f2, float f3, String string4, float f5) {
        try {
            int i2 = 0;
            while (i2 < this.levelListeners.size()) {
                this.levelListeners.get(i2).playMusic(string4, f1, f2, f3, 0.0f);
                ++i2;
            }
        }
        catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public final void playSoundAtPlayer(float f1, float f2, float f3, String string4, float f5, float f6) {
        int i2 = 0;
        while (i2 < this.levelListeners.size()) {
            this.levelListeners.get(i2).playSoundAtPlayer(f1, f2, f3, string4, f5, f6);
            ++i2;
        }
    }

    public final void onTileHit(int x2, int y2, int z2, int f2) {
        if (f2 == 0) {
            --y2;
        }
        if (f2 == 1) {
            ++y2;
        }
        if (f2 == 2) {
            --z2;
        }
        if (f2 == 3) {
            ++z2;
        }
        if (f2 == 4) {
            --x2;
        }
        if (f2 == 5) {
            ++x2;
        }
        if (this.getTile(x2, y2, z2) == Tile.fire.id) {
            this.playSoundAtPlayer((float)x2 + 0.5f, (float)y2 + 0.5f, (float)z2 + 0.5f, "random.fizz", 0.5f, 2.6f + (this.random.nextFloat() - this.random.nextFloat()) * 0.8f);
            this.setTile(x2, y2, z2, 0);
        }
    }

    public final void setTileEntity(int x2, int y2, int z2, TileEntity te) {
        te.level = this;
        te.x = x2;
        te.y = y2;
        te.z = z2;
        this.tileEntities.put(x2 + (y2 << 10) + (z2 << 10 << 10), te);
        this.tileEntities2.add(te);
    }

    public final void removeTileEntity(int x2, int y2, int z2) {
        this.tileEntities2.remove(this.tileEntities.remove(x2 + (y2 << 10) + (z2 << 10 << 10)));
    }

    public final TileEntity getTileEntity(int x2, int y2, int z2) {
        int id;
        int at = x2 + (y2 << 10) + (z2 << 10 << 10);
        TileEntity te = this.tileEntities.get(at);
        if (te == null && (id = this.getTile(x2, y2, z2)) > 0) {
            ((ContainerTile)Tile.tiles[id]).onBlockAdded(this, x2, y2, z2);
            te = this.tileEntities.get(at);
        }
        return te;
    }

    public final void spawnParticle(String string1, float f2, float f3, float f4, float f5, float f6, float f7) {
        int i2 = 0;
        while (i2 < this.levelListeners.size()) {
            this.levelListeners.get(i2).spawnParticle(string1, f2, f3, f4, f5, f6, f7);
            ++i2;
        }
    }

    public final void createRandomTicks(int x2, int y2, int z2) {
        int i2 = 0;
        while (i2 < 1000) {
            int zz;
            int yy;
            int xx = x2 + this.random.nextInt(16) - this.random.nextInt(16);
            int id = this.getTile(xx, yy = y2 + this.random.nextInt(16) - this.random.nextInt(16), zz = z2 + this.random.nextInt(16) - this.random.nextInt(16));
            if (id > 0) {
                Tile.tiles[id].randomTick(this, xx, yy, zz, this.rand);
            }
            ++i2;
        }
    }

    public final String debugSkylightUpdates() {
        return this.tickList.size() + ". L: " + this.lightUpdates.debugLightUpdates();
    }

    public final void clearEntitiesInLevel() {
        int i2 = 0;
        while (i2 < this.levelListeners.size()) {
            IWorldAccess listeners = this.levelListeners.get(i2);
            int j2 = 0;
            while (j2 < this.entityMap.entities.size()) {
                listeners.releaseEntitySkin(this.entityMap.entities.get(i2));
                ++j2;
            }
            ++i2;
        }
    }

    public final void clearEntitiesInLevelNoPlayers() {
        int j2 = 0;
        while (j2 < this.entityMap.entities.size()) {
            Entity e2 = this.entityMap.entities.get(j2);
            if (!(e2 instanceof Player)) {
                e2.remove();
            }
            ++j2;
        }
    }

    public Entity getEntity(int id) {
        for (Entity e2 : this.entityMap.entities) {
            if (e2.entityID != id) continue;
            return e2;
        }
        return null;
    }

    public void updateChunkLight(int i1) {
        this.lightUpdates.updateDaylightCycle(i1);
    }

    /*
     * Unable to fully structure code
     */
    public boolean canBlockSeeTheSky(int x, int y, int z) {
        int at = x + z * this.width;
        if (at >= this.heightMap.length || at < 0) {
            return true;
        }
        if (this.heightMap[at] <= y) {
            return true;
        }
        while (y < this.height) {
            if (Tile.opaqueCubeLookup[this.getTile(x, y, z)]) {
                return false;
            }
            ++y;
        }
        return true;
    }

    public byte[] copyBlocks() {
        return Arrays.copyOf(this.blocks, this.blocks.length);
    }

    public byte[] copyMetadata() {
        return Arrays.copyOf(this.data, this.data.length);
    }

    public void updateEntityStatus(Entity target, byte b2, float xx, float zz) {
    }

    public boolean canBlockBePlacedAt(int id, int x2, int y2, int z2, boolean var5, int f2) {
        int current = this.getTile(x2, y2, z2);
        Tile t0 = Tile.tiles[current];
        Tile t1 = Tile.tiles[id];
        AABB abb = t1.getAABB(x2, y2, z2);
        if (var5) {
            abb = null;
        }
        if (abb != null && !this.checkIfAABBIsClear(abb)) {
            return false;
        }
        if (t0 == Tile.water || t0 == Tile.waterCalm || t0 == Tile.lava || t0 == Tile.lavaCalm || t0 == Tile.fire) {
            t0 = null;
        }
        return id > 0 && t0 == null && t1.canPlaceBlockAt(this, x2, y2, z2);
    }
}

