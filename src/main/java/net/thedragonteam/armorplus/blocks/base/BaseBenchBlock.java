/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.blocks.base;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.tileentity.base.TileEntityBaseBench;

public class BaseBenchBlock extends BaseBlock implements ITileEntityProvider {

    public TileEntityBaseBench tileEntityBaseBench;

    public BaseBenchBlock(Material material, String name, float resistance, float hardness, String tool, int harvestLevel, TileEntityBaseBench tileEntityBaseBench) {
        super(material, name, resistance, hardness, tool, harvestLevel);
        this.tileEntityBaseBench = tileEntityBaseBench;
    }


    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return tileEntityBaseBench;
    }
}