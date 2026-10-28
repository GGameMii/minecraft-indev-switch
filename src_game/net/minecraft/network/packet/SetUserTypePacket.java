/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.network.PacketListener;
import net.minecraft.network.packet.Packet;

public class SetUserTypePacket
extends Packet {
    public byte userType;

    public SetUserTypePacket() {
    }

    public SetUserTypePacket(byte type) {
        this.userType = type;
    }

    @Override
    public void read(DataInputStream dis) throws IOException {
        this.userType = dis.readByte();
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.write(this.userType);
    }

    @Override
    public void handle(PacketListener callback) {
        callback.handleSetUserType(this);
    }

    @Override
    public int size() {
        return 1;
    }
}

