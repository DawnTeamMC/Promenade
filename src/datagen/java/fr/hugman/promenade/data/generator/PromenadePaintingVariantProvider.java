package fr.hugman.promenade.data.generator;

import fr.hugman.promenade.entity.variant.PromenadePaintingVariants;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.entity.decoration.painting.PaintingVariant;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class PromenadePaintingVariantProvider extends FabricDynamicRegistryProvider {
    public PromenadePaintingVariantProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        var wrapper = registries.getOrThrow(RegistryKeys.PAINTING_VARIANT);
        entries.add(wrapper, PromenadePaintingVariants.OPTIMISM);
        entries.add(wrapper, PromenadePaintingVariants.NURTURE);
    }

    @Override
    public String getName() {
        return "Painting Variants";
    }

    public static void register(Registerable<PaintingVariant> registerable) {
        of(registerable, PromenadePaintingVariants.OPTIMISM, 2, 2, "hugman");
        of(registerable, PromenadePaintingVariants.NURTURE, 2, 2, "hugman");
    }

    private static void of(Registerable<PaintingVariant> registry, RegistryKey<PaintingVariant> key, int width, int height, String authorKey) {
        registry.register(key, new PaintingVariant(
                width,
                height,
                key.getValue(),
                Optional.of(Text.translatable(key.getValue().toTranslationKey("painting", "title")).formatted(Formatting.YELLOW)),
                Optional.of(Text.translatable("name." + authorKey).formatted(Formatting.GRAY)))
        );
    }
}