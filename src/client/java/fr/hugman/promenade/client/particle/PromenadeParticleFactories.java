package fr.hugman.promenade.client.particle;

import fr.hugman.promenade.particle.PromenadeParticleTypes;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;

public class PromenadeParticleFactories {
    public static void register() {
        var instance = ParticleFactoryRegistry.getInstance();

        instance.register(PromenadeParticleTypes.BLUSH_SAKURA_BLOSSOM, FallingLeafParticle.BlossomFactory::new);
        instance.register(PromenadeParticleTypes.COTTON_SAKURA_BLOSSOM, FallingLeafParticle.BlossomFactory::new);

        instance.register(PromenadeParticleTypes.MIKADO_MAPLE_LEAF, FallingLeafParticle.MapleLeafFactory::new);
        instance.register(PromenadeParticleTypes.FULVOUS_MAPLE_LEAF, FallingLeafParticle.MapleLeafFactory::new);
        instance.register(PromenadeParticleTypes.VERMILION_MAPLE_LEAF, FallingLeafParticle.MapleLeafFactory::new);
    }
}
