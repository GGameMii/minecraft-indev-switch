/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.network.PacketListener;
import net.minecraft.network.packet.Debug;
import net.minecraft.network.packet.Packet;

public class LevelDataChunkPacket
extends Packet
implements Debug {
    public short length;
    public byte[] data;
    public byte percentComplete;

    public LevelDataChunkPacket(int len, byte[] buf, byte pComplete) {
        this.length = (short)len;
        this.data = buf;
        this.percentComplete = pComplete;
    }

    public LevelDataChunkPacket() {
    }

    @Override
    public void read(DataInputStream dis) throws IOException {
        this.length = dis.readShort();
        this.data = LevelDataChunkPacket.readByteArray(dis);
        this.percentComplete = dis.readByte();
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeShort(this.length);
        LevelDataChunkPacket.writeByteArray(dos, this.data);
        dos.writeByte(this.percentComplete);
    }

    @Override
    public void handle(PacketListener callback) {
        callback.handleLevelDataChunk(this);
    }

    @Override
    public int size() {
        return 1027;
    }
}

