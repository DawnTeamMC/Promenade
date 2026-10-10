package fr.hugman.promenade.particle;

import fr.hugman.promenade.Promenade;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;

public class PromenadeParticleTypes {
    public static final SimpleParticleType BLUSH_SAKURA_BLOSSOM = register("blush_sakura_blossom", simple());
    public static final SimpleParticleType COTTON_SAKURA_BLOSSOM = register("cotton_sakura_blossom", simple());

    public static final SimpleParticleType VERMILION_MAPLE_LEAF = register("vermilion_maple_leaf", simple());
    public static final SimpleParticleType FULVOUS_MAPLE_LEAF = register("fulvous_maple_leaf", simple());
    public static final SimpleParticleType MIKADO_MAPLE_LEAF = register("mikado_maple_leaf", simple());

    public static <B extends ParticleType<?>> B register(String path, B particleType) {
        return Registry.register(BuiltInRegistries.PARTICLE_TYPE, Promenade.id(path), particleType);
    }

    private static SimpleParticleType simple() {
        // the constructor is protected
        return new SimpleParticleType(false) {
        };
    }
}
