/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.network.PacketListener;
import net.minecraft.network.packet.Packet;

public class TimePacket
extends Packet {
    public int newTime;
    public int skylightSubtracted;

    public TimePacket() {
    }

    public TimePacket(int time, int skylightSub) {
        this.newTime = time;
        this.skylightSubtracted = skylightSub;
    }

    @Override
    public void read(DataInputStream dis) throws IOException {
        this.newTime = dis.readInt();
        this.skylightSubtracted = dis.readInt();
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeInt(this.newTime);
        dos.writeInt(this.skylightSubtracted);
    }

    @Override
    public void handle(PacketListener callback) {
        callback.handleTime(this);
    }

    @Override
    public int size() {
        return 8;
    }
}

