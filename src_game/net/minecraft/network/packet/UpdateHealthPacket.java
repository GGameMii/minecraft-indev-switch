/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.network.PacketListener;
import net.minecraft.network.packet.Packet;

public class UpdateHealthPacket
extends Packet {
    public int healthMP;

    public UpdateHealthPacket() {
    }

    public UpdateHealthPacket(int health) {
        this.healthMP = health;
    }

    @Override
    public void read(DataInputStream var1) throws IOException {
        this.healthMP = var1.readShort();
    }

    @Override
    public void write(DataOutputStream var1) throws IOException {
        var1.writeShort(this.healthMP);
    }

    @Override
    public void handle(PacketListener var1) {
        var1.handleHealthUpdate(this);
    }

    @Override
    public int size() {
        return 4;
    }
}

