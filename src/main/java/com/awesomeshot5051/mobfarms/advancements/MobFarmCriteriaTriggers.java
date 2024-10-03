package com.awesomeshot5051.mobfarms.advancements;

import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;

import java.util.function.Supplier;

public class MobFarmCriteriaTriggers {

    private static final MobFarmsDeferredRegister<CriterionTrigger<?>> deferredRegister =
            new MobFarmsDeferredRegister<>(Registries.TRIGGER_TYPE, DeferredCriterionTrigger::new);
    public static final DeferredCriterionTrigger<WitherFarmCraftTrigger.TriggerInstance, WitherFarmCraftTrigger> WITHER_CRAFT =
            register("wither_craft", WitherFarmCraftTrigger::new);

    public static void register(IEventBus modEventBus) {
        deferredRegister.register(modEventBus);
    }

    private static <I extends CriterionTriggerInstance, T extends CriterionTrigger<I>> DeferredCriterionTrigger<I, T> register(String name, Supplier<T> sup) {
        return (DeferredCriterionTrigger<I, T>) deferredRegister.register(name, sup);
    }
}