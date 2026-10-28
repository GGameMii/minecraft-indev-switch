/*
 * Decompiled with CFR 0.152.
 */
package com.mojang.nbt;

import com.mojang.nbt.NBTBase;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public final class NBTTagLong
extends NBTBase {
    public long longValue;

    public NBTTagLong() {
    }

    public NBTTagLong(long l2) {
        this.longValue = l2;
    }

    @Override
    final void writeTagContents(DataOutput dataOutput) throws IOException {
        dataOutput.writeLong(this.longValue);
    }

    @Override
    final void readTagContents(DataInput dataInput) throws IOException {
        this.longValue = dataInput.readLong();
    }

    @Override
    public final byte getType() {
        return 4;
    }

    public final String toString() {
        return "" + this.longValue;
    }
}

