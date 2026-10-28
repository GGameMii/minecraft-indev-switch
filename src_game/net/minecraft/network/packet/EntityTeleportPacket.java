/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.network.PacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.world.entity.Entity;
import util.Mth;

public class EntityTeleportPacket
extends Packet {
    public int entityId;
    public int xPosition;
    public int yPosition;
    public int zPosition;
    public byte yaw;
    public byte pitch;

    public EntityTeleportPacket() {
    }

    public EntityTeleportPacket(Entity entity) {
        this.entityId = entity.entityID;
        this.xPosition = Mth.floor(entity.x * 32.0f);
        this.yPosition = Mth.floor(entity.y * 32.0f);
        this.zPosition = Mth.floor(entity.z * 32.0f);
        this.yaw = (byte)(entity.xRot * 256.0f / 360.0f);
        this.pitch = (byte)(entity.yRot * 256.0f / 360.0f);
    }

    public EntityTeleportPacket(int entityID, int x2, int y2, int z2, byte xrot, byte yrot) {
        this.entityId = entityID;
        this.xPosition = x2;
        this.yPosition = y2;
        this.zPosition = z2;
        this.yaw = xrot;
        this.pitch = yrot;
    }

    @Override
    public void read(DataInputStream in) throws IOException {
        this.entityId = in.readInt();
        this.xPosition = in.readInt();
        this.yPosition = in.readInt();
        this.zPosition = in.readInt();
        this.yaw = (byte)in.read();
        this.pitch = (byte)in.read();
    }

    @Override
    public void write(DataOutputStream out) throws IOException {
        out.writeInt(this.entityId);
        out.writeInt(this.xPosition);
        out.writeInt(this.yPosition);
        out.writeInt(this.zPosition);
        out.write(this.yaw);
        out.write(this.pitch);
    }

    @Override
    public void handle(PacketListener netHandler) {
        netHandler.handleEntityTeleport(this);
    }

    @Override
    public int size() {
        return 34;
    }
}

