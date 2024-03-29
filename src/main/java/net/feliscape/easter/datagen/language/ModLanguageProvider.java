package net.feliscape.easter.datagen.language;

import net.feliscape.easter.Easter;
import net.feliscape.easter.advancements.ModAdvancement;
import net.feliscape.easter.datagen.advancements.ModAdvancements;
import net.feliscape.easter.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class ModLanguageProvider extends LanguageProvider {
    public ModLanguageProvider(PackOutput output, String locale) {
        super(output, Easter.MOD_ID, locale);
    }

    @Override
    protected void addTranslations() {
        ModAdvancements.ENTRIES.forEach(this::addAdvancement);

        this.add(ModItems.STRIPED_RED_EGG.get(), "Easter Egg");
        this.add(ModItems.SPECKLED_EGG.get(), "Easter Egg");
        this.add(ModItems.STARRY_EGG.get(), "Easter Egg");
        this.add(ModItems.GREEN_DOTS_EGG.get(), "Easter Egg");
        this.add(ModItems.GRASS_EGG.get(), "Easter Egg");
        this.add(ModItems.GOLD_RING_EGG.get(), "Easter Egg");

        this.add(ModItems.GOLDEN_EGG.get(), "Golden Egg");

        this.add("item.easter.golden_egg.tooltip", "You found the rare Golden Egg!");
        this.add("item.easter.easter_egg.tooltip", "You found an Easter Egg!");

        this.add("stat.easter.easter_eggs_found", "Easter Eggs Found");
    }

    private void addAdvancement(ModAdvancement modAdvancement) {
        this.add(modAdvancement.titleKey(), modAdvancement.getTitle());
        this.add(modAdvancement.descriptionKey(), modAdvancement.getDescription());
    }


}

