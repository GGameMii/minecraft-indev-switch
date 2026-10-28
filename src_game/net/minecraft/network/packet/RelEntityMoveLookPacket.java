/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.network.packet.EntityFlyingPacket;

public class RelEntityMoveLookPacket
extends EntityFlyingPacket {
    public RelEntityMoveLookPacket() {
        this.rotating = true;
    }

    public RelEntityMoveLookPacket(int entityID, byte xPosition, byte yPosition, byte zPosition, byte yaw, byte pitch) {
        super(entityID);
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.zPosition = zPosition;
        this.yaw = yaw;
        this.pitch = pitch;
        this.rotating = true;
    }

    @Override
    public void read(DataInputStream dataInputStream) throws IOException {
        super.read(dataInputStream);
        this.xPosition = dataInputStream.readByte();
        this.yPosition = dataInputStream.readByte();
        this.zPosition = dataInputStream.readByte();
        this.yaw = dataInputStream.readByte();
        this.pitch = dataInputStream.readByte();
    }

    @Override
    public void write(DataOutputStream dataOutputStream) throws IOException {
        super.write(dataOutputStream);
        dataOutputStream.writeByte(this.xPosition);
        dataOutputStream.writeByte(this.yPosition);
        dataOutputStream.writeByte(this.zPosition);
        dataOutputStream.writeByte(this.yaw);
        dataOutputStream.writeByte(this.pitch);
    }

    @Override
    public int size() {
        return 9;
    }
}

