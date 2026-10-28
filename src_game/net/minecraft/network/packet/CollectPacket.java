/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.network.PacketListener;
import net.minecraft.network.packet.Packet;

public class CollectPacket
extends Packet {
    public int collectedEntityId;
    public int collectorEntityId;

    public CollectPacket() {
    }

    public CollectPacket(int collectedEntityId, int collectorEntityId) {
        this.collectedEntityId = collectedEntityId;
        this.collectorEntityId = collectorEntityId;
    }

    @Override
    public void read(DataInputStream var1) throws IOException {
        this.collectedEntityId = var1.readInt();
        this.collectorEntityId = var1.readInt();
    }

    @Override
    public void write(DataOutputStream var1) throws IOException {
        var1.writeInt(this.collectedEntityId);
        var1.writeInt(this.collectorEntityId);
    }

    @Override
    public void handle(PacketListener var1) {
        var1.handleCollect(this);
    }

    @Override
    public int size() {
        return 8;
    }
}

