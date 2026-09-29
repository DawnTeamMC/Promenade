package fr.hugman.promenade.entity.variant;

import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.registry.PromenadeRegistryKeys;
import java.util.Optional;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.variant.PriorityProvider;
import net.minecraft.world.entity.variant.SpawnContext;

public class CapybaraVariants {
    public static final ResourceKey<CapybaraVariant> BROWN = of("brown");
    public static final ResourceKey<CapybaraVariant> ALBINO = of("albino");

    public static final ResourceKey<CapybaraVariant> DEFAULT = BROWN;

    public static ResourceKey<CapybaraVariant> of(String path) {
        return ResourceKey.create(PromenadeRegistryKeys.CAPYBARA_VARIANT, Promenade.id(path));
    }

    public static Optional<Holder.Reference<CapybaraVariant>> select(RandomSource random, RegistryAccess registries, SpawnContext context) {
        return PriorityProvider.pick(registries.lookupOrThrow(PromenadeRegistryKeys.CAPYBARA_VARIANT).listElements(), Holder::value, random, context);
    }
}
