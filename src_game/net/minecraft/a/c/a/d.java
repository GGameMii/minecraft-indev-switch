/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.c.a;

import com.a.a.c;
import net.minecraft.a.a.g;
import net.minecraft.a.b.i;
import net.minecraft.a.c.a.e;
import net.minecraft.a.c.b;

public class d
extends e {
    private int P;
    private int Q;
    private int R = 30;
    private int an = -1;

    public d(g g2) {
        super(g2);
        this.V = "/mob/creeper.png";
    }

    protected final void a(c c2) {
        super.a(c2);
    }

    protected final void b(c c2) {
        super.b(c2);
    }

    protected final String a() {
        return "Creeper";
    }

    protected final void e() {
        this.Q = this.P;
        if (this.P > 0 && this.an < 0) {
            --this.P;
        }
        if (this.an >= 0) {
            this.an = 2;
        }
        super.e();
        if (this.an != 1) {
            this.an = -1;
        }
    }

    protected final void a(b b2, float f2) {
        if (this.an <= 0 && f2 < 3.0f || this.an > 0 && f2 < 7.0f) {
            if (this.P == 0) {
                this.d.a(this, "random.fuse", 1.0f, 0.5f);
            }
            this.an = 1;
            ++this.P;
            if (this.P == this.R) {
                this.d.a(this, this.h, this.i, this.j, 3.0f);
                this.k();
            }
            this.O = true;
        }
    }

    public final float c(float f2) {
        return ((float)this.Q + (float)(this.P - this.Q) * f2) / (float)(this.R - 2);
    }

    protected final int c() {
        return net.minecraft.a.b.i.I.ap;
    }
}

