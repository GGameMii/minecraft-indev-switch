/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.network.PacketListener;
import net.minecraft.network.packet.Packet;

public class PlayerFlyingPacket
extends Packet {
    public float xPosition;
    public float yPosition;
    public float zPosition;
    public float stance;
    public float yaw;
    public float pitch;
    public boolean onGround;
    public boolean moving;
    public boolean rotating;

    public PlayerFlyingPacket() {
    }

    public PlayerFlyingPacket(boolean onGround) {
        this.onGround = onGround;
    }

    @Override
    public void handle(PacketListener netHandler) {
        netHandler.handleFlying(this);
    }

    @Override
    public void read(DataInputStream dataInputStream) throws IOException {
        this.onGround = dataInputStream.read() != 0;
    }

    @Override
    public void write(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.write(this.onGround ? 1 : 0);
    }

    @Override
    public int size() {
        return 1;
    }
}

