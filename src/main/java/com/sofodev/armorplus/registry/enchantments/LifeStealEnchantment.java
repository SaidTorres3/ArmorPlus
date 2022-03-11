package com.sofodev.armorplus.registry.enchantments;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolItem;

import static com.sofodev.armorplus.registry.enchantments.LifeStealEnchantment.Levels.limit;
import static net.minecraft.enchantment.EnchantmentType.WEAPON;
import static net.minecraft.inventory.EquipmentSlotType.MAINHAND;

public class LifeStealEnchantment extends APEnchantment {

    public LifeStealEnchantment() {
        super(Rarity.VERY_RARE, WEAPON, new EquipmentSlotType[]{MAINHAND},
                1, 3, 10, 30, true, true
        );
    }

    @Override
    public void doPostHurt(LivingEntity user, Entity attacker, int level) {
        if (level > limit()) {
            level = limit();
        }
        super.doPostHurt(user, attacker, level);
    }

    @Override
    public void doPostAttack(LivingEntity user, Entity target, int level) {
        if (level > limit()) {
            level = limit();
        }
        Levels lvl = Levels.values()[level];
        float damageDealt;
        if (user == null || target == null) {
            return;
        }
        ItemStack mainHand = user.getMainHandItem();
        Item handItem = mainHand.getItem();
        if (mainHand.isEmpty()) return;
        if (!isCorrectItem(handItem)) {
            user.heal(lvl.healingFactor);
        } else if (handItem instanceof ToolItem) {
            damageDealt = ((ToolItem) handItem).getTier().getAttackDamageBonus();
            user.heal(level * softCap(damageDealt, 10, 1) / 4);
        } else if (handItem instanceof SwordItem) {
            damageDealt = ((SwordItem) handItem).getDamage();
            user.heal(level * softCap(damageDealt, 10, 1) / 4);
        }
    }


    /**
     * Julian's function for softCap
     */
    private float softCap(float value, float max, float scale) {
        if (value <= max) {
            return value;
        }
        float space = max * scale;
        float offset = value - max;
        return max + space * offset / (space + offset);
    }

    private boolean isCorrectItem(Item item) {
        return item instanceof SwordItem || item instanceof ToolItem;
    }

    public enum Levels {
        ZERO(0.0f),
        ONE(0.5f),
        TWO(1.5f),
        THREE(2.5f);

        public final float healingFactor;

        Levels(float healingFactor) {
            this.healingFactor = healingFactor;
        }

        public static int limit() {
            return values().length - 1;
        }
    }
}