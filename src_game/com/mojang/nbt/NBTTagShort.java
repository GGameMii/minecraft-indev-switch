/*
 * Decompiled with CFR 0.152.
 */
package com.mojang.nbt;

import com.mojang.nbt.NBTBase;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public final class NBTTagShort
extends NBTBase {
    public short shortValue;

    public NBTTagShort() {
    }

    public NBTTagShort(short s2) {
        this.shortValue = s2;
    }

    @Override
    final void writeTagContents(DataOutput dataOutput) throws IOException {
        dataOutput.writeShort(this.shortValue);
    }

    @Override
    final void readTagContents(DataInput dataInput) throws IOException {
        this.shortValue = dataInput.readShort();
    }

    @Override
    public final byte getType() {
        return 2;
    }

    public final String toString() {
        return "" + this.shortValue;
    }
}

