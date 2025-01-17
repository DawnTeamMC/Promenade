package fr.hugman.promenade.data.generator;

import fr.hugman.promenade.block.PromenadeBlocks;
import fr.hugman.promenade.data.PromenadeBlockFamilies;
import fr.hugman.promenade.item.PromenadeItems;
import fr.hugman.promenade.tag.PromenadeItemTags;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.resource.featuretoggle.FeatureSet;

public class PromenadeRecipeGenerator extends RecipeGenerator {
    public PromenadeRecipeGenerator(RegistryWrapper.WrapperLookup registries, RecipeExporter exporter) {
        super(registries, exporter);
    }

    @Override
    public void generate() {
        PromenadeBlockFamilies.getFamilies().filter(BlockFamily::shouldGenerateRecipes).forEach(family -> this.generateFamily(family, FeatureSet.of(FeatureFlags.VANILLA)));

        this.offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, PromenadeBlocks.ASPHALT_SLAB, PromenadeBlocks.ASPHALT, 2);
        this.offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, PromenadeBlocks.ASPHALT_STAIRS, PromenadeBlocks.ASPHALT);
        this.offerStonecuttingRecipe(RecipeCategory.DECORATIONS, PromenadeBlocks.ASPHALT_WALL, PromenadeBlocks.ASPHALT);
        this.offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, PromenadeBlocks.POLISHED_ASPHALT, PromenadeBlocks.ASPHALT);
        this.offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, PromenadeBlocks.POLISHED_ASPHALT_SLAB, PromenadeBlocks.ASPHALT, 2);
        this.offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, PromenadeBlocks.POLISHED_ASPHALT_STAIRS, PromenadeBlocks.ASPHALT);
        this.offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, PromenadeBlocks.POLISHED_ASPHALT_SLAB, PromenadeBlocks.POLISHED_ASPHALT, 2);
        this.offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, PromenadeBlocks.POLISHED_ASPHALT_STAIRS, PromenadeBlocks.POLISHED_ASPHALT);

        this.offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, PromenadeBlocks.BLUNITE_SLAB, PromenadeBlocks.BLUNITE, 2);
        this.offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, PromenadeBlocks.BLUNITE_STAIRS, PromenadeBlocks.BLUNITE);
        this.offerStonecuttingRecipe(RecipeCategory.DECORATIONS, PromenadeBlocks.BLUNITE_WALL, PromenadeBlocks.BLUNITE);
        this.offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, PromenadeBlocks.POLISHED_BLUNITE, PromenadeBlocks.BLUNITE);
        this.offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, PromenadeBlocks.POLISHED_BLUNITE_SLAB, PromenadeBlocks.BLUNITE, 2);
        this.offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, PromenadeBlocks.POLISHED_BLUNITE_STAIRS, PromenadeBlocks.BLUNITE);
        this.offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, PromenadeBlocks.POLISHED_BLUNITE_SLAB, PromenadeBlocks.POLISHED_BLUNITE, 2);
        this.offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, PromenadeBlocks.POLISHED_BLUNITE_STAIRS, PromenadeBlocks.POLISHED_BLUNITE);

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

        this.offerSnowyLeavesRecipe(PromenadeBlocks.SNOWY_SPRUCE_LEAVES, Blocks.SPRUCE_LEAVES);

        this.offerPlanksRecipe(PromenadeBlocks.SAKURA_PLANKS, PromenadeItemTags.SAKURA_LOGS, 4);
        this.offerBarkBlockRecipe(PromenadeBlocks.SAKURA_WOOD, PromenadeBlocks.SAKURA_LOG);
        this.offerBarkBlockRecipe(PromenadeBlocks.STRIPPED_SAKURA_WOOD, PromenadeBlocks.STRIPPED_SAKURA_LOG);
        this.offerHangingSignRecipe(PromenadeItems.SAKURA_HANGING_SIGN, PromenadeBlocks.STRIPPED_SAKURA_LOG);
        this.offerLeafPileRecipe(PromenadeBlocks.BLUSH_SAKURA_BLOSSOM_PILE, PromenadeBlocks.BLUSH_SAKURA_BLOSSOM_PILE);
        this.offerLeafPileRecipe(PromenadeBlocks.COTTON_SAKURA_BLOSSOM_PILE, PromenadeBlocks.COTTON_SAKURA_BLOSSOM_PILE);
        this.offerBoatRecipe(PromenadeItems.SAKURA_BOAT, PromenadeBlocks.SAKURA_PLANKS);
        this.offerChestBoatRecipe(PromenadeItems.SAKURA_CHEST_BOAT, PromenadeItems.SAKURA_BOAT);

        this.offerPlanksRecipe(PromenadeBlocks.MAPLE_PLANKS, PromenadeItemTags.MAPLE_LOGS, 4);
        this.offerBarkBlockRecipe(PromenadeBlocks.MAPLE_WOOD, PromenadeBlocks.MAPLE_LOG);
        this.offerBarkBlockRecipe(PromenadeBlocks.STRIPPED_MAPLE_WOOD, PromenadeBlocks.STRIPPED_MAPLE_LOG);
        this.offerHangingSignRecipe(PromenadeItems.MAPLE_HANGING_SIGN, PromenadeBlocks.STRIPPED_MAPLE_LOG);
        this.offerLeafPileRecipe(PromenadeBlocks.SAP_MAPLE_LEAF_PILE, PromenadeBlocks.SAP_MAPLE_LEAVES);
        this.offerLeafPileRecipe(PromenadeBlocks.VERMILION_MAPLE_LEAF_PILE, PromenadeBlocks.VERMILION_MAPLE_LEAF_PILE);
        this.offerLeafPileRecipe(PromenadeBlocks.FULVOUS_MAPLE_LEAF_PILE, PromenadeBlocks.FULVOUS_MAPLE_LEAVES);
        this.offerLeafPileRecipe(PromenadeBlocks.MIKADO_MAPLE_LEAF_PILE, PromenadeBlocks.MIKADO_MAPLE_LEAVES);
        this.offerBoatRecipe(PromenadeItems.MAPLE_BOAT, PromenadeBlocks.MAPLE_PLANKS);
        this.offerChestBoatRecipe(PromenadeItems.MAPLE_CHEST_BOAT, PromenadeItems.MAPLE_BOAT);

        this.offerPlanksRecipe(PromenadeBlocks.PALM_PLANKS, PromenadeItemTags.PALM_LOGS, 4);
        this.offerBarkBlockRecipe(PromenadeBlocks.PALM_WOOD, PromenadeBlocks.PALM_LOG);
        this.offerBarkBlockRecipe(PromenadeBlocks.STRIPPED_PALM_WOOD, PromenadeBlocks.STRIPPED_PALM_LOG);
        this.offerHangingSignRecipe(PromenadeItems.PALM_HANGING_SIGN, PromenadeBlocks.STRIPPED_PALM_LOG);
        this.offerLeafPileRecipe(PromenadeBlocks.PALM_LEAF_PILE, PromenadeBlocks.PALM_LEAVES);
        this.offerBoatRecipe(PromenadeItems.PALM_BOAT, PromenadeBlocks.PALM_PLANKS);
        this.offerChestBoatRecipe(PromenadeItems.PALM_CHEST_BOAT, PromenadeItems.PALM_BOAT);

        this.offerPlanksRecipe(PromenadeBlocks.DARK_AMARANTH_PLANKS, PromenadeItemTags.DARK_AMARANTH_STEMS, 4);
        this.offerBarkBlockRecipe(PromenadeBlocks.DARK_AMARANTH_HYPHAE, PromenadeBlocks.DARK_AMARANTH_STEM);
        this.offerBarkBlockRecipe(PromenadeBlocks.STRIPPED_DARK_AMARANTH_HYPHAE, PromenadeBlocks.STRIPPED_DARK_AMARANTH_STEM);
        this.offerHangingSignRecipe(PromenadeItems.DARK_AMARANTH_HANGING_SIGN, PromenadeBlocks.STRIPPED_DARK_AMARANTH_STEM);

        this.offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, PromenadeBlocks.MOAI, Blocks.TUFF);

        this.generateCookingRecipes("smelting", RecipeSerializer.SMELTING, SmeltingRecipe::new, 200);
        this.generateCookingRecipes("smoking", RecipeSerializer.SMOKING, SmokingRecipe::new, 100);
        this.generateCookingRecipes("campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING, CampfireCookingRecipe::new, 600);

        this.offerShapelessRecipe(Items.MAGENTA_DYE, PromenadeItems.BLUEBERRIES, "magenta_dye", 1);
    }

    public void offerSnowyLeavesRecipe(ItemConvertible output, ItemConvertible input) {
        this.createShaped(RecipeCategory.DECORATIONS, output, 1)
                .input('_', Blocks.SNOW)
                .input('#', input)
                .pattern("_")
                .pattern("#")
                .group("snowy_leaves")
                .criterion(hasItem(input), this.conditionsFromItem(input))
                .offerTo(this.exporter);

    }


    public void offerLeafPileRecipe(ItemConvertible output, ItemConvertible input) {
        this.createShaped(RecipeCategory.DECORATIONS, output, 2)
                .input('#', input)
                .pattern("##")
                .group("leaf_pile")
                .criterion(hasItem(input), this.conditionsFromItem(input))
                .offerTo(this.exporter);
    }

    public void offerFlowerPileRecipe(ItemConvertible output, ItemConvertible input) {
        this.createShaped(RecipeCategory.DECORATIONS, output, 2)
                .input('#', input)
                .pattern("##")
                .group("flower_pile")
                .criterion(hasItem(input), this.conditionsFromItem(input))
                .offerTo(this.exporter);
    }

    public <T extends AbstractCookingRecipe> void generateCookingRecipes(
            String cooker, RecipeSerializer<T> serializer, AbstractCookingRecipe.RecipeFactory<T> recipeFactory, int cookingTime
    ) {
        this.offerFoodCookingRecipe(cooker, serializer, recipeFactory, cookingTime, PromenadeItems.DUCK, PromenadeItems.COOKED_DUCK, 0.35F);
    }
}
