package net.feliscape.easter.datagen.advancements.criterion;

import net.minecraft.advancements.CriteriaTriggers;

public class ModCriteriaTriggers {
    public static FoundEasterEggsTrigger FOUND_EASTER_EGGS;

    public static void registerTriggers(){
        FOUND_EASTER_EGGS = CriteriaTriggers.register(new FoundEasterEggsTrigger());
    }
}
