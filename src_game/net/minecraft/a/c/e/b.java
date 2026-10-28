/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.c.e;

import net.minecraft.a.b.j;
import net.minecraft.a.b.k;
import net.minecraft.a.c.e.a;

public final class b
implements net.minecraft.a.b {
    public k[] a = new k[36];
    public k[] b = new k[4];
    public int c = 0;
    private a d;

    public b(a a2) {
        this.d = a2;
    }

    public final k d() {
        return this.a[this.c];
    }

    private int d(int n2) {
        for (int i2 = 0; i2 < this.a.length; ++i2) {
            if (this.a[i2] == null || this.a[i2].c != n2) continue;
            return i2;
        }
        return -1;
    }

    private int f() {
        for (int i2 = 0; i2 < this.a.length; ++i2) {
            if (this.a[i2] != null) continue;
            return i2;
        }
        return -1;
    }

    public final void b(int n2) {
        if ((n2 = this.d(n2)) >= 0 && n2 < 9) {
            this.c = n2;
            return;
        }
    }

    public final boolean c(int n2) {
        if ((n2 = this.d(n2)) < 0) {
            return false;
        }
        if (--this.a[n2].a <= 0) {
            this.a[n2] = null;
        }
        return true;
    }

    public final boolean a(k k2) {
        int n2;
        if (k2.d == 0) {
            int n3;
            int n4;
            int n5;
            Object object;
            b b2;
            int n6;
            int n7;
            block12: {
                n7 = k2.a;
                n6 = k2.c;
                b2 = this;
                int n8 = n6;
                b b3 = b2;
                for (int i2 = 0; i2 < b3.a.length; ++i2) {
                    if (b3.a[i2] == null || b3.a[i2].c != n8 || b3.a[i2].a >= ((k)(object = b3.a[i2])).a().c()) continue;
                    object = b3;
                    if (b3.a[i2].a >= 64) continue;
                    n5 = i2;
                    break block12;
                }
                n5 = n4 = -1;
            }
            if (n5 < 0) {
                n4 = b2.f();
            }
            if (n4 < 0) {
                n3 = n7;
            } else {
                if (b2.a[n4] == null) {
                    b2.a[n4] = new k(n6, 0);
                }
                n6 = n7;
                object = b2.a[n4];
                if (n7 > ((k)object).a().c() - b2.a[n4].a) {
                    object = b2.a[n4];
                    n6 = ((k)object).a().c() - b2.a[n4].a;
                }
                object = b2;
                if (n6 > 64 - b2.a[n4].a) {
                    object = b2;
                    n6 = 64 - b2.a[n4].a;
                }
                if (n6 == 0) {
                    n3 = n7;
                } else {
                    b2.a[n4].a += n6;
                    b2.a[n4].b = 5;
                    n3 = k2.a = (n7 -= n6);
                }
            }
            if (k2.a == 0) {
                return true;
            }
        }
        if ((n2 = this.f()) >= 0) {
            this.a[n2] = k2;
            this.a[n2].b = 5;
            return true;
        }
        return false;
    }

    public final k a(int n2, int n3) {
        k[] kArray = this.a;
        if (n2 >= this.a.length) {
            kArray = this.b;
            n2 -= this.a.length;
        }
        if (kArray[n2] != null) {
            if (kArray[n2].a <= n3) {
                k k2 = kArray[n2];
                kArray[n2] = null;
                return k2;
            }
            k k3 = kArray[n2].a(n3);
            if (kArray[n2].a == 0) {
                kArray[n2] = null;
            }
            return k3;
        }
        return null;
    }

    public final void a(int n2, k k2) {
        k[] kArray = this.a;
        if (n2 >= this.a.length) {
            kArray = this.b;
            n2 -= this.a.length;
        }
        kArray[n2] = k2;
    }

    public final int a() {
        return this.a.length + 4;
    }

    public final k a(int n2) {
        k[] kArray = this.a;
        if (n2 >= this.a.length) {
            kArray = this.b;
            n2 -= this.a.length;
        }
        return kArray[n2];
    }

    public final String b() {
        return "Inventory";
    }

    public final int c() {
        return 64;
    }

    public final int e() {
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        for (int i2 = 0; i2 < this.b.length; ++i2) {
            if (this.b[i2] == null || !(this.b[i2].a() instanceof j)) continue;
            int n5 = this.b[i2].b();
            int n6 = this.b[i2].d;
            n6 = n5 - n6;
            n3 += n6;
            n4 += n5;
            n5 = ((j)this.b[i2].a()).at;
            n2 += n5;
        }
        if (n4 == 0) {
            return 0;
        }
        return (n2 - 1) * n3 / n4 + 1;
    }
}

