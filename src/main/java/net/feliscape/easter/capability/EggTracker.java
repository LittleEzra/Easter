package net.feliscape.easter.capability;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;

public class EggTracker {
    private int easterEggs;
    private int goldenEggs;

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
