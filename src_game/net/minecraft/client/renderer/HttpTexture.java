/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.renderer;

import java.awt.image.BufferedImage;
import net.minecraft.client.renderer.HttpTextureProcessor;
import net.minecraft.client.renderer.ThreadDownloadImage;

public final class HttpTexture {
    public BufferedImage image;
    public int referenceCount = 1;
    public int textureName = -1;
    public boolean textureSetupComplete = false;

    public HttpTexture(String string1, HttpTextureProcessor imageBufferDownload) {
        new ThreadDownloadImage(this, string1, imageBufferDownload).start();
    }
}

