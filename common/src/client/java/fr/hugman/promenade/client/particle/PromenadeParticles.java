package fr.hugman.promenade.client.particle;

import fr.hugman.promenade.particle.PromenadeParticleTypes;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;

import java.util.function.Function;

public class PromenadeParticles {
    public static void register(Registrar registrar) {
        registrar.register(PromenadeParticleTypes.BLUSH_SAKURA_BLOSSOM, PromenadeLeavesParticle.BlossomProvider::new);
        registrar.register(PromenadeParticleTypes.COTTON_SAKURA_BLOSSOM, PromenadeLeavesParticle.BlossomProvider::new);

        registrar.register(PromenadeParticleTypes.MIKADO_MAPLE_LEAF, PromenadeLeavesParticle.MapleLeavesProvider::new);
        registrar.register(PromenadeParticleTypes.FULVOUS_MAPLE_LEAF, PromenadeLeavesParticle.MapleLeavesProvider::new);
        registrar.register(PromenadeParticleTypes.VERMILION_MAPLE_LEAF, PromenadeLeavesParticle.MapleLeavesProvider::new);
    }

    @FunctionalInterface
    public interface Registrar {
        void register(SimpleParticleType type, Function<SpriteSet, ParticleProvider<SimpleParticleType>> provider);
    }
}
