/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.Display
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client;

import a.b;
import com.a.a.c;
import com.a.a.j;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import net.minecraft.client.a.d;
import net.minecraft.client.c.h;
import net.minecraft.client.p;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

public class a
implements b {
    private String a;
    private net.minecraft.client.d b;
    private String c;
    private long d;

    public a(net.minecraft.client.d d2) {
        this.a = "";
        this.c = "";
        this.d = System.currentTimeMillis();
        this.b = d2;
    }

    public final void a(String object) {
        if (!this.b.A) {
            throw new p();
        }
        this.c = object;
        object = new h(this.b.b, this.b.c);
        int n2 = ((h)object).a();
        int n3 = ((h)object).b();
        GL11.glClear((int)256);
        GL11.glMatrixMode((int)5889);
        GL11.glLoadIdentity();
        GL11.glOrtho((double)0.0, (double)n2, (double)n3, (double)0.0, (double)100.0, (double)300.0);
        GL11.glMatrixMode((int)5888);
        GL11.glLoadIdentity();
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-200.0f);
    }

    public final void b(String string) {
        if (!this.b.A) {
            throw new p();
        }
        this.d = 0L;
        this.a = string;
        this.a(-1);
        this.d = 0L;
    }

    public final void a(int n2) {
        if (!this.b.A) {
            throw new p();
        }
        long l2 = System.currentTimeMillis();
        if (l2 - this.d < 20L) {
            return;
        }
        this.d = l2;
        h h2 = new h(this.b.b, this.b.c);
        int n3 = h2.a();
        int n4 = h2.b();
        GL11.glClear((int)256);
        GL11.glMatrixMode((int)5889);
        GL11.glLoadIdentity();
        GL11.glOrtho((double)0.0, (double)n3, (double)n4, (double)0.0, (double)100.0, (double)300.0);
        GL11.glMatrixMode((int)5888);
        GL11.glLoadIdentity();
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-200.0f);
        GL11.glClear((int)16640);
        d d2 = net.minecraft.client.a.d.a;
        int n5 = this.b.m.a("/dirt.png");
        GL11.glBindTexture((int)3553, (int)n5);
        d2.b();
        d2.b(0x404040);
        d2.a(0.0f, n4, 0.0f, 0.0f, (float)n4 / 32.0f);
        d2.a(n3, n4, 0.0f, (float)n3 / 32.0f, (float)n4 / 32.0f);
        d2.a(n3, 0.0f, 0.0f, (float)n3 / 32.0f, 0.0f);
        d2.a(0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        d2.a();
        if (n2 >= 0) {
            n5 = n3 / 2 - 50;
            int n6 = n4 / 2 + 16;
            GL11.glDisable((int)3553);
            d2.b();
            d2.b(0x808080);
            d2.b(n5, n6, 0.0f);
            d2.b(n5, n6 + 2, 0.0f);
            d2.b(n5 + 100, n6 + 2, 0.0f);
            d2.b(n5 + 100, n6, 0.0f);
            d2.b(0x80FF80);
            d2.b(n5, n6, 0.0f);
            d2.b(n5, n6 + 2, 0.0f);
            d2.b(n5 + n2, n6 + 2, 0.0f);
            d2.b(n5 + n2, n6, 0.0f);
            d2.a();
            GL11.glEnable((int)3553);
        }
        this.b.n.a(this.c, (n3 - this.b.n.a(this.c)) / 2, n4 / 2 - 4 - 16, 0xFFFFFF);
        this.b.n.a(this.a, (n3 - this.b.n.a(this.a)) / 2, n4 / 2 - 4 + 8, 0xFFFFFF);
        Display.update();
        try {
            Thread.yield();
            return;
        }
        catch (Exception exception) {
            return;
        }
    }

    public a() {
    }

    public static c a(InputStream inputStream) {
        inputStream = new DataInputStream(new GZIPInputStream(inputStream));
        try {
            Object object = inputStream;
            object = j.b((DataInput)object);
            if (!(object instanceof c)) {
                throw new IOException("Root tag must be a named compound tag");
            }
            object = (c)object;
            return object;
        }
        finally {
            ((FilterInputStream)inputStream).close();
        }
    }
}

