package com.sofodev.armorplus.data;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.loot.*;
import net.minecraft.loot.functions.CopyName;
import net.minecraft.loot.functions.CopyNbt;
import net.minecraft.loot.functions.SetContents;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.sofodev.armorplus.registry.ModBlocks.*;

public class LootTables extends BaseLootTableProvider {

    public List<RegistryObject<Block>> blockList = new ArrayList<>();

    public LootTables(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
    }

    @Override
    protected void addTables() {
        blockList.addAll(blocks);
        this.removeEntries(ORE_FROST_CRYSTAL, ORE_FROST_CRYSTAL_STONE, ORE_FROST_CRYSTAL_OBSIDIAN,
                ORE_LAVA_CRYSTAL, ORE_LAVA_CRYSTAL_STONE, ORE_LAVA_CRYSTAL_OBSIDIAN);

        blockList.stream().map(RegistryObject::get).forEach(this::registerDropSelfLootTable);
    }

    @SafeVarargs
    public final void removeEntries(RegistryObject<Block>... specialBlocks) {
        Arrays.stream(specialBlocks).forEach(block -> blockList.remove(block));
    }

    //////////////////////////
    // From BlockLootTables //
    //////////////////////////

    protected void registerLootTable(Block block, LootTable.Builder table) {
        this.lootTables.put(block, table);
    }

    protected static LootTable.Builder dropping(IItemProvider block) {
        return LootTable.lootTable().withPool(LootPool.lootPool()
                .setRolls(ConstantRange.exactly(1))
                .add(ItemLootEntry.lootTableItem(block))
        );
    }

    public void registerDropping(Block block, IItemProvider drop) {
        this.registerLootTable(block, dropping(drop));
    }

    public void registerDropSelfLootTable(Block block) {
        this.registerDropping(block, block);
    }

    public void registerStandardTable(String name, Block block) {
        this.registerLootTable(block, createStandardTable(name, block));
    }

    protected static LootTable.Builder droppingWithContents(Block block, ResourceLocation key) {
        return LootTable.lootTable().withPool(LootPool.lootPool()
                .setRolls(ConstantRange.exactly(1))
                .add(ItemLootEntry.lootTableItem(block)
                        .apply(CopyName.copyName(CopyName.Source.BLOCK_ENTITY))
                        .apply(CopyNbt.copyData(CopyNbt.Source.BLOCK_ENTITY)
                                .copy("Lock", "BlockEntityTag.Lock")
                                .copy("LootTable", "BlockEntityTag.LootTable")
                                .copy("LootTableSeed", "BlockEntityTag.LootTableSeed"))
                        .apply(SetContents.setContents().withEntry(DynamicLootEntry.dynamicEntry(key)))
                ));
    }

}