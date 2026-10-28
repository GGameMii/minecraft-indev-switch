/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client;

import java.awt.Canvas;
import net.minecraft.client.MinecraftApplet;

final class b
extends Canvas {
    private /* synthetic */ MinecraftApplet a;

    b(MinecraftApplet minecraftApplet) {
        this.a = minecraftApplet;
    }

    public final synchronized void addNotify() {
        super.addNotify();
        this.a.a();
    }

    public final synchronized void removeNotify() {
        this.a.b();
        super.removeNotify();
    }
}

