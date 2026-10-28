/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.level;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightUpdate;
import net.minecraft.world.level.tile.Tile;

public final class Light {
    private int lightingUpdateCounter = 0;
    private List<int[]> lightingUpdateList = new ArrayList<int[]>();
    private Level worldObj;
    private int worldWidth;
    private int worldLength;
    private int worldHeight;
    private byte[] blocks;
    private byte[] lightData;
    private int[] heightMap;
    private List<LightUpdate> skyLightList = new ArrayList<LightUpdate>();
    private List<LightUpdate> blockLightList = new ArrayList<LightUpdate>();
    private List<LightUpdate> lightingUpdateList1 = new ArrayList<LightUpdate>();
    private List<int[]> lightingUpdateList2 = new ArrayList<int[]>();
    private int[] lightingUpdateList3 = this.getLightingUpdates();
    private byte[] chunks;
    private LightUpdate metadataChunkBlock = null;
    private int lightValue = 0;
    private int skylightSubtracted;

    public Light(Level world) {
        this.worldObj = world;
        this.worldWidth = world.width;
        this.worldLength = world.depth;
        this.worldHeight = world.height;
        this.chunks = new byte[world.blocks.length / 8];
        this.blocks = world.blocks;
        this.lightData = world.lightData;
        this.heightMap = world.heightMap;
    }

    private int[] getLightingUpdates() {
        return this.lightingUpdateList2.size() > 0 ? this.lightingUpdateList2.remove(this.lightingUpdateList2.size() - 1) : new int[32768];
    }

    public final void updateSkylight(int i1, int i2, int i3, int i4) {
        this.lightingUpdateList1.add(new LightUpdate(this, i1, i2, 0, i3, i4, 1));
    }

    public final void updateDaylightCycle(int i1) {
        if (i1 > 15) {
            i1 = 15;
        }
        if (i1 < 0) {
            i1 = 0;
        }
        this.skylightSubtracted = i1 - this.worldObj.skylightSubtracted;
        if (this.skylightSubtracted != 0) {
            this.lightValue = this.worldObj.skylightSubtracted;
            this.worldObj.skylightSubtracted = i1;
            while (this.metadataChunkBlock != null) {
                this.updateLight(64);
            }
            this.metadataChunkBlock = new LightUpdate(this, 0, 0, 0, this.worldObj.width, this.worldObj.height, this.worldObj.depth);
        }
    }

    private void updateLight(int updates) {
        int i7;
        int i6;
        int i2 = this.metadataChunkBlock.x;
        int i3 = this.metadataChunkBlock.maxX;
        int i4 = this.metadataChunkBlock.z;
        int i5 = this.metadataChunkBlock.maxZ;
        int i8 = i2;
        while (i8 < i3) {
            if (updates-- <= 0 && i8 != i3 - 1) {
                this.metadataChunkBlock.x = i8;
                return;
            }
            i6 = i4;
            while (i6 < i5) {
                i7 = this.heightMap[i8 + i6 * this.worldWidth] - 1;
                while (i7 > 0 && Tile.lightOpacity[this.blocks[(i7 * this.worldLength + i6) * this.worldWidth + i8]] < 100) {
                    --i7;
                }
                ++i7;
                while (i7 < this.worldHeight) {
                    int i9;
                    int i82 = (i7 * this.worldLength + i6) * this.worldWidth + i8;
                    if (Tile.lightValue[this.blocks[i82]] == 0 && (i9 = this.lightData[i82] & 0xF) <= this.lightValue) {
                        if (this.skylightSubtracted < 0 && i9 > 0) {
                            int n2 = i82;
                            this.lightData[n2] = (byte)(this.lightData[n2] - 1);
                        } else if (this.skylightSubtracted > 0 && i9 < 15) {
                            int n3 = i82;
                            this.lightData[n3] = (byte)(this.lightData[n3] + 1);
                        }
                    }
                    ++i7;
                }
                ++i6;
            }
            ++i8;
        }
        i6 = 0;
        while (i6 < this.worldWidth) {
            i7 = 0;
            while (i7 < this.worldLength) {
                this.blockLightList.add(new LightUpdate(this, i6, 0, i7, i6 + 32, this.worldHeight, i7 + 32));
                this.skyLightList.add(new LightUpdate(this, i6, 0, i7, i6 + 32, this.worldHeight, i7 + 32));
                i7 += 32;
            }
            i6 += 32;
        }
        i6 = 0;
        while (i6 < this.worldObj.levelListeners.size()) {
            this.worldObj.levelListeners.get(i6).updateAllRenderers();
            ++i6;
        }
        this.metadataChunkBlock = null;
    }

    public final void updateBlockLight(int i1, int i2, int i3, int i4, int i5, int i6) {
        this.blockLightList.add(new LightUpdate(this, i1, i2, i3, i4, i5, i6));
    }

    private void updateLists(int i1, int i2, int i3, int i4, int i5, int i6) {
        int i7 = i2;
        while (i7 < i5) {
            int i72 = i3;
            while (i72 < i6) {
                int i8 = i1;
                while (i8 < i4) {
                    int i9 = i8 + i7 * this.worldWidth + i72 * this.worldWidth * this.worldHeight;
                    if ((this.chunks[i9 >> 3] & 1 << (i9 & 7)) == 0) {
                        this.chunks[i9 >> 3] = (byte)(this.chunks[i9 >> 3] | 1 << (i9 & 7));
                        this.lightingUpdateList3[this.lightingUpdateCounter++] = i9;
                        if ((this.chunks[i9 >> 3] & 1 << (i9 & 7)) == 0) {
                            System.out.println("OMG ERROR!");
                        }
                        if (this.lightingUpdateCounter > this.lightingUpdateList3.length - 32) {
                            i9 = this.lightingUpdateList3[--this.lightingUpdateCounter];
                            this.lightingUpdateList3[this.lightingUpdateList3.length - 1] = this.lightingUpdateCounter;
                            this.lightingUpdateList.add(this.lightingUpdateList3);
                            this.lightingUpdateList3 = this.getLightingUpdates();
                            this.lightingUpdateCounter = 1;
                            this.lightingUpdateList3[0] = i9;
                        }
                    }
                    ++i8;
                }
                ++i72;
            }
            ++i7;
        }
    }

    public final void updateLight() {
        if (this.lightingUpdateList2.size() > 0) {
            this.lightingUpdateList2.remove(this.lightingUpdateList2.size() - 1);
        }
        int i1 = 5;
        while (this.skyLightList.size() > 0 && i1-- > 0) {
            LightUpdate metadataChunkBlock2 = this.skyLightList.remove(0);
            int i3 = 0;
            while (i3 < this.worldObj.levelListeners.size()) {
                this.worldObj.levelListeners.get(i3).markBlockRangeNeedsUpdate(metadataChunkBlock2.x, metadataChunkBlock2.y, metadataChunkBlock2.z, metadataChunkBlock2.maxX, metadataChunkBlock2.maxY, metadataChunkBlock2.maxZ);
                ++i3;
            }
        }
        if (this.metadataChunkBlock != null) {
            this.updateLight(8);
        } else {
            int i19 = 0;
            while (i19 < 16) {
                int i13;
                int i12;
                int i11;
                int i10;
                int i9;
                int i8;
                Light light22;
                int i4;
                int i5;
                int i6;
                int i7;
                if (this.blockLightList.size() > 0) {
                    LightUpdate metadataChunkBlock21 = this.blockLightList.remove(0);
                    this.updateLists(metadataChunkBlock21.x, metadataChunkBlock21.y, metadataChunkBlock21.z, metadataChunkBlock21.maxX, metadataChunkBlock21.maxY, metadataChunkBlock21.maxZ);
                }
                if (this.lightingUpdateList1.size() > 0) {
                    LightUpdate metadataChunkBlock21 = this.lightingUpdateList1.remove(0);
                    i7 = metadataChunkBlock21.maxY;
                    i6 = metadataChunkBlock21.maxX;
                    i5 = metadataChunkBlock21.y;
                    i4 = metadataChunkBlock21.x;
                    light22 = this;
                    i8 = i4;
                    while (i8 < i4 + i6) {
                        i9 = i5;
                        while (i9 < i5 + i7) {
                            i10 = light22.heightMap[i8 + i9 * light22.worldWidth];
                            i11 = light22.worldHeight - 1;
                            while (i11 > 0 && Tile.lightOpacity[light22.blocks[(i11 * light22.worldLength + i9) * light22.worldWidth + i8]] == 0) {
                                --i11;
                            }
                            light22.heightMap[i8 + i9 * light22.worldWidth] = i11 + 1;
                            if (i10 != i11) {
                                i12 = i10 < i11 ? i10 : i11;
                                i13 = i10 > i11 ? i10 : i11;
                                light22.updateLists(i8, i12, i9, i8 + 1, i13, i9 + 1);
                            }
                            ++i9;
                        }
                        ++i8;
                    }
                }
                light22 = this;
                i4 = this.worldObj.skylightSubtracted;
                i5 = -999;
                i6 = -999;
                i7 = -999;
                i8 = -999;
                i9 = -999;
                i10 = -999;
                i11 = 1024;
                i12 = 0;
                while (i11-- > 0 && (light22.lightingUpdateCounter > 0 || light22.lightingUpdateList.size() > 0)) {
                    ++i12;
                    if (light22.lightingUpdateCounter == 0) {
                        if (light22.lightingUpdateList3 != null) {
                            int[] i23 = light22.lightingUpdateList3;
                            light22.lightingUpdateList2.add(i23);
                        }
                        light22.lightingUpdateList3 = light22.lightingUpdateList.remove(light22.lightingUpdateList.size() - 1);
                        light22.lightingUpdateCounter = light22.lightingUpdateList3[light22.lightingUpdateList3.length - 1];
                    }
                    if (light22.lightingUpdateCounter > light22.lightingUpdateList3.length - 32) {
                        i13 = light22.lightingUpdateList3[--light22.lightingUpdateCounter];
                        light22.lightingUpdateList3[light22.lightingUpdateList3.length - 1] = light22.lightingUpdateCounter;
                        light22.lightingUpdateList.add(light22.lightingUpdateList3);
                        light22.lightingUpdateList3 = light22.getLightingUpdates();
                        light22.lightingUpdateCounter = 1;
                        light22.lightingUpdateList3[0] = i13;
                        continue;
                    }
                    i13 = light22.lightingUpdateList3[--light22.lightingUpdateCounter];
                    i1 = i13 % light22.worldWidth;
                    int i14 = i13 / light22.worldWidth % light22.worldHeight;
                    int i15 = i13 / light22.worldWidth / light22.worldHeight % light22.worldLength;
                    light22.chunks[i13 >> 3] = (byte)(light22.chunks[i13 >> 3] ^ 1 << (i13 & 7));
                    i13 = light22.heightMap[i1 + i15 * light22.worldWidth];
                    int i16 = i14 >= i13 ? i4 : 0;
                    byte b24 = light22.blocks[(i14 * light22.worldLength + i15) * light22.worldWidth + i1];
                    int i17 = Tile.lightOpacity[b24];
                    if (i17 > 100) {
                        i16 = 0;
                    } else if (i16 < 15) {
                        int i18;
                        if (i17 == 0) {
                            i17 = 1;
                        }
                        if (i1 > 0 && (i18 = (light22.lightData[(i14 * light22.worldLength + i15) * light22.worldWidth + (i1 - 1)] & 0xF) - i17) > i16) {
                            i16 = i18;
                        }
                        if (i1 < light22.worldWidth - 1 && (i18 = (light22.lightData[(i14 * light22.worldLength + i15) * light22.worldWidth + i1 + 1] & 0xF) - i17) > i16) {
                            i16 = i18;
                        }
                        if (i14 > 0 && (i18 = (light22.lightData[((i14 - 1) * light22.worldLength + i15) * light22.worldWidth + i1] & 0xF) - i17) > i16) {
                            i16 = i18;
                        }
                        if (i14 < light22.worldHeight - 1 && (i18 = (light22.lightData[((i14 + 1) * light22.worldLength + i15) * light22.worldWidth + i1] & 0xF) - i17) > i16) {
                            i16 = i18;
                        }
                        if (i15 > 0 && (i18 = (light22.lightData[(i14 * light22.worldLength + (i15 - 1)) * light22.worldWidth + i1] & 0xF) - i17) > i16) {
                            i16 = i18;
                        }
                        if (i15 < light22.worldLength - 1 && (i18 = (light22.lightData[(i14 * light22.worldLength + i15 + 1) * light22.worldWidth + i1] & 0xF) - i17) > i16) {
                            i16 = i18;
                        }
                    }
                    if (i16 < Tile.lightValue[b24]) {
                        i16 = Tile.lightValue[b24];
                    }
                    if ((light22.lightData[(i14 * light22.worldLength + i15) * light22.worldWidth + i1] & 0xF) == i16) continue;
                    light22.lightData[(i14 * light22.worldLength + i15) * light22.worldWidth + i1] = (byte)((light22.lightData[(i14 * light22.worldLength + i15) * light22.worldWidth + i1] & 0xF0) + i16);
                    if (i1 > 0 && (light22.lightData[(i14 * light22.worldLength + i15) * light22.worldWidth + (i1 - 1)] & 0xF) != i16 - 1 && (light22.chunks[(i13 = i1 - 1 + i14 * light22.worldWidth + i15 * light22.worldWidth * light22.worldHeight) >> 3] & 1 << (i13 & 7)) == 0) {
                        light22.chunks[i13 >> 3] = (byte)(light22.chunks[i13 >> 3] | 1 << (i13 & 7));
                        light22.lightingUpdateList3[light22.lightingUpdateCounter++] = i13;
                    }
                    if (i1 < light22.worldWidth - 1 && (light22.lightData[(i14 * light22.worldLength + i15) * light22.worldWidth + i1 + 1] & 0xF) != i16 - 1 && (light22.chunks[(i13 = i1 + 1 + i14 * light22.worldWidth + i15 * light22.worldWidth * light22.worldHeight) >> 3] & 1 << (i13 & 7)) == 0) {
                        light22.chunks[i13 >> 3] = (byte)(light22.chunks[i13 >> 3] | 1 << (i13 & 7));
                        light22.lightingUpdateList3[light22.lightingUpdateCounter++] = i13;
                    }
                    if (i14 > 0 && (light22.lightData[((i14 - 1) * light22.worldLength + i15) * light22.worldWidth + i1] & 0xF) != i16 - 1 && (light22.chunks[(i13 = i1 + (i14 - 1) * light22.worldWidth + i15 * light22.worldWidth * light22.worldHeight) >> 3] & 1 << (i13 & 7)) == 0) {
                        light22.chunks[i13 >> 3] = (byte)(light22.chunks[i13 >> 3] | 1 << (i13 & 7));
                        light22.lightingUpdateList3[light22.lightingUpdateCounter++] = i13;
                    }
                    if (i14 < light22.worldHeight - 1 && (light22.lightData[((i14 + 1) * light22.worldLength + i15) * light22.worldWidth + i1] & 0xF) != i16 - 1 && (light22.chunks[(i13 = i1 + (i14 + 1) * light22.worldWidth + i15 * light22.worldWidth * light22.worldHeight) >> 3] & 1 << (i13 & 7)) == 0) {
                        light22.chunks[i13 >> 3] = (byte)(light22.chunks[i13 >> 3] | 1 << (i13 & 7));
                        light22.lightingUpdateList3[light22.lightingUpdateCounter++] = i13;
                    }
                    if (i15 > 0 && (light22.lightData[(i14 * light22.worldLength + (i15 - 1)) * light22.worldWidth + i1] & 0xF) != i16 - 1 && (light22.chunks[(i13 = i1 + i14 * light22.worldWidth + (i15 - 1) * light22.worldWidth * light22.worldHeight) >> 3] & 1 << (i13 & 7)) == 0) {
                        light22.chunks[i13 >> 3] = (byte)(light22.chunks[i13 >> 3] | 1 << (i13 & 7));
                        light22.lightingUpdateList3[light22.lightingUpdateCounter++] = i13;
                    }
                    if (i15 < light22.worldLength - 1 && (light22.lightData[(i14 * light22.worldLength + i15 + 1) * light22.worldWidth + i1] & 0xF) != i16 - 1 && (light22.chunks[(i13 = i1 + i14 * light22.worldWidth + (i15 + 1) * light22.worldWidth * light22.worldHeight) >> 3] & 1 << (i13 & 7)) == 0) {
                        light22.chunks[i13 >> 3] = (byte)(light22.chunks[i13 >> 3] | 1 << (i13 & 7));
                        light22.lightingUpdateList3[light22.lightingUpdateCounter++] = i13;
                    }
                    if (i5 == -999) {
                        i5 = i1;
                        i6 = i1;
                        i7 = i14;
                        i8 = i14;
                        i9 = i15;
                        i10 = i15;
                    }
                    if (i1 < i5) {
                        i5 = i1;
                    } else if (i1 > i6) {
                        i6 = i1;
                    }
                    if (i14 > i8) {
                        i8 = i14;
                    } else if (i14 < i7) {
                        i7 = i14;
                    }
                    if (i15 < i9) {
                        i9 = i15;
                        continue;
                    }
                    if (i15 <= i10) continue;
                    i10 = i15;
                }
                if (i5 > -999) {
                    light22.skyLightList.add(new LightUpdate(light22, i5, i7, i9, i6, i8, i10));
                }
                ++i19;
            }
        }
    }

    public final String debugLightUpdates() {
        return "" + (this.blockLightList.size() + this.skyLightList.size());
    }
}

