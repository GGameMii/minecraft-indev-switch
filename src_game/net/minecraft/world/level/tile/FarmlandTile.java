/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.level.tile;

import java.util.Random;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.tile.Tile;
import net.minecraft.world.phys.AABB;

public final class FarmlandTile
extends Tile {
    protected FarmlandTile(int blockID) {
        super(60, Material.ground);
        this.tex = 87;
        this.setTicking(true);
        this.setShape(0.0f, 0.0f, 0.0f, 1.0f, 0.9375f, 1.0f);
        this.setLightOpacity(255);
    }

    @Override
    public final AABB getAABB(int xCoord, int yCoord, int zCoord) {
        return new AABB(xCoord, yCoord, zCoord, xCoord + 1, yCoord + 1, zCoord + 1);
    }

    @Override
    public final boolean isSolid() {
        return false;
    }

    @Override
    public final boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public final int getTextureFromSideAndMetadata(int i1, int i2) {
        return i1 == 1 && i2 > 0 ? this.tex - 1 : (i1 == 1 ? this.tex : 2);
    }

    @Override
    public final void tick(Level world, int xCoord, int yCoord, int zCoord, Random random) {
        if (world.onlineLevel) {
            return;
        }
        if (random.nextInt(5) == 0) {
            int i11;
            int i10;
            boolean z10000;
            int i8 = zCoord;
            int i7 = yCoord;
            int i6 = xCoord;
            Level world12 = world;
            int i9 = xCoord - 4;
            block0: while (true) {
                if (i9 > i6 + 4) {
                    z10000 = false;
                    break;
                }
                i10 = i7;
                while (i10 <= i7 + 1) {
                    i11 = i8 - 4;
                    while (i11 <= i8 + 4) {
                        if (world12.getMaterial(i9, i10, i11) == Material.water) {
                            z10000 = true;
                            break block0;
                        }
                        ++i11;
                    }
                    ++i10;
                }
                ++i9;
            }
            if (z10000) {
                world.setTileMetadata(xCoord, yCoord, zCoord, 7);
                return;
            }
            byte b13 = world.getTileMetadata(xCoord, yCoord, zCoord);
            if (b13 > 0) {
                world.setTileMetadata(xCoord, yCoord, zCoord, b13 - 1);
                return;
            }
            i8 = zCoord;
            i7 = yCoord;
            i6 = xCoord;
            world12 = world;
            i10 = xCoord;
            block3: while (true) {
                if (i10 > i6) {
                    z10000 = false;
                    break;
                }
                i11 = i8;
                while (i11 <= i8) {
                    if (world12.getTile(i10, i7 + 1, i11) == Tile.crops.id) {
                        z10000 = true;
                        break block3;
                    }
                    ++i11;
                }
                ++i10;
            }
            if (!z10000) {
                world.setTile(xCoord, yCoord, zCoord, Tile.dirt.id);
            }
        }
    }

    @Override
    public final void onEntityWalking(Level world, int i2, int i3, int i4) {
        if (!world.onlineLevel && world.mobGriefing && world.random.nextInt(4) == 0) {
            world.setTile(i2, i3, i4, Tile.dirt.id);
        }
    }

    @Override
    public final void neighborChanged(Level world, int xCoord, int yCoord, int zCoord, int blockID) {
        super.neighborChanged(world, xCoord, yCoord, zCoord, blockID);
        if (!world.onlineLevel && world.getMaterial(xCoord, yCoord + 1, zCoord).isSolid()) {
            world.setTile(xCoord, yCoord, zCoord, Tile.dirt.id);
        }
    }

    @Override
    public final int idDropped(int i1, Random random) {
        return Tile.dirt.idDropped(0, random);
    }
}

