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

public class PlayerInventoryPacket
extends Packet {
    public int entityID;
    public int slot;
    public int itemID;
    public int itemDamage;

    public PlayerInventoryPacket() {
    }

    public PlayerInventoryPacket(int entityID, int slot, ItemStack stack) {
        this.entityID = entityID;
        this.slot = slot;
        if (stack == null) {
            this.itemID = -1;
            this.itemDamage = 0;
        } else {
            this.itemID = stack.itemID;
            this.itemDamage = stack.itemDamage;
        }
    }

    @Override
    public void read(DataInputStream var1) throws IOException {
        this.entityID = var1.readInt();
        this.slot = var1.readShort();
        this.itemID = var1.readShort();
        this.itemDamage = var1.readShort();
    }

    @Override
    public void write(DataOutputStream var1) throws IOException {
        var1.writeInt(this.entityID);
        var1.writeShort(this.slot);
        var1.writeShort(this.itemID);
        var1.writeShort(this.itemDamage);
    }

    @Override
    public void handle(PacketListener var1) {
        var1.handlePlayerInventory(this);
    }

    @Override
    public int size() {
        return 8;
    }
}

