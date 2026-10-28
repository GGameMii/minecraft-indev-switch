/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.network.PacketListener;
import net.minecraft.network.packet.Packet;

public class OpenWindowPacket
extends Packet {
    public int windowId;
    public int inventoryType;
    public String windowTitle;
    public int slotsCount;

    public OpenWindowPacket() {
    }

    public OpenWindowPacket(int windowId, int inventoryType, String windowTitle, int slotsCount) {
        this.windowId = windowId;
        this.inventoryType = inventoryType;
        this.windowTitle = windowTitle;
        this.slotsCount = slotsCount;
    }

    @Override
    public void handle(PacketListener var1) {
        var1.handleOpenWindow(this);
    }

    @Override
    public void read(DataInputStream var1) throws IOException {
        this.windowId = var1.readByte();
        this.inventoryType = var1.readByte();
        this.windowTitle = var1.readUTF();
        this.slotsCount = var1.readByte();
    }

    @Override
    public void write(DataOutputStream var1) throws IOException {
        var1.writeByte(this.windowId);
        var1.writeByte(this.inventoryType);
        var1.writeUTF(this.windowTitle);
        var1.writeByte(this.slotsCount);
    }

    @Override
    public int size() {
        return 3 + this.windowTitle.length();
    }
}

