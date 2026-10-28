/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.a.a;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.a.a.a.a;
import net.minecraft.a.a.a.b;
import net.minecraft.a.a.a.d;
import net.minecraft.a.a.g;

public final class c {
    private g a;
    private d b = new d();
    private Map c = new HashMap();
    private a[] d = new a[32];

    public c(g g2) {
        this.a = g2;
    }

    public final b a(net.minecraft.a.c.b b2, net.minecraft.a.c.b b3, float f2) {
        return this.a(b2, b3.h, b3.r.b, b3.j, 16.0f);
    }

    public final b a(net.minecraft.a.c.b b2, int n2, int n3, int n4, float f2) {
        return this.a(b2, (float)n2 + 0.5f, (float)n3 + 0.5f, (float)n4 + 0.5f, 16.0f);
    }

    private b a(net.minecraft.a.c.b object, float f2, float f3, float f4, float f5) {
        b b2;
        block10: {
            this.b.a();
            this.c.clear();
            a a2 = this.a((int)((net.minecraft.a.c.b)object).r.a, (int)((net.minecraft.a.c.b)object).r.b, (int)((net.minecraft.a.c.b)object).r.c);
            Object object2 = this.a((int)(f2 - ((net.minecraft.a.c.b)object).w / 2.0f), (int)f3, (int)(f4 - ((net.minecraft.a.c.b)object).w / 2.0f));
            a a3 = new a((int)(((net.minecraft.a.c.b)object).w + 1.0f), (int)(((net.minecraft.a.c.b)object).x + 1.0f), (int)(((net.minecraft.a.c.b)object).w + 1.0f));
            float f6 = f5;
            a a4 = a3;
            a a5 = object2;
            a3 = a2;
            object2 = object;
            object = this;
            a3.f = 0.0f;
            a3.h = a3.g = a3.a(a5);
            ((c)object).b.a();
            ((c)object).b.a(a3);
            a a6 = a3;
            while (!((c)object).b.c()) {
                a a7 = ((c)object).b.b();
                if (a7.d == a5.d) {
                    b2 = net.minecraft.a.a.a.c.a(a5);
                    break block10;
                }
                if (a7.a(a5) < a6.a(a5)) {
                    a6 = a7;
                }
                a7.j = true;
                float f7 = f6;
                a a8 = a5;
                a a9 = a4;
                a a10 = a7;
                Object object3 = object2;
                Object object4 = object;
                int n2 = 0;
                int n3 = 0;
                if (super.a(a10.a, a10.b + 1, a10.c, a9) > 0) {
                    n3 = 1;
                }
                a a11 = super.a((net.minecraft.a.c.b)object3, a10.a, a10.b, a10.c + 1, a9, n3);
                a a12 = super.a((net.minecraft.a.c.b)object3, a10.a - 1, a10.b, a10.c, a9, n3);
                a a13 = super.a((net.minecraft.a.c.b)object3, a10.a + 1, a10.b, a10.c, a9, n3);
                object3 = super.a((net.minecraft.a.c.b)object3, a10.a, a10.b, a10.c - 1, a9, n3);
                if (a11 != null && !a11.j && a11.a(a8) < f7) {
                    ++n2;
                    ((c)object4).d[0] = a11;
                }
                if (a12 != null && !a12.j && a12.a(a8) < f7) {
                    ((c)object4).d[n2++] = a12;
                }
                if (a13 != null && !a13.j && a13.a(a8) < f7) {
                    ((c)object4).d[n2++] = a13;
                }
                if (object3 != null && !((a)object3).j && ((a)object3).a(a8) < f7) {
                    ((c)object4).d[n2++] = object3;
                }
                int n4 = n2;
                for (int i2 = 0; i2 < n4; ++i2) {
                    a10 = ((c)object).d[i2];
                    float f8 = a7.f + a7.a(a10);
                    if (a10.a() && !(f8 < a10.f)) continue;
                    a10.i = a7;
                    a10.f = f8;
                    a10.g = a10.a(a5);
                    if (a10.a()) {
                        ((c)object).b.a(a10, a10.f + a10.g);
                        continue;
                    }
                    a10.h = a10.f + a10.g;
                    ((c)object).b.a(a10);
                }
            }
            b2 = a6 == a3 ? null : net.minecraft.a.a.a.c.a(a6);
        }
        object = b2;
        return b2;
    }

    private a a(net.minecraft.a.c.b object, int n2, int n3, int n4, a a2, int n5) {
        object = null;
        if (this.a(n2, n3, n4, a2) > 0) {
            object = this.a(n2, n3, n4);
        }
        if (object == null && this.a(n2, n3 + n5, n4, a2) > 0) {
            object = this.a(n2, n3 + n5, n4);
        }
        if (object != null) {
            int n6;
            n5 = 0;
            while (n3 > 0 && (n6 = this.a(n2, n3 - 1, n4, a2)) > 0) {
                if (n6 < 0) {
                    return null;
                }
                if (++n5 >= 4) {
                    return null;
                }
                object = this.a(n2, --n3, n4);
            }
            net.minecraft.a.a.d.c c2 = this.a.f(n2, n3 - 1, n4);
            if (c2 == net.minecraft.a.a.d.c.f || c2 == net.minecraft.a.a.d.c.g) {
                return null;
            }
        }
        return object;
    }

    private final a a(int n2, int n3, int n4) {
        int n5 = n2 | n3 << 10 | n4 << 20;
        a a2 = (a)this.c.get(n5);
        if (a2 == null) {
            a2 = new a(n2, n3, n4);
            this.c.put(n5, a2);
        }
        return a2;
    }

    private int a(int n2, int n3, int n4, a a2) {
        for (int i2 = n2; i2 < n2 + a2.a; ++i2) {
            if (i2 < 0 || i2 >= this.a.a) {
                return 0;
            }
            for (int i3 = n3; i3 < n3 + a2.b; ++i3) {
                if (i3 < 0 || i3 >= this.a.c) {
                    return 0;
                }
                for (int i4 = n4; i4 < n4 + a2.c; ++i4) {
                    if (i4 < 0 || i4 >= this.a.b) {
                        return 0;
                    }
                    net.minecraft.a.a.d.c c2 = this.a.f(n2, n3, n4);
                    if (c2.c()) {
                        return 0;
                    }
                    if (c2 != net.minecraft.a.a.d.c.f && c2 != net.minecraft.a.a.d.c.g) continue;
                    return -1;
                }
            }
        }
        return 1;
    }

    private static b a(a a2) {
        int n2 = 1;
        a a3 = a2;
        while (a3.i != null) {
            ++n2;
            a3 = a3.i;
        }
        a[] aArray = new a[n2];
        a3 = a2;
        aArray[--n2] = a3;
        while (a3.i != null) {
            a3 = a3.i;
            aArray[--n2] = a3;
        }
        return new b(aArray);
    }
}

