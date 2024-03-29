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

public class ModStatTypes {
    public static final DeferredRegister<StatType<?>> STAT_TYPES =
            DeferredRegister.create(ForgeRegistries.STAT_TYPES, Easter.MOD_ID);

    public static final RegistryObject<StatType<ResourceLocation>> CUSTOM = STAT_TYPES.register("custom",
            () -> new StatType<>(BuiltInRegistries.CUSTOM_STAT));

    public static void register(IEventBus eventBus){
        STAT_TYPES.register(eventBus);
    }
}
