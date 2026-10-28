/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client.c.a;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.a.b.k;
import net.minecraft.client.a.a.e;
import net.minecraft.client.c;
import net.minecraft.client.c.a.g;
import net.minecraft.client.c.i;
import net.minecraft.client.g.a;
import org.lwjgl.opengl.GL11;

public abstract class j
extends i {
    private static e k = new e();
    private k l = null;
    protected int a = 176;
    protected int i = 166;
    protected List j = new ArrayList();

    public void a(int n2, int n3, float f2) {
        this.h();
        int n4 = (this.c - this.a) / 2;
        int n5 = (this.d - this.i) / 2;
        this.d();
        GL11.glPushMatrix();
        GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        net.minecraft.client.c.b();
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef((float)n4, (float)n5, (float)0.0f);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glEnable((int)2977);
        for (int i2 = 0; i2 < this.j.size(); ++i2) {
            int n6;
            g g2;
            g g3 = g2 = (g)this.j.get(i2);
            j j2 = this;
            Object object = g3.d;
            int n7 = g3.a;
            int n8 = g3.b;
            int n9 = g3.c;
            if ((object = object.a(n7)) == null && (n6 = g3.b()) >= 0) {
                GL11.glDisable((int)2896);
                net.minecraft.client.a.k.a(j2.b.m.a("/gui/items.png"));
                j2.b(n8, n9, n6 % 16 << 4, n6 / 16 << 4, 16, 16);
                GL11.glEnable((int)2896);
            } else {
                k.a(j2.b.m, (k)object, n8, n9);
                k.a(j2.g, (k)object, n8, n9);
            }
            if (!g2.a(n2, n3)) continue;
            GL11.glDisable((int)2896);
            GL11.glDisable((int)2929);
            int n10 = g2.b;
            int n11 = g2.c;
            net.minecraft.client.c.a.j.a(n10, n11, n10 + 16, n11 + 16, -2130706433, -2130706433);
            GL11.glEnable((int)2896);
            GL11.glEnable((int)2929);
        }
        if (this.l != null) {
            GL11.glTranslatef((float)0.0f, (float)0.0f, (float)32.0f);
            k.a(this.b.m, this.l, n2 - n4 - 8, n3 - n5 - 8);
            k.a(this.g, this.l, n2 - n4 - 8, n3 - n5 - 8);
        }
        GL11.glDisable((int)2977);
        net.minecraft.client.c.a();
        GL11.glDisable((int)2896);
        GL11.glDisable((int)2929);
        this.e_();
        GL11.glEnable((int)2896);
        GL11.glEnable((int)2929);
        GL11.glPopMatrix();
    }

    protected void e_() {
    }

    protected abstract void d();

    protected final void a(int n2, int n3, int n4) {
        block18: {
            int n5;
            block19: {
                block20: {
                    k k2;
                    g g2;
                    Object object;
                    block23: {
                        k k3;
                        block25: {
                            k k4;
                            block24: {
                                block22: {
                                    block21: {
                                        g g3;
                                        block17: {
                                            if (n4 != 0 && n4 != 1) break block18;
                                            n5 = n3;
                                            int n6 = n2;
                                            object = this;
                                            for (int i2 = 0; i2 < ((j)object).j.size(); ++i2) {
                                                g g4 = (g)((j)object).j.get(i2);
                                                if (!g4.a(n6, n5)) continue;
                                                g3 = g4;
                                                break block17;
                                            }
                                            g3 = g2 = null;
                                        }
                                        if (g3 == null) break block19;
                                        object = g2;
                                        object = ((g)object).d.a(((g)object).a);
                                        if (object == null && this.l == null) break block20;
                                        if (object == null || this.l != null) break block21;
                                        n5 = n4 == 0 ? ((k)object).a : (((k)object).a + 1) / 2;
                                        this.l = g2.d.a(g2.a, n5);
                                        if (((k)object).a == 0) {
                                            g2.b(null);
                                        }
                                        g2.a();
                                        break block18;
                                    }
                                    if (object != null || this.l == null || !g2.a(this.l)) break block22;
                                    n5 = n4 == 0 ? this.l.a : 1;
                                    if (n5 > g2.d.c()) {
                                        n5 = g2.d.c();
                                    }
                                    g2.b(this.l.a(n5));
                                    if (this.l.a == 0) {
                                        this.l = null;
                                    }
                                    break block18;
                                }
                                if (object == null || this.l == null) break block20;
                                if (!g2.a(this.l)) break block23;
                                if (((k)object).c == this.l.c) break block24;
                                if (this.l.a > g2.d.c()) break block20;
                                Object object2 = object;
                                g2.b(this.l);
                                this.l = object2;
                                break block18;
                            }
                            if (((k)object).c != this.l.c) break block20;
                            if (n4 != 0) break block25;
                            n5 = this.l.a;
                            if (n5 > g2.d.c() - ((k)object).a) {
                                n5 = g2.d.c() - ((k)object).a;
                            }
                            if (n5 > (k4 = this.l).a().c() - ((k)object).a) {
                                k4 = this.l;
                                n5 = k4.a().c() - ((k)object).a;
                            }
                            this.l.a(n5);
                            if (this.l.a == 0) {
                                this.l = null;
                            }
                            ((k)object).a += n5;
                            break block18;
                        }
                        if (n4 != 1) break block20;
                        n5 = 1;
                        if (1 > g2.d.c() - ((k)object).a) {
                            n5 = g2.d.c() - ((k)object).a;
                        }
                        if (n5 > (k3 = this.l).a().c() - ((k)object).a) {
                            k3 = this.l;
                            n5 = k3.a().c() - ((k)object).a;
                        }
                        this.l.a(n5);
                        if (this.l.a == 0) {
                            this.l = null;
                        }
                        ((k)object).a += n5;
                        break block18;
                    }
                    if (((k)object).c == this.l.c && (k2 = this.l).a().c() > 1 && (n5 = ((k)object).a) > 0 && n5 + this.l.a <= (k2 = this.l).a().c()) {
                        this.l.a += n5;
                        ((k)object).a(n5);
                        if (((k)object).a == 0) {
                            g2.b(null);
                        }
                        g2.a();
                    }
                }
                return;
            }
            if (this.l != null) {
                int n7 = (this.c - this.a) / 2;
                n5 = (this.d - this.i) / 2;
                if (n2 < n7 || n3 < n5 || n2 >= n7 + this.a || n3 >= n5 + this.a) {
                    a a2 = this.b.f;
                    if (n4 == 0) {
                        a2.a(this.l);
                        this.l = null;
                    }
                    if (n4 == 1) {
                        a2.a(this.l.a(1));
                        if (this.l.a == 0) {
                            this.l = null;
                        }
                    }
                }
            }
        }
    }

    protected final void a(char c2, int n2) {
        if (n2 == 1 || n2 == this.b.w.n.b) {
            this.b.a((i)null);
        }
    }

    public void a() {
        if (this.l != null) {
            this.b.f.a(this.l);
        }
    }

    public void d_() {
    }

    public final boolean c() {
        return false;
    }
}

