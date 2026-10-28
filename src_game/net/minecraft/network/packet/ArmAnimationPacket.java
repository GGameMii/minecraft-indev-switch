/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.network.PacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.world.entity.Entity;

public class ArmAnimationPacket
extends Packet {
    public int entityId;
    public byte animate;

    public ArmAnimationPacket() {
    }

    public ArmAnimationPacket(Entity var1, byte var2) {
        this.entityId = var1.entityID;
        this.animate = var2;
    }

    @Override
    public void read(DataInputStream var1) throws IOException {
        this.entityId = var1.readInt();
        this.animate = var1.readByte();
    }

    @Override
    public void write(DataOutputStream var1) throws IOException {
        var1.writeInt(this.entityId);
        var1.writeByte(this.animate);
    }

    @Override
    public void handle(PacketListener var1) {
        var1.handleArmAnimation(this);
    }

    @Override
    public int size() {
        return 5;
    }
}

