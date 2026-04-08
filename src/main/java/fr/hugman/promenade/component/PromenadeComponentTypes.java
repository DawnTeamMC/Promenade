package fr.hugman.promenade.component;

import com.mojang.serialization.Codec;
import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.entity.variant.CapybaraVariant;
import fr.hugman.promenade.entity.variant.DuckVariant;
import fr.hugman.promenade.entity.variant.SunkenVariant;
import java.util.function.UnaryOperator;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;

public class PromenadeComponentTypes {
    public static final DataComponentType<Holder<DuckVariant>> DUCK_VARIANT = of("duck/variant", DuckVariant.ENTRY_CODEC, DuckVariant.ENTRY_PACKET_CODEC);
    public static final DataComponentType<Holder<CapybaraVariant>> CAPYBARA_VARIANT = of("capybara/variant", CapybaraVariant.ENTRY_CODEC, CapybaraVariant.ENTRY_PACKET_CODEC);
    public static final DataComponentType<Holder<SunkenVariant>> SUNKEN_VARIANT = of("sunken/variant", SunkenVariant.ENTRY_CODEC, SunkenVariant.ENTRY_PACKET_CODEC);

    private static <T> DataComponentType<T> of(String id, Codec<T> codec, StreamCodec<? super RegistryFriendlyByteBuf, T> packetCodec) {
        return of(id, builder -> builder.persistent(codec).networkSynchronized(packetCodec));
    }

    private static <T> DataComponentType<T> of(String id, UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
        return Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, Promenade.id(id), ((DataComponentType.Builder)builderOperator.apply(DataComponentType.builder())).build());
    }
}
