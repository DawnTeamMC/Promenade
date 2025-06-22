package fr.hugman.promenade.data.provider;

import fr.hugman.promenade.block.PromenadeBlocks;
import fr.hugman.promenade.tag.PromenadeBlockTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static fr.hugman.promenade.item.PromenadeItems.*;
import static fr.hugman.promenade.tag.PromenadeItemTags.*;

public class PromenadeItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public PromenadeItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture, @Nullable BlockTagProvider blockTagProvider) {
        super(output, completableFuture, blockTagProvider);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        // Promenade
        copy(PromenadeBlockTags.SAKURA_LOGS, SAKURA_LOGS);
        copy(PromenadeBlockTags.MAPLE_LOGS, MAPLE_LOGS);
        copy(PromenadeBlockTags.PALM_LOGS, PALM_LOGS);
        copy(PromenadeBlockTags.DARK_AMARANTH_STEMS, DARK_AMARANTH_STEMS);

        copy(PromenadeBlockTags.SNOWY_LEAVES, SNOWY_LEAVES);

        valueLookupBuilder(CAPYBARA_FOOD).add(Items.CARROT, Items.MELON_SLICE);
        valueLookupBuilder(DUCK_FOOD).add(Items.WHEAT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS, Items.TORCHFLOWER_SEEDS, Items.PITCHER_POD);

        // Vanilla
        copy(BlockTags.PLANKS, ItemTags.PLANKS);
        copy(BlockTags.WOODEN_BUTTONS, ItemTags.WOODEN_BUTTONS);
        copy(BlockTags.BUTTONS, ItemTags.BUTTONS);
        copy(BlockTags.WOODEN_DOORS, ItemTags.WOODEN_DOORS);
        copy(BlockTags.WOODEN_STAIRS, ItemTags.WOODEN_STAIRS);
        copy(BlockTags.WOODEN_SLABS, ItemTags.WOODEN_SLABS);
        copy(BlockTags.WOODEN_FENCES, ItemTags.WOODEN_FENCES);
        copy(BlockTags.FENCE_GATES, ItemTags.FENCE_GATES);
        copy(BlockTags.WOODEN_PRESSURE_PLATES, ItemTags.WOODEN_PRESSURE_PLATES);
        copy(BlockTags.DOORS, ItemTags.DOORS);
        copy(BlockTags.SAPLINGS, ItemTags.SAPLINGS);
        copy(BlockTags.WART_BLOCKS, ItemTags.WART_BLOCKS);
        copy(BlockTags.LOGS_THAT_BURN, ItemTags.LOGS_THAT_BURN);
        copy(BlockTags.LOGS, ItemTags.LOGS);
        copy(BlockTags.SLABS, ItemTags.SLABS);
        copy(BlockTags.WALLS, ItemTags.WALLS);
        copy(BlockTags.STAIRS, ItemTags.STAIRS);
        copy(BlockTags.ANVIL, ItemTags.ANVIL);
        copy(BlockTags.RAILS, ItemTags.RAILS);
        copy(BlockTags.LEAVES, ItemTags.LEAVES);
        copy(BlockTags.WOODEN_TRAPDOORS, ItemTags.WOODEN_TRAPDOORS);
        copy(BlockTags.FENCES, ItemTags.FENCES);
        copy(BlockTags.BEE_ATTRACTIVE, ItemTags.BEE_FOOD);
        copy(BlockTags.SOUL_FIRE_BASE_BLOCKS, ItemTags.SOUL_FIRE_BASE_BLOCKS);
        copy(BlockTags.STANDING_SIGNS, ItemTags.SIGNS);
        copy(BlockTags.CEILING_HANGING_SIGNS, ItemTags.HANGING_SIGNS);

        valueLookupBuilder(ItemTags.BOATS).add(SAKURA_BOAT, MAPLE_BOAT, PALM_BOAT);
        valueLookupBuilder(ItemTags.CHEST_BOATS).add(SAKURA_CHEST_BOAT, MAPLE_CHEST_BOAT, PALM_CHEST_BOAT);
        valueLookupBuilder(ItemTags.MEAT).add(DUCK, COOKED_DUCK);
        valueLookupBuilder(ItemTags.FOX_FOOD).add(BLUEBERRIES);

        valueLookupBuilder(ItemTags.NON_FLAMMABLE_WOOD)
                .add(
                        PromenadeBlocks.DARK_AMARANTH_STEM.asItem(),
                        PromenadeBlocks.STRIPPED_DARK_AMARANTH_STEM.asItem(),
                        PromenadeBlocks.DARK_AMARANTH_HYPHAE.asItem(),
                        PromenadeBlocks.STRIPPED_DARK_AMARANTH_HYPHAE.asItem(),
                        PromenadeBlocks.DARK_AMARANTH_PLANKS.asItem(),
                        PromenadeBlocks.DARK_AMARANTH_SLAB.asItem(),
                        PromenadeBlocks.DARK_AMARANTH_PRESSURE_PLATE.asItem(),
                        PromenadeBlocks.DARK_AMARANTH_FENCE.asItem(),
                        PromenadeBlocks.DARK_AMARANTH_TRAPDOOR.asItem(),
                        PromenadeBlocks.DARK_AMARANTH_FENCE_GATE.asItem(),
                        PromenadeBlocks.DARK_AMARANTH_STAIRS.asItem(),
                        PromenadeBlocks.DARK_AMARANTH_BUTTON.asItem(),
                        PromenadeBlocks.DARK_AMARANTH_DOOR.asItem(),
                        DARK_AMARANTH_SIGN,
                        DARK_AMARANTH_HANGING_SIGN
                );

        // Conventional
        valueLookupBuilder(ConventionalItemTags.RAW_MEAT_FOODS).add(DUCK);
        valueLookupBuilder(ConventionalItemTags.COOKED_MEAT_FOODS).add(COOKED_DUCK);
        copy(ConventionalBlockTags.STONES, ConventionalItemTags.STONES);
        copy(ConventionalBlockTags.FENCES, ConventionalItemTags.FENCES);
        copy(ConventionalBlockTags.WOODEN_FENCES, ConventionalItemTags.WOODEN_FENCES);
        copy(ConventionalBlockTags.FENCE_GATES, ConventionalItemTags.FENCE_GATES);
        copy(ConventionalBlockTags.WOODEN_FENCE_GATES, ConventionalItemTags.WOODEN_FENCE_GATES);
        copy(ConventionalBlockTags.STRIPPED_WOODS, ConventionalItemTags.STRIPPED_WOODS);
        copy(ConventionalBlockTags.STRIPPED_LOGS, ConventionalItemTags.STRIPPED_LOGS);
    }
}