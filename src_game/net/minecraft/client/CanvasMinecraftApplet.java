/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client;

import java.awt.Canvas;
import net.minecraft.client.MinecraftApplet;

final class CanvasMinecraftApplet
extends Canvas {
    private static final long serialVersionUID = 1L;
    private MinecraftApplet mcApplet;

    CanvasMinecraftApplet(MinecraftApplet minecraftApplet) {
        this.mcApplet = minecraftApplet;
    }

    @Override
    public final synchronized void addNotify() {
        super.addNotify();
        this.mcApplet.startMainThread();
    }

    @Override
    public final synchronized void removeNotify() {
        this.mcApplet.shutdown();
        super.removeNotify();
    }
}

