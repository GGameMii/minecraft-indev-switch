/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.network.PacketListener;
import net.minecraft.network.packet.Packet;

public class EntityFirePacket
extends Packet {
    public int entityId;
    public int fire;

    public EntityFirePacket() {
    }

    public EntityFirePacket(int entityID, int fire) {
        this.entityId = entityID;
        this.fire = fire;
    }

    @Override
    public void read(DataInputStream in) throws IOException {
        this.entityId = in.readInt();
        this.fire = in.readInt();
    }

    @Override
    public void write(DataOutputStream out) throws IOException {
        out.writeInt(this.entityId);
        out.writeInt(this.fire);
    }

    @Override
    public void handle(PacketListener netHandler) {
        netHandler.handleEntityFire(this);
    }

    @Override
    public int size() {
        return 8;
    }
}

