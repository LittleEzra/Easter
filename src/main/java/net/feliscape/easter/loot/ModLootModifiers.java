package net.feliscape.easter.loot;

import com.mojang.serialization.Codec;
import net.feliscape.easter.Easter;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModLootModifiers {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIER_SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, Easter.MOD_ID);

    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> ADD_EASTER_EGGS =
            LOOT_MODIFIER_SERIALIZERS.register("add_easter_eggs", AddEasterEggsModifier.CODEC);

    public static void register(IEventBus eventBus){
        LOOT_MODIFIER_SERIALIZERS.register(eventBus);
    }
}
