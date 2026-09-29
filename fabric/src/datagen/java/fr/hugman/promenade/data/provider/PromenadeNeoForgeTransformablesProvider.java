package fr.hugman.promenade.data.provider;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.hugman.promenade.registry.PromenadeStrippables;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricCodecDataProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.BlockTransformer;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.component.BlockTransformers;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.stateproviders.RuleBasedStateProvider;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

/**
 * Generates NeoForge's {@code neoforge:transformables} block data map, through which NeoForge adds tool transformations
 * (Fabric registers them at runtime instead). The file is ignored on Fabric.
 */
public class PromenadeNeoForgeTransformablesProvider extends FabricCodecDataProvider<Map<ResourceKey<Block>, PromenadeNeoForgeTransformablesProvider.Transform>> {
	private static final Codec<Map<ResourceKey<Block>, Transform>> CODEC = Codec.unboundedMap(ResourceKey.codec(Registries.BLOCK), Transform.CODEC).fieldOf("values").codec();

	public PromenadeNeoForgeTransformablesProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(packOutput, registriesFuture, PackOutput.Target.DATA_PACK, "data_maps/block", CODEC);
	}

	@Override
	public void configure(BiConsumer<Identifier, Map<ResourceKey<Block>, Transform>> provider, HolderLookup.Provider lookup) {
		Map<ResourceKey<Block>, Transform> values = new LinkedHashMap<>();
		// mirrors NeoForge's Transformable#stripping
		PromenadeStrippables.register((block, stripped) -> {
			var ruleProvider = RuleBasedStateProvider.builder()
					.ifTrueThenProvide(BlockPredicate.matchesBlocks(block), stripped)
					.build();
			var data = BlockTransformer.BlockTransformData.builder(ruleProvider).sound(SoundEvents.AXE_STRIP).build();
			values.put(block.builtInRegistryHolder().key(), new Transform(BlockTransformers.AXE, data));
		});
		provider.accept(Identifier.fromNamespaceAndPath("neoforge", "transformables"), values);
	}

	@Override
	public String getName() {
		return "NeoForge Transformables";
	}

	public record Transform(ResourceKey<BlockTransformer> transformer, BlockTransformer.BlockTransformData transformData) {
		public static final Codec<Transform> CODEC = RecordCodecBuilder.create(instance -> instance.group(
				ResourceKey.codec(Registries.BLOCK_TRANSFORMER).fieldOf("transformer").forGetter(Transform::transformer),
				BlockTransformer.BlockTransformData.CODEC.fieldOf("transform_data").forGetter(Transform::transformData)
		).apply(instance, Transform::new));
	}
}
