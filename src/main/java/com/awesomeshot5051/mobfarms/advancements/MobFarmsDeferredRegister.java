package com.awesomeshot5051.mobfarms.advancements;

import com.awesomeshot5051.mobfarms.Main;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;

public class MobFarmsDeferredRegister<T> extends DeferredRegister<T> {

    private final Function<ResourceKey<T>, ? extends DeferredHolder<T, ?>> holderCreator;

    public MobFarmsDeferredRegister(ResourceKey<? extends Registry<T>> registryKey,
                                    Function<ResourceKey<T>, ? extends DeferredHolder<T, ?>> holderCreator) {
        super(registryKey, Main.MODID);
        this.holderCreator = holderCreator;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected <I extends T> DeferredHolder<T, I> createHolder(ResourceKey<? extends Registry<T>> registryKey, ResourceLocation key) {
        return (DeferredHolder<T, I>) holderCreator.apply(ResourceKey.create(registryKey, key));
    }
}