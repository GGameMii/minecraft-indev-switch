/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.network.packet.PlayerFlyingPacket;

public class PlayerLookMovePacket
extends PlayerFlyingPacket {
    public PlayerLookMovePacket() {
        this.rotating = true;
        this.moving = true;
    }

    public PlayerLookMovePacket(float x2, float minY, float y2, float z2, float yaw, float pitch, boolean onGround) {
        this.xPosition = x2;
        this.yPosition = minY;
        this.stance = y2;
        this.zPosition = z2;
        this.yaw = yaw;
        this.pitch = pitch;
        this.onGround = onGround;
        this.rotating = true;
        this.moving = true;
    }

    @Override
    public void read(DataInputStream dataInputStream) throws IOException {
        this.xPosition = dataInputStream.readFloat();
        this.yPosition = dataInputStream.readFloat();
        this.stance = dataInputStream.readFloat();
        this.zPosition = dataInputStream.readFloat();
        this.yaw = dataInputStream.readFloat();
        this.pitch = dataInputStream.readFloat();
        super.read(dataInputStream);
    }

    @Override
    public void write(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeFloat(this.xPosition);
        dataOutputStream.writeFloat(this.yPosition);
        dataOutputStream.writeFloat(this.stance);
        dataOutputStream.writeFloat(this.zPosition);
        dataOutputStream.writeFloat(this.yaw);
        dataOutputStream.writeFloat(this.pitch);
        super.write(dataOutputStream);
    }

    @Override
    public int size() {
        return 41;
    }
}

