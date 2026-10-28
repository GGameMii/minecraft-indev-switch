/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.network.PacketListener;
import net.minecraft.network.packet.Packet;

public class DisconnectPlayerPacket
extends Packet {
    public String reason;

    public DisconnectPlayerPacket(String reason) {
        this.reason = reason;
    }

    public DisconnectPlayerPacket() {
    }

    @Override
    public void read(DataInputStream dis) throws IOException {
        this.reason = DisconnectPlayerPacket.readString(dis);
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        DisconnectPlayerPacket.writeString(dos, this.reason);
    }

    @Override
    public void handle(PacketListener callback) {
        callback.handleDisconnectPlayer(this);
    }

    @Override
    public int size() {
        return 64;
    }
}

