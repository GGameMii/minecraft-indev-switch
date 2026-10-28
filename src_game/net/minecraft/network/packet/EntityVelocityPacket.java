/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.network.PacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.world.entity.Entity;

public class EntityVelocityPacket
extends Packet {
    public int entityId;
    public int motionX;
    public int motionY;
    public int motionZ;

    public EntityVelocityPacket() {
    }

    public EntityVelocityPacket(Entity var1) {
        this(var1.entityID, var1.xd, var1.yd, var1.zd);
    }

    public EntityVelocityPacket(int entityId, float xd, float yd, float zd) {
        this.entityId = entityId;
        float max = 3.9f;
        if (xd < -max) {
            xd = -max;
        }
        if (yd < -max) {
            yd = -max;
        }
        if (zd < -max) {
            zd = -max;
        }
        if (xd > max) {
            xd = max;
        }
        if (yd > max) {
            yd = max;
        }
        if (zd > max) {
            zd = max;
        }
        this.motionX = (int)(xd * 8000.0f);
        this.motionY = (int)(yd * 8000.0f);
        this.motionZ = (int)(zd * 8000.0f);
    }

    @Override
    public void read(DataInputStream var1) throws IOException {
        this.entityId = var1.readInt();
        this.motionX = var1.readShort();
        this.motionY = var1.readShort();
        this.motionZ = var1.readShort();
    }

    @Override
    public void write(DataOutputStream var1) throws IOException {
        var1.writeInt(this.entityId);
        var1.writeShort(this.motionX);
        var1.writeShort(this.motionY);
        var1.writeShort(this.motionZ);
    }

    @Override
    public void handle(PacketListener var1) {
        var1.handleEntityVelocity(this);
    }

    @Override
    public int size() {
        return 10;
    }
}

