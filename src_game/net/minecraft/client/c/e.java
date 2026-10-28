/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.c;

import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import net.minecraft.a.a.g;
import net.minecraft.client.c.f;
import net.minecraft.client.c.i;
import net.minecraft.client.c.r;

public class e
extends i
implements Runnable {
    private i i;
    private boolean j = false;
    private boolean k = false;
    private String[] l = null;
    private String m = "";
    protected String a = "Load level";
    private boolean n = false;
    private File o;

    public e(i i2) {
        this.i = i2;
    }

    public final void f_() {
        if (this.o != null) {
            if (!this.o.getName().endsWith(".mclevel")) {
                this.o = new File(this.o.getAbsolutePath() + ".mclevel");
            }
            this.a(this.o);
            this.o = null;
            this.b.a((i)null);
        }
    }

    public void run() {
        try {
            this.m = "Getting level list..";
            Object object = new URL("http://" + this.b.i + "/listmaps.jsp?user=" + this.b.h.b);
            object = new BufferedReader(new InputStreamReader(((URL)object).openConnection().getInputStream()));
            this.l = ((BufferedReader)object).readLine().split(";");
            if (this.l.length >= 5) {
                this.a(this.l);
                this.k = true;
                return;
            }
            this.m = this.l[0];
            this.j = true;
        }
        catch (Exception exception) {
            Exception exception2 = exception;
            exception.printStackTrace();
            this.m = "Failed to load levels";
            this.j = true;
        }
    }

    protected void a(String[] stringArray) {
        for (int i2 = 0; i2 < 5; ++i2) {
            ((r)this.e.get((int)i2)).c = !stringArray[i2].equals("-");
            ((r)this.e.get((int)i2)).a = stringArray[i2];
            ((r)this.e.get((int)i2)).d = true;
        }
        ((r)this.e.get((int)5)).d = true;
    }

    public void b() {
        new Thread(this).start();
        for (int i2 = 0; i2 < 5; ++i2) {
            this.e.add(new r(i2, this.c / 2 - 100, this.d / 6 + i2 * 24, "---"));
            ((r)this.e.get((int)i2)).d = false;
        }
        this.e.add(new r(5, this.c / 2 - 100, this.d / 6 + 120 + 12, "Load file..."));
        this.e.add(new r(6, this.c / 2 - 100, this.d / 6 + 168, "Cancel"));
        ((r)this.e.get((int)5)).d = false;
    }

    protected final void a(r r2) {
        if (this.n) {
            return;
        }
        if (!r2.c) {
            return;
        }
        if (this.k && r2.b < 5) {
            this.a(r2.b);
        }
        if (this.j || this.k && r2.b == 5) {
            this.n = true;
            f f2 = new f(this);
            f2.setDaemon(true);
            f2.start();
        }
        if (this.j || this.k && r2.b == 6) {
            this.b.a(this.i);
        }
    }

    protected FileDialog e() {
        return new FileDialog(null, "Load level", 0);
    }

    protected void a(int n2) {
        this.b.a((i)null);
        this.b.b();
    }

    public final void a(int n2, int n3, float f2) {
        this.h();
        net.minecraft.client.c.e.a(this.g, this.a, this.c / 2, 20, 0xFFFFFF);
        if (!this.k) {
            net.minecraft.client.c.e.a(this.g, this.m, this.c / 2, this.d / 2 - 4, 0xFFFFFF);
        }
        super.a(n2, n3, f2);
    }

    protected void a(File object) {
        try {
            object = new FileInputStream((File)object);
            g g2 = new net.minecraft.client.f(this.b, this.b.p).a((InputStream)object);
            ((FileInputStream)object).close();
            this.b.a(g2);
            return;
        }
        catch (IOException iOException) {
            object = iOException;
            iOException.printStackTrace();
            return;
        }
    }

    static /* synthetic */ File a(e e2, File file) {
        e2.o = file;
        return e2.o;
    }

    static /* synthetic */ boolean a(e e2, boolean bl) {
        e2.n = false;
        return false;
    }
}

