/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.model;

import net.minecraft.client.model.ModelQuadruped;
import net.minecraft.client.model.ModelRenderer;

public final class ModelSheepFur
extends ModelQuadruped {
    public ModelSheepFur() {
        super(12, 0.0f);
        this.head = new ModelRenderer(0, 0);
        this.head.addBox(-3.0f, -4.0f, -4.0f, 6, 6, 6, 0.6f);
        this.head.setRotationPoint(0.0f, 6.0f, -8.0f);
        this.body = new ModelRenderer(28, 8);
        this.body.addBox(-4.0f, -10.0f, -7.0f, 8, 16, 6, 1.75f);
        this.body.setRotationPoint(0.0f, 5.0f, 2.0f);
        this.leg1 = new ModelRenderer(0, 16);
        this.leg1.addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4, 0.5f);
        this.leg1.setRotationPoint(-3.0f, 12.0f, 7.0f);
        this.leg2 = new ModelRenderer(0, 16);
        this.leg2.addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4, 0.5f);
        this.leg2.setRotationPoint(3.0f, 12.0f, 7.0f);
        this.leg3 = new ModelRenderer(0, 16);
        this.leg3.addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4, 0.5f);
        this.leg3.setRotationPoint(-3.0f, 12.0f, -5.0f);
        this.leg4 = new ModelRenderer(0, 16);
        this.leg4.addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4, 0.5f);
        this.leg4.setRotationPoint(3.0f, 12.0f, -5.0f);
    }
}

