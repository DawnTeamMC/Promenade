package fr.hugman.promenade.data.provider;

import fr.hugman.promenade.entity.variant.PromenadePaintingVariants;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.decoration.painting.PaintingVariant;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class PromenadePaintingVariantProvider extends FabricDynamicRegistryProvider {
    public PromenadePaintingVariantProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        final var wrapper = registries.lookupOrThrow(Registries.PAINTING_VARIANT);
        entries.add(wrapper, PromenadePaintingVariants.OPTIMISM);
        entries.add(wrapper, PromenadePaintingVariants.NURTURE);
    }

    @Override
    public String getName() {
        return "Painting Variants";
    }

    public static void register(BootstrapContext<PaintingVariant> registerable) {
        of(registerable, PromenadePaintingVariants.OPTIMISM, 2, 2, "hugman");
        of(registerable, PromenadePaintingVariants.NURTURE, 2, 2, "hugman");
    }

    private static void of(BootstrapContext<PaintingVariant> registry, ResourceKey<PaintingVariant> key, int width, int height, String authorKey) {
        registry.register(key, new PaintingVariant(
                width,
                height,
                key.identifier(),
                Optional.of(Component.translatable(key.identifier().toLanguageKey("painting", "title")).withStyle(ChatFormatting.YELLOW)),
                Optional.of(Component.translatable("name." + authorKey).withStyle(ChatFormatting.GRAY)))
        );
    }
}