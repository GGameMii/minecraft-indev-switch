/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import net.minecraft.network.PacketListener;
import net.minecraft.network.packet.Packet;

public class ExplosionPacket
extends Packet {
    public float explosionX;
    public float explosionY;
    public float explosionZ;
    public float explosionSize;
    public Set<Integer> destroyedBlockPositions;

    public ExplosionPacket() {
    }

    public ExplosionPacket(float posX, float posY, float posZ, float explosionSize, Set<Integer> destroyedBlocks) {
        this.explosionX = posX;
        this.explosionY = posY;
        this.explosionZ = posZ;
        this.explosionSize = explosionSize;
        this.destroyedBlockPositions = new HashSet<Integer>(destroyedBlocks);
    }

    @Override
    public void read(DataInputStream var1) throws IOException {
        this.explosionX = var1.readFloat();
        this.explosionY = var1.readFloat();
        this.explosionZ = var1.readFloat();
        this.explosionSize = var1.readFloat();
        int var2 = var1.readInt();
        this.destroyedBlockPositions = new HashSet<Integer>();
        int var3 = (int)this.explosionX;
        int var4 = (int)this.explosionY;
        int var5 = (int)this.explosionZ;
        int var6 = 0;
        while (var6 < var2) {
            int x2 = var1.readByte() + var3;
            int y2 = var1.readByte() + var4;
            int z2 = var1.readByte() + var5;
            int pos = x2 + (y2 << 10) + (z2 << 10 << 10);
            this.destroyedBlockPositions.add(pos);
            ++var6;
        }
    }

    @Override
    public void write(DataOutputStream var1) throws IOException {
        var1.writeFloat(this.explosionX);
        var1.writeFloat(this.explosionY);
        var1.writeFloat(this.explosionZ);
        var1.writeFloat(this.explosionSize);
        var1.writeInt(this.destroyedBlockPositions.size());
        int var2 = (int)this.explosionX;
        int var3 = (int)this.explosionY;
        int var4 = (int)this.explosionZ;
        for (int i39 : this.destroyedBlockPositions) {
            int var7 = (i39 & 0x3FF) - var2;
            int var8 = (i39 >> 10 & 0x3FF) - var3;
            int var9 = (i39 >> 20 & 0x3FF) - var4;
            var1.writeByte(var7);
            var1.writeByte(var8);
            var1.writeByte(var9);
        }
    }

    @Override
    public void handle(PacketListener var1) {
        var1.handleExplosion(this);
    }

    @Override
    public int size() {
        return 32 + this.destroyedBlockPositions.size() * 3;
    }
}

