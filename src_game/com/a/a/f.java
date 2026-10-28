/*
 * Decompiled with CFR 0.152.
 */
package com.a.a;

import com.a.a.j;
import java.io.DataInput;
import java.io.DataOutput;
import java.util.ArrayList;
import java.util.List;

public final class f
extends j {
    private List a = new ArrayList();
    private byte b;

    final void a(DataOutput dataOutput) {
        this.b = this.a.size() > 0 ? ((j)this.a.get(0)).a() : (byte)1;
        dataOutput.writeByte(this.b);
        dataOutput.writeInt(this.a.size());
        for (int i2 = 0; i2 < this.a.size(); ++i2) {
            ((j)this.a.get(i2)).a(dataOutput);
        }
    }

    final void a(DataInput dataInput) {
        this.b = dataInput.readByte();
        int n2 = dataInput.readInt();
        this.a = new ArrayList();
        for (int i2 = 0; i2 < n2; ++i2) {
            j j2 = j.a(this.b);
            j2.a(dataInput);
            this.a.add(j2);
        }
    }

    public final byte a() {
        return 9;
    }

    public final String toString() {
        String string;
        StringBuilder stringBuilder = new StringBuilder().append("").append(this.a.size()).append(" entries of type ");
        byte by = this.b;
        switch (by) {
            case 0: {
                string = "TAG_End";
                break;
            }
            case 1: {
                string = "TAG_Byte";
                break;
            }
            case 2: {
                string = "TAG_Short";
                break;
            }
            case 3: {
                string = "TAG_Int";
                break;
            }
            case 4: {
                string = "TAG_Long";
                break;
            }
            case 5: {
                string = "TAG_Float";
                break;
            }
            case 6: {
                string = "TAG_Double";
                break;
            }
            case 7: {
                string = "TAG_Byte_Array";
                break;
            }
            case 8: {
                string = "TAG_String";
                break;
            }
            case 9: {
                string = "TAG_List";
                break;
            }
            case 10: {
                string = "TAG_Compound";
                break;
            }
            default: {
                string = "UNKNOWN";
            }
        }
        return stringBuilder.append(string).toString();
    }

    public final void a(j j2) {
        this.b = j2.a();
        this.a.add(j2);
    }

    public final j a(int n2) {
        return (j)this.a.get(n2);
    }

    public final int b() {
        return this.a.size();
    }
}

