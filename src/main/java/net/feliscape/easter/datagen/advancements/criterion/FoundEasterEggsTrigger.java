package net.feliscape.easter.datagen.advancements.criterion;

import com.google.gson.JsonObject;
import net.feliscape.easter.Easter;
import net.feliscape.easter.stats.ModStats;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;

public class FoundEasterEggsTrigger extends SimpleCriterionTrigger<FoundEasterEggsTrigger.TriggerInstance> {
    static final ResourceLocation ID = new ResourceLocation(Easter.MOD_ID, "found_easter_eggs");

    public ResourceLocation getId() {
        return ID;
    }

    public TriggerInstance createInstance(JsonObject pJson, ContextAwarePredicate pPredicate, DeserializationContext pDeserializationContext) {
        MinMaxBounds.Ints count = MinMaxBounds.Ints.fromJson(pJson.get("count"));
        return new TriggerInstance(pPredicate, count);
    }

    public void trigger(ServerPlayer pPlayer) {
        this.trigger(pPlayer, (triggerInstance) -> {
            return triggerInstance.matches(pPlayer);
        });
    }

    public static class TriggerInstance extends AbstractCriterionTriggerInstance {
        private final MinMaxBounds.Ints count;

        public TriggerInstance(ContextAwarePredicate pPlayer, MinMaxBounds.Ints pCount) {
            super(FoundEasterEggsTrigger.ID, pPlayer);
            this.count = pCount;
        }

        public static TriggerInstance amount(MinMaxBounds.Ints count) {
            return new TriggerInstance(ContextAwarePredicate.ANY, count);
        }

        public boolean matches(ServerPlayer player) {
            return this.count.matches(player.getStats().getValue(Stats.CUSTOM, ModStats.EASTER_EGGS_FOUND.get()));
        }

        public JsonObject serializeToJson(SerializationContext pConditions) {
            JsonObject jsonobject = super.serializeToJson(pConditions);
            jsonobject.add("count", this.count.serializeToJson());
            return jsonobject;
        }
    }
}
