package net.feliscape.easter.item.custom;

import net.feliscape.easter.datagen.advancements.criterion.FoundEasterEggsTrigger;
import net.feliscape.easter.datagen.advancements.criterion.ModCriteriaTriggers;
import net.feliscape.easter.item.ModItems;
import net.feliscape.easter.stats.ModStats;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

public class EasterEggItem extends Item {
    public static final float BLOCK_BREAK_CHANCE = 0.04F;
    public static final float MONSTER_DROP_CHANCE = 0.05F;
    public static final float GOLDEN_EGG_CHANCE = 0.01F;

    public static List<Supplier<Item>> EASTER_EGGS = List.of(
            ModItems.STRIPED_RED_EGG,
            ModItems.SPECKLED_EGG,
            ModItems.STARRY_EGG,
            ModItems.GREEN_DOTS_EGG,
            ModItems.GRASS_EGG,
            ModItems.GOLD_RING_EGG
    );

    public EasterEggItem(Properties pProperties) {
        super(pProperties);
    }

    public static Item getRandomEgg(RandomSource pRandom){
        return pRandom.nextFloat() < GOLDEN_EGG_CHANCE ? ModItems.GOLDEN_EGG.get() : EASTER_EGGS.get(pRandom.nextInt(EASTER_EGGS.size())).get();
    }
    public static void dropRandomFromBlock(Level pLevel, BlockPos pPos){
        Block.popResource(pLevel, pPos, new ItemStack(getRandomEgg(pLevel.getRandom())));
    }
    public static void dropRandomFromBlock(Level pLevel, BlockPos pPos, @Nullable Player player){
        dropRandomFromBlock(pLevel, pPos);

        if (player != null) {
            player.awardStat(ModStats.EASTER_EGGS_FOUND.get());
            if (player instanceof ServerPlayer){
                ModCriteriaTriggers.FOUND_EASTER_EGGS.trigger(((ServerPlayer) player));
            }
        }
    }
    public static void dropRandomFromEntity(Entity entity){
        entity.spawnAtLocation(new ItemStack(getRandomEgg(entity.level().getRandom())));
    }
    public static void dropRandomFromEntity(Entity entity, int count){
        for (int i = 0; i < count; i++){
            dropRandomFromEntity(entity);
        }
    }
    public static void dropRandomFromEntity(Entity entity, @Nullable Player player){
        dropRandomFromEntity(entity);

        if (player != null) {
            player.awardStat(ModStats.EASTER_EGGS_FOUND.get());
            if (player instanceof ServerPlayer){
                ModCriteriaTriggers.FOUND_EASTER_EGGS.trigger(((ServerPlayer) player));
            }
        }
    }
    public static void dropRandomFromEntity(Entity entity, int count, @Nullable Player player){
        for (int i = 0; i < count; i++){
            dropRandomFromEntity(entity);
        }
        if (player != null) {
            player.awardStat(ModStats.EASTER_EGGS_FOUND.get(), count);
            if (player instanceof ServerPlayer){
                ModCriteriaTriggers.FOUND_EASTER_EGGS.trigger(((ServerPlayer) player));
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        pTooltipComponents.add(Component.translatable("item.easter.easter_egg.tooltip").withStyle(ChatFormatting.GOLD));
    }
}
