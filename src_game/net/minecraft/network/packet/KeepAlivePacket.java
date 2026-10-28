/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.network.PacketListener;
import net.minecraft.network.packet.Packet;

public class KeepAlivePacket
extends Packet {
    @Override
    public void read(DataInputStream dis) throws IOException {
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
    }

    @Override
    public void handle(PacketListener callback) {
    }

    @Override
    public int size() {
        return 0;
    }
}

