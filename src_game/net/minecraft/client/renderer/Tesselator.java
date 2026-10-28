/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.BufferUtils
 *  org.lwjgl.opengl.ARBVertexBufferObject
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client.renderer;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.ARBVertexBufferObject;
import org.lwjgl.opengl.GL11;

public final class Tesselator {
    private static boolean convertQuadsToTriangles = false;
    private static boolean tryVBO = false;
    private ByteBuffer byteBuffer = BufferUtils.createByteBuffer((int)0x800000);
    private int[] rawBuffer = new int[0x200000];
    private int vertexCount = 0;
    private float textureU;
    private float textureV;
    private int color;
    private boolean hasColor = false;
    private boolean hasTexture = false;
    private int rawBufferIndex = 0;
    private int addedVertices = 0;
    private boolean isColorDisabled = false;
    private int drawMode;
    public static Tesselator instance = new Tesselator();
    private boolean isDrawing = false;
    private boolean useVBO = false;
    private IntBuffer vertexBuffers;
    private int vboIndex = 0;
    private int vboCount = 10;

    private Tesselator() {
        if (this.useVBO) {
            this.vertexBuffers = BufferUtils.createIntBuffer((int)this.vboCount);
            ARBVertexBufferObject.glGenBuffersARB((IntBuffer)this.vertexBuffers);
        }
    }

    public final void end() {
        if (!this.isDrawing) {
            throw new IllegalStateException("Not tesselating!");
        }
        this.isDrawing = false;
        if (this.vertexCount > 0) {
            IntBuffer intBuffer1 = this.byteBuffer.asIntBuffer();
            FloatBuffer floatBuffer2 = this.byteBuffer.asFloatBuffer();
            intBuffer1.clear();
            intBuffer1.put(this.rawBuffer, 0, this.rawBufferIndex);
            this.byteBuffer.position(0);
            this.byteBuffer.limit(this.rawBufferIndex << 2);
            if (this.useVBO) {
                this.vboIndex = (this.vboIndex + 1) % this.vboCount;
                ARBVertexBufferObject.glBindBufferARB((int)34962, (int)this.vertexBuffers.get(this.vboIndex));
                ARBVertexBufferObject.glBufferDataARB((int)34962, (ByteBuffer)this.byteBuffer, (int)35040);
            }
            if (this.hasTexture) {
                if (this.useVBO) {
                    GL11.glTexCoordPointer((int)2, (int)5126, (int)32, (long)12L);
                } else {
                    floatBuffer2.position(3);
                    GL11.glTexCoordPointer((int)2, (int)32, (FloatBuffer)floatBuffer2);
                }
                GL11.glEnableClientState((int)32888);
            }
            if (this.hasColor) {
                if (this.useVBO) {
                    GL11.glColorPointer((int)4, (int)5121, (int)32, (long)20L);
                } else {
                    this.byteBuffer.position(20);
                    GL11.glColorPointer((int)4, (boolean)true, (int)32, (ByteBuffer)this.byteBuffer);
                }
                GL11.glEnableClientState((int)32886);
            }
            if (this.useVBO) {
                GL11.glVertexPointer((int)3, (int)5126, (int)32, (long)0L);
            } else {
                floatBuffer2.position(0);
                GL11.glVertexPointer((int)3, (int)32, (FloatBuffer)floatBuffer2);
            }
            GL11.glEnableClientState((int)32884);
            GL11.glDrawArrays((int)this.drawMode, (int)0, (int)this.vertexCount);
            GL11.glDisableClientState((int)32884);
            if (this.hasTexture) {
                GL11.glDisableClientState((int)32888);
            }
            if (this.hasColor) {
                GL11.glDisableClientState((int)32886);
            }
        }
        this.reset();
    }

    private void reset() {
        this.vertexCount = 0;
        this.byteBuffer.clear();
        this.rawBufferIndex = 0;
        this.addedVertices = 0;
    }

    public final void begin() {
        this.startDrawing(7);
    }

    public final void startDrawing(int i1) {
        if (this.isDrawing) {
            throw new IllegalStateException("Already tesselating!");
        }
        this.isDrawing = true;
        this.reset();
        this.drawMode = i1;
        this.hasColor = false;
        this.hasTexture = false;
        this.isColorDisabled = false;
    }

    public final void setColorOpaque_F(float f1, float f2, float f3) {
        this.setColorOpaque((int)(f1 * 255.0f), (int)(f2 * 255.0f), (int)(f3 * 255.0f));
    }

    public final void setColorRGBA_F(float f1, float f2, float f3, float f4) {
        this.setColorRGBA((int)(f1 * 255.0f), (int)(f2 * 255.0f), (int)(f3 * 255.0f), (int)(f4 * 255.0f));
    }

    private void setColorOpaque(int i1, int i2, int i3) {
        this.setColorRGBA(i1, i2, i3, 255);
    }

    private void setColorRGBA(int i1, int i2, int i3, int i4) {
        if (!this.isColorDisabled) {
            if (i1 > 255) {
                i1 = 255;
            }
            if (i2 > 255) {
                i2 = 255;
            }
            if (i3 > 255) {
                i3 = 255;
            }
            if (i4 > 255) {
                i4 = 255;
            }
            if (i1 < 0) {
                i1 = 0;
            }
            if (i2 < 0) {
                i2 = 0;
            }
            if (i3 < 0) {
                i3 = 0;
            }
            if (i4 < 0) {
                i4 = 0;
            }
            this.hasColor = true;
            this.color = i4 << 24 | i3 << 16 | i2 << 8 | i1;
        }
    }

    public final void vertexUV(float f1, float f2, float f3, float f4, float f5) {
        this.hasTexture = true;
        this.textureU = f4;
        this.textureV = f5;
        this.addVertex(f1, f2, f3);
    }

    public final void addVertex(float f1, float f2, float f3) {
        ++this.addedVertices;
        if (this.hasTexture) {
            this.rawBuffer[this.rawBufferIndex + 3] = Float.floatToRawIntBits(this.textureU);
            this.rawBuffer[this.rawBufferIndex + 4] = Float.floatToRawIntBits(this.textureV);
        }
        if (this.hasColor) {
            this.rawBuffer[this.rawBufferIndex + 5] = this.color;
        }
        this.rawBuffer[this.rawBufferIndex] = Float.floatToRawIntBits(f1);
        this.rawBuffer[this.rawBufferIndex + 1] = Float.floatToRawIntBits(f2);
        this.rawBuffer[this.rawBufferIndex + 2] = Float.floatToRawIntBits(f3);
        this.rawBufferIndex += 8;
        ++this.vertexCount;
        if (this.vertexCount % 4 == 0 && this.rawBufferIndex >= 2097120) {
            this.end();
        }
    }

    public final void color(int i1) {
        int i2 = i1 >> 16 & 0xFF;
        int i3 = i1 >> 8 & 0xFF;
        this.setColorOpaque(i2, i3, i1 &= 0xFF);
    }

    public final void disableColor() {
        this.isColorDisabled = true;
    }

    public static void setNormal(float f0, float f1, float f2) {
        GL11.glNormal3f((float)f0, (float)f1, (float)f2);
    }
}

