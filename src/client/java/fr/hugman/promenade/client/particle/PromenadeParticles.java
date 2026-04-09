package fr.hugman.promenade.client.particle;

import fr.hugman.promenade.particle.PromenadeParticleTypes;
import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;
import net.minecraft.client.particle.FallingLeavesParticle;

public class PromenadeParticles {
    public static void register() {
        var instance = ParticleProviderRegistry.getInstance();

        instance.register(PromenadeParticleTypes.BLUSH_SAKURA_BLOSSOM, FallingLeavesParticle.CherryProvider::new);
        instance.register(PromenadeParticleTypes.COTTON_SAKURA_BLOSSOM, FallingLeavesParticle.CherryProvider::new);

        instance.register(PromenadeParticleTypes.MIKADO_MAPLE_LEAF, PromenadeLeavesParticle.MapleLeavesProvider::new);
        instance.register(PromenadeParticleTypes.FULVOUS_MAPLE_LEAF, PromenadeLeavesParticle.MapleLeavesProvider::new);
        instance.register(PromenadeParticleTypes.VERMILION_MAPLE_LEAF, PromenadeLeavesParticle.MapleLeavesProvider::new);
    }
}
