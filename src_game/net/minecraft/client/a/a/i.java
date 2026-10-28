/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client.a.a;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.a.a.a;
import net.minecraft.client.a.a.b;
import net.minecraft.client.a.a.c;
import net.minecraft.client.a.a.d;
import net.minecraft.client.a.a.e;
import net.minecraft.client.a.a.f;
import net.minecraft.client.a.a.g;
import net.minecraft.client.a.a.h;
import net.minecraft.client.a.a.j;
import net.minecraft.client.a.a.k;
import net.minecraft.client.a.a.l;
import net.minecraft.client.a.a.m;
import org.lwjgl.opengl.GL11;

public final class i {
    private Map e = new HashMap();
    public static i a = new i();
    public net.minecraft.client.a.k b;
    public net.minecraft.a.a.g c;
    public float d;
    private float f;
    private float g;
    private float h;

    private i() {
        this.e.put(net.minecraft.a.c.a.b.class, new m());
        this.e.put(net.minecraft.a.c.b.c.class, new j(new net.minecraft.client.b.f(), 0.7f));
        this.e.put(net.minecraft.a.c.b.b.class, new k(new net.minecraft.client.b.a(), new net.minecraft.client.b.d(), 0.7f));
        this.e.put(net.minecraft.a.c.a.d.class, new c());
        this.e.put(net.minecraft.a.c.a.c.class, new j(new net.minecraft.client.b.m(), 0.5f));
        this.e.put(net.minecraft.a.c.a.f.class, new j(new net.minecraft.client.b.g(), 0.5f));
        this.e.put(net.minecraft.a.c.e.a.class, new g());
        this.e.put(net.minecraft.a.c.a.a.class, new l(new net.minecraft.client.b.g(), 0.5f, 6.0f));
        this.e.put(net.minecraft.a.c.e.class, new j(new net.minecraft.client.b.h(), 0.5f));
        this.e.put(net.minecraft.a.c.b.class, new a());
        this.e.put(net.minecraft.a.c.a.class, new h());
        this.e.put(net.minecraft.a.c.d.a.class, new b());
        this.e.put(net.minecraft.a.c.c.b.class, new e());
        this.e.put(net.minecraft.a.c.c.a.class, new d());
        for (f f2 : this.e.values()) {
            f2.a(this);
        }
    }

    public final f a(net.minecraft.a.c.b object) {
        Class<?> clazz = object.getClass();
        object = this;
        f f2 = (f)((i)object).e.get(clazz);
        if (f2 == null && clazz != net.minecraft.a.c.b.class) {
            f2 = (f)((i)object).e.get(clazz.getSuperclass());
            ((i)object).e.put(clazz, f2);
        }
        return f2;
    }

    public final void a(net.minecraft.a.a.g g2, net.minecraft.client.a.k k2, net.minecraft.a.c.e.a a2, float f2) {
        this.c = g2;
        this.b = k2;
        this.d = a2.p + (a2.n - a2.p) * f2;
        this.f = a2.B + (a2.h - a2.B) * f2;
        this.g = a2.C + (a2.i - a2.C) * f2;
        this.h = a2.D + (a2.j - a2.D) * f2;
    }

    public final void a(net.minecraft.a.c.b b2, float f2) {
        float f3 = b2.B + (b2.h - b2.B) * f2;
        float f4 = b2.C + (b2.i - b2.C) * f2;
        float f5 = b2.D + (b2.j - b2.D) * f2;
        float f6 = b2.p + (b2.n - b2.p) * f2;
        float f7 = this.c.c((int)f3, (int)(f4 + b2.c_()), (int)f5);
        GL11.glColor3f((float)f7, (float)f7, (float)f7);
        this.a(b2, f3, f4, f5, f6, f2);
    }

    public final void a(net.minecraft.a.c.b b2, float f2, float f3, float f4, float f5, float f6) {
        f f7 = this.a(b2);
        if (f7 != null) {
            f7.a(b2, f2, f3, f4, f5, f6);
            f7.a(b2, f2, f3, f4, f6);
        }
    }

    public final void a(net.minecraft.a.a.g g2) {
        this.c = g2;
    }

    public final float a(float f2, float f3, float f4) {
        return (f2 -= this.f) * f2 + (f3 -= this.g) * f3 + (f4 -= this.h) * f4;
    }
}

