/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.c;

import java.awt.FileDialog;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import net.minecraft.client.c.e;
import net.minecraft.client.c.i;
import net.minecraft.client.c.p;
import net.minecraft.client.c.r;
import net.minecraft.client.f;

public final class o
extends e {
    public o(i i2) {
        super(i2);
        this.a = "Save level";
    }

    protected final FileDialog e() {
        return new FileDialog(null, "Save level", 1);
    }

    public final void b() {
        super.b();
        ((r)this.e.get((int)5)).a = "Save file...";
    }

    protected final void a(String[] stringArray) {
        for (int i2 = 0; i2 < 5; ++i2) {
            ((r)this.e.get((int)i2)).a = stringArray[i2];
            ((r)this.e.get((int)i2)).d = true;
        }
        ((r)this.e.get((int)5)).d = true;
    }

    protected final void a(File object) {
        try {
            object = new FileOutputStream((File)object);
            new f(this.b, this.b.p).a(this.b.d, (OutputStream)object);
            ((FileOutputStream)object).close();
            return;
        }
        catch (IOException iOException) {
            object = iOException;
            iOException.printStackTrace();
            return;
        }
    }

    protected final void a(int n2) {
        this.b.a(new p(this, ((r)this.e.get((int)n2)).a, n2));
    }
}

