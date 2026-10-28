/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.network.PacketListener;
import net.minecraft.network.packet.Packet;

public class BlockDigPacket
extends Packet {
    public int xPosition;
    public int yPosition;
    public int zPosition;
    public int face;
    public int status;

    public BlockDigPacket() {
    }

    public BlockDigPacket(int status, int x2, int y2, int z2, int side) {
        this.status = status;
        this.xPosition = x2;
        this.yPosition = y2;
        this.zPosition = z2;
        this.face = side;
    }

    @Override
    public void read(DataInputStream dis) throws IOException {
        this.status = dis.read();
        this.xPosition = dis.readInt();
        this.yPosition = dis.read();
        this.zPosition = dis.readInt();
        this.face = dis.read();
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.write(this.status);
        dos.writeInt(this.xPosition);
        dos.write(this.yPosition);
        dos.writeInt(this.zPosition);
        dos.write(this.face);
    }

    @Override
    public void handle(PacketListener netHandler) {
        netHandler.handleBlockDig(this);
    }

    @Override
    public int size() {
        return 11;
    }
}

