/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import util.Mth;

public final class ModelSpider
extends ModelBase {
    private ModelRenderer spiderHead = new ModelRenderer(32, 4);
    private ModelRenderer spiderNeck;
    private ModelRenderer spiderBody;
    private ModelRenderer spiderLeg1;
    private ModelRenderer spiderLeg2;
    private ModelRenderer spiderLeg3;
    private ModelRenderer spiderLeg4;
    private ModelRenderer spiderLeg5;
    private ModelRenderer spiderLeg6;
    private ModelRenderer spiderLeg7;
    private ModelRenderer spiderLeg8;

    public ModelSpider() {
        this.spiderHead.addBox(-4.0f, -4.0f, -8.0f, 8, 8, 8, 0.0f);
        this.spiderHead.setRotationPoint(0.0f, 15.0f, -3.0f);
        this.spiderNeck = new ModelRenderer(0, 0);
        this.spiderNeck.addBox(-3.0f, -3.0f, -3.0f, 6, 6, 6, 0.0f);
        this.spiderNeck.setRotationPoint(0.0f, 15.0f, 0.0f);
        this.spiderBody = new ModelRenderer(0, 12);
        this.spiderBody.addBox(-5.0f, -4.0f, -6.0f, 10, 8, 12, 0.0f);
        this.spiderBody.setRotationPoint(0.0f, 15.0f, 9.0f);
        this.spiderLeg1 = new ModelRenderer(18, 0);
        this.spiderLeg1.addBox(-15.0f, -1.0f, -1.0f, 16, 2, 2, 0.0f);
        this.spiderLeg1.setRotationPoint(-4.0f, 15.0f, 2.0f);
        this.spiderLeg2 = new ModelRenderer(18, 0);
        this.spiderLeg2.addBox(-1.0f, -1.0f, -1.0f, 16, 2, 2, 0.0f);
        this.spiderLeg2.setRotationPoint(4.0f, 15.0f, 2.0f);
        this.spiderLeg3 = new ModelRenderer(18, 0);
        this.spiderLeg3.addBox(-15.0f, -1.0f, -1.0f, 16, 2, 2, 0.0f);
        this.spiderLeg3.setRotationPoint(-4.0f, 15.0f, 1.0f);
        this.spiderLeg4 = new ModelRenderer(18, 0);
        this.spiderLeg4.addBox(-1.0f, -1.0f, -1.0f, 16, 2, 2, 0.0f);
        this.spiderLeg4.setRotationPoint(4.0f, 15.0f, 1.0f);
        this.spiderLeg5 = new ModelRenderer(18, 0);
        this.spiderLeg5.addBox(-15.0f, -1.0f, -1.0f, 16, 2, 2, 0.0f);
        this.spiderLeg5.setRotationPoint(-4.0f, 15.0f, 0.0f);
        this.spiderLeg6 = new ModelRenderer(18, 0);
        this.spiderLeg6.addBox(-1.0f, -1.0f, -1.0f, 16, 2, 2, 0.0f);
        this.spiderLeg6.setRotationPoint(4.0f, 15.0f, 0.0f);
        this.spiderLeg7 = new ModelRenderer(18, 0);
        this.spiderLeg7.addBox(-15.0f, -1.0f, -1.0f, 16, 2, 2, 0.0f);
        this.spiderLeg7.setRotationPoint(-4.0f, 15.0f, -1.0f);
        this.spiderLeg8 = new ModelRenderer(18, 0);
        this.spiderLeg8.addBox(-1.0f, -1.0f, -1.0f, 16, 2, 2, 0.0f);
        this.spiderLeg8.setRotationPoint(4.0f, 15.0f, -1.0f);
    }

    @Override
    public final void render(float limbAngle, float animationSpeed, float f3, float headYaw, float headPitch, float f6) {
        this.setRotationAngles(limbAngle, animationSpeed, f3, headYaw, headPitch, 1.0f);
        this.spiderHead.render(1.0f);
        this.spiderNeck.render(1.0f);
        this.spiderBody.render(1.0f);
        this.spiderLeg1.render(1.0f);
        this.spiderLeg2.render(1.0f);
        this.spiderLeg3.render(1.0f);
        this.spiderLeg4.render(1.0f);
        this.spiderLeg5.render(1.0f);
        this.spiderLeg6.render(1.0f);
        this.spiderLeg7.render(1.0f);
        this.spiderLeg8.render(1.0f);
    }

    @Override
    public final void setRotationAngles(float limbAngle, float animationSpeed, float f3, float headYaw, float headPitch, float f6) {
        this.spiderHead.yRot = headYaw / 57.295776f;
        this.spiderHead.xRot = headPitch / 57.295776f;
        this.spiderLeg1.zRot = -0.7853982f;
        this.spiderLeg2.zRot = 0.7853982f;
        this.spiderLeg3.zRot = -0.58119464f;
        this.spiderLeg4.zRot = 0.58119464f;
        this.spiderLeg5.zRot = -0.58119464f;
        this.spiderLeg6.zRot = 0.58119464f;
        this.spiderLeg7.zRot = -0.7853982f;
        this.spiderLeg8.zRot = 0.7853982f;
        this.spiderLeg1.yRot = 0.7853982f;
        this.spiderLeg2.yRot = -0.7853982f;
        this.spiderLeg3.yRot = 0.3926991f;
        this.spiderLeg4.yRot = -0.3926991f;
        this.spiderLeg5.yRot = -0.3926991f;
        this.spiderLeg6.yRot = 0.3926991f;
        this.spiderLeg7.yRot = -0.7853982f;
        this.spiderLeg8.yRot = 0.7853982f;
        f3 = -(Mth.cos(limbAngle * 0.6662f * 2.0f) * 0.4f) * animationSpeed;
        headYaw = -(Mth.cos(limbAngle * 0.6662f * 2.0f + (float)Math.PI) * 0.4f) * animationSpeed;
        headPitch = -(Mth.cos(limbAngle * 0.6662f * 2.0f + 1.5707964f) * 0.4f) * animationSpeed;
        f6 = -(Mth.cos(limbAngle * 0.6662f * 2.0f + 4.712389f) * 0.4f) * animationSpeed;
        float f7 = Math.abs(Mth.sin(limbAngle * 0.6662f) * 0.4f) * animationSpeed;
        float f8 = Math.abs(Mth.sin(limbAngle * 0.6662f + (float)Math.PI) * 0.4f) * animationSpeed;
        float f9 = Math.abs(Mth.sin(limbAngle * 0.6662f + 1.5707964f) * 0.4f) * animationSpeed;
        limbAngle = Math.abs(Mth.sin(limbAngle * 0.6662f + 4.712389f) * 0.4f) * animationSpeed;
        this.spiderLeg1.yRot += f3;
        this.spiderLeg2.yRot -= f3;
        this.spiderLeg3.yRot += headYaw;
        this.spiderLeg4.yRot -= headYaw;
        this.spiderLeg5.yRot += headPitch;
        this.spiderLeg6.yRot -= headPitch;
        this.spiderLeg7.yRot += f6;
        this.spiderLeg8.yRot -= f6;
        this.spiderLeg1.zRot += f7;
        this.spiderLeg2.zRot -= f7;
        this.spiderLeg3.zRot += f8;
        this.spiderLeg4.zRot -= f8;
        this.spiderLeg5.zRot += f9;
        this.spiderLeg6.zRot -= f9;
        this.spiderLeg7.zRot += limbAngle;
        this.spiderLeg8.zRot -= limbAngle;
    }
}

