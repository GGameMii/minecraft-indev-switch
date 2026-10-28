/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.c.b;

import net.minecraft.a.a.b.x;
import net.minecraft.a.a.g;
import net.minecraft.a.c.c;

public abstract class a
extends c {
    public a(g g2) {
        super(g2);
    }

    protected final float a(int n2, int n3, int n4) {
        if (this.d.a(n2, n3 - 1, n4) == net.minecraft.a.a.b.x.j.at) {
            return 10.0f;
        }
        return this.d.c(n2, n3, n4) - 0.5f;
    }

    protected void a(com.a.a.c c2) {
        super.a(c2);
    }

    protected void b(com.a.a.c c2) {
        super.b(c2);
    }

    public final boolean a(float f2, float f3, float f4) {
        return this.d.d((int)f2, (int)f3, (int)f4) > 8 && super.a(f2, f3, f4);
    }
}

