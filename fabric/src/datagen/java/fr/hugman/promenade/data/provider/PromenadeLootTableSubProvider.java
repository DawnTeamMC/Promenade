package fr.hugman.promenade.data.provider;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableSubProvider;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.context.ContextKeySet;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

import java.util.concurrent.CompletableFuture;

/**
 * Base class for the loot table sub providers that build their tables by hand, as opposed to the block and entity
 * ones which extend their vanilla counterparts.
 */
public abstract class PromenadeLootTableSubProvider extends SimpleFabricLootTableSubProvider {
    protected final HolderLookup.Provider registries;
    protected final HolderGetter<Enchantment> enchantments;
    protected final HolderGetter<Block> blocks;
    protected final HolderGetter<Fluid> fluids;
    protected final HolderGetter<Item> items;

    protected PromenadeLootTableSubProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookup, ContextKeySet contextParamSet) {
        super(output, registryLookup, contextParamSet);
        this.registries = registryLookup.join();
        this.enchantments = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        this.blocks = this.registries.lookupOrThrow(Registries.BLOCK);
        this.fluids = this.registries.lookupOrThrow(Registries.FLUID);
        this.items = this.registries.lookupOrThrow(Registries.ITEM);
    }

    /**
     * Never called: the data generator drives these providers through {@link #run(net.minecraft.data.CachedOutput)},
     * which collects the tables from {@link #generate(java.util.function.BiConsumer)}.
     */
    @Override
    public void run() {
    }
}
