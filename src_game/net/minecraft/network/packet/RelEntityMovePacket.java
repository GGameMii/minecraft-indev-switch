/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.network.packet.EntityFlyingPacket;

public class RelEntityMovePacket
extends EntityFlyingPacket {
    public RelEntityMovePacket() {
    }

    public RelEntityMovePacket(int entityID, byte xPosition, byte yPosition, byte zPosition) {
        super(entityID);
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.zPosition = zPosition;
    }

    @Override
    public void read(DataInputStream dataInputStream) throws IOException {
        super.read(dataInputStream);
        this.xPosition = dataInputStream.readByte();
        this.yPosition = dataInputStream.readByte();
        this.zPosition = dataInputStream.readByte();
    }

    @Override
    public void write(DataOutputStream dataOutputStream) throws IOException {
        super.write(dataOutputStream);
        dataOutputStream.writeByte(this.xPosition);
        dataOutputStream.writeByte(this.yPosition);
        dataOutputStream.writeByte(this.zPosition);
    }

    @Override
    public int size() {
        return 8;
    }
}

