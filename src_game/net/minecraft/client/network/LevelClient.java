/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.network;

import net.minecraft.client.network.Client;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class LevelClient
extends Level {
    public LevelClient(Client client) {
        this.onlineLevel = true;
    }

    @Override
    public final void tick() {
        int br;
        ++this.worldTime;
        if (this.worldTime == 24000) {
            this.worldTime = 0;
        }
        if (this.skylightSubtracted > (br = this.getSkyBrightness())) {
            this.updateChunkLight(this.skylightSubtracted - 1);
        }
        if (this.skylightSubtracted < br) {
            this.updateChunkLight(this.skylightSubtracted + 1);
        }
    }

    public void handleBlockChange(int x2, int y2, int z2, int id, int metadata) {
        this.setTile(x2, y2, z2, id);
        this.setTileMetadata(x2, y2, z2, metadata);
    }

    public void addEntity(int id, Entity e2) {
        if (e2 instanceof Player) {
            System.out.println("Adding player with id " + id);
        }
        this.spawnEntityInWorld(e2);
    }

    public void removeEntity(int id) {
        int i2 = 0;
        while (i2 < this.entityMap.entities.size()) {
            Entity e2 = this.entityMap.entities.get(i2);
            if (e2.entityID == id) {
                this.releaseEntitySkin(e2);
                e2.remove();
                return;
            }
            ++i2;
        }
    }

    public void removeLighting() {
        this.lightUpdates = null;
    }

    public void updateSkylight(int i2, int j2, int width, int depth) {
        this.lightUpdates.updateSkylight(i2, j2, width, depth);
    }
}

