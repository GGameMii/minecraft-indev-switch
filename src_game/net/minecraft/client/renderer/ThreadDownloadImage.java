/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.renderer;

import java.net.HttpURLConnection;
import java.net.URL;
import javax.imageio.ImageIO;
import net.minecraft.client.renderer.HttpTexture;
import net.minecraft.client.renderer.HttpTextureProcessor;

final class ThreadDownloadImage
extends Thread {
    private String location;
    private HttpTextureProcessor buffer;
    private HttpTexture imageData;

    ThreadDownloadImage(HttpTexture threadDownloadImageData, String string2, HttpTextureProcessor imageBufferDownload) {
        this.imageData = threadDownloadImageData;
        this.location = string2;
        this.buffer = imageBufferDownload;
    }

    @Override
    public final void run() {
        HttpURLConnection httpURLConnection1 = null;
        try {
            httpURLConnection1 = (HttpURLConnection)new URL(this.location).openConnection();
            httpURLConnection1.setDoInput(true);
            httpURLConnection1.setDoOutput(false);
            httpURLConnection1.connect();
            if (httpURLConnection1.getResponseCode() == 404) {
                return;
            }
            this.imageData.image = this.buffer == null ? ImageIO.read(httpURLConnection1.getInputStream()) : this.buffer.parseUserSkin(ImageIO.read(httpURLConnection1.getInputStream()));
            return;
        }
        catch (Exception exception5) {
            exception5.printStackTrace();
        }
        finally {
            httpURLConnection1.disconnect();
        }
    }
}

