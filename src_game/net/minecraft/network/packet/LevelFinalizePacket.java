/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.network.PacketListener;
import net.minecraft.network.packet.Packet;

public class LevelFinalizePacket
extends Packet {
    public short x;
    public short y;
    public short z;
    public int worldTime;
    public int skylightSubtracted;
    public int skyBrightness;

    public LevelFinalizePacket(int xSize, int ySize, int zSize, int skylight, int time, int brightness, int playerid) {
        this.x = (short)xSize;
        this.y = (short)ySize;
        this.z = (short)zSize;
        this.skylightSubtracted = skylight;
        this.worldTime = time;
        this.skyBrightness = brightness;
    }

    public LevelFinalizePacket() {
    }

    @Override
    public void read(DataInputStream dis) throws IOException {
        this.x = dis.readShort();
        this.y = dis.readShort();
        this.z = dis.readShort();
        this.skylightSubtracted = dis.readInt();
        this.worldTime = dis.readInt();
        this.skyBrightness = dis.readInt();
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeShort(this.x);
        dos.writeShort(this.y);
        dos.writeShort(this.z);
        dos.writeInt(this.skylightSubtracted);
        dos.writeInt(this.worldTime);
        dos.writeInt(this.skyBrightness);
    }

    @Override
    public void handle(PacketListener callback) {
        callback.handleLevelFinalize(this);
    }

    @Override
    public int size() {
        return 18;
    }
}

