/*
 * Decompiled with CFR 0.152.
 */
package com.mojang.nbt;

import com.mojang.nbt.NBTBase;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public final class NBTTagFloat
extends NBTBase {
    public float floatValue;

    public NBTTagFloat() {
    }

    public NBTTagFloat(float f2) {
        this.floatValue = f2;
    }

    @Override
    final void writeTagContents(DataOutput dataOutput) throws IOException {
        dataOutput.writeFloat(this.floatValue);
    }

    @Override
    final void readTagContents(DataInput dataInput) throws IOException {
        this.floatValue = dataInput.readFloat();
    }

    @Override
    public final byte getType() {
        return 5;
    }

    public final String toString() {
        return "" + this.floatValue;
    }
}

