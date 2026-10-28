/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.c;

public final class h {
    private int a;
    private int b;

    public h(int n2, int n3) {
        this.a = n2;
        this.b = n3;
        n2 = 1;
        while (this.a / (n2 + 1) >= 320 && this.b / (n2 + 1) >= 240) {
            ++n2;
        }
        this.a /= n2;
        this.b /= n2;
    }

    public final int a() {
        return this.a;
    }

    public final int b() {
        return this.b;
    }
}

