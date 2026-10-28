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
import util.Mth;

public class VehicleSpawnPacket
extends Packet {
    public int entityId;
    public int xPosition;
    public int yPosition;
    public int zPosition;
    public int field_28047_e;
    public int field_28046_f;
    public int field_28045_g;
    public int type;
    public int field_28044_i;

    public VehicleSpawnPacket() {
    }

    public VehicleSpawnPacket(Entity entity, int type) {
        this(entity, type, 0);
    }

    public VehicleSpawnPacket(Entity entity, int type, int var3) {
        this.entityId = entity.entityID;
        this.xPosition = Mth.floor(entity.x * 32.0f);
        this.yPosition = Mth.floor(entity.y * 32.0f);
        this.zPosition = Mth.floor(entity.z * 32.0f);
        this.type = type;
        this.field_28044_i = var3;
        if (var3 > 0) {
            float var4 = entity.xd;
            float var6 = entity.yd;
            float var8 = entity.zd;
            float var10 = 3.9f;
            if (var4 < -var10) {
                var4 = -var10;
            }
            if (var6 < -var10) {
                var6 = -var10;
            }
            if (var8 < -var10) {
                var8 = -var10;
            }
            if (var4 > var10) {
                var4 = var10;
            }
            if (var6 > var10) {
                var6 = var10;
            }
            if (var8 > var10) {
                var8 = var10;
            }
            this.field_28047_e = (int)(var4 * 8000.0f);
            this.field_28046_f = (int)(var6 * 8000.0f);
            this.field_28045_g = (int)(var8 * 8000.0f);
        }
    }

    @Override
    public void read(DataInputStream var1) throws IOException {
        this.entityId = var1.readInt();
        this.type = var1.readByte();
        this.xPosition = var1.readInt();
        this.yPosition = var1.readInt();
        this.zPosition = var1.readInt();
        this.field_28044_i = var1.readInt();
        if (this.field_28044_i > 0) {
            this.field_28047_e = var1.readShort();
            this.field_28046_f = var1.readShort();
            this.field_28045_g = var1.readShort();
        }
    }

    @Override
    public void write(DataOutputStream var1) throws IOException {
        var1.writeInt(this.entityId);
        var1.writeByte(this.type);
        var1.writeInt(this.xPosition);
        var1.writeInt(this.yPosition);
        var1.writeInt(this.zPosition);
        var1.writeInt(this.field_28044_i);
        if (this.field_28044_i > 0) {
            var1.writeShort(this.field_28047_e);
            var1.writeShort(this.field_28046_f);
            var1.writeShort(this.field_28045_g);
        }
    }

    @Override
    public void handle(PacketListener var1) {
        var1.handleVehicleSpawn(this);
    }

    @Override
    public int size() {
        return 21 + this.field_28044_i > 0 ? 6 : 0;
    }
}

