/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.network.PacketListener;
import net.minecraft.network.packet.Packet;

public class UpdateProgressbarPacket
extends Packet {
    public int windowId;
    public int progressBar;
    public int progressBarValue;

    public UpdateProgressbarPacket() {
    }

    public UpdateProgressbarPacket(int windowId, int progressBar, int progressBarValue) {
        this.windowId = windowId;
        this.progressBar = progressBar;
        this.progressBarValue = progressBarValue;
    }

    @Override
    public void handle(PacketListener var1) {
        var1.handleFurnaceProgress(this);
    }

    @Override
    public void read(DataInputStream var1) throws IOException {
        this.windowId = var1.readByte();
        this.progressBar = var1.readShort();
        this.progressBarValue = var1.readShort();
    }

    @Override
    public void write(DataOutputStream var1) throws IOException {
        var1.writeByte(this.windowId);
        var1.writeShort(this.progressBar);
        var1.writeShort(this.progressBarValue);
    }

    @Override
    public int size() {
        return 5;
    }
}

