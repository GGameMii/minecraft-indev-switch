/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.network.packet.PlayerFlyingPacket;

public class PlayerPositionPacket
extends PlayerFlyingPacket {
    public PlayerPositionPacket() {
        this.moving = true;
    }

    public PlayerPositionPacket(float x2, float minY, float y2, float z2, boolean onGround) {
        this.xPosition = x2;
        this.yPosition = minY;
        this.stance = y2;
        this.zPosition = z2;
        this.onGround = onGround;
        this.moving = true;
    }

    @Override
    public void read(DataInputStream dataInputStream) throws IOException {
        this.xPosition = dataInputStream.readFloat();
        this.yPosition = dataInputStream.readFloat();
        this.stance = dataInputStream.readFloat();
        this.zPosition = dataInputStream.readFloat();
        super.read(dataInputStream);
    }

    @Override
    public void write(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeFloat(this.xPosition);
        dataOutputStream.writeFloat(this.yPosition);
        dataOutputStream.writeFloat(this.stance);
        dataOutputStream.writeFloat(this.zPosition);
        super.write(dataOutputStream);
    }

    @Override
    public int size() {
        return 33;
    }
}

