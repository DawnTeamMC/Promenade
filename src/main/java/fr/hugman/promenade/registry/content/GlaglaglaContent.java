package fr.hugman.promenade.registry.content;

import fr.hugman.dawn.DawnFactory;
import fr.hugman.dawn.Registrar;
import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.gen.feature.FreezeTopLayerFeature;
import fr.hugman.promenade.item.ItemGroupHelper;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.world.LightType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

public class GlaglaglaContent {
	public static final Block SNOWY_SPRUCE_LEAVES = DawnFactory.leaves();

	public static final FreezeTopLayerFeature FREEZE_TOP_LAYER = new FreezeTopLayerFeature(DefaultFeatureConfig.CODEC);

	public static final RegistryKey<Biome> GLACARIAN_TAIGA = DawnFactory.biome(Promenade.id("glacarian_taiga"));
	public static final TagKey<Biome> CAN_FREEZE_DURING_SNOWFALL = DawnFactory.biomeTag(Promenade.id("can_freeze_during_snowfall"));

	public static boolean canFreezeFromBiomeAndWeather(LivingEntity entity) {
		RegistryEntry<Biome> biome = entity.world.getBiome(entity.getBlockPos());
		if(entity.getType().isIn(EntityTypeTags.FREEZE_IMMUNE_ENTITY_TYPES)) {
			// is immune
			return false;
		}
		if(!biome.isIn(CAN_FREEZE_DURING_SNOWFALL) || entity.isSpectator()) {
			// is not the correct biome
			// is spectator
			return false;
		}
		if(!entity.world.isRaining()) {
			// is not snowing
			return false;
		}
		boolean exposedToSky = entity.world.getLightLevel(LightType.SKY, entity.getBlockPos()) >= 5;
		boolean lightSourceNear = entity.world.getLightLevel(LightType.BLOCK, entity.getBlockPos()) >= 5;
		if(lightSourceNear || !exposedToSky) {
			// is near a light source
			// is not exposed much to sky
			return false;
		}
		if(entity.getEquippedStack(EquipmentSlot.HEAD).isIn(ItemTags.FREEZE_IMMUNE_WEARABLES) ||
				entity.getEquippedStack(EquipmentSlot.CHEST).isIn(ItemTags.FREEZE_IMMUNE_WEARABLES) ||
				entity.getEquippedStack(EquipmentSlot.LEGS).isIn(ItemTags.FREEZE_IMMUNE_WEARABLES) ||
				entity.getEquippedStack(EquipmentSlot.FEET).isIn(ItemTags.FREEZE_IMMUNE_WEARABLES)) {
			// wear any leather piece
			return false;
		}
		return true;
	}

	public static void register(Registrar r) {
		r.add(("snowy_spruce_leaves"), SNOWY_SPRUCE_LEAVES);

		r.add(("freeze_top_layer"), FREEZE_TOP_LAYER);

		ItemGroupHelper.append(ItemGroups.NATURAL, e -> {
			e.addAfter(Blocks.SPRUCE_LEAVES, SNOWY_SPRUCE_LEAVES);
		});
	}
}
