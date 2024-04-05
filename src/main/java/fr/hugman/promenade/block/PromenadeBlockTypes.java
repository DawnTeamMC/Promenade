package fr.hugman.promenade.block;

import com.mojang.serialization.MapCodec;
import fr.hugman.promenade.Promenade;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class PromenadeBlockTypes {
    public static final MapCodec<BerryBushBlock> BERRY_BUSH = of("berry_bush", BerryBushBlock.CODEC);
    public static final MapCodec<DecoratedLeavesBlock> DECORATED_LEAVES = of("decorated_leaves", DecoratedLeavesBlock.CODEC);
    public static final MapCodec<MoaiBlock> MOAI = of("moai", MoaiBlock.CODEC);

    private static <B extends Block> MapCodec<B> of(String path, MapCodec<B> blockType) {
        return Registry.register(Registries.BLOCK_TYPE, Promenade.id(path), blockType);
    }
}
