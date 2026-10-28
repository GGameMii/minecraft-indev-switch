/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.BufferUtils
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client.renderer;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import net.minecraft.client.GameOptions;
import net.minecraft.client.renderer.HttpTexture;
import net.minecraft.client.renderer.HttpTextureProcessor;
import net.minecraft.client.renderer.ptexture.TextureFX;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public class RenderEngine {
    private HashMap<String, Integer> idMap = new HashMap();
    private HashMap<Integer, BufferedImage> loadedImages = new HashMap();
    private IntBuffer ib = BufferUtils.createIntBuffer((int)1);
    private ByteBuffer pixels = BufferUtils.createByteBuffer((int)262144);
    private List<TextureFX> textureList = new ArrayList<TextureFX>();
    private Map<String, HttpTexture> urlToImageDataMap = new HashMap<String, HttpTexture>();
    private GameOptions options;
    private boolean clamp = false;

    public RenderEngine(GameOptions gameSettings) {
        this.options = gameSettings;
    }

    public final int getTexture(String string1) {
        Integer integer2 = this.idMap.get(string1);
        if (integer2 != null) {
            return integer2;
        }
        try {
            this.ib.clear();
            GL11.glGenTextures((IntBuffer)this.ib);
            int i4 = this.ib.get(0);
            if (string1.startsWith("##")) {
                this.loadTexture(RenderEngine.makeStrip(ImageIO.read(RenderEngine.class.getResourceAsStream(string1.substring(2)))), i4);
            } else if (string1.startsWith("%%")) {
                this.clamp = true;
                this.loadTexture(ImageIO.read(RenderEngine.class.getResourceAsStream(string1.substring(2))), i4);
                this.clamp = false;
            } else {
                this.loadTexture(ImageIO.read(RenderEngine.class.getResourceAsStream(string1)), i4);
            }
            this.idMap.put(string1, i4);
            return i4;
        }
        catch (IOException iOException3) {
            throw new RuntimeException("!!");
        }
    }

    private static BufferedImage makeStrip(BufferedImage source) {
        int cols = source.getWidth() / 16;
        BufferedImage out = new BufferedImage(16, source.getHeight() * cols, 2);
        Graphics g2 = out.getGraphics();
        int i4 = 0;
        while (i4 < cols) {
            g2.drawImage(source, -i4 << 4, i4 * source.getHeight(), null);
            ++i4;
        }
        g2.dispose();
        return out;
    }

    private void loadTexture(BufferedImage source, int i2) {
        GL11.glBindTexture((int)3553, (int)i2);
        GL11.glTexParameteri((int)3553, (int)10241, (int)9728);
        GL11.glTexParameteri((int)3553, (int)10240, (int)9728);
        if (this.clamp) {
            GL11.glTexParameteri((int)3553, (int)10242, (int)10496);
            GL11.glTexParameteri((int)3553, (int)10243, (int)10496);
        } else {
            GL11.glTexParameteri((int)3553, (int)10242, (int)10497);
            GL11.glTexParameteri((int)3553, (int)10243, (int)10497);
        }
        i2 = source.getWidth();
        int i3 = source.getHeight();
        int[] i4 = new int[i2 * i3];
        byte[] b5 = new byte[i2 * i3 << 2];
        source.getRGB(0, 0, i2, i3, i4, 0, i2);
        int i11 = 0;
        while (i11 < i4.length) {
            int i6 = i4[i11] >>> 24;
            int i7 = i4[i11] >> 16 & 0xFF;
            int i8 = i4[i11] >> 8 & 0xFF;
            int i9 = i4[i11] & 0xFF;
            if (this.options != null && this.options.anaglyph3d) {
                int i10 = (i7 * 30 + i8 * 59 + i9 * 11) / 100;
                i8 = (i7 * 30 + i8 * 70) / 100;
                i9 = (i7 * 30 + i9 * 70) / 100;
                i7 = i10;
            }
            b5[i11 << 2] = (byte)i7;
            b5[(i11 << 2) + 1] = (byte)i8;
            b5[(i11 << 2) + 2] = (byte)i9;
            b5[(i11 << 2) + 3] = (byte)i6;
            ++i11;
        }
        this.pixels.clear();
        this.pixels.put(b5);
        this.pixels.position(0).limit(b5.length);
        GL11.glTexImage2D((int)3553, (int)0, (int)6408, (int)i2, (int)i3, (int)0, (int)6408, (int)5121, (ByteBuffer)this.pixels);
    }

    public final int loadHttpTexture(String url, String backup) {
        HttpTexture texture = this.urlToImageDataMap.get(url);
        if (texture != null && texture.image != null && !texture.textureSetupComplete) {
            if (texture.textureName < 0) {
                BufferedImage img = texture.image;
                this.ib.clear();
                GL11.glGenTextures((IntBuffer)this.ib);
                int i5 = this.ib.get(0);
                this.loadTexture(img, i5);
                this.loadedImages.put(i5, img);
                texture.textureName = i5;
            } else {
                this.loadTexture(texture.image, texture.textureName);
            }
            texture.textureSetupComplete = true;
        }
        return texture != null && texture.textureName >= 0 ? texture.textureName : this.getTexture(backup);
    }

    public final HttpTexture addHttpTexture(String url, HttpTextureProcessor processor) {
        HttpTexture texture = this.urlToImageDataMap.get(url);
        if (texture == null) {
            this.urlToImageDataMap.put(url, new HttpTexture(url, processor));
        } else {
            ++texture.referenceCount;
        }
        return texture;
    }

    public final void removeHttpTexture(String url) {
        HttpTexture texture = this.urlToImageDataMap.get(url);
        if (texture != null) {
            --texture.referenceCount;
            if (texture.referenceCount == 0) {
                if (texture.textureName >= 0) {
                    int i3 = texture.textureName;
                    this.loadedImages.remove(i3);
                    this.ib.clear();
                    this.ib.put(i3);
                    this.ib.flip();
                    GL11.glDeleteTextures((IntBuffer)this.ib);
                }
                this.urlToImageDataMap.remove(url);
            }
        }
    }

    public final void addDynamicTexture(TextureFX dynamicTexture) {
        this.textureList.add(dynamicTexture);
        dynamicTexture.tick();
    }

    public final void updateDynamicTextures() {
        TextureFX dynamicTexture;
        int i2 = 0;
        while (i2 < this.textureList.size()) {
            dynamicTexture = this.textureList.get(i2);
            this.textureList.get(i2).anaglyphEnabled = this.options.anaglyph3d;
            dynamicTexture.tick();
            this.pixels.clear();
            this.pixels.put(dynamicTexture.imageData);
            this.pixels.position(0).limit(dynamicTexture.imageData.length);
            GL11.glTexSubImage2D((int)3553, (int)0, (int)(dynamicTexture.iconIndex % 16 << 4), (int)(dynamicTexture.iconIndex / 16 << 4), (int)16, (int)16, (int)6408, (int)5121, (ByteBuffer)this.pixels);
            ++i2;
        }
        i2 = 0;
        while (i2 < this.textureList.size()) {
            dynamicTexture = this.textureList.get(i2);
            if (dynamicTexture.textureId > 0) {
                this.pixels.clear();
                this.pixels.put(dynamicTexture.imageData);
                this.pixels.position(0).limit(dynamicTexture.imageData.length);
                GL11.glBindTexture((int)3553, (int)dynamicTexture.textureId);
                GL11.glTexSubImage2D((int)3553, (int)0, (int)0, (int)0, (int)16, (int)16, (int)6408, (int)5121, (ByteBuffer)this.pixels);
            }
            ++i2;
        }
    }

    public final void refreshTextures() {
        BufferedImage bufferedImage3;
        for (int i2 : this.loadedImages.keySet()) {
            bufferedImage3 = this.loadedImages.get(i2);
            this.loadTexture(bufferedImage3, i2);
        }
        Iterator<HttpTexture> iterator0 = this.urlToImageDataMap.values().iterator();
        while (iterator0.hasNext()) {
            iterator0.next().textureSetupComplete = false;
        }
        for (String string5 : this.idMap.keySet()) {
            try {
                int i2;
                if (string5.startsWith("##")) {
                    bufferedImage3 = RenderEngine.makeStrip(ImageIO.read(RenderEngine.class.getResourceAsStream(string5.substring(2))));
                } else if (string5.startsWith("%%")) {
                    this.clamp = true;
                    bufferedImage3 = ImageIO.read(RenderEngine.class.getResourceAsStream(string5.substring(2)));
                    this.clamp = false;
                } else {
                    bufferedImage3 = ImageIO.read(RenderEngine.class.getResourceAsStream(string5));
                }
                i2 = this.idMap.get(string5);
                this.loadTexture(bufferedImage3, i2);
            }
            catch (IOException iOException4) {
                iOException4.printStackTrace();
            }
        }
    }

    public static void bind(int id) {
        if (id >= 0) {
            GL11.glBindTexture((int)3553, (int)id);
        }
    }
}

