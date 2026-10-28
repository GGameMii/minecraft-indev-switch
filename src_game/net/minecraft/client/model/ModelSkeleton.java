/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.model;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelZombie;

public final class ModelSkeleton
extends ModelZombie {
    public ModelSkeleton() {
        this.bipedRightArm = new ModelRenderer(40, 16);
        this.bipedRightArm.addBox(-1.0f, -2.0f, -1.0f, 2, 12, 2, 0.0f);
        this.bipedRightArm.setRotationPoint(-5.0f, 2.0f, 0.0f);
        this.bipedLeftArm = new ModelRenderer(40, 16);
        this.bipedLeftArm.mirror = true;
        this.bipedLeftArm.addBox(-1.0f, -2.0f, -1.0f, 2, 12, 2, 0.0f);
        this.bipedLeftArm.setRotationPoint(5.0f, 2.0f, 0.0f);
        this.bipedRightLeg = new ModelRenderer(0, 16);
        this.bipedRightLeg.addBox(-1.0f, 0.0f, -1.0f, 2, 12, 2, 0.0f);
        this.bipedRightLeg.setRotationPoint(-2.0f, 12.0f, 0.0f);
        this.bipedLeftLeg = new ModelRenderer(0, 16);
        this.bipedLeftLeg.mirror = true;
        this.bipedLeftLeg.addBox(-1.0f, 0.0f, -1.0f, 2, 12, 2, 0.0f);
        this.bipedLeftLeg.setRotationPoint(2.0f, 12.0f, 0.0f);
    }
}

