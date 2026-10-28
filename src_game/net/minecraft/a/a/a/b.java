/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.a.a;

import net.minecraft.a.a.a.a;

public final class b {
    private final a[] a;
    private int b;

    public b(a[] aArray) {
        this.a = aArray;
    }

    public final void a() {
        ++this.b;
    }

    public final boolean b() {
        return this.b >= this.a.length;
    }

    public final net.minecraft.a.d.a a(net.minecraft.a.c.b b2) {
        float f2 = (float)this.a[this.b].a + (float)((int)(b2.w + 1.0f)) * 0.5f;
        float f3 = this.a[this.b].b;
        float f4 = (float)this.a[this.b].c + (float)((int)(b2.w + 1.0f)) * 0.5f;
        return new net.minecraft.a.d.a(f2, f3, f4);
    }
}

