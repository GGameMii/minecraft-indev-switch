/*
 * Decompiled with CFR 0.152.
 */
package com.a.a;

import com.a.a.j;
import java.io.DataInput;
import java.io.DataOutput;

public final class e
extends j {
    public byte a;

    public e() {
    }

    public e(byte by) {
        this.a = by;
    }

    final void a(DataOutput dataOutput) {
        dataOutput.writeByte(this.a);
    }

    final void a(DataInput dataInput) {
        this.a = dataInput.readByte();
    }

    public final byte a() {
        return 1;
    }

    public final String toString() {
        return "" + this.a;
    }
}

