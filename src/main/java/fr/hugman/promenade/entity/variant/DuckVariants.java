package fr.hugman.promenade.entity.variant;

import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.registry.PromenadeRegistryKeys;
import java.util.Optional;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.variant.PriorityProvider;
import net.minecraft.world.entity.variant.SpawnContext;

public class DuckVariants {
    public static final ResourceKey<DuckVariant> PEKIN = of("pekin");
    public static final ResourceKey<DuckVariant> MALLARD = of("mallard");

    public static final ResourceKey<DuckVariant> DEFAULT = PEKIN;

    private static ResourceKey<DuckVariant> of(String path) {
        return of(Promenade.id(path));
    }

    public static ResourceKey<DuckVariant> of(Identifier id) {
        return ResourceKey.create(PromenadeRegistryKeys.DUCK_VARIANT, id);
    }

    public static Optional<Holder.Reference<DuckVariant>> select(RandomSource random, RegistryAccess registries, SpawnContext context) {
        return PriorityProvider.pick(registries.lookupOrThrow(PromenadeRegistryKeys.DUCK_VARIANT).listElements(), Holder::value, random, context);
    }
}
