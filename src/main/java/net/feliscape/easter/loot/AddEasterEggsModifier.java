package net.feliscape.easter.loot;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.feliscape.easter.item.custom.EasterEggItem;
import net.feliscape.easter.stats.ModStatTypes;
import net.feliscape.easter.stats.ModStats;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class AddEasterEggsModifier extends LootModifier {
    public static final Supplier<Codec<AddEasterEggsModifier>> CODEC = Suppliers.memoize(()
            -> RecordCodecBuilder.create(inst -> codecStart(inst).and(Codec.INT
            .fieldOf("minCount").forGetter(m -> m.minCount)).and(Codec.INT
            .fieldOf("maxCount").forGetter(m -> m.maxCount)).apply(inst, AddEasterEggsModifier::new)));

    private final int minCount;
    private final int maxCount;

    public AddEasterEggsModifier(LootItemCondition[] conditionsIn, int minCount, int maxCount) {
        super(conditionsIn);
        this.minCount = minCount;
        this.maxCount = maxCount;
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        for(LootItemCondition condition : this.conditions){
            if (!condition.test(context)){
                return generatedLoot;
            }
        }

        Entity entity = context.getParamOrNull(LootContextParams.THIS_ENTITY);

        int count = context.getRandom().nextInt(minCount, maxCount);
        for (int i = 0; i < count; i++){
            if (entity instanceof Player){
                ((Player) entity).awardStat(ModStats.EASTER_EGGS_FOUND);
            }
            generatedLoot.add(new ItemStack(EasterEggItem.getRandomEgg(context.getRandom())));
        }

        return generatedLoot;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}