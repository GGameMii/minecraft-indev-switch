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
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.data.DataWatcher;
import net.minecraft.world.level.data.EntityList;
import net.minecraft.world.level.data.WatchableObject;
import util.Mth;

public class MobSpawnPacket
extends Packet {
    public int entityId;
    public byte type;
    public int xPosition;
    public int yPosition;
    public int zPosition;
    public byte yaw;
    public byte pitch;
    private DataWatcher metaData;
    private List<WatchableObject> receivedMetadata;

    public MobSpawnPacket() {
    }

    public MobSpawnPacket(LivingEntity e2) {
        this.entityId = e2.entityID;
        this.type = (byte)EntityList.getEntityID(e2);
        this.xPosition = Mth.floor(e2.x * 32.0f);
        this.yPosition = Mth.floor(e2.y * 32.0f);
        this.zPosition = Mth.floor(e2.z * 32.0f);
        this.yaw = (byte)(e2.xRot * 256.0f / 360.0f);
        this.pitch = (byte)(e2.yRot * 256.0f / 360.0f);
        this.metaData = e2.getDataWatcher();
    }

    @Override
    public void read(DataInputStream var1) throws IOException {
        this.entityId = var1.readInt();
        this.type = var1.readByte();
        this.xPosition = var1.readInt();
        this.yPosition = var1.readInt();
        this.zPosition = var1.readInt();
        this.yaw = var1.readByte();
        this.pitch = var1.readByte();
        this.receivedMetadata = DataWatcher.readWatchableObjects(var1);
    }

    @Override
    public void write(DataOutputStream var1) throws IOException {
        var1.writeInt(this.entityId);
        var1.writeByte(this.type);
        var1.writeInt(this.xPosition);
        var1.writeInt(this.yPosition);
        var1.writeInt(this.zPosition);
        var1.writeByte(this.yaw);
        var1.writeByte(this.pitch);
        this.metaData.writeWatchableObjects(var1);
    }

    @Override
    public void handle(PacketListener var1) {
        var1.handleMobSpawn(this);
    }

    @Override
    public int size() {
        return 20;
    }

    public List<WatchableObject> getMetadata() {
        return this.receivedMetadata;
    }
}

