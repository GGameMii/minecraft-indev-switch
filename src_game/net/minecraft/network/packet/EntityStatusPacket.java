/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.network.PacketListener;
import net.minecraft.network.packet.Packet;

public class EntityStatusPacket
extends Packet {
    public int entityId;
    public byte entityStatus;
    public int x;
    public int z;

    public EntityStatusPacket() {
    }

    public EntityStatusPacket(int entityId, byte entityStatus, int x2, int z2) {
        this.entityId = entityId;
        this.entityStatus = entityStatus;
        this.x = x2;
        this.z = z2;
    }

    @Override
    public void read(DataInputStream var1) throws IOException {
        this.entityId = var1.readInt();
        this.entityStatus = var1.readByte();
        this.x = var1.readInt();
        this.z = var1.readInt();
    }

    @Override
    public void write(DataOutputStream var1) throws IOException {
        var1.writeInt(this.entityId);
        var1.writeByte(this.entityStatus);
        var1.writeInt(this.x);
        var1.writeInt(this.z);
    }

    @Override
    public void handle(PacketListener var1) {
        var1.handleEntityStatusUpdate(this);
    }

    @Override
    public int size() {
        return 13;
    }
}

