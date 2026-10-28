/*
 * Decompiled with CFR 0.152.
 */
package com.a.a;

import com.a.a.j;
import java.io.DataInput;
import java.io.DataOutput;

public final class g
extends j {
    private double a;

    final void a(DataOutput dataOutput) {
        dataOutput.writeDouble(this.a);
    }

    final void a(DataInput dataInput) {
        this.a = dataInput.readDouble();
    }

    public final byte a() {
        return 6;
    }

    public final String toString() {
        return "" + this.a;
    }
}

