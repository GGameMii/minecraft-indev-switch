/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.network.PacketListener;
import net.minecraft.network.packet.Packet;

public class LevelSettingsPacket
extends Packet {
    public String authorName;
    public String name;
    public long createTime;
    public short cloudHeight;
    public short groundLevel;
    public short waterLevel;
    public int cloudColor;
    public int skyColor;
    public int fogColor;
    public byte defaultFluid;

    public LevelSettingsPacket(String an, String n2, long ct, int cc, int sc, int fc, int ch, int gl, int wl, int df) {
        this.authorName = an;
        this.name = n2;
        this.createTime = ct;
        this.cloudColor = cc;
        this.skyColor = sc;
        this.fogColor = fc;
        this.cloudHeight = (short)ch;
        this.groundLevel = (short)gl;
        this.waterLevel = (short)wl;
        this.defaultFluid = (byte)df;
    }

    public LevelSettingsPacket() {
    }

    @Override
    public void read(DataInputStream dis) throws IOException {
        this.authorName = LevelSettingsPacket.readString(dis);
        this.name = LevelSettingsPacket.readString(dis);
        this.createTime = dis.readLong();
        this.cloudColor = dis.readInt();
        this.skyColor = dis.readInt();
        this.fogColor = dis.readInt();
        this.cloudHeight = dis.readShort();
        this.groundLevel = dis.readShort();
        this.waterLevel = dis.readShort();
        this.defaultFluid = dis.readByte();
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        LevelSettingsPacket.writeString(dos, this.authorName);
        LevelSettingsPacket.writeString(dos, this.name);
        dos.writeLong(this.createTime);
        dos.writeInt(this.cloudColor);
        dos.writeInt(this.skyColor);
        dos.writeInt(this.fogColor);
        dos.writeShort(this.cloudHeight);
        dos.writeShort(this.groundLevel);
        dos.writeShort(this.waterLevel);
        dos.writeByte(this.defaultFluid);
    }

    @Override
    public void handle(PacketListener callback) {
        callback.handleLevelSettings(this);
    }

    @Override
    public int size() {
        return 19;
    }
}

