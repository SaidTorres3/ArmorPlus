/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraftforge.common.AchievementPage;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.registry.ModItems;

import java.util.ArrayList;
import java.util.List;

public class ARPAchievements {
    private static AchievementPage ARP_ACHIEVEMENT_PAGE;

    public static Achievement CRAFT_COAL_ARMOR,
            CRAFT_LAPIS_ARMOR,
            CRAFT_REDSTONE_ARMOR,
            CRAFT_EMERALD_ARMOR,
            CRAFT_OBSIDIAN_ARMOR,
            CRAFT_LAVA_ARMOR,
            CRAFT_SUPER_STAR_ARMOR,
            CRAFT_ENDER_DRAGON_ARMOR,
            CRAFT_GUARDIAN_ARMOR,
            CRAFT_THE_ULTIMATE_ARMOR,
            CRAFT_REINFORCED_ARMOR,
            CRAFT_SLIME_ARMOR,
            CRAFT_CHICKEN_ARMOR,
            CRAFT_COBALT_ARMOR,
            CRAFT_ARDITE_ARMOR,
            CRAFT_MANYULLYN_ARMOR,
            CRAFT_PIG_IRON_ARMOR,
            CRAFT_KNIGHT_SLIME_ARMOR,
            WELCOME_TO_ARMORPLUS;
    public static ArrayList<AchievementARP> normalCraftingAchievements = new ArrayList();

    public static void init() {
        WELCOME_TO_ARMORPLUS = new AchievementARP("craft_armor_forge", -2, -2, ModItems.ARMORPLUS_BOOK, AchievementList.OPEN_INVENTORY).setNormalCrafting().setSpecial();
        CRAFT_COAL_ARMOR = new AchievementARP("craft_coal_armor", 0, 0, ModItems.COAL_HELMET, AchievementList.OPEN_INVENTORY).setNormalCrafting();
        CRAFT_CHICKEN_ARMOR = new AchievementARP("craft_chicken_armor", 2, 2, ModItems.CHICKEN_BOOTS, CRAFT_COAL_ARMOR).setNormalCrafting();
        CRAFT_SLIME_ARMOR = new AchievementARP("craft_slime_armor", 2, 4, ModItems.SLIME_BOOTS, CRAFT_COAL_ARMOR).setNormalCrafting();
        CRAFT_LAPIS_ARMOR = new AchievementARP("craft_lapis_armor", -2, 2, ModItems.LAPIS_HELMET, CRAFT_COAL_ARMOR).setNormalCrafting();
        CRAFT_GUARDIAN_ARMOR = new AchievementARP("craft_guardian_armor", -2, 4, ModItems.GUARDIAN_HELMET, CRAFT_LAPIS_ARMOR).setNormalCrafting();
        CRAFT_REDSTONE_ARMOR = new AchievementARP("craft_redstone_armor", 0, -4, ModItems.REDSTONE_BOOTS, CRAFT_COAL_ARMOR).setNormalCrafting();
        CRAFT_EMERALD_ARMOR = new AchievementARP("craft_emerald_armor", 0, -6, ModItems.EMERALD_CHESTPLATE, CRAFT_REDSTONE_ARMOR).setNormalCrafting();
        CRAFT_REINFORCED_ARMOR = new AchievementARP("craft_reinforced_armor", 2, -2, ModItems.RD_CHESTPLATE, CRAFT_COAL_ARMOR).setNormalCrafting();
        CRAFT_OBSIDIAN_ARMOR = new AchievementARP("craft_obsidian_armor", 4, 0, ModItems.OBSIDIAN_CHESTPLATE, CRAFT_COAL_ARMOR).setNormalCrafting();
        CRAFT_LAVA_ARMOR = new AchievementARP("craft_lava_armor", 6, 0, ModItems.LAVA_CHESTPLATE, CRAFT_OBSIDIAN_ARMOR).setNormalCrafting();
        CRAFT_SUPER_STAR_ARMOR = new AchievementARP("craft_super_star_armor", 8, 0, ModItems.SUPER_STAR_CHESTPLATE, CRAFT_LAVA_ARMOR).setNormalCrafting();
        CRAFT_ENDER_DRAGON_ARMOR = new AchievementARP("craft_ender_dragon_armor", 8, 2, ModItems.ENDER_DRAGON_CHESTPLATE, AchievementList.THE_END2).setNormalCrafting();
        CRAFT_THE_ULTIMATE_ARMOR = new AchievementARP("craft_the_ultimate_armor", 6, 4, ModItems.THE_ULTIMATE_CHESTPLATE, CRAFT_ENDER_DRAGON_ARMOR).setNormalCrafting().setSpecial();
        CRAFT_COBALT_ARMOR = new AchievementARP("craft_cobalt_armor", -4, 0, ModItems.COBALT_CHESTPLATE, CRAFT_COAL_ARMOR).setNormalCrafting();
        CRAFT_ARDITE_ARMOR = new AchievementARP("craft_ardite_armor", -6, 0, ModItems.ARDITE_CHESTPLATE, CRAFT_COBALT_ARMOR).setNormalCrafting();
        CRAFT_MANYULLYN_ARMOR = new AchievementARP("craft_manyullyn_armor", -8, 0, ModItems.MANYULLYN_CHESTPLATE, CRAFT_ARDITE_ARMOR).setNormalCrafting().setSpecial();
        CRAFT_PIG_IRON_ARMOR = new AchievementARP("craft_pig_iron_armor", -4, -2, ModItems.PIG_IRON_CHESTPLATE, CRAFT_COBALT_ARMOR).setNormalCrafting();
        CRAFT_KNIGHT_SLIME_ARMOR = new AchievementARP("craft_knight_slime_armor", -4, 2, ModItems.KNIGHT_SLIME_CHESTPLATE, CRAFT_COBALT_ARMOR).setNormalCrafting();

        ARP_ACHIEVEMENT_PAGE = new AchievementPage(ArmorPlus.MODNAME, AchievementARP.achievements.toArray(new Achievement[AchievementARP.achievements.size()]));
        AchievementPage.registerAchievementPage(ARP_ACHIEVEMENT_PAGE);

    }

    public static class AchievementARP extends Achievement {
        public static List<Achievement> achievements = new ArrayList();
        public ItemStack[] triggerItems;

        public AchievementARP(String name, int x, int y, ItemStack icon, Achievement parent) {
            super("achievement.armorplus." + name, "armorplus." + name, x, y, icon, parent);
            achievements.add(this);
            registerStat();
        }

        public AchievementARP(String name, int x, int y, Item icon, Achievement parent) {
            this(name, x, y, new ItemStack(icon), parent);
        }

        public AchievementARP(String name, int x, int y, Block icon, Achievement parent) {
            this(name, x, y, new ItemStack(icon), parent);
        }

        public AchievementARP setNormalCrafting(ItemStack... triggerItems) {
            this.triggerItems = triggerItems;
            normalCraftingAchievements.add(this);
            return this;
        }

    }
}