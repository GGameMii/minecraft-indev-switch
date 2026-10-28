/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client;

import net.minecraft.client.Minecraft;

final class ThreadSleepForever
extends Thread {
    ThreadSleepForever(Minecraft minecraft, String string2) {
        super(string2);
        this.setDaemon(true);
        this.start();
    }

    @Override
    public final void run() {
        while (true) {
            try {
                Thread.sleep(Integer.MAX_VALUE);
            }
            catch (InterruptedException interruptedException) {
            }
        }
    }
}

