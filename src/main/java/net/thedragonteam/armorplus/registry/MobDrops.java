/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.registry;

import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityElderGuardian;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.thedragonteam.thedragonlib.util.LogHelper;

import java.util.Random;

import static net.thedragonteam.armorplus.ARPConfig.*;
import static net.thedragonteam.armorplus.util.ItemStackUtils.getItemStack;

/**
 * net.thedragonteam.armorplus.registry
 * ArmorPlus Created by sokratis12GR on 4/4/2016.
 * - TheDragonTeam
 */
public class MobDrops {
    private Random random = new Random();
    private int min = 0;
    private int max = 1;

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public void playerKilledEntity(LivingDropsEvent event) {
        if (event.getEntity() instanceof EntityDragon) {
            event.getEntityLiving().entityDropItem(getItemStack(ModItems.materials, 3), enderdragonScaleDropAmount);
            if (debugMode) {
                LogHelper.info("Ender Dragon Dropped: " + getItemStack(ModItems.materials, 3) + " x " + enderdragonScaleDropAmount);
            }
        } else if (event.getEntity() instanceof EntityWither) {
            event.getEntityLiving().entityDropItem(getItemStack(ModItems.materials, 2), witherBoneDropAmount);
            if (debugMode) {
                LogHelper.info("Wither Boss Dropped: " + getItemStack(ModItems.materials, 2) + " x " + witherBoneDropAmount);
            }
        } else if (event.getEntity() instanceof EntityWitherSkeleton) {
            event.getEntityLiving().entityDropItem(getItemStack(ModItems.materials, 2), random.nextInt(max - min + 1) + min);
            if (debugMode) {
                LogHelper.info("Wither Skeleton Dropped: " + getItemStack(ModItems.materials, 2) + " x " + random.nextInt(max - min + 1) + min);
            }
        } else if (event.getEntity() instanceof EntityGuardian) {
            event.getEntityLiving().entityDropItem(getItemStack(ModItems.materials, 1), random.nextInt(max - min + 1) + min);
            if (debugMode) {
                LogHelper.info("Guardian Dropped: " + getItemStack(ModItems.materials, 1) + " x " + random.nextInt(max - min + 1) + min);
            }
        } else if (event.getEntity() instanceof EntityElderGuardian) {
            event.getEntityLiving().entityDropItem(getItemStack(ModItems.materials, 1), guardianScaleElderDropAmount);
            if (debugMode) {
                LogHelper.info("Elder Guardian Dropped:" + getItemStack(ModItems.materials, 1) + " x " + guardianScaleElderDropAmount);
            }
        }
    }
}