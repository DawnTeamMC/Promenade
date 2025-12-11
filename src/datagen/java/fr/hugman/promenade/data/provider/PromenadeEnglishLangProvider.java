package fr.hugman.promenade.data.provider;

import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.item.PromenadeItems;
import fr.hugman.promenade.tag.PromenadeBiomeTags;
import fr.hugman.promenade.tag.PromenadeBlockTags;
import fr.hugman.promenade.tag.PromenadeEntityTypeTags;
import fr.hugman.promenade.tag.PromenadeItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Util;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class PromenadeEnglishLangProvider extends FabricLanguageProvider {
	private static final Set<String> DO_NOT_TITLE_CASE = Set.of(
			"of", "the", "and", "a", "an", "in", "on", "for", "to", "at", "by", "from", "with"
	);

	public PromenadeEnglishLangProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
		super(dataOutput, "en_us", registryLookup);
	}

	@Override
	public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder builder) {
		builder.add(PromenadeItems.DUCK, "Raw Duck");

		this.generateAutomaticTranslations(wrapperLookup, builder);

		// Advancements

		builder.add("advancements.promenade.adventure.kill_sunken_outside_water.description", "Kill a sunken that's outside of water using a crossbow while being submerged yourself");
		builder.add("advancements.promenade.adventure.kill_sunken_outside_water.title", "Supernautica: Above Sixty-Two");
		builder.add("advancements.promenade.husbandry.harvest_maple_syrup.description", "Strip a natural Maple tree using an Axe to collect Maple Syrup with a Bottle");
		builder.add("advancements.promenade.husbandry.harvest_maple_syrup.title", "Don't Worry, Be Sappy!");

		// Subtitles

		builder.add("subtitles.promenade.entity.capybara.ambient", "Capybara squeaks");
		builder.add("subtitles.promenade.entity.capybara.death", "Capybara dies");
		builder.add("subtitles.promenade.entity.capybara.fart", "Capybara farts");
		builder.add("subtitles.promenade.entity.capybara.hurt", "Capybara hurts");
		builder.add("subtitles.promenade.entity.duck.ambient", "Duck quacks");
		builder.add("subtitles.promenade.entity.duck.death", "Duck dies");
		builder.add("subtitles.promenade.entity.duck.hurt", "Duck hurts");
		builder.add("subtitles.promenade.entity.sunken.ambient", "Sunken rattles");
		builder.add("subtitles.promenade.entity.sunken.death", "Sunken dies");
		builder.add("subtitles.promenade.entity.sunken.hurt", "Sunken hurts");
		builder.add("subtitles.promenade.entity.sunken.shoot", "Sunken shoots");

		// Tags

		builder.add(PromenadeItemTags.APRICOTS_FOODS, "Apricots");
		builder.add(PromenadeItemTags.BANANA_FOODS, "Bananas");
		builder.add(PromenadeItemTags.MANGOES_FOODS, "Mangoes");
		builder.add(PromenadeItemTags.BLUEBERRIES_FOODS, "Blueberries");
		builder.add(PromenadeItemTags.MAPLE_SYRUP_DRINKS, "Maple Syrups");
		builder.add(PromenadeItemTags.CAPYBARA_FOOD, "Food for Capybaras");
		builder.add(PromenadeItemTags.DUCK_FOOD, "Food for Ducks");
		builder.add(PromenadeBiomeTags.PRIMARY_WOOD_TYPE_SAKURA, "Sakura Primary Wood Type");
		builder.add(PromenadeBiomeTags.PRIMARY_WOOD_TYPE_PALM, "Palm Primary Wood Type");
		builder.add(PromenadeBiomeTags.PRIMARY_WOOD_TYPE_MAPLE, "Maple Primary Wood Type");
		builder.add(PromenadeBiomeTags.PRIMARY_WOOD_TYPE_DARK_AMARANTH, "Dark Amaranth Primary Wood Type");
		this.generateAutomaticTagTranslations(builder,
				PromenadeBlockTags.SAKURA_LOGS,
				PromenadeBlockTags.MAPLE_LOGS,
				PromenadeBlockTags.PALM_LOGS,
				PromenadeBlockTags.DARK_AMARANTH_STEMS,
				PromenadeBlockTags.SNOWY_LEAVES,
				PromenadeBlockTags.FALLEN_LEAVES,
				PromenadeBlockTags.LEAF_PILES,
				PromenadeBlockTags.FLOWER_PILES,
				PromenadeBlockTags.DARK_AMARANTH_FUNGUS_PLACEABLE_ON,
				PromenadeBlockTags.DARK_AMARANTH_FUNGUS_GROWABLE_ON,
				PromenadeBlockTags.DARK_AMARANTH_ROOTS_PLACEABLE_ON,
				PromenadeBlockTags.IGNEOUS_ROCKS,
				PromenadeItemTags.SAKURA_LOGS,
				PromenadeItemTags.MAPLE_LOGS,
				PromenadeItemTags.PALM_LOGS,
				PromenadeItemTags.DARK_AMARANTH_STEMS,
				PromenadeItemTags.SNOWY_LEAVES,
				PromenadeItemTags.IGNEOUS_ROCKS,
				PromenadeEntityTypeTags.ANIMALS,
				PromenadeEntityTypeTags.MONSTERS,
				PromenadeEntityTypeTags.BIRDS,
				PromenadeEntityTypeTags.RODENTS,
				PromenadeEntityTypeTags.CREEPERS,
				PromenadeBiomeTags.SAKURA_GROVES,
				PromenadeBiomeTags.HAS_PALMS,
				PromenadeBiomeTags.HAS_DARK_FOREST_WITCH_HUTS,
				PromenadeBiomeTags.CAN_FREEZE_DURING_SNOWFALL,
				PromenadeBiomeTags.SPAWNS_CAPYBARAS,
				PromenadeBiomeTags.SPAWNS_SUNKEN,
				PromenadeBiomeTags.SPAWNS_MALLARD_DUCKS,
				PromenadeBiomeTags.SPAWNS_PEKIN_DUCKS
		);

		// Other

		builder.add("name.hugman", "Hugman");
		builder.add("death.fell.accident.coiled_vines", "%1$s fell off some coiled vines");
		builder.add("gamerule.doBlocksGetSnowy", "Blocks can get snowy");
		builder.add("gamerule.doBlocksGetSnowy.description", "Some blocks may turn into snowy variants when they are covered by a snow layer");

		// Mod compatibility

		builder.add("modmenu.descriptionTranslation.promenade", "Fancy and simplistic animals, biomes, structures and more!");
	}

	private void generateAutomaticTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder builder) {
		for (var block : getRegistryEntries(wrapperLookup, RegistryKeys.BLOCK)) {
			builder.add(block.value(), snakeToTitleCase(block.registryKey().getValue().getPath()));
		}

		for (var item : getRegistryEntries(wrapperLookup, RegistryKeys.ITEM)) {
			if (item.value().getTranslationKey().startsWith("block.")) {
				continue;
			}
			var path = item.registryKey().getValue().getPath();
			if (path.endsWith("_chest_boat")) {
				path = path.replace("_chest_boat", "_boat_with_chest");
			}
			try {
				builder.add(item.value(), snakeToTitleCase(path));
			} catch (RuntimeException ignored) {}
		}

		for (var entity : getRegistryEntries(wrapperLookup, RegistryKeys.ENTITY_TYPE)) {
			var path = entity.registryKey().getValue().getPath();
			if (path.endsWith("_chest_boat")) {
				path = path.replace("_chest_boat", "_boat_with_chest");
			}
			builder.add(entity.value(), snakeToTitleCase(path));
		}

		for (var biome : getRegistryEntries(wrapperLookup, RegistryKeys.BIOME)) {
			var id = biome.registryKey().getValue();
			builder.add(Util.createTranslationKey("biome", id), snakeToTitleCase(id.getPath()));
		}

		for (var bannerPattern : getRegistryEntries(wrapperLookup, RegistryKeys.BANNER_PATTERN)) {
			var id = bannerPattern.registryKey().getValue();
			builder.add(
					Util.createTranslationKey("item", id.withPath(s -> s + "_banner_pattern.desc")),
					snakeToTitleCase(id.getPath())
			);
			for (DyeColor color : DyeColor.values()) {
				builder.add(
						Util.createTranslationKey("block", id.withPath(s -> "banner." + s + "." + color.getId())),
						snakeToTitleCase(color.getId() + "_" + id.getPath())
				);
			}
		}

		for (var paintingVariant : getRegistryEntries(wrapperLookup, RegistryKeys.PAINTING_VARIANT)) {
			var id = paintingVariant.registryKey().getValue();
			builder.add(Util.createTranslationKey("painting", id) + ".title", snakeToTitleCase(id.getPath()));
		}

		for (var itemGroup : getRegistryEntries(wrapperLookup, RegistryKeys.ITEM_GROUP)) {
			var id = itemGroup.registryKey().getValue();
			builder.add(Util.createTranslationKey("item_group", id), snakeToTitleCase(id.getPath()));
		}
	}


	private void generateAutomaticTagTranslations(TranslationBuilder builder, TagKey<?>... tagKeys) {
		for (var tagKey : tagKeys) {
			var id = tagKey.id();
			builder.add(tagKey, snakeToTitleCase(id.getPath()));
		}
	}

	private static <O> List<RegistryEntry.Reference<O>> getRegistryEntries(RegistryWrapper.WrapperLookup wrapperLookup, RegistryKey<? extends Registry<O>> registryKey) {
		return wrapperLookup.getOrThrow(registryKey).streamEntries()
				.filter(entry -> entry.registryKey().getValue().getNamespace().equals(Promenade.MOD_ID))
				.toList();
	}

	private static String snakeToTitleCase(String str) {
		String[] words = str.split("_");
		StringBuilder titleCase = new StringBuilder();
		for (int i = 0; i < words.length; i++) {
			String word = words[i];
			if (word.isEmpty()) {
				continue;
			}
			if (i != 0 && DO_NOT_TITLE_CASE.contains(word.toLowerCase())) {
				titleCase.append(word.toLowerCase());
			} else {
				titleCase.append(Character.toUpperCase(word.charAt(0)))
						.append(word.substring(1).toLowerCase());
			}
			if (i < words.length - 1) {
				titleCase.append(" ");
			}
		}
		return titleCase.toString();
	}
}