package net.feliscape.easter.event;

import net.feliscape.easter.Easter;
import net.feliscape.easter.item.ModItems;
import net.feliscape.easter.item.custom.EasterEggItem;
import net.feliscape.easter.stats.ModStats;
import net.feliscape.easter.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ModEvents {
    @Mod.EventBusSubscriber(modid = Easter.MOD_ID)
    public static class ForgeEvents{

        @SubscribeEvent
        public static void breakBlockEvent(BlockEvent.BreakEvent event){
            Player player = event.getPlayer();
            Level level = event.getPlayer().level();
            BlockState blockState = event.getState();
            BlockPos pos = event.getPos();

            ItemStack itemStack = player.getMainHandItem();

            if (EnchantmentHelper.getTagEnchantmentLevel(Enchantments.SILK_TOUCH, itemStack) == 0 && !itemStack.is(Items.SHEARS) &&
                    blockState.is(ModTags.Blocks.CAN_DROP_EGG) && level.getRandom().nextFloat() < EasterEggItem.BLOCK_BREAK_CHANCE){
                EasterEggItem.dropRandomFromBlock(level, pos, player);
            }
        }

        @SubscribeEvent
        public static void livingDeathEvent(LivingDeathEvent event){
            Easter.printDebug("LivingEntity died");

            LivingEntity entity = event.getEntity();
            RandomSource randomSource = entity.level().getRandom();
            DamageSource source = event.getSource();
            Entity attacker = source.getEntity();
            if (attacker == null) attacker = event.getSource().getDirectEntity();

            Player player = null;
            if (attacker instanceof Player){
                player = ((Player) attacker);
            }

            if (entity.getType() == EntityType.PLAYER || entity.getType() == EntityType.ARMOR_STAND){
                return;
            }

            if (entity.getType() == EntityType.ENDER_DRAGON){
                EasterEggItem.dropRandomFromEntity(entity, 4, player);
                entity.spawnAtLocation(new ItemStack(ModItems.GOLDEN_EGG.get()));
                if (player != null) player.awardStat(ModStats.EASTER_EGGS_FOUND);

            } else if (entity.getType() == EntityType.WITHER){
                EasterEggItem.dropRandomFromEntity(entity, 2, player);
                entity.spawnAtLocation(new ItemStack(ModItems.GOLDEN_EGG.get()));
                if (player != null) player.awardStat(ModStats.EASTER_EGGS_FOUND);

            } else if (entity.getType() == EntityType.ELDER_GUARDIAN){
                EasterEggItem.dropRandomFromEntity(entity, player);

            } else if (entity.getType() == EntityType.WARDEN){
                EasterEggItem.dropRandomFromEntity(entity, player);

            } else if (entity instanceof Monster){
                if (randomSource.nextFloat() < EasterEggItem.MONSTER_DROP_CHANCE)
                    EasterEggItem.dropRandomFromEntity(entity, player);
            }
        }
    }
    @Mod.EventBusSubscriber(modid = Easter.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEventBusEvents{

    }
}
