package net.feliscape.easter.capability;

import net.feliscape.easter.Easter;
import net.feliscape.easter.item.custom.EasterEggItem;
import net.feliscape.easter.networking.ModMessages;
import net.feliscape.easter.networking.packets.EggDataSyncS2CPacket;
import net.feliscape.easter.stats.ModStats;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.LazyOptional;

public class EggTracker {
    public static final float BLOCK_BREAK_CHANCE = 0.04F;
    public static final float MONSTER_DROP_CHANCE = 0.05F;
    public static final float GOLDEN_EGG_CHANCE = 0.005F;

    private int easterEggs;
    private int goldenEggs;

    public static void giveEasterEggsToPlayer(Player player, int amount, boolean displayParticle) {
        LazyOptional<EggTracker> eggTrackerLazyOptional = player.getCapability(EggTrackerProvider.EGG_TRACKER);
        if (!eggTrackerLazyOptional.isPresent()){
            Easter.printDebug("Player does not have EggTracker");
            return;
        }

        for (int i = 0; i < amount; i++){
            if (displayParticle) playNormalParticle(player);
            eggTrackerLazyOptional.ifPresent(eggTracker -> {
                eggTracker.addEasterEggs(1);
                player.awardStat(ModStats.EASTER_EGGS_FOUND.get());

                if (player instanceof ServerPlayer){
                    ModMessages.sendToPlayer(new EggDataSyncS2CPacket(eggTracker.getEasterEggs(), eggTracker.getGoldenEggs()),
                            (ServerPlayer) player);
                }
            });
        }
    }

    public static void giveGoldenEggsToPlayer(Player player, int amount, boolean displayParticle) {
        LazyOptional<EggTracker> eggTrackerLazyOptional = player.getCapability(EggTrackerProvider.EGG_TRACKER);
        if (!eggTrackerLazyOptional.isPresent()){
            Easter.printDebug("Player does not have EggTracker");
            return;
        }

        for (int i = 0; i < amount; i++){
            if (displayParticle) playNormalParticle(player);
            eggTrackerLazyOptional.ifPresent(eggTracker -> {
                eggTracker.addGoldenEggs(1);
                player.awardStat(ModStats.EASTER_EGGS_FOUND.get());

                if (player instanceof ServerPlayer){
                    ModMessages.sendToPlayer(new EggDataSyncS2CPacket(eggTracker.getEasterEggs(), eggTracker.getGoldenEggs()),
                            (ServerPlayer) player);
                }
            });
        }
    }

    public static void giveRandomEggsToPlayer(Player player, int amount, boolean displayParticle) {
        LazyOptional<EggTracker> eggTrackerLazyOptional = player.getCapability(EggTrackerProvider.EGG_TRACKER);
        if (!eggTrackerLazyOptional.isPresent()){
            Easter.printDebug("Player does not have EggTracker");
            return;
        }

        for (int i = 0; i < amount; i++){
            if (player.getRandom().nextFloat() < GOLDEN_EGG_CHANCE){
                if (displayParticle) playGoldenParticle(player);
                eggTrackerLazyOptional.ifPresent(eggTracker -> {
                    eggTracker.addGoldenEggs(1);
                    player.awardStat(ModStats.EASTER_EGGS_FOUND.get());

                    if (player instanceof ServerPlayer){
                        ModMessages.sendToPlayer(new EggDataSyncS2CPacket(eggTracker.getEasterEggs(), eggTracker.getGoldenEggs()),
                                (ServerPlayer) player);
                    }
                });
            }
            else {
                if (displayParticle) playNormalParticle(player);
                eggTrackerLazyOptional.ifPresent(eggTracker -> {
                    eggTracker.addEasterEggs(1);
                    player.awardStat(ModStats.EASTER_EGGS_FOUND.get());

                    if (player instanceof ServerPlayer){
                        ModMessages.sendToPlayer(new EggDataSyncS2CPacket(eggTracker.getEasterEggs(), eggTracker.getGoldenEggs()),
                                (ServerPlayer) player);
                    }
                });
            }
        }
    }

    private static void playNormalParticle(Player player) {

    }

    private static void playGoldenParticle(Player player) {

    }

    public int getEasterEggs() {
        return easterEggs;
    }
    public void setEasterEggs(int easterEggs) {
        this.easterEggs = easterEggs;
    }

    public int getGoldenEggs() {
        return goldenEggs;
    }
    public void setGoldenEggs(int goldenEggs) {
        this.goldenEggs = goldenEggs;
    }


    private void addEasterEggs(int amount) {
        this.easterEggs += amount;
    }
    private void addGoldenEggs(int amount) {
        this.goldenEggs += amount;
    }


    public void copyFrom(EggTracker source){
        setEasterEggs(source.getEasterEggs());
        setGoldenEggs(source.getGoldenEggs());
    }

    public void saveNBTData(CompoundTag nbt){
        nbt.putInt("easter_eggs", this.getEasterEggs());
        nbt.putInt("golden_eggs", this.getGoldenEggs());
    }

    public void loadNBTData(CompoundTag nbt){
        setEasterEggs(nbt.getInt("easter_eggs"));
        setGoldenEggs(nbt.getInt("golden_eggs"));
    }
}
