package fr.hugman.promenade.client.particle;

import fr.hugman.promenade.particle.PromenadeParticleTypes;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.particle.LeavesParticle;

@Environment(EnvType.CLIENT)
public class PromenadeParticleFactories {
    public static void register() {
        var instance = ParticleFactoryRegistry.getInstance();

        instance.register(PromenadeParticleTypes.BLUSH_SAKURA_BLOSSOM, LeavesParticle.CherryLeavesFactory::new);
        instance.register(PromenadeParticleTypes.COTTON_SAKURA_BLOSSOM, LeavesParticle.CherryLeavesFactory::new);

        instance.register(PromenadeParticleTypes.MIKADO_MAPLE_LEAF, PromenadeLeavesParticleFactories.MapleLeavesFactory::new);
        instance.register(PromenadeParticleTypes.FULVOUS_MAPLE_LEAF, PromenadeLeavesParticleFactories.MapleLeavesFactory::new);
        instance.register(PromenadeParticleTypes.VERMILION_MAPLE_LEAF, PromenadeLeavesParticleFactories.MapleLeavesFactory::new);
    }
}
