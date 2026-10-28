/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.network.packet.EntityFlyingPacket;

public class EntityLookPacket
extends EntityFlyingPacket {
    public EntityLookPacket() {
        this.rotating = true;
    }

    public EntityLookPacket(int entityID, byte yaw, byte pitch) {
        super(entityID);
        this.yaw = yaw;
        this.pitch = pitch;
        this.rotating = true;
    }

    @Override
    public void read(DataInputStream in) throws IOException {
        super.read(in);
        this.yaw = in.readByte();
        this.pitch = in.readByte();
    }

    @Override
    public void write(DataOutputStream out) throws IOException {
        super.write(out);
        out.writeByte(this.yaw);
        out.writeByte(this.pitch);
    }

    @Override
    public int size() {
        return 6;
    }
}

