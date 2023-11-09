package fr.hugman.promenade.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

@Environment(value = EnvType.CLIENT)
public class FallingLeafParticle extends SpriteBillboardParticle {
    private static final float field_43372 = 0.0025f;
    private static final int field_43373 = 300;
    private static final int field_43366 = 300;
    private static final float field_43367 = 0.25f;
    private static final float field_43368 = 2.0f;
    private float field_43369;
    private final float field_43370;
    private final float field_43371;

    public FallingLeafParticle(ClientWorld world, double x, double y, double z, SpriteProvider spriteProvider) {
        super(world, x, y, z);
        float f;
        this.setSprite(spriteProvider.getSprite(this.random.nextInt(12), 12));
        this.field_43369 = (float)Math.toRadians(this.random.nextBoolean() ? -30.0 : 30.0);
        this.field_43370 = this.random.nextFloat();
        this.field_43371 = (float)Math.toRadians(this.random.nextBoolean() ? -5.0 : 5.0);
        this.maxAge = 300;
        this.gravityStrength = 7.5E-4f;
        this.scale = f = this.random.nextBoolean() ? 0.05f : 0.075f;
        this.setBoundingBoxSpacing(f, f);
        this.velocityMultiplier = 1.0f;
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }


    @Override
    public void tick() {
        this.prevPosX = this.x;
        this.prevPosY = this.y;
        this.prevPosZ = this.z;
        if (this.maxAge-- <= 0) {
            this.markDead();
        }
        if (this.dead) {
            return;
        }
        float f = 300 - this.maxAge;
        float g = Math.min(f / 300.0f, 1.0f);
        double d = Math.cos(Math.toRadians(this.field_43370 * 60.0f)) * 2.0 * Math.pow(g, 1.25);
        double e = Math.sin(Math.toRadians(this.field_43370 * 60.0f)) * 2.0 * Math.pow(g, 1.25);
        this.velocityX += d * (double)field_43372;
        this.velocityZ += e * (double)field_43372;
        this.velocityY -= this.gravityStrength;
        this.field_43369 += this.field_43371 / 20.0f;
        this.prevAngle = this.angle;
        this.angle += this.field_43369 / 20.0f;
        this.move(this.velocityX, this.velocityY, this.velocityZ);
        if (this.onGround || this.maxAge < 299 && (this.velocityX == 0.0 || this.velocityZ == 0.0)) {
            this.markDead();
        }
        if (this.dead) {
            return;
        }
        this.velocityX *= this.velocityMultiplier;
        this.velocityY *= this.velocityMultiplier;
        this.velocityZ *= this.velocityMultiplier;
    }

    @Environment(value = EnvType.CLIENT)
    public record BlossomFactory(SpriteProvider spriteProvider) implements ParticleFactory<DefaultParticleType> {
        @Override
        public Particle createParticle(DefaultParticleType defaultParticleType, ClientWorld clientWorld, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
            FallingLeafParticle particle = new FallingLeafParticle(clientWorld, x, y, z, this.spriteProvider);
            //particle.bobbingAmplitude = MathHelper.nextBetween(clientWorld.random, 0.9f, 1.2f);
            //particle.maxAge = MathHelper.nextBetween(clientWorld.random, 500, 1000);
            return particle;
        }
    }

    @Environment(value = EnvType.CLIENT)
    public record MapleLeafFactory(SpriteProvider spriteProvider) implements ParticleFactory<DefaultParticleType> {
        @Override
        public Particle createParticle(DefaultParticleType defaultParticleType, ClientWorld clientWorld, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
            FallingLeafParticle particle = new FallingLeafParticle(clientWorld, x, y, z, this.spriteProvider);
            //particle.bobbingAmplitude = MathHelper.nextBetween(world.random, 0.6f, 0.8f);
            //particle.maxAge = MathHelper.nextBetween(world.random, 500, 1000);
            //particle.scale *= world.random.nextFloat() * 0.1f + 1.4f;
            return particle;
        }
    }
}