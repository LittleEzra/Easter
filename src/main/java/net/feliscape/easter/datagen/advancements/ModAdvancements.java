package net.feliscape.easter.datagen.advancements;

import net.feliscape.easter.Easter;
import net.feliscape.easter.advancements.ModAdvancement;
import net.feliscape.easter.datagen.advancements.criterion.FoundEasterEggsTrigger;
import net.feliscape.easter.item.ModItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

public class ModAdvancements implements ForgeAdvancementProvider.AdvancementGenerator {

    public static final List<ModAdvancement> ENTRIES = new ArrayList<>();

    public static final ItemLike[] EGGS = new ItemLike[]{
            ModItems.STRIPED_RED_EGG.get(),
            ModItems.SPECKLED_EGG.get(),
            ModItems.STARRY_EGG.get(),
            ModItems.GREEN_DOTS_EGG.get(),
            ModItems.GRASS_EGG.get(),
            ModItems.GOLD_RING_EGG.get(),

            ModItems.GOLDEN_EGG.get()
    };


    public static final ModAdvancement
        ROOT = create("root", b -> b.icon(ModItems.STRIPED_RED_EGG.get())
            .title("Easter")
            .description("Find Easter Eggs by doing almost anything")
            .whenGet(EGGS)
            .special(ModAdvancement.TaskType.SILENT)),

        GOLDEN_EGG = create("golden_egg", b -> b.icon(ModItems.GOLDEN_EGG.get())
            .title("Where's The Goose?")
            .description("Find the rare Golden Egg")
            .whenGetIcon()
            .special(ModAdvancement.TaskType.SECRET)
            .after(ROOT)),

        EGGS_1 = create("eggs_one", b -> b.icon(ModItems.STRIPED_RED_EGG.get())
            .title("Got One!")
            .description("Find your first Easter Egg")
            .when(FoundEasterEggsTrigger.TriggerInstance.amount(MinMaxBounds.Ints.atLeast(1)))
            .after(ROOT)),
        EGGS_5 = create("eggs_five", b -> b.icon(ModItems.GRASS_EGG.get())
            .title("Kindergartener")
            .description("Find 5 Easter Eggs")
            .when(FoundEasterEggsTrigger.TriggerInstance.amount(MinMaxBounds.Ints.atLeast(5)))
            .after(ROOT)),
        EGGS_10 = create("eggs_ten", b -> b.icon(ModItems.GREEN_DOTS_EGG.get())
            .title("Egg Hunter")
            .description("Find 10 Easter Eggs")
            .when(FoundEasterEggsTrigger.TriggerInstance.amount(MinMaxBounds.Ints.atLeast(10)))
            .after(ROOT)),
        EGGS_20 = create("eggs_twenty", b -> b.icon(ModItems.SPECKLED_EGG.get())
            .title("Winner of the Egg Festival")
            .description("Find 20 Easter Eggs")
            .when(FoundEasterEggsTrigger.TriggerInstance.amount(MinMaxBounds.Ints.atLeast(20)))
            .after(ROOT)),
        EGGS_50 = create("eggs_fifty", b -> b.icon(ModItems.STARRY_EGG.get())
            .title("Search Squad")
            .description("Find 50 Easter Eggs")
            .when(FoundEasterEggsTrigger.TriggerInstance.amount(MinMaxBounds.Ints.atLeast(50)))
            .after(ROOT)),
        EGGS_100 = create("eggs_hundred", b -> b.icon(ModItems.GOLD_RING_EGG.get())
            .title("Master Egg Hunter")
            .description("Find 100 Easter Eggs")
            .when(FoundEasterEggsTrigger.TriggerInstance.amount(MinMaxBounds.Ints.atLeast(100)))
            .after(ROOT)),

        END = null;


    private static ModAdvancement create(String id, UnaryOperator<ModAdvancement.Builder> b) {
        return new ModAdvancement(id, b);
    }

    @Override
    public void generate(HolderLookup.Provider registries, Consumer<Advancement> pWriter, ExistingFileHelper existingFileHelper) {
        /*Advancement breweryRoot = Advancement.Builder.advancement()
                .display(ModBlocks.KEG.get(),
                        title("root"), description("root"), new ResourceLocation(Brewery.MOD_ID, "textures/gui/advancements/backgrounds/brewery.png"),
                        FrameType.TASK, false, false, false)
                .addCriterion("brewed_alcohol", BrewingTrigger.TriggerInstance.anyItem())
                .save(pWriter, advLocation("root"), existingFileHelper);

        getItemAdvancement(ModItems.BEER.get(), breweryRoot, FrameType.TASK, pWriter, existingFileHelper);*/

        for (ModAdvancement advancement : ENTRIES)
            advancement.save(pWriter);
    }

    /*protected Advancement getItemAdvancement(ItemLike pItem, Advancement parent, FrameType frameType, Consumer<Advancement> pWriter, ExistingFileHelper existingFileHelper){
        String name = "get_" + ForgeRegistries.ITEMS.getKey(pItem.asItem()).getPath();
        return Advancement.Builder.advancement().parent(parent).display(pItem, title(name), description(name),
                        (ResourceLocation)null, frameType, true, false, false)
                .addCriterion(name, InventoryChangeTrigger.TriggerInstance.hasItems(pItem))
                .save(pWriter, advLocation(name), existingFileHelper);
    }*/

    protected Component title(String advancementName){
        return Component.translatable("advancements.easter." + advancementName + ".title");
    }
    protected Component description(String advancementName){
        return Component.translatable("advancements.easter." + advancementName + ".description");
    }
    protected ResourceLocation advLocation(String advancementName){
        return new ResourceLocation(Easter.MOD_ID, "easter/" + advancementName);
    }

    protected String nameOf(ItemLike itemLike){
        return ForgeRegistries.ITEMS.getKey(itemLike.asItem()).getPath();
    }
}
