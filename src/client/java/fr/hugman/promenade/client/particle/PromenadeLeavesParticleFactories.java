package fr.hugman.promenade.client.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.FallingLeavesParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import org.jetbrains.annotations.Nullable;

public class PromenadeLeavesParticleFactories {
    @Environment(EnvType.CLIENT)
    public static class MapleLeavesFactory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteProvider;

        public MapleLeavesFactory(SpriteSet spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Override
        public @Nullable Particle createParticle(SimpleParticleType parameters, ClientLevel world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, RandomSource random) {
            return new FallingLeavesParticle(world, x, y, z, this.spriteProvider.get(random), 0.07F, 5.0F, true, false, 1.5F, 0.021F);
        }
    }
}
