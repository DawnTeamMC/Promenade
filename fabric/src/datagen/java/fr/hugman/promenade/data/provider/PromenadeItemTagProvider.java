package fr.hugman.promenade.data.provider;

import fr.hugman.promenade.tag.PromenadeBlockTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.references.BlockItemIds;
import net.minecraft.references.ItemIds;
import net.minecraft.tags.BlockItemTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static fr.hugman.promenade.references.PromenadeBlockItemIds.*;
import static fr.hugman.promenade.references.PromenadeItemIds.*;

import static fr.hugman.promenade.tag.PromenadeItemTags.*;

public class PromenadeItemTagProvider extends FabricTagsProvider.ItemTagsProvider {
    public PromenadeItemTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> completableFuture, @Nullable BlockTagsProvider blockTagProvider) {
        super(output, completableFuture, blockTagProvider);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        // Promenade
        copy(PromenadeBlockTags.SAKURA_LOGS, SAKURA_LOGS);
        copy(PromenadeBlockTags.MAPLE_LOGS, MAPLE_LOGS);
        copy(PromenadeBlockTags.PALM_LOGS, PALM_LOGS);
        copy(PromenadeBlockTags.DARK_AMARANTH_STEMS, DARK_AMARANTH_STEMS);

        copy(PromenadeBlockTags.SNOWY_LEAVES, SNOWY_LEAVES);

        builder(CAPYBARA_FOOD).add(BlockItemIds.CARROT_CROP).add(ItemIds.MELON_SLICE);
        builder(DUCK_FOOD).add(BlockItemIds.WHEAT_CROP, BlockItemIds.MELON_CROP, BlockItemIds.PUMPKIN_CROP, BlockItemIds.BEETROOT_CROP, BlockItemIds.TORCHFLOWER_CROP, BlockItemIds.PITCHER_CROP);

        // Vanilla
        copy(BlockTags.PLANKS, ItemTags.PLANKS);
        copy(BlockTags.WOODEN_BUTTONS, ItemTags.WOODEN_BUTTONS);
        copy(BlockTags.BUTTONS, BlockItemTags.BUTTONS.item());
        copy(BlockTags.WOODEN_DOORS, ItemTags.WOODEN_DOORS);
        copy(BlockTags.WOODEN_STAIRS, ItemTags.WOODEN_STAIRS);
        copy(BlockTags.WOODEN_SLABS, ItemTags.WOODEN_SLABS);
        copy(BlockTags.WOODEN_FENCES, ItemTags.WOODEN_FENCES);
        copy(BlockTags.FENCE_GATES, ItemTags.FENCE_GATES);
        copy(BlockTags.WOODEN_PRESSURE_PLATES, ItemTags.WOODEN_PRESSURE_PLATES);
        copy(BlockTags.DOORS, BlockItemTags.DOORS.item());
        copy(BlockItemTags.SAPLINGS.block(), ItemTags.SAPLINGS);
        copy(BlockTags.WART_BLOCKS, ItemTags.WART_BLOCKS);
        copy(BlockItemTags.LOGS_THAT_BURN.block(), ItemTags.LOGS_THAT_BURN);
        copy(BlockTags.LOGS, ItemTags.LOGS);
        copy(BlockTags.SLABS, BlockItemTags.SLABS.item());
        copy(BlockTags.WALLS, ItemTags.WALLS);
        copy(BlockTags.STAIRS, BlockItemTags.STAIRS.item());
        copy(BlockTags.ANVIL, ItemTags.ANVIL);
        copy(BlockTags.RAILS, ItemTags.RAILS);
        copy(BlockTags.LEAVES, ItemTags.LEAVES);
        copy(BlockTags.WOODEN_TRAPDOORS, ItemTags.WOODEN_TRAPDOORS);
        copy(BlockTags.FENCES, BlockItemTags.FENCES.item());
        copy(BlockTags.BEE_ATTRACTIVE, ItemTags.BEE_FOOD);
        copy(BlockTags.SOUL_FIRE_BASE_BLOCKS, ItemTags.SOUL_FIRE_BASE_BLOCKS);
        copy(BlockTags.STANDING_SIGNS, ItemTags.SIGNS);
        copy(BlockTags.CEILING_HANGING_SIGNS, ItemTags.HANGING_SIGNS);
        copy(BlockTags.WOODEN_SHELVES, ItemTags.WOODEN_SHELVES);

        builder(ItemTags.BOATS).add(SAKURA_BOAT, MAPLE_BOAT, PALM_BOAT);
        builder(ItemTags.CHEST_BOATS).add(SAKURA_CHEST_BOAT, MAPLE_CHEST_BOAT, PALM_CHEST_BOAT);
        builder(ItemTags.MEAT).add(DUCK, COOKED_DUCK);
        builder(ItemTags.FOX_FOOD).add(BLUEBERRIES);

        builder(ItemTags.NON_FLAMMABLE_WOOD)
                .add(
                        DARK_AMARANTH_STEM,
                        STRIPPED_DARK_AMARANTH_STEM,
                        DARK_AMARANTH_HYPHAE,
                        STRIPPED_DARK_AMARANTH_HYPHAE,
                        DARK_AMARANTH_PLANKS,
                        DARK_AMARANTH_SLAB,
                        DARK_AMARANTH_PRESSURE_PLATE,
                        DARK_AMARANTH_FENCE,
                        DARK_AMARANTH_TRAPDOOR,
                        DARK_AMARANTH_FENCE_GATE,
                        DARK_AMARANTH_STAIRS,
                        DARK_AMARANTH_BUTTON,
                        DARK_AMARANTH_DOOR,
                        DARK_AMARANTH_SIGN,
                        DARK_AMARANTH_HANGING_SIGN
                );

        // Conventional
        builder(ConventionalItemTags.RAW_MEAT_FOODS).add(DUCK);
        builder(ConventionalItemTags.COOKED_MEAT_FOODS).add(COOKED_DUCK);
        builder(APRICOTS_FOODS).add(APRICOT);
        builder(BANANA_FOODS).add(BANANA);
        builder(MANGOES_FOODS).add(MANGO);
        builder(BLUEBERRIES_FOODS).add(BLUEBERRIES);

        builder(ConventionalItemTags.FOODS)
                .add(DUCK)
                .add(COOKED_DUCK);

        builder(ConventionalItemTags.FRUIT_FOODS)
                .addTag(APRICOTS_FOODS)
                .addTag(BANANA_FOODS)
                .addTag(MANGOES_FOODS);

        builder(ConventionalItemTags.BERRY_FOODS)
                .addTag(BLUEBERRIES_FOODS);

        builder(MAPLE_SYRUP_DRINKS)
                .add(MAPLE_SYRUP_BOTTLE);

        builder(ConventionalItemTags.DRINK_CONTAINING_BOTTLE)
                .add(MAPLE_SYRUP_BOTTLE);

        copy(ConventionalBlockTags.STONES, ConventionalItemTags.STONES);
        copy(ConventionalBlockTags.FENCES, ConventionalItemTags.FENCES);
        copy(ConventionalBlockTags.WOODEN_FENCES, ConventionalItemTags.WOODEN_FENCES);
        copy(ConventionalBlockTags.FENCE_GATES, ConventionalItemTags.FENCE_GATES);
        copy(ConventionalBlockTags.WOODEN_FENCE_GATES, ConventionalItemTags.WOODEN_FENCE_GATES);
        copy(ConventionalBlockTags.STRIPPED_WOODS, ConventionalItemTags.STRIPPED_WOODS);
        copy(ConventionalBlockTags.STRIPPED_LOGS, ConventionalItemTags.STRIPPED_LOGS);
        copy(PromenadeBlockTags.IGNEOUS_ROCKS, IGNEOUS_ROCKS);
    }
}