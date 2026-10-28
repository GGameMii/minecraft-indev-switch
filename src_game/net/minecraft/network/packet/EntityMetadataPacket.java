/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;
import net.minecraft.network.PacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.world.level.data.DataWatcher;
import net.minecraft.world.level.data.WatchableObject;

public class EntityMetadataPacket
extends Packet {
    public int entityId;
    private List<WatchableObject> data;

    public EntityMetadataPacket() {
    }

    public EntityMetadataPacket(int entityId, DataWatcher dataWatcher) {
        this.entityId = entityId;
        this.data = dataWatcher.getChangedObjects();
    }

    @Override
    public void read(DataInputStream var1) throws IOException {
        this.entityId = var1.readInt();
        this.data = DataWatcher.readWatchableObjects(var1);
    }

    @Override
    public void write(DataOutputStream var1) throws IOException {
        var1.writeInt(this.entityId);
        DataWatcher.writeObjectsInListToStream(this.data, var1);
    }

    @Override
    public void handle(PacketListener var1) {
        var1.handleEntityMetadata(this);
    }

    @Override
    public int size() {
        return 5;
    }

    public List<WatchableObject> getData() {
        return this.data;
    }
}

