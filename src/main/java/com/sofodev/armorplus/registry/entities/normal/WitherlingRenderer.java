package com.sofodev.armorplus.registry.entities.normal;

import com.sofodev.armorplus.registry.entities.bosses.SkeletalKingAnimatedModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

/**
 * @author Sokratis Fotkatzikis
 **/
public class WitherlingRenderer extends GeoEntityRenderer<WitherlingEntity> {
    public WitherlingRenderer(EntityRendererManager renderManager) {
        super(renderManager, new SkeletalKingAnimatedModel<>());
    }
}