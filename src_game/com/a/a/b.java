/*
 * Decompiled with CFR 0.152.
 */
package com.a.a;

import com.a.a.j;
import java.io.DataInput;
import java.io.DataOutput;

public final class b
extends j {
    public float a;

    public b() {
    }

    public b(float f2) {
        this.a = f2;
    }

    final void a(DataOutput dataOutput) {
        dataOutput.writeFloat(this.a);
    }

    final void a(DataInput dataInput) {
        this.a = dataInput.readFloat();
    }

    public final byte a() {
        return 5;
    }

    public final String toString() {
        return "" + this.a;
    }
}

