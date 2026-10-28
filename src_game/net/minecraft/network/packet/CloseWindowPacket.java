/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.network.PacketListener;
import net.minecraft.network.packet.Packet;

public class CloseWindowPacket
extends Packet {
    public int windowId;

    public CloseWindowPacket() {
    }

    public CloseWindowPacket(int var1) {
        this.windowId = var1;
    }

    @Override
    public void handle(PacketListener var1) {
        var1.handleCloseWindow(this);
    }

    @Override
    public void read(DataInputStream var1) throws IOException {
        this.windowId = var1.readByte();
    }

    @Override
    public void write(DataOutputStream var1) throws IOException {
        var1.writeByte(this.windowId);
    }

    @Override
    public int size() {
        return 1;
    }
}

