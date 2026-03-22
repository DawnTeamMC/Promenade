package fr.hugman.promenade.entity.data;

import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.entity.CapybaraEntity;
import fr.hugman.promenade.entity.variant.CapybaraVariant;
import fr.hugman.promenade.entity.variant.DuckVariant;
import fr.hugman.promenade.entity.variant.SunkenVariant;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricTrackedDataRegistry;
import net.minecraft.core.Holder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.syncher.EntityDataSerializer;

public class PromenadeTrackedData {
    public static final EntityDataSerializer<Holder<DuckVariant>> DUCK_VARIANT = of("duck_variant", DuckVariant.ENTRY_PACKET_CODEC);
    public static final EntityDataSerializer<Holder<CapybaraVariant>> CAPYBARA_VARIANT = of("capybara_variant", CapybaraVariant.ENTRY_PACKET_CODEC);
    public static final EntityDataSerializer<Holder<SunkenVariant>> SUNKEN_VARIANT = of("sunken_variant", SunkenVariant.ENTRY_PACKET_CODEC);

    public static final EntityDataSerializer<CapybaraEntity.State> CAPYBARA_STATE = of("capybara_state", CapybaraEntity.State.PACKET_CODEC);

    public static <T> EntityDataSerializer<T> of(String name, StreamCodec<? super RegistryFriendlyByteBuf, T> codec) {
        var handler = EntityDataSerializer.forValueType(codec);
        FabricTrackedDataRegistry.register(Promenade.id(name), handler);
        return handler;
    }
}
