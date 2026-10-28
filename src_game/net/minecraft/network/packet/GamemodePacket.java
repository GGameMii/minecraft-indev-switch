/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.network.PacketListener;
import net.minecraft.network.packet.Packet;

public class GamemodePacket
extends Packet {
    public byte gamemode;

    public GamemodePacket() {
    }

    public GamemodePacket(byte gm) {
        this.gamemode = gm;
    }

    @Override
    public void read(DataInputStream dis) throws IOException {
        this.gamemode = dis.readByte();
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeByte(this.gamemode);
    }

    @Override
    public void handle(PacketListener callback) {
        callback.handleGamemodePacket(this);
    }

    @Override
    public int size() {
        return 1;
    }
}

