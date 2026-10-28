/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.level;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;

public final class EntityMap {
    public int width;
    public int depth;
    public int height;
    public List<Entity> entities = new ArrayList<Entity>();
    private List<Entity> entitiesExcludingEntity = new ArrayList<Entity>();

    public EntityMap(int n2, int n3, int n4) {
        this.width = n2 / 8;
        this.depth = n3 / 8;
        this.height = n4 / 8;
        if (this.width == 0) {
            this.width = 1;
        }
        if (this.depth == 0) {
            this.depth = 1;
        }
        if (this.height == 0) {
            this.height = 1;
        }
    }

    public final void insert(Entity entity) {
        this.entities.add(entity);
        entity.lastTickX = entity.x;
        entity.lastTickY = entity.y;
        entity.lastTickZ = entity.z;
    }

    public final void remove(Entity entity) {
        this.entities.remove(entity);
    }

    public final List<Entity> getEntities(Entity entity, float f2, float f3, float f4, float f5, float f6, float f7) {
        this.entitiesExcludingEntity.clear();
        return this.getEntities(entity, f2, f3, f4, f5, f6, f7, this.entitiesExcludingEntity);
    }

    private List<Entity> getEntities(Entity entity, float f2, float f3, float f4, float f5, float f6, float f7, List<Entity> list) {
        for (int i2 = 0; i2 < this.entities.size(); ++i2) {
            Entity entity2 = this.entities.get(i2);
            if (entity2 == entity) continue;
            AABB aABB = entity2.bb;
            if (entity2 instanceof Player) {
                // empty if block
            }
            if (!(f5 > aABB.x0) || !(f2 < aABB.x1) || !(f6 > aABB.y0) || !(f3 < aABB.y1) || !(f7 > aABB.z0) || !(f4 < aABB.z1)) continue;
            list.add(entity2);
        }
        return list;
    }

    public final List<Entity> getEntitiesWithinAABB(Entity entity, AABB aABB) {
        this.entitiesExcludingEntity.clear();
        return aABB == null ? this.entitiesExcludingEntity : this.getEntities(entity, aABB.x0, aABB.y0, aABB.z0, aABB.x1, aABB.y1, aABB.z1, this.entitiesExcludingEntity);
    }

    public final void updateEntities() {
        for (int i2 = 0; i2 < this.entities.size(); ++i2) {
            Entity entity = this.entities.get(i2);
            entity.lastTickX = entity.x;
            entity.lastTickY = entity.y;
            entity.lastTickZ = entity.z;
            entity.tick();
            if (!entity.removed) continue;
            this.entities.remove(i2--);
            entity.level.entityRemoved(entity);
        }
    }
}

