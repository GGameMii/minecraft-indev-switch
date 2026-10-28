/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client.c;

import net.minecraft.client.c.j;
import net.minecraft.client.c.k;
import net.minecraft.client.d;
import org.lwjgl.opengl.GL11;

public class r
extends k {
    private int e = 200;
    private int f = 20;
    private int g;
    private int i;
    public String a;
    public int b;
    public boolean c = true;
    public boolean d = true;

    public r(int n2, int n3, int n4, String string) {
        this(n2, n3, n4, 200, 20, string);
    }

    protected r(int n2, int n3, int n4, int n5, int n6, String string) {
        this.b = n2;
        this.g = n3;
        this.i = n4;
        this.e = n5;
        this.f = 20;
        this.a = string;
    }

    public final void a(d d2, int n2, int n3) {
        if (!this.d) {
            return;
        }
        j j2 = d2.n;
        GL11.glBindTexture((int)3553, (int)d2.m.a("/gui/gui.png"));
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        int n4 = 1;
        int n5 = n2 = n2 >= this.g && n3 >= this.i && n2 < this.g + this.e && n3 < this.i + this.f ? 1 : 0;
        if (!this.c) {
            n4 = 0;
        } else if (n2 != 0) {
            n4 = 2;
        }
        this.b(this.g, this.i, 0, 46 + n4 * 20, this.e / 2, this.f);
        this.b(this.g + this.e / 2, this.i, 200 - this.e / 2, 46 + n4 * 20, this.e / 2, this.f);
        if (!this.c) {
            r.a(j2, this.a, this.g + this.e / 2, this.i + (this.f - 8) / 2, -6250336);
            return;
        }
        if (n2 != 0) {
            r.a(j2, this.a, this.g + this.e / 2, this.i + (this.f - 8) / 2, 0xFFFFA0);
            return;
        }
        r.a(j2, this.a, this.g + this.e / 2, this.i + (this.f - 8) / 2, 0xE0E0E0);
    }

    public final boolean a(int n2, int n3) {
        return this.c && n2 >= this.g && n3 >= this.i && n2 < this.g + this.e && n3 < this.i + this.f;
    }
}

