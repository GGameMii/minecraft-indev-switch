/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.network.PacketListener;
import net.minecraft.network.packet.Packet;

public class DestroyEntityPacket
extends Packet {
    public int entityId;

    public DestroyEntityPacket() {
    }

    public DestroyEntityPacket(int entityID) {
        this.entityId = entityID;
    }

    @Override
    public void read(DataInputStream in) throws IOException {
        this.entityId = in.readInt();
    }

    @Override
    public void write(DataOutputStream out) throws IOException {
        out.writeInt(this.entityId);
    }

    @Override
    public void handle(PacketListener netHandler) {
        netHandler.handleDestroyEntity(this);
    }

    @Override
    public int size() {
        return 4;
    }
}

