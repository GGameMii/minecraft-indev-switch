/*
 * Decompiled with CFR 0.152.
 */
package com.a.a;

import com.a.a.a;
import com.a.a.b;
import com.a.a.d;
import com.a.a.e;
import com.a.a.f;
import com.a.a.h;
import com.a.a.i;
import com.a.a.j;
import com.a.a.k;
import java.io.DataInput;
import java.io.DataOutput;
import java.util.HashMap;
import java.util.Map;

public final class c
extends j {
    private Map a = new HashMap();

    final void a(DataOutput dataOutput) {
        for (j j2 : this.a.values()) {
            j.a(j2, dataOutput);
        }
        dataOutput.writeByte(0);
    }

    final void a(DataInput dataInput) {
        j j2;
        this.a.clear();
        while ((j2 = j.b(dataInput)).a() != 0) {
            this.a.put(j2.c(), j2);
        }
    }

    public final byte a() {
        return 10;
    }

    public final void a(String string, j j2) {
        this.a.put(string, j2.l(string));
    }

    public final void a(String string, byte by) {
        this.a.put(string, new e(by).l(string));
    }

    public final void a(String string, short s2) {
        this.a.put(string, new h(s2).l(string));
    }

    public final void a(String string, int n2) {
        this.a.put(string, new d(n2).l(string));
    }

    public final void a(String string, long l2) {
        this.a.put(string, new a(l2).l(string));
    }

    public final void a(String string, float f2) {
        this.a.put(string, new b(f2).l(string));
    }

    public final void a(String string, String string2) {
        this.a.put(string, new k(string2).l(string));
    }

    public final void a(String string, byte[] byArray) {
        this.a.put(string, new i(byArray).l(string));
    }

    public final void a(String string, c c2) {
        this.a.put(string, c2.l(string));
    }

    public final void a(String string, boolean bl) {
        this.a(string, bl ? (byte)1 : 0);
    }

    public final boolean a(String string) {
        return this.a.containsKey(string);
    }

    public final byte b(String string) {
        if (!this.a.containsKey(string)) {
            return 0;
        }
        return ((e)this.a.get((Object)string)).a;
    }

    public final short c(String string) {
        if (!this.a.containsKey(string)) {
            return 0;
        }
        return ((h)this.a.get((Object)string)).a;
    }

    public final int d(String string) {
        if (!this.a.containsKey(string)) {
            return 0;
        }
        return ((d)this.a.get((Object)string)).a;
    }

    public final long e(String string) {
        if (!this.a.containsKey(string)) {
            return 0L;
        }
        return ((a)this.a.get((Object)string)).a;
    }

    public final float f(String string) {
        if (!this.a.containsKey(string)) {
            return 0.0f;
        }
        return ((b)this.a.get((Object)string)).a;
    }

    public final String g(String string) {
        if (!this.a.containsKey(string)) {
            return "";
        }
        return ((k)this.a.get((Object)string)).a;
    }

    public final byte[] h(String string) {
        if (!this.a.containsKey(string)) {
            return new byte[0];
        }
        return ((i)this.a.get((Object)string)).a;
    }

    public final c i(String string) {
        if (!this.a.containsKey(string)) {
            return new c();
        }
        return (c)this.a.get(string);
    }

    public final f j(String string) {
        if (!this.a.containsKey(string)) {
            return new f();
        }
        return (f)this.a.get(string);
    }

    public final boolean k(String string) {
        return this.b(string) != 0;
    }

    public final String toString() {
        return "" + this.a.size() + " entries";
    }

    public final boolean b() {
        return this.a.isEmpty();
    }
}

