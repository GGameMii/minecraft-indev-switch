/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.network.PacketListener;
import net.minecraft.network.packet.Packet;

public class SoundEffectPacket
extends Packet {
    public int x;
    public int y;
    public int z;
    public int type;

    public SoundEffectPacket() {
    }

    public SoundEffectPacket(int x2, int y2, int z2, int type) {
        this.x = x2;
        this.y = y2;
        this.z = z2;
        this.type = type;
    }

    @Override
    public void read(DataInputStream dis) throws IOException {
        this.x = dis.readInt();
        this.y = dis.readInt();
        this.z = dis.readInt();
        this.type = dis.readInt();
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeInt(this.x);
        dos.writeInt(this.y);
        dos.writeInt(this.z);
        dos.writeInt(this.type);
    }

    @Override
    public void handle(PacketListener callback) {
        callback.handleSoundEffect(this);
    }

    @Override
    public int size() {
        return 16;
    }
}

