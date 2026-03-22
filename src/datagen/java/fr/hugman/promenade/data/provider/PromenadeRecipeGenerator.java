package fr.hugman.promenade.data.provider;

import fr.hugman.promenade.block.PromenadeBlocks;
import fr.hugman.promenade.data.PromenadeBlockFamilies;
import fr.hugman.promenade.item.PromenadeItems;
import fr.hugman.promenade.tag.PromenadeItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.CampfireCookingRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.item.crafting.SmokingRecipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import java.util.concurrent.CompletableFuture;

public class PromenadeRecipeGenerator extends RecipeProvider {
    public PromenadeRecipeGenerator(HolderLookup.Provider registries, RecipeOutput exporter) {
        super(registries, exporter);
    }

    @Override
    public void buildRecipes() {
        PromenadeBlockFamilies.getFamilies().filter(BlockFamily::shouldGenerateRecipe).forEach(family -> this.generateRecipes(family, FeatureFlagSet.of(FeatureFlags.VANILLA)));

        this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, PromenadeBlocks.ASPHALT_SLAB, PromenadeBlocks.ASPHALT, 2);
        this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, PromenadeBlocks.ASPHALT_STAIRS, PromenadeBlocks.ASPHALT);
        this.stonecutterResultFromBase(RecipeCategory.DECORATIONS, PromenadeBlocks.ASPHALT_WALL, PromenadeBlocks.ASPHALT);
        this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, PromenadeBlocks.POLISHED_ASPHALT, PromenadeBlocks.ASPHALT);
        this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, PromenadeBlocks.POLISHED_ASPHALT_SLAB, PromenadeBlocks.ASPHALT, 2);
        this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, PromenadeBlocks.POLISHED_ASPHALT_STAIRS, PromenadeBlocks.ASPHALT);
        this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, PromenadeBlocks.POLISHED_ASPHALT_SLAB, PromenadeBlocks.POLISHED_ASPHALT, 2);
        this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, PromenadeBlocks.POLISHED_ASPHALT_STAIRS, PromenadeBlocks.POLISHED_ASPHALT);

        this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, PromenadeBlocks.BLUNITE_SLAB, PromenadeBlocks.BLUNITE, 2);
        this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, PromenadeBlocks.BLUNITE_STAIRS, PromenadeBlocks.BLUNITE);
        this.stonecutterResultFromBase(RecipeCategory.DECORATIONS, PromenadeBlocks.BLUNITE_WALL, PromenadeBlocks.BLUNITE);
        this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, PromenadeBlocks.POLISHED_BLUNITE, PromenadeBlocks.BLUNITE);
        this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, PromenadeBlocks.POLISHED_BLUNITE_SLAB, PromenadeBlocks.BLUNITE, 2);
        this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, PromenadeBlocks.POLISHED_BLUNITE_STAIRS, PromenadeBlocks.BLUNITE);
        this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, PromenadeBlocks.POLISHED_BLUNITE_SLAB, PromenadeBlocks.POLISHED_BLUNITE, 2);
        this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, PromenadeBlocks.POLISHED_BLUNITE_STAIRS, PromenadeBlocks.POLISHED_BLUNITE);

        this.offerLeafPileRecipe(PromenadeBlocks.OAK_LEAF_PILE, Blocks.OAK_LEAVES);
        this.offerLeafPileRecipe(PromenadeBlocks.SPRUCE_LEAF_PILE, Blocks.SPRUCE_LEAVES);
        this.offerLeafPileRecipe(PromenadeBlocks.BIRCH_LEAF_PILE, Blocks.BIRCH_LEAVES);
        this.offerLeafPileRecipe(PromenadeBlocks.JUNGLE_LEAF_PILE, Blocks.JUNGLE_LEAVES);
        this.offerLeafPileRecipe(PromenadeBlocks.ACACIA_LEAF_PILE, Blocks.ACACIA_LEAVES);
        this.offerLeafPileRecipe(PromenadeBlocks.CHERRY_LEAF_PILE, Blocks.CHERRY_LEAVES);
        this.offerLeafPileRecipe(PromenadeBlocks.DARK_OAK_LEAF_PILE, Blocks.DARK_OAK_LEAVES);
        this.offerLeafPileRecipe(PromenadeBlocks.PALE_OAK_LEAF_PILE, Blocks.PALE_OAK_LEAVES);
        this.offerLeafPileRecipe(PromenadeBlocks.MANGROVE_LEAF_PILE, Blocks.MANGROVE_LEAVES);
        this.offerLeafPileRecipe(PromenadeBlocks.AZALEA_LEAF_PILE, Blocks.AZALEA_LEAVES);
        this.offerLeafPileRecipe(PromenadeBlocks.FLOWERING_AZALEA_LEAF_PILE, Blocks.FLOWERING_AZALEA_LEAVES);

        this.offerFlowerPileRecipe(PromenadeBlocks.DANDELION_PILE, Blocks.DANDELION);
        this.offerFlowerPileRecipe(PromenadeBlocks.POPPY_PILE, Blocks.POPPY);
        this.offerFlowerPileRecipe(PromenadeBlocks.BLUE_ORCHID_PILE, Blocks.BLUE_ORCHID);
        this.offerFlowerPileRecipe(PromenadeBlocks.ALLIUM_PILE, Blocks.ALLIUM);
        this.offerFlowerPileRecipe(PromenadeBlocks.AZURE_BLUET_PILE, Blocks.AZURE_BLUET);
        this.offerFlowerPileRecipe(PromenadeBlocks.RED_TULIP_PILE, Blocks.RED_TULIP);
        this.offerFlowerPileRecipe(PromenadeBlocks.ORANGE_TULIP_PILE, Blocks.ORANGE_TULIP);
        this.offerFlowerPileRecipe(PromenadeBlocks.WHITE_TULIP_PILE, Blocks.WHITE_TULIP);
        this.offerFlowerPileRecipe(PromenadeBlocks.PINK_TULIP_PILE, Blocks.PINK_TULIP);
        this.offerFlowerPileRecipe(PromenadeBlocks.OXEYE_DAISY_PILE, Blocks.OXEYE_DAISY);
        this.offerFlowerPileRecipe(PromenadeBlocks.CORNFLOWER_PILE, Blocks.CORNFLOWER);
        this.offerFlowerPileRecipe(PromenadeBlocks.LILY_OF_THE_VALLEY_PILE, Blocks.LILY_OF_THE_VALLEY);
        this.offerFlowerPileRecipe(PromenadeBlocks.WITHER_ROSE_PILE, Blocks.WITHER_ROSE);

        this.offerSnowyLeavesRecipe(PromenadeBlocks.SNOWY_OAK_LEAVES, Blocks.OAK_LEAVES);
        this.offerSnowyLeavesRecipe(PromenadeBlocks.SNOWY_SPRUCE_LEAVES, Blocks.SPRUCE_LEAVES);
        this.offerSnowyLeavesRecipe(PromenadeBlocks.SNOWY_BIRCH_LEAVES, Blocks.BIRCH_LEAVES);
        this.offerSnowyLeavesRecipe(PromenadeBlocks.SNOWY_JUNGLE_LEAVES, Blocks.JUNGLE_LEAVES);
        this.offerSnowyLeavesRecipe(PromenadeBlocks.SNOWY_ACACIA_LEAVES, Blocks.ACACIA_LEAVES);
        this.offerSnowyLeavesRecipe(PromenadeBlocks.SNOWY_CHERRY_LEAVES, Blocks.CHERRY_LEAVES);
        this.offerSnowyLeavesRecipe(PromenadeBlocks.SNOWY_DARK_OAK_LEAVES, Blocks.DARK_OAK_LEAVES);
        this.offerSnowyLeavesRecipe(PromenadeBlocks.SNOWY_PALE_OAK_LEAVES, Blocks.PALE_OAK_LEAVES);
        this.offerSnowyLeavesRecipe(PromenadeBlocks.SNOWY_MANGROVE_LEAVES, Blocks.MANGROVE_LEAVES);
        this.offerSnowyLeavesRecipe(PromenadeBlocks.SNOWY_AZALEA_LEAVES, Blocks.AZALEA_LEAVES);
        this.offerSnowyLeavesRecipe(PromenadeBlocks.SNOWY_FLOWERING_AZALEA_LEAVES, Blocks.FLOWERING_AZALEA_LEAVES);

        this.planksFromLogs(PromenadeBlocks.SAKURA_PLANKS, PromenadeItemTags.SAKURA_LOGS, 4);
        this.woodFromLogs(PromenadeBlocks.SAKURA_WOOD, PromenadeBlocks.SAKURA_LOG);
        this.woodFromLogs(PromenadeBlocks.STRIPPED_SAKURA_WOOD, PromenadeBlocks.STRIPPED_SAKURA_LOG);
        this.hangingSign(PromenadeItems.SAKURA_HANGING_SIGN, PromenadeBlocks.STRIPPED_SAKURA_LOG);
        this.shelf(PromenadeBlocks.SAKURA_SHELF, PromenadeBlocks.STRIPPED_SAKURA_LOG);
        this.offerSnowyLeavesRecipe(PromenadeBlocks.SNOWY_BLUSH_SAKURA_BLOSSOMS, PromenadeBlocks.BLUSH_SAKURA_BLOSSOMS);
        this.offerSnowyLeavesRecipe(PromenadeBlocks.SNOWY_COTTON_SAKURA_BLOSSOMS, PromenadeBlocks.COTTON_SAKURA_BLOSSOMS);
        this.offerLeafPileRecipe(PromenadeBlocks.BLUSH_SAKURA_BLOSSOM_PILE, PromenadeBlocks.BLUSH_SAKURA_BLOSSOM_PILE);
        this.offerLeafPileRecipe(PromenadeBlocks.COTTON_SAKURA_BLOSSOM_PILE, PromenadeBlocks.COTTON_SAKURA_BLOSSOM_PILE);
        this.woodenBoat(PromenadeItems.SAKURA_BOAT, PromenadeBlocks.SAKURA_PLANKS);
        this.chestBoat(PromenadeItems.SAKURA_CHEST_BOAT, PromenadeItems.SAKURA_BOAT);

        this.planksFromLogs(PromenadeBlocks.MAPLE_PLANKS, PromenadeItemTags.MAPLE_LOGS, 4);
        this.woodFromLogs(PromenadeBlocks.MAPLE_WOOD, PromenadeBlocks.MAPLE_LOG);
        this.woodFromLogs(PromenadeBlocks.STRIPPED_MAPLE_WOOD, PromenadeBlocks.STRIPPED_MAPLE_LOG);
        this.hangingSign(PromenadeItems.MAPLE_HANGING_SIGN, PromenadeBlocks.STRIPPED_MAPLE_LOG);
        this.shelf(PromenadeBlocks.MAPLE_SHELF, PromenadeBlocks.STRIPPED_MAPLE_LOG);
        this.offerSnowyLeavesRecipe(PromenadeBlocks.SNOWY_SAP_MAPLE_LEAVES, PromenadeBlocks.SAP_MAPLE_LEAVES);
        this.offerSnowyLeavesRecipe(PromenadeBlocks.SNOWY_VERMILION_MAPLE_LEAVES, PromenadeBlocks.VERMILION_MAPLE_LEAVES);
        this.offerSnowyLeavesRecipe(PromenadeBlocks.SNOWY_FULVOUS_MAPLE_LEAVES, PromenadeBlocks.FULVOUS_MAPLE_LEAVES);
        this.offerSnowyLeavesRecipe(PromenadeBlocks.SNOWY_MIKADO_MAPLE_LEAVES, PromenadeBlocks.MIKADO_MAPLE_LEAVES);
        this.offerFallenLeavesRecipe(PromenadeBlocks.FALLEN_SAP_MAPLE_LEAVES, PromenadeBlocks.SAP_MAPLE_LEAVES);
        this.offerFallenLeavesRecipe(PromenadeBlocks.FALLEN_VERMILION_MAPLE_LEAVES, PromenadeBlocks.SAP_MAPLE_LEAVES);
        this.offerFallenLeavesRecipe(PromenadeBlocks.FALLEN_FULVOUS_MAPLE_LEAVES, PromenadeBlocks.FULVOUS_MAPLE_LEAVES);
        this.offerFallenLeavesRecipe(PromenadeBlocks.FALLEN_MIKADO_MAPLE_LEAVES, PromenadeBlocks.MIKADO_MAPLE_LEAVES);
        this.offerLeafPileRecipe(PromenadeBlocks.SAP_MAPLE_LEAF_PILE, PromenadeBlocks.SAP_MAPLE_LEAVES);
        this.offerLeafPileRecipe(PromenadeBlocks.VERMILION_MAPLE_LEAF_PILE, PromenadeBlocks.VERMILION_MAPLE_LEAF_PILE);
        this.offerLeafPileRecipe(PromenadeBlocks.FULVOUS_MAPLE_LEAF_PILE, PromenadeBlocks.FULVOUS_MAPLE_LEAVES);
        this.offerLeafPileRecipe(PromenadeBlocks.MIKADO_MAPLE_LEAF_PILE, PromenadeBlocks.MIKADO_MAPLE_LEAVES);
        this.woodenBoat(PromenadeItems.MAPLE_BOAT, PromenadeBlocks.MAPLE_PLANKS);
        this.chestBoat(PromenadeItems.MAPLE_CHEST_BOAT, PromenadeItems.MAPLE_BOAT);

        this.planksFromLogs(PromenadeBlocks.PALM_PLANKS, PromenadeItemTags.PALM_LOGS, 4);
        this.woodFromLogs(PromenadeBlocks.PALM_WOOD, PromenadeBlocks.PALM_LOG);
        this.woodFromLogs(PromenadeBlocks.STRIPPED_PALM_WOOD, PromenadeBlocks.STRIPPED_PALM_LOG);
        this.hangingSign(PromenadeItems.PALM_HANGING_SIGN, PromenadeBlocks.STRIPPED_PALM_LOG);
        this.shelf(PromenadeBlocks.PALM_SHELF, PromenadeBlocks.STRIPPED_PALM_LOG);
        this.offerSnowyLeavesRecipe(PromenadeBlocks.SNOWY_PALM_LEAVES, PromenadeBlocks.PALM_LEAVES);
        this.offerLeafPileRecipe(PromenadeBlocks.PALM_LEAF_PILE, PromenadeBlocks.PALM_LEAVES);
        this.woodenBoat(PromenadeItems.PALM_BOAT, PromenadeBlocks.PALM_PLANKS);
        this.chestBoat(PromenadeItems.PALM_CHEST_BOAT, PromenadeItems.PALM_BOAT);

        this.planksFromLogs(PromenadeBlocks.DARK_AMARANTH_PLANKS, PromenadeItemTags.DARK_AMARANTH_STEMS, 4);
        this.woodFromLogs(PromenadeBlocks.DARK_AMARANTH_HYPHAE, PromenadeBlocks.DARK_AMARANTH_STEM);
        this.woodFromLogs(PromenadeBlocks.STRIPPED_DARK_AMARANTH_HYPHAE, PromenadeBlocks.STRIPPED_DARK_AMARANTH_STEM);
        this.hangingSign(PromenadeItems.DARK_AMARANTH_HANGING_SIGN, PromenadeBlocks.STRIPPED_DARK_AMARANTH_STEM);
        this.shelf(PromenadeBlocks.DARK_AMARANTH_SHELF, PromenadeBlocks.STRIPPED_DARK_AMARANTH_STEM);

        this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, PromenadeBlocks.MOAI, Blocks.TUFF);

        this.cookRecipes("smelting", RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, 200);
        this.cookRecipes("smoking", RecipeSerializer.SMOKING_RECIPE, SmokingRecipe::new, 100);
        this.cookRecipes("campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING_RECIPE, CampfireCookingRecipe::new, 600);

        this.oneToOneConversionRecipe(Items.MAGENTA_DYE, PromenadeItems.BLUEBERRIES, "magenta_dye", 1);

        this.shapeless(RecipeCategory.MISC, PromenadeItems.BOVINE_BANNER_PATTERN)
                .requires(Items.PAPER)
                .requires(Items.LEATHER)
                .unlockedBy("has_leather", this.has(Items.LEATHER))
                .save(this.output);
    }

    public void offerSnowyLeavesRecipe(ItemLike output, ItemLike input) {
        this.shaped(RecipeCategory.DECORATIONS, output, 1)
                .define('_', Blocks.SNOW)
                .define('#', input)
                .pattern("_")
                .pattern("#")
                .group("snowy_leaves")
                .unlockedBy(getHasName(input), this.has(input))
                .save(this.output);

    }

    public void offerFallenLeavesRecipe(ItemLike output, ItemLike input) {
        this.shaped(RecipeCategory.DECORATIONS, output, 2)
                .define('#', input)
                .pattern("##")
                .group("fallen_leaves")
                .unlockedBy(getHasName(input), this.has(input))
                .save(this.output);
    }


    public void offerLeafPileRecipe(ItemLike output, ItemLike input) {
        this.shaped(RecipeCategory.DECORATIONS, output, 3)
                .define('#', input)
                .pattern("###")
                .group("leaf_pile")
                .unlockedBy(getHasName(input), this.has(input))
                .save(this.output);
    }

    public void offerFlowerPileRecipe(ItemLike output, ItemLike input) {
        this.shaped(RecipeCategory.DECORATIONS, output, 2)
                .define('#', input)
                .pattern("##")
                .group("flower_pile")
                .unlockedBy(getHasName(input), this.has(input))
                .save(this.output);
    }

    public <T extends AbstractCookingRecipe> void cookRecipes(
            String cooker, RecipeSerializer<T> serializer, AbstractCookingRecipe.Factory<T> recipeFactory, int cookingTime
    ) {
        this.simpleCookingRecipe(cooker, serializer, recipeFactory, cookingTime, PromenadeItems.DUCK, PromenadeItems.COOKED_DUCK, 0.35F);
    }

    public static FabricRecipeProvider create(FabricDataOutput fabricDataOutput, CompletableFuture<HolderLookup.Provider> completableFuture) {
        return new FabricRecipeProvider(fabricDataOutput, completableFuture) {
            @Override
            protected RecipeProvider createRecipeProvider(HolderLookup.Provider wrapperLookup, RecipeOutput recipeExporter) {
                return new PromenadeRecipeGenerator(wrapperLookup, recipeExporter);
            }

            @Override
            public String getName() {
                return "Recipes";
            }
        };
    }
}
