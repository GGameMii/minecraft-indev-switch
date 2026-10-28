/*
 * Decompiled with CFR 0.152.
 */
package com.mojang.nbt;

import com.mojang.nbt.NBTBase;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public final class NBTTagByte
extends NBTBase {
    public byte byteValue;

    public NBTTagByte() {
    }

    public NBTTagByte(byte by) {
        this.byteValue = by;
    }

    @Override
    final void writeTagContents(DataOutput dataOutput) throws IOException {
        dataOutput.writeByte(this.byteValue);
    }

    @Override
    final void readTagContents(DataInput dataInput) throws IOException {
        this.byteValue = dataInput.readByte();
    }

    @Override
    public final byte getType() {
        return 1;
    }

    public final String toString() {
        return "" + this.byteValue;
    }
}

