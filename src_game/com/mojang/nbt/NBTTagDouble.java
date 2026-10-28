/*
 * Decompiled with CFR 0.152.
 */
package com.mojang.nbt;

import com.mojang.nbt.NBTBase;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public final class NBTTagDouble
extends NBTBase {
    private double doubleValue;

    @Override
    final void writeTagContents(DataOutput dataOutput) throws IOException {
        dataOutput.writeDouble(this.doubleValue);
    }

    @Override
    final void readTagContents(DataInput dataInput) throws IOException {
        this.doubleValue = dataInput.readDouble();
    }

    @Override
    public final byte getType() {
        return 6;
    }

    public final String toString() {
        return "" + this.doubleValue;
    }
}

