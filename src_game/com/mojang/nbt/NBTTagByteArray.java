/*
 * Decompiled with CFR 0.152.
 */
package com.mojang.nbt;

import com.mojang.nbt.NBTBase;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public final class NBTTagByteArray
extends NBTBase {
    public byte[] byteArray;

    public NBTTagByteArray() {
    }

    public NBTTagByteArray(byte[] byArray) {
        this.byteArray = byArray;
    }

    @Override
    final void writeTagContents(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(this.byteArray.length);
        dataOutput.write(this.byteArray);
    }

    @Override
    final void readTagContents(DataInput dataInput) throws IOException {
        int n2 = dataInput.readInt();
        this.byteArray = new byte[n2];
        dataInput.readFully(this.byteArray);
    }

    @Override
    public final byte getType() {
        return 7;
    }

    public final String toString() {
        return "[" + this.byteArray.length + " bytes]";
    }
}

