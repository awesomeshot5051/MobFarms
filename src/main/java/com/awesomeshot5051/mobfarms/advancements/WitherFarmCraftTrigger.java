package com.awesomeshot5051.mobfarms.advancements;

import com.awesomeshot5051.mobfarms.blocks.aggressiveMobs.WitherFarmBlock;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.server.level.ServerPlayer;

import java.util.Optional;

public class WitherFarmCraftTrigger extends
        SimpleCriterionTrigger<WitherFarmCraftTrigger.TriggerInstance> {

    public static Criterion<TriggerInstance> hasCraftedWitherFarm() {
        return MobFarmCriteriaTriggers.WITHER_CRAFT.createCriterion(
                new TriggerInstance(Optional.empty(), Optional.empty()));  // Added wither farm block here
    }

    /**
     * Invoked when the user crafts a Wither Farm.
     */
    public void trigger(ServerPlayer playerEntity, WitherFarmBlock witherFarmBlock) {
        this.trigger(playerEntity, criterionInstance -> criterionInstance.matches(playerEntity, witherFarmBlock));
    }

    @Override
    public Codec<TriggerInstance> codec() {
        return TriggerInstance.CODEC;
    }

    public record TriggerInstance(
            Optional<ContextAwarePredicate> player,
            Optional<WitherFarmBlock> witherFarmBlock) implements SimpleCriterionTrigger.SimpleInstance {  // Added witherFarmBlock

        public static final Codec<TriggerInstance> CODEC =
                RecordCodecBuilder.create(instance -> instance.group(
                        EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player")
                                .forGetter(TriggerInstance::player),
                        WitherFarmBlock.CODEC.optionalFieldOf("witherFarmBlock")  // Assuming WitherFarmBlock has a codec
                                .forGetter(TriggerInstance::witherFarmBlock)
                ).apply(instance, TriggerInstance::new));

        public boolean matches(ServerPlayer player, WitherFarmBlock witherFarmBlock) {
            return this.witherFarmBlock.map(x -> x.equals(witherFarmBlock))  // Comparison logic for wither farm block
                    .orElse(true);
        }

        @Override
        public Optional<ContextAwarePredicate> player() {
            return player;
        }

        public Optional<WitherFarmBlock> witherFarmBlock() {
            return witherFarmBlock;
        }
    }
}
