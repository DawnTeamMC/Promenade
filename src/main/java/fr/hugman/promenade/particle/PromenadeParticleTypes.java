package fr.hugman.promenade.particle;

import fr.hugman.promenade.Promenade;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class PromenadeParticleTypes {
    public static final DefaultParticleType BLUSH_SAKURA_BLOSSOM = register("blush_sakura_blossom", FabricParticleTypes.simple());
    public static final DefaultParticleType COTTON_SAKURA_BLOSSOM = register("cotton_sakura_blossom", FabricParticleTypes.simple());

    public static final DefaultParticleType VERMILION_MAPLE_LEAF = register("vermilion_maple_leaf", FabricParticleTypes.simple());
    public static final DefaultParticleType FULVOUS_MAPLE_LEAF = register("fulvous_maple_leaf", FabricParticleTypes.simple());
    public static final DefaultParticleType MIKADO_MAPLE_LEAF = register("mikado_maple_leaf", FabricParticleTypes.simple());

    public static <B extends ParticleType<?>> B register(String path, B particleType) {
        return Registry.register(Registries.PARTICLE_TYPE, Promenade.id(path), particleType);
    }
}
