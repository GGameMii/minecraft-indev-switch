/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.network.PacketListener;
import net.minecraft.network.packet.Packet;

public class RevivePacket
extends Packet {
    public boolean allowsReviving;
    public int entityId;

    public RevivePacket() {
    }

    public RevivePacket(boolean allowsReviving, int id) {
        this.allowsReviving = allowsReviving;
        this.entityId = id;
    }

    @Override
    public void read(DataInputStream dis) throws IOException {
        this.allowsReviving = dis.readBoolean();
        this.entityId = dis.readInt();
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeBoolean(this.allowsReviving);
        dos.writeInt(this.entityId);
    }

    @Override
    public void handle(PacketListener callback) {
        callback.handleReviving(this);
    }

    @Override
    public int size() {
        return 5;
    }
}

