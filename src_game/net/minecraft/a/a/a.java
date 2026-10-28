/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.a;

import com.a.a.h;
import com.a.a.j;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.FilterOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;
import net.minecraft.a.a.b.a.b;
import net.minecraft.a.a.b.a.c;
import net.minecraft.a.a.b.x;
import net.minecraft.a.a.g;
import net.minecraft.a.c.a.d;
import net.minecraft.a.c.a.f;

public abstract class a {
    private a.b a;

    public a(a.b b2) {
        this.a = b2;
    }

    public final g a(InputStream object) {
        if (this.a != null) {
            this.a.a("Loading level");
        }
        if (this.a != null) {
            this.a.b("Reading..");
        }
        object = net.minecraft.client.a.a((InputStream)object);
        com.a.a.c c2 = ((com.a.a.c)object).i("About");
        com.a.a.c c3 = ((com.a.a.c)object).i("Map");
        Object object2 = ((com.a.a.c)object).i("Environment");
        Object object3 = ((com.a.a.c)object).j("Entities");
        int n2 = c3.c("Width");
        int n3 = c3.c("Length");
        short s2 = c3.c("Height");
        g g2 = new g();
        if (this.a != null) {
            this.a.b("Preparing level..");
        }
        com.a.a.f f2 = c3.j("Spawn");
        g2.i = ((h)f2.a((int)0)).a;
        g2.j = ((h)f2.a((int)1)).a;
        g2.k = ((h)f2.a((int)2)).a;
        g2.g = c2.g("Author");
        g2.f = c2.g("Name");
        g2.h = c2.e("CreatedOn");
        g2.x = ((com.a.a.c)object2).d("CloudColor");
        g2.v = ((com.a.a.c)object2).d("SkyColor");
        g2.w = ((com.a.a.c)object2).d("FogColor");
        g2.A = ((com.a.a.c)object2).b("SkyBrightness");
        if (g2.A < 0) {
            g2.A = 0;
        }
        if (g2.A > 16) {
            g2.A = g2.A * 15 / 100;
        }
        g2.u = ((com.a.a.c)object2).c("CloudHeight");
        g2.t = ((com.a.a.c)object2).c("SurroundingGroundHeight");
        g2.s = ((com.a.a.c)object2).c("SurroundingWaterHeight");
        g2.m = ((com.a.a.c)object2).b("SurroundingWaterType");
        g2.D = ((com.a.a.c)object2).c("TimeOfDay");
        g2.B = g2.e();
        g2.a(n2, (int)s2, n3, c3.h("Blocks"), c3.h("Data"));
        if (this.a != null) {
            this.a.b("Preparing entities..");
        }
        for (int i2 = 0; i2 < ((com.a.a.f)object3).b(); ++i2) {
            try {
                c3 = (com.a.a.c)((com.a.a.f)object3).a(i2);
                object2 = c3.g("id");
                net.minecraft.a.c.b b2 = this.a(g2, (String)object2);
                if (b2 != null) {
                    b2.d(c3);
                    g2.a(b2);
                    continue;
                }
                System.out.println("Skipping unknown entity id \"" + (String)object2 + "\"");
                continue;
            }
            catch (Exception exception) {
                System.out.println("Error reading entity");
                exception.printStackTrace();
            }
        }
        com.a.a.f f3 = ((com.a.a.c)object).j("TileEntities");
        for (int i3 = 0; i3 < f3.b(); ++i3) {
            try {
                object2 = (com.a.a.c)f3.a(i3);
                n2 = ((com.a.a.c)object2).d("Pos");
                object3 = object = ((com.a.a.c)object2).g("id");
                if ((((String)object).equals("Chest") ? new c() : (object3 = ((String)object3).equals("Furnace") ? new b() : null)) != null) {
                    int n4 = n2 % 1024;
                    n3 = (n2 >> 10) % 1024;
                    n2 = (n2 >> 20) % 1024;
                    ((net.minecraft.a.a.b.a.a)object3).a((com.a.a.c)object2);
                    g2.a(n4, n3, n2, (net.minecraft.a.a.b.a.a)object3);
                    continue;
                }
                System.out.println("Skipping unknown tile entity id \"" + (String)object + "\"");
                continue;
            }
            catch (Exception exception) {
                System.out.println("Error reading tileentity");
                exception.printStackTrace();
            }
        }
        return g2;
    }

    protected net.minecraft.a.c.b a(g g2, String string) {
        if (string.equals("Pig")) {
            return new net.minecraft.a.c.b.c(g2);
        }
        if (string.equals("Sheep")) {
            return new net.minecraft.a.c.b.b(g2);
        }
        if (string.equals("Creeper")) {
            return new d(g2);
        }
        if (string.equals("Skeleton")) {
            return new net.minecraft.a.c.a.c(g2);
        }
        if (string.equals("Spider")) {
            return new net.minecraft.a.c.a.b(g2);
        }
        if (string.equals("Zombie")) {
            return new f(g2);
        }
        if (string.equals("Giant")) {
            return new net.minecraft.a.c.a.a(g2);
        }
        if (string.equals("Item")) {
            return new net.minecraft.a.c.c.b(g2);
        }
        if (string.equals("Painting")) {
            return new net.minecraft.a.c.a(g2);
        }
        return null;
    }

    public final void a(g object, OutputStream outputStream) {
        Object object2;
        Object object32;
        if (this.a != null) {
            this.a.a("Saving level");
        }
        if (this.a != null) {
            this.a.b("Preparing level..");
        }
        Object object4 = new com.a.a.c();
        ((com.a.a.c)object4).a("CloudColor", ((g)object).x);
        ((com.a.a.c)object4).a("SkyColor", ((g)object).v);
        ((com.a.a.c)object4).a("FogColor", ((g)object).w);
        ((com.a.a.c)object4).a("SkyBrightness", (byte)((g)object).A);
        ((com.a.a.c)object4).a("CloudHeight", (short)((g)object).u);
        ((com.a.a.c)object4).a("SurroundingGroundHeight", (short)((g)object).t);
        ((com.a.a.c)object4).a("SurroundingWaterHeight", (short)((g)object).s);
        ((com.a.a.c)object4).a("SurroundingGroundType", (byte)x.j.at);
        ((com.a.a.c)object4).a("SurroundingWaterType", (byte)((g)object).m);
        ((com.a.a.c)object4).a("TimeOfDay", (short)((g)object).D);
        com.a.a.c c2 = new com.a.a.c();
        c2.a("Width", (short)((g)object).a);
        c2.a("Length", (short)((g)object).b);
        c2.a("Height", (short)((g)object).c);
        c2.a("Blocks", ((g)object).d);
        c2.a("Data", ((g)object).e);
        j j2 = new com.a.a.f();
        ((com.a.a.f)j2).a(new h((short)((g)object).i));
        ((com.a.a.f)j2).a(new h((short)((g)object).j));
        ((com.a.a.f)j2).a(new h((short)((g)object).k));
        c2.a("Spawn", j2);
        j2 = new com.a.a.c();
        ((com.a.a.c)j2).a("Author", ((g)object).g);
        ((com.a.a.c)j2).a("Name", ((g)object).f);
        ((com.a.a.c)j2).a("CreatedOn", ((g)object).h);
        if (this.a != null) {
            this.a.b("Preparing entities..");
        }
        com.a.a.f f2 = new com.a.a.f();
        for (Object object32 : ((g)object).r.e) {
            object2 = new com.a.a.c();
            ((net.minecraft.a.c.b)object32).c((com.a.a.c)object2);
            if (((com.a.a.c)object2).b()) continue;
            f2.a((j)object2);
        }
        com.a.a.f f3 = new com.a.a.f();
        object32 = ((g)object).o.keySet().iterator();
        while (object32.hasNext()) {
            int n2 = (Integer)object32.next();
            com.a.a.c c3 = new com.a.a.c();
            c3.a("Pos", n2);
            object2 = (net.minecraft.a.a.b.a.a)((g)object).o.get(n2);
            ((net.minecraft.a.a.b.a.a)object2).b(c3);
            f3.a(c3);
        }
        object32 = new com.a.a.c();
        ((j)object32).l("MinecraftLevel");
        ((com.a.a.c)object32).a("About", (com.a.a.c)j2);
        ((com.a.a.c)object32).a("Map", c2);
        ((com.a.a.c)object32).a("Environment", (com.a.a.c)object4);
        ((com.a.a.c)object32).a("Entities", f2);
        ((com.a.a.c)object32).a("TileEntities", f3);
        if (this.a != null) {
            this.a.b("Writing..");
        }
        object = object32;
        outputStream = new DataOutputStream(new GZIPOutputStream(outputStream));
        try {
            object4 = outputStream;
            j.a((j)object, (DataOutput)object4);
            return;
        }
        finally {
            ((FilterOutputStream)outputStream).close();
        }
    }
}

