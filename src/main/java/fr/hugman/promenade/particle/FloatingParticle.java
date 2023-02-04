package fr.hugman.promenade.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.ParticleGroup;
import net.minecraft.client.particle.ParticleTextureSheet;
import net.minecraft.client.particle.SpriteBillboardParticle;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.math.MathHelper;

import java.util.Optional;

@Environment(value = EnvType.CLIENT)
public class FloatingParticle extends SpriteBillboardParticle {
	private static final int FADE_IN = 50;
	private static final int FADE_OUT = 100;
	private static final int GROUND_ALIVE = 100;

	private float bobbingAmplitude = 1.0F;
	private final float bobbingSpeed = 0.1F;

	public FloatingParticle(ClientWorld world, SpriteProvider spriteProvider, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
		super(world, x, y - 0.125, z, velocityX, velocityY, velocityZ);
		this.setBoundingBoxSpacing(0.01f, 0.01f);
		this.setSprite(spriteProvider);
		this.scale *= this.random.nextFloat() * 0.2f + 0.4f;
		this.maxAge = (int) (16.0 / (Math.random() * 0.8 + 0.2));
		this.collidesWithWorld = true;
		this.velocityMultiplier = 1.0f;
		this.gravityStrength = 0.01f;
		this.alpha = 0.0f;
	}

	@Override
	public void tick() {
		super.tick();
		this.prevAngle = this.angle;
		if(!this.onGround) {
			this.angle = (float) Math.cos(this.age * this.bobbingSpeed) * this.bobbingAmplitude;
		}
		if(this.age <= FADE_IN) {
			this.alpha = this.age / (float) FADE_IN;
		}
		if(this.maxAge - this.age <= FADE_OUT) {
			this.alpha = this.maxAge - this.age / (float) FADE_OUT;
		}
		else if(this.onGround && this.maxAge >= this.age + FADE_OUT + GROUND_ALIVE) {
			this.maxAge = this.age + FADE_OUT + GROUND_ALIVE;
		}
	}

	@Override
	public ParticleTextureSheet getType() {
		return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
	}

	@Environment(value = EnvType.CLIENT)
	public record CherryBlossomFactory(SpriteProvider spriteProvider) implements ParticleFactory<DefaultParticleType> {
		@Override
		public Particle createParticle(DefaultParticleType defaultParticleType, ClientWorld clientWorld, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
			FloatingParticle particle = new FloatingParticle(clientWorld, this.spriteProvider, x, y, z, 0.0f, -0.8f, 0.0f) {
				@Override
				public Optional<ParticleGroup> getGroup() {
					return Optional.of(ParticleGroup.SPORE_BLOSSOM_AIR);
				}
			};
			particle.bobbingAmplitude = MathHelper.nextBetween(clientWorld.random, 0.9f, 1.2f);
			particle.maxAge = MathHelper.nextBetween(clientWorld.random, 500, 1000);
			return particle;
		}
	}


	@Environment(value = EnvType.CLIENT)
	public record MapleLeafFactory(SpriteProvider spriteProvider) implements ParticleFactory<DefaultParticleType> {
		@Override
		public Particle createParticle(DefaultParticleType type, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
			FloatingParticle particle = new FloatingParticle(world, this.spriteProvider, x, y, z, 0.0f, -0.8f, 0.0f) {
				@Override
				public Optional<ParticleGroup> getGroup() {
					return Optional.of(ParticleGroup.SPORE_BLOSSOM_AIR);
				}
			};
			particle.bobbingAmplitude = MathHelper.nextBetween(world.random, 0.6f, 0.8f);
			particle.maxAge = MathHelper.nextBetween(world.random, 500, 1000);
			particle.scale *= world.random.nextFloat() * 0.1f + 1.4f;
			return particle;
		}
	}
}