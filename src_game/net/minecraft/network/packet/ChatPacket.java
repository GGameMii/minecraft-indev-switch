/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.network.PacketListener;
import net.minecraft.network.packet.Packet;

public class ChatPacket
extends Packet {
    public String message;

    public ChatPacket() {
    }

    public ChatPacket(String message) {
        this.message = message;
    }

    @Override
    public void read(DataInputStream dataInputStream) throws IOException {
        this.message = dataInputStream.readUTF();
    }

    @Override
    public void write(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeUTF(this.message);
    }

    @Override
    public void handle(PacketListener netHandler) {
        netHandler.handleChat(this);
    }

    @Override
    public int size() {
        return this.message.length();
    }
}

