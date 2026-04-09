package fr.hugman.promenade.tag;

import fr.hugman.promenade.Promenade;
import net.fabricmc.fabric.api.tag.convention.v2.TagUtil;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

public class PromenadeEntityTypeTags {
    public static final TagKey<EntityType<?>> ANIMALS = ofConventional("animals");
    public static final TagKey<EntityType<?>> MONSTERS = ofConventional("monsters");
    public static final TagKey<EntityType<?>> BIRDS = ofConventional("birds");
    public static final TagKey<EntityType<?>> RODENTS = ofConventional("rodents");
    public static final TagKey<EntityType<?>> CREEPERS = ofConventional("creepers");

    private static TagKey<EntityType<?>> of(String path) {
        return TagKey.create(Registries.ENTITY_TYPE, Promenade.id(path));
    }

    private static TagKey<EntityType<?>> ofConventional(String path) {
		return TagKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(TagUtil.C_TAG_NAMESPACE, path));
    }
}
