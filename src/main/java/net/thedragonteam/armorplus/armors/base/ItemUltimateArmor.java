/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.armors.base;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.APConfig;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.util.Utils;

import java.util.List;

import static net.minecraft.init.SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND;
import static net.minecraft.util.text.TextFormatting.getValueByName;
import static net.minecraftforge.common.util.EnumHelper.addArmorMaterial;
import static net.thedragonteam.armorplus.APConfig.*;
import static net.thedragonteam.armorplus.registry.ModItems.materials;
import static net.thedragonteam.armorplus.registry.ModItems.theUltimate;
import static net.thedragonteam.armorplus.util.EnumHelperUtil.addRarity;
import static net.thedragonteam.armorplus.util.Utils.setLocation;
import static net.thedragonteam.armorplus.util.Utils.setName;
import static net.thedragonteam.thedragonlib.util.PotionUtils.PotionType.BAD;
import static net.thedragonteam.thedragonlib.util.PotionUtils.addPotion;

public class ItemUltimateArmor extends ItemArmor {

    public static ArmorMaterial theUltimateArmor = addArmorMaterial("THE_ULTIMATE_ARMOR", setLocation("the_ultimate_armor"), 160,
            theUltimateArmorProtectionPoints, 1, ITEM_ARMOR_EQUIP_DIAMOND, (float) theUltimateArmorToughnessPoints);

    public EnumRarity formattingName;

    public ItemUltimateArmor(EntityEquipmentSlot slot) {
        super(theUltimateArmor, 0, slot);
        setMaxStackSize(1);
        switch (slot) {
            case FEET:
                String boots = "the_ultimate_boots";
                setRegistryName(boots);
                setUnlocalizedName(setName(boots));
                break;
            case LEGS:
                String leggings = "the_ultimate_leggings";
                setRegistryName(leggings);
                setUnlocalizedName(setName(leggings));
                break;
            case CHEST:
                String chestplate = "the_ultimate_chestplate";
                setRegistryName(chestplate);
                setUnlocalizedName(setName(chestplate));
                break;
            case HEAD:
                String helmet = "the_ultimate_helmet";
                setRegistryName(helmet);
                setUnlocalizedName(setName(helmet));
                break;
        }
        GameRegistry.register(this);
        setCreativeTab(ArmorPlus.tabArmorplus);
        formattingName = addRarity("ULTIMATE_ARMOR_COLOR", theUltimateArmorItemNameColor, "Ultimate Armor Color");
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return formattingName;
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return false;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return false;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        final KeyBinding keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;

        if (GameSettings.isKeyDown(keyBindSneak)) {
            tooltip.add("\2479Ability: " + "\247rThe Most OverPowered Armor");
            tooltip.add("\2473Use: " + "\247rEquip The Full Set");
        } else
            tooltip.add(I18n.format("tooltip.shift.showinfo", getValueByName(theUltimateArmorItemNameColor), keyBindSneak.getDisplayName(), TextFormatting.GRAY, TextFormatting.GREEN));
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if (makeTheUltimateArmorUnbreakable)
            Utils.setUnbreakable(stack);
    }

    @Override
    public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
        ItemStack head = entity.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
        ItemStack chest = entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
        ItemStack legs = entity.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
        ItemStack feet = entity.getItemStackFromSlot(EntityEquipmentSlot.FEET);
        if (APConfig.enableFlightAbility)
            if (head.getCount() > 0 && head.getItem() == theUltimate[0] && chest.getCount() > 0 && chest.getItem() == theUltimate[1] && legs.getCount() > 0 && legs.getItem() == theUltimate[2] && feet.getCount() > 0 && feet.getItem() == theUltimate[3] || entity.capabilities.isCreativeMode || entity.isSpectator())
                entity.capabilities.allowFlying = true;
            else {
                entity.capabilities.isFlying = false;
                entity.capabilities.allowFlying = false;
            }
        if (APConfig.enableTheUltimateArmorInvincibility)
            if (head.getCount() > 0 && head.getItem() == theUltimate[0] && chest.getCount() > 0 && chest.getItem() == theUltimate[1] && legs.getCount() > 0 && legs.getItem() == theUltimate[2] && feet.getCount() > 0 && feet.getItem() == theUltimate[3] || entity.capabilities.isCreativeMode || entity.isSpectator())
                entity.capabilities.disableDamage = true;
            else entity.capabilities.disableDamage = false;
        if (head.getCount() > 0 && head.getItem() == theUltimate[0] && chest.getCount() > 0 && chest.getItem() == theUltimate[1] && legs.getCount() > 0 && legs.getItem() == theUltimate[2] && feet.getCount() > 0 && feet.getItem() == theUltimate[3] || entity.capabilities.isCreativeMode || entity.isSpectator()) {
        } else if (enableTheUltimateArmorDeBuffs) {
            addPotion(entity, MobEffects.POISON, 60, 2, BAD);
            addPotion(entity, MobEffects.SLOWNESS, 60, 2, BAD);
            addPotion(entity, MobEffects.BLINDNESS, 60, 0, BAD);

            entity.motionX = 0;
            if (entity.onGround)
                entity.motionY = 0;
            entity.motionZ = 0;
            entity.velocityChanged = true; // assumes that entity instanceof EntityPlayer
        }
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        switch (APConfig.getRD()) {
            case EASY:
                return repair.getItem() == new ItemStack(materials, 1, 4).getItem();
            case EXPERT:
                return repair.getItem() == new ItemStack(materials, 1, 4).getItem();
            case HELLISH:
                return false;
        }
        return true;
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}