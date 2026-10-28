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

public class SetCreativeSlotPacket
extends Packet {
    public int slot;
    public ItemStack itemStack;

    public SetCreativeSlotPacket() {
    }

    public SetCreativeSlotPacket(int slot, ItemStack item) {
        this.slot = slot;
        this.itemStack = item;
    }

    @Override
    public void read(DataInputStream in) throws IOException {
        this.slot = in.readShort();
        this.itemStack = this.readItemStack(in);
    }

    @Override
    public void write(DataOutputStream out) throws IOException {
        out.writeShort(this.slot);
        this.writeItemStack(this.itemStack, out);
    }

    @Override
    public void handle(PacketListener callback) {
        callback.handleCreativeSetSlot(this);
    }

    @Override
    public int size() {
        return 8;
    }
}

