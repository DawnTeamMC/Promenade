package fr.hugman.promenade.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.FallingLeavesParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import org.jetbrains.annotations.Nullable;

public class PromenadeLeavesParticle {
    public static class MapleLeavesProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public MapleLeavesProvider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Override
        public @Nullable Particle createParticle(SimpleParticleType options, ClientLevel level, double x, double y, double z, double velocityX, double velocityY, double velocityZ, RandomSource random) {
            return new FallingLeavesParticle(level, x, y, z, this.sprites.get(random), 0.07F, 5.0F, true, false, 1.5F, 0.021F);
        }
    }
}
