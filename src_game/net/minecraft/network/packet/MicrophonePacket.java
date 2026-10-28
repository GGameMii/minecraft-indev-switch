/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.network.PacketListener;
import net.minecraft.network.packet.Packet;

public class MicrophonePacket
extends Packet {
    public byte[] audioData;
    public int entityId;

    public MicrophonePacket(byte[] audio, int entityid) {
        this.audioData = audio;
        this.entityId = entityid;
    }

    public MicrophonePacket() {
    }

    @Override
    public void read(DataInputStream dis) throws IOException {
        this.entityId = dis.readInt();
        this.audioData = MicrophonePacket.readByteArray(dis);
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeInt(this.entityId);
        MicrophonePacket.writeByteArray(dos, this.audioData);
    }

    @Override
    public void handle(PacketListener callback) {
        callback.handleMicrophoneAudio(this);
    }

    @Override
    public int size() {
        return 1028;
    }
}

