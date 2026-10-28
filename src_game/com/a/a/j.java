/*
 * Decompiled with CFR 0.152.
 */
package com.a.a;

import com.a.a.a;
import com.a.a.b;
import com.a.a.c;
import com.a.a.d;
import com.a.a.e;
import com.a.a.f;
import com.a.a.g;
import com.a.a.h;
import com.a.a.i;
import com.a.a.k;
import com.a.a.l;
import java.io.DataInput;
import java.io.DataOutput;

public abstract class j {
    private String a = null;

    abstract void a(DataOutput var1);

    abstract void a(DataInput var1);

    public abstract byte a();

    public final String c() {
        if (this.a == null) {
            return "";
        }
        return this.a;
    }

    public final j l(String string) {
        this.a = string;
        return this;
    }

    public static j b(DataInput dataInput) {
        byte by = dataInput.readByte();
        if (by == 0) {
            return new l();
        }
        j j2 = j.a(by);
        short s2 = dataInput.readShort();
        byte[] byArray = new byte[s2];
        dataInput.readFully(byArray);
        j2.a = new String(byArray, "UTF-8");
        j2.a(dataInput);
        return j2;
    }

    public static void a(j j2, DataOutput dataOutput) {
        dataOutput.writeByte(j2.a());
        if (j2.a() == 0) {
            return;
        }
        byte[] byArray = j2.c().getBytes("UTF-8");
        dataOutput.writeShort(byArray.length);
        dataOutput.write(byArray);
        j2.a(dataOutput);
    }

    public static j a(byte by) {
        switch (by) {
            case 0: {
                return new l();
            }
            case 1: {
                return new e();
            }
            case 2: {
                return new h();
            }
            case 3: {
                return new d();
            }
            case 4: {
                return new a();
            }
            case 5: {
                return new b();
            }
            case 6: {
                return new g();
            }
            case 7: {
                return new i();
            }
            case 8: {
                return new k();
            }
            case 9: {
                return new f();
            }
            case 10: {
                return new c();
            }
        }
        return null;
    }
}

