/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.level.tile;

import java.util.Random;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.tile.LiquidTile;
import net.minecraft.world.level.tile.Tile;
import net.minecraft.world.phys.AABB;

public final class FlowingTile
extends LiquidTile {
    private int stillId1;
    private int movingId1;
    private Random rand = new Random();
    private int[] liquidIntArray;

    protected FlowingTile(int i1, Material material2) {
        super(i1, material2);
        int[] nArray = new int[4];
        nArray[1] = 1;
        nArray[2] = 2;
        nArray[3] = 3;
        this.liquidIntArray = nArray;
        this.tex = 14;
        if (material2 == Material.lava) {
            this.tex = 30;
        }
        Tile.isBlockFluid[i1] = true;
        this.movingId1 = i1;
        this.stillId1 = i1 + 1;
        this.setShape(0.01f, -0.09f, 0.01f, 1.01f, 0.90999997f, 1.01f);
        this.setTicking(true);
    }

    @Override
    public final void onBlockAdded(Level world, int xCoord, int yCoord, int zCoord) {
        world.scheduleBlockUpdate(xCoord, yCoord, zCoord, this.movingId1);
    }

    @Override
    public final void tick(Level world, int xCoord, int yCoord, int zCoord, Random random) {
        this.update(world, xCoord, yCoord, zCoord, 0);
    }

    @Override
    public final boolean update(Level world, int xCoord, int yCoord, int zCoord, int i5) {
        int i9;
        int i8;
        boolean z10 = false;
        boolean z6 = this.canFlow(world, xCoord - 1, yCoord, zCoord) || this.canFlow(world, xCoord + 1, yCoord, zCoord) || this.canFlow(world, xCoord, yCoord, zCoord - 1) || this.canFlow(world, xCoord, yCoord, zCoord + 1);
        if (z6 && world.getMaterial(xCoord, yCoord - 1, zCoord) == this.material && world.floodFill(xCoord, yCoord - 1, zCoord, this.movingId1, this.stillId1) == 1) {
            int i7 = world.fluidFlowCheck(xCoord, yCoord, zCoord, this.movingId1, this.stillId1);
            if (i7 != -9999) {
                if (i7 < 0) {
                    return false;
                }
                int i82 = i7 % 1024;
                int i92 = (i7 >>= 10) % 1024;
                xCoord = (i7 >>= 10) % 1024;
                world.setTile(i82, xCoord, i92, 0);
                return false;
            }
            return false;
        }
        z10 = this.liquidSpread2(world, xCoord, yCoord, zCoord, xCoord, yCoord - 1, zCoord);
        int i7 = 0;
        while (i7 < 4) {
            i8 = this.rand.nextInt(4 - i7) + i7;
            i9 = this.liquidIntArray[i7];
            this.liquidIntArray[i7] = this.liquidIntArray[i8];
            this.liquidIntArray[i8] = i9;
            if (this.liquidIntArray[i7] == 0 && !z10) {
                z10 = this.liquidSpread2(world, xCoord, yCoord, zCoord, xCoord - 1, yCoord, zCoord);
            }
            if (this.liquidIntArray[i7] == 1 && !z10) {
                z10 = this.liquidSpread2(world, xCoord, yCoord, zCoord, xCoord + 1, yCoord, zCoord);
            }
            if (this.liquidIntArray[i7] == 2 && !z10) {
                z10 = this.liquidSpread2(world, xCoord, yCoord, zCoord, xCoord, yCoord, zCoord - 1);
            }
            if (this.liquidIntArray[i7] == 3 && !z10) {
                z10 = this.liquidSpread2(world, xCoord, yCoord, zCoord, xCoord, yCoord, zCoord + 1);
            }
            ++i7;
        }
        if (!z10 && z6) {
            if (this.rand.nextInt(3) == 0) {
                if (this.rand.nextInt(3) == 0) {
                    z10 = false;
                    i7 = 0;
                    while (i7 < 4) {
                        i8 = this.rand.nextInt(4 - i7) + i7;
                        i9 = this.liquidIntArray[i7];
                        this.liquidIntArray[i7] = this.liquidIntArray[i8];
                        this.liquidIntArray[i8] = i9;
                        if (this.liquidIntArray[i7] == 0 && !z10) {
                            z10 = this.liquidSpread(world, xCoord, yCoord, zCoord, xCoord - 1, yCoord, zCoord);
                        }
                        if (this.liquidIntArray[i7] == 1 && !z10) {
                            z10 = this.liquidSpread(world, xCoord, yCoord, zCoord, xCoord + 1, yCoord, zCoord);
                        }
                        if (this.liquidIntArray[i7] == 2 && !z10) {
                            z10 = this.liquidSpread(world, xCoord, yCoord, zCoord, xCoord, yCoord, zCoord - 1);
                        }
                        if (this.liquidIntArray[i7] == 3 && !z10) {
                            z10 = this.liquidSpread(world, xCoord, yCoord, zCoord, xCoord, yCoord, zCoord + 1);
                        }
                        ++i7;
                    }
                } else if (this.material == Material.lava) {
                    world.setTile(xCoord, yCoord, zCoord, Tile.stone.id);
                } else {
                    world.setTile(xCoord, yCoord, zCoord, 0);
                }
            }
            return false;
        }
        if (this.material == Material.water) {
            z10 = z10 | FlowingTile.extinguishFireLava(world, xCoord - 1, yCoord, zCoord) | FlowingTile.extinguishFireLava(world, xCoord + 1, yCoord, zCoord) | FlowingTile.extinguishFireLava(world, xCoord, yCoord, zCoord - 1) | FlowingTile.extinguishFireLava(world, xCoord, yCoord, zCoord + 1);
        }
        if (this.material == Material.lava) {
            z10 = z10 | FlowingTile.flow(world, xCoord - 1, yCoord, zCoord) | FlowingTile.flow(world, xCoord + 1, yCoord, zCoord) | FlowingTile.flow(world, xCoord, yCoord, zCoord - 1) | FlowingTile.flow(world, xCoord, yCoord, zCoord + 1);
        }
        if (!z10) {
            world.setTileNoUpdate(xCoord, yCoord, zCoord, this.stillId1);
        } else {
            world.scheduleBlockUpdate(xCoord, yCoord, zCoord, this.movingId1);
        }
        return z10;
    }

    private boolean liquidSpread(Level world, int i2, int i3, int i4, int i5, int i6, int i7) {
        if (this.canFlow(world, i5, i6, i7)) {
            world.setTile(i5, i6, i7, this.id);
            world.scheduleBlockUpdate(i5, i6, i7, this.id);
            return true;
        }
        return false;
    }

    private boolean liquidSpread2(Level world, int i2, int i3, int i4, int i5, int i6, int i7) {
        if (!this.canFlow(world, i5, i6, i7)) {
            return false;
        }
        if ((i2 = world.fluidFlowCheck(i2, i3, i4, this.movingId1, this.stillId1)) != -9999) {
            if (i2 < 0) {
                return false;
            }
            i3 = i2 % 1024;
            i4 = (i2 >>= 10) % 1024;
            i2 >>= 10;
            if (!((i2 %= 1024) <= i6 && this.canFlow(world, i5, i6 - 1, i7) || i2 > i6 || i3 == 0 || i3 == world.width - 1 || i4 == 0 || i4 == world.depth - 1)) {
                return false;
            }
            world.setTile(i3, i2, i4, 0);
        }
        world.setTile(i5, i6, i7, this.id);
        world.scheduleBlockUpdate(i5, i6, i7, this.id);
        return true;
    }

    @Override
    public final boolean shouldRenderFace(Level world, int xCoord, int yCoord, int zCoord, int i5) {
        int i6;
        return xCoord >= 0 && yCoord >= 0 && zCoord >= 0 && xCoord < world.width && zCoord < world.depth ? ((i6 = world.getTile(xCoord, yCoord, zCoord)) != this.movingId1 && i6 != this.stillId1 ? (i5 == 1 && (world.getTile(xCoord - 1, yCoord, zCoord) == 0 || world.getTile(xCoord + 1, yCoord, zCoord) == 0 || world.getTile(xCoord, yCoord, zCoord - 1) == 0 || world.getTile(xCoord, yCoord, zCoord + 1) == 0) ? true : super.shouldRenderFace(world, xCoord, yCoord, zCoord, i5)) : false) : false;
    }

    @Override
    public final boolean mayPick() {
        return false;
    }

    @Override
    public final AABB getAABB(int xCoord, int yCoord, int zCoord) {
        return null;
    }

    @Override
    public final boolean isSolid() {
        return false;
    }

    @Override
    public final void neighborChanged(Level world, int xCoord, int yCoord, int zCoord, int blockID) {
    }

    @Override
    public final int tickRate() {
        return this.material == Material.lava ? 25 : 5;
    }

    @Override
    public final int quantityDropped(Random random) {
        return 0;
    }

    @Override
    public final int getRenderBlockPass() {
        return this.material == Material.water ? 1 : 0;
    }

    private static boolean extinguishFireLava(Level world, int i1, int i2, int i3) {
        if (world.getTile(i1, i2, i3) == Tile.fire.id) {
            world.setTile(i1, i2, i3, 0);
            return true;
        }
        if (world.getTile(i1, i2, i3) != Tile.lava.id && world.getTile(i1, i2, i3) != Tile.lavaCalm.id) {
            return false;
        }
        world.setTile(i1, i2, i3, Tile.stone.id);
        return true;
    }

    private static boolean flow(Level world0, int i1, int i2, int i3) {
        if (Tile.fire.getChanceOfNeighborsEncouragingFire(world0.getTile(i1, i2, i3))) {
            Tile.fire.fireSpread(world0, i1, i2, i3);
            return true;
        }
        return false;
    }
}

