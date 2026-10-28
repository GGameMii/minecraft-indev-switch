/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.network.PacketListener;
import net.minecraft.network.packet.Packet;

public class BlockItemSwitchPacket
extends Packet {
    public int id;

    public BlockItemSwitchPacket() {
    }

    public BlockItemSwitchPacket(int var1) {
        this.id = var1;
    }

    @Override
    public void read(DataInputStream var1) throws IOException {
        this.id = var1.readShort();
    }

    @Override
    public void write(DataOutputStream var1) throws IOException {
        var1.writeShort(this.id);
    }

    @Override
    public void handle(PacketListener var1) {
        var1.handleBlockItemSwitch(this);
    }

    @Override
    public int size() {
        return 2;
    }
}

