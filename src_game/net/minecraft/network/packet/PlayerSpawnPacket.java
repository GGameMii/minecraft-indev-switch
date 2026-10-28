/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.network.PacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import util.Mth;

public class PlayerSpawnPacket
extends Packet {
    public int entityId;
    public String name;
    public int x;
    public int y;
    public int z;
    public byte rotation;
    public byte pitch;
    public int currentItem;

    public PlayerSpawnPacket() {
    }

    public PlayerSpawnPacket(Player player) {
        this.entityId = player.entityID;
        this.name = player.username;
        this.x = Mth.floor(player.x * 32.0f);
        float resultY = player.y - 1.62f;
        this.y = Mth.floor(resultY * 32.0f);
        this.z = Mth.floor(player.z * 32.0f);
        this.rotation = (byte)(player.xRot * 256.0f / 360.0f);
        this.pitch = (byte)(player.yRot * 256.0f / 360.0f);
        ItemStack itemStack2 = player.inventory.getCurrentItem();
        this.currentItem = itemStack2 == null ? 0 : itemStack2.itemID;
    }

    @Override
    public void read(DataInputStream dis) throws IOException {
        this.entityId = dis.readInt();
        this.name = dis.readUTF();
        this.x = dis.readInt();
        this.y = dis.readInt();
        this.z = dis.readInt();
        this.rotation = dis.readByte();
        this.pitch = dis.readByte();
        this.currentItem = dis.readShort();
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeInt(this.entityId);
        dos.writeUTF(this.name);
        dos.writeInt(this.x);
        dos.writeInt(this.y);
        dos.writeInt(this.z);
        dos.writeByte(this.rotation);
        dos.writeByte(this.pitch);
        dos.writeShort(this.currentItem);
    }

    @Override
    public void handle(PacketListener netHandler) {
        netHandler.handlePlayerSpawn(this);
    }

    @Override
    public int size() {
        return 28;
    }
}

