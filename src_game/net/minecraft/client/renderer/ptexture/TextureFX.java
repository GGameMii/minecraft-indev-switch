/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.renderer.ptexture;

public class TextureFX {
    public byte[] imageData = new byte[1024];
    public int iconIndex;
    public boolean anaglyphEnabled = false;
    public int textureId = 0;

    public TextureFX(int iconIndex) {
        this.iconIndex = iconIndex;
    }

    public void tick() {
    }
}

