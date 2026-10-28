/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.network.PacketListener;
import net.minecraft.network.packet.Packet;

public class SpawnPositionPacket
extends Packet {
    public int x;
    public int y;
    public int z;

    public SpawnPositionPacket() {
    }

    public SpawnPositionPacket(int xPosition, int yPosition, int zPosition) {
        this.x = xPosition;
        this.y = yPosition;
        this.z = zPosition;
    }

    @Override
    public void read(DataInputStream dataInputStream) throws IOException {
        this.x = dataInputStream.readInt();
        this.y = dataInputStream.readInt();
        this.z = dataInputStream.readInt();
    }

    @Override
    public void write(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeInt(this.x);
        dataOutputStream.writeInt(this.y);
        dataOutputStream.writeInt(this.z);
    }

    @Override
    public void handle(PacketListener netHandler) {
        netHandler.handleSpawnPosition(this);
    }

    @Override
    public int size() {
        return 12;
    }
}

