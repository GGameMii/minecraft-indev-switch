/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.a.b.a;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import net.minecraft.a.a.b.x;
import net.minecraft.a.b.a.a;
import net.minecraft.a.b.a.b;
import net.minecraft.a.b.a.c;
import net.minecraft.a.b.a.d;
import net.minecraft.a.b.a.e;
import net.minecraft.a.b.a.g;
import net.minecraft.a.b.a.h;
import net.minecraft.a.b.i;
import net.minecraft.a.b.k;

public final class f {
    private static final f a = new f();
    private List b = new ArrayList();

    public static final f a() {
        return a;
    }

    private f() {
        new e().a(this);
        new h().a(this);
        new d().a(this);
        new b();
        f f2 = this;
        f2.a(new k(i.B), "Y", "X", "#", Character.valueOf('X'), x.U, Character.valueOf('Y'), x.V, Character.valueOf('#'), i.A);
        f2.a(new k(i.B), "Y", "X", "#", Character.valueOf('X'), x.V, Character.valueOf('Y'), x.U, Character.valueOf('#'), i.A);
        new g();
        f2 = this;
        f2.a(new k(x.aj), "###", "# #", "###", Character.valueOf('#'), x.m);
        f2.a(new k(x.aq), "###", "# #", "###", Character.valueOf('#'), x.l);
        f2.a(new k(x.an), "##", "##", Character.valueOf('#'), x.m);
        new a().a(this);
        this.a(new k(x.Q, 1), "###", "###", "###", Character.valueOf('#'), i.G);
        this.a(new k(x.ab, 1), "X#X", "#X#", "X#X", Character.valueOf('X'), i.I, Character.valueOf('#'), x.t);
        this.a(new k(x.Z, 3), "###", Character.valueOf('#'), x.l);
        this.a(new k(x.m, 4), "#", Character.valueOf('#'), x.y);
        this.a(new k(i.z, 4), "#", "#", Character.valueOf('#'), x.m);
        this.a(new k(x.af, 4), "X", "#", Character.valueOf('X'), i.i, Character.valueOf('#'), i.z);
        this.a(new k(i.A, 4), "# #", " # ", Character.valueOf('#'), x.m);
        this.a(new k(i.f, 1), "A ", " B", Character.valueOf('A'), i.k, Character.valueOf('B'), i.al);
        this.a(new k(i.Q, 1), "###", Character.valueOf('#'), i.P);
        this.a(new k(i.ao, 1), "###", "#X#", "###", Character.valueOf('#'), x.m, Character.valueOf('X'), x.Q);
        Collections.sort(this.b, new net.minecraft.a.b.a.i(this));
        System.out.println(this.b.size() + " recipes");
    }

    final void a(k k2, Object ... objectArray) {
        Object object;
        String string = "";
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        if (objectArray[0] instanceof String[]) {
            ++n2;
            object = (String[])objectArray[0];
            for (int i2 = 0; i2 < ((String[])object).length; ++i2) {
                Object object2 = object[i2];
                ++n4;
                n3 = ((String)object2).length();
                string = string + (String)object2;
            }
        } else {
            while (objectArray[n2] instanceof String) {
                object = (String)objectArray[n2++];
                ++n4;
                n3 = ((String)object).length();
                string = string + (String)object;
            }
        }
        object = new HashMap();
        while (n2 < objectArray.length) {
            Character c2 = (Character)objectArray[n2];
            int n5 = 0;
            if (objectArray[n2 + 1] instanceof i) {
                n5 = ((i)objectArray[n2 + 1]).ap;
            } else if (objectArray[n2 + 1] instanceof x) {
                n5 = ((x)objectArray[n2 + 1]).at;
            }
            object.put(c2, n5);
            n2 += 2;
        }
        int[] nArray = new int[n3 * n4];
        for (int i3 = 0; i3 < n3 * n4; ++i3) {
            char c3 = string.charAt(i3);
            nArray[i3] = object.containsKey(Character.valueOf(c3)) ? (Integer)object.get(Character.valueOf(c3)) : -1;
        }
        this.b.add(new c(n3, n4, nArray, k2));
    }

    public final k a(int[] nArray) {
        for (int i2 = 0; i2 < this.b.size(); ++i2) {
            c c2 = (c)this.b.get(i2);
            if (!c2.a(nArray)) continue;
            return c2.a();
        }
        return null;
    }
}

