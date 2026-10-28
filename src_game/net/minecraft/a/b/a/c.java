/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.b.a;

import net.minecraft.a.b.k;

public final class c {
    private int a;
    private int b;
    private int[] c;
    private k d;

    public c(int n2, int n3, int[] nArray, k k2) {
        this.a = n2;
        this.b = n3;
        this.c = nArray;
        this.d = k2;
    }

    public final boolean a(int[] nArray) {
        for (int i2 = 0; i2 <= 3 - this.a; ++i2) {
            for (int i3 = 0; i3 <= 3 - this.b; ++i3) {
                if (this.a(nArray, i2, i3, true)) {
                    return true;
                }
                if (!this.a(nArray, i2, i3, false)) continue;
                return true;
            }
        }
        return false;
    }

    private boolean a(int[] nArray, int n2, int n3, boolean bl) {
        for (int i2 = 0; i2 < 3; ++i2) {
            for (int i3 = 0; i3 < 3; ++i3) {
                int n4 = i2 - n2;
                int n5 = i3 - n3;
                int n6 = -1;
                if (n4 >= 0 && n5 >= 0 && n4 < this.a && n5 < this.b) {
                    n6 = bl ? this.c[this.a - n4 - 1 + n5 * this.a] : this.c[n4 + n5 * this.a];
                }
                if (nArray[i2 + i3 * 3] == n6) continue;
                return false;
            }
        }
        return true;
    }

    public final k a() {
        return new k(this.d.c, this.d.a);
    }

    public final int b() {
        return this.a * this.b;
    }
}

