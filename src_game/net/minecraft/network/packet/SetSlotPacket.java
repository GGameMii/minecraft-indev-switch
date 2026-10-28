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

public class SetSlotPacket
extends Packet {
    public int windowId;
    public int itemSlot;
    public ItemStack myItemStack;

    public SetSlotPacket() {
    }

    public SetSlotPacket(int var1, int var2, ItemStack var3) {
        this.windowId = var1;
        this.itemSlot = var2;
        this.myItemStack = var3 == null ? var3 : var3.copy();
    }

    @Override
    public void handle(PacketListener var1) {
        var1.handleSetSlot(this);
    }

    @Override
    public void read(DataInputStream var1) throws IOException {
        this.windowId = var1.readByte();
        this.itemSlot = var1.readShort();
        short var2 = var1.readShort();
        if (var2 >= 0) {
            byte var3 = var1.readByte();
            short var4 = var1.readShort();
            this.myItemStack = new ItemStack(var2, (int)var3, (int)var4);
        } else {
            this.myItemStack = null;
        }
    }

    @Override
    public void write(DataOutputStream var1) throws IOException {
        var1.writeByte(this.windowId);
        var1.writeShort(this.itemSlot);
        if (this.myItemStack == null) {
            var1.writeShort(-1);
        } else {
            var1.writeShort(this.myItemStack.itemID);
            var1.writeByte(this.myItemStack.stackSize);
            var1.writeShort(this.myItemStack.itemDamage);
        }
    }

    @Override
    public int size() {
        return 8;
    }
}

