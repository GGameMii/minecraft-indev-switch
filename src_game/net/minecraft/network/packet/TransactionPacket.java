/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.network.PacketListener;
import net.minecraft.network.packet.Packet;

public class TransactionPacket
extends Packet {
    public int windowId;
    public short field_20028_b;
    public boolean field_20030_c;

    public TransactionPacket() {
    }

    public TransactionPacket(int var1, short var2, boolean var3) {
        this.windowId = var1;
        this.field_20028_b = var2;
        this.field_20030_c = var3;
    }

    @Override
    public void handle(PacketListener var1) {
        var1.handleTransaction(this);
    }

    @Override
    public void read(DataInputStream var1) throws IOException {
        this.windowId = var1.readByte();
        this.field_20028_b = var1.readShort();
        this.field_20030_c = var1.readByte() != 0;
    }

    @Override
    public void write(DataOutputStream var1) throws IOException {
        var1.writeByte(this.windowId);
        var1.writeShort(this.field_20028_b);
        var1.writeByte(this.field_20030_c ? 1 : 0);
    }

    @Override
    public int size() {
        return 4;
    }
}

