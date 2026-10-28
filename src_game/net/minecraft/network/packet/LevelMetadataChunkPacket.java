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

public class LevelMetadataChunkPacket
extends Packet
implements Debug {
    public short length;
    public byte[] metadata;
    public byte percentComplete;

    public LevelMetadataChunkPacket(int len, byte[] databuf, byte pComplete) {
        this.length = (short)len;
        this.metadata = databuf;
        this.percentComplete = pComplete;
    }

    public LevelMetadataChunkPacket() {
    }

    @Override
    public void read(DataInputStream dis) throws IOException {
        this.length = dis.readShort();
        this.metadata = LevelMetadataChunkPacket.readByteArray(dis);
        this.percentComplete = dis.readByte();
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeShort(this.length);
        LevelMetadataChunkPacket.writeByteArray(dos, this.metadata);
        dos.writeByte(this.percentComplete);
    }

    @Override
    public void handle(PacketListener callback) {
        callback.handleLevelMetadataChunk(this);
    }

    @Override
    public int size() {
        return 1027;
    }
}

