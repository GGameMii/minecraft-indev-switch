/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.gui;

import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import net.minecraft.client.gui.Button;
import net.minecraft.client.gui.NameServerScreen;
import net.minecraft.client.gui.Screen;

public class ServerListScreen
extends Screen
implements Runnable {
    private Screen parent;
    private boolean finished = false;
    private boolean loaded = false;
    private String[] names = new String[5];
    private String[] ips = new String[5];
    private String status = "";
    protected String title = "Join server";
    private boolean frozen = false;
    private boolean selecting = false;
    private boolean deleting = false;
    private File serverfile;

    public ServerListScreen(Screen screen) {
        this.parent = screen;
    }

    @Override
    public final void tick() {
    }

    @Override
    public void run() {
        try {
            this.status = "Getting server list..";
            if (this.serverfile.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(this.serverfile));
                try {
                    String log;
                    while ((log = reader.readLine()) != null) {
                        String[] l2 = log.split(":");
                        if (l2[0].equals("server0.name") && l2.length >= 2) {
                            this.names[0] = l2[1];
                        }
                        if (l2[0].equals("server0.ip") && l2.length >= 2) {
                            this.ips[0] = l2[1];
                        }
                        if (l2[0].equals("server1.name") && l2.length >= 2) {
                            this.names[1] = l2[1];
                        }
                        if (l2[0].equals("server1.ip") && l2.length >= 2) {
                            this.ips[1] = l2[1];
                        }
                        if (l2[0].equals("server2.name") && l2.length >= 2) {
                            this.names[2] = l2[1];
                        }
                        if (l2[0].equals("server2.ip") && l2.length >= 2) {
                            this.ips[2] = l2[1];
                        }
                        if (l2[0].equals("server3.name") && l2.length >= 2) {
                            this.names[3] = l2[1];
                        }
                        if (l2[0].equals("server3.ip") && l2.length >= 2) {
                            this.ips[3] = l2[1];
                        }
                        if (l2[0].equals("server4.name") && l2.length >= 2) {
                            this.names[4] = l2[1];
                        }
                        if (!l2[0].equals("server4.ip") || l2.length < 2) continue;
                        this.ips[4] = l2[1];
                    }
                }
                catch (Exception e2) {
                    e2.printStackTrace();
                    this.status = "Failed to load list file";
                    this.finished = true;
                    this.loaded = false;
                }
                reader.close();
                if (!this.loaded && this.finished) {
                    return;
                }
            }
            int slot = 0;
            while (slot < 5) {
                if (this.names[slot] == null) {
                    this.names[slot] = "-";
                }
                if (this.ips[slot] == null) {
                    this.ips[slot] = "";
                }
                ++slot;
            }
            slot = 0;
            while (slot < 5) {
                this.setLevels(this.names);
                ++slot;
            }
            this.loaded = true;
            this.finished = true;
        }
        catch (Exception e3) {
            e3.printStackTrace();
            this.status = "Failed to load list";
            this.finished = true;
        }
    }

    protected void setLevels(String[] names) {
        int i2 = 0;
        while (i2 < 5) {
            ((Button)this.buttons.get((int)i2)).enabled = !names[i2].equals("-");
            ((Button)this.buttons.get((int)i2)).displayString = names[i2];
            ((Button)this.buttons.get((int)i2)).visible = true;
            ++i2;
        }
        ((Button)this.buttons.get((int)5)).visible = true;
        ((Button)this.buttons.get((int)6)).visible = true;
    }

    @Override
    public void init() {
        this.serverfile = new File(this.mc.mcDataDir, "indev_servers.dat");
        this.buttons.clear();
        new Thread(this).start();
        int i2 = 0;
        while (i2 < 5) {
            this.buttons.add(new Button(i2, this.width / 2 - 100, this.height / 6 + i2 * 24, "---"));
            ((Button)this.buttons.get((int)i2)).visible = false;
            ++i2;
        }
        this.buttons.add(new Button(5, this.width / 2 - 100, this.height / 6 + 120 + 12, 100, 20, "Add/Edit..."));
        this.buttons.add(new Button(6, this.width / 2 - 100 + 100, this.height / 6 + 120 + 12, 100, 20, "Delete..."));
        this.buttons.add(new Button(7, this.width / 2 - 100, this.height / 6 + 168, "Cancel"));
        ((Button)this.buttons.get((int)5)).visible = false;
        ((Button)this.buttons.get((int)6)).visible = false;
    }

    @Override
    protected final void buttonClicked(Button button) {
        if (!this.frozen && button.enabled) {
            if (this.loaded && button.id < 5) {
                if (this.deleting) {
                    this.names[button.id] = "-";
                    this.ips[button.id] = "";
                    this.saveServers();
                    this.deleting = false;
                    ((Button)this.buttons.get((int)5)).enabled = true;
                    return;
                }
                if (this.selecting) {
                    this.mc.setScreen(new NameServerScreen(this, this.names[button.id], this.ips[button.id], button.id));
                    return;
                }
                this.openLevel(button.id);
            }
            if (this.finished && this.loaded && button.id == 5) {
                if (this.selecting) {
                    ((Button)this.buttons.get((int)5)).displayString = "Add/Edit...";
                    this.setLevels(this.names);
                    this.selecting = false;
                    ((Button)this.buttons.get((int)6)).enabled = true;
                    return;
                }
                ((Button)this.buttons.get((int)6)).enabled = false;
                ((Button)this.buttons.get((int)5)).displayString = "Cancel";
                this.selecting = true;
                int i2 = 0;
                while (i2 < 5) {
                    ((Button)this.buttons.get((int)i2)).enabled = true;
                    ++i2;
                }
            }
            if (this.finished && this.loaded && button.id == 6) {
                if (this.deleting) {
                    this.deleting = false;
                    ((Button)this.buttons.get((int)6)).displayString = "Delete...";
                    ((Button)this.buttons.get((int)5)).enabled = true;
                    return;
                }
                this.deleting = true;
                ((Button)this.buttons.get((int)6)).displayString = "Cancel";
                ((Button)this.buttons.get((int)5)).enabled = false;
            }
            if (this.finished && this.loaded && button.id == 7) {
                this.mc.setScreen(this.parent);
            }
        }
    }

    protected FileDialog saveFileDialog() {
        return new FileDialog((java.awt.Frame)null, "Load level", 0);
    }

    protected void openLevel(int slot) {
        String server = this.ips[slot];
        server = server.replaceAll("_", ":");
        String port = "25565";
        String[] split = server.split(":");
        if (split.length > 1) {
            port = split[1];
        }
        this.mc.hideScreen = true;
        this.mc.connectTo(split[0], Integer.parseInt(port));
    }

    @Override
    public final void render(int mx, int my, float a2) {
        this.drawBackground();
        ServerListScreen.drawCenteredString(this.font, this.title, this.width / 2, 20, 0xFFFFFF);
        if (!this.loaded) {
            ServerListScreen.drawCenteredString(this.font, this.status, this.width / 2, this.height / 2 - 4, 0xFFFFFF);
        }
        super.render(mx, my, a2);
    }

    public void setServer(int id, String name, String ip) {
        this.names[id] = name;
        this.ips[id] = ip;
        this.selecting = false;
        this.saveServers();
    }

    private void saveServers() {
        try {
            PrintWriter w2 = new PrintWriter(new FileWriter(this.serverfile));
            int i2 = 0;
            while (i2 < 5) {
                w2.println("server" + i2 + ".name:" + this.names[i2]);
                w2.println("server" + i2 + ".ip:" + this.ips[i2]);
                ++i2;
            }
            w2.close();
        }
        catch (Exception e2) {
            System.out.println("Failed to save options");
            e2.printStackTrace();
        }
        int slot = 0;
        while (slot < 5) {
            this.setLevels(this.names);
            ++slot;
        }
    }
}

