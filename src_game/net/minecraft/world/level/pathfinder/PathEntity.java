/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.level.pathfinder;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.pathfinder.PathPoint;
import net.minecraft.world.phys.Vec3;

public final class PathEntity {
    private final PathPoint[] points;
    private int pathIndex;

    public PathEntity(PathPoint[] points) {
        this.points = points;
    }

    public final void incrementPathIndex() {
        ++this.pathIndex;
    }

    public final boolean isFinished() {
        return this.pathIndex >= this.points.length;
    }

    public final Vec3 getPosition(Entity entity) {
        float f2 = (float)this.points[this.pathIndex].xCoord + (float)((int)(entity.bbWidth + 1.0f)) * 0.5f;
        float f3 = this.points[this.pathIndex].yCoord;
        float entity1 = (float)this.points[this.pathIndex].zCoord + (float)((int)(entity.bbWidth + 1.0f)) * 0.5f;
        return new Vec3(f2, f3, entity1);
    }
}

