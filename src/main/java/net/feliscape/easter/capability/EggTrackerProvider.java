package net.feliscape.easter.capability;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EggTrackerProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<EggTracker> EGG_TRACKER =
            CapabilityManager.get(new CapabilityToken<EggTracker>() {});

    private EggTracker eggTracker = null;
    private final LazyOptional<EggTracker> optional = LazyOptional.of(this::createEggTracker);

    private EggTracker createEggTracker() {
        if (this.eggTracker == null){
            this.eggTracker = new EggTracker();
        }

        return this.eggTracker;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == EGG_TRACKER){
            return optional.cast();
        }

        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createEggTracker().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createEggTracker().loadNBTData(nbt);
    }
}
