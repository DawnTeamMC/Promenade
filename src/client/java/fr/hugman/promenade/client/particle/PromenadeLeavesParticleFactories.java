package fr.hugman.promenade.client.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.LeavesParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.util.math.random.Random;
import org.jetbrains.annotations.Nullable;

public class PromenadeLeavesParticleFactories {
    @Environment(EnvType.CLIENT)
    public static class MapleLeavesFactory implements ParticleFactory<SimpleParticleType> {
        private final SpriteProvider spriteProvider;

        public MapleLeavesFactory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Override
        public @Nullable Particle createParticle(SimpleParticleType parameters, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, Random random) {
            return new LeavesParticle(world, x, y, z, this.spriteProvider.getSprite(random), 0.07F, 5.0F, true, false, 1.5F, 0.021F);
        }
    }
}
