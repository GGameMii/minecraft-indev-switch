/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.network.PacketListener;
import net.minecraft.network.packet.Packet;

public class EntityFlyingPacket
extends Packet {
    public int entityId;
    public byte xPosition;
    public byte yPosition;
    public byte zPosition;
    public byte yaw;
    public byte pitch;
    public boolean rotating = false;

    public EntityFlyingPacket() {
    }

    public EntityFlyingPacket(int entityID) {
        this.entityId = entityID;
    }

    @Override
    public void read(DataInputStream dataInputStream) throws IOException {
        this.entityId = dataInputStream.readInt();
    }

    @Override
    public void write(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeInt(this.entityId);
    }

    @Override
    public void handle(PacketListener netHandler) {
        netHandler.handleEntity(this);
    }

    @Override
    public int size() {
        return 4;
    }
}

