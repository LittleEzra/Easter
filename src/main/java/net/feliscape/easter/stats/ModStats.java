package net.feliscape.easter.stats;

import net.feliscape.easter.Easter;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.StatType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModStats {
    public static final DeferredRegister<ResourceLocation> CUSTOM_STATS =
            DeferredRegister.create(Registries.CUSTOM_STAT, Easter.MOD_ID);

    public static final RegistryObject<ResourceLocation> EASTER_EGGS_FOUND = CUSTOM_STATS.register("custom",
            () -> Easter.asResource("easter_eggs_found"));

    public static void register(IEventBus eventBus){
        CUSTOM_STATS.register(eventBus);
    }
}
