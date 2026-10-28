/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client;

import net.minecraft.client.d;

final class n
extends Thread {
    n(d d2, String string) {
        super(string);
        this.setDaemon(true);
        this.start();
    }

    public final void run() {
        while (true) {
            try {
                while (true) {
                    Thread.sleep(Integer.MAX_VALUE);
                }
            }
            catch (InterruptedException interruptedException) {
                continue;
            }
            break;
        }
    }
}

