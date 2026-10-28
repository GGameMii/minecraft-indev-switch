/*
 * Decompiled with CFR 0.152.
 */
package com.a.a;

import com.a.a.j;
import java.io.DataInput;
import java.io.DataOutput;

public final class d
extends j {
    public int a;

    public d() {
    }

    public d(int n2) {
        this.a = n2;
    }

    final void a(DataOutput dataOutput) {
        dataOutput.writeInt(this.a);
    }

    final void a(DataInput dataInput) {
        this.a = dataInput.readInt();
    }

    public final byte a() {
        return 3;
    }

    public final String toString() {
        return "" + this.a;
    }
}

