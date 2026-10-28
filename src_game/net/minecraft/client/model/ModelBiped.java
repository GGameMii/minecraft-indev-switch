/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import util.Mth;

public class ModelBiped
extends ModelBase {
    public ModelRenderer bipedHead;
    public ModelRenderer bipedHeadwear;
    public ModelRenderer bipedBody;
    public ModelRenderer bipedRightArm;
    public ModelRenderer bipedLeftArm;
    public ModelRenderer bipedRightLeg;
    public ModelRenderer bipedLeftLeg;
    public ModelRenderer bipedCloak = new ModelRenderer(0, 0);

    public ModelBiped() {
        this(0.0f);
    }

    public ModelBiped(float scale) {
        this(scale, 0.0f);
    }

    private ModelBiped(float scale, float f2) {
        this.bipedCloak.addBox(-5.0f, 0.0f, -1.0f, 10, 16, 1, scale);
        this.bipedHead = new ModelRenderer(0, 0);
        this.bipedHead.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8, scale);
        this.bipedHead.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.bipedHeadwear = new ModelRenderer(32, 0);
        this.bipedHeadwear.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8, scale + 0.5f);
        this.bipedHeadwear.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.bipedBody = new ModelRenderer(16, 16);
        this.bipedBody.addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4, scale);
        this.bipedBody.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.bipedRightArm = new ModelRenderer(40, 16);
        this.bipedRightArm.addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4, scale);
        this.bipedRightArm.setRotationPoint(-5.0f, 2.0f, 0.0f);
        this.bipedLeftArm = new ModelRenderer(40, 16);
        this.bipedLeftArm.mirror = true;
        this.bipedLeftArm.addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4, scale);
        this.bipedLeftArm.setRotationPoint(5.0f, 2.0f, 0.0f);
        this.bipedRightLeg = new ModelRenderer(0, 16);
        this.bipedRightLeg.addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4, scale);
        this.bipedRightLeg.setRotationPoint(-2.0f, 12.0f, 0.0f);
        this.bipedLeftLeg = new ModelRenderer(0, 16);
        this.bipedLeftLeg.mirror = true;
        this.bipedLeftLeg.addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4, scale);
        this.bipedLeftLeg.setRotationPoint(2.0f, 12.0f, 0.0f);
    }

    @Override
    public final void render(float limbAngle, float animationSpeed, float f3, float headYaw, float headPitch, float f6) {
        this.setRotationAngles(limbAngle, animationSpeed, f3, headYaw, headPitch, 1.0f);
        this.bipedHead.render(1.0f);
        this.bipedBody.render(1.0f);
        this.bipedRightArm.render(1.0f);
        this.bipedLeftArm.render(1.0f);
        this.bipedRightLeg.render(1.0f);
        this.bipedLeftLeg.render(1.0f);
        this.bipedHeadwear.render(1.0f);
    }

    @Override
    public void setRotationAngles(float limbAngle, float animationSpeed, float f3, float headYaw, float headPitch, float f6) {
        this.bipedHead.yRot = headYaw / 57.295776f;
        this.bipedHead.xRot = headPitch / 57.295776f;
        this.bipedHeadwear.yRot = this.bipedHead.yRot;
        this.bipedHeadwear.xRot = this.bipedHead.xRot;
        this.bipedRightArm.xRot = Mth.cos(limbAngle * 0.6662f + (float)Math.PI) * 2.0f * animationSpeed;
        this.bipedRightArm.zRot = (Mth.cos(limbAngle * 0.2312f) + 1.0f) * animationSpeed;
        this.bipedLeftArm.xRot = Mth.cos(limbAngle * 0.6662f) * 2.0f * animationSpeed;
        this.bipedLeftArm.zRot = (Mth.cos(limbAngle * 0.2812f) - 1.0f) * animationSpeed;
        this.bipedRightLeg.xRot = Mth.cos(limbAngle * 0.6662f) * 1.4f * animationSpeed;
        this.bipedLeftLeg.xRot = Mth.cos(limbAngle * 0.6662f + (float)Math.PI) * 1.4f * animationSpeed;
        this.bipedRightArm.zRot += Mth.cos(f3 * 0.09f) * 0.05f + 0.05f;
        this.bipedLeftArm.zRot -= Mth.cos(f3 * 0.09f) * 0.05f + 0.05f;
        this.bipedRightArm.xRot += Mth.sin(f3 * 0.067f) * 0.05f;
        this.bipedLeftArm.xRot -= Mth.sin(f3 * 0.067f) * 0.05f;
        this.bipedRightArm.yRot = 0.0f;
        this.bipedLeftArm.yRot = 0.0f;
        if (this.swingProgress > -9990.0f) {
            float f7 = this.swingProgress;
            this.bipedBody.yRot = Mth.sin(Mth.sqrt(f7) * (float)Math.PI * 2.0f) * 0.2f;
            this.bipedRightArm.z = Mth.sin(this.bipedBody.yRot) * 5.0f;
            this.bipedRightArm.x = -Mth.cos(this.bipedBody.yRot) * 5.0f;
            this.bipedLeftArm.z = -Mth.sin(this.bipedBody.yRot) * 5.0f;
            this.bipedLeftArm.x = Mth.cos(this.bipedBody.yRot) * 5.0f;
            this.bipedRightArm.yRot += this.bipedBody.yRot;
            this.bipedLeftArm.yRot += this.bipedBody.yRot;
            this.bipedLeftArm.xRot += this.bipedBody.yRot;
            f7 = 1.0f - this.swingProgress;
            f7 *= f7;
            f7 *= f7;
            f7 = 1.0f - f7;
            float f8 = Mth.sin(f7 * (float)Math.PI);
            float f9 = Mth.sin(this.swingProgress * (float)Math.PI) * -(this.bipedHead.xRot - 0.7f) * 0.75f;
            this.bipedRightArm.xRot = (float)((double)this.bipedRightArm.xRot - ((double)f8 * 1.2 + (double)f9));
            this.bipedRightArm.yRot += this.bipedBody.yRot * 2.0f;
            this.bipedRightArm.zRot += Mth.sin(this.swingProgress * (float)Math.PI) * -0.4f;
        }
        this.bipedBody.xRot = 0.0f;
        this.bipedRightLeg.z = 0.0f;
        this.bipedLeftLeg.z = 0.0f;
        this.bipedRightLeg.y = 12.0f;
        this.bipedLeftLeg.y = 12.0f;
        this.bipedHead.y = 0.0f;
    }
}

