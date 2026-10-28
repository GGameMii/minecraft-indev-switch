/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client.a;

import net.minecraft.a.a.b.x;
import net.minecraft.a.a.d.c;
import net.minecraft.a.a.g;
import net.minecraft.client.a.d;
import org.lwjgl.opengl.GL11;

public final class f {
    private g a;
    private int b = -1;
    private boolean c = false;

    public f(g g2) {
        this.a = g2;
    }

    public f() {
    }

    public final void a(x x2, int n2, int n3, int n4, int n5) {
        this.b = n5;
        this.b(x2, n2, n3, n4);
        this.b = -1;
    }

    public final void a(x x2, int n2, int n3, int n4) {
        this.c = true;
        this.b(x2, n2, n3, n4);
        this.c = false;
    }

    public final boolean b(x object, int n2, int n3, int n4) {
        int n5 = ((x)object).a();
        if (n5 == 0) {
            float f2;
            n5 = n4;
            n4 = n3;
            n3 = n2;
            x x2 = object;
            object = this;
            d d2 = d.a;
            boolean bl = false;
            if (((f)object).c || x2.d(((f)object).a, n3, n4 - 1, n5, 0)) {
                f2 = x2.f(((f)object).a, n3, n4 - 1, n5);
                if (x.h[x2.at] > 0) {
                    f2 = 1.0f;
                }
                d2.a(0.5f * f2, 0.5f * f2, 0.5f * f2);
                super.a(x2, (float)n3, (float)n4, (float)n5, x2.a(((f)object).a, n3, n4, n5, 0));
                bl = true;
            }
            if (((f)object).c || x2.d(((f)object).a, n3, n4 + 1, n5, 1)) {
                f2 = x2.f(((f)object).a, n3, n4 + 1, n5);
                if (x.h[x2.at] > 0) {
                    f2 = 1.0f;
                }
                d2.a(f2 * 1.0f, f2 * 1.0f, f2 * 1.0f);
                super.b(x2, (float)n3, (float)n4, (float)n5, x2.a(((f)object).a, n3, n4, n5, 1));
                bl = true;
            }
            if (((f)object).c || x2.d(((f)object).a, n3, n4, n5 - 1, 2)) {
                f2 = x2.f(((f)object).a, n3, n4, n5 - 1);
                if (x.h[x2.at] > 0) {
                    f2 = 1.0f;
                }
                d2.a(0.8f * f2, 0.8f * f2, 0.8f * f2);
                super.b(x2, n3, n4, n5, x2.a(((f)object).a, n3, n4, n5, 2));
                bl = true;
            }
            if (((f)object).c || x2.d(((f)object).a, n3, n4, n5 + 1, 3)) {
                f2 = x2.f(((f)object).a, n3, n4, n5 + 1);
                if (x.h[x2.at] > 0) {
                    f2 = 1.0f;
                }
                d2.a(0.8f * f2, 0.8f * f2, 0.8f * f2);
                super.c(x2, n3, n4, n5, x2.a(((f)object).a, n3, n4, n5, 3));
                bl = true;
            }
            if (((f)object).c || x2.d(((f)object).a, n3 - 1, n4, n5, 4)) {
                f2 = x2.f(((f)object).a, n3 - 1, n4, n5);
                if (x.h[x2.at] > 0) {
                    f2 = 1.0f;
                }
                d2.a(0.6f * f2, 0.6f * f2, 0.6f * f2);
                super.d(x2, n3, n4, n5, x2.a(((f)object).a, n3, n4, n5, 4));
                bl = true;
            }
            if (((f)object).c || x2.d(((f)object).a, n3 + 1, n4, n5, 5)) {
                f2 = x2.f(((f)object).a, n3 + 1, n4, n5);
                if (x.h[x2.at] > 0) {
                    f2 = 1.0f;
                }
                d2.a(0.6f * f2, 0.6f * f2, 0.6f * f2);
                super.e(x2, n3, n4, n5, x2.a(((f)object).a, n3, n4, n5, 5));
                bl = true;
            }
            return bl;
        }
        if (n5 == 4) {
            n5 = n4;
            n4 = n3;
            n3 = n2;
            x x3 = object;
            object = this;
            d d3 = d.a;
            boolean bl = false;
            float f3 = x3.av;
            float f4 = x3.ay;
            x3.ay = f4 - super.a(n3, n4, n5);
            if (((f)object).c || x3.d(((f)object).a, n3, n4 - 1, n5, 0)) {
                float f5 = x3.f(((f)object).a, n3, n4 - 1, n5);
                d3.a(0.5f * f5, 0.5f * f5, 0.5f * f5);
                super.a(x3, (float)n3, (float)n4, (float)n5, x3.a(0));
                bl = true;
            }
            if (((f)object).c || x3.d(((f)object).a, n3, n4 + 1, n5, 1)) {
                float f6 = x3.f(((f)object).a, n3, n4 + 1, n5);
                d3.a(f6 * 1.0f, f6 * 1.0f, f6 * 1.0f);
                super.b(x3, (float)n3, (float)n4, (float)n5, x3.a(1));
                bl = true;
            }
            x3.av = f4 - super.a(n3, n4, n5 - 1);
            if (((f)object).c || x3.ay > x3.av || x3.d(((f)object).a, n3, n4, n5 - 1, 2)) {
                float f7 = x3.f(((f)object).a, n3, n4, n5 - 1);
                d3.a(0.8f * f7, 0.8f * f7, 0.8f * f7);
                super.b(x3, n3, n4, n5, x3.a(2));
                bl = true;
            }
            x3.av = f4 - super.a(n3, n4, n5 + 1);
            if (((f)object).c || x3.ay > x3.av || x3.d(((f)object).a, n3, n4, n5 + 1, 3)) {
                float f8 = x3.f(((f)object).a, n3, n4, n5 + 1);
                d3.a(0.8f * f8, 0.8f * f8, 0.8f * f8);
                super.c(x3, n3, n4, n5, x3.a(3));
                bl = true;
            }
            x3.av = f4 - super.a(n3 - 1, n4, n5);
            if (((f)object).c || x3.ay > x3.av || x3.d(((f)object).a, n3 - 1, n4, n5, 4)) {
                float f9 = x3.f(((f)object).a, n3 - 1, n4, n5);
                d3.a(0.6f * f9, 0.6f * f9, 0.6f * f9);
                super.d(x3, n3, n4, n5, x3.a(4));
                bl = true;
            }
            x3.av = f4 - super.a(n3 + 1, n4, n5);
            if (((f)object).c || x3.ay > x3.av || x3.d(((f)object).a, n3 + 1, n4, n5, 5)) {
                float f10 = x3.f(((f)object).a, n3 + 1, n4, n5);
                d3.a(0.6f * f10, 0.6f * f10, 0.6f * f10);
                super.e(x3, n3, n4, n5, x3.a(5));
                bl = true;
            }
            x3.av = f3;
            x3.ay = f4;
            return bl;
        }
        if (n5 == 1) {
            n5 = n4;
            n4 = n3;
            n3 = n2;
            x x4 = object;
            object = this;
            d d4 = d.a;
            float f11 = x4.f(((f)object).a, n3, n4, n5);
            d4.a(f11, f11, f11);
            super.a(x4, (int)((f)object).a.e(n3, n4, n5), (float)n3, (float)n4, (float)n5);
            return true;
        }
        if (n5 == 6) {
            n5 = n4;
            n4 = n3;
            n3 = n2;
            Object object2 = object;
            object = this;
            d d5 = d.a;
            float f12 = ((x)object2).f(((f)object).a, n3, n4, n5);
            d5.a(f12, f12, f12);
            super.b((x)object2, (int)((f)object).a.e(n3, n4, n5), (float)n3, (float)n4 - 0.0625f, (float)n5);
            return true;
        }
        if (n5 == 2) {
            n5 = n4;
            n4 = n3;
            n3 = n2;
            Object object3 = object;
            object = this;
            byte by = ((f)object).a.e(n3, n4, n5);
            d d6 = d.a;
            float f13 = ((x)object3).f(((f)object).a, n3, n4, n5);
            if (x.h[((x)object3).at] > 0) {
                f13 = 1.0f;
            }
            d6.a(f13, f13, f13);
            if (by == 1) {
                super.a((x)object3, (float)n3 - 0.099999994f, (float)n4 + 0.2f, n5, -0.4f, 0.0f);
            } else if (by == 2) {
                super.a((x)object3, (float)n3 + 0.099999994f, (float)n4 + 0.2f, n5, 0.4f, 0.0f);
            } else if (by == 3) {
                super.a((x)object3, n3, (float)n4 + 0.2f, (float)n5 - 0.099999994f, 0.0f, -0.4f);
            } else if (by == 4) {
                super.a((x)object3, n3, (float)n4 + 0.2f, (float)n5 + 0.099999994f, 0.0f, 0.4f);
            } else {
                super.a((x)object3, n3, n4, n5, 0.0f, 0.0f);
            }
            return true;
        }
        if (n5 == 3) {
            n5 = n4;
            n4 = n3;
            n3 = n2;
            Object object4 = object;
            object = this;
            d d7 = d.a;
            int n6 = ((x)object4).a(0);
            if (((f)object).b >= 0) {
                n6 = ((f)object).b;
            }
            float f14 = ((x)object4).f(((f)object).a, n3, n4, n5);
            d7.a(f14, f14, f14);
            int n7 = (n6 & 0xF) << 4;
            int n8 = n6 & 0xF0;
            float f15 = (float)n7 / 256.0f;
            float f16 = ((float)n7 + 15.99f) / 256.0f;
            float f17 = (float)n8 / 256.0f;
            float f18 = ((float)n8 + 15.99f) / 256.0f;
            if (((f)object).a.b(n3, n4 - 1, n5) || x.ag.e(((f)object).a, n3, n4 - 1, n5)) {
                float f19 = (float)n3 + 0.5f + 0.2f;
                float f20 = (float)n3 + 0.5f - 0.2f;
                float f21 = (float)n5 + 0.5f + 0.2f;
                float f22 = (float)n5 + 0.5f - 0.2f;
                float f23 = (float)n3 + 0.5f - 0.3f;
                float f24 = (float)n3 + 0.5f + 0.3f;
                float f25 = (float)n5 + 0.5f - 0.3f;
                float f26 = (float)n5 + 0.5f + 0.3f;
                d7.a(f23, (float)n4 + 1.4f, n5 + 1, f16, f17);
                d7.a(f19, n4, n5 + 1, f16, f18);
                d7.a(f19, n4, n5, f15, f18);
                d7.a(f23, (float)n4 + 1.4f, n5, f15, f17);
                d7.a(f24, (float)n4 + 1.4f, n5, f16, f17);
                d7.a(f20, n4, n5, f16, f18);
                d7.a(f20, n4, n5 + 1, f15, f18);
                d7.a(f24, (float)n4 + 1.4f, n5 + 1, f15, f17);
                f15 = (float)n7 / 256.0f;
                f16 = ((float)n7 + 15.99f) / 256.0f;
                f17 = (float)(n8 + 16) / 256.0f;
                f18 = ((float)n8 + 15.99f + 16.0f) / 256.0f;
                d7.a(n3 + 1, (float)n4 + 1.4f, f26, f16, f17);
                d7.a(n3 + 1, n4, f22, f16, f18);
                d7.a(n3, n4, f22, f15, f18);
                d7.a(n3, (float)n4 + 1.4f, f26, f15, f17);
                d7.a(n3, (float)n4 + 1.4f, f25, f16, f17);
                d7.a(n3, n4, f21, f16, f18);
                d7.a(n3 + 1, n4, f21, f15, f18);
                d7.a(n3 + 1, (float)n4 + 1.4f, f25, f15, f17);
                f19 = (float)n3 + 0.5f - 0.5f;
                f20 = (float)n3 + 0.5f + 0.5f;
                f21 = (float)n5 + 0.5f - 0.5f;
                f22 = (float)n5 + 0.5f + 0.5f;
                f23 = (float)n3 + 0.5f - 0.4f;
                f24 = (float)n3 + 0.5f + 0.4f;
                f25 = (float)n5 + 0.5f - 0.4f;
                f26 = (float)n5 + 0.5f + 0.4f;
                d7.a(f23, (float)n4 + 1.4f, n5, f15, f17);
                d7.a(f19, n4, n5, f15, f18);
                d7.a(f19, n4, n5 + 1, f16, f18);
                d7.a(f23, (float)n4 + 1.4f, n5 + 1, f16, f17);
                d7.a(f24, (float)n4 + 1.4f, n5 + 1, f15, f17);
                d7.a(f20, n4, n5 + 1, f15, f18);
                d7.a(f20, n4, n5, f16, f18);
                d7.a(f24, (float)n4 + 1.4f, n5, f16, f17);
                f15 = (float)n7 / 256.0f;
                f16 = ((float)n7 + 15.99f) / 256.0f;
                f17 = (float)n8 / 256.0f;
                f18 = ((float)n8 + 15.99f) / 256.0f;
                d7.a(n3, (float)n4 + 1.4f, f26, f15, f17);
                d7.a(n3, n4, f22, f15, f18);
                d7.a(n3 + 1, n4, f22, f16, f18);
                d7.a(n3 + 1, (float)n4 + 1.4f, f26, f16, f17);
                d7.a(n3 + 1, (float)n4 + 1.4f, f25, f15, f17);
                d7.a(n3 + 1, n4, f21, f15, f18);
                d7.a(n3, n4, f21, f16, f18);
                d7.a(n3, (float)n4 + 1.4f, f25, f16, f17);
            } else {
                float f27;
                if ((n3 + n4 + n5 & 1) == 1) {
                    f15 = (float)n7 / 256.0f;
                    f16 = ((float)n7 + 15.99f) / 256.0f;
                    f17 = (float)(n8 + 16) / 256.0f;
                    f18 = ((float)n8 + 15.99f + 16.0f) / 256.0f;
                }
                if ((n3 / 2 + n4 / 2 + n5 / 2 & 1) == 1) {
                    f27 = f16;
                    f16 = f15;
                    f15 = f27;
                }
                if (x.ag.e(((f)object).a, n3 - 1, n4, n5)) {
                    d7.a((float)n3 + 0.2f, (float)n4 + 1.4f + 0.0625f, n5 + 1, f16, f17);
                    d7.a(n3, (float)n4 + 0.0625f, n5 + 1, f16, f18);
                    d7.a(n3, (float)n4 + 0.0625f, n5, f15, f18);
                    d7.a((float)n3 + 0.2f, (float)n4 + 1.4f + 0.0625f, n5, f15, f17);
                    d7.a((float)n3 + 0.2f, (float)n4 + 1.4f + 0.0625f, n5, f15, f17);
                    d7.a(n3, (float)n4 + 0.0625f, n5, f15, f18);
                    d7.a(n3, (float)n4 + 0.0625f, n5 + 1, f16, f18);
                    d7.a((float)n3 + 0.2f, (float)n4 + 1.4f + 0.0625f, n5 + 1, f16, f17);
                }
                if (x.ag.e(((f)object).a, n3 + 1, n4, n5)) {
                    d7.a((float)(n3 + 1) - 0.2f, (float)n4 + 1.4f + 0.0625f, n5, f15, f17);
                    d7.a(n3 + 1, (float)n4 + 0.0625f, n5, f15, f18);
                    d7.a(n3 + 1, (float)n4 + 0.0625f, n5 + 1, f16, f18);
                    d7.a((float)(n3 + 1) - 0.2f, (float)n4 + 1.4f + 0.0625f, n5 + 1, f16, f17);
                    d7.a((float)(n3 + 1) - 0.2f, (float)n4 + 1.4f + 0.0625f, n5 + 1, f16, f17);
                    d7.a(n3 + 1, (float)n4 + 0.0625f, n5 + 1, f16, f18);
                    d7.a(n3 + 1, (float)n4 + 0.0625f, n5, f15, f18);
                    d7.a((float)(n3 + 1) - 0.2f, (float)n4 + 1.4f + 0.0625f, n5, f15, f17);
                }
                if (x.ag.e(((f)object).a, n3, n4, n5 - 1)) {
                    d7.a(n3, (float)n4 + 1.4f + 0.0625f, (float)n5 + 0.2f, f16, f17);
                    d7.a(n3, (float)n4 + 0.0625f, n5, f16, f18);
                    d7.a(n3 + 1, (float)n4 + 0.0625f, n5, f15, f18);
                    d7.a(n3 + 1, (float)n4 + 1.4f + 0.0625f, (float)n5 + 0.2f, f15, f17);
                    d7.a(n3 + 1, (float)n4 + 1.4f + 0.0625f, (float)n5 + 0.2f, f15, f17);
                    d7.a(n3 + 1, (float)n4 + 0.0625f, n5, f15, f18);
                    d7.a(n3, (float)n4 + 0.0625f, n5, f16, f18);
                    d7.a(n3, (float)n4 + 1.4f + 0.0625f, (float)n5 + 0.2f, f16, f17);
                }
                if (x.ag.e(((f)object).a, n3, n4, n5 + 1)) {
                    d7.a(n3 + 1, (float)n4 + 1.4f + 0.0625f, (float)(n5 + 1) - 0.2f, f15, f17);
                    d7.a(n3 + 1, (float)n4 + 0.0625f, n5 + 1, f15, f18);
                    d7.a(n3, (float)n4 + 0.0625f, n5 + 1, f16, f18);
                    d7.a(n3, (float)n4 + 1.4f + 0.0625f, (float)(n5 + 1) - 0.2f, f16, f17);
                    d7.a(n3, (float)n4 + 1.4f + 0.0625f, (float)(n5 + 1) - 0.2f, f16, f17);
                    d7.a(n3, (float)n4 + 0.0625f, n5 + 1, f16, f18);
                    d7.a(n3 + 1, (float)n4 + 0.0625f, n5 + 1, f15, f18);
                    d7.a(n3 + 1, (float)n4 + 1.4f + 0.0625f, (float)(n5 + 1) - 0.2f, f15, f17);
                }
                if (x.ag.e(((f)object).a, n3, n4 + 1, n5)) {
                    f27 = (float)n3 + 0.5f + 0.5f;
                    float f28 = (float)n3 + 0.5f - 0.5f;
                    float f29 = (float)n5 + 0.5f + 0.5f;
                    float f30 = (float)n5 + 0.5f - 0.5f;
                    f15 = (float)n7 / 256.0f;
                    f16 = ((float)n7 + 15.99f) / 256.0f;
                    f17 = (float)n8 / 256.0f;
                    f18 = ((float)n8 + 15.99f) / 256.0f;
                    if ((n3 + ++n4 + n5 & 1) == 0) {
                        d7.a(f28, (float)n4 + -0.2f, n5, f16, f17);
                        d7.a(f27, n4, n5, f16, f18);
                        d7.a(f27, n4, n5 + 1, f15, f18);
                        d7.a(f28, (float)n4 + -0.2f, n5 + 1, f15, f17);
                        f15 = (float)n7 / 256.0f;
                        f16 = ((float)n7 + 15.99f) / 256.0f;
                        f17 = (float)(n8 + 16) / 256.0f;
                        f18 = ((float)n8 + 15.99f + 16.0f) / 256.0f;
                        d7.a(f27, (float)n4 + -0.2f, n5 + 1, f16, f17);
                        d7.a(f28, n4, n5 + 1, f16, f18);
                        d7.a(f28, n4, n5, f15, f18);
                        d7.a(f27, (float)n4 + -0.2f, n5, f15, f17);
                    } else {
                        d7.a(n3, (float)n4 + -0.2f, f29, f16, f17);
                        d7.a(n3, n4, f30, f16, f18);
                        d7.a(n3 + 1, n4, f30, f15, f18);
                        d7.a(n3 + 1, (float)n4 + -0.2f, f29, f15, f17);
                        f15 = (float)n7 / 256.0f;
                        f16 = ((float)n7 + 15.99f) / 256.0f;
                        f17 = (float)(n8 + 16) / 256.0f;
                        f18 = ((float)n8 + 15.99f + 16.0f) / 256.0f;
                        d7.a(n3 + 1, (float)n4 + -0.2f, f30, f16, f17);
                        d7.a(n3 + 1, n4, f29, f16, f18);
                        d7.a(n3, n4, f29, f15, f18);
                        d7.a(n3, (float)n4 + -0.2f, f30, f15, f17);
                    }
                }
            }
            return true;
        }
        if (n5 == 5) {
            n5 = n4;
            n4 = n3;
            n3 = n2;
            Object object5 = object;
            object = this;
            d d8 = d.a;
            int n9 = ((x)object5).a(0);
            if (((f)object).b >= 0) {
                n9 = ((f)object).b;
            }
            float f31 = ((x)object5).f(((f)object).a, n3, n4, n5);
            d8.a(f31, f31, f31);
            int n10 = ((n9 & 0xF) << 4) + 16;
            int n11 = (n9 & 0xF) << 4;
            int n12 = n9 & 0xF0;
            if ((n3 + n4 + n5 & 1) == 1) {
                n10 = (n9 & 0xF) << 4;
                n11 = ((n9 & 0xF) << 4) + 16;
            }
            float f32 = (float)n10 / 256.0f;
            float f33 = ((float)n10 + 15.99f) / 256.0f;
            float f34 = (float)n12 / 256.0f;
            float f35 = ((float)n12 + 15.99f) / 256.0f;
            float f36 = (float)n11 / 256.0f;
            float f37 = ((float)n11 + 15.99f) / 256.0f;
            float f38 = (float)n12 / 256.0f;
            float f39 = ((float)n12 + 15.99f) / 256.0f;
            if (((f)object).a.b(n3 - 1, n4, n5)) {
                d8.a((float)n3 + 0.05f, (float)(n4 + 1) + 0.125f, (float)(n5 + 1) + 0.125f, f32, f34);
                d8.a((float)n3 + 0.05f, (float)n4 - 0.125f, (float)(n5 + 1) + 0.125f, f32, f35);
                d8.a((float)n3 + 0.05f, (float)n4 - 0.125f, (float)n5 - 0.125f, f33, f35);
                d8.a((float)n3 + 0.05f, (float)(n4 + 1) + 0.125f, (float)n5 - 0.125f, f33, f34);
            }
            if (((f)object).a.b(n3 + 1, n4, n5)) {
                d8.a((float)(n3 + 1) - 0.05f, (float)n4 - 0.125f, (float)(n5 + 1) + 0.125f, f33, f35);
                d8.a((float)(n3 + 1) - 0.05f, (float)(n4 + 1) + 0.125f, (float)(n5 + 1) + 0.125f, f33, f34);
                d8.a((float)(n3 + 1) - 0.05f, (float)(n4 + 1) + 0.125f, (float)n5 - 0.125f, f32, f34);
                d8.a((float)(n3 + 1) - 0.05f, (float)n4 - 0.125f, (float)n5 - 0.125f, f32, f35);
            }
            if (((f)object).a.b(n3, n4, n5 - 1)) {
                d8.a((float)(n3 + 1) + 0.125f, (float)n4 - 0.125f, (float)n5 + 0.05f, f37, f39);
                d8.a((float)(n3 + 1) + 0.125f, (float)(n4 + 1) + 0.125f, (float)n5 + 0.05f, f37, f38);
                d8.a((float)n3 - 0.125f, (float)(n4 + 1) + 0.125f, (float)n5 + 0.05f, f36, f38);
                d8.a((float)n3 - 0.125f, (float)n4 - 0.125f, (float)n5 + 0.05f, f36, f39);
            }
            if (((f)object).a.b(n3, n4, n5 + 1)) {
                d8.a((float)(n3 + 1) + 0.125f, (float)(n4 + 1) + 0.125f, (float)(n5 + 1) - 0.05f, f36, f38);
                d8.a((float)(n3 + 1) + 0.125f, (float)n4 - 0.125f, (float)(n5 + 1) - 0.05f, f36, f39);
                d8.a((float)n3 - 0.125f, (float)n4 - 0.125f, (float)(n5 + 1) - 0.05f, f37, f39);
                d8.a((float)n3 - 0.125f, (float)(n4 + 1) + 0.125f, (float)(n5 + 1) - 0.05f, f37, f38);
            }
            return true;
        }
        return false;
    }

    private void a(x x2, float f2, float f3, float f4, float f5, float f6) {
        d d2 = d.a;
        int n2 = x2.a(0);
        if (this.b >= 0) {
            n2 = this.b;
        }
        int n3 = (n2 & 0xF) << 4;
        float f7 = (float)n3 / 256.0f;
        float f8 = ((float)n3 + 15.99f) / 256.0f;
        float f9 = (float)(n2 &= 0xF0) / 256.0f;
        float f10 = ((float)n2 + 15.99f) / 256.0f;
        float f11 = f7 + 0.02734375f;
        float f12 = f9 + 0.0234375f;
        float f13 = f7 + 0.03515625f;
        float f14 = f9 + 0.03125f;
        float f15 = (f2 += 0.5f) - 0.5f;
        float f16 = f2 + 0.5f;
        float f17 = (f4 += 0.5f) - 0.5f;
        float f18 = f4 + 0.5f;
        d2.a(f2 + f5 * 0.375f - 0.0625f, f3 + 0.625f, f4 + f6 * 0.375f - 0.0625f, f11, f12);
        d2.a(f2 + f5 * 0.375f - 0.0625f, f3 + 0.625f, f4 + f6 * 0.375f + 0.0625f, f11, f14);
        d2.a(f2 + f5 * 0.375f + 0.0625f, f3 + 0.625f, f4 + f6 * 0.375f + 0.0625f, f13, f14);
        d2.a(f2 + f5 * 0.375f + 0.0625f, f3 + 0.625f, f4 + f6 * 0.375f - 0.0625f, f13, f12);
        d2.a(f2 - 0.0625f, f3 + 1.0f, f17, f7, f9);
        d2.a(f2 - 0.0625f + f5, f3, f17 + f6, f7, f10);
        d2.a(f2 - 0.0625f + f5, f3, f18 + f6, f8, f10);
        d2.a(f2 - 0.0625f, f3 + 1.0f, f18, f8, f9);
        d2.a(f2 + 0.0625f, f3 + 1.0f, f18, f7, f9);
        d2.a(f2 + f5 + 0.0625f, f3, f18 + f6, f7, f10);
        d2.a(f2 + f5 + 0.0625f, f3, f17 + f6, f8, f10);
        d2.a(f2 + 0.0625f, f3 + 1.0f, f17, f8, f9);
        d2.a(f15, f3 + 1.0f, f4 + 0.0625f, f7, f9);
        d2.a(f15 + f5, f3, f4 + 0.0625f + f6, f7, f10);
        d2.a(f16 + f5, f3, f4 + 0.0625f + f6, f8, f10);
        d2.a(f16, f3 + 1.0f, f4 + 0.0625f, f8, f9);
        d2.a(f16, f3 + 1.0f, f4 - 0.0625f, f7, f9);
        d2.a(f16 + f5, f3, f4 - 0.0625f + f6, f7, f10);
        d2.a(f15 + f5, f3, f4 - 0.0625f + f6, f8, f10);
        d2.a(f15, f3 + 1.0f, f4 - 0.0625f, f8, f9);
    }

    private void a(x x2, int n2, float f2, float f3, float f4) {
        d d2 = d.a;
        int n3 = x2.a(0, n2);
        if (this.b >= 0) {
            n3 = this.b;
        }
        n2 = (n3 & 0xF) << 4;
        float f5 = (float)n2 / 256.0f;
        float f6 = ((float)n2 + 15.99f) / 256.0f;
        float f7 = (float)(n3 &= 0xF0) / 256.0f;
        float f8 = ((float)n3 + 15.99f) / 256.0f;
        float f9 = f2 + 0.5f - 0.45f;
        f2 = f2 + 0.5f + 0.45f;
        float f10 = f4 + 0.5f - 0.45f;
        f4 = f4 + 0.5f + 0.45f;
        d2.a(f9, f3 + 1.0f, f10, f5, f7);
        d2.a(f9, f3, f10, f5, f8);
        d2.a(f2, f3, f4, f6, f8);
        d2.a(f2, f3 + 1.0f, f4, f6, f7);
        d2.a(f2, f3 + 1.0f, f4, f5, f7);
        d2.a(f2, f3, f4, f5, f8);
        d2.a(f9, f3, f10, f6, f8);
        d2.a(f9, f3 + 1.0f, f10, f6, f7);
        d2.a(f9, f3 + 1.0f, f4, f5, f7);
        d2.a(f9, f3, f4, f5, f8);
        d2.a(f2, f3, f10, f6, f8);
        d2.a(f2, f3 + 1.0f, f10, f6, f7);
        d2.a(f2, f3 + 1.0f, f10, f5, f7);
        d2.a(f2, f3, f10, f5, f8);
        d2.a(f9, f3, f4, f6, f8);
        d2.a(f9, f3 + 1.0f, f4, f6, f7);
    }

    private void b(x x2, int n2, float f2, float f3, float f4) {
        d d2 = d.a;
        int n3 = x2.a(0, n2);
        if (this.b >= 0) {
            n3 = this.b;
        }
        n2 = (n3 & 0xF) << 4;
        float f5 = (float)n2 / 256.0f;
        float f6 = ((float)n2 + 15.99f) / 256.0f;
        float f7 = (float)(n3 &= 0xF0) / 256.0f;
        float f8 = ((float)n3 + 15.99f) / 256.0f;
        float f9 = f2 + 0.5f - 0.25f;
        float f10 = f2 + 0.5f + 0.25f;
        float f11 = f4 + 0.5f - 0.5f;
        float f12 = f4 + 0.5f + 0.5f;
        d2.a(f9, f3 + 1.0f, f11, f5, f7);
        d2.a(f9, f3, f11, f5, f8);
        d2.a(f9, f3, f12, f6, f8);
        d2.a(f9, f3 + 1.0f, f12, f6, f7);
        d2.a(f9, f3 + 1.0f, f12, f5, f7);
        d2.a(f9, f3, f12, f5, f8);
        d2.a(f9, f3, f11, f6, f8);
        d2.a(f9, f3 + 1.0f, f11, f6, f7);
        d2.a(f10, f3 + 1.0f, f12, f5, f7);
        d2.a(f10, f3, f12, f5, f8);
        d2.a(f10, f3, f11, f6, f8);
        d2.a(f10, f3 + 1.0f, f11, f6, f7);
        d2.a(f10, f3 + 1.0f, f11, f5, f7);
        d2.a(f10, f3, f11, f5, f8);
        d2.a(f10, f3, f12, f6, f8);
        d2.a(f10, f3 + 1.0f, f12, f6, f7);
        f9 = f2 + 0.5f - 0.5f;
        f10 = f2 + 0.5f + 0.5f;
        f11 = f4 + 0.5f - 0.25f;
        f12 = f4 + 0.5f + 0.25f;
        d2.a(f9, f3 + 1.0f, f11, f5, f7);
        d2.a(f9, f3, f11, f5, f8);
        d2.a(f10, f3, f11, f6, f8);
        d2.a(f10, f3 + 1.0f, f11, f6, f7);
        d2.a(f10, f3 + 1.0f, f11, f5, f7);
        d2.a(f10, f3, f11, f5, f8);
        d2.a(f9, f3, f11, f6, f8);
        d2.a(f9, f3 + 1.0f, f11, f6, f7);
        d2.a(f10, f3 + 1.0f, f12, f5, f7);
        d2.a(f10, f3, f12, f5, f8);
        d2.a(f9, f3, f12, f6, f8);
        d2.a(f9, f3 + 1.0f, f12, f6, f7);
        d2.a(f9, f3 + 1.0f, f12, f5, f7);
        d2.a(f9, f3, f12, f5, f8);
        d2.a(f10, f3, f12, f6, f8);
        d2.a(f10, f3 + 1.0f, f12, f6, f7);
    }

    private float a(int n2, int n3, int n4) {
        if (this.a.f(n2, n3, n4) != net.minecraft.a.a.d.c.f) {
            return 1.0f;
        }
        return (float)this.a.e(n2, n3, n4) / 9.0f;
    }

    private void a(x x2, float f2, float f3, float f4, int n2) {
        d d2 = d.a;
        if (this.b >= 0) {
            n2 = this.b;
        }
        int n3 = (n2 & 0xF) << 4;
        float f5 = (float)n3 / 256.0f;
        float f6 = ((float)n3 + 15.99f) / 256.0f;
        float f7 = (float)(n2 &= 0xF0) / 256.0f;
        float f8 = ((float)n2 + 15.99f) / 256.0f;
        float f9 = f2 + x2.au;
        float f10 = f4 + x2.aw;
        float f11 = f4 + x2.az;
        d2.a(f9, f3 += x2.av, f11, f5, f8);
        d2.a(f9, f3, f10, f5, f7);
        d2.a(f2 += x2.ax, f3, f10, f6, f7);
        d2.a(f2, f3, f11, f6, f8);
    }

    private void b(x x2, float f2, float f3, float f4, int n2) {
        d d2 = d.a;
        if (this.b >= 0) {
            n2 = this.b;
        }
        int n3 = (n2 & 0xF) << 4;
        float f5 = (float)n3 / 256.0f;
        float f6 = ((float)n3 + 15.99f) / 256.0f;
        float f7 = (float)(n2 &= 0xF0) / 256.0f;
        float f8 = ((float)n2 + 15.99f) / 256.0f;
        float f9 = f2 + x2.au;
        float f10 = f4 + x2.aw;
        float f11 = f4 + x2.az;
        d2.a(f2 += x2.ax, f3 += x2.ay, f11, f6, f8);
        d2.a(f2, f3, f10, f6, f7);
        d2.a(f9, f3, f10, f5, f7);
        d2.a(f9, f3, f11, f5, f8);
    }

    private void b(x x2, int n2, int n3, int n4, int n5) {
        float f2;
        float f3;
        d d2 = d.a;
        if (this.b >= 0) {
            n5 = this.b;
        }
        int n6 = (n5 & 0xF) << 4;
        n5 &= 0xF0;
        float f4 = (float)n6 / 256.0f;
        float f5 = ((float)n6 + 15.99f) / 256.0f;
        if (x2.av < 0.0f || x2.ay > 1.0f) {
            f3 = (float)n5 / 256.0f;
            f2 = ((float)n5 + 15.99f) / 256.0f;
        } else {
            f3 = ((float)n5 + x2.av * 15.99f) / 256.0f;
            f2 = ((float)n5 + x2.ay * 15.99f) / 256.0f;
        }
        float f6 = (float)n2 + x2.au;
        float f7 = (float)n2 + x2.ax;
        float f8 = (float)n3 + x2.av;
        float f9 = (float)n3 + x2.ay;
        float f10 = (float)n4 + x2.aw;
        d2.a(f6, f9, f10, f5, f3);
        d2.a(f7, f9, f10, f4, f3);
        d2.a(f7, f8, f10, f4, f2);
        d2.a(f6, f8, f10, f5, f2);
    }

    private void c(x x2, int n2, int n3, int n4, int n5) {
        float f2;
        float f3;
        d d2 = d.a;
        if (this.b >= 0) {
            n5 = this.b;
        }
        int n6 = (n5 & 0xF) << 4;
        n5 &= 0xF0;
        float f4 = (float)n6 / 256.0f;
        float f5 = ((float)n6 + 15.99f) / 256.0f;
        if (x2.av < 0.0f || x2.ay > 1.0f) {
            f3 = (float)n5 / 256.0f;
            f2 = ((float)n5 + 15.99f) / 256.0f;
        } else {
            f3 = ((float)n5 + x2.av * 15.99f) / 256.0f;
            f2 = ((float)n5 + x2.ay * 15.99f) / 256.0f;
        }
        float f6 = (float)n2 + x2.au;
        float f7 = (float)n2 + x2.ax;
        float f8 = (float)n3 + x2.av;
        float f9 = (float)n3 + x2.ay;
        float f10 = (float)n4 + x2.az;
        d2.a(f6, f9, f10, f4, f3);
        d2.a(f6, f8, f10, f4, f2);
        d2.a(f7, f8, f10, f5, f2);
        d2.a(f7, f9, f10, f5, f3);
    }

    private void d(x x2, int n2, int n3, int n4, int n5) {
        float f2;
        float f3;
        d d2 = d.a;
        if (this.b >= 0) {
            n5 = this.b;
        }
        int n6 = (n5 & 0xF) << 4;
        n5 &= 0xF0;
        float f4 = (float)n6 / 256.0f;
        float f5 = ((float)n6 + 15.99f) / 256.0f;
        if (x2.av < 0.0f || x2.ay > 1.0f) {
            f3 = (float)n5 / 256.0f;
            f2 = ((float)n5 + 15.99f) / 256.0f;
        } else {
            f3 = ((float)n5 + x2.av * 15.99f) / 256.0f;
            f2 = ((float)n5 + x2.ay * 15.99f) / 256.0f;
        }
        float f6 = (float)n2 + x2.au;
        float f7 = (float)n3 + x2.av;
        float f8 = (float)n3 + x2.ay;
        float f9 = (float)n4 + x2.aw;
        float f10 = (float)n4 + x2.az;
        d2.a(f6, f8, f10, f5, f3);
        d2.a(f6, f8, f9, f4, f3);
        d2.a(f6, f7, f9, f4, f2);
        d2.a(f6, f7, f10, f5, f2);
    }

    private void e(x x2, int n2, int n3, int n4, int n5) {
        float f2;
        float f3;
        d d2 = d.a;
        if (this.b >= 0) {
            n5 = this.b;
        }
        int n6 = (n5 & 0xF) << 4;
        n5 &= 0xF0;
        float f4 = (float)n6 / 256.0f;
        float f5 = ((float)n6 + 15.99f) / 256.0f;
        if (x2.av < 0.0f || x2.ay > 1.0f) {
            f3 = (float)n5 / 256.0f;
            f2 = ((float)n5 + 15.99f) / 256.0f;
        } else {
            f3 = ((float)n5 + x2.av * 15.99f) / 256.0f;
            f2 = ((float)n5 + x2.ay * 15.99f) / 256.0f;
        }
        float f6 = (float)n2 + x2.ax;
        float f7 = (float)n3 + x2.av;
        float f8 = (float)n3 + x2.ay;
        float f9 = (float)n4 + x2.aw;
        float f10 = (float)n4 + x2.az;
        d2.a(f6, f7, f10, f4, f2);
        d2.a(f6, f7, f9, f5, f2);
        d2.a(f6, f8, f9, f5, f3);
        d2.a(f6, f8, f10, f4, f3);
    }

    public final void a(x x2) {
        d d2 = d.a;
        int n2 = x2.a();
        if (n2 == 0) {
            GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)-0.5f);
            d2.b();
            d.c(0.0f, -1.0f, 0.0f);
            this.a(x2, 0.0f, 0.0f, 0.0f, x2.a(0));
            d2.a();
            d2.b();
            d.c(0.0f, 1.0f, 0.0f);
            this.b(x2, 0.0f, 0.0f, 0.0f, x2.a(1));
            d2.a();
            d2.b();
            d.c(0.0f, 0.0f, -1.0f);
            this.b(x2, 0, 0, 0, x2.a(2));
            d2.a();
            d2.b();
            d.c(0.0f, 0.0f, 1.0f);
            this.c(x2, 0, 0, 0, x2.a(3));
            d2.a();
            d2.b();
            d.c(-1.0f, 0.0f, 0.0f);
            this.d(x2, 0, 0, 0, x2.a(4));
            d2.a();
            d2.b();
            d.c(1.0f, 0.0f, 0.0f);
            this.e(x2, 0, 0, 0, x2.a(5));
            d2.a();
            GL11.glTranslatef((float)0.5f, (float)0.5f, (float)0.5f);
            return;
        }
        if (n2 == 1) {
            d2.b();
            d.c(0.0f, -1.0f, 0.0f);
            this.a(x2, -1, -0.5f, -0.5f, -0.5f);
            d2.a();
            return;
        }
        if (n2 == 6) {
            d2.b();
            d.c(0.0f, -1.0f, 0.0f);
            this.b(x2, -1, -0.5f, -0.5f, -0.5f);
            d2.a();
            return;
        }
        if (n2 == 2) {
            d2.b();
            d.c(0.0f, -1.0f, 0.0f);
            this.a(x2, -0.5f, -0.5f, -0.5f, 0.0f, 0.0f);
            d2.a();
        }
    }
}

