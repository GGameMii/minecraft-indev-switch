/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.b;

import net.minecraft.client.b.j;

public final class i {
    public j[] a;

    private i(j[] jArray) {
        this.a = jArray;
    }

    public i(j[] jArray, int n2, int n3, int n4, int n5) {
        this(jArray);
        jArray[0] = jArray[0].a((float)n4 / 64.0f - 0.0015625f, (float)n3 / 32.0f + 0.003125f);
        jArray[1] = jArray[1].a((float)n2 / 64.0f + 0.0015625f, (float)n3 / 32.0f + 0.003125f);
        jArray[2] = jArray[2].a((float)n2 / 64.0f + 0.0015625f, (float)n5 / 32.0f - 0.003125f);
        jArray[3] = jArray[3].a((float)n4 / 64.0f - 0.0015625f, (float)n5 / 32.0f - 0.003125f);
    }
}

