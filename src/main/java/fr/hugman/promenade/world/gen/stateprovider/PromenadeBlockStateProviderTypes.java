package fr.hugman.promenade.world.gen.stateprovider;

import com.mojang.serialization.MapCodec;
import fr.hugman.promenade.Promenade;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class PromenadeBlockStateProviderTypes {
    public static final MapCodec<MapleSyrupStateProvider> MAPLE_SYRUP = register("maple_syrup", MapleSyrupStateProvider.CODEC);

    private static <P extends BlockStateProvider> MapCodec<P> register(String path, MapCodec<P> codec) {
        return Registry.register(BuiltInRegistries.BLOCK_STATE_PROVIDER_TYPE, Promenade.id(path), codec);
    }
}
