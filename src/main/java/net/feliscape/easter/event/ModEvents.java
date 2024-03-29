package net.feliscape.easter.event;

import net.feliscape.easter.Easter;
import net.feliscape.easter.capability.EggTracker;
import net.feliscape.easter.capability.EggTrackerProvider;
import net.feliscape.easter.item.ModItems;
import net.feliscape.easter.item.custom.EasterEggItem;
import net.feliscape.easter.networking.ModMessages;
import net.feliscape.easter.networking.packets.EggDataSyncS2CPacket;
import net.feliscape.easter.stats.ModStats;
import net.feliscape.easter.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
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
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
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
                    blockState.is(ModTags.Blocks.CAN_DROP_EGG) && level.getRandom().nextFloat() < EggTracker.BLOCK_BREAK_CHANCE){
                EggTracker.giveRandomEggsToPlayer(player, 1, true);
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
                EggTracker.giveGoldenEggsToPlayer(player, 1, true);
                EggTracker.giveRandomEggsToPlayer(player, 4, true);

            } else if (entity.getType() == EntityType.WITHER){
                EggTracker.giveGoldenEggsToPlayer(player, 1, true);
                EggTracker.giveRandomEggsToPlayer(player, 2, true);

            } else if (entity.getType() == EntityType.ELDER_GUARDIAN){
                EggTracker.giveRandomEggsToPlayer(player, 1, true);

            } else if (entity.getType() == EntityType.WARDEN){
                EggTracker.giveRandomEggsToPlayer(player, 1, true);

            } else if (entity instanceof Monster){
                if (randomSource.nextFloat() < EggTracker.MONSTER_DROP_CHANCE)
                    //EasterEggItem.dropRandomFromEntity(entity, player);
                    EggTracker.giveRandomEggsToPlayer(player, 1, true);
            }
        }

        @SubscribeEvent
        public static void onAttachCapabilitiesLiving(AttachCapabilitiesEvent<Entity> event){
            if (event.getObject() instanceof LivingEntity){
                if (!event.getObject().getCapability(EggTrackerProvider.EGG_TRACKER).isPresent()){
                    event.addCapability(new ResourceLocation(Easter.MOD_ID, "egg_tracker"), new EggTrackerProvider());
                }
            }
        }

        @SubscribeEvent
        public static void onPlayerCloned(PlayerEvent.Clone event){
            if (event.isWasDeath()){
                event.getOriginal().getCapability(EggTrackerProvider.EGG_TRACKER).ifPresent(oldStore -> {
                    event.getOriginal().getCapability(EggTrackerProvider.EGG_TRACKER).ifPresent(newStore ->{
                        newStore.copyFrom(oldStore);
                    });
                });
            }
        }

        @SubscribeEvent
        public static void onPlayerJoinWorld(EntityJoinLevelEvent event){
            if (!event.getLevel().isClientSide()){
                if (event.getEntity() instanceof ServerPlayer player){
                    player.getCapability(EggTrackerProvider.EGG_TRACKER).ifPresent(eggTracker ->{
                        ModMessages.sendToPlayer(new EggDataSyncS2CPacket(eggTracker.getEasterEggs(), eggTracker.getGoldenEggs()), player);
                    });
                }
            }
        }

        @SubscribeEvent
        public static void onRegisterCapabilities(RegisterCapabilitiesEvent event){
            event.register(EggTracker.class);
        }
    }
    @Mod.EventBusSubscriber(modid = Easter.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEventBusEvents{

    }
}
