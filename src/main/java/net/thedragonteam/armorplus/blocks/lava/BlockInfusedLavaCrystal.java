package net.thedragonteam.armorplus.blocks.lava;

import net.minecraft.block.material.Material;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.blocks.base.BlockBase;
import net.thedragonteam.armorplus.blocks.base.ToolType;
import net.thedragonteam.armorplus.iface.IModelHelper;

public class BlockInfusedLavaCrystal extends BlockBase implements IModelHelper {

    //TODO: Rename to "block_infused_lava_crystal"
    public BlockInfusedLavaCrystal() {
        super(Material.IRON, "block_infused_lava_crystal", 1000, 5.0F, ToolType.PICKAXE, 3);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void initModel() {
        this.initModel(this, getRegistryName(), 0, "normal");
    }
}