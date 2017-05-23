/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat.jei

import mezz.jei.api.*
import mezz.jei.api.ingredients.IModIngredientRegistration
import net.minecraftforge.fml.common.Optional.Interface
import net.minecraftforge.fml.common.Optional.Method
import net.thedragonteam.armorplus.registry.ModItems
import net.thedragonteam.armorplus.registry.ModBlocks
import net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack

@Interface(iface = "mezz.jei.api.ingredients.IIngredientBlacklist", modid = "jei", striprefs = true)
@JEIPlugin
class JEI : IModPlugin {

    @Method(modid = "jei")
    override fun register(registry: IModRegistry) {
        val helpers = registry.jeiHelpers
        val blacklist = helpers.ingredientBlacklist
        blacklist.addIngredientToBlacklist(getItemStack(ModItems.moddedCityItem))
        blacklist.addIngredientToBlacklist(getItemStack(ModItems.jonBamsItem))
        blacklist.addIngredientToBlacklist(getItemStack(ModItems.jonBamsItem, 1))
        blacklist.addIngredientToBlacklist(getItemStack(ModItems.theDragonTeamItem))
        blacklist.addIngredientToBlacklist(getItemStack(ModItems.twitchItem))
        blacklist.addIngredientToBlacklist(getItemStack(ModItems.beamItem))
        blacklist.addIngredientToBlacklist(getItemStack(ModItems.nbtItem))
        ModItems.templates.forEach { item -> blacklist.addIngredientToBlacklist(getItemStack(item)) }
        ModBlocks.enderBlocks.forEach { block -> blacklist.addIngredientToBlacklist(getItemStack(block)) }
    }

    @Method(modid = "jei")
    override fun onRuntimeAvailable(jeiRuntime: IJeiRuntime) = Unit

    @Method(modid = "jei")
    override fun registerIngredients(registry: IModIngredientRegistration) = Unit

    @Method(modid = "jei")
    override fun registerItemSubtypes(subtypeRegistry: ISubtypeRegistry) = Unit
}