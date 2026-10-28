/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.c.b;

import com.a.a.c;
import net.minecraft.a.a.b.x;
import net.minecraft.a.a.g;
import net.minecraft.a.c.b.a;
import net.minecraft.a.c.e;

public class b
extends a {
    public boolean a = false;

    public b(g g2) {
        super(g2);
        this.V = "/mob/sheep.png";
        this.a(0.9f, 1.3f);
    }

    public final boolean a(net.minecraft.a.c.b b2, int n2) {
        if (!this.a && b2 instanceof e) {
            this.a = true;
            int n3 = 1 + this.G.nextInt(3);
            for (int i2 = 0; i2 < n3; ++i2) {
                net.minecraft.a.c.c.b b3 = this.a(net.minecraft.a.a.b.x.Q.at, 1, 1.0f);
                b3.l += this.G.nextFloat() * 0.05f;
                b3.k += (this.G.nextFloat() - this.G.nextFloat()) * 0.1f;
                b3.m += (this.G.nextFloat() - this.G.nextFloat()) * 0.1f;
            }
        }
        return super.a(b2, n2);
    }

    protected final void a(c c2) {
        super.a(c2);
        c2.a("Sheared", this.a);
    }

    protected final void b(c c2) {
        super.b(c2);
        this.a = c2.k("Sheared");
    }

    protected final String a() {
        return "Sheep";
    }

    protected final String g() {
        return "mob.sheep";
    }

    protected final String h() {
        return "mob.sheep";
    }

    protected final String i() {
        return "mob.sheep";
    }
}

