/*
 * Decompiled with CFR 0.152.
 */
package com.a.a;

import com.a.a.j;
import java.io.DataInput;
import java.io.DataOutput;

public final class i
extends j {
    public byte[] a;

    public i() {
    }

    public i(byte[] byArray) {
        this.a = byArray;
    }

    final void a(DataOutput dataOutput) {
        dataOutput.writeInt(this.a.length);
        dataOutput.write(this.a);
    }

    final void a(DataInput dataInput) {
        int n2 = dataInput.readInt();
        this.a = new byte[n2];
        dataInput.readFully(this.a);
    }

    public final byte a() {
        return 7;
    }

    public final String toString() {
        return "[" + this.a.length + " bytes]";
    }
}

