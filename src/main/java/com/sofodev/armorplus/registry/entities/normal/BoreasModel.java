package com.sofodev.armorplus.registry.entities.normal;

import com.sofodev.armorplus.ArmorPlus;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BoreasModel<T extends MobEntity & IAnimatable> extends AnimatedGeoModel<T> {

    @Override
    public ResourceLocation getModelLocation(T object) {
        return new ResourceLocation(ArmorPlus.MODID, "geo/boreas.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(T object) {
        return new ResourceLocation(ArmorPlus.MODID, "textures/entity/boreas.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(T object) {
        return new ResourceLocation(ArmorPlus.MODID, "animations/boreas.animation.json");
    }
}