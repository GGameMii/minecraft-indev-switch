/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.b;

import net.minecraft.a.d.a;

public final class j {
    public a a;
    public float b;
    public float c;

    public j(float f2, float f3, float f4, float f5, float f6) {
        this(new a(f2, f3, f4), f5, f6);
    }

    public final j a(float f2, float f3) {
        return new j(this, f2, f3);
    }

    private j(j j2, float f2, float f3) {
        this.a = j2.a;
        this.b = f2;
        this.c = f3;
    }

    private j(a a2, float f2, float f3) {
        this.a = a2;
        this.b = f2;
        this.c = f3;
    }
}

