/*
 * Decompiled with CFR 0.152.
 */
package com.a.a;

import com.a.a.j;
import java.io.DataInput;
import java.io.DataOutput;

public final class k
extends j {
    public String a;

    public k() {
    }

    public k(String string) {
        this.a = string;
    }

    final void a(DataOutput dataOutput) {
        byte[] byArray = this.a.getBytes("UTF-8");
        dataOutput.writeShort(byArray.length);
        dataOutput.write(byArray);
    }

    final void a(DataInput dataInput) {
        short s2 = dataInput.readShort();
        byte[] byArray = new byte[s2];
        dataInput.readFully(byArray);
        this.a = new String(byArray, "UTF-8");
    }

    public final byte a() {
        return 8;
    }

    public final String toString() {
        return "" + this.a;
    }
}

