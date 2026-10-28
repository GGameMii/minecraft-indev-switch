/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.model;

import net.minecraft.client.model.ModelBiped;
import util.Mth;

public class ModelZombie
extends ModelBiped {
    @Override
    public final void setRotationAngles(float limbAngle, float animationSpeed, float f3, float headYaw, float headPitch, float f6) {
        super.setRotationAngles(limbAngle, animationSpeed, f3, headYaw, headPitch, f6);
        limbAngle = Mth.sin(0.0f);
        animationSpeed = Mth.sin(0.0f);
        this.bipedRightArm.zRot = 0.0f;
        this.bipedLeftArm.zRot = 0.0f;
        this.bipedRightArm.yRot = -(0.1f - limbAngle * 0.6f);
        this.bipedLeftArm.yRot = 0.1f - limbAngle * 0.6f;
        this.bipedRightArm.xRot = -1.5707964f;
        this.bipedLeftArm.xRot = -1.5707964f;
        this.bipedRightArm.xRot -= limbAngle * 1.2f - animationSpeed * 0.4f;
        this.bipedLeftArm.xRot -= limbAngle * 1.2f - animationSpeed * 0.4f;
        this.bipedRightArm.zRot += Mth.cos(f3 * 0.09f) * 0.05f + 0.05f;
        this.bipedLeftArm.zRot -= Mth.cos(f3 * 0.09f) * 0.05f + 0.05f;
        this.bipedRightArm.xRot += Mth.sin(f3 * 0.067f) * 0.05f;
        this.bipedLeftArm.xRot -= Mth.sin(f3 * 0.067f) * 0.05f;
    }
}

