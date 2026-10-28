/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.a;

import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.imageio.ImageIO;
import net.minecraft.client.a.c;
import net.minecraft.client.a.i;

final class j
extends Thread {
    private /* synthetic */ String a;
    private /* synthetic */ c b;
    private /* synthetic */ i c;

    j(i i2, String string, c c2) {
        this.c = i2;
        this.a = string;
        this.b = c2;
    }

    public final void run() {
        Serializable serializable;
        HttpURLConnection httpURLConnection = null;
        try {
            serializable = new URL(this.a);
            httpURLConnection = (HttpURLConnection)((URL)serializable).openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(false);
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() == 404) {
                return;
            }
            this.c.a = this.b == null ? ImageIO.read(httpURLConnection.getInputStream()) : this.b.a(ImageIO.read(httpURLConnection.getInputStream()));
            return;
        }
        catch (Exception exception) {
            serializable = exception;
            exception.printStackTrace();
            return;
        }
        finally {
            httpURLConnection.disconnect();
        }
    }
}

