/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.items.base.energy.rf;

import cofh.api.energy.IEnergyContainerItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;

public class BaseRFSword extends ItemSword implements IEnergyContainerItem {

    protected int capacity;
    protected int maxReceive;
    protected int maxExtract;

    public BaseRFSword(ToolMaterial material, String name, int capacity) {
        this(material, name, capacity, capacity, capacity);
    }

    public BaseRFSword(ToolMaterial material, String name, int capacity, int maxTransfer) {
        this(material, name, capacity, maxTransfer, maxTransfer);
    }

    public BaseRFSword(ToolMaterial material, String name, int capacity, int maxReceive, int maxExtract) {
        super(material);
        setRegistryName(name);        // The unique name (within your mod) that identifies this item
        setUnlocalizedName(ArmorPlus.MODID + "." + name);     // Used for localization (en_US.lang)
        GameRegistry.register(this);
        this.setCreativeTab(ArmorPlus.tabArmorplusRF);
        this.capacity = capacity;
        this.maxReceive = maxReceive;
        this.maxExtract = maxExtract;
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return true;
    }

    public BaseRFSword setCapacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

    public BaseRFSword setMaxTransfer(int maxTransfer) {
        setMaxReceive(maxTransfer);
        setMaxExtract(maxTransfer);
        return this;
    }

    public BaseRFSword setMaxReceive(int maxReceive) {
        this.maxReceive = maxReceive;
        return this;
    }

    public BaseRFSword setMaxExtract(int maxExtract) {
        this.maxExtract = maxExtract;
        return this;
    }

    public int getMaxExtract(ItemStack container) {
        return this.maxExtract;
    }

    public int getMaxReceive(ItemStack container) {
        return this.maxReceive;
    }

    public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {
        if (!container.hasTagCompound()) {
            container.setTagCompound(new NBTTagCompound());
        }
        int energy = container.getTagCompound().getInteger("Energy");
        int energyReceived = Math.min(this.capacity - energy, Math.min(this.maxReceive, maxReceive));
        if (!simulate) {
            energy += energyReceived;
            container.getTagCompound().setInteger("Energy", energy);
        }
        return energyReceived;
    }

    public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
        if ((container.getTagCompound() == null) || (!container.getTagCompound().hasKey("Energy"))) {
            return 0;
        }
        int energy = container.getTagCompound().getInteger("Energy");
        int energyExtracted = Math.min(energy, Math.min(this.maxExtract, maxExtract));
        if (!simulate) {
            energy -= energyExtracted;
            container.getTagCompound().setInteger("Energy", energy);
        }
        return energyExtracted;
    }

    public int getEnergyStored(ItemStack container) {
        if ((container.getTagCompound() == null) || (!container.getTagCompound().hasKey("Energy"))) {
            return 0;
        }
        return container.getTagCompound().getInteger("Energy");
    }

    public int getMaxEnergyStored(ItemStack container) {
        return this.capacity;
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        return (1 - (double) this.getEnergyStored(stack) / (double) this.getMaxEnergyStored(stack));
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(this, 1, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}