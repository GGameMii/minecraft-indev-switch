/*
 * Decompiled with CFR 0.152.
 */
package com.mojang.nbt;

import com.mojang.nbt.NBTBase;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public final class NBTTagEnd
extends NBTBase {
    @Override
    final void readTagContents(DataInput dataInput) throws IOException {
    }

    @Override
    final void writeTagContents(DataOutput dataOutput) throws IOException {
    }

    @Override
    public final byte getType() {
        return 0;
    }

    public final String toString() {
        return "END";
    }
}

