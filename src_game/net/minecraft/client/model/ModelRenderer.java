/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client.model;

import net.minecraft.client.model.PositionTextureVertex;
import net.minecraft.client.model.TexturedQuad;
import net.minecraft.client.renderer.Tesselator;
import net.minecraft.world.phys.Vec3;
import org.lwjgl.opengl.GL11;

public final class ModelRenderer {
    private PositionTextureVertex[] corners;
    private TexturedQuad[] faces;
    private int textureOffsetX;
    private int textureOffsetY;
    public float x;
    public float y;
    public float z;
    public float xRot;
    public float yRot;
    public float zRot;
    private boolean compiled = false;
    private int displayList = 0;
    public boolean mirror = false;
    public boolean showModel = true;
    private boolean isHidden = false;

    public ModelRenderer(int i1, int i2) {
        this.textureOffsetX = i1;
        this.textureOffsetY = i2;
    }

    public final void addBox(float f1, float f2, float f3, int i4, int i5, int i6, float f7) {
        this.corners = new PositionTextureVertex[8];
        this.faces = new TexturedQuad[6];
        float f8 = f1 + (float)i4;
        float f9 = f2 + (float)i5;
        float f10 = f3 + (float)i6;
        f1 -= f7;
        f2 -= f7;
        f3 -= f7;
        f8 += f7;
        f9 += f7;
        f10 += f7;
        if (this.mirror) {
            f7 = f8;
            f8 = f1;
            f1 = f7;
        }
        PositionTextureVertex positionTextureVertex20 = new PositionTextureVertex(f1, f2, f3, 0.0f, 0.0f);
        PositionTextureVertex positionTextureVertex11 = new PositionTextureVertex(f8, f2, f3, 0.0f, 8.0f);
        PositionTextureVertex positionTextureVertex12 = new PositionTextureVertex(f8, f9, f3, 8.0f, 8.0f);
        PositionTextureVertex positionTextureVertex18 = new PositionTextureVertex(f1, f9, f3, 8.0f, 0.0f);
        PositionTextureVertex positionTextureVertex13 = new PositionTextureVertex(f1, f2, f10, 0.0f, 0.0f);
        PositionTextureVertex positionTextureVertex15 = new PositionTextureVertex(f8, f2, f10, 0.0f, 8.0f);
        PositionTextureVertex positionTextureVertex21 = new PositionTextureVertex(f8, f9, f10, 8.0f, 8.0f);
        PositionTextureVertex positionTextureVertex14 = new PositionTextureVertex(f1, f9, f10, 8.0f, 0.0f);
        this.corners[0] = positionTextureVertex20;
        this.corners[1] = positionTextureVertex11;
        this.corners[2] = positionTextureVertex12;
        this.corners[3] = positionTextureVertex18;
        this.corners[4] = positionTextureVertex13;
        this.corners[5] = positionTextureVertex15;
        this.corners[6] = positionTextureVertex21;
        this.corners[7] = positionTextureVertex14;
        this.faces[0] = new TexturedQuad(new PositionTextureVertex[]{positionTextureVertex15, positionTextureVertex11, positionTextureVertex12, positionTextureVertex21}, this.textureOffsetX + i6 + i4, this.textureOffsetY + i6, this.textureOffsetX + i6 + i4 + i6, this.textureOffsetY + i6 + i5);
        this.faces[1] = new TexturedQuad(new PositionTextureVertex[]{positionTextureVertex20, positionTextureVertex13, positionTextureVertex14, positionTextureVertex18}, this.textureOffsetX, this.textureOffsetY + i6, this.textureOffsetX + i6, this.textureOffsetY + i6 + i5);
        this.faces[2] = new TexturedQuad(new PositionTextureVertex[]{positionTextureVertex15, positionTextureVertex13, positionTextureVertex20, positionTextureVertex11}, this.textureOffsetX + i6, this.textureOffsetY, this.textureOffsetX + i6 + i4, this.textureOffsetY + i6);
        this.faces[3] = new TexturedQuad(new PositionTextureVertex[]{positionTextureVertex12, positionTextureVertex18, positionTextureVertex14, positionTextureVertex21}, this.textureOffsetX + i6 + i4, this.textureOffsetY, this.textureOffsetX + i6 + i4 + i4, this.textureOffsetY + i6);
        this.faces[4] = new TexturedQuad(new PositionTextureVertex[]{positionTextureVertex11, positionTextureVertex20, positionTextureVertex18, positionTextureVertex12}, this.textureOffsetX + i6, this.textureOffsetY + i6, this.textureOffsetX + i6 + i4, this.textureOffsetY + i6 + i5);
        this.faces[5] = new TexturedQuad(new PositionTextureVertex[]{positionTextureVertex13, positionTextureVertex15, positionTextureVertex21, positionTextureVertex14}, this.textureOffsetX + i6 + i4 + i6, this.textureOffsetY + i6, this.textureOffsetX + i6 + i4 + i6 + i4, this.textureOffsetY + i6 + i5);
        if (this.mirror) {
            int i16 = 0;
            while (i16 < this.faces.length) {
                TexturedQuad texturedQuad17 = this.faces[i16];
                PositionTextureVertex[] positionTextureVertex19 = new PositionTextureVertex[texturedQuad17.vertexPositions.length];
                i4 = 0;
                while (i4 < texturedQuad17.vertexPositions.length) {
                    positionTextureVertex19[i4] = texturedQuad17.vertexPositions[texturedQuad17.vertexPositions.length - i4 - 1];
                    ++i4;
                }
                texturedQuad17.vertexPositions = positionTextureVertex19;
                ++i16;
            }
        }
    }

    public final void setRotationPoint(float f1, float f2, float f3) {
        this.x = f1;
        this.y = f2;
        this.z = f3;
    }

    public final void render(float f1) {
        if (this.showModel) {
            if (!this.compiled) {
                this.compile(f1);
            }
            if (this.xRot == 0.0f && this.yRot == 0.0f && this.zRot == 0.0f) {
                if (this.x == 0.0f && this.y == 0.0f && this.z == 0.0f) {
                    GL11.glCallList((int)this.displayList);
                } else {
                    GL11.glTranslatef((float)(this.x * f1), (float)(this.y * f1), (float)(this.z * f1));
                    GL11.glCallList((int)this.displayList);
                    GL11.glTranslatef((float)(-this.x * f1), (float)(-this.y * f1), (float)(-this.z * f1));
                }
            } else {
                GL11.glPushMatrix();
                GL11.glTranslatef((float)(this.x * f1), (float)(this.y * f1), (float)(this.z * f1));
                if (this.zRot != 0.0f) {
                    GL11.glRotatef((float)(this.zRot * 57.295776f), (float)0.0f, (float)0.0f, (float)1.0f);
                }
                if (this.yRot != 0.0f) {
                    GL11.glRotatef((float)(this.yRot * 57.295776f), (float)0.0f, (float)1.0f, (float)0.0f);
                }
                if (this.xRot != 0.0f) {
                    GL11.glRotatef((float)(this.xRot * 57.295776f), (float)1.0f, (float)0.0f, (float)0.0f);
                }
                GL11.glCallList((int)this.displayList);
                GL11.glPopMatrix();
            }
        }
    }

    private void compile(float f1) {
        float f3 = f1;
        ModelRenderer modelRenderer2 = this;
        this.displayList = GL11.glGenLists((int)1);
        GL11.glNewList((int)this.displayList, (int)4864);
        Tesselator tessellator4 = Tesselator.instance;
        int i5 = 0;
        while (i5 < modelRenderer2.faces.length) {
            tessellator4.begin();
            TexturedQuad texturedQuad10000 = modelRenderer2.faces[i5];
            float f8 = f3;
            Tesselator tessellator7 = tessellator4;
            TexturedQuad texturedQuad6 = texturedQuad10000;
            Vec3 vec3D9 = texturedQuad10000.vertexPositions[1].vector3D.subtract(texturedQuad6.vertexPositions[0].vector3D).normalize();
            Vec3 vec3D10 = texturedQuad6.vertexPositions[1].vector3D.subtract(texturedQuad6.vertexPositions[2].vector3D).normalize();
            vec3D9 = new Vec3(vec3D9.y * vec3D10.z - vec3D9.z * vec3D10.y, vec3D9.z * vec3D10.x - vec3D9.x * vec3D10.z, vec3D9.x * vec3D10.y - vec3D9.y * vec3D10.x).normalize();
            Tesselator.setNormal(-vec3D9.x, -vec3D9.y, -vec3D9.z);
            int i11 = 0;
            while (i11 < 4) {
                PositionTextureVertex positionTextureVertex12 = texturedQuad6.vertexPositions[i11];
                tessellator7.vertexUV(positionTextureVertex12.vector3D.x * f8, positionTextureVertex12.vector3D.y * f8, positionTextureVertex12.vector3D.z * f8, positionTextureVertex12.texturePositionX, positionTextureVertex12.texturePositionY);
                ++i11;
            }
            tessellator4.end();
            ++i5;
        }
        GL11.glEndList();
        modelRenderer2.compiled = true;
    }

    public void postRender(float var1) {
        if (!this.isHidden && this.showModel) {
            if (!this.compiled) {
                this.compile(var1);
            }
            if (this.xRot == 0.0f && this.yRot == 0.0f && this.zRot == 0.0f) {
                if (this.x != 0.0f || this.y != 0.0f || this.z != 0.0f) {
                    GL11.glTranslatef((float)(this.x * var1), (float)(this.y * var1), (float)(this.z * var1));
                }
            } else {
                GL11.glTranslatef((float)(this.x * var1), (float)(this.y * var1), (float)(this.z * var1));
                if (this.zRot != 0.0f) {
                    GL11.glRotatef((float)(this.zRot * 57.295776f), (float)0.0f, (float)0.0f, (float)1.0f);
                }
                if (this.yRot != 0.0f) {
                    GL11.glRotatef((float)(this.yRot * 57.295776f), (float)0.0f, (float)1.0f, (float)0.0f);
                }
                if (this.xRot != 0.0f) {
                    GL11.glRotatef((float)(this.xRot * 57.295776f), (float)1.0f, (float)0.0f, (float)0.0f);
                }
            }
        }
    }
}

