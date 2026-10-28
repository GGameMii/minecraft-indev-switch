/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client.a.a;

import java.util.Random;
import net.minecraft.a.a.b.x;
import net.minecraft.client.a.d;
import net.minecraft.client.a.f;
import net.minecraft.client.a.k;
import net.minecraft.client.c.j;
import org.lwjgl.opengl.GL11;

public final class e
extends net.minecraft.client.a.a.f {
    private f d = new f();
    private Random e = new Random();

    public e() {
        this.b = 0.15f;
        this.c = 0.75f;
    }

    public final void a(k object, net.minecraft.a.b.k k2, int n2, int n3) {
        if (k2 == null) {
            return;
        }
        if (k2.c < 256 && x.c[k2.c].a() == 0) {
            int n4 = k2.c;
            k.a(((k)object).a("/terrain.png"));
            object = x.c[n4];
            GL11.glPushMatrix();
            GL11.glTranslatef((float)(n2 - 2), (float)(n3 + 3), (float)0.0f);
            GL11.glScalef((float)10.0f, (float)10.0f, (float)10.0f);
            GL11.glTranslatef((float)1.0f, (float)0.5f, (float)8.0f);
            GL11.glRotatef((float)210.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            this.d.a((x)object);
            GL11.glPopMatrix();
            return;
        }
        Object object2 = k2;
        if (((net.minecraft.a.b.k)object2).a().b() >= 0) {
            GL11.glDisable((int)2896);
            if (k2.c < 256) {
                k.a(((k)object).a("/terrain.png"));
            } else {
                k.a(((k)object).a("/gui/items.png"));
            }
            int n5 = n2;
            int n6 = n3;
            net.minecraft.a.b.k k3 = k2;
            object2 = k3;
            object2 = k2;
            int n7 = 16;
            n7 = 16;
            n3 = ((net.minecraft.a.b.k)object2).a().b() / 16 << 4;
            n2 = k3.a().b() % 16 << 4;
            int n8 = n6;
            n7 = n5;
            object2 = net.minecraft.client.a.d.a;
            ((d)object2).b();
            ((d)object2).a(n7, n8 + 16, 0.0f, (float)n2 * 0.00390625f, (float)(n3 + 16) * 0.00390625f);
            ((d)object2).a(n7 + 16, n8 + 16, 0.0f, (float)(n2 + 16) * 0.00390625f, (float)(n3 + 16) * 0.00390625f);
            ((d)object2).a(n7 + 16, n8, 0.0f, (float)(n2 + 16) * 0.00390625f, (float)n3 * 0.00390625f);
            ((d)object2).a(n7, n8, 0.0f, (float)n2 * 0.00390625f, (float)n3 * 0.00390625f);
            ((d)object2).a();
            GL11.glEnable((int)2896);
        }
    }

    public final void a(j j2, net.minecraft.a.b.k object, int n2, int n3) {
        if (object == null) {
            return;
        }
        if (((net.minecraft.a.b.k)object).a > 1) {
            String string = "" + ((net.minecraft.a.b.k)object).a;
            GL11.glDisable((int)2896);
            GL11.glDisable((int)2929);
            j2.a(string, n2 + 19 - 2 - j2.a(string), n3 + 6 + 3, 0xFFFFFF);
            GL11.glEnable((int)2896);
            GL11.glEnable((int)2929);
        }
        if (((net.minecraft.a.b.k)object).d > 0) {
            int n4 = 13 - ((net.minecraft.a.b.k)object).d * 13 / ((net.minecraft.a.b.k)object).b();
            int n5 = 255 - ((net.minecraft.a.b.k)object).d * 255 / ((net.minecraft.a.b.k)object).b();
            GL11.glDisable((int)2896);
            GL11.glDisable((int)2929);
            GL11.glDisable((int)3553);
            object = net.minecraft.client.a.d.a;
            int n6 = 255 - n5 << 16 | n5 << 8;
            n5 = (255 - n5) / 4 << 16 | 0x3F00;
            net.minecraft.client.a.a.e.a((d)object, n2 + 2, n3 + 13, 13, 2, 0);
            net.minecraft.client.a.a.e.a((d)object, n2 + 2, n3 + 13, 12, 1, n5);
            net.minecraft.client.a.a.e.a((d)object, n2 + 2, n3 + 13, n4, 1, n6);
            GL11.glEnable((int)3553);
            GL11.glEnable((int)2896);
            GL11.glEnable((int)2929);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        }
    }

    private static void a(d d2, int n2, int n3, int n4, int n5, int n6) {
        d2.b();
        d2.b(n6);
        d2.b(n2, n3, 0.0f);
        d2.b(n2, n3 + n5, 0.0f);
        d2.b(n2 + n4, n3 + n5, 0.0f);
        d2.b(n2 + n4, n3, 0.0f);
        d2.a();
    }
}

