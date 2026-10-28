/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.network.PacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.world.item.ItemStack;

public class PlaceTilePacket
extends Packet {
    public int xPosition;
    public int yPosition;
    public int zPosition;
    public int direction;
    public ItemStack itemStack;

    public PlaceTilePacket() {
    }

    public PlaceTilePacket(int x2, int y2, int z2, int direction, ItemStack item) {
        this.xPosition = x2;
        this.yPosition = y2;
        this.zPosition = z2;
        this.direction = direction;
        this.itemStack = item;
    }

    @Override
    public void read(DataInputStream dis) throws IOException {
        this.xPosition = dis.readInt();
        this.yPosition = dis.read();
        this.zPosition = dis.readInt();
        this.direction = dis.read();
        short var2 = dis.readShort();
        if (var2 >= 0) {
            byte var3 = dis.readByte();
            short var4 = dis.readShort();
            this.itemStack = new ItemStack(var2, (int)var3, (int)var4);
        } else {
            this.itemStack = null;
        }
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeInt(this.xPosition);
        dos.write(this.yPosition);
        dos.writeInt(this.zPosition);
        dos.write(this.direction);
        if (this.itemStack == null) {
            dos.writeShort(-1);
        } else {
            dos.writeShort(this.itemStack.itemID);
            dos.writeByte(this.itemStack.stackSize);
            dos.writeShort(this.itemStack.itemDamage);
        }
    }

    @Override
    public void handle(PacketListener netHandler) {
        netHandler.handlePlaceTile(this);
    }

    @Override
    public int size() {
        return 15;
    }
}

