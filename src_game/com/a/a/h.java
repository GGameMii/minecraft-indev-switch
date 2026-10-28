/*
 * Decompiled with CFR 0.152.
 */
package com.a.a;

import com.a.a.j;
import java.io.DataInput;
import java.io.DataOutput;

public final class h
extends j {
    public short a;

    public h() {
    }

    public h(short s2) {
        this.a = s2;
    }

    final void a(DataOutput dataOutput) {
        dataOutput.writeShort(this.a);
    }

    final void a(DataInput dataInput) {
        this.a = dataInput.readShort();
    }

    public final byte a() {
        return 2;
    }

    public final String toString() {
        return "" + this.a;
    }
}

