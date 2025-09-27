/*
 * Copyright (c) 2020, 2021, 2022, 2023, 2024, 2025 Hugman
 *
 * This software is licensed under the PolyForm Shield License 1.0.0.
 * You may obtain a copy of the License at
 *
 *      https://polyformproject.org/licenses/shield/1.0.0
 *
 * You may use this software only for non-commercial purposes.
 * For commercial use, you must obtain a separate commercial license.
 */
package fr.hugman.promenade.data.provider;

import fr.hugman.promenade.entity.PromenadeEntityTypes;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.EntityTypeTags;

import java.util.concurrent.CompletableFuture;

public class PromenadeEntityTypeTagProvider extends FabricTagProvider.EntityTypeTagProvider {
	public PromenadeEntityTypeTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
		super(output, completableFuture);
	}

	@Override
	protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
		// Vanilla
		valueLookupBuilder(EntityTypeTags.SKELETONS).add(PromenadeEntityTypes.SUNKEN);
		valueLookupBuilder(EntityTypeTags.AXOLOTL_ALWAYS_HOSTILES).add(PromenadeEntityTypes.SUNKEN);
		valueLookupBuilder(EntityTypeTags.FREEZE_IMMUNE_ENTITY_TYPES).add(PromenadeEntityTypes.DUCK);
		valueLookupBuilder(EntityTypeTags.FALL_DAMAGE_IMMUNE).add(PromenadeEntityTypes.DUCK);

		valueLookupBuilder(EntityTypeTags.BOAT).add(PromenadeEntityTypes.SAKURA_BOAT, PromenadeEntityTypes.MAPLE_BOAT, PromenadeEntityTypes.PALM_BOAT);
	}
}