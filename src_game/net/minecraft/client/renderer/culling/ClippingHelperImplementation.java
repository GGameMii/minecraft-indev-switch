/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.BufferUtils
 *  org.lwjgl.opengl.GL11
 */
package net.minecraft.client.renderer.culling;

import java.nio.FloatBuffer;
import net.minecraft.client.renderer.culling.ClippingHelper;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import util.Mth;

public final class ClippingHelperImplementation
extends ClippingHelper {
    private static ClippingHelperImplementation instance = new ClippingHelperImplementation();
    private FloatBuffer projectionMatrixBuffer = BufferUtils.createFloatBuffer((int)16);
    private FloatBuffer modelviewMatrixBuffer = BufferUtils.createFloatBuffer((int)16);
    private FloatBuffer ICamera = BufferUtils.createFloatBuffer((int)16);

    public static ClippingHelper init() {
        ClippingHelperImplementation a2 = instance;
        ClippingHelperImplementation.instance.projectionMatrixBuffer.clear();
        a2.modelviewMatrixBuffer.clear();
        a2.ICamera.clear();
        GL11.glGetFloat((int)2983, (FloatBuffer)a2.projectionMatrixBuffer);
        GL11.glGetFloat((int)2982, (FloatBuffer)a2.modelviewMatrixBuffer);
        a2.projectionMatrixBuffer.flip().limit(16);
        a2.projectionMatrixBuffer.get(a2.projectionMatrix);
        a2.modelviewMatrixBuffer.flip().limit(16);
        a2.modelviewMatrixBuffer.get(a2.modelviewMatrix);
        a2.clippingMatrix[0] = a2.modelviewMatrix[0] * a2.projectionMatrix[0] + a2.modelviewMatrix[1] * a2.projectionMatrix[4] + a2.modelviewMatrix[2] * a2.projectionMatrix[8] + a2.modelviewMatrix[3] * a2.projectionMatrix[12];
        a2.clippingMatrix[1] = a2.modelviewMatrix[0] * a2.projectionMatrix[1] + a2.modelviewMatrix[1] * a2.projectionMatrix[5] + a2.modelviewMatrix[2] * a2.projectionMatrix[9] + a2.modelviewMatrix[3] * a2.projectionMatrix[13];
        a2.clippingMatrix[2] = a2.modelviewMatrix[0] * a2.projectionMatrix[2] + a2.modelviewMatrix[1] * a2.projectionMatrix[6] + a2.modelviewMatrix[2] * a2.projectionMatrix[10] + a2.modelviewMatrix[3] * a2.projectionMatrix[14];
        a2.clippingMatrix[3] = a2.modelviewMatrix[0] * a2.projectionMatrix[3] + a2.modelviewMatrix[1] * a2.projectionMatrix[7] + a2.modelviewMatrix[2] * a2.projectionMatrix[11] + a2.modelviewMatrix[3] * a2.projectionMatrix[15];
        a2.clippingMatrix[4] = a2.modelviewMatrix[4] * a2.projectionMatrix[0] + a2.modelviewMatrix[5] * a2.projectionMatrix[4] + a2.modelviewMatrix[6] * a2.projectionMatrix[8] + a2.modelviewMatrix[7] * a2.projectionMatrix[12];
        a2.clippingMatrix[5] = a2.modelviewMatrix[4] * a2.projectionMatrix[1] + a2.modelviewMatrix[5] * a2.projectionMatrix[5] + a2.modelviewMatrix[6] * a2.projectionMatrix[9] + a2.modelviewMatrix[7] * a2.projectionMatrix[13];
        a2.clippingMatrix[6] = a2.modelviewMatrix[4] * a2.projectionMatrix[2] + a2.modelviewMatrix[5] * a2.projectionMatrix[6] + a2.modelviewMatrix[6] * a2.projectionMatrix[10] + a2.modelviewMatrix[7] * a2.projectionMatrix[14];
        a2.clippingMatrix[7] = a2.modelviewMatrix[4] * a2.projectionMatrix[3] + a2.modelviewMatrix[5] * a2.projectionMatrix[7] + a2.modelviewMatrix[6] * a2.projectionMatrix[11] + a2.modelviewMatrix[7] * a2.projectionMatrix[15];
        a2.clippingMatrix[8] = a2.modelviewMatrix[8] * a2.projectionMatrix[0] + a2.modelviewMatrix[9] * a2.projectionMatrix[4] + a2.modelviewMatrix[10] * a2.projectionMatrix[8] + a2.modelviewMatrix[11] * a2.projectionMatrix[12];
        a2.clippingMatrix[9] = a2.modelviewMatrix[8] * a2.projectionMatrix[1] + a2.modelviewMatrix[9] * a2.projectionMatrix[5] + a2.modelviewMatrix[10] * a2.projectionMatrix[9] + a2.modelviewMatrix[11] * a2.projectionMatrix[13];
        a2.clippingMatrix[10] = a2.modelviewMatrix[8] * a2.projectionMatrix[2] + a2.modelviewMatrix[9] * a2.projectionMatrix[6] + a2.modelviewMatrix[10] * a2.projectionMatrix[10] + a2.modelviewMatrix[11] * a2.projectionMatrix[14];
        a2.clippingMatrix[11] = a2.modelviewMatrix[8] * a2.projectionMatrix[3] + a2.modelviewMatrix[9] * a2.projectionMatrix[7] + a2.modelviewMatrix[10] * a2.projectionMatrix[11] + a2.modelviewMatrix[11] * a2.projectionMatrix[15];
        a2.clippingMatrix[12] = a2.modelviewMatrix[12] * a2.projectionMatrix[0] + a2.modelviewMatrix[13] * a2.projectionMatrix[4] + a2.modelviewMatrix[14] * a2.projectionMatrix[8] + a2.modelviewMatrix[15] * a2.projectionMatrix[12];
        a2.clippingMatrix[13] = a2.modelviewMatrix[12] * a2.projectionMatrix[1] + a2.modelviewMatrix[13] * a2.projectionMatrix[5] + a2.modelviewMatrix[14] * a2.projectionMatrix[9] + a2.modelviewMatrix[15] * a2.projectionMatrix[13];
        a2.clippingMatrix[14] = a2.modelviewMatrix[12] * a2.projectionMatrix[2] + a2.modelviewMatrix[13] * a2.projectionMatrix[6] + a2.modelviewMatrix[14] * a2.projectionMatrix[10] + a2.modelviewMatrix[15] * a2.projectionMatrix[14];
        a2.clippingMatrix[15] = a2.modelviewMatrix[12] * a2.projectionMatrix[3] + a2.modelviewMatrix[13] * a2.projectionMatrix[7] + a2.modelviewMatrix[14] * a2.projectionMatrix[11] + a2.modelviewMatrix[15] * a2.projectionMatrix[15];
        a2.frustrum[0][0] = a2.clippingMatrix[3] - a2.clippingMatrix[0];
        a2.frustrum[0][1] = a2.clippingMatrix[7] - a2.clippingMatrix[4];
        a2.frustrum[0][2] = a2.clippingMatrix[11] - a2.clippingMatrix[8];
        a2.frustrum[0][3] = a2.clippingMatrix[15] - a2.clippingMatrix[12];
        ClippingHelperImplementation.normalize(a2.frustrum, 0);
        a2.frustrum[1][0] = a2.clippingMatrix[3] + a2.clippingMatrix[0];
        a2.frustrum[1][1] = a2.clippingMatrix[7] + a2.clippingMatrix[4];
        a2.frustrum[1][2] = a2.clippingMatrix[11] + a2.clippingMatrix[8];
        a2.frustrum[1][3] = a2.clippingMatrix[15] + a2.clippingMatrix[12];
        ClippingHelperImplementation.normalize(a2.frustrum, 1);
        a2.frustrum[2][0] = a2.clippingMatrix[3] + a2.clippingMatrix[1];
        a2.frustrum[2][1] = a2.clippingMatrix[7] + a2.clippingMatrix[5];
        a2.frustrum[2][2] = a2.clippingMatrix[11] + a2.clippingMatrix[9];
        a2.frustrum[2][3] = a2.clippingMatrix[15] + a2.clippingMatrix[13];
        ClippingHelperImplementation.normalize(a2.frustrum, 2);
        a2.frustrum[3][0] = a2.clippingMatrix[3] - a2.clippingMatrix[1];
        a2.frustrum[3][1] = a2.clippingMatrix[7] - a2.clippingMatrix[5];
        a2.frustrum[3][2] = a2.clippingMatrix[11] - a2.clippingMatrix[9];
        a2.frustrum[3][3] = a2.clippingMatrix[15] - a2.clippingMatrix[13];
        ClippingHelperImplementation.normalize(a2.frustrum, 3);
        a2.frustrum[4][0] = a2.clippingMatrix[3] - a2.clippingMatrix[2];
        a2.frustrum[4][1] = a2.clippingMatrix[7] - a2.clippingMatrix[6];
        a2.frustrum[4][2] = a2.clippingMatrix[11] - a2.clippingMatrix[10];
        a2.frustrum[4][3] = a2.clippingMatrix[15] - a2.clippingMatrix[14];
        ClippingHelperImplementation.normalize(a2.frustrum, 4);
        a2.frustrum[5][0] = a2.clippingMatrix[3] + a2.clippingMatrix[2];
        a2.frustrum[5][1] = a2.clippingMatrix[7] + a2.clippingMatrix[6];
        a2.frustrum[5][2] = a2.clippingMatrix[11] + a2.clippingMatrix[10];
        a2.frustrum[5][3] = a2.clippingMatrix[15] + a2.clippingMatrix[14];
        ClippingHelperImplementation.normalize(a2.frustrum, 5);
        return instance;
    }

    private static void normalize(float[][] f0, int i1) {
        float f2 = Mth.sqrt(f0[i1][0] * f0[i1][0] + f0[i1][1] * f0[i1][1] + f0[i1][2] * f0[i1][2]);
        float[] fArray = f0[i1];
        fArray[0] = fArray[0] / f2;
        float[] fArray2 = f0[i1];
        fArray2[1] = fArray2[1] / f2;
        float[] fArray3 = f0[i1];
        fArray3[2] = fArray3[2] / f2;
        float[] fArray4 = f0[i1];
        fArray4[3] = fArray4[3] / f2;
    }
}

