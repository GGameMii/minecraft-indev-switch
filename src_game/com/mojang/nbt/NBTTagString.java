/*
 * Decompiled with CFR 0.152.
 */
package com.mojang.nbt;

import com.mojang.nbt.NBTBase;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public final class NBTTagString
extends NBTBase {
    public String stringValue;

    public NBTTagString() {
    }

    public NBTTagString(String string) {
        this.stringValue = string;
    }

    @Override
    final void writeTagContents(DataOutput dataOutput) throws IOException {
        byte[] byArray = this.stringValue.getBytes("UTF-8");
        dataOutput.writeShort(byArray.length);
        dataOutput.write(byArray);
    }

    @Override
    final void readTagContents(DataInput dataInput) throws IOException {
        byte[] byArray = new byte[dataInput.readShort()];
        dataInput.readFully(byArray);
        this.stringValue = new String(byArray, "UTF-8");
    }

    @Override
    public final byte getType() {
        return 8;
    }

    public final String toString() {
        return "" + this.stringValue;
    }
}

