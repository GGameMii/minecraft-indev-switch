/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.level.tile;

import java.util.Random;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.tile.Tile;
import net.minecraft.world.phys.AABB;

public class LiquidTile
extends Tile {
    protected int stillId;
    protected int movingId;

    protected LiquidTile(int i1, Material material2) {
        super(i1, material2);
        this.tex = 14;
        if (material2 == Material.lava) {
            this.tex = 30;
        }
        Tile.isBlockFluid[i1] = true;
        this.movingId = i1;
        this.stillId = i1 + 1;
        this.setShape(0.01f, -0.09f, 0.01f, 1.01f, 0.90999997f, 1.01f);
        this.setTicking(true);
        this.setResistance(2.0f);
    }

    @Override
    public final int getTexture(int blockSide) {
        return this.material == Material.lava ? this.tex : (blockSide == 1 ? this.tex : (blockSide == 0 ? this.tex : this.tex + 32));
    }

    @Override
    public final boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public void onBlockAdded(Level world, int xCoord, int yCoord, int zCoord) {
        if (world.onlineLevel) {
            return;
        }
        world.scheduleBlockUpdate(xCoord, yCoord, zCoord, this.movingId);
    }

    @Override
    public void tick(Level world, int xCoord, int yCoord, int zCoord, Random random) {
        if (world.onlineLevel) {
            return;
        }
        this.update(world, xCoord, yCoord, zCoord, 0);
    }

    public boolean update(Level world, int xCoord, int yCoord, int zCoord, int i5) {
        if (world.onlineLevel) {
            return true;
        }
        boolean z7 = false;
        while (this.canFlow(world, xCoord, --yCoord, zCoord)) {
            boolean z6 = world.setTile(xCoord, yCoord, zCoord, this.movingId);
            if (z6) {
                z7 = true;
            }
            if (z6 && this.material != Material.lava) continue;
        }
        ++yCoord;
        if (this.material == Material.water || !z7) {
            z7 = z7 | this.flow(world, xCoord - 1, yCoord, zCoord) | this.flow(world, xCoord + 1, yCoord, zCoord) | this.flow(world, xCoord, yCoord, zCoord - 1) | this.flow(world, xCoord, yCoord, zCoord + 1);
        }
        if (this.material == Material.lava) {
            z7 = z7 | LiquidTile.extinguishFireLava(world, xCoord - 1, yCoord, zCoord) | LiquidTile.extinguishFireLava(world, xCoord + 1, yCoord, zCoord) | LiquidTile.extinguishFireLava(world, xCoord, yCoord, zCoord - 1) | LiquidTile.extinguishFireLava(world, xCoord, yCoord, zCoord + 1);
        }
        if (!z7) {
            world.setTileNoUpdate(xCoord, yCoord, zCoord, this.stillId);
        } else {
            world.scheduleBlockUpdate(xCoord, yCoord, zCoord, this.movingId);
        }
        return z7;
    }

    protected final boolean canFlow(Level world1, int i2, int i3, int i4) {
        if (!world1.getMaterial(i2, i3, i4).liquidSolidCheck()) {
            return false;
        }
        if (this.material == Material.water) {
            int i5 = i2 - 2;
            while (i5 <= i2 + 2) {
                int i6 = i3 - 2;
                while (i6 <= i3 + 2) {
                    int i7 = i4 - 2;
                    while (i7 <= i4 + 2) {
                        if (world1.getTile(i5, i6, i7) == Tile.sponge.id) {
                            return false;
                        }
                        ++i7;
                    }
                    ++i6;
                }
                ++i5;
            }
        }
        return true;
    }

    private static boolean extinguishFireLava(Level world, int i1, int i2, int i3) {
        if (Tile.fire.getChanceOfNeighborsEncouragingFire(world.getTile(i1, i2, i3))) {
            Tile.fire.fireSpread(world, i1, i2, i3);
            return true;
        }
        return false;
    }

    private boolean flow(Level world, int i2, int i3, int i4) {
        if (!this.canFlow(world, i2, i3, i4)) {
            return false;
        }
        if (world.setTile(i2, i3, i4, this.movingId)) {
            world.scheduleBlockUpdate(i2, i3, i4, this.movingId);
        }
        return false;
    }

    @Override
    public final float getBrightness(Level world, int i2, int i3, int i4) {
        return this.material == Material.lava ? 100.0f : world.getLightBrightness(i2, i3, i4);
    }

    @Override
    public boolean shouldRenderFace(Level world, int xCoord, int yCoord, int zCoord, int i5) {
        int i6;
        return xCoord >= 0 && yCoord >= 0 && zCoord >= 0 && xCoord < world.width && zCoord < world.depth ? ((i6 = world.getTile(xCoord, yCoord, zCoord)) != this.movingId && i6 != this.stillId ? (i5 == 1 && (world.getTile(xCoord - 1, yCoord, zCoord) == 0 || world.getTile(xCoord + 1, yCoord, zCoord) == 0 || world.getTile(xCoord, yCoord, zCoord - 1) == 0 || world.getTile(xCoord, yCoord, zCoord + 1) == 0) ? true : super.shouldRenderFace(world, xCoord, yCoord, zCoord, i5)) : false) : false;
    }

    @Override
    public boolean mayPick() {
        return false;
    }

    @Override
    public AABB getAABB(int xCoord, int yCoord, int zCoord) {
        return null;
    }

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public void neighborChanged(Level world, int xCoord, int yCoord, int zCoord, int blockID) {
        if (world.onlineLevel) {
            return;
        }
        if (blockID != 0) {
            Material blockID1 = Tile.tiles[blockID].material;
            if (this.material == Material.water && blockID1 == Material.lava || blockID1 == Material.water && this.material == Material.lava) {
                world.setTile(xCoord, yCoord, zCoord, Tile.stone.id);
            }
        }
        world.scheduleBlockUpdate(xCoord, yCoord, zCoord, this.id);
    }

    @Override
    public int tickRate() {
        return this.material == Material.lava ? 25 : 5;
    }

    @Override
    public int quantityDropped(Random random) {
        return 0;
    }

    @Override
    public int getRenderBlockPass() {
        return this.material == Material.water ? 1 : 0;
    }

    @Override
    public final void randomTick(Level world, int xCoord, int yCoord, int zCoord, Random random) {
        if (random.nextInt(128) == -1 && world.getMaterial(xCoord, yCoord + 1, zCoord).getIsSolid()) {
            if (this.material == Material.lava) {
                world.playSoundAtPlayer((float)xCoord + 0.5f, (float)yCoord + 0.5f, (float)zCoord + 0.5f, "liquid.lava", random.nextFloat() * 0.25f + 0.75f, random.nextFloat() * 0.5f + 0.3f);
            }
            if (this.material == Material.water) {
                world.playSoundAtPlayer((float)xCoord + 0.5f, (float)yCoord + 0.5f, (float)zCoord + 0.5f, "liquid.water", random.nextFloat() * 0.25f + 0.75f, random.nextFloat() + 0.5f);
            }
        }
        if (this.material == Material.lava && world.getMaterial(xCoord, yCoord + 1, zCoord) == Material.air && !world.isSolidTile(xCoord, yCoord + 1, zCoord) && random.nextInt(100) == 0) {
            float f6 = (float)xCoord + random.nextFloat();
            float f7 = (float)yCoord + this.yy1;
            float f8 = (float)zCoord + random.nextFloat();
            world.spawnParticle("lava", f6, f7, f8, 0.0f, 0.0f, 0.0f);
        }
        if (this.material == Material.water) {
            if (LiquidTile.liquidAirCheck(world, xCoord + 1, yCoord, zCoord)) {
                int i9 = 0;
                while (i9 < 4) {
                    world.spawnParticle("splash", (float)(xCoord + 1) + 0.125f, yCoord, (float)zCoord + random.nextFloat(), 0.0f, 0.0f, 0.0f);
                    ++i9;
                }
            }
            if (LiquidTile.liquidAirCheck(world, xCoord - 1, yCoord, zCoord)) {
                int i9 = 0;
                while (i9 < 4) {
                    world.spawnParticle("splash", (float)xCoord - 0.125f, yCoord, (float)zCoord + random.nextFloat(), 0.0f, 0.0f, 0.0f);
                    ++i9;
                }
            }
            if (LiquidTile.liquidAirCheck(world, xCoord, yCoord, zCoord + 1)) {
                int i9 = 0;
                while (i9 < 4) {
                    world.spawnParticle("splash", (float)xCoord + random.nextFloat(), yCoord, (float)(zCoord + 1) + 0.125f, 0.0f, 0.0f, 0.0f);
                    ++i9;
                }
            }
            if (LiquidTile.liquidAirCheck(world, xCoord, yCoord, zCoord - 1)) {
                int i9 = 0;
                while (i9 < 4) {
                    world.spawnParticle("splash", (float)xCoord + random.nextFloat(), yCoord, (float)zCoord - 0.125f, 0.0f, 0.0f, 0.0f);
                    ++i9;
                }
            }
        }
    }

    private static boolean liquidAirCheck(Level world, int i1, int i2, int i3) {
        Material material4 = world.getMaterial(i1, i2, i3);
        Material world1 = world.getMaterial(i1, i2 - 1, i3);
        return !material4.getIsSolid() && !material4.getIsLiquid() ? world1.getIsSolid() || world1.getIsLiquid() : false;
    }
}

