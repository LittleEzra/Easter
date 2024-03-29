package net.feliscape.easter.datagen;

import net.feliscape.easter.Easter;
import net.feliscape.easter.loot.AddEasterEggsModifier;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;

public class ModGlobalLootModifiersProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifiersProvider(PackOutput output) {
        super(output, Easter.MOD_ID);
    }

    @Override
    protected void start() {
        add("easter_eggs_from_plains_village", new AddEasterEggsModifier(new LootItemCondition[]{
            LootTableIdCondition.builder(new ResourceLocation("chests/village/village_plains_house")).build(),
                LootItemRandomChanceCondition.randomChance(0.5F).build()}, 1, 2));
        add("easter_eggs_from_desert_village", new AddEasterEggsModifier(new LootItemCondition[]{
            LootTableIdCondition.builder(new ResourceLocation("chests/village/village_desert_house")).build(),
                LootItemRandomChanceCondition.randomChance(0.5F).build()}, 1, 2));
        add("easter_eggs_from_savanna_village", new AddEasterEggsModifier(new LootItemCondition[]{
            LootTableIdCondition.builder(new ResourceLocation("chests/village/village_savanna_house")).build(),
                LootItemRandomChanceCondition.randomChance(0.5F).build()}, 1, 2));
        add("easter_eggs_from_snowy_village", new AddEasterEggsModifier(new LootItemCondition[]{
            LootTableIdCondition.builder(new ResourceLocation("chests/village/village_snowy_house")).build(),
                LootItemRandomChanceCondition.randomChance(0.5F).build()}, 1, 2));
        add("easter_eggs_from_taiga_village", new AddEasterEggsModifier(new LootItemCondition[]{
            LootTableIdCondition.builder(new ResourceLocation("chests/village/village_taiga_house")).build(),
                LootItemRandomChanceCondition.randomChance(0.5F).build()}, 1, 2));
    }
}
