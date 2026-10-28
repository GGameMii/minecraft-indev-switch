/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.input.Keyboard
 */
package net.minecraft.client.c;

import net.minecraft.client.c.i;
import net.minecraft.client.c.r;
import org.lwjgl.input.Keyboard;

public final class p
extends i {
    private i a;
    private String i = "Enter level name:";
    private int j;
    private String k;
    private int l = 0;

    public p(i i2, String string, int n2) {
        this.a = i2;
        this.j = n2;
        this.k = string;
        if (this.k.equals("-")) {
            this.k = "";
        }
    }

    public final void b() {
        this.e.clear();
        Keyboard.enableRepeatEvents((boolean)true);
        this.e.add(new r(0, this.c / 2 - 100, this.d / 4 + 120, "Save"));
        this.e.add(new r(1, this.c / 2 - 100, this.d / 4 + 144, "Cancel"));
        ((r)this.e.get((int)0)).c = this.k.trim().length() > 1;
    }

    public final void a() {
        Keyboard.enableRepeatEvents((boolean)false);
    }

    public final void f_() {
        ++this.l;
    }

    protected final void a(r r2) {
        if (!r2.c) {
            return;
        }
        if (r2.b == 0 && this.k.trim().length() > 1) {
            this.k.trim();
            this.b.a((i)null);
            this.b.b();
        }
        if (r2.b == 1) {
            this.b.a(this.a);
        }
    }

    protected final void a(char c2, int n2) {
        if (n2 == 14 && this.k.length() > 0) {
            this.k = this.k.substring(0, this.k.length() - 1);
        }
        if ("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 ,.:-_'*!\"#%/()=+?[]{}<>".indexOf(c2) >= 0 && this.k.length() < 64) {
            this.k = this.k + c2;
        }
        ((r)this.e.get((int)0)).c = this.k.trim().length() > 1;
    }

    public final void a(int n2, int n3, float f2) {
        this.h();
        p.a(this.g, this.i, this.c / 2, 40, 0xFFFFFF);
        int n4 = this.c / 2 - 100;
        int n5 = this.d / 2 - 10;
        p.a(n4 - 1, n5 - 1, n4 + 200 + 1, n5 + 20 + 1, -6250336);
        p.a(n4, n5, n4 + 200, n5 + 20, -16777216);
        p.b(this.g, this.k + (this.l / 6 % 2 == 0 ? "_" : ""), n4 + 4, n5 + 6, 0xE0E0E0);
        super.a(n2, n3, f2);
    }
}

