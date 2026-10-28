/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.network.PacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.world.entity.Painting;

public class PaintingPacket
extends Packet {
    public int entityId;
    public int xPosition;
    public int yPosition;
    public int zPosition;
    public int direction;
    public String title;

    public PaintingPacket() {
    }

    public PaintingPacket(Painting painting) {
        this.entityId = painting.entityID;
        this.xPosition = painting.xPosition;
        this.yPosition = painting.yPosition;
        this.zPosition = painting.zPosition;
        this.direction = painting.direction;
        this.title = painting.art.title;
    }

    @Override
    public void read(DataInputStream in) throws IOException {
        this.entityId = in.readInt();
        this.title = PaintingPacket.readString(in);
        this.xPosition = in.readInt();
        this.yPosition = in.readInt();
        this.zPosition = in.readInt();
        this.direction = in.readInt();
    }

    @Override
    public void write(DataOutputStream out) throws IOException {
        out.writeInt(this.entityId);
        PaintingPacket.writeString(out, this.title);
        out.writeInt(this.xPosition);
        out.writeInt(this.yPosition);
        out.writeInt(this.zPosition);
        out.writeInt(this.direction);
    }

    @Override
    public void handle(PacketListener netHandler1) {
        netHandler1.handlePainting(this);
    }

    @Override
    public int size() {
        return 24;
    }
}

