/*
 * Decompiled with CFR 0.152.
 */
package com.a.a;

import com.a.a.j;
import java.io.DataInput;
import java.io.DataOutput;

public final class a
extends j {
    public long a;

    public a() {
    }

    public a(long l2) {
        this.a = l2;
    }

    final void a(DataOutput dataOutput) {
        dataOutput.writeLong(this.a);
    }

    final void a(DataInput dataInput) {
        this.a = dataInput.readLong();
    }

    public final byte a() {
        return 4;
    }

    public final String toString() {
        return "" + this.a;
    }
}

