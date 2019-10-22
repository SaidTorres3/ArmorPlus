/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.registry.blocks.lava;

import com.sofodev.armorplus.common.iface.IModdedBlock;
import com.sofodev.armorplus.common.registry.ModItems;
import com.sofodev.armorplus.common.registry.blocks.BlockProperties;
import com.sofodev.armorplus.common.registry.blocks.base.BlockBase;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.Random;
import java.util.stream.IntStream;

import static com.sofodev.armorplus.common.config.ModConfig.RegistryConfig.blocks;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * @author Sokratis Fotkatzikis
 */
public class BlockCrystalOre extends BlockBase implements IModdedBlock {

    public BlockCrystalOre() {
        super(Material.ROCK, "ore_lava_crystal", new BlockProperties(2000.0F, 25.0F, blocks.ore_lava_crystal.props, 0.8F));
    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        Item item = ModItems.itemLavaCrystal;
        Random rand;
        rand = world instanceof World ? ((World) world).rand : RANDOM;
        int count = quantityDropped(state, fortune, rand);
        IntStream.range(0, count).mapToObj(i -> getItemStack(item)).forEachOrdered(drops::add);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void initModel() {
        this.initModel(0, "normal");
    }

    @Override
    public int quantityDropped(IBlockState blockstate, int fortune, @Nonnull Random random) {
        return 1 + random.nextInt(1 + fortune);
    }

    /**
     * Get the MapColor for this Block and the given BlockState
     */
    @Override
    @SuppressWarnings("deprecation")
    public MapColor getMapColor(IBlockState state, IBlockAccess world, BlockPos pos) {
        return MapColor.RED;
    }
}