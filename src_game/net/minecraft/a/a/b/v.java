/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.a.b;

import net.minecraft.a.a.b.ad;
import net.minecraft.a.a.b.x;
import net.minecraft.a.a.g;

public final class v
extends ad {
    protected v(int n2, int n3) {
        super(n2, n3);
        this.a(0.3f, 0.0f, 0.3f, 0.7f, 0.4f, 0.7f);
    }

    protected final boolean b(int n2) {
        return net.minecraft.a.a.b.x.e[n2];
    }

    public final boolean e(g object, int n2, int n3, int n4) {
        if (((g)object).d(n2, n3, n4) <= 13) {
            n2 = ((g)object).a(n2, n3 - 1, n4);
            object = this;
            if (net.minecraft.a.a.b.x.e[n2]) {
                return true;
            }
        }
        return false;
    }
}

