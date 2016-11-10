/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.resources;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.thedragonteam.armorplus.ARPConfig;
import net.thedragonteam.armorplus.registry.ModBlocks;
import net.thedragonteam.armorplus.registry.ModItems;
import net.thedragonteam.armorplus.util.ARPAchievements;
import net.thedragonteam.armorplus.util.ArmorUtils;
import net.thedragonteam.armorplus.util.ParticlesHelper;
import net.thedragonteam.thedragonlib.util.LogHelper;

import static net.thedragonteam.armorplus.ARPConfig.*;
import static net.thedragonteam.armorplus.ArmorPlus.configuration;

public class GlobalEventsArmorPlus {

    @SubscribeEvent
    public void onPlayerCraftedItem(PlayerEvent.ItemCraftedEvent event) {
        Item i = event.crafting.getItem();
        if (i == Item.getItemFromBlock(ModBlocks.arpWorkbench)) {
            event.player.addStat(ARPAchievements.welcomeToArmorPlus, 1);
            event.player.inventory.addItemStackToInventory(new ItemStack(ModItems.armorPlusBook, 1));
        }
    }
    //int level = EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, itemStack);

    @SubscribeEvent
    public void onArmorTick(TickEvent.PlayerTickEvent event) {
        EntityPlayer entity;
        entity = event.player;
        ItemStack head;
        head = entity.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
        ItemStack chest;
        chest = entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
        ItemStack legs;
        legs = entity.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
        ItemStack feet;
        feet = entity.getItemStackFromSlot(EntityEquipmentSlot.FEET);

        if (head != null && chest != null && legs != null && feet != null) {
            if (head.getItem() == ModItems.lavaHelmet && chest.getItem() == ModItems.lavaChestplate && legs.getItem() == ModItems.lavaLeggings && feet.getItem() == ModItems.lavaBoots && entity.isInWater() && enableFullLavaArmorEffect) {
                entity.extinguish();
                if (entity.isInLava())
                    entity.setAbsorptionAmount(4.0F);
                if (entity.getActivePotionEffect(MobEffects.WATER_BREATHING) == null) {
                    ArmorUtils.addArmorEffect(entity, MobEffects.SLOWNESS, 120, 1);
                    head.damageItem(1, entity);
                    chest.damageItem(1, entity);
                    legs.damageItem(1, entity);
                    feet.damageItem(1, entity);
                    entity.attackEntityFrom(DamageSource.drown, 1F);
                }
            } else if (head.getItem() == ModItems.theUltimateHelmet && chest.getItem() == ModItems.theUltimateChestplate && legs.getItem() == ModItems.theUltimateLeggings && feet.getItem() == ModItems.theUltimateBoots) {
                ArmorUtils.addArmorEffect(entity, Potion.getPotionFromResourceLocation(theUltimateArmorAddPotionEffects[0]), 120);
                ArmorUtils.addArmorEffect(entity, Potion.getPotionFromResourceLocation(theUltimateArmorAddPotionEffects[1]), 120, ARPConfig.ultimateArmorEffectlevel);
                ArmorUtils.addArmorEffect(entity, Potion.getPotionFromResourceLocation(theUltimateArmorAddPotionEffects[2]), 120);
                ArmorUtils.removeArmorEffect(entity, Potion.getPotionFromResourceLocation(theUltimateArmorRemovePotionEffect));
            } else if (ARPConfig.enableFullSuperStarArmorEffect && head.getItem() == ModItems.superStarHelmet && chest.getItem() == ModItems.superStarChestplate && legs.getItem() == ModItems.superStarLeggings && feet.getItem() == ModItems.superStarBoots) {
                ArmorUtils.addArmorEffect(entity, Potion.getPotionFromResourceLocation(superStarArmorAddPotionEffect), 120, ARPConfig.superstarArmorEffectlevel);
                ArmorUtils.removeArmorEffect(entity, Potion.getPotionFromResourceLocation(superStarArmorRemovePotionEffect));
            } else if (ARPConfig.enableFullCoalArmorEffect && head.getItem() == ModItems.coalHelmet && chest.getItem() == ModItems.coalChestplate && legs.getItem() == ModItems.coalLeggings && feet.getItem() == ModItems.coalBoots) {
                ArmorUtils.addArmorEffect(entity, Potion.getPotionFromResourceLocation(coalArmorAddPotionEffect), 240);
            } else if (ARPConfig.enablePigIronArmorEffect && head.getItem() == ModItems.pigIronHelmet && chest.getItem() == ModItems.pigIronChestplate && legs.getItem() == ModItems.pigIronLeggings && feet.getItem() == ModItems.pigIronBoots && entity.getFoodStats().needFood()) {
                ArmorUtils.addArmorEffect(entity, Potion.getPotionFromResourceLocation(pigIronArmorAddPotionEffect), 20);
                head.damageItem(1, entity);
                chest.damageItem(1, entity);
                legs.damageItem(1, entity);
                feet.damageItem(1, entity);
            } else if (ARPConfig.enableFullLapisArmorEffect && head.getItem() == ModItems.lapisHelmet && chest.getItem() == ModItems.lapisChestplate && legs.getItem() == ModItems.lapisLeggings && feet.getItem() == ModItems.lapisBoots) {
                ArmorUtils.addArmorEffect(entity, Potion.getPotionFromResourceLocation(lapisArmorAddPotionEffect), 120);
            } else if (ARPConfig.enableFullLavaArmorEffect && head.getItem() == ModItems.lavaHelmet && chest.getItem() == ModItems.lavaChestplate && legs.getItem() == ModItems.lavaLeggings && feet.getItem() == ModItems.lavaBoots) {
                ArmorUtils.addArmorEffect(entity, Potion.getPotionFromResourceLocation(lavaArmorAddPotionEffect), 120);
            } else if (ARPConfig.enableFullEmeraldArmorEffect && head.getItem() == ModItems.emeraldHelmet && chest.getItem() == ModItems.emeraldChestplate && legs.getItem() == ModItems.emeraldLeggings && feet.getItem() == ModItems.emeraldBoots) {
                ArmorUtils.addArmorEffect(entity, Potion.getPotionFromResourceLocation(emeraldArmorAddPotionEffect), 120, ARPConfig.emeraldArmorEffectlevel);
            } else if (ARPConfig.enableFullObsidianArmorEffect && head.getItem() == ModItems.obsidianHelmet && chest.getItem() == ModItems.obsidianChestplate && legs.getItem() == ModItems.obsidianLeggings && feet.getItem() == ModItems.obsidianBoots) {
                ArmorUtils.addArmorEffect(entity, Potion.getPotionFromResourceLocation(obsidianArmorAddPotionEffect), 120, ARPConfig.obsidianArmorEffectlevel);
            } else if (ARPConfig.enableFullRedstoneArmorEffect && head.getItem() == ModItems.redstoneHelmet && chest.getItem() == ModItems.redstoneChestplate && legs.getItem() == ModItems.redstoneLeggings && feet.getItem() == ModItems.redstoneBoots) {
                ArmorUtils.addArmorEffect(entity, Potion.getPotionFromResourceLocation(redstoneArmorAddPotionEffect), 240, ARPConfig.redstoneArmorEffectlevel);
                if (entity.worldObj.isRemote) {
                    ParticlesHelper.spawnParticle(entity, EnumParticleTypes.REDSTONE, entity.posX, entity.posY, entity.posZ);
                }
            } else if (head.getItem() == ModItems.guardianHelmet && chest.getItem() == ModItems.guardianChestplate && legs.getItem() == ModItems.guardianLeggings && feet.getItem() == ModItems.guardianBoots) {
                ArmorUtils.addArmorEffect(entity, Potion.getPotionFromResourceLocation(guardianArmorAddPotionEffect), 120);
            } else if (head.getItem() == ModItems.chickenHelmet && chest.getItem() == ModItems.chickenChestplate && legs.getItem() == ModItems.chickenLeggings && feet.getItem() == ModItems.chickenBoots) {
                ArmorUtils.addArmorEffect(entity, Potion.getPotionFromResourceLocation(chickenArmorAddPotionEffect), 120, 4);
            } else if (head.getItem() == ModItems.slimeHelmet && chest.getItem() == ModItems.slimeChestplate && legs.getItem() == ModItems.slimeLeggings && feet.getItem() == ModItems.slimeBoots) {
                ArmorUtils.addArmorEffect(entity, Potion.getPotionFromResourceLocation(slimeArmorAddPotionEffect), 120, 2);
            } else if (ARPConfig.enableManyullynArmorEffect && head.getItem() == ModItems.manyullynHelmet && chest.getItem() == ModItems.manyullynChestplate && legs.getItem() == ModItems.manyullynLeggings && feet.getItem() == ModItems.manyullynBoots) {
                ArmorUtils.addArmorEffect(entity, Potion.getPotionFromResourceLocation(manyullynArmorAddPotionEffect), 120, 1);
            } else if (ARPConfig.enableKnightSlimeArmorEffect && head.getItem() == ModItems.knightSlimeHelmet && chest.getItem() == ModItems.knightSlimeChestplate && legs.getItem() == ModItems.knightSlimeLeggings && feet.getItem() == ModItems.knightSlimeBoots) {
                ArmorUtils.addArmorEffect(entity, Potion.getPotionFromResourceLocation(knightSlimeArmorAddPotionEffect), 120, 1);
            } else if (ARPConfig.enableCobaltArmorEffect && head.getItem() == ModItems.cobaltHelmet && chest.getItem() == ModItems.cobaltChestplate && legs.getItem() == ModItems.cobaltLeggings && feet.getItem() == ModItems.cobaltBoots) {
                ArmorUtils.addArmorEffect(entity, Potion.getPotionFromResourceLocation(cobaltArmorAddPotionEffect), 120, 2);
            } else if (ARPConfig.enableArditeArmorEffect && head.getItem() == ModItems.arditeHelmet && chest.getItem() == ModItems.arditeChestplate && legs.getItem() == ModItems.arditeLeggings && feet.getItem() == ModItems.arditeBoots) {
                ArmorUtils.addArmorEffect(entity, Potion.getPotionFromResourceLocation(arditeArmorAddPotionEffect), 120);
            }
        }
    }

    public static void syncConfig() {
        if (configuration.hasChanged())
            configuration.save();
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        syncConfig();
        LogHelper.info("Refreshing configuration file");
    }
}