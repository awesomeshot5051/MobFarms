package com.awesomeshot5051.mobfarms.advancements;

import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.resources.ResourceKey;
import net.neoforged.neoforge.registries.DeferredHolder;

public class DeferredCriterionTrigger<I extends CriterionTriggerInstance, T extends CriterionTrigger<I>> extends
        DeferredHolder<CriterionTrigger<?>, T> {

    public DeferredCriterionTrigger(ResourceKey<CriterionTrigger<?>> key) {
        super(key);
    }

    public Criterion<I> createCriterion(I triggerInstance) {
        return value().createCriterion(triggerInstance);
    }
}