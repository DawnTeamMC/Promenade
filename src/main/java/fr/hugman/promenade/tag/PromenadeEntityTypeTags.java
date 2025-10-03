package fr.hugman.promenade.tag;

import fr.hugman.promenade.Promenade;
import net.fabricmc.fabric.api.tag.convention.v2.TagUtil;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class PromenadeEntityTypeTags {
    public static final TagKey<EntityType<?>> ANIMALS = ofConventional("animals");
    public static final TagKey<EntityType<?>> MONSTERS = ofConventional("monsters");
    public static final TagKey<EntityType<?>> BIRDS = ofConventional("birds");
    public static final TagKey<EntityType<?>> RODENTS = ofConventional("rodents");
    public static final TagKey<EntityType<?>> CREEPERS = ofConventional("creepers");

    private static TagKey<EntityType<?>> of(String path) {
        return TagKey.of(RegistryKeys.ENTITY_TYPE, Promenade.id(path));
    }

    private static TagKey<EntityType<?>> ofConventional(String path) {
		return TagKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(TagUtil.C_TAG_NAMESPACE, path));
    }
}
