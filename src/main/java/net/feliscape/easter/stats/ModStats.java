package net.feliscape.easter.stats;

import net.feliscape.easter.Easter;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.Stat;
import net.minecraft.stats.StatFormatter;
import net.minecraft.stats.Stats;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModStats {

    public static final ResourceLocation EASTER_EGGS_FOUND = makeCustomStat("easter_eggs_found", StatFormatter.DEFAULT);

    private static ResourceLocation makeCustomStat(String pKey, StatFormatter pFormatter) {
        ResourceLocation resourcelocation = new ResourceLocation(Easter.MOD_ID, pKey);
        Registry.register(BuiltInRegistries.CUSTOM_STAT, pKey, resourcelocation);
        Stats.CUSTOM.get(resourcelocation, pFormatter);
        return resourcelocation;
    }
}
