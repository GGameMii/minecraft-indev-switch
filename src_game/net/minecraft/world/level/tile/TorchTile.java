/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.level.tile;

import java.util.Random;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.tile.Tile;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public final class TorchTile
extends Tile {
    protected TorchTile(int id, int tex) {
        super(id, tex, Material.circuits);
        this.setTicking(true);
    }

    @Override
    public final AABB getAABB(int x2, int y2, int z2) {
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
        return 2;
    }

    @Override
    public final boolean canPlaceBlockAt(Level world, int x2, int y2, int z2) {
        return world.isSolidTile(x2 - 1, y2, z2) ? true : (world.isSolidTile(x2 + 1, y2, z2) ? true : (world.isSolidTile(x2, y2, z2 - 1) ? true : (world.isSolidTile(x2, y2, z2 + 1) ? true : world.isSolidTile(x2, y2 - 1, z2))));
    }

    @Override
    public final void onBlockPlaced(Level level, int x2, int y2, int z2, int f2) {
        int data = level.getTileMetadata(x2, y2, z2);
        if (f2 == 1 && level.isSolidTile(x2, y2 - 1, z2)) {
            data = 5;
        }
        if (f2 == 2 && level.isSolidTile(x2, y2, z2 + 1)) {
            data = 4;
        }
        if (f2 == 3 && level.isSolidTile(x2, y2, z2 - 1)) {
            data = 3;
        }
        if (f2 == 4 && level.isSolidTile(x2 + 1, y2, z2)) {
            data = 2;
        }
        if (f2 == 5 && level.isSolidTile(x2 - 1, y2, z2)) {
            data = 1;
        }
        level.setTileMetadata(x2, y2, z2, data);
    }

    @Override
    public final void tick(Level level, int x2, int y2, int z2, Random random) {
        super.tick(level, x2, y2, z2, random);
        if (level.getTileMetadata(x2, y2, z2) == 0) {
            this.onBlockAdded(level, x2, y2, z2);
        }
    }

    @Override
    public final void onBlockAdded(Level level, int x2, int y2, int z2) {
        if (level.isSolidTile(x2 - 1, y2, z2)) {
            level.setTileMetadata(x2, y2, z2, 1);
        } else if (level.isSolidTile(x2 + 1, y2, z2)) {
            level.setTileMetadata(x2, y2, z2, 2);
        } else if (level.isSolidTile(x2, y2, z2 - 1)) {
            level.setTileMetadata(x2, y2, z2, 3);
        } else if (level.isSolidTile(x2, y2, z2 + 1)) {
            level.setTileMetadata(x2, y2, z2, 4);
        } else if (level.isSolidTile(x2, y2 - 1, z2)) {
            level.setTileMetadata(x2, y2, z2, 5);
        }
        this.dropTorchIfCantStay(level, x2, y2, z2);
    }

    @Override
    public final void neighborChanged(Level level, int x2, int y2, int z2, int blockID) {
        if (this.dropTorchIfCantStay(level, x2, y2, z2)) {
            byte data = level.getTileMetadata(x2, y2, z2);
            boolean fall = false;
            if (!level.isSolidTile(x2 - 1, y2, z2) && data == 1) {
                fall = true;
            }
            if (!level.isSolidTile(x2 + 1, y2, z2) && data == 2) {
                fall = true;
            }
            if (!level.isSolidTile(x2, y2, z2 - 1) && data == 3) {
                fall = true;
            }
            if (!level.isSolidTile(x2, y2, z2 + 1) && data == 4) {
                fall = true;
            }
            if (!level.isSolidTile(x2, y2 - 1, z2) && data == 5) {
                fall = true;
            }
            if (fall && level.tilePhysics && !level.onlineLevel) {
                this.dropBlockAsItem(level, x2, y2, z2, level.getTileMetadata(x2, y2, z2));
                level.setTile(x2, y2, z2, 0);
            }
        }
    }

    private boolean dropTorchIfCantStay(Level level, int x2, int y2, int z2) {
        if (!level.onlineLevel && !this.canPlaceBlockAt(level, x2, y2, z2)) {
            this.dropBlockAsItem(level, x2, y2, z2, level.getTileMetadata(x2, y2, z2));
            level.setTile(x2, y2, z2, 0);
            return false;
        }
        return true;
    }

    @Override
    public final HitResult collisionRayTrace(Level level, int x2, int y2, int z2, Vec3 vec3D, Vec3 vec3D2) {
        byte data = level.getTileMetadata(x2, y2, z2);
        if (data == 1) {
            this.setShape(0.0f, 0.2f, 0.35f, 0.3f, 0.8f, 0.65f);
        } else if (data == 2) {
            this.setShape(0.7f, 0.2f, 0.35f, 1.0f, 0.8f, 0.65f);
        } else if (data == 3) {
            this.setShape(0.35f, 0.2f, 0.0f, 0.65f, 0.8f, 0.3f);
        } else if (data == 4) {
            this.setShape(0.35f, 0.2f, 0.7f, 0.65f, 0.8f, 1.0f);
        } else {
            this.setShape(0.4f, 0.0f, 0.4f, 0.6f, 0.6f, 0.6f);
        }
        return super.collisionRayTrace(level, x2, y2, z2, vec3D, vec3D2);
    }

    @Override
    public final void randomTick(Level level, int x2, int y2, int z2, Random random) {
        byte data = level.getTileMetadata(x2, y2, z2);
        float xx = (float)x2 + 0.5f;
        float yy = (float)y2 + 0.7f;
        float zz = (float)z2 + 0.5f;
        if (data == 1) {
            level.spawnParticle("smoke", xx - 0.27f, yy + 0.22f, zz, 0.0f, 0.0f, 0.0f);
            level.spawnParticle("flame", xx - 0.27f, yy + 0.22f, zz, 0.0f, 0.0f, 0.0f);
        } else if (data == 2) {
            level.spawnParticle("smoke", xx + 0.27f, yy + 0.22f, zz, 0.0f, 0.0f, 0.0f);
            level.spawnParticle("flame", xx + 0.27f, yy + 0.22f, zz, 0.0f, 0.0f, 0.0f);
        } else if (data == 3) {
            level.spawnParticle("smoke", xx, yy + 0.22f, zz - 0.27f, 0.0f, 0.0f, 0.0f);
            level.spawnParticle("flame", xx, yy + 0.22f, zz - 0.27f, 0.0f, 0.0f, 0.0f);
        } else if (data == 4) {
            level.spawnParticle("smoke", xx, yy + 0.22f, zz + 0.27f, 0.0f, 0.0f, 0.0f);
            level.spawnParticle("flame", xx, yy + 0.22f, zz + 0.27f, 0.0f, 0.0f, 0.0f);
        } else {
            level.spawnParticle("smoke", xx, yy, zz, 0.0f, 0.0f, 0.0f);
            level.spawnParticle("flame", xx, yy, zz, 0.0f, 0.0f, 0.0f);
        }
    }
}

