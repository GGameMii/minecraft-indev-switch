/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.a.b;

import net.minecraft.client.a.b.a;
import net.minecraft.client.a.b.b;
import net.minecraft.client.a.b.e;

public final class c
implements e {
    private b a = net.minecraft.client.a.b.a.a();

    public final boolean a(net.minecraft.a.d.b object) {
        net.minecraft.a.d.b b2 = object;
        object = this.a;
        float f2 = b2.f;
        float f3 = b2.e;
        float f4 = b2.d;
        float f5 = b2.c;
        float f6 = b2.b;
        float f7 = b2.a;
        for (int i2 = 0; i2 < 6; ++i2) {
            if (((b)object).a[i2][0] * f7 + ((b)object).a[i2][1] * f6 + ((b)object).a[i2][2] * f5 + ((b)object).a[i2][3] > 0.0f || ((b)object).a[i2][0] * f4 + ((b)object).a[i2][1] * f6 + ((b)object).a[i2][2] * f5 + ((b)object).a[i2][3] > 0.0f || ((b)object).a[i2][0] * f7 + ((b)object).a[i2][1] * f3 + ((b)object).a[i2][2] * f5 + ((b)object).a[i2][3] > 0.0f || ((b)object).a[i2][0] * f4 + ((b)object).a[i2][1] * f3 + ((b)object).a[i2][2] * f5 + ((b)object).a[i2][3] > 0.0f || ((b)object).a[i2][0] * f7 + ((b)object).a[i2][1] * f6 + ((b)object).a[i2][2] * f2 + ((b)object).a[i2][3] > 0.0f || ((b)object).a[i2][0] * f4 + ((b)object).a[i2][1] * f6 + ((b)object).a[i2][2] * f2 + ((b)object).a[i2][3] > 0.0f || ((b)object).a[i2][0] * f7 + ((b)object).a[i2][1] * f3 + ((b)object).a[i2][2] * f2 + ((b)object).a[i2][3] > 0.0f || ((b)object).a[i2][0] * f4 + ((b)object).a[i2][1] * f3 + ((b)object).a[i2][2] * f2 + ((b)object).a[i2][3] > 0.0f) continue;
            return false;
        }
        return true;
    }
}

