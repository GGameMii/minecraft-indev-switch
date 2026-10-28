/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.BufferUtils
 *  org.lwjgl.LWJGLException
 *  org.lwjgl.Sys
 *  org.lwjgl.input.Controllers
 *  org.lwjgl.input.Keyboard
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.ContextCapabilities
 *  org.lwjgl.opengl.Display
 *  org.lwjgl.opengl.DisplayMode
 *  org.lwjgl.opengl.GL11
 *  org.lwjgl.opengl.GLContext
 */
package net.minecraft.client;

import java.awt.Canvas;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import javax.swing.JOptionPane;
import net.minecraft.a.a.b.x;
import net.minecraft.client.MinecraftApplet;
import net.minecraft.client.a.c.f;
import net.minecraft.client.a.k;
import net.minecraft.client.b.h;
import net.minecraft.client.d.a;
import net.minecraft.client.d.b;
import net.minecraft.client.d.c;
import net.minecraft.client.e;
import net.minecraft.client.g;
import net.minecraft.client.i;
import net.minecraft.client.j;
import net.minecraft.client.l;
import net.minecraft.client.m;
import net.minecraft.client.n;
import net.minecraft.client.p;
import net.minecraft.client.q;
import net.minecraft.client.r;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Controllers;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.ContextCapabilities;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;

public final class d
implements Runnable {
    public a a = new b(this);
    private boolean E = false;
    public int b;
    public int c;
    private i F;
    private g G = new g(20.0f);
    public net.minecraft.a.a.g d;
    public net.minecraft.client.a.b e;
    public net.minecraft.client.g.a f;
    public net.minecraft.client.f.d g;
    public l h = null;
    public String i;
    public Canvas j;
    public boolean k = true;
    public volatile boolean l = false;
    public k m;
    public net.minecraft.client.c.j n;
    public net.minecraft.client.c.i o = null;
    public net.minecraft.client.a p = new net.minecraft.client.a(this);
    public net.minecraft.client.a.e q = new net.minecraft.client.a.e(this);
    private j H;
    private int I = 0;
    private int J = 0;
    private int K;
    private int L;
    public String r = null;
    public int s = 0;
    public net.minecraft.client.c.l t;
    public boolean u = false;
    public net.minecraft.a.d.c v;
    public q w;
    private MinecraftApplet M;
    public net.minecraft.client.e.c x;
    public m y;
    public File z;
    private String N;
    private f O;
    private net.minecraft.client.a.c.d P;
    volatile boolean A;
    public String B;
    public boolean C;
    private int Q;
    public boolean D;

    public d(Canvas canvas, MinecraftApplet minecraftApplet, int n2, int n3, boolean bl) {
        new h(0.0f);
        this.v = null;
        this.x = new net.minecraft.client.e.c();
        this.N = null;
        this.O = new f();
        this.P = new net.minecraft.client.a.c.d();
        this.A = false;
        this.B = "";
        this.C = false;
        this.Q = 0;
        this.D = false;
        this.K = n2;
        this.L = n3;
        this.E = bl;
        this.M = minecraftApplet;
        new n(this, "Timer hack thread");
        this.j = canvas;
        this.b = n2;
        this.c = n3;
        this.E = bl;
    }

    public final void a(String string, int n2) {
        this.N = string;
    }

    public final void a(net.minecraft.client.c.i i2) {
        if (this.o instanceof net.minecraft.client.c.c) {
            return;
        }
        if (this.o != null) {
            this.o.a();
        }
        if (i2 == null && this.d == null) {
            i2 = new r();
        } else if (i2 == null && this.f.W <= 0) {
            i2 = new net.minecraft.client.c.d();
        }
        this.o = i2;
        if (i2 != null) {
            this.e();
            net.minecraft.client.c.h h2 = new net.minecraft.client.c.h(this.b, this.c);
            int n2 = h2.a();
            int n3 = h2.b();
            i2.a(this, n2, n3);
            this.u = false;
            return;
        }
        this.b();
    }

    public final void a() {
        try {
            if (this.H != null) {
                this.H.a();
            }
        }
        catch (Exception exception) {}
        try {
            this.x.b();
            Mouse.destroy();
            Keyboard.destroy();
            return;
        }
        finally {
            Display.destroy();
        }
    }

    public final void run() {
        Object object;
        this.A = true;
        try {
            Object object2;
            d d2 = this;
            if (d2.j != null) {
                Display.setParent((Canvas)d2.j);
            } else if (d2.E) {
                Display.setFullscreen((boolean)true);
                d2.b = Display.getDisplayMode().getWidth();
                d2.c = Display.getDisplayMode().getHeight();
            } else {
                Display.setDisplayMode((DisplayMode)new DisplayMode(d2.b, d2.c));
            }
            Display.setTitle((String)"Minecraft Minecraft Indev");
            try {
                Display.create();
                System.out.println("LWJGL version: " + Sys.getVersion());
                System.out.println("GL RENDERER: " + GL11.glGetString((int)7937));
                System.out.println("GL VENDOR: " + GL11.glGetString((int)7936));
                System.out.println("GL VERSION: " + GL11.glGetString((int)7938));
                object2 = GLContext.getCapabilities();
                System.out.println("OpenGL 3.0: " + ((ContextCapabilities)object2).OpenGL30);
                System.out.println("OpenGL 3.1: " + ((ContextCapabilities)object2).OpenGL31);
                System.out.println("OpenGL 3.2: " + ((ContextCapabilities)object2).OpenGL32);
                System.out.println("ARB_compatibility: " + ((ContextCapabilities)object2).GL_ARB_compatibility);
                if (((ContextCapabilities)object2).OpenGL32) {
                    object2 = ByteBuffer.allocateDirect(64).order(ByteOrder.nativeOrder()).asIntBuffer();
                    GL11.glGetInteger((int)37158, (IntBuffer)object2);
                    int n2 = ((IntBuffer)object2).get(0);
                    System.out.println("PROFILE MASK: " + Integer.toBinaryString(n2));
                    System.out.println("CORE PROFILE: " + ((n2 & 1) != 0));
                    System.out.println("COMPATIBILITY PROFILE: " + ((n2 & 2) != 0));
                }
            }
            catch (LWJGLException lWJGLException) {
                object2 = lWJGLException;
                lWJGLException.printStackTrace();
                try {
                    Thread.sleep(1000L);
                }
                catch (InterruptedException interruptedException) {}
                Display.create();
            }
            Keyboard.create();
            Mouse.create();
            d2.y = new m(d2.j);
            try {
                Controllers.create();
            }
            catch (Exception exception) {
                object2 = exception;
                exception.printStackTrace();
            }
            GL11.glEnable((int)3553);
            GL11.glShadeModel((int)7425);
            GL11.glClearDepth((double)1.0);
            GL11.glEnable((int)2929);
            GL11.glDepthFunc((int)515);
            GL11.glEnable((int)3008);
            GL11.glAlphaFunc((int)516, (float)0.1f);
            GL11.glCullFace((int)1029);
            GL11.glMatrixMode((int)5889);
            GL11.glLoadIdentity();
            GL11.glMatrixMode((int)5888);
            d2.F = new i();
            String string = "minecraft";
            object2 = d2;
            object2 = System.getProperty("user.home", ".");
            object = System.getProperty("os.name").toLowerCase();
            switch (((String)object).contains("win") ? net.minecraft.client.e.c : (((String)object).contains("mac") ? net.minecraft.client.e.d : (((String)object).contains("solaris") ? net.minecraft.client.e.b : (((String)object).contains("sunos") ? net.minecraft.client.e.b : (((String)object).contains("linux") ? net.minecraft.client.e.a : (((String)object).contains("unix") ? net.minecraft.client.e.a : net.minecraft.client.e.e)))))) {
                case a: 
                case b: {
                    object2 = new File((String)object2, '.' + string + '/');
                    break;
                }
                case c: {
                    object = System.getenv("APPDATA");
                    if (object != null) {
                        object2 = new File((String)object, "." + string + '/');
                        break;
                    }
                    object2 = new File((String)object2, '.' + string + '/');
                    break;
                }
                case d: {
                    object2 = new File((String)object2, "Library/Application Support/" + string);
                    break;
                }
                default: {
                    object2 = new File((String)object2, string + '/');
                }
            }
            if (!((File)object2).exists() && !((File)object2).mkdirs()) {
                throw new RuntimeException("The working directory could not be created: " + object2);
            }
            d2.z = object2;
            d2.w = new q(d2, d2.z);
            d2.x.a(d2.w);
            d2.m = new k(d2.w);
            d2.m.a(d2.P);
            d2.m.a(d2.O);
            d2.m.a(new net.minecraft.client.a.c.a());
            d2.m.a(new net.minecraft.client.a.c.e(0));
            d2.m.a(new net.minecraft.client.a.c.e(1));
            d2.m.a(new net.minecraft.client.a.c.c(0));
            d2.m.a(new net.minecraft.client.a.c.c(1));
            d2.n = new net.minecraft.client.c.j(d2.w, "/default.png", d2.m);
            object2 = BufferUtils.createIntBuffer((int)256);
            ((Buffer)object2).clear().limit(256);
            d2.e = new net.minecraft.client.a.b(d2, d2.m);
            GL11.glViewport((int)0, (int)0, (int)d2.b, (int)d2.c);
            if (d2.N != null && d2.h != null) {
                object2 = new net.minecraft.a.a.g();
                ((net.minecraft.a.a.g)object2).a(8, 8, 8, new byte[512], new byte[512]);
                d2.a((net.minecraft.a.a.g)object2);
            } else if (d2.d == null) {
                d2.a(new r());
            }
            d2.g = new net.minecraft.client.f.d(d2.d, d2.m);
            try {
                d2.H = new j(d2.z, d2);
                d2.H.start();
            }
            catch (Exception exception) {}
            d2.t = new net.minecraft.client.c.l(d2);
        }
        catch (Exception exception) {
            Exception exception2 = exception;
            exception.printStackTrace();
            JOptionPane.showMessageDialog(null, exception2.toString(), "Failed to start Minecraft", 0);
            return;
        }
        long l2 = System.currentTimeMillis();
        int n3 = 0;
        try {
            while (this.A) {
                if (this.d != null) {
                    this.d.d();
                }
                if (this.j == null && Display.isCloseRequested()) {
                    d d3 = this;
                    this.A = false;
                }
                try {
                    if (this.l) {
                        float f2 = this.G.c;
                        this.G.a();
                        this.G.c = f2;
                    } else {
                        this.G.a();
                    }
                    for (int i2 = 0; i2 < this.G.b; ++i2) {
                        ++this.I;
                        this.f();
                    }
                    this.x.a(this.f, this.G.c);
                    GL11.glEnable((int)3553);
                    this.a.a(this.G.c);
                    this.q.a(this.G.c);
                    if (!Display.isActive()) {
                        if (this.E) {
                            this.d();
                        }
                        Thread.sleep(10L);
                    }
                    if (!(this.j == null || this.E || this.j.getWidth() == this.b && this.j.getHeight() == this.c)) {
                        this.b = this.j.getWidth();
                        this.c = this.j.getHeight();
                        this.a(this.b, this.c);
                    }
                    if (this.w.h) {
                        Thread.sleep(5L);
                    }
                    ++n3;
                    this.l = this.o != null && this.o.c();
                }
                catch (Exception exception) {
                    this.a(new net.minecraft.client.c.c("Client error", "The game broke! [" + exception + "]"));
                    exception.printStackTrace();
                    break;
                }
                while (System.currentTimeMillis() >= l2 + 1000L) {
                    this.B = n3 + " fps, " + net.minecraft.client.a.h.a + " chunk updates";
                    net.minecraft.client.a.h.a = 0;
                    l2 += 1000L;
                    n3 = 0;
                }
            }
            return;
        }
        catch (p p2) {
            return;
        }
        catch (Exception exception) {
            object = exception;
            exception.printStackTrace();
            return;
        }
        finally {
            this.a();
        }
    }

    public final void b() {
        if (!Display.isActive()) {
            return;
        }
        if (this.C) {
            return;
        }
        this.C = true;
        this.y.a();
        this.a((net.minecraft.client.c.i)null);
        this.Q = this.I + 10000;
    }

    private void e() {
        Object object;
        if (!this.C) {
            return;
        }
        if (this.f != null) {
            object = this.f;
            object.a.b();
        }
        this.C = false;
        try {
            Mouse.setNativeCursor(null);
            return;
        }
        catch (LWJGLException lWJGLException) {
            object = lWJGLException;
            lWJGLException.printStackTrace();
            return;
        }
    }

    public final void c() {
        if (this.o != null) {
            return;
        }
        this.a(new net.minecraft.client.c.b());
    }

    private void a(int n2) {
        Object object;
        Object object2;
        int n3;
        net.minecraft.a.b.k k2;
        if (n2 == 0 && this.J > 0) {
            return;
        }
        if (n2 == 0) {
            this.q.a.c();
        }
        if (n2 == 1 && (k2 = this.f.b.d()) != null) {
            n3 = k2.a;
            net.minecraft.client.g.a a2 = this.f;
            object2 = this.d;
            object = k2;
            if ((object = ((net.minecraft.a.b.k)object).a().a((net.minecraft.a.b.k)object, (net.minecraft.a.a.g)object2, a2)) != k2 || object != null && ((net.minecraft.a.b.k)object).a != n3) {
                this.f.b.a[this.f.b.c] = object;
                this.q.a.d();
                if (((net.minecraft.a.b.k)object).a == 0) {
                    this.f.b.a[this.f.b.c] = null;
                }
            }
        }
        if (this.v == null) {
            if (n2 == 0 && !(this.a instanceof c)) {
                this.J = 10;
            }
            return;
        }
        if (this.v.a == 1) {
            if (n2 == 0) {
                int n4;
                int n5;
                object2 = this.v.g;
                object = this.f;
                Object object3 = object2;
                Object object4 = ((net.minecraft.client.g.a)object).b;
                Object object5 = ((net.minecraft.a.c.e.b)object4).a(((net.minecraft.a.c.e.b)object4).c);
                if (object5 != null) {
                    net.minecraft.a.b.k k3 = object5;
                    object5 = object3;
                    object5 = k3;
                    n5 = net.minecraft.a.b.i.b[((net.minecraft.a.b.k)object5).c].a();
                } else {
                    n5 = n4 = 1;
                }
                if (n5 > 0) {
                    ((net.minecraft.a.c.b)object2).a((net.minecraft.a.c.b)object, n4);
                    object5 = object;
                    k2 = ((net.minecraft.client.g.a)object5).b.d();
                    if (k2 != null && object2 instanceof net.minecraft.a.c.e) {
                        object3 = (net.minecraft.a.c.e)object2;
                        object4 = k2;
                        net.minecraft.a.b.i.b[((net.minecraft.a.b.k)object4).c].a((net.minecraft.a.b.k)object4);
                        if (k2.a <= 0) {
                            ((net.minecraft.client.g.a)object).h_();
                        }
                    }
                }
                return;
            }
        } else if (this.v.a == 0) {
            int n6 = this.v.b;
            n3 = this.v.c;
            int n7 = this.v.d;
            int n8 = this.v.e;
            x x2 = net.minecraft.a.a.b.x.c[this.d.a(n6, n3, n7)];
            if (n2 == 0) {
                this.d.g(n6, n3, n7, this.v.e);
                if (x2 != net.minecraft.a.a.b.x.o) {
                    this.a.a(n6, n3, n7);
                    return;
                }
            } else {
                net.minecraft.a.b.k k4 = this.f.b.d();
                int n9 = this.d.a(n6, n3, n7);
                if (n9 > 0 && net.minecraft.a.a.b.x.c[n9].a(this.d, n6, n3, n7, this.f)) {
                    return;
                }
                if (k4 == null) {
                    return;
                }
                n9 = k4.a;
                int n10 = n6;
                int n11 = n3;
                int n12 = n8;
                n3 = n7;
                n6 = n11;
                int n13 = n10;
                net.minecraft.a.a.g g2 = this.d;
                net.minecraft.a.b.k k5 = k4;
                if (k5.a().a(k5, g2, n13, n6, n3, n12)) {
                    this.q.a.c();
                }
                if (k4.a == 0) {
                    this.f.b.a[this.f.b.c] = null;
                    return;
                }
                if (k4.a != n9) {
                    this.q.a.b();
                }
            }
        }
    }

    public final void d() {
        try {
            this.E = !this.E;
            System.out.println("Toggle fullscreen!");
            if (this.E) {
                Display.setDisplayMode((DisplayMode)Display.getDesktopDisplayMode());
                this.b = Display.getDisplayMode().getWidth();
                this.c = Display.getDisplayMode().getHeight();
            } else {
                if (this.j != null) {
                    this.b = this.j.getWidth();
                    this.c = this.j.getHeight();
                } else {
                    this.b = this.K;
                    this.c = this.L;
                }
                Display.setDisplayMode((DisplayMode)new DisplayMode(this.K, this.L));
            }
            this.e();
            Display.setFullscreen((boolean)this.E);
            Display.update();
            Thread.sleep(1000L);
            if (this.E) {
                this.b();
            }
            if (this.o != null) {
                this.e();
                this.a(this.b, this.c);
            }
            System.out.println("Size: " + this.b + ", " + this.c);
            return;
        }
        catch (Exception exception) {
            Exception exception2 = exception;
            exception.printStackTrace();
            return;
        }
    }

    private void a(int n2, int n3) {
        this.b = n2;
        this.c = n3;
        if (this.o != null) {
            net.minecraft.client.c.h h2 = new net.minecraft.client.c.h(n2, n3);
            n3 = h2.a();
            int n4 = h2.b();
            this.o.a(this, n3, n4);
        }
    }

    private void f() {
        Object object;
        this.t.a();
        if (!this.l && this.d != null) {
            this.a.c();
        }
        GL11.glBindTexture((int)3553, (int)this.m.a("/terrain.png"));
        if (!this.l) {
            this.m.a();
        }
        if (this.o == null && this.f != null && this.f.W <= 0) {
            this.a((net.minecraft.client.c.i)null);
        }
        if (this.o == null || this.o.f) {
            int n2;
            int n3;
            while (Mouse.next()) {
                int n4 = Mouse.getEventDWheel();
                if (n4 != 0) {
                    n3 = n4;
                    net.minecraft.a.c.e.b b2 = this.f.b;
                    if (n3 > 0) {
                        n3 = 1;
                    }
                    if (n3 < 0) {
                        n3 = -1;
                    }
                    b2.c -= n3;
                    while (b2.c < 0) {
                        b2.c += 9;
                    }
                    while (b2.c >= 9) {
                        b2.c -= 9;
                    }
                }
                if (this.o == null) {
                    if (!this.C && Mouse.getEventButtonState()) {
                        this.b();
                        continue;
                    }
                    if (Mouse.getEventButton() == 0 && Mouse.getEventButtonState()) {
                        this.a(0);
                        this.Q = this.I;
                    }
                    if (Mouse.getEventButton() == 1 && Mouse.getEventButtonState()) {
                        this.a(1);
                        this.Q = this.I;
                    }
                    if (Mouse.getEventButton() != 2 || !Mouse.getEventButtonState()) continue;
                    d d2 = this;
                    if (d2.v == null) continue;
                    n3 = d2.d.a(d2.v.b, d2.v.c, d2.v.d);
                    if (n3 == net.minecraft.a.a.b.x.j.at) {
                        n3 = net.minecraft.a.a.b.x.k.at;
                    }
                    if (n3 == net.minecraft.a.a.b.x.Y.at) {
                        n3 = net.minecraft.a.a.b.x.Z.at;
                    }
                    if (n3 == net.minecraft.a.a.b.x.o.at) {
                        n3 = net.minecraft.a.a.b.x.i.at;
                    }
                    d2.f.b.b(n3);
                    continue;
                }
                if (this.o == null) continue;
                this.o.f();
            }
            if (this.J > 0) {
                --this.J;
            }
            while (Keyboard.next()) {
                n2 = Keyboard.getEventKeyState();
                n3 = Keyboard.getEventKey();
                net.minecraft.client.g.a a2 = this.f;
                a2.a.a(n3, n2 != 0);
                if (!Keyboard.getEventKeyState()) continue;
                if (Keyboard.getEventKey() == 87) {
                    this.d();
                    continue;
                }
                if (this.o != null) {
                    this.o.g();
                } else {
                    if (Keyboard.getEventKey() == 1) {
                        this.c();
                    }
                    if (Keyboard.getEventKey() == 65) {
                        this.q.b();
                    }
                    if (this.a instanceof c) {
                        if (Keyboard.getEventKey() == this.w.r.b) {
                            this.f.j();
                        }
                        if (Keyboard.getEventKey() == this.w.q.b) {
                            this.d.a((int)this.f.h, (int)this.f.i, (int)this.f.j, this.f.n);
                            this.f.j();
                        }
                    }
                    if (Keyboard.getEventKey() == 63) {
                        boolean bl = this.w.v = !this.w.v;
                    }
                    if (Keyboard.getEventKey() == this.w.n.b) {
                        this.a(new net.minecraft.client.c.a.f(this.f.b));
                    }
                    if (Keyboard.getEventKey() == this.w.o.b) {
                        this.f.a(this.f.b.a(this.f.b.c, 1), false);
                    }
                }
                for (int i2 = 0; i2 < 9; ++i2) {
                    if (Keyboard.getEventKey() != i2 + 2) continue;
                    this.f.b.c = i2;
                }
                if (Keyboard.getEventKey() != this.w.p.b) continue;
                this.w.b(4, Keyboard.isKeyDown((int)42) || Keyboard.isKeyDown((int)54) ? -1 : 1);
            }
            if (this.o == null) {
                if (Mouse.isButtonDown((int)0) && (float)(this.I - this.Q) >= this.G.a / 4.0f && this.C) {
                    this.a(0);
                    this.Q = this.I;
                }
                if (Mouse.isButtonDown((int)1) && (float)(this.I - this.Q) >= this.G.a / 4.0f && this.C) {
                    this.a(1);
                    this.Q = this.I;
                }
            }
            n2 = this.o == null && Mouse.isButtonDown((int)0) && this.C ? 1 : 0;
            n3 = 0;
            object = this;
            if (!((d)object).a.b && ((d)object).J <= 0) {
                if (n2 != 0 && ((d)object).v != null && ((d)object).v.a == 0) {
                    n3 = ((d)object).v.b;
                    n2 = ((d)object).v.c;
                    int n5 = ((d)object).v.d;
                    ((d)object).a.a(n3, n2, n5, ((d)object).v.e);
                    ((d)object).g.a(n3, n2, n5, ((d)object).v.e);
                } else {
                    ((d)object).a.a();
                }
            }
        }
        if (this.o != null) {
            this.Q = this.I + 10000;
        }
        if (this.o != null) {
            object = this.o;
            while (Mouse.next()) {
                ((net.minecraft.client.c.i)object).f();
            }
            while (Keyboard.next()) {
                ((net.minecraft.client.c.i)object).g();
            }
            if (this.o != null) {
                this.o.f_();
            }
        }
        if (this.d != null) {
            this.d.E = this.w.u;
            if (!this.l) {
                this.q.a();
            }
            if (!this.l) {
                this.e.e();
            }
            if (!this.l) {
                this.d.c();
            }
            if (!this.l) {
                this.d.f();
            }
            if (!this.l) {
                this.d.k((int)this.f.h, (int)this.f.i, (int)this.f.j);
            }
            if (!this.l) {
                this.g.a();
            }
        }
    }

    public final void a(int n2, int n3, int n4, int n5) {
        this.a((net.minecraft.a.a.g)null);
        System.gc();
        String string = this.h != null ? this.h.b : "anonymous";
        net.minecraft.a.a.c.a a2 = new net.minecraft.a.a.c.a(this.p);
        new net.minecraft.a.a.c.a(this.p).a = n4 == 1;
        a2.b = n4 == 2;
        a2.c = n4 == 3;
        a2.d = n5;
        n4 = n2 = 128 << n2;
        n5 = 64;
        if (n3 == 1) {
            n2 /= 2;
            n4 <<= 1;
        } else if (n3 == 2) {
            n4 = n2 /= 2;
            n5 = 256;
        }
        net.minecraft.a.a.g g2 = a2.a(string, n2, n4, n5);
        this.a(g2);
    }

    public final void a(net.minecraft.a.a.g g2) {
        if (this.d != null) {
            this.d.k();
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(this.M.getDocumentBase() + "?n=" + this.h.b + "&i=" + this.h.c).openStream()));
            Integer.parseInt(bufferedReader.readLine());
            bufferedReader.close();
            if (this.M.getDocumentBase().toString().startsWith("http://www.minecraft.net/") || this.M.getDocumentBase().toString().startsWith("http://minecraft.net/")) {
                this.d = g2;
            }
        }
        catch (Throwable throwable) {}
        if (g2 != null) {
            g2.a();
            this.a.a(g2);
            this.f = (net.minecraft.client.g.a)g2.b(net.minecraft.client.g.a.class);
            g2.y = this.f;
            if (this.f == null) {
                this.f = new net.minecraft.client.g.a(this, g2, this.h);
                this.f.j();
                if (g2 != null) {
                    g2.a(this.f);
                    g2.y = this.f;
                }
            }
            if (this.f != null) {
                this.f.a = new net.minecraft.client.g.c(this.w);
                this.a.a(this.f);
            }
            if (this.e != null) {
                this.e.a(g2);
            }
            if (this.g != null) {
                this.g.a(g2);
            }
            this.O.d = 0;
            this.P.d = 0;
            int n2 = this.m.a("/water.png");
            if (g2.m == net.minecraft.a.a.b.x.p.at) {
                this.O.d = n2;
            } else {
                this.P.d = n2;
            }
        }
        System.gc();
    }
}

