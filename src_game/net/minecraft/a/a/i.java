/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.a;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.a.a.f;
import net.minecraft.a.c.b;

public final class i {
    public int a;
    public int b;
    public int c;
    private f f = new f(this);
    private f g = new f(this);
    public List[] d;
    public List e = new ArrayList();
    private List h = new ArrayList();

    public i(int n2, int n3, int n4) {
        this.a = n2 / 8;
        this.b = n3 / 8;
        this.c = n4 / 8;
        if (this.a == 0) {
            this.a = 1;
        }
        if (this.b == 0) {
            this.b = 1;
        }
        if (this.c == 0) {
            this.c = 1;
        }
        this.d = new ArrayList[this.a * this.b * this.c];
        for (n2 = 0; n2 < this.a; ++n2) {
            for (n3 = 0; n3 < this.b; ++n3) {
                for (n4 = 0; n4 < this.c; ++n4) {
                    this.d[(n4 * this.b + n3) * this.a + n2] = new ArrayList();
                }
            }
        }
    }

    public final void a(b b2) {
        this.e.add(b2);
        this.f.a(b2.h, b2.i, b2.j).a(b2);
        b2.B = b2.h;
        b2.C = b2.i;
        b2.D = b2.j;
    }

    public final void b(b b2) {
        this.f.a(b2.B, b2.C, b2.D).b(b2);
        this.f.a(b2.h, b2.i, b2.j).b(b2);
        this.e.remove(b2);
    }

    public final List a(b b2, float f2, float f3, float f4, float f5, float f6, float f7) {
        this.h.clear();
        return this.a(b2, f2, f3, f4, f5, f6, f7, this.h);
    }

    private List a(b b2, float f2, float f3, float f4, float f5, float f6, float f7, List list) {
        f f8 = this.f.a(f2, f3, f4);
        f f9 = this.g.a(f5, f6, f7);
        for (int i2 = net.minecraft.a.a.f.a(f8) - 1; i2 <= net.minecraft.a.a.f.a(f9) + 1; ++i2) {
            for (int i3 = net.minecraft.a.a.f.b(f8) - 1; i3 <= net.minecraft.a.a.f.b(f9) + 1; ++i3) {
                for (int i4 = net.minecraft.a.a.f.c(f8) - 1; i4 <= net.minecraft.a.a.f.c(f9) + 1; ++i4) {
                    if (i2 < 0 || i3 < 0 || i4 < 0 || i2 >= this.a || i3 >= this.b || i4 >= this.c) continue;
                    List list2 = this.d[(i4 * this.b + i3) * this.a + i2];
                    for (int i5 = 0; i5 < list2.size(); ++i5) {
                        b b3 = (b)list2.get(i5);
                        if (b3 == b2) continue;
                        float f10 = f7;
                        float f11 = f6;
                        float f12 = f5;
                        float f13 = f4;
                        float f14 = f3;
                        float f15 = f2;
                        Object object = b3;
                        object = ((b)object).r;
                        if (!(f12 <= ((net.minecraft.a.d.b)object).a || f15 >= ((net.minecraft.a.d.b)object).d ? false : (f11 <= ((net.minecraft.a.d.b)object).b || f14 >= ((net.minecraft.a.d.b)object).e ? false : !(f10 <= ((net.minecraft.a.d.b)object).c) && !(f13 >= ((net.minecraft.a.d.b)object).f)))) continue;
                        list.add(b3);
                    }
                }
            }
        }
        return list;
    }

    public final List a(b b2, net.minecraft.a.d.b b3) {
        this.h.clear();
        if (b3 == null) {
            return this.h;
        }
        return this.a(b2, b3.a, b3.b, b3.c, b3.d, b3.e, b3.f, this.h);
    }

    public final void a() {
        for (int i2 = 0; i2 < this.e.size(); ++i2) {
            Object object = (b)this.e.get(i2);
            ((b)this.e.get(i2)).B = ((b)object).h;
            ((b)object).C = ((b)object).i;
            ((b)object).D = ((b)object).j;
            ((b)object).b_();
            if (((b)object).u) {
                this.e.remove(i2--);
                this.f.a(((b)object).B, ((b)object).C, ((b)object).D).b((b)object);
                continue;
            }
            int n2 = (int)(((b)object).B / 8.0f);
            int n3 = (int)(((b)object).C / 8.0f);
            int n4 = (int)(((b)object).D / 8.0f);
            int n5 = (int)(((b)object).h / 8.0f);
            int n6 = (int)(((b)object).i / 8.0f);
            int n7 = (int)(((b)object).j / 8.0f);
            if (n2 == n5 && n3 == n6 && n4 == n7) continue;
            b b2 = object;
            object = this;
            f f2 = ((i)object).f.a(b2.B, b2.C, b2.D);
            if (f2.equals(object = ((i)object).g.a(b2.h, b2.i, b2.j))) continue;
            f2.b(b2);
            ((f)object).a(b2);
        }
    }
}

