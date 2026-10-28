/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.b;

import net.minecraft.a.b.i;

public final class j
extends i {
    private static final int[] av = new int[]{3, 8, 6, 3};
    private static final int[] aw = new int[]{11, 16, 15, 13};
    public final int as;
    public final int at;
    public final int au;

    public j(int n2, int n3, int n4, int n5) {
        super(n2);
        this.as = n5;
        this.au = n4;
        this.at = av[n5];
        this.ar = aw[n5] * 3 << n3;
        this.aq = 1;
    }
}

