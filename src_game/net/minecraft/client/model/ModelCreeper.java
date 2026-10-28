/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import util.Mth;

public final class ModelCreeper
extends ModelBase {
    private ModelRenderer head = new ModelRenderer(0, 0);
    private ModelRenderer headwear;
    private ModelRenderer body;
    private ModelRenderer leg1;
    private ModelRenderer leg2;
    private ModelRenderer leg3;
    private ModelRenderer leg4;

    public ModelCreeper() {
        this.head.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8, 0.0f);
        this.head.setRotationPoint(0.0f, 4.0f, 0.0f);
        this.headwear = new ModelRenderer(32, 0);
        this.headwear.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8, 0.5f);
        this.headwear.setRotationPoint(0.0f, 4.0f, 0.0f);
        this.body = new ModelRenderer(16, 16);
        this.body.addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4, 0.0f);
        this.body.setRotationPoint(0.0f, 4.0f, 0.0f);
        this.leg1 = new ModelRenderer(0, 16);
        this.leg1.addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4, 0.0f);
        this.leg1.setRotationPoint(-2.0f, 16.0f, 4.0f);
        this.leg2 = new ModelRenderer(0, 16);
        this.leg2.addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4, 0.0f);
        this.leg2.setRotationPoint(2.0f, 16.0f, 4.0f);
        this.leg3 = new ModelRenderer(0, 16);
        this.leg3.addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4, 0.0f);
        this.leg3.setRotationPoint(-2.0f, 16.0f, -4.0f);
        this.leg4 = new ModelRenderer(0, 16);
        this.leg4.addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4, 0.0f);
        this.leg4.setRotationPoint(2.0f, 16.0f, -4.0f);
    }

    @Override
    public final void render(float limbAngle, float animationSpeed, float f3, float headYaw, float headPitch, float f6) {
        this.setRotationAngles(limbAngle, animationSpeed, f3, headYaw, headPitch, 1.0f);
        this.head.render(1.0f);
        this.body.render(1.0f);
        this.leg1.render(1.0f);
        this.leg2.render(1.0f);
        this.leg3.render(1.0f);
        this.leg4.render(1.0f);
    }

    @Override
    public final void setRotationAngles(float limbAngle, float animationSpeed, float f3, float headYaw, float headPitch, float f6) {
        this.head.yRot = headYaw / 57.295776f;
        this.head.xRot = headPitch / 57.295776f;
        this.leg1.xRot = Mth.cos(limbAngle * 0.6662f) * 1.4f * animationSpeed;
        this.leg2.xRot = Mth.cos(limbAngle * 0.6662f + (float)Math.PI) * 1.4f * animationSpeed;
        this.leg3.xRot = Mth.cos(limbAngle * 0.6662f + (float)Math.PI) * 1.4f * animationSpeed;
        this.leg4.xRot = Mth.cos(limbAngle * 0.6662f) * 1.4f * animationSpeed;
    }
}

