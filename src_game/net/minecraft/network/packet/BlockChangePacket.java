/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.network.PacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.world.level.Level;

public class BlockChangePacket
extends Packet {
    public int xPosition;
    public int yPosition;
    public int zPosition;
    public int type;
    public int metadata;

    public BlockChangePacket() {
    }

    public BlockChangePacket(int x2, int y2, int z2, Level world) {
        this.xPosition = x2;
        this.yPosition = y2;
        this.zPosition = z2;
        this.type = world.getTile(x2, y2, z2);
        this.metadata = world.getTileMetadata(x2, y2, z2);
    }

    @Override
    public void read(DataInputStream dis) throws IOException {
        this.xPosition = dis.readInt();
        this.yPosition = dis.readInt();
        this.zPosition = dis.readInt();
        this.type = dis.readInt();
        this.metadata = dis.readInt();
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeInt(this.xPosition);
        dos.writeInt(this.yPosition);
        dos.writeInt(this.zPosition);
        dos.writeInt(this.type);
        dos.writeInt(this.metadata);
    }

    @Override
    public void handle(PacketListener netHandler) {
        netHandler.handleBlockChange(this);
    }

    @Override
    public int size() {
        return 20;
    }
}

