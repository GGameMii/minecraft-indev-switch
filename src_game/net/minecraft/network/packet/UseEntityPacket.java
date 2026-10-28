/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.network.PacketListener;
import net.minecraft.network.packet.Packet;

public class UseEntityPacket
extends Packet {
    public int playerEntityId;
    public int targetEntity;
    public int isLeftClick;

    public UseEntityPacket() {
    }

    public UseEntityPacket(int var1, int var2, int var3) {
        this.playerEntityId = var1;
        this.targetEntity = var2;
        this.isLeftClick = var3;
    }

    @Override
    public void read(DataInputStream var1) throws IOException {
        this.playerEntityId = var1.readInt();
        this.targetEntity = var1.readInt();
        this.isLeftClick = var1.readByte();
    }

    @Override
    public void write(DataOutputStream var1) throws IOException {
        var1.writeInt(this.playerEntityId);
        var1.writeInt(this.targetEntity);
        var1.writeByte(this.isLeftClick);
    }

    @Override
    public void handle(PacketListener var1) {
        var1.handleUseEntity(this);
    }

    @Override
    public int size() {
        return 9;
    }
}

