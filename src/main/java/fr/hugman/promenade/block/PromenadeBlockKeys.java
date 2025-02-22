package fr.hugman.promenade.block;

import fr.hugman.promenade.Promenade;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class PromenadeBlockKeys {
    public static final RegistryKey<Block> STAR_BITS = of("star_bits");

    private static RegistryKey<Block> of(String path) {
        return RegistryKey.of(RegistryKeys.BLOCK, Promenade.id(path));
    }
}
