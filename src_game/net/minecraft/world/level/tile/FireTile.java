/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.level.tile;

import java.util.Random;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.tile.Tile;
import net.minecraft.world.phys.AABB;

public final class FireTile
extends Tile {
    private int[] chanceToEncourageFire = new int[256];
    private int[] abilityToCatchFire = new int[256];

    protected FireTile(int n2, int n3) {
        super(51, 31, Material.fire);
        this.setBurnRate(Tile.planks.id, 5, 20);
        this.setBurnRate(Tile.wood.id, 5, 5);
        this.setBurnRate(Tile.leaves.id, 30, 60);
        this.setBurnRate(Tile.bookShelf.id, 30, 20);
        this.setBurnRate(Tile.tnt.id, 15, 100);
        for (n2 = 0; n2 < 16; ++n2) {
            this.setBurnRate(Tile.clothRed.id + n2, 30, 60);
        }
        this.setTicking(true);
    }

    private void setBurnRate(int n2, int n3, int n4) {
        this.chanceToEncourageFire[n2] = n3;
        this.abilityToCatchFire[n2] = n4;
    }

    @Override
    public final AABB getAABB(int n2, int n3, int n4) {
        return null;
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
    public final int getRenderType() {
        return 3;
    }

    @Override
    public final int quantityDropped(Random random) {
        return 0;
    }

    @Override
    public final int tickRate() {
        return 20;
    }

    @Override
    public final void tick(Level level, int n2, int n3, int n4, Random random) {
        if (level.onlineLevel) {
            return;
        }
        byte by = level.getTileMetadata(n2, n3, n4);
        if (by < 15) {
            level.setTileMetadata(n2, n3, n4, by + 1);
            level.scheduleBlockUpdate(n2, n3, n4, this.id);
        }
        if (!this.canNeighborCatchFire(level, n2, n3, n4)) {
            if (!level.isSolidTile(n2, n3 - 1, n4) || by > 3) {
                level.setTile(n2, n3, n4, 0);
            }
        } else if (!this.canBlockCatchFire(level, n2, n3 - 1, n4) && by == 15 && random.nextInt(4) == 0) {
            level.setTile(n2, n3, n4, 0);
        } else if (by % 5 == 0 && by > 5) {
            this.tryToCatchBlockOnFire(level, n2 + 1, n3, n4, 300, random);
            this.tryToCatchBlockOnFire(level, n2 - 1, n3, n4, 300, random);
            this.tryToCatchBlockOnFire(level, n2, n3 - 1, n4, 100, random);
            this.tryToCatchBlockOnFire(level, n2, n3 + 1, n4, 200, random);
            this.tryToCatchBlockOnFire(level, n2, n3, n4 - 1, 300, random);
            this.tryToCatchBlockOnFire(level, n2, n3, n4 + 1, 300, random);
            for (int i2 = n2 - 1; i2 <= n2 + 1; ++i2) {
                for (int i3 = n4 - 1; i3 <= n4 + 1; ++i3) {
                    for (int i4 = n3 - 1; i4 <= n3 + 4; ++i4) {
                        int n5;
                        int n6;
                        if (i2 == n2 && i4 == n3 && i3 == n4) continue;
                        int n7 = 100;
                        if (i4 > n3 + 1) {
                            n7 = 100 + (i4 - (n3 + 1)) * 100;
                        }
                        if (level.getTile(i2, i4, i3) != 0) {
                            n6 = 0;
                        } else {
                            n5 = this.getChanceToEncourageFire(level, i2 + 1, i4, i3, 0);
                            n5 = this.getChanceToEncourageFire(level, i2 - 1, i4, i3, n5);
                            n5 = this.getChanceToEncourageFire(level, i2, i4 - 1, i3, n5);
                            n5 = this.getChanceToEncourageFire(level, i2, i4 + 1, i3, n5);
                            n5 = this.getChanceToEncourageFire(level, i2, i4, i3 - 1, n5);
                            n6 = this.getChanceToEncourageFire(level, i2, i4, i3 + 1, n5);
                        }
                        n5 = n6;
                        if (n6 <= 0 || random.nextInt(n7) > n5) continue;
                        level.setTile(i2, i4, i3, this.id);
                    }
                }
            }
        }
    }

    private void tryToCatchBlockOnFire(Level level, int n2, int n3, int n4, int n5, Random random) {
        int n6 = this.abilityToCatchFire[level.getTile(n2, n3, n4)];
        if (random.nextInt(n5) < n6) {
            boolean bl;
            boolean bl2 = bl = level.getTile(n2, n3, n4) == Tile.tnt.id;
            if (random.nextInt(2) == 0) {
                level.setTile(n2, n3, n4, this.id);
            } else {
                level.setTile(n2, n3, n4, 0);
            }
            if (bl) {
                Tile.tnt.onBlockDestroyedByPlayer(level, n2, n3, n4, 0);
            }
        }
    }

    private boolean canNeighborCatchFire(Level level, int n2, int n3, int n4) {
        return this.canBlockCatchFire(level, n2 + 1, n3, n4) ? true : (this.canBlockCatchFire(level, n2 - 1, n3, n4) ? true : (this.canBlockCatchFire(level, n2, n3 - 1, n4) ? true : (this.canBlockCatchFire(level, n2, n3 + 1, n4) ? true : (this.canBlockCatchFire(level, n2, n3, n4 - 1) ? true : this.canBlockCatchFire(level, n2, n3, n4 + 1)))));
    }

    @Override
    public final boolean mayPick() {
        return false;
    }

    public final boolean canBlockCatchFire(Level level, int n2, int n3, int n4) {
        return this.chanceToEncourageFire[level.getTile(n2, n3, n4)] > 0;
    }

    private int getChanceToEncourageFire(Level level, int n2, int n3, int n4, int n5) {
        int n6 = this.chanceToEncourageFire[level.getTile(n2, n3, n4)];
        return n6 > n5 ? n6 : n5;
    }

    @Override
    public final boolean canPlaceBlockAt(Level level, int n2, int n3, int n4) {
        return level.isSolidTile(n2, n3 - 1, n4) || this.canNeighborCatchFire(level, n2, n3, n4);
    }

    @Override
    public final void neighborChanged(Level level, int n2, int n3, int n4, int n5) {
        if (!level.isSolidTile(n2, n3 - 1, n4) && !this.canNeighborCatchFire(level, n2, n3, n4)) {
            level.setTile(n2, n3, n4, 0);
        }
    }

    @Override
    public final void onBlockAdded(Level level, int n2, int n3, int n4) {
        if (!level.isSolidTile(n2, n3 - 1, n4) && !this.canNeighborCatchFire(level, n2, n3, n4)) {
            level.setTile(n2, n3, n4, 0);
        } else {
            level.scheduleBlockUpdate(n2, n3, n4, this.id);
        }
    }

    public final boolean getChanceOfNeighborsEncouragingFire(int n2) {
        return this.chanceToEncourageFire[n2] > 0;
    }

    public final void fireSpread(Level level, int n2, int n3, int n4) {
        boolean bl = false;
        bl = FireTile.fireCheck(level, n2, n3 + 1, n4);
        if (!bl) {
            bl = FireTile.fireCheck(level, n2 - 1, n3, n4);
        }
        if (!bl) {
            bl = FireTile.fireCheck(level, n2 + 1, n3, n4);
        }
        if (!bl) {
            bl = FireTile.fireCheck(level, n2, n3, n4 - 1);
        }
        if (!bl) {
            bl = FireTile.fireCheck(level, n2, n3, n4 + 1);
        }
        if (!bl) {
            bl = FireTile.fireCheck(level, n2, n3 - 1, n4);
        }
        if (!bl) {
            level.setTile(n2, n3, n4, Tile.fire.id);
        }
    }

    @Override
    public final void randomTick(Level level, int n2, int n3, int n4, Random random) {
        block12: {
            block11: {
                float f2;
                float f3;
                float f4;
                int n5;
                if (random.nextInt(24) == 0) {
                    level.playSoundAtPlayer((float)n2 + 0.5f, (float)n3 + 0.5f, (float)n4 + 0.5f, "fire.fire", 1.0f + random.nextFloat(), random.nextFloat() * 0.7f + 0.3f);
                }
                if (level.isSolidTile(n2, n3 - 1, n4) || Tile.fire.canBlockCatchFire(level, n2, n3 - 1, n4)) break block11;
                if (Tile.fire.canBlockCatchFire(level, n2 - 1, n3, n4)) {
                    for (n5 = 0; n5 < 2; ++n5) {
                        f4 = (float)n2 + random.nextFloat() * 0.1f;
                        f3 = (float)n3 + random.nextFloat();
                        f2 = (float)n4 + random.nextFloat();
                        level.spawnParticle("largesmoke", f4, f3, f2, 0.0f, 0.0f, 0.0f);
                    }
                }
                if (Tile.fire.canBlockCatchFire(level, n2 + 1, n3, n4)) {
                    for (n5 = 0; n5 < 2; ++n5) {
                        f4 = (float)(n2 + 1) - random.nextFloat() * 0.1f;
                        f3 = (float)n3 + random.nextFloat();
                        f2 = (float)n4 + random.nextFloat();
                        level.spawnParticle("largesmoke", f4, f3, f2, 0.0f, 0.0f, 0.0f);
                    }
                }
                if (Tile.fire.canBlockCatchFire(level, n2, n3, n4 - 1)) {
                    for (n5 = 0; n5 < 2; ++n5) {
                        f4 = (float)n2 + random.nextFloat();
                        f3 = (float)n3 + random.nextFloat();
                        f2 = (float)n4 + random.nextFloat() * 0.1f;
                        level.spawnParticle("largesmoke", f4, f3, f2, 0.0f, 0.0f, 0.0f);
                    }
                }
                if (Tile.fire.canBlockCatchFire(level, n2, n3, n4 + 1)) {
                    for (n5 = 0; n5 < 2; ++n5) {
                        f4 = (float)n2 + random.nextFloat();
                        f3 = (float)n3 + random.nextFloat();
                        f2 = (float)(n4 + 1) - random.nextFloat() * 0.1f;
                        level.spawnParticle("largesmoke", f4, f3, f2, 0.0f, 0.0f, 0.0f);
                    }
                }
                if (!Tile.fire.canBlockCatchFire(level, n2, n3 + 1, n4)) break block12;
                for (n5 = 0; n5 < 2; ++n5) {
                    f4 = (float)n2 + random.nextFloat();
                    f3 = (float)(n3 + 1) - random.nextFloat() * 0.1f;
                    f2 = (float)n4 + random.nextFloat();
                    level.spawnParticle("largesmoke", f4, f3, f2, 0.0f, 0.0f, 0.0f);
                }
                break block12;
            }
            for (int i2 = 0; i2 < 3; ++i2) {
                float f5 = (float)n2 + random.nextFloat();
                float f6 = (float)n3 + random.nextFloat() * 0.5f + 0.5f;
                float f7 = (float)n4 + random.nextFloat();
                level.spawnParticle("largesmoke", f5, f6, f7, 0.0f, 0.0f, 0.0f);
            }
        }
    }

    private static boolean fireCheck(Level level, int n2, int n3, int n4) {
        int n5 = level.getTile(n2, n3, n4);
        if (n5 == Tile.fire.id) {
            return true;
        }
        if (n5 == 0) {
            level.setTile(n2, n3, n4, Tile.fire.id);
            return true;
        }
        return false;
    }
}

