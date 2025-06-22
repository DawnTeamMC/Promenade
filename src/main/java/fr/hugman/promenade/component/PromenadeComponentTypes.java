package fr.hugman.promenade.component;

import com.mojang.serialization.Codec;
import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.entity.variant.CapybaraVariant;
import fr.hugman.promenade.entity.variant.DuckVariant;
import fr.hugman.promenade.entity.variant.SunkenVariant;
import net.minecraft.component.ComponentType;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;

import java.util.function.UnaryOperator;

public class PromenadeComponentTypes {
    public static final ComponentType<RegistryEntry<DuckVariant>> DUCK_VARIANT = of("duck/variant", DuckVariant.ENTRY_CODEC, DuckVariant.ENTRY_PACKET_CODEC);
    public static final ComponentType<RegistryEntry<CapybaraVariant>> CAPYBARA_VARIANT = of("capybara/variant", CapybaraVariant.ENTRY_CODEC, CapybaraVariant.ENTRY_PACKET_CODEC);
    public static final ComponentType<RegistryEntry<SunkenVariant>> SUNKEN_VARIANT = of("sunken/variant", SunkenVariant.ENTRY_CODEC, SunkenVariant.ENTRY_PACKET_CODEC);

    private static <T> ComponentType<T> of(String id, Codec<T> codec, PacketCodec<? super RegistryByteBuf, T> packetCodec) {
        return of(id, builder -> builder.codec(codec).packetCodec(packetCodec));
    }

    private static <T> ComponentType<T> of(String id, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Promenade.id(id), ((ComponentType.Builder)builderOperator.apply(ComponentType.builder())).build());
    }
}
