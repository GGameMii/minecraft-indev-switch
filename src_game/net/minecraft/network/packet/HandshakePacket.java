/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.network.PacketListener;
import net.minecraft.network.packet.Packet;

public class HandshakePacket
extends Packet {
    public String secret;

    public HandshakePacket(String secret) {
        this.secret = secret;
    }

    public HandshakePacket() {
    }

    @Override
    public void read(DataInputStream dis) throws IOException {
        this.secret = HandshakePacket.readString(dis);
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        HandshakePacket.writeString(dos, this.secret);
    }

    @Override
    public void handle(PacketListener callback) {
        callback.handleHandshake(this);
    }

    @Override
    public int size() {
        return 64;
    }
}

