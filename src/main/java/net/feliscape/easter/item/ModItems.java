package net.feliscape.easter.item;

import net.feliscape.easter.Easter;
import net.feliscape.easter.item.custom.EasterEggItem;
import net.feliscape.easter.item.custom.GoldenEggItem;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Easter.MOD_ID);

    public static final RegistryObject<Item> STRIPED_RED_EGG = ITEMS.register("striped_red_egg",
            () -> new EasterEggItem(new Item.Properties()));
    public static final RegistryObject<Item> SPECKLED_EGG = ITEMS.register("speckled_egg",
            () -> new EasterEggItem(new Item.Properties()));
    public static final RegistryObject<Item> STARRY_EGG = ITEMS.register("starry_egg",
            () -> new EasterEggItem(new Item.Properties()));
    public static final RegistryObject<Item> GREEN_DOTS_EGG = ITEMS.register("green_dots_egg",
            () -> new EasterEggItem(new Item.Properties()));
    public static final RegistryObject<Item> GRASS_EGG = ITEMS.register("grass_egg",
            () -> new EasterEggItem(new Item.Properties()));
    public static final RegistryObject<Item> GOLD_RING_EGG = ITEMS.register("gold_ring_egg",
            () -> new EasterEggItem(new Item.Properties()));

    public static final RegistryObject<Item> GOLDEN_EGG = ITEMS.register("golden_egg",
            () -> new GoldenEggItem(new Item.Properties().rarity(Rarity.RARE)));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
