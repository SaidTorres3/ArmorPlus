package sokratis12GR.ArmorPlus.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraftforge.common.AchievementPage;
import sokratis12GR.ArmorPlus.ArmorPlus;
import sokratis12GR.ArmorPlus.armors.origin.*;
import sokratis12GR.ArmorPlus.armors.reinforced.RDArmor;
import sokratis12GR.ArmorPlus.armors.special.EnderDragonArmor;
import sokratis12GR.ArmorPlus.armors.special.GuardianArmor;
import sokratis12GR.ArmorPlus.armors.special.SuperStarArmor;
import sokratis12GR.ArmorPlus.armors.special.TheUltimateArmor;
import sokratis12GR.ArmorPlus.armors.tconstruct.*;

import java.util.ArrayList;
import java.util.List;

public class ARPAchievements {
    public static AchievementPage arpAchievementPage;

    public static Achievement craftCoalArmor;//0,0
    public static Achievement craftLapisArmor;//0,2
    public static Achievement craftRedstoneArmor;//0,-2
    public static Achievement craftEmeraldArmor;//0,-4
    public static Achievement craftObsidianArmor;//2,0
    public static Achievement craftLavaArmor;//4,0
    public static Achievement craftSuperStarArmor;//6,0
    public static Achievement craftEnderDragonArmor;//8,0
    public static Achievement craftGuardianArmor;//0,4
    public static Achievement craftTheUltimateArmor;//10,0
    public static Achievement craftReinforcedArmor;//2,-2
    /** Tinkers' Construct */
    public static Achievement craftCobaltArmor;
    public static Achievement craftArditeArmor;
    public static Achievement craftManyullynArmor;
    public static Achievement craftPigIronArmor;
    public static Achievement craftKnightSlimeArmor;

    public static void init() {
        /** Center */
        craftCoalArmor = new AchievementARP("craftCoalArmor", 0, 0, CoalArmor.helmet, AchievementList.openInventory).setNormalCrafting();
        /** Bottom */
        craftLapisArmor = new AchievementARP("craftLapisArmor", 0, 2, LapisArmor.helmet, craftCoalArmor).setNormalCrafting();
        craftGuardianArmor = new AchievementARP("craftGuardianArmor", 0, 4, GuardianArmor.helmet, craftLapisArmor).setNormalCrafting();
        /** Top */
        craftRedstoneArmor = new AchievementARP("craftRedstoneArmor", 0, -2, RedstoneArmor.boots, craftCoalArmor).setNormalCrafting();
        craftEmeraldArmor = new AchievementARP("craftEmeraldArmor", 0, -4, EmeraldArmor.chestplate, craftRedstoneArmor).setNormalCrafting();
        /** Top-Right */
        craftReinforcedArmor = new AchievementARP("craftReinforcedArmor", 2, -2, RDArmor.chestplate, craftCoalArmor).setNormalCrafting();
        /** Right */
        craftObsidianArmor = new AchievementARP("craftObsidianArmor", 2, 0, ObsidianArmor.chestplate, craftCoalArmor).setNormalCrafting();
        craftLavaArmor = new AchievementARP("craftLavaArmor", 4, 0, LavaArmor.chestplate, craftObsidianArmor).setNormalCrafting();
        craftSuperStarArmor = new AchievementARP("craftSuperStarArmor", 6, 0, SuperStarArmor.chestplate, craftLavaArmor).setNormalCrafting();
        craftEnderDragonArmor = new AchievementARP("craftEnderDragonArmor", 8, 0, EnderDragonArmor.chestplate, AchievementList.theEnd2).setNormalCrafting();
        craftTheUltimateArmor = new AchievementARP("craftTheUltimateArmor", 10, 0, TheUltimateArmor.chestplate, craftEnderDragonArmor).setNormalCrafting();
        /** Left */
        /** Tinkers' Construct */
        craftCobaltArmor = new AchievementARP("craftCobaltArmor", -2, 0, CobaltArmor.chestplate, craftCoalArmor).setNormalCrafting();
        craftArditeArmor = new AchievementARP("craftArditeArmor", -4, 0, ArditeArmor.chestplate, craftCobaltArmor).setNormalCrafting();
        craftManyullynArmor = new AchievementARP("craftManyullynArmor", -6, 0, ManyullynArmor.chestplate, craftArditeArmor).setNormalCrafting();
        craftPigIronArmor = new AchievementARP("craftPigIronArmor", -2, -2, PigIronArmor.chestplate, craftCobaltArmor).setNormalCrafting();
        craftKnightSlimeArmor = new AchievementARP("craftKnightSlimeArmor", -2, 2, KnightSlimeArmor.chestplate, craftCobaltArmor).setNormalCrafting();

        arpAchievementPage = new AchievementPage(ArmorPlus.MODNAME, AchievementARP.achievements.toArray(new Achievement[AchievementARP.achievements.size()]));
        AchievementPage.registerAchievementPage(arpAchievementPage);

    }

    public static class AchievementARP extends Achievement {
        public static List<Achievement> achievements = new ArrayList();

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

        public ItemStack[] triggerItems;

        public AchievementARP setNormalCrafting(ItemStack... triggerItems) {
            this.triggerItems = triggerItems;
            normalCraftingAchievements.add(this);
            return this;
        }

    }

    public static ArrayList<AchievementARP> normalCraftingAchievements = new ArrayList();
}