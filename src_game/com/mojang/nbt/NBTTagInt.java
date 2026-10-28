/*
 * Decompiled with CFR 0.152.
 */
package com.mojang.nbt;

import com.mojang.nbt.NBTBase;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public final class NBTTagInt
extends NBTBase {
    public int intValue;

    public NBTTagInt() {
    }

    public NBTTagInt(int n2) {
        this.intValue = n2;
    }

    @Override
    final void writeTagContents(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(this.intValue);
    }

    @Override
    final void readTagContents(DataInput dataInput) throws IOException {
        this.intValue = dataInput.readInt();
    }

    @Override
    public final byte getType() {
        return 3;
    }

    public final String toString() {
        return "" + this.intValue;
    }
}

