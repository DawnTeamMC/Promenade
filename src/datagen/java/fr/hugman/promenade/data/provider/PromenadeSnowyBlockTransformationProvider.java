package fr.hugman.promenade.data.provider;

import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.block.PromenadeBlocks;
import fr.hugman.promenade.block.snowy.SnowyBlockTransformation;
import fr.hugman.promenade.registry.PromenadeRegistryKeys;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

//TODO: a generic class for other devs
public class PromenadeSnowyBlockTransformationProvider extends FabricDynamicRegistryProvider {
    public PromenadeSnowyBlockTransformationProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        entries.addAll(registries.getOrThrow(PromenadeRegistryKeys.SNOWY_BLOCK_TRANSFORMATION));
    }

    @Override
    public String getName() {
        return "Snowy Block Transformations";
    }

    public static void register(Registerable<SnowyBlockTransformation> registerable) {
        of(registerable, Blocks.OAK_LEAVES, PromenadeBlocks.SNOWY_OAK_LEAVES);
        of(registerable, Blocks.SPRUCE_LEAVES, PromenadeBlocks.SNOWY_SPRUCE_LEAVES);
        of(registerable, Blocks.BIRCH_LEAVES, PromenadeBlocks.SNOWY_BIRCH_LEAVES);
        of(registerable, Blocks.JUNGLE_LEAVES, PromenadeBlocks.SNOWY_JUNGLE_LEAVES);
        of(registerable, Blocks.ACACIA_LEAVES, PromenadeBlocks.SNOWY_ACACIA_LEAVES);
        of(registerable, Blocks.CHERRY_LEAVES, PromenadeBlocks.SNOWY_CHERRY_LEAVES);
        of(registerable, Blocks.DARK_OAK_LEAVES, PromenadeBlocks.SNOWY_DARK_OAK_LEAVES);
        of(registerable, Blocks.PALE_OAK_LEAVES, PromenadeBlocks.SNOWY_PALE_OAK_LEAVES);
        of(registerable, Blocks.MANGROVE_LEAVES, PromenadeBlocks.SNOWY_MANGROVE_LEAVES);
        of(registerable, Blocks.AZALEA_LEAVES, PromenadeBlocks.SNOWY_AZALEA_LEAVES);
        of(registerable, Blocks.FLOWERING_AZALEA_LEAVES, PromenadeBlocks.SNOWY_FLOWERING_AZALEA_LEAVES);
        of(registerable, PromenadeBlocks.BLUSH_SAKURA_BLOSSOMS, PromenadeBlocks.SNOWY_BLUSH_SAKURA_BLOSSOMS);
        of(registerable, PromenadeBlocks.COTTON_SAKURA_BLOSSOMS, PromenadeBlocks.SNOWY_COTTON_SAKURA_BLOSSOMS);
        of(registerable, PromenadeBlocks.SAP_MAPLE_LEAVES, PromenadeBlocks.SNOWY_SAP_MAPLE_LEAVES);
        of(registerable, PromenadeBlocks.VERMILION_MAPLE_LEAVES, PromenadeBlocks.SNOWY_VERMILION_MAPLE_LEAVES);
        of(registerable, PromenadeBlocks.FULVOUS_MAPLE_LEAVES, PromenadeBlocks.SNOWY_FULVOUS_MAPLE_LEAVES);
        of(registerable, PromenadeBlocks.MIKADO_MAPLE_LEAVES, PromenadeBlocks.SNOWY_MIKADO_MAPLE_LEAVES);
        of(registerable, PromenadeBlocks.PALM_LEAVES, PromenadeBlocks.SNOWY_PALM_LEAVES);
    }

    private static void of(Registerable<SnowyBlockTransformation> registry, Block baseBlock, Block snowyBlock) {
        var id = RegistryKey.of(PromenadeRegistryKeys.SNOWY_BLOCK_TRANSFORMATION, Promenade.id(Registries.BLOCK.getId(snowyBlock).getPath()));
        registry.register(id, new SnowyBlockTransformation(baseBlock.getRegistryEntry(), snowyBlock.getRegistryEntry()));
    }
}