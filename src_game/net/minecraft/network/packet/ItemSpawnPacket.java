/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.network.PacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.world.entity.item.ItemEntity;
import util.Mth;

public class ItemSpawnPacket
extends Packet {
    public int entityId;
    public int xPosition;
    public int yPosition;
    public int zPosition;
    public byte rotation;
    public byte pitch;
    public byte roll;
    public int itemID;
    public int count;
    public int itemDamage;
    public int xRot;
    public int yRot;

    public ItemSpawnPacket() {
    }

    public ItemSpawnPacket(ItemEntity var1) {
        this.entityId = var1.entityID;
        this.itemID = var1.item.itemID;
        this.count = var1.item.stackSize;
        this.itemDamage = var1.item.itemDamage;
        this.xPosition = Mth.floor(var1.x * 32.0f);
        this.yPosition = Mth.floor(var1.y * 32.0f);
        this.zPosition = Mth.floor(var1.z * 32.0f);
        this.rotation = (byte)(var1.xd * 128.0f);
        this.pitch = (byte)(var1.yd * 128.0f);
        this.roll = (byte)(var1.zd * 128.0f);
        this.xRot = (byte)(var1.xRot * 128.0f);
        this.yRot = (byte)(var1.yRot * 128.0f);
    }

    @Override
    public void read(DataInputStream var1) throws IOException {
        this.entityId = var1.readInt();
        this.itemID = var1.readShort();
        this.count = var1.readByte();
        this.itemDamage = var1.readShort();
        this.xPosition = var1.readInt();
        this.yPosition = var1.readInt();
        this.zPosition = var1.readInt();
        this.rotation = var1.readByte();
        this.pitch = var1.readByte();
        this.roll = var1.readByte();
        this.xRot = var1.readInt();
        this.yRot = var1.readInt();
    }

    @Override
    public void write(DataOutputStream var1) throws IOException {
        var1.writeInt(this.entityId);
        var1.writeShort(this.itemID);
        var1.writeByte(this.count);
        var1.writeShort(this.itemDamage);
        var1.writeInt(this.xPosition);
        var1.writeInt(this.yPosition);
        var1.writeInt(this.zPosition);
        var1.writeByte(this.rotation);
        var1.writeByte(this.pitch);
        var1.writeByte(this.roll);
        var1.writeInt(this.xRot);
        var1.writeInt(this.yRot);
    }

    @Override
    public void handle(PacketListener var1) {
        var1.handleItemSpawn(this);
    }

    @Override
    public int size() {
        return 32;
    }
}

