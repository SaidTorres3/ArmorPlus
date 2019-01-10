/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.items.weapons;

import com.sofodev.armorplus.api.properties.iface.IDurable;
import com.sofodev.armorplus.api.properties.iface.IRemovable;
import com.sofodev.armorplus.api.properties.iface.IRepairable;
import com.sofodev.armorplus.config.ModConfig;
import com.sofodev.armorplus.registry.APItems;
import com.sofodev.armorplus.registry.ModBlocks;
import com.sofodev.armorplus.registry.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.thedragonteam.thedragonlib.util.ItemStackUtils;

import static net.minecraft.init.Blocks.*;
import static net.minecraft.item.ItemStack.EMPTY;
import static net.minecraft.util.text.TextFormatting.getValueByName;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * @author Sokratis Fotkatzikis
 **/
public enum Bows implements IRepairable, IRemovable, IDurable {
    COAL(getItemStack(COAL_BLOCK), ModConfig.RegistryConfig.coal, APItems.coalBow, ModConfig.RegistryConfig.global_registry.enableCoalWeapons),
    LAPIS(getItemStack(LAPIS_BLOCK), ModConfig.RegistryConfig.lapis, APItems.lapisBow, ModConfig.RegistryConfig.global_registry.enableEmeraldWeapons),
    REDSTONE(getItemStack(REDSTONE_BLOCK), ModConfig.RegistryConfig.redstone, APItems.redstoneBow, ModConfig.RegistryConfig.global_registry.enableRedstoneWeapons),
    EMERALD(getItemStack(EMERALD_BLOCK), ModConfig.RegistryConfig.emerald, APItems.emeraldBow, ModConfig.RegistryConfig.global_registry.enableEmeraldWeapons),
    OBSIDIAN(getItemStack(ModBlocks.blockCompressedObsidian), ModConfig.RegistryConfig.obsidian, APItems.obsidianBow, ModConfig.RegistryConfig.global_registry.enableObsidianWeapons),
    INFUSED_LAVA(ItemStackUtils.getItemStack(ModItems.itemLavaCrystal, 1), ModConfig.RegistryConfig.lava, APItems.lavaBow, ModConfig.RegistryConfig.global_registry.enableLavaWeapons),
    GUARDIAN(ItemStackUtils.getItemStack(ModItems.materials, 1), ModConfig.RegistryConfig.guardian, APItems.guardianBow, ModConfig.RegistryConfig.global_registry.enableGuardianWeapons),
    SUPER_STAR(ItemStackUtils.getItemStack(ModItems.materials, 2), ModConfig.RegistryConfig.super_star, APItems.superStarBow, ModConfig.RegistryConfig.global_registry.enableSuperStarWeapons),
    ENDER_DRAGON(ItemStackUtils.getItemStack(ModItems.materials, 3), ModConfig.RegistryConfig.ender_dragon, APItems.enderDragonBow, ModConfig.RegistryConfig.global_registry.enableEnderDragonWeapons);

    private final BowStats bowStats;
    private final boolean isEnabled;
    private final ItemStack repairStack;
    private final TextFormatting textFormatting;
    private final Item bowItem;

    Bows(ItemStack repairStackIn, ModConfig.RegistryConfig.OriginMaterial material, Item bowItemIn, boolean[] isEnabled) {
        this.bowStats = new BowStats(material);
        this.isEnabled = isEnabled[2];
        if (repairStackIn == null) repairStackIn = EMPTY;
        this.repairStack = repairStackIn;
        this.textFormatting = getValueByName(material.weapons.itemNameColor);
        this.bowItem = bowItemIn;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }

    public String getName() {
        return this.name().toLowerCase();
    }

    public double getDamage() {
        return this.bowStats.getBonusDamage();
    }

    @Override
    public int getDurability(boolean unbreakable) {
        return this.bowStats.getDurability();
    }

    public Item getBowItem() {
        return this.bowItem;
    }

    @Override
    public ItemStack getRepairStack() {
        return this.repairStack;
    }

    public TextFormatting getTextFormatting() {
        return this.textFormatting;
    }

    private static class BowStats {
        private final int durabilityIn;
        private final double bonusDamage;

        BowStats(ModConfig.RegistryConfig.OriginMaterial material) {
            this(material.weapons.bow.durability, material.weapons.bow.arrowBonusDamage);
        }

        private BowStats(int durability, double bonusDamage) {
            this.durabilityIn = durability;
            this.bonusDamage = bonusDamage;
        }

        double getBonusDamage() {
            return bonusDamage;
        }

        int getDurability() {
            return durabilityIn;
        }
    }
}
