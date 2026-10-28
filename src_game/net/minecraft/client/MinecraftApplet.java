/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Component;
import net.minecraft.client.CanvasMinecraftApplet;
import net.minecraft.client.Minecraft;
import net.minecraft.network.User;

public class MinecraftApplet
extends Applet {
    private static final long serialVersionUID = 1L;
    private Canvas mcCanvas;
    private Minecraft mc;
    private Thread mcThread = null;

    @Override
    public void init() {
        this.mcCanvas = new CanvasMinecraftApplet(this);
        boolean bl = false;
        if (this.getParameter("fullscreen") != null) {
            bl = this.getParameter("fullscreen").equalsIgnoreCase("true");
        }
        this.mc = new Minecraft(this, this.mcCanvas, this, this.getWidth(), this.getHeight(), bl);
        this.mc.minecraftUri = this.getDocumentBase().getHost();
        if (this.getDocumentBase().getPort() > 0) {
            this.mc.minecraftUri = this.mc.minecraftUri + ":" + this.getDocumentBase().getPort();
        }
        if (this.getParameter("username") != null && this.getParameter("sessionid") != null) {
            this.mc.user = new User(this.getParameter("username"), this.getParameter("sessionid"));
            if (this.getParameter("mppass") != null) {
                this.mc.user.mpPass = this.getParameter("mppass");
            }
        }
        if (this.getParameter("loadmap_user") != null && this.getParameter("loadmap_id") != null) {
            this.mc.loadMapUser = this.getParameter("loadmap_user");
            this.mc.loadMapID = Integer.parseInt(this.getParameter("loadmap_id"));
        } else if (this.getParameter("server") != null && this.getParameter("port") != null) {
            this.mc.joinServer(this.getParameter("server"), Integer.parseInt(this.getParameter("port")));
        }
        this.mc.appletMode = true;
        this.setLayout(new BorderLayout());
        this.add((Component)this.mcCanvas, "Center");
        this.mcCanvas.setFocusable(true);
        this.validate();
    }

    public final void startMainThread() {
        if (this.mcThread == null) {
            this.mcThread = new Thread((Runnable)this.mc, "Minecraft main thread");
            this.mcThread.start();
        }
    }

    @Override
    public void start() {
        if (this.mc == null) {
            return;
        }
        this.mc.isGamePaused = false;
    }

    @Override
    public void stop() {
        if (this.mc == null) {
            return;
        }
        this.mc.isGamePaused = true;
    }

    @Override
    public void destroy() {
        this.shutdown();
    }

    public final void shutdown() {
        if (this.mcThread != null) {
            this.mc.running = false;
            try {
                this.mcThread.join(1000L);
            }
            catch (InterruptedException interruptedException) {
                try {
                    this.mc.shutdownMinecraftApplet();
                }
                catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            this.mcThread = null;
        }
    }
}

