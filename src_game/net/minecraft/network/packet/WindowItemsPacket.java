/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;
import net.minecraft.network.PacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.world.item.ItemStack;

public class WindowItemsPacket
extends Packet {
    public int windowId;
    public ItemStack[] itemStack;

    public WindowItemsPacket() {
    }

    public WindowItemsPacket(int windowId, List<ItemStack> stacks) {
        this.windowId = windowId;
        this.itemStack = new ItemStack[stacks.size()];
        int i2 = 0;
        while (i2 < this.itemStack.length) {
            ItemStack var4 = stacks.get(i2);
            this.itemStack[i2] = var4 == null ? null : var4.copy();
            ++i2;
        }
    }

    @Override
    public void read(DataInputStream var1) throws IOException {
        this.windowId = var1.readByte();
        int var2 = var1.readShort();
        this.itemStack = new ItemStack[var2];
        int var3 = 0;
        while (var3 < var2) {
            short var4 = var1.readShort();
            if (var4 >= 0) {
                byte var5 = var1.readByte();
                short var6 = var1.readShort();
                this.itemStack[var3] = new ItemStack(var4, (int)var5, (int)var6);
            }
            ++var3;
        }
    }

    @Override
    public void write(DataOutputStream var1) throws IOException {
        var1.writeByte(this.windowId);
        var1.writeShort(this.itemStack.length);
        int var2 = 0;
        while (var2 < this.itemStack.length) {
            if (this.itemStack[var2] == null) {
                var1.writeShort(-1);
            } else {
                var1.writeShort((short)this.itemStack[var2].itemID);
                var1.writeByte((byte)this.itemStack[var2].stackSize);
                var1.writeShort((short)this.itemStack[var2].itemDamage);
            }
            ++var2;
        }
    }

    @Override
    public void handle(PacketListener var1) {
        var1.handleWindowItems(this);
    }

    @Override
    public int size() {
        return 3 + this.itemStack.length * 5;
    }
}

