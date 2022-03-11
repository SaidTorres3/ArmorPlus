package com.sofodev.armorplus.registry.blocks.ore;

import net.minecraft.block.OreBlock;
import net.minecraft.block.material.Material;

import static net.minecraftforge.common.ToolType.PICKAXE;

public class CrystalOreBlock extends OreBlock {

    public CrystalOreBlock(Variant variant) {
        super(Properties.of(Material.STONE).strength(variant.getHardness(), variant.getResistance()).requiresCorrectToolForDrops()
                .lightLevel((light) -> variant.getLightValue()).harvestTool(PICKAXE).harvestLevel(variant.getHarvestLevel())
        );
    }

}